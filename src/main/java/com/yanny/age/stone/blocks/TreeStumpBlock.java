package com.yanny.age.stone.blocks;

import com.yanny.age.stone.compatibility.top.TopBlockInfoProvider;
import mcjty.theoneprobe.api.IProbeHitData;
import mcjty.theoneprobe.api.IProbeInfo;
import mcjty.theoneprobe.api.ProbeMode;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Tiers;
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

public class TreeStumpBlock extends Block implements TopBlockInfoProvider {
    private static final VoxelShape SHAPE = Shapes.or(Block.box(0, 0, 0, 16, 1, 16),
            Block.box(2, 1, 2, 14, 12, 14));

    public TreeStumpBlock() {
        super(Properties.of(Material.WOOD).harvestLevel(Tiers.WOOD.getLevel()).harvestTool(ToolType.AXE).strength(2.0f));
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Nullable
    @Override
    public BlockEntity createTileEntity(BlockState state, BlockGetter world) {
        return new TreeStumpTileEntity();
    }

    @SuppressWarnings("deprecation")
    @Nonnull
    @Override
    public VoxelShape getShape(@Nonnull BlockState state, @Nonnull BlockGetter worldIn, @Nonnull BlockPos pos, @Nonnull CollisionContext context) {
        return SHAPE;
    }

    @SuppressWarnings("deprecation")
    @Override
    public void attack(@Nonnull BlockState state, Level worldIn, @Nonnull BlockPos pos, @Nonnull Player player) {
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
    public InteractionResult use(@Nonnull BlockState state, Level worldIn, @Nonnull BlockPos pos, @Nonnull Player player,
                                             @Nonnull InteractionHand handIn, @Nonnull BlockHitResult hit) {
        BlockEntity tileentity = worldIn.getBlockEntity(pos);

        if ((tileentity instanceof TreeStumpTileEntity) && !worldIn.isClientSide && (handIn == InteractionHand.MAIN_HAND)) {
            ((TreeStumpTileEntity) tileentity).blockActivated(player);
        }

        return InteractionResult.SUCCESS;
    }

    @SuppressWarnings("deprecation")
    @Override
    public float getDestroyProgress(@Nonnull BlockState state, @Nonnull Player player, @Nonnull BlockGetter worldIn, @Nonnull BlockPos pos) {
        BlockEntity tileentity = worldIn.getBlockEntity(pos);

        if ((tileentity instanceof TreeStumpTileEntity) && ((TreeStumpTileEntity) tileentity).hasTool(player.getMainHandItem())) {
            return 0.0f;
        }

        return super.getDestroyProgress(state, player, worldIn, pos);
    }

    @Override
    public void addProbeInfo(@Nonnull ProbeMode probeMode, @Nonnull IProbeInfo iProbeInfo, @Nonnull Player playerEntity,
                             @Nonnull Level world, @Nonnull BlockState blockState, @Nonnull IProbeHitData iProbeHitData) {
        BlockEntity te = world.getBlockEntity(iProbeHitData.getPos());

        if (te instanceof TreeStumpTileEntity) {
            TreeStumpTileEntity treeStump = (TreeStumpTileEntity) te;

            if (!treeStump.getResult().isEmpty()) {
                iProbeInfo.horizontal().item(treeStump.getResult()).progress(treeStump.getProgress(), 100, iProbeInfo.defaultProgressStyle().suffix("%"));
            }
        }
    }
}
