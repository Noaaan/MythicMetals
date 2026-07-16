package com.mythicmetals.item;

import net.minecraft.core.Holder;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier.Operation;

public record MythicAttributeModifier(
    Holder<Attribute> attribute,
    double value,
    Operation operation,
    EquipmentSlotGroup requiredSlot
) {
}
