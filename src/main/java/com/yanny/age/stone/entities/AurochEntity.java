package com.yanny.age.stone.entities;

import com.yanny.age.stone.compatibility.top.TopEntityInfoProvider;
import com.yanny.age.stone.config.Config;
import com.yanny.age.stone.subscribers.EntitySubscriber;
import mcjty.theoneprobe.api.IProbeHitEntityData;
import mcjty.theoneprobe.api.IProbeInfo;
import mcjty.theoneprobe.api.ProbeMode;
import net.minecraft.block.BlockState;
import net.minecraft.block.CropsBlock;
import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class AurochEntity extends WildAnimalEntity implements TopEntityInfoProvider {

    public AurochEntity(@Nonnull EntityType<AurochEntity> type, @Nonnull World worldIn) {
        super(type, worldIn);
    }

    @Nullable
    @Override
    public AgeableEntity getBreedOffspring(@Nonnull ServerWorld serverWorld, @Nonnull AgeableEntity ageable) {
        if (Math.min(entityData.get(GENERATION), ageable.getEntityData().get(GENERATION)) >= Config.domesticateAfterGenerations) {
            EntityType<?> child = ForgeRegistries.ENTITIES.getValue(new ResourceLocation(Config.aurochBreedingResult));

            if (child != null) {
                Entity result = child.create(level);

                if (result instanceof AgeableEntity) {
                    return (AgeableEntity) child.create(level);
                } else {
                    LOGGER.warn("'{}' is not instance of Ageable entity! Spawning default COW entity", Config.aurochBreedingResult);
                }
            } else {
                LOGGER.warn("'{}' does not exists! Spawning default COW entity", Config.aurochBreedingResult);
            }

            return EntityType.COW.create(level);
        } else {
            AurochEntity entity = EntitySubscriber.auroch.create(level);

            if (entity != null) {
                entity.setGeneration(entityData.get(GENERATION) + 1);
            }

            return entity;
        }
    }

    @Override
    public void registerGoals() {
        this.goalSelector.addGoal(0, new SwimGoal(this));
        this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1.0D, false));
        this.goalSelector.addGoal(2, new BreedGoal(this, 1.0D));
        this.goalSelector.addGoal(3, new TemptGoal(this, 1.0D, Ingredient.of(Items.WHEAT), false));
        this.goalSelector.addGoal(5, new RaidFarmGoal<>(this, CropsBlock.class, CropsBlock.AGE));
        this.goalSelector.addGoal(4, new FollowParentGoal(this, 1.25D));
        this.goalSelector.addGoal(5, new WaterAvoidingRandomWalkingGoal(this, 1.0D));
        this.goalSelector.addGoal(6, new LookAtGoal(this, PlayerEntity.class, 6.0F));
        this.goalSelector.addGoal(6, new LookRandomlyGoal(this));
        this.targetSelector.addGoal(1, new AgroTargetGoal(this, AurochEntity.class));
        this.targetSelector.addGoal(2, new TargetAggressorGoal<>(this, AurochEntity.class));
    }

    public static AttributeModifierMap getAttributes() {
        return MobEntity.createMobAttributes().add(Attributes.MAX_HEALTH, 20.0D).add(Attributes.MOVEMENT_SPEED, 0.3F).build();
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

    @Override
    public void addProbeInfo(@Nonnull ProbeMode mode, @Nonnull IProbeInfo probeInfo, @Nonnull PlayerEntity player, @Nonnull World world, @Nonnull Entity entity, @Nonnull IProbeHitEntityData data) {
        probeInfo.horizontal().text(new StringTextComponent("Generation: " + entityData.get(GENERATION)));
    }
}
