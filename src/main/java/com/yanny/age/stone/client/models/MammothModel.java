package com.yanny.age.stone.client.models;

import com.google.common.collect.ImmutableList;
import com.yanny.age.stone.Reference;
import com.yanny.age.stone.entities.MammothEntity;
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

public class MammothModel extends AgeableListModel<MammothEntity> {
	public static final ModelLayerLocation MAMMOTH_LAYER = new ModelLayerLocation(
			new ResourceLocation(Reference.MODID,"mammoth_layer"),"main");
	private final ModelPart body;
	private final ModelPart head;
	private final ModelPart foot1;
	private final ModelPart foot2;
	private final ModelPart foot3;
	private final ModelPart foot4;

	public MammothModel(ModelPart part) {
		this.body = part.getChild("body");
		this.head = part.getChild("head");
		this.foot1 = part.getChild("foot1");
		this.foot2 = part.getChild("foot2");
		this.foot3 = part.getChild("foot3");
		this.foot4 = part.getChild("foot4");
		/*texWidth = 64;
		texHeight = 64;

		body = new ModelPart(this, 0, 39);
		body.setPos(0.0F, 5.0F, 0.0F);
		body.addBox(-6.0F, -8.0F, -9.0F, 12, 17, 8, 0.0F, false);

		ModelPart body1 = new ModelPart(this, 2, 41);
		body1.addBox(-6.0F, -7.0F, -1.0F, 12, 16, 6, 0.0F, false);
		body1.addBox(-6.0F, -6.0F, 5.0F, 12, 15, 6, 0.0F, false);
		body.addChild(body1);

		head = new ModelPart(this, 0 , 17);
		head.setPos(0.0F, -1.0F, -7.0F);
		setRotationAngle(head, -0.3491F, 0.0F, 0.0F);
		head.addBox(-4.0F, -3.0F, -8.0F, 8, 9, 8, 0.0F, false);

		ModelPart head1 = new ModelPart(this, 4, 1);
		head1.addBox(-3.0F, 6.0F, -7.0F, 6, 8, 7, 0.0F, false);
		head.addChild(head1);

		ModelPart head2 = new ModelPart(this, 32, 0);
		head2.addBox(3.0F, 6.0F, -5.0F, 1, 11, 1, 0.0F, false);
		head2.addBox(-4.0F, 6.0F, -5.0F, 1, 11, 1, 0.0F, false);
		head.addChild(head2);

		ModelPart ears2 = new ModelPart(this, 10, 7);
		ears2.setPos(0.0F, 2.0F, -6.0F);
		setRotationAngle(ears2, 0.0F, 0.2618F, 0.0F);
		head.addChild(ears2);
		ears2.addBox(-9.0F, -5.0F, 1.0F, 5, 6, 1, 0.0F, false);

		ModelPart ears = new ModelPart(this, 12,  7);
		ears.setPos(0.0F, 2.0F, -6.0F);
		setRotationAngle(ears, 0.0F, -0.2618F, 0.0F);
		head.addChild(ears);
		ears.addBox(4.0F, -5.0F, 1.0F, 5, 6, 1, 0.0F, false);

		ModelPart horns = new ModelPart(this, 32, 0);
		horns.setPos(0.0F, 17.0F, -4.0F);
		setRotationAngle(horns, -1.2217F, 0.0F, 0.0F);
		head.addChild(horns);
		horns.addBox(-4.0F, 0.0F, -1.0F, 1, 7, 1, 0.0F, false);
		horns.addBox(3.0F, 0.0F, -1.0F, 1, 7, 1, 0.0F, false);

		ModelPart horns2 = new ModelPart(this, 32, 0);
		horns2.setPos(0.0F, 7.0F, 0.0F);
		setRotationAngle(horns2, -0.8727F, 0.0F, 0.0F);
		horns.addChild(horns2);
		horns2.addBox(-4.0F, 0.0F, -1.0F, 1, 4, 1, 0.0F, false);
		horns2.addBox(3.0F, 0.0F, -1.0F, 1, 4, 1, 0.0F, false);

		ModelPart bone = new ModelPart(this, 10, 4);
		bone.setPos(0.0F, 14.0F, -6.0F);
		setRotationAngle(bone, 0.3491F, 0.0F, 0.0F);
		head.addChild(bone);
		bone.addBox(-2.0F, -0.342F, 0.0603F, 4, 5, 4, 0.0F, false);

		ModelPart bone2 = new ModelPart(this, 14, 5);
		bone2.setPos(0.0F, 5.0F, 0.0F);
		setRotationAngle(bone2, 0.3491F, 0.0F, 0.0F);
		bone.addChild(bone2);
		bone2.addBox(-1.0F, -0.0405F, 0.7704F, 2, 5, 3, 0.0F, false);

		ModelPart bone3 = new ModelPart(this, 15, 6);
		bone3.setPos(0.0F, 5.0F, 1.0F);
		setRotationAngle(bone3, 0.8727F, 0.0F, 0.0F);
		bone2.addChild(bone3);
		bone3.addBox(-1.0F, -0.081F, 0.0408F, 2, 7, 2, 0.0F, false);

		foot1 = new ModelPart(this, 48, 49);
		foot1.setPos(-3.0F, 13.0F, 8.0F);
		foot1.addBox(-2.0F, 0.0F, -2.0F, 4, 11, 4, 0.0F, false);

		foot2 = new ModelPart(this, 48, 49);
		foot2.setPos(3.0F, 13.0F, 8.0F);
		foot2.addBox(-2.0F, 0.0F, -2.0F, 4, 11, 4, 0.0F, false);

		foot3 = new ModelPart(this, 48, 49);
		foot3.setPos(3.0F, 13.0F, -6.0F);
		foot3.addBox(-2.0F, 0.0F, -2.0F, 4, 11, 4, 0.0F, false);

		foot4 = new ModelPart(this, 48, 49);
		foot4.setPos(-3.0F, 13.0F, -6.0F);
		foot4.addBox(-2.0F, 0.0F, -2.0F, 4, 11, 4, 0.0F, false);*/
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
						.texOffs(0, 39)
						.addBox(-6.0F, -8.0F, -9.0F, 12, 17, 8),
				PartPose.offset(0.0F, 5.0F, 0.0F));

