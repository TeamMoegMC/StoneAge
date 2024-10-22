package com.yanny.age.stone.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.yanny.age.stone.api.utils.ItemStackUtils;
import com.yanny.age.stone.blocks.FlintWorkbenchTileEntity;
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

import static net.minecraft.client.renderer.block.model.ItemTransforms.*;

import net.minecraft.client.renderer.block.model.ItemTransforms.TransformType;

@OnlyIn(Dist.CLIENT)
public class FlintWorkbenchRenderer extends BlockEntityRenderer<FlintWorkbenchTileEntity> {
    public FlintWorkbenchRenderer(@Nonnull BlockEntityRenderDispatcher rendererDispatcher) {
        super(rendererDispatcher);
    }

    @Override
    public void render(@Nonnull FlintWorkbenchTileEntity tileEntity, float partialTicks, @Nonnull PoseStack matrixStack,
                       @Nonnull MultiBufferSource renderTypeBuffer, int overlayUV, int lightmapUV) {
        Direction direction = tileEntity.getBlockState().getValue(HorizontalDirectionalBlock.FACING);
        float border = 0.0625f;
        float part = (1 - 4 * border) / 3f;
        float t = border + part / 2f;
        float off = part + border;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                int x = 0;
                int y = 0;

                // handle rotation
                switch (direction) {
                    case NORTH:
                        x = 2 - j;
                        y = 2 - i;
                        break;
                    case SOUTH:
                        x = j;
                        y = i;
                        break;
                    case WEST:
                        x = 2 - i;
                        y = j;
                        break;
                    case EAST:
                        x = i;
                        y = 2 - j;
                        break;
                }

                matrixStack.pushPose();
                matrixStack.translate(off * x + t, 0.125f, off * y + t);

                switch (direction) {
                    case SOUTH:
                        matrixStack.mulPose(Vector3f.YP.rotationDegrees(180));
                        break;
                    case WEST:
                        matrixStack.mulPose(Vector3f.YP.rotationDegrees(90));
                        break;
                    case EAST:
                        matrixStack.mulPose(Vector3f.YP.rotationDegrees(270));
                        break;
                }

                matrixStack.mulPose(Vector3f.XP.rotationDegrees(90));
                matrixStack.scale(0.25F, 0.25F, 0.25F);

                Minecraft.getInstance().getItemRenderer().renderStatic(tileEntity.getInventory().getItem(i * 3 + j), TransformType.FIXED,
                        overlayUV, lightmapUV, matrixStack, renderTypeBuffer);

                matrixStack.popPose();
            }
        }

        if (!tileEntity.getRecipeOutput().isEmpty()) {
            matrixStack.pushPose();

            switch (direction) {
                case NORTH:
                    matrixStack.translate(0.5f, 0.4f, 0.95f);
                    break;
                case SOUTH:
                    matrixStack.translate(0.5f, 0.4f, 0.05f);
                    matrixStack.mulPose(Vector3f.YP.rotationDegrees(180));
                    break;
                case WEST:
                    matrixStack.translate(0.95f, 0.4f, 0.5f);
                    matrixStack.mulPose(Vector3f.YP.rotationDegrees(90));
                    break;
                case EAST:
                    matrixStack.translate(0.05f, 0.4f, 0.5f);
                    matrixStack.mulPose(Vector3f.YP.rotationDegrees(270));
                    break;
            }

            matrixStack.scale(0.5F, 0.5F, 0.5F);
            ItemStackUtils.renderItem(tileEntity.getRecipeOutput(), TransformType.FIXED, overlayUV, lightmapUV, matrixStack, renderTypeBuffer, 0.6f);

            matrixStack.popPose();
        }
    }
}
