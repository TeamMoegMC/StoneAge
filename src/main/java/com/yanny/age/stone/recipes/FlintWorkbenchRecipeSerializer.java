package com.yanny.age.stone.recipes;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.util.GsonHelper;
import net.minecraft.core.NonNullList;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.crafting.CraftingHelper;

import javax.annotation.Nonnull;
import java.util.Map;
import java.util.Set;

public class FlintWorkbenchRecipeSerializer implements RecipeSerializer<FlintWorkbenchRecipe> {
    @Nonnull
    @Override
    public FlintWorkbenchRecipe fromJson(@Nonnull final ResourceLocation recipeID, @Nonnull final JsonObject json) {
        String s = GsonHelper.getAsString(json, "group", "");
        Map<String, Ingredient> map = deserializeKey(GsonHelper.getAsJsonObject(json, "key"));
        String[] astring = shrink(patternFromJson(GsonHelper.getAsJsonArray(json, "pattern")));
        int i = astring[0].length();
        int j = astring.length;
        NonNullList<Ingredient> nonnulllist = deserializeIngredients(astring, map, i, j);
        ItemStack itemstack = CraftingHelper.getItemStack(GsonHelper.getAsJsonObject(json, "result"), true);
        Ingredient tool = Ingredient.fromJson(GsonHelper.getAsJsonObject(json, "tool"));
        return new FlintWorkbenchRecipe(recipeID, s, i, j, tool, nonnulllist, itemstack);
    }

    @Override
    public FlintWorkbenchRecipe fromNetwork(@Nonnull final ResourceLocation recipeID, final FriendlyByteBuf buffer) {
        final int width = buffer.readVarInt();
        final int height = buffer.readVarInt();
        final String group = buffer.readUtf(Short.MAX_VALUE);
        final NonNullList<Ingredient> ingredients = NonNullList.withSize(width * height, Ingredient.EMPTY);

        for (int i = 0; i < ingredients.size(); ++i) {
            ingredients.set(i, Ingredient.fromNetwork(buffer));
        }

        final ItemStack result = buffer.readItem();
        final Ingredient tool = Ingredient.fromNetwork(buffer);
        return new FlintWorkbenchRecipe(recipeID, group, width, height, tool, ingredients, result);
    }

    @Override
    public void toNetwork(final FriendlyByteBuf buffer, final FlintWorkbenchRecipe recipe) {
        buffer.writeVarInt(recipe.getWidth());
        buffer.writeVarInt(recipe.getHeight());
        buffer.writeUtf(recipe.getGroup());

        for (final Ingredient ingredient : recipe.getIngredients()) {
            ingredient.toNetwork(buffer);
        }

        buffer.writeItem(recipe.getResultItem(null));
        recipe.getTool().toNetwork(buffer);
    }

    @Nonnull
    private static Map<String, Ingredient> deserializeKey(@Nonnull JsonObject json) {
        Map<String, Ingredient> map = Maps.newHashMap();

        for(Map.Entry<String, JsonElement> entry : json.entrySet()) {
            if (entry.getKey().length() != 1) {
                throw new JsonSyntaxException("Invalid key entry: '" + entry.getKey() + "' is an invalid symbol (must be 1 character only).");
            }

            if (" ".equals(entry.getKey())) {
                throw new JsonSyntaxException("Invalid key entry: ' ' is a reserved symbol.");
            }

            map.put(entry.getKey(), Ingredient.fromJson(entry.getValue()));
        }

        map.put(" ", Ingredient.EMPTY);
        return map;
    }

    @Nonnull
    private static String[] shrink(@Nonnull String... toShrink) {
        int i = Integer.MAX_VALUE;
        int j = 0;
        int k = 0;
        int l = 0;

        for(int i1 = 0; i1 < toShrink.length; ++i1) {
            String s = toShrink[i1];
            i = Math.min(i, firstNonSpace(s));
            int j1 = lastNonSpace(s);
            j = Math.max(j, j1);
            if (j1 < 0) {
                if (k == i1) {
                    ++k;
                }

                ++l;
            } else {
                l = 0;
            }
        }

        if (toShrink.length == l) {
            return new String[0];
        } else {
            String[] astring = new String[toShrink.length - l - k];

            for(int k1 = 0; k1 < astring.length; ++k1) {
                astring[k1] = toShrink[k1 + k].substring(i, j + 1);
            }

            return astring;
        }
    }

    private static int firstNonSpace(@Nonnull String str) {
        int i;
        //noinspection StatementWithEmptyBody
        for(i = 0; i < str.length() && str.charAt(i) == ' '; ++i) {}

        return i;
    }

    private static int lastNonSpace(@Nonnull String str) {
        int i;
        //noinspection StatementWithEmptyBody
        for(i = str.length() - 1; i >= 0 && str.charAt(i) == ' '; --i) {}

        return i;
    }

    @Nonnull
    private static NonNullList<Ingredient> deserializeIngredients(@Nonnull String[] pattern, @Nonnull Map<String, Ingredient> keys, int patternWidth, int patternHeight) {
        NonNullList<Ingredient> nonnulllist = NonNullList.withSize(patternWidth * patternHeight, Ingredient.EMPTY);
        Set<String> set = Sets.newHashSet(keys.keySet());
        set.remove(" ");

        for(int i = 0; i < pattern.length; ++i) {
            for(int j = 0; j < pattern[i].length(); ++j) {
                String s = pattern[i].substring(j, j + 1);
                Ingredient ingredient = keys.get(s);
                if (ingredient == null) {
                    throw new JsonSyntaxException("Pattern references symbol '" + s + "' but it's not defined in the key");
                }

                set.remove(s);
                nonnulllist.set(j + patternWidth * i, ingredient);
            }
        }

        if (!set.isEmpty()) {
            throw new JsonSyntaxException("Key defines symbols that aren't used in pattern: " + set);
        } else {
            return nonnulllist;
        }
    }

    @Nonnull
    private static String[] patternFromJson(@Nonnull JsonArray jsonArr) {
        String[] astring = new String[jsonArr.size()];
        if (astring.length > 3) {
            throw new JsonSyntaxException("Invalid pattern: too many rows, " + 3 + " is maximum");
        } else if (astring.length == 0) {
            throw new JsonSyntaxException("Invalid pattern: empty pattern not allowed");
        } else {
            for(int i = 0; i < astring.length; ++i) {
                String s = GsonHelper.convertToString(jsonArr.get(i), "pattern[" + i + "]");
                if (s.length() > 3) {
                    throw new JsonSyntaxException("Invalid pattern: too many columns, " + 3 + " is maximum");
                }

                if (i > 0 && astring[0].length() != s.length()) {
                    throw new JsonSyntaxException("Invalid pattern: each row must be the same width");
                }

                astring[i] = s;
            }

            return astring;
        }
    }
}
