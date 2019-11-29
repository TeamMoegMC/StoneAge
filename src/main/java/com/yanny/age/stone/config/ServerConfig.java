package com.yanny.age.stone.config;

import com.yanny.age.stone.Reference;
import net.minecraftforge.common.ForgeConfigSpec;

import javax.annotation.Nonnull;

class ServerConfig {
    final ForgeConfigSpec.BooleanValue removeVanillaRecipes;
    final ForgeConfigSpec.BooleanValue removeVanillaGeneratedAnimals;
    final ForgeConfigSpec.IntValue domesticateAfterGenerations;
    final ForgeConfigSpec.DoubleValue tanningRackFinishChance;
    final ForgeConfigSpec.BooleanValue changeMiningLevelForVanillaBlocks;
    final ForgeConfigSpec.IntValue aquaductTickChanceBoneMealEffect;

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

    final ForgeConfigSpec.IntValue clayVesselCapacity;

    ServerConfig(@Nonnull final ForgeConfigSpec.Builder builder) {
        builder.push("general");
        removeVanillaRecipes = builder
                .comment("Remove vanilla recipes that are changed by mod or break mod gameplay")
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
                .comment("Chance of bonemeal efect from aquaduct every X ticks (randomly)")
                .translation(Reference.MODID + ".config.aquaduct_tich_chance_bone_meal_effect")
                .defineInRange("aquaductTickChanceBoneMealEffect", 200, 1, Integer.MAX_VALUE);
        clayVesselCapacity = builder
                .comment("Fluid capacity in mB of clay vessel")
                .translation(Reference.MODID + ".config.clay_vessel_capaicty")
                .defineInRange("clayVesselCapacity", 8000, 1, Integer.MAX_VALUE);
        builder.pop();

        builder.push("mob spawning");
        spawnDeerEnable = builder
                .comment("Enable spawning of mob")
                .translation(Reference.MODID + ".config.spawn_deer_enable")
                .define("spawnDeerEnable", true);
        spawnDeerWeight = builder
                .comment("Spawn weight of mob")
                .translation(Reference.MODID + ".config.spawn_deer_weight")
                .defineInRange("spawnDeerWeight", 10, 0, 100);
        spawnDeerMinCount = builder
                .comment("Spawn min count of mob")
                .translation(Reference.MODID + ".config.spawn_deer_min_count")
                .defineInRange("spawnDeerMinCount", 8, 1, 100);
        spawnDeerMaxCount = builder
                .comment("Spawn max count of mob")
                .translation(Reference.MODID + ".config.spawn_deer_max_count")
                .defineInRange("spawnDeerMaxCount", 12, 1, 100);
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
        builder.pop();
    }
}
