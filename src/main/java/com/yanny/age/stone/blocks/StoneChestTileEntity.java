package com.yanny.age.stone.blocks;

import com.yanny.age.stone.api.utils.ItemStackUtils;
import com.yanny.age.stone.subscribers.TileEntitySubscriber;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.Container;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.level.block.entity.LidBlockEntity;
import net.minecraft.world.level.block.entity.RandomizableContainerBlockEntity;
import net.minecraft.util.*;
import net.minecraft.world.phys.AABB;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.wrapper.RecipeWrapper;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;

@OnlyIn(value = Dist.CLIENT, _interface = LidBlockEntity.class)
public class StoneChestTileEntity extends RandomizableContainerBlockEntity implements IInventoryInterface, MenuProvider, LidBlockEntity {
    static final int INVENTORY_WIDTH = 5;
    static final int INVENTORY_HEIGHT = 3;

    private NonNullList<ItemStack> stacks = NonNullList.create();
    private final IItemHandlerModifiable nonSidedItemHandler = createNonSidedInventoryHandler(stacks);
    private final LazyOptional<IItemHandlerModifiable> nonSidedInventoryHandler = LazyOptional.of(() -> nonSidedItemHandler);
    private final RecipeWrapper inventoryWrapper = new RecipeWrapper(nonSidedItemHandler);
    private float lidAngle;
    private float prevLidAngle;
    private int numPlayersUsing;
    private int ticksSinceSync;

    public StoneChestTileEntity(BlockPos pos, BlockState state) {
        //noinspection ConstantConditions
        super(TileEntitySubscriber.stone_chest,pos,state);
        for (int i = 0; i < INVENTORY_WIDTH * INVENTORY_HEIGHT; i++) {
            stacks.add(ItemStack.EMPTY);
        }
    }

    public static void tick(Level level, BlockPos blockPos, BlockState state, StoneChestTileEntity tile) {
        assert level != null;
        int i = blockPos.getX();
        int j = blockPos.getY();
        int k = blockPos.getZ();

        ++tile.ticksSinceSync;
        tile.numPlayersUsing = calculatePlayersUsingSync(level, tile, tile.ticksSinceSync, i, j, k, tile.numPlayersUsing);
        tile.prevLidAngle = tile.lidAngle;

        if (tile.numPlayersUsing > 0 && tile.lidAngle == 0.0F) {
            tile.playSound();
        }

        if ((tile.numPlayersUsing == 0 && tile.lidAngle > 0.0F) || (tile.numPlayersUsing > 0 && tile.lidAngle < 1.0F)) {
            float f1 = tile.lidAngle;
            if (tile.numPlayersUsing > 0) {
                tile.lidAngle += 0.05F;
            } else {
                tile.lidAngle -= 0.05F;
            }

            if (tile.lidAngle > 1.0F) {
                tile.lidAngle = 1.0F;
            }

            if (tile.lidAngle < 0.5F && f1 >= 0.5F) {
                tile.playSound();
            }

            if (tile.lidAngle < 0.0F) {
                tile.lidAngle = 0.0F;
            }
        }
    }

    @Nonnull
    @Override
    public Container getInventory() {
        return inventoryWrapper;
    }

    @Override
    public void load(CompoundTag tag) {
        CompoundTag invTag = tag.getCompound("inv");

        if (!this.tryLoadLootTable(tag)) {
            ItemStackUtils.deserializeStacks(invTag, stacks);
        }

        super.load(tag);
    }

    @Override
    @Nonnull
    public void saveAdditional(@Nonnull CompoundTag tag) {
        if (!this.trySaveLootTable(tag)) {
            tag.put("inv", ItemStackUtils.serializeStacks(stacks));
        }

        super.saveAdditional(tag);
    }

    @Nullable
    @Override
    public ClientboundBlockEntityDataPacket getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Nonnull
    @Override
    public CompoundTag getUpdateTag() {
        return saveWithoutMetadata();
    }

    @Override
    public void onDataPacket(Connection net, ClientboundBlockEntityDataPacket pkt) {
        super.onDataPacket(net, pkt);
        this.load(pkt.getTag());
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        if (cap == ForgeCapabilities.ITEM_HANDLER) {
            return nonSidedInventoryHandler.cast();
        }
        return super.getCapability(cap, side);
    }

