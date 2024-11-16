package com.yanny.age.stone.blocks;

import com.yanny.age.stone.api.utils.ItemStackUtils;
import com.yanny.age.stone.recipes.MillstoneRecipe;
import com.yanny.age.stone.subscribers.TileEntitySubscriber;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
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
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.network.chat.Component;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.wrapper.RecipeWrapper;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Optional;

import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.inventory.ContainerData;

public class MillstoneTileEntity extends BlockEntity implements IInventoryInterface, MenuProvider {
    static final int ITEMS = 3;
    private static final double PI2 = Math.PI * 2;

    private final NonNullList<ItemStack> stacks = NonNullList.withSize(ITEMS, ItemStack.EMPTY);
    private final IItemHandlerModifiable nonSidedItemHandler = createNonSidedInventoryHandler(stacks);
    private final LazyOptional<IItemHandlerModifiable> sidedInventoryHandler = LazyOptional.of(() -> createSidedInventoryHandler(stacks));
    private final LazyOptional<IItemHandlerModifiable> nonSidedInventoryHandler = LazyOptional.of(() -> nonSidedItemHandler);
    private final RecipeWrapper inventoryWrapper = new RecipeWrapper(nonSidedItemHandler);
    private final IItemHandlerModifiable tmpItemHandler = new ItemStackHandler(1);
    private final RecipeWrapper tmpItemHandlerWrapper = new RecipeWrapper(tmpItemHandler);
    private final ContainerData data = getData();

    private float rotation = 0f;
    private boolean active = false;
    private int activateTicks = 0;
    private int ticks = 0;
    private double secondChance = 0.0D;
    private ItemStack result = ItemStack.EMPTY;
    private ItemStack secondResult = ItemStack.EMPTY;

    public MillstoneTileEntity(BlockPos pos, BlockState state) {
        //noinspection ConstantConditions
        super(TileEntitySubscriber.millstone,pos, state);
    }


    public static void tick(Level level, BlockPos blockPos, BlockState state, MillstoneTileEntity tile) {
        assert level != null;

        if (tile.active) {
            if (level.random.nextInt(5) == 0) {
                double d0 = blockPos.getX() + level.random.nextFloat() / 2 + 0.25;
                double d1 = blockPos.getY() + 7 / 16f + 0.025D;
                double d2 = blockPos.getZ() + level.random.nextFloat() / 2 + 0.25;
                level.addParticle(ParticleTypes.CRIT, d0, d1, d2, 0, level.random.nextFloat(), 0);
            }

            tile.rotation += PI2 / 80;
            tile.rotation = (float) (tile.rotation % PI2);
            tile.ticks++;

            if (tile.ticks % 20 == 0) {
                tile.active = false;

                if (tile.ticks == tile.activateTicks) {
                    if (tile.stacks.get(1).isEmpty()) {
                        tile.stacks.set(1, tile.result);
                    } else {
                        tile.stacks.get(1).grow(tile.result.getCount());
                    }

                    if (level.random.nextDouble() < tile.secondChance) {
                        if (tile.stacks.get(2).isEmpty()) {
                            tile.stacks.set(2, tile.secondResult);
                        } else {
                            tile.stacks.get(2).grow(tile.secondResult.getCount());
                        }
                    }

                    tile.result = ItemStack.EMPTY;
                    tile.secondResult = ItemStack.EMPTY;
                    tile.ticks = 0;

                    if (!level.isClientSide) {
                        level.sendBlockUpdated(blockPos, state, state, 3);
                    }
                }
            }
        }
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int id, @Nonnull Inventory inventory, @Nonnull Player entity) {
        assert level != null;
        return new MillstoneContainer(id, worldPosition, level, inventory, entity, data);
    }

    @Nonnull
    @Override
    public Component getDisplayName() {
        return Component.translatable("block.stone_age.millstone");
    }

    @Nonnull
    @Override
    public Container getInventory() {
        return inventoryWrapper;
    }

    @Override
    public void load(CompoundTag tag) {
        CompoundTag invTag = tag.getCompound("inv");
        ItemStackUtils.deserializeStacks(invTag, stacks);
        active = tag.getBoolean("active");
        rotation = tag.getFloat("rotation");
        ticks = tag.getInt("ticks");
        result = ItemStack.of(tag.getCompound("result"));
        secondResult = ItemStack.of(tag.getCompound("secondResult"));
        secondChance = tag.getDouble("secondChance");
        activateTicks = tag.getInt("activateTicks");
        super.load(tag);
    }

