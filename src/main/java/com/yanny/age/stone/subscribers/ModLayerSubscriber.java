package com.yanny.age.stone.subscribers;

import com.yanny.age.stone.Reference;
import com.yanny.age.stone.client.models.AurochModel;
import com.yanny.age.stone.client.models.MillstoneModel;
import com.yanny.age.stone.client.models.StoneChestModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static com.yanny.age.stone.Reference.MODID;

@Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModLayerSubscriber {

    @SubscribeEvent
    public static void registerLayer(EntityRenderersEvent.RegisterLayerDefinitions event){
        event.registerLayerDefinition(AurochModel.AUROCH_LAYER, AurochModel::createBodyLayer);
        event.registerLayerDefinition(MillstoneModel.MILLSTONE_LAYER, MillstoneModel::createBodyLayer);
        event.registerLayerDefinition(StoneChestModel.STONECHEST_LAYER, StoneChestModel::createBodyLayer);
    }
}
