package com.yanny.age.stone.blocks;

import com.google.common.collect.Lists;
import com.yanny.age.stone.recipes.FlintWorkbenchRecipe;
import com.yanny.age.stone.subscribers.TileEntitySubscriber;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.Container;
import net.minecraft.world.Containers;
import net.minecraft.world.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.wrapper.RecipeWrapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;
import java.util.stream.Collectors;

import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;

public class FlintWorkbenchTileEntity extends BlockEntity implements IInventoryInterface {
    private static final Logger LOGGER = LogManager.getLogger(FlintWorkbenchTileEntity.class);
    private final NonNullList<ItemStack> stacks = NonNullList.withSize(9, ItemStack.EMPTY);
    private final IItemHandlerModifiable nonSidedItemHandler = createNonSidedInventoryHandler(stacks);
    private final LazyOptional<IItemHandlerModifiable> nonSidedInventoryHandler = LazyOptional.of(() -> nonSidedItemHandler);
    private final RecipeWrapper inventoryWrapper = new RecipeWrapper(nonSidedItemHandler);
    private ItemStack recipeOutput = ItemStack.EMPTY;

    public FlintWorkbenchTileEntity(BlockPos pos, BlockState state) {
        //noinspection ConstantConditions
        super(TileEntitySubscriber.flint_workbench, pos, state);
    }

    @Override
    public void load(CompoundTag tag) {
        CompoundTag invTag = tag.getCompound("inv");
        CompoundTag outTag = tag.getCompound("output");
        ItemStackUtils.deserializeStacks(invTag, stacks);
        recipeOutput = ItemStack.of(outTag);
        super.load(tag);
    }

