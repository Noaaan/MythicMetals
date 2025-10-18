package com.mythicmetals.armor;

import com.mythicmetals.MythicMetals;
import com.mythicmetals.misc.RegistryHelper;
import com.mythicmetals.misc.StringUtilsAtHome;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.*;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.item.*;
import net.minecraft.item.equipment.ArmorMaterial;
import net.minecraft.item.equipment.EquipmentType;
import net.minecraft.registry.*;
import net.minecraft.util.Identifier;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class ArmorSet {

    private final String name;
    private final ArmorItem helmet;
    private final ArmorItem chestplate;
    private final ArmorItem leggings;
    private final ArmorItem boots;

    private final List<Item> armorItems;

    private final ArmorMaterial material;

    private static final Map<EquipmentType, Integer> BASE_DURABILITY = Map.of(
        EquipmentType.HELMET, 12,
        EquipmentType.CHESTPLATE, 16,
        EquipmentType.LEGGINGS, 15,
        EquipmentType.BOOTS, 13
    );

    public ArmorItem baseArmorItem(ArmorMaterial material, EquipmentType equipmentType, Consumer<Item.Settings> settingsProcessor) {
        final var settings = new Item.Settings()
            .group(MythicMetals.TABBED_GROUP)
            .tab(3)
            .registryKey(fromType(equipmentType))
            .attributeModifiers(createAttributeModifiers(equipmentType))
            .component(DataComponentTypes.EQUIPPABLE, EquippableComponent
                .builder(equipmentType.getEquipmentSlot())
                .model(material.assetId())
                .equipSound(material.equipSound())
                .build()
            )
            .repairable(material.repairIngredient())
            .maxDamage(BASE_DURABILITY.get(equipmentType) * material.durability());
        settingsProcessor.accept(settings);
        return this.makeItem(material, equipmentType, settings);
    }

    public ArmorSet(String name, ArmorMaterial material) {
        this(name, material, settings -> {
        });
    }

    public ArmorSet(String name, ArmorMaterial material, Consumer<Item.Settings> settingsProcessor) {
        this.name = name;
        this.material = material;
        this.helmet = baseArmorItem(material, EquipmentType.HELMET, settingsProcessor);
        this.chestplate = baseArmorItem(material, EquipmentType.CHESTPLATE, settingsProcessor);
        this.leggings = baseArmorItem(material, EquipmentType.LEGGINGS, settingsProcessor);
        this.boots = baseArmorItem(material, EquipmentType.BOOTS, settingsProcessor);
        this.armorItems = List.of(helmet, chestplate, leggings, boots);
    }

    public void register(String name) {
        Registry.register(Registries.ITEM, RegistryHelper.id(name + "_helmet"), helmet);
        Registry.register(Registries.ITEM, RegistryHelper.id(name + "_chestplate"), chestplate);
        Registry.register(Registries.ITEM, RegistryHelper.id(name + "_leggings"), leggings);
        Registry.register(Registries.ITEM, RegistryHelper.id(name + "_boots"), boots);
    }

    public void register(String modid, String name) {
        Registry.register(Registries.ITEM, Identifier.of(modid, name + "_helmet"), helmet);
        Registry.register(Registries.ITEM, Identifier.of(modid, name + "_chestplate"), chestplate);
        Registry.register(Registries.ITEM, Identifier.of(modid, name + "_leggings"), leggings);
        Registry.register(Registries.ITEM, Identifier.of(modid, name + "_boots"), boots);
    }

    protected ArmorItem makeItem(ArmorMaterial material, EquipmentType slot, Item.Settings settings) {
        return new ArmorItem(material, slot, settings);
    }

    public ArmorItem getHelmet() {
        return helmet;
    }

    public ArmorItem getChestplate() {
        return chestplate;
    }

    public ArmorItem getLeggings() {
        return leggings;
    }

    public ArmorItem getBoots() {
        return boots;
    }

    public static Map<EquipmentType, Integer> getBaseDurability() {
        return BASE_DURABILITY;
    }

    public List<Item> getArmorItems() {
        return armorItems;
    }

    public boolean isInArmorSet(ItemStack stack) {
        return this.getArmorItems().contains(stack.getItem());
    }

    public String getTitlecaseName() {
        return StringUtilsAtHome.toTitleCase(name);
    }

    public String getMaterialId() {
        return MythicArmor.ARMOR_MAP.inverse().get(this);
    }

    private AttributeModifiersComponent createAttributeModifiers(EquipmentType equipmentType) {
        int armor = this.material.defense().getOrDefault(equipmentType, 0);
        double toughness = this.material.toughness();
        double knockbackResistance = this.material.knockbackResistance();
        AttributeModifiersComponent.Builder builder = AttributeModifiersComponent.builder();
        AttributeModifierSlot attributeModifierSlot = AttributeModifierSlot.forEquipmentSlot(equipmentType.getEquipmentSlot());
        Identifier identifier = Identifier.ofVanilla("armor." + equipmentType.getName());
        builder.add(EntityAttributes.ARMOR, new EntityAttributeModifier(identifier, armor, EntityAttributeModifier.Operation.ADD_VALUE), attributeModifierSlot);
        builder.add(
            EntityAttributes.ARMOR_TOUGHNESS,
            new EntityAttributeModifier(identifier, toughness, EntityAttributeModifier.Operation.ADD_VALUE),
            attributeModifierSlot
        );
        if (knockbackResistance > 0.0F) {
            builder.add(
                EntityAttributes.KNOCKBACK_RESISTANCE,
                new EntityAttributeModifier(identifier, knockbackResistance, EntityAttributeModifier.Operation.ADD_VALUE),
                attributeModifierSlot
            );
        }

        return builder.build();
    }

    public ArmorMaterial getMaterial() {
        return material;
    }

    private RegistryKey<Item> fromType(EquipmentType type) {
        var typeName = switch (type) {
            case HELMET -> "helmet";
            case CHESTPLATE -> "chestplate";
            case LEGGINGS -> "leggings";
            case BOOTS -> "boots";
            case BODY -> "body";
        };
        return RegistryHelper.itemKey(name + "_" + typeName);
    }

    public String getName() {
        return name;
    }
}
