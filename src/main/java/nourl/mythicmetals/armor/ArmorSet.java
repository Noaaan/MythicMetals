package nourl.mythicmetals.armor;

import io.wispforest.owo.itemgroup.OwoItemSettings;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import nourl.mythicmetals.MythicMetals;
import nourl.mythicmetals.misc.RegistryHelper;

import java.util.List;
import java.util.function.Consumer;

public class ArmorSet {

    private final ArmorItem helmet;
    private final ArmorItem chestplate;
    private final ArmorItem leggings;
    private final ArmorItem boots;

    private final List<Item> armorSet;

    public ArmorItem baseArmorItem(ArmorMaterial material, EquipmentSlot slot, Consumer<Item.Settings> settingsProcessor) {
        final var settings = new OwoItemSettings().group(MythicMetals.TABBED_GROUP).tab(3);
        settingsProcessor.accept(settings);
        return this.makeItem(material, slot, settings);
    }

    public ArmorSet(ArmorMaterial material) {
        this(material, settings -> {
        });
    }

    public ArmorSet(ArmorMaterial material, Consumer<Item.Settings> settingsProcessor) {
        this.helmet = baseArmorItem(material, EquipmentSlot.HEAD, settingsProcessor);
        this.chestplate = baseArmorItem(material, EquipmentSlot.CHEST, settingsProcessor);
        this.leggings = baseArmorItem(material, EquipmentSlot.LEGS, settingsProcessor);
        this.boots = baseArmorItem(material, EquipmentSlot.FEET, settingsProcessor);
        this.armorSet = List.of(helmet, chestplate, leggings, boots);
    }

    public void register(String name) {
        Registry.register(Registry.ITEM, RegistryHelper.id(name + "_helmet"), helmet);
        Registry.register(Registry.ITEM, RegistryHelper.id(name + "_chestplate"), chestplate);
        Registry.register(Registry.ITEM, RegistryHelper.id(name + "_leggings"), leggings);
        Registry.register(Registry.ITEM, RegistryHelper.id(name + "_boots"), boots);
    }

    public void register(String modid, String name) {
        Registry.register(Registry.ITEM, new Identifier(modid, name + "_helmet"), helmet);
        Registry.register(Registry.ITEM, new Identifier(modid, name + "_chestplate"), chestplate);
        Registry.register(Registry.ITEM, new Identifier(modid, name + "_leggings"), leggings);
        Registry.register(Registry.ITEM, new Identifier(modid, name + "_boots"), boots);
    }

    protected ArmorItem makeItem(ArmorMaterial material, EquipmentSlot slot, Item.Settings settings) {
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

    public List<Item> getArmorSet() {
        return armorSet;
    }

    public boolean isInArmorSet(ItemStack stack) {
        return this.getArmorSet().contains(stack.getItem());
    }
}
