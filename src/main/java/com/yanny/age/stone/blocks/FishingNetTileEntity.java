package com.yanny.age.stone.blocks;

import com.yanny.age.stone.api.utils.ItemStackUtils;
import com.yanny.age.stone.api.utils.Tags;
import com.yanny.age.stone.config.Config;
import com.yanny.age.stone.subscribers.TileEntitySubscriber;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.Container;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.item.ItemStack;
import net.minecraft.loot.*;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.entity.TickableBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.world.phys.Vec3;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.server.level.ServerLevel;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.wrapper.RecipeWrapper;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;

public class FishingNetTileEntity extends BlockEntity implements IInventoryInterface, TickableBlockEntity, MenuProvider {

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
    public AbstractContainerMenu createMenu(int id, @Nonnull Inventory inventory, @Nonnull Player entity) {
        assert level != null;
        return new FishingNetContainer(id, worldPosition, level, inventory, entity);
    }

    @Nonnull
    @Override
    public Component getDisplayName() {
        return new TranslatableComponent("block.stone_age.fishing_net");
    }

    @Nonnull
    @Override
    public Container getInventory() {
        return inventoryWrapper;
    }

    @Override
    public void load(@Nonnull BlockState blockState, CompoundTag tag) {
        CompoundTag invTag = tag.getCompound("inv");
        ItemStackUtils.deserializeStacks(invTag, stacks);
        super.load(blockState, tag);
    }

    @Override
    @Nonnull
    public CompoundTag save(CompoundTag tag) {
        tag.put("inv", ItemStackUtils.serializeStacks(stacks));
        return super.save(tag);
    }

    @Nullable
    @Override
    public ClientboundBlockEntityDataPacket getUpdatePacket() {
        return new ClientboundBlockEntityDataPacket(getBlockPos(), getType().hashCode(), getUpdateTag());
    }

    @Nonnull
    @Override
    public CompoundTag getUpdateTag() {
        return save(new CompoundTag());
    }

    @Override
    public void onDataPacket(Connection net, ClientboundBlockEntityDataPacket pkt) {
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
        LootTable lootTable = ((ServerLevel) level).getServer().getLootTables().get(BuiltInLootTables.FISHING);
        LootContext lootContext = new LootContext.Builder((ServerLevel) level)
                .withParameter(LootContextParams.ORIGIN, Vec3.atBottomCenterOf(getBlockPos()))
                .withParameter(LootContextParams.TOOL, stacks.get(0))
                .create(LootContextParamSets.FISHING);

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
