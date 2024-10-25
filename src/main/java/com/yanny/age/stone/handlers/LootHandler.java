package com.yanny.age.stone.handlers;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.mojang.datafixers.util.Pair;
import net.minecraft.advancements.criterion.ItemPredicate;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.loot.*;
import net.minecraft.loot.conditions.ILootCondition;
import net.minecraft.loot.conditions.MatchTool;
import net.minecraft.tags.ITag;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.Nonnull;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import static com.yanny.age.stone.Reference.MODID;

@Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class LootHandler {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final Map<String, Pair<String, Item>> REMOVE_LOOT = Maps.newHashMap();
    private static final Map<String, String> INJECT_POOL = Maps.newHashMap();
    private static final Map<String, Pair<Block, ITag.INamedTag<Item>>> INJECT_ITEM_POOL = Maps.newHashMap();
    private static final Map<String, Pair<Block, ITag.INamedTag<Item>>> INJECT_ALTERNATE_ENTRIES = Maps.newHashMap();
    private static final Map<String, Pair<Block, ITag.INamedTag<Item>>> CHANGE_ALTERNATE_ENTRIES = Maps.newHashMap();

    private static final Field lootEntries;
    private static final Field entryItem;
    private static final Field alternativeEntries;
    private static final Field alternativeConditions;
    private static final Constructor<AlternativesLootEntry> constructAlternative;

    static {
        lootEntries = ObfuscationReflectionHelper.findField(LootPool.class, "entries");
        lootEntries.setAccessible(true);
        alternativeEntries = ObfuscationReflectionHelper.findField(ParentedLootEntry.class, "children");
        alternativeEntries.setAccessible(true);
        alternativeConditions = ObfuscationReflectionHelper.findField(LootEntry.class, "conditions");
        alternativeConditions.setAccessible(true);
        constructAlternative = ObfuscationReflectionHelper.findConstructor(AlternativesLootEntry.class, LootEntry[].class, ILootCondition[].class);
        constructAlternative.setAccessible(true);
        entryItem = ObfuscationReflectionHelper.findField(ItemLootEntry.class, "item");
        entryItem.setAccessible(true);

        REMOVE_LOOT.put("minecraft:entities/horse", new Pair<>("main", Items.LEATHER));
        REMOVE_LOOT.put("minecraft:entities/cow", new Pair<>("main", Items.LEATHER));
        REMOVE_LOOT.put("minecraft:entities/donkey", new Pair<>("main", Items.LEATHER));
        REMOVE_LOOT.put("minecraft:entities/llama", new Pair<>("main", Items.LEATHER));
        REMOVE_LOOT.put("minecraft:entities/mooshroom", new Pair<>("main", Items.LEATHER));
        REMOVE_LOOT.put("minecraft:entities/trader_llama", new Pair<>("main", Items.LEATHER));

        INJECT_POOL.put("minecraft:blocks/dirt", "stone_age:inject/blocks/bones_and_sticks");
        INJECT_POOL.put("minecraft:blocks/coarse_dirt", "stone_age:inject/blocks/bones_and_sticks");
        INJECT_POOL.put("minecraft:blocks/gravel", "stone_age:inject/blocks/bones_and_sticks");
        INJECT_POOL.put("minecraft:blocks/sand", "stone_age:inject/blocks/bones_and_sticks");
        INJECT_POOL.put("minecraft:blocks/red_sand", "stone_age:inject/blocks/bones_and_sticks");
        INJECT_POOL.put("minecraft:blocks/grass_block", "stone_age:inject/blocks/saplings");
        INJECT_POOL.put("minecraft:entities/cod", "stone_age:inject/entities/fish_bone");
        INJECT_POOL.put("minecraft:entities/salmon", "stone_age:inject/entities/fish_bone");
        INJECT_POOL.put("minecraft:entities/tropical_fish", "stone_age:inject/entities/fish_bone");
        INJECT_POOL.put("minecraft:entities/dolphin", "stone_age:inject/entities/fish_bone");
        INJECT_POOL.put("minecraft:entities/cow", "stone_age:inject/entities/bone_fat_hide");
        INJECT_POOL.put("minecraft:entities/horse", "stone_age:inject/entities/bone_fat_hide");
        INJECT_POOL.put("minecraft:entities/donkey", "stone_age:inject/entities/bone_fat_hide");
        INJECT_POOL.put("minecraft:entities/llama", "stone_age:inject/entities/bone_fat_hide");
        INJECT_POOL.put("minecraft:entities/mooshroom", "stone_age:inject/entities/bone_fat_hide");
        INJECT_POOL.put("minecraft:entities/trader_llama", "stone_age:inject/entities/bone_fat_hide");
        INJECT_POOL.put("minecraft:entities/pig", "stone_age:inject/entities/bone_fat");
        INJECT_POOL.put("minecraft:entities/sheep", "stone_age:inject/entities/bone_fat");
    }

    @SubscribeEvent
    public static void lootLoad(LootTableLoadEvent event) {
        String name = event.getName().toString();

        if (REMOVE_LOOT.containsKey(name)) {
            removeLootFromTable(event.getTable(), REMOVE_LOOT.get(name));
        }
        if (INJECT_POOL.containsKey(name)) {
            event.getTable().addPool(getInjectPool(INJECT_POOL.get(name)));
        }
        if (INJECT_ITEM_POOL.containsKey(name)) {
            injectItemPool(event.getTable(), INJECT_ITEM_POOL.get(name));
        }
        if (INJECT_ALTERNATE_ENTRIES.containsKey(name)) {
            injectMainAlternativeEntries(event.getTable(), INJECT_ALTERNATE_ENTRIES.get(name));
        }
        if (CHANGE_ALTERNATE_ENTRIES.containsKey(name)) {
            changeToMainAlternativeEntries(event.getTable(), CHANGE_ALTERNATE_ENTRIES.get(name));
        }
    }

    @Nonnull
    private static LootPool getInjectPool(@Nonnull String entryName) {
        return LootPool.lootPool().add(getInjectEntry(entryName)).bonusRolls(0, 1).name("stone_age:inject").build();
    }

    @Nonnull
    private static LootEntry.Builder<?> getInjectEntry(@Nonnull String name) {
        ResourceLocation table = new ResourceLocation(name);
        return TableLootEntry.lootTableReference(table).setWeight(1);
    }

    private static void injectItemPool(@Nonnull LootTable table, @Nonnull Pair<Block, ITag.INamedTag<Item>> data) {
        ItemPredicate.Builder predicate = ItemPredicate.Builder.item().of(data.getSecond());
        LootEntry.Builder<?> itemLootEntry = ItemLootEntry.lootTableItem(data.getFirst()).when(MatchTool.toolMatches(predicate)).setWeight(1);
        LootPool.Builder pool = LootPool.lootPool().add(itemLootEntry).bonusRolls(0, 1).name("stone_age:inject");
        table.addPool(pool.build());
    }

    @SuppressWarnings("unchecked")
    private static void injectMainAlternativeEntries(@Nonnull LootTable table, @Nonnull Pair<Block, ITag.INamedTag<Item>> data) {
        try {
            LootPool pool = table.getPool("main");
            //noinspection ConstantConditions
            if (pool != null) {
                List<LootEntry> entries = (List<LootEntry>) lootEntries.get(pool);

                if (entries != null && entries.size() > 0 && entries.get(0) instanceof AlternativesLootEntry) {
                    ItemPredicate.Builder predicate = ItemPredicate.Builder.item().of(data.getSecond());
                    LootEntry itemLootEntry = ItemLootEntry.lootTableItem(data.getFirst()).when(MatchTool.toolMatches(predicate)).build();
                    LootEntry[] aEntries = (LootEntry[]) alternativeEntries.get(entries.get(0));
                    ILootCondition[] aConditions = (ILootCondition[]) alternativeConditions.get(entries.get(0));

                    if (aEntries != null && aConditions != null) {
                        List<LootEntry> list = Lists.newArrayList(itemLootEntry);
                        list.addAll(Arrays.asList(aEntries));
                        AlternativesLootEntry newAlternative = constructAlternative.newInstance(list.toArray(new LootEntry[0]), aConditions);
                        entries.set(0, newAlternative);
                    } else {
                        LOGGER.warn("Fields not available! {}", data.toString());
                    }
                } else {
                    LOGGER.warn("Alternatives not available! {}", data.toString());
                }
            } else {
                LOGGER.warn("Main pool not available! {}", data.toString());
            }
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    private static void changeToMainAlternativeEntries(@Nonnull LootTable table, @Nonnull Pair<Block, ITag.INamedTag<Item>> data) {
        try {
            LootPool pool = table.getPool("main");
            //noinspection ConstantConditions
            if (pool != null) {
                List<LootEntry> entries = (List<LootEntry>) lootEntries.get(pool);

                if (entries != null && entries.size() > 0) {
                    ItemPredicate.Builder predicate = ItemPredicate.Builder.item().of(data.getSecond());
                    LootEntry.Builder<?> itemLootEntry = ItemLootEntry.lootTableItem(data.getFirst()).when(MatchTool.toolMatches(predicate));
                    List<LootEntry> list = Lists.newArrayList(itemLootEntry.build());
                    list.addAll(entries);
                    AlternativesLootEntry a = constructAlternative.newInstance(list.toArray(new LootEntry[0]), new ILootCondition[0]);
                    entries.set(0, a);
                } else {
                    LOGGER.warn("Alternatives not available! {}", data.toString());
                }
            } else {
                LOGGER.warn("Main pool not available! {}", data.toString());
            }
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }


    @SuppressWarnings("unchecked")
    public static void removeLootFromTable(@Nonnull LootTable table, @Nonnull Pair<String, Item> data) {
        try {
            LootPool pool = table.getPool(data.getFirst());
            List<LootEntry> entries = (List<LootEntry>) lootEntries.get(pool);
            Iterator<LootEntry> it = entries.iterator();

            while(it.hasNext()) {
                LootEntry entry = it.next();

                if(entry instanceof ItemLootEntry) {
                    ItemLootEntry lootEntry = (ItemLootEntry)entry;
                    Item i = (Item) entryItem.get(lootEntry);

                    if(i == data.getSecond()) {
                        it.remove();
                        return;
                    }
                }
            }
        } catch (IllegalArgumentException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
