package com.mythicmetals.armor;

import com.mythicmetals.MythicAttributeModifier;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.equipment.ArmorMaterial;
import net.minecraft.world.item.equipment.ArmorType;
import java.util.List;
import java.util.function.Consumer;

public class BanglumArmorSet extends ArmorSet {

    public BanglumArmorSet(ArmorMaterial material, Consumer<Item.Properties> settingsProcessor, List<MythicAttributeModifier> extraModifiers) {
        super("legendary_banglum", material, extraModifiers, settingsProcessor);
    }

    @Override
    protected Item makeItem(ArmorMaterial material, ArmorType slot, Item.Properties settings) {
        if (slot != ArmorType.HELMET) return super.makeItem(material, slot, settings);
        return new BanglumArmor(slot, settings);
    }
}
