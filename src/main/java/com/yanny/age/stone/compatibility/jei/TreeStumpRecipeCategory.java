package com.yanny.age.stone.compatibility.jei;

import com.yanny.age.stone.Reference;
import net.minecraft.resources.ResourceLocation;

public class TreeStumpRecipeCategory /*implements IRecipeCategory<TreeStumpRecipe> */{
    static final ResourceLocation UID = new ResourceLocation(Reference.MODID, "tree_stump_recipe_category");

    /*private final String localizedName;
    private final IDrawableStatic background;
    private final IDrawable icon;

    TreeStumpRecipeCategory(@Nonnull IGuiHelper guiHelper) {
        ResourceLocation location = new ResourceLocation(Reference.MODID, "textures/gui/jei/gui_layouts.png");
        background = guiHelper.createDrawable(location, 0, 61, 120, 60);
        localizedName = I18n.get("block.stone_age.tree_stump");
        icon = guiHelper.createDrawableIngredient(new ItemStack(BlockSubscriber.tree_stump));
    }

    @Nonnull
    @Override
    public ResourceLocation getUid() {
        return UID;
    }

    @Nonnull
    @Override
    public Class<? extends TreeStumpRecipe> getRecipeClass() {
        return TreeStumpRecipe.class;
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
    public void setIngredients(@Nonnull TreeStumpRecipe treeStumpRecipe, @Nonnull IIngredients ingredients) {
        ImmutableList.Builder<List<ItemStack>> inputBuilder = ImmutableList.builder();
        ImmutableList.Builder<ItemStack> outputBuilder = ImmutableList.builder();

        inputBuilder.add(Arrays.asList(treeStumpRecipe.getIngredients().get(0).getItems()));
        inputBuilder.add(Arrays.asList(treeStumpRecipe.getTools().get(0).getItems()));
        outputBuilder.add(treeStumpRecipe.getResultItem());

        ingredients.setInputLists(VanillaTypes.ITEM, inputBuilder.build());
        ingredients.setOutputLists(VanillaTypes.ITEM, ImmutableList.of(outputBuilder.build()));
    }

    @Override
    public void setRecipe(@Nonnull IRecipeLayout recipeLayout, @Nonnull TreeStumpRecipe treeStumpRecipe, @Nonnull IIngredients ingredients) {
        recipeLayout.getItemStacks().init(0, true, 21, 21);
        recipeLayout.getItemStacks().set(0, ingredients.getInputs(VanillaTypes.ITEM).get(0));

        recipeLayout.getItemStacks().init(1, false, 84, 22);
        recipeLayout.getItemStacks().set(1, ingredients.getOutputs(VanillaTypes.ITEM).get(0));

        recipeLayout.getItemStacks().init(2, false, 51, 13);
        recipeLayout.getItemStacks().set(2, ingredients.getInputs(VanillaTypes.ITEM).get(1));

        recipeLayout.getItemStacks().init(3, false, 51, 29);
        recipeLayout.getItemStacks().set(3, Collections.singletonList(new ItemStack(BlockSubscriber.tree_stump)));
    }*/
}
