package com.yanny.age.stone.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import com.yanny.age.stone.blocks.TreeStumpTileEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nonnull;


@OnlyIn(Dist.CLIENT)
public class TreeStumpRenderer implements BlockEntityRenderer<TreeStumpTileEntity> {
    public TreeStumpRenderer(@Nonnull BlockEntityRendererProvider.Context context) {
    }

    @Override
    public void render(@Nonnull TreeStumpTileEntity tileEntity, float partialTicks, @Nonnull PoseStack matrixStack,
                       @Nonnull MultiBufferSource renderTypeBuffer, int overlayUV, int lightmapUV) {
        int p = (int)tileEntity.getBlockPos().asLong();

        matrixStack.pushPose();
        matrixStack.translate(0.5, 0.77, 0.5);
        matrixStack.mulPose(Axis.XP.rotationDegrees(90));
        matrixStack.scale(0.7f, 0.7f, 0.7f);

        Minecraft.getInstance().getItemRenderer().renderStatic(tileEntity.getInventory().getItem(0), ItemDisplayContext.FIXED,
                overlayUV, lightmapUV, matrixStack, renderTypeBuffer,tileEntity.getLevel(),p);
        if (tileEntity.getInventory().getItem(0).getCount() == 2) {
            matrixStack.translate(0, 0, -0.05);
            matrixStack.mulPose(Axis.ZP.rotationDegrees(30));
            Minecraft.getInstance().getItemRenderer().renderStatic(tileEntity.getInventory().getItem(0), ItemDisplayContext.FIXED,
                    overlayUV, lightmapUV, matrixStack, renderTypeBuffer,tileEntity.getLevel(),p);
        }
        matrixStack.popPose();
    }
}
