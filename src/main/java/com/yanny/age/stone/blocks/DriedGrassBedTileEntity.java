package com.yanny.age.stone.blocks;

import com.yanny.age.stone.subscribers.TileEntitySubscriber;
import net.minecraft.core.BlockPos;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class DriedGrassBedTileEntity extends BlockEntity {
    public DriedGrassBedTileEntity(BlockPos blockPos, BlockState state) {
        //noinspection ConstantConditions
        super(TileEntitySubscriber.dried_grass_bed,blockPos, state);
    }

    @Override
    public ClientboundBlockEntityDataPacket getUpdatePacket() {
        return new ClientboundBlockEntityDataPacket(this.worldPosition, 11, this.getUpdateTag());
    }
}
