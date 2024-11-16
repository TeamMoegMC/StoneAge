package com.yanny.age.stone.client.renderer;

import com.yanny.age.stone.Reference;
import com.yanny.age.stone.client.models.CoelacanthModel;
import com.yanny.age.stone.client.models.DeerModel;
import com.yanny.age.stone.entities.DeerEntity;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nonnull;

@OnlyIn(Dist.CLIENT)
public class DeerRenderer extends MobRenderer<DeerEntity, DeerModel> {
    private static final ResourceLocation DEER_TEXTURE = new ResourceLocation(Reference.MODID, "textures/entity/deer.png");

    public DeerRenderer(@Nonnull EntityRendererProvider.Context context) {
        super(context, new DeerModel(context.bakeLayer(DeerModel.DEER_LAYER)), 0.5f);
    }

    @Nonnull
    @Override
    public ResourceLocation getTextureLocation(@Nonnull DeerEntity entity) {
        return DEER_TEXTURE;
    }

    @Override
    protected boolean shouldShowName(DeerEntity entity) {
        return entity.hasCustomName();
    }

    /*public static class RenderFactory implements IRenderFactory<DeerEntity> {

        @Override
        public EntityRenderer<? super DeerEntity> createRenderFor(EntityRenderDispatcher manager) {
            return new DeerRenderer(manager);
        }
    }*/
}
