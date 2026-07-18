package com.mythicmetals.item;

import com.mythicmetals.api.v2.ToolSet;
import com.mythicmetals.misc.RegistryHelper;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ToolMaterial;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.item.equipment.ArmorMaterial;
import net.minecraft.world.item.equipment.ArmorType;

import java.util.List;
import java.util.Locale;

public class MythicItemAttributes {

    private MythicItemAttributes() {}

    private static final String UNKNOWN = "unknown";
    protected static final float SWORD_BASE_DAMAGE = 3.0f;
    protected static final float AXE_BASE_DAMAGE = 5.0f;
    protected static final float PICKAXE_BASE_DAMAGE = 2.0f;
    protected static final float SHOVEL_BASE_DAMAGE = 1.0f;
    protected static final float HOE_BASE_DAMAGE = 0.0f;

    public static ItemAttributeModifiers createArmorModifier(String armorName, ArmorMaterial material, ArmorType type, List<MythicAttributeModifier> extraAttributes) {
        var baseAttributes = material.createAttributes(type);
        if (extraAttributes.isEmpty()) return baseAttributes;
        var attributeBuilder = ItemAttributeModifiers.builder();
        var attributes = baseAttributes.modifiers();
        attributes.forEach(entry -> attributeBuilder.add(entry.attribute(), entry.modifier(), entry.slot(), entry.display()));

        for (var extraModifier : extraAttributes) {
            if (extraModifier.requiredSlot().test(type.getSlot())) {
                var attributeName = extraModifier
                    .attribute()
                    .unwrapKey()
                    .map(key -> key.identifier().getPath() + "_" + type.getSlot().getName())
                    .orElse(UNKNOWN)
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

    public static ItemAttributeModifiers createToolModifier(String materialName, ToolType type, ToolSet.AttackSpeeds attackSpeeds, ToolMaterial material, List<MythicAttributeModifier> extraAttributes) {
        var attributeBuilder = ItemAttributeModifiers.builder();
        float baseToolDamage = switch (type) {
            case SWORD -> SWORD_BASE_DAMAGE;
            case AXE -> AXE_BASE_DAMAGE;
            case PICKAXE -> PICKAXE_BASE_DAMAGE;
            case SHOVEL -> SHOVEL_BASE_DAMAGE;
            case HOE -> HOE_BASE_DAMAGE;
        };
        float baseAttackSpeed = switch (type) {
            case SWORD -> attackSpeeds.sword;
            case AXE -> attackSpeeds.axe;
            case PICKAXE -> attackSpeeds.pickaxe;
            case SHOVEL -> attackSpeeds.shovel;
            case HOE -> attackSpeeds.hoe;
        } - 4.0f;

        attributeBuilder
            .add(
                Attributes.ATTACK_DAMAGE,
                new AttributeModifier(Item.BASE_ATTACK_DAMAGE_ID, baseToolDamage + material.attackDamageBonus(), AttributeModifier.Operation.ADD_VALUE),
                EquipmentSlotGroup.MAINHAND
            )
            .add(Attributes.ATTACK_SPEED, new AttributeModifier(Item.BASE_ATTACK_SPEED_ID, baseAttackSpeed, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.MAINHAND);
        if (extraAttributes.isEmpty()) return attributeBuilder.build();

        for (var extraModifier : extraAttributes) {
            if (extraModifier.requiredSlot().test(EquipmentSlot.MAINHAND)) {
                var attributeName = extraModifier
                    .attribute()
                    .unwrapKey()
                    .map(key -> key.identifier().getPath())
                    .orElse(UNKNOWN)
                    .toLowerCase(Locale.ROOT);
                attributeBuilder.add(
                    extraModifier.attribute(),
                    new AttributeModifier(
                        RegistryHelper.id(materialName + "_" + attributeName),
                        extraModifier.value(),
                        extraModifier.operation()
                    ),
                    EquipmentSlotGroup.MAINHAND
                );
            }

            if (extraModifier.requiredSlot().test(EquipmentSlot.OFFHAND)) {
                var attributeName = extraModifier
                    .attribute()
                    .unwrapKey()
                    .map(key -> key.identifier().getPath())
                    .orElse(UNKNOWN)
                    .toLowerCase(Locale.ROOT);
                attributeBuilder.add(
                    extraModifier.attribute(),
                    new AttributeModifier(
                        RegistryHelper.id(materialName + "_" + attributeName),
                        extraModifier.value(),
                        extraModifier.operation()
                    ),
                    EquipmentSlotGroup.OFFHAND
                );
            }
        }
        return attributeBuilder.build();
    }

    public enum ToolType {
        SWORD,
        AXE,
        PICKAXE,
        SHOVEL,
        HOE
    }
}
