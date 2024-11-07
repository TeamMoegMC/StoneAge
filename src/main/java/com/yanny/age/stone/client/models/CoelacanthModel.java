package com.yanny.age.stone.client.models;

import com.google.common.collect.ImmutableList;
import com.yanny.age.stone.entities.CoelacanthEntity;
import net.minecraft.client.model.ListModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.util.Mth;

import javax.annotation.Nonnull;

public class CoelacanthModel extends ListModel<CoelacanthEntity> {
//	private final ModelPart body;
//	private final ModelPart head;

	public CoelacanthModel() {
		/*texWidth = 32;
		texHeight = 32;

		body = new ModelPart(this);
		body.setPos(0.0F, 24.0F, 0.0F);
		body.texOffs(0, 22).addBox(-1.0F, -4.0F, -5.0F, 3.0F, 4.0F, 6.0F, 0.0F, false);
		body.texOffs(20, 25).addBox(-0.5F, -3.75F, 1.0F, 2.0F, 3.0F, 4.0F, 0.0F, false);
		body.texOffs(12, 21).addBox(0.0F, -3.75F, 5.0F, 1.0F, 3.0F, 4.0F, 0.0F, false);

		ModelPart bone = new ModelPart(this);
		bone.setPos(0.5F, -3.25F, -2.0F);
		body.addChild(bone);
		setRotationAngle(bone, 0.3491F, 0.0F, 0.0F);
		bone.texOffs(0, 6).addBox(0.0F, -0.9957F, -0.4347F, 0.0F, 2.0F, 3.0F, 0.0F, false);
		bone.texOffs(0, 6).addBox(0.0F, 0.9493F, 4.1782F, 0.0F, 2.0F, 3.0F, 0.0F, false);
		bone.texOffs(21, 0).addBox(0.0F, 3.1472F, 9.3933F, 0.0F, 2.0F, 3.0F, 0.0F, false);

		ModelPart bone2 = new ModelPart(this);
		bone2.setPos(0.0F, 2.25F, 3.0F);
		body.addChild(bone2);
		setRotationAngle(bone2, -0.3491F, 0.0F, 0.0F);
		bone2.texOffs(0, 6).addBox(0.5F, -4.2457F, -2.4347F, 0.0F, 2.0F, 3.0F, 0.0F, false);
		bone2.texOffs(21, 0).addBox(0.5F, -7.4983F, 3.3438F, 0.0F, 3.0F, 3.0F, 0.0F, false);

		head = new ModelPart(this);
		head.setPos(0.0F, 22.5F, -5.0F);
		setRotationAngle(head, 0.7854F, 0.0F, 0.0F);
		head.texOffs(22, 19).addBox(-0.5F, -1.8232F, -1.1768F, 2.0F, 3.0F, 3.0F, 0.0F, false);*/
	}

	@Nonnull
	@Override
	public Iterable<ModelPart> parts() {
		return ImmutableList.of(/*this.body, this.head*/);
	}

	@Override
	public void setupAnim(CoelacanthEntity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		float f = 1.0F;
		float f1 = 1.0F;

		if (!entityIn.isInWater()) {
			f = 1.3F;
			f1 = 1.7F;
		}

//		this.head.yRot = -f * 0.25F * Mth.sin(f1 * 0.6F * ageInTicks);
	}

	public void setRotationAngle(ModelPart modelRenderer, float x, float y, float z) {
		modelRenderer.xRot = x;
		modelRenderer.yRot = y;
		modelRenderer.zRot = z;
	}
}