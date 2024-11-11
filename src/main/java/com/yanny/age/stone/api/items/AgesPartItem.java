package com.yanny.age.stone.api.items;

import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.network.chat.Component;
import net.minecraft.ChatFormatting;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;
import java.util.List;

public class AgesPartItem extends Item {

    private IAdditionalProperties additionalProperties;

    public AgesPartItem(Properties properties, IAdditionalProperties additionalProperties) {
        super(properties);
        this.additionalProperties = additionalProperties;
    }

    public void applyStats(ItemStack itemStack) {
        AgesToolItem.setAdditionalModifiers(itemStack, additionalProperties.getAttackDamage(), additionalProperties.getAttackSpeed(), additionalProperties.getEfficiency());
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
        super.appendHoverText(stack, worldIn, tooltip, flagIn);

        if (stack.getItem() instanceof AgesPartItem) {
            float attackDamage = AgesToolItem.getAdditionalAttackDamage(stack);
            float attackSpeed = AgesToolItem.getAdditionalAttackSpeed(stack);
            float efficiency = AgesToolItem.getAdditionalEfficiency(stack);

            tooltip.add(Component.literal("Attack damage: ").withStyle(ChatFormatting.DARK_GREEN)
                    .append(Component.literal(String.format("%.2f", attackDamage))
                            .withStyle(attackDamage >= 0 ? ChatFormatting.GREEN : ChatFormatting.RED)));
            tooltip.add(Component.literal("Attack speed: ").withStyle(ChatFormatting.DARK_GREEN)
                    .append(Component.literal(String.format("%.2f", attackSpeed))
                            .withStyle(attackSpeed >= 0 ? ChatFormatting.GREEN : ChatFormatting.RED)));
            tooltip.add(Component.literal("Efficiency: ").withStyle(ChatFormatting.DARK_GREEN)
                    .append(Component.literal(String.format("%.2f", efficiency))
                            .withStyle(efficiency >= 0 ? ChatFormatting.GREEN : ChatFormatting.RED)));
        }
    }
}
