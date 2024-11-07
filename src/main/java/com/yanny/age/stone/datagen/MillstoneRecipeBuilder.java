package com.yanny.age.stone.datagen;

import com.google.gson.JsonObject;
import com.yanny.age.stone.subscribers.RecipeSubscriber;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.advancements.CriterionTriggerInstance;
import net.minecraft.advancements.RequirementsStrategy;
import net.minecraft.advancements.critereon.RecipeUnlockedTrigger;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistries;
import org.lwjgl.system.NonnullDefault;

import javax.annotation.Nullable;
import java.util.Objects;
import java.util.function.Consumer;

@NonnullDefault
public class MillstoneRecipeBuilder {
    /*private final Item result;
    private final int count;
    private final Ingredient input;
    private final Advancement.Builder advancementBuilder = Advancement.Builder.advancement();
    @Nullable private String group = null;
    private int activateCount = 1;
    private float secondChance = 0;
    private Item secondResult = Items.AIR;

    public MillstoneRecipeBuilder(ItemLike resultIn, ItemLike input, int countIn) {
        this.result = resultIn.asItem();
        this.input = Ingredient.of(input);
        this.count = countIn;
    }

    public static MillstoneRecipeBuilder recipe(ItemLike resultIn, ItemLike input) {
        return recipe(resultIn, input, 1);
    }

    public static MillstoneRecipeBuilder recipe(ItemLike resultIn, ItemLike input, int countIn) {
        return new MillstoneRecipeBuilder(resultIn, input, countIn);
    }

    public MillstoneRecipeBuilder secondResult(ItemLike secondResult, float secondChance) {
        this.secondResult = secondResult.asItem();
        this.secondChance = secondChance;
        return this;
    }

    public MillstoneRecipeBuilder activateCount(int activateCount) {
        this.activateCount = activateCount;
        return this;
    }

    public MillstoneRecipeBuilder addCriterion(String name, CriterionTriggerInstance criterionIn) {
        this.advancementBuilder.addCriterion(name, criterionIn);
        return this;
    }

    public MillstoneRecipeBuilder setGroup(String groupIn) {
        this.group = groupIn;
        return this;
    }

    public void build(Consumer<FinishedRecipe> consumerIn) {
        this.build(consumerIn, Objects.requireNonNull(ForgeRegistries.ITEMS.getKey(this.result)));
    }

    public void build(Consumer<FinishedRecipe> consumerIn, String save) {
        ResourceLocation resourcelocation = ForgeRegistries.ITEMS.getKey(this.result);

        if ((new ResourceLocation(save)).equals(resourcelocation)) {
            throw new IllegalStateException("Shaped Recipe " + save + " should remove its 'save' argument");
        } else {
            this.build(consumerIn, new ResourceLocation(save));
        }
    }

    public void build(Consumer<FinishedRecipe> consumerIn, ResourceLocation id) {
        if (this.result.getItemCategory() == null) {
            throw new IllegalStateException("Recipe " + id + " has null group!");
        }

        this.advancementBuilder.parent(new ResourceLocation("recipes/root")).addCriterion("has_the_recipe", RecipeUnlockedTrigger.unlocked(id)).rewards(AdvancementRewards.Builder.recipe(id)).requirements(RequirementsStrategy.OR);
        consumerIn.accept(new Result(id, this.result, this.secondResult, this.secondChance, this.input, this.count, this.activateCount, this.group == null ? "" : this.group, this.advancementBuilder, new ResourceLocation(id.getNamespace(), "recipes/" + this.result.getItemCategory().getRecipeFolderName() + "/" + id.getPath())));
    }

    public static class Result implements FinishedRecipe {
        private final ResourceLocation id;
        private final Item result;
        private final Item secondResult;
        private final float secondChance;
        private final int count;
        private final int activateCount;
        private final String group;
        private final Ingredient input;
        private final Advancement.Builder advancementBuilder;
        private final ResourceLocation advancementId;

        public Result(ResourceLocation idIn, Item resultIn, Item secondResult, float secondChance, Ingredient input, int countIn, int activateCount, String groupIn, Advancement.Builder advancementBuilderIn, ResourceLocation advancementIdIn) {
            this.id = idIn;
            this.result = resultIn;
            this.secondResult = secondResult;
            this.secondChance = secondChance;
            this.count = countIn;
            this.activateCount = activateCount;
            this.group = groupIn;
            this.input = input;
            this.advancementBuilder = advancementBuilderIn;
            this.advancementId = advancementIdIn;
        }

        public void serializeRecipeData(JsonObject json) {
            if (!this.group.isEmpty()) {
                json.addProperty("group", this.group);
            }

            json.add("ingredient", input.toJson());
            json.addProperty("activateCount", activateCount);

            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("item", Objects.requireNonNull(ForgeRegistries.ITEMS.getKey(this.result)).toString());

            if (this.count > 1) {
                jsonObject.addProperty("count", this.count);
            }

            json.add("result", jsonObject);

            JsonObject jsonSecondObject = new JsonObject();
            jsonSecondObject.addProperty("item", Objects.requireNonNull(ForgeRegistries.ITEMS.getKey(this.secondResult)).toString());

            json.add("secondResult", jsonSecondObject);
            json.addProperty("secondChance", secondChance);
        }

        @SuppressWarnings("ConstantConditions")
        public RecipeSerializer<?> getType() {
            return RecipeSubscriber.millstone;
        }

        *//**
         * Gets the ID for the recipe.
         *//*
        public ResourceLocation getId() {
            return this.id;
        }

        *//**
         * Gets the JSON for the advancement that unlocks this recipe. Null if there is no advancement.
         *//*
        @Nullable
        public JsonObject serializeAdvancement() {
            return this.advancementBuilder.serializeToJson();
        }

        *//**
         * Gets the ID for the advancement associated with this recipe. Should not be null if {@link #getAdvancementJson}
         * is non-null.
         *//*
        @Nullable
        public ResourceLocation getAdvancementId() {
            return this.advancementId;
        }
    }*/
}
