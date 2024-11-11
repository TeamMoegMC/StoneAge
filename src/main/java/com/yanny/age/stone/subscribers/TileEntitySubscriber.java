package com.yanny.age.stone.subscribers;

import com.yanny.age.stone.blocks.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegisterEvent;

import javax.annotation.Nonnull;

import static com.yanny.age.stone.Reference.MODID;

//@ObjectHolder(MODID)
//@Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class TileEntitySubscriber {
/*    public static BlockEntityType<FlintWorkbenchTileEntity> flint_workbench;
    public static  BlockEntityType<DryingRackTileEntity> drying_rack ;
    public static  BlockEntityType<TanningRackTileEntity> tanning_rack;
    public static  BlockEntityType<StoneChestTileEntity> stone_chest;
    public static  BlockEntityType<TreeStumpTileEntity> tree_stump;
    public static  BlockEntityType<DriedGrassBedTileEntity> dried_grass_bed;
    public static  BlockEntityType<FeederTileEntity> feeder;
    public static  BlockEntityType<MillstoneTileEntity> millstone;
    public static  BlockEntityType<FishingNetTileEntity> fishing_net;*/
    public static BlockEntityType<FlintWorkbenchTileEntity> flint_workbench = BlockEntityType.Builder.of(FlintWorkbenchTileEntity::new, BlockSubscriber.flint_workbench).build(null);
    public static  BlockEntityType<DryingRackTileEntity> drying_rack = BlockEntityType.Builder.of(DryingRackTileEntity::new, BlockSubscriber.drying_rack).build(null);
    public static  BlockEntityType<TanningRackTileEntity> tanning_rack = BlockEntityType.Builder.of(TanningRackTileEntity::new, BlockSubscriber.tanning_rack).build(null);
    public static  BlockEntityType<StoneChestTileEntity> stone_chest = BlockEntityType.Builder.of(StoneChestTileEntity::new, BlockSubscriber.stone_chest).build(null);
    public static  BlockEntityType<TreeStumpTileEntity> tree_stump = BlockEntityType.Builder.of(TreeStumpTileEntity::new, BlockSubscriber.tree_stump).build(null);
    public static  BlockEntityType<DriedGrassBedTileEntity> dried_grass_bed = BlockEntityType.Builder.of(DriedGrassBedTileEntity::new, BlockSubscriber.dried_grass_bed).build(null);
    public static  BlockEntityType<FeederTileEntity> feeder = BlockEntityType.Builder.of(FeederTileEntity::new, BlockSubscriber.feeder).build(null);
    public static  BlockEntityType<MillstoneTileEntity> millstone = BlockEntityType.Builder.of(MillstoneTileEntity::new, BlockSubscriber.millstone).build(null);
    public static  BlockEntityType<FishingNetTileEntity> fishing_net = BlockEntityType.Builder.of(FishingNetTileEntity::new, BlockSubscriber.fishing_net).build(null);

    @SuppressWarnings("ConstantConditions")
//    @SubscribeEvent
    public static void registerTileEntity(@Nonnull RegisterEvent event) {
       /* IForgeRegistry<BlockEntityType<?>> registry = event.getRegistry();
        registry.register(BlockEntityType.Builder.of(FlintWorkbenchTileEntity::new, BlockSubscriber.flint_workbench)
                .build(null).setRegistryName("flint_workbench"));
        registry.register(BlockEntityType.Builder.of(DryingRackTileEntity::new, BlockSubscriber.drying_rack)
                .build(null).setRegistryName("drying_rack"));
        registry.register(BlockEntityType.Builder.of(TanningRackTileEntity::new, BlockSubscriber.tanning_rack)
                .build(null).setRegistryName("tanning_rack"));
        registry.register(BlockEntityType.Builder.of(StoneChestTileEntity::new, BlockSubscriber.stone_chest)
                .build(null).setRegistryName("stone_chest"));
        registry.register(BlockEntityType.Builder.of(TreeStumpTileEntity::new, BlockSubscriber.tree_stump)
                .build(null).setRegistryName("tree_stump"));
        registry.register(BlockEntityType.Builder.of(DriedGrassBedTileEntity::new, BlockSubscriber.dried_grass_bed)
                .build(null).setRegistryName("dried_grass_bed"));
        registry.register(BlockEntityType.Builder.of(FeederTileEntity::new, BlockSubscriber.feeder)
                .build(null).setRegistryName("feeder"));
        registry.register(BlockEntityType.Builder.of(MillstoneTileEntity::new, BlockSubscriber.millstone)
                .build(null).setRegistryName("millstone"));
        registry.register(BlockEntityType.Builder.of(FishingNetTileEntity::new, BlockSubscriber.fishing_net)
                .build(null).setRegistryName("fishing_net"));*/
 
        event.register(ForgeRegistries.Keys.BLOCK_ENTITY_TYPES,
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
                }
        );
    }
}
