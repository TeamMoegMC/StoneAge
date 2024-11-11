package com.yanny.age.stone.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.yanny.age.stone.Reference;
import com.yanny.age.stone.blocks.MillstoneTileEntity;
import com.yanny.age.stone.client.models.MillstoneModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderDispatcher;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nonnull;

@OnlyIn(Dist.CLIENT)
public class MillstoneRenderer implements BlockEntityRenderer<MillstoneTileEntity> {
    private static final ResourceLocation TEXTURE_NORMAL = new ResourceLocation(Reference.MODID, "textures/entity/millstone.png");
    private final MillstoneModel model;
    public MillstoneRenderer(@Nonnull BlockEntityRendererProvider.Context context) {
        model = new MillstoneModel(context.bakeLayer(MillstoneModel.MILLSTONE_LAYER));
    }

    @Override
    public void render(@Nonnull MillstoneTileEntity tileEntity, float partialTicks, @Nonnull PoseStack matrixStack,
                       @Nonnull MultiBufferSource renderTypeBuffer, int overlayUV, int lightmapUV) {
        matrixStack.pushPose();
        matrixStack.translate(0.5, 0.05 * (1 / 16.0), 0.5);
        model.rotate(tileEntity.rotateAngle());
        model.renderToBuffer(matrixStack, renderTypeBuffer.getBuffer(RenderType.entityTranslucentCull(TEXTURE_NORMAL)), overlayUV, lightmapUV, 1f, 1f, 1f, 1f);
        matrixStack.popPose();
    }
}
