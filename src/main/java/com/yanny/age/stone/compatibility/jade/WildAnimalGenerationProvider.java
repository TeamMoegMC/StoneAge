package com.yanny.age.stone.compatibility.jade;

import com.yanny.age.stone.entities.WildAnimalEntity;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import snownee.jade.api.EntityAccessor;
import snownee.jade.api.IEntityComponentProvider;
import snownee.jade.api.IServerDataProvider;
import snownee.jade.api.ITooltip;
import snownee.jade.api.config.IPluginConfig;

public enum WildAnimalGenerationProvider implements IEntityComponentProvider, IServerDataProvider<EntityAccessor> {
    INSTANCE;

    @Override
    public void appendTooltip(ITooltip tooltip, EntityAccessor accessor, IPluginConfig config) {
        CompoundTag serverData = accessor.getServerData();

        if (serverData.contains("Generation")) {
            int generation = serverData.getInt("Generation");
            tooltip.add(Component.translatable("jade.stoneage.generation", generation));
        }
    }

    @Override
    public void appendServerData(CompoundTag data, EntityAccessor accessor) {
        if (accessor.getEntity() instanceof WildAnimalEntity wildAnimal) {
            data.putInt("Generation", getGeneration(wildAnimal));
        }
    }

    @Override
    public ResourceLocation getUid() {
        return StoneAgeJadePlugin.WILD_ANIMAL_GENERATION;
    }

    private int getGeneration(WildAnimalEntity animal) {
        try {
            java.lang.reflect.Field generationField = WildAnimalEntity.class.getDeclaredField("GENERATION");
            generationField.setAccessible(true);
            net.minecraft.network.syncher.EntityDataAccessor<Integer> dataParameter =
                    (net.minecraft.network.syncher.EntityDataAccessor<Integer>) generationField.get(null);
            return animal.getEntityData().get(dataParameter);
        } catch (Exception e) {
            return 0;
        }
    }
}