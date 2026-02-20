package com.mythicmetals.item;

import com.mythicmetals.MythicAttributeModifier;
import com.mythicmetals.misc.RegistryHelper;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.item.equipment.ArmorMaterial;
import net.minecraft.world.item.equipment.ArmorType;

import java.util.List;
import java.util.Locale;

public class MythicItemAttributes {

    private MythicItemAttributes() {}

    public static ItemAttributeModifiers createArmorModifier(String armorName, ArmorMaterial material, ArmorType type, List<MythicAttributeModifier> extraAttributes) {
        var baseAttributes = material.createAttributes(type);
        if (extraAttributes.isEmpty()) return baseAttributes;
        var attributeBuilder = ItemAttributeModifiers.builder();
        var attributes = baseAttributes.modifiers();
        attributes.forEach(entry -> attributeBuilder.add(entry.attribute(), entry.modifier(), entry.slot(), entry.display()));

        for (MythicAttributeModifier extraModifier : extraAttributes) {
            if (extraModifier.requiredSlot().test(type.getSlot())) {
                var attributeName = extraModifier
                    .attribute()
                    .unwrapKey()
                    .map(key -> key.identifier().getPath())
                    .orElse("unknown")
                    .toLowerCase(Locale.ROOT);
                attributeBuilder.add(
                    extraModifier.attribute(),
                    new AttributeModifier(
                        RegistryHelper.id(armorName + "_" + attributeName),
                        extraModifier.value(),
                        extraModifier.operation()
                    ),
                    EquipmentSlotGroup.bySlot(type.getSlot())
                );
            }
        }
        return attributeBuilder.build();
    }
}
