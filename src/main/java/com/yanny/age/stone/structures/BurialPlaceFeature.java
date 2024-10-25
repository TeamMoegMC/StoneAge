package com.yanny.age.stone.structures;

import com.mojang.serialization.Codec;
import com.yanny.age.stone.Reference;
import net.minecraft.block.Blocks;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.ProbabilityConfig;

import javax.annotation.Nonnull;
import java.util.Random;

import static com.yanny.age.stone.structures.FeatureUtils.generateStoneChest;
import static com.yanny.age.stone.structures.FeatureUtils.replaceAirAndLiquidDownwards;

public class BurialPlaceFeature extends Feature<ProbabilityConfig> {

    public BurialPlaceFeature(Codec<ProbabilityConfig> configCodec) {
        super(configCodec);
    }

    @Override
    public boolean place(@Nonnull ISeedReader seedReader, @Nonnull ChunkGenerator chunkGenerator, @Nonnull Random random, @Nonnull BlockPos pos, @Nonnull ProbabilityConfig featureConfig) {
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
