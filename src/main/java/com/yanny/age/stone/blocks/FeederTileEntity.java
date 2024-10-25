package com.yanny.age.stone.blocks;

import com.yanny.age.stone.config.Config;
import com.yanny.age.stone.subscribers.TileEntitySubscriber;
import com.yanny.ages.api.utils.ItemStackUtils;
import net.minecraft.block.BlockState;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.wrapper.RecipeWrapper;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.*;

public class FeederTileEntity extends TileEntity implements IInventoryInterface, ITickableTileEntity, INamedContainerProvider {
    private static final Set<Item> VALID_ITEMS = new HashSet<>();
    static {
        VALID_ITEMS.addAll(Tags.Items.SEEDS.getValues());
        VALID_ITEMS.addAll(Tags.Items.CROPS.getValues());
    }

    public static final int ITEMS = 4;

    private final NonNullList<ItemStack> stacks = NonNullList.withSize(ITEMS, ItemStack.EMPTY);
    private final IItemHandlerModifiable nonSidedItemHandler = createNonSidedInventoryHandler(stacks);
    private final LazyOptional<IItemHandlerModifiable> sidedInventoryHandler = LazyOptional.of(() -> createSidedInventoryHandler(stacks));
    private final LazyOptional<IItemHandlerModifiable> nonSidedInventoryHandler = LazyOptional.of(() -> nonSidedItemHandler);
    private final RecipeWrapper inventoryWrapper = new RecipeWrapper(nonSidedItemHandler);

    private AxisAlignedBB boundingBox = new AxisAlignedBB(getBlockPos());

    public FeederTileEntity() {
        //noinspection ConstantConditions
        super(TileEntitySubscriber.feeder);
    }

    @Override
    public void tick() {
        if (level != null && !level.isClientSide) {
            if (level.random.nextInt(Config.feederTickChanceBreedAnimalEffect) == 0 && getItem().isPresent()) {
                useOnEntity();
                level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), 3);
            }
        }
    }

    @Nullable
    @Override
    public Container createMenu(int id, @Nonnull PlayerInventory inventory, @Nonnull PlayerEntity entity) {
        assert level != null;
        return new FeederContainer(id, worldPosition, level, inventory, entity);
    }

    @Nonnull
    @Override
    public ITextComponent getDisplayName() {
        return new TranslationTextComponent("block.stone_age.feeder");
    }

    @Nonnull
    @Override
    public IInventory getInventory() {
        return inventoryWrapper;
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
                if (side != Direction.UP) {
                    return sidedInventoryHandler.cast();
                }
            } else {
                return nonSidedInventoryHandler.cast();
            }
        }
        return super.getCapability(cap, side);
    }

    @Override
    public void clearRemoved() {
        super.clearRemoved();
        boundingBox = new AxisAlignedBB(worldPosition.getX() - Config.feederEffectRange, worldPosition.getY() - 1, worldPosition.getZ() - Config.feederEffectRange,
                worldPosition.getX() + Config.feederEffectRange + 1, worldPosition.getY() + 2, worldPosition.getZ() + Config.feederEffectRange + 1);
    }

    @Override
    public void setRemoved() {
        sidedInventoryHandler.invalidate();
        nonSidedInventoryHandler.invalidate();
        super.setRemoved();
    }

    boolean isItemValid(@Nonnull ItemStack itemStack) {
        return VALID_ITEMS.contains(itemStack.getItem());
    }

    @Nonnull
    private IItemHandlerModifiable createNonSidedInventoryHandler(@Nonnull NonNullList<ItemStack> stacks) {
        return new ItemStackHandler(stacks) {

            @Nonnull
            @Override
            public ItemStack insertItem(int slot, @Nonnull ItemStack stack, boolean simulate) {
                if (FeederTileEntity.this.isItemValid(stack)) {
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
                return ItemStack.EMPTY;
            }

            @Nonnull
            @Override
            public ItemStack insertItem(int slot, @Nonnull ItemStack stack, boolean simulate) {
                if (FeederTileEntity.this.isItemValid(stack)) {
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
    private Optional<ItemStack> getItem() {
        return stacks.stream().filter(itemStack -> !itemStack.isEmpty()).findFirst();
    }

    private void useOnEntity() {
        assert level != null;
        List<AnimalEntity> entities = level.getEntitiesOfClass(AnimalEntity.class, boundingBox, AnimalEntity::canBreed);
        Collections.shuffle(entities);

        if (!entities.isEmpty()) {
            getItem().ifPresent(itemStack -> {
                AnimalEntity winner = entities.get(0);

                if (winner.isBaby()) {
                    winner.ageUp((int)((float)(-winner.getAge() / 20) * 0.1F), true);
                    itemStack.shrink(1);
                } else {
                    List<AnimalEntity> entities1 = level.getEntitiesOfClass(winner.getClass(), boundingBox,
                            livingEntity -> !livingEntity.is(winner) && !livingEntity.isBaby() && livingEntity.canBreed());

                    if (winner.getAge() == 0 && entities1.size() < 30) {
                        winner.setInLove(null);
                        itemStack.shrink(1);

                        getItem().ifPresent(itemStack1 -> {
                            if (!entities1.isEmpty()) {
                                Collections.shuffle(entities1);
                                AnimalEntity winner1 = entities1.get(0);

                                if (winner1.getAge() == 0 && winner1.canBreed()) {
                                    winner1.setInLove(null);
                                    itemStack1.shrink(1);
                                }
                            }
                        });
                    }
                }
            });
        }
    }
}
