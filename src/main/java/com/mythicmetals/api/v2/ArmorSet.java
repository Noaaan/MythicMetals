package com.mythicmetals.api.v2;

import com.mythicmetals.MythicAttributeModifier;
import com.mythicmetals.MythicMetals;
import com.mythicmetals.item.MythicItemAttributes;
import com.mythicmetals.misc.RegistryHelper;
import com.mythicmetals.misc.StringUtilsAtHome;
import net.minecraft.core.component.DataComponents;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
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
    public final ResourceKey<Item> horseKey;
    public final ResourceKey<Item> nautilusKey;
    private final ArmorMaterial armorMaterial;
    private final String name;

    protected Item helmet;
    protected Item chestplate;
    protected Item leggings;
    protected Item boots;
    protected Item horse;
    protected Item nautilus;

    public ArmorSet(String name, ArmorMaterial armorMaterial) {
        this.name = name;
        this.helmetKey = itemKey(name + "_helmet");
        this.chestplateKey = itemKey(name + "_chestplate");
        this.leggingsKey = itemKey(name + "_leggings");
        this.bootsKey = itemKey(name + "_boots");
        this.horseKey = itemKey(name + "_horse_armor");
        this.nautilusKey = itemKey(name + "_nautilus_armor");
        this.armorMaterial = armorMaterial;
    }

    public ArmorSet createDefault() {
        return createDefault(List.of());
    }

    public ArmorSet createDefault(List<MythicAttributeModifier> extraModifiers) {
        this.helmet = RegistryHelper.item(
            helmetKey,
            baseItem(helmetKey, armorMaterial, ArmorType.HELMET, settings -> {}, extraModifiers)
        );
        this.chestplate = RegistryHelper.item(chestplateKey, baseItem(chestplateKey, armorMaterial, ArmorType.CHESTPLATE, settings -> {}, extraModifiers));
        this.leggings = RegistryHelper.item(leggingsKey, baseItem(leggingsKey, armorMaterial, ArmorType.LEGGINGS, settings -> {}, extraModifiers));
        this.boots = RegistryHelper.item(bootsKey, baseItem(bootsKey, armorMaterial, ArmorType.BOOTS, settings -> {}, extraModifiers));
        // TODO - Apply extra modifiers to both horse and naut armor
        this.horse = RegistryHelper.item(horseKey, new Item(
            new Item.Properties()
                .horseArmor(armorMaterial)
                .setId(horseKey)
        ));
        this.nautilus = RegistryHelper.item(nautilusKey, new Item(
            new Item.Properties()
                .nautilusArmor(armorMaterial)
                .setId(nautilusKey)
        ));
        return this;
    }

    public Item baseItem(ResourceKey<Item> key, ArmorMaterial material, ArmorType equipmentType, Consumer<Item.Properties> settingsProcessor) {
        return baseItem(key, material, equipmentType, settingsProcessor, List.of());
    }

    public Item baseItem(ResourceKey<Item> key, ArmorMaterial material, ArmorType equipmentType, Consumer<Item.Properties> settingsConsumer, List<MythicAttributeModifier> extraModifiers) {
        var settings = baseArmorSettings(key, material, equipmentType, extraModifiers);
        settingsConsumer.accept(settings);
        return this.makeItem(equipmentType, settings);
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

    ///
    /// Override this if needed, for example for custom armor sets to implement different item classes
    ///
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

    public Item getHorse() {
        return horse;
    }

    public Item getNautilus() {
        return nautilus;
    }

    public ArmorMaterial getArmorMaterial() {
        return armorMaterial;
    }

    public String getName() {
        return name;
    }

    public boolean isInArmorSet(Item armorItem) {
        return
            armorItem.equals(helmet) ||
                armorItem.equals(chestplate) ||
                armorItem.equals(leggings) ||
                armorItem.equals(boots);
    }

    public boolean isInArmorSet(ItemStack armorStack) {
        var item = armorStack.getItem();
        return
            item.equals(helmet) ||
            item.equals(chestplate) ||
            item.equals(leggings) ||
            item.equals(boots);
    }

    ///
    /// Get items usually equipped by a player, which is the Helmet, Chestplate, Leggings, and Boots, in that order.
    ///
    public List<Item> getPlayerItems() {
        return List.of(helmet, chestplate, leggings, boots);
    }

    public String getTitlecaseName() {
        return StringUtilsAtHome.toTitleCase(this.name);
    }

    public List<Item> getItems() {
        return List.of(helmet, chestplate, leggings, boots, horse, nautilus);
    }
}
