package com.yanny.age.stone.subscribers;

import com.yanny.age.stone.Reference;
import com.yanny.age.stone.group.ModItemGroup;
import com.yanny.age.stone.items.HammerItem;
import com.yanny.ages.api.items.AgesAxeItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemTier;
import net.minecraft.item.SwordItem;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.ObjectHolder;

import javax.annotation.Nonnull;

@SuppressWarnings("unused")
@ObjectHolder(Reference.MODID)
@Mod.EventBusSubscriber(modid = Reference.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ToolSubscriber {

    public static final Item flint_knife = null;
    public static final Item stone_hammer = null;
    public static final Item stone_axe_head = null;


    @SubscribeEvent
    public static void registerItems(@Nonnull RegistryEvent.Register<Item> event) {
        IForgeRegistry<Item> registry = event.getRegistry();
        Item.Properties combatProperties = new Item.Properties().maxStackSize(1).group(ModItemGroup.AGES);
        Item.Properties stoneAxeToolProperties = new Item.Properties().maxStackSize(1).group(ModItemGroup.AGES).addToolType(ToolType.AXE, ItemTier.STONE.getHarvestLevel());
        Item.Properties toolProperties = new Item.Properties().maxStackSize(1).group(ModItemGroup.AGES);

        registry.register(new SwordItem(ItemTier.WOOD, 0, -1.0f, combatProperties).setRegistryName(Reference.MODID, "flint_knife"));
        registry.register(new AgesAxeItem(ItemTier.STONE, 7, -3.2f, stoneAxeToolProperties).setRegistryName(Reference.MODID, "stone_axe"));
        registry.register(new HammerItem(ItemTier.STONE, 2, -3.5f, toolProperties).setRegistryName(Reference.MODID, "stone_hammer"));

    }
}

