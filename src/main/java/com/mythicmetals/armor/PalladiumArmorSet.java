package com.mythicmetals.armor;

import com.mythicmetals.AttributeModifier;
import net.minecraft.item.Item;
import net.minecraft.item.equipment.ArmorMaterial;
import net.minecraft.item.equipment.EquipmentType;
import java.util.List;
import java.util.function.Consumer;

public class PalladiumArmorSet extends ArmorSet {

    public PalladiumArmorSet(String name, ArmorMaterial material, List<AttributeModifier> extraAttributes, Consumer<Item.Settings> settingsConsumer) {
        super(name, material, extraAttributes, settingsConsumer);
    }

    @Override
    protected Item makeItem(ArmorMaterial material, EquipmentType slot, Item.Settings settings) {
        if (slot != EquipmentType.HELMET) return super.makeItem(material, slot, settings);
        return new PalladiumArmor(slot, settings);
    }
}
