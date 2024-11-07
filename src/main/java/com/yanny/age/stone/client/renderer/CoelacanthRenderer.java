package com.yanny.age.stone.client.renderer;

import com.yanny.age.stone.Reference;
import com.yanny.age.stone.client.models.CoelacanthModel;
import com.yanny.age.stone.entities.CoelacanthEntity;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nonnull;

@OnlyIn(Dist.CLIENT)
public class CoelacanthRenderer extends MobRenderer<CoelacanthEntity, CoelacanthModel> {
    private static final ResourceLocation COELACANT_TEXTURE = new ResourceLocation(Reference.MODID, "textures/entity/coelacanth.png");

    private CoelacanthRenderer(@Nonnull EntityRendererProvider.Context context) {
        super(context, new CoelacanthModel(), 0.3f);
    }

    @Nonnull
    @Override
    public ResourceLocation getTextureLocation(@Nonnull CoelacanthEntity entity) {
        return COELACANT_TEXTURE;
    }

    @Override
    protected float getBob(@Nonnull CoelacanthEntity livingBase, float partialTicks) {
        return 1.0f;
    }

    @Override
    protected boolean shouldShowName(CoelacanthEntity entity) {
        return entity.hasCustomName();
    }

    /*public static class RenderFactory implements IRenderFactory<CoelacanthEntity> {

        @Override
        public EntityRenderer<? super CoelacanthEntity> createRenderFor(EntityRenderDispatcher manager) {
            return new CoelacanthRenderer(manager);
        }
    }*/
}
