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
public class TreeStumpRecipeBuilder {
    /*private final Item result;
    private final int count;
    private final Ingredient input;
    private final Advancement.Builder advancementBuilder = Advancement.Builder.advancement();
    @Nullable private String group = null;
    @Nullable private Ingredient tool = null;
    private int chopTimes = 1;

    public TreeStumpRecipeBuilder(ItemLike resultIn, ItemLike input, int countIn) {
        this.result = resultIn.asItem();
        this.input = Ingredient.of(input);
        this.count = countIn;
    }

    public static TreeStumpRecipeBuilder recipe(ItemLike resultIn, ItemLike input) {
        return recipe(resultIn, input, 1);
    }

    public static TreeStumpRecipeBuilder recipe(ItemLike resultIn, ItemLike input, int countIn) {
        return new TreeStumpRecipeBuilder(resultIn, input, countIn);
    }

    public TreeStumpRecipeBuilder tool(Tag<Item> tool) {
        return this.tool(Ingredient.of(tool));
    }

    public TreeStumpRecipeBuilder tool(ItemLike tool) {
        return this.tool(Ingredient.of(tool));
    }

    public TreeStumpRecipeBuilder tool(Ingredient tool) {
        if (this.tool != null) {
            throw new IllegalArgumentException("Tool is already defined!");
        } else {
            this.tool = tool;
            return this;
        }
    }

    public TreeStumpRecipeBuilder chopTimes(int chopTimes) {
        this.chopTimes = chopTimes;
        return this;
    }

    public TreeStumpRecipeBuilder addCriterion(String name, CriterionTriggerInstance criterionIn) {
        this.advancementBuilder.addCriterion(name, criterionIn);
        return this;
    }

    public TreeStumpRecipeBuilder setGroup(String groupIn) {
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
        } else if (this.tool == null) {
            throw new IllegalStateException("Tool is not set!");
        }

        this.advancementBuilder.parent(new ResourceLocation("recipes/root")).addCriterion("has_the_recipe", RecipeUnlockedTrigger.unlocked(id)).rewards(AdvancementRewards.Builder.recipe(id)).requirements(RequirementsStrategy.OR);
        consumerIn.accept(new Result(id, this.result, this.input, this.count, this.chopTimes, this.group == null ? "" : this.group, this.tool, this.advancementBuilder, new ResourceLocation(id.getNamespace(), "recipes/" + this.result.getItemCategory().getRecipeFolderName() + "/" + id.getPath())));
    }

    public static class Result implements FinishedRecipe {
        private final ResourceLocation id;
        private final Item result;
        private final int count;
        private final int chopTimes;
        private final String group;
        private final Ingredient input;
        private final Ingredient tool;
        private final Advancement.Builder advancementBuilder;
        private final ResourceLocation advancementId;

        public Result(ResourceLocation idIn, Item resultIn, Ingredient input, int countIn, int chopTimes, String groupIn, Ingredient tool, Advancement.Builder advancementBuilderIn, ResourceLocation advancementIdIn) {
            this.id = idIn;
            this.result = resultIn;
            this.count = countIn;
            this.chopTimes = chopTimes;
            this.group = groupIn;
            this.tool = tool;
            this.input = input;
            this.advancementBuilder = advancementBuilderIn;
            this.advancementId = advancementIdIn;
        }

        public void serializeRecipeData(JsonObject json) {
            if (!this.group.isEmpty()) {
                json.addProperty("group", this.group);
            }

            json.add("tool", tool.toJson());
            json.add("ingredient", input.toJson());
            json.addProperty("chopTimes", chopTimes);

            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("item", Objects.requireNonNull(ForgeRegistries.ITEMS.getKey(this.result)).toString());

            if (this.count > 1) {
                jsonObject.addProperty("count", this.count);
            }

            json.add("result", jsonObject);
        }

        @SuppressWarnings("ConstantConditions")
        public RecipeSerializer<?> getType() {
            return RecipeSubscriber.tree_stump;
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