    @Override
    public void setRemoved() {
        nonSidedInventoryHandler.invalidate();
        super.setRemoved();
    }

    @Nonnull
    @Override
    protected NonNullList<ItemStack> getItems() {
        return stacks;
    }

    @Override
    protected void setItems(@Nonnull NonNullList<ItemStack> itemsIn) {
        stacks = itemsIn;
    }

    @Nonnull
    @Override
    protected AbstractContainerMenu createMenu(int id, @Nonnull Inventory player) {
        assert level != null;
        return new StoneChestContainer(id, worldPosition, level, player, player.player);
    }

    @Nonnull
    @Override
    public Component getDisplayName() {
        return Component.translatable("block.stone_age.stone_chest");
    }

    @Nonnull
    @Override
    protected Component getDefaultName() {
        return Component.translatable("StoneChest");
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public float getOpenNess(float partialTicks) {
        return Mth.lerp(partialTicks, this.prevLidAngle, this.lidAngle);
    }

    @Override
    public boolean triggerEvent(int id, int type) {
        if (id == 1) {
            this.numPlayersUsing = type;
            return true;
        } else {
            return super.triggerEvent(id, type);
        }
    }

    @Override
    public int getContainerSize() {
        return stacks.size();
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    public void startOpen(@Nonnull Player player) {
        if (!player.isSpectator()) {
            assert level != null;

            if (this.numPlayersUsing < 0) {
                this.numPlayersUsing = 0;
            }

            ++this.numPlayersUsing;
            this.onOpenOrClose();
        }
    }

    public void stopOpen(@Nonnull Player player) {
        if (!player.isSpectator()) {
            --this.numPlayersUsing;
            this.onOpenOrClose();
        }
    }

    private void onOpenOrClose() {
        assert this.level != null;
        Block block = this.getBlockState().getBlock();

        if (block instanceof StoneChestBlock) {
            this.level.blockEvent(this.worldPosition, block, 1, this.numPlayersUsing);
            this.level.updateNeighborsAt(this.worldPosition, block);
        }
    }

    private void playSound() {
        assert this.level != null;
        double d0 = (double)this.worldPosition.getX() + 0.5D;
        double d1 = (double)this.worldPosition.getY() + 0.5D;
        double d2 = (double)this.worldPosition.getZ() + 0.5D;
        this.level.playSound(null, d0, d1, d2, SoundEvents.GRINDSTONE_USE, SoundSource.BLOCKS, 0.5F, level.random.nextFloat() * 0.1F + 0.9F);
    }

    @Nonnull
    private IItemHandlerModifiable createNonSidedInventoryHandler(@Nonnull NonNullList<ItemStack> stacks) {
        return new ItemStackHandler(stacks) {
            @Override
            protected void onContentsChanged(int slot) {
                assert level != null;
                setChanged();
                level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), 3);
            }
        };
    }

    private static int calculatePlayersUsingSync(@Nonnull Level world, @Nonnull StoneChestTileEntity tileEntity, int tickSinceSync, int x, int y, int z, int numUsing) {
        if (!world.isClientSide && numUsing != 0 && (tickSinceSync + x + y + z) % 200 == 0) {
            numUsing = calculatePlayersUsing(world, tileEntity, x, y, z);
        }

        return numUsing;
    }

    private static int calculatePlayersUsing(@Nonnull Level world, @Nonnull StoneChestTileEntity tileEntity, int x, int y, int z) {
        int i = 0;
        float f = 5.0F;

        for(Player playerentity : world.getEntitiesOfClass(Player.class,
                new AABB(x - f, y - f, z - f, (x + 1) + f, (y + 1) + f, (z + 1) + f))) {
            if (playerentity.containerMenu instanceof StoneChestContainer) {
                Container inventory = ((StoneChestContainer)playerentity.containerMenu).getIInventory();

                if (inventory == tileEntity.inventoryWrapper) {
                    ++i;
                }
            }
        }

        return i;
    }
}
