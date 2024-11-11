package com.yanny.age.stone.api.utils;

import com.yanny.age.stone.api.enums.Age;
import net.minecraft.world.entity.player.Player;
import net.minecraft.nbt.CompoundTag;

import static com.yanny.age.stone.Reference.MODID;


public class AgeUtils {
    private static final String PLAYER_AGE_NBT = MODID + "_PLAYER_AGE";

    public static void initPlayerAge(Player playerEntity) {
        CompoundTag nbt = playerEntity.getPersistentData();
        CompoundTag persistent;

        if (!nbt.contains(Player.PERSISTED_NBT_TAG)) {
            nbt.put(Player.PERSISTED_NBT_TAG, (persistent = new CompoundTag()));
        } else {
            persistent = nbt.getCompound(Player.PERSISTED_NBT_TAG);
        }

        if (!persistent.contains(PLAYER_AGE_NBT)) {
            persistent.putInt(PLAYER_AGE_NBT, Age.STONE_AGE.value);
        }
    }

    public static int getPlayerAge(Player playerEntity) {
        CompoundTag nbt = playerEntity.getPersistentData();
        CompoundTag persistent;

        if (!nbt.contains(Player.PERSISTED_NBT_TAG)) {
            nbt.put(Player.PERSISTED_NBT_TAG, (persistent = new CompoundTag()));
        } else {
            persistent = nbt.getCompound(Player.PERSISTED_NBT_TAG);
        }

        if (!persistent.contains(PLAYER_AGE_NBT)) {
            persistent.putInt(PLAYER_AGE_NBT, Age.STONE_AGE.value);
        }

        return persistent.getInt(PLAYER_AGE_NBT);
    }

    public static void setPlayerAge(Player playerEntity, Age age) {
        CompoundTag nbt = playerEntity.getPersistentData();
        CompoundTag persistent;

        if (!nbt.contains(Player.PERSISTED_NBT_TAG)) {
            nbt.put(Player.PERSISTED_NBT_TAG, (persistent = new CompoundTag()));
        } else {
            persistent = nbt.getCompound(Player.PERSISTED_NBT_TAG);
        }

        persistent.putInt(PLAYER_AGE_NBT, age.value);
    }
}
