package com.mythicmetals.armor;

import com.mythicmetals.MythicAttributeModifier;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.equipment.ArmorMaterial;
import net.minecraft.world.item.equipment.ArmorType;
import java.util.List;
import java.util.function.Consumer;

public class PalladiumArmorSet extends ArmorSet {

    public PalladiumArmorSet(String name, ArmorMaterial material, List<MythicAttributeModifier> extraAttributes, Consumer<Item.Properties> settingsConsumer) {
        super(name, material, extraAttributes, settingsConsumer);
    }

    @Override
    protected Item makeItem(ArmorMaterial material, ArmorType slot, Item.Properties settings) {
        if (slot != ArmorType.HELMET) return super.makeItem(material, slot, settings);
        return new PalladiumArmor(slot, settings);
    }
}
