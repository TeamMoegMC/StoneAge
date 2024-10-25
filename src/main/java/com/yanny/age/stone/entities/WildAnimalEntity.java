package com.yanny.age.stone.entities;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.goal.PrioritizedGoal;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.Difficulty;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Random;
import java.util.UUID;

public abstract class WildAnimalEntity extends AnimalEntity implements IBecomeAngry {
    static final DataParameter<Integer> GENERATION = EntityDataManager.defineId(WildAnimalEntity.class, DataSerializers.INT);

    private int angerLevel;
    private UUID angerTargetUUID;

    WildAnimalEntity(@Nonnull EntityType<? extends AnimalEntity> type, @Nonnull World worldIn) {
        super(type, worldIn);
    }

    public static boolean canMonsterSpawn(EntityType<? extends WildAnimalEntity> type, IWorld worldIn, SpawnReason reason, BlockPos pos, Random randomIn) {
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
                goalSelector.getRunningGoals().forEach(PrioritizedGoal::stop);
            }
        }

        if (this.isAngry() && this.angerTargetUUID != null && livingentity == null) {
            PlayerEntity playerentity = this.level.getPlayerByUUID(this.angerTargetUUID);
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

            if (entity instanceof PlayerEntity && !((PlayerEntity)entity).isCreative() && this.canSee(entity)) {
                this.becomeAngryAt(entity);
            }

            return super.hurt(source, amount);
        }
    }

    @Override
    public void addAdditionalSaveData(@Nonnull CompoundNBT compound) {
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
    public void readAdditionalSaveData(@Nonnull CompoundNBT compound) {
        super.readAdditionalSaveData(compound);
        this.angerLevel = compound.getShort("Anger");
        this.setGeneration(compound.getInt("Generation"));
        String s = compound.getString("HurtBy");
        if (!s.isEmpty()) {
            this.angerTargetUUID = UUID.fromString(s);
            PlayerEntity playerentity = this.level.getPlayerByUUID(this.angerTargetUUID);
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
        this.yRot = (float)(MathHelper.atan2(z - this.getZ(), x - this.getX()) * (double)(180F / (float)Math.PI)) - 90.0F;
    }
}
