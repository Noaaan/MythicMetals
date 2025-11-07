package com.mythicmetals;

import net.minecraft.component.type.AttributeModifierSlot;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.registry.entry.RegistryEntry;

public record AttributeModifier(
    RegistryEntry<EntityAttribute> attribute,
    double value,
    EntityAttributeModifier.Operation operation,
    AttributeModifierSlot requiredSlot
) {

}
