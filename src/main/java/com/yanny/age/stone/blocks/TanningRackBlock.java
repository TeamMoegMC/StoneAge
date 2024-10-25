package com.yanny.age.stone.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemTier;
import net.minecraft.state.StateContainer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import net.minecraft.block.AbstractBlock.Properties;

public class TanningRackBlock extends HorizontalBlock {
    private static final VoxelShape SHAPE_N = VoxelShapes.or(
            Block.box(0.0D, 13.5D, 7.0D, 16.0D, 14.5D, 8.0D),
            Block.box(0.0D, 1.0D, 1.5D, 16.0D, 2.0D, 2.5D),
            Block.box(0.0D, 0.0, 7.5D, 16.0D, 15.0D, 8.5D));
    private static final VoxelShape SHAPE_S = VoxelShapes.or(
            Block.box(0.0D, 13.5D, 8.0D, 16.0D, 14.5D, 9.0D),
            Block.box(0.0D, 1.0D, 13.5D, 16.0D, 2.0D, 14.5D),
            Block.box(0.0D, 0.0, 7.5D, 16.0D, 15.0D, 8.5D));
    private static final VoxelShape SHAPE_W = VoxelShapes.or(
            Block.box(7.0D, 13.5D, 0.0D, 8.0D, 14.5D, 16.0D),
            Block.box(1.5D, 1.0D, 0.0D, 2.5D, 2.0D, 16.0D),
            Block.box(7.5D, 0.0, 0.0D, 8.5D, 15.0D, 16D));
    private static final VoxelShape SHAPE_E = VoxelShapes.or(
            Block.box(8.0D, 13.5D, 0.0D, 9.0D, 14.5D, 16.0D),
            Block.box(13.5D, 1.0D, 0.0D, 14.5D, 2.0D, 16.0D),
            Block.box(7.5D, 0.0, 0.0D, 8.5D, 15.0D, 16D));

    public TanningRackBlock() {
        super(Properties.of(Material.WOOD).harvestLevel(ItemTier.WOOD.getLevel()).harvestTool(ToolType.AXE).strength(2.0f));
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new TanningRackTileEntity();
    }

    @SuppressWarnings("deprecation")
    @Override
    @Nonnull
    public BlockRenderType getRenderShape(@Nonnull BlockState state) {
        return BlockRenderType.MODEL;
    }

    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
    }

    @Override
    protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    @Override
    public boolean hasDynamicShape() {
        return true;
    }

    @Nonnull
    @SuppressWarnings("deprecation")
    @Override
    public ActionResultType use(@Nonnull BlockState state, World worldIn, @Nonnull BlockPos pos, @Nonnull PlayerEntity player,
                                             @Nonnull Hand handIn, @Nonnull BlockRayTraceResult hit) {
        TileEntity tileentity = worldIn.getBlockEntity(pos);

        if (tileentity instanceof TanningRackTileEntity && !worldIn.isClientSide && (handIn == Hand.MAIN_HAND)) {
            return ((TanningRackTileEntity) tileentity).blockActivated(player);
        }

        return super.use(state, worldIn, pos, player, handIn, hit);
    }

    @SuppressWarnings("deprecation")
    @Nonnull
    @Override
    public VoxelShape getShape(BlockState state, @Nonnull IBlockReader worldIn, @Nonnull BlockPos pos, @Nonnull ISelectionContext context) {
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

        return VoxelShapes.block();
    }

    @SuppressWarnings("deprecation")
    @Override
    public void onRemove(BlockState state, @Nonnull World worldIn, @Nonnull BlockPos pos, BlockState newState, boolean isMoving) {
        if (state.getBlock() != newState.getBlock()) {
            TileEntity tileentity = worldIn.getBlockEntity(pos);

            if (tileentity instanceof TanningRackTileEntity) {
                InventoryHelper.dropContents(worldIn, pos, ((TanningRackTileEntity)tileentity).getInventory());
            }

            super.onRemove(state, worldIn, pos, newState, isMoving);
        }
    }
}
