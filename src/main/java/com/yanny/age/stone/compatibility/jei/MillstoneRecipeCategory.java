package com.yanny.age.stone.compatibility.jei;

public class MillstoneRecipeCategory /*implements IRecipeCategory<MillstoneRecipe>*/ {
    /*static final ResourceLocation UID = new ResourceLocation(Reference.MODID, "millstone_recipe_category");

    private final String localizedName;
    private final IDrawableStatic background;
    private final IDrawable icon;

    MillstoneRecipeCategory(@Nonnull IGuiHelper guiHelper) {
        ResourceLocation location = new ResourceLocation(Reference.MODID, "textures/gui/jei/gui_layouts.png");
        background = guiHelper.createDrawable(location, 0, 122, 120, 60);
        localizedName = I18n.get("block.stone_age.millstone");
        icon = guiHelper.createDrawableIngredient(new ItemStack(BlockSubscriber.millstone));
    }

    @Nonnull
    @Override
    public ResourceLocation getUid() {
        return UID;
    }

    @Nonnull
    @Override
    public Class<? extends MillstoneRecipe> getRecipeClass() {
        return MillstoneRecipe.class;
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
    public void setIngredients(@Nonnull MillstoneRecipe millstoneRecipe, @Nonnull IIngredients ingredients) {
        ImmutableList.Builder<List<ItemStack>> inputBuilder = ImmutableList.builder();
        ImmutableList.Builder<List<ItemStack>> outputBuilder = ImmutableList.builder();

        inputBuilder.add(Arrays.asList(millstoneRecipe.getIngredients().get(0).getItems()));
        outputBuilder.add(Collections.singletonList(millstoneRecipe.getResultItem()));

        if (!millstoneRecipe.getRecipeSecondOutput().isEmpty()) {
            outputBuilder.add(Collections.singletonList(millstoneRecipe.getRecipeSecondOutput()));
        }

        ingredients.setInputLists(VanillaTypes.ITEM, inputBuilder.build());
        ingredients.setOutputLists(VanillaTypes.ITEM, outputBuilder.build());
    }

    @Override
    public void setRecipe(@Nonnull IRecipeLayout recipeLayout, @Nonnull MillstoneRecipe millstoneRecipe, @Nonnull IIngredients ingredients) {
        recipeLayout.getItemStacks().init(0, true, 25, 21);
        recipeLayout.getItemStacks().set(0, ingredients.getInputs(VanillaTypes.ITEM).get(0));

        recipeLayout.getItemStacks().init(1, false, 77, 10);
        recipeLayout.getItemStacks().set(1, ingredients.getOutputs(VanillaTypes.ITEM).get(0));

        if (!millstoneRecipe.getRecipeSecondOutput().isEmpty()) {
            recipeLayout.getItemStacks().init(2, false, 77, 32);
            recipeLayout.getItemStacks().set(2, ingredients.getOutputs(VanillaTypes.ITEM).get(1));
        }
    }

    @Override
    public void draw(MillstoneRecipe recipe, @Nonnull PoseStack matrixStack, double mouseX, double mouseY) {
        if (!recipe.getRecipeSecondOutput().isEmpty()) {
            Minecraft.getInstance().font.draw(matrixStack, String.format(Locale.ENGLISH, "%.0f%%", recipe.getSecondChance() * 100), 96, 38, -1);
        }
    }*/
}
