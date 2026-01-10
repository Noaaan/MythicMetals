package com.mythicmetals.armor;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.equipment.ArmorMaterial;
import net.minecraft.world.item.equipment.ArmorType;
import java.util.function.Consumer;

public class HallowedArmorSet extends ArmorSet {

    public HallowedArmorSet(ArmorMaterial material, Consumer<Item.Properties> settingsProcessor) {
        super("hallowed", material, settingsProcessor);
    }

    @Override
    protected Item makeItem(ArmorMaterial material, ArmorType slot, Item.Properties settings) {
        if (slot != ArmorType.HELMET) {
            return super.makeItem(material, slot, settings);
        }
        return new HallowedArmor(slot, settings);
    }
}
