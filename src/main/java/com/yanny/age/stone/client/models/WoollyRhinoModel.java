package com.yanny.age.stone.client.models;

import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.yanny.age.stone.Reference;
import com.yanny.age.stone.entities.WoollyRhinoEntity;
import net.minecraft.client.model.AgeableListModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;

import javax.annotation.Nonnull;

public class WoollyRhinoModel extends AgeableListModel<WoollyRhinoEntity> {
	public static final ModelLayerLocation WOOLLYRHINO_LAYER = new ModelLayerLocation(
			new ResourceLocation(Reference.MODID,"swoollyrhino_layer"),"main");
	private final ModelPart body;
	private final ModelPart head;
	private final ModelPart foot1;
	private final ModelPart foot2;
	private final ModelPart foot3;
	private final ModelPart foot4;

	public WoollyRhinoModel(ModelPart part) {
		this.body = part.getChild("body");
		this.head = part.getChild("head");
		this.foot1 = part.getChild("foot1");
		this.foot2 = part.getChild("foot2");
		this.foot3 = part.getChild("foot3");
		this.foot4 = part.getChild("foot4");
		/*texWidth = 64;
		texHeight = 64;

		body = new ModelPart(this, 27, 21);
		body.setPos(0.0F, 24.0F, 0.0F);
		body.addBox(-5.0F, -17.0F, -9.0F, 10, 11, 6, 0.0F, false);

		ModelPart body1 = new ModelPart(this, 38, 50);
		body1.addBox(-4.0F, -14.75F, -10.0F, 8, 8, 1, 0.0F, false);
		body.addChild(body1);

		ModelPart body2 = new ModelPart(this, 28, 38);
		body2.addBox(-4.5F, -16.0F, -3.0F, 9, 10, 2, 0.0F, false);
		body.addChild(body2);

		ModelPart body3 = new ModelPart(this, 0, 40);
		body3.addBox(-4.5F, -15.0F, -1.0F, 9, 9, 10, 0.0F, false);
		body.addChild(body3);


		head = new ModelPart(this, 9, 31);
		head.setPos(0.0F, 14.0F, -8.0F);
		head.addBox(-3.0F, -3.0F, -7.0F, 6, 6, 3, 0.0F, false);

		ModelPart head1 = new ModelPart(this, 37, 10);
		head1.addBox(-3.5F, -4.0F, -4.0F, 7, 7, 4, 0.0F, false);
		head.addChild(head1);

		ModelPart head2 = new ModelPart(this, 43, 2);
		head2.addBox(-2.5F, -2.0F, -10.0F, 5, 5, 3, 0.0F, false);
		head.addChild(head2);

		ModelPart head3 = new ModelPart(this, 50, 44);
		head3.addBox(-0.5F, -9.0F, -9.5F, 1, 5, 1, 0.0F, false);
		head.addChild(head3);

		ModelPart head4 = new ModelPart(this, 3, 42);
		head4.addBox(-0.5F, -6.0F, -6.75F, 1, 3, 1, 0.0F, false);
		head.addChild(head4);

		ModelPart head5 = new ModelPart(this, 16, 12);
		head5.addBox(2.75F, -5.0F, -3.75F, 1, 2, 1, 0.0F, false);
		head5.addBox(-3.75F, -5.0F, -3.75F, 1, 2, 1, 0.0F, false);
		head.addChild(head5);

		ModelPart head6 = new ModelPart(this, 50, 38);
		head6.addBox(-1.0F, -4.0F, -9.75F, 2, 2, 2, 0.0F, false);
		head.addChild(head6);

		foot1 = new ModelPart(this, 0, 25);
		foot1.setPos(1.0F, 18.0F, -6.5F);
		foot1.addBox(0.0F, 0.0F, -1.5F, 3, 6, 3, 0.0F, false);

		foot2 = new ModelPart(this, 0, 16);
		foot2.setPos(-1.0F, 18.0F, -6.5F);
		foot2.addBox(-3.0F, 0.0F, -1.5F, 3, 6, 3, 0.0F, false);

		foot3 = new ModelPart(this, 12, 22);
		foot3.setPos(1.0F, 18.0F, 6.5F);
		foot3.addBox(0.0F, 0.0F, -1.5F, 3, 6, 3, 0.0F, false);

		foot4 = new ModelPart(this, 0, 0);
		foot4.setPos(-1.0F, 18.0F, 6.5F);
		foot4.addBox(-3.0F, 0.0F, -1.5F, 3, 6, 3, 0.0F, false);*/
	}

