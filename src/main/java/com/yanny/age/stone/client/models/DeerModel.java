package com.yanny.age.stone.client.models;

import com.google.common.collect.ImmutableList;
import com.yanny.age.stone.entities.DeerEntity;
import net.minecraft.client.model.AgeableListModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.util.Mth;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nonnull;

@OnlyIn(Dist.CLIENT)
public class DeerModel extends AgeableListModel<DeerEntity> {
//	private final ModelPart body;
//	private final ModelPart head;
//	private final ModelPart feet1;
//	private final ModelPart feet2;
//	private final ModelPart feet3;
//	private final ModelPart feet4;

	public DeerModel() {
        /*texWidth = 64;
        texHeight = 64;

        body = new ModelPart(this);
        body.setPos(-1.0F, 10.0F, 0.0F);
        body.texOffs(0, 44).addBox(5.5F, -2.0F, 6.0F, 1.0F, 5.0F, 3.0F, 0.0F, false);
        body.texOffs(0, 44).addBox(-4.5F, -2.0F, 6.0F, 1.0F, 5.0F, 3.0F, 0.0F, false);

        ModelPart tail_r1 = new ModelPart(this);
        tail_r1.setPos(1.0F, -3.0F, 11.0F);
        body.addChild(tail_r1);
        setRotationAngle(tail_r1, 0.3054F, 0.0F, 0.0F);
        tail_r1.texOffs(13, 60).addBox(-1.0F, 0.0F, 0.0F, 2.0F, 3.0F, 1.0F, 0.0F, false);

        ModelPart neck1_r1 = new ModelPart(this);
        neck1_r1.setPos(0.5F, -0.5F, -9.0F);
        body.addChild(neck1_r1);
        setRotationAngle(neck1_r1, 0.1745F, 0.0F, 0.0F);
        neck1_r1.texOffs(0, 0).addBox(-2.5F, -6.5F, -1.0F, 6.0F, 7.0F, 6.0F, 0.0F, false);

        ModelPart body3_r1 = new ModelPart(this);
        body3_r1.setPos(1.0F, 1.0F, 2.0F);
        body.addChild(body3_r1);
        setRotationAngle(body3_r1, -0.0087F, 0.0F, 0.0F);
        body3_r1.texOffs(32, 0).addBox(-4.5F, -7.0F, -11.0F, 9.0F, 9.0F, 7.0F, 0.0F, false);

        ModelPart body2_r1 = new ModelPart(this);
        body2_r1.setPos(1.0F, 1.0F, 3.0F);
        body.addChild(body2_r1);
        setRotationAngle(body2_r1, -0.0087F, 0.0F, 0.0F);
        body2_r1.texOffs(34, 33).addBox(-4.0F, -6.5F, -5.0F, 8.0F, 8.0F, 6.0F, 0.0F, false);

        ModelPart body_r1 = new ModelPart(this);
        body_r1.setPos(1.0F, 1.0F, 3.0F);
        body.addChild(body_r1);
        setRotationAngle(body_r1, -0.1396F, 0.0F, 0.0F);
        body_r1.texOffs(30, 16).addBox(-4.5F, -6.0F, 0.0F, 9.0F, 8.0F, 8.0F, 0.0F, false);

        head = new ModelPart(this);
        head.setPos(0.0F, 4.0F, -9.0F);
        setRotationAngle(head, -0.0436F, 0.0F, 0.0F);


        ModelPart neck2_r1 = new ModelPart(this);
        neck2_r1.setPos(-0.5F, 8.4128F, 0.4981F);
        head.addChild(neck2_r1);
        setRotationAngle(neck2_r1, -0.0436F, 0.0F, 0.0F);
        neck2_r1.texOffs(36, 54).addBox(-2.0F, -12.7539F, -2.9962F, 5.0F, 4.0F, 5.0F, 0.0F, false);

        ModelPart mouth2_r1 = new ModelPart(this);
        mouth2_r1.setPos(0.0F, -2.5872F, 0.4981F);
        head.addChild(mouth2_r1);
        setRotationAngle(mouth2_r1, 0.4363F, 0.0F, 0.0F);
        mouth2_r1.texOffs(0, 53).addBox(-2.0F, -6.2304F, -6.1585F, 4.0F, 2.0F, 4.0F, 0.0F, false);

        ModelPart mouth_r1 = new ModelPart(this);
        mouth_r1.setPos(0.0F, -2.5872F, 0.4981F);
        head.addChild(mouth_r1);
        setRotationAngle(mouth_r1, 0.2618F, 0.0F, 0.0F);
        mouth_r1.texOffs(2, 59).addBox(-1.5F, -4.669F, -6.5434F, 3.0F, 2.0F, 3.0F, 0.0F, false);

        ModelPart ear2_r1 = new ModelPart(this);
        ear2_r1.setPos(-3.0F, -8.5872F, 0.4981F);
        head.addChild(ear2_r1);
        setRotationAngle(ear2_r1, -0.0436F, 0.3054F, 0.0F);
        ear2_r1.texOffs(14, 53).addBox(-2.6733F, -1.9074F, 0.8797F, 3.0F, 2.0F, 1.0F, 0.0F, false);

        ModelPart ear_r1 = new ModelPart(this);
        ear_r1.setPos(3.0F, -8.5872F, 0.4981F);
        head.addChild(ear_r1);
        setRotationAngle(ear_r1, -0.0873F, -0.3054F, 0.0F);
        ear_r1.texOffs(14, 53).addBox(-0.3267F, -1.8567F, 0.7976F, 3.0F, 2.0F, 1.0F, 0.0F, false);

        ModelPart head1_r1 = new ModelPart(this);
        head1_r1.setPos(0.0F, -2.5872F, 0.4981F);
        head.addChild(head1_r1);
        setRotationAngle(head1_r1, 0.1309F, 0.0F, 0.0F);
        head1_r1.texOffs(0, 13).addBox(-3.0F, -7.0795F, -3.8219F, 6.0F, 6.0F, 7.0F, 0.0F, false);

        feet1 = new ModelPart(this);
        feet1.setPos(4.0F, 12.0F, -6.0F);
        feet1.texOffs(56, 49).addBox(-1.0F, 6.0F, -1.5F, 2.0F, 6.0F, 2.0F, 0.0F, false);

        ModelPart feetH1_r1 = new ModelPart(this);
        feetH1_r1.setPos(0.5F, 3.0F, -1.0F);
        feet1.addChild(feetH1_r1);
        setRotationAngle(feetH1_r1, -0.2182F, 0.0F, 0.0F);
        feetH1_r1.texOffs(23, 44).addBox(-2.0F, -4.0F, -0.5F, 3.0F, 7.0F, 3.0F, 0.0F, false);

        feet2 = new ModelPart(this);
        feet2.setPos(4.0F, 13.0F, 6.0F);
        feet2.texOffs(56, 49).addBox(-0.5F, 5.0F, 2.0F, 2.0F, 6.0F, 2.0F, 0.0F, false);

        ModelPart feetH2_r1 = new ModelPart(this);
        feetH2_r1.setPos(-0.5F, 4.5F, 2.5F);
        feet2.addChild(feetH2_r1);
        setRotationAngle(feetH2_r1, 0.2618F, 0.0F, 0.0F);
        feetH2_r1.texOffs(23, 54).addBox(-0.5F, -5.5F, -1.5F, 3.0F, 7.0F, 3.0F, 0.0F, false);

        feet3 = new ModelPart(this);
        feet3.setPos(-4.0F, 13.0F, 6.0F);
        feet3.texOffs(56, 49).addBox(-1.5F, 5.0F, 2.0F, 2.0F, 6.0F, 2.0F, 0.0F, false);

        ModelPart feetH3_r1 = new ModelPart(this);
        feetH3_r1.setPos(0.5F, 4.5F, 2.5F);
        feet3.addChild(feetH3_r1);
        setRotationAngle(feetH3_r1, 0.2618F, 0.0F, 0.0F);
        feetH3_r1.texOffs(23, 54).addBox(-2.5F, -5.5F, -1.5F, 3.0F, 7.0F, 3.0F, 0.0F, false);

        feet4 = new ModelPart(this);
        feet4.setPos(-4.0F, 12.0F, -6.0F);
        feet4.texOffs(56, 49).addBox(-1.0F, 6.0F, -1.5F, 2.0F, 6.0F, 2.0F, 0.0F, false);

        ModelPart feetH4_r1 = new ModelPart(this);
        feetH4_r1.setPos(-0.5F, 3.0F, -1.0F);
        feet4.addChild(feetH4_r1);
        setRotationAngle(feetH4_r1, -0.2182F, 0.0F, 0.0F);
        feetH4_r1.texOffs(23, 44).addBox(-1.0F, -4.0F, -0.5F, 3.0F, 7.0F, 3.0F, 0.0F, false);*/
    }

	@Nonnull
	@Override
	protected Iterable<ModelPart> headParts() {
		return ImmutableList.of();
	}

	@Nonnull
	@Override
	protected Iterable<ModelPart> bodyParts() {
		return ImmutableList.of(/*head, body, feet1, feet2, feet3, feet4*/);
	}

	@Override
	public void setupAnim(@Nonnull DeerEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.setRotationAngles(limbSwing, limbSwingAmount, netHeadYaw, headPitch);
    }

    public void setRotationAngles(float limbSwing, float limbSwingAmount, float netHeadYaw, float headPitch) {
//        this.head.xRot = headPitch * ((float) Math.PI / 180F);
//        this.head.yRot = netHeadYaw * ((float) Math.PI / 180F);
//        this.feet1.xRot = Mth.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
//        this.feet2.xRot = Mth.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
//        this.feet3.xRot = Mth.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F * limbSwingAmount;
//        this.feet4.xRot = Mth.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F * limbSwingAmount;
    }

    public void setRotationAngle(ModelPart modelRenderer, float x, float y, float z) {
        modelRenderer.xRot = x;
        modelRenderer.yRot = y;
        modelRenderer.zRot = z;
    }
}