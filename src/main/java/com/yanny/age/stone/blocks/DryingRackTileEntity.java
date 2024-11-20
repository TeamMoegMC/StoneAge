package com.yanny.age.stone.blocks;

import com.yanny.age.stone.api.utils.ItemStackUtils;
import com.yanny.age.stone.config.Config;
import com.yanny.age.stone.recipes.DryingRackRecipe;
import com.yanny.age.stone.subscribers.TileEntitySubscriber;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.Container;
import net.minecraft.world.Containers;
import net.minecraft.world.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.sounds.SoundSource;
import net.minecraft.sounds.SoundEvents;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.wrapper.RecipeWrapper;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Optional;

public class DryingRackTileEntity extends BlockEntity implements IInventoryInterface {
    public static final int ITEMS = 4;

    private final NonNullList<ItemStack> stacks = NonNullList.withSize(ITEMS * 2, ItemStack.EMPTY);
    private final IItemHandlerModifiable nonSidedItemHandler = createNonSidedInventoryHandler(stacks);
    private final LazyOptional<IItemHandlerModifiable> sidedInventoryHandler = LazyOptional.of(() -> createSidedInventoryHandler(stacks));
    private final LazyOptional<IItemHandlerModifiable> nonSidedInventoryHandler = LazyOptional.of(() -> nonSidedItemHandler);
    private final RecipeWrapper inventoryWrapper = new RecipeWrapper(nonSidedItemHandler);
    private final IItemHandlerModifiable tmpItemHandler = new ItemStackHandler(1);
    private final RecipeWrapper tmpItemHandlerWrapper = new RecipeWrapper(tmpItemHandler);
    private final DryingItem[] items = new DryingItem[ITEMS];

    public DryingRackTileEntity(BlockPos pos, BlockState state) {
        //noinspection ConstantConditions
        super(TileEntitySubscriber.drying_rack,pos,state);
        for (int i = 0; i < ITEMS; i++) {
            items[i] = new DryingItem();
        }
    }


    public static void tick(Level level, BlockPos blockPos, BlockState state, DryingRackTileEntity tile) {
        assert level != null;
        if (!level.isClientSide) {
            if (Config.DryingRackNeedDaytime) {
                if (level.isDay()) {
                    for (int i = 0; i < ITEMS; i++) {
                        if (tile.items[i].active) {
                            if (tile.items[i].isDried()) {
                                tile.stacks.set(i + ITEMS, tile.items[i].result);
                                tile.stacks.set(i, ItemStack.EMPTY);
                                tile.items[i].reset();
                                level.sendBlockUpdated(blockPos, state, state, 3);
                            } else {
                                tile.items[i].remaining--;
                            }
                        }
                    }
                }
            } else {
                for (int i = 0; i < ITEMS; i++) {
                    if (tile.items[i].active) {
                        if (tile.items[i].isDried()) {
                            tile.stacks.set(i + ITEMS, tile.items[i].result);
                            tile.stacks.set(i, ItemStack.EMPTY);
                            tile.items[i].reset();
                            level.sendBlockUpdated(blockPos, state, state, 3);
                        } else {
                            tile.items[i].remaining--;
                        }
                    }
                }

            }
        }
    }

    @Nonnull
    @Override
    public Container getInventory() {
        return inventoryWrapper;
    }

    @Nonnull
    public NonNullList<ItemStack> getStacks() {
        return stacks;
    }

    @Override
    public void load(CompoundTag tag) {
        CompoundTag invTag = tag.getCompound("inv");
        ItemStackUtils.deserializeStacks(invTag, stacks);

        for (int i = 0; i < ITEMS; i++) {
            items[i].read(tag.getCompound("items" + i));
        }

        super.load(tag);
    }

