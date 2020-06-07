package com.yanny.age.stone.compatibility.crt;

import com.blamejared.crafttweaker.api.CraftTweakerAPI;
import com.blamejared.crafttweaker.api.annotations.ZenRegister;
import com.blamejared.crafttweaker.api.item.IIngredient;
import com.blamejared.crafttweaker.api.item.IItemStack;
import com.blamejared.crafttweaker.api.managers.IRecipeManager;
import com.blamejared.crafttweaker.impl.actions.recipes.ActionAddRecipe;
import com.yanny.age.stone.recipes.FlintWorkbenchRecipe;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import org.apache.commons.lang3.tuple.Triple;
import org.openzen.zencode.java.ZenCodeType;

import static org.openzen.zencode.java.ZenCodeType.*;

@ZenRegister
@Name("mods.stone_age.FlintWorkbenchManager")
public class FlintWorkbenchManager implements IRecipeManager {
    @SuppressWarnings("unused")
    @ZenCodeType.Field
    public static final FlintWorkbenchManager INSTANCE = new FlintWorkbenchManager();

    @SuppressWarnings("rawtypes")
    @Override
    public IRecipeType getRecipeType() {
        return FlintWorkbenchRecipe.flint_workbench;
    }

    @Method
    public void addRecipe(String name, IItemStack output, IIngredient[][] inputMatrix, IIngredient tool, @OptionalString String group) {
        Triple<NonNullList<Ingredient>, Integer, Integer> ingredient = CrtUtils.convert(inputMatrix);
        FlintWorkbenchRecipe recipe = new FlintWorkbenchRecipe(new ResourceLocation("crafttweaker", name), group, ingredient.getMiddle(),
                ingredient.getRight(), tool.asVanillaIngredient(), ingredient.getLeft(), output.getInternal());
        CraftTweakerAPI.apply(new ActionAddRecipe(this, recipe, ""));
    }
}
