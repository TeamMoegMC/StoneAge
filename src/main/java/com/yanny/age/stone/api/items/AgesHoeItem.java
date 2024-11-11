package com.yanny.age.stone.api.items;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import com.google.common.collect.Sets;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.InteractionResult;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundSource;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;

import javax.annotation.Nonnull;
import java.util.Map;
import java.util.Set;

import net.minecraft.world.item.HoeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.context.UseOnContext;

public class AgesHoeItem extends AgesToolItem {
    public AgesHoeItem(float attackDamageIn, float attackSpeedIn, Tier tier, Set<Block> effectiveBlocksIn, Properties builder, boolean displayAttackDamage, boolean displayAttackSpeed, boolean displayEfficiency) {
        super(attackDamageIn, attackSpeedIn, tier, effectiveBlocksIn, builder, displayAttackDamage, displayAttackSpeed, displayEfficiency);
    }
   /* private static final Map<Block, BlockState> HOE_LOOKUP;

    static {
        HOE_LOOKUP = getPrivateValue(HoeItem.class, null, 1);
    }

    public AgesHoeItem(Tier tier, float attackSpeedIn, Properties builder) {
        super(0f, attackSpeedIn, tier, Sets.newHashSet(), builder, false, true, true);
    }

    *//**
     * Called when this item is used when targetting a Block
     *//*
    @Nonnull
    @Override
    public InteractionResult useOn(UseOnContext context) {
        Level world = context.getLevel();
        BlockPos blockpos = context.getClickedPos();
        int hook = net.minecraftforge.event.ForgeEventFactory.onHoeUse(context);

        if (hook != 0) {
            return hook > 0 ? InteractionResult.SUCCESS : InteractionResult.FAIL;
        }

        if (context.getClickedFace() != Direction.DOWN && world.isEmptyBlock(blockpos.above())) {
            BlockState blockstate = HOE_LOOKUP.get(world.getBlockState(blockpos).getBlock());

            if (blockstate != null) {
                Player playerentity = context.getPlayer();
                world.playSound(playerentity, blockpos, SoundEvents.HOE_TILL, SoundSource.BLOCKS, 1.0F, 1.0F);

                if (!world.isClientSide) {
                    world.setBlock(blockpos, blockstate, 11);

                    if (playerentity != null) {
                        context.getItemInHand().hurtAndBreak(1, playerentity, (playerEntity) -> playerEntity.broadcastBreakEvent(context.getHand()));
                    }
                }

                return InteractionResult.SUCCESS;
            }
        }

        return InteractionResult.PASS;
    }

    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(EquipmentSlot slot, ItemStack stack) {
        if (slot == EquipmentSlot.MAINHAND) {
            ImmutableMultimap.Builder<Attribute, AttributeModifier> attributeModifierBuilder = ImmutableMultimap.builder();
            attributeModifierBuilder.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(BASE_ATTACK_DAMAGE_UUID,
                    "Tool modifier", 0, AttributeModifier.Operation.ADDITION));
            attributeModifierBuilder.put(Attributes.ATTACK_SPEED, new AttributeModifier(BASE_ATTACK_SPEED_UUID,
                    "Tool modifier", this.attackSpeed + getAdditionalAttackSpeed(stack), AttributeModifier.Operation.ADDITION));
            return attributeModifierBuilder.build();
        } else {
            return super.getAttributeModifiers(slot, stack);
        }
    }

    *//**
     * Current implementations of this method in child classes do not use the entry argument beside ev. They just raise
     * the damage on the stack.
     *//*
    @Override
    public boolean hurtEnemy(ItemStack stack, @Nonnull LivingEntity target, @Nonnull LivingEntity attacker) {
        stack.hurtAndBreak(1, attacker, (livingEntity) -> livingEntity.broadcastBreakEvent(EquipmentSlot.MAINHAND));
        return true;
    }*/
}
