package com.mythicmetals.armor;

import com.mythicmetals.misc.RegistryHelper;
import com.mythicmetals.registry.RegisterSounds;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.Identifier;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.equipment.*;
import java.util.Map;

@SuppressWarnings("CodeBlock2Expr")
public class MythicArmorMaterials {
    public static final ArmorMaterial ADAMANTITE = new ArmorMaterial(30, Map.of(
        ArmorType.HELMET, 3,
        ArmorType.CHESTPLATE, 8,
        ArmorType.LEGGINGS, 6,
        ArmorType.BOOTS, 3,
        ArmorType.BODY, 11), 16, sound(RegisterSounds.EQUIP_ADAMANTITE), 2.0f, 0.0f, repairTag("adamantite"), layer("adamantite"));

    public static final ArmorMaterial AQUARIUM = new ArmorMaterial(19, Map.of(
        ArmorType.HELMET, 2,
        ArmorType.CHESTPLATE, 5,
        ArmorType.LEGGINGS, 4,
        ArmorType.BOOTS, 1,
        ArmorType.BODY, 6), 12, sound(RegisterSounds.EQUIP_AQUARIUM), 0f, 0f, repairTag("aquarium"), layer("aquarium"));

    public static final ArmorMaterial BANGLUM = new ArmorMaterial(14, Map.of(
        ArmorType.HELMET, 3,
        ArmorType.CHESTPLATE, 6,
        ArmorType.LEGGINGS, 5,
        ArmorType.BOOTS, 2,
        ArmorType.BODY, 7), 1, sound(RegisterSounds.EQUIP_BANGLUM), 0.0f, 0.05f, repairTag("banglum"), layer("banglum"));

    public static final ArmorMaterial BRONZE = new ArmorMaterial(17, Map.of(
        ArmorType.HELMET, 3,
        ArmorType.CHESTPLATE, 6,
        ArmorType.LEGGINGS, 5,
        ArmorType.BOOTS, 2,
        ArmorType.BODY, 8), 14, sound(RegisterSounds.EQUIP_BRONZE), 0.0f, 0.0f, repairTagCommon("bronze"), layer("bronze"));

    public static final ArmorMaterial CARMOT = new ArmorMaterial(26, Map.of(
        ArmorType.HELMET, 3,
        ArmorType.CHESTPLATE, 8,
        ArmorType.LEGGINGS, 6,
        ArmorType.BOOTS, 3,
        ArmorType.BODY, 11), 40, sound(RegisterSounds.EQUIP_CARMOT), 0.0f, 0.0f, repairTag("carmot"), layer("carmot"));

    public static final ArmorMaterial CELESTIUM = new ArmorMaterial(41, Map.of(
        ArmorType.HELMET, 4,
        ArmorType.CHESTPLATE, 10,
        ArmorType.LEGGINGS, 7,
        ArmorType.BOOTS, 4,
        ArmorType.BODY, 20), 30, sound(RegisterSounds.EQUIP_CELESTIUM), 3.0f, 0.0f, repairTag("celestium"), layer("celestium"));

    public static final ArmorMaterial DURASTEEL = new ArmorMaterial(25, Map.of(
        ArmorType.HELMET, 3,
        ArmorType.CHESTPLATE, 8,
        ArmorType.LEGGINGS, 6,
        ArmorType.BOOTS, 3,
        ArmorType.BODY, 10), 12, sound(RegisterSounds.EQUIP_DURASTEEL), 1.0f, 0.0f, repairTag("durasteel"), layer("durasteel"));

    public static final ArmorMaterial HALLOWED = new ArmorMaterial(41, Map.of(
        ArmorType.HELMET, 4,
        ArmorType.CHESTPLATE, 9,
        ArmorType.LEGGINGS, 7,
        ArmorType.BOOTS, 4,
        ArmorType.BODY, 16), 20, sound(RegisterSounds.EQUIP_HALLOWED), 4.0f, 0.0f, repairTag("hallowed"), layer("hallowed"));

