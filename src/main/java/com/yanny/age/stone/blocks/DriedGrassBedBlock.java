package com.yanny.age.stone.blocks;

import com.yanny.age.stone.config.Config;
import net.minecraft.block.BedBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.merchant.villager.VillagerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.DyeColor;
import net.minecraft.state.properties.BedPart;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.Explosion;
import net.minecraft.world.ExplosionContext;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

import net.minecraft.block.AbstractBlock.Properties;

public class DriedGrassBedBlock extends BedBlock {
    private static final VoxelShape NORTH = Block.box(2.0D, 0.0D, 2.0D, 14.0D, 3.0D, 16.0D);
    private static final VoxelShape SOUTH = Block.box(2.0D, 0.0D, 0.0D, 14.0D, 3.0D, 14.0D);
    private static final VoxelShape WEST = Block.box(2.0D, 0.0D, 2.0D, 16.0D, 3.0D, 14.0D);
    private static final VoxelShape EAST = Block.box(0.0D, 0.0D, 2.0D, 14.0D, 3.0D, 14.0D);

    public DriedGrassBedBlock() {
        super(DyeColor.BLACK, Properties.of(Material.WOOL).strength(2.0f));
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new DriedGrassBedTileEntity();
    }

    @Override
    public TileEntity newBlockEntity(@Nonnull IBlockReader worldIn) {
        return new DriedGrassBedTileEntity();
    }

    @SuppressWarnings("deprecation")
    @Nonnull
    @Override
    public BlockRenderType getRenderShape(@Nonnull BlockState state) {
        return BlockRenderType.MODEL;
    }

    @Nonnull
    @Override
    public VoxelShape getShape(BlockState state, @Nonnull IBlockReader worldIn, @Nonnull BlockPos pos, @Nonnull ISelectionContext context) {
        Direction direction = state.getValue(FACING);
        Direction direction1 = state.getValue(PART) == BedPart.HEAD ? direction : direction.getOpposite();
        switch(direction1) {
            case NORTH:
                return NORTH;
            case SOUTH:
                return SOUTH;
            case WEST:
                return WEST;
            case EAST:
                return EAST;
        }

        throw new IllegalStateException();
    }

    @Override
    public ActionResultType use(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        if (worldIn.isClientSide) {
            return ActionResultType.CONSUME;
        } else {
            if (state.getValue(PART) != BedPart.HEAD) {
                pos = pos.relative(state.getValue(FACING));
                state = worldIn.getBlockState(pos);
                if (!state.is(this)) {
                    return ActionResultType.CONSUME;
                }
            }

            if (!canSetSpawn(worldIn)) {
                worldIn.removeBlock(pos, false);
                BlockPos blockpos = pos.relative(state.getValue(FACING).getOpposite());
                if (worldIn.getBlockState(blockpos).is(this)) {
                    worldIn.removeBlock(blockpos, false);
                }

                worldIn.explode((Entity) null, DamageSource.badRespawnPointExplosion(), (ExplosionContext) null, (double) pos.getX() + 0.5D, (double) pos.getY() + 0.5D, (double) pos.getZ() + 0.5D, 5.0F, true, Explosion.Mode.DESTROY);
                return ActionResultType.SUCCESS;
            } else if (state.getValue(OCCUPIED)) {
                if (!this.tryWakeUpVillager(worldIn, pos)) {
                    player.displayClientMessage(new TranslationTextComponent("block.minecraft.bed.occupied"), true);
                }

                return ActionResultType.SUCCESS;
            } else {
                if (Config.GrassBedSleep) {
                    player.startSleepInBed(pos).ifLeft((result) -> {
                        if (result != null) {
                            player.displayClientMessage(result.getMessage(), true);
                        }

                    });
                } else {
                    ((ServerPlayerEntity) player).setRespawnPosition(worldIn.dimension(), pos, player.yRot, false, true);
                    player.displayClientMessage(new TranslationTextComponent("block.stone_age.bed.cantsleep"), true);
                }
                return ActionResultType.SUCCESS;
            }
        }
    }

    private boolean tryWakeUpVillager(World world, BlockPos pos) {
        List<VillagerEntity> list = world.getEntitiesOfClass(VillagerEntity.class, new AxisAlignedBB(pos), LivingEntity::isSleeping);
        if (list.isEmpty()) {
            return false;
        } else {
            list.get(0).stopSleeping();
            return true;
        }
    }
}
