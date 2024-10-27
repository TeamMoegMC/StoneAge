package com.yanny.age.stone.structures;

import com.mojang.serialization.Codec;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.ProbabilityFeatureConfiguration;

import static com.yanny.age.stone.structures.FeatureUtils.*;
import static net.minecraft.state.properties.BlockStateProperties.LIT;

public class AbandonedCampFeature extends Feature<ProbabilityFeatureConfiguration> {

    public AbandonedCampFeature(Codec<ProbabilityFeatureConfiguration> configCodec) {
        super(configCodec);
    }

    @Override
    public boolean place(FeaturePlaceContext<ProbabilityFeatureConfiguration> featurePlaceContext) {
        return false;
    }

   /* public boolean place(@Nonnull WorldGenLevel seedReader, @Nonnull ChunkGenerator chunkGenerator, @Nonnull Random random, @Nonnull BlockPos pos, @Nonnull ProbabilityFeatureConfiguration featureConfig) {
        if (random.nextFloat() < featureConfig.probability) {
            fillWithAir(seedReader, pos, -2, 0, -2, 2, 3, 2);
            fillWithBlocks(seedReader, pos, -2, -1, -2, 2, -1, 2, Blocks.GRASS_BLOCK.defaultBlockState(), Blocks.GRASS_BLOCK.defaultBlockState(), false);

            Direction direction = Direction.Plane.HORIZONTAL.getRandomDirection(random);

            Matrix3f normal = new Matrix3f();
            normal.identity();
            normal.rotate((float) Math.toRadians(direction.toYRot()), new Vector3f(0, 1, 0));

            for (int i = -2; i <= 2; i++) {
                if (random.nextDouble() < 0.5) {
                    generateRandomRack(seedReader, getRotatedPos(pos, i, 0, 2, normal), random, direction.getOpposite());
                }
            }

            seedReader.setBlock(pos, Blocks.CAMPFIRE.defaultBlockState().setValue(LIT, false), 2);
            generateBed(seedReader, getRotatedPos(pos, 2, 0, 0, normal), direction);
            generateFlintWorkbench(seedReader, getRotatedPos(pos, -2, 0, 0, normal), random, direction.getOpposite().getClockWise());
            generateStoneChest(seedReader, getRotatedPos(pos, -2, 0, -1, normal), random, new ResourceLocation(Reference.MODID, "chests/stone_chest"), direction.getOpposite().getClockWise());

            return true;
        }

        return false;
    }*/
}
