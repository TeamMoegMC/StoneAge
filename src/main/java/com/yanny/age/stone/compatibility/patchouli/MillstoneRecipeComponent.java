package com.yanny.age.stone.compatibility.patchouli;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import com.yanny.age.stone.recipes.MillstoneRecipe;
import com.yanny.age.stone.subscribers.BlockSubscriber;
import net.minecraft.client.gui.AbstractGui;
import net.minecraft.util.IReorderingProcessor;
import net.minecraft.util.ResourceLocation;
import vazkii.patchouli.api.IComponentRenderContext;
import vazkii.patchouli.api.IVariable;

import javax.annotation.Nonnull;
import java.util.function.UnaryOperator;

@SuppressWarnings("unused")
public class MillstoneRecipeComponent extends BaseComponent {
    private transient MillstoneRecipe recipe = null;
    private transient IReorderingProcessor title;

    @Override
    public void build(int componentX, int componentY, int pageNum) {
        x = componentX;
        y = componentY;
    }

    @SuppressWarnings("ConstantConditions")
    @Override
    public void render(@Nonnull MatrixStack matrixStack, @Nonnull IComponentRenderContext context, float partialTicks, int mouseX, int mouseY) {
        if (recipe == null) {
            return;
        }

        mc.textureManager.bindTexture(TEXTURE);
        RenderSystem.enableBlend();
        AbstractGui.blit(matrixStack, x, y, 11, 71, 96, 24, 128, 128);
        drawCenteredStringNoShadow(matrixStack, title, PAGE_WIDTH / 2, y - 10, context.getHeaderColor(), mc.fontRenderer);

        context.renderIngredient(matrixStack, x + 4, y + 4, mouseX, mouseY, recipe.getIngredients().get(0));
        context.renderItemStack(matrixStack, x + 40, y + 4, mouseX, mouseY, BlockSubscriber.millstone.asItem().getDefaultInstance());
        context.renderItemStack(matrixStack, x + 76, y + 4, mouseX, mouseY, recipe.getRecipeOutput());
    }

    @SuppressWarnings("ConstantConditions")
    @Override
    public void onVariablesAvailable(@Nonnull UnaryOperator<IVariable> unaryOperator) {
        String recipeName = unaryOperator.apply(IVariable.wrap("#recipe#")).asString();

        mc.world.getRecipeManager().getRecipe(new ResourceLocation(recipeName)).ifPresent((recipe) -> {
            this.recipe = (MillstoneRecipe) recipe;
            this.title = recipe.getRecipeOutput().getDisplayName().func_241878_f();
        });
    }
}
