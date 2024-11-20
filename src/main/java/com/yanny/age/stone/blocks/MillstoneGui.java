package com.yanny.age.stone.blocks;

import com.mojang.blaze3d.vertex.PoseStack;
import com.yanny.age.stone.Reference;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;

import javax.annotation.Nonnull;

public class MillstoneGui extends AbstractContainerScreen<MillstoneContainer> {

    private final ResourceLocation GUI = new ResourceLocation(Reference.MODID, "textures/gui/container/millstone.png");

    public MillstoneGui(@Nonnull MillstoneContainer screenContainer, @Nonnull Inventory inv, @Nonnull Component titleIn) {
        super(screenContainer, inv, titleIn);
    }

    @Override
    public void render(@Nonnull GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(guiGraphics);
        super.render(guiGraphics, mouseX, mouseY, partialTicks);
        this.renderTooltip(guiGraphics, mouseX, mouseY);
    }

    @Override
    protected void renderBg(@Nonnull GuiGraphics guiGraphics, float partialTicks, int mouseX, int mouseY) {
        if (minecraft == null) {
            return;
        }

//        minecraft.getTextureManager().bindForSetup(GUI);
        guiGraphics.blit(GUI, leftPos, topPos, 0, 0, imageWidth, imageHeight);

        if (menu.getProgress() > 0) {
            int l = (int) Math.ceil(menu.getProgress() / 100.0 * 16);
//            guiGraphics.blit(GUI, leftPos + 80, topPos + 35, 176, 0, l, 16);
        }
    }
}
