package com.yanny.age.stone.entities;

import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.Difficulty;

import javax.annotation.Nonnull;

class TargetAggressorGoal<T extends Mob> extends NearestAttackableTargetGoal<Player> {
    private final Class<? extends IBecomeAngry> entity;

    TargetAggressorGoal(@Nonnull T entity, @Nonnull Class<? extends IBecomeAngry> clazz) {
        super(entity, Player.class, true);
        this.entity = clazz;
    }

    @Override
    public boolean canUse() {
        return entity.cast(mob).isAngry() && super.canUse() && mob.level.getDifficulty() != Difficulty.PEACEFUL;
    }
}
