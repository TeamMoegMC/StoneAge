package com.yanny.age.stone.api.utils;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.server.players.PlayerList;
import net.minecraft.resources.ResourceKey;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.storage.LevelData;
import net.minecraftforge.common.ForgeHooks;

import javax.annotation.Nonnull;

import net.minecraft.network.protocol.game.ClientboundChangeDifficultyPacket;
import net.minecraft.network.protocol.game.ClientboundLevelEventPacket;
import net.minecraft.network.protocol.game.ClientboundPlayerAbilitiesPacket;
import net.minecraft.network.protocol.game.ClientboundRespawnPacket;
import net.minecraft.network.protocol.game.ClientboundUpdateMobEffectPacket;

public class GeneralUtils {

    private static final int HOURS = 20 * 60 * 60;
    private static final int MINUTES = 20 * 60;
    private static final int SECONDS = 20;

    @Nonnull
    public static String tickToTime(int burnTime) {
        int hours = burnTime / HOURS;
        burnTime = burnTime % HOURS;
        int minutes = burnTime / MINUTES;
        burnTime = burnTime % MINUTES;
        int seconds = burnTime / SECONDS;

        return (hours > 0 ? hours + "h:":"") + ((hours > 0 || minutes > 0) ? minutes + "m:" : "") + (seconds + "s");
    }

    /*public static void changeDim(ServerPlayer player, double x, double y, double z, ResourceKey<Level> type) {
        if (!ForgeHooks.onTravelToDimension(player, type)) {
            return;
        }

        ResourceKey<Level> dimensiontype = player.getRespawnDimension();
        ServerLevel srcWorld = player.server.getLevel(dimensiontype);
        //player.dimension = type; TODO ??? 1.16 ???
        ServerLevel destWorld = player.server.getLevel(type);
        LevelData worldinfo = player.level.getLevelData();
        player.connection.send(new ClientboundRespawnPacket(srcWorld.dimensionType(), type, 20523245, player.gameMode.getGameModeForPlayer(), player.gameMode.getGameModeForPlayer(), false, false, false)); //TODO 1.16 ???
        player.connection.send(new ClientboundChangeDifficultyPacket(worldinfo.getDifficulty(), worldinfo.isDifficultyLocked()));
        PlayerList playerlist = player.server.getPlayerList();
        playerlist.sendPlayerPermissionLevel(player);
        srcWorld.removeEntity(player, true);
        player.revive();
        float f = player.xRot;
        float f1 = player.yRot;

        player.moveTo(x, y, z, f1, f);
        player.setLevel(destWorld);
        destWorld.addNewPlayer(player); //TODO 1.16 ????
        player.connection.teleport(x, y, z, f1, f);
        player.gameMode.setLevel(destWorld);
        player.connection.send(new ClientboundPlayerAbilitiesPacket(player.abilities));
        playerlist.sendLevelInfo(player, destWorld);
        playerlist.sendAllPlayerInfo(player);

        for(MobEffectInstance effectinstance : player.getActiveEffects()) {
            player.connection.send(new ClientboundUpdateMobEffectPacket(player.getId(), effectinstance));
        }

        player.connection.send(new ClientboundLevelEventPacket(1032, BlockPos.ZERO, 0, false));
        BasicEventHooks.firePlayerChangedDimensionEvent(player, dimensiontype, type);
    }*/
}
