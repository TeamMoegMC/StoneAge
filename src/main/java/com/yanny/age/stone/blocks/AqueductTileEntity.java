package com.yanny.age.stone.blocks;

import com.google.common.collect.Maps;
import com.mojang.datafixers.util.Pair;
import com.yanny.age.stone.config.Config;
import com.yanny.age.stone.handlers.AqueductHandler;
import com.yanny.age.stone.subscribers.TileEntitySubscriber;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.block.IGrowable;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.capability.templates.FluidTank;
import org.apache.logging.log4j.LogManager;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.lang.reflect.Array;
import java.util.Map;

import static net.minecraft.state.properties.BlockStateProperties.WATERLOGGED;

public class AqueductTileEntity extends TileEntity implements ITickableTileEntity {
    private static final float LEVELS = 20f;
    private static final FluidStack WATER = new FluidStack(Fluids.WATER, 0);

    private final Map<Direction, Boolean> sources = Maps.newHashMap();

    private boolean activated = false;
    private boolean initialized = false;
    private float capacity = 0f;
    private int level = 0;
    private int filled = 0;
    private int fullCapacity = 0;
    private int drainedAmount = 0;

    public AqueductTileEntity() {
        //noinspection ConstantConditions
        super(TileEntitySubscriber.aqueduct);
        sources.put(Direction.NORTH, false);
        sources.put(Direction.SOUTH, false);
        sources.put(Direction.EAST, false);
        sources.put(Direction.WEST, false);
    }

    @Override
    public void tick() {
        assert world != null;

        if (!initialized) {
            initialized = true;

            HorizontalBlock.HORIZONTAL_FACING.getAllowedValues().forEach(direction -> {
                BlockPos pos = getPos().offset(direction);
                setSource(direction, AqueductBlock.isWater(world.getBlockState(pos).getBlock()));
            });
        }

        if (!world.isRemote) {
            LazyOptional<FluidTank> fluidTank = AqueductHandler.getInstance(world).getCapability(pos);
            fluidTank.ifPresent(tank -> {
                int oldLevel = level;

                filled = tank.getFluidAmount();
                fullCapacity = tank.getCapacity();
                level = Math.round((float)tank.getFluidAmount() / tank.getCapacity() * LEVELS);

                if (oldLevel != level) {
                    capacity = level / LEVELS;
                    world.notifyBlockUpdate(pos, getBlockState(), getBlockState(), 3);
                }

                LazyOptional<FluidTank> upperTank = AqueductHandler.getInstance(world).getCapability(pos.up());

                if (sources.containsValue(true)) {
                    activated = false;
                    WATER.setAmount(Config.aqueductFillPerTick);
                    drainedAmount += tank.fill(WATER, IFluidHandler.FluidAction.EXECUTE);
                } else if (upperTank.isPresent()) {
                    upperTank.ifPresent(upTank -> {
                        activated = false;
                        if (tank.getSpace() >= Config.aqueductFillPerTick) {
                            drainedAmount += tank.fill(upTank.drain(Config.aqueductFillPerTick, IFluidHandler.FluidAction.EXECUTE), IFluidHandler.FluidAction.EXECUTE);
                        }
                    });
                } else {
                    if (activated) {
                        FluidStack fluidStack = tank.drain(Config.aqueductUsePerTick, IFluidHandler.FluidAction.EXECUTE);

                        if (fluidStack.isEmpty()) {
                            if (world.getBlockState(pos).get(WATERLOGGED)) {
                                world.setBlockState(pos, world.getBlockState(pos).with(WATERLOGGED, false));
                            }
                        } else {
                            if (!world.getBlockState(pos).get(WATERLOGGED)) {
                                world.setBlockState(pos, world.getBlockState(pos).with(WATERLOGGED, true));
                            }

                            if (world.rand.nextInt(Config.aqueductTickChanceBoneMealEffect) == 0) {
                                boneMealEffect(pos, (ServerWorld)world);
                            }
                        }
                    } else {
                        if (world.getBlockState(pos).get(WATERLOGGED)) {
                            world.setBlockState(pos, world.getBlockState(pos).with(WATERLOGGED, false));
                        }
                    }
                }

                if (Config.aqueductRemoveWaterSource && drainedAmount / 1000 > 0) {
                    drainedAmount = drainedAmount % 1000;

                    BlockPos blockPos = findWaterSource(world, pos);

                    if (blockPos != null) {
                        System.out.println(blockPos);
                        world.setBlockState(blockPos, Blocks.AIR.getDefaultState());
                    }
                }
            });
        }
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        if (world != null && !world.isRemote && cap == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY) {
            return AqueductHandler.getInstance(world).getCapability(pos);
        }

        return super.getCapability(cap, side);
    }

    @Override
    public void read(@Nonnull BlockState blockState, CompoundNBT tag) {
        capacity = tag.getFloat("capacity");
        activated = tag.getBoolean("activated");
        drainedAmount = tag.getInt("drainedAmount");
        super.read(blockState, tag);
    }

