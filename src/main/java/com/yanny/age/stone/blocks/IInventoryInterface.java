package com.yanny.age.stone.blocks;

import net.minecraft.world.Container;

import javax.annotation.Nonnull;

interface IInventoryInterface {
    @Nonnull
    Container getInventory();
}
