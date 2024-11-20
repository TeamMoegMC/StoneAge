package com.yanny.age.stone.blocks;

import com.yanny.age.stone.subscribers.TileEntitySubscriber;
import net.minecraft.world.entity.player.Player;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.Containers;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.InteractionResult;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.core.BlockPos;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import static net.minecraft.world.level.block.state.properties.BlockStateProperties.*;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.network.NetworkHooks;

public class FishingNetBlock extends Block implements SimpleWaterloggedBlock, EntityBlock {
    private static final VoxelShape SHAPE = Shapes.or(
            Block.box(0, 0, 0, 16, 1, 16),
            Block.box(0, 1, 0, 2, 16, 2),
            Block.box(14, 1, 0, 16, 16, 2),
            Block.box(0, 1, 14, 2, 16, 16),
            Block.box(14, 1, 14, 16, 16, 16)
    );

    public FishingNetBlock() {
        super(Properties.of().strength(2.0f));
        registerDefaultState(defaultBlockState().setValue(WATERLOGGED, false).setValue(ATTACHED, false));
    }
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new FishingNetTileEntity(pos,state);
    }
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level world, BlockState state, BlockEntityType<T> type) {
        return createTickerHelper(type, TileEntitySubscriber.fishing_net,FishingNetTileEntity::tick);
    }
    protected static <E extends BlockEntity, A extends BlockEntity> BlockEntityTicker<A> createTickerHelper(BlockEntityType<A> type, BlockEntityType<E> entitytype, BlockEntityTicker<? super E> ticker) {
        return type == entitytype ? (BlockEntityTicker<A>) ticker : null;
    }
    @Override
    public boolean hasDynamicShape() {
        return true;
    }

    @Nullable

    @SuppressWarnings("deprecation")
    @Nonnull
    @Override
    public VoxelShape getShape(@Nonnull BlockState state, @Nonnull BlockGetter worldIn, @Nonnull BlockPos pos, @Nonnull CollisionContext context) {
        return SHAPE;
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState().setValue(WATERLOGGED, context.getLevel().getFluidState(context.getClickedPos()).getType() == Fluids.WATER);
    }

    @SuppressWarnings("deprecation")
    @Nonnull
    @Override
    public FluidState getFluidState(BlockState state) {
        return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
    }

    @Override
    protected void createBlockStateDefinition(@Nonnull StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(WATERLOGGED);
        builder.add(ATTACHED);
    }

    @SuppressWarnings("deprecation")
    @Override
    public void onRemove(BlockState state, @Nonnull Level worldIn, @Nonnull BlockPos pos, BlockState newState, boolean isMoving) {
        if (state.getBlock() != newState.getBlock()) {
            BlockEntity tileentity = worldIn.getBlockEntity(pos);

            if (tileentity instanceof FishingNetTileEntity) {
                Containers.dropContents(worldIn, pos, ((FishingNetTileEntity)tileentity).getInventory());
            }

            super.onRemove(state, worldIn, pos, newState, isMoving);
        }
    }

    @Nonnull
    @SuppressWarnings("deprecation")
    @Override
    public InteractionResult use(@Nonnull BlockState state, Level worldIn, @Nonnull BlockPos pos, @Nonnull Player player,
                                             @Nonnull InteractionHand handIn, @Nonnull BlockHitResult hit) {
        FishingNetTileEntity tile = (FishingNetTileEntity) worldIn.getBlockEntity(pos);

        if (tile != null) {
            if (!worldIn.isClientSide) {
                NetworkHooks.openScreen((ServerPlayer) player, tile, tile.getBlockPos());
            }
            return InteractionResult.SUCCESS;
        } else {
            throw new IllegalStateException("Named container provider is missing");
        }
    }

    @SuppressWarnings("deprecation")
    @Nonnull
    @Override
    public BlockState updateShape(@Nonnull BlockState stateIn, @Nonnull Direction facing, @Nonnull BlockState facingState,
                                          LevelAccessor worldIn, @Nonnull BlockPos currentPos, @Nonnull BlockPos facingPos) {
        worldIn.scheduleTick(currentPos, Fluids.WATER, Fluids.WATER.getTickDelay(worldIn));
        return super.updateShape(stateIn, facing, facingState, worldIn, currentPos, facingPos);
    }

    @SuppressWarnings("deprecation")
    @Override
    @Nonnull
    public RenderShape getRenderShape(@Nonnull BlockState state) {
        return RenderShape.MODEL;
    }
}
