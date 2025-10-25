package com.mythicmetals.armor;

import net.minecraft.item.*;
import net.minecraft.item.equipment.ArmorMaterial;
import net.minecraft.item.equipment.EquipmentType;
import java.util.function.Consumer;

public class MetallurgiumArmorSet extends ArmorSet {

    public MetallurgiumArmorSet(ArmorMaterial material, Consumer<Item.Settings> settingsProcessor) {
        super("metallurgium", material, settingsProcessor);
    }

    @Override
    protected Item makeItem(ArmorMaterial material, EquipmentType slot, Item.Settings settings) {
        if (slot != EquipmentType.HELMET) {
            return super.makeItem(material, slot, settings);
        }
        return new MetallurgiumArmor(slot, settings);
    }
}
