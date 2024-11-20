package com.yanny.age.stone.compatibility.jei;

import com.yanny.age.stone.Reference;
import com.yanny.age.stone.recipes.*;
import com.yanny.age.stone.subscribers.BlockSubscriber;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeManager;

import javax.annotation.Nonnull;

@JeiPlugin
public class JeiCompatPlugin implements IModPlugin {

    @Nonnull
    @Override
    public ResourceLocation getPluginUid() {
        return new ResourceLocation(Reference.MODID, "main");
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        registration.addRecipeCategories(
                new DryingRackRecipeCategory(registration.getJeiHelpers().getGuiHelper()),
                new TanningRackRecipeCategory(registration.getJeiHelpers().getGuiHelper()),
                new FlintWorkbenchRecipeCategory(registration.getJeiHelpers().getGuiHelper()),
                new TreeStumpRecipeCategory(registration.getJeiHelpers().getGuiHelper()),
                new MillstoneRecipeCategory(registration.getJeiHelpers().getGuiHelper())
        );
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        assert Minecraft.getInstance().level != null;
        RecipeManager recipeManager = Minecraft.getInstance().level.getRecipeManager();
//        List<DryingRackRecipe> dryingRackRecipes = recipeManager.getAllRecipesFor(DryingRackRecipe.drying_rack);
//        List<Recipe<?>> tanningRackRecipes = recipeManager.getRecipes().stream().filter(recipe -> recipe instanceof TanningRackRecipe).collect(Collectors.toList());
//        List<Recipe<?>> flintWorkbenchRecipes = recipeManager.getRecipes().stream().filter(recipe -> recipe instanceof FlintWorkbenchRecipe).collect(Collectors.toList());
//        List<Recipe<?>> treeStumpRecipes = recipeManager.getRecipes().stream().filter(recipe -> recipe instanceof TreeStumpRecipe).collect(Collectors.toList());
//        List<Recipe<?>> millstoneRecipes = recipeManager.getRecipes().stream().filter(recipe -> recipe instanceof MillstoneRecipe).collect(Collectors.toList());

        registration.addRecipes(DryingRackRecipeCategory.DRYINGRACK_RECIPE_TYPE, recipeManager.getAllRecipesFor(DryingRackRecipe.drying_rack));
        registration.addRecipes(TanningRackRecipeCategory.TANNINGRACK_RECIPE_TYPE, recipeManager.getAllRecipesFor(TanningRackRecipe.tanning_rack));
        registration.addRecipes(FlintWorkbenchRecipeCategory.FLINETWORKBENCH_RECIPE_TYPE, recipeManager.getAllRecipesFor(FlintWorkbenchRecipe.flint_workbench));
        registration.addRecipes(TreeStumpRecipeCategory.TREESTUMP_RECIPE_TYPE, recipeManager.getAllRecipesFor(TreeStumpRecipe.tree_stump));
        registration.addRecipes(MillstoneRecipeCategory.MILLSTONE_RECIPE_TYPE, recipeManager.getAllRecipesFor(MillstoneRecipe.millstone));
    }

    @Override
    public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
        registration.addRecipeCatalyst(new ItemStack(BlockSubscriber.drying_rack), DryingRackRecipeCategory.DRYINGRACK_RECIPE_TYPE);
        registration.addRecipeCatalyst(new ItemStack(BlockSubscriber.tanning_rack), TanningRackRecipeCategory.TANNINGRACK_RECIPE_TYPE);
        registration.addRecipeCatalyst(new ItemStack(BlockSubscriber.flint_workbench), FlintWorkbenchRecipeCategory.FLINETWORKBENCH_RECIPE_TYPE);
        registration.addRecipeCatalyst(new ItemStack(BlockSubscriber.tree_stump), TreeStumpRecipeCategory.TREESTUMP_RECIPE_TYPE);
        registration.addRecipeCatalyst(new ItemStack(BlockSubscriber.millstone), MillstoneRecipeCategory.MILLSTONE_RECIPE_TYPE);
    }
}
