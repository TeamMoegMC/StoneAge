package com.yanny.age.stone.compatibility.top;

import mcjty.theoneprobe.api.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;
import java.util.function.Function;

import static com.yanny.age.stone.compatibility.top.TopCompatibility.ID;

public class TopRegistration implements Function<ITheOneProbe, Void> {

    @Nullable
    @Override
    public Void apply(ITheOneProbe theOneProbe) {
        theOneProbe.registerProvider(new IProbeInfoProvider() {
            @Override
            public String getID() {
                return ID;
            }

            @Override
            public void addProbeInfo(ProbeMode mode, IProbeInfo probeInfo, Player player, Level world, BlockState blockState, IProbeHitData data) {
                if (blockState.getBlock() instanceof TopBlockInfoProvider) {
                    TopBlockInfoProvider provider = (TopBlockInfoProvider) blockState.getBlock();
                    provider.addProbeInfo(mode, probeInfo, player, world, blockState, data);
                }
            }
        });
        theOneProbe.registerEntityProvider(new IProbeInfoEntityProvider() {
            @Override
            public String getID() {
                return ID;
            }

            @Override
            public void addProbeEntityInfo(ProbeMode probeMode, IProbeInfo iProbeInfo, Player playerEntity, Level world, Entity entity, IProbeHitEntityData iProbeHitEntityData) {
                if (entity instanceof TopEntityInfoProvider) {
                    TopEntityInfoProvider provider = (TopEntityInfoProvider) entity;
                    provider.addProbeInfo(probeMode, iProbeInfo, playerEntity, world, entity, iProbeHitEntityData);
                }
            }
        });

        return null;
    }
}
