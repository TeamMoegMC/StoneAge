package com.yanny.age.stone.subscribers;

import com.yanny.age.stone.group.ModItemGroup;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;

import javax.annotation.Nonnull;

import static com.yanny.age.stone.Reference.MODID;

@SuppressWarnings("unused")
@Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class FoodSubscriber {
    public static final Item fat = null;
    public static final Item cooked_fat = null;
    public static final Item venison = null;
    public static final Item cooked_venison = null;
    public static final Item fowl_meat = null;
    public static final Item cooked_fowl_meat = null;
    public static final Item auroch_meat = null;
    public static final Item cooked_auroch_meat = null;
    public static final Item mouflon_meat = null;
    public static final Item cooked_mouflon_meat = null;
    public static final Item boar_meat = null;
    public static final Item cooked_boar_meat = null;
    public static final Item mammoth_meat = null;
    public static final Item cooked_mammoth_meat = null;
    public static final Item rhino_meat = null;
    public static final Item cooked_rhino_meat = null;
    public static final Item tiger_meat = null;
    public static final Item cooked_tiger_meat = null;

    private static final FoodProperties FAT = (new FoodProperties.Builder()).nutrition(1).saturationMod(0.1f).meat().build();
    private static final FoodProperties COOKED_FAT = (new FoodProperties.Builder()).nutrition(2).saturationMod(0.2f).meat().build();
    private static final FoodProperties VENISON = (new FoodProperties.Builder()).nutrition(1).saturationMod(0.15f).meat().build();
    private static final FoodProperties COOKED_VENISON = (new FoodProperties.Builder()).nutrition(3).saturationMod(0.3f).meat().build();
    private static final FoodProperties FOWL_MEAT = (new FoodProperties.Builder()).nutrition(2).saturationMod(0.2f).meat().build();
    private static final FoodProperties COOKED_FOWL_MEAT = (new FoodProperties.Builder()).nutrition(4).saturationMod(0.4f).meat().build();
    private static final FoodProperties AUROCH_MEAT = (new FoodProperties.Builder()).nutrition(2).saturationMod(0.25f).meat().build();
    private static final FoodProperties COOKED_AUROCH_MEAT = (new FoodProperties.Builder()).nutrition(5).saturationMod(0.5f).meat().build();
    private static final FoodProperties MOUFLON_MEAT = (new FoodProperties.Builder()).nutrition(3).saturationMod(0.3f).meat().build();
    private static final FoodProperties COOKED_MOUFLON_MEAT = (new FoodProperties.Builder()).nutrition(6).saturationMod(0.6f).meat().build();
    private static final FoodProperties BOAR_MEAT = (new FoodProperties.Builder()).nutrition(3).saturationMod(0.35f).meat().build();
    private static final FoodProperties COOKED_BOAR_MEAT = (new FoodProperties.Builder()).nutrition(7).saturationMod(0.7f).meat().build();
    private static final FoodProperties MAMMOTH_MEAT = (new FoodProperties.Builder()).nutrition(4).saturationMod(0.4f).meat().build();
    private static final FoodProperties COOKED_MAMMOTH_MEAT = (new FoodProperties.Builder()).nutrition(8).saturationMod(0.8f).meat().build();
    private static final FoodProperties RHINO_MEAT = (new FoodProperties.Builder()).nutrition(4).saturationMod(0.45f).meat().build();
    private static final FoodProperties COOKED_RHINO_MEAT = (new FoodProperties.Builder()).nutrition(9).saturationMod(0.9f).meat().build();
    private static final FoodProperties TIGER_MEAT = (new FoodProperties.Builder()).nutrition(5).saturationMod(0.5f).meat().build();
    private static final FoodProperties COOKED_TIGER_MEAT = (new FoodProperties.Builder()).nutrition(10).saturationMod(1.0f).meat().build();

    @SubscribeEvent
    public static void registerItems(@Nonnull RegistryEvent.Register<Item> event) {
        IForgeRegistry<Item> registry = event.getRegistry();
        registry.register(new Item(new Item.Properties().tab(ModItemGroup.AGES).stacksTo(64).food(FAT)).setRegistryName(MODID, "fat"));
        registry.register(new Item(new Item.Properties().tab(ModItemGroup.AGES).stacksTo(64).food(COOKED_FAT)).setRegistryName(MODID, "cooked_fat"));
        registry.register(new Item(new Item.Properties().tab(ModItemGroup.AGES).stacksTo(64).food(VENISON)).setRegistryName(MODID, "venison"));
        registry.register(new Item(new Item.Properties().tab(ModItemGroup.AGES).stacksTo(64).food(COOKED_VENISON)).setRegistryName(MODID, "cooked_venison"));
        registry.register(new Item(new Item.Properties().tab(ModItemGroup.AGES).stacksTo(64).food(FOWL_MEAT)).setRegistryName(MODID, "fowl_meat"));
        registry.register(new Item(new Item.Properties().tab(ModItemGroup.AGES).stacksTo(64).food(COOKED_FOWL_MEAT)).setRegistryName(MODID, "cooked_fowl_meat"));
        registry.register(new Item(new Item.Properties().tab(ModItemGroup.AGES).stacksTo(64).food(AUROCH_MEAT)).setRegistryName(MODID, "auroch_meat"));
        registry.register(new Item(new Item.Properties().tab(ModItemGroup.AGES).stacksTo(64).food(COOKED_AUROCH_MEAT)).setRegistryName(MODID, "cooked_auroch_meat"));
        registry.register(new Item(new Item.Properties().tab(ModItemGroup.AGES).stacksTo(64).food(MOUFLON_MEAT)).setRegistryName(MODID, "mouflon_meat"));
        registry.register(new Item(new Item.Properties().tab(ModItemGroup.AGES).stacksTo(64).food(COOKED_MOUFLON_MEAT)).setRegistryName(MODID, "cooked_mouflon_meat"));
        registry.register(new Item(new Item.Properties().tab(ModItemGroup.AGES).stacksTo(64).food(BOAR_MEAT)).setRegistryName(MODID, "boar_meat"));
        registry.register(new Item(new Item.Properties().tab(ModItemGroup.AGES).stacksTo(64).food(COOKED_BOAR_MEAT)).setRegistryName(MODID, "cooked_boar_meat"));
        registry.register(new Item(new Item.Properties().tab(ModItemGroup.AGES).stacksTo(64).food(MAMMOTH_MEAT)).setRegistryName(MODID, "mammoth_meat"));
        registry.register(new Item(new Item.Properties().tab(ModItemGroup.AGES).stacksTo(64).food(COOKED_MAMMOTH_MEAT)).setRegistryName(MODID, "cooked_mammoth_meat"));
        registry.register(new Item(new Item.Properties().tab(ModItemGroup.AGES).stacksTo(64).food(RHINO_MEAT)).setRegistryName(MODID, "rhino_meat"));
        registry.register(new Item(new Item.Properties().tab(ModItemGroup.AGES).stacksTo(64).food(COOKED_RHINO_MEAT)).setRegistryName(MODID, "cooked_rhino_meat"));
        registry.register(new Item(new Item.Properties().tab(ModItemGroup.AGES).stacksTo(64).food(TIGER_MEAT)).setRegistryName(MODID, "tiger_meat"));
        registry.register(new Item(new Item.Properties().tab(ModItemGroup.AGES).stacksTo(64).food(COOKED_TIGER_MEAT)).setRegistryName(MODID, "cooked_tiger_meat"));
    }
}
