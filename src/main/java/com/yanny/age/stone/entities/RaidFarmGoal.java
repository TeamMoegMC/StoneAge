package com.yanny.age.stone.entities;

import net.minecraft.block.*;
import net.minecraft.world.entity.ai.goal.MoveToBlockGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.ForgeEventFactory;

import javax.annotation.Nonnull;

import net.minecraft.world.level.block.BeetrootBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;

class RaidFarmGoal<T extends Animal> extends MoveToBlockGoal {
    private final T entity;
    private final Class<? extends CropBlock> cropsBlock;
    private final IntegerProperty ageProperty;
    private boolean wantsToRaid;
    private boolean canRaid;

    RaidFarmGoal(@Nonnull T entity, @Nonnull Class<? extends CropBlock> cropsBlock, @Nonnull IntegerProperty ageProperty) {
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
            Level world = this.entity.level;
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
    protected boolean isValidTarget(LevelReader worldIn, @Nonnull BlockPos pos) {
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
