package com.mythicmetals.armor;

import com.mythicmetals.MythicMetals;
import com.mythicmetals.component.MythicDataComponents;
import com.mythicmetals.component.PrometheumComponent;
import com.mythicmetals.misc.RegistryHelper;
import io.wispforest.owo.registration.reflect.SimpleFieldProcessingSubject;
import net.minecraft.item.Item;
import net.minecraft.util.Rarity;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("unused")
public class MythicArmor implements SimpleFieldProcessingSubject<ArmorSet> {
    public static final Map<String, ArmorSet> ARMOR_MAP = new HashMap<>();
    public static final ArmorSet ADAMANTITE = new ArmorSet(MythicArmorMaterials.ADAMANTITE, 30);
    public static final ArmorSet AQUARIUM = new ArmorSet(MythicArmorMaterials.AQUARIUM, 20);
    public static final ArmorSet BANGLUM = new ArmorSet(MythicArmorMaterials.BANGLUM, 14);
    public static final ArmorSet BRONZE = new ArmorSet(MythicArmorMaterials.BRONZE, 15);
    public static final ArmorSet CARMOT = new ArmorSet(MythicArmorMaterials.CARMOT, 26, settings -> settings.rarity(Rarity.UNCOMMON));
    public static final ArmorSet CELESTIUM = new CelestiumArmorSet(MythicArmorMaterials.CELESTIUM, 41, settings -> settings.rarity(Rarity.RARE).fireproof());
    public static final Item CELESTIUM_ELYTRA = new CelestiumElytra(new Item.Settings()
        .rarity(Rarity.RARE)
        .group(MythicMetals.TABBED_GROUP).tab(3)
        .maxDamage(832)
        .attributeModifiers(CelestiumElytra.createDefaultAttributes())
    );
    public static final ArmorSet COPPER = new ArmorSet(MythicArmorMaterials.COPPER, 9);
    public static final ArmorSet DURASTEEL = new ArmorSet(MythicArmorMaterials.DURASTEEL, 25);
    public static final ArmorSet HALLOWED = new HallowedArmorSet(MythicArmorMaterials.HALLOWED, 41, settings -> settings.rarity(Rarity.UNCOMMON));
    public static final ArmorSet KYBER = new ArmorSet(MythicArmorMaterials.KYBER, 21);
    public static final ArmorSet LEGENDARY_BANGLUM = new BanglumArmorSet(MythicArmorMaterials.LEGENDARY_BANGLUM, 28, settings -> settings.rarity(Rarity.UNCOMMON));
    public static final ArmorSet METALLURGIUM = new MetallurgiumArmorSet(MythicArmorMaterials.METALLURGIUM, 69, settings -> settings.fireproof().rarity(Rarity.RARE));
    public static final ArmorSet MIDAS_GOLD = new ArmorSet(MythicArmorMaterials.MIDAS_GOLD, 14);
    public static final ArmorSet MYTHRIL = new ArmorSet(MythicArmorMaterials.MYTHRIL, 31);
    public static final ArmorSet ORICHALCUM = new ArmorSet(MythicArmorMaterials.ORICHALCUM, 40);
    public static final ArmorSet OSMIUM = new ArmorSet(MythicArmorMaterials.OSMIUM, 25);
    public static final ArmorSet OSMIUM_CHAINMAIL = new ArmorSet(MythicArmorMaterials.OSMIUM_CHAINMAIL, 25);
    public static final ArmorSet PALLADIUM = new ArmorSet(MythicArmorMaterials.PALLADIUM, 28, Item.Settings::fireproof);
    public static final ArmorSet PROMETHEUM = new ArmorSet(MythicArmorMaterials.PROMETHEUM, 18, settings -> settings.component(MythicDataComponents.PROMETHEUM, PrometheumComponent.DEFAULT));
    public static final ArmorSet RUNITE = new RuniteArmorSet(MythicArmorMaterials.RUNITE, 27);
    public static final ArmorSet SILVER = new ArmorSet(MythicArmorMaterials.SILVER, 10);
    public static final ArmorSet STAR_PLATINUM = new ArmorSet(MythicArmorMaterials.STAR_PLATINUM, 34);
    public static final ArmorSet STEEL = new ArmorSet(MythicArmorMaterials.STEEL, 20);
    public static final ArmorSet STORMYX = new ArmorSet(MythicArmorMaterials.STORMYX, 30);
    public static final ArmorSet TIDESINGER = new TidesingerArmorSet(MythicArmorMaterials.TIDESINGER, 32, settings -> settings.rarity(Rarity.UNCOMMON));

    @Override
    public void processField(ArmorSet armorSet, String name, Field f) {
        armorSet.register(name);
        ARMOR_MAP.put(name, armorSet);
    }

    @Override
    public void afterFieldProcessing() {
        RegistryHelper.item("celestium_elytra", CELESTIUM_ELYTRA);
    }

    @Override
    public Class<ArmorSet> getTargetFieldType() {
        return ArmorSet.class;
    }
}
