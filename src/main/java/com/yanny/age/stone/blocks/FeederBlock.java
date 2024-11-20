package com.yanny.age.stone.blocks;

import com.yanny.age.stone.subscribers.TileEntitySubscriber;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.entity.player.Player;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.Containers;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.InteractionResult;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.core.BlockPos;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.NetworkHooks;

import javax.annotation.Nonnull;

public class FeederBlock extends HorizontalDirectionalBlock implements EntityBlock {
    private static final VoxelShape SHAPE_NS = Block.box(0, 0, 4, 16, 6, 12);
    private static final VoxelShape SHAPE_EW = Block.box(4, 0, 0, 12, 6, 16);

    public FeederBlock() {
        super(Properties.of().requiresCorrectToolForDrops().strength(2.0f));
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new FeederTileEntity(pos,state);
    }
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level world, BlockState state, BlockEntityType<T> type) {
        return createTickerHelper(type, TileEntitySubscriber.feeder,FeederTileEntity::tick);
    }
    protected static <E extends BlockEntity, A extends BlockEntity> BlockEntityTicker<A> createTickerHelper(BlockEntityType<A> type, BlockEntityType<E> entitytype, BlockEntityTicker<? super E> ticker) {
        return type == entitytype ? (BlockEntityTicker<A>) ticker : null;
    }
    @SuppressWarnings("deprecation")
    @Nonnull
    @Override
    public VoxelShape getShape(BlockState state, @Nonnull BlockGetter worldIn, @Nonnull BlockPos pos, @Nonnull CollisionContext context) {
        if (state.getValue(FACING).getAxis() == Direction.Axis.Z) {
            return SHAPE_NS;
        } else {
            return SHAPE_EW;
        }
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    @SuppressWarnings("deprecation")
    @Override
    public void onRemove(BlockState state, @Nonnull Level worldIn, @Nonnull BlockPos pos, BlockState newState, boolean isMoving) {
        if (state.getBlock() != newState.getBlock()) {
            BlockEntity tileentity = worldIn.getBlockEntity(pos);

            if (tileentity instanceof FeederTileEntity) {
                Containers.dropContents(worldIn, pos, ((FeederTileEntity)tileentity).getInventory());
            }

            super.onRemove(state, worldIn, pos, newState, isMoving);
        }
    }

    @Nonnull
    @SuppressWarnings("deprecation")
    @Override
    public InteractionResult use(@Nonnull BlockState state, Level worldIn, @Nonnull BlockPos pos, @Nonnull Player player,
                                             @Nonnull InteractionHand handIn, @Nonnull BlockHitResult hit) {
        FeederTileEntity tile = (FeederTileEntity) worldIn.getBlockEntity(pos);

        if (tile != null) {
            if (!player.isShiftKeyDown()) {
                if (!worldIn.isClientSide) {
                    NetworkHooks.openScreen((ServerPlayer) player, tile, tile.getBlockPos());
                }
                return InteractionResult.SUCCESS;
            }

            InteractionResult resultType = super.use(state, worldIn, pos, player, handIn, hit);

            if (player.isShiftKeyDown() && resultType == InteractionResult.PASS) {
                if (!worldIn.isClientSide) {
                    NetworkHooks.openScreen((ServerPlayer) player, tile, tile.getBlockPos());
                }
                return InteractionResult.SUCCESS;
            }
        } else {
            throw new IllegalStateException("Named container provider is missing");
        }

        return super.use(state, worldIn, pos, player, handIn, hit);
    }
}
