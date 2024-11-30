package com.yanny.age.stone.subscribers;

import com.yanny.age.stone.Reference;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegisterEvent;

import javax.annotation.Nonnull;

import static com.yanny.age.stone.Reference.MODID;

//@ObjectHolder(Reference.MODID)
//@Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class SoundSubscriber {
    public static final SoundEvent mammoth_ambient = SoundEvent.createVariableRangeEvent(new ResourceLocation(Reference.MODID, "mammoth_ambient"));
    public static final SoundEvent mammoth_hit = SoundEvent.createVariableRangeEvent(new ResourceLocation(Reference.MODID, "mammoth_hit"));
    public static final SoundEvent mammoth_death = SoundEvent.createVariableRangeEvent(new ResourceLocation(Reference.MODID, "mammoth_death"));
    public static final SoundEvent saber_tooth_tiger_ambient = SoundEvent.createVariableRangeEvent(new ResourceLocation(Reference.MODID, "saber_tooth_tiger_hit"));
    public static final SoundEvent saber_tooth_tiger_hit = SoundEvent.createVariableRangeEvent(new ResourceLocation(Reference.MODID, "saber_tooth_tiger_hit"));
    public static final SoundEvent saber_tooth_tiger_death = SoundEvent.createVariableRangeEvent(new ResourceLocation(Reference.MODID, "saber_tooth_tiger_death"));
    public static final SoundEvent woolly_rhino_ambient = SoundEvent.createVariableRangeEvent(new ResourceLocation(Reference.MODID, "woolly_rhino_ambient"));
    public static final SoundEvent woolly_rhino_hit = SoundEvent.createVariableRangeEvent(new ResourceLocation(Reference.MODID, "woolly_rhino_hit"));
    public static final SoundEvent woolly_rhino_death = SoundEvent.createVariableRangeEvent(new ResourceLocation(Reference.MODID, "woolly_rhino_death"));

//    @SubscribeEvent
    public static void registerSounds(@Nonnull RegisterEvent event) {
        /*IForgeRegistry<SoundEvent> registry = event.getRegistry();
        registry.register(new SoundEvent(new ResourceLocation(Reference.MODID, "mammoth_ambient")).setRegistryName(MODID, "mammoth_ambient"));
        registry.register(new SoundEvent(new ResourceLocation(Reference.MODID, "mammoth_hit")).setRegistryName(MODID, "mammoth_hit"));
        registry.register(new SoundEvent(new ResourceLocation(Reference.MODID, "mammoth_death")).setRegistryName(MODID, "mammoth_death"));
        registry.register(new SoundEvent(new ResourceLocation(Reference.MODID, "saber_tooth_tiger_ambient")).setRegistryName(MODID, "saber_tooth_tiger_ambient"));
        registry.register(new SoundEvent(new ResourceLocation(Reference.MODID, "saber_tooth_tiger_hit")).setRegistryName(MODID, "saber_tooth_tiger_hit"));
        registry.register(new SoundEvent(new ResourceLocation(Reference.MODID, "saber_tooth_tiger_death")).setRegistryName(MODID, "saber_tooth_tiger_death"));
        registry.register(new SoundEvent(new ResourceLocation(Reference.MODID, "woolly_rhino_ambient")).setRegistryName(MODID, "woolly_rhino_ambient"));
        registry.register(new SoundEvent(new ResourceLocation(Reference.MODID, "woolly_rhino_hit")).setRegistryName(MODID, "woolly_rhino_hit"));
        registry.register(new SoundEvent(new ResourceLocation(Reference.MODID, "woolly_rhino_death")).setRegistryName(MODID, "woolly_rhino_death"));*/
        event.register(ForgeRegistries.Keys.SOUND_EVENTS,
                helper -> {
                    helper.register(new ResourceLocation(MODID, "mammoth_ambient"), mammoth_ambient);
                    helper.register(new ResourceLocation(MODID, "mammoth_hit"), mammoth_hit);
                    helper.register(new ResourceLocation(MODID, "mammoth_death"), mammoth_death);
                    helper.register(new ResourceLocation(MODID, "saber_tooth_tiger_ambient"), saber_tooth_tiger_ambient);
                    helper.register(new ResourceLocation(MODID, "saber_tooth_tiger_hit"), saber_tooth_tiger_hit);
                    helper.register(new ResourceLocation(MODID, "saber_tooth_tiger_death"), saber_tooth_tiger_death);
                    helper.register(new ResourceLocation(MODID, "woolly_rhino_ambient"), woolly_rhino_ambient);
                    helper.register(new ResourceLocation(MODID, "woolly_rhino_hit"), woolly_rhino_hit);
                    helper.register(new ResourceLocation(MODID, "woolly_rhino_death"), woolly_rhino_death);
                }
        );
    }
}
