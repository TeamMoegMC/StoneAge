package com.yanny.age.stone.client.renderer;

import com.yanny.age.stone.Reference;
import com.yanny.age.stone.client.models.BoarModel;
import com.yanny.age.stone.entities.BoarEntity;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nonnull;

@OnlyIn(Dist.CLIENT)
public class BoarRenderer extends MobRenderer<BoarEntity, BoarModel> {
    private static final ResourceLocation BOAR_TEXTURE = new ResourceLocation(Reference.MODID, "textures/entity/boar.png");

    private BoarRenderer(@Nonnull EntityRenderDispatcher rendererManager) {
        super(rendererManager, new BoarModel(), 0.5f);
    }

    @Nonnull
    @Override
    public ResourceLocation getTextureLocation(@Nonnull BoarEntity entity) {
        return BOAR_TEXTURE;
    }

    @Override
    protected boolean shouldShowName(BoarEntity entity) {
        return entity.hasCustomName();
    }

    public static class RenderFactory implements IRenderFactory<BoarEntity> {

        @Override
        public EntityRenderer<? super BoarEntity> createRenderFor(EntityRenderDispatcher manager) {
            return new BoarRenderer(manager);
        }
    }
}
