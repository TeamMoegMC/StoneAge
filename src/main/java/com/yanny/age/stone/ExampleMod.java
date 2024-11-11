package com.yanny.age.stone;

import com.yanny.age.stone.config.ConfigHolder;
import com.yanny.age.stone.proxy.ClientProxy;
import com.yanny.age.stone.proxy.IProxy;
import com.yanny.age.stone.proxy.ServerProxy;
import com.yanny.age.stone.subscribers.ModCreativeModeTab;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(Reference.MODID)
public class ExampleMod {
    public static final IProxy proxy = DistExecutor.safeRunForDist(() -> ClientProxy::new, () -> ServerProxy::new);

    public ExampleMod() {
        final ModLoadingContext modLoadingContext = ModLoadingContext.get();
        modLoadingContext.registerConfig(ModConfig.Type.CLIENT, ConfigHolder.CLIENT_SPEC);
        modLoadingContext.registerConfig(ModConfig.Type.COMMON, ConfigHolder.SERVER_SPEC);

        final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        ModCreativeModeTab.CREATIVE_MODE_TAB.register(modEventBus);
    }
}
