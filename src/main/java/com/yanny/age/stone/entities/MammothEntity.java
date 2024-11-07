package com.yanny.age.stone.entities;

import com.yanny.age.stone.subscribers.EntitySubscriber;
import com.yanny.age.stone.subscribers.SoundSubscriber;
import net.minecraft.world.entity.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.world.level.Level;
import net.minecraft.server.level.ServerLevel;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import net.minecraft.world.entity.AgableMob;
import net.minecraft.world.entity.ai.goal.BreedGoal;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.FollowParentGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.TemptGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;

public class MammothEntity extends WildAnimalEntity {

    public MammothEntity(@Nonnull EntityType<MammothEntity> type, @Nonnull Level worldIn) {
        super(type, worldIn);
    }

    @Nullable
    @Override
    public AgeableMob getBreedOffspring(@Nonnull ServerLevel serverWorld, @Nonnull AgeableMob ageable) {
        return EntitySubscriber.mammoth.create(level());
    }

    @Override
    public void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1.0D, false));
        this.goalSelector.addGoal(2, new BreedGoal(this, 1.0D));
        this.goalSelector.addGoal(3, new TemptGoal(this, 1.0D, Ingredient.of(Items.HAY_BLOCK), false));
        this.goalSelector.addGoal(4, new FollowParentGoal(this, 1.25D));
        this.goalSelector.addGoal(5, new WaterAvoidingRandomStrollGoal(this, 1.0D));
        this.goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 6.0F));
        this.goalSelector.addGoal(6, new RandomLookAroundGoal(this));
        this.targetSelector.addGoal(1, new AgroTargetGoal(this, MammothEntity.class));
        this.targetSelector.addGoal(2, new TargetAggressorGoal<>(this, MammothEntity.class));
    }

    public static AttributeSupplier getAttributes() {
        return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 40.0D).add(Attributes.MOVEMENT_SPEED, 0.3F).build();
    }

    @Override
    public boolean doHurtTarget(@Nonnull Entity entityIn) {
        //noinspection ConstantConditions
        this.playSound(SoundSubscriber.mammoth_hit, 1.0F, (this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 1.0F);

        if (entityIn instanceof LivingEntity) {
            ((LivingEntity) entityIn).knockback(2.0F,
                    Mth.sin(this.yRot * ((float)Math.PI / 180F)),
                    -Mth.cos(this.yRot * ((float)Math.PI / 180F)));
        }

        return entityIn.hurt(entityIn.damageSources().mobAttack(this), 8.0F);
    }

    @Override
    public SoundEvent getAmbientSound() {
        return SoundSubscriber.mammoth_ambient;
    }

    @Override
    public SoundEvent getHurtSound(@Nonnull DamageSource damageSourceIn) {
        return SoundSubscriber.mammoth_hit;
    }

    @Override
    public SoundEvent getDeathSound() {
        return SoundSubscriber.mammoth_death;
    }

    @Override
    public void playStepSound(@Nonnull BlockPos pos, @Nonnull BlockState blockIn) {
        this.playSound(SoundEvents.COW_STEP, 0.15F, 1.0F);
    }

    @Override
    public boolean isFood(ItemStack stack) {
        return stack.getItem() == Items.HAY_BLOCK;
    }
}
