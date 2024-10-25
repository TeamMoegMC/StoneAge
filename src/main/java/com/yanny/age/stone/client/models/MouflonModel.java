package com.yanny.age.stone.client.models;

import com.google.common.collect.ImmutableList;
import com.yanny.age.stone.entities.MouflonEntity;
import net.minecraft.client.renderer.entity.model.AgeableModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nonnull;

@OnlyIn(Dist.CLIENT)
public class MouflonModel extends AgeableModel<MouflonEntity> {
    private final ModelRenderer body;
    private final ModelRenderer body4_r1;
    private final ModelRenderer tail_r1;
    private final ModelRenderer head;
    private final ModelRenderer neck_r1;
    private final ModelRenderer ear2_r1;
    private final ModelRenderer ear1_r1;
    private final ModelRenderer head3_r1;
    private final ModelRenderer horn;
    private final ModelRenderer horn3_r1;
    private final ModelRenderer horn4_r1;
    private final ModelRenderer horn2_r1;
    private final ModelRenderer horn1_r1;
    private final ModelRenderer feet1;
    private final ModelRenderer feet3;
    private final ModelRenderer feet3_2_r1;
    private final ModelRenderer feet4;
    private final ModelRenderer feet2;
    private final ModelRenderer feet3_2_r2;

