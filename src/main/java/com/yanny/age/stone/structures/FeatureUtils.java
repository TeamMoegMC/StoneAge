package com.yanny.age.stone.structures;

import com.yanny.age.stone.blocks.DryingRackTileEntity;
import com.yanny.age.stone.blocks.FlintWorkbenchTileEntity;
import com.yanny.age.stone.blocks.StoneChestTileEntity;
import com.yanny.age.stone.blocks.TanningRackTileEntity;
import com.yanny.age.stone.subscribers.BlockSubscriber;
import com.yanny.age.stone.subscribers.ItemSubscriber;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.block.material.Material;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.properties.BedPart;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.WorldGenLevel;
import org.joml.Matrix3f;
import org.joml.Vector3f;

import javax.annotation.Nonnull;
import java.util.Random;

public class FeatureUtils {

    public static void fillWithAir(WorldGenLevel seedReader, BlockPos pos, int minX, int minY, int minZ, int maxX, int maxY, int maxZ) {
        for(int i = minY; i <= maxY; ++i) {
            for(int j = minX; j <= maxX; ++j) {
                for(int k = minZ; k <= maxZ; ++k) {
                    seedReader.setBlock(pos.offset(j, i, k), Blocks.AIR.defaultBlockState(), 2);
                }
            }
        }

    }

    public static void fillWithBlocks(WorldGenLevel seedReader, BlockPos pos, int xMin, int yMin, int zMin, int xMax, int yMax, int zMax, BlockState boundaryBlockState, BlockState insideBlockState, boolean existingOnly) {
        for(int i = yMin; i <= yMax; ++i) {
            for(int j = xMin; j <= xMax; ++j) {
                for(int k = zMin; k <= zMax; ++k) {
                    //noinspection deprecation
                    if (!existingOnly || !seedReader.getBlockState(pos.offset(j, i, k)).isAir()) {
                        if (i != yMin && i != yMax && j != xMin && j != xMax && k != zMin && k != zMax) {
                            seedReader.setBlock(pos.offset(j, i, k), insideBlockState, 2);
                        } else {
                            seedReader.setBlock(pos.offset(j, i, k), boundaryBlockState, 2);
                        }
                    }
                }
            }
        }
    }

    @SuppressWarnings("ConstantConditions")
    public static void generateRandomRack(@Nonnull WorldGenLevel seedReader, @Nonnull BlockPos pos, @Nonnull Random random, @Nonnull Direction direction) {
        if (random.nextDouble() < 0.5) {
            seedReader.setBlock(pos, BlockSubscriber.drying_rack.defaultBlockState().setValue(HORIZONTAL_FACING, direction), 2);
            BlockEntity tileentity = seedReader.getBlockEntity(pos);

            if (tileentity instanceof DryingRackTileEntity) {
                NonNullList<ItemStack> stacks = ((DryingRackTileEntity)tileentity).getStacks();

                for (int i = stacks.size() / 2; i < stacks.size(); i++) {
                    if (random.nextDouble() < 0.5) {
                        stacks.set(i, new ItemStack(ItemSubscriber.dried_grass));
                    }
                }
            }
        } else {
            seedReader.setBlock(pos, BlockSubscriber.tanning_rack.defaultBlockState().setValue(HORIZONTAL_FACING, direction), 2);
            BlockEntity tileentity = seedReader.getBlockEntity(pos);

            if (tileentity instanceof TanningRackTileEntity) {
                NonNullList<ItemStack> stacks = ((TanningRackTileEntity)tileentity).getStacks();

                for (int i = 0; i < stacks.size() / 2; i++) {
                    if (random.nextDouble() < 0.7) {
                        stacks.set(i, new ItemStack(ItemSubscriber.raw_hide));
                    }
                }
            }
        }
    }

    @SuppressWarnings("ConstantConditions")
    public static void generateBed(@Nonnull WorldGenLevel seedReader, @Nonnull BlockPos pos, Direction direction) {
        Direction bedDirection = direction.getAxisDirection() == Direction.AxisDirection.NEGATIVE ? direction : direction.getOpposite();
        seedReader.setBlock(pos, BlockSubscriber.dried_grass_bed.defaultBlockState().setValue(HORIZONTAL_FACING, bedDirection).setValue(BED_PART, BedPart.HEAD), 2);
        seedReader.setBlock(pos.relative(direction.getAxis(), 1), BlockSubscriber.dried_grass_bed.defaultBlockState().setValue(HORIZONTAL_FACING, bedDirection).setValue(BED_PART, BedPart.FOOT), 2);
    }

    private static final Vector3f vector = new Vector3f();
    public static BlockPos getRotatedPos(BlockPos pos, int x, int y, int z, Matrix3f normal) {
        vector.set(x, y, z);
        vector.mulTranspose(normal);
        return pos.offset(Math.round(vector.x), Math.round(vector.y), Math.round(vector.z));
    }

    @SuppressWarnings("ConstantConditions")
    public static void generateFlintWorkbench(@Nonnull ISeedReader seedReader, @Nonnull BlockPos pos, @Nonnull Random rand, @Nonnull Direction direction) {
        seedReader.setBlock(pos, BlockSubscriber.flint_workbench.defaultBlockState().setValue(BlockStateProperties.HORIZONTAL_FACING, direction), 2);
        TileEntity tileentity = seedReader.getBlockEntity(pos);

        if (tileentity instanceof FlintWorkbenchTileEntity) {
            NonNullList<ItemStack> stacks = ((FlintWorkbenchTileEntity)tileentity).getStacks();

            for (int i = 0; i < stacks.size(); i++) {
                if (rand.nextDouble() < 0.25) {
                    stacks.set(i, new ItemStack(ItemSubscriber.antler));
                }
            }
        }
    }

    @SuppressWarnings("ConstantConditions")
    public static void generateStoneChest(@Nonnull ISeedReader seedReader, BlockPos pos, @Nonnull Random rand, @Nonnull ResourceLocation lootResource, Direction direction) {
        seedReader.setBlock(pos, BlockSubscriber.stone_chest.defaultBlockState().setValue(BlockStateProperties.HORIZONTAL_FACING, direction), 2);
        TileEntity tileentity = seedReader.getBlockEntity(pos);

        if (tileentity instanceof StoneChestTileEntity) {
            ((StoneChestTileEntity)tileentity).setLootTable(lootResource, rand.nextLong());
        }
    }

    public static void replaceAirAndLiquidDownwards(@Nonnull ISeedReader seedReader, @Nonnull BlockPos pos, @Nonnull BlockState blockState) {
        int i = pos.getY();
        BlockPos p = pos;

        while (isNotSolid(seedReader, p) && i > 1) {
            seedReader.setBlock(p, blockState, 2);
            p = p.below();
        }
    }

    private static boolean isNotSolid(@Nonnull ISeedReader seedReader, @Nonnull BlockPos pos) {
        BlockState state = seedReader.getBlockState(pos);
        return seedReader.isEmptyBlock(pos) || state.getMaterial().isLiquid() || state.getMaterial().isReplaceable() || state.getMaterial().equals(Material.SAND)
                || state.getMaterial().equals(Material.DIRT) || state.getMaterial().equals(Material.GRASS);
    }
}
