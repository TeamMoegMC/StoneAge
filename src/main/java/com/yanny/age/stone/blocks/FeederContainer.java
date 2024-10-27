package com.yanny.age.stone.blocks;

import com.yanny.age.stone.ExampleMod;
import com.yanny.age.stone.subscribers.BlockSubscriber;
import com.yanny.age.stone.subscribers.ContainerSubscriber;
import com.yanny.age.stone.utils.ContainerUtils;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.items.SlotItemHandler;

import javax.annotation.Nonnull;

import java.util.Objects;

import static com.yanny.age.stone.blocks.FeederTileEntity.ITEMS;

public class FeederContainer extends AbstractContainerMenu {
    private final FeederTileEntity tile;
    private final Player player;

    public FeederContainer(int windowId, @Nonnull Inventory inv, @Nonnull FriendlyByteBuf extraData) {
        this(windowId, extraData.readBlockPos(), Objects.requireNonNull(ExampleMod.proxy.getClientWorld()), inv, Objects.requireNonNull(ExampleMod.proxy.getClientPlayer()));
    }

    FeederContainer(int id, @Nonnull BlockPos pos, @Nonnull Level world, @Nonnull Inventory inventory, @Nonnull Player player) {
        super(ContainerSubscriber.feeder, id);
        tile = (FeederTileEntity) world.getBlockEntity(pos);
        this.player = player;

        if (tile == null) {
            throw new IllegalStateException("TileEntity does not exists!");
        }

        tile.getCapability(ForgeCapabilities.ITEM_HANDLER).ifPresent(h -> {
            for (int x = 0; x < ITEMS; x++) {
                addSlot(new SlotItemHandler(h, x, 53 + x * 18, 35));
            }
        });

        ContainerUtils.layoutPlayerInventorySlots(((slot, x, y) -> addSlot(new Slot(inventory, slot, x, y))), 8, 84);
    }

    @SuppressWarnings("ConstantConditions")
    @Override
    public boolean stillValid(@Nonnull Player playerIn) {
        if (tile == null || tile.getLevel() == null) {
            throw new IllegalStateException("Null pointer");
        }

        return stillValid(ContainerLevelAccess.create(tile.getLevel(), tile.getBlockPos()), player, BlockSubscriber.feeder);
    }

    @Nonnull
    @Override
    public ItemStack quickMoveStack(@Nonnull Player playerIn, int index) {
        Slot slot = slots.get(index);

        if (slot != null && slot.hasItem()) {
            ItemStack stack = slot.getItem();
            ItemStack itemstack = stack.copy();

            if (index < ITEMS) {
                if (!moveItemStackTo(stack, ITEMS + 1, ITEMS + 36, true)) {
                    return ItemStack.EMPTY;
                }

                slot.onQuickCraft(stack, itemstack);
            } else {
                if (tile.isItemValid(stack) && !moveItemStackTo(stack, 0, ITEMS, false)) {
                    return ItemStack.EMPTY;
                }
            }

            if (stack.isEmpty()) {
                slot.set(ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }

            if (stack.getCount() == itemstack.getCount()) {
                return ItemStack.EMPTY;
            }

            slot.onTake(playerIn, stack);
        }

        return ItemStack.EMPTY;
    }

    @Override
    public void removed(@Nonnull Player playerIn) {
        super.removed(playerIn);
    }
}
