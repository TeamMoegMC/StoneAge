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
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class TreeStumpRecipeSerializer implements RecipeSerializer<TreeStumpRecipe> {
    private final TreeStumpRecipeSerializer.IFactory<TreeStumpRecipe> factory;

    public TreeStumpRecipeSerializer(@Nonnull TreeStumpRecipeSerializer.IFactory<TreeStumpRecipe> factory) {
        this.factory = factory;
    }

    @Override
    @Nonnull
    public TreeStumpRecipe fromJson(@Nonnull ResourceLocation recipeId, @Nonnull JsonObject json) {
        String s = GsonHelper.getAsString(json, "group", "");
        JsonElement ingredientJsonElement = GsonHelper.isArrayNode(json, "ingredient")
                ? GsonHelper.getAsJsonArray(json, "ingredient")
                : GsonHelper.getAsJsonObject(json, "ingredient");
        JsonElement toolJsonElement = GsonHelper.isArrayNode(json, "tool")
                ? GsonHelper.getAsJsonArray(json, "tool")
                : GsonHelper.getAsJsonObject(json, "tool");
        Ingredient ingredient = Ingredient.fromJson(ingredientJsonElement);
        Ingredient tool = Ingredient.fromJson(toolJsonElement);
        ItemStack itemstack;

        if (!json.has("result")) {
            throw new com.google.gson.JsonSyntaxException("Missing result, expected to find a string or object");
        }

        if (json.get("result").isJsonObject()) {
            itemstack = CraftingHelper.getItemStack(GsonHelper.getAsJsonObject(json, "result"), true);
        } else {
            String s1 = GsonHelper.getAsString(json, "result");
            ResourceLocation resourcelocation = new ResourceLocation(s1);
            itemstack = new ItemStack(ForgeRegistries.ITEMS.getValue(resourcelocation));
        }

        int i = GsonHelper.getAsInt(json, "chopTimes", 1);
        int a = GsonHelper.getAsInt(json, "amount", 1);

        ingredient.getItems()[0].setCount(a);

        return this.factory.create(recipeId, s, ingredient, tool, itemstack, i);
    }

    @Nullable
    @Override
    public TreeStumpRecipe fromNetwork(@Nonnull ResourceLocation recipeId, FriendlyByteBuf buffer) {
        String s = buffer.readUtf(32767);
        Ingredient ingredient = Ingredient.fromNetwork(buffer);
        Ingredient tool = Ingredient.fromNetwork(buffer);
        ItemStack itemstack = buffer.readItem();

        int i = buffer.readVarInt();

        return this.factory.create(recipeId, s, ingredient, tool, itemstack, i);
    }

    @Override
    public void toNetwork(FriendlyByteBuf buffer, TreeStumpRecipe recipe) {
        buffer.writeUtf(recipe.group);
        recipe.ingredient.toNetwork(buffer);
        recipe.tool.toNetwork(buffer);
        buffer.writeItem(recipe.result);
        buffer.writeVarInt(recipe.chopTimes);
    }

    public interface IFactory<T extends TreeStumpRecipe> {
        T create(@Nonnull ResourceLocation resourceLocation, @Nonnull String group, @Nonnull Ingredient ingredient, @Nonnull Ingredient tool,
                 @Nonnull ItemStack result, int chopTimes);
    }
}
