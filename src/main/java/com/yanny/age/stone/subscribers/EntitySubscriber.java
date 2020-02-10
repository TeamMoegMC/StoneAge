package com.yanny.age.stone.subscribers;

import com.google.common.collect.Sets;
import com.yanny.age.stone.Reference;
import com.yanny.age.stone.config.Config;
import com.yanny.age.stone.entities.*;
import com.yanny.ages.api.group.ModItemGroup;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.ObjectHolder;

import static net.minecraft.entity.EntityClassification.CREATURE;
import static net.minecraft.entity.EntityType.*;

@SuppressWarnings({"unused", "unchecked"})
@ObjectHolder(Reference.MODID)
@Mod.EventBusSubscriber(modid = Reference.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class EntitySubscriber {
    public static final EntityType<DeerEntity> deer = (EntityType<DeerEntity>) EntityType.Builder.create(DeerEntity::new, CREATURE)
            .size(0.9f, 1.5f).build("").setRegistryName(Reference.MODID,"deer");
    public static final EntityType<BoarEntity> boar = (EntityType<BoarEntity>) EntityType.Builder.create(BoarEntity::new, CREATURE)
            .size(0.9f, 0.9f).build("").setRegistryName(Reference.MODID,"boar");
    public static final EntityType<AurochEntity> auroch = (EntityType<AurochEntity>) EntityType.Builder.create(AurochEntity::new, CREATURE)
            .size(1.1f, 1.5f).build("").setRegistryName(Reference.MODID,"auroch");
    public static final EntityType<FowlEntity> fowl = (EntityType<FowlEntity>) EntityType.Builder.create(FowlEntity::new, CREATURE)
            .size(0.7f, 0.7f).build("").setRegistryName(Reference.MODID,"fowl");
    public static final EntityType<MouflonEntity> mouflon = (EntityType<MouflonEntity>) EntityType.Builder.create(MouflonEntity::new, CREATURE)
            .size(0.9f, 1.2f).build("").setRegistryName(Reference.MODID,"mouflon");
    public static final EntityType<FlintSpearEntity> flint_spear = (EntityType<FlintSpearEntity>) EntityType.Builder.<FlintSpearEntity>create(FlintSpearEntity::new, CREATURE)
            .size(0.5f, 0.5f).build("").setRegistryName(Reference.MODID,"flint_spear");
    public static final EntityType<MammothEntity> mammoth = (EntityType<MammothEntity>) EntityType.Builder.create(MammothEntity::new, CREATURE)
            .size(1.9f, 3.5f).build("").setRegistryName(Reference.MODID,"mammoth");
    public static final EntityType<SaberToothTigerEntity> saber_tooth_tiger = (EntityType<SaberToothTigerEntity>) EntityType.Builder.create(SaberToothTigerEntity::new, CREATURE)
            .size(1.4f, 1.4f).build("").setRegistryName(Reference.MODID, "saber_tooth_tiger");
    public static final EntityType<WoollyRhinoEntity> woolly_rhino = (EntityType<WoollyRhinoEntity>) EntityType.Builder.create(WoollyRhinoEntity::new, CREATURE)
            .size(1.7f, 1.4f).build("").setRegistryName(Reference.MODID, "woolly_rhino");

    public static final Item deer_spawn_egg = null;
    public static final Item boar_spawn_egg = null;
    public static final Item auroch_spawn_egg = null;
    public static final Item fowl_spawn_egg = null;
    public static final Item mouflon_spawn_egg = null;
    public static final Item mammoth_spawn_egg = null;
    public static final Item saber_tooth_tiger_spawn_egg = null;
    public static final Item woolly_rhino_spawn_egg = null;

    @SubscribeEvent
    public static void registerEntities(RegistryEvent.Register<EntityType<?>> event) {
        IForgeRegistry<EntityType<?>> registry = event.getRegistry();
        registry.register(deer);
        registry.register(boar);
        registry.register(auroch);
        registry.register(fowl);
        registry.register(mouflon);
        registry.register(flint_spear);
        registry.register(mammoth);
        registry.register(saber_tooth_tiger);
        registry.register(woolly_rhino);

        for (Biome biome : ForgeRegistries.BIOMES) {
            if (Config.spawnDeerAllowedBiomes.contains(biome) && Config.spawnDeerEnable) {
                biome.getSpawns(deer.getClassification()).add(new Biome.SpawnListEntry(deer, Config.spawnDeerWeight, Config.spawnDeerMinCount, Config.spawnDeerMaxCount));
            }
            if (Config.spawnBoarAllowedBiomes.contains(biome) && Config.spawnBoarEnable) {
                biome.getSpawns(boar.getClassification()).add(new Biome.SpawnListEntry(boar, Config.spawnBoarWeight, Config.spawnBoarMinCount, Config.spawnBoarMaxCount));
            }
            if (Config.spawnAurochAllowedBiomes.contains(biome) && Config.spawnAurochEnable) {
                biome.getSpawns(auroch.getClassification()).add(new Biome.SpawnListEntry(auroch, Config.spawnAurochWeight, Config.spawnAurochMinCount, Config.spawnAurochMaxCount));
            }
            if (Config.spawnFowlAllowedBiomes.contains(biome) && Config.spawnFowlEnable) {
                biome.getSpawns(fowl.getClassification()).add(new Biome.SpawnListEntry(fowl, Config.spawnFowlWeight, Config.spawnFowlMinCount, Config.spawnFowlMaxCount));
            }
            if (Config.spawnMouflonAllowedBiomes.contains(biome) && Config.spawnMouflonEnable) {
                biome.getSpawns(mouflon.getClassification()).add(new Biome.SpawnListEntry(mouflon, Config.spawnMouflonWeight, Config.spawnMouflonMinCount, Config.spawnMouflonMaxCount));
            }
            if (Config.spawnMammothAllowedBiomes.contains(biome) && Config.spawnMammothEnable) {
                biome.getSpawns(mammoth.getClassification()).add(new Biome.SpawnListEntry(mammoth, Config.spawnMammothWeight, Config.spawnMammothMinCount, Config.spawnMammothMaxCount));
            }
            if (Config.spawnSaberToothTigerAllowedBiomes.contains(biome) && Config.spawnSaberToothTigerEnable) {
                biome.getSpawns(saber_tooth_tiger.getClassification()).add(new Biome.SpawnListEntry(saber_tooth_tiger, Config.spawnSaberToothTigerWeight, Config.spawnSaberToothTigerMinCount, Config.spawnSaberToothTigerMaxCount));
            }
            if (Config.spawnWoollyRhinoAllowedBiomes.contains(biome) && Config.spawnWoollyRhinoEnable) {
                biome.getSpawns(woolly_rhino.getClassification()).add(new Biome.SpawnListEntry(woolly_rhino, Config.spawnWoollyRhinoWeight, Config.spawnWoollyRhinoMinCount, Config.spawnWoollyRhinoMaxCount));
            }

            if (Config.removeVanillaGeneratedAnimals) {
                biome.getSpawns(CREATURE).removeIf(entry -> Sets.newHashSet(COW, SHEEP, PIG, CHICKEN).contains(entry.entityType));
                // COW -> AUROCH
                // SHEEP -> MOUFLON
                // PIG -> BOAR
                // CHICKEN -> FOWL
            }
        }
    }

    @SubscribeEvent
    public static void registerSpawnEggs(RegistryEvent.Register<Item> event) {
        IForgeRegistry<Item> registry = event.getRegistry();
        registry.register(new SpawnEggItem(deer, 0x00ffff, 0xff00ff,
                new Item.Properties().group(ModItemGroup.AGES)).setRegistryName(Reference.MODID, "deer_spawn_egg"));
        registry.register(new SpawnEggItem(boar, 0xc0c0ff, 0xff00ff,
                new Item.Properties().group(ModItemGroup.AGES)).setRegistryName(Reference.MODID, "boar_spawn_egg"));
        registry.register(new SpawnEggItem(auroch, 0xffc0c0, 0xffc0ff,
                new Item.Properties().group(ModItemGroup.AGES)).setRegistryName(Reference.MODID, "auroch_spawn_egg"));
        registry.register(new SpawnEggItem(fowl, 0xd666c0, 0x33c066,
                new Item.Properties().group(ModItemGroup.AGES)).setRegistryName(Reference.MODID, "fowl_spawn_egg"));
        registry.register(new SpawnEggItem(mouflon, 0xec6699, 0xccc096,
                new Item.Properties().group(ModItemGroup.AGES)).setRegistryName(Reference.MODID, "mouflon_spawn_egg"));
        registry.register(new SpawnEggItem(mammoth, 0xecff99, 0xc330a6,
                new Item.Properties().group(ModItemGroup.AGES)).setRegistryName(Reference.MODID, "mammoth_spawn_egg"));
        registry.register(new SpawnEggItem(saber_tooth_tiger, 0x333f99, 0xc3fea6,
                new Item.Properties().group(ModItemGroup.AGES)).setRegistryName(Reference.MODID, "saber_tooth_tiger_spawn_egg"));
        registry.register(new SpawnEggItem(woolly_rhino, 0xec6512, 0xc33905,
                new Item.Properties().group(ModItemGroup.AGES)).setRegistryName(Reference.MODID, "woolly_rhino_spawn_egg"));
    }
}
