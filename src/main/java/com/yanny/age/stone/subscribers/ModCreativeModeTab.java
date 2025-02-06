package com.yanny.age.stone.subscribers;

import com.yanny.age.stone.Reference;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModCreativeModeTab {

    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TAB =DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Reference.MODID);

    public static final RegistryObject<CreativeModeTab> STONE_AGE = CREATIVE_MODE_TAB.register(Reference.MODID, () -> CreativeModeTab.builder()
            .title(Component.translatable("itemGroup.stone_age"))
            .icon(() -> new ItemStack(ItemSubscriber.flint_knife))
            .displayItems((params, output) -> {
                output.accept(ItemSubscriber.raw_hide);
                output.accept(ItemSubscriber.antler);
                output.accept(ItemSubscriber.unlit_torch);
                output.accept(ItemSubscriber.bone_arrow_head);
                output.accept(ItemSubscriber.dried_grass);
                output.accept(ItemSubscriber.leather_strip);
                output.accept(ItemSubscriber.fish_bone);
                output.accept(ItemSubscriber.flour);
                output.accept(ItemSubscriber.raw_bread);
                output.accept(ItemSubscriber.mammoth_tusk);
                output.accept(ItemSubscriber.rhino_tusk);
                output.accept(ItemSubscriber.saber_teeth);
                output.accept(ItemSubscriber.grass_mesh);
                output.accept(ItemSubscriber.cobweb_mesh);
                output.accept(ItemSubscriber.grass_lead);
                output.accept(ItemSubscriber.flint_knife);

                 output.accept(ItemSubscriber.flint_workbench);
                 output.accept(ItemSubscriber.drying_rack);
                 output.accept(ItemSubscriber.tanning_rack);
                 output.accept(ItemSubscriber.stone_chest);
                 output.accept(ItemSubscriber.tree_stump);
                 output.accept(ItemSubscriber.dried_grass_bed);
                 output.accept(ItemSubscriber.feeder);
                 output.accept(ItemSubscriber.millstone);
                 output.accept(ItemSubscriber.fishing_net);
                 output.accept(ItemSubscriber.thatch_block);

                output.accept(ItemSubscriber.fat);
                output.accept(ItemSubscriber.cooked_fat);
                output.accept(ItemSubscriber.venison);
                output.accept(ItemSubscriber.cooked_venison);
                output.accept(ItemSubscriber.fowl_meat);
                output.accept(ItemSubscriber.cooked_fowl_meat);
                output.accept(ItemSubscriber.auroch_meat);
                output.accept(ItemSubscriber.cooked_auroch_meat);
                output.accept(ItemSubscriber.mouflon_meat);
                output.accept(ItemSubscriber.cooked_mouflon_meat);
                output.accept(ItemSubscriber.boar_meat);
                output.accept(ItemSubscriber.cooked_boar_meat);
                output.accept(ItemSubscriber.mammoth_meat);
                output.accept(ItemSubscriber.cooked_mammoth_meat);
                output.accept(ItemSubscriber.rhino_meat);
                output.accept(ItemSubscriber.cooked_rhino_meat);
                output.accept(ItemSubscriber.tiger_meat);
                output.accept(ItemSubscriber.cooked_tiger_meat);



                 output.accept(ItemSubscriber.deer_spawn_egg);
                 output.accept(ItemSubscriber.boar_spawn_egg);
                 output.accept(ItemSubscriber.auroch_spawn_egg);
                 output.accept(ItemSubscriber.fowl_spawn_egg);
                 output.accept(ItemSubscriber.mouflon_spawn_egg);
                 output.accept(ItemSubscriber.mammoth_spawn_egg);
                 output.accept(ItemSubscriber.saber_tooth_tiger_spawn_egg);
                 output.accept(ItemSubscriber.woolly_rhino_spawn_egg);
                 output.accept(ItemSubscriber.terror_bird_spawn_egg);
                 output.accept(ItemSubscriber.coelacanth_spawn_egg);
            }).build()
    );
}
