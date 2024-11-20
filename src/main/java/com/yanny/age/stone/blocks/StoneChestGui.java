package com.yanny.age.stone.blocks;

import com.mojang.blaze3d.vertex.PoseStack;
import com.yanny.age.stone.Reference;
import net.minecraft.client.gui.GuiGraphics;
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
//        this.minecraft.getTextureManager().bindForSetup(GUI);
        int i = (this.width - this.imageWidth) / 2;
        int j = (this.height - this.imageHeight) / 2;
        guiGraphics.blit(GUI, i, j, 0, 0, this.imageWidth, this.imageHeight);
/*        minecraft.getTextureManager().bind(GUI);
        blit(matrixStack, leftPos, topPos, 0, 0, imageWidth, imageHeight);*/
    }
}
