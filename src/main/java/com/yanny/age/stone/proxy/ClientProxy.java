package com.yanny.age.stone.proxy;

import com.yanny.age.stone.blocks.FeederGui;
import com.yanny.age.stone.blocks.FishingNetGui;
import com.yanny.age.stone.blocks.MillstoneGui;
import com.yanny.age.stone.blocks.StoneChestGui;
import com.yanny.age.stone.subscribers.ContainerSubscriber;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;

public class ClientProxy implements IProxy {

    @Override
    public void init() {
        MenuScreens.register(ContainerSubscriber.stone_chest, StoneChestGui::new);
        MenuScreens.register(ContainerSubscriber.feeder, FeederGui::new);
        MenuScreens.register(ContainerSubscriber.millstone, MillstoneGui::new);
        MenuScreens.register(ContainerSubscriber.fishing_net, FishingNetGui::new);

        /*RenderingRegistry.registerEntityRenderingHandler(EntitySubscriber.deer, new DeerRenderer.RenderFactory());
        RenderingRegistry.registerEntityRenderingHandler(EntitySubscriber.boar, new BoarRenderer.RenderFactory());
        RenderingRegistry.registerEntityRenderingHandler(EntitySubscriber.auroch, new AurochRenderer.RenderFactory());
        RenderingRegistry.registerEntityRenderingHandler(EntitySubscriber.fowl, new FowlRenderer.RenderFactory());
        RenderingRegistry.registerEntityRenderingHandler(EntitySubscriber.mouflon, new MouflonRenderer.RenderFactory());
        RenderingRegistry.registerEntityRenderingHandler(EntitySubscriber.mammoth, new MammothRenderer.RenderFactory());
        RenderingRegistry.registerEntityRenderingHandler(EntitySubscriber.saber_tooth_tiger, new SaberToothTigerRenderer.RenderFactory());
        RenderingRegistry.registerEntityRenderingHandler(EntitySubscriber.woolly_rhino, new WoollyRhinoRenderer.RenderFactory());
        RenderingRegistry.registerEntityRenderingHandler(EntitySubscriber.terror_bird, new TerrorBirdRenderer.RenderFactory());
        RenderingRegistry.registerEntityRenderingHandler(EntitySubscriber.coelacanth, new CoelacanthRenderer.RenderFactory());*/
    }

    @Override
    @Nullable
    public Level getClientWorld() {
        return Minecraft.getInstance().level;
    }

    @Override
    @Nullable
    public Player getClientPlayer() {
        return Minecraft.getInstance().player;
    }
}
