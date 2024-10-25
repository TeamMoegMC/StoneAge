package com.yanny.age.stone.proxy;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;

public class ServerProxy implements IProxy {
    @Override
    public void init() {

    }

    @Override
    @Nullable
    public Level getClientWorld() {
        throw new IllegalStateException("No client world on server");
    }

    @Override
    @Nullable
    public Player getClientPlayer() {
        throw new IllegalStateException("No client player on server");
    }
}
