package com.yanny.age.stone.items;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.yanny.age.stone.Reference;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

import javax.annotation.Nonnull;

public class BackpackGui extends ContainerScreen<Container> {

    private final ResourceLocation GUI = new ResourceLocation(Reference.MODID, "textures/gui/container/stone_chest.png");

    public BackpackGui(@Nonnull Container screenContainer, @Nonnull PlayerInventory inv, @Nonnull ITextComponent titleIn) {
        super(screenContainer, inv, titleIn);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(@Nonnull MatrixStack matrixStack, float partialTicks, int mouseX, int mouseY) {
        if (minecraft == null) {
            return;
        }

        minecraft.getTextureManager().bindTexture(GUI);
        blit(matrixStack, guiLeft, guiTop, 0, 0, xSize, ySize);
    }
}
