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
public class MillstoneModel extends Model {
	private final ModelPart body;
	public static final ModelLayerLocation MILLSTONE_LAYER = new ModelLayerLocation(
			new ResourceLocation(Reference.MODID,"millstone_layer"),"main");

	public MillstoneModel(ModelPart part) {
		super(RenderType::entityCutout);
	/*	texWidth = 64;
		texHeight = 64;

		body = new ModelPart(this);
		body.setPos(0.0F, 0.0F, 0.0F);
		body.addBox(-5.0F, 7.0F, -5.0F, 10, 4, 10);*/
		this.body = part.getChild("body");
	}

	public void rotate(float angle) {
		this.body.yRot = angle;
	}

	@Override
	public void renderToBuffer(@Nonnull PoseStack matrixStack, @Nonnull VertexConsumer iVertexBuilder, int i, int i1, float v, float v1, float v2, float v3) {
//		body.render(matrixStack, iVertexBuilder, i, i1, v, v1, v2, v3);
	}
	public static LayerDefinition createBodyLayer() {
		MeshDefinition mesh = new MeshDefinition();
		PartDefinition partDefinition = mesh.getRoot();

		partDefinition .addOrReplaceChild("body", CubeListBuilder.create().addBox(-5.0F, 7.0F, -5.0F, 10, 4, 10), PartPose.ZERO);
		return LayerDefinition.create(mesh, 64, 32);
	}
}