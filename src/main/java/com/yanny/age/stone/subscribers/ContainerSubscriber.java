package com.yanny.age.stone.subscribers;

import com.yanny.age.stone.blocks.FeederContainer;
import com.yanny.age.stone.blocks.FishingNetContainer;
import com.yanny.age.stone.blocks.MillstoneContainer;
import com.yanny.age.stone.blocks.StoneChestContainer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegisterEvent;

import javax.annotation.Nonnull;

import static com.yanny.age.stone.Reference.MODID;

//@ObjectHolder(MODID)
//@Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ContainerSubscriber {
    public static final MenuType<StoneChestContainer> stone_chest = IForgeMenuType.create((id, inv, data)->{
        return new StoneChestContainer(id,inv,data);
    });
    public static final MenuType<FeederContainer> feeder = IForgeMenuType.create((id, inv, data)->{
        return new FeederContainer(id,inv,data);
    });
    public static final MenuType<MillstoneContainer> millstone = IForgeMenuType.create((id, inv, data)->{
        return new MillstoneContainer(id,inv,data);
    });
    public static final MenuType<FishingNetContainer> fishing_net = IForgeMenuType.create((id, inv, data)->{
        return new FishingNetContainer(id,inv,data);
    });

//    @SubscribeEvent
    public static void registerContainer(@Nonnull RegisterEvent event) {
        /*IForgeRegistry<MenuType<?>> registry = event.getRegistry();
        registry.register(stone_chest.setRegistryName(MODID, "stone_chest"));
        registry.register(feeder.setRegistryName(MODID, "feeder"));
        registry.register(millstone.setRegistryName(MODID, "millstone"));
        registry.register(fishing_net.setRegistryName(MODID, "fishing_net"));*/
        event.register(ForgeRegistries.Keys.MENU_TYPES,
                helper -> {
                    helper.register(new ResourceLocation(MODID, "stone_chest"), stone_chest);
                    helper.register(new ResourceLocation(MODID,"feeder"), feeder);
                    helper.register(new ResourceLocation(MODID, "millstone"), millstone);
                    helper.register(new ResourceLocation(MODID, "fishing_net"), fishing_net);
                }
        );
    }
}