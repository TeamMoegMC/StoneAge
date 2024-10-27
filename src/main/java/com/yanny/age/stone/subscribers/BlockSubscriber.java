package com.yanny.age.stone.subscribers;

import com.yanny.age.stone.blocks.*;
import com.yanny.age.stone.group.ModItemGroup;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.item.BedItem;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;

import javax.annotation.Nonnull;

import static com.yanny.age.stone.Reference.MODID;

@SuppressWarnings("WeakerAccess")
@Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class BlockSubscriber {
    public static final Block flint_workbench = null;
    public static final Block drying_rack = null;
    public static final Block tanning_rack = null;
    public static final Block stone_chest = null;
    public static final Block tree_stump = null;
    public static final Block dried_grass_bed = null;
    public static final Block feeder = null;
    public static final Block millstone = null;
    public static final Block fishing_net = null;
    public static final Block thatch_block = null;
    public static final Block thatch_stairs = null;
    public static final Block thatch_slab = null;

    @SuppressWarnings({"ConstantConditions", "Convert2MethodRef"})
    @SubscribeEvent
    public static void registerBlocks(@Nonnull RegistryEvent.Register<Block> event) {
        Block.Properties thatchProperties = Block.Properties.of(Material.GRASS).strength(1.0f).sound(SoundType.GRASS);

        IForgeRegistry<Block> registry = event.getRegistry();
        registry.register(new FlintWorkbenchBlock().setRegistryName(MODID, "flint_workbench"));
        registry.register(new DryingRackBlock().setRegistryName(MODID, "drying_rack"));
        registry.register(new TanningRackBlock().setRegistryName(MODID, "tanning_rack"));
        registry.register(new StoneChestBlock().setRegistryName(MODID, "stone_chest"));
        registry.register(new TreeStumpBlock().setRegistryName(MODID, "tree_stump"));
        registry.register(new DriedGrassBedBlock().setRegistryName(MODID, "dried_grass_bed"));
        registry.register(new FeederBlock().setRegistryName(MODID, "feeder"));
        registry.register(new MillstoneBlock().setRegistryName(MODID, "millstone"));
        registry.register(new FishingNetBlock().setRegistryName(MODID, "fishing_net"));
        registry.register(new Block(thatchProperties).setRegistryName(MODID, "thatch_block"));
        registry.register(new StairBlock(() -> thatch_block.defaultBlockState(), thatchProperties).setRegistryName(MODID, "thatch_stairs"));
        registry.register(new SlabBlock(thatchProperties).setRegistryName(MODID, "thatch_slab"));
    }

    @SuppressWarnings("ConstantConditions")
    @SubscribeEvent
    public static void registerItems(@Nonnull RegistryEvent.Register<Item> event) {
        IForgeRegistry<Item> registry = event.getRegistry();
        registry.register(new BlockItem(flint_workbench, new Item.Properties().tab(ModItemGroup.AGES))
                .setRegistryName(MODID, "flint_workbench"));
        registry.register(new BlockItem(drying_rack, new Item.Properties().tab(ModItemGroup.AGES))
                .setRegistryName(MODID, "drying_rack"));
        registry.register(new BlockItem(tanning_rack, new Item.Properties().tab(ModItemGroup.AGES))
                .setRegistryName(MODID, "tanning_rack"));
        registry.register(new BlockItem(stone_chest, new Item.Properties().tab(ModItemGroup.AGES))
                .setRegistryName(MODID, "stone_chest"));
        registry.register(new BlockItem(tree_stump, new Item.Properties().tab(ModItemGroup.AGES))
                .setRegistryName(MODID, "tree_stump"));
        registry.register(new BedItem(dried_grass_bed, new Item.Properties().tab(ModItemGroup.AGES))
                .setRegistryName(MODID, "dried_grass_bed"));
        registry.register(new BlockItem(feeder, new Item.Properties().tab(ModItemGroup.AGES))
                .setRegistryName(MODID, "feeder"));
        registry.register(new BlockItem(millstone, new Item.Properties().tab(ModItemGroup.AGES))
                .setRegistryName(MODID, "millstone"));
        registry.register(new BlockItem(fishing_net, new Item.Properties().tab(ModItemGroup.AGES))
                .setRegistryName(MODID, "fishing_net"));
        registry.register(new BlockItem(thatch_block, new Item.Properties().tab(ModItemGroup.AGES))
                .setRegistryName(MODID, "thatch_block"));
        registry.register(new BlockItem(thatch_stairs, new Item.Properties().tab(ModItemGroup.AGES))
                .setRegistryName(MODID, "thatch_stairs"));
        registry.register(new BlockItem(thatch_slab, new Item.Properties().tab(ModItemGroup.AGES))
                .setRegistryName(MODID, "thatch_slab"));
    }
}
