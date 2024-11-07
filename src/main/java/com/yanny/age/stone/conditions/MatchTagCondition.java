package com.yanny.age.stone.conditions;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.yanny.age.stone.subscribers.ModifierSubscriber;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.predicates.LootItemConditionType;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.GsonHelper;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.tags.ITag;

import javax.annotation.Nonnull;

public class MatchTagCondition implements LootItemCondition {
    private final ITag<Block> tag;

    private MatchTagCondition(ITag<Block> tag) {
        this.tag = tag;
    }

    @Nonnull
    @Override
    public LootItemConditionType getType() {
        return ModifierSubscriber.matchTag;
    }

    @Override
    public boolean test(LootContext lootContext) {
        BlockState blockstate = lootContext.getParamOrNull(LootContextParams.BLOCK_STATE);
        return blockstate != null && tag.contains(blockstate.getBlock());
    }

    public static class Serializer implements net.minecraft.world.level.storage.loot.Serializer<MatchTagCondition> {

        @Override
        public void serialize(@Nonnull JsonObject jsonObject, @Nonnull MatchTagCondition matchTagCondition, @Nonnull JsonSerializationContext serializationContext) {
            jsonObject.addProperty("tag", matchTagCondition.tag.toString());
        }

        @Nonnull
        @Override
        public MatchTagCondition deserialize(@Nonnull JsonObject jsonObject, @Nonnull JsonDeserializationContext context) {
            ITag<Block> optional = ForgeRegistries.BLOCKS.tags().getTag(BlockTags.create(new ResourceLocation(GsonHelper.getAsString(jsonObject, "tag"))));
            return new MatchTagCondition(optional);
        }
    }
}
