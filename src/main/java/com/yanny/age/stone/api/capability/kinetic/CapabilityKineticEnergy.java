package com.yanny.age.stone.api.capability.kinetic;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.nbt.ListTag;
import net.minecraft.core.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;

public class CapabilityKineticEnergy {
   /* @CapabilityInject(IKineticEnergy.class)
    public static Capability<IKineticEnergy> ENERGY_CAPABILITY = null;

    public static void register()
    {
        CapabilityManager.INSTANCE.register(IKineticEnergy.class, new Capability.IStorage<IKineticEnergy>() {
                @Override
                public Tag writeNBT(Capability<IKineticEnergy> capability, IKineticEnergy instance, Direction side) {
                    ListTag nbtTagList = new ListTag();
                    CompoundTag tag = new CompoundTag();
                    tag.putInt("energy", instance.getAvailableEnergy());
                    return nbtTagList;
                }

                @Override
                public void readNBT(Capability<IKineticEnergy> capability, IKineticEnergy instance, Direction side, Tag nbt) {
                    ListTag tagList = (ListTag) nbt;
                    CompoundTag tag = tagList.getCompound(0);
                    instance.setAvailableEnergy(tag.getInt("energy"));
                }
        }, KineticEnergy::new);
    }*/
}
