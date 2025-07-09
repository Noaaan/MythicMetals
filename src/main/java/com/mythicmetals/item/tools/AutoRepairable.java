package com.mythicmetals.item.tools;

import net.fabricmc.fabric.api.item.v1.FabricItem;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;

/**
 * Used to handle Auto Repair behavior regarding more gracefully
 */
public interface AutoRepairable extends FabricItem {

    // Don't interrupt mining if durability is repaired
    // Might affect mending as a side effect
    @Override
    default boolean allowContinuingBlockBreaking(PlayerEntity player, ItemStack oldStack, ItemStack newStack) {
        return oldStack.getDamage() != newStack.getDamage();
    }

    // Don't update the item in hand if durability is repaired
    // Might affect mending as a side effect
    @Override
    default boolean allowComponentsUpdateAnimation(PlayerEntity player, Hand hand, ItemStack oldStack, ItemStack newStack) {
        return oldStack.getDamage() == newStack.getDamage();
    }
}
