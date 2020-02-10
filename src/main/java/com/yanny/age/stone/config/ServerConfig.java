package com.yanny.age.stone.config;

import com.yanny.age.stone.Reference;
import com.yanny.age.stone.subscribers.ModEventSubscriber;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

class ServerConfig {
    final ForgeConfigSpec.BooleanValue removeVanillaRecipes;
    final ForgeConfigSpec.BooleanValue removeVanillaGeneratedAnimals;
    final ForgeConfigSpec.IntValue domesticateAfterGenerations;
    final ForgeConfigSpec.DoubleValue tanningRackFinishChance;
    final ForgeConfigSpec.BooleanValue changeMiningLevelForVanillaBlocks;
    final ForgeConfigSpec.IntValue aquaductTickChanceBoneMealEffect;
    final ForgeConfigSpec.IntValue aquaductEffectRange;
    final ForgeConfigSpec.IntValue aquaductFillPerTick;
    final ForgeConfigSpec.IntValue aquaductUsePerTick;
    final ForgeConfigSpec.IntValue feederTickChanceBreedAnimalEffect;
    final ForgeConfigSpec.IntValue feederEffectRange;
    final ForgeConfigSpec.BooleanValue forceToolForWood;
    final ForgeConfigSpec.BooleanValue givePlayerManualOnFirstConnect;
    final ForgeConfigSpec.IntValue fishingNetChance;

    final ForgeConfigSpec.DoubleValue abandonedCampSpawnChance;
    final ForgeConfigSpec.DoubleValue burialPlaceSpawnChance;

    final ForgeConfigSpec.ConfigValue<List<? extends String>> abandonedCampAllowedBiomes;
    final ForgeConfigSpec.ConfigValue<List<? extends String>> burialPlaceAllowedBiomes;

    final ForgeConfigSpec.BooleanValue spawnDeerEnable;
    final ForgeConfigSpec.IntValue spawnDeerWeight;
    final ForgeConfigSpec.IntValue spawnDeerMinCount;
    final ForgeConfigSpec.IntValue spawnDeerMaxCount;
    final ForgeConfigSpec.BooleanValue spawnBoarEnable;
    final ForgeConfigSpec.IntValue spawnBoarWeight;
    final ForgeConfigSpec.IntValue spawnBoarMinCount;
    final ForgeConfigSpec.IntValue spawnBoarMaxCount;
    final ForgeConfigSpec.BooleanValue spawnAurochEnable;
    final ForgeConfigSpec.IntValue spawnAurochWeight;
    final ForgeConfigSpec.IntValue spawnAurochMinCount;
    final ForgeConfigSpec.IntValue spawnAurochMaxCount;
    final ForgeConfigSpec.BooleanValue spawnFowlEnable;
    final ForgeConfigSpec.IntValue spawnFowlWeight;
    final ForgeConfigSpec.IntValue spawnFowlMinCount;
    final ForgeConfigSpec.IntValue spawnFowlMaxCount;
    final ForgeConfigSpec.BooleanValue spawnMouflonEnable;
    final ForgeConfigSpec.IntValue spawnMouflonWeight;
    final ForgeConfigSpec.IntValue spawnMouflonMinCount;
    final ForgeConfigSpec.IntValue spawnMouflonMaxCount;
    final ForgeConfigSpec.BooleanValue spawnMammothEnable;
    final ForgeConfigSpec.IntValue spawnMammothWeight;
    final ForgeConfigSpec.IntValue spawnMammothMinCount;
    final ForgeConfigSpec.IntValue spawnMammothMaxCount;
    final ForgeConfigSpec.BooleanValue spawnSaberToothTigerEnable;
    final ForgeConfigSpec.IntValue spawnSaberToothTigerWeight;
    final ForgeConfigSpec.IntValue spawnSaberToothTigerMinCount;
    final ForgeConfigSpec.IntValue spawnSaberToothTigerMaxCount;
    final ForgeConfigSpec.BooleanValue spawnWoollyRhinoEnable;
    final ForgeConfigSpec.IntValue spawnWoollyRhinoWeight;
    final ForgeConfigSpec.IntValue spawnWoollyRhinoMinCount;
    final ForgeConfigSpec.IntValue spawnWoollyRhinoMaxCount;

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
        changeMiningLevelForVanillaBlocks = builder
                .comment("Change mining level for vanilla items (change it to -1, to be mineable by antler pickaxe)")
                .translation(Reference.MODID + ".config.change_mining_level_for_vanilla_blocks")
                .define("changeMiningLevelForVanillaBlocks", true);
        aquaductTickChanceBoneMealEffect = builder
                .comment("Chance of bonemeal efect from aquaduct every 1/X tick (randomly)")
                .translation(Reference.MODID + ".config.aquaduct_tick_chance_bone_meal_effect")
                .defineInRange("aquaductTickChanceBoneMealEffect", 200, 1, Integer.MAX_VALUE);
        aquaductEffectRange = builder
                .comment("Aquaduct area of bonemeal effect")
                .translation(Reference.MODID + ".config.aquaduct_effect_range")
                .defineInRange("aquaductEffectRange", 4, 0, 15);
        aquaductFillPerTick = builder
                .comment("Fill amount per tick when filling from water source")
                .translation(Reference.MODID + ".config.aquaduct_fill_per_tick")
                .defineInRange("aquaductFillPerTick", 4, 1, Integer.MAX_VALUE);
        aquaductUsePerTick = builder
                .comment("Usage of water per tick when activated aquaduct")
                .translation(Reference.MODID + ".config.aquaduct_use_per_tick")
                .defineInRange("aquaductUsePerTick", 1, 1, Integer.MAX_VALUE);
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
        builder.pop();

        builder.push("structures");
        abandonedCampSpawnChance = builder
                .comment("Abandoned camp spawn chance")
                .translation(Reference.MODID + ".config.abandoned_camp_spawn_chance")
                .defineInRange("abandonedCampSpawnChance", 0.009, Double.MIN_VALUE, 1.0);
        abandonedCampAllowedBiomes = builder
                .comment("Abandoned camp allowed biomes")
                .translation(Reference.MODID + ".config.abandoned_camp_allowed_biomes")
                .defineList("abandonedCampAllowedBiomes", ModEventSubscriber.DEFAULT_BIOMES.stream()
                        .map(value -> Objects.requireNonNull(value.getRegistryName()).toString()).collect(Collectors.toList()),
                        string -> ForgeRegistries.BIOMES.containsKey(new ResourceLocation((String) string)));
        burialPlaceSpawnChance = builder
                .comment("Burial place spawn chance")
                .translation(Reference.MODID + ".config.burial_place_spawn_chance")
                .defineInRange("burialPlaceSpawnChance", 0.01, Double.MIN_VALUE, 1.0);
        burialPlaceAllowedBiomes = builder
                .comment("Burial place allowed biomes")
                .translation(Reference.MODID + ".config.burial_place_allowed_biomes")
                .defineList("burialPlaceAllowedBiomes", ModEventSubscriber.DEFAULT_BIOMES.stream()
                        .map(value -> Objects.requireNonNull(value.getRegistryName()).toString()).collect(Collectors.toList()),
                        string -> ForgeRegistries.BIOMES.containsKey(new ResourceLocation((String) string)));
        builder.pop();

        builder.push("mob spawning");
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
        builder.pop();
    }
}
