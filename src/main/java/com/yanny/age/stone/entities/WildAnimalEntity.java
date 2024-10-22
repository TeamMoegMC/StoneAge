package com.yanny.age.stone.entities;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.ai.goal.WrappedGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.world.Difficulty;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Random;
import java.util.UUID;

public abstract class WildAnimalEntity extends Animal implements IBecomeAngry {
    static final EntityDataAccessor<Integer> GENERATION = SynchedEntityData.defineId(WildAnimalEntity.class, EntityDataSerializers.INT);

    private int angerLevel;
    private UUID angerTargetUUID;

    WildAnimalEntity(@Nonnull EntityType<? extends Animal> type, @Nonnull Level worldIn) {
        super(type, worldIn);
    }

    public static boolean canMonsterSpawn(EntityType<? extends WildAnimalEntity> type, LevelAccessor worldIn, MobSpawnType reason, BlockPos pos, Random randomIn) {
        return worldIn.getDifficulty() != Difficulty.PEACEFUL && checkMobSpawnRules(type, worldIn, reason, pos, randomIn);
    }

    @Override
    public void customServerAiStep() {
        LivingEntity livingentity = this.getTarget();
        if (livingentity != null && this.distanceToSqr(livingentity) < 16.0D) {
            this.calculateRotationYaw(livingentity.getX(), livingentity.getZ());
            this.moveControl.setWantedPosition(livingentity.getX(), livingentity.getY(), livingentity.getZ(), this.moveControl.getSpeedModifier());
        }

        if (this.isAngry()) {
            --this.angerLevel;
            if (!this.isAngry()) {
                this.setLastHurtByMob(null);
                this.setTarget(null);
                goalSelector.getRunningGoals().forEach(WrappedGoal::stop);
            }
        }

        if (this.isAngry() && this.angerTargetUUID != null && livingentity == null) {
            Player playerentity = this.level.getPlayerByUUID(this.angerTargetUUID);
            this.setLastHurtByMob(playerentity);
            this.lastHurtByPlayer = playerentity;
            this.lastHurtByPlayerTime = this.getLastHurtByMobTimestamp();
        }
    }

    @Override
    public boolean hurt(@Nonnull DamageSource source, float amount) {
        if (this.isInvulnerableTo(source)) {
            return false;
        } else {
            Entity entity = source.getEntity();

            if (entity instanceof Player && !((Player)entity).isCreative() && this.canSee(entity)) {
                this.becomeAngryAt(entity);
            }

            return super.hurt(source, amount);
        }
    }

    @Override
    public void addAdditionalSaveData(@Nonnull CompoundTag compound) {
        super.addAdditionalSaveData(compound);
        compound.putShort("Anger", (short)this.angerLevel);
        compound.putInt("Generation", entityData.get(GENERATION));
        if (this.angerTargetUUID != null) {
            compound.putString("HurtBy", this.angerTargetUUID.toString());
        } else {
            compound.putString("HurtBy", "");
        }
    }

    @Override
    public void readAdditionalSaveData(@Nonnull CompoundTag compound) {
        super.readAdditionalSaveData(compound);
        this.angerLevel = compound.getShort("Anger");
        this.setGeneration(compound.getInt("Generation"));
        String s = compound.getString("HurtBy");
        if (!s.isEmpty()) {
            this.angerTargetUUID = UUID.fromString(s);
            Player playerentity = this.level.getPlayerByUUID(this.angerTargetUUID);
            this.setLastHurtByMob(playerentity);
            if (playerentity != null) {
                this.lastHurtByPlayer = playerentity;
                this.lastHurtByPlayerTime = this.getLastHurtByMobTimestamp();
            }
        }
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(GENERATION, 0);
    }

    @Override
    public void setLastHurtByMob(@Nullable LivingEntity livingBase) {
        super.setLastHurtByMob(livingBase);
        if (livingBase != null) {
            this.angerTargetUUID = livingBase.getUUID();
        }
    }

    @Override
    public boolean becomeAngryAt(@Nonnull Entity entity) {
        this.angerLevel = this.nextRand();
        if (entity instanceof LivingEntity) {
            this.setLastHurtByMob((LivingEntity)entity);
        }

        return true;
    }

    @Override
    public boolean isAngry() {
        return this.angerLevel > 0;
    }

    void setGeneration(int generation) {
        entityData.set(GENERATION, generation);
    }

    private int nextRand() {
        return 100 + this.random.nextInt(100);
    }

    private void calculateRotationYaw(double x, double z) {
        this.yRot = (float)(Mth.atan2(z - this.getZ(), x - this.getX()) * (double)(180F / (float)Math.PI)) - 90.0F;
    }
}
