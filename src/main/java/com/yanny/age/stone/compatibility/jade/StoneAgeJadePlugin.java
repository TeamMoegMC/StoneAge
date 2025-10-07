package com.yanny.age.stone.compatibility.jade;

import net.minecraft.resources.ResourceLocation;
import snownee.jade.api.IWailaClientRegistration;
import snownee.jade.api.IWailaCommonRegistration;
import snownee.jade.api.IWailaPlugin;
import snownee.jade.api.WailaPlugin;

@WailaPlugin
public class StoneAgeJadePlugin implements IWailaPlugin {

    public static final ResourceLocation WILD_ANIMAL_GENERATION = new ResourceLocation("stoneage", "wild_animal_generation");

    @Override
    public void register(IWailaCommonRegistration registration) {
        // 注册服务器数据提供者
        registration.registerEntityDataProvider(WildAnimalGenerationProvider.INSTANCE, com.yanny.age.stone.entities.WildAnimalEntity.class);
    }

    @Override
    public void registerClient(IWailaClientRegistration registration) {
        // 注册客户端组件提供者
        registration.registerEntityComponent(WildAnimalGenerationProvider.INSTANCE, com.yanny.age.stone.entities.WildAnimalEntity.class);
    }
}