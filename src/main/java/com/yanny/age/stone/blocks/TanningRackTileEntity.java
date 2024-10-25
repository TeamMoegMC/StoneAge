package com.yanny.age.stone.blocks;

import com.yanny.age.stone.config.Config;
import com.yanny.age.stone.recipes.TanningRackRecipe;
import com.yanny.age.stone.subscribers.TileEntitySubscriber;
import com.yanny.ages.api.utils.ItemStackUtils;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.wrapper.RecipeWrapper;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Random;

public class TanningRackTileEntity extends TileEntity implements IInventoryInterface {
    public static final int ITEMS = 1;
    private static final Random random = new Random(System.currentTimeMillis());

    private final NonNullList<ItemStack> stacks = NonNullList.withSize(ITEMS * 2, ItemStack.EMPTY);
    private final IItemHandlerModifiable nonSidedItemHandler = createNonSidedInventoryHandler(stacks);
    private final LazyOptional<IItemHandlerModifiable> sidedInventoryHandler = LazyOptional.of(() -> createSidedInventoryHandler(stacks));
    private final LazyOptional<IItemHandlerModifiable> nonSidedInventoryHandler = LazyOptional.of(() -> nonSidedItemHandler);
    private final RecipeWrapper inventoryWrapper = new RecipeWrapper(nonSidedItemHandler);
    private final IItemHandlerModifiable tmpItemHandler = new ItemStackHandler(1);
    private final RecipeWrapper tmpItemHandlerWrapper = new RecipeWrapper(tmpItemHandler);

    public TanningRackTileEntity() {
        //noinspection ConstantConditions
        super(TileEntitySubscriber.tanning_rack);
    }

    @Nonnull
    @Override
    public IInventory getInventory() {
        return inventoryWrapper;
    }

    @Nonnull
    public NonNullList<ItemStack> getStacks() {
        return stacks;
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
    ActionResultType blockActivated(@Nonnull PlayerEntity player) {
        assert level != null;
        ItemStack itemMainhand = player.getMainHandItem();
        TanningRackRecipe recipe = getRecipe(itemMainhand);
        int pos = 0;

        if (stacks.get(pos).isEmpty() && stacks.get(pos + ITEMS).isEmpty() && recipe != null) {
            stacks.set(pos, itemMainhand.split(1));

            level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), 3);
            return ActionResultType.SUCCESS;
        }

        recipe = getRecipe(stacks.get(pos));

        if (recipe != null && recipe.getTool().test(itemMainhand) && !stacks.get(pos).isEmpty()) {
            itemMainhand.hurtAndBreak(1, player, playerEntity -> playerEntity.broadcastBreakEvent(EquipmentSlotType.MAINHAND));

            if (random.nextDouble() < Config.tanningRackFinishChance) {
                stacks.set(pos + ITEMS, recipe.assemble(null));
                stacks.set(pos, ItemStack.EMPTY);
                level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), 3);
            }

            return ActionResultType.SUCCESS;
        }

        if (!stacks.get(pos + ITEMS).isEmpty()) {
            NonNullList<ItemStack> itemStacks = NonNullList.create();
            itemStacks.add(stacks.get(pos + ITEMS).copy());
            stacks.set(pos + ITEMS, ItemStack.EMPTY);
            stacks.set(pos, ItemStack.EMPTY);
            InventoryHelper.dropContents(level, getBlockPos(), itemStacks);

            level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), 3);
            level.playSound(null, getBlockPos(), SoundEvents.ITEM_PICKUP, SoundCategory.BLOCKS, 1.0f, 1.0f);
            return ActionResultType.SUCCESS;
        }

        return ActionResultType.PASS;
    }

    @Nullable
    private TanningRackRecipe getRecipe(@Nonnull ItemStack item) {
        assert level != null;
        tmpItemHandler.setStackInSlot(0, item);
        return level.getRecipeManager().getRecipeFor(TanningRackRecipe.tanning_rack, tmpItemHandlerWrapper, level).orElse(null);
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

    @Nonnull
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
                    TanningRackRecipe recipe = getRecipe(stack);

                    if (recipe != null) {
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
}
