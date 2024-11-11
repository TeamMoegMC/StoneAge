package com.yanny.age.stone.subscribers;

import com.yanny.age.stone.entities.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nonnull;

import static com.yanny.age.stone.Reference.MODID;
import static net.minecraft.world.entity.MobCategory.CREATURE;
import static net.minecraft.world.entity.MobCategory.WATER_CREATURE;

import net.minecraftforge.registries.RegisterEvent;

@SuppressWarnings({"unused", "unchecked"})
//@ObjectHolder(Reference.MODID)
//@Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class EntitySubscriber {
    public static EntityType<DeerEntity> deer = (EntityType<DeerEntity>) EntityType.Builder.of(DeerEntity::new, CREATURE)
            .sized(0.9f, 1.6f).clientTrackingRange(10).build("deer");
    public static final EntityType<BoarEntity> boar = (EntityType<BoarEntity>) EntityType.Builder.of(BoarEntity::new, CREATURE)
            .sized(0.9f, 1.5f).clientTrackingRange(10).build("boar");
    public static final EntityType<AurochEntity> auroch = (EntityType<AurochEntity>) EntityType.Builder.of(AurochEntity::new, CREATURE)
            .sized(0.9f, 1.5f).clientTrackingRange(10).build("auroch");
    public static final EntityType<FowlEntity> fowl = (EntityType<FowlEntity>) EntityType.Builder.of(FowlEntity::new, CREATURE)
            .sized(0.7f, 0.7f).clientTrackingRange(10).build("fowl");
    public static final EntityType<MouflonEntity> mouflon = (EntityType<MouflonEntity>) EntityType.Builder.of(MouflonEntity::new, CREATURE)
            .sized(0.9f, 1.5f).clientTrackingRange(10).build("mouflon");
    public static final EntityType<MammothEntity> mammoth = (EntityType<MammothEntity>) EntityType.Builder.of(MammothEntity::new, CREATURE)
            .sized(1.9f, 2.0f).clientTrackingRange(10).build("mammoth");
    public static final EntityType<SaberToothTigerEntity> saber_tooth_tiger = (EntityType<SaberToothTigerEntity>) EntityType.Builder.of(SaberToothTigerEntity::new, CREATURE)
            .sized(0.9f, 1.1f).clientTrackingRange(8).build("saber_tooth_tiger");
    public static final EntityType<WoollyRhinoEntity> woolly_rhino = (EntityType<WoollyRhinoEntity>) EntityType.Builder.of(WoollyRhinoEntity::new, CREATURE)
            .sized(1.7f, 1.4f).clientTrackingRange(10).build("woolly_rhino");
    public static final EntityType<TerrorBirdEntity> terror_bird = (EntityType<TerrorBirdEntity>) EntityType.Builder.of(TerrorBirdEntity::new, CREATURE)
            .sized(0.9f, 1.9f).clientTrackingRange(8).build("terror_bird");
    public static final EntityType<CoelacanthEntity> coelacanth = (EntityType<CoelacanthEntity>) EntityType.Builder.of(CoelacanthEntity::new, WATER_CREATURE)
            .sized(0.8f, 0.35f).clientTrackingRange(4).build("coelacanth");

    public static final Item deer_spawn_egg = null;
    public static final Item boar_spawn_egg = null;
    public static final Item auroch_spawn_egg = null;
    public static final Item fowl_spawn_egg = null;
    public static final Item mouflon_spawn_egg = null;
    public static final Item mammoth_spawn_egg = null;
    public static final Item saber_tooth_tiger_spawn_egg = null;
    public static final Item woolly_rhino_spawn_egg = null;
    public static final Item terror_bird_spawn_egg = null;
    public static final Item coelacanth_spawn_egg = null;
