package com.yanny.age.stone.config;

import com.google.common.collect.Sets;
import com.yanny.age.stone.Reference;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import static net.minecraft.world.level.biome.Biomes.*;

public class ServerConfig {
    public final ForgeConfigSpec.BooleanValue removeVanillaRecipes;
    public final ForgeConfigSpec.BooleanValue removeVanillaGeneratedAnimals;
    public final ForgeConfigSpec.IntValue domesticateAfterGenerations;
    public final ForgeConfigSpec.DoubleValue tanningRackFinishChance;
    public final ForgeConfigSpec.IntValue feederTickChanceBreedAnimalEffect;
    public final ForgeConfigSpec.IntValue feederEffectRange;
    public final ForgeConfigSpec.BooleanValue forceToolForWood;
    public final ForgeConfigSpec.BooleanValue givePlayerManualOnFirstConnect;
    public final ForgeConfigSpec.IntValue fishingNetChance;
    public final ForgeConfigSpec.BooleanValue disableVanillaCraftingTable;
    public final ForgeConfigSpec.ConfigValue<List<? extends String>> disabledUseInStoneAgeList;

    public final ForgeConfigSpec.DoubleValue abandonedCampSpawnChance;
//    public final ForgeConfigSpec.DoubleValue burialPlaceSpawnChance;
//    public final ForgeConfigSpec.ConfigValue<List<? extends String>> abandonedCampAllowedBiomes;
//    public final ForgeConfigSpec.ConfigValue<List<? extends String>> burialPlaceAllowedBiomes;

    public final ForgeConfigSpec.BooleanValue spawnDeerEnable;
    public final ForgeConfigSpec.IntValue spawnDeerWeight;
    public final ForgeConfigSpec.IntValue spawnDeerMinCount;
    public final ForgeConfigSpec.IntValue spawnDeerMaxCount;
//    public final ForgeConfigSpec.BooleanValue spawnDeerAllowedBiomesBlacklist;
//    public final ForgeConfigSpec.ConfigValue<List<? extends String>> spawnDeerAllowedBiomes;

    public final ForgeConfigSpec.BooleanValue spawnBoarEnable;
    public final ForgeConfigSpec.IntValue spawnBoarWeight;
    public final ForgeConfigSpec.IntValue spawnBoarMinCount;
    public final ForgeConfigSpec.IntValue spawnBoarMaxCount;
    //    public final ForgeConfigSpec.BooleanValue spawnBoarAllowedBiomesBlacklist;
    public final ForgeConfigSpec.ConfigValue<String> boarBreedingResult;
//    public final ForgeConfigSpec.ConfigValue<List<? extends String>> spawnBoarAllowedBiomes;

    public final ForgeConfigSpec.BooleanValue spawnAurochEnable;
    public final ForgeConfigSpec.IntValue spawnAurochWeight;
    public final ForgeConfigSpec.IntValue spawnAurochMinCount;
    public final ForgeConfigSpec.IntValue spawnAurochMaxCount;
    //    public final ForgeConfigSpec.BooleanValue spawnAurochAllowedBiomesBlacklist;
    public final ForgeConfigSpec.ConfigValue<String> aurochBreedingResult;
//    public final ForgeConfigSpec.ConfigValue<List<? extends String>> spawnAurochAllowedBiomes;

    public final ForgeConfigSpec.BooleanValue spawnFowlEnable;
    public final ForgeConfigSpec.IntValue spawnFowlWeight;
    public final ForgeConfigSpec.IntValue spawnFowlMinCount;
    public final ForgeConfigSpec.IntValue spawnFowlMaxCount;
    //    public final ForgeConfigSpec.BooleanValue spawnFowlAllowedBiomesBlacklist;
    public final ForgeConfigSpec.ConfigValue<String> fowlBreedingResult;
//    public final ForgeConfigSpec.ConfigValue<List<? extends String>> spawnFowlAllowedBiomes;

    public final ForgeConfigSpec.BooleanValue spawnMouflonEnable;
    public final ForgeConfigSpec.IntValue spawnMouflonWeight;
    public final ForgeConfigSpec.IntValue spawnMouflonMinCount;
    public final ForgeConfigSpec.IntValue spawnMouflonMaxCount;
    //    public final ForgeConfigSpec.BooleanValue spawnMouflonAllowedBiomesBlacklist;
    public final ForgeConfigSpec.ConfigValue<String> mouflonBreedingResult;
//    public final ForgeConfigSpec.ConfigValue<List<? extends String>> spawnMouflonAllowedBiomes;

