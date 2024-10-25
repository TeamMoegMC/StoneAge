package com.yanny.age.stone.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.yanny.age.stone.blocks.TanningRackTileEntity;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderDispatcher;
import net.minecraft.core.Direction;
import com.mojang.math.Vector3f;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nonnull;

import static net.minecraft.client.renderer.model.ItemCameraTransforms.TransformType;

import net.net.minecraft.client.renderer.block.model.ItemTransformsnsformType;

import net.minecraft.client.renderer.block.model.ItemTransforms.TransformType;

@OnlyIn(Dist.CLIENT)
public class TanningRackRenderer extends BlockEntityRenderer<TanningRackTileEntity> {
    public TanningRackRenderer(@Nonnull BlockEntityRenderDispatcher rendererDispatcher) {
        super(rendererDispatcher);
    }

    @Override
    public void render(@Nonnull TanningRackTileEntity tileEntity, float partialTicks, @Nonnull PoseStack matrixStack,
                       @Nonnull MultiBufferSource renderTypeBuffer, int overlayUV, int lightmapUV) {
        Direction direction = tileEntity.getBlockState().getValue(HorizontalDirectionalBlock.FACING);

        for (int i = 0; i < TanningRackTileEntity.ITEMS; i++) {
            boolean n = direction == Direction.NORTH;
            boolean s = direction == Direction.SOUTH;
            boolean e = direction == Direction.EAST;
            boolean w = direction == Direction.WEST;
            float xOffset = (w || e) ? (w ? 0.3f : 0.7f) : 0.5f;
            float zOffset = (n || s) ? (n ? 0.3f : 0.7f) : 0.5f;

            matrixStack.pushPose();
            matrixStack.translate(xOffset, 0.5f, zOffset);
            matrixStack.mulPose(Vector3f.YP.rotationDegrees((e || w) ? 90 : 0));
            matrixStack.mulPose(Vector3f.XP.rotationDegrees((n || w) ? 22.5f : -22.5f));
            matrixStack.scale(0.9f, 0.9f, 0.9f);

            Minecraft.getInstance().getItemRenderer().renderStatic(tileEntity.getInventory().getItem(i), TransformType.FIXED,
                    overlayUV, lightmapUV, matrixStack, renderTypeBuffer);
            Minecraft.getInstance().getItemRenderer().renderStatic(tileEntity.getInventory().getItem(i + TanningRackTileEntity.ITEMS), TransformType.FIXED,
                    overlayUV, lightmapUV, matrixStack, renderTypeBuffer);

            matrixStack.popPose();
        }
    }
}
