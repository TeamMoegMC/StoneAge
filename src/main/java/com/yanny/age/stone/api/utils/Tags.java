package com.yanny.age.stone.api.utils;

import com.yanny.age.stone.Reference;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public class Tags {
    public static class Blocks {

    }

    public static class Items {
        public static final TagKey<Item> AXES = tag(new ResourceLocation(Reference.MODID, "axes"));
        public static final TagKey<Item> HAMMERS = tag(new ResourceLocation(Reference.MODID, "hammers"));
        public static final TagKey<Item> KNIVES = tag(new ResourceLocation("stone", "knives"));
        public static final TagKey<Item> FISHING_NET_MESHES = tag(new ResourceLocation("stone", "fishing_net_meshes"));
        public static final TagKey<Item> BONES = tag(new ResourceLocation("stone", "bones"));

        private static TagKey<Item> tag(ResourceLocation resourceLocation) {;
            return TagKey.create(Registries.ITEM, resourceLocation);
        }
    }
}