    @Override
    @Nonnull
    public void saveAdditional(CompoundTag tag) {
        tag.put("inv", ItemStackUtils.serializeStacks(stacks));
        CompoundTag outTag = new CompoundTag();
        recipeOutput.save(outTag);
        tag.put("output", outTag);
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
            if (side == null) {
                return nonSidedInventoryHandler.cast();
            }
        }
        return super.getCapability(cap, side);
    }

    @Override
    public void setRemoved() {
        nonSidedInventoryHandler.invalidate();
        super.setRemoved();
    }

    @Nonnull
    public Container getInventory() {
        return inventoryWrapper;
    }

    @Nonnull
    public NonNullList<ItemStack> getStacks() {
        return stacks;
    }

    @Nonnull
    public ItemStack getRecipeOutput() {
        return recipeOutput;
    }

    @Nonnull
    InteractionResult blockActivated(@Nonnull Player player, @Nonnull BlockHitResult hit) {
        assert level != null;
        ItemStack heldItemMainhand = player.getMainHandItem();
        List<FlintWorkbenchRecipe> recipes = findMatchingRecipes(heldItemMainhand);

        if (!recipes.isEmpty()) {
            if (recipes.size() > 1) {
                LOGGER.warn("Too many valid recipes! Selecting first valid recipe");
            }

            FlintWorkbenchRecipe flintWorkbenchRecipe = recipes.get(0);
            ItemStack result = flintWorkbenchRecipe.assemble(getInventory());
            NonNullList<ItemStack> itemStacks = NonNullList.create();
            itemStacks.add(result);
            Containers.dropContents(level, getBlockPos(), itemStacks);

            for (int i = 0; i < stacks.size(); i++) {
                stacks.set(i, ItemStack.EMPTY);
            }

            recipeOutput = ItemStack.EMPTY;
            heldItemMainhand.hurtAndBreak(1, player, playerEntity -> playerEntity.broadcastBreakEvent(EquipmentSlot.MAINHAND));
            level.playSound(null, getBlockPos(), SoundEvents.DISPENSER_DISPENSE, SoundSource.BLOCKS, 1.0f, 1.0f);
            this.setChanged();
            this.level.sendBlockUpdated(worldPosition, getBlockState(), getBlockState(), 3);
            return InteractionResult.SUCCESS;
        }
		if (hit.getDirection() == Direction.UP) {
		    Direction dir = getBlockState().getValue(HorizontalDirectionalBlock.FACING);
		    int x = 0;
		    int y = 0;

		    // handle rotation
		    switch (dir) {
		        case NORTH:
		            x = (int) Math.floor(FlintWorkbenchRecipe.MAX_WIDTH - (hit.getLocation().x - hit.getBlockPos().getX()) * FlintWorkbenchRecipe.MAX_WIDTH);
		            y = (int) Math.floor(FlintWorkbenchRecipe.MAX_HEIGHT - (hit.getLocation().z - hit.getBlockPos().getZ()) * FlintWorkbenchRecipe.MAX_HEIGHT);
		            break;
		        case SOUTH:
		            x = (int) Math.floor((hit.getLocation().x - hit.getBlockPos().getX()) * FlintWorkbenchRecipe.MAX_WIDTH);
		            y = (int) Math.floor((hit.getLocation().z - hit.getBlockPos().getZ()) * FlintWorkbenchRecipe.MAX_HEIGHT);
		            break;
		        case EAST:
		            x = (int) Math.floor(FlintWorkbenchRecipe.MAX_HEIGHT - (hit.getLocation().z - hit.getBlockPos().getZ()) * FlintWorkbenchRecipe.MAX_HEIGHT);
		            y = (int) Math.floor((hit.getLocation().x - hit.getBlockPos().getX()) * FlintWorkbenchRecipe.MAX_WIDTH);
		            break;
		        case WEST:
		            x = (int) Math.floor((hit.getLocation().z - hit.getBlockPos().getZ()) * FlintWorkbenchRecipe.MAX_HEIGHT);
		            y = (int) Math.floor(FlintWorkbenchRecipe.MAX_WIDTH - (hit.getLocation().x - hit.getBlockPos().getX()) * FlintWorkbenchRecipe.MAX_WIDTH);
		            break;
		    }

		    ItemStack stack = stacks.get(y * FlintWorkbenchRecipe.MAX_WIDTH + x);

		    if (!heldItemMainhand.isEmpty() && stack.isEmpty()) {
		        stacks.set(y * FlintWorkbenchRecipe.MAX_WIDTH + x, heldItemMainhand.split(1));
		        List<FlintWorkbenchRecipe> recipe = findMatchingRecipes();

		        if (!recipe.isEmpty()) {
		            if (recipes.size() > 1) {
		                LOGGER.warn("Too many valid recipes! Selecting first valid recipe");
		            }

		            recipeOutput = recipe.get(0).getResultItem().copy();
		        } else {
		            recipeOutput = ItemStack.EMPTY;
		        }
		        this.setChanged();
	            this.level.sendBlockUpdated(worldPosition, getBlockState(), getBlockState(), 3);
		        return InteractionResult.SUCCESS;
		    }

		    if (heldItemMainhand.isEmpty() && !stacks.get(y * FlintWorkbenchRecipe.MAX_WIDTH + x).isEmpty()) {
		        NonNullList<ItemStack> itemStacks = NonNullList.create();
		        itemStacks.add(stack);
		        Containers.dropContents(level, getBlockPos(), itemStacks);
		        stacks.set(y * FlintWorkbenchRecipe.MAX_WIDTH + x, ItemStack.EMPTY);
		        level.playSound(null, getBlockPos(), SoundEvents.ITEM_PICKUP, SoundSource.BLOCKS, 1.0f, 1.0f);
		        List<FlintWorkbenchRecipe> recipe = findMatchingRecipes();

		        if (!recipe.isEmpty()) {
		            if (recipes.size() > 1) {
		                LOGGER.warn("Too many valid recipes! Selecting first valid recipe");
		            }

		            recipeOutput = recipe.get(0).getResultItem().copy();
		        } else {
		            recipeOutput = ItemStack.EMPTY;
		        }
		        this.setChanged();
	            this.level.sendBlockUpdated(worldPosition, getBlockState(), getBlockState(), 3);
		        return InteractionResult.SUCCESS;
		    }
		}

        return InteractionResult.PASS;
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
    private List<FlintWorkbenchRecipe> findMatchingRecipes(@Nonnull ItemStack heldItemMainhand) {
        assert this.level != null;
        List<FlintWorkbenchRecipe> recipes = stacks.stream().allMatch(ItemStack::isEmpty) ? Lists.newArrayList()
                : this.level.getRecipeManager().getRecipesFor(FlintWorkbenchRecipe.flint_workbench, inventoryWrapper, this.level);

        return recipes.stream().filter(flintWorkbenchRecipe -> flintWorkbenchRecipe.testTool(heldItemMainhand)).collect(Collectors.toList());
    }

    @Nonnull
    private List<FlintWorkbenchRecipe> findMatchingRecipes() {
        assert this.level != null;
        return stacks.stream().allMatch(ItemStack::isEmpty) ? Lists.newArrayList()
                : this.level.getRecipeManager().getRecipesFor(FlintWorkbenchRecipe.flint_workbench, inventoryWrapper, this.level);
    }
}
