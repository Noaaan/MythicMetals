package com.mythicmetals.armor;

import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;
import com.mythicmetals.MythicMetals;
import com.mythicmetals.misc.RegistryHelper;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class ArmorSet {

    private final ArmorItem helmet;
    private final ArmorItem chestplate;
    private final ArmorItem leggings;
    private final ArmorItem boots;

    private final List<ArmorItem> armorSet;

    private static final Map<ArmorItem.Type, Integer> BASE_DURABILITY = Map.of(
        ArmorItem.Type.HELMET, 12,
        ArmorItem.Type.CHESTPLATE, 16,
        ArmorItem.Type.LEGGINGS, 15,
        ArmorItem.Type.BOOTS, 13
    );

    public ArmorItem baseArmorItem(ArmorMaterial material, ArmorItem.Type slot, int durabilityModifier, Consumer<Item.Settings> settingsProcessor) {
        final var settings = new Item.Settings()
            .group(MythicMetals.TABBED_GROUP)
            .tab(3)
            .maxDamage(BASE_DURABILITY.get(slot) * durabilityModifier);
        settingsProcessor.accept(settings);
        return this.makeItem(material, slot, settings);
    }

    public ArmorSet(ArmorMaterial material, int durabilityModifier) {
        this(material, durabilityModifier, settings -> {
        });
    }

    public ArmorSet(ArmorMaterial material, int durabilityModifier, Consumer<Item.Settings> settingsProcessor) {
        this.helmet = baseArmorItem(material, ArmorItem.Type.HELMET, durabilityModifier, settingsProcessor);
        this.chestplate = baseArmorItem(material, ArmorItem.Type.CHESTPLATE, durabilityModifier, settingsProcessor);
        this.leggings = baseArmorItem(material, ArmorItem.Type.LEGGINGS, durabilityModifier, settingsProcessor);
        this.boots = baseArmorItem(material, ArmorItem.Type.BOOTS, durabilityModifier, settingsProcessor);
        this.armorSet = List.of(helmet, chestplate, leggings, boots);
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

    protected ArmorItem makeItem(ArmorMaterial material, ArmorItem.Type slot, Item.Settings settings) {
        return new ArmorItem(getEntry(material), slot, settings);
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

    public List<ArmorItem> getArmorItems() {
        return armorSet;
    }

    public boolean isInArmorSet(ItemStack stack) {
        return this.getArmorItems().contains(stack.getItem());
    }

    public RegistryEntry<ArmorMaterial> getEntry(ArmorMaterial material) {
        return Registries.ARMOR_MATERIAL.getEntry(material);
    }
}
