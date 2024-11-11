package com.yanny.age.stone.modifiers;

import com.google.gson.JsonObject;
import com.mojang.serialization.Codec;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.util.GsonHelper;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.common.loot.LootModifier;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;
import java.util.List;

public class LeavesDropModifier extends LootModifier {
    protected LeavesDropModifier(LootItemCondition[] conditionsIn) {
        super(conditionsIn);
    }

    @Override
    protected @NotNull ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> objectArrayList, LootContext lootContext) {
        return null;
    }

    @Override
    public Codec<? extends IGlobalLootModifier> codec() {
        return null;
    }
/*    private final float chance;

    private LeavesDropModifier(LootItemCondition[] conditionsIn, float chance) {
        super(conditionsIn);
        this.chance = chance;
    }

    @Nonnull
    @Override
    protected ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> objectArrayList, LootContext context) {
        if(context.hasParam(LootContextParams.THIS_ENTITY)) {//only by hand
        	ItemStack ctxTool = context.getParamOrNull(LootContextParams.TOOL);
            float chanceAfterLooting = chance;
	        if (ctxTool != null) {
	            if (EnchantmentHelper.getEnchantments(ctxTool).containsKey(Enchantments.SILK_TOUCH)) {
	                return generatedLoot;
	            }
	
	            if (EnchantmentHelper.getEnchantments(ctxTool).containsKey(Enchantments.MOB_LOOTING)) {
	                int enchantmentLevel = EnchantmentHelper.getItemEnchantmentLevel(Enchantments.MOB_LOOTING, ctxTool);
	                chanceAfterLooting = chanceAfterLooting * enchantmentLevel;
	            }
	        }
	
	        if (Math.random() < chanceAfterLooting) {
	            generatedLoot.add(new ItemStack(Items.STICK, Math.max(1, Math.round(chanceAfterLooting))));
	        }
        }
        return generatedLoot;
    }

    @Override
    public Codec<? extends IGlobalLootModifier> codec() {
        return null;
    }

    public static class Serializer extends GlobalLootModifierSerializer<LeavesDropModifier> {

        @Override
        public LeavesDropModifier read(ResourceLocation location, JsonObject object, LootItemCondition[] conditions) {
            float chance = GsonHelper.getAsFloat(object, "chance");
            return new LeavesDropModifier(conditions, chance);
        }

        @Override
        public JsonObject write(LeavesDropModifier instance) {
            JsonObject object = new JsonObject();
            object.addProperty("chance", instance.chance);
            return object;
        }
    }*/
}
