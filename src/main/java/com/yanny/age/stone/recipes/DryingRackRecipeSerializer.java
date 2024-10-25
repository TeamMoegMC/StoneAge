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

public class DryingRackRecipeSerializer extends ForgeRegistryEntry<RecipeSerializer<?>> implements RecipeSerializer<DryingRackRecipe> {
    private final DryingRackRecipeSerializer.IFactory<DryingRackRecipe> factory;

    public DryingRackRecipeSerializer(@Nonnull DryingRackRecipeSerializer.IFactory<DryingRackRecipe> factory) {
        this.factory = factory;
    }

    @Override
    @Nonnull
    public DryingRackRecipe fromJson(@Nonnull ResourceLocation recipeId, @Nonnull JsonObject json) {
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

        int i = GsonHelper.getAsInt(json, "dryingTime", 200);

        return this.factory.create(recipeId, s, ingredient, itemstack, i);
    }

    @Nullable
    @Override
    public DryingRackRecipe fromNetwork(@Nonnull ResourceLocation recipeId, FriendlyByteBuf buffer) {
        String s = buffer.readUtf(32767);
        Ingredient ingredient = Ingredient.fromNetwork(buffer);
        ItemStack itemstack = buffer.readItem();

        int i = buffer.readVarInt();

        return this.factory.create(recipeId, s, ingredient, itemstack, i);
    }

    @Override
    public void toNetwork(FriendlyByteBuf buffer, DryingRackRecipe recipe) {
        buffer.writeUtf(recipe.group);
        recipe.ingredient.toNetwork(buffer);
        buffer.writeItem(recipe.result);
        buffer.writeVarInt(recipe.dryingTime);
    }

    public interface IFactory<T extends DryingRackRecipe> {
        T create(@Nonnull ResourceLocation resourceLocation, @Nonnull String group, @Nonnull Ingredient ingredient, @Nonnull ItemStack result, int dryingTime);
    }
}
