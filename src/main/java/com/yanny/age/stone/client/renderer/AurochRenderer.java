package com.yanny.age.stone.client.renderer;

import com.yanny.age.stone.Reference;
import com.yanny.age.stone.client.models.AurochModel;
import com.yanny.age.stone.entities.AurochEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nonnull;

@OnlyIn(Dist.CLIENT)
public class AurochRenderer extends MobRenderer<AurochEntity, AurochModel> {
    private static final ResourceLocation AUROCH_TEXTURE = new ResourceLocation(Reference.MODID, "textures/entity/auroch.png");

    public AurochRenderer(@Nonnull EntityRendererProvider.Context context) {
        super(context, new AurochModel(context.bakeLayer(AurochModel.AUROCH_LAYER)), 0.5f);
    }

    @Nonnull
    @Override
    public ResourceLocation getTextureLocation(@Nonnull AurochEntity entity) {
        return AUROCH_TEXTURE;
    }

    @Override
    protected boolean shouldShowName(AurochEntity entity) {
        return entity.hasCustomName();
    }

    /*public static class RenderFactory implements IRenderFactory<AurochEntity> {

        @Override
        public EntityRenderer<? super AurochEntity> createRenderFor(EntityRenderDispatcher manager) {
            return new AurochRenderer(manager);
        }
    }*/
}
