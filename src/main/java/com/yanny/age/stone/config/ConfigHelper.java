package com.yanny.age.stone.config;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.stream.Collectors;

public class ConfigHelper {

    public static void bakeServer() {
        Config.removeVanillaRecipes = ConfigHolder.SERVER.removeVanillaRecipes.get();
        Config.removeVanillaGeneratedAnimals = ConfigHolder.SERVER.removeVanillaGeneratedAnimals.get();
        Config.domesticateAfterGenerations = ConfigHolder.SERVER.domesticateAfterGenerations.get();
        Config.tanningRackFinishChance = ConfigHolder.SERVER.tanningRackFinishChance.get();
        Config.changeMiningLevelForVanillaBlocks = ConfigHolder.SERVER.changeMiningLevelForVanillaBlocks.get();
        Config.aquaductTickChanceBoneMealEffect = ConfigHolder.SERVER.aquaductTickChanceBoneMealEffect.get();
        Config.aquaductEffectRange = ConfigHolder.SERVER.aquaductEffectRange.get();
        Config.aquaductFillPerTick = ConfigHolder.SERVER.aquaductFillPerTick.get();
        Config.aquaductUsePerTick = ConfigHolder.SERVER.aquaductUsePerTick.get();
        Config.feederTickChanceBreedAnimalEffect = ConfigHolder.SERVER.feederTickChanceBreedAnimalEffect.get();
        Config.feederEffectRange = ConfigHolder.SERVER.feederEffectRange.get();
        Config.forceToolForWood = ConfigHolder.SERVER.forceToolForWood.get();
        Config.givePlayerManualOnFirstConnect = ConfigHolder.SERVER.givePlayerManualOnFirstConnect.get();
        Config.fishingNetChance = ConfigHolder.SERVER.fishingNetChance.get();

        Config.abandonedCampSpawnChance = ConfigHolder.SERVER.abandonedCampSpawnChance.get();
        Config.burialPlaceSpawnChance = ConfigHolder.SERVER.burialPlaceSpawnChance.get();

        Config.abandonedCampAllowedBiomes.clear();
        Config.abandonedCampAllowedBiomes.addAll(ConfigHolder.SERVER.abandonedCampAllowedBiomes.get().stream()
                .map(value -> ForgeRegistries.BIOMES.getValue(new ResourceLocation(value))).collect(Collectors.toList()));
        Config.burialPlaceAllowedBiomes.clear();
        Config.burialPlaceAllowedBiomes.addAll(ConfigHolder.SERVER.burialPlaceAllowedBiomes.get().stream()
                .map(value -> ForgeRegistries.BIOMES.getValue(new ResourceLocation(value))).collect(Collectors.toList()));

        Config.spawnDeerEnable = ConfigHolder.SERVER.spawnDeerEnable.get();
        Config.spawnDeerWeight = ConfigHolder.SERVER.spawnDeerWeight.get();
        Config.spawnDeerMinCount = ConfigHolder.SERVER.spawnDeerMinCount.get();
        Config.spawnDeerMaxCount = ConfigHolder.SERVER.spawnDeerMaxCount.get();
        Config.spawnBoarEnable = ConfigHolder.SERVER.spawnBoarEnable.get();
        Config.spawnBoarWeight = ConfigHolder.SERVER.spawnBoarWeight.get();
        Config.spawnBoarMinCount = ConfigHolder.SERVER.spawnBoarMinCount.get();
        Config.spawnBoarMaxCount = ConfigHolder.SERVER.spawnBoarMaxCount.get();
        Config.spawnAurochEnable = ConfigHolder.SERVER.spawnAurochEnable.get();
        Config.spawnAurochWeight = ConfigHolder.SERVER.spawnAurochWeight.get();
        Config.spawnAurochMinCount = ConfigHolder.SERVER.spawnAurochMinCount.get();
        Config.spawnAurochMaxCount = ConfigHolder.SERVER.spawnAurochMaxCount.get();
        Config.spawnFowlEnable = ConfigHolder.SERVER.spawnFowlEnable.get();
        Config.spawnFowlWeight = ConfigHolder.SERVER.spawnFowlWeight.get();
        Config.spawnFowlMinCount = ConfigHolder.SERVER.spawnFowlMinCount.get();
        Config.spawnFowlMaxCount = ConfigHolder.SERVER.spawnFowlMaxCount.get();
        Config.spawnMouflonEnable = ConfigHolder.SERVER.spawnMouflonEnable.get();
        Config.spawnMouflonWeight = ConfigHolder.SERVER.spawnMouflonWeight.get();
        Config.spawnMouflonMinCount = ConfigHolder.SERVER.spawnMouflonMinCount.get();
        Config.spawnMouflonMaxCount = ConfigHolder.SERVER.spawnMouflonMaxCount.get();
        Config.spawnMammothEnable = ConfigHolder.SERVER.spawnMammothEnable.get();
        Config.spawnMammothWeight = ConfigHolder.SERVER.spawnMammothWeight.get();
        Config.spawnMammothMinCount = ConfigHolder.SERVER.spawnMammothMinCount.get();
        Config.spawnMammothMaxCount = ConfigHolder.SERVER.spawnMammothMaxCount.get();
        Config.spawnSaberToothTigerEnable = ConfigHolder.SERVER.spawnSaberToothTigerEnable.get();
        Config.spawnSaberToothTigerWeight = ConfigHolder.SERVER.spawnSaberToothTigerWeight.get();
        Config.spawnSaberToothTigerMinCount = ConfigHolder.SERVER.spawnSaberToothTigerMinCount.get();
        Config.spawnSaberToothTigerMaxCount = ConfigHolder.SERVER.spawnSaberToothTigerMaxCount.get();
        Config.spawnWoollyRhinoEnable = ConfigHolder.SERVER.spawnWoollyRhinoEnable.get();
        Config.spawnWoollyRhinoWeight = ConfigHolder.SERVER.spawnWoollyRhinoWeight.get();
        Config.spawnWoollyRhinoMinCount = ConfigHolder.SERVER.spawnWoollyRhinoMinCount.get();
        Config.spawnWoollyRhinoMaxCount = ConfigHolder.SERVER.spawnWoollyRhinoMaxCount.get();
    }

    public static void bakeClient() {
    }
}
