package com.yanny.age.stone.client.models;

import com.google.common.collect.ImmutableList;
import com.yanny.age.stone.Reference;
import com.yanny.age.stone.entities.MouflonEntity;
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
public class MouflonModel extends AgeableListModel<MouflonEntity> {
	public static final ModelLayerLocation MOUFLON_LAYER = new ModelLayerLocation(
			new ResourceLocation(Reference.MODID,"mouflon_layer"),"main");
    private final ModelPart body;
    private final ModelPart head;
    private final ModelPart foot1;
    private final ModelPart foot3;
    private final ModelPart foot4;
    private final ModelPart foot2;


    public MouflonModel(ModelPart part) {
		this.body = part.getChild("body");
		this.head = part.getChild("head");
		this.foot1 = part.getChild("foot1");
		this.foot2 = part.getChild("foot2");
		this.foot3 = part.getChild("foot3");
		this.foot4 = part.getChild("foot4");
        /*texWidth = 64;
        texHeight = 64;

        body = new ModelPart(this);
        body.setPos(0.0F, 13.0F, 0.0F);
        body.texOffs(8, 6).addBox(-5.0F, -7.0F, 4.0F, 10.0F, 8.0F, 6.0F, 0.0F, false);
        body.texOffs(9, 20).addBox(-4.5F, -6.5F, -2.0F, 9.0F, 7.0F, 6.0F, 0.0F, false);
        body.texOffs(9, 36).addBox(-5.0F, -7.0F, -8.0F, 10.0F, 8.0F, 6.0F, 0.0F, false);

        body4_r1 = new ModelPart(this);
        body4_r1.setPos(0.0F, -4.5F, -8.5F);
        body.addChild(body4_r1);
        setRotationAngle(body4_r1, 0.1309F, 0.0F, 0.0F);
        body4_r1.texOffs(44, 50).addBox(-4.0F, -2.5F, -0.5F, 8.0F, 5.0F, 2.0F, 0.0F, false);

        tail_r1 = new ModelPart(this);
        tail_r1.setPos(0.0F, -3.5F, 11.5F);
        body.addChild(tail_r1);
        setRotationAngle(tail_r1, 0.5672F, 0.0F, 0.0F);
        tail_r1.texOffs(0, 20).addBox(-1.0F, -1.5F, -1.5F, 2.0F, 3.0F, 1.0F, 0.0F, false);

        head = new ModelPart(this);
        head.setPos(0.0F, 7.0F, -8.0F);
        head.texOffs(40, 0).addBox(-3.0F, -7.0F, -5.0F, 6.0F, 5.0F, 6.0F, 0.0F, false);
        head.texOffs(43, 12).addBox(-2.0F, -5.5F, -8.0F, 4.0F, 3.0F, 3.0F, 0.0F, false);

        neck_r1 = new ModelPart(this);
        neck_r1.setPos(0.0F, -1.0F, 0.5F);
        head.addChild(neck_r1);
        setRotationAngle(neck_r1, 0.3491F, 0.0F, 0.0F);
        neck_r1.texOffs(28, 51).addBox(-2.5F, -3.0F, -2.5F, 5.0F, 6.0F, 4.0F, 0.0F, false);

        ear2_r1 = new ModelPart(this);
        ear2_r1.setPos(4.0F, -6.5F, -2.5F);
        head.addChild(ear2_r1);
        setRotationAngle(ear2_r1, 0.0F, 0.0F, 0.2182F);
        ear2_r1.texOffs(55, 20).addBox(-1.0F, 0.0F, -0.5F, 2.0F, 2.0F, 1.0F, 0.0F, false);

        ear1_r1 = new ModelPart(this);
        ear1_r1.setPos(-4.0F, -6.5F, -2.5F);
        head.addChild(ear1_r1);
        setRotationAngle(ear1_r1, 0.0F, 0.0F, -0.2182F);
        ear1_r1.texOffs(55, 20).addBox(-1.0F, 0.0F, -0.5F, 2.0F, 2.0F, 1.0F, 0.0F, false);

        head3_r1 = new ModelPart(this);
        head3_r1.setPos(0.0F, -6.0F, -6.5F);
        head.addChild(head3_r1);
        setRotationAngle(head3_r1, 0.2618F, 0.0F, 0.0F);
        head3_r1.texOffs(44, 19).addBox(-1.0F, 0.0F, -2.0F, 2.0F, 2.0F, 5.0F, 0.0F, false);

        horn = new ModelPart(this);
        horn.setPos(0.0F, -3.0F, -2.0F);
        head.addChild(horn);


        horn3_r1 = new ModelPart(this);
        horn3_r1.setPos(0.0F, -1.5F, 1.0F);
        horn.addChild(horn3_r1);
        setRotationAngle(horn3_r1, -0.6109F, 0.2269F, -0.1047F);
        horn3_r1.texOffs(10, 0).addBox(1.0F, -4.5F, -2.0F, 2.0F, 3.0F, 1.0F, 0.0F, false);

        horn4_r1 = new ModelPart(this);
        horn4_r1.setPos(2.0F, -5.5F, 2.0F);
        horn.addChild(horn4_r1);
        setRotationAngle(horn4_r1, -0.5236F, 0.3665F, 0.3578F);
        horn4_r1.texOffs(10, 0).addBox(0.0F, -3.5F, -1.0F, 1.0F, 3.0F, 1.0F, 0.0F, false);

        horn2_r1 = new ModelPart(this);
        horn2_r1.setPos(-2.0F, -7.5F, 1.0F);
        horn.addChild(horn2_r1);
        setRotationAngle(horn2_r1, -0.5236F, -0.4363F, -0.3752F);
        horn2_r1.texOffs(10, 0).addBox(-1.0F, -2.5F, 1.0F, 1.0F, 3.0F, 1.0F, 0.0F, false);

        horn1_r1 = new ModelPart(this);
        horn1_r1.setPos(0.0F, -1.5F, 1.0F);
        horn.addChild(horn1_r1);
        setRotationAngle(horn1_r1, -0.6109F, -0.2269F, 0.1047F);
        horn1_r1.texOffs(10, 0).addBox(-3.0F, -4.5F, -2.0F, 2.0F, 3.0F, 1.0F, 0.0F, false);

        feet1 = new ModelPart(this);
        feet1.setPos(2.0F, 14.0F, -5.0F);
        feet1.texOffs(0, 0).addBox(0.0F, -1.0F, 0.0F, 2.0F, 11.0F, 2.0F, 0.0F, false);

        feet3 = new ModelPart(this);
        feet3.setPos(-2.0F, 14.0F, 6.0F);
        feet3.texOffs(56, 40).addBox(-2.0F, 3.0F, 2.5F, 2.0F, 7.0F, 2.0F, 0.0F, false);

        feet3_2_r1 = new ModelPart(this);
        feet3_2_r1.setPos(0.0F, 3.0F, 3.0F);
        feet3.addChild(feet3_2_r1);
        setRotationAngle(feet3_2_r1, 0.2618F, 0.0F, 0.0F);
        feet3_2_r1.texOffs(52, 29).addBox(-2.5F, -4.0F, -1.5F, 3.0F, 6.0F, 3.0F, 0.0F, false);

        feet4 = new ModelPart(this);
        feet4.setPos(-2.0F, 14.0F, -5.0F);
        feet4.texOffs(0, 0).addBox(-2.0F, -1.0F, 0.0F, 2.0F, 11.0F, 2.0F, 0.0F, false);

        feet2 = new ModelPart(this);
        feet2.setPos(4.0F, 14.0F, 6.0F);
        feet2.texOffs(56, 40).addBox(-2.0F, 3.0F, 2.5F, 2.0F, 7.0F, 2.0F, 0.0F, false);

        feet3_2_r2 = new ModelPart(this);
        feet3_2_r2.setPos(0.0F, 3.0F, 3.0F);
        feet2.addChild(feet3_2_r2);
        setRotationAngle(feet3_2_r2, 0.2618F, 0.0F, 0.0F);
        feet3_2_r2.texOffs(52, 29).addBox(-2.5F, -4.0F, -1.5F, 3.0F, 6.0F, 3.0F, 0.0F, false);*/
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
		MeshDefinition mesh = new MeshDefinition();
		PartDefinition partDefinitionRoot = mesh.getRoot();

		PartDefinition partDefinitionBody = partDefinitionRoot.addOrReplaceChild("body", CubeListBuilder.create().texOffs(8, 6).addBox(-5.0F, -7.0F, 4.0F, 10.0F, 8.0F, 6.0F)
				.texOffs(9, 20).addBox(-4.5F, -6.5F, -2.0F, 9.0F, 7.0F, 6.0F)
				.texOffs(9, 36).addBox(-5.0F, -7.0F, -8.0F, 10.0F, 8.0F, 6.0F), PartPose.offset(0.0F, 13.0F, 0.0F));
		partDefinitionBody.addOrReplaceChild("body4_r1", CubeListBuilder.create().texOffs(44, 50).addBox(-4.0F, -2.5F, -0.5F, 8.0F, 5.0F, 2.0F), PartPose.offsetAndRotation(0.0F, -4.5F, -8.5F,0.1309F, 0.0F, 0.0F));
		partDefinitionBody.addOrReplaceChild("tail_r1", CubeListBuilder.create().texOffs(0, 20).addBox(-1.0F, -1.5F, -1.5F, 2.0F, 3.0F, 1.0F), PartPose.offsetAndRotation(0.0F, -3.5F, 11.5F,0.5672F, 0.0F, 0.0F));

		PartDefinition partDefinitionHead = partDefinitionRoot.addOrReplaceChild("head", CubeListBuilder.create().texOffs(40, 0).addBox(-3.0F, -7.0F, -5.0F, 6.0F, 5.0F, 6.0F)
				.texOffs(43, 12).addBox(-2.0F, -5.5F, -8.0F, 4.0F, 3.0F, 3.0F), PartPose.offsetAndRotation(0.0F, 7.0F, -8.0F,0.3491F, 0.0F, 0.0F));
		partDefinitionHead.addOrReplaceChild("neck_r1", CubeListBuilder.create().texOffs(28, 51).addBox(-2.5F, -3.0F, -2.5F, 5.0F, 6.0F, 4.0F), PartPose.offset(0.0F, -1.0F, 0.5F));
		partDefinitionHead.addOrReplaceChild("ear2_r1", CubeListBuilder.create().texOffs(55, 20).addBox(-1.0F, 0.0F, -0.5F, 2.0F, 2.0F, 1.0F),PartPose.offsetAndRotation(4.0F, -6.5F, -2.5F,0.0F, 0.0F, 0.2182F));
		partDefinitionHead.addOrReplaceChild("ear1_r1", CubeListBuilder.create().texOffs(55, 20).addBox(-1.0F, 0.0F, -0.5F, 2.0F, 2.0F, 1.0F),PartPose.offsetAndRotation(-4.0F, -6.5F, -2.5F,0.0F, 0.0F, -0.2182F));
		partDefinitionHead.addOrReplaceChild("head3_r1", CubeListBuilder.create().texOffs(44, 19).addBox(-1.0F, 0.0F, -2.0F, 2.0F, 2.0F, 5.0F),PartPose.offsetAndRotation(0.0F, -6.0F, -6.5F,0.2618F, 0.0F, 0.0F));

		PartDefinition partDefinitionHorn = partDefinitionHead.addOrReplaceChild("horn", CubeListBuilder.create(),PartPose.offset(0.0F, -3.0F, -2.0F));
		partDefinitionHorn.addOrReplaceChild("horn3_r1", CubeListBuilder.create().texOffs(10, 0).addBox(1.0F, -4.5F, -2.0F, 2.0F, 3.0F, 1.0F),PartPose.offsetAndRotation(0.0F, -1.5F, 1.0F,-0.6109F, 0.2269F, -0.1047F));
		partDefinitionHorn.addOrReplaceChild("horn4_r1", CubeListBuilder.create().texOffs(10, 0).addBox(0.0F, -3.5F, -1.0F, 1.0F, 3.0F, 1.0F),PartPose.offsetAndRotation(2.0F, -5.5F, 2.0F,-0.5236F, 0.3665F, 0.3578F));
		partDefinitionHorn.addOrReplaceChild("horn2_r1", CubeListBuilder.create().texOffs(10, 0).addBox(-1.0F, -2.5F, 1.0F, 1.0F, 3.0F, 1.0F),PartPose.offsetAndRotation(-2.0F, -7.5F, 1.0F,-0.5236F, -0.4363F, -0.3752F));
		partDefinitionHorn.addOrReplaceChild("horn1_r1", CubeListBuilder.create().texOffs(10, 0).addBox(-3.0F, -4.5F, -2.0F, 2.0F, 3.0F, 1.0F),PartPose.offsetAndRotation(0.0F, -1.5F, 1.0F,-0.6109F, -0.2269F, 0.1047F));


		partDefinitionRoot.addOrReplaceChild("foot1", CubeListBuilder.create().texOffs(0, 0).addBox(0.0F, -1.0F, 0.0F, 2.0F, 11.0F, 2.0F), PartPose.offset(2.0F, 14.0F, -5.0F));

		PartDefinition partDefinitionFoot2 = partDefinitionRoot.addOrReplaceChild("foot2", CubeListBuilder.create().texOffs(56, 40).addBox(-2.0F, 3.0F, 2.5F, 2.0F, 7.0F, 2.0F), PartPose.offset(4.0F, 14.0F, 6.0F));
		partDefinitionFoot2.addOrReplaceChild("feet2_2_r2", CubeListBuilder.create().texOffs(52, 29).addBox(-2.5F, -4.0F, -1.5F, 3.0F, 6.0F, 3.0F), PartPose.offsetAndRotation(0.0F, 3.0F, 3.0F,0.2618F, 0.0F, 0.0F));

		PartDefinition partDefinitionFoot3 = partDefinitionRoot.addOrReplaceChild("foot3", CubeListBuilder.create().texOffs(56, 40).addBox(-2.0F, 3.0F, 2.5F, 2.0F, 7.0F, 2.0F), PartPose.offset(-2.0F, 14.0F, 6.0F));
		partDefinitionFoot3.addOrReplaceChild("feet3_2_r1", CubeListBuilder.create().texOffs(52, 29).addBox(-2.5F, -4.0F, -1.5F, 3.0F, 6.0F, 3.0F), PartPose.offsetAndRotation(0.0F, 3.0F, 3.0F,0.2618F, 0.0F, 0.0F));

		partDefinitionRoot.addOrReplaceChild("foot4", CubeListBuilder.create().texOffs(0, 0).addBox(-2.0F, -1.0F, 0.0F, 2.0F, 11.0F, 2.0F), PartPose.offset(-2.0F, 14.0F, -5.0F));
		return LayerDefinition.create(mesh, 64, 64);
	}
	@Override
	public void setupAnim(@Nonnull MouflonEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.setRotationAngles(limbSwing, limbSwingAmount, netHeadYaw, headPitch);
	}

	public void setRotationAngles(float limbSwing, float limbSwingAmount, float netHeadYaw, float headPitch) {
		this.head.xRot = headPitch * ((float) Math.PI / 180F);
		this.head.yRot = netHeadYaw * ((float) Math.PI / 180F);
		this.foot1.xRot = Mth.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
		this.foot2.xRot = Mth.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F * limbSwingAmount;
		this.foot3.xRot = Mth.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
		this.foot4.xRot = Mth.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F * limbSwingAmount;
	}

	public void setRotationAngle(ModelPart modelRenderer, float x, float y, float z) {
		modelRenderer.xRot = x;
		modelRenderer.yRot = y;
		modelRenderer.zRot = z;
	}
}