    public static final ArmorMaterial KYBER = new ArmorMaterial(21, Map.of(
        ArmorType.HELMET, 3,
        ArmorType.CHESTPLATE, 8,
        ArmorType.LEGGINGS, 6,
        ArmorType.BOOTS, 3,
        ArmorType.BODY, 11), 20, sound(RegisterSounds.EQUIP_KYBER), 0.0f, 1.0f, repairTag("kyber"), layer("kyber"));

    public static final ArmorMaterial LEGENDARY_BANGLUM = new ArmorMaterial(28, Map.of(
        ArmorType.HELMET, 3,
        ArmorType.CHESTPLATE, 8,
        ArmorType.LEGGINGS, 6,
        ArmorType.BOOTS, 3,
        ArmorType.BODY, 12), 2, sound(RegisterSounds.EQUIP_LEGENDARY_BANGLUM), 2.0f, 0.05f, repairTag("legendary_banglum"), layer("legendary_banglum"));

    public static final ArmorMaterial METALLURGIUM = new ArmorMaterial(69, Map.of(
        ArmorType.HELMET, 5,
        ArmorType.CHESTPLATE, 12,
        ArmorType.LEGGINGS, 8,
        ArmorType.BOOTS, 5,
        ArmorType.BODY, 23), 30, sound(RegisterSounds.EQUIP_METALLURGIUM), 5.0f, 0.225f, repairTag("metallurgium"), layer("metallurgium"));

    public static final ArmorMaterial MIDAS_GOLD = new ArmorMaterial(14, Map.of(
        ArmorType.HELMET, 2,
        ArmorType.CHESTPLATE, 5,
        ArmorType.LEGGINGS, 3,
        ArmorType.BOOTS, 1,
        ArmorType.BODY, 6), 24, sound(RegisterSounds.EQUIP_MIDAS_GOLD), 0.0f, 0.0f, repairTag("midas_gold"), layer("midas_gold"));

    public static final ArmorMaterial MYTHRIL = new ArmorMaterial(31, Map.of(
        ArmorType.HELMET, 3,
        ArmorType.CHESTPLATE, 8,
        ArmorType.LEGGINGS, 6,
        ArmorType.BOOTS, 3,
        ArmorType.BODY, 12), 22, sound(RegisterSounds.EQUIP_MYTHRIL), 2.0f, 0.0f, repairTag("mythril"), layer("mythril"));

    public static final ArmorMaterial ORICHALCUM = new ArmorMaterial(40, Map.of(
        ArmorType.HELMET, 4,
        ArmorType.CHESTPLATE, 9,
        ArmorType.LEGGINGS, 7,
        ArmorType.BOOTS, 4,
        ArmorType.BODY, 16), 16, sound(RegisterSounds.EQUIP_ORICHALCUM), 3.0f, 0.1f, repairTag("orichalcum"), layer("orichalcum"));

    public static final ArmorMaterial OSMIUM = new ArmorMaterial(25, Map.of(
        ArmorType.HELMET, 3,
        ArmorType.CHESTPLATE, 7,
        ArmorType.LEGGINGS, 6,
        ArmorType.BOOTS, 3,
        ArmorType.BODY, 10), 16, sound(RegisterSounds.EQUIP_OSMIUM), 2.0f, 0.25f, repairTagCommon("osmium"), layer("osmium"));

    public static final ArmorMaterial OSMIUM_CHAINMAIL = new ArmorMaterial(25, Map.of(
        ArmorType.HELMET, 2,
        ArmorType.CHESTPLATE, 5,
        ArmorType.LEGGINGS, 4,
        ArmorType.BOOTS, 1), 15, sound(RegisterSounds.EQUIP_OSMIUM_CHAINMAIL), 2.0f, 0.2f, repairTag("osmium"), layer("osmium_chainmail"));

