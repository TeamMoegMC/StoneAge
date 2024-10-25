package com.yanny.age.stone.compatibility.top;

import mcjty.theoneprobe.api.IProbeHitData;
import mcjty.theoneprobe.api.IProbeInfo;
import mcjty.theoneprobe.api.ProbeMode;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

import javax.annotation.Nonnull;

public interface TopBlockInfoProvider {
    void addProbeInfo(@Nonnull ProbeMode mode, @Nonnull IProbeInfo probeInfo, @Nonnull Player player, @Nonnull Level world,
                      @Nonnull BlockState blockState, @Nonnull IProbeHitData data);
}
