package com.yanny.age.stone.client.renderer;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.yanny.age.stone.blocks.FeederTileEntity;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.util.Direction;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nonnull;

import static net.minecraft.client.renderer.model.ItemCameraTransforms.TransformType;

import net.minecraft.client.renderer.model.ItemCameraTransforms.TransformType;

@OnlyIn(Dist.CLIENT)
public class FeederRenderer extends TileEntityRenderer<FeederTileEntity> {
    public FeederRenderer(@Nonnull TileEntityRendererDispatcher rendererDispatcher) {
        super(rendererDispatcher);
    }

    @Override
    public void render(@Nonnull FeederTileEntity tileEntityIn, float partialTicks, @Nonnull MatrixStack matrixStack,
                       @Nonnull IRenderTypeBuffer renderTypeBuffer, int overlayUV, int lightmapUV) {
        Direction direction = tileEntityIn.getBlockState().getValue(HorizontalBlock.FACING);

        for (int i = 0; i < FeederTileEntity.ITEMS; i++) {
            matrixStack.pushPose();

            if (direction == Direction.NORTH || direction == Direction.SOUTH) {
                matrixStack.translate(i * 0.1875f + 0.21875f, 0.3125f + i * 0.001f, 0.5f);
            } else {
                matrixStack.translate(0.5f, 0.3125f + i * 0.001f, i * 0.1875f + 0.21875f);
                matrixStack.mulPose(Vector3f.YP.rotationDegrees(90));
            }

            matrixStack.mulPose(Vector3f.XP.rotationDegrees(90));
            matrixStack.scale(0.3F, 0.3F, 0.3F);

            Minecraft.getInstance().getItemRenderer().renderStatic(tileEntityIn.getInventory().getItem(i), TransformType.FIXED,
                    overlayUV, lightmapUV, matrixStack, renderTypeBuffer);

            matrixStack.popPose();
        }
    }
}
