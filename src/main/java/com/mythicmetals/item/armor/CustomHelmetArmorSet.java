package com.mythicmetals.item.armor;

import com.mythicmetals.api.v2.ArmorSet;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.equipment.ArmorMaterial;
import net.minecraft.world.item.equipment.ArmorType;

public class CustomHelmetArmorSet extends ArmorSet {

    private final Identifier texture;

    public CustomHelmetArmorSet(String name, ArmorMaterial armorMaterial, Identifier texture) {
        super(name, armorMaterial);
        this.texture = texture;
    }

    @Override
    protected Item makeItem(ArmorType armorType, Item.Properties settings) {
        if (armorType != ArmorType.HELMET) {
            return super.makeItem(armorType, settings);
        }
        return new CustomHelmetArmor(
            armorType,
            settings,
            texture
        );
    }
}
