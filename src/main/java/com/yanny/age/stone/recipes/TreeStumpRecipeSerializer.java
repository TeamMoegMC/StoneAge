package com.yanny.age.stone.recipes;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.ForgeRegistryEntry;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class TreeStumpRecipeSerializer extends ForgeRegistryEntry<IRecipeSerializer<?>> implements IRecipeSerializer<TreeStumpRecipe> {
    private final TreeStumpRecipeSerializer.IFactory<TreeStumpRecipe> factory;

    public TreeStumpRecipeSerializer(@Nonnull TreeStumpRecipeSerializer.IFactory<TreeStumpRecipe> factory) {
        this.factory = factory;
    }

    @Override
    @Nonnull
    public TreeStumpRecipe fromJson(@Nonnull ResourceLocation recipeId, @Nonnull JsonObject json) {
        String s = JSONUtils.getAsString(json, "group", "");
        JsonElement ingredientJsonElement = JSONUtils.isArrayNode(json, "ingredient")
                ? JSONUtils.getAsJsonArray(json, "ingredient")
                : JSONUtils.getAsJsonObject(json, "ingredient");
        JsonElement toolJsonElement = JSONUtils.isArrayNode(json, "tool")
                ? JSONUtils.getAsJsonArray(json, "tool")
                : JSONUtils.getAsJsonObject(json, "tool");
        Ingredient ingredient = Ingredient.fromJson(ingredientJsonElement);
        Ingredient tool = Ingredient.fromJson(toolJsonElement);
        ItemStack itemstack;

        if (!json.has("result")) {
            throw new com.google.gson.JsonSyntaxException("Missing result, expected to find a string or object");
        }

        if (json.get("result").isJsonObject()) {
            itemstack = CraftingHelper.getItemStack(JSONUtils.getAsJsonObject(json, "result"), true);
        } else {
            String s1 = JSONUtils.getAsString(json, "result");
            ResourceLocation resourcelocation = new ResourceLocation(s1);
            itemstack = new ItemStack(ForgeRegistries.ITEMS.getValue(resourcelocation));
        }

        int i = JSONUtils.getAsInt(json, "chopTimes", 1);
        int a = JSONUtils.getAsInt(json, "amount", 1);

        ingredient.getItems()[0].setCount(a);

        return this.factory.create(recipeId, s, ingredient, tool, itemstack, i);
    }

    @Nullable
    @Override
    public TreeStumpRecipe fromNetwork(@Nonnull ResourceLocation recipeId, PacketBuffer buffer) {
        String s = buffer.readUtf(32767);
        Ingredient ingredient = Ingredient.fromNetwork(buffer);
        Ingredient tool = Ingredient.fromNetwork(buffer);
        ItemStack itemstack = buffer.readItem();

        int i = buffer.readVarInt();

        return this.factory.create(recipeId, s, ingredient, tool, itemstack, i);
    }

    @Override
    public void toNetwork(PacketBuffer buffer, TreeStumpRecipe recipe) {
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
