package com.yanny.age.stone.structures;

import com.mojang.serialization.Codec;
import com.yanny.age.stone.Reference;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.ProbabilityFeatureConfiguration;

import javax.annotation.Nonnull;
import java.util.Random;

import static com.yanny.age.stone.structures.FeatureUtils.generateStoneChest;
import static com.yanny.age.stone.structures.FeatureUtils.replaceAirAndLiquidDownwards;

public class BurialPlaceFeature extends Feature<ProbabilityFeatureConfiguration> {

    public BurialPlaceFeature(Codec<ProbabilityFeatureConfiguration> configCodec) {
        super(configCodec);
    }

    @Override
    public boolean place(@Nonnull WorldGenLevel seedReader, @Nonnull ChunkGenerator chunkGenerator, @Nonnull Random random, @Nonnull BlockPos pos, @Nonnull ProbabilityFeatureConfiguration featureConfig) {
        if (random.nextFloat() < featureConfig.probability) {
            generateStoneChest(seedReader, pos.offset(0, -2, 0), random, new ResourceLocation(Reference.MODID, "chests/stone_chest"), Direction.Plane.HORIZONTAL.getRandomDirection(random));

            replaceAirAndLiquidDownwards(seedReader, pos.offset(-4, 2, 0), Blocks.STONE.defaultBlockState());
            replaceAirAndLiquidDownwards(seedReader, pos.offset(-3, 2, -3), Blocks.STONE.defaultBlockState());
            replaceAirAndLiquidDownwards(seedReader, pos.offset(-3, 2, 3), Blocks.STONE.defaultBlockState());
            replaceAirAndLiquidDownwards(seedReader, pos.offset(0, 2, -4), Blocks.STONE.defaultBlockState());
            replaceAirAndLiquidDownwards(seedReader, pos.offset(0, 2, 4), Blocks.STONE.defaultBlockState());
            replaceAirAndLiquidDownwards(seedReader, pos.offset(3, 2, -3), Blocks.STONE.defaultBlockState());
            replaceAirAndLiquidDownwards(seedReader, pos.offset(3, 2, 3), Blocks.STONE.defaultBlockState());
            replaceAirAndLiquidDownwards(seedReader, pos.offset(4, 2, 0), Blocks.STONE.defaultBlockState());

            return true;
        }

        return false;
    }
}
