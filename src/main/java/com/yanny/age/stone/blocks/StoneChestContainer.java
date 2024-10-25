package com.yanny.age.stone.blocks;

import com.yanny.age.stone.ExampleMod;
import com.yanny.age.stone.subscribers.BlockSubscriber;
import com.yanny.age.stone.subscribers.ContainerSubscriber;
import com.yanny.age.stone.utils.ContainerUtils;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.IWorldPosCallable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.SlotItemHandler;

import javax.annotation.Nonnull;

import java.util.Objects;

import static com.yanny.age.stone.blocks.StoneChestTileEntity.INVENTORY_HEIGHT;
import static com.yanny.age.stone.blocks.StoneChestTileEntity.INVENTORY_WIDTH;

public class StoneChestContainer extends Container {
    private final StoneChestTileEntity tile;
    private final PlayerEntity player;

    public StoneChestContainer(int windowId, @Nonnull PlayerInventory inv, @Nonnull PacketBuffer extraData) {
        this(windowId, extraData.readBlockPos(), Objects.requireNonNull(ExampleMod.proxy.getClientWorld()), inv, Objects.requireNonNull(ExampleMod.proxy.getClientPlayer()));
    }

    StoneChestContainer(int id, @Nonnull BlockPos pos, @Nonnull World world, @Nonnull PlayerInventory inventory, @Nonnull PlayerEntity player) {
        super(ContainerSubscriber.stone_chest, id);
        tile = (StoneChestTileEntity) world.getBlockEntity(pos);
        this.player = player;

        if (tile == null) {
            throw new IllegalStateException("TileEntity does not exists!");
        }

        tile.startOpen(player);
        tile.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).ifPresent(h -> {
            for (int y = 0; y < INVENTORY_HEIGHT; y++) {
                for (int x = 0; x < INVENTORY_WIDTH; x++) {
                    addSlot(new SlotItemHandler(h, x + y * INVENTORY_WIDTH, 44 + x * 18, 17 + y * 18));
                }
            }
        });

        ContainerUtils.layoutPlayerInventorySlots(((slot, x, y) -> addSlot(new Slot(inventory, slot, x, y))), 8, 84);
    }

    @SuppressWarnings("ConstantConditions")
    @Override
    public boolean stillValid(@Nonnull PlayerEntity playerIn) {
        if (tile == null || tile.getLevel() == null) {
            throw new IllegalStateException("Null pointer");
        }
        return stillValid(IWorldPosCallable.create(tile.getLevel(), tile.getBlockPos()), player, BlockSubscriber.stone_chest);
    }

    @Nonnull
    @Override
    public ItemStack quickMoveStack(@Nonnull PlayerEntity playerIn, int index) {
        Slot slot = slots.get(index);

        if (slot != null && slot.hasItem()) {
            ItemStack stack = slot.getItem();
            ItemStack itemstack = stack.copy();
            if (index < INVENTORY_WIDTH * INVENTORY_HEIGHT) {
                if (!moveItemStackTo(stack, INVENTORY_WIDTH * INVENTORY_HEIGHT + 1, INVENTORY_WIDTH * INVENTORY_HEIGHT + 36, true)) {
                    return ItemStack.EMPTY;
                }
                slot.onQuickCraft(stack, itemstack);
            } else {
                if (!moveItemStackTo(stack, 0, INVENTORY_WIDTH * INVENTORY_HEIGHT, false)) {
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
    public void removed(@Nonnull PlayerEntity playerIn) {
        super.removed(playerIn);
        tile.stopOpen(playerIn);
    }

    @Nonnull
    public IInventory getIInventory() {
        return player.inventory;
    }
}
