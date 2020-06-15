package com.yanny.age.stone.blocks;

import com.yanny.age.stone.subscribers.TileEntitySubscriber;
import com.yanny.ages.api.utils.ItemStackUtils;
import net.minecraft.block.Block;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.IChestLid;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.LockableLootTileEntity;
import net.minecraft.util.*;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.wrapper.RecipeWrapper;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

@OnlyIn(value = Dist.CLIENT, _interface = IChestLid.class)
public class StoneChestTileEntity extends LockableLootTileEntity implements IInventoryInterface, INamedContainerProvider, IChestLid, ITickableTileEntity {
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

    public StoneChestTileEntity() {
        //noinspection ConstantConditions
        super(TileEntitySubscriber.stone_chest);
        for (int i = 0; i < INVENTORY_WIDTH * INVENTORY_HEIGHT; i++) {
            stacks.add(ItemStack.EMPTY);
        }
    }

    public void tick() {
        assert this.world != null;
        int i = this.pos.getX();
        int j = this.pos.getY();
        int k = this.pos.getZ();

        ++this.ticksSinceSync;
        this.numPlayersUsing = calculatePlayersUsingSync(this.world, this, this.ticksSinceSync, i, j, k, this.numPlayersUsing);
        this.prevLidAngle = this.lidAngle;

        if (this.numPlayersUsing > 0 && this.lidAngle == 0.0F) {
            this.playSound();
        }

        if ((this.numPlayersUsing == 0 && this.lidAngle > 0.0F) || (this.numPlayersUsing > 0 && this.lidAngle < 1.0F)) {
            float f1 = this.lidAngle;
            if (this.numPlayersUsing > 0) {
                this.lidAngle += 0.05F;
            } else {
                this.lidAngle -= 0.05F;
            }

            if (this.lidAngle > 1.0F) {
                this.lidAngle = 1.0F;
            }

            if (this.lidAngle < 0.5F && f1 >= 0.5F) {
                this.playSound();
            }

            if (this.lidAngle < 0.0F) {
                this.lidAngle = 0.0F;
            }
        }
    }

    @Nonnull
    @Override
    public IInventory getInventory() {
        return inventoryWrapper;
    }

    @Override
    public void read(CompoundNBT tag) {
        CompoundNBT invTag = tag.getCompound("inv");

        if (!this.checkLootAndRead(tag)) {
            ItemStackUtils.deserializeStacks(invTag, stacks);
        }

        super.read(tag);
    }

    @Override
    @Nonnull
    public CompoundNBT write(@Nonnull CompoundNBT tag) {
        if (!this.checkLootAndWrite(tag)) {
            tag.put("inv", ItemStackUtils.serializeStacks(stacks));
        }

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
        read(pkt.getNbtCompound());
    }

    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        if (cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            return nonSidedInventoryHandler.cast();
        }
        return super.getCapability(cap, side);
    }

    @Override
    public void remove() {
        nonSidedInventoryHandler.invalidate();
        super.remove();
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
    protected Container createMenu(int id, @Nonnull PlayerInventory player) {
        assert world != null;
        return new StoneChestContainer(id, pos, world, player, player.player);
    }

    @Nonnull
    @Override
    public ITextComponent getDisplayName() {
        assert getType().getRegistryName() != null;
        return new StringTextComponent(getType().getRegistryName().getPath());
    }

    @Nonnull
    @Override
    protected ITextComponent getDefaultName() {
        return new StringTextComponent("StoneChest");
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public float getLidAngle(float partialTicks) {
        return MathHelper.lerp(partialTicks, this.prevLidAngle, this.lidAngle);
    }

    @Override
    public boolean receiveClientEvent(int id, int type) {
        if (id == 1) {
            this.numPlayersUsing = type;
            return true;
        } else {
            return super.receiveClientEvent(id, type);
        }
    }

    @Override
    public int getSizeInventory() {
        return stacks.size();
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    public void openInventory(@Nonnull PlayerEntity player) {
        if (!player.isSpectator()) {
            assert this.world != null;

            if (this.numPlayersUsing < 0) {
                this.numPlayersUsing = 0;
            }

            ++this.numPlayersUsing;
            this.onOpenOrClose();
        }
    }

    public void closeInventory(@Nonnull PlayerEntity player) {
        if (!player.isSpectator()) {
            --this.numPlayersUsing;
            this.onOpenOrClose();
        }
    }

    private void onOpenOrClose() {
        assert this.world != null;
        Block block = this.getBlockState().getBlock();

        if (block instanceof StoneChestBlock) {
            this.world.addBlockEvent(this.pos, block, 1, this.numPlayersUsing);
            this.world.notifyNeighborsOfStateChange(this.pos, block);
        }
    }

    private void playSound() {
        assert this.world != null;
        double d0 = (double)this.pos.getX() + 0.5D;
        double d1 = (double)this.pos.getY() + 0.5D;
        double d2 = (double)this.pos.getZ() + 0.5D;
        this.world.playSound(null, d0, d1, d2, SoundEvents.BLOCK_GRINDSTONE_USE, SoundCategory.BLOCKS, 0.5F, this.world.rand.nextFloat() * 0.1F + 0.9F);
    }

    @Nonnull
    private IItemHandlerModifiable createNonSidedInventoryHandler(@Nonnull NonNullList<ItemStack> stacks) {
        return new ItemStackHandler(stacks) {
            @Override
            protected void onContentsChanged(int slot) {
                assert world != null;
                markDirty();
                world.notifyBlockUpdate(getPos(), getBlockState(), getBlockState(), 3);
            }
        };
    }

    private static int calculatePlayersUsingSync(@Nonnull World world, @Nonnull StoneChestTileEntity tileEntity, int tickSinceSync, int x, int y, int z, int numUsing) {
        if (!world.isRemote && numUsing != 0 && (tickSinceSync + x + y + z) % 200 == 0) {
            numUsing = calculatePlayersUsing(world, tileEntity, x, y, z);
        }

        return numUsing;
    }

    private static int calculatePlayersUsing(@Nonnull World world, @Nonnull StoneChestTileEntity tileEntity, int x, int y, int z) {
        int i = 0;
        float f = 5.0F;

        for(PlayerEntity playerentity : world.getEntitiesWithinAABB(PlayerEntity.class,
                new AxisAlignedBB(x - f, y - f, z - f, (x + 1) + f, (y + 1) + f, (z + 1) + f))) {
            if (playerentity.openContainer instanceof StoneChestContainer) {
                IInventory inventory = ((StoneChestContainer)playerentity.openContainer).getIInventory();

                if (inventory == tileEntity.inventoryWrapper) {
                    ++i;
                }
            }
        }

        return i;
    }
}
