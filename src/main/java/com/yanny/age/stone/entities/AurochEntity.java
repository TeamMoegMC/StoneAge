package com.yanny.age.stone.entities;

import com.yanny.age.stone.compatibility.top.TopEntityInfoProvider;
import com.yanny.age.stone.config.Config;
import com.yanny.age.stone.subscribers.EntitySubscriber;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.server.level.ServerLevel;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import net.minecraft.world.entity.ai.goal.BreedGoal;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.FollowParentGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.TemptGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;

public class AurochEntity extends WildAnimalEntity implements TopEntityInfoProvider {

    public AurochEntity(@Nonnull EntityType<AurochEntity> type, @Nonnull Level worldIn) {
        super(type, worldIn);
    }

    @Nullable
    @Override
    public AgeableMob getBreedOffspring(@Nonnull ServerLevel serverWorld, @Nonnull AgeableMob ageable) {
        if (Math.min(entityData.get(GENERATION), ageable.getEntityData().get(GENERATION)) >= Config.domesticateAfterGenerations) {
            EntityType<?> child = ForgeRegistries.ENTITY_TYPES.getValue(new ResourceLocation(Config.aurochBreedingResult));

            if (child != null) {
                Entity result = child.create(level());

                if (result instanceof AgeableMob) {
                    return (AgeableMob) child.create(level());
                } else {
                    LOGGER.warn("'{}' is not instance of Ageable entity! Spawning default COW entity", Config.aurochBreedingResult);
                }
            } else {
                LOGGER.warn("'{}' does not exists! Spawning default COW entity", Config.aurochBreedingResult);
            }

            return EntityType.COW.create(level());
        } else {
            AurochEntity entity = EntitySubscriber.auroch.create(level());

            if (entity != null) {
                entity.setGeneration(entityData.get(GENERATION) + 1);
            }

            return entity;
        }
    }

    @Override
    public void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1.0D, false));
        this.goalSelector.addGoal(2, new BreedGoal(this, 1.0D));
        this.goalSelector.addGoal(3, new TemptGoal(this, 1.0D, Ingredient.of(Items.WHEAT), false));
        this.goalSelector.addGoal(5, new RaidFarmGoal<>(this, CropBlock.class, CropBlock.AGE));
        this.goalSelector.addGoal(4, new FollowParentGoal(this, 1.25D));
        this.goalSelector.addGoal(5, new WaterAvoidingRandomStrollGoal(this, 1.0D));
        this.goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 6.0F));
        this.goalSelector.addGoal(6, new RandomLookAroundGoal(this));
        this.targetSelector.addGoal(1, new AgroTargetGoal(this, AurochEntity.class));
        this.targetSelector.addGoal(2, new TargetAggressorGoal<>(this, AurochEntity.class));
    }

    public static AttributeSupplier getAttributes() {
        return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 20.0D).add(Attributes.MOVEMENT_SPEED, 0.3F).build();
    }

    @Override
    public boolean doHurtTarget(Entity entityIn) {
        this.playSound(SoundEvents.COW_HURT, 1.0F, (this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 1.0F);
        return entityIn.hurt(DamageSource.mobAttack(this), 5.0F);
    }

    @Override
    public SoundEvent getAmbientSound() {
        return SoundEvents.COW_AMBIENT;
    }

    @Override
    public SoundEvent getHurtSound(@Nonnull DamageSource damageSourceIn) {
        return SoundEvents.COW_HURT;
    }

    @Override
    public SoundEvent getDeathSound() {
        return SoundEvents.COW_DEATH;
    }

    @Override
    public void playStepSound(@Nonnull BlockPos pos, @Nonnull BlockState blockIn) {
        this.playSound(SoundEvents.COW_STEP, 0.15F, 1.0F);
    }

    @Override
    public boolean isFood(ItemStack stack) {
        return stack.getItem() == Items.WHEAT;
    }

    /*@Override
    public void addProbeInfo(@Nonnull ProbeMode mode, @Nonnull IProbeInfo probeInfo, @Nonnull PlayerEntity player, @Nonnull World world, @Nonnull Entity entity, @Nonnull IProbeHitEntityData data) {
        probeInfo.horizontal().text(new StringTextComponent("Generation: " + entityData.get(GENERATION)));
    }*/
}
