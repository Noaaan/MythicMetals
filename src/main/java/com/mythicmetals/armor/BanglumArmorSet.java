package com.mythicmetals.armor;

import com.mythicmetals.AttributeModifier;
import net.minecraft.item.Item;
import net.minecraft.item.equipment.ArmorMaterial;
import net.minecraft.item.equipment.EquipmentType;
import java.util.List;
import java.util.function.Consumer;

public class BanglumArmorSet extends ArmorSet {

    public BanglumArmorSet(ArmorMaterial material, Consumer<Item.Settings> settingsProcessor, List<AttributeModifier> extraModifiers) {
        super("legendary_banglum", material, extraModifiers, settingsProcessor);
    }

    @Override
    protected Item makeItem(ArmorMaterial material, EquipmentType slot, Item.Settings settings) {
        if (slot != EquipmentType.HELMET) return super.makeItem(material, slot, settings);
        return new BanglumArmor(slot, settings);
    }
}
