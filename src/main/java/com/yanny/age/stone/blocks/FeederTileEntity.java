package com.yanny.age.stone.blocks;

import com.yanny.age.stone.api.utils.ItemStackUtils;
import com.yanny.age.stone.config.Config;
import com.yanny.age.stone.subscribers.TileEntitySubscriber;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.Container;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.world.phys.AABB;
import net.minecraft.network.chat.Component;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.wrapper.RecipeWrapper;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.*;

public class FeederTileEntity extends BlockEntity implements IInventoryInterface, MenuProvider {
    private static final Set<Item> VALID_ITEMS = new HashSet<>();
    static {
        VALID_ITEMS.addAll(ForgeRegistries.ITEMS.tags().getTag(Tags.Items.SEEDS).stream().toList());
        VALID_ITEMS.addAll(ForgeRegistries.ITEMS.tags().getTag(Tags.Items.CROPS).stream().toList());
    }

    public static final int ITEMS = 4;

    private final NonNullList<ItemStack> stacks = NonNullList.withSize(ITEMS, ItemStack.EMPTY);
    private final IItemHandlerModifiable nonSidedItemHandler = createNonSidedInventoryHandler(stacks);
    private final LazyOptional<IItemHandlerModifiable> sidedInventoryHandler = LazyOptional.of(() -> createSidedInventoryHandler(stacks));
    private final LazyOptional<IItemHandlerModifiable> nonSidedInventoryHandler = LazyOptional.of(() -> nonSidedItemHandler);
    private final RecipeWrapper inventoryWrapper = new RecipeWrapper(nonSidedItemHandler);

    private AABB boundingBox = new AABB(getBlockPos());

    public FeederTileEntity(BlockPos pos, BlockState state) {
        //noinspection ConstantConditions
        super(TileEntitySubscriber.feeder,pos,state);
    }


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
    public AbstractContainerMenu createMenu(int id, @Nonnull Inventory inventory, @Nonnull Player entity) {
        assert level != null;
        return new FeederContainer(id, worldPosition, level, inventory, entity);
    }

    @Nonnull
    @Override
    public Component getDisplayName() {
        return Component.translatable("block.stone_age.feeder");
    }

    @Nonnull
    @Override
    public Container getInventory() {
        return inventoryWrapper;
    }

    @Override
    public void load(@Nonnull CompoundTag tag) {
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
        boundingBox = new AABB(worldPosition.getX() - Config.feederEffectRange, worldPosition.getY() - 1, worldPosition.getZ() - Config.feederEffectRange,
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
        List<Animal> entities = level.getEntitiesOfClass(Animal.class, boundingBox, Animal::canBreed);
        Collections.shuffle(entities);

        if (!entities.isEmpty()) {
            getItem().ifPresent(itemStack -> {
                Animal winner = entities.get(0);

                if (winner.isBaby()) {
                    winner.ageUp((int)((float)(-winner.getAge() / 20) * 0.1F), true);
                    itemStack.shrink(1);
                } else {
                    List<Animal> entities1 = level.getEntitiesOfClass((Class<Animal>)winner.getClass(), boundingBox,
                            livingEntity -> !livingEntity.is(winner) && !livingEntity.isBaby() && livingEntity.canBreed());

                    if (winner.getAge() == 0 && entities1.size() < 30) {
                        winner.setInLove(null);
                        itemStack.shrink(1);

                        getItem().ifPresent(itemStack1 -> {
                            if (!entities1.isEmpty()) {
                                Collections.shuffle(entities1);
                                Animal winner1 = entities1.get(0);

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