//    static {
//        deer = (EntityType<DeerEntity>) EntityType.Builder.of(DeerEntity::new, CREATURE)
//                .sized(0.9f, 1.6f).clientTrackingRange(10).build("");
//        boar = (EntityType<BoarEntity>) EntityType.Builder.of(BoarEntity::new, CREATURE)
//                .sized(0.9f, 1.5f).clientTrackingRange(10).build("");
//        auroch = (EntityType<AurochEntity>) EntityType.Builder.of(AurochEntity::new, CREATURE)
//                .sized(0.9f, 1.5f).clientTrackingRange(10).build("");
//        fowl = (EntityType<FowlEntity>) EntityType.Builder.of(FowlEntity::new, CREATURE)
//                .sized(0.7f, 0.7f).clientTrackingRange(10).build("");
//        mouflon = (EntityType<MouflonEntity>) EntityType.Builder.of(MouflonEntity::new, CREATURE)
//                .sized(0.9f, 1.5f).clientTrackingRange(10).build("");
//        mammoth = (EntityType<MammothEntity>) EntityType.Builder.of(MammothEntity::new, CREATURE)
//                .sized(1.9f, 2.0f).clientTrackingRange(10).build("");
//        saber_tooth_tiger = (EntityType<SaberToothTigerEntity>) EntityType.Builder.of(SaberToothTigerEntity::new, CREATURE)
//                .sized(0.9f, 1.1f).clientTrackingRange(8).build("");
//        woolly_rhino = (EntityType<WoollyRhinoEntity>) EntityType.Builder.of(WoollyRhinoEntity::new, CREATURE)
//                .sized(1.7f, 1.4f).clientTrackingRange(10).build("");
//        terror_bird = (EntityType<TerrorBirdEntity>) EntityType.Builder.of(TerrorBirdEntity::new, CREATURE)
//                .sized(0.9f, 1.9f).clientTrackingRange(8).build("");
//        coelacanth = (EntityType<CoelacanthEntity>) EntityType.Builder.of(CoelacanthEntity::new, WATER_CREATURE)
//                .sized(0.8f, 0.35f).clientTrackingRange(4).build("");
//    }
//    @SubscribeEvent


    public static void registerEntities(@Nonnull RegisterEvent event) {
        /*IForgeRegistry<EntityType<?>> registry = event.getRegistry();
        registry.register(deer);
        registry.register(boar);
        registry.register(auroch);
        registry.register(fowl);
        registry.register(mouflon);
        registry.register(mammoth);
        registry.register(saber_tooth_tiger);
        registry.register(woolly_rhino);
        registry.register(terror_bird);
        registry.register(coelacanth);*/

        event.register(ForgeRegistries.Keys.ENTITY_TYPES,
                helper -> {
                    helper.register(new ResourceLocation(MODID, "deer"), deer);
                    helper.register(new ResourceLocation(MODID, "boar"), boar);
                    helper.register(new ResourceLocation(MODID, "auroch"), auroch);
                    helper.register(new ResourceLocation(MODID, "fowl"), fowl);
                    helper.register(new ResourceLocation(MODID, "mouflon"), mouflon);
                    helper.register(new ResourceLocation(MODID, "mammoth"), mammoth);
                    helper.register(new ResourceLocation(MODID, "saber_tooth_tiger"), saber_tooth_tiger);
                    helper.register(new ResourceLocation(MODID, "woolly_rhino"), woolly_rhino);
                    helper.register(new ResourceLocation(MODID, "terror_bird"), terror_bird);
                    helper.register(new ResourceLocation(MODID, "coelacanth"), coelacanth);
                }
        );
    }

    /*@SubscribeEvent
    public static void registerSpawnEggs(@Nonnull RegistryEvent.Register<Item> event) {
        IForgeRegistry<Item> registry = event.getRegistry();
        registry.register(new SpawnEggItem(deer, 0x00ffff, 0xff00ff,
                new Item.Properties().tab(ModItemGroup.AGES)).setRegistryName(Reference.MODID, "deer_spawn_egg"));
        registry.register(new SpawnEggItem(boar, 0xc0c0ff, 0xff00ff,
                new Item.Properties().tab(ModItemGroup.AGES)).setRegistryName(Reference.MODID, "boar_spawn_egg"));
        registry.register(new SpawnEggItem(auroch, 0xffc0c0, 0xffc0ff,
                new Item.Properties().tab(ModItemGroup.AGES)).setRegistryName(Reference.MODID, "auroch_spawn_egg"));
        registry.register(new SpawnEggItem(fowl, 0xd666c0, 0x33c066,
                new Item.Properties().tab(ModItemGroup.AGES)).setRegistryName(Reference.MODID, "fowl_spawn_egg"));
        registry.register(new SpawnEggItem(mouflon, 0xec6699, 0xccc096,
                new Item.Properties().tab(ModItemGroup.AGES)).setRegistryName(Reference.MODID, "mouflon_spawn_egg"));
        registry.register(new SpawnEggItem(mammoth, 0xecff99, 0xc330a6,
                new Item.Properties().tab(ModItemGroup.AGES)).setRegistryName(Reference.MODID, "mammoth_spawn_egg"));
        registry.register(new SpawnEggItem(saber_tooth_tiger, 0x333f99, 0xc3fea6,
                new Item.Properties().tab(ModItemGroup.AGES)).setRegistryName(Reference.MODID, "saber_tooth_tiger_spawn_egg"));
        registry.register(new SpawnEggItem(woolly_rhino, 0xec6512, 0xc33905,
                new Item.Properties().tab(ModItemGroup.AGES)).setRegistryName(Reference.MODID, "woolly_rhino_spawn_egg"));
        registry.register(new SpawnEggItem(terror_bird, 0x925512, 0x225515,
                new Item.Properties().tab(ModItemGroup.AGES)).setRegistryName(Reference.MODID, "terror_bird_spawn_egg"));
        registry.register(new SpawnEggItem(coelacanth, 0xfa9987, 0x0977af,
                new Item.Properties().tab(ModItemGroup.AGES)).setRegistryName(Reference.MODID, "coelacanth_spawn_egg"));
    }*/
}
