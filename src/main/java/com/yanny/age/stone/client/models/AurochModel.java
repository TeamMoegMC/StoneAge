package com.yanny.age.stone.client.models;

import com.google.common.collect.ImmutableList;
import com.yanny.age.stone.entities.AurochEntity;
import net.minecraft.client.renderer.entity.model.AgeableModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nonnull;

@OnlyIn(Dist.CLIENT)
public class AurochModel extends AgeableModel<AurochEntity> {
	private final ModelRenderer body;
	private final ModelRenderer head;
	private final ModelRenderer foot1;
	private final ModelRenderer foot2;
	private final ModelRenderer foot3;
	private final ModelRenderer foot4;

	public AurochModel() {
		texWidth = 64;
		texHeight = 64;

		body = new ModelRenderer(this, 0, 36);
		body.setPos(0.0F, 5.0F, 0.0F);
		body.addBox(-6.0F, -4.0F, -9.0F, 12, 10, 18, 0.0F, false);

		head = new ModelRenderer(this, 32, 0);
		head.setPos(0.0F, 3.0F, -7.0F);
		head.addBox(-4.0F, -4.0F, -8.0F, 8, 8, 8, 0.0F, false);

		ModelRenderer horns = new ModelRenderer(this, 0, 30);
		horns.setPos(0.0F, 0.0F, -4.0F);
		horns.xRot = 0.6981F;
		head.addChild(horns);
		horns.addBox(4.0F, -1.0F, -1.0F, 3, 1, 1, 0.0F, false);
		horns.addBox(-7.0F, -1.0F, -1.0F, 3, 1, 1, 0.0F, false);
		horns.addBox(6.0F, -3.0F, -1.0F, 1, 2, 1, 0.0F, false);
		horns.addBox(-7.0F, -3.0F, -1.0F, 1, 2, 1, 0.0F, false);

		foot1 = new ModelRenderer(this, 0, 0);
		foot1.setPos(-3.0F, 11.0F, 6.0F);
		foot1.addBox(-2.0F, 0.0F, -2.0F, 4, 13, 4, 0.0F, false);

		foot2 = new ModelRenderer(this, 0, 0);
		foot2.setPos(3.0F, 11.0F, 6.0F);
		foot2.addBox(-2.0F, 0.0F, -2.0F, 4, 13, 4, 0.0F, false);

		foot3 = new ModelRenderer(this, 0, 0);
		foot3.setPos(3.0F, 11.0F, -6.0F);
		foot3.addBox(-2.0F, 0.0F, -2.0F, 4, 13, 4, 0.0F, false);

		foot4 = new ModelRenderer(this, 0, 0);
		foot4.setPos(-3.0F, 11.0F, -6.0F);
		foot4.addBox(-2.0F, 0.0F, -2.0F, 4, 13, 4, 0.0F, false);
	}

	@Nonnull
	@Override
	protected Iterable<ModelRenderer> headParts() {
		return ImmutableList.of();
	}

	@Nonnull
	@Override
	protected Iterable<ModelRenderer> bodyParts() {
		return ImmutableList.of(head, body, foot1, foot2, foot3, foot4);
	}

	@Override
	public void setupAnim(@Nonnull AurochEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.setRotationAngles(limbSwing, limbSwingAmount, netHeadYaw, headPitch);
	}

	public void setRotationAngles(float limbSwing, float limbSwingAmount, float netHeadYaw, float headPitch) {
		this.head.xRot = headPitch * ((float)Math.PI / 180F);
		this.head.yRot = netHeadYaw * ((float)Math.PI / 180F);
		this.foot1.xRot = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
		this.foot2.xRot = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
		this.foot3.xRot = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
		this.foot4.xRot = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
	}
}