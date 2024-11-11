package com.yanny.age.stone.api.capability.kinetic;

import net.minecraft.core.Direction;

import javax.annotation.Nullable;

public interface IKineticGenerator extends IKinetic {
    float getRotationPerTick();

    @Nullable
    Direction getDirection();

    int getGeneratedPower();
}
