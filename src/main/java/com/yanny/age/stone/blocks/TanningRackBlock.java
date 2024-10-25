package com.yanny.age.stone.blocks;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.Containers;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionHand;
import net.minecraft.core.BlockPos;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.ToolType;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import net.minecraft.world.level.block.state.BlockBehaviour.Properties;

public class TanningRackBlock extends HorizontalDirectionalBlock {
    private static final VoxelShape SHAPE_N = Shapes.or(
            Block.box(0.0D, 13.5D, 7.0D, 16.0D, 14.5D, 8.0D),
            Block.box(0.0D, 1.0D, 1.5D, 16.0D, 2.0D, 2.5D),
            Block.box(0.0D, 0.0, 7.5D, 16.0D, 15.0D, 8.5D));
    private static final VoxelShape SHAPE_S = Shapes.or(
            Block.box(0.0D, 13.5D, 8.0D, 16.0D, 14.5D, 9.0D),
            Block.box(0.0D, 1.0D, 13.5D, 16.0D, 2.0D, 14.5D),
            Block.box(0.0D, 0.0, 7.5D, 16.0D, 15.0D, 8.5D));
    private static final VoxelShape SHAPE_W = Shapes.or(
            Block.box(7.0D, 13.5D, 0.0D, 8.0D, 14.5D, 16.0D),
            Block.box(1.5D, 1.0D, 0.0D, 2.5D, 2.0D, 16.0D),
            Block.box(7.5D, 0.0, 0.0D, 8.5D, 15.0D, 16D));
    private static final VoxelShape SHAPE_E = Shapes.or(
            Block.box(8.0D, 13.5D, 0.0D, 9.0D, 14.5D, 16.0D),
            Block.box(13.5D, 1.0D, 0.0D, 14.5D, 2.0D, 16.0D),
            Block.box(7.5D, 0.0, 0.0D, 8.5D, 15.0D, 16D));

    public TanningRackBlock() {
        super(Properties.of(Material.WOOD).harvestLevel(Tiers.WOOD.getLevel()).harvestTool(ToolType.AXE).strength(2.0f));
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Nullable
    @Override
    public BlockEntity createTileEntity(BlockState state, BlockGetter world) {
        return new TanningRackTileEntity();
    }

    @SuppressWarnings("deprecation")
    @Override
    @Nonnull
    public RenderShape getRenderShape(@Nonnull BlockState state) {
        return RenderShape.MODEL;
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    @Override
    public boolean hasDynamicShape() {
        return true;
    }

    @Nonnull
    @SuppressWarnings("deprecation")
    @Override
    public InteractionResult use(@Nonnull BlockState state, Level worldIn, @Nonnull BlockPos pos, @Nonnull Player player,
                                             @Nonnull InteractionHand handIn, @Nonnull BlockHitResult hit) {
        BlockEntity tileentity = worldIn.getBlockEntity(pos);

        if (tileentity instanceof TanningRackTileEntity && !worldIn.isClientSide && (handIn == InteractionHand.MAIN_HAND)) {
            return ((TanningRackTileEntity) tileentity).blockActivated(player);
        }

        return super.use(state, worldIn, pos, player, handIn, hit);
    }

    @SuppressWarnings("deprecation")
    @Nonnull
    @Override
    public VoxelShape getShape(BlockState state, @Nonnull BlockGetter worldIn, @Nonnull BlockPos pos, @Nonnull CollisionContext context) {
        switch (state.getValue(FACING)) {
            case NORTH:
                return SHAPE_N;
            case SOUTH:
                return SHAPE_S;
            case WEST:
                return SHAPE_W;
            case EAST:
                return SHAPE_E;
        }

        return Shapes.block();
    }

    @SuppressWarnings("deprecation")
    @Override
    public void onRemove(BlockState state, @Nonnull Level worldIn, @Nonnull BlockPos pos, BlockState newState, boolean isMoving) {
        if (state.getBlock() != newState.getBlock()) {
            BlockEntity tileentity = worldIn.getBlockEntity(pos);

            if (tileentity instanceof TanningRackTileEntity) {
                Containers.dropContents(worldIn, pos, ((TanningRackTileEntity)tileentity).getInventory());
            }

            super.onRemove(state, worldIn, pos, newState, isMoving);
        }
    }
}
