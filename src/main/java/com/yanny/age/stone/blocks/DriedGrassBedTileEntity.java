package com.yanny.age.stone.blocks;

import com.yanny.age.stone.subscribers.TileEntitySubscriber;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.level.block.entity.BlockEntity;

public class DriedGrassBedTileEntity extends BlockEntity {
    public DriedGrassBedTileEntity() {
        //noinspection ConstantConditions
        super(TileEntitySubscriber.dried_grass_bed);
    }

    @Override
    public ClientboundBlockEntityDataPacket getUpdatePacket() {
        return new ClientboundBlockEntityDataPacket(this.worldPosition, 11, this.getUpdateTag());
    }
}
