package com.yanny.age.stone.structures;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.ProbabilityFeatureConfiguration;

import javax.annotation.Nonnull;
import java.util.Random;


public class BurialPlaceFeature extends Feature<ProbabilityFeatureConfiguration> {

    public BurialPlaceFeature(Codec<ProbabilityFeatureConfiguration> configCodec) {
        super(configCodec);
    }

    @Override
    public boolean place(@Nonnull WorldGenLevel seedReader, @Nonnull ChunkGenerator chunkGenerator, @Nonnull Random random, @Nonnull BlockPos pos, @Nonnull ProbabilityFeatureConfiguration featureConfig) {
        if (random.nextFloat() < featureConfig.probability) {
/*
            generateStoneChest(seedReader, pos.add(0, -2, 0), random, new ResourceLocation(Reference.MODID, "chests/stone_chest"), Direction.Plane.HORIZONTAL.random(random));

            replaceAirAndLiquidDownwards(seedReader, pos.add(-4, 2, 0), Blocks.STONE.getDefaultState());
            replaceAirAndLiquidDownwards(seedReader, pos.add(-3, 2, -3), Blocks.STONE.getDefaultState());
            replaceAirAndLiquidDownwards(seedReader, pos.add(-3, 2, 3), Blocks.STONE.getDefaultState());
            replaceAirAndLiquidDownwards(seedReader, pos.add(0, 2, -4), Blocks.STONE.getDefaultState());
            replaceAirAndLiquidDownwards(seedReader, pos.add(0, 2, 4), Blocks.STONE.getDefaultState());
            replaceAirAndLiquidDownwards(seedReader, pos.add(3, 2, -3), Blocks.STONE.getDefaultState());
            replaceAirAndLiquidDownwards(seedReader, pos.add(3, 2, 3), Blocks.STONE.getDefaultState());
            replaceAirAndLiquidDownwards(seedReader, pos.add(4, 2, 0), Blocks.STONE.getDefaultState());
*/

            return true;
        }

        return false;
    }
}
