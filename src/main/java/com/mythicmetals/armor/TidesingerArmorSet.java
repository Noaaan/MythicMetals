package com.mythicmetals.armor;

import net.minecraft.item.*;
import java.util.function.Consumer;

public class TidesingerArmorSet extends ArmorSet {

    public TidesingerArmorSet(ArmorMaterial material, int duraMod, Consumer<Item.Settings> settingsProcessor) {
        super(material, duraMod, settingsProcessor);
    }

    @Override
    protected ArmorItem makeItem(ArmorMaterial material, ArmorItem.Type slot, Item.Settings settings) {
        return new TidesingerArmor(slot, settings);
    }
}
