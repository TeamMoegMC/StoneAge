package com.yanny.age.stone.recipes;

import com.yanny.age.stone.Reference;
import com.yanny.age.stone.subscribers.BlockSubscriber;
import com.yanny.age.stone.subscribers.RecipeSubscriber;
import net.minecraft.core.RegistryAccess;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.core.NonNullList;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class MillstoneRecipe implements Recipe<Container> {
    public static final RecipeType<MillstoneRecipe> millstone = RecipeType.simple(new ResourceLocation(Reference.MODID + ":millstone"));

    private final RecipeType<?> type;
    private final ResourceLocation id;
    final String group;
    final Ingredient ingredient;
    final ItemStack result;
    final ItemStack secondResult;
    final double secondChance;
    final int activateCount;

    public MillstoneRecipe(@Nonnull ResourceLocation resourceLocation, @Nonnull String group, @Nonnull Ingredient ingredient,
                           @Nonnull ItemStack result, @Nonnull ItemStack secondResult, double secondChance, int activateCount) {
        type = millstone;
        id = resourceLocation;
        this.group = group;
        this.ingredient = ingredient;
        this.result = result;
        this.secondResult = secondResult;
        this.secondChance = secondChance;
        this.activateCount = activateCount;
    }

    @Override
    public boolean matches(Container inv, @Nonnull Level worldIn) {
        return this.ingredient.test(inv.getItem(0));
    }

    @Override
    @Nonnull
    public ItemStack assemble(@Nullable Container inv, RegistryAccess access) {
        return result.copy();
    }

    @Nonnull
    public ItemStack getCraftingSecondResult() {
        return secondResult.copy();
    }

    @Override
    public boolean canCraftInDimensions(int width, int height) {
        return true;
    }

    @Override
    @Nonnull
    public ItemStack getResultItem(RegistryAccess access) {
        return result;
    }

    @Nonnull
    public ItemStack getRecipeSecondOutput() {
        return secondResult;
    }

    @Override
    @Nonnull
    public ResourceLocation getId() {
        return id;
    }

    @Override
    @Nonnull
    public RecipeSerializer<?> getSerializer() {
        //noinspection ConstantConditions
        return RecipeSubscriber.millstone_serializer;
    }

    @Override
    @Nonnull
    public RecipeType<?> getType() {
        return type;
    }

    @Override
    @Nonnull
    public NonNullList<Ingredient> getIngredients() {
        NonNullList<Ingredient> nonnulllist = NonNullList.create();
        nonnulllist.add(this.ingredient);
        return nonnulllist;
    }

    @Override
    @Nonnull
    public ItemStack getToastSymbol() {
        return new ItemStack(BlockSubscriber.millstone);
    }

    public double getSecondChance() {
        return secondChance;
    }

    public int getActivateCount() {
        return activateCount;
    }
    @Override
    public boolean isSpecial()
    {
        return true;
    }
}