	@Override
	public void renderToBuffer(@Nonnull PoseStack matrixStackIn, @Nonnull VertexConsumer bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
		matrixStackIn.pushPose();
		matrixStackIn.scale(1.2f, 1.2f, 1.2f);
		matrixStackIn.translate(0, -0.25, 0);
		super.renderToBuffer(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
		matrixStackIn.popPose();
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

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition root = meshdefinition.getRoot();

		// body
		PartDefinition body = root.addOrReplaceChild("body",
				CubeListBuilder.create()
						.texOffs(27, 21)
						.addBox(-5.0F, -17.0F, -9.0F, 10, 11, 6),
				PartPose.offset(0.0F, 24.0F, 0.0F));

		body.addOrReplaceChild("body1",
				CubeListBuilder.create()
						.texOffs(38, 50)
						.addBox(-4.0F, -14.75F, -10.0F, 8, 8, 1),
				PartPose.ZERO);

		body.addOrReplaceChild("body2",
				CubeListBuilder.create()
						.texOffs(28, 38)
						.addBox(-4.5F, -16.0F, -3.0F, 9, 10, 2),
				PartPose.ZERO);

		body.addOrReplaceChild("body3",
				CubeListBuilder.create()
						.texOffs(0, 40)
						.addBox(-4.5F, -15.0F, -1.0F, 9, 9, 10),
				PartPose.ZERO);

		// head
		PartDefinition head = root.addOrReplaceChild("head",
				CubeListBuilder.create()
						.texOffs(9, 31)
						.addBox(-3.0F, -3.0F, -7.0F, 6, 6, 3),
				PartPose.offset(0.0F, 14.0F, -8.0F));

		head.addOrReplaceChild("head1",
				CubeListBuilder.create()
						.texOffs(37, 10)
						.addBox(-3.5F, -4.0F, -4.0F, 7, 7, 4),
				PartPose.ZERO);

		head.addOrReplaceChild("head2",
				CubeListBuilder.create()
						.texOffs(43, 2)
						.addBox(-2.5F, -2.0F, -10.0F, 5, 5, 3),
				PartPose.ZERO);

		head.addOrReplaceChild("head3",
				CubeListBuilder.create()
						.texOffs(50, 44)
						.addBox(-0.5F, -9.0F, -9.5F, 1, 5, 1),
				PartPose.ZERO);

		head.addOrReplaceChild("head4",
				CubeListBuilder.create()
						.texOffs(3, 42)
						.addBox(-0.5F, -6.0F, -6.75F, 1, 3, 1),
				PartPose.ZERO);

		head.addOrReplaceChild("head5",
				CubeListBuilder.create()
						.texOffs(16, 12)
						.addBox(2.75F, -5.0F, -3.75F, 1, 2, 1)
						.addBox(-3.75F, -5.0F, -3.75F, 1, 2, 1),
				PartPose.ZERO);

		head.addOrReplaceChild("head6",
				CubeListBuilder.create()
						.texOffs(50, 38)
						.addBox(-1.0F, -4.0F, -9.75F, 2, 2, 2),
				PartPose.ZERO);

		// feet
		root.addOrReplaceChild("foot1",
				CubeListBuilder.create()
						.texOffs(0, 25)
						.addBox(0.0F, 0.0F, -1.5F, 3, 6, 3),
				PartPose.offset(1.0F, 18.0F, -6.5F));

		root.addOrReplaceChild("foot2",
				CubeListBuilder.create()
						.texOffs(0, 16)
						.addBox(-3.0F, 0.0F, -1.5F, 3, 6, 3),
				PartPose.offset(-1.0F, 18.0F, -6.5F));

		root.addOrReplaceChild("foot3",
				CubeListBuilder.create()
						.texOffs(12, 22)
						.addBox(0.0F, 0.0F, -1.5F, 3, 6, 3),
				PartPose.offset(1.0F, 18.0F, 6.5F));

		root.addOrReplaceChild("foot4",
				CubeListBuilder.create()
						.texOffs(0, 0)
						.addBox(-3.0F, 0.0F, -1.5F, 3, 6, 3),
				PartPose.offset(-1.0F, 18.0F, 6.5F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void setupAnim(@Nonnull WoollyRhinoEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.setRotationAngles(limbSwing, limbSwingAmount, netHeadYaw, headPitch);
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