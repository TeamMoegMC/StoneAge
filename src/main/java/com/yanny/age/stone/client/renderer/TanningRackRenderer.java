package com.yanny.age.stone.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import com.yanny.age.stone.blocks.TanningRackTileEntity;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
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
public class TanningRackRenderer implements BlockEntityRenderer<TanningRackTileEntity> {
    public TanningRackRenderer(@Nonnull BlockEntityRendererProvider.Context context) {
    }

    @Override
    public void render(@Nonnull TanningRackTileEntity tileEntity, float partialTicks, @Nonnull PoseStack matrixStack,
                       @Nonnull MultiBufferSource renderTypeBuffer, int overlayUV, int lightmapUV) {
        Direction direction = tileEntity.getBlockState().getValue(HorizontalDirectionalBlock.FACING);
        int p = (int)tileEntity.getBlockPos().asLong();

        for (int i = 0; i < TanningRackTileEntity.ITEMS; i++) {
            boolean n = direction == Direction.NORTH;
            boolean s = direction == Direction.SOUTH;
            boolean e = direction == Direction.EAST;
            boolean w = direction == Direction.WEST;
            float xOffset = (w || e) ? (w ? 0.3f : 0.7f) : 0.5f;
            float zOffset = (n || s) ? (n ? 0.3f : 0.7f) : 0.5f;

            matrixStack.pushPose();
            matrixStack.translate(xOffset, 0.5f, zOffset);
            matrixStack.mulPose(Axis.YP.rotationDegrees((e || w) ? 90 : 0));
            matrixStack.mulPose(Axis.XP.rotationDegrees((n || w) ? 22.5f : -22.5f));
            matrixStack.scale(0.9f, 0.9f, 0.9f);

            Minecraft.getInstance().getItemRenderer().renderStatic(tileEntity.getInventory().getItem(i), ItemDisplayContext.FIXED,
                    overlayUV, lightmapUV, matrixStack, renderTypeBuffer,tileEntity.getLevel(),p);
            Minecraft.getInstance().getItemRenderer().renderStatic(tileEntity.getInventory().getItem(i + TanningRackTileEntity.ITEMS), ItemDisplayContext.FIXED,
                    overlayUV, lightmapUV, matrixStack, renderTypeBuffer,tileEntity.getLevel(),p);

            matrixStack.popPose();
        }
    }
}
