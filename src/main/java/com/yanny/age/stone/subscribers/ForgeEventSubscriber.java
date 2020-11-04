package com.yanny.age.stone.subscribers;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.yanny.age.stone.config.Config;
import com.yanny.age.stone.entities.SaberToothTigerEntity;
import com.yanny.ages.api.enums.Age;
import com.yanny.ages.api.utils.AgeUtils;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementList;
import net.minecraft.advancements.AdvancementManager;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.passive.ChickenEntity;
import net.minecraft.entity.passive.SheepEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.AxeItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.item.crafting.RecipeManager;
import net.minecraft.util.Hand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.ProbabilityConfig;
import net.minecraft.world.gen.placement.IPlacementConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.player.AdvancementEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import net.minecraftforge.fml.event.server.FMLServerAboutToStartEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nonnull;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static com.yanny.age.stone.Reference.MODID;
import static com.yanny.age.stone.subscribers.EntitySubscriber.*;
import static net.minecraft.block.Blocks.*;
import static net.minecraft.entity.EntityClassification.CREATURE;
import static net.minecraft.entity.EntityType.*;

@Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ForgeEventSubscriber {

    private static final Set<ResourceLocation> RECIPES_TO_REMOVE = Sets.newHashSet(
            new ResourceLocation("minecraft", "wooden_axe"),        // removed
            new ResourceLocation("minecraft", "wooden_pickaxe"),    // removed
            new ResourceLocation("minecraft", "wooden_hoe"),        // removed
            new ResourceLocation("minecraft", "wooden_shovel"),     // removed
            new ResourceLocation("minecraft", "wooden_sword"),      // removed
            new ResourceLocation("minecraft", "stone_axe"),         // removed
            new ResourceLocation("minecraft", "stone_pickaxe"),     // removed
            new ResourceLocation("minecraft", "stone_hoe"),         // removed
            new ResourceLocation("minecraft", "stone_shovel"),      // removed
            new ResourceLocation("minecraft", "stone_sword"),       // removed
            new ResourceLocation("minecraft", "torch"),             // lit by using on fire or campfire
            new ResourceLocation("minecraft", "oak_planks"),        // removed
            new ResourceLocation("minecraft", "birch_planks"),      // removed
            new ResourceLocation("minecraft", "acacia_planks"),     // removed
            new ResourceLocation("minecraft", "jungle_planks"),     // removed
            new ResourceLocation("minecraft", "spruce_planks"),     // removed
            new ResourceLocation("minecraft", "dark_oak_planks"),   // removed
            new ResourceLocation("minecraft", "crafting_table")     // changed recipe
    );
    private static final Set<ResourceLocation> RECIPES_TO_ADD = Sets.newHashSet(
            new ResourceLocation(MODID, "crafting_table")
    );
    private static final Set<ResourceLocation> ADVANCEMENTS_TO_REMOVE = Sets.newHashSet(
            new ResourceLocation("minecraft", "recipes/tools/wooden_axe"),
            new ResourceLocation("minecraft", "recipes/tools/wooden_pickaxe"),
            new ResourceLocation("minecraft", "recipes/tools/wooden_hoe"),
            new ResourceLocation("minecraft", "recipes/tools/wooden_shovel"),
            new ResourceLocation("minecraft", "recipes/combat/wooden_sword"),
            new ResourceLocation("minecraft", "recipes/tools/stone_axe"),
            new ResourceLocation("minecraft", "recipes/tools/stone_pickaxe"),
            new ResourceLocation("minecraft", "recipes/tools/stone_hoe"),
            new ResourceLocation("minecraft", "recipes/tools/stone_shovel"),
            new ResourceLocation("minecraft", "recipes/combat/stone_sword"),
            new ResourceLocation("minecraft", "recipes/building_blocks/oak_planks"),
            new ResourceLocation("minecraft", "recipes/building_blocks/birch_planks"),
            new ResourceLocation("minecraft", "recipes/building_blocks/acacia_planks"),
            new ResourceLocation("minecraft", "recipes/building_blocks/jungle_planks"),
            new ResourceLocation("minecraft", "recipes/building_blocks/spruce_planks"),
            new ResourceLocation("minecraft", "recipes/building_blocks/dark_oak_planks"),
            new ResourceLocation("minecraft", "recipes/decorations/torch")
    );

    @SuppressWarnings("unchecked")
    @SubscribeEvent
    public static void FMLServerStartingEvent(@Nonnull FMLServerStartingEvent event) {
        if (Config.removeVanillaRecipes) {
            RecipeManager recipeManager = event.getServer().getRecipeManager();
            Class<?> recipeManagerClass = recipeManager.getClass();

            try {
                Field recipes = recipeManagerClass.getDeclaredFields()[2];
                recipes.setAccessible(true);
                Map<IRecipeType<?>, Map<ResourceLocation, IRecipe<?>>> recipesMap = (Map<IRecipeType<?>, Map<ResourceLocation, IRecipe<?>>>) recipes.get(recipeManager);
                Map<IRecipeType<?>, Map<ResourceLocation, IRecipe<?>>> map = Maps.newHashMap();
                recipesMap.forEach((iRecipeType, resourceLocationIRecipeMap) -> {
                    Map<ResourceLocation, IRecipe<?>> map1 = map.computeIfAbsent(iRecipeType, (recipeType) -> Maps.newHashMap());
                    resourceLocationIRecipeMap.forEach(map1::put);
                    RECIPES_TO_REMOVE.forEach(map1::remove);
                    RECIPES_TO_ADD.forEach(resourceLocation -> {
                        IRecipe<?> recipe = map1.remove(resourceLocation);

                        if (recipe != null) {
                            map1.put(new ResourceLocation("minecraft", resourceLocation.getPath()), recipe);
                        }
                    });
                });
                recipes.set(recipeManager, ImmutableMap.copyOf(map));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }

            AdvancementManager advancementManager = event.getServer().getAdvancementManager();
            Class<?> advancementManagerClass = advancementManager.getClass();
            Field advancements = advancementManagerClass.getDeclaredFields()[2];
            advancements.setAccessible(true);

            try {
                AdvancementList advancementList = (AdvancementList) advancements.get(advancementManager);
                Class<?> list = advancementList.getClass();
                Field listField = list.getDeclaredFields()[1];
                listField.setAccessible(true);
                Map<ResourceLocation, Advancement> map = (Map<ResourceLocation, Advancement>) listField.get(advancementList);
                ADVANCEMENTS_TO_REMOVE.forEach(map::remove);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        if (Config.forceToolForWood) {
            setUseToolForWood();
        }
    }

    @SubscribeEvent
    public static void entitySpawnEvent(@Nonnull EntityJoinWorldEvent event) {
        Entity entity = event.getEntity();

        if (entity instanceof SheepEntity) {
            SheepEntity sheepEntity = (SheepEntity) entity;
            sheepEntity.goalSelector.addGoal(4, new AvoidEntityGoal<>(sheepEntity, SaberToothTigerEntity.class, 14.0F, 1.5D, 2.2D));
        }
        if (entity instanceof ChickenEntity) {
            ChickenEntity chickenEntity = (ChickenEntity) entity;
            chickenEntity.goalSelector.addGoal(4, new AvoidEntityGoal<>(chickenEntity, SaberToothTigerEntity.class, 14.0F, 1.5D, 2.2D));
        }
    }

    @SubscribeEvent
    public static void rightClickBlockEvent(@Nonnull PlayerInteractEvent.RightClickBlock event) {
        BlockPos blockPos = event.getPos();
        BlockState blockState = event.getWorld().getBlockState(blockPos);

        if (Config.disableVanillaCraftingTable && (blockState.getBlock() == CRAFTING_TABLE) && (AgeUtils.getPlayerAge(event.getPlayer()) <= Age.STONE_AGE.value)) {
            event.setUseBlock(Event.Result.DENY);
        }
    }

    @SubscribeEvent
    public static void advancementEvent(@Nonnull AdvancementEvent event) {
        if (event.getAdvancement().getId().equals(new ResourceLocation(MODID, "stone_age/end_of_stone_age")) && (AgeUtils.getPlayerAge(event.getPlayer()) <= Age.STONE_AGE.value)) {
            AgeUtils.setPlayerAge(event.getPlayer(), Age.BRONZE_AGE);
        }
    }

    @SuppressWarnings("ConstantConditions")
    @SubscribeEvent
    public static void litTorch(@Nonnull PlayerInteractEvent.RightClickBlock event) {
        PlayerEntity player = event.getPlayer();

        if (event.getHand() == Hand.MAIN_HAND && player.getHeldItemMainhand().getItem().equals(ItemSubscriber.unlit_torch)) {
            World world = event.getWorld();
            BlockPos pos = event.getPos();
            BlockPos facePos = event.getPos().offset(event.getFace());
            BlockState blockState = world.getBlockState(pos);
            BlockState faceBlockState = world.getBlockState(facePos);

            if (blockState.getBlock().equals(CAMPFIRE) || faceBlockState.getBlock().equals(FIRE) || blockState.getBlock().equals(TORCH)) {
                player.setHeldItem(Hand.MAIN_HAND, new ItemStack(Items.TORCH, player.getHeldItemMainhand().getCount()));
                event.setUseItem(Event.Result.DENY);
            }
        }
    }

    @SubscribeEvent
    public static void disableInfinityWaterSource(@Nonnull BlockEvent.CreateFluidSourceEvent event) {
        Biome biome = event.getWorld().getBiome(event.getPos());

        if (Config.aqueductRemoveWaterSource && !Config.infinityWaterSourceBiomeList.contains(biome)) {
            event.setResult(Event.Result.DENY);
        }
    }

    @SuppressWarnings("ConstantConditions")
    @SubscribeEvent
    public static void makeFireWithSticksAndDriedGrass(@Nonnull PlayerInteractEvent.RightClickBlock event) {
        PlayerEntity player = event.getPlayer();
        ItemStack mainItem = player.getHeldItemMainhand();
        ItemStack offItem = player.getHeldItemOffhand();

        if (mainItem.getItem() == Items.STICK && offItem.getItem() == Items.STICK && event.getFace() != null) {
            World world = event.getWorld();
            BlockPos position = event.getPos().offset(event.getFace());
            BlockState blockState = world.getBlockState(position);
            List<ItemEntity> driedGrassList = world.getEntitiesWithinAABB(ItemEntity.class, new AxisAlignedBB(position),
                    itemEntity -> itemEntity.getItem().getItem().equals(ItemSubscriber.dried_grass));

            if (blockState.isAir(world, position) && !driedGrassList.isEmpty()) {
                world.setBlockState(position, FIRE.getDefaultState(), 11);
                player.sendBreakAnimation(Hand.MAIN_HAND);
                player.sendBreakAnimation(Hand.OFF_HAND);

                if (mainItem.getCount() > 1) {
                    mainItem.setCount(mainItem.getCount() - 1);
                } else {
                    player.setHeldItem(Hand.MAIN_HAND, ItemStack.EMPTY);
                }
                if (offItem.getCount() > 1) {
                    offItem.setCount(offItem.getCount() - 1);
                } else {
                    player.setHeldItem(Hand.OFF_HAND, ItemStack.EMPTY);
                }

                driedGrassList.forEach(Entity::remove);
            }
        }
    }

    @SuppressWarnings("ConstantConditions")
    @SubscribeEvent
    public static void serverAboutToStartEvent(@Nonnull FMLServerAboutToStartEvent event) {
        for (Biome biome : ForgeRegistries.BIOMES) {
            if (Config.spawnDeerEnable && (!Config.spawnDeerAllowedBiomesBlacklist == Config.spawnDeerAllowedBiomes.contains(biome))) {
                biome.getSpawns(deer.getClassification()).add(new Biome.SpawnListEntry(deer, Config.spawnDeerWeight, Config.spawnDeerMinCount, Config.spawnDeerMaxCount));
            }
            if (Config.spawnBoarEnable && (!Config.spawnBoarAllowedBiomesBlacklist == Config.spawnBoarAllowedBiomes.contains(biome))) {
                biome.getSpawns(boar.getClassification()).add(new Biome.SpawnListEntry(boar, Config.spawnBoarWeight, Config.spawnBoarMinCount, Config.spawnBoarMaxCount));
            }
            if (Config.spawnAurochEnable && (!Config.spawnAurochAllowedBiomesBlacklist == Config.spawnAurochAllowedBiomes.contains(biome))) {
                biome.getSpawns(auroch.getClassification()).add(new Biome.SpawnListEntry(auroch, Config.spawnAurochWeight, Config.spawnAurochMinCount, Config.spawnAurochMaxCount));
            }
            if (Config.spawnFowlEnable && (!Config.spawnFowlAllowedBiomesBlacklist == Config.spawnFowlAllowedBiomes.contains(biome))) {
                biome.getSpawns(fowl.getClassification()).add(new Biome.SpawnListEntry(fowl, Config.spawnFowlWeight, Config.spawnFowlMinCount, Config.spawnFowlMaxCount));
            }
            if (Config.spawnMouflonEnable && (!Config.spawnMouflonAllowedBiomesBlacklist == Config.spawnMouflonAllowedBiomes.contains(biome))) {
                biome.getSpawns(mouflon.getClassification()).add(new Biome.SpawnListEntry(mouflon, Config.spawnMouflonWeight, Config.spawnMouflonMinCount, Config.spawnMouflonMaxCount));
            }
            if (Config.spawnMammothEnable && (!Config.spawnMammothAllowedBiomesBlacklist == Config.spawnMammothAllowedBiomes.contains(biome))) {
                biome.getSpawns(mammoth.getClassification()).add(new Biome.SpawnListEntry(mammoth, Config.spawnMammothWeight, Config.spawnMammothMinCount, Config.spawnMammothMaxCount));
            }
            if (Config.spawnSaberToothTigerEnable && (!Config.spawnSaberToothTigerAllowedBiomesBlacklist == Config.spawnSaberToothTigerAllowedBiomes.contains(biome))) {
                biome.getSpawns(saber_tooth_tiger.getClassification()).add(new Biome.SpawnListEntry(saber_tooth_tiger, Config.spawnSaberToothTigerWeight, Config.spawnSaberToothTigerMinCount, Config.spawnSaberToothTigerMaxCount));
            }
            if (Config.spawnWoollyRhinoEnable && (!Config.spawnWoollyRhinoAllowedBiomesBlacklist == Config.spawnWoollyRhinoAllowedBiomes.contains(biome))) {
                biome.getSpawns(woolly_rhino.getClassification()).add(new Biome.SpawnListEntry(woolly_rhino, Config.spawnWoollyRhinoWeight, Config.spawnWoollyRhinoMinCount, Config.spawnWoollyRhinoMaxCount));
            }

            if (Config.removeVanillaGeneratedAnimals) {
                //noinspection unchecked
                biome.getSpawns(CREATURE).removeIf(entry -> Sets.newHashSet(COW, SHEEP, PIG, CHICKEN).contains(entry.entityType));
            }

            if (Config.abandonedCampAllowedBiomes.contains(biome)) {
                biome.addStructure(FeatureSubscriber.abandoned_camp_structure.withConfiguration(new ProbabilityConfig((float) Config.abandonedCampSpawnChance)));
                biome.addFeature(GenerationStage.Decoration.SURFACE_STRUCTURES,
                        FeatureSubscriber.abandoned_camp_structure.withConfiguration(new ProbabilityConfig((float) Config.abandonedCampSpawnChance)).
                                withPlacement(Placement.NOPE.configure(IPlacementConfig.NO_PLACEMENT_CONFIG)));
            }

            if (Config.burialPlaceAllowedBiomes.contains(biome)) {
                biome.addStructure(FeatureSubscriber.burial_place_structure.withConfiguration(new ProbabilityConfig((float) Config.burialPlaceSpawnChance)));
                biome.addFeature(GenerationStage.Decoration.SURFACE_STRUCTURES,
                        FeatureSubscriber.burial_place_structure.withConfiguration(new ProbabilityConfig((float) Config.burialPlaceSpawnChance)).
                                withPlacement(Placement.NOPE.configure(IPlacementConfig.NO_PLACEMENT_CONFIG)));
            }
        }
    }

    @SubscribeEvent
    public static void axeHarvestCheck(@Nonnull PlayerEvent.HarvestCheck event) {
        BlockState state = event.getTargetBlock();
        PlayerEntity entity = event.getPlayer();
        ItemStack stack = entity.getHeldItem(Hand.MAIN_HAND);
        Item item = stack.getItem();

        if ((entity.getHeldItem(Hand.MAIN_HAND).getItem() instanceof AxeItem) && (state.getMaterial() == Material.WOOD) &&
                (state.getBlock().getHarvestLevel(state) <= item.getHarvestLevel(stack, ToolType.AXE, entity, state))) {
            event.setCanHarvest(true);
        }
    }

    private static void setUseToolForWood() {
        Field field = ObfuscationReflectionHelper.findField(Material.class, "field_76241_J");
        field.setAccessible(true);

        try {
            Field modifiersField = Field.class.getDeclaredField("modifiers");
            modifiersField.setAccessible(true);
            modifiersField.setInt(field, field.getModifiers() & ~Modifier.FINAL);
            field.setBoolean(Material.WOOD, false);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