    public MouflonModel() {
        texWidth = 64;
        texHeight = 64;

        body = new ModelRenderer(this);
        body.setPos(0.0F, 13.0F, 0.0F);
        body.texOffs(8, 6).addBox(-5.0F, -7.0F, 4.0F, 10.0F, 8.0F, 6.0F, 0.0F, false);
        body.texOffs(9, 20).addBox(-4.5F, -6.5F, -2.0F, 9.0F, 7.0F, 6.0F, 0.0F, false);
        body.texOffs(9, 36).addBox(-5.0F, -7.0F, -8.0F, 10.0F, 8.0F, 6.0F, 0.0F, false);

        body4_r1 = new ModelRenderer(this);
        body4_r1.setPos(0.0F, -4.5F, -8.5F);
        body.addChild(body4_r1);
        setRotationAngle(body4_r1, 0.1309F, 0.0F, 0.0F);
        body4_r1.texOffs(44, 50).addBox(-4.0F, -2.5F, -0.5F, 8.0F, 5.0F, 2.0F, 0.0F, false);

        tail_r1 = new ModelRenderer(this);
        tail_r1.setPos(0.0F, -3.5F, 11.5F);
        body.addChild(tail_r1);
        setRotationAngle(tail_r1, 0.5672F, 0.0F, 0.0F);
        tail_r1.texOffs(0, 20).addBox(-1.0F, -1.5F, -1.5F, 2.0F, 3.0F, 1.0F, 0.0F, false);

        head = new ModelRenderer(this);
        head.setPos(0.0F, 7.0F, -8.0F);
        head.texOffs(40, 0).addBox(-3.0F, -7.0F, -5.0F, 6.0F, 5.0F, 6.0F, 0.0F, false);
        head.texOffs(43, 12).addBox(-2.0F, -5.5F, -8.0F, 4.0F, 3.0F, 3.0F, 0.0F, false);

        neck_r1 = new ModelRenderer(this);
        neck_r1.setPos(0.0F, -1.0F, 0.5F);
        head.addChild(neck_r1);
        setRotationAngle(neck_r1, 0.3491F, 0.0F, 0.0F);
        neck_r1.texOffs(28, 51).addBox(-2.5F, -3.0F, -2.5F, 5.0F, 6.0F, 4.0F, 0.0F, false);

        ear2_r1 = new ModelRenderer(this);
        ear2_r1.setPos(4.0F, -6.5F, -2.5F);
        head.addChild(ear2_r1);
        setRotationAngle(ear2_r1, 0.0F, 0.0F, 0.2182F);
        ear2_r1.texOffs(55, 20).addBox(-1.0F, 0.0F, -0.5F, 2.0F, 2.0F, 1.0F, 0.0F, false);

        ear1_r1 = new ModelRenderer(this);
        ear1_r1.setPos(-4.0F, -6.5F, -2.5F);
        head.addChild(ear1_r1);
        setRotationAngle(ear1_r1, 0.0F, 0.0F, -0.2182F);
        ear1_r1.texOffs(55, 20).addBox(-1.0F, 0.0F, -0.5F, 2.0F, 2.0F, 1.0F, 0.0F, false);

        head3_r1 = new ModelRenderer(this);
        head3_r1.setPos(0.0F, -6.0F, -6.5F);
        head.addChild(head3_r1);
        setRotationAngle(head3_r1, 0.2618F, 0.0F, 0.0F);
        head3_r1.texOffs(44, 19).addBox(-1.0F, 0.0F, -2.0F, 2.0F, 2.0F, 5.0F, 0.0F, false);

        horn = new ModelRenderer(this);
        horn.setPos(0.0F, -3.0F, -2.0F);
        head.addChild(horn);


        horn3_r1 = new ModelRenderer(this);
        horn3_r1.setPos(0.0F, -1.5F, 1.0F);
        horn.addChild(horn3_r1);
        setRotationAngle(horn3_r1, -0.6109F, 0.2269F, -0.1047F);
        horn3_r1.texOffs(10, 0).addBox(1.0F, -4.5F, -2.0F, 2.0F, 3.0F, 1.0F, 0.0F, false);

        horn4_r1 = new ModelRenderer(this);
        horn4_r1.setPos(2.0F, -5.5F, 2.0F);
        horn.addChild(horn4_r1);
        setRotationAngle(horn4_r1, -0.5236F, 0.3665F, 0.3578F);
        horn4_r1.texOffs(10, 0).addBox(0.0F, -3.5F, -1.0F, 1.0F, 3.0F, 1.0F, 0.0F, false);

        horn2_r1 = new ModelRenderer(this);
        horn2_r1.setPos(-2.0F, -7.5F, 1.0F);
        horn.addChild(horn2_r1);
        setRotationAngle(horn2_r1, -0.5236F, -0.4363F, -0.3752F);
        horn2_r1.texOffs(10, 0).addBox(-1.0F, -2.5F, 1.0F, 1.0F, 3.0F, 1.0F, 0.0F, false);

        horn1_r1 = new ModelRenderer(this);
        horn1_r1.setPos(0.0F, -1.5F, 1.0F);
        horn.addChild(horn1_r1);
        setRotationAngle(horn1_r1, -0.6109F, -0.2269F, 0.1047F);
        horn1_r1.texOffs(10, 0).addBox(-3.0F, -4.5F, -2.0F, 2.0F, 3.0F, 1.0F, 0.0F, false);

        feet1 = new ModelRenderer(this);
        feet1.setPos(2.0F, 14.0F, -5.0F);
        feet1.texOffs(0, 0).addBox(0.0F, -1.0F, 0.0F, 2.0F, 11.0F, 2.0F, 0.0F, false);

        feet3 = new ModelRenderer(this);
        feet3.setPos(-2.0F, 14.0F, 6.0F);
        feet3.texOffs(56, 40).addBox(-2.0F, 3.0F, 2.5F, 2.0F, 7.0F, 2.0F, 0.0F, false);

        feet3_2_r1 = new ModelRenderer(this);
        feet3_2_r1.setPos(0.0F, 3.0F, 3.0F);
        feet3.addChild(feet3_2_r1);
        setRotationAngle(feet3_2_r1, 0.2618F, 0.0F, 0.0F);
        feet3_2_r1.texOffs(52, 29).addBox(-2.5F, -4.0F, -1.5F, 3.0F, 6.0F, 3.0F, 0.0F, false);

        feet4 = new ModelRenderer(this);
        feet4.setPos(-2.0F, 14.0F, -5.0F);
        feet4.texOffs(0, 0).addBox(-2.0F, -1.0F, 0.0F, 2.0F, 11.0F, 2.0F, 0.0F, false);

        feet2 = new ModelRenderer(this);
        feet2.setPos(4.0F, 14.0F, 6.0F);
        feet2.texOffs(56, 40).addBox(-2.0F, 3.0F, 2.5F, 2.0F, 7.0F, 2.0F, 0.0F, false);

        feet3_2_r2 = new ModelRenderer(this);
        feet3_2_r2.setPos(0.0F, 3.0F, 3.0F);
        feet2.addChild(feet3_2_r2);
        setRotationAngle(feet3_2_r2, 0.2618F, 0.0F, 0.0F);
        feet3_2_r2.texOffs(52, 29).addBox(-2.5F, -4.0F, -1.5F, 3.0F, 6.0F, 3.0F, 0.0F, false);
    }

	@Nonnull
	@Override
	protected Iterable<ModelRenderer> headParts() {
		return ImmutableList.of();
	}

	@Nonnull
	@Override
	protected Iterable<ModelRenderer> bodyParts() {
		return ImmutableList.of(head, body, feet1, feet2, feet3, feet4);
	}

	@Override
	public void setupAnim(@Nonnull MouflonEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.setRotationAngles(limbSwing, limbSwingAmount, netHeadYaw, headPitch);
	}

	public void setRotationAngles(float limbSwing, float limbSwingAmount, float netHeadYaw, float headPitch) {
		this.head.xRot = headPitch * ((float) Math.PI / 180F);
		this.head.yRot = netHeadYaw * ((float) Math.PI / 180F);
		this.feet1.xRot = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
		this.feet2.xRot = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F * limbSwingAmount;
		this.feet3.xRot = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
		this.feet4.xRot = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F * limbSwingAmount;
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.xRot = x;
		modelRenderer.yRot = y;
		modelRenderer.zRot = z;
	}
}