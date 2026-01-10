package com.mythicmetals.armor;

import com.mythicmetals.MythicAttributeModifier;
import com.mythicmetals.MythicMetals;
import com.mythicmetals.misc.RegistryHelper;
import com.mythicmetals.misc.StringUtilsAtHome;
import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.item.equipment.*;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

import static net.minecraft.world.entity.ai.attributes.AttributeModifier.Operation.ADD_VALUE;

public class ArmorSet {

    private final String name;
    protected final Item helmet;
    protected final Item chestplate;
    protected final Item leggings;
    protected final Item boots;

    private final List<Item> armorItems;

    private final ArmorMaterial material;

    private static final Map<ArmorType, Integer> BASE_DURABILITY = Map.of(
        ArmorType.HELMET, 12,
        ArmorType.CHESTPLATE, 16,
        ArmorType.LEGGINGS, 15,
        ArmorType.BOOTS, 13
    );

    public Item baseItem(ArmorMaterial material, ArmorType equipmentType, Consumer<Item.Properties> settingsProcessor) {
        return baseItem(material, equipmentType, settingsProcessor, List.of());
    }

    public Item baseItem(ArmorMaterial material, ArmorType equipmentType, Consumer<Item.Properties> settingsConsumer, List<MythicAttributeModifier> extraModifiers) {
        var settings = baseArmorSettings(name, material, equipmentType, extraModifiers);
        settingsConsumer.accept(settings);
        return this.makeItem(material, equipmentType, settings);
    }

    public Item.Properties baseArmorSettings(String name, ArmorMaterial material, ArmorType equipmentType, List<MythicAttributeModifier> extraModifiers) {
        return new Item.Properties()
            .group(MythicMetals.TABBED_GROUP)
            .tab(3)
            .setId(keyFromType(name, equipmentType))
            .attributes(createAttributeModifiers(name, material, equipmentType, extraModifiers))
            .component(DataComponents.EQUIPPABLE, Equippable
                .builder(equipmentType.getSlot())
                .setAsset(material.assetId())
                .setEquipSound(material.equipSound())
                .build()
            )
            .repairable(material.repairIngredient())
            .durability(BASE_DURABILITY.get(equipmentType) * material.durability());
    }

    public ArmorSet(String name, ArmorMaterial material) {
        this(name, material, settings -> {
        });
    }

    public ArmorSet(String name, ArmorMaterial material, List<MythicAttributeModifier> extraModifiers) {
        this(name, material, extraModifiers, settings -> {
        });
    }

    public ArmorSet(String name, ArmorMaterial material, Consumer<Item.Properties> settingsProcessor) {
        this.name = name;
        this.material = material;
        this.helmet = baseItem(material, ArmorType.HELMET, settingsProcessor);
        this.chestplate = baseItem(material, ArmorType.CHESTPLATE, settingsProcessor);
        this.leggings = baseItem(material, ArmorType.LEGGINGS, settingsProcessor);
        this.boots = baseItem(material, ArmorType.BOOTS, settingsProcessor);
        this.armorItems = List.of(helmet, chestplate, leggings, boots);
    }

    public ArmorSet(String name, ArmorMaterial material, List<MythicAttributeModifier> extraModifiers, Consumer<Item.Properties> settingsProcessor) {
        this.name = name;
        this.material = material;
        this.helmet = baseItem(material, ArmorType.HELMET, settingsProcessor, extraModifiers);
        this.chestplate = baseItem(material, ArmorType.CHESTPLATE, settingsProcessor, extraModifiers);
        this.leggings = baseItem(material, ArmorType.LEGGINGS, settingsProcessor, extraModifiers);
        this.boots = baseItem(material, ArmorType.BOOTS, settingsProcessor, extraModifiers);
        this.armorItems = List.of(helmet, chestplate, leggings, boots);
    }

    public void register(String name) {
        Registry.register(BuiltInRegistries.ITEM, RegistryHelper.id(name + "_helmet"), helmet);
        Registry.register(BuiltInRegistries.ITEM, RegistryHelper.id(name + "_chestplate"), chestplate);
        Registry.register(BuiltInRegistries.ITEM, RegistryHelper.id(name + "_leggings"), leggings);
        Registry.register(BuiltInRegistries.ITEM, RegistryHelper.id(name + "_boots"), boots);
    }

    public void register(String modid, String name) {
        Registry.register(BuiltInRegistries.ITEM, ResourceLocation.fromNamespaceAndPath(modid, name + "_helmet"), helmet);
        Registry.register(BuiltInRegistries.ITEM, ResourceLocation.fromNamespaceAndPath(modid, name + "_chestplate"), chestplate);
        Registry.register(BuiltInRegistries.ITEM, ResourceLocation.fromNamespaceAndPath(modid, name + "_leggings"), leggings);
        Registry.register(BuiltInRegistries.ITEM, ResourceLocation.fromNamespaceAndPath(modid, name + "_boots"), boots);
    }

    protected Item makeItem(ArmorMaterial material, ArmorType slot, Item.Properties settings) {
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

    public static Map<ArmorType, Integer> getBaseDurability() {
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

    private static ItemAttributeModifiers createAttributeModifiers(String name, ArmorMaterial material, ArmorType equipmentType, List<MythicAttributeModifier> extraModifiers) {
        int armor = material.defense().getOrDefault(equipmentType, 0);
        double toughness = material.toughness();
        double knockbackResistance = material.knockbackResistance();
        var builder = ItemAttributeModifiers.builder();
        var equipmentSlot = EquipmentSlotGroup.bySlot(equipmentType.getSlot());
        var identifier = ResourceLocation.withDefaultNamespace("armor." + equipmentType.getName());
        builder.add(
            Attributes.ARMOR,
            new net.minecraft.world.entity.ai.attributes.AttributeModifier(identifier, armor, ADD_VALUE),
            equipmentSlot
        );
        builder.add(
            Attributes.ARMOR_TOUGHNESS,
            new net.minecraft.world.entity.ai.attributes.AttributeModifier(identifier, toughness, ADD_VALUE),
            equipmentSlot
        );
        if (knockbackResistance > 0.0F) {
            builder.add(
                Attributes.KNOCKBACK_RESISTANCE,
                new net.minecraft.world.entity.ai.attributes.AttributeModifier(identifier, knockbackResistance, ADD_VALUE),
                equipmentSlot
            );
        }
        extraModifiers.forEach(modifier -> {
            if (modifier.requiredSlot().test(equipmentType.getSlot())) {
                var id = RegistryHelper.id(name + "_" + modifier.attribute().unwrapKey().orElseThrow().location().getPath());
                builder.add(
                    modifier.attribute(),
                    new net.minecraft.world.entity.ai.attributes.AttributeModifier(id, modifier.value(), modifier.operation()),
                    equipmentSlot
                );
            }
        });

        return builder.build();
    }

    public ArmorMaterial getMaterial() {
        return material;
    }

    protected ResourceKey<Item> keyFromType(String name, ArmorType type) {
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
