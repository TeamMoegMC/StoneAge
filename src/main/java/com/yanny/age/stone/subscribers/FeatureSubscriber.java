package com.yanny.age.stone.subscribers;

import com.yanny.age.stone.structures.AbandonedCampFeature;
import com.yanny.age.stone.structures.BurialPlaceFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.ProbabilityFeatureConfiguration;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.ObjectHolder;

import javax.annotation.Nonnull;

import static com.yanny.age.stone.Reference.MODID;

@SuppressWarnings("WeakerAccess")
@ObjectHolder(MODID)
@Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class FeatureSubscriber {
    public static Feature<ProbabilityFeatureConfiguration> abandoned_camp_feature = null;
    public static Feature<ProbabilityFeatureConfiguration> burial_place_feature = null;

    @SuppressWarnings("unchecked")
    @SubscribeEvent
    public static void registerFeature(@Nonnull RegistryEvent.Register<Feature<?>>  event) {
        IForgeRegistry<Feature<?>> registry = event.getRegistry();

        abandoned_camp_feature = (Feature<ProbabilityFeatureConfiguration>) new AbandonedCampFeature(ProbabilityFeatureConfiguration.CODEC).setRegistryName(MODID, "abandoned_camp_feature");
        burial_place_feature = (Feature<ProbabilityFeatureConfiguration>) new BurialPlaceFeature(ProbabilityFeatureConfiguration.CODEC).setRegistryName(MODID, "burial_place_feature");

        registry.register(abandoned_camp_feature);
    }
}
