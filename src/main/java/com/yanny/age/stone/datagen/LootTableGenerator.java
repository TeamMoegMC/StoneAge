package com.yanny.age.stone.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.resources.ResourceLocation;
import org.lwjgl.system.NonnullDefault;

import java.util.List;
import java.util.Set;

@NonnullDefault
public class LootTableGenerator extends LootTableProvider {
    public LootTableGenerator(PackOutput p_254123_, Set<ResourceLocation> p_254481_, List<SubProviderEntry> p_253798_) {
        super(p_254123_, p_254481_, p_253798_);
    }

    /*private static final Set<Block> BLOCKS = Sets.newHashSet(BlockSubscriber.drying_rack, BlockSubscriber.feeder, BlockSubscriber.fishing_net,
            BlockSubscriber.flint_workbench, BlockSubscriber.millstone, BlockSubscriber.stone_chest, BlockSubscriber.tanning_rack, BlockSubscriber.thatch_block,
            BlockSubscriber.thatch_stairs, BlockSubscriber.tree_stump);

    public LootTableGenerator(DataGenerator dataGeneratorIn) {
        super(dataGeneratorIn);
    }

    @Override
    protected List<Pair<Supplier<Consumer<BiConsumer<ResourceLocation, LootTable.Builder>>>, LootContextParamSet>> getTables() {
        return ImmutableList.of(Pair.of(Blocks::new, LootContextParamSets.BLOCK));
    }

    @Override
    protected void validate(Map<ResourceLocation, LootTable> map, ValidationContext validationtracker) {
        map.forEach((name, table) -> LootTables.validate(validationtracker, name, table));
    }

    private static class Blocks extends BlockLoot {
        @Override
        protected void addTables() {
            BLOCKS.forEach(this::dropSelf);
        }

        @Override
        protected Iterable<Block> getKnownBlocks() {
            return new ArrayList<>(BLOCKS);
        }
    }*/
}
