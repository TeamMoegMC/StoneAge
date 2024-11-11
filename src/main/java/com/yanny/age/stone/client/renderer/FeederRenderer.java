package com.yanny.age.stone.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import com.yanny.age.stone.blocks.FeederTileEntity;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.core.Direction;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nonnull;


@OnlyIn(Dist.CLIENT)
public class FeederRenderer implements BlockEntityRenderer<FeederTileEntity> {
    public FeederRenderer(@Nonnull BlockEntityRendererProvider.Context context) {
    }

    @Override
    public void render(@Nonnull FeederTileEntity tileEntityIn, float partialTicks, @Nonnull PoseStack matrixStack,
                       @Nonnull MultiBufferSource renderTypeBuffer, int overlayUV, int lightmapUV) {
        Direction direction = tileEntityIn.getBlockState().getValue(HorizontalDirectionalBlock.FACING);
        int p = (int)tileEntityIn.getBlockPos().asLong();

        for (int i = 0; i < FeederTileEntity.ITEMS; i++) {
            matrixStack.pushPose();

            if (direction == Direction.NORTH || direction == Direction.SOUTH) {
                matrixStack.translate(i * 0.1875f + 0.21875f, 0.3125f + i * 0.001f, 0.5f);
            } else {
                matrixStack.translate(0.5f, 0.3125f + i * 0.001f, i * 0.1875f + 0.21875f);
                matrixStack.mulPose(Axis.YP.rotationDegrees(90));
            }

            matrixStack.mulPose(Axis.XP.rotationDegrees(90));
            matrixStack.scale(0.3F, 0.3F, 0.3F);

            Minecraft.getInstance().getItemRenderer().renderStatic(tileEntityIn.getInventory().getItem(i), ItemDisplayContext.FIXED,
                    overlayUV, lightmapUV, matrixStack, renderTypeBuffer,tileEntityIn.getLevel(),p);

            matrixStack.popPose();
        }
    }
}
