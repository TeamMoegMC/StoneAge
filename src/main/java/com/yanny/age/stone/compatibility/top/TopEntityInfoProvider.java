package com.yanny.age.stone.compatibility.top;

import mcjty.theoneprobe.api.IProbeHitEntityData;
import mcjty.theoneprobe.api.IProbeInfo;
import mcjty.theoneprobe.api.ProbeMode;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

import javax.annotation.Nonnull;

public interface TopEntityInfoProvider {
    void addProbeInfo(@Nonnull ProbeMode mode, @Nonnull IProbeInfo probeInfo, @Nonnull Player player, @Nonnull Level world,
                      @Nonnull Entity entity, @Nonnull IProbeHitEntityData data);
}
