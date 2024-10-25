package com.yanny.age.stone.proxy;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;

public interface IProxy {
    void init();
    @Nullable
    Level getClientWorld();
    @Nullable
    Player getClientPlayer();
}
