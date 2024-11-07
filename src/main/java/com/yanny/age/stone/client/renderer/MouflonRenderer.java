package com.yanny.age.stone.client.renderer;

import com.yanny.age.stone.Reference;
import com.yanny.age.stone.client.models.MouflonModel;
import com.yanny.age.stone.entities.MouflonEntity;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nonnull;

@OnlyIn(Dist.CLIENT)
public class MouflonRenderer extends MobRenderer<MouflonEntity, MouflonModel> {
    private static final ResourceLocation MOUFLON_TEXTURE = new ResourceLocation(Reference.MODID, "textures/entity/mouflon.png");

    private MouflonRenderer(@Nonnull EntityRendererProvider.Context context) {
        super(context, new MouflonModel(), 0.5f);
    }

    @Nonnull
    @Override
    public ResourceLocation getTextureLocation(@Nonnull MouflonEntity entity) {
        return MOUFLON_TEXTURE;
    }

    @Override
    protected boolean shouldShowName(MouflonEntity entity) {
        return entity.hasCustomName();
    }

    /*public static class RenderFactory implements IRenderFactory<MouflonEntity> {

        @Override
        public EntityRenderer<? super MouflonEntity> createRenderFor(EntityRenderDispatcher manager) {
            return new MouflonRenderer(manager);
        }
    }*/
}
