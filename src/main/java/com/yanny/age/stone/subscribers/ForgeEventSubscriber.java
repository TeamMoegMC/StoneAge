package com.yanny.age.stone.subscribers;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.yanny.age.stone.config.Config;
import com.yanny.age.stone.entities.SaberToothTigerEntity;
import com.yanny.ages.api.enums.Age;
import com.yanny.ages.api.utils.AgeUtils;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
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
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Hand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.MobSpawnInfo;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.Features;
import net.minecraft.world.gen.feature.ProbabilityConfig;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.common.world.MobSpawnInfoBuilder;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.player.AdvancementEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.event.world.StructureSpawnListGatherEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static com.yanny.age.stone.Reference.MODID;
import static com.yanny.age.stone.subscribers.EntitySubscriber.*;
import static net.minecraft.block.Blocks.*;
import static net.minecraft.entity.EntityClassification.CREATURE;
import static net.minecraft.entity.EntityClassification.MISC;
import static net.minecraft.entity.EntityType.*;

@Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ForgeEventSubscriber {
    private static final String PLAYER_MANUAL_NBT = MODID + "_manual";
    private static final Set<EntityType<?>> VANILLA_ENTITIES = Sets.newHashSet(COW, SHEEP, PIG, CHICKEN);

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
            new ResourceLocation("minecraft", "stone_sword")        // removed
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
            new ResourceLocation("minecraft", "recipes/combat/stone_sword")
    );

    @SuppressWarnings("unchecked")
    @SubscribeEvent
    public static void FMLServerStartingEvent(@Nonnull FMLServerStartingEvent event) {
        if (Config.removeVanillaRecipes) {
            Map<IRecipeType<?>, Map<ResourceLocation, IRecipe<?>>> recipesMap = event.getServer().getRecipeManager().recipes;
			Map<IRecipeType<?>, Map<ResourceLocation, IRecipe<?>>> map = Maps.newHashMap();
			recipesMap.forEach((iRecipeType, resourceLocationIRecipeMap) -> {
			    Map<ResourceLocation, IRecipe<?>> map1 = map.computeIfAbsent(iRecipeType, (recipeType) -> Maps.newHashMap());
			    resourceLocationIRecipeMap.forEach(map1::put);
			    RECIPES_TO_REMOVE.forEach(map1::remove);
			});
			event.getServer().getRecipeManager().recipes=ImmutableMap.copyOf(map);
			ADVANCEMENTS_TO_REMOVE.forEach(event.getServer().getAdvancementManager().advancementList.advancements::remove);
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
    public static void advancementEvent(@Nonnull AdvancementEvent event) {
        if (event.getAdvancement().getId().equals(new ResourceLocation(MODID, "stone_age/end_of_stone_age")) && (AgeUtils.getPlayerAge(event.getPlayer()) <= Age.STONE_AGE.value)) {
            AgeUtils.setPlayerAge(event.getPlayer(), Age.BRONZE_AGE);
        }
    }

    @SubscribeEvent
    public static void litTorch(@Nonnull PlayerInteractEvent.RightClickBlock event) {
        if (Config.LitTorche) {
            PlayerEntity player = event.getPlayer();

            if (event.getHand() == Hand.MAIN_HAND && player.getHeldItemMainhand().getItem().equals(ItemSubscriber.unlit_torch)) {
                World world = event.getWorld();
                BlockPos pos = event.getPos();
                BlockState blockState = world.getBlockState(pos);

                if (blockState.getBlock().equals(CAMPFIRE) || blockState.getBlock().equals(FIRE) || blockState.getBlock().equals(TORCH)) {
                    player.setHeldItem(Hand.MAIN_HAND, new ItemStack(Items.TORCH, player.getHeldItemMainhand().getCount()));
                    event.setUseItem(Event.Result.DENY);
                }
            }
        }
    }

    @SubscribeEvent
    public static void makeFireWithSticksAndDriedGrass(@Nonnull PlayerInteractEvent.RightClickBlock event) {
        PlayerEntity player = event.getPlayer();
        ItemStack mainItem = player.getHeldItemMainhand();
        ItemStack offItem = player.getHeldItemOffhand();
        if (Config.MakeFire) {
            if (mainItem.getItem() == Items.STICK && offItem.getItem() == Items.STICK && event.getFace() != null) {
                World world = event.getWorld();
                BlockPos position = event.getPos().offset(event.getFace());
                BlockState blockState = world.getBlockState(position);
                List<ItemEntity> driedGrassList = world.getEntitiesWithinAABB(ItemEntity.class, new AxisAlignedBB(position),
                        itemEntity -> itemEntity.getItem().getItem().equals(ItemSubscriber.dried_grass));

                if (blockState.getBlock().isAir(blockState, world, position) && !driedGrassList.isEmpty()) {
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
    }

    @SubscribeEvent(priority = EventPriority.NORMAL)
    public static void structureLoadingEventRemove(@Nonnull StructureSpawnListGatherEvent event) {
        if (Config.removeVanillaGeneratedAnimals) {
            event.getEntitySpawns().forEach(((classification, spawners) -> spawners.forEach(spawner -> {
                if (VANILLA_ENTITIES.contains(spawner.type)) {
                    event.removeEntitySpawn(classification, spawner);
                }
            })));
        }
    }
    
    @SubscribeEvent(priority = EventPriority.HIGH)
    public static void biomeLoadingEventAdd(@Nonnull BiomeLoadingEvent event) {
        MobSpawnInfoBuilder spawns = event.getSpawns();

        if (Config.spawnDeerEnable && (!Config.spawnDeerAllowedBiomesBlacklist == Config.spawnDeerAllowedBiomes.stream().anyMatch(biome -> biomeComparator(biome, event)))) {
            spawns.getSpawner(deer.getClassification()).add(new MobSpawnInfo.Spawners(deer, Config.spawnDeerWeight, Config.spawnDeerMinCount, Config.spawnDeerMaxCount));
        }
        if (Config.spawnBoarEnable && (!Config.spawnBoarAllowedBiomesBlacklist == Config.spawnBoarAllowedBiomes.stream().anyMatch(biome -> biomeComparator(biome, event)))) {
            spawns.getSpawner(boar.getClassification()).add(new MobSpawnInfo.Spawners(boar, Config.spawnBoarWeight, Config.spawnBoarMinCount, Config.spawnBoarMaxCount));
        }
        if (Config.spawnAurochEnable && (!Config.spawnAurochAllowedBiomesBlacklist == Config.spawnAurochAllowedBiomes.stream().anyMatch(biome -> biomeComparator(biome, event)))) {
            spawns.getSpawner(auroch.getClassification()).add(new MobSpawnInfo.Spawners(auroch, Config.spawnAurochWeight, Config.spawnAurochMinCount, Config.spawnAurochMaxCount));
        }
        if (Config.spawnFowlEnable && (!Config.spawnFowlAllowedBiomesBlacklist == Config.spawnFowlAllowedBiomes.stream().anyMatch(biome -> biomeComparator(biome, event)))) {
            spawns.getSpawner(fowl.getClassification()).add(new MobSpawnInfo.Spawners(fowl, Config.spawnFowlWeight, Config.spawnFowlMinCount, Config.spawnFowlMaxCount));
        }
        if (Config.spawnMouflonEnable && (!Config.spawnMouflonAllowedBiomesBlacklist == Config.spawnMouflonAllowedBiomes.stream().anyMatch(biome -> biomeComparator(biome, event)))) {
            spawns.getSpawner(mouflon.getClassification()).add(new MobSpawnInfo.Spawners(mouflon, Config.spawnMouflonWeight, Config.spawnMouflonMinCount, Config.spawnMouflonMaxCount));
        }
        if (Config.spawnMammothEnable && (!Config.spawnMammothAllowedBiomesBlacklist == Config.spawnMammothAllowedBiomes.stream().anyMatch(biome -> biomeComparator(biome, event)))) {
            spawns.getSpawner(mammoth.getClassification()).add(new MobSpawnInfo.Spawners(mammoth, Config.spawnMammothWeight, Config.spawnMammothMinCount, Config.spawnMammothMaxCount));
        }
        if (Config.spawnSaberToothTigerEnable && (!Config.spawnSaberToothTigerAllowedBiomesBlacklist == Config.spawnSaberToothTigerAllowedBiomes.stream().anyMatch(biome -> biomeComparator(biome, event)))) {
            spawns.getSpawner(saber_tooth_tiger.getClassification()).add(new MobSpawnInfo.Spawners(saber_tooth_tiger, Config.spawnSaberToothTigerWeight, Config.spawnSaberToothTigerMinCount, Config.spawnSaberToothTigerMaxCount));
        }
        if (Config.spawnWoollyRhinoEnable && (!Config.spawnWoollyRhinoAllowedBiomesBlacklist == Config.spawnWoollyRhinoAllowedBiomes.stream().anyMatch(biome -> biomeComparator(biome, event)))) {
            spawns.getSpawner(woolly_rhino.getClassification()).add(new MobSpawnInfo.Spawners(woolly_rhino, Config.spawnWoollyRhinoWeight, Config.spawnWoollyRhinoMinCount, Config.spawnWoollyRhinoMaxCount));
        }
        if (Config.spawnTerrorBirdEnable && (!Config.spawnTerrorBirdAllowedBiomesBlacklist == Config.spawnTerrorBirdAllowedBiomes.stream().anyMatch(biome -> biomeComparator(biome, event)))) {
            spawns.getSpawner(terror_bird.getClassification()).add(new MobSpawnInfo.Spawners(terror_bird, Config.spawnTerrorBirdWeight, Config.spawnTerrorBirdMinCount, Config.spawnTerrorBirdMaxCount));
        }
        if (Config.spawnCoelacanthEnable && (!Config.spawnCoelacanthAllowedBiomesBlacklist == Config.spawnCoelacanthAllowedBiomes.stream().anyMatch(biome -> biomeComparator(biome, event)))) {
            spawns.getSpawner(coelacanth.getClassification()).add(new MobSpawnInfo.Spawners(coelacanth, Config.spawnCoelacanthWeight, Config.spawnCoelacanthMinCount, Config.spawnCoelacanthMaxCount));
        }

        if (Config.abandonedCampAllowedBiomes.stream().anyMatch(biome -> biomeComparator(biome, event))) {
            event.getGeneration().getFeatures(GenerationStage.Decoration.SURFACE_STRUCTURES).add(() ->
                    FeatureSubscriber.abandoned_camp_feature.withConfiguration(new ProbabilityConfig((float) Config.abandonedCampSpawnChance)).withPlacement(Features.Placements.HEIGHTMAP_PLACEMENT));
        }
        if (Config.burialPlaceAllowedBiomes.stream().anyMatch(biome -> biomeComparator(biome, event))) {
            event.getGeneration().getFeatures(GenerationStage.Decoration.SURFACE_STRUCTURES).add(() ->
                    FeatureSubscriber.burial_place_feature.withConfiguration(new ProbabilityConfig((float) Config.burialPlaceSpawnChance)).withPlacement(Features.Placements.HEIGHTMAP_PLACEMENT));
        }
    }

    @SubscribeEvent(priority = EventPriority.NORMAL)
    public static void biomeLoadingEventRemove(@Nonnull BiomeLoadingEvent event) {
        MobSpawnInfoBuilder spawns = event.getSpawns();

        if (Config.removeVanillaGeneratedAnimals) {
            spawns.getSpawner(CREATURE).removeIf(entry -> VANILLA_ENTITIES.contains(entry.type));
            spawns.getSpawner(MISC).removeIf(entry -> entry.type == PIG);
        }
    }

    @SubscribeEvent
    public static void axeHarvestCheck(@Nonnull PlayerEvent.HarvestCheck event) {
        BlockState state = event.getTargetBlock();
        PlayerEntity entity = event.getPlayer();
        ItemStack stack = entity.getHeldItem(Hand.MAIN_HAND);
        Item item = stack.getItem();

        if ((entity.getHeldItem(Hand.MAIN_HAND).getItem() instanceof AxeItem||stack.canHarvestBlock(state)) && (state.getMaterial() == Material.WOOD) &&
                (state.getBlock().getHarvestLevel(state) <= item.getHarvestLevel(stack, ToolType.AXE, entity, state))) {
            event.setCanHarvest(true);
        }
    }
    static final ResourceLocation logs=new ResourceLocation("minecraft","logs");
    @SubscribeEvent
    public static void axeBreakCheck(@Nonnull PlayerEvent.BreakSpeed event) {
        BlockState state = event.getState();
        PlayerEntity entity = event.getPlayer();
        ItemStack stack = entity.getHeldItem(Hand.MAIN_HAND);
        
        if (state.getBlock().getTags().contains(logs)&&!(stack.getItem() instanceof AxeItem||stack.canHarvestBlock(state))) {
            event.setNewSpeed(0);
        }
    }
    @SubscribeEvent
    public static void addManualToPlayer(@Nonnull PlayerEvent.PlayerLoggedInEvent event) {
        if (!Config.givePlayerManualOnFirstConnect) {
            return;
        }

        CompoundNBT nbt = event.getPlayer().getPersistentData();
        CompoundNBT persistent;

        if (!nbt.contains(PlayerEntity.PERSISTED_NBT_TAG)) {
            nbt.put(PlayerEntity.PERSISTED_NBT_TAG, (persistent = new CompoundNBT()));
        } else {
            persistent = nbt.getCompound(PlayerEntity.PERSISTED_NBT_TAG);
        }

        if (!persistent.contains(PLAYER_MANUAL_NBT)) {
            persistent.putBoolean(PLAYER_MANUAL_NBT, true);

            ItemStack book = new ItemStack(ForgeRegistries.ITEMS.getValue(new ResourceLocation("patchouli", "guide_book")));

            if (!book.isEmpty()) {
                book.getOrCreateTag().putString("patchouli:book", "stone_age:stone_tablet");
                event.getPlayer().inventory.addItemStackToInventory(book);
            }
        }
    }

    private static boolean biomeComparator(Biome biome, BiomeLoadingEvent event) {
        if (biome.getRegistryName() != null) {
            return biome.getRegistryName().compareTo(event.getName()) == 0;
        }
		return false;
    }

    private static void setUseToolForWood() {

        ForgeRegistries.BLOCKS.forEach(block -> {
            if (block.material.equals(Material.WOOD)&&block.getTags().contains(logs)) {
            	block.getDefaultState().requiresTool=true;
            }
        });

    }
}