    @Override
    @Nonnull
    public CompoundNBT write(CompoundNBT tag) {
        tag.putFloat("capacity", capacity);
        tag.putBoolean("activated", activated);
        tag.putInt("drainedAmount", drainedAmount);
        return super.write(tag);
    }

    @Nullable
    @Override
    public SUpdateTileEntityPacket getUpdatePacket() {
        return new SUpdateTileEntityPacket(getPos(), getType().hashCode(), getUpdateTag());
    }

    @Nonnull
    @Override
    public CompoundNBT getUpdateTag() {
        return write(new CompoundNBT());
    }

    @Override
    public void onDataPacket(NetworkManager net, SUpdateTileEntityPacket pkt) {
        super.onDataPacket(net, pkt);
        read(getBlockState(), pkt.getNbtCompound());
    }

    @Override
    public void validate() {
        super.validate();

        if (world != null && !world.isRemote) {
            AqueductHandler.getInstance(world).register(pos);
        }
    }

    @Override
    public void remove() {
        if (world != null && !world.isRemote) {
            AqueductHandler.getInstance(world).remove(pos);
        }

        super.remove();
    }

    public float getCapacity() {
        return capacity;
    }

    public int getFilled() {
        return filled;
    }

    public int getFullCapacity() {
        return fullCapacity;
    }

    void setSource(@Nonnull Direction direction, boolean isSource) {
        sources.replace(direction, isSource);
    }

    void changedState() {
        assert world != null;
        activated = !activated;
    }

    private static void boneMealEffect(@Nonnull BlockPos pos, @Nonnull ServerWorld world) {
        int r = Config.aqueductEffectRange;
        BlockPos cropPos = pos.up().north(world.rand.nextInt(r * 2 + 1) - r).east(world.rand.nextInt(r * 2 + 1) - r);
        BlockState blockstate = world.getBlockState(cropPos);

        if (blockstate.getBlock() instanceof IGrowable) {
            IGrowable igrowable = (IGrowable)blockstate.getBlock();

            if (igrowable.canGrow(world, cropPos, blockstate, world.isRemote) && igrowable.canUseBonemeal(world, world.rand, cropPos, blockstate)) {
                igrowable.grow(world, world.rand, cropPos, blockstate);
                world.playEvent(2005, cropPos, 0);

                for(int i = 0; i < 5; ++i) {
                    double d0 = world.rand.nextGaussian() * 0.02D;
                    double d1 = world.rand.nextGaussian() * 0.02D;
                    double d2 = world.rand.nextGaussian() * 0.02D;
                    world.addParticle(ParticleTypes.HAPPY_VILLAGER,
                            cropPos.getX() + world.rand.nextFloat(),
                            cropPos.getY() + world.rand.nextFloat() * blockstate.getShape(world, cropPos).getEnd(Direction.Axis.Y),
                            cropPos.getZ() + world.rand.nextFloat(),
                            d0, d1, d2);
                }
            }
        }
    }

    @Nullable
    private BlockPos findWaterSource(IWorldReader reader, BlockPos pos) {
        Pair<BlockPos, BlockState>[] aqueduct = getBlockStatesAround(reader, pos);
        Pair<BlockPos, Integer> level = getHighestWaterLevel(aqueduct);
        int cnt = 0;

        while(level != null && level.getSecond() < 8) {
            aqueduct = getBlockStatesAround(reader, level.getFirst());
            level = getHighestWaterLevel(aqueduct);

            if (cnt++ > 64) {
                LogManager.getLogger(AqueductTileEntity.class.getSimpleName()).warn("Skipping search for water source from {}", pos);
                return null;
            }
        }

        return level == null ? null : level.getFirst();
    }

    @SuppressWarnings("unchecked")
    @Nonnull
    private Pair<BlockPos, BlockState>[] getBlockStatesAround(IWorldReader reader, BlockPos pos) {
        Pair<BlockPos, BlockState>[] around = (Pair<BlockPos, BlockState>[]) Array.newInstance(Pair.class, 4);

        Direction.Plane.HORIZONTAL.forEach(direction -> {
            BlockPos p = pos.offset(direction);
            around[direction.getHorizontalIndex()] = new Pair<>(p, reader.getBlockState(p));
        });

        return around;
    }

    @Nullable
    private Pair<BlockPos, Integer> getHighestWaterLevel(Pair<BlockPos, BlockState>[] pairs) {
        Pair<BlockPos, Integer> result = null;

        for (Pair<BlockPos, BlockState> pair : pairs) {
            FluidState state = pair.getSecond().getFluidState();
            if (state.getFluid() == Fluids.WATER || state.getFluid() == Fluids.FLOWING_WATER) {
                int level = state.getFluid() == Fluids.WATER ? 8 : state.getLevel();

                if (result == null || result.getSecond() < level) {
                    result = new Pair<>(pair.getFirst(), level);
                }
            }
        }

        return result;
    }
}
