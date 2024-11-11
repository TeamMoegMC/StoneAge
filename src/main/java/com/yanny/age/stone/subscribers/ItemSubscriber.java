package com.yanny.age.stone.subscribers;

import com.yanny.age.stone.Reference;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.RegisterEvent;

import javax.annotation.Nonnull;

import static com.yanny.age.stone.Reference.MODID;

@SuppressWarnings({"unused", "WeakerAccess"})
//@Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ItemSubscriber {
    public static Item raw_hide;
    public static Item antler;
    public static Item unlit_torch;
    public static Item bone_arrow_head;
    public static Item dried_grass;
    public static Item leather_strip;
    public static Item fish_bone;
    public static Item flour;
    public static Item raw_bread;
    public static Item mammoth_tusk;
    public static Item rhino_tusk;
    public static Item saber_teeth;
    public static Item grass_mesh;
    public static Item cobweb_mesh;
    public static Item grass_lead;
    public static Item flint_knife;

    public static BlockItem flint_workbench;
    public static BlockItem drying_rack;
    public static BlockItem tanning_rack;
    public static BlockItem stone_chest;
    public static BlockItem tree_stump;
    public static BlockItem dried_grass_bed;
    public static BlockItem feeder;
    public static BlockItem millstone;
    public static BlockItem fishing_net;
    public static BlockItem thatch_block;

    public static Item deer_spawn_egg;
    public static Item boar_spawn_egg;
    public static Item auroch_spawn_egg;
    public static Item fowl_spawn_egg;
    public static Item mouflon_spawn_egg;
    public static Item mammoth_spawn_egg;
    public static Item saber_tooth_tiger_spawn_egg;
    public static Item woolly_rhino_spawn_egg;
    public static Item terror_bird_spawn_egg;
    public static Item coelacanth_spawn_egg;

//    public static  Item stone_hammer;
//    public static  Item stone_axe_head;
    static {
        raw_hide = new Item(new Item.Properties());
        antler = new Item(new Item.Properties());
        unlit_torch = new Item(new Item.Properties());
        bone_arrow_head = new Item(new Item.Properties());
        dried_grass = new Item(new Item.Properties());
        leather_strip = new Item(new Item.Properties());
        fish_bone = new Item(new Item.Properties());
        flour = new Item(new Item.Properties());
        raw_bread = new Item(new Item.Properties());
        mammoth_tusk = new Item(new Item.Properties());
        rhino_tusk = new Item(new Item.Properties());
        saber_teeth = new Item(new Item.Properties());
        grass_mesh = new Item(new Item.Properties());
        cobweb_mesh = new Item(new Item.Properties());
        grass_lead = new Item(new Item.Properties());

        Item.Properties combatProperties = new Item.Properties().stacksTo(1);
        flint_knife = new SwordItem(Tiers.WOOD, 0, -1.0f, combatProperties);

        flint_workbench = new BlockItem(BlockSubscriber.flint_workbench, new Item.Properties());
        drying_rack= new BlockItem(BlockSubscriber.drying_rack, new Item.Properties());
        tanning_rack= new BlockItem(BlockSubscriber.tanning_rack, new Item.Properties());
        stone_chest= new BlockItem(BlockSubscriber.stone_chest, new Item.Properties());
        tree_stump= new BlockItem(BlockSubscriber.tree_stump, new Item.Properties());
        dried_grass_bed= new BlockItem(BlockSubscriber.dried_grass_bed, new Item.Properties());
        feeder= new BlockItem(BlockSubscriber.feeder, new Item.Properties());
        millstone= new BlockItem(BlockSubscriber.millstone, new Item.Properties());
        fishing_net= new BlockItem(BlockSubscriber.fishing_net, new Item.Properties());
        thatch_block= new BlockItem(BlockSubscriber.thatch_block, new Item.Properties());

        deer_spawn_egg = new SpawnEggItem(EntitySubscriber.deer, 0x00ffff, 0xff00ff,new Item.Properties());
        boar_spawn_egg = new SpawnEggItem(EntitySubscriber.boar, 0xc0c0ff, 0xff00ff,new Item.Properties());
        auroch_spawn_egg = new SpawnEggItem(EntitySubscriber.auroch, 0xffc0c0, 0xffc0ff,new Item.Properties());
        fowl_spawn_egg = new SpawnEggItem(EntitySubscriber.fowl, 0xd666c0, 0x33c066,new Item.Properties());
        mouflon_spawn_egg = new SpawnEggItem(EntitySubscriber.mouflon, 0xec6699, 0xccc09,new Item.Properties());
        mammoth_spawn_egg = new SpawnEggItem(EntitySubscriber.mammoth, 0xecff99, 0xc330a6,new Item.Properties());
        saber_tooth_tiger_spawn_egg = new SpawnEggItem(EntitySubscriber.saber_tooth_tiger, 0x333f99, 0xc3fea6,new Item.Properties());
        woolly_rhino_spawn_egg = new SpawnEggItem(EntitySubscriber.woolly_rhino, 0xec6512, 0xc33905,new Item.Properties());
        terror_bird_spawn_egg = new SpawnEggItem(EntitySubscriber.terror_bird, 0x925512, 0x225515,new Item.Properties());
        coelacanth_spawn_egg = new SpawnEggItem(EntitySubscriber.coelacanth, 0xfa9987, 0x0977aff,new Item.Properties());
    }
