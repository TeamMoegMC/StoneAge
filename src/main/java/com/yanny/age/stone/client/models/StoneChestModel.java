package com.yanny.age.stone.client.models;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.yanny.age.stone.Reference;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.model.Model;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nonnull;

@OnlyIn(Dist.CLIENT)
public class StoneChestModel extends Model {
//	private final ModelPart body;
	private final ModelPart lid;
	public static final ModelLayerLocation STONECHEST_LAYER = new ModelLayerLocation(
			new ResourceLocation(Reference.MODID,"stonechest_layer"),"main");

	public StoneChestModel(ModelPart part) {
		super(RenderType::entityCutout);
		/*texWidth = 64;
		texHeight = 64;

		body = new ModelPart(this, 0, 48);
		body.setPos(0.0F, 24.0F, 0.0F);
		body.addBox(-7.0F, -2.0F, -7.0F, 14, 2, 14, 0.0F, false);

		ModelPart body1 = new ModelPart(this, 0, 24);
		body1.addBox(-7.0F, -12.0F, -7.0F, 2, 10, 14, 0.0F, false);
		body1.addBox(5.0F, -12.0F, -7.0F, 2, 10, 14, 0.0F, false);
		body.addChild(body1);

		ModelPart body2 = new ModelPart(this, 32, 36);
		body2.addBox(-5.0F, -12.0F, -7.0F, 10, 10, 2, 0.0F, false);
		body2.addBox(-5.0F, -12.0F, 5.0F, 10, 10, 2, 0.0F, false);
		body.addChild(body2);

		lid = new ModelPart(this, 0, 0);
		lid.setPos(3.0F, 10.0F, 5.0F);
		lid.addBox(-10.0F, 0.0F, -12.0F, 14, 2, 14, 0.0F, false);*/
		this.lid = part.getChild("lid");
	}

	public ModelPart getLid() {
		return lid;
	}

	@Override
	public void renderToBuffer(@Nonnull PoseStack matrixStack, @Nonnull VertexConsumer iVertexBuilder, int i, int i1, float v, float v1, float v2, float v3) {
//		body.render(matrixStack, iVertexBuilder, i, i1, v, v1, v2, v3);
//		lid.render(matrixStack, iVertexBuilder, i, i1, v, v1, v2, v3);
	}
	public static LayerDefinition createBodyLayer() {
		MeshDefinition mesh = new MeshDefinition();
		PartDefinition partDefinition = mesh.getRoot();

		partDefinition .addOrReplaceChild("body", CubeListBuilder.create().addBox(-7.0F, -2.0F, -7.0F, 14, 2, 14), PartPose.ZERO);
		partDefinition .addOrReplaceChild("body1", CubeListBuilder.create().addBox(-7.0F, -12.0F, -7.0F, 2, 10, 14).addBox(5.0F, -12.0F, -7.0F, 2, 10, 14), PartPose.ZERO);
		partDefinition .addOrReplaceChild("body2", CubeListBuilder.create().addBox(-5.0F, -12.0F, -7.0F, 10, 10, 2).addBox(-5.0F, -12.0F, 5.0F, 10, 10, 2), PartPose.ZERO);
		partDefinition .addOrReplaceChild("lid", CubeListBuilder.create().addBox(-10.0F, 0.0F, -12.0F, 14, 2, 14), PartPose.ZERO);
		return LayerDefinition.create(mesh, 64, 32);
	}
}