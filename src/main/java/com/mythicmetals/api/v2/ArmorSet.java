package com.mythicmetals.api.v2;

import com.mythicmetals.MythicAttributeModifier;
import com.mythicmetals.MythicMetals;
import com.mythicmetals.item.MythicItemAttributes;
import com.mythicmetals.misc.RegistryHelper;
import net.minecraft.core.component.DataComponents;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.equipment.ArmorMaterial;
import net.minecraft.world.item.equipment.ArmorType;
import net.minecraft.world.item.equipment.Equippable;

import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

import static com.mythicmetals.misc.RegistryHelper.itemKey;

public class ArmorSet {
    public final ResourceKey<Item> helmetKey;
    public final ResourceKey<Item> chestplateKey;
    public final ResourceKey<Item> leggingsKey;
    public final ResourceKey<Item> bootsKey;
    public final ResourceKey<Item> bodyKey;
    private final ArmorMaterial armorMaterial;
    private final String name;

    protected Item helmet;
    protected Item chestplate;
    protected Item leggings;
    protected Item boots;
    protected Item horse;

    public ArmorSet(String name, ArmorMaterial armorMaterial) {
        this.name = name;
        this.helmetKey = itemKey(name + "_helmet");
        this.chestplateKey = itemKey(name + "_chestplate");
        this.leggingsKey = itemKey(name + "_leggings");
        this.bootsKey = itemKey(name + "_boots");
        this.bodyKey = itemKey(name + "_body");
        this.armorMaterial = armorMaterial;
    }

    public ArmorSet createDefault() {
        this.helmet = RegistryHelper.item(helmetKey, baseItem(helmetKey, armorMaterial, ArmorType.HELMET, settings -> {
        }));
        this.chestplate = RegistryHelper.item(chestplateKey, baseItem(chestplateKey, armorMaterial, ArmorType.CHESTPLATE, settings -> {}));
        this.leggings = RegistryHelper.item(leggingsKey, baseItem(leggingsKey, armorMaterial, ArmorType.LEGGINGS, settings -> {}));
        this.boots = RegistryHelper.item(bootsKey, baseItem(bootsKey, armorMaterial, ArmorType.BOOTS, settings -> {}));
        this.horse = RegistryHelper.item(bodyKey, baseItem(bodyKey, armorMaterial, ArmorType.BODY, settings -> {}));
        this.horse = RegistryHelper.item(bodyKey, new Item(
            new Item.Properties().horseArmor(armorMaterial).setId(bodyKey)
        ));
        return this;
    }

    ///
    /// I declare my own armor settings for two reasons:
    ///
    /// - To set the correct creative group and tab
    /// - To construct the attributes on my own
    ///
    /// The latter is important, since any armor item can have new attributes as defined by the list of [MythicAttributeModifier]s.
    ///
    public Item.Properties baseArmorSettings(ResourceKey<Item> key, ArmorMaterial material, ArmorType equipmentType, List<MythicAttributeModifier> extraModifiers) {
        return new Item.Properties()
            .group(MythicMetals.TABBED_GROUP)
            .tab(3)
            .setId(key)
            .durability(BASE_DURABILITY.get(equipmentType) * material.durability())
            .attributes(MythicItemAttributes.createArmorModifier(this.name, material, equipmentType, extraModifiers))
            .enchantable(material.enchantmentValue())
            .component(DataComponents.EQUIPPABLE, Equippable
                .builder(equipmentType.getSlot())
                .setAsset(material.assetId())
                .setEquipSound(material.equipSound())
                .build()
            )
            .repairable(material.repairIngredient());
    }

    public Item baseItem(ResourceKey<Item> key, ArmorMaterial material, ArmorType equipmentType, Consumer<Item.Properties> settingsProcessor) {
        return baseItem(key, material, equipmentType, settingsProcessor, List.of());
    }

    public Item baseItem(ResourceKey<Item> key, ArmorMaterial material, ArmorType equipmentType, Consumer<Item.Properties> settingsConsumer, List<MythicAttributeModifier> extraModifiers) {
        var settings = baseArmorSettings(key, material, equipmentType, extraModifiers);
        settingsConsumer.accept(settings);
        return this.makeItem(equipmentType, settings);
    }

    protected Item makeItem(ArmorType armorType, Item.Properties settings) {
        return new Item(settings);
    }

    // TODO - Compute from config?
    private static final Map<ArmorType, Integer> BASE_DURABILITY = Map.of(
        ArmorType.HELMET, 12,
        ArmorType.CHESTPLATE, 16,
        ArmorType.LEGGINGS, 15,
        ArmorType.BOOTS, 13
    );
}
