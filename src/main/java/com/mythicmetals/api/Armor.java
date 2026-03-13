package com.mythicmetals.api;

import com.mythicmetals.misc.RegistryHelper;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.equipment.ArmorMaterial;
import net.minecraft.world.item.equipment.ArmorType;

import static com.mythicmetals.misc.RegistryHelper.itemKey;

public class Armor {
    public final ResourceKey<Item> helmetKey;
    public final ResourceKey<Item> chestplateKey;
    public final ResourceKey<Item> leggingsKey;
    public final ResourceKey<Item> bootsKey;
    public final ResourceKey<Item> bodyKey;
    private final ArmorMaterial armorMaterial;

    protected Item helmet;
    protected Item chestplate;
    protected Item leggings;
    protected Item boots;
    protected Item horse;

    public Armor(String name, ArmorMaterial armorMaterial) {
        this.helmetKey = itemKey(name + "_helmet");
        this.chestplateKey = itemKey(name + "_chestplate");
        this.leggingsKey = itemKey(name + "_leggings");
        this.bootsKey = itemKey(name + "_boots");
        this.bodyKey = itemKey(name + "_body");
        this.armorMaterial = armorMaterial;
    }

    public void create() {
        this.helmet = RegistryHelper.item(helmetKey, new Item(
           new Item.Properties().humanoidArmor(armorMaterial, ArmorType.HELMET).setId(helmetKey)
        ));
        this.chestplate = RegistryHelper.item(chestplateKey, new Item(
            new Item.Properties().humanoidArmor(armorMaterial, ArmorType.CHESTPLATE).setId(chestplateKey)
        ));
        this.leggings = RegistryHelper.item(leggingsKey, new Item(
            new Item.Properties().humanoidArmor(armorMaterial, ArmorType.LEGGINGS).setId(leggingsKey)
        ));
        this.boots = RegistryHelper.item(bootsKey, new Item(
            new Item.Properties().humanoidArmor(armorMaterial, ArmorType.BOOTS).setId(bootsKey)
        ));
    }
}
