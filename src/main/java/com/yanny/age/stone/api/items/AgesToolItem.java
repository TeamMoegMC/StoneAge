package com.yanny.age.stone.api.items;

import com.google.common.base.Throwables;
import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TieredItem;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.ChatFormatting;
import net.minecraft.world.level.Level;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Set;

public class AgesToolItem extends TieredItem {
    private final Set<Block> effectiveBlocks;
    private final boolean displayAttackDamage;
    private final boolean displayAttackSpeed;
    private final boolean displayEfficiency;
    protected float efficiency;
    protected float attackDamage;
    protected float attackSpeed;

    public AgesToolItem(float attackDamageIn, float attackSpeedIn, Tier tier, Set<Block> effectiveBlocksIn, Item.Properties builder,
                        boolean displayAttackDamage, boolean displayAttackSpeed, boolean displayEfficiency) {
        super(tier, builder);
        this.effectiveBlocks = effectiveBlocksIn;
        this.efficiency = tier.getSpeed();
        this.attackDamage = attackDamageIn + tier.getAttackDamageBonus();
        this.attackSpeed = attackSpeedIn;
        this.displayAttackDamage = displayAttackDamage;
        this.displayAttackSpeed = displayAttackSpeed;
        this.displayEfficiency = displayEfficiency;
    }

    @Override
    public float getDestroySpeed(@Nonnull ItemStack stack, BlockState state) {
        /*if (getToolTypes(stack).stream().anyMatch(state::isToolEffective)) {
            return efficiency + getAdditionalEfficiency(stack);
        }*/

        return this.effectiveBlocks.contains(state.getBlock()) ? this.efficiency + getAdditionalEfficiency(stack) : 1.0F;
    }

    /**
     * Current implementations of this method in child classes do not use the entry argument beside ev. They just raise
     * the damage on the stack.
     */
    @Override
    public boolean hurtEnemy(ItemStack stack, @Nonnull LivingEntity target, @Nonnull LivingEntity attacker) {
        stack.hurtAndBreak(2, attacker, (livingEntity) -> livingEntity.broadcastBreakEvent(EquipmentSlot.MAINHAND));
        return true;
    }

    /**
     * Called when a Block is destroyed using this Item. Return true to trigger the "Use Item" statistic.
     */
    @Override
    public boolean mineBlock(@Nonnull ItemStack stack, Level worldIn, @Nonnull BlockState state, @Nonnull BlockPos pos, @Nonnull LivingEntity entityLiving) {
        if (!worldIn.isClientSide && state.getDestroySpeed(worldIn, pos) != 0.0F) {
            stack.hurtAndBreak(1, entityLiving, (livingEntity) -> livingEntity.broadcastBreakEvent(EquipmentSlot.MAINHAND));
        }

        return true;
    }

    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(EquipmentSlot slot, ItemStack stack) {
        if (slot == EquipmentSlot.MAINHAND) {
            ImmutableMultimap.Builder<Attribute, AttributeModifier> attributeModifierBuilder = ImmutableMultimap.builder();
            attributeModifierBuilder.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(BASE_ATTACK_DAMAGE_UUID,
                    "Tool modifier", this.attackDamage + getAdditionalAttackDamage(stack), AttributeModifier.Operation.ADDITION));
            attributeModifierBuilder.put(Attributes.ATTACK_SPEED, new AttributeModifier(BASE_ATTACK_SPEED_UUID,
                    "Tool modifier", this.attackSpeed + getAdditionalAttackSpeed(stack), AttributeModifier.Operation.ADDITION));
            return attributeModifierBuilder.build();
        } else {
            return super.getAttributeModifiers(slot, stack);
        }
    }

    @Override
    public void appendHoverText(@Nonnull ItemStack stack, @Nullable Level worldIn, @Nonnull List<Component> tooltip, @Nonnull TooltipFlag flagIn) {
        super.appendHoverText(stack, worldIn, tooltip, flagIn);

        if (stack.getItem() instanceof AgesToolItem) {
            if (displayAttackDamage) {
                float attackDamage = getAdditionalAttackDamage(stack);

                if (Math.abs(attackDamage) > 0.01) {
                    tooltip.add(Component.literal("Attack damage: ").withStyle(ChatFormatting.DARK_GREEN)
                            .append(Component.literal(String.format("%.2f", attackDamage))
                                    .withStyle(attackDamage >= 0 ? ChatFormatting.GREEN : ChatFormatting.RED)));
                }
            }

            if (displayAttackSpeed) {
                float attackSpeed = getAdditionalAttackSpeed(stack);

                if (Math.abs(attackSpeed) > 0.01) {
                    tooltip.add(Component.literal("Attack speed: ").withStyle(ChatFormatting.DARK_GREEN)
                            .append(Component.literal(String.format("%.2f", attackSpeed))
                                    .withStyle(attackSpeed >= 0 ? ChatFormatting.GREEN : ChatFormatting.RED)));
                }
            }

            if (displayEfficiency) {
                float efficiency = getAdditionalEfficiency(stack);

                if (Math.abs(efficiency) > 0.01) {
                    tooltip.add(Component.literal("Efficiency: ").withStyle(ChatFormatting.DARK_GREEN)
                            .append(Component.literal(String.format("%.2f", efficiency))
                                    .withStyle(efficiency >= 0 ? ChatFormatting.GREEN : ChatFormatting.RED)));
                }
            }
        }
    }

    public static float getAdditionalAttackDamage(ItemStack itemStack) {
        CompoundTag tag = itemStack.getOrCreateTagElement("additionalModifiers");
        return tag.getFloat("attackDamage");
    }

    public static float getAdditionalAttackSpeed(ItemStack itemStack) {
        CompoundTag tag = itemStack.getOrCreateTagElement("additionalModifiers");
        return tag.getFloat("attackSpeed");
    }

    public static float getAdditionalEfficiency(ItemStack itemStack) {
        CompoundTag tag = itemStack.getOrCreateTagElement("additionalModifiers");
        return tag.getFloat("efficiency");
    }

    public static void setAdditionalModifiers(ItemStack itemStack, float attackDamage, float attackSpeed, float efficiency) {
        CompoundTag tag = itemStack.getOrCreateTagElement("additionalModifiers");
        tag.putFloat("attackDamage", attackDamage);
        tag.putFloat("attackSpeed", attackSpeed);
        tag.putFloat("efficiency", efficiency);
    }

    @SuppressWarnings("unchecked")
    public static <T, E> T getPrivateValue(Class <? super E > classToAccess, @Nullable E instance, int fieldIndex)
    {
        try
        {
            Field f = classToAccess.getDeclaredFields()[fieldIndex];
            f.setAccessible(true);
            return (T) f.get(instance);
        }
        catch (Exception e)
        {
            Throwables.throwIfUnchecked(e);
            throw new RuntimeException(e);
        }
    }
}
