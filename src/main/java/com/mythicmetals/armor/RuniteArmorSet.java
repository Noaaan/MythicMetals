package com.mythicmetals.armor;

import net.minecraft.item.*;
import net.minecraft.item.equipment.ArmorMaterial;
import net.minecraft.item.equipment.EquipmentType;

public class RuniteArmorSet extends ArmorSet {

    public RuniteArmorSet(ArmorMaterial material) {
        super("runite", material);
    }

    @Override
    protected Item makeItem(ArmorMaterial material, EquipmentType slot, Item.Settings settings) {
        if (slot != EquipmentType.HELMET) return super.makeItem(material, slot, settings);
        return new RuniteArmor(slot, settings);
    }
}
