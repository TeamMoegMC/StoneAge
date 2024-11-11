package com.yanny.age.stone.blocks;

import com.yanny.age.stone.api.utils.ItemStackUtils;
import com.yanny.age.stone.config.Config;
import com.yanny.age.stone.recipes.TanningRackRecipe;
import com.yanny.age.stone.subscribers.TileEntitySubscriber;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.Container;
import net.minecraft.world.Containers;
import net.minecraft.world.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.wrapper.RecipeWrapper;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Random;

import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;

public class TanningRackTileEntity extends BlockEntity implements IInventoryInterface {
    public static final int ITEMS = 1;
    private static final Random random = new Random(System.currentTimeMillis());

    private final NonNullList<ItemStack> stacks = NonNullList.withSize(ITEMS * 2, ItemStack.EMPTY);
    private final IItemHandlerModifiable nonSidedItemHandler = createNonSidedInventoryHandler(stacks);
    private final LazyOptional<IItemHandlerModifiable> sidedInventoryHandler = LazyOptional.of(() -> createSidedInventoryHandler(stacks));
    private final LazyOptional<IItemHandlerModifiable> nonSidedInventoryHandler = LazyOptional.of(() -> nonSidedItemHandler);
    private final RecipeWrapper inventoryWrapper = new RecipeWrapper(nonSidedItemHandler);
    private final IItemHandlerModifiable tmpItemHandler = new ItemStackHandler(1);
    private final RecipeWrapper tmpItemHandlerWrapper = new RecipeWrapper(tmpItemHandler);

    public TanningRackTileEntity(BlockPos pos, BlockState state) {
        //noinspection ConstantConditions
        super(TileEntitySubscriber.tanning_rack,pos,state);
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
        super.load(tag);
    }

    @Override
    @Nonnull
    public void saveAdditional(CompoundTag tag) {
        tag.put("inv", ItemStackUtils.serializeStacks(stacks));
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
    InteractionResult blockActivated(@Nonnull Player player) {
        assert level != null;
        ItemStack itemMainhand = player.getMainHandItem();
        TanningRackRecipe recipe = getRecipe(itemMainhand);
        int pos = 0;

        if (stacks.get(pos).isEmpty() && stacks.get(pos + ITEMS).isEmpty() && recipe != null) {
            stacks.set(pos, itemMainhand.split(1));

            level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), 3);
            return InteractionResult.SUCCESS;
        }

        recipe = getRecipe(stacks.get(pos));

        if (recipe != null && recipe.getTool().test(itemMainhand) && !stacks.get(pos).isEmpty()) {
            itemMainhand.hurtAndBreak(1, player, playerEntity -> playerEntity.broadcastBreakEvent(EquipmentSlot.MAINHAND));

            if (random.nextDouble() < Config.tanningRackFinishChance) {
                stacks.set(pos + ITEMS, recipe.assemble(null,level.registryAccess()));
                stacks.set(pos, ItemStack.EMPTY);
                level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), 3);
            }

            return InteractionResult.SUCCESS;
        }

        if (!stacks.get(pos + ITEMS).isEmpty()) {
            NonNullList<ItemStack> itemStacks = NonNullList.create();
            itemStacks.add(stacks.get(pos + ITEMS).copy());
            stacks.set(pos + ITEMS, ItemStack.EMPTY);
            stacks.set(pos, ItemStack.EMPTY);
            Containers.dropContents(level, getBlockPos(), itemStacks);

            level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), 3);
            level.playSound(null, getBlockPos(), SoundEvents.ITEM_PICKUP, SoundSource.BLOCKS, 1.0f, 1.0f);
            return InteractionResult.SUCCESS;
        }

        return InteractionResult.PASS;
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
