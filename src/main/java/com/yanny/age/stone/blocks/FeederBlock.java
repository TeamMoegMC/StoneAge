package com.yanny.age.stone.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemTier;
import net.minecraft.state.StateContainer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fml.network.NetworkHooks;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import net.minecraft.block.AbstractBlock.Properties;

public class FeederBlock extends HorizontalBlock {
    private static final VoxelShape SHAPE_NS = Block.box(0, 0, 4, 16, 6, 12);
    private static final VoxelShape SHAPE_EW = Block.box(4, 0, 0, 12, 6, 16);

    public FeederBlock() {
        super(Properties.of(Material.WOOD).harvestLevel(ItemTier.WOOD.getLevel()).harvestTool(ToolType.AXE).strength(2.0f));
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new FeederTileEntity();
    }

    @SuppressWarnings("deprecation")
    @Nonnull
    @Override
    public VoxelShape getShape(BlockState state, @Nonnull IBlockReader worldIn, @Nonnull BlockPos pos, @Nonnull ISelectionContext context) {
        if (state.getValue(FACING).getAxis() == Direction.Axis.Z) {
            return SHAPE_NS;
        } else {
            return SHAPE_EW;
        }
    }

    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
    }

    @Override
    protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    @SuppressWarnings("deprecation")
    @Override
    public void onRemove(BlockState state, @Nonnull World worldIn, @Nonnull BlockPos pos, BlockState newState, boolean isMoving) {
        if (state.getBlock() != newState.getBlock()) {
            TileEntity tileentity = worldIn.getBlockEntity(pos);

            if (tileentity instanceof FeederTileEntity) {
                InventoryHelper.dropContents(worldIn, pos, ((FeederTileEntity)tileentity).getInventory());
            }

            super.onRemove(state, worldIn, pos, newState, isMoving);
        }
    }

    @Nonnull
    @SuppressWarnings("deprecation")
    @Override
    public ActionResultType use(@Nonnull BlockState state, World worldIn, @Nonnull BlockPos pos, @Nonnull PlayerEntity player,
                                             @Nonnull Hand handIn, @Nonnull BlockRayTraceResult hit) {
        FeederTileEntity tile = (FeederTileEntity) worldIn.getBlockEntity(pos);

        if (tile != null) {
            if (!player.isShiftKeyDown()) {
                if (!worldIn.isClientSide) {
                    NetworkHooks.openGui((ServerPlayerEntity) player, tile, tile.getBlockPos());
                }
                return ActionResultType.SUCCESS;
            }

            ActionResultType resultType = super.use(state, worldIn, pos, player, handIn, hit);

            if (player.isShiftKeyDown() && resultType == ActionResultType.PASS) {
                if (!worldIn.isClientSide) {
                    NetworkHooks.openGui((ServerPlayerEntity) player, tile, tile.getBlockPos());
                }
                return ActionResultType.SUCCESS;
            }
        } else {
            throw new IllegalStateException("Named container provider is missing");
        }

        return super.use(state, worldIn, pos, player, handIn, hit);
    }
}
