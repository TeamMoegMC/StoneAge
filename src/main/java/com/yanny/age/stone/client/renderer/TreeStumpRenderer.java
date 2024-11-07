package com.yanny.age.stone.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.yanny.age.stone.blocks.TreeStumpTileEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nonnull;

import static net.minecraft.client.renderer.model.ItemCameraTransforms.TransformType;

@OnlyIn(Dist.CLIENT)
public class TreeStumpRenderer implements BlockEntityRenderer<TreeStumpTileEntity> {
    /*public TreeStumpRenderer(@Nonnull BlockEntityRenderDispatcher rendererDispatcher) {
        super(rendererDispatcher);
    }*/

    @Override
    public void render(@Nonnull TreeStumpTileEntity tileEntity, float partialTicks, @Nonnull PoseStack matrixStack,
                       @Nonnull MultiBufferSource renderTypeBuffer, int overlayUV, int lightmapUV) {
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
