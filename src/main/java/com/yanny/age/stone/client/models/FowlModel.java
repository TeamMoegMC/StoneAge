package com.yanny.age.stone.client.models;

import com.google.common.collect.ImmutableList;
import com.yanny.age.stone.Reference;
import com.yanny.age.stone.entities.FowlEntity;
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
public class FowlModel extends AgeableListModel<FowlEntity> {
	public static final ModelLayerLocation FOWL_LAYER = new ModelLayerLocation(
			new ResourceLocation(Reference.MODID,"fowl_layer"),"main");
	private final ModelPart body;
	private final ModelPart head;
	private final ModelPart foot1;
	private final ModelPart foot2;
	private final ModelPart wing1;
	private final ModelPart wing2;

	public FowlModel(ModelPart part) {
		this.body = part.getChild("body");
		this.head = part.getChild("head");
		this.foot1 = part.getChild("foot1");
		this.foot2 = part.getChild("foot2");
		this.wing1 = part.getChild("wing1");
		this.wing2 = part.getChild("wing2");
/*		texWidth = 64;
		texHeight = 64;

		body = new ModelPart(this, 36, 0);
		body.setPos(0.0F, 17.0F, 0.0F);
		body.addBox(-3.0F, -3.0F, -4.0F, 6, 6, 8, 0.0F, false);

		ModelPart body1 = new ModelPart(this, 52, 14);
		body1.addBox(-1.0F, -5.0F, 3.0F, 2, 5, 4, 0.0F, false);
		body.addChild(body1);

		head = new ModelPart(this, 22, 0);
		head.setPos(0.0F, 14.0F, -4.0F);
		head.addBox(-2.0F, -5.0F, -2.0F, 4, 7, 3, 0.0F, false);

		ModelPart head1 = new ModelPart(this, 36, 5);
		head1.addBox(-1.0F, -3.0F, -4.0F, 2, 1, 2, 0.0F, false);
		head.addChild(head1);

		foot1 = new ModelPart(this, 60, 0);
		foot1.setPos(-1.0F, 20.0F, 0.0F);
		foot1.addBox(-1.0F, -1.0F, 0.0F, 1, 5, 1, 0.0F, false);

		ModelPart foot11 = new ModelPart(this, 24, 11);
		foot11.addBox(-2.0F, 4.0F, -2.0F, 3, 0, 3, 0.0F, false);
		foot1.addChild(foot11);

		foot2 = new ModelPart(this, 60, 0);
		foot2.setPos(1.0F, 20.0F, 0.0F);
		foot2.addBox(0.0F, -1.0F, 0.0F, 1, 5, 1, 0.0F, false);

		ModelPart foot21 = new ModelPart(this, 24, 11);
		foot21.addBox(-1.0F, 4.0F, -2.0F, 3, 0, 3, 0.0F, false);
		foot2.addChild(foot21);

		wing1 = new ModelPart(this, 0, 0);
		wing1.setPos(3.0F, 15.0F, 0.0F);
		wing1.addBox(0.0F, 0.0F, -3.0F, 1, 4, 5, 0.0F, false);

		wing2 = new ModelPart(this);
		wing2.setPos(-3.0F, 15.0F, 0.0F);
		wing2.addBox(-1.0F, 0.0F, -3.0F, 1, 4, 5, 0.0F, false);*/
	}

	@Override
	public void setupAnim(@Nonnull FowlEntity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
	}

	@Nonnull
	@Override
	protected Iterable<ModelPart> headParts() {
		return ImmutableList.of();
	}

	@Nonnull
	@Override
	protected Iterable<ModelPart> bodyParts() {
		return ImmutableList.of(head, body, foot1, foot2, wing1, wing2);
	}
	public static LayerDefinition createBodyLayer() {
		MeshDefinition mesh = new MeshDefinition();
		PartDefinition partDefinitionRoot = mesh.getRoot();

		PartDefinition partDefinition1 = partDefinitionRoot.addOrReplaceChild("body", CubeListBuilder.create().texOffs(36, 0).addBox(-3.0F, -3.0F, -4.0F, 6, 6, 8), PartPose.offset(0.0F, 17.0F, 0.0F));
		partDefinition1.addOrReplaceChild("body1", CubeListBuilder.create().texOffs(52, 14).addBox(-1.0F, -5.0F, 3.0F, 2, 5, 4), PartPose.ZERO);

		PartDefinition partDefinition2 = partDefinitionRoot.addOrReplaceChild("head", CubeListBuilder.create().texOffs(22, 0).addBox(-2.0F, -5.0F, -2.0F, 4, 7, 3), PartPose.offset(0.0F, 14.0F, -4.0F));
		partDefinition2.addOrReplaceChild("head1", CubeListBuilder.create().texOffs(36, 5).addBox(-1.0F, -3.0F, -4.0F, 2, 1, 2), PartPose.ZERO);

		CubeListBuilder foot = CubeListBuilder.create().texOffs(60, 0).addBox(-1.0F, -1.0F, 0.0F, 1, 5, 1);
		PartDefinition partDefinitionfoot1= partDefinitionRoot.addOrReplaceChild("foot1", foot, PartPose.offset(-1.0F, 20.0F, 0.0F));
		partDefinitionfoot1.addOrReplaceChild("foot11", CubeListBuilder.create().texOffs(24, 11).addBox(-2.0F, 4.0F, -2.0F, 3, 0, 3), PartPose.ZERO);

		PartDefinition partDefinitionfoot2= partDefinitionRoot.addOrReplaceChild("foot2", foot, PartPose.offset(1.0F, 20.0F, 0.0F));
		partDefinitionfoot2.addOrReplaceChild("foot21", CubeListBuilder.create().texOffs(24, 11).addBox(-2.0F, 4.0F, -2.0F, 3, 0, 3), PartPose.ZERO);

		partDefinitionRoot.addOrReplaceChild("wing1", CubeListBuilder.create().texOffs(0, 0).addBox(0.0F, 0.0F, -3.0F, 1, 4, 5), PartPose.offset(3.0F, 15.0F, 0.0F));
		partDefinitionRoot.addOrReplaceChild("wing2", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, 0.0F, -3.0F, 1, 4, 5), PartPose.offset(-3.0F, 18.0F, -6.0F));
		return LayerDefinition.create(mesh, 64, 64);
	}

	public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.head.xRot = headPitch * ((float)Math.PI / 180F);
		this.head.yRot = netHeadYaw * ((float)Math.PI / 180F);
		this.foot1.xRot = Mth.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
		this.foot2.xRot = Mth.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
		this.wing1.zRot = -ageInTicks;
		this.wing2.zRot = ageInTicks;
	}
}