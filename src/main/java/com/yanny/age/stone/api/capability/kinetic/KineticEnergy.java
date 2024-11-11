package com.yanny.age.stone.api.capability.kinetic;

import net.minecraft.nbt.CompoundTag;

public class KineticEnergy implements IKineticEnergy {
    private int currentEnergy;

    public KineticEnergy() {
        currentEnergy = 0;
    }

    @Override
    public int getAvailableEnergy() {
        return currentEnergy;
    }

    @Override
    public void setAvailableEnergy(int energy) {
        currentEnergy = energy;
    }

    @Override
    public String toString() {
        return "[energy: " + currentEnergy + "]";
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag tag = new CompoundTag();
        tag.putInt("energy", currentEnergy);
        return tag;
    }

    @Override
    public void deserializeNBT(CompoundTag tag) {
        currentEnergy = tag.getInt("energy");
    }
}
