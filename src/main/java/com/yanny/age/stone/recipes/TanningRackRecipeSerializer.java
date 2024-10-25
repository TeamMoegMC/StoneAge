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
import net.minecraftforge.registries.ForgeRegistryEntry;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class TanningRackRecipeSerializer extends ForgeRegistryEntry<RecipeSerializer<?>> implements RecipeSerializer<TanningRackRecipe> {
    private final TanningRackRecipeSerializer.IFactory<TanningRackRecipe> factory;

    public TanningRackRecipeSerializer(@Nonnull TanningRackRecipeSerializer.IFactory<TanningRackRecipe> factory) {
        this.factory = factory;
    }

    @Override
    @Nonnull
    public TanningRackRecipe fromJson(@Nonnull ResourceLocation recipeId, @Nonnull JsonObject json) {
        String s = GsonHelper.getAsString(json, "group", "");
        JsonElement jsonelement = GsonHelper.isArrayNode(json, "ingredient")
                ? GsonHelper.getAsJsonArray(json, "ingredient")
                : GsonHelper.getAsJsonObject(json, "ingredient");
        Ingredient ingredient = Ingredient.fromJson(jsonelement);
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

        JsonElement toolElement = GsonHelper.isArrayNode(json, "tool")
                ? GsonHelper.getAsJsonArray(json, "tool")
                : GsonHelper.getAsJsonObject(json, "tool");
        Ingredient tool = Ingredient.fromJson(toolElement);

        return this.factory.create(recipeId, s, ingredient, itemstack, tool);
    }

    @Nullable
    @Override
    public TanningRackRecipe fromNetwork(@Nonnull ResourceLocation recipeId, FriendlyByteBuf buffer) {
        String s = buffer.readUtf(32767);
        Ingredient ingredient = Ingredient.fromNetwork(buffer);
        ItemStack itemstack = buffer.readItem();
        Ingredient tool = Ingredient.fromNetwork(buffer);

        return this.factory.create(recipeId, s, ingredient, itemstack, tool);
    }

    @Override
    public void toNetwork(FriendlyByteBuf buffer, TanningRackRecipe recipe) {
        buffer.writeUtf(recipe.group);
        recipe.ingredient.toNetwork(buffer);
        buffer.writeItem(recipe.result);
        recipe.tool.toNetwork(buffer);
    }

    public interface IFactory<T extends TanningRackRecipe> {
        T create(@Nonnull ResourceLocation resourceLocation, @Nonnull String group, @Nonnull Ingredient ingredient, @Nonnull ItemStack result, @Nonnull Ingredient tool);
    }
}
