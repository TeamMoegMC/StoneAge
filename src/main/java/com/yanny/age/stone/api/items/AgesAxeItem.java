package com.yanny.age.stone.api.items;

import net.minecraft.world.item.Tier;
import net.minecraft.world.level.block.Block;

import java.util.Set;

public class AgesAxeItem extends AgesToolItem {
    public AgesAxeItem(float attackDamageIn, float attackSpeedIn, Tier tier, Set<Block> effectiveBlocksIn, Properties builder, boolean displayAttackDamage, boolean displayAttackSpeed, boolean displayEfficiency) {
        super(attackDamageIn, attackSpeedIn, tier, effectiveBlocksIn, builder, displayAttackDamage, displayAttackSpeed, displayEfficiency);
    }
    /*private static final Set<Block> EFFECTIVE_ON;
    private static final Set<Block> BLOCK_STRIPPING_MAP;
    private static final Set<Material> EFFECTIVE_MATERIALS;

    static {
        EFFECTIVE_ON = getPrivateValue(AxeItem.class, null, 0);
        BLOCK_STRIPPING_MAP = getPrivateValue(AxeItem.class, null, 1);
        EFFECTIVE_MATERIALS = Sets.newHashSet(WOOD, PLANT, REPLACEABLE_PLANT, BAMBOO, LEAVES, CACTUS);
    }

    public AgesAxeItem(Tier tier, float attackDamageIn, float attackSpeedIn, Properties builder) {
        super(attackDamageIn, attackSpeedIn, tier, EFFECTIVE_ON, builder, true, true, true);
    }
    @Override
    public boolean isCorrectToolForDrops(BlockState blockIn) {
        int i = this.getTier().getLevel();

        if (blockIn.getHarvestTool() == ToolType.AXE) {
            return i >= blockIn.getHarvestLevel();
        }

        return EFFECTIVE_MATERIALS.contains(blockIn.getMaterial());
    }

    @Override
    public float getDestroySpeed(@Nonnull ItemStack stack, BlockState state) {
        return !EFFECTIVE_MATERIALS.contains(state.getMaterial()) ? super.getDestroySpeed(stack, state) : efficiency + getAdditionalEfficiency(stack);
    }

    @Nonnull
    @Override
    public InteractionResult useOn(UseOnContext context) {
        Level world = context.getLevel();
        BlockPos blockpos = context.getClickedPos();
        BlockState blockstate = world.getBlockState(blockpos);
        Block block = blockstate.getBlock();

        if (BLOCK_STRIPPING_MAP.contains(block)) {
            Player playerentity = context.getPlayer();
            world.playSound(playerentity, blockpos, SoundEvents.AXE_STRIP, SoundSource.BLOCKS, 1.0F, 1.0F);

            if (!world.isClientSide) {
                world.setBlock(blockpos, block.defaultBlockState().setValue(RotatedPillarBlock.AXIS, blockstate.getValue(RotatedPillarBlock.AXIS)), 11);

                if (playerentity != null) {
                    context.getItemInHand().hurtAndBreak(1, playerentity, (playerEntity) -> playerEntity.broadcastBreakEvent(context.getHand()));
                }
            }

            return InteractionResult.SUCCESS;
        } else {
            return InteractionResult.PASS;
        }
    }*/
}
