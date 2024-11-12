package com.yanny.age.stone.client.renderer;

import com.yanny.age.stone.Reference;
import com.yanny.age.stone.client.models.WoollyRhinoModel;
import com.yanny.age.stone.entities.WoollyRhinoEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nonnull;

@OnlyIn(Dist.CLIENT)
public class WoollyRhinoRenderer extends MobRenderer<WoollyRhinoEntity, WoollyRhinoModel> {
    private static final ResourceLocation WOOLLY_RHINO_TEXTURE = new ResourceLocation(Reference.MODID, "textures/entity/woolly_rhino.png");

    public WoollyRhinoRenderer(@Nonnull EntityRendererProvider.Context context) {
        super(context, new WoollyRhinoModel(), 0.7f);
    }

    @Nonnull
    @Override
    public ResourceLocation getTextureLocation(@Nonnull WoollyRhinoEntity entity) {
        return WOOLLY_RHINO_TEXTURE;
    }

    @Override
    protected boolean shouldShowName(WoollyRhinoEntity entity) {
        return entity.hasCustomName();
    }

    /*public static class RenderFactory implements IRenderFactory<WoollyRhinoEntity> {

        @Override
        public EntityRenderer<? super WoollyRhinoEntity> createRenderFor(EntityRenderDispatcher manager) {
            return new WoollyRhinoRenderer(manager);
        }
    }*/
}
