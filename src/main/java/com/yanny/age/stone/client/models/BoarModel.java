package com.yanny.age.stone.client.models;

import com.google.common.collect.ImmutableList;
import com.yanny.age.stone.Reference;
import com.yanny.age.stone.entities.BoarEntity;
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
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nonnull;

@OnlyIn(Dist.CLIENT)
public class BoarModel extends AgeableListModel<BoarEntity> {
	public static final ModelLayerLocation BOAR_LAYER = new ModelLayerLocation(
			new ResourceLocation(Reference.MODID,"boar_layer"),"main");
	private final ModelPart body;
	private final ModelPart head;
	private final ModelPart foot1;
	private final ModelPart foot2;
	private final ModelPart foot3;
	private final ModelPart foot4;

	public BoarModel(ModelPart part) {
		this.body = part.getChild("body");
		this.head = part.getChild("head");
		this.foot1 = part.getChild("foot1");
		this.foot2 = part.getChild("foot2");
		this.foot3 = part.getChild("foot3");
		this.foot4 = part.getChild("foot4");
		/*texWidth = 64;
		texHeight = 64;

		body = new ModelPart(this, 0, 16);
		body.setPos(0.0F, 14.0F, 0.0F);
		body.addBox(-5.0F, -4.0F, -8.0F, 10, 8, 16, 0.0F, false);

		foot1 = new ModelPart(this, 36, 14);
		foot1.setPos(3.0F, 18.0F, 6.0F);
		foot1.addBox(-2.0F, 0.0F, -2.0F, 4, 6, 4, 0.0F, false);

		foot2 = new ModelPart(this, 36, 14);
		foot2.setPos(-3.0F, 18.0F, 6.0F);
		foot2.addBox(-2.0F, 0.0F, -2.0F, 4, 6, 4, 0.0F, false);

		foot3 = new ModelPart(this, 36, 14);
		foot3.setPos(-3.0F, 18.0F, -6.0F);
		foot3.addBox(-2.0F, 0.0F, -2.0F, 4, 6, 4, 0.0F, false);

		foot4 = new ModelPart(this, 36, 14);
		foot4.setPos(3.0F, 18.0F, -6.0F);
		foot4.addBox(-2.0F, 0.0F, -2.0F, 4, 6, 4, 0.0F, false);

		head = new ModelPart(this, 2, 0);
		head.setPos(0.0F, 12.0F, -8.0F);
		head.addBox(-4.0F, -4.0F, -6.0F, 8, 8, 8, 0.0F, false);

		ModelPart head2 = new ModelPart(this, 54, 28);
		head2.addBox(-2.0F, 0.0F, -7.0F, 4, 3, 1, 0.0F, false);
		head.addChild(head2);

		ModelPart bone = new ModelPart(this, 0, 0);
		bone.setPos(0.0F, 2.0F, -7.0F);
		bone.xRot = -0.6109f;
		bone.addBox( 2.0F, 0.0F, -2.0F, 1, 1, 4, 0.0F, false);
		bone.addBox(-3.0F, 0.0F, -2.0F, 1, 1, 4, 0.0F, false);
		head.addChild(bone);*/
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
	public void setupAnim(@Nonnull BoarEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.setRotationAngles(limbSwing, limbSwingAmount, netHeadYaw, headPitch);
	}
	public static LayerDefinition createBodyLayer() {
		MeshDefinition mesh = new MeshDefinition();
		PartDefinition partDefinitionRoot = mesh.getRoot();

		partDefinitionRoot.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 16).addBox(-5.0F, -4.0F, -8.0F, 10, 8, 16), PartPose.offset(0.0F, 14.0F, 0.0F));

		PartDefinition partDefinition1 = partDefinitionRoot.addOrReplaceChild("head", CubeListBuilder.create().texOffs(2, 0).addBox(-4.0F, -4.0F, -6.0F, 8, 8, 8), PartPose.offset(0.0F, 12.0F, -8.0F));
		partDefinition1.addOrReplaceChild("head2", CubeListBuilder.create().texOffs(54, 28).addBox(-2.0F, 0.0F, -7.0F, 4, 3, 1), PartPose.ZERO);
		partDefinition1.addOrReplaceChild("bone", CubeListBuilder.create().texOffs(0, 0)
				.addBox(2.0F, 0.0F, -2.0F, 1, 1, 4)
				.addBox(-3.0F, 0.0F, -2.0F, 1, 1, 4), PartPose.offsetAndRotation(0.0F, 2.0F, -7.0F,-0.6109F,0F,0F));


		CubeListBuilder foot = CubeListBuilder.create().texOffs(36, 14).addBox(-2.0F, 0.0F, -2.0F, 4, 6, 4);
		partDefinitionRoot.addOrReplaceChild("foot1", foot, PartPose.offset(3.0F, 18.0F, 6.0F));
		partDefinitionRoot.addOrReplaceChild("foot2", foot, PartPose.offset(-3.0F, 18.0F, 6.0F));
		partDefinitionRoot.addOrReplaceChild("foot3", foot, PartPose.offset(-3.0F, 18.0F, -6.0F));
		partDefinitionRoot.addOrReplaceChild("foot4", foot, PartPose.offset(3.0F, 18.0F, -6.0F));
		return LayerDefinition.create(mesh, 64, 64);
	}

	public void setRotationAngles(float limbSwing, float limbSwingAmount, float netHeadYaw, float headPitch) {
		this.head.xRot = headPitch * ((float)Math.PI / 180F);
		this.head.yRot = netHeadYaw * ((float)Math.PI / 180F);
		this.foot1.xRot = Mth.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
		this.foot2.xRot = Mth.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
		this.foot3.xRot = Mth.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
		this.foot4.xRot = Mth.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
	}
}