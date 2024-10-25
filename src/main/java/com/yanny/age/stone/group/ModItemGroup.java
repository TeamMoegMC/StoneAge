package com.yanny.age.stone.group;

import com.yanny.age.stone.Reference;
import com.yanny.age.stone.subscribers.ToolSubscriber;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

import javax.annotation.Nonnull;

public class ModItemGroup extends ItemGroup {
    public static final ItemGroup AGES = new ModItemGroup();

    ModItemGroup() {
        super(Reference.MODID);
    }

    @Override
    @Nonnull
    public ItemStack makeIcon() {
        return new ItemStack(ToolSubscriber.flint_knife);
    }
}
