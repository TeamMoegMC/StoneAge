package com.yanny.age.stone.blocks;

import com.yanny.age.stone.compatibility.top.TopBlockInfoProvider;
import mcjty.theoneprobe.api.IProbeHitData;
import mcjty.theoneprobe.api.IProbeInfo;
import mcjty.theoneprobe.api.ProbeMode;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemTier;
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

public class TreeStumpBlock extends Block implements TopBlockInfoProvider {
    private static final VoxelShape SHAPE = VoxelShapes.or(Block.box(0, 0, 0, 16, 1, 16),
            Block.box(2, 1, 2, 14, 12, 14));

    public TreeStumpBlock() {
        super(Properties.of(Material.WOOD).harvestLevel(ItemTier.WOOD.getLevel()).harvestTool(ToolType.AXE).strength(2.0f));
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new TreeStumpTileEntity();
    }

    @SuppressWarnings("deprecation")
    @Nonnull
    @Override
    public VoxelShape getShape(@Nonnull BlockState state, @Nonnull IBlockReader worldIn, @Nonnull BlockPos pos, @Nonnull ISelectionContext context) {
        return SHAPE;
    }

    @SuppressWarnings("deprecation")
    @Override
    public void attack(@Nonnull BlockState state, World worldIn, @Nonnull BlockPos pos, @Nonnull PlayerEntity player) {
        if (!worldIn.isClientSide) {
            TreeStumpTileEntity tileEntity = (TreeStumpTileEntity) worldIn.getBlockEntity(pos);

            if (tileEntity != null) {
                tileEntity.onBlockRightClicked(player);
                return;
            }
        }

        super.attack(state, worldIn, pos, player);
    }

    @Nonnull
    @SuppressWarnings("deprecation")
    @Override
    public ActionResultType use(@Nonnull BlockState state, World worldIn, @Nonnull BlockPos pos, @Nonnull PlayerEntity player,
                                             @Nonnull Hand handIn, @Nonnull BlockRayTraceResult hit) {
        TileEntity tileentity = worldIn.getBlockEntity(pos);

        if ((tileentity instanceof TreeStumpTileEntity) && !worldIn.isClientSide && (handIn == Hand.MAIN_HAND)) {
            ((TreeStumpTileEntity) tileentity).blockActivated(player);
        }

        return ActionResultType.SUCCESS;
    }

    @SuppressWarnings("deprecation")
    @Override
    public float getDestroyProgress(@Nonnull BlockState state, @Nonnull PlayerEntity player, @Nonnull IBlockReader worldIn, @Nonnull BlockPos pos) {
        TileEntity tileentity = worldIn.getBlockEntity(pos);

        if ((tileentity instanceof TreeStumpTileEntity) && ((TreeStumpTileEntity) tileentity).hasTool(player.getMainHandItem())) {
            return 0.0f;
        }

        return super.getDestroyProgress(state, player, worldIn, pos);
    }

    @Override
    public void addProbeInfo(@Nonnull ProbeMode probeMode, @Nonnull IProbeInfo iProbeInfo, @Nonnull PlayerEntity playerEntity,
                             @Nonnull World world, @Nonnull BlockState blockState, @Nonnull IProbeHitData iProbeHitData) {
        TileEntity te = world.getBlockEntity(iProbeHitData.getPos());

        if (te instanceof TreeStumpTileEntity) {
            TreeStumpTileEntity treeStump = (TreeStumpTileEntity) te;

            if (!treeStump.getResult().isEmpty()) {
                iProbeInfo.horizontal().item(treeStump.getResult()).progress(treeStump.getProgress(), 100, iProbeInfo.defaultProgressStyle().suffix("%"));
            }
        }
    }
}
