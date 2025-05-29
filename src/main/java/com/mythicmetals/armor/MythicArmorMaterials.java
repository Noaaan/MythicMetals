package com.mythicmetals.armor;

import io.wispforest.owo.registration.reflect.AutoRegistryContainer;
import net.minecraft.item.*;
import net.minecraft.recipe.Ingredient;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.sound.SoundEvent;
import com.mythicmetals.item.MythicItems;
import com.mythicmetals.misc.RegistryHelper;
import com.mythicmetals.registry.RegisterSounds;
import java.util.List;
import java.util.Map;

@SuppressWarnings("CodeBlock2Expr")
public class MythicArmorMaterials implements AutoRegistryContainer<ArmorMaterial> {
    public static final ArmorMaterial ADAMANTITE = new ArmorMaterial(Map.of(
        ArmorItem.Type.HELMET, 3,
        ArmorItem.Type.CHESTPLATE, 8,
        ArmorItem.Type.LEGGINGS, 6,
        ArmorItem.Type.BOOTS, 3), 16, sound(RegisterSounds.EQUIP_ADAMANTITE), () -> {
        return Ingredient.ofItems(MythicItems.ADAMANTITE.getIngot());
    }, List.of(layer("adamantite")), 2.0f, 0.0f);

    public static final ArmorMaterial AQUARIUM = new ArmorMaterial(Map.of(
        ArmorItem.Type.HELMET, 2,
        ArmorItem.Type.CHESTPLATE, 5,
        ArmorItem.Type.LEGGINGS, 4,
        ArmorItem.Type.BOOTS, 1), 12, sound(RegisterSounds.EQUIP_AQUARIUM), () -> {
        return Ingredient.ofItems(MythicItems.AQUARIUM.getIngot());
    }, List.of(layer("aquarium")), 0f, 0f);

    public static final ArmorMaterial BANGLUM = new ArmorMaterial(Map.of(
        ArmorItem.Type.HELMET, 3,
        ArmorItem.Type.CHESTPLATE, 6,
        ArmorItem.Type.LEGGINGS, 5,
        ArmorItem.Type.BOOTS, 2), 1, sound(RegisterSounds.EQUIP_BANGLUM), () -> {
        return Ingredient.ofItems(MythicItems.BANGLUM.getIngot());
    }, List.of(layer("banglum")), 0.0f, 0.0f);

    public static final ArmorMaterial BRONZE = new ArmorMaterial(Map.of(
        ArmorItem.Type.HELMET, 3,
        ArmorItem.Type.CHESTPLATE, 6,
        ArmorItem.Type.LEGGINGS, 5,
        ArmorItem.Type.BOOTS, 2), 14, sound(RegisterSounds.EQUIP_BRONZE), () -> {
        return Ingredient.ofItems(MythicItems.BRONZE.getIngot());
    }, List.of(layer("bronze")), 0.0f, 0.0f);

    public static final ArmorMaterial CARMOT = new ArmorMaterial(Map.of(
        ArmorItem.Type.HELMET, 3,
        ArmorItem.Type.CHESTPLATE, 8,
        ArmorItem.Type.LEGGINGS, 6,
        ArmorItem.Type.BOOTS, 3), 40, sound(RegisterSounds.EQUIP_CARMOT), () -> {
        return Ingredient.ofItems(MythicItems.CARMOT.getIngot());
    }, List.of(layer("carmot")), 0.0f, 0.0f);

    public static final ArmorMaterial CELESTIUM = new ArmorMaterial(Map.of(
        ArmorItem.Type.HELMET, 4,
        ArmorItem.Type.CHESTPLATE, 10,
        ArmorItem.Type.LEGGINGS, 7,
        ArmorItem.Type.BOOTS, 4), 30, sound(RegisterSounds.EQUIP_CELESTIUM), () -> {
        return Ingredient.ofItems(MythicItems.CELESTIUM.getIngot());
    }, List.of(layer("celestium")), 3.0f, 0.0f);

    public static final ArmorMaterial COPPER = new ArmorMaterial(Map.of(
        ArmorItem.Type.HELMET, 2,
        ArmorItem.Type.CHESTPLATE, 4,
        ArmorItem.Type.LEGGINGS, 3,
        ArmorItem.Type.BOOTS, 1), 8, sound(RegisterSounds.EQUIP_COPPER), () -> {
        return Ingredient.ofItems(Items.COPPER_INGOT);
    }, List.of(layer("copper")), 0.0f, 0.0f);

    public static final ArmorMaterial DURASTEEL = new ArmorMaterial(Map.of(
        ArmorItem.Type.HELMET, 3,
        ArmorItem.Type.CHESTPLATE, 8,
        ArmorItem.Type.LEGGINGS, 6,
        ArmorItem.Type.BOOTS, 3), 12, sound(RegisterSounds.EQUIP_DURASTEEL), () -> {
        return Ingredient.ofItems(MythicItems.DURASTEEL.getIngot());
    }, List.of(layer("durasteel")), 1.0f, 0.0f);

