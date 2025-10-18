package com.mythicmetals.armor;

import net.minecraft.item.*;
import net.minecraft.item.equipment.ArmorMaterial;
import net.minecraft.item.equipment.EquipmentType;

public class TidesingerArmorSet extends ArmorSet {

    public TidesingerArmorSet(ArmorMaterial material) {
        super("tidesinger", material);
    }

    @Override
    protected ArmorItem makeItem(ArmorMaterial material, EquipmentType slot, Item.Settings settings) {
        return new TidesingerArmor(slot, settings);
    }
}
