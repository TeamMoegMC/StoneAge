package com.yanny.age.stone.recipes;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.util.GsonHelper;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.common.crafting.NBTIngredient;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.ForgeRegistryEntry;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class MillstoneRecipeSerializer extends ForgeRegistryEntry<RecipeSerializer<?>> implements RecipeSerializer<MillstoneRecipe> {
    private final MillstoneRecipeSerializer.IFactory<MillstoneRecipe> factory;

    public MillstoneRecipeSerializer(@Nonnull MillstoneRecipeSerializer.IFactory<MillstoneRecipe> factory) {
        this.factory = factory;
    }

    @Override
    @Nonnull
    public MillstoneRecipe fromJson(@Nonnull ResourceLocation recipeId, @Nonnull JsonObject json) {
        String group = GsonHelper.getAsString(json, "group", "");
        JsonElement jsonelement = GsonHelper.isArrayNode(json, "ingredient")
                ? GsonHelper.getAsJsonArray(json, "ingredient")
                : GsonHelper.getAsJsonObject(json, "ingredient");
        Ingredient ingredient = NBTIngredient.fromJson(jsonelement);
        ItemStack result;
        ItemStack secondResult = ItemStack.EMPTY;
        int activateCount = GsonHelper.getAsInt(json, "activateCount", 1);
        double secondChance = GsonHelper.getAsFloat(json, "secondChance", 1.0f);

        if (!json.has("result")) {
            throw new com.google.gson.JsonSyntaxException("Missing result, expected to find a string or object");
        }

        if (json.get("result").isJsonObject()) {
            result = CraftingHelper.getItemStack(GsonHelper.getAsJsonObject(json, "result"), true);
        } else {
            String s1 = GsonHelper.getAsString(json, "result");
            ResourceLocation resourcelocation = new ResourceLocation(s1);
            result = new ItemStack(ForgeRegistries.ITEMS.getValue(resourcelocation));
        }

        if (json.has("secondResult")) {
            if (json.get("secondResult").isJsonObject()) {
                secondResult = CraftingHelper.getItemStack(GsonHelper.getAsJsonObject(json, "secondResult"), true);
            } else {
                String string = GsonHelper.getAsString(json, "secondResult");
                ResourceLocation resourceLocation = new ResourceLocation(string);
                secondResult = new ItemStack(ForgeRegistries.ITEMS.getValue(resourceLocation));
            }
        } else {
            secondChance = 0;
        }

        return this.factory.create(recipeId, group, ingredient, result, secondResult, secondChance, activateCount);
    }

    @Nullable
    @Override
    public MillstoneRecipe fromNetwork(@Nonnull ResourceLocation recipeId, FriendlyByteBuf buffer) {
        String group = buffer.readUtf(32767);
        Ingredient ingredient = Ingredient.fromNetwork(buffer);
        ItemStack result = buffer.readItem();
        ItemStack secondResult = buffer.readItem();
        double secondChance = buffer.readDouble();
        int activateCount = buffer.readInt();

        return this.factory.create(recipeId, group, ingredient, result, secondResult, secondChance, activateCount);
    }

    @Override
    public void toNetwork(FriendlyByteBuf buffer, MillstoneRecipe recipe) {
        buffer.writeUtf(recipe.group);
        recipe.ingredient.toNetwork(buffer);
        buffer.writeItem(recipe.result);
        buffer.writeItem(recipe.secondResult);
        buffer.writeDouble(recipe.secondChance);
        buffer.writeInt(recipe.activateCount);
    }

    public interface IFactory<T extends MillstoneRecipe> {
        T create(@Nonnull ResourceLocation resourceLocation, @Nonnull String group, @Nonnull Ingredient ingredient, @Nonnull ItemStack result,
                 @Nonnull ItemStack secondResult, double secondChance, int activateCount);
    }
}