//    @SubscribeEvent
    public static void registerItems(RegisterEvent event) {
      /*  IForgeRegistry<Item> registry = event.getRegistry();
        registry.register(new Item(new Item.Properties().tab(ModItemGroup.AGES).stacksTo(64)).setRegistryName(MODID, "raw_hide"));
        registry.register(new Item(new Item.Properties().tab(ModItemGroup.AGES).stacksTo(64)).setRegistryName(MODID, "antler"));
        registry.register(new Item(new Item.Properties().tab(ModItemGroup.AGES).stacksTo(64)).setRegistryName(MODID, "unlit_torch"));
        registry.register(new Item(new Item.Properties().tab(ModItemGroup.AGES).stacksTo(64)).setRegistryName(MODID, "bone_arrow_head"));
        registry.register(new Item(new Item.Properties().tab(ModItemGroup.AGES).stacksTo(64)).setRegistryName(MODID, "dried_grass"));
        registry.register(new Item(new Item.Properties().tab(ModItemGroup.AGES).stacksTo(64)).setRegistryName(MODID, "leather_strip"));
        registry.register(new Item(new Item.Properties().tab(ModItemGroup.AGES).stacksTo(64)).setRegistryName(MODID, "fish_bone"));
        registry.register(new Item(new Item.Properties().tab(ModItemGroup.AGES).stacksTo(64)).setRegistryName(MODID, "flour"));
        registry.register(new Item(new Item.Properties().tab(ModItemGroup.AGES).stacksTo(64)).setRegistryName(MODID, "raw_bread"));
        registry.register(new Item(new Item.Properties().tab(ModItemGroup.AGES).stacksTo(64)).setRegistryName(MODID, "mammoth_tusk"));
        registry.register(new Item(new Item.Properties().tab(ModItemGroup.AGES).stacksTo(64)).setRegistryName(MODID, "rhino_tusk"));
        registry.register(new Item(new Item.Properties().tab(ModItemGroup.AGES).stacksTo(64)).setRegistryName(MODID, "saber_teeth"));
        registry.register(new MeshItem(new Item.Properties().tab(ModItemGroup.AGES).durability(15)).setRegistryName(MODID, "grass_mesh"));
        registry.register(new MeshItem(new Item.Properties().tab(ModItemGroup.AGES).durability(30)).setRegistryName(MODID, "cobweb_mesh"));
        registry.register(new Item(new Item.Properties().tab(ModItemGroup.AGES).stacksTo(64)).setRegistryName(MODID, "grass_lead"));
        */
        /*raw_hide = new Item(new Item.Properties());
        antler = new Item(new Item.Properties());
        unlit_torch = new Item(new Item.Properties());
        bone_arrow_head = new Item(new Item.Properties());
        dried_grass = new Item(new Item.Properties());
        leather_strip = new Item(new Item.Properties());
        fish_bone = new Item(new Item.Properties());
        flour = new Item(new Item.Properties());
        raw_bread = new Item(new Item.Properties());
        mammoth_tusk = new Item(new Item.Properties());
        rhino_tusk = new Item(new Item.Properties());
        saber_teeth = new Item(new Item.Properties());
        backpack = new Item(new Item.Properties());
        grass_mesh = new Item(new Item.Properties());
        cobweb_mesh = new Item(new Item.Properties());
        grass_lead = new Item(new Item.Properties());

        flint_knife = new Item(new Item.Properties());
        stone_hammer = new Item(new Item.Properties());
        stone_axe_head = new Item(new Item.Properties());*/
        event.register(ForgeRegistries.Keys.ITEMS,
                helper -> {
                    helper.register(new ResourceLocation(MODID, "raw_hide"), raw_hide);
                    helper.register(new ResourceLocation(MODID, "antler"), antler);
                    helper.register(new ResourceLocation(MODID, "unlit_torch"), unlit_torch);
                    helper.register(new ResourceLocation(MODID, "bone_arrow_head"), bone_arrow_head);
                    helper.register(new ResourceLocation(MODID, "dried_grass"), dried_grass);
                    helper.register(new ResourceLocation(MODID, "leather_strip"), leather_strip);
                    helper.register(new ResourceLocation(MODID, "fish_bone"), fish_bone);
                    helper.register(new ResourceLocation(MODID, "flour"), flour);
                    helper.register(new ResourceLocation(MODID, "raw_bread"), raw_bread);
                    helper.register(new ResourceLocation(MODID, "mammoth_tusk"), mammoth_tusk);
                    helper.register(new ResourceLocation(MODID, "rhino_tusk"), rhino_tusk);
                    helper.register(new ResourceLocation(MODID, "saber_teeth"), saber_teeth);
                    helper.register(new ResourceLocation(MODID, "grass_mesh"), grass_mesh);
                    helper.register(new ResourceLocation(MODID, "cobweb_mesh"), cobweb_mesh);
                    helper.register(new ResourceLocation(MODID, "grass_lead"), grass_lead);

                    helper.register(new ResourceLocation(Reference.MODID, "flint_knife"),flint_knife);

                    //spawn egg
                    helper.register(new ResourceLocation(Reference.MODID, "deer_spawn_egg"), deer_spawn_egg);
                    helper.register(new ResourceLocation(Reference.MODID, "boar_spawn_egg"), boar_spawn_egg);
                    helper.register(new ResourceLocation(Reference.MODID, "auroch_spawn_egg"), auroch_spawn_egg);
                    helper.register(new ResourceLocation(Reference.MODID, "fowl_spawn_egg"), fowl_spawn_egg);
                    helper.register(new ResourceLocation(Reference.MODID, "mouflon_spawn_egg"), mouflon_spawn_egg);
                    helper.register(new ResourceLocation(Reference.MODID, "mammoth_spawn_egg"), mammoth_spawn_egg);
                    helper.register(new ResourceLocation(Reference.MODID, "saber_tooth_tiger_spawn_egg"), saber_tooth_tiger_spawn_egg);
                    helper.register(new ResourceLocation(Reference.MODID, "woolly_rhino_spawn_egg"), woolly_rhino_spawn_egg);
                    helper.register(new ResourceLocation(Reference.MODID, "terror_bird_spawn_egg"), terror_bird_spawn_egg);
                    helper.register(new ResourceLocation(Reference.MODID, "coelacanth_spawn_egg"), coelacanth_spawn_egg);

                    helper.register(new ResourceLocation(MODID, "flint_workbench"),flint_workbench );
                    helper.register(new ResourceLocation(MODID, "drying_rack"), drying_rack);
                    helper.register(new ResourceLocation(MODID, "tanning_rack"), tanning_rack);
                    helper.register(new ResourceLocation(MODID, "stone_chest"), stone_chest);
                    helper.register(new ResourceLocation(MODID, "tree_stump"), tree_stump);
                    helper.register(new ResourceLocation(MODID, "dried_grass_bed"), dried_grass_bed);
                    helper.register(new ResourceLocation(MODID, "feeder"), feeder);
                    helper.register(new ResourceLocation(MODID, "millstone"), millstone);
                    helper.register(new ResourceLocation(MODID, "fishing_net"), fishing_net);
                    helper.register(new ResourceLocation(MODID, "thatch_block"), thatch_block);
                });
    }
}
