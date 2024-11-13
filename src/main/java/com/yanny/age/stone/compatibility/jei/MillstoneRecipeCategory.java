package com.yanny.age.stone.compatibility.jei;

import com.yanny.age.stone.Reference;
import com.yanny.age.stone.recipes.DryingRackRecipe;
import com.yanny.age.stone.recipes.MillstoneRecipe;
import com.yanny.age.stone.subscribers.BlockSubscriber;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.drawable.IDrawableStatic;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

import javax.annotation.Nonnull;
import java.util.Locale;

public class MillstoneRecipeCategory implements IRecipeCategory<MillstoneRecipe> {
    static final ResourceLocation UID = new ResourceLocation(Reference.MODID, "millstone_recipe_category");

    private final String localizedName;
    private final IDrawableStatic background;
    private final IDrawable icon;
    public static final RecipeType<MillstoneRecipe> MILLSTONE_RECIPE_TYPE = new RecipeType<>(UID,MillstoneRecipe.class);

    MillstoneRecipeCategory(@Nonnull IGuiHelper guiHelper) {
        ResourceLocation location = new ResourceLocation(Reference.MODID, "textures/gui/jei/gui_layouts.png");
        background = guiHelper.createDrawable(location, 0, 122, 120, 60);
        localizedName = I18n.get("block.stone_age.millstone");
        icon = guiHelper.createDrawableItemStack(new ItemStack(BlockSubscriber.millstone));
    }

/*    @Nonnull
    @Override
    public ResourceLocation getUid() {
        return UID;
    }

    @Nonnull
    @Override
    public Class<? extends MillstoneRecipe> getRecipeClass() {
        return MillstoneRecipe.class;
    }*/
@Override
public RecipeType<MillstoneRecipe> getRecipeType() {
    return MILLSTONE_RECIPE_TYPE;
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
//    @Override
//    public void setIngredients(@Nonnull MillstoneRecipe millstoneRecipe, @Nonnull IIngredients ingredients) {
//        ImmutableList.Builder<List<ItemStack>> inputBuilder = ImmutableList.builder();
//        ImmutableList.Builder<List<ItemStack>> outputBuilder = ImmutableList.builder();
//
//        inputBuilder.add(Arrays.asList(millstoneRecipe.getIngredients().get(0).getItems()));
//        outputBuilder.add(Collections.singletonList(millstoneRecipe.getResultItem()));
//
//        if (!millstoneRecipe.getRecipeSecondOutput().isEmpty()) {
//            outputBuilder.add(Collections.singletonList(millstoneRecipe.getRecipeSecondOutput()));
//        }
//
//        ingredients.setInputLists(VanillaTypes.ITEM, inputBuilder.build());
//        ingredients.setOutputLists(VanillaTypes.ITEM, outputBuilder.build());
//    }

    @Override
    public void setRecipe(@Nonnull IRecipeLayoutBuilder builder, @Nonnull MillstoneRecipe millstoneRecipe, @Nonnull IFocusGroup focuses) {
//        recipeLayout.getItemStacks().init(0, true, 25, 21);
//        recipeLayout.getItemStacks().set(0, ingredients.getInputs(VanillaTypes.ITEM).get(0));
        builder.addSlot(RecipeIngredientRole.INPUT, 26, 22).addIngredients(millstoneRecipe.getIngredients().get(0));

//        recipeLayout.getItemStacks().init(1, false, 77, 10);
//        recipeLayout.getItemStacks().set(1, ingredients.getOutputs(VanillaTypes.ITEM).get(0));
        builder.addSlot(RecipeIngredientRole.OUTPUT, 78, 11).addItemStack(millstoneRecipe.getResultItem(null));

        if (!millstoneRecipe.getRecipeSecondOutput().isEmpty()) {
//            recipeLayout.getItemStacks().init(2, false, 77, 32);
//            recipeLayout.getItemStacks().set(2, ingredients.getOutputs(VanillaTypes.ITEM).get(1));
            builder.addSlot(RecipeIngredientRole.OUTPUT, 78, 33).addItemStack(millstoneRecipe.getCraftingSecondResult());
        }
    }

    @Override
    public void draw(MillstoneRecipe recipe, @Nonnull IRecipeSlotsView recipeSlotsView, GuiGraphics guiGraphics, double mouseX, double mouseY) {
        if (!recipe.getRecipeSecondOutput().isEmpty()) {
//            Minecraft.getInstance().font.draw(guiGraphics, String.format(Locale.ENGLISH, "%.0f%%", recipe.getSecondChance() * 100), 96, 38, -1);
        }
    }
}
