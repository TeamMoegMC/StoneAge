package com.yanny.age.stone.api.capability.kinetic;

import net.minecraft.core.Direction;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public interface IKineticSpindle extends IKinetic {
    void onDisconnectFromGenerator();

    void onConnectedToGenerator(@Nonnull IKineticGenerator generator);

    void onEnergyChanged(@Nonnull IKineticGenerator generator);

    @Nullable
    Direction getDirection();
}
