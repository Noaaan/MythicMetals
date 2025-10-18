package com.mythicmetals.armor;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.mythicmetals.MythicMetals;
import com.mythicmetals.component.MythicDataComponents;
import com.mythicmetals.component.PrometheumComponent;
import com.mythicmetals.misc.RegistryHelper;
import com.mythicmetals.registry.RegisterSounds;
import io.wispforest.owo.registration.reflect.SimpleFieldProcessingSubject;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.EquippableComponent;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.util.Rarity;
import net.minecraft.util.Unit;
import java.lang.reflect.Field;

@SuppressWarnings("unused")
public class MythicArmor implements SimpleFieldProcessingSubject<ArmorSet> {
    public static final BiMap<String, ArmorSet> ARMOR_MAP = HashBiMap.create();
    public static final ArmorSet ADAMANTITE = new ArmorSet("adamantite", MythicArmorMaterials.ADAMANTITE);
    public static final ArmorSet AQUARIUM = new ArmorSet("aquarium", MythicArmorMaterials.AQUARIUM);
    public static final ArmorSet BANGLUM = new ArmorSet("banglum", MythicArmorMaterials.BANGLUM);
    public static final ArmorSet BRONZE = new ArmorSet("bronze", MythicArmorMaterials.BRONZE);
    public static final ArmorSet CARMOT = new ArmorSet("carmot", MythicArmorMaterials.CARMOT, settings -> settings.rarity(Rarity.UNCOMMON));
    public static final ArmorSet CELESTIUM = new ArmorSet("celestium", MythicArmorMaterials.CELESTIUM, settings -> settings.rarity(Rarity.RARE).fireproof());
    public static final Item CELESTIUM_ELYTRA = new CelestiumElytra(new Item.Settings()
        .maxDamage(832)
        .registryKey(RegistryHelper.itemKey("celestium_elytra"))
        .rarity(Rarity.EPIC)
        .component(DataComponentTypes.GLIDER, Unit.INSTANCE)
        .component(DataComponentTypes.EQUIPPABLE, EquippableComponent.builder(EquipmentSlot.CHEST)
            .equipSound(RegistryHelper.getEntry(RegisterSounds.EQUIP_CELESTIUM_ELYTRA))
            .build())
        .group(MythicMetals.TABBED_GROUP).tab(3)
        .attributeModifiers(CelestiumElytra.createDefaultAttributes())
    );
    public static final ArmorSet COPPER = new ArmorSet("copper", MythicArmorMaterials.COPPER);
    public static final ArmorSet DURASTEEL = new ArmorSet("durasteel", MythicArmorMaterials.DURASTEEL);
    public static final ArmorSet HALLOWED = new HallowedArmorSet(MythicArmorMaterials.HALLOWED, settings -> settings.rarity(Rarity.UNCOMMON));
    public static final ArmorSet KYBER = new ArmorSet("kyber", MythicArmorMaterials.KYBER);
    public static final ArmorSet LEGENDARY_BANGLUM = new BanglumArmorSet(MythicArmorMaterials.LEGENDARY_BANGLUM, 28, settings -> settings.rarity(Rarity.UNCOMMON));
    public static final ArmorSet METALLURGIUM = new MetallurgiumArmorSet(MythicArmorMaterials.METALLURGIUM, settings -> settings.fireproof().rarity(Rarity.RARE));
    public static final ArmorSet MIDAS_GOLD = new ArmorSet("midas_gold", MythicArmorMaterials.MIDAS_GOLD);
    public static final ArmorSet MYTHRIL = new ArmorSet("mythril", MythicArmorMaterials.MYTHRIL);
    public static final ArmorSet ORICHALCUM = new ArmorSet("orichalcum", MythicArmorMaterials.ORICHALCUM);
    public static final ArmorSet OSMIUM = new ArmorSet("osmium", MythicArmorMaterials.OSMIUM);
    public static final ArmorSet OSMIUM_CHAINMAIL = new ArmorSet("osmium_chainmail", MythicArmorMaterials.OSMIUM_CHAINMAIL);
    public static final ArmorSet PALLADIUM = new ArmorSet("palladium", MythicArmorMaterials.PALLADIUM, Item.Settings::fireproof);
    public static final ArmorSet PROMETHEUM = new ArmorSet("prometheum", MythicArmorMaterials.PROMETHEUM, settings -> settings.component(MythicDataComponents.PROMETHEUM, PrometheumComponent.DEFAULT));
    public static final ArmorSet RUNITE = new RuniteArmorSet(MythicArmorMaterials.RUNITE);
    public static final ArmorSet SILVER = new ArmorSet("silver", MythicArmorMaterials.SILVER);
    public static final ArmorSet STAR_PLATINUM = new ArmorSet("star_platinum", MythicArmorMaterials.STAR_PLATINUM);
    public static final ArmorSet STEEL = new ArmorSet("steel", MythicArmorMaterials.STEEL);
    public static final ArmorSet STORMYX = new ArmorSet("stormyx", MythicArmorMaterials.STORMYX);
    public static final ArmorSet TIDESINGER = new TidesingerArmorSet(MythicArmorMaterials.TIDESINGER);

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
