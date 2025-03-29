package com.mythicmetals.armor;

import net.minecraft.item.*;
import java.util.function.Consumer;

public class BanglumArmorSet extends ArmorSet {

    public BanglumArmorSet(ArmorMaterial material, int duraMod, Consumer<Item.Settings> settingsProcessor) {
        super(material, duraMod, settingsProcessor);
    }

    @Override
    protected ArmorItem makeItem(ArmorMaterial material, ArmorItem.Type slot, Item.Settings settings) {
        if (slot != ArmorItem.Type.HELMET) return super.makeItem(material, slot, settings);
        return new BanglumArmor(slot, settings);
    }
}
