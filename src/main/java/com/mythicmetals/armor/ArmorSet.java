package com.mythicmetals.armor;

import com.mythicmetals.MythicMetals;
import com.mythicmetals.misc.RegistryHelper;
import com.mythicmetals.misc.StringUtilsAtHome;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.*;
import net.minecraft.entity.attribute.*;
import net.minecraft.item.*;
import net.minecraft.item.equipment.ArmorMaterial;
import net.minecraft.item.equipment.EquipmentType;
import net.minecraft.registry.*;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

import static net.minecraft.entity.attribute.EntityAttributeModifier.Operation.ADD_VALUE;

public class ArmorSet {

    private final String name;
    private final Item helmet;
    private final Item chestplate;
    private final Item leggings;
    private final Item boots;

    private final List<Item> armorItems;

    private final ArmorMaterial material;

    private static final Map<EquipmentType, Integer> BASE_DURABILITY = Map.of(
        EquipmentType.HELMET, 12,
        EquipmentType.CHESTPLATE, 16,
        EquipmentType.LEGGINGS, 15,
        EquipmentType.BOOTS, 13
    );

    public Item baseItem(ArmorMaterial material, EquipmentType equipmentType, Consumer<Item.Settings> settingsProcessor) {
        return baseItem(material, equipmentType, settingsProcessor, List.of());
    }

    public Item baseItem(ArmorMaterial material, EquipmentType equipmentType, Consumer<Item.Settings> settingsConsumer, List<AttributeModifier> extraModifiers) {
        var settings = baseArmorSettings(name, material, equipmentType, extraModifiers);
        settingsConsumer.accept(settings);
        return this.makeItem(material, equipmentType, settings);
    }

    public static Item.Settings baseArmorSettings(String name, ArmorMaterial material, EquipmentType equipmentType, List<AttributeModifier> extraModifiers) {
        return new Item.Settings()
            .group(MythicMetals.TABBED_GROUP)
            .tab(3)
            .registryKey(keyFromType(name, equipmentType))
            .attributeModifiers(createAttributeModifiers(name, material, equipmentType, extraModifiers))
            .component(DataComponentTypes.EQUIPPABLE, EquippableComponent
                .builder(equipmentType.getEquipmentSlot())
                .model(material.assetId())
                .equipSound(material.equipSound())
                .build()
            )
            .repairable(material.repairIngredient())
            .maxDamage(BASE_DURABILITY.get(equipmentType) * material.durability());
    }

    public ArmorSet(String name, ArmorMaterial material) {
        this(name, material, settings -> {
        });
    }

    public ArmorSet(String name, ArmorMaterial material, List<AttributeModifier> extraModifiers) {
        this(name, material, extraModifiers, settings -> {
        });
    }

    public ArmorSet(String name, ArmorMaterial material, Consumer<Item.Settings> settingsProcessor) {
        this.name = name;
        this.material = material;
        this.helmet = baseItem(material, EquipmentType.HELMET, settingsProcessor);
        this.chestplate = baseItem(material, EquipmentType.CHESTPLATE, settingsProcessor);
        this.leggings = baseItem(material, EquipmentType.LEGGINGS, settingsProcessor);
        this.boots = baseItem(material, EquipmentType.BOOTS, settingsProcessor);
        this.armorItems = List.of(helmet, chestplate, leggings, boots);
    }

    public ArmorSet(String name, ArmorMaterial material, List<AttributeModifier> extraModifiers, Consumer<Item.Settings> settingsProcessor) {
        this.name = name;
        this.material = material;
        this.helmet = baseItem(material, EquipmentType.HELMET, settingsProcessor, extraModifiers);
        this.chestplate = baseItem(material, EquipmentType.CHESTPLATE, settingsProcessor, extraModifiers);
        this.leggings = baseItem(material, EquipmentType.LEGGINGS, settingsProcessor, extraModifiers);
        this.boots = baseItem(material, EquipmentType.BOOTS, settingsProcessor, extraModifiers);
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

    protected Item makeItem(ArmorMaterial material, EquipmentType slot, Item.Settings settings) {
        return new Item(settings);
    }

    public Item getHelmet() {
        return helmet;
    }

    public Item getChestplate() {
        return chestplate;
    }

    public Item getLeggings() {
        return leggings;
    }

    public Item getBoots() {
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

    private static AttributeModifiersComponent createAttributeModifiers(String name, ArmorMaterial material, EquipmentType equipmentType, List<AttributeModifier> extraModifiers) {
        int armor = material.defense().getOrDefault(equipmentType, 0);
        double toughness = material.toughness();
        double knockbackResistance = material.knockbackResistance();
        var builder = AttributeModifiersComponent.builder();
        var equipmentSlot = AttributeModifierSlot.forEquipmentSlot(equipmentType.getEquipmentSlot());
        var identifier = Identifier.ofVanilla("armor." + equipmentType.getName());
        builder.add(
            EntityAttributes.ARMOR,
            new EntityAttributeModifier(identifier, armor, ADD_VALUE),
            equipmentSlot
        );
        builder.add(
            EntityAttributes.ARMOR_TOUGHNESS,
            new EntityAttributeModifier(identifier, toughness, ADD_VALUE),
            equipmentSlot
        );
        if (knockbackResistance > 0.0F) {
            builder.add(
                EntityAttributes.KNOCKBACK_RESISTANCE,
                new EntityAttributeModifier(identifier, knockbackResistance, ADD_VALUE),
                equipmentSlot
            );
        }
        extraModifiers.forEach(modifier -> {
            if (modifier.requiredSlot.matches(equipmentType.getEquipmentSlot())) {
                var id = RegistryHelper.id(name + "_" + modifier.attribute().getKey().orElseThrow().getValue().getPath());
                builder.add(
                    modifier.attribute,
                    new EntityAttributeModifier(id, modifier.value, modifier.operation),
                    equipmentSlot
                );
            }
        });

        return builder.build();
    }

    public ArmorMaterial getMaterial() {
        return material;
    }

    private static RegistryKey<Item> keyFromType(String name, EquipmentType type) {
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

    public record AttributeModifier(
        RegistryEntry<EntityAttribute> attribute,
        double value,
        EntityAttributeModifier.Operation operation,
        AttributeModifierSlot requiredSlot
    ) {

    }
}
