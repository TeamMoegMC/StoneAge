package com.yanny.age.stone.api.datagen;

import com.yanny.age.stone.api.utils.Tags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.world.item.Items;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.lwjgl.system.NonnullDefault;

import javax.annotation.Nullable;

@NonnullDefault
public class ItemTagGenerator/* extends ItemTagsProvider*/ {

   /* public ItemTagGenerator(DataGenerator dataGenerator, BlockTagsProvider blockTagProvider, String modId, @Nullable ExistingFileHelper existingFileHelper) {
        super(dataGenerator, blockTagProvider, modId, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        tag(Tags.Items.AXES).add(Items.WOODEN_AXE).add(Items.STONE_AXE).add(Items.IRON_AXE).add(Items.GOLDEN_AXE).add(Items.DIAMOND_AXE);
        tag(Tags.Items.BONES).add(Items.BONE);
        tag(Tags.Items.FISHING_NET_MESHES);
        tag(Tags.Items.HAMMERS);
        tag(Tags.Items.KNIVES);
    }*/
}
