package com.yanny.age.stone.api.capability.kinetic;

import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.util.INBTSerializable;

public interface IKineticEnergy extends INBTSerializable<CompoundTag> {
    int getAvailableEnergy();

    void setAvailableEnergy(int energy);
}