    public static final ArmorMaterial HALLOWED = new ArmorMaterial(Map.of(
        ArmorItem.Type.HELMET, 4,
        ArmorItem.Type.CHESTPLATE, 9,
        ArmorItem.Type.LEGGINGS, 7,
        ArmorItem.Type.BOOTS, 4), 20, sound(RegisterSounds.EQUIP_HALLOWED), () -> {
        return Ingredient.ofItems(MythicItems.HALLOWED.getIngot());
    }, List.of(layer("hallowed")), 4.0f, 0.0f);

    public static final ArmorMaterial KYBER = new ArmorMaterial(Map.of(
        ArmorItem.Type.HELMET, 3,
        ArmorItem.Type.CHESTPLATE, 8,
        ArmorItem.Type.LEGGINGS, 6,
        ArmorItem.Type.BOOTS, 3), 20, sound(RegisterSounds.EQUIP_KYBER), () -> {
        return Ingredient.ofItems(MythicItems.KYBER.getIngot());
    }, List.of(layer("kyber")), 0.0f, 0.0f);

    public static final ArmorMaterial LEGENDARY_BANGLUM = new ArmorMaterial(Map.of(
        ArmorItem.Type.HELMET, 3,
        ArmorItem.Type.CHESTPLATE, 8,
        ArmorItem.Type.LEGGINGS, 6,
        ArmorItem.Type.BOOTS, 3), 2, sound(RegisterSounds.EQUIP_LEGENDARY_BANGLUM), () -> {
        return Ingredient.ofItems(MythicItems.BANGLUM.getIngot());
    }, List.of(layer("legendary_banglum")), 2.0f, 0.0f);

    public static final ArmorMaterial METALLURGIUM = new ArmorMaterial(Map.of(
        ArmorItem.Type.HELMET, 5,
        ArmorItem.Type.CHESTPLATE, 12,
        ArmorItem.Type.LEGGINGS, 8,
        ArmorItem.Type.BOOTS, 5), 30, sound(RegisterSounds.EQUIP_METALLURGIUM), () -> {
        return Ingredient.ofItems(MythicItems.METALLURGIUM.getIngot());
    }, List.of(layer("metallurgium")), 5.0f, 0.225f);

    public static final ArmorMaterial MIDAS_GOLD = new ArmorMaterial(Map.of(
        ArmorItem.Type.HELMET, 2,
        ArmorItem.Type.CHESTPLATE, 5,
        ArmorItem.Type.LEGGINGS, 3,
        ArmorItem.Type.BOOTS, 1), 24, sound(RegisterSounds.EQUIP_MIDAS_GOLD), () -> {
        return Ingredient.ofItems(MythicItems.MIDAS_GOLD.getIngot());
    }, List.of(layer("midas_gold")), 0.0f, 0.0f);

    public static final ArmorMaterial MYTHRIL = new ArmorMaterial(Map.of(
        ArmorItem.Type.HELMET, 3,
        ArmorItem.Type.CHESTPLATE, 8,
        ArmorItem.Type.LEGGINGS, 6,
        ArmorItem.Type.BOOTS, 3), 22, sound(RegisterSounds.EQUIP_MYTHRIL), () -> {
        return Ingredient.ofItems(MythicItems.MYTHRIL.getIngot());
    }, List.of(layer("mythril")), 2.0f, 0.0f);

    public static final ArmorMaterial ORICHALCUM = new ArmorMaterial(Map.of(
        ArmorItem.Type.HELMET, 4,
        ArmorItem.Type.CHESTPLATE, 9,
        ArmorItem.Type.LEGGINGS, 7,
        ArmorItem.Type.BOOTS, 4), 16, sound(RegisterSounds.EQUIP_ORICHALCUM), () -> {
        return Ingredient.ofItems(MythicItems.ORICHALCUM.getIngot());
    }, List.of(layer("orichalcum")), 3.0f, 0.1f);

    public static final ArmorMaterial OSMIUM = new ArmorMaterial(Map.of(
        ArmorItem.Type.HELMET, 3,
        ArmorItem.Type.CHESTPLATE, 7,
        ArmorItem.Type.LEGGINGS, 6,
        ArmorItem.Type.BOOTS, 3), 16, sound(RegisterSounds.EQUIP_OSMIUM), () -> {
        return Ingredient.ofItems(MythicItems.OSMIUM.getIngot());
    }, List.of(layer("osmium")), 2.0f, 0.25f);

    public static final ArmorMaterial OSMIUM_CHAINMAIL = new ArmorMaterial(Map.of(
        ArmorItem.Type.HELMET, 2,
        ArmorItem.Type.CHESTPLATE, 5,
        ArmorItem.Type.LEGGINGS, 4,
        ArmorItem.Type.BOOTS, 1), 15, sound(RegisterSounds.EQUIP_OSMIUM_CHAINMAIL), () -> {
        return Ingredient.ofItems(MythicItems.OSMIUM.getIngot());
    }, List.of(layer("osmium_chainmail")), 2.0f, 0.2f);

