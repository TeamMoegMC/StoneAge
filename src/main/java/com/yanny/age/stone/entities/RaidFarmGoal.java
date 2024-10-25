package com.yanny.age.stone.entities;

import net.minecraft.block.*;
import net.minecraft.entity.ai.goal.MoveToBlockGoal;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.state.IntegerProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraftforge.event.ForgeEventFactory;

import javax.annotation.Nonnull;

class RaidFarmGoal<T extends AnimalEntity> extends MoveToBlockGoal {
    private final T entity;
    private final Class<? extends CropsBlock> cropsBlock;
    private final IntegerProperty ageProperty;
    private boolean wantsToRaid;
    private boolean canRaid;

    RaidFarmGoal(@Nonnull T entity, @Nonnull Class<? extends CropsBlock> cropsBlock, @Nonnull IntegerProperty ageProperty) {
        super(entity, 0.7F, 16);
        this.entity = entity;
        this.cropsBlock = cropsBlock;
        this.ageProperty = ageProperty;
    }

    @Override
    public boolean canUse() {
        if (this.nextStartTick <= 0) {
            if (!ForgeEventFactory.getMobGriefingEvent(this.entity.level, this.entity)) {
                return false;
            }

            this.canRaid = false;
            this.wantsToRaid = true;
        }

        return super.canUse();
    }

    @Override
    public boolean canContinueToUse() {
        return this.canRaid && super.canContinueToUse();
    }

    @Override
    public void tick() {
        super.tick();
        this.entity.getLookControl().setLookAt(
                (double)this.blockPos.getX() + 0.5D,
                this.blockPos.getY() + 1,
                (double)this.blockPos.getZ() + 0.5D,
                10.0F, (float)this.entity.getMaxHeadXRot());

        if (this.isReachedTarget()) {
            World world = this.entity.level;
            BlockPos blockpos = this.blockPos.above();
            BlockState blockstate = world.getBlockState(blockpos);
            Block block = blockstate.getBlock();

            if (this.canRaid && cropsBlock.isAssignableFrom(block.getClass())) {
                int integer;

                if (block instanceof BeetrootBlock) {
                    integer = blockstate.getValue(BeetrootBlock.AGE);
                } else {
                    integer = blockstate.getValue(ageProperty);
                }

                if (integer == 0) {
                    world.setBlock(blockpos, Blocks.AIR.defaultBlockState(), 2);
                    world.destroyBlock(blockpos, true);
                } else {
                    world.setBlock(blockpos, blockstate.setValue((block instanceof BeetrootBlock ? BeetrootBlock.AGE : ageProperty), integer - 1), 2);
                    world.levelEvent(2001, blockpos, Block.getId(blockstate));
                }
            }

            this.canRaid = false;
            this.nextStartTick = 10;
        }

    }

    @Override
    protected boolean isValidTarget(IWorldReader worldIn, @Nonnull BlockPos pos) {
        Block block = worldIn.getBlockState(pos).getBlock();
        if (block == Blocks.FARMLAND && this.wantsToRaid && !this.canRaid) {
            pos = pos.above();
            BlockState blockstate = worldIn.getBlockState(pos);
            block = blockstate.getBlock();

            if (cropsBlock.isAssignableFrom(block.getClass()) && cropsBlock.cast(block).isMaxAge(blockstate)) {
                this.canRaid = true;
                return true;
            }
        }

        return false;
    }
}
