package com.mythicmetals.armor;

import net.minecraft.item.Item;
import net.minecraft.item.equipment.ArmorMaterial;
import net.minecraft.item.equipment.EquipmentType;

public class AdamantiteArmorSet extends ArmorSet {

    public AdamantiteArmorSet(ArmorMaterial material) {
        super("adamantite", material);
    }

    @Override
    protected Item makeItem(ArmorMaterial material, EquipmentType slot, Item.Settings settings) {
        if (slot != EquipmentType.HELMET) return super.makeItem(material, slot, settings);
        return new AdamantiteArmor(slot, settings);
    }
}