    public static final ArmorMaterial PALLADIUM = new ArmorMaterial(28, Map.of(
        ArmorType.HELMET, 3,
        ArmorType.CHESTPLATE, 8,
        ArmorType.LEGGINGS, 6,
        ArmorType.BOOTS, 3,
        ArmorType.BODY, 11), 16, sound(RegisterSounds.EQUIP_PALLADIUM), 0.0f, 0.0f, repairTag("palladium"), layer("palladium"));

    public static final ArmorMaterial PROMETHEUM = new ArmorMaterial(18, Map.of(
        ArmorType.HELMET, 3,
        ArmorType.CHESTPLATE, 8,
        ArmorType.LEGGINGS, 6,
        ArmorType.BOOTS, 3,
        ArmorType.BODY, 11), 17, sound(RegisterSounds.EQUIP_PROMETHEUM), 1.0f, 0.0f, repairTag("prometheum"), layer("prometheum"));

    public static final ArmorMaterial RUNITE = new ArmorMaterial(27, Map.of(
        ArmorType.HELMET, 3,
        ArmorType.CHESTPLATE, 8,
        ArmorType.LEGGINGS, 6,
        ArmorType.BOOTS, 3), 13, sound(RegisterSounds.EQUIP_RUNITE), 2.0f, 0.0f, repairTag("runite"), layer("runite"));

    public static final ArmorMaterial SILVER = new ArmorMaterial(10, Map.of(
        ArmorType.HELMET, 2,
        ArmorType.CHESTPLATE, 4,
        ArmorType.LEGGINGS, 3,
        ArmorType.BOOTS, 1,
        ArmorType.BODY, 5), 20, sound(RegisterSounds.EQUIP_SILVER), 0.0f, 0.0f, repairTagCommon("silver"), layer("silver"));

    public static final ArmorMaterial STAR_PLATINUM = new ArmorMaterial(34, Map.of(
        ArmorType.HELMET, 3,
        ArmorType.CHESTPLATE, 7,
        ArmorType.LEGGINGS, 7,
        ArmorType.BOOTS, 3,
        ArmorType.BODY, 11), 18, sound(RegisterSounds.EQUIP_STAR_PLATINUM), 2.0f, 0.1f, repairTag("star_platinum"), layer("star_platinum"));

    public static final ArmorMaterial STEEL = new ArmorMaterial(20, Map.of(
        ArmorType.HELMET, 2,
        ArmorType.CHESTPLATE, 6,
        ArmorType.LEGGINGS, 5,
        ArmorType.BOOTS, 2,
        ArmorType.BODY, 9), 10, sound(RegisterSounds.EQUIP_STEEL), 0.5f, 0.0f, repairTagCommon("steel"), layer("steel"));

    public static final ArmorMaterial STORMYX = new ArmorMaterial(30, Map.of(
        ArmorType.HELMET, 3,
        ArmorType.CHESTPLATE, 8,
        ArmorType.LEGGINGS, 6,
        ArmorType.BOOTS, 3,
        ArmorType.BODY, 11), 30, sound(RegisterSounds.EQUIP_STORMYX), 2.0f, 0.0f, repairTag("stormyx"), layer("stormyx"));

    public static final ArmorMaterial TIDESINGER = new ArmorMaterial(32, Map.of(
        ArmorType.HELMET, 3,
        ArmorType.CHESTPLATE, 8,
        ArmorType.LEGGINGS, 6,
        ArmorType.BOOTS, 3,
        ArmorType.BODY, 13), 16, sound(RegisterSounds.EQUIP_TIDESINGER), 2.0f, 0.0f, repairTag("aquarium"), layer("tidesinger"));

    private static Holder<SoundEvent> sound(SoundEvent sound) {
        return BuiltInRegistries.SOUND_EVENT.wrapAsHolder(sound);
    }

    private static ResourceKey<EquipmentAsset> layer(String name) {
        return RegistryHelper.equipmentAsset(name);
    }

    private static TagKey<Item> repairTag(String material) {
        return TagKey.create(Registries.ITEM, RegistryHelper.id("ingots/" + material));
    }

    private static TagKey<Item> repairTagCommon(String material) {
        return TagKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath("c", "ingots/" + material));
    }
}
