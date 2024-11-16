package com.yanny.age.stone.client.models;

import com.google.common.collect.ImmutableList;
import com.yanny.age.stone.Reference;
import com.yanny.age.stone.entities.AurochEntity;
import net.minecraft.client.model.AgeableListModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nonnull;

@OnlyIn(Dist.CLIENT)
public class AurochModel extends AgeableListModel<AurochEntity> {
	public static final ModelLayerLocation AUROCH_LAYER = new ModelLayerLocation(
			new ResourceLocation(Reference.MODID,"auroch_layer"),"main");
	private final ModelPart body;
	private final ModelPart head;
//	private final ModelPart horns;
	private final ModelPart foot1;
	private final ModelPart foot2;
	private final ModelPart foot3;
	private final ModelPart foot4;

	public AurochModel(ModelPart part){
		this.head = part.getChild("head");
		this.body = part.getChild("body");
//		this.horns = part.getChild("horns");
//		this.horns = this.head.getChild("horns");
		this.foot1 = part.getChild("foot1");
		this.foot2 = part.getChild("foot2");
		this.foot3 = part.getChild("foot3");
		this.foot4 = part.getChild("foot4");
/*		this.foot1 = part.getChild("foot1");
		this.foot2 = part.getChild("foot2");
		this.foot3 = part.getChild("foot3");
		this.foot4 = part.getChild("foot4");*/
/*		texWidth = 64;
		texHeight = 64;

		body = new ModelPart(this, 0, 36);
		body.setPos(0.0F, 5.0F, 0.0F);
		body.addBox(-6.0F, -4.0F, -9.0F, 12, 10, 18, 0.0F, false);

		head = new ModelPart(this, 32, 0);
		head.setPos(0.0F, 3.0F, -7.0F);
		head.addBox(,-4.0F, -4.0F, -8.0F, 8, 8 8, 0.0F, false);

		ModelPart horns = new ModelPart(this, 0, 30);
		horns.setPos(0.0F, 0.0F, -4.0F);
		horns.xRot = 0.6981F;
		head.addChild(horns);
		horns.addBox(4.0F, -1.0F, -1.0F, 3, 1, 1, 0.0F, false);
		horns.addBox(-7.0F, -1.0F, -1.0F, 3, 1, 1, 0.0F, false);
		horns.addBox(6.0F, -3.0F, -1.0F, 1, 2, 1, 0.0F, false);
		horns.addBox(-7.0F, -3.0F, -1.0F, 1, 2, 1, 0.0F, false);

		foot1 = new ModelPart(this, 0, 0);
		foot1.setPos(-3.0F, 11.0F, 6.0F);
		foot1.addBox(-2.0F, 0.0F, -2.0F, 4, 13, 4, 0.0F, false);

		foot2 = new ModelPart(this, 0, 0);
		foot2.setPos(3.0F, 11.0F, 6.0F);
		foot2.addBox(-2.0F, 0.0F, -2.0F, 4, 13, 4, 0.0F, false);

		foot3 = new ModelPart(this, 0, 0);
		foot3.setPos(3.0F, 11.0F, -6.0F);
		foot3.addBox(-2.0F, 0.0F, -2.0F, 4, 13, 4, 0.0F, false);

		foot4 = new ModelPart(this, 0, 0);
		foot4.setPos(-3.0F, 11.0F, -6.0F);
		foot4.addBox(-2.0F, 0.0F, -2.0F, 4, 13, 4, 0.0F, false);*/
	}
	public static LayerDefinition createBodyLayer() {
		MeshDefinition mesh = new MeshDefinition();
		PartDefinition partDefinitionRoot = mesh.getRoot();

		partDefinitionRoot.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 36).addBox(-6.0F, -4.0F, -9.0F, 12, 10, 18), PartPose.offset(0.0F, 5.0F, 0.0F));
		PartDefinition partdefinition1 = partDefinitionRoot.addOrReplaceChild("head", CubeListBuilder.create().texOffs(32, 0).addBox(-4.0F, -4.0F, -8.0F, 8, 8, 8), PartPose.offset(0.0F, 3.0F, -7.0F));
		partdefinition1.addOrReplaceChild("horns", CubeListBuilder.create().texOffs(0, 30)
				.addBox(4.0F, -1.0F, -1.0F, 3, 1, 1)
				.addBox(-7.0F, -1.0F, -1.0F, 3, 1, 1)
				.addBox(6.0F, -3.0F, -1.0F, 1, 2, 1)
				.addBox(-7.0F, -3.0F, -1.0F, 1, 2, 1), PartPose.offsetAndRotation(0.0F, 0.0F, -4.0F,0.6981F,0F,0F));


		CubeListBuilder foot = CubeListBuilder.create().texOffs(0, 0).addBox(-2.0F, 0.0F, -2.0F, 4, 13, 4);
		partDefinitionRoot.addOrReplaceChild("foot1", foot, PartPose.offset(-3.0F, 11.0F, 6.0F));
		partDefinitionRoot.addOrReplaceChild("foot2", foot, PartPose.offset(3.0F, 11.0F, 6.0F));
		partDefinitionRoot.addOrReplaceChild("foot3", foot, PartPose.offset(3.0F, 11.0F, -6.0F));
		partDefinitionRoot.addOrReplaceChild("foot4", foot, PartPose.offset(-3.0F, 11.0F, -6.0F));
		//size
		return LayerDefinition.create(mesh, 64, 64);
	}
	@Nonnull
	@Override
	protected Iterable<ModelPart> headParts() {
		return ImmutableList.of();
	}

	@Nonnull
	@Override
	protected Iterable<ModelPart> bodyParts() {
		return ImmutableList.of(head/*horns*/,body,foot1,foot2,foot3,foot4);
	}

	@Override
	public void setupAnim(@Nonnull AurochEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.setRotationAngles(limbSwing, limbSwingAmount, netHeadYaw, headPitch);
	}

	public void setRotationAngles(float limbSwing, float limbSwingAmount, float netHeadYaw, float headPitch) {
		this.head.xRot = headPitch * ((float)Math.PI / 180F);
		this.head.yRot = netHeadYaw * ((float)Math.PI / 180F);
//		this.horns.xRot = headPitch * ((float)Math.PI / 180F);
//		this.horns.yRot = netHeadYaw * ((float)Math.PI / 180F);

		this.foot1.xRot = Mth.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
		this.foot2.xRot = Mth.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
		this.foot3.xRot = Mth.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
		this.foot4.xRot = Mth.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
	}
}