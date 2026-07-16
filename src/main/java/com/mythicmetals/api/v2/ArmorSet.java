package com.mythicmetals.api.v2;

import com.mythicmetals.item.MythicAttributeModifier;
import com.mythicmetals.MythicMetals;
import com.mythicmetals.item.MythicItemAttributes;
import com.mythicmetals.misc.RegistryHelper;
import com.mythicmetals.misc.StringUtilsAtHome;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.equipment.ArmorMaterial;
import net.minecraft.world.item.equipment.ArmorType;
import net.minecraft.world.item.equipment.Equippable;
import org.jspecify.annotations.Nullable;

import java.util.List;
import java.util.Map;
import java.util.function.UnaryOperator;

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
    private static final UnaryOperator<Item.Properties> NONE = UnaryOperator.identity();

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

    public ArmorSet initialize(UnaryOperator<Item.Properties> customProperties) {
        return initialize(customProperties, List.of(), true);
    }

    public ArmorSet initialize() {
        return initialize(NONE, List.of(), true);
    }

    public ArmorSet initialize(List<MythicAttributeModifier> extraModifiers) {
        return initialize(NONE, extraModifiers, true);
    }

    public ArmorSet initialize(boolean initializeMountArmor, List<MythicAttributeModifier> extraModifiers) {
        return initialize(NONE, extraModifiers, initializeMountArmor);
    }

    public ArmorSet initialize(UnaryOperator<Item.Properties> customProperties, List<MythicAttributeModifier> extraModifiers, boolean initMountArmor) {
        this.helmet = RegistryHelper.item(
            helmetKey,
            baseItem(helmetKey, armorMaterial, ArmorType.HELMET, customProperties, extraModifiers)
        );
        this.chestplate = RegistryHelper.item(chestplateKey, baseItem(chestplateKey, armorMaterial, ArmorType.CHESTPLATE, customProperties, extraModifiers));
        this.leggings = RegistryHelper.item(leggingsKey, baseItem(leggingsKey, armorMaterial, ArmorType.LEGGINGS, customProperties, extraModifiers));
        this.boots = RegistryHelper.item(bootsKey, baseItem(bootsKey, armorMaterial, ArmorType.BOOTS, customProperties, extraModifiers));
        if (initMountArmor) {
            this.horse = RegistryHelper.item(horseKey, baseHorseItem(horseKey, armorMaterial, customProperties, extraModifiers));
            this.nautilus = RegistryHelper.item(nautilusKey, baseNautilusItem(nautilusKey, armorMaterial, customProperties, extraModifiers));
        }
        return this;
    }

    public Item baseItem(ResourceKey<Item> key, ArmorMaterial material, ArmorType armorType, UnaryOperator<Item.Properties> settingsConsumer, List<MythicAttributeModifier> extraModifiers) {
        var settings = baseArmorSettings(key, material, armorType, extraModifiers);
        settings = armor(material, armorType, settings);
        settings = settingsConsumer.apply(settings);
        return this.makeItem(armorType, settings);
    }

    public Item baseHorseItem(ResourceKey<Item> key, ArmorMaterial material, UnaryOperator<Item.Properties> settingsConsumer, List<MythicAttributeModifier> extraModifiers) {
        var settings = baseArmorSettings(key, material, ArmorType.BODY, extraModifiers);
        settings = horseArmor(material, settings);
        settings = settingsConsumer.apply(settings);
        return this.makeItem(ArmorType.BODY, settings);
    }

    public Item baseNautilusItem(ResourceKey<Item> key, ArmorMaterial material, UnaryOperator<Item.Properties> settingsConsumer, List<MythicAttributeModifier> extraModifiers) {
        var settings = baseArmorSettings(key, material, ArmorType.BODY, extraModifiers);
        settings = nautilusArmor(material, settings);
        settings = settingsConsumer.apply(settings);
        return this.makeItem(ArmorType.BODY, settings);
    }

    ///
    /// I declare my own armor settings for two reasons:
    ///
    /// - To set the correct creative group and tab
    /// - To construct the attributes on my own
    ///
    /// The latter is important, since any armor item can have new attributes as defined by the list of [MythicAttributeModifier]s.
    ///
    public Item.Properties baseArmorSettings(ResourceKey<Item> key, ArmorMaterial material, ArmorType armorType, List<MythicAttributeModifier> extraModifiers) {
        return new Item.Properties()
            .group(MythicMetals.TABBED_GROUP)
            .tab(3)
            .setId(key)
            .attributes(MythicItemAttributes.createArmorModifier(this.name, material, armorType, extraModifiers))
            .enchantable(material.enchantmentValue());
    }

    public Item.Properties armor(ArmorMaterial material, ArmorType armorType, Item.Properties props) {
        return props
            .component(DataComponents.EQUIPPABLE, Equippable
                .builder(armorType.getSlot())
                .setAsset(material.assetId())
                .setEquipSound(material.equipSound())
                .build())
            .durability(BASE_DURABILITY.get(armorType) * material.durability())
            .repairable(material.repairIngredient()
            );
    }

    public Item.Properties horseArmor(ArmorMaterial armorMaterial, Item.Properties props) {
        HolderGetter<EntityType<?>> holderGetter = BuiltInRegistries.acquireBootstrapRegistrationLookup(BuiltInRegistries.ENTITY_TYPE);
        return props
            .component(
                DataComponents.EQUIPPABLE,
                Equippable.builder(EquipmentSlot.BODY)
                    .setEquipSound(SoundEvents.HORSE_ARMOR)
                    .setAsset(armorMaterial.assetId())
                    .setAllowedEntities(holderGetter.getOrThrow(EntityTypeTags.CAN_WEAR_HORSE_ARMOR))
                    .setDamageOnHurt(false)
                    .setCanBeSheared(true)
                    .setShearingSound(SoundEvents.HORSE_ARMOR_UNEQUIP)
                    .build()
            )
            .stacksTo(1);
    }

    public Item.Properties nautilusArmor(ArmorMaterial armorMaterial, Item.Properties props) {
        HolderGetter<EntityType<?>> holderGetter = BuiltInRegistries.acquireBootstrapRegistrationLookup(BuiltInRegistries.ENTITY_TYPE);
        return props
            .component(
                DataComponents.EQUIPPABLE,
                Equippable.builder(EquipmentSlot.BODY)
                    .setEquipSound(SoundEvents.ARMOR_EQUIP_NAUTILUS)
                    .setAsset(armorMaterial.assetId())
                    .setAllowedEntities(holderGetter.getOrThrow(EntityTypeTags.CAN_WEAR_NAUTILUS_ARMOR))
                    .setDamageOnHurt(false)
                    .setEquipOnInteract(true)
                    .setCanBeSheared(true)
                    .setShearingSound(SoundEvents.ARMOR_UNEQUIP_NAUTILUS)
                    .build()
            )
            .stacksTo(1);
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

    @Nullable
    public Item getHorse() {
        return horse;
    }

    @Nullable
    public Item getNautilus() {
        return nautilus;
    }

    public ArmorMaterial getArmorMaterial() {
        return armorMaterial;
    }

    public String getName() {
        return name;
    }

    public boolean isInArmorSet(Item item) {
        return
            item.equals(helmet) ||
                item.equals(chestplate) ||
                item.equals(leggings) ||
                item.equals(boots) ||
                item.equals(horse) ||
                item.equals(nautilus);
    }

    public boolean isInArmorSet(ItemStack armorStack) {
        return isInArmorSet(armorStack.getItem());
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
