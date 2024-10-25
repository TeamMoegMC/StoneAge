package com.yanny.age.stone.blocks;

import com.yanny.age.stone.config.Config;
import com.yanny.age.stone.subscribers.TileEntitySubscriber;
import com.yanny.ages.api.utils.ItemStackUtils;
import com.yanny.ages.api.utils.Tags;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.*;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.wrapper.RecipeWrapper;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

public class FishingNetTileEntity extends TileEntity implements IInventoryInterface, ITickableTileEntity, INamedContainerProvider {

    static final int INVENTORY_WIDTH = 5;
    static final int INVENTORY_HEIGHT = 3;
    static final int ITEMS = 1 + INVENTORY_WIDTH * INVENTORY_HEIGHT;

    private final NonNullList<ItemStack> stacks = NonNullList.withSize(ITEMS, ItemStack.EMPTY);
    private final IItemHandlerModifiable nonSidedItemHandler = createInventoryHandler(stacks);
    private final LazyOptional<IItemHandlerModifiable> sidedInventoryHandler = LazyOptional.of(() -> createInventoryHandler(stacks));
    private final LazyOptional<IItemHandlerModifiable> nonSidedInventoryHandler = LazyOptional.of(() -> nonSidedItemHandler);
    private final RecipeWrapper inventoryWrapper = new RecipeWrapper(nonSidedItemHandler);

    public FishingNetTileEntity() {
        //noinspection ConstantConditions
        super(TileEntitySubscriber.fishing_net);
    }

    @Override
    public void tick() {
        if (level != null && !level.isClientSide) {
            if (!stacks.get(0).isEmpty() && level.random.nextInt(Config.fishingNetChance) == 0 && hasWaterAround()) {
                if (stacks.get(0).hurt(1, level.random, null)) {
                    stacks.set(0, ItemStack.EMPTY);
                    level.setBlockAndUpdate(getBlockPos(), getBlockState().setValue(BlockStateProperties.ATTACHED, false));
                }

                generateLoot();
                level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), 3);
            }
        }
    }

    @Nullable
    @Override
    public Container createMenu(int id, @Nonnull PlayerInventory inventory, @Nonnull PlayerEntity entity) {
        assert level != null;
        return new FishingNetContainer(id, worldPosition, level, inventory, entity);
    }

    @Nonnull
    @Override
    public ITextComponent getDisplayName() {
        return new TranslationTextComponent("block.stone_age.fishing_net");
    }

    @Nonnull
    @Override
    public IInventory getInventory() {
        return inventoryWrapper;
    }

    @Override
    public void load(@Nonnull BlockState blockState, CompoundNBT tag) {
        CompoundNBT invTag = tag.getCompound("inv");
        ItemStackUtils.deserializeStacks(invTag, stacks);
        super.load(blockState, tag);
    }

    @Override
    @Nonnull
    public CompoundNBT save(CompoundNBT tag) {
        tag.put("inv", ItemStackUtils.serializeStacks(stacks));
        return super.save(tag);
    }

    @Nullable
    @Override
    public SUpdateTileEntityPacket getUpdatePacket() {
        return new SUpdateTileEntityPacket(getBlockPos(), getType().hashCode(), getUpdateTag());
    }

    @Nonnull
    @Override
    public CompoundNBT getUpdateTag() {
        return save(new CompoundNBT());
    }

    @Override
    public void onDataPacket(NetworkManager net, SUpdateTileEntityPacket pkt) {
        super.onDataPacket(net, pkt);
        load(getBlockState(), pkt.getTag());
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        if (cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            if (side != null) {
                if (side == Direction.DOWN || side == Direction.UP) {
                    return sidedInventoryHandler.cast();
                }
            } else {
                return nonSidedInventoryHandler.cast();
            }
        }
        return super.getCapability(cap, side);
    }

    @Override
    public void setRemoved() {
        sidedInventoryHandler.invalidate();
        nonSidedInventoryHandler.invalidate();
        super.setRemoved();
    }

    public void updateState() {
        assert level != null;
        level.setBlockAndUpdate(getBlockPos(), getBlockState().setValue(BlockStateProperties.ATTACHED, !stacks.get(0).isEmpty()));
    }

    private void generateLoot() {
        assert level != null;
        LootTable lootTable = ((ServerWorld) level).getServer().getLootTables().get(LootTables.FISHING);
        LootContext lootContext = new LootContext.Builder((ServerWorld) level)
                .withParameter(LootParameters.ORIGIN, Vector3d.atBottomCenterOf(getBlockPos()))
                .withParameter(LootParameters.TOOL, stacks.get(0))
                .create(LootParameterSets.FISHING);

        List<ItemStack> loot = lootTable.getRandomItems(lootContext);
        ItemStackUtils.insertItems(loot, stacks, 1, ITEMS);
    }

    @Nonnull
    private IItemHandlerModifiable createInventoryHandler(@Nonnull NonNullList<ItemStack> stacks) {
        return new ItemStackHandler(stacks) {

            @Nonnull
            @Override
            public ItemStack insertItem(int slot, @Nonnull ItemStack stack, boolean simulate) {
                if (slot == 0) {
                    assert level != null;
                    level.setBlockAndUpdate(getBlockPos(), getBlockState().setValue(BlockStateProperties.ATTACHED, true));
                }

                return super.insertItem(slot, stack, simulate);
            }

            @Nonnull
            @Override
            public ItemStack extractItem(int slot, int amount, boolean simulate) {
                if (slot == 0) {
                    assert level != null;
                    level.setBlockAndUpdate(getBlockPos(), getBlockState().setValue(BlockStateProperties.ATTACHED, false));
                }

                return super.extractItem(slot, amount, simulate);
            }

            @Override
            public boolean isItemValid(int slot, @Nonnull ItemStack stack) {
                return (slot == 0) && Tags.Items.FISHING_NET_MESHES.contains(stack.getItem());
            }

            @Override
            protected void onContentsChanged(int slot) {
                assert level != null;
                setChanged();
                level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), 3);
            }
        };
    }

    private boolean hasWaterAround() {
        assert level != null;

        for (Direction direction : Direction.Plane.HORIZONTAL) {
            FluidState fluidState = level.getFluidState(worldPosition.relative(direction));

            if (!fluidState.isSource() || fluidState.getType() != Fluids.WATER) {
                return false;
            }
        }

        return true;
    }
}
