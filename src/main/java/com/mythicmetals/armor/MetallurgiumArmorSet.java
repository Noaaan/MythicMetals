package com.mythicmetals.armor;

import net.minecraft.item.*;
import java.util.function.Consumer;

public class MetallurgiumArmorSet extends ArmorSet {

    public MetallurgiumArmorSet(ArmorMaterial material, int duraMod, Consumer<Item.Settings> settingsProcessor) {
        super(material, duraMod, settingsProcessor);
    }

    @Override
    protected ArmorItem makeItem(ArmorMaterial material, ArmorItem.Type slot, Item.Settings settings) {
        if (slot != ArmorItem.Type.HELMET) {
            return super.makeItem(material, slot, settings);
        }
        return new MetallurgiumArmor(slot, settings);
    }
}
