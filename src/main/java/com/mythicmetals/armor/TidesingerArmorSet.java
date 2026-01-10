package com.mythicmetals.armor;

import com.mythicmetals.MythicAttributeModifier;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.equipment.ArmorMaterial;
import net.minecraft.world.item.equipment.ArmorType;
import java.util.List;

public class TidesingerArmorSet extends ArmorSet {

    public TidesingerArmorSet(ArmorMaterial material, List<MythicAttributeModifier> extraAttributes) {
        super("tidesinger", material, extraAttributes, settings -> {});
    }

    @Override
    protected Item makeItem(ArmorMaterial material, ArmorType slot, Item.Properties settings) {
        return new TidesingerArmor(slot, settings);
    }
}
