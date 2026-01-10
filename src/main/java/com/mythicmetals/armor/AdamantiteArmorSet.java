package com.mythicmetals.armor;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.equipment.ArmorMaterial;
import net.minecraft.world.item.equipment.ArmorType;

public class AdamantiteArmorSet extends ArmorSet {

    public AdamantiteArmorSet(ArmorMaterial material) {
        super("adamantite", material);
    }

    @Override
    protected Item makeItem(ArmorMaterial material, ArmorType slot, Item.Properties settings) {
        if (slot != ArmorType.HELMET) return super.makeItem(material, slot, settings);
        return new AdamantiteArmor(slot, settings);
    }
}
