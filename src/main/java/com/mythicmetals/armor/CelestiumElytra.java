package com.mythicmetals.armor;

import com.mythicmetals.entity.MythicEntityAttributes;
import com.mythicmetals.misc.RegistryHelper;
import net.minecraft.component.type.AttributeModifierSlot;
import net.minecraft.component.type.AttributeModifiersComponent;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.item.Item;

public class CelestiumElytra extends Item {
    public CelestiumElytra(Settings settings) {
        super(settings);
    }

    public static boolean isWearing(LivingEntity entity) {
        var stack = entity.getEquippedStack(EquipmentSlot.CHEST);
        return stack.isOf(MythicArmor.CELESTIUM_ELYTRA);
    }

    public static AttributeModifiersComponent createDefaultAttributes() {
        var builder = AttributeModifiersComponent.builder();
        var armor = new EntityAttributeModifier(RegistryHelper.id("celestium_elytra_armor_protection"), 5.0F, EntityAttributeModifier.Operation.ADD_VALUE);
        var toughness = new EntityAttributeModifier(RegistryHelper.id("celestium_elytra_armor_toughness"), 3.0F, EntityAttributeModifier.Operation.ADD_VALUE);
        var speed = new EntityAttributeModifier(RegistryHelper.id("celestium_elytra_speed_bonus"), 0.08F, EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL);
        var rocketSpeedBonus = new EntityAttributeModifier(RegistryHelper.id("celestium_elytra_rocket_speed_bonus"), 0.20F, EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL);
        builder.add(EntityAttributes.ARMOR, armor, AttributeModifierSlot.CHEST);
        builder.add(EntityAttributes.ARMOR_TOUGHNESS, toughness, AttributeModifierSlot.CHEST);
        builder.add(EntityAttributes.MOVEMENT_SPEED, speed, AttributeModifierSlot.CHEST);
        builder.add(MythicEntityAttributes.ELYTRA_ROCKET_SPEED, rocketSpeedBonus, AttributeModifierSlot.CHEST);
        return builder.build();
    }
}
