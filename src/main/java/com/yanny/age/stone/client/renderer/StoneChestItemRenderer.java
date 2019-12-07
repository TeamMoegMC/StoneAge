package com.yanny.age.stone.client.renderer;

import com.yanny.age.stone.blocks.StoneChestTileEntity;
import net.minecraft.client.renderer.tileentity.ItemStackTileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class StoneChestItemRenderer extends ItemStackTileEntityRenderer {
    private static final TileEntity tileEntity = new StoneChestTileEntity();

    @Override
    public void renderByItem(ItemStack itemStackIn) {
        TileEntityRendererDispatcher.instance.renderAsItem(tileEntity);
    }
}
