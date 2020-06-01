package com.yanny.age.stone.blocks;

import com.yanny.age.stone.ExampleMod;
import com.yanny.age.stone.subscribers.BlockSubscriber;
import com.yanny.age.stone.subscribers.ContainerSubscriber;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.IWorldPosCallable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;
import net.minecraftforge.items.wrapper.InvWrapper;

import javax.annotation.Nonnull;

import static com.yanny.age.stone.blocks.FishingNetTileEntity.*;

public class FishingNetContainer extends Container {
    private final FishingNetTileEntity tile;
    private final PlayerEntity player;
    private final IItemHandler inventory;

    public FishingNetContainer(int windowId, PlayerInventory inv, PacketBuffer extraData) {
        this(windowId, extraData.readBlockPos(), ExampleMod.proxy.getClientWorld(), inv, ExampleMod.proxy.getClientPlayer());
    }

    FishingNetContainer(int id, BlockPos pos, World world, PlayerInventory inventory, PlayerEntity player) {
        super(ContainerSubscriber.fishing_net, id);
        tile = (FishingNetTileEntity) world.getTileEntity(pos);
        this.player = player;
        this.inventory = new InvWrapper(inventory);

        if (tile == null) {
            throw new IllegalStateException("TileEntity does not exists!");
        }

        tile.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).ifPresent(h -> {
            addSlot(new SlotItemHandler(h, 0, 26, 35));

            for (int y = 0; y < INVENTORY_HEIGHT; y++) {
                for (int x = 0; x < INVENTORY_WIDTH; x++) {
                    addSlot(new SlotItemHandler(h, 1 + x + y * INVENTORY_WIDTH, 62 + x * 18, 17 + y * 18));
                }
            }
        });
        layoutPlayerInventorySlots(8, 84);
    }

    @Override
    public boolean canInteractWith(@Nonnull PlayerEntity playerIn) {
        if (tile == null || tile.getWorld() == null) {
            throw new IllegalStateException("Null pointer");
        }

        return isWithinUsableDistance(IWorldPosCallable.of(tile.getWorld(), tile.getPos()), player, BlockSubscriber.fishing_net);
    }

    @Nonnull
    @Override
    public ItemStack transferStackInSlot(@Nonnull PlayerEntity playerIn, int index) {
        Slot slot = inventorySlots.get(index);

        if (slot != null && slot.getHasStack()) {
            ItemStack stack = slot.getStack();
            ItemStack itemstack = stack.copy();
            if (index < ITEMS) {
                if (!mergeItemStack(stack, ITEMS + 1, ITEMS + 36, true)) {
                    return ItemStack.EMPTY;
                }

                slot.onSlotChange(stack, itemstack);
            } else {
                if (!mergeItemStack(stack, 0, 1, false)) {
                    return ItemStack.EMPTY;
                }

            }

            tile.updateState();
            if (stack.isEmpty()) {
                slot.putStack(ItemStack.EMPTY);
            } else {
                slot.onSlotChanged();
            }

            if (stack.getCount() == itemstack.getCount()) {
                return ItemStack.EMPTY;
            }

            slot.onTake(playerIn, stack);
        }

        return ItemStack.EMPTY;
    }

    private int addSlotRange(IItemHandler handler, int index, int x, int y, int amount, int dx) {
        for (int i = 0 ; i < amount ; i++) {
            addSlot(new SlotItemHandler(handler, index, x, y));
            x += dx;
            index++;
        }

        return index;
    }

    @SuppressWarnings("SameParameterValue")
    private void addSlotBox(IItemHandler handler, int index, int x, int y, int horAmount, int dx, int verAmount, int dy) {
        for (int j = 0 ; j < verAmount ; j++) {
            index = addSlotRange(handler, index, x, y, horAmount, dx);
            y += dy;
        }
    }

    @SuppressWarnings("SameParameterValue")
    private void layoutPlayerInventorySlots(int leftCol, int topRow) {
        addSlotBox(inventory, 9, leftCol, topRow, 9, 18, 3, 18);

        // Hotbar offset
        topRow += 58;
        addSlotRange(inventory, 0, leftCol, topRow, 9, 18);
    }
}
