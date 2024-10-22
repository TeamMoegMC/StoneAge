package com.yanny.age.stone.entities;

import net.minecraft.world.entity.Entity;

import javax.annotation.Nonnull;

interface IBecomeAngry {
    boolean becomeAngryAt(@Nonnull Entity entity);
    boolean isAngry();
}
