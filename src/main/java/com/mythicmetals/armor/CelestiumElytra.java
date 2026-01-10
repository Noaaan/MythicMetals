package com.mythicmetals.armor;

import com.mythicmetals.entity.MythicEntityAttributes;
import com.mythicmetals.misc.RegistryHelper;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.item.component.ItemAttributeModifiers.Builder;

public class CelestiumElytra extends Item {
    public CelestiumElytra(Properties settings) {
        super(settings);
    }

    public static boolean isWearing(LivingEntity entity) {
        var stack = entity.getItemBySlot(EquipmentSlot.CHEST);
        return stack.is(MythicArmor.CELESTIUM_ELYTRA);
    }

    public static ItemAttributeModifiers createDefaultAttributes() {
        var builder = ItemAttributeModifiers.builder();
        var armor = new AttributeModifier(RegistryHelper.id("celestium_elytra_armor_protection"), 5.0F, AttributeModifier.Operation.ADD_VALUE);
        var toughness = new AttributeModifier(RegistryHelper.id("celestium_elytra_armor_toughness"), 3.0F, AttributeModifier.Operation.ADD_VALUE);
        var speed = new AttributeModifier(RegistryHelper.id("celestium_elytra_speed_bonus"), 0.08F, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL);
        var rocketSpeedBonus = new AttributeModifier(RegistryHelper.id("celestium_elytra_rocket_speed_bonus"), 0.20F, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL);
        builder.add(Attributes.ARMOR, armor, EquipmentSlotGroup.CHEST);
        builder.add(Attributes.ARMOR_TOUGHNESS, toughness, EquipmentSlotGroup.CHEST);
        builder.add(Attributes.MOVEMENT_SPEED, speed, EquipmentSlotGroup.CHEST);
        builder.add(MythicEntityAttributes.ELYTRA_ROCKET_SPEED, rocketSpeedBonus, EquipmentSlotGroup.CHEST);
        return builder.build();
    }
}
