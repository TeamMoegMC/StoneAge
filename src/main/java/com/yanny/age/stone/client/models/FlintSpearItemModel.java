package com.yanny.age.stone.client.models;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.model.Model;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nonnull;

@OnlyIn(Dist.CLIENT)
public class FlintSpearItemModel extends Model {
	private final ModelPart bone;

	public FlintSpearItemModel() {
		super(RenderType::entityCutout);
		texWidth = 32;
		texHeight = 32;

		bone = new ModelPart(this, 0, 0);
		bone.setPos(0.0F, 24.0F, 0.0F);
		bone.addBox(-0.5F, -16F, -0.5F, 1, 29, 1, 0.0F, false);

		ModelPart bone1 = new ModelPart(this, 4, 0);
		bone1.addBox(-1.5F, 12F, -0.5F, 1, 2, 1, 0.0F, false);
		bone.addChild(bone1);

		ModelPart bone2 = new ModelPart(this, 8, 0);
		bone2.addBox(0.5F, 12F, -0.5F, 1, 2, 1, 0.0F, false);
		bone.addChild(bone2);

		ModelPart bone3 = new ModelPart(this, 4, 3);
		bone3.addBox(-1.0F, 14F, -0.5F, 2, 1, 1, 0.0F, false);
		bone.addChild(bone3);

		ModelPart bone4 = new ModelPart(this, 4, 5);
		bone4.addBox(-0.5F, 13f, -0.5F, 1, 3, 1, 0.0F, false);
		bone.addChild(bone4);

		ModelPart bone5 = new ModelPart(this, 4, 9);
		bone5.addBox(-0.5F, 13F, -1.0F, 1, 2, 2, 0.0F, false);
		bone.addChild(bone5);
	}

	@Override
	public void renderToBuffer(@Nonnull PoseStack matrixStack, @Nonnull VertexConsumer iVertexBuilder, int i, int i1, float v, float v1, float v2, float v3) {
		bone.render(matrixStack, iVertexBuilder, i, i1, v, v1, v2, v3);
	}
}