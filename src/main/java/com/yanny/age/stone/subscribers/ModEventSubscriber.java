package com.yanny.age.stone.subscribers;

import com.yanny.age.stone.ExampleMod;
import com.yanny.age.stone.client.models.*;
import com.yanny.age.stone.client.renderer.*;
import com.yanny.age.stone.compatibility.top.TopCompatibility;
import com.yanny.age.stone.entities.*;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.entity.SpawnPlacementRegisterEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;

import javax.annotation.Nonnull;

import static com.yanny.age.stone.Reference.MODID;

@Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEventSubscriber {

    @SubscribeEvent
    public static void init(@Nonnull FMLCommonSetupEvent event) {
        ExampleMod.proxy.init();
    }

    @SubscribeEvent
    public static void registerTOP(@Nonnull InterModEnqueueEvent event) {
        TopCompatibility.register();
    }

    /*@SubscribeEvent
    public static void onModConfigEvent(@Nonnull ModConfig.ModConfigEvent event) {
        final ModConfig config = event.getConfig();

        if (!config.getModId().equals(MODID)) {
            return;
        }

        // Rebake the configs when they change
        if (config.getSpec() == ConfigHolder.CLIENT_SPEC) {
            ConfigHelper.bakeClient();
        } else if (config.getSpec() == ConfigHolder.SERVER_SPEC) {
            ConfigHelper.bakeServer();
        }
    }*/

    @SuppressWarnings("ConstantConditions")
    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    public static void onClientSetupEvent(@Nonnull FMLClientSetupEvent event) {
        ItemBlockRenderTypes.setRenderLayer(BlockSubscriber.fishing_net, RenderType.cutoutMipped());
        ItemBlockRenderTypes.setRenderLayer(BlockSubscriber.dried_grass_bed, RenderType.cutoutMipped());
    }

    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    public static void RegisterEntityRender(EntityRenderersEvent.RegisterRenderers event){
        event.registerBlockEntityRenderer(TileEntitySubscriber.flint_workbench, FlintWorkbenchRenderer::new);
        event.registerBlockEntityRenderer(TileEntitySubscriber.drying_rack, DryingRackRenderer::new);
        event.registerBlockEntityRenderer(TileEntitySubscriber.tanning_rack, TanningRackRenderer::new);
        event.registerBlockEntityRenderer(TileEntitySubscriber.stone_chest, StoneChestRenderer::new);
        event.registerBlockEntityRenderer(TileEntitySubscriber.tree_stump, TreeStumpRenderer::new);
        event.registerBlockEntityRenderer(TileEntitySubscriber.feeder, FeederRenderer::new);
        event.registerBlockEntityRenderer(TileEntitySubscriber.millstone, MillstoneRenderer::new);

        event.registerEntityRenderer(EntitySubscriber.deer,DeerRenderer::new);
        event.registerEntityRenderer(EntitySubscriber.boar,BoarRenderer::new);
        event.registerEntityRenderer(EntitySubscriber.auroch,AurochRenderer::new);
        event.registerEntityRenderer(EntitySubscriber.fowl,FowlRenderer::new);
        event.registerEntityRenderer(EntitySubscriber.mouflon,MouflonRenderer::new);
        event.registerEntityRenderer(EntitySubscriber.mammoth,MammothRenderer::new);
        event.registerEntityRenderer(EntitySubscriber.saber_tooth_tiger,SaberToothTigerRenderer::new);
        event.registerEntityRenderer(EntitySubscriber.woolly_rhino,WoollyRhinoRenderer::new);
        event.registerEntityRenderer(EntitySubscriber.terror_bird,TerrorBirdRenderer::new);
        event.registerEntityRenderer(EntitySubscriber.coelacanth,CoelacanthRenderer::new);
    }

    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    public static void registerLayer(EntityRenderersEvent.RegisterLayerDefinitions event){
        event.registerLayerDefinition(AurochModel.AUROCH_LAYER, AurochModel::createBodyLayer);
        event.registerLayerDefinition(BoarModel.BOAR_LAYER, BoarModel::createBodyLayer);
        event.registerLayerDefinition(CoelacanthModel.COELACANTH_LAYER, CoelacanthModel::createBodyLayer);
        event.registerLayerDefinition(DeerModel.DEER_LAYER, DeerModel::createBodyLayer);

        event.registerLayerDefinition(MillstoneModel.MILLSTONE_LAYER, MillstoneModel::createBodyLayer);
        event.registerLayerDefinition(StoneChestModel.STONECHEST_LAYER, StoneChestModel::createBodyLayer);
    }
    /*@SubscribeEvent
    public static void dataGen(@Nonnull GatherDataEvent event) {
        if (event.includeServer()) {
            // ages_api MODID
            RecipeGenerator recipeGenerator = new RecipeGenerator(event.getGenerator());

            event.getGenerator().addProvider(recipeGenerator);
        }

        if (event.includeClient()) {
            event.getGenerator().addProvider(new LootTableGenerator(event.getGenerator()));
        }
    }*/
    @SubscribeEvent
    public static void entityAttributeCreatingEvent(@Nonnull EntityAttributeCreationEvent event) {
        event.put(EntitySubscriber.deer, DeerEntity.createAttributes());
        event.put(EntitySubscriber.boar, BoarEntity.createAttributes());
        event.put(EntitySubscriber.auroch, AurochEntity.createAttributes());
        event.put(EntitySubscriber.fowl, FowlEntity.createAttributes());
        event.put(EntitySubscriber.mouflon, MouflonEntity.createAttributes());
        event.put(EntitySubscriber.mammoth, MammothEntity.createAttributes());
        event.put(EntitySubscriber.saber_tooth_tiger, SaberToothTigerEntity.createAttributes());
        event.put(EntitySubscriber.woolly_rhino, WoollyRhinoEntity.createAttributes());
        event.put(EntitySubscriber.terror_bird, TerrorBirdEntity.createAttributes());
        event.put(EntitySubscriber.coelacanth, CoelacanthEntity.createAttributes());
    }
    @SubscribeEvent
    public static void onSpawnPlacementRegister(SpawnPlacementRegisterEvent event) {
        event.register(EntitySubscriber.deer, SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Animal::checkAnimalSpawnRules, SpawnPlacementRegisterEvent.Operation.OR);
        event.register(EntitySubscriber.boar, SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Animal::checkAnimalSpawnRules, SpawnPlacementRegisterEvent.Operation.OR);
        event.register(EntitySubscriber.auroch, SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Animal::checkAnimalSpawnRules, SpawnPlacementRegisterEvent.Operation.OR);
        event.register(EntitySubscriber.fowl, SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Animal::checkAnimalSpawnRules, SpawnPlacementRegisterEvent.Operation.OR);
        event.register(EntitySubscriber.mouflon, SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Animal::checkAnimalSpawnRules, SpawnPlacementRegisterEvent.Operation.OR);
        event.register(EntitySubscriber.mammoth, SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Animal::checkAnimalSpawnRules, SpawnPlacementRegisterEvent.Operation.OR);
        event.register(EntitySubscriber.saber_tooth_tiger, SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, WildAnimalEntity::canMonsterSpawn, SpawnPlacementRegisterEvent.Operation.OR);
        event.register(EntitySubscriber.woolly_rhino, SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Animal::checkAnimalSpawnRules, SpawnPlacementRegisterEvent.Operation.OR);
        event.register(EntitySubscriber.terror_bird, SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, WildAnimalEntity::canMonsterSpawn, SpawnPlacementRegisterEvent.Operation.OR);
        event.register(EntitySubscriber.coelacanth, SpawnPlacements.Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, CoelacanthEntity::canSpawn, SpawnPlacementRegisterEvent.Operation.OR);
        /*SpawnPlacements.register(deer, SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Animal::checkAnimalSpawnRules);
        SpawnPlacements.register(boar, SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Animal::checkAnimalSpawnRules);
        SpawnPlacements.register(auroch, SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Animal::checkAnimalSpawnRules);
        SpawnPlacements.register(fowl, SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Animal::checkAnimalSpawnRules);
        SpawnPlacements.register(mouflon, SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Animal::checkAnimalSpawnRules);
        SpawnPlacements.register(mammoth, SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Animal::checkAnimalSpawnRules);
        SpawnPlacements.register(saber_tooth_tiger, SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, WildAnimalEntity::canMonsterSpawn);
        SpawnPlacements.register(woolly_rhino, SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Animal::checkAnimalSpawnRules);
        SpawnPlacements.register(terror_bird, SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, WildAnimalEntity::canMonsterSpawn);
        SpawnPlacements.register(coelacanth, SpawnPlacements.Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, CoelacanthEntity::canSpawn);*/
    }
}
