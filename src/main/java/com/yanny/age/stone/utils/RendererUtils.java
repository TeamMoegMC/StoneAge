package com.yanny.age.stone.utils;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockAndTintGetter;

import javax.annotation.Nonnull;

public class RendererUtils {

    /*public static void add(@Nonnull Fluid fluid, @Nonnull BlockAndTintGetter lightReader, @Nonnull BlockPos posIn, @Nonnull VertexConsumer renderer,
                           @Nonnull PoseStack stack, float x, float y, float z, float u, float v) {
        int i = fluid.getFluid().getAttributes().getColor(lightReader, posIn);
        float alpha = (float)(i >> 24 & 255) / 255.0F;
        float r = (float)(i >> 16 & 255) / 255.0F;
        float g = (float)(i >> 8 & 255) / 255.0F;
        float b = (float)(i & 255) / 255.0F;
        int j = getCombinedAverageLight(lightReader, posIn);
        renderer.vertex(stack.last().pose(), x, y, z)
                .color(r, g, b, alpha)
                .uv(u, v)
                .uv2(j & 0xffff, j >> 16 & 0xffff)
                .normal(1, 0, 0)
                .endVertex();
    }*/

    public static int getCombinedAverageLight(@Nonnull BlockAndTintGetter lightReaderIn, @Nonnull BlockPos posIn) {
        int i = LevelRenderer.getLightColor(lightReaderIn, posIn);
        int j = LevelRenderer.getLightColor(lightReaderIn, posIn.above());
        int k = i & 255;
        int l = j & 255;
        int i1 = i >> 16 & 255;
        int j1 = j >> 16 & 255;
        return (Math.max(k, l)) | (Math.max(i1, j1)) << 16;
    }
}
