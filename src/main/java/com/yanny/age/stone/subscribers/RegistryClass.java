package com.yanny.age.stone.subscribers;

import com.yanny.age.stone.Reference;
import net.minecraft.world.entity.Entity;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.RegisterEvent;

@Mod.EventBusSubscriber(modid = Reference.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class RegistryClass {
    public RegistryClass() {
    }

    @SubscribeEvent
    public static void registerBlocks(RegisterEvent event) {
        BlockSubscriber.registerBlocks(event);
    }

    @SubscribeEvent
    public static void registerItems(RegisterEvent event) {
        ItemSubscriber.registerItems(event);
        FoodSubscriber.registerFoodItems(event);
    }

    @SubscribeEvent
    public static void registerContainers(RegisterEvent event) {
        ContainerSubscriber.registerContainer(event);
    }

    @SubscribeEvent
    public static void registerTileEntity(RegisterEvent event) {
        TileEntitySubscriber.registerTileEntity(event);
    }

    @SubscribeEvent
    public static void registerRecipeType(RegisterEvent event) {
        RecipeSubscriber.registerRecipeType(event);
    }

    @SubscribeEvent
    public static void registerEntity(RegisterEvent event) {
        EntitySubscriber.registerEntities(event);
    }
}
