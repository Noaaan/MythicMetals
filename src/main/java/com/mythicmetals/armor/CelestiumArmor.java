package com.mythicmetals.armor;

import com.mythicmetals.misc.RegistryHelper;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;

// TODO - Make this extend HallowedArmor and give it an epic model sometime
public class CelestiumArmor extends ArmorItem {

    public CelestiumArmor(ArmorMaterial material, Type type, Settings settings) {
        super(RegistryHelper.getEntry(material), type, settings);
    }
}