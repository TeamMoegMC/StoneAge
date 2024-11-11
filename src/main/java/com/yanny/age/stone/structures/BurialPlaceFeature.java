package com.yanny.age.stone.structures;

import com.mojang.serialization.Codec;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.ProbabilityFeatureConfiguration;


public class BurialPlaceFeature extends Feature<ProbabilityFeatureConfiguration> {

    public BurialPlaceFeature(Codec<ProbabilityFeatureConfiguration> configCodec) {
        super(configCodec);
    }

    @Override
    public boolean place(FeaturePlaceContext<ProbabilityFeatureConfiguration> featurePlaceContext) {
        return false;
    }

   /* @Override
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
    }*/
}
