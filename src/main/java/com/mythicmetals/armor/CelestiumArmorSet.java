package com.mythicmetals.armor;

import net.minecraft.item.*;
import java.util.function.Consumer;

public class CelestiumArmorSet extends ArmorSet {

    public CelestiumArmorSet(ArmorMaterial material, int duraMod, Consumer<Item.Settings> settingsConsumer) {
        super(material, duraMod, settingsConsumer);
    }

    @Override
    protected ArmorItem makeItem(ArmorMaterial material, ArmorItem.Type slot, Item.Settings settings) {
        return new CelestiumArmor(material, slot, settings);
    }
}
