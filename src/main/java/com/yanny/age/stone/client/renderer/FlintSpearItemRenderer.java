package com.yanny.age.stone.client.renderer;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.yanny.age.stone.client.models.FlintSpearItemModel;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.tileentity.ItemStackTileEntityRenderer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nonnull;

@OnlyIn(Dist.CLIENT)
public class FlintSpearItemRenderer extends ItemStackTileEntityRenderer {
    private final FlintSpearItemModel model = new FlintSpearItemModel();

    @Override
    public void render(ItemStack itemStack, @Nonnull MatrixStack matrixStack, @Nonnull IRenderTypeBuffer renderTypeBuffer, int overlayUV, int lightmapUV) {
        matrixStack.push();
        matrixStack.translate(0.5, -1, 0.5);
        model.render(matrixStack, renderTypeBuffer.getBuffer(RenderType.entityCutoutNoCull(FlintSpearRenderer.TEXTURE)), overlayUV, lightmapUV, 1f, 1f, 1f, 1f);
        matrixStack.pop();
    }
}
