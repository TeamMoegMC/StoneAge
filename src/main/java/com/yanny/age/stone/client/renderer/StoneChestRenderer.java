package com.yanny.age.stone.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import com.yanny.age.stone.Reference;
import com.yanny.age.stone.blocks.StoneChestBlock;
import com.yanny.age.stone.blocks.StoneChestTileEntity;
import com.yanny.age.stone.client.models.StoneChestModel;
import com.yanny.age.stone.subscribers.BlockSubscriber;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.world.level.block.entity.LidBlockEntity;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nonnull;

@OnlyIn(Dist.CLIENT)
public class StoneChestRenderer implements BlockEntityRenderer<StoneChestTileEntity> {
    private static final ResourceLocation TEXTURE_NORMAL = new ResourceLocation(Reference.MODID, "textures/entity/stone_chest.png");
    private final StoneChestModel model;
    public StoneChestRenderer(@Nonnull BlockEntityRendererProvider.Context context) {

        model = new StoneChestModel(context.bakeLayer(StoneChestModel.STONECHEST_LAYER));
    }

    @Override
    public void render(@Nonnull StoneChestTileEntity tileEntity, float partialTicks, @Nonnull PoseStack matrixStack,
                       @Nonnull MultiBufferSource renderTypeBuffer, int overlayUV, int lightmapUV) {
        //noinspection ConstantConditions
        BlockState blockstate = tileEntity.hasLevel() ? tileEntity.getBlockState() :
                BlockSubscriber.stone_chest.defaultBlockState().setValue(StoneChestBlock.FACING, Direction.SOUTH);

        matrixStack.pushPose();
        matrixStack.translate(0, 1, 1);
        matrixStack.scale(1, -1, -1);

        float f = blockstate.getValue(StoneChestBlock.FACING).toYRot();

        if ((double)Math.abs(f) > 1.0E-5D) {
            matrixStack.translate(0.5, 0.5, 0.5);
            matrixStack.mulPose(Axis.YP.rotationDegrees(f));
            matrixStack.translate(-0.5, -0.5, -0.5);
        }

        matrixStack.translate(0.5, -0.5, 0.5);

        this.applyLidRotation(tileEntity, partialTicks, model);
        model.renderToBuffer(matrixStack, renderTypeBuffer.getBuffer(RenderType.entityCutout(TEXTURE_NORMAL)), overlayUV, lightmapUV, 1f, 1f, 1f, 1f);

        matrixStack.popPose();
    }

    private void applyLidRotation(@Nonnull StoneChestTileEntity tileEntity, float angle, @Nonnull StoneChestModel model) {
        float f = ((LidBlockEntity)tileEntity).getOpenNess(angle);
        f = 1.0F - f;
        f = 1.0F - f * f * f;
        model.getLid().yRot = -(f * ((float)Math.PI / 2F));
    }
}
