package com.yanny.age.stone.subscribers;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.predicates.LootItemConditionType;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegisterEvent;

import javax.annotation.Nonnull;

import static com.yanny.age.stone.Reference.MODID;

//@Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModifierSubscriber {
//    public static LootItemConditionType matchTag = null;

//    @SubscribeEvent
    public static void registerModifierSerializers(@Nonnull final RegisterEvent event) {
        /*IForgeRegistry<GlobalLootModifierSerializer<?>> registry = event.getRegistry();

        registry.register(new LeavesDropModifier.Serializer().setRegistryName(new ResourceLocation(MODID,"leaves_drop")));

        matchTag = Registry.register(Registry.LOOT_CONDITION_TYPE, new ResourceLocation(MODID, "match_tag"), new LootItemConditionType(new MatchTagCondition.Serializer()));

        event.register(ForgeRegistries.Keys.GLOBAL_LOOT_MODIFIER_SERIALIZERS,
                helper -> {
                    helper.register(new ResourceLocation(MODID, "leaves_drop"), leaves_drop);
                }
        );*/
    }
}
