package com.yanny.age.stone.items;

import net.minecraft.world.item.Item;

import javax.annotation.Nonnull;

import net.minecraft.world.item.Item.Properties;

public class MeshItem extends Item {

    public MeshItem(@Nonnull Properties properties) {
        super(properties);
    }

    @Override
    public int getEnchantmentValue() {
        return 1;
    }
}
