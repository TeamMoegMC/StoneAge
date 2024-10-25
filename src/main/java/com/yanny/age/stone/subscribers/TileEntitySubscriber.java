package com.yanny.age.stone.subscribers;

import com.yanny.age.stone.blocks.*;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.ObjectHolder;

import javax.annotation.Nonnull;

import static com.yanny.age.stone.Reference.MODID;

@ObjectHolder(MODID)
@Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class TileEntitySubscriber {
    public static final BlockEntityType<FlintWorkbenchTileEntity> flint_workbench = null;
    public static final BlockEntityType<DryingRackTileEntity> drying_rack = null;
    public static final BlockEntityType<TanningRackTileEntity> tanning_rack = null;
    public static final BlockEntityType<StoneChestTileEntity> stone_chest = null;
    public static final BlockEntityType<TreeStumpTileEntity> tree_stump = null;
    public static final BlockEntityType<DriedGrassBedTileEntity> dried_grass_bed = null;
    public static final BlockEntityType<FeederTileEntity> feeder = null;
    public static final BlockEntityType<MillstoneTileEntity> millstone = null;
    public static final BlockEntityType<FishingNetTileEntity> fishing_net = null;

    @SuppressWarnings("ConstantConditions")
    @SubscribeEvent
    public static void registerTileEntity(@Nonnull RegistryEvent.Register<BlockEntityType<?>> event) {
        IForgeRegistry<BlockEntityType<?>> registry = event.getRegistry();
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
                .build(null).setRegistryName("fishing_net"));
    }
}
