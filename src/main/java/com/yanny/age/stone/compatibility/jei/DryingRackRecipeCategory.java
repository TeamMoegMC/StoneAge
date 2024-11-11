package com.yanny.age.stone.compatibility.jei;

import com.google.common.collect.ImmutableList;
import com.yanny.age.stone.Reference;
import com.yanny.age.stone.recipes.DryingRackRecipe;
import com.yanny.age.stone.subscribers.BlockSubscriber;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.world.item.ItemStack;
import net.minecraft.resources.ResourceLocation;

import javax.annotation.Nonnull;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class DryingRackRecipeCategory /*implements IRecipeCategory<DryingRackRecipe>*/ {
    static final ResourceLocation UID = new ResourceLocation(Reference.MODID, "drying_rack_recipe_category");

    /*private final String localizedName;
    private final IDrawableStatic background;
    private final IDrawable icon;

    DryingRackRecipeCategory(@Nonnull IGuiHelper guiHelper) {
        ResourceLocation location = new ResourceLocation(Reference.MODID, "textures/gui/jei/gui_layouts.png");
        background = guiHelper.createDrawable(location, 0, 61, 120, 60);
        localizedName = I18n.get("block.stone_age.drying_rack");
        icon = guiHelper.createDrawableIngredient(new ItemStack(BlockSubscriber.drying_rack));
    }

    @Nonnull
    @Override
    public ResourceLocation getUid() {
        return UID;
    }

    @Nonnull
    @Override
    public Class<? extends DryingRackRecipe> getRecipeClass() {
        return DryingRackRecipe.class;
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
    public void setIngredients(@Nonnull DryingRackRecipe dryingRackRecipe, @Nonnull IIngredients ingredients) {
        ImmutableList.Builder<List<ItemStack>> inputBuilder = ImmutableList.builder();
        ImmutableList.Builder<ItemStack> outputBuilder = ImmutableList.builder();

        inputBuilder.add(Arrays.asList(dryingRackRecipe.getIngredients().get(0).getItems()));
        outputBuilder.add(dryingRackRecipe.getResultItem());

        ingredients.setInputLists(VanillaTypes.ITEM, inputBuilder.build());
        ingredients.setOutputLists(VanillaTypes.ITEM, ImmutableList.of(outputBuilder.build()));
    }

    @Override
    public void setRecipe(@Nonnull IRecipeLayout recipeLayout, @Nonnull DryingRackRecipe dryingRackRecipe, @Nonnull IIngredients ingredients) {
        recipeLayout.getItemStacks().init(0, true, 21, 21);
        recipeLayout.getItemStacks().set(0, ingredients.getInputs(VanillaTypes.ITEM).get(0));

        recipeLayout.getItemStacks().init(1, false, 84, 22);
        recipeLayout.getItemStacks().set(1, ingredients.getOutputs(VanillaTypes.ITEM).get(0));

        recipeLayout.getItemStacks().init(2, false, 51, 21);
        recipeLayout.getItemStacks().set(2, Collections.singletonList(new ItemStack(BlockSubscriber.drying_rack)));
    }*/
}
