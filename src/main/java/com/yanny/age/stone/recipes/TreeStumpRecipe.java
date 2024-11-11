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

public class TreeStumpRecipe implements Recipe<Container> {
    public static final RecipeType<TreeStumpRecipe> tree_stump = RecipeType.simple(new ResourceLocation(Reference.MODID + ":tree_stump"));

    private final RecipeType<?> type;
    private final ResourceLocation id;
    final String group;
    final Ingredient ingredient;
    final Ingredient tool;
    final ItemStack result;
    final int chopTimes;

    public TreeStumpRecipe(@Nonnull ResourceLocation resourceLocation, @Nonnull String group, @Nonnull Ingredient ingredient,
                           @Nonnull Ingredient tool, @Nonnull ItemStack result, int chopTimes) {
        type = tree_stump;
        id = resourceLocation;
        this.group = group;
        this.ingredient = ingredient;
        this.tool = tool;
        this.result = result;
        this.chopTimes = chopTimes;
    }

    @Override
    public boolean matches(Container inv, @Nonnull Level worldIn) {
        return this.ingredient.test(inv.getItem(0));
    }

    @Override
    @Nonnull
    public ItemStack assemble(@Nullable Container inv, RegistryAccess access) {
        return this.result.copy();
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

    @Override
    @Nonnull
    public ResourceLocation getId() {
        return id;
    }

    @Override
    @Nonnull
    public RecipeSerializer<?> getSerializer() {
        //noinspection ConstantConditions
        return RecipeSubscriber.tree_stump_serializer;
    }

    @Override
    @Nonnull
    public RecipeType<?> getType() {
        return type;
    }

    @Override
    @Nonnull
    public NonNullList<Ingredient> getIngredients() {
        NonNullList<Ingredient> nonNullList = NonNullList.create();
        nonNullList.add(this.ingredient);
        return nonNullList;
    }

    @Nonnull
    public NonNullList<Ingredient> getTools() {
        NonNullList<Ingredient> nonNullList = NonNullList.create();
        nonNullList.add(this.tool);
        return nonNullList;
    }

    @Override
    @Nonnull
    public ItemStack getToastSymbol() {
        return new ItemStack(BlockSubscriber.tree_stump);
    }

    public int getChopTimes() {
        return chopTimes;
    }
}