		body.addOrReplaceChild("body1",
				CubeListBuilder.create()
						.texOffs(2, 41)
						.addBox(-6.0F, -7.0F, -1.0F, 12, 16, 6)
						.addBox(-6.0F, -6.0F, 5.0F, 12, 15, 6),
				PartPose.ZERO);

		// head
		PartDefinition head = root.addOrReplaceChild("head",
				CubeListBuilder.create()
						.texOffs(0, 17)
						.addBox(-4.0F, -3.0F, -8.0F, 8, 9, 8),
				PartPose.offsetAndRotation(0.0F, -1.0F, -7.0F, -0.3491F, 0.0F, 0.0F));

		head.addOrReplaceChild("head1",
				CubeListBuilder.create()
						.texOffs(4, 1)
						.addBox(-3.0F, 6.0F, -7.0F, 6, 8, 7),
				PartPose.ZERO);

		head.addOrReplaceChild("head2",
				CubeListBuilder.create()
						.texOffs(32, 0)
						.addBox(3.0F, 6.0F, -5.0F, 1, 11, 1)
						.addBox(-4.0F, 6.0F, -5.0F, 1, 11, 1),
				PartPose.ZERO);

		// ear
		head.addOrReplaceChild("ears2",
				CubeListBuilder.create()
						.texOffs(10, 7)
						.addBox(-9.0F, -5.0F, 1.0F, 5, 6, 1),
				PartPose.offsetAndRotation(0.0F, 2.0F, -6.0F, 0.0F, 0.2618F, 0.0F));

		head.addOrReplaceChild("ears",
				CubeListBuilder.create()
						.texOffs(12, 7)
						.addBox(4.0F, -5.0F, 1.0F, 5, 6, 1),
				PartPose.offsetAndRotation(0.0F, 2.0F, -6.0F, 0.0F, -0.2618F, 0.0F));

