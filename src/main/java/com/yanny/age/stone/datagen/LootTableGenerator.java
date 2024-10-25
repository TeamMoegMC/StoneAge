package com.yanny.age.stone.datagen;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Sets;
import com.mojang.datafixers.util.Pair;
import com.yanny.age.stone.subscribers.BlockSubscriber;
import net.minecraft.world.level.block.Block;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.data.loot.BlockLoot;
import net.minecraft.loot.*;
import net.minecraft.resources.ResourceLocation;
import org.lwjgl.system.NonnullDefault;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;

import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.LootTables;
import net.minecraft.world.level.storage.loot.ValidationContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSet;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;

@NonnullDefault
public class LootTableGenerator extends LootTableProvider {

    private static final Set<Block> BLOCKS = Sets.newHashSet(BlockSubscriber.drying_rack, BlockSubscriber.feeder, BlockSubscriber.fishing_net,
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
    }
}
