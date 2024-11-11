package com.yanny.age.stone.api.items;

import net.minecraft.world.item.Tier;
import net.minecraft.world.level.block.Block;

import java.util.Set;


public class AgesSwordItem extends AgesToolItem {
    public AgesSwordItem(float attackDamageIn, float attackSpeedIn, Tier tier, Set<Block> effectiveBlocksIn, Properties builder, boolean displayAttackDamage, boolean displayAttackSpeed, boolean displayEfficiency) {
        super(attackDamageIn, attackSpeedIn, tier, effectiveBlocksIn, builder, displayAttackDamage, displayAttackSpeed, displayEfficiency);
    }
   /* private static final Set<Material> EFFECTIVE_MATERIALS;

    static {
        EFFECTIVE_MATERIALS = Sets.newHashSet(WEB, PLANT, REPLACEABLE_PLANT, CORAL, LEAVES, VEGETABLE);
    }

    public AgesSwordItem(Tier tier, float attackDamageIn, float attackSpeedIn, Properties builder) {
        super(attackDamageIn + tier.getAttackDamageBonus(), attackSpeedIn, tier, Sets.newHashSet(), builder, true, true, false);
    }

    @Override
    public boolean canAttackBlock(BlockState state, Level worldIn, BlockPos pos, Player player) {
        return !player.isCreative();
    }

    @Override
    public float getDestroySpeed(@Nonnull ItemStack stack, BlockState state) {
        Block block = state.getBlock();

        if (block == Blocks.COBWEB) {
            return 15.0F;
        } else {
            return !EFFECTIVE_MATERIALS.contains(state.getMaterial()) ? 1.0F : 1.5F;
        }
    }

    *//**
     * Current implementations of this method in child classes do not use the entry argument beside ev. They just raise
     * the damage on the stack.
     *//*
    @Override
    public boolean hurtEnemy(ItemStack stack, @Nonnull LivingEntity target, @Nonnull LivingEntity attacker) {
        stack.hurtAndBreak(1, attacker, (livingEntity) -> livingEntity.broadcastBreakEvent(EquipmentSlot.MAINHAND));
        return true;
    }

    *//**
     * Called when a Block is destroyed using this Item. Return true to trigger the "Use Item" statistic.
     *//*
    @Override
    public boolean mineBlock(@Nonnull ItemStack stack, Level worldIn, @Nonnull BlockState state, @Nonnull BlockPos pos, @Nonnull LivingEntity entityLiving) {
        if (state.getDestroySpeed(worldIn, pos) != 0.0F) {
            stack.hurtAndBreak(2, entityLiving, (livingEntity) -> livingEntity.broadcastBreakEvent(EquipmentSlot.MAINHAND));
        }

        return true;
    }

    *//**
     * Check whether this Item can harvest the given Block
     *//*
    @Override
    public boolean isCorrectToolForDrops(BlockState blockIn) {
        return blockIn.getMaterial() == WEB;
    }*/
}
