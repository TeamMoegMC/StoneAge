package com.yanny.age.stone.blocks;

import com.mojang.blaze3d.systems.RenderSystem;
import com.yanny.age.stone.Reference;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

import javax.annotation.Nonnull;

public class StoneChestGui extends ContainerScreen<Container> {

    private final ResourceLocation GUI = new ResourceLocation(Reference.MODID, "textures/gui/container/stone_chest.png");

    public StoneChestGui(@Nonnull Container screenContainer, @Nonnull PlayerInventory inv, @Nonnull ITextComponent titleIn) {
        super(screenContainer, inv, titleIn);
    }

    @Override
    public void render(int mouseX, int mouseY, float partialTicks) {
        this.renderBackground();
        super.render(mouseX, mouseY, partialTicks);
        this.renderHoveredToolTip(mouseX, mouseY);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        if (minecraft == null) {
            return;
        }

        RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        minecraft.getTextureManager().bindTexture(GUI);
        blit(guiLeft, guiTop, 0, 0, xSize, ySize);
    }
}
