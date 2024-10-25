package com.yanny.age.stone.blocks;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.yanny.age.stone.Reference;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

import javax.annotation.Nonnull;

public class FeederGui extends ContainerScreen<Container> {

    private final ResourceLocation GUI = new ResourceLocation(Reference.MODID, "textures/gui/container/feeder.png");

    public FeederGui(@Nonnull Container screenContainer, @Nonnull PlayerInventory inv, @Nonnull ITextComponent titleIn) {
        super(screenContainer, inv, titleIn);
    }

    @Nonnull
    @Override
    public ITextComponent getTitle() {
        return super.getTitle();
    }

    @Override
    public void render(@Nonnull MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(matrixStack);
        super.render(matrixStack, mouseX, mouseY, partialTicks);
        this.renderTooltip(matrixStack, mouseX, mouseY);
    }

    @Override
    protected void renderBg(@Nonnull MatrixStack matrixStack, float partialTicks, int mouseX, int mouseY) {
        if (minecraft == null) {
            return;
        }

        minecraft.getTextureManager().bind(GUI);
        blit(matrixStack, leftPos, topPos, 0, 0, imageWidth, imageHeight);
    }
}
