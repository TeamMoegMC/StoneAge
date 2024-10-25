package com.yanny.age.stone.client.renderer;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.yanny.age.stone.blocks.TreeStumpTileEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nonnull;

import static net.minecraft.client.renderer.model.ItemCameraTransforms.TransformType;

import net.minecraft.client.renderer.model.ItemCameraTransforms.TransformType;

@OnlyIn(Dist.CLIENT)
public class TreeStumpRenderer extends TileEntityRenderer<TreeStumpTileEntity> {
    public TreeStumpRenderer(@Nonnull TileEntityRendererDispatcher rendererDispatcher) {
        super(rendererDispatcher);
    }

    @Override
    public void render(@Nonnull TreeStumpTileEntity tileEntity, float partialTicks, @Nonnull MatrixStack matrixStack,
                       @Nonnull IRenderTypeBuffer renderTypeBuffer, int overlayUV, int lightmapUV) {
        matrixStack.pushPose();
        matrixStack.translate(0.5, 0.77, 0.5);
        matrixStack.mulPose(Vector3f.XP.rotationDegrees(90));
        matrixStack.scale(0.7f, 0.7f, 0.7f);

        Minecraft.getInstance().getItemRenderer().renderStatic(tileEntity.getInventory().getItem(0), TransformType.FIXED,
                overlayUV, lightmapUV, matrixStack, renderTypeBuffer);
        if (tileEntity.getInventory().getItem(0).getCount() == 2) {
            matrixStack.translate(0, 0, -0.05);
            matrixStack.mulPose(Vector3f.ZP.rotationDegrees(30));
            Minecraft.getInstance().getItemRenderer().renderStatic(tileEntity.getInventory().getItem(0), TransformType.FIXED,
                    overlayUV, lightmapUV, matrixStack, renderTypeBuffer);
        }
        matrixStack.popPose();
    }
}
