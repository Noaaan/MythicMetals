package com.mythicmetals.item.armor;

import com.mythicmetals.api.v2.ArmorSet;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.equipment.ArmorMaterial;
import net.minecraft.world.item.equipment.ArmorType;

public class TidesingerArmorSet extends ArmorSet {

    public TidesingerArmorSet(ArmorMaterial material) {
        super("tidesinger", material);
    }

    @Override
    protected Item makeItem(ArmorType armorType, Item.Properties settings) {
        return new TidesingerArmor(armorType, settings);
    }
}
