package com.yanny.age.stone.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class CommonCofing {
    public static ForgeConfigSpec CONFIG;

    public static ForgeConfigSpec.BooleanValue GrassBedSleep;
    public static ForgeConfigSpec.BooleanValue DryingRackNeedDaytime;

    static {
        ForgeConfigSpec.Builder builder = new ForgeConfigSpec.Builder();
        TileBlockConfig(builder);
        CONFIG = builder.build();
    }

    public static void TileBlockConfig(ForgeConfigSpec.Builder builder) {
        builder.push("TileBlockConfig");
        GrassBedSleep = builder.comment("If grass bed can sleep").define("sleep", true);
        DryingRackNeedDaytime = builder.comment("If Drying rack need daytime").define("need daytime", true);
        builder.pop();
    }
}
