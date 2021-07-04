package com.yanny.age.stone.config;

import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.EnumSet;
import java.util.Set;
import java.util.stream.Collectors;

public class CommonConfig {
    public static ForgeConfigSpec CONFIG;

    public static ForgeConfigSpec.BooleanValue GrassBedSleep;
    public static ForgeConfigSpec.BooleanValue DryingRackNeedDaytime;
    public static ForgeConfigSpec.BooleanValue MakeFire;
    public static ForgeConfigSpec.BooleanValue LitTorche;
    public static final Set<Biome> DEFAULT_INFINITY_WATER_SOURCE_BIOMES;

    static {
        DEFAULT_INFINITY_WATER_SOURCE_BIOMES = ForgeRegistries.BIOMES.getValues().stream().filter(biome -> EnumSet.of(Biome.Category.OCEAN, Biome.Category.RIVER, Biome.Category.SWAMP)
                .contains(biome.getCategory())).collect(Collectors.toSet());
    }

    static {
        ForgeConfigSpec.Builder builder = new ForgeConfigSpec.Builder();
        TileBlockConfig(builder);
        CONFIG = builder.build();
    }

    public static void TileBlockConfig(ForgeConfigSpec.Builder builder) {
        builder.push("Config");
        GrassBedSleep = builder.comment("If grass bed can sleep").define("sleep", true);
        DryingRackNeedDaytime = builder.comment("If Drying rack need daytime").define("need daytime", true);
        MakeFire = builder.define("Make fire", true);
        LitTorche = builder.define("Lit torche", true);
        builder.pop();
    }
}
