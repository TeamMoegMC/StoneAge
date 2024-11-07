package com.yanny.age.stone.entities;

import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.target.TargetGoal;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.Difficulty;

import javax.annotation.Nonnull;
import java.util.EnumSet;
import java.util.List;
import java.util.Objects;

class AgroTargetGoal extends TargetGoal {
    private static final TargetingConditions predicate = (TargetingConditions.forCombat()).ignoreInvisibilityTesting();
    private int revengeTimerOld;
    private final Class<? extends IBecomeAngry> reinforcement;
    private final Class<?>[] excludedReinforcementTypes;

    AgroTargetGoal(@Nonnull PathfinderMob creatureIn, @Nonnull Class<? extends IBecomeAngry> reinforcement, @Nonnull Class<?>... excludeReinforcement) {
        super(creatureIn, true);
        this.reinforcement = reinforcement;
        this.excludedReinforcementTypes = excludeReinforcement;
        this.setFlags(EnumSet.of(Goal.Flag.TARGET));
    }

    @Override
    public boolean canUse() {
        int i = this.mob.getLastHurtByMobTimestamp();
        LivingEntity livingentity = this.mob.getLastHurtByMob();

        if (i != this.revengeTimerOld && livingentity != null) {
            for(Class<?> oclass : this.excludedReinforcementTypes) {
                if (oclass.isAssignableFrom(livingentity.getClass())) {
                    return false;
                }
            }

            return this.canAttack(livingentity, predicate) && mob.level().getDifficulty() != Difficulty.PEACEFUL;
        } else {
            return false;
        }
    }

    @Override
    public void start() {
        this.mob.setTarget(this.mob.getLastHurtByMob());
        this.targetMob = this.mob.getTarget();
        this.revengeTimerOld = this.mob.getLastHurtByMobTimestamp();
        this.unseenMemoryTicks = 300;
        this.alertOthers();
        super.start();
    }

    private void alertOthers() {
        double d0 = this.getFollowDistance();
        List<Mob> list = (List<Mob>) this.mob.level().getEntitiesOfClass(this.mob.getClass(),
                (new AABB(this.mob.getX(), this.mob.getY(), this.mob.getZ(),
                        this.mob.getX() + 1.0D, this.mob.getY() + 1.0D, this.mob.getZ() + 1.0D)).inflate(d0, 10.0D, d0));

        for (Mob mobentity : list) {
            if (this.mob != mobentity && mobentity.getTarget() == null
                    && (!(this.mob instanceof TamableAnimal) || ((TamableAnimal) this.mob).getOwner() == ((TamableAnimal) mobentity).getOwner())
                    && !mobentity.isAlliedTo(Objects.requireNonNull(this.mob.getLastHurtByMob()))) {

                boolean flag = false;

                if (mobentity.getClass() == reinforcement) {
                    flag = true;
                }

                if (!flag) {
                    break;
                }

                this.setAttackTarget(mobentity, this.mob.getLastHurtByMob());
            }
        }
    }

    private void setAttackTarget(@Nonnull Mob mobIn, @Nonnull LivingEntity targetIn) {
        if (reinforcement.isAssignableFrom(mobIn.getClass()) && this.mob.getSensing().hasLineOfSight(targetIn) && reinforcement.cast(mobIn).becomeAngryAt(targetIn)) {
            mobIn.setTarget(targetIn);
        }
    }
}
