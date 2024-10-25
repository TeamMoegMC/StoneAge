package com.yanny.age.stone.client.models;

import com.google.common.collect.ImmutableList;
import com.yanny.age.stone.entities.SaberToothTigerEntity;
import net.minecraft.client.model.AgeableListModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.util.Mth;

import javax.annotation.Nonnull;

public class SaberToothTigerModel extends AgeableListModel<SaberToothTigerEntity> {
	private final ModelPart body;
	private final ModelPart foot1;
	private final ModelPart foot2;
	private final ModelPart foot3;
	private final ModelPart foot4;
	private final ModelPart head;

	public SaberToothTigerModel() {
		texWidth = 64;
		texHeight = 64;

		body = new ModelPart(this, 0, 42);
		body.setPos(0.0F, 24.0F, -2.0F);
		body.addBox(-4.5F, -13.0F, -7.0F, 9, 8, 8, 0.0F, false);

		ModelPart body1 = new ModelPart(this, 0, 11);
		body1.addBox(-2.5F, -13.0F, -9.0F, 5, 5, 2, 0.0F, false);
		body.addChild(body1);

		ModelPart body2 = new ModelPart(this, 17, 3);
		body2.addBox(-3.5F, -12.0F, 1.0F, 7, 6, 9, 0.0F, false);
		body.addChild(body2);

		foot1 = new ModelPart(this, 26, 41);
		foot1.setPos(3.0F, 17.0F, 7.0F);
		foot1.addBox(-1.5F, 6.0F, -2.5F, 2, 1, 2, 0.0F, false);

		ModelPart bone = new ModelPart(this, 35, 50);
		bone.setPos(-3.0F, 3.0F, -2.0F);
		setRotationAngle(bone, -0.4363F, 0.0F, 0.0F);
		foot1.addChild(bone);
		bone.addBox(1.0F, -4.0F, -1.25F, 3, 4, 3, 0.0F, false);

		ModelPart bone2 = new ModelPart(this, 48, 51);
		bone2.setPos(0.0F, 3.0F, -2.0F);
		setRotationAngle(bone2, 0.3491F, 0.0F, 0.0F);
		foot1.addChild(bone2);
		bone2.addBox(-1.5F, -1.0F, -0.5F, 2, 5, 2, 0.0F, false);

		foot2 = new ModelPart(this, 26, 38);
		foot2.setPos(-2.0F, 17.0F, 7.0F);
		foot2.addBox(-1.5F, 6.0F, -2.5F, 2, 1, 2, 0.0F, false);

		ModelPart bone3 = new ModelPart(this, 1, 2);
		bone3.setPos(2.0F, 3.0F, -1.0F);
		setRotationAngle(bone3, -0.4363F, 0.0F, 0.0F);
		foot2.addChild(bone3);
		bone3.addBox(-4.0F, -3.5774F, -2.1563F, 3, 4, 3, 0.0F, false);

		ModelPart bone4 = new ModelPart(this, 47, 23);
		bone4.setPos(5.0F, 3.0F, -1.0F);
		setRotationAngle(bone4, 0.3491F, 0.0F, 0.0F);
		foot2.addChild(bone4);
		bone4.addBox(-6.5F, -1.342F, -1.4397F, 2, 5, 2, 0.0F, false);

		foot3 = new ModelPart(this);
		foot3.setPos(2.25F, 18.0F, -6.5F);

		ModelPart bone5 = new ModelPart(this, 49, 38);
		bone5.setPos(-2.0F, 6.0F, 6.5F);
		foot3.addChild(bone5);
		bone5.addBox(2.0F, -4.0F, -6.75F, 2, 4, 2, 0.0F, false);

		ModelPart bone51 = new ModelPart(this, 36, 42);
		bone51.addBox(2.0F, -1.0F, -7.75F, 2, 1, 1, 0.0F, false);
		bone5.addChild(bone51);

		ModelPart bone6 = new ModelPart(this, 0, 36);
		bone6.setPos(1.0F, 1.0F, 0.0F);
		setRotationAngle(bone6, 0.3491F, 0.0F, 0.0F);
		foot3.addChild(bone6);
		bone6.addBox(-1.5F, -1.0F, -1.5F, 3, 3, 3, 0.0F, false);

		foot4 = new ModelPart(this);
		foot4.setPos(-1.75F, 18.0F, -6.5F);

		ModelPart bone7 = new ModelPart(this, 49, 32);
		bone7.setPos(2.0F, 6.0F, 6.5F);
		foot4.addChild(bone7);
		bone7.addBox(-4.5F, -4.0F, -6.75F, 2, 4, 2, 0.0F, false);

		ModelPart bone71 = new ModelPart(this, 36, 40);
		bone71.addBox(-4.5F, -1.0F, -7.75F, 2, 1, 1, 0.0F, false);
		bone7.addChild(bone71);

		ModelPart bone8 = new ModelPart(this, 12, 36);
		bone8.setPos(5.0F, 1.0F, 0.0F);
		setRotationAngle(bone8, 0.3491F, 0.0F, 0.0F);
		foot4.addChild(bone8);
		bone8.addBox(-8.0F, -1.0F, -1.5F, 3, 3, 3, 0.0F, false);

		head = new ModelPart(this, 0, 26);
		head.setPos(0.0F, 13.0F, -10.0F);
		head.addBox(-3.0F, -2.5F, -3.0F, 6, 6, 3, 0.0F, false);

		ModelPart head1 = new ModelPart(this, 22, 28);
		head1.addBox(-2.5F, -2.0F, -5.0F, 5, 5, 2, 0.0F, false);
		head.addChild(head1);

		ModelPart head2 = new ModelPart(this, 0, 19);
		head2.addBox(-2.0F, -0.5F, -8.0F, 4, 2, 3, 0.0F, false);
		head.addChild(head2);

		ModelPart bone9 = new ModelPart(this, 19, 20);
		bone9.setPos(0.0F, 2.0F, -5.0F);
		setRotationAngle(bone9, 0.0873F, 0.0F, 0.0F);
		head.addChild(bone9);
		bone9.addBox(-1.5F, -0.5F, -3.0F, 3, 1, 3, 0.0F, false);

		ModelPart bone10 = new ModelPart(this, 41, 27);
		bone10.setPos(0.5F, 1.0F, -7.25F);
		setRotationAngle(bone10, 0.0873F, 0.0F, 0.0F);
		head.addChild(bone10);
		bone10.addBox(0.4F, 0.25F, -0.75F, 1, 3, 1, 0.0F, false);
		bone10.addBox(-2.4F, 0.25F, -0.75F, 1, 3, 1, 0.0F, false);
	}

	@Nonnull
	@Override
	protected Iterable<ModelPart> headParts() {
		return ImmutableList.of();
	}

	@Nonnull
	@Override
	protected Iterable<ModelPart> bodyParts() {
		return ImmutableList.of(head, body, foot1, foot2, foot3, foot4);
	}

	@Override
	public void setupAnim(@Nonnull SaberToothTigerEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.setRotationAngles(limbSwing, limbSwingAmount, netHeadYaw, headPitch);
	}

	public void setRotationAngle(@Nonnull ModelPart ModelRenderer, float x, float y, float z) {
		ModelRenderer.xRot = x;
		ModelRenderer.yRot = y;
		ModelRenderer.zRot = z;
	}

	public void setRotationAngles(float limbSwing, float limbSwingAmount, float netHeadYaw, float headPitch) {
		this.head.xRot = headPitch * ((float)Math.PI / 180F);
		this.head.yRot = netHeadYaw * ((float)Math.PI / 180F);
		this.foot1.xRot = Mth.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
		this.foot2.xRot = Mth.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
		this.foot3.xRot = Mth.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
		this.foot4.xRot = Mth.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
	}
}