package com.yanny.age.stone.api.items;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.item.Tier;

import java.util.Set;



public class AgesPickaxeItem extends AgesToolItem {
    public AgesPickaxeItem(float attackDamageIn, float attackSpeedIn, Tier tier, Set<Block> effectiveBlocksIn, Properties builder, boolean displayAttackDamage, boolean displayAttackSpeed, boolean displayEfficiency) {
        super(attackDamageIn, attackSpeedIn, tier, effectiveBlocksIn, builder, displayAttackDamage, displayAttackSpeed, displayEfficiency);
    }
    /*private static final Set<Block> EFFECTIVE_ON;
    private static final Set<Material> EFFECTIVE_MATERIALS;

    static {
        EFFECTIVE_ON = getPrivateValue(PickaxeItem.class, null, 0);
        EFFECTIVE_MATERIALS = Sets.newHashSet(STONE, METAL, HEAVY_METAL);
    }

    public AgesPickaxeItem(Tier tier, float attackDamageIn, float attackSpeedIn, Properties builder) {
        super(attackDamageIn, attackSpeedIn, tier, EFFECTIVE_ON, builder, true, true, true);
    }

    *//**
     * Check whether this Item can harvest the given Block
     *//*
    @Override
    public boolean isCorrectToolForDrops(BlockState blockIn) {
        int i = this.getTier().getLevel();

        if (blockIn.getHarvestTool() == ToolType.PICKAXE) {
            return i >= blockIn.getHarvestLevel();
        }

        return EFFECTIVE_MATERIALS.contains(blockIn.getMaterial());
    }

    @Override
    public float getDestroySpeed(@Nonnull ItemStack stack, BlockState state) {
        return !EFFECTIVE_MATERIALS.contains(state.getMaterial()) ? super.getDestroySpeed(stack, state) : this.efficiency + getAdditionalEfficiency(stack);
    }*/
}
