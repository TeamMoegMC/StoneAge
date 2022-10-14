package com.yanny.age.stone.client.models;

import com.google.common.collect.ImmutableList;
import com.yanny.age.stone.entities.MouflonEntity;
import net.minecraft.client.renderer.entity.model.AgeableModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nonnull;

@OnlyIn(Dist.CLIENT)
public class MouflonModel extends AgeableModel<MouflonEntity> {
	private final ModelRenderer body;
	private final ModelRenderer body1;
	private final ModelRenderer head;
	private final ModelRenderer horn4_r1;
	private final ModelRenderer horn2_r1;
	private final ModelRenderer feet1;
	private final ModelRenderer feet2;
	private final ModelRenderer feet3;
	private final ModelRenderer feet4;

	public MouflonModel() {
		textureWidth = 64;
		textureHeight = 64;
		body = new ModelRenderer(this);
		body.setRotationPoint(0.0F, 13.0F, 0.0F);
		body.setTextureOffset(0, 23).addBox(-5.0F, -7.0F, -7.0F, 10.0F, 8.0F, 15.0F, 0.0F, false);

		body1 = new ModelRenderer(this);
		body1.setRotationPoint(0.0F, -3.5F, 9.5F);
		body.addChild(body1);
		setRotationAngle(body1, 0.5672F, 0.0F, 0.0F);
		body1.setTextureOffset(0, 20).addBox(-1.0F, -1.5F, -1.5F, 2.0F, 3.0F, 1.0F, 0.0F, false);

		head = new ModelRenderer(this);
		head.setRotationPoint(0.0F, 7.0F, -7.0F);
		head.setTextureOffset(40, 0).addBox(-3.0F, -4.0F, -4.0F, 6.0F, 5.0F, 6.0F, 0.0F, false);

		horn4_r1 = new ModelRenderer(this);
		horn4_r1.setRotationPoint(0.0F, -1.5F, -1.0F);
		head.addChild(horn4_r1);
		setRotationAngle(horn4_r1, -0.6109F, 0.2269F, -0.1047F);
		horn4_r1.setTextureOffset(10, 0).addBox(1.5F, -6.5F, -1.0F, 1.0F, 2.0F, 1.0F, 0.0F, false);
		horn4_r1.setTextureOffset(10, 0).addBox(1.0F, -4.5F, -1.0F, 2.0F, 3.0F, 1.0F, 0.0F, false);

		horn2_r1 = new ModelRenderer(this);
		horn2_r1.setRotationPoint(0.0F, -1.5F, -1.0F);
		head.addChild(horn2_r1);
		setRotationAngle(horn2_r1, -0.6109F, -0.2269F, 0.1047F);
		horn2_r1.setTextureOffset(10, 0).addBox(-2.5F, -6.5F, -1.0F, 1.0F, 2.0F, 1.0F, 0.0F, false);
		horn2_r1.setTextureOffset(10, 0).addBox(-3.0F, -4.5F, -1.0F, 2.0F, 3.0F, 1.0F, 0.0F, false);

		feet1 = new ModelRenderer(this);
		feet1.setRotationPoint(2.0F, 14.0F, -5.0F);
		feet1.setTextureOffset(0, 0).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 10.0F, 2.0F, 0.0F, false);

		feet2 = new ModelRenderer(this);
		feet2.setRotationPoint(2.0F, 14.0F, 6.0F);
		feet2.setTextureOffset(0, 0).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 10.0F, 2.0F, 0.0F, false);

		feet3 = new ModelRenderer(this);
		feet3.setRotationPoint(-2.0F, 14.0F, 6.0F);
		feet3.setTextureOffset(0, 0).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 10.0F, 2.0F, 0.0F, false);

		feet4 = new ModelRenderer(this);
		feet4.setRotationPoint(-2.0F, 14.0F, -5.0F);
		feet4.setTextureOffset(0, 0).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 10.0F, 2.0F, 0.0F, false);
	}

	@Nonnull
	@Override
	protected Iterable<ModelRenderer> getHeadParts() {
		return ImmutableList.of();
	}

	@Nonnull
	@Override
	protected Iterable<ModelRenderer> getBodyParts() {
		return ImmutableList.of(head, body, feet1, feet2, feet3, feet4);
	}

	@Override
	public void setRotationAngles(@Nonnull MouflonEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.setRotationAngles(limbSwing, limbSwingAmount, netHeadYaw, headPitch);
	}

	public void setRotationAngles(float limbSwing, float limbSwingAmount, float netHeadYaw, float headPitch) {
		this.head.rotateAngleX = headPitch * ((float) Math.PI / 180F);
		this.head.rotateAngleY = netHeadYaw * ((float) Math.PI / 180F);
		this.feet1.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
		this.feet2.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F * limbSwingAmount;
		this.feet3.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
		this.feet4.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F * limbSwingAmount;
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}