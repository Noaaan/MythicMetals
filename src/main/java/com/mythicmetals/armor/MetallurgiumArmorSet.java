package com.mythicmetals.armor;

import java.util.function.Consumer;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.equipment.ArmorMaterial;
import net.minecraft.world.item.equipment.ArmorType;

public class MetallurgiumArmorSet extends ArmorSet {

    public MetallurgiumArmorSet(ArmorMaterial material, Consumer<Item.Properties> settingsProcessor) {
        super("metallurgium", material, settingsProcessor);
    }

    @Override
    protected Item makeItem(ArmorMaterial material, ArmorType slot, Item.Properties settings) {
        if (slot != ArmorType.HELMET) {
            return super.makeItem(material, slot, settings);
        }
        return new MetallurgiumArmor(slot, settings);
    }
}
