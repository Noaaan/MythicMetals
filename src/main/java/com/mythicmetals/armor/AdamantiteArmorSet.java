package com.mythicmetals.armor;

import com.mythicmetals.api.v2.ArmorSet;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.equipment.ArmorMaterial;
import net.minecraft.world.item.equipment.ArmorType;

public class AdamantiteArmorSet extends ArmorSet {

    public AdamantiteArmorSet(ArmorMaterial material) {
        super("adamantite", material);
    }

    @Override
    protected Item makeItem(ArmorType armorType, Item.Properties settings) {
        if (armorType != ArmorType.HELMET) return super.makeItem(armorType, settings);
        return new AdamantiteArmor(armorType, settings);
    }
}