    public final ForgeConfigSpec.BooleanValue spawnMammothEnable;
    public final ForgeConfigSpec.IntValue spawnMammothWeight;
    public final ForgeConfigSpec.IntValue spawnMammothMinCount;
    public final ForgeConfigSpec.IntValue spawnMammothMaxCount;
//    public final ForgeConfigSpec.BooleanValue spawnMammothAllowedBiomesBlacklist;
//    public final ForgeConfigSpec.ConfigValue<List<? extends String>> spawnMammothAllowedBiomes;

    public final ForgeConfigSpec.BooleanValue spawnSaberToothTigerEnable;
    public final ForgeConfigSpec.IntValue spawnSaberToothTigerWeight;
    public final ForgeConfigSpec.IntValue spawnSaberToothTigerMinCount;
    public final ForgeConfigSpec.IntValue spawnSaberToothTigerMaxCount;
//    public final ForgeConfigSpec.BooleanValue spawnSaberToothTigerAllowedBiomesBlacklist;
//    public final ForgeConfigSpec.ConfigValue<List<? extends String>> spawnSaberToothTigerAllowedBiomes;

    public final ForgeConfigSpec.BooleanValue spawnWoollyRhinoEnable;
    public final ForgeConfigSpec.IntValue spawnWoollyRhinoWeight;
    public final ForgeConfigSpec.IntValue spawnWoollyRhinoMinCount;
    public final ForgeConfigSpec.IntValue spawnWoollyRhinoMaxCount;
//    public final ForgeConfigSpec.BooleanValue spawnWoollyRhinoAllowedBiomesBlacklist;
//    public final ForgeConfigSpec.ConfigValue<List<? extends String>> spawnWoollyRhinoAllowedBiomes;

    public final ForgeConfigSpec.BooleanValue spawnTerrorBirdEnable;
    public final ForgeConfigSpec.IntValue spawnTerrorBirdWeight;
    public final ForgeConfigSpec.IntValue spawnTerrorBirdMinCount;
    public final ForgeConfigSpec.IntValue spawnTerrorBirdMaxCount;
//    public final ForgeConfigSpec.BooleanValue spawnTerrorBirdAllowedBiomesBlacklist;
//    public final ForgeConfigSpec.ConfigValue<List<? extends String>> spawnTerrorBirdAllowedBiomes;

    public final ForgeConfigSpec.BooleanValue spawnCoelacanthEnable;
    public final ForgeConfigSpec.IntValue spawnCoelacanthWeight;
    public final ForgeConfigSpec.IntValue spawnCoelacanthMinCount;
    public final ForgeConfigSpec.IntValue spawnCoelacanthMaxCount;
//    public final ForgeConfigSpec.BooleanValue spawnCoelacanthAllowedBiomesBlacklist;
//    public final ForgeConfigSpec.ConfigValue<List<? extends String>> spawnCoelacanthAllowedBiomes;

    public final ForgeConfigSpec.ConfigValue<List<? extends String>> infinityWaterSourceBiomeList;

    public final ForgeConfigSpec.BooleanValue GrassBedSleep;
    public final ForgeConfigSpec.BooleanValue DryingRackNeedDaytime;
    public final ForgeConfigSpec.BooleanValue MakeFire;
    public final ForgeConfigSpec.BooleanValue LitTorche;

