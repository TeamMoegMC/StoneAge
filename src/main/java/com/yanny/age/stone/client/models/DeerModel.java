package com.yanny.age.stone.client.models;

import com.google.common.collect.ImmutableList;
import com.yanny.age.stone.entities.DeerEntity;
import net.minecraft.client.renderer.entity.model.AgeableModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nonnull;

@OnlyIn(Dist.CLIENT)
public class DeerModel extends AgeableModel<DeerEntity> {
	private final ModelRenderer body;
	private final ModelRenderer head;
	private final ModelRenderer feet1;
	private final ModelRenderer feet2;
	private final ModelRenderer feet3;
	private final ModelRenderer feet4;

	public DeerModel() {
        textureWidth = 64;
        textureHeight = 64;

        body = new ModelRenderer(this);
        body.setRotationPoint(-1.0F, 10.0F, 0.0F);
        body.setTextureOffset(0, 44).addBox(5.5F, -2.0F, 6.0F, 1.0F, 5.0F, 3.0F, 0.0F, false);
        body.setTextureOffset(0, 44).addBox(-4.5F, -2.0F, 6.0F, 1.0F, 5.0F, 3.0F, 0.0F, false);

        ModelRenderer tail_r1 = new ModelRenderer(this);
        tail_r1.setRotationPoint(1.0F, -3.0F, 11.0F);
        body.addChild(tail_r1);
        setRotationAngle(tail_r1, 0.3054F, 0.0F, 0.0F);
        tail_r1.setTextureOffset(13, 60).addBox(-1.0F, 0.0F, 0.0F, 2.0F, 3.0F, 1.0F, 0.0F, false);

        ModelRenderer neck1_r1 = new ModelRenderer(this);
        neck1_r1.setRotationPoint(0.5F, -0.5F, -9.0F);
        body.addChild(neck1_r1);
        setRotationAngle(neck1_r1, 0.1745F, 0.0F, 0.0F);
        neck1_r1.setTextureOffset(0, 0).addBox(-2.5F, -6.5F, -1.0F, 6.0F, 7.0F, 6.0F, 0.0F, false);

        ModelRenderer body3_r1 = new ModelRenderer(this);
        body3_r1.setRotationPoint(1.0F, 1.0F, 2.0F);
        body.addChild(body3_r1);
        setRotationAngle(body3_r1, -0.0087F, 0.0F, 0.0F);
        body3_r1.setTextureOffset(32, 0).addBox(-4.5F, -7.0F, -11.0F, 9.0F, 9.0F, 7.0F, 0.0F, false);

        ModelRenderer body2_r1 = new ModelRenderer(this);
        body2_r1.setRotationPoint(1.0F, 1.0F, 3.0F);
        body.addChild(body2_r1);
        setRotationAngle(body2_r1, -0.0087F, 0.0F, 0.0F);
        body2_r1.setTextureOffset(34, 33).addBox(-4.0F, -6.5F, -5.0F, 8.0F, 8.0F, 6.0F, 0.0F, false);

        ModelRenderer body_r1 = new ModelRenderer(this);
        body_r1.setRotationPoint(1.0F, 1.0F, 3.0F);
        body.addChild(body_r1);
        setRotationAngle(body_r1, -0.1396F, 0.0F, 0.0F);
        body_r1.setTextureOffset(30, 16).addBox(-4.5F, -6.0F, 0.0F, 9.0F, 8.0F, 8.0F, 0.0F, false);

        head = new ModelRenderer(this);
        head.setRotationPoint(0.0F, 4.0F, -9.0F);
        setRotationAngle(head, -0.0436F, 0.0F, 0.0F);


        ModelRenderer neck2_r1 = new ModelRenderer(this);
        neck2_r1.setRotationPoint(-0.5F, 8.4128F, 0.4981F);
        head.addChild(neck2_r1);
        setRotationAngle(neck2_r1, -0.0436F, 0.0F, 0.0F);
        neck2_r1.setTextureOffset(36, 54).addBox(-2.0F, -12.7539F, -2.9962F, 5.0F, 4.0F, 5.0F, 0.0F, false);

        ModelRenderer mouth2_r1 = new ModelRenderer(this);
        mouth2_r1.setRotationPoint(0.0F, -2.5872F, 0.4981F);
        head.addChild(mouth2_r1);
        setRotationAngle(mouth2_r1, 0.4363F, 0.0F, 0.0F);
        mouth2_r1.setTextureOffset(0, 53).addBox(-2.0F, -6.2304F, -6.1585F, 4.0F, 2.0F, 4.0F, 0.0F, false);

        ModelRenderer mouth_r1 = new ModelRenderer(this);
        mouth_r1.setRotationPoint(0.0F, -2.5872F, 0.4981F);
        head.addChild(mouth_r1);
        setRotationAngle(mouth_r1, 0.2618F, 0.0F, 0.0F);
        mouth_r1.setTextureOffset(2, 59).addBox(-1.5F, -4.669F, -6.5434F, 3.0F, 2.0F, 3.0F, 0.0F, false);

        ModelRenderer ear2_r1 = new ModelRenderer(this);
        ear2_r1.setRotationPoint(-3.0F, -8.5872F, 0.4981F);
        head.addChild(ear2_r1);
        setRotationAngle(ear2_r1, -0.0436F, 0.3054F, 0.0F);
        ear2_r1.setTextureOffset(14, 53).addBox(-2.6733F, -1.9074F, 0.8797F, 3.0F, 2.0F, 1.0F, 0.0F, false);

        ModelRenderer ear_r1 = new ModelRenderer(this);
        ear_r1.setRotationPoint(3.0F, -8.5872F, 0.4981F);
        head.addChild(ear_r1);
        setRotationAngle(ear_r1, -0.0873F, -0.3054F, 0.0F);
        ear_r1.setTextureOffset(14, 53).addBox(-0.3267F, -1.8567F, 0.7976F, 3.0F, 2.0F, 1.0F, 0.0F, false);

        ModelRenderer head1_r1 = new ModelRenderer(this);
        head1_r1.setRotationPoint(0.0F, -2.5872F, 0.4981F);
        head.addChild(head1_r1);
        setRotationAngle(head1_r1, 0.1309F, 0.0F, 0.0F);
        head1_r1.setTextureOffset(0, 13).addBox(-3.0F, -7.0795F, -3.8219F, 6.0F, 6.0F, 7.0F, 0.0F, false);

        feet1 = new ModelRenderer(this);
        feet1.setRotationPoint(4.0F, 12.0F, -6.0F);
        feet1.setTextureOffset(56, 49).addBox(-1.0F, 6.0F, -1.5F, 2.0F, 6.0F, 2.0F, 0.0F, false);

        ModelRenderer feetH1_r1 = new ModelRenderer(this);
        feetH1_r1.setRotationPoint(0.5F, 3.0F, -1.0F);
        feet1.addChild(feetH1_r1);
        setRotationAngle(feetH1_r1, -0.2182F, 0.0F, 0.0F);
        feetH1_r1.setTextureOffset(23, 44).addBox(-2.0F, -4.0F, -0.5F, 3.0F, 7.0F, 3.0F, 0.0F, false);

        feet2 = new ModelRenderer(this);
        feet2.setRotationPoint(4.0F, 13.0F, 6.0F);
        feet2.setTextureOffset(56, 49).addBox(-0.5F, 5.0F, 2.0F, 2.0F, 6.0F, 2.0F, 0.0F, false);

        ModelRenderer feetH2_r1 = new ModelRenderer(this);
        feetH2_r1.setRotationPoint(-0.5F, 4.5F, 2.5F);
        feet2.addChild(feetH2_r1);
        setRotationAngle(feetH2_r1, 0.2618F, 0.0F, 0.0F);
        feetH2_r1.setTextureOffset(23, 54).addBox(-0.5F, -5.5F, -1.5F, 3.0F, 7.0F, 3.0F, 0.0F, false);

        feet3 = new ModelRenderer(this);
        feet3.setRotationPoint(-4.0F, 13.0F, 6.0F);
        feet3.setTextureOffset(56, 49).addBox(-1.5F, 5.0F, 2.0F, 2.0F, 6.0F, 2.0F, 0.0F, false);

        ModelRenderer feetH3_r1 = new ModelRenderer(this);
        feetH3_r1.setRotationPoint(0.5F, 4.5F, 2.5F);
        feet3.addChild(feetH3_r1);
        setRotationAngle(feetH3_r1, 0.2618F, 0.0F, 0.0F);
        feetH3_r1.setTextureOffset(23, 54).addBox(-2.5F, -5.5F, -1.5F, 3.0F, 7.0F, 3.0F, 0.0F, false);

        feet4 = new ModelRenderer(this);
        feet4.setRotationPoint(-4.0F, 12.0F, -6.0F);
        feet4.setTextureOffset(56, 49).addBox(-1.0F, 6.0F, -1.5F, 2.0F, 6.0F, 2.0F, 0.0F, false);

        ModelRenderer feetH4_r1 = new ModelRenderer(this);
        feetH4_r1.setRotationPoint(-0.5F, 3.0F, -1.0F);
        feet4.addChild(feetH4_r1);
        setRotationAngle(feetH4_r1, -0.2182F, 0.0F, 0.0F);
        feetH4_r1.setTextureOffset(23, 44).addBox(-1.0F, -4.0F, -0.5F, 3.0F, 7.0F, 3.0F, 0.0F, false);
    }

	@Nonnull
	@Override
	protected Iterable<ModelRenderer> getHeadParts() {
		return ImmutableList.of();
	}

	@Nonnull
	@Override
	protected Iterable<ModelRenderer> getBodyParts() {
		return ImmutableList.of(head, body, feet1, feet2, feet3, feet4);
	}

	@Override
	public void setRotationAngles(@Nonnull DeerEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.setRotationAngles(limbSwing, limbSwingAmount, netHeadYaw, headPitch);
    }

    public void setRotationAngles(float limbSwing, float limbSwingAmount, float netHeadYaw, float headPitch) {
        this.head.rotateAngleX = headPitch * ((float) Math.PI / 180F);
        this.head.rotateAngleY = netHeadYaw * ((float) Math.PI / 180F);
        this.feet1.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
        this.feet2.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
        this.feet3.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F * limbSwingAmount;
        this.feet4.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F * limbSwingAmount;
    }

    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}