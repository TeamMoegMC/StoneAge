package com.yanny.age.stone.blocks;

import com.yanny.age.stone.compatibility.top.TopBlockInfoProvider;
import mcjty.theoneprobe.api.IProbeHitData;
import mcjty.theoneprobe.api.IProbeInfo;
import mcjty.theoneprobe.api.ProbeMode;
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
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class DryingRackBlock extends HorizontalBlock implements TopBlockInfoProvider {
    private static final VoxelShape SHAPE_NS = Block.makeCuboidShape(0.0D, 15.0D, 7.5D, 16.0D, 16.0D, 8.5D);
    private static final VoxelShape SHAPE_EW = Block.makeCuboidShape(7.5D, 15.0D, 0.0D, 8.5D, 16.0D, 16.0D);

    public DryingRackBlock() {
        super(Block.Properties.create(Material.WOOD).harvestLevel(ItemTier.WOOD.getHarvestLevel()).harvestTool(ToolType.AXE).hardnessAndResistance(2.0f));
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new DryingRackTileEntity();
    }

    @SuppressWarnings("deprecation")
    @Override
    @Nonnull
    public BlockRenderType getRenderType(@Nonnull BlockState state) {
        return BlockRenderType.MODEL;
    }

    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        return this.getDefaultState().with(HORIZONTAL_FACING, context.getPlacementHorizontalFacing().getOpposite());
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(HORIZONTAL_FACING);
    }

    @Override
    public boolean isVariableOpacity() {
        return true;
    }

    @Nonnull
    @SuppressWarnings("deprecation")
    @Override
    public ActionResultType onBlockActivated(@Nonnull BlockState state, World worldIn, @Nonnull BlockPos pos, @Nonnull PlayerEntity player,
                                             @Nonnull Hand handIn, @Nonnull BlockRayTraceResult hit) {
        TileEntity tileentity = worldIn.getTileEntity(pos);

        if (tileentity instanceof DryingRackTileEntity) {
            ((DryingRackTileEntity) tileentity).blockActivated(player);
            return ActionResultType.CONSUME;
        }

        return super.onBlockActivated(state, worldIn, pos, player, handIn, hit);
    }

    @SuppressWarnings("deprecation")
    @Nonnull
    @Override
    public VoxelShape getShape(BlockState state, @Nonnull IBlockReader worldIn, @Nonnull BlockPos pos, @Nonnull ISelectionContext context) {
        if (state.get(HORIZONTAL_FACING).getAxis() == Direction.Axis.Z) {
            return SHAPE_NS;
        } else {
            return SHAPE_EW;
        }
    }

    @SuppressWarnings("deprecation")
    @Override
    public void onReplaced(BlockState state, @Nonnull World worldIn, @Nonnull BlockPos pos, BlockState newState, boolean isMoving) {
        if (state.getBlock() != newState.getBlock()) {
            TileEntity tileentity = worldIn.getTileEntity(pos);

            if (tileentity instanceof DryingRackTileEntity) {
                InventoryHelper.dropInventoryItems(worldIn, pos, ((DryingRackTileEntity)tileentity).getInventory());
            }

            super.onReplaced(state, worldIn, pos, newState, isMoving);
        }
    }

    @Override
    public void addProbeInfo(ProbeMode probeMode, IProbeInfo iProbeInfo, PlayerEntity playerEntity, World world, BlockState blockState, IProbeHitData iProbeHitData) {
        TileEntity te = world.getTileEntity(iProbeHitData.getPos());

        if (te instanceof DryingRackTileEntity) {
            DryingRackTileEntity dataTileEntity = (DryingRackTileEntity) te;

            for (int i = 0; i < DryingRackTileEntity.ITEMS; i++) {
                DryingRackTileEntity.DryingItem item = dataTileEntity.getItem(i);

                if (!item.result.isEmpty()) {
                    iProbeInfo.horizontal().item(item.result).progress(100 - (int) (item.remaining / (float) item.dryingTime * 100), 100,
                            iProbeInfo.defaultProgressStyle().suffix("%"));
                }
            }
        }
    }
}