    public static final ArmorMaterial PALLADIUM = new ArmorMaterial(Map.of(
        ArmorItem.Type.HELMET, 3,
        ArmorItem.Type.CHESTPLATE, 8,
        ArmorItem.Type.LEGGINGS, 6,
        ArmorItem.Type.BOOTS, 3), 16, sound(RegisterSounds.EQUIP_PALLADIUM), () -> {
        return Ingredient.ofItems(MythicItems.PALLADIUM.getIngot());
    }, List.of(layer("palladium")), 0.0f, 0.0f);

    public static final ArmorMaterial PROMETHEUM = new ArmorMaterial(Map.of(
        ArmorItem.Type.HELMET, 3,
        ArmorItem.Type.CHESTPLATE, 8,
        ArmorItem.Type.LEGGINGS, 6,
        ArmorItem.Type.BOOTS, 3), 17, sound(RegisterSounds.EQUIP_PROMETHEUM), () -> {
        return Ingredient.ofItems(MythicItems.PROMETHEUM.getIngot());
    }, List.of(layer("prometheum")), 1.0f, 0.0f);

    public static final ArmorMaterial RUNITE = new ArmorMaterial(Map.of(
        ArmorItem.Type.HELMET, 3,
        ArmorItem.Type.CHESTPLATE, 8,
        ArmorItem.Type.LEGGINGS, 6,
        ArmorItem.Type.BOOTS, 3), 13, sound(RegisterSounds.EQUIP_RUNITE), () -> {
        return Ingredient.ofItems(MythicItems.RUNITE.getIngot());
    }, List.of(layer("runite")), 2.0f, 0.0f);

    public static final ArmorMaterial SILVER = new ArmorMaterial(Map.of(
        ArmorItem.Type.HELMET, 2,
        ArmorItem.Type.CHESTPLATE, 4,
        ArmorItem.Type.LEGGINGS, 3,
        ArmorItem.Type.BOOTS, 1), 20, sound(RegisterSounds.EQUIP_SILVER), () -> {
        return Ingredient.ofItems(MythicItems.SILVER.getIngot());
    }, List.of(layer("silver")), 0.0f, 0.0f);

    public static final ArmorMaterial STAR_PLATINUM = new ArmorMaterial(Map.of(
        ArmorItem.Type.HELMET, 3,
        ArmorItem.Type.CHESTPLATE, 7,
        ArmorItem.Type.LEGGINGS, 7,
        ArmorItem.Type.BOOTS, 3), 18, sound(RegisterSounds.EQUIP_STAR_PLATINUM), () -> {
        return Ingredient.ofItems(MythicItems.STAR_PLATINUM.getIngot());
    }, List.of(layer("star_platinum")), 2.0f, 0.1f);

    public static final ArmorMaterial STEEL = new ArmorMaterial(Map.of(
        ArmorItem.Type.HELMET, 2,
        ArmorItem.Type.CHESTPLATE, 6,
        ArmorItem.Type.LEGGINGS, 5,
        ArmorItem.Type.BOOTS, 2), 10, sound(RegisterSounds.EQUIP_STEEL), () -> {
        return Ingredient.ofItems(MythicItems.STEEL.getIngot());
    }, List.of(layer("steel")), 0.5f, 0.0f);

    public static final ArmorMaterial STORMYX = new ArmorMaterial(Map.of(
        ArmorItem.Type.HELMET, 3,
        ArmorItem.Type.CHESTPLATE, 8,
        ArmorItem.Type.LEGGINGS, 6,
        ArmorItem.Type.BOOTS, 3), 30, sound(RegisterSounds.EQUIP_STORMYX), () -> {
        return Ingredient.ofItems(MythicItems.STORMYX.getIngot());
    }, List.of(layer("stormyx")), 2.0f, 0.0f);

    public static final ArmorMaterial TIDESINGER = new ArmorMaterial(Map.of(
        ArmorItem.Type.HELMET, 3,
        ArmorItem.Type.CHESTPLATE, 8,
        ArmorItem.Type.LEGGINGS, 6,
        ArmorItem.Type.BOOTS, 3), 16, sound(RegisterSounds.EQUIP_TIDESINGER), () -> {
        return Ingredient.ofItems(MythicItems.AQUARIUM.getIngot());
    }, List.of(layer("tidesinger")), 2.0f, 0.0f);

    @Override
    public Registry<ArmorMaterial> getRegistry() {
        return Registries.ARMOR_MATERIAL;
    }

    @Override
    public Class<ArmorMaterial> getTargetFieldType() {
        return ArmorMaterial.class;
    }

    private static RegistryEntry<SoundEvent> sound(SoundEvent sound) {
        return Registries.SOUND_EVENT.getEntry(sound);
    }

    private static ArmorMaterial.Layer layer(String name) {
        return new ArmorMaterial.Layer(RegistryHelper.id(name));
    }
}
