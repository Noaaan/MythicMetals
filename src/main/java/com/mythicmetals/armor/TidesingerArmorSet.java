package com.mythicmetals.armor;

import net.minecraft.item.*;
import net.minecraft.item.equipment.ArmorMaterial;
import net.minecraft.item.equipment.EquipmentType;
import java.util.List;

public class TidesingerArmorSet extends ArmorSet {

    public TidesingerArmorSet(ArmorMaterial material, List<AttributeModifier> extraAttributes) {
        super("tidesinger", material, extraAttributes, settings -> {});
    }

    @Override
    protected Item makeItem(ArmorMaterial material, EquipmentType slot, Item.Settings settings) {
        return new TidesingerArmor(slot, settings);
    }
}
