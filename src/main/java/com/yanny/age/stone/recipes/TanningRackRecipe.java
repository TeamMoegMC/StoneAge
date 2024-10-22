package com.yanny.age.stone.recipes;

import com.yanny.age.stone.Reference;
import com.yanny.age.stone.subscribers.BlockSubscriber;
import com.yanny.age.stone.subscribers.RecipeSubscriber;
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

public class TanningRackRecipe implements Recipe<Container> {
    public static final RecipeType<TanningRackRecipe> tanning_rack = RecipeType.register(Reference.MODID + ":tanning_rack");

    private final RecipeType<?> type;
    private final ResourceLocation id;
    final String group;
    final Ingredient ingredient;
    final ItemStack result;
    final Ingredient tool;

    public TanningRackRecipe(@Nonnull ResourceLocation resourceLocation, @Nonnull String group, @Nonnull Ingredient ingredient,
                             @Nonnull ItemStack result, @Nonnull Ingredient tool) {
        type = tanning_rack;
        id = resourceLocation;
        this.group = group;
        this.ingredient = ingredient;
        this.result = result;
        this.tool = tool;
    }

    @Override
    public boolean matches(Container inv, @Nonnull Level worldIn) {
        return this.ingredient.test(inv.getItem(0));
    }

    @Override
    @Nonnull
    public ItemStack assemble(@Nullable Container inv) {
        return this.result.copy();
    }

    @Override
    public boolean canCraftInDimensions(int width, int height) {
        return true;
    }

    @Override
    @Nonnull
    public ItemStack getResultItem() {
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
        return RecipeSubscriber.tanning_rack;
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
        return new ItemStack(BlockSubscriber.tanning_rack);
    }

    @Nonnull
    public Ingredient getTool() {
        return tool;
    }
}
