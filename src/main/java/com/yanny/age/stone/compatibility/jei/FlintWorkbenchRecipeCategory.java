package com.yanny.age.stone.compatibility.jei;

import com.google.common.collect.ImmutableList;
import com.yanny.age.stone.Reference;
import com.yanny.age.stone.recipes.FlintWorkbenchRecipe;
import com.yanny.age.stone.subscribers.BlockSubscriber;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.drawable.IDrawableStatic;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nonnull;
import java.util.Arrays;
import java.util.List;

public class FlintWorkbenchRecipeCategory implements IRecipeCategory<FlintWorkbenchRecipe> {
    static final ResourceLocation UID = new ResourceLocation(Reference.MODID, "flint_workbench_recipe_category");

    private final String localizedName;
    private final IDrawableStatic background;
    private final IDrawable icon;

    FlintWorkbenchRecipeCategory(@Nonnull IGuiHelper guiHelper) {
        ResourceLocation location = new ResourceLocation(Reference.MODID, "textures/gui/jei/gui_layouts.png");
        background = guiHelper.createDrawable(location, 0, 0, 120, 60);
        localizedName = I18n.format("block.stone_age.flint_workbench");
        icon = guiHelper.createDrawableIngredient(new ItemStack(BlockSubscriber.flint_workbench));
    }

    @Nonnull
    @Override
    public ResourceLocation getUid() {
        return UID;
    }

    @Nonnull
    @Override
    public Class<? extends FlintWorkbenchRecipe> getRecipeClass() {
        return FlintWorkbenchRecipe.class;
    }

    @Nonnull
    @Override
    public String getTitle() {
        return localizedName;
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

    @Override
    public void setIngredients(@Nonnull FlintWorkbenchRecipe flintWorkbenchRecipe, @Nonnull IIngredients ingredients) {
        ImmutableList.Builder<List<ItemStack>> inputBuilder = ImmutableList.builder();
        ImmutableList.Builder<ItemStack> outputBuilder = ImmutableList.builder();
        int width = flintWorkbenchRecipe.getWidth();
        int height = flintWorkbenchRecipe.getHeight();

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                inputBuilder.add(Arrays.asList(flintWorkbenchRecipe.getIngredients().get(x + y * width).getMatchingStacks()));
            }
        }

        inputBuilder.add(Arrays.asList(flintWorkbenchRecipe.getTool().getMatchingStacks()));
        outputBuilder.add(flintWorkbenchRecipe.getRecipeOutput());
        ingredients.setInputLists(VanillaTypes.ITEM, inputBuilder.build());
        ingredients.setOutputLists(VanillaTypes.ITEM, ImmutableList.of(outputBuilder.build()));
    }

    @Override
    public void setRecipe(@Nonnull IRecipeLayout recipeLayout, @Nonnull FlintWorkbenchRecipe flintWorkbenchRecipe, @Nonnull IIngredients ingredients) {
        int width = flintWorkbenchRecipe.getWidth();
        int height = flintWorkbenchRecipe.getHeight();

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                recipeLayout.getItemStacks().init(x + y * width, true, 3 + x * 18, 3 + y * 18);
                recipeLayout.getItemStacks().set(x + y * width, ingredients.getInputs(VanillaTypes.ITEM).get(x + y * width));
            }
        }

        recipeLayout.getItemStacks().init(width * height, false, 97, 21);
        recipeLayout.getItemStacks().set(width * height, ingredients.getOutputs(VanillaTypes.ITEM).get(0));

        recipeLayout.getItemStacks().init(width * height + 1, false, 66, 20);
        recipeLayout.getItemStacks().set(width * height + 1, ingredients.getInputs(VanillaTypes.ITEM).get(width * height));
    }
}
