package com.mythicmetals.armor;

import com.mythicmetals.api.v2.ArmorSet;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.equipment.ArmorMaterial;
import net.minecraft.world.item.equipment.ArmorType;

public class CustomHelmetArmorSet extends ArmorSet {

    private final ModelLayerLocation modelLocation;
    private final Identifier texture;

    public CustomHelmetArmorSet(String name, ArmorMaterial armorMaterial, ModelLayerLocation modelLocation, Identifier texture) {
        super(name, armorMaterial);
        this.modelLocation = modelLocation;
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
            modelLocation,
            texture
        );
    }
}
