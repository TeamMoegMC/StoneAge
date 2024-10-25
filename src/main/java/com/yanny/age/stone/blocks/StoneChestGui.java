package com.yanny.age.stone.blocks;

import com.mojang.blaze3d.vertex.PoseStack;
import com.yanny.age.stone.Reference;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;

import javax.annotation.Nonnull;

public class StoneChestGui extends AbstractContainerScreen<AbstractContainerMenu> {

    private final ResourceLocation GUI = new ResourceLocation(Reference.MODID, "textures/gui/container/stone_chest.png");

    public StoneChestGui(@Nonnull AbstractContainerMenu screenContainer, @Nonnull Inventory inv, @Nonnull Component titleIn) {
        super(screenContainer, inv, titleIn);
    }

    @Override
    public void render(@Nonnull PoseStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(matrixStack);
        super.render(matrixStack, mouseX, mouseY, partialTicks);
        this.renderTooltip(matrixStack, mouseX, mouseY);
    }

    @Override
    protected void renderBg(@Nonnull PoseStack matrixStack, float partialTicks, int mouseX, int mouseY) {
        if (minecraft == null) {
            return;
        }

        minecraft.getTextureManager().bind(GUI);
        blit(matrixStack, leftPos, topPos, 0, 0, imageWidth, imageHeight);
    }
}
