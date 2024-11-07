package com.yanny.age.stone.client.models;

import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.yanny.age.stone.entities.TerrorBirdEntity;
import net.minecraft.client.model.AgeableListModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.util.Mth;

import javax.annotation.Nonnull;

public class TerrorBirdModel extends AgeableListModel<TerrorBirdEntity> {
//	private final ModelPart rfeet;
//	private final ModelPart body;
//	private final ModelPart head;
//	private final ModelPart lfeet;

	public TerrorBirdModel() {
		/*texWidth = 64;
		texHeight = 64;

		rfeet = new ModelPart(this);
		rfeet.setPos(1.0F, 17.0F, 1.0F);
		rfeet.texOffs(23, 54).addBox(0.0F, 6.0F, -3.0F, 2.0F, 1.0F, 3.0F, 0.0F, false);
		rfeet.texOffs(0, 53).addBox(0.0F, -1.0F, -1.0F, 2.0F, 4.0F, 2.0F, 0.0F, false);

		ModelPart knee = new ModelPart(this);
		knee.setPos(1.0F, 4.25F, -0.5F);
		rfeet.addChild(knee);
		setRotationAngle(knee, -0.1745F, 0.0F, 0.0F);
		knee.texOffs(0, 39).addBox(-0.5F, -2.0F, -0.5F, 1.0F, 4.0F, 1.0F, 0.0F, false);

		body = new ModelPart(this);
		body.setPos(0.0F, 19.25F, -0.5F);
		body.texOffs(0, 51).addBox(-3.5F, -7.25F, -4.5F, 7.0F, 5.0F, 8.0F, 0.0F, true);

		ModelPart bone = new ModelPart(this);
		bone.setPos(0.0F, -4.25F, 5.0F);
		body.addChild(bone);
		setRotationAngle(bone, -0.1745F, 0.0F, 0.0F);
		bone.texOffs(30, 56).addBox(-2.5F, -2.0F, -2.5F, 5.0F, 4.0F, 4.0F, 0.0F, false);
		bone.texOffs(48, 61).addBox(-1.5F, -1.5F, 1.5F, 3.0F, 2.0F, 1.0F, 0.0F, false);
		bone.texOffs(50, 56).addBox(-1.5F, -1.5F, 2.5F, 3.0F, 1.0F, 4.0F, 0.0F, false);

		head = new ModelPart(this);
		head.setPos(0.0F, 13.0F, -4.0F);
		head.texOffs(37, 43).addBox(-2.0F, -9.0F, -5.0F, 4.0F, 3.0F, 4.0F, 0.0F, false);
		head.texOffs(10, 38).addBox(-1.5F, -9.0F, -10.0F, 3.0F, 2.0F, 5.0F, 0.0F, false);
		head.texOffs(26, 32).addBox(-1.0F, -7.75F, -10.15F, 2.0F, 1.0F, 1.0F, 0.0F, false);

		ModelPart bone2 = new ModelPart(this);
		bone2.setPos(0.0F, -5.0F, -2.0F);
		head.addChild(bone2);
		setRotationAngle(bone2, 0.3491F, 0.0F, 0.0F);
		bone2.texOffs(0, 8).addBox(-1.0F, -2.0F, -1.0F, 2.0F, 8.0F, 2.0F, 0.0F, false);

		ModelPart bone3 = new ModelPart(this);
		bone3.setPos(0.0F, -7.0F, -5.0F);
		head.addChild(bone3);
		setRotationAngle(bone3, 0.0873F, 0.0F, 0.0F);
		bone3.texOffs(4, 30).addBox(-1.5F, 0.0F, -4.0F, 3.0F, 1.0F, 4.0F, 0.0F, false);

		lfeet = new ModelPart(this);
		lfeet.setPos(-1.0F, 17.0F, 1.0F);
		lfeet.texOffs(23, 54).addBox(-2.0F, 6.0F, -3.0F, 2.0F, 1.0F, 3.0F, 0.0F, false);
		lfeet.texOffs(0, 53).addBox(-2.0F, -1.0F, -1.0F, 2.0F, 4.0F, 2.0F, 0.0F, false);

		ModelPart knee2 = new ModelPart(this);
		knee2.setPos(-1.0F, 4.25F, -0.5F);
		lfeet.addChild(knee2);
		setRotationAngle(knee2, -0.1745F, 0.0F, 0.0F);
		knee2.texOffs(0, 39).addBox(-0.5F, -2.0F, -0.5F, 1.0F, 4.0F, 1.0F, 0.0F, false);*/
	}

	@Override
	public void setupAnim(@Nonnull TerrorBirdEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
//		this.head.xRot = headPitch * ((float)Math.PI / 180F);
//		this.head.yRot = netHeadYaw * ((float)Math.PI / 180F);
//		this.lfeet.xRot = Mth.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
//		this.rfeet.xRot = Mth.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
	}

	@Override
	public void renderToBuffer(@Nonnull PoseStack matrixStackIn, @Nonnull VertexConsumer bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
		matrixStackIn.scale(1.5f, 1.5f, 1.5f);
		matrixStackIn.translate(0, -0.5f, 0);
		super.renderToBuffer(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
	}

	@Nonnull
	@Override
	protected Iterable<ModelPart> headParts() {
		return ImmutableList.of();
	}

	@Nonnull
	@Override
	protected Iterable<ModelPart> bodyParts() {
		return ImmutableList.of(/*body, rfeet, lfeet, head*/);
	}

	public void setRotationAngle(ModelPart modelRenderer, float x, float y, float z) {
		modelRenderer.xRot = x;
		modelRenderer.yRot = y;
		modelRenderer.zRot = z;
	}
}