    @Override
    @Nonnull
    public void saveAdditional(CompoundTag tag) {
        tag.put("inv", ItemStackUtils.serializeStacks(stacks));
        tag.putBoolean("active", active);
        tag.putFloat("rotation", rotation);
        tag.putInt("ticks", ticks);
        tag.put("result", result.save(new CompoundTag()));
        tag.put("secondResult", secondResult.save(new CompoundTag()));
        tag.putDouble("secondChance", secondChance);
        tag.putInt("activateTicks", activateTicks);
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
    ItemStack getResult() {
        return result;
    }

    int getCraftingProgress() {
        return Math.round(ticks / (float) activateTicks * 100);
    }

    boolean isItemValid(@Nonnull ItemStack itemStack) {
        return getRecipe(itemStack).isPresent();
    }

    @Nonnull
    private IItemHandlerModifiable createNonSidedInventoryHandler(@Nonnull NonNullList<ItemStack> stacks) {
        return new ItemStackHandler(stacks) {
            @Nonnull
            @Override
            public ItemStack insertItem(int slot, @Nonnull ItemStack stack, boolean simulate) {
                if (MillstoneTileEntity.this.isItemValid(stack)) {
                    return super.insertItem(slot, stack, simulate);
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

    @Nonnull
    private IItemHandlerModifiable createSidedInventoryHandler(@Nonnull NonNullList<ItemStack> stacks) {
        return new ItemStackHandler(stacks) {
            @Nonnull
            @Override
            public ItemStack extractItem(int slot, int amount, boolean simulate) {
                if (slot > 0) {
                    return super.extractItem(slot, amount, simulate);
                }

                return ItemStack.EMPTY;
            }

            @Nonnull
            @Override
            public ItemStack insertItem(int slot, @Nonnull ItemStack stack, boolean simulate) {
                if (slot == 0 && MillstoneTileEntity.this.isItemValid(stack)) {
                    return super.insertItem(slot, stack, simulate);
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

    public float rotateAngle() {
        return rotation;
    }

    public void onActivated() {
        assert level != null;

        if (!active) {
            ItemStack inputStack = stacks.get(0);

            if (result.isEmpty() && !inputStack.isEmpty()) {
                getRecipe(inputStack).ifPresent(millstoneRecipe -> {
                    if (canCraft(millstoneRecipe)) {
                        inputStack.shrink(millstoneRecipe.getIngredients().get(0).getItems()[0].getCount());
                        result = millstoneRecipe.assemble(null,level.registryAccess());
                        secondResult = millstoneRecipe.getCraftingSecondResult();
                        secondChance = millstoneRecipe.getSecondChance();
                        active = true;
                        activateTicks = millstoneRecipe.getActivateCount() * 20;
                        ticks = 0;

                        level.playSound(null, getBlockPos(), SoundEvents.GRINDSTONE_USE, SoundSource.BLOCKS, 0.5f, 1.0f);
                        level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), 3);
                    }
                });
            } else if (!result.isEmpty() || !secondResult.isEmpty()) {
                active = true;

                level.playSound(null, getBlockPos(), SoundEvents.GRINDSTONE_USE, SoundSource.BLOCKS, 0.5f, 1.0f);
                level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), 3);
            }
        }
    }

    @Nonnull
    private Optional<MillstoneRecipe> getRecipe(@Nonnull ItemStack item) {
        assert level != null;
        tmpItemHandler.setStackInSlot(0, item);
        return level.getRecipeManager().getRecipeFor(MillstoneRecipe.millstone, tmpItemHandlerWrapper, level);
    }

    @Nonnull
    private ContainerData getData() {
        return new ContainerData() {
            @Override
            public int get(int index) {
                return Math.round(ticks / (float) activateTicks * 100);
            }

            @Override
            public void set(int index, int value) {

            }

            @Override
            public int getCount() {
                return 1;
            }
        };
    }

    public boolean canCraft(@Nonnull MillstoneRecipe recipe) {
        ItemStack inputStack = stacks.get(0);
        ItemStack outputStack = stacks.get(1);
        ItemStack outputSecondStack = stacks.get(2);
        ItemStack recipeInput = recipe.getIngredients().get(0).getItems()[0];
        ItemStack recipeOutput = recipe.getResultItem(level.registryAccess());
        ItemStack recipeSecondOutput = recipe.getRecipeSecondOutput();
        boolean emptyOutput = outputStack.isEmpty();
        boolean emptyRecipe = recipeOutput.isEmpty();
        boolean emptySecondOutput = outputSecondStack.isEmpty();
        boolean emptySecondRecipe = recipeSecondOutput.isEmpty();
        int inputCount = inputStack.getCount();
        int inputRecipeCount = recipeInput.getCount();
        int outputCount = outputStack.getCount();
        int outputMaxCount = outputStack.getMaxStackSize();
        int outputSecondCount = outputSecondStack.getCount();
        int outputSecondMaxCount = outputSecondStack.getMaxStackSize();
        int outputRecipeCount = recipeOutput.getCount();
        int outputRecipeSecondCount = recipeSecondOutput.getCount();

        if (inputCount >= inputRecipeCount) {
            return (emptyOutput || emptyRecipe || (outputStack.is(recipeOutput.getItem()) && outputCount <= outputMaxCount - outputRecipeCount)) &&
                    (emptySecondOutput || emptySecondRecipe || (outputSecondStack.is(recipeSecondOutput.getItem()) && outputSecondCount <= outputSecondMaxCount - outputRecipeSecondCount));
        }

        return false;
    }
}
