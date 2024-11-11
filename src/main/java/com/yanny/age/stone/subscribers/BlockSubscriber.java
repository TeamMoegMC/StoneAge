package com.yanny.age.stone.subscribers;

import com.yanny.age.stone.blocks.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.item.BedItem;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegisterEvent;

import javax.annotation.Nonnull;

import static com.yanny.age.stone.Reference.MODID;

@SuppressWarnings("WeakerAccess")
//@Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class BlockSubscriber {
    public static Block flint_workbench;
    public static Block drying_rack;
    public static Block tanning_rack;
    public static Block stone_chest;
    public static Block tree_stump;
    public static Block dried_grass_bed;
    public static Block feeder;
    public static Block millstone;
    public static Block fishing_net;
    public static Block thatch_block;

    /*    public static final Block thatch_stairs = null;
        public static final Block thatch_slab = null;*/
    static {
        flint_workbench = new FlintWorkbenchBlock();
        drying_rack = new DryingRackBlock();
        tanning_rack = new TanningRackBlock();
        stone_chest = new StoneChestBlock();
        tree_stump = new TreeStumpBlock();
        dried_grass_bed = new DriedGrassBedBlock();
        feeder = new FeederBlock();
        millstone = new MillstoneBlock();
        fishing_net = new FishingNetBlock();
        thatch_block = new Block(Block.Properties.of().strength(1.0f).sound(SoundType.GRASS));
    }

    @SuppressWarnings({"ConstantConditions", "Convert2MethodRef"})
//    @SubscribeEvent
    public static void registerBlocks(@Nonnull RegisterEvent event) {
        Block.Properties thatchProperties = Block.Properties.of().strength(1.0f).sound(SoundType.GRASS);

/*        IForgeRegistry<Block> registry = event.getRegistry();
        registry.register(new FlintWorkbenchBlock().setRegistryName(MODID, "flint_workbench"));
        registry.register(new DryingRackBlock().setRegistryName(MODID, "drying_rack"));
        registry.register(new TanningRackBlock().setRegistryName(MODID, "tanning_rack"));
        registry.register(new StoneChestBlock().setRegistryName(MODID, "stone_chest"));
        registry.register(new TreeStumpBlock().setRegistryName(MODID, "tree_stump"));
        registry.register(new DriedGrassBedBlock().setRegistryName(MODID, "dried_grass_bed"));
        registry.register(new FeederBlock().setRegistryName(MODID, "feeder"));
        registry.register(new MillstoneBlock().setRegistryName(MODID, "millstone"));
        registry.register(new FishingNetBlock().setRegistryName(MODID, "fishing_net"));
        registry.register(new Block(thatchProperties).setRegistryName(MODID, "thatch_block"));*/
//        registry.register(new StairBlock(() -> thatch_block.defaultBlockState(), thatchProperties).setRegistryName(MODID, "thatch_stairs"));
//        registry.register(new SlabBlock(thatchProperties).setRegistryName(MODID, "thatch_slab"));
      /*  flint_workbench = new FlintWorkbenchBlock();
        drying_rack = new DryingRackBlock();
        tanning_rack =new TanningRackBlock();
        stone_chest =new StoneChestBlock();
        tree_stump =new TreeStumpBlock();
        dried_grass_bed =new DriedGrassBedBlock();
        feeder = new FeederBlock();
        millstone =new MillstoneBlock();
        fishing_net =new FishingNetBlock();
        thatch_block =new Block(thatchProperties);*/
        event.register(ForgeRegistries.Keys.BLOCKS,
                helper -> {
                    helper.register(new ResourceLocation(MODID, "flint_workbench"), flint_workbench);
                    helper.register(new ResourceLocation(MODID, "drying_rack"), drying_rack);
                    helper.register(new ResourceLocation(MODID, "tanning_rack"), tanning_rack);
                    helper.register(new ResourceLocation(MODID, "stone_chest"), stone_chest);
                    helper.register(new ResourceLocation(MODID, "tree_stump"), tree_stump);
                    helper.register(new ResourceLocation(MODID, "dried_grass_bed"), dried_grass_bed);
                    helper.register(new ResourceLocation(MODID, "feeder"), feeder);
                    helper.register(new ResourceLocation(MODID, "millstone"), millstone);
                    helper.register(new ResourceLocation(MODID, "fishing_net"), fishing_net);
                    helper.register(new ResourceLocation(MODID, "thatch_block"), thatch_block);
                });
    }
}
