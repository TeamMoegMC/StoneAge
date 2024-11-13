package com.yanny.age.stone.compatibility.jei;

import com.google.common.collect.ImmutableList;
import com.yanny.age.stone.Reference;
import com.yanny.age.stone.recipes.DryingRackRecipe;
import com.yanny.age.stone.subscribers.BlockSubscriber;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.drawable.IDrawableStatic;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.resources.ResourceLocation;

import javax.annotation.Nonnull;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class DryingRackRecipeCategory implements IRecipeCategory<DryingRackRecipe> {
    static final ResourceLocation UID = new ResourceLocation(Reference.MODID, "drying_rack_recipe_category");

    private final String localizedName;
    private final IDrawableStatic background;
    private final IDrawable icon;
    public static final RecipeType<DryingRackRecipe> DRYINGRACK_RECIPE_TYPE = new RecipeType<>(UID,DryingRackRecipe.class);

    DryingRackRecipeCategory(@Nonnull IGuiHelper guiHelper) {
        ResourceLocation location = new ResourceLocation(Reference.MODID, "textures/gui/jei/gui_layouts.png");
        background = guiHelper.createDrawable(location, 0, 61, 120, 60);
        localizedName = I18n.get("block.stone_age.drying_rack");
        icon = guiHelper.createDrawableItemStack(new ItemStack(BlockSubscriber.drying_rack));
    }

/*    @Nonnull
    @Override
    public ResourceLocation getUid() {
        return UID;
    }

    @Nonnull
    @Override
    public Class<? extends DryingRackRecipe> getRecipeClass() {
        return DryingRackRecipe.class;
    }*/

    @Override
    public RecipeType<DryingRackRecipe> getRecipeType() {
        return DRYINGRACK_RECIPE_TYPE;
    }

    @Nonnull
    @Override
    public Component getTitle() {
        return Component.literal(localizedName);
    }

    @Nonnull
    @Override
    public IDrawable getBackground() {
        return background;
    }

    @Nonnull
    @Override
    public IDrawable getIcon() {
        return icon;
    }

/*    @Override
    public void setIngredients(@Nonnull DryingRackRecipe dryingRackRecipe, @Nonnull IIngredients ingredients) {
        ImmutableList.Builder<List<ItemStack>> inputBuilder = ImmutableList.builder();
        ImmutableList.Builder<ItemStack> outputBuilder = ImmutableList.builder();

        inputBuilder.add(Arrays.asList(dryingRackRecipe.getIngredients().get(0).getItems()));
        outputBuilder.add(dryingRackRecipe.getResultItem());

        ingredients.setInputLists(VanillaTypes.ITEM, inputBuilder.build());
        ingredients.setOutputLists(VanillaTypes.ITEM, ImmutableList.of(outputBuilder.build()));
    }*/


    @Override
    public void setRecipe(@Nonnull IRecipeLayoutBuilder builder, @Nonnull DryingRackRecipe dryingRackRecipe, @Nonnull IFocusGroup focuses) {
//        recipeLayout.getItemStacks().init(0, true, 21, 21);
//        recipeLayout.getItemStacks().set(0, ingredients.getInputs(VanillaTypes.ITEM).get(0));
//
//        recipeLayout.getItemStacks().init(1, false, 84, 22);
//        recipeLayout.getItemStacks().set(1, ingredients.getOutputs(VanillaTypes.ITEM).get(0));
//
//        recipeLayout.getItemStacks().init(2, false, 51, 21);
//        recipeLayout.getItemStacks().set(2, Collections.singletonList(new ItemStack(BlockSubscriber.drying_rack)));

        builder.addSlot(RecipeIngredientRole.INPUT, 22, 22).addIngredients(dryingRackRecipe.getIngredients().get(0));
        builder.addSlot(RecipeIngredientRole.OUTPUT, 85, 23).addItemStack(dryingRackRecipe.getResultItem(null));
    }
}
