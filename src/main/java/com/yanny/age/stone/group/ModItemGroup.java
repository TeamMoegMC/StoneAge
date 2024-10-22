package com.yanny.age.stone.group;

import com.yanny.age.stone.Reference;
import com.yanny.age.stone.subscribers.ToolSubscriber;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

import javax.annotation.Nonnull;

public class ModItemGroup extends CreativeModeTab {
    public static final CreativeModeTab AGES = new ModItemGroup();

    ModItemGroup() {
        super(Reference.MODID);
    }

    @Override
    @Nonnull
    public ItemStack makeIcon() {
        return new ItemStack(ToolSubscriber.flint_knife);
    }
}
