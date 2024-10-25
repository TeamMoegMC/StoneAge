package com.yanny.age.stone.blocks;

import com.google.common.collect.Lists;
import com.yanny.age.stone.recipes.FlintWorkbenchRecipe;
import com.yanny.age.stone.subscribers.TileEntitySubscriber;
import com.yanny.ages.api.utils.ItemStackUtils;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalBlock;
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
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.wrapper.RecipeWrapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;
import java.util.stream.Collectors;

public class FlintWorkbenchTileEntity extends TileEntity implements IInventoryInterface {
    private static final Logger LOGGER = LogManager.getLogger(FlintWorkbenchTileEntity.class);
    private final NonNullList<ItemStack> stacks = NonNullList.withSize(9, ItemStack.EMPTY);
    private final IItemHandlerModifiable nonSidedItemHandler = createNonSidedInventoryHandler(stacks);
    private final LazyOptional<IItemHandlerModifiable> nonSidedInventoryHandler = LazyOptional.of(() -> nonSidedItemHandler);
    private final RecipeWrapper inventoryWrapper = new RecipeWrapper(nonSidedItemHandler);
    private ItemStack recipeOutput = ItemStack.EMPTY;

    public FlintWorkbenchTileEntity() {
        //noinspection ConstantConditions
        super(TileEntitySubscriber.flint_workbench);
    }

    @Override
    public void load(@Nonnull BlockState blockState, CompoundNBT tag) {
        CompoundNBT invTag = tag.getCompound("inv");
        CompoundNBT outTag = tag.getCompound("output");
        ItemStackUtils.deserializeStacks(invTag, stacks);
        recipeOutput = ItemStack.of(outTag);
        super.load(blockState, tag);
    }

    @Override
    @Nonnull
    public CompoundNBT save(CompoundNBT tag) {
        tag.put("inv", ItemStackUtils.serializeStacks(stacks));
        CompoundNBT outTag = new CompoundNBT();
        recipeOutput.save(outTag);
        tag.put("output", outTag);
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
    public IInventory getInventory() {
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
    ActionResultType blockActivated(@Nonnull PlayerEntity player, @Nonnull BlockRayTraceResult hit) {
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
            InventoryHelper.dropContents(level, getBlockPos(), itemStacks);

            for (int i = 0; i < stacks.size(); i++) {
                stacks.set(i, ItemStack.EMPTY);
            }

            recipeOutput = ItemStack.EMPTY;
            heldItemMainhand.hurtAndBreak(1, player, playerEntity -> playerEntity.broadcastBreakEvent(EquipmentSlotType.MAINHAND));
            level.playSound(null, getBlockPos(), SoundEvents.DISPENSER_DISPENSE, SoundCategory.BLOCKS, 1.0f, 1.0f);
            this.setChanged();
            this.level.sendBlockUpdated(worldPosition, getBlockState(), getBlockState(), 3);
            return ActionResultType.SUCCESS;
        }
		if (hit.getDirection() == Direction.UP) {
		    Direction dir = getBlockState().getValue(HorizontalBlock.FACING);
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
		        return ActionResultType.SUCCESS;
		    }

		    if (heldItemMainhand.isEmpty() && !stacks.get(y * FlintWorkbenchRecipe.MAX_WIDTH + x).isEmpty()) {
		        NonNullList<ItemStack> itemStacks = NonNullList.create();
		        itemStacks.add(stack);
		        InventoryHelper.dropContents(level, getBlockPos(), itemStacks);
		        stacks.set(y * FlintWorkbenchRecipe.MAX_WIDTH + x, ItemStack.EMPTY);
		        level.playSound(null, getBlockPos(), SoundEvents.ITEM_PICKUP, SoundCategory.BLOCKS, 1.0f, 1.0f);
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
		        return ActionResultType.SUCCESS;
		    }
		}

        return ActionResultType.PASS;
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
