package com.yanny.age.stone.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.yanny.age.stone.Reference;
import com.yanny.age.stone.client.models.MammothModel;
import com.yanny.age.stone.entities.MammothEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nonnull;

@OnlyIn(Dist.CLIENT)
public class MammothRenderer extends MobRenderer<MammothEntity, MammothModel> {
    private static final ResourceLocation MAMMOTH_TEXTURE = new ResourceLocation(Reference.MODID, "textures/entity/mammoth.png");

    public MammothRenderer(@Nonnull EntityRendererProvider.Context context) {
        super(context, new MammothModel(context.bakeLayer(MammothModel.MAMMOTH_LAYER)), 1.0f);
    }

    @Override
    public void render(@Nonnull MammothEntity entityIn, float entityYaw, float partialTicks, PoseStack matrixStackIn, @Nonnull MultiBufferSource bufferIn, int packedLightIn) {
        matrixStackIn.scale(1.5f, 1.5f, 1.5f);
        super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
    }

    @Nonnull
    @Override
    public ResourceLocation getTextureLocation(@Nonnull MammothEntity entity) {
        return MAMMOTH_TEXTURE;
    }

    @Override
    protected boolean shouldShowName(MammothEntity entity) {
        return entity.hasCustomName();
    }

    /*public static class RenderFactory implements IRenderFactory<MammothEntity> {

        @Override
        public EntityRenderer<? super MammothEntity> createRenderFor(EntityRenderDispatcher manager) {
            return new MammothRenderer(manager);
        }
    }*/
}