		// horn
		PartDefinition horns = head.addOrReplaceChild("horns",
				CubeListBuilder.create()
						.texOffs(32, 0)
						.addBox(-4.0F, 0.0F, -1.0F, 1, 7, 1)
						.addBox(3.0F, 0.0F, -1.0F, 1, 7, 1),
				PartPose.offsetAndRotation(0.0F, 17.0F, -4.0F, -1.2217F, 0.0F, 0.0F));

		horns.addOrReplaceChild("horns2",
				CubeListBuilder.create()
						.texOffs(32, 0)
						.addBox(-4.0F, 0.0F, -1.0F, 1, 4, 1)
						.addBox(3.0F, 0.0F, -1.0F, 1, 4, 1),
				PartPose.offsetAndRotation(0.0F, 7.0F, 0.0F, -0.8727F, 0.0F, 0.0F));

		// bone
		PartDefinition bone = head.addOrReplaceChild("bone",
				CubeListBuilder.create()
						.texOffs(10, 4)
						.addBox(-2.0F, -0.342F, 0.0603F, 4, 5, 4),
				PartPose.offsetAndRotation(0.0F, 14.0F, -6.0F, 0.3491F, 0.0F, 0.0F));

		PartDefinition bone2 = bone.addOrReplaceChild("bone2",
				CubeListBuilder.create()
						.texOffs(14, 5)
						.addBox(-1.0F, -0.0405F, 0.7704F, 2, 5, 3),
				PartPose.offsetAndRotation(0.0F, 5.0F, 0.0F, 0.3491F, 0.0F, 0.0F));

		bone2.addOrReplaceChild("bone3",
				CubeListBuilder.create()
						.texOffs(15, 6)
						.addBox(-1.0F, -0.081F, 0.0408F, 2, 7, 2),
				PartPose.offsetAndRotation(0.0F, 5.0F, 1.0F, 0.8727F, 0.0F, 0.0F));

		// feet
		root.addOrReplaceChild("foot1",
				CubeListBuilder.create()
						.texOffs(48, 49)
						.addBox(-2.0F, 0.0F, -2.0F, 4, 11, 4),
				PartPose.offset(-3.0F, 13.0F, 8.0F));

		root.addOrReplaceChild("foot2",
				CubeListBuilder.create()
						.texOffs(48, 49)
						.addBox(-2.0F, 0.0F, -2.0F, 4, 11, 4),
				PartPose.offset(3.0F, 13.0F, 8.0F));

		root.addOrReplaceChild("foot3",
				CubeListBuilder.create()
						.texOffs(48, 49)
						.addBox(-2.0F, 0.0F, -2.0F, 4, 11, 4),
				PartPose.offset(3.0F, 13.0F, -6.0F));

		root.addOrReplaceChild("foot4",
				CubeListBuilder.create()
						.texOffs(48, 49)
						.addBox(-2.0F, 0.0F, -2.0F, 4, 11, 4),
				PartPose.offset(-3.0F, 13.0F, -6.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void setupAnim(@Nonnull MammothEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.setRotationAngles(limbSwing, limbSwingAmount, netHeadYaw, headPitch);
	}

	public void setRotationAngle(@Nonnull ModelPart ModelRenderer, float x, float y, float z) {
		ModelRenderer.xRot = x;
		ModelRenderer.yRot = y;
		ModelRenderer.zRot = z;
	}

	public void setRotationAngles(float limbSwing, float limbSwingAmount, float netHeadYaw, float headPitch) {
		this.head.xRot = headPitch * ((float)Math.PI / 180F) - 0.7854F;
		this.head.yRot = netHeadYaw * ((float)Math.PI / 180F);
		this.foot1.xRot = Mth.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
		this.foot2.xRot = Mth.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
		this.foot3.xRot = Mth.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
		this.foot4.xRot = Mth.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
	}
}