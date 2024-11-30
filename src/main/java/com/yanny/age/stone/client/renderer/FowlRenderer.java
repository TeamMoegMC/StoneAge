package com.yanny.age.stone.client.renderer;

import com.yanny.age.stone.Reference;
import com.yanny.age.stone.client.models.DeerModel;
import com.yanny.age.stone.client.models.FowlModel;
import com.yanny.age.stone.entities.FowlEntity;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nonnull;

@OnlyIn(Dist.CLIENT)
public class FowlRenderer extends MobRenderer<FowlEntity, FowlModel> {
    private static final ResourceLocation FOWL_TEXTURE = new ResourceLocation(Reference.MODID, "textures/entity/fowl.png");
    public FowlRenderer(@Nonnull EntityRendererProvider.Context context) {
        super(context, new FowlModel(context.bakeLayer(FowlModel.FOWL_LAYER)), 0.3f);
    }

    @Nonnull
    @Override
    public ResourceLocation getTextureLocation(@Nonnull FowlEntity entity) {
        return FOWL_TEXTURE;
    }

    @Override
    protected float getBob(FowlEntity livingBase, float partialTicks) {
        float f = Mth.lerp(partialTicks, livingBase.oFlap, livingBase.wingRotation);
        float f1 = Mth.lerp(partialTicks, livingBase.oFlapSpeed, livingBase.destPos);
        return (Mth.sin(f) + 1.0F) * f1;
    }

    @Override
    protected boolean shouldShowName(FowlEntity entity) {
        return entity.hasCustomName();
    }

    /*public static class RenderFactory implements IRenderFactory<FowlEntity> {

        @Override
        public EntityRenderer<? super FowlEntity> createRenderFor(EntityRenderDispatcher manager) {
            return new FowlRenderer(manager);
        }
    }*/
}