    @Override
    @Nonnull
    public void saveAdditional(CompoundTag tag) {
        tag.put("inv", ItemStackUtils.serializeStacks(stacks));

        for (int i = 0; i < ITEMS; i++) {
            tag.put("items" + i, items[i].write());
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
        return this.saveWithoutMetadata();
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
            if (side != null) {
                return sidedInventoryHandler.cast();
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

    @Nonnull
    DryingItem getItem(int index) {
        return items[index];
    }

    void blockActivated(@Nonnull Player player) {
        assert level != null;

        if (!level.isClientSide) {
            ItemStack itemStack = player.getMainHandItem();
            DryingRackRecipe recipe = getRecipe(itemStack).orElse(null);

            for (int i = 0; i < ITEMS; i++) {
                if (stacks.get(i).isEmpty() && stacks.get(i + ITEMS).isEmpty() && recipe != null) {
                    DryingItem item = items[i];

                    stacks.set(i, itemStack.split(1));
                    item.setup(true, recipe.getDryingTime(), recipe.assemble(null,level.registryAccess()));

                    level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), 3);
                    return;
                }

                if (!stacks.get(i + ITEMS).isEmpty()) {
                    NonNullList<ItemStack> itemStacks = NonNullList.create();
                    itemStacks.add(stacks.get(i + ITEMS).copy());

                    stacks.set(i + ITEMS, ItemStack.EMPTY);
                    stacks.set(i, ItemStack.EMPTY);

                    Containers.dropContents(level, getBlockPos(), itemStacks);

                    level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), 3);
                    level.playSound(null, getBlockPos(), SoundEvents.ITEM_PICKUP, SoundSource.BLOCKS, 1.0f, 1.0f);
                    return;
                }
            }
        }
    }

    @Nonnull
    private Optional<DryingRackRecipe> getRecipe(@Nonnull ItemStack item) {
        assert level != null;
        tmpItemHandler.setStackInSlot(0, item);
        return level.getRecipeManager().getRecipeFor(DryingRackRecipe.drying_rack, tmpItemHandlerWrapper, level);
    }

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

    private IItemHandlerModifiable createSidedInventoryHandler(@Nonnull NonNullList<ItemStack> stacks) {
        return new ItemStackHandler(stacks) {
            @Nonnull
            @Override
            public ItemStack extractItem(int slot, int amount, boolean simulate) {
                if (slot >= ITEMS) {
                    return super.extractItem(slot, amount, simulate);
                } else {
                    return ItemStack.EMPTY;
                }
            }

            @Nonnull
            @Override
            public ItemStack insertItem(int slot, @Nonnull ItemStack stack, boolean simulate) {
                if (slot < ITEMS && getStackInSlot(slot).isEmpty() && level != null) {
                    DryingRackRecipe recipe = getRecipe(stack).orElse(null);

                    if (recipe != null) {
                        items[slot].setup(true, recipe.getDryingTime(), recipe.assemble(null,level.registryAccess()));
                        return super.insertItem(slot, stack, simulate);
                    }
                }

                return stack;
            }

            @Override
            protected void onContentsChanged(int slot) {
                assert level != null;
                setChanged();
                level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), 3);
            }
        };
    }

    static class DryingItem {
        boolean active;
        int dryingTime;
        int remaining;
        ItemStack result;

        DryingItem() {
            setup(false, 0, ItemStack.EMPTY);
        }

        boolean isDried() {
            return active && remaining <= 0;
        }

        void setup(boolean active, int dryingTime, ItemStack result) {
            this.active = active;
            this.dryingTime = this.remaining = dryingTime;
            this.result = result;
        }

        void reset() {
            this.active = false;
            this.result = ItemStack.EMPTY;
        }

        CompoundTag write() {
            CompoundTag nbt = new CompoundTag();
            nbt.putBoolean("active", active);
            nbt.putInt("dryingTime", dryingTime);
            nbt.putInt("remaining", remaining);
            CompoundTag item = new CompoundTag();
            result.save(item);
            nbt.put("item", item);
            return nbt;
        }

        void read(CompoundTag nbt) {
            active = nbt.getBoolean("active");
            dryingTime = nbt.getInt("dryingTime");
            remaining = nbt.getInt("remaining");
            result = ItemStack.of(nbt.getCompound("item"));
        }
    }
}
