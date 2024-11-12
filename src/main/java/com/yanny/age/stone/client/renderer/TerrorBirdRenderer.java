package com.yanny.age.stone.client.renderer;

import com.yanny.age.stone.Reference;
import com.yanny.age.stone.client.models.TerrorBirdModel;
import com.yanny.age.stone.entities.TerrorBirdEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nonnull;

@OnlyIn(Dist.CLIENT)
public class TerrorBirdRenderer extends MobRenderer<TerrorBirdEntity, TerrorBirdModel> {
    private static final ResourceLocation TERROR_BIRD_TEXTURE = new ResourceLocation(Reference.MODID, "textures/entity/terror_bird.png");

    public TerrorBirdRenderer(@Nonnull EntityRendererProvider.Context context) {
        super(context, new TerrorBirdModel(), 0.3f);
    }

    @Nonnull
    @Override
    public ResourceLocation getTextureLocation(@Nonnull TerrorBirdEntity entity) {
        return TERROR_BIRD_TEXTURE;
    }

    @Override
    protected float getBob(TerrorBirdEntity livingBase, float partialTicks) {
        float f = Mth.lerp(partialTicks, livingBase.oFlap, livingBase.wingRotation);
        float f1 = Mth.lerp(partialTicks, livingBase.oFlapSpeed, livingBase.destPos);
        return (Mth.sin(f) + 1.0F) * f1;
    }

    @Override
    protected boolean shouldShowName(TerrorBirdEntity entity) {
        return entity.hasCustomName();
    }

    /*public static class RenderFactory implements IRenderFactory<TerrorBirdEntity> {

        @Override
        public EntityRenderer<? super TerrorBirdEntity> createRenderFor(EntityRenderDispatcher manager) {
            return new TerrorBirdRenderer(manager);
        }
    }*/
}
