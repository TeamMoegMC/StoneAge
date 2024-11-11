package com.yanny.age.stone.api.items;

import net.minecraft.world.item.Tier;
import net.minecraft.world.level.block.Block;

import java.util.Set;

public class AgesShovelItem extends AgesToolItem {
    public AgesShovelItem(float attackDamageIn, float attackSpeedIn, Tier tier, Set<Block> effectiveBlocksIn, Properties builder, boolean displayAttackDamage, boolean displayAttackSpeed, boolean displayEfficiency) {
        super(attackDamageIn, attackSpeedIn, tier, effectiveBlocksIn, builder, displayAttackDamage, displayAttackSpeed, displayEfficiency);
    }
    /*private static final Set<Block> EFFECTIVE_ON;
    private static final Map<Block, BlockState> SHOVEL_LOOKUP;
    private static final Set<Material> EFFECTIVE_MATERIALS;

    static {
        EFFECTIVE_ON = getPrivateValue(ShovelItem.class, null, 0);
        SHOVEL_LOOKUP = getPrivateValue(ShovelItem.class, null, 1);
        EFFECTIVE_MATERIALS = Sets.newHashSet(TOP_SNOW, SNOW);
    }

    public AgesShovelItem(IItemTier tier, float attackDamageIn, float attackSpeedIn, Properties builder) {
        super(attackDamageIn, attackSpeedIn, tier, EFFECTIVE_ON, builder, true, true, true);
    }

    @Override
    public boolean isCorrectToolForDrops(BlockState blockIn) {
        return EFFECTIVE_MATERIALS.contains(blockIn.getMaterial());
    }

    *//**
     * Called when this item is used when targetting a Block
     *//*
    @Nonnull
    @Override
    public ActionResultType useOn(ItemUseContext context) {
        World world = context.getLevel();
        BlockPos blockpos = context.getClickedPos();
        BlockState blockstate = world.getBlockState(blockpos);

        if (context.getClickedFace() == Direction.DOWN) {
            return ActionResultType.PASS;
        } else {
            PlayerEntity playerentity = context.getPlayer();
            BlockState effectiveBlockState = SHOVEL_LOOKUP.get(blockstate.getBlock());
            BlockState effective = null;

            if (effectiveBlockState != null && world.isEmptyBlock(blockpos.above())) {
                world.playSound(playerentity, blockpos, SoundEvents.SHOVEL_FLATTEN, SoundCategory.BLOCKS, 1.0F, 1.0F);
                effective = effectiveBlockState;
            } else if (blockstate.getBlock() instanceof CampfireBlock && blockstate.getValue(CampfireBlock.LIT)) {
                world.levelEvent(null, 1009, blockpos, 0);
                effective = blockstate.setValue(CampfireBlock.LIT, Boolean.FALSE);
            }

            if (effective != null) {
                if (!world.isClientSide) {
                    world.setBlock(blockpos, effective, 11);

                    if (playerentity != null) {
                        context.getItemInHand().hurtAndBreak(1, playerentity, (playerEntity) -> playerEntity.broadcastBreakEvent(context.getHand()));
                    }
                }

                return ActionResultType.SUCCESS;
            } else {
                return ActionResultType.PASS;
            }
        }
    }*/
}
