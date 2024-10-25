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
import net.minecraft.pathfinding.PathNodeType;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nonnull;

public class FowlEntity extends WildAnimalEntity implements TopEntityInfoProvider {

    private static final Ingredient TEMPTATION_ITEMS = Ingredient.of(Items.WHEAT_SEEDS, Items.MELON_SEEDS, Items.PUMPKIN_SEEDS, Items.BEETROOT_SEEDS);
    public float wingRotation;
    public float destPos;
    public float oFlapSpeed;
    public float oFlap;
    private float wingRotDelta = 1.0F;

    public FowlEntity(@Nonnull EntityType<? extends FowlEntity> type, @Nonnull World worldIn) {
        super(type, worldIn);
        this.setPathfindingMalus(PathNodeType.WATER, 0.0F);
    }

    @Override
    public AgeableEntity getBreedOffspring(@Nonnull ServerWorld serverWorld, @Nonnull AgeableEntity ageable) {
        if (Math.min(entityData.get(GENERATION), ageable.getEntityData().get(GENERATION)) >= Config.domesticateAfterGenerations) {
            EntityType<?> child = ForgeRegistries.ENTITIES.getValue(new ResourceLocation(Config.fowlBreedingResult));

            if (child != null) {
                Entity result = child.create(level);

                if (result instanceof AgeableEntity) {
                    return (AgeableEntity) child.create(level);
                } else {
                    LOGGER.warn("'{}' is not instance of Ageable entity! Spawning default CHICKEN entity", Config.fowlBreedingResult);
                }
            } else {
                LOGGER.warn("'{}' does not exists! Spawning default CHICKEN entity", Config.fowlBreedingResult);
            }

            return EntityType.CHICKEN.create(level);
        } else {
            FowlEntity entity = EntitySubscriber.fowl.create(level);

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
        this.goalSelector.addGoal(3, new TemptGoal(this, 1.0D, TEMPTATION_ITEMS, false));
        this.goalSelector.addGoal(5, new RaidFarmGoal<>(this, CropsBlock.class, CropsBlock.AGE));
        this.goalSelector.addGoal(4, new FollowParentGoal(this, 1.25D));
        this.goalSelector.addGoal(5, new WaterAvoidingRandomWalkingGoal(this, 1.0D));
        this.goalSelector.addGoal(6, new LookAtGoal(this, PlayerEntity.class, 6.0F));
        this.goalSelector.addGoal(6, new LookRandomlyGoal(this));
        this.targetSelector.addGoal(1, new AgroTargetGoal(this, FowlEntity.class));
        this.targetSelector.addGoal(2, new TargetAggressorGoal<>(this, FowlEntity.class));
    }

    public static AttributeModifierMap getAttributes() {
        return MobEntity.createMobAttributes().add(Attributes.MAX_HEALTH, 6.0D).add(Attributes.MOVEMENT_SPEED, 0.3F).build();
    }

    @Override
    public boolean doHurtTarget(Entity entityIn) {
        this.playSound(SoundEvents.CHICKEN_HURT, 1.0F, (this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 1.0F);
        return entityIn.hurt(DamageSource.mobAttack(this), 1.0F);
    }

    @Override
    public void aiStep() {
        super.aiStep();
        this.oFlap = this.wingRotation;
        this.oFlapSpeed = this.destPos;
        this.destPos = (float)((double)this.destPos + (double)(this.onGround ? -1 : 4) * 0.3D);
        this.destPos = MathHelper.clamp(this.destPos, 0.0F, 1.0F);
        if (!this.onGround && this.wingRotDelta < 1.0F) {
            this.wingRotDelta = 1.0F;
        }

        this.wingRotDelta = (float)((double)this.wingRotDelta * 0.9D);
        Vector3d vec3d = this.getDeltaMovement();
        if (!this.onGround && vec3d.y < 0.0D) {
            this.setDeltaMovement(vec3d.multiply(1.0D, 0.6D, 1.0D));
        }

        this.wingRotation += this.wingRotDelta * 2.0F;
    }

    public boolean causeFallDamage(float distance, float damageMultiplier) {
        return false;
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return SoundEvents.CHICKEN_AMBIENT;
    }

    @Override
    protected SoundEvent getHurtSound(@Nonnull DamageSource damageSourceIn) {
        return SoundEvents.CHICKEN_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.CHICKEN_DEATH;
    }

    @Override
    protected void playStepSound(@Nonnull BlockPos pos, @Nonnull BlockState blockIn) {
        this.playSound(SoundEvents.CHICKEN_STEP, 0.15F, 1.0F);
    }

    @Override
    public boolean isFood(@Nonnull ItemStack stack) {
        return TEMPTATION_ITEMS.test(stack);
    }

    @Override
    public void addProbeInfo(@Nonnull ProbeMode mode, @Nonnull IProbeInfo probeInfo, @Nonnull PlayerEntity player, @Nonnull World world, @Nonnull Entity entity, @Nonnull IProbeHitEntityData data) {
        probeInfo.horizontal().text(new StringTextComponent("Generation: " + entityData.get(GENERATION)));
    }
}