    ServerConfig(@Nonnull final ForgeConfigSpec.Builder builder) {
        builder.push("general");
        removeVanillaRecipes = builder
                .comment("Remove vanilla recipes that are changed by mod or break mod gameplay (wooden tools, torch)")
                .translation(Reference.MODID + ".config.remove_vanilla_recipes")
                .define("removeVanillaRecipes", true);
        removeVanillaGeneratedAnimals = builder
                .comment("Remove spawning of vanilla animals like cows, pigs, sheeps")
                .translation(Reference.MODID + ".config.remove_vanilla_generated_animals")
                .define("removeVanillaGeneratedAnimals", true);
        domesticateAfterGenerations = builder
                .comment("Domesticate wild animal after given generations")
                .translation(Reference.MODID + ".config.domesticate_after_generations")
                .defineInRange("domesticateAfterGenerations", 3, 1, Integer.MAX_VALUE);
        tanningRackFinishChance = builder
                .comment("Chance of finishing recipe in tanning rack")
                .translation(Reference.MODID + ".config.tanning_rack_finish_chance")
                .defineInRange("tanningRackFinishChance", 0.1, 0.001, 1.0);
        feederTickChanceBreedAnimalEffect = builder
                .comment("Chance of breed effect from feeder every 1/X tick")
                .translation(Reference.MODID + ".config.feeder_tick_chance_breed_animal_effect")
                .defineInRange("feederTickChanceBreedAnimal", 200, 1, Integer.MAX_VALUE);
        feederEffectRange = builder
                .comment("Feeder area of breed effect")
                .translation(Reference.MODID + ".config.feeder_effect_range")
                .defineInRange("feederEffectRange", 4, 0, 15);
        forceToolForWood = builder
                .comment("Force tool for wood material - harvestable only with axe")
                .translation(Reference.MODID + ".config.force_tool_for_wood")
                .define("forceToolForWood", true);
        givePlayerManualOnFirstConnect = builder
                .comment("Give player manual on first connect to game")
                .translation(Reference.MODID + ".config.give_player_manual_on_first_connect")
                .define("givePlayerManualOnFirstConnect", true);
        fishingNetChance = builder
                .comment("Chance of successfull fishing every 1/X tick (randomly)")
                .translation(Reference.MODID + ".config.fishing_net_chance")
                .defineInRange("fishingNetChance", 3600, 1, Integer.MAX_VALUE);
        disableVanillaCraftingTable = builder
                .comment("Disable use of vanilla crafting table until end of Stone Age")
                .translation(Reference.MODID + ".config.disable_vanilla_crafting_table")
                .define("disableVanillaCraftingTable", true);
        disabledUseInStoneAgeList = builder
                .comment("List of blocks that have disabled use until end of stone age")
                .translation(Reference.MODID + ".config.disabled_use_in_stone_age_list")
                .defineList("disabledUseInStoneAgeList", Sets.newHashSet(Blocks.CRAFTING_TABLE).stream()
                                .map(value -> Objects.requireNonNull(ForgeRegistries.BLOCKS.getKey(value)).toString()).collect(Collectors.toList()),
                        string -> string instanceof String && ForgeRegistries.BLOCKS.containsKey(new ResourceLocation((String) string)));
        builder.pop();

        builder.push("structures");
        abandonedCampSpawnChance = builder
                .comment("Abandoned camp spawn chance")
                .translation(Reference.MODID + ".config.abandoned_camp_spawn_chance")
                .defineInRange("abandonedCampSpawnChance", 0.001, Double.MIN_VALUE, 1.0);
        /*abandonedCampAllowedBiomes = builder
                .comment("Abandoned camp allowed biomes")
                .translation(Reference.MODID + ".config.abandoned_camp_allowed_biomes")
                .defineList("abandonedCampAllowedBiomes", ForgeRegistries.BIOMES.getValues().stream().filter(biome -> !Set.of(OCEAN, RIVER, THE_END, NETHER_WASTES)
                                        .contains(biome)).collect(Collectors.toSet()).stream()
                                .map(value -> Objects.requireNonNull(ForgeRegistries.BIOMES.getKey(value)).toString()).collect(Collectors.toList()),
                        string -> string instanceof String && ForgeRegistries.BIOMES.containsKey(new ResourceLocation((String) string)));
        burialPlaceSpawnChance = builder
                .comment("Burial place spawn chance")
                .translation(Reference.MODID + ".config.burial_place_spawn_chance")
                .defineInRange("burialPlaceSpawnChance", 0.001, Double.MIN_VALUE, 1.0);
        burialPlaceAllowedBiomes = builder
                .comment("Burial place allowed biomes")
                .translation(Reference.MODID + ".config.burial_place_allowed_biomes")
                .defineList("burialPlaceAllowedBiomes", ForgeRegistries.BIOMES.getValues().stream().filter(biome -> !Set.of(OCEAN, RIVER, THE_END, NETHER_WASTES)
                                        .contains(biome)).collect(Collectors.toSet()).stream()
                                .map(value -> Objects.requireNonNull(ForgeRegistries.BIOMES.getKey(value)).toString()).collect(Collectors.toList()),
                        string -> string instanceof String && ForgeRegistries.BIOMES.containsKey(new ResourceLocation((String) string)));*/
        builder.pop();

        builder.push("mob spawning");
        builder.push("deer");
        spawnDeerEnable = builder
                .comment("Enable spawning of mob")
                .translation(Reference.MODID + ".config.spawn_deer_enable")
                .define("spawnDeerEnable", true);
        spawnDeerWeight = builder
                .comment("Spawn weight of mob")
                .translation(Reference.MODID + ".config.spawn_deer_weight")
                .defineInRange("spawnDeerWeight", 20, 0, 100);
        spawnDeerMinCount = builder
                .comment("Spawn min count of mob")
                .translation(Reference.MODID + ".config.spawn_deer_min_count")
                .defineInRange("spawnDeerMinCount", 4, 1, 100);
        spawnDeerMaxCount = builder
                .comment("Spawn max count of mob")
                .translation(Reference.MODID + ".config.spawn_deer_max_count")
                .defineInRange("spawnDeerMaxCount", 10, 1, 100);
        /*spawnDeerAllowedBiomesBlacklist = builder
                .comment("If spawnDeerAllowedBiomes is blacklist")
                .translation(Reference.MODID + ".config.spawn_deer_allowed_biomes_blacklist")
                .define("spawnDeerAllowedBiomesBlacklist", false);
        spawnDeerAllowedBiomes = builder
                .comment("Spawn deer in allowed biomes")
                .translation(Reference.MODID + ".config.spawn_deer_allowed_biomes")
                .defineList("spawnDeerAllowedBiomes", ForgeRegistries.BIOMES.getValues().stream().filter(biome -> Set.of(FOREST, PLAINS, TAIGA, WINDSWEPT_HILLS, SAVANNA, BEACH, SWAMP, JUNGLE, MESA, ICY)
                                        .contains(biome)).collect(Collectors.toSet()).stream()
                                .map(value -> Objects.requireNonNull(ForgeRegistries.BIOMES.getKey(value)).toString()).collect(Collectors.toList()),
                        string -> string instanceof String && ForgeRegistries.BIOMES.containsKey(new ResourceLocation((String) string)));*/
        builder.pop();
        builder.push("boar");
        spawnBoarEnable = builder
                .comment("Enable spawning of mob")
                .translation(Reference.MODID + ".config.spawn_boar_enable")
                .define("spawnBoarEnable", true);
        spawnBoarWeight = builder
                .comment("Spawn weight of mob")
                .translation(Reference.MODID + ".config.spawn_boar_weight")
                .defineInRange("spawnBoarWeight", 10, 0, 100);
        spawnBoarMinCount = builder
                .comment("Spawn min count of mob")
                .translation(Reference.MODID + ".config.spawn_boar_min_count")
                .defineInRange("spawnBoarMinCount", 4, 1, 100);
        spawnBoarMaxCount = builder
                .comment("Spawn max count of mob")
                .translation(Reference.MODID + ".config.spawn_boar_max_count")
                .defineInRange("spawnBoarMaxCount", 6, 1, 100);
        /*spawnBoarAllowedBiomesBlacklist = builder
                .comment("If spawnBoarAllowedBiomes is blacklist")
                .translation(Reference.MODID + ".config.spawn_boar_allowed_biomes_blacklist")
                .define("spawnBoarAllowedBiomesBlacklist", false);*/
        boarBreedingResult = builder
                .comment("Result of breeding after generations in captivity")
                .translation(Reference.MODID + ".config.boar_breeding_result")
                .define("boarBreedingResult", Objects.requireNonNull(ForgeRegistries.ENTITY_TYPES.getKey(EntityType.PIG)).toString());
        /*spawnBoarAllowedBiomes = builder
                .comment("Spawn boar in allowed biomes")
                .translation(Reference.MODID + ".config.spawn_boar_allowed_biomes")
                .defineList("spawnBoarAllowedBiomes", ForgeRegistries.BIOMES.getValues().stream().filter(biome -> Set.of(FOREST, PLAINS, TAIGA, WINDSWEPT_HILLS, SAVANNA, SWAMP, JUNGLE)
                                        .contains(biome)).collect(Collectors.toSet()).stream()
                                .map(value -> Objects.requireNonNull(ForgeRegistries.BIOMES.getKey(value)).toString()).collect(Collectors.toList()),
                        string -> string instanceof String && ForgeRegistries.BIOMES.containsKey(new ResourceLocation((String) string)));*/
        builder.pop();
        builder.push("auroch");
        spawnAurochEnable = builder
                .comment("Enable spawning of mob")
                .translation(Reference.MODID + ".config.spawn_auroch_enable")
                .define("spawnAurochEnable", true);
        spawnAurochWeight = builder
                .comment("Spawn weight of mob")
                .translation(Reference.MODID + ".config.spawn_auroch_weight")
                .defineInRange("spawnAurochWeight", 10, 0, 100);
        spawnAurochMinCount = builder
                .comment("Spawn min count of mob")
                .translation(Reference.MODID + ".config.spawn_auroch_min_count")
                .defineInRange("spawnAurochMinCount", 4, 1, 100);
        spawnAurochMaxCount = builder
                .comment("Spawn max count of mob")
                .translation(Reference.MODID + ".config.spawn_auroch_max_count")
                .defineInRange("spawnAurochMaxCount", 8, 1, 100);
        /*spawnAurochAllowedBiomesBlacklist = builder
                .comment("If spawnAurochAllowedBiomes is blacklist")
                .translation(Reference.MODID + ".config.spawn_auroch_allowed_biomes_blacklist")
                .define("spawnAurochAllowedBiomesBlacklist", false);*/
        aurochBreedingResult = builder
                .comment("Result of breeding after generations in captivity")
                .translation(Reference.MODID + ".config.auroch_breeding_result")
                .define("aurochBreedingResult", Objects.requireNonNull(ForgeRegistries.ENTITY_TYPES.getKey(EntityType.COW)).toString());
        /*spawnAurochAllowedBiomes = builder
                .comment("Spawn auroch in allowed biomes")
                .translation(Reference.MODID + ".config.spawn_auroch_allowed_biomes")
                .defineList("spawnAurochAllowedBiomes", ForgeRegistries.BIOMES.getValues().stream().filter(biome -> Set.of(FOREST, PLAINS, TAIGA, WINDSWEPT_HILLS, SAVANNA, BEACH)
                                        .contains(biome)).collect(Collectors.toSet()).stream()
                                .map(value -> Objects.requireNonNull(ForgeRegistries.BIOMES.getKey(value)).toString()).collect(Collectors.toList()),
                        string -> string instanceof String && ForgeRegistries.BIOMES.containsKey(new ResourceLocation((String) string)));*/
        builder.pop();
        builder.push("fowl");
        spawnFowlEnable = builder
                .comment("Enable spawning of mob")
                .translation(Reference.MODID + ".config.spawn_fowl_enable")
                .define("spawnFowlEnable", true);
        spawnFowlWeight = builder
                .comment("Spawn weight of mob")
                .translation(Reference.MODID + ".config.spawn_fowl_weight")
                .defineInRange("spawnFowlWeight", 10, 0, 100);
        spawnFowlMinCount = builder
                .comment("Spawn min count of mob")
                .translation(Reference.MODID + ".config.spawn_fowl_min_count")
                .defineInRange("spawnFowlMinCount", 6, 1, 100);
        spawnFowlMaxCount = builder
                .comment("Spawn max count of mob")
                .translation(Reference.MODID + ".config.spawn_fowl_max_count")
                .defineInRange("spawnFowlMaxCount", 8, 1, 100);
        /*spawnFowlAllowedBiomesBlacklist = builder
                .comment("If spawnFowlAllowedBiomes is blacklist")
                .translation(Reference.MODID + ".config.spawn_fowl_allowed_biomes_blacklist")
                .define("spawnFowlAllowedBiomesBlacklist", false);*/
        fowlBreedingResult = builder
                .comment("Result of breeding after generations in captivity")
                .translation(Reference.MODID + ".config.fowl_breeding_result")
                .define("fowlBreedingResult", Objects.requireNonNull(ForgeRegistries.ENTITY_TYPES.getKey(EntityType.CHICKEN)).toString());
        /*spawnFowlAllowedBiomes = builder
                .comment("Spawn fowl in allowed biomes")
                .translation(Reference.MODID + ".config.spawn_fowl_allowed_biomes")
                .defineList("spawnFowlAllowedBiomes", ForgeRegistries.BIOMES.getValues().stream().filter(biome -> Set.of(FOREST, PLAINS, TAIGA, WINDSWEPT_HILLS, SAVANNA, SWAMP, JUNGLE, BEACH, MESA)
                                        .contains(biome)).collect(Collectors.toSet()).stream()
                                .map(value -> Objects.requireNonNull(ForgeRegistries.BIOMES.getKey(value)).toString()).collect(Collectors.toList()),
                        string -> string instanceof String && ForgeRegistries.BIOMES.containsKey(new ResourceLocation((String) string)));*/
        builder.pop();
        builder.push("mouflon");
        spawnMouflonEnable = builder
                .comment("Enable spawning of mob")
                .translation(Reference.MODID + ".config.spawn_mouflon_enable")
                .define("spawnMouflonEnable", true);
        spawnMouflonWeight = builder
                .comment("Spawn weight of mob")
                .translation(Reference.MODID + ".config.spawn_mouflon_weight")
                .defineInRange("spawnMouflonWeight", 10, 0, 100);
        spawnMouflonMinCount = builder
                .comment("Spawn min count of mob")
                .translation(Reference.MODID + ".config.spawn_mouflon_min_count")
                .defineInRange("spawnMouflonMinCount", 4, 1, 100);
        spawnMouflonMaxCount = builder
                .comment("Spawn max count of mob")
                .translation(Reference.MODID + ".config.spawn_mouflon_max_count")
                .defineInRange("spawnMouflonMaxCount", 8, 1, 100);
        /*spawnMouflonAllowedBiomesBlacklist = builder
                .comment("If spawnMouflonAllowedBiomes is blacklist")
                .translation(Reference.MODID + ".config.spawn_mouflon_allowed_biomes_blacklist")
                .define("spawnMouflonAllowedBiomesBlacklist", false);*/
        mouflonBreedingResult = builder
                .comment("Result of breeding after generations in captivity")
                .translation(Reference.MODID + ".config.mouflon_breeding_result")
                .define("mouflonBreedingResult", Objects.requireNonNull(ForgeRegistries.ENTITY_TYPES.getKey(EntityType.SHEEP)).toString());
        /*spawnMouflonAllowedBiomes = builder
                .comment("Spawn mouflon in allowed biomes")
                .translation(Reference.MODID + ".config.spawn_mouflon_allowed_biomes")
                .defineList("spawnMouflonAllowedBiomes", ForgeRegistries.BIOMES.getValues().stream().filter(biome -> Set.of(FOREST, PLAINS, TAIGA, WINDSWEPT_HILLS, SWAMP, MESA)
                                        .contains(biome)).collect(Collectors.toSet()).stream()
                                .map(value -> Objects.requireNonNull(ForgeRegistries.BIOMES.getKey(value)).toString()).collect(Collectors.toList()),
                        string -> string instanceof String && ForgeRegistries.BIOMES.containsKey(new ResourceLocation((String) string)));*/
        builder.pop();
        builder.push("mammoth");
        spawnMammothEnable = builder
                .comment("Enable spawning of mob")
                .translation(Reference.MODID + ".config.spawn_saberToothTiger_enable")
                .define("spawnMammothEnable", true);
        spawnMammothWeight = builder
                .comment("Spawn weight of mob")
                .translation(Reference.MODID + ".config.spawn_mammoth_weight")
                .defineInRange("spawnMammothWeight", 10, 0, 100);
        spawnMammothMinCount = builder
                .comment("Spawn min count of mob")
                .translation(Reference.MODID + ".config.spawn_mammoth_min_count")
                .defineInRange("spawnMammothMinCount", 2, 1, 100);
        spawnMammothMaxCount = builder
                .comment("Spawn max count of mob")
                .translation(Reference.MODID + ".config.spawn_mammoth_max_count")
                .defineInRange("spawnMammothMaxCount", 4, 1, 100);
        /*spawnMammothAllowedBiomesBlacklist = builder
                .comment("If spawnMammothAllowedBiomes is blacklist")
                .translation(Reference.MODID + ".config.spawn_mammoth_allowed_biomes_blacklist")
                .define("spawnMammothAllowedBiomesBlacklist", false);
        spawnMammothAllowedBiomes = builder
                .comment("Spawn mammoth in allowed biomes")
                .translation(Reference.MODID + ".config.spawn_mammoth_allowed_biomes")
                .defineList("spawnMammothAllowedBiomes", ForgeRegistries.BIOMES.getValues().stream().filter(biome -> Set.of(PLAINS, SAVANNA, ICY, TAIGA, WINDSWEPT_HILLS, DESERT, SAVANNA)
                                        .contains(biome)).collect(Collectors.toSet()).stream()
                                .map(value -> Objects.requireNonNull(ForgeRegistries.BIOMES.getKey(value)).toString()).collect(Collectors.toList()),
                        string -> string instanceof String && ForgeRegistries.BIOMES.containsKey(new ResourceLocation((String) string)));*/
        builder.pop();
        builder.push("saber tooth tiger");
        spawnSaberToothTigerEnable = builder
                .comment("Enable spawning of mob")
                .translation(Reference.MODID + ".config.spawn_saber_tooth_tiger_enable")
                .define("spawnSaberToothTigerEnable", true);
        spawnSaberToothTigerWeight = builder
                .comment("Spawn weight of mob")
                .translation(Reference.MODID + ".config.spawn_saber_tooth_tiger_weight")
                .defineInRange("spawnSaberToothTigerWeight", 5, 0, 100);
        spawnSaberToothTigerMinCount = builder
                .comment("Spawn min count of mob")
                .translation(Reference.MODID + ".config.spawn_saber_tooth_tiger_min_count")
                .defineInRange("spawnSaberToothTigerMinCount", 1, 1, 100);
        spawnSaberToothTigerMaxCount = builder
                .comment("Spawn max count of mob")
                .translation(Reference.MODID + ".config.spawn_saber_tooth_tiger_max_count")
                .defineInRange("spawnSaberToothTigerMaxCount", 2, 1, 100);
        /*spawnSaberToothTigerAllowedBiomesBlacklist = builder
                .comment("If spawnSaberToothTigerAllowedBiomes is blacklist")
                .translation(Reference.MODID + ".config.spawn_saber_tooth_tiger_allowed_biomes_blacklist")
                .define("spawnSaberToothTigerAllowedBiomesBlacklist", false);
        spawnSaberToothTigerAllowedBiomes = builder
                .comment("Spawn saber-tooth tiger in allowed biomes")
                .translation(Reference.MODID + ".config.spawn_saber_tooth_tiger_allowed_biomes")
                .defineList("spawnSaberToothTigerAllowedBiomes", ForgeRegistries.BIOMES.getValues().stream().filter(biome -> Set.of(PLAINS, SAVANNA, ICY, TAIGA, DESERT, FOREST, BEACH, JUNGLE, MUSHROOM_FIELDS)
                                        .contains(biome)).collect(Collectors.toSet()).stream()
                                .map(value -> Objects.requireNonNull(ForgeRegistries.BIOMES.getKey(value)).toString()).collect(Collectors.toList()),
                        string -> string instanceof String && ForgeRegistries.BIOMES.containsKey(new ResourceLocation((String) string)));*/
        builder.pop();
        builder.push("woolly rhino");
        spawnWoollyRhinoEnable = builder
                .comment("Enable spawning of mob")
                .translation(Reference.MODID + ".config.spawn_woolly_rhino_enable")
                .define("spawnWoollyRhinoEnable", true);
        spawnWoollyRhinoWeight = builder
                .comment("Spawn weight of mob")
                .translation(Reference.MODID + ".config.spawn_woolly_rhino_weight")
                .defineInRange("spawnWoollyRhinoWeight", 10, 0, 100);
        spawnWoollyRhinoMinCount = builder
                .comment("Spawn min count of mob")
                .translation(Reference.MODID + ".config.spawn_woolly_rhino_min_count")
                .defineInRange("spawnWoollyRhinoMinCount", 2, 1, 100);
        spawnWoollyRhinoMaxCount = builder
                .comment("Spawn max count of mob")
                .translation(Reference.MODID + ".config.spawn_woolly_rhino_max_count")
                .defineInRange("spawnWoollyRhinoMaxCount", 6, 1, 100);
        /*spawnWoollyRhinoAllowedBiomesBlacklist = builder
                .comment("If spawnWoollyRhinoAllowedBiomes is blacklist")
                .translation(Reference.MODID + ".config.spawn_woolly_rhino_allowed_biomes_blacklist")
                .define("spawnWoollyRhinoAllowedBiomesBlacklist", false);
        spawnWoollyRhinoAllowedBiomes = builder
                .comment("Spawn woolly rhino in allowed biomes")
                .translation(Reference.MODID + ".config.spawn_woolly_rhino_allowed_biomes")
                .defineList("spawnWoollyRhinoAllowedBiomes", ForgeRegistries.BIOMES.getValues().stream().filter(biome -> Set.of(PLAINS, SAVANNA, ICY, TAIGA, BEACH, SAVANNA, MUSHROOM_FIELDS, RIVER, SWAMP)
                                        .contains(biome)).collect(Collectors.toSet()).stream()
                                .map(value -> Objects.requireNonNull(ForgeRegistries.BIOMES.getKey(value)).toString()).collect(Collectors.toList()),
                        string -> string instanceof String && ForgeRegistries.BIOMES.containsKey(new ResourceLocation((String) string)));*/
        builder.pop();
        builder.push("terror bird");
        spawnTerrorBirdEnable = builder
                .comment("Enable spawning of mob")
                .translation(Reference.MODID + ".config.spawn_terror_bird_enable")
                .define("spawnTerrorBirdEnable", true);
        spawnTerrorBirdWeight = builder
                .comment("Spawn weight of mob")
                .translation(Reference.MODID + ".config.spawn_terror_bird_weight")
                .defineInRange("spawnTerrorBirdWeight", 5, 0, 100);
        spawnTerrorBirdMinCount = builder
                .comment("Spawn min count of mob")
                .translation(Reference.MODID + ".config.spawn_terror_bird_min_count")
                .defineInRange("spawnTerrorBirdMinCount", 1, 1, 100);
        spawnTerrorBirdMaxCount = builder
                .comment("Spawn max count of mob")
                .translation(Reference.MODID + ".config.spawn_terror_bird_max_count")
                .defineInRange("spawnTerrorBirdMaxCount", 3, 1, 100);
        /*spawnTerrorBirdAllowedBiomesBlacklist = builder
                .comment("If spawnTerrorBirdAllowedBiomes is blacklist")
                .translation(Reference.MODID + ".config.spawn_terror_bird_allowed_biomes_blacklist")
                .define("spawnTerrorBirdAllowedBiomesBlacklist", false);
        spawnTerrorBirdAllowedBiomes = builder
                .comment("Spawn terror bird in allowed biomes")
                .translation(Reference.MODID + ".config.spawn_terror_bird_allowed_biomes")
                .defineList("spawnTerrorBirdAllowedBiomes", ForgeRegistries.BIOMES.getValues().stream().filter(biome -> Set.of(PLAINS, SAVANNA, FOREST, JUNGLE, MUSHROOM_FIELDS)
                                        .contains(biome)).collect(Collectors.toSet()).stream()
                                .map(value -> Objects.requireNonNull(ForgeRegistries.BIOMES.getKey(value)).toString()).collect(Collectors.toList()),
                        string -> string instanceof String && ForgeRegistries.BIOMES.containsKey(new ResourceLocation((String) string)));*/
        builder.pop();
        builder.push("coelacanth");
        spawnCoelacanthEnable = builder
                .comment("Enable spawning of mob")
                .translation(Reference.MODID + ".config.spawn_coelacanth_enable")
                .define("spawnCoelacanthEnable", true);
        spawnCoelacanthWeight = builder
                .comment("Spawn weight of mob")
                .translation(Reference.MODID + ".config.spawn_coelacanth_weight")
                .defineInRange("spawnCoelacanthWeight", 5, 0, 100);
        spawnCoelacanthMinCount = builder
                .comment("Spawn min count of mob")
                .translation(Reference.MODID + ".config.spawn_coelacanth_min_count")
                .defineInRange("spawnCoelacanthMinCount", 1, 1, 100);
        spawnCoelacanthMaxCount = builder
                .comment("Spawn max count of mob")
                .translation(Reference.MODID + ".config.spawn_coelacanth_max_count")
                .defineInRange("spawnCoelacanthMaxCount", 3, 1, 100);
        /*spawnCoelacanthAllowedBiomesBlacklist = builder
                .comment("If spawnCoelacanthAllowedBiomes is blacklist")
                .translation(Reference.MODID + ".config.spawn_coelacanth_allowed_biomes_blacklist")
                .define("spawnCoelacanthAllowedBiomesBlacklist", false);
        spawnCoelacanthAllowedBiomes = builder
                .comment("Spawn coelacanth in allowed biomes")
                .translation(Reference.MODID + ".config.spawn_coelacanth_allowed_biomes")
                .defineList("spawnCoelacanthAllowedBiomes", ForgeRegistries.BIOMES.getValues().stream().filter(biome -> !Set.of(OCEAN, RIVER, SWAMP)
                                        .contains(biome)).collect(Collectors.toSet()).stream()
                                .map(value -> Objects.requireNonNull(ForgeRegistries.BIOMES.getKey(value)).toString()).collect(Collectors.toList()),
                        string -> string instanceof String && ForgeRegistries.BIOMES.containsKey(new ResourceLocation((String) string)));*/
        builder.pop();
        
        builder.push("aqueduct");
        infinityWaterSourceBiomeList = builder
                .comment("List of biomes where aqueduct doesn't remove water source")
                .translation(Reference.MODID + ".config.infinity_water_source_biome_list")
                .defineList("infinityWaterSourceBiomeList", ForgeRegistries.BIOMES.getValues().stream().filter(biome -> Set.of(OCEAN, RIVER, SWAMP)
                                        .contains(biome)).collect(Collectors.toSet()).stream()
                                .map(value -> Objects.requireNonNull(ForgeRegistries.BIOMES.getKey(value)).toString()).collect(Collectors.toList()),
                        string -> string instanceof String && ForgeRegistries.BIOMES.containsKey(new ResourceLocation((String) string)));
        builder.pop();

        builder.push("Config");
        GrassBedSleep = builder.comment("If grass bed can sleep").define("sleep", true);
        DryingRackNeedDaytime = builder.comment("If Drying rack need daytime").define("needDaytime", true);
        MakeFire = builder.define("makeFire", true);
        LitTorche = builder.define("litTorche", true);
        builder.pop();
    }
}