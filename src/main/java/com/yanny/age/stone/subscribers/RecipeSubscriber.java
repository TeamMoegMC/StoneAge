package com.yanny.age.stone.subscribers;

import com.yanny.age.stone.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegisterEvent;

import static com.yanny.age.stone.Reference.MODID;

@SuppressWarnings("unused")
//@ObjectHolder(MODID)
//@Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class RecipeSubscriber {
    public static final FlintWorkbenchRecipeSerializer flint_workbench_serializer = new FlintWorkbenchRecipeSerializer();
    public static final DryingRackRecipeSerializer drying_rack_serializer = new DryingRackRecipeSerializer(DryingRackRecipe::new);
    public static final TanningRackRecipeSerializer tanning_rack_serializer = new TanningRackRecipeSerializer(TanningRackRecipe::new);
    public static final TreeStumpRecipeSerializer tree_stump_serializer = new TreeStumpRecipeSerializer(TreeStumpRecipe::new);
    public static final MillstoneRecipeSerializer millstone_serializer = new MillstoneRecipeSerializer(MillstoneRecipe::new);

//    @SubscribeEvent
    public static void registerRecipeType(RegisterEvent event) {
        /*IForgeRegistry<RecipeSerializer<?>> registry = event.getRegistry();
        registry.register(new FlintWorkbenchRecipeSerializer().setRegistryName(MODID, "flint_workbench"));
        registry.register(new DryingRackRecipeSerializer(DryingRackRecipe::new).setRegistryName(MODID, "drying_rack"));
        registry.register(new TanningRackRecipeSerializer(TanningRackRecipe::new).setRegistryName(MODID, "tanning_rack"));
        registry.register(new TreeStumpRecipeSerializer(TreeStumpRecipe::new).setRegistryName(MODID, "tree_stump"));
        registry.register(new MillstoneRecipeSerializer(MillstoneRecipe::new).setRegistryName(MODID, "millstone"));*/
        event.register(ForgeRegistries.Keys.RECIPE_TYPES,
                helper -> {
                    helper.register(new ResourceLocation(MODID, "flint_workbench"), FlintWorkbenchRecipe.flint_workbench);
                    helper.register(new ResourceLocation(MODID, "drying_rack"), DryingRackRecipe.drying_rack);
                    helper.register(new ResourceLocation(MODID, "tanning_rack"), TanningRackRecipe.tanning_rack);
                    helper.register(new ResourceLocation(MODID, "tree_stump"), TreeStumpRecipe.tree_stump);
                    helper.register(new ResourceLocation(MODID, "millstone"), MillstoneRecipe.millstone);
                }
        );
        event.register(ForgeRegistries.Keys.RECIPE_SERIALIZERS,
                helper -> {
                    helper.register(new ResourceLocation(MODID, "flint_workbench"), flint_workbench_serializer);
                    helper.register(new ResourceLocation(MODID, "drying_rack"), drying_rack_serializer);
                    helper.register(new ResourceLocation(MODID, "tanning_rack"), tanning_rack_serializer);
                    helper.register(new ResourceLocation(MODID, "tree_stump"), tree_stump_serializer);
                    helper.register(new ResourceLocation(MODID, "millstone"), millstone_serializer);
                }
        );
    }
}
