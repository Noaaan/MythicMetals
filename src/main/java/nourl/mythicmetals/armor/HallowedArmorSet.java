package nourl.mythicmetals.armor;

import io.wispforest.owo.itemgroup.OwoItemSettings;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.Item;
import net.minecraft.util.registry.Registry;
import nourl.mythicmetals.MythicMetals;
import nourl.mythicmetals.utils.RegistryHelper;

import java.util.function.Consumer;

public class HallowedArmorSet extends ArmorSet {

    private final HallowedArmor helmet;
    private final HallowedArmor chestplate;
    private final HallowedArmor leggings;
    private final ArmorItem boots;

    private static HallowedArmor baseArmorItem(EquipmentSlot slot, Consumer<Item.Settings> settingsProcessor) {
        final var settings = new OwoItemSettings().group(MythicMetals.TABBED_GROUP).tab(3);
        settingsProcessor.accept(settings);
        return new HallowedArmor(slot, settings);
    }

    public HallowedArmorSet(ArmorMaterial material) {
        this(material, settings -> {
        });
    }

    public HallowedArmorSet(ArmorMaterial material, Consumer<Item.Settings> settingsProcessor) {
        super(material);
        this.helmet = baseArmorItem(EquipmentSlot.HEAD, settingsProcessor);
        this.chestplate = baseArmorItem(EquipmentSlot.CHEST, settingsProcessor);
        this.leggings = baseArmorItem(EquipmentSlot.LEGS, settingsProcessor);
        this.boots = ArmorSet.baseArmorItem(ArmorMaterials.HALLOWED, EquipmentSlot.FEET, settingsProcessor);
    }

    public void register(String name) {
        Registry.register(Registry.ITEM, RegistryHelper.id(name + "_helmet"), helmet);
        Registry.register(Registry.ITEM, RegistryHelper.id(name + "_chestplate"), chestplate);
        Registry.register(Registry.ITEM, RegistryHelper.id(name + "_leggings"), leggings);
        Registry.register(Registry.ITEM, RegistryHelper.id(name + "_boots"), boots);
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
}
