package com.mythicmetals.armor;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.mythicmetals.MythicMetals;
import com.mythicmetals.component.MythicDataComponents;
import com.mythicmetals.component.PrometheumComponent;
import com.mythicmetals.entity.MythicEntityAttributes;
import com.mythicmetals.misc.RegistryHelper;
import com.mythicmetals.registry.RegisterSounds;
import de.dafuqs.additionalentityattributes.AdditionalEntityAttributes;
import io.wispforest.owo.registration.reflect.SimpleFieldProcessingSubject;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.EquippableComponent;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.util.Rarity;
import net.minecraft.util.Unit;
import java.lang.reflect.Field;
import java.util.List;

import static net.minecraft.component.type.AttributeModifierSlot.*;
import static net.minecraft.component.type.AttributeModifierSlot.ARMOR;
import static net.minecraft.entity.attribute.EntityAttributeModifier.Operation.*;
import static net.minecraft.entity.attribute.EntityAttributes.*;

// TODO - Maybe configurable armor attributes is viable?
@SuppressWarnings("unused")
public class MythicArmor implements SimpleFieldProcessingSubject<ArmorSet> {
    public static final BiMap<String, ArmorSet> ARMOR_MAP = HashBiMap.create();
    public static final ArmorSet ADAMANTITE = new AdamantiteArmorSet(MythicArmorMaterials.ADAMANTITE);
    public static final ArmorSet AQUARIUM = new ArmorSet(
        "aquarium",
        MythicArmorMaterials.AQUARIUM,
        List.of(
            new ArmorSet.AttributeModifier(SUBMERGED_MINING_SPEED, 1.0, ADD_MULTIPLIED_TOTAL, HEAD),
            new ArmorSet.AttributeModifier(OXYGEN_BONUS, 1.0, ADD_VALUE, CHEST),
            new ArmorSet.AttributeModifier(OXYGEN_BONUS, 1.0, ADD_VALUE, LEGS),
            new ArmorSet.AttributeModifier(WATER_MOVEMENT_EFFICIENCY, 0.5, ADD_MULTIPLIED_TOTAL, FEET)
        )
    );
    public static final ArmorSet BANGLUM = new ArmorSet("banglum", MythicArmorMaterials.BANGLUM);
    public static final ArmorSet BRONZE = new ArmorSet("bronze", MythicArmorMaterials.BRONZE);
    public static final ArmorSet CARMOT = new ArmorSet(
        "carmot",
        MythicArmorMaterials.CARMOT,
        List.of(
            new ArmorSet.AttributeModifier(MAX_HEALTH, 2.0, ADD_VALUE, ARMOR),
            new ArmorSet.AttributeModifier(MythicEntityAttributes.CARMOT_SHIELD, 5.0, ADD_VALUE, ARMOR)
        ),
        settings -> settings.rarity(Rarity.UNCOMMON)
    );
    public static final ArmorSet CELESTIUM = new ArmorSet(
        "celestium",
        MythicArmorMaterials.CELESTIUM,
        List.of(
            new ArmorSet.AttributeModifier(MOVEMENT_SPEED, 0.1, ADD_MULTIPLIED_TOTAL, ARMOR),
            new ArmorSet.AttributeModifier(ATTACK_DAMAGE, 1.0, ADD_VALUE, ARMOR)
        ),
        settings -> settings.rarity(Rarity.RARE).fireproof()
    );
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
    public static final ArmorSet LEGENDARY_BANGLUM = new BanglumArmorSet(MythicArmorMaterials.LEGENDARY_BANGLUM, settings -> settings.rarity(Rarity.UNCOMMON));
    public static final ArmorSet METALLURGIUM = new MetallurgiumArmorSet(MythicArmorMaterials.METALLURGIUM, settings -> settings.fireproof().rarity(Rarity.RARE));
    public static final ArmorSet MIDAS_GOLD = new ArmorSet(
        "midas_gold",
        MythicArmorMaterials.MIDAS_GOLD,
        List.of(
            new ArmorSet.AttributeModifier(LUCK, 1.0, ADD_VALUE, ARMOR)
        )
    );
    public static final ArmorSet MYTHRIL = new ArmorSet("mythril", MythicArmorMaterials.MYTHRIL);
    public static final ArmorSet ORICHALCUM = new ArmorSet("orichalcum", MythicArmorMaterials.ORICHALCUM);
    public static final ArmorSet OSMIUM = new ArmorSet("osmium", MythicArmorMaterials.OSMIUM);
    public static final ArmorSet OSMIUM_CHAINMAIL = new ArmorSet("osmium_chainmail", MythicArmorMaterials.OSMIUM_CHAINMAIL);
    public static final ArmorSet PALLADIUM = new PalladiumArmorSet(
        "palladium",
        MythicArmorMaterials.PALLADIUM,
        List.of(
            new ArmorSet.AttributeModifier(AdditionalEntityAttributes.LAVA_VISIBILITY, 2.0, ADD_VALUE, HEAD),
            new ArmorSet.AttributeModifier(AdditionalEntityAttributes.LAVA_SPEED, 2.0, ADD_VALUE, CHEST),
            new ArmorSet.AttributeModifier(AdditionalEntityAttributes.LAVA_SPEED, 2.0, ADD_VALUE, LEGS),
            new ArmorSet.AttributeModifier(AdditionalEntityAttributes.LAVA_SPEED, 2.0, ADD_VALUE, FEET),
            new ArmorSet.AttributeModifier(BURNING_TIME, -0.25, ADD_MULTIPLIED_BASE, ARMOR)
        ),
        Item.Settings::fireproof
    );
    public static final ArmorSet PROMETHEUM = new ArmorSet("prometheum", MythicArmorMaterials.PROMETHEUM, settings -> settings.component(MythicDataComponents.PROMETHEUM, PrometheumComponent.DEFAULT));
    public static final ArmorSet RUNITE = new RuniteArmorSet(MythicArmorMaterials.RUNITE);
    public static final ArmorSet SILVER = new ArmorSet("silver", MythicArmorMaterials.SILVER);
    public static final ArmorSet STAR_PLATINUM = new ArmorSet(
        "star_platinum",
        MythicArmorMaterials.STAR_PLATINUM,
        List.of(
            new ArmorSet.AttributeModifier(ATTACK_DAMAGE, 1.0, ADD_VALUE, ARMOR)
        )
    );
    public static final ArmorSet STEEL = new ArmorSet("steel", MythicArmorMaterials.STEEL);
    public static final ArmorSet STORMYX = new ArmorSet(
        "stormyx",
        MythicArmorMaterials.STORMYX,
        List.of(
            new ArmorSet.AttributeModifier(AdditionalEntityAttributes.MAGIC_PROTECTION, 2.0, ADD_VALUE, ARMOR)
        )
    );
    public static final ArmorSet TIDESINGER = new TidesingerArmorSet(MythicArmorMaterials.TIDESINGER, List.of(
        new ArmorSet.AttributeModifier(AdditionalEntityAttributes.WATER_VISIBILITY, 0.3, ADD_MULTIPLIED_TOTAL, HEAD),
        new ArmorSet.AttributeModifier(SUBMERGED_MINING_SPEED, 3.0, ADD_MULTIPLIED_TOTAL, HEAD),
        new ArmorSet.AttributeModifier(OXYGEN_BONUS, 2.0, ADD_VALUE, CHEST),
        new ArmorSet.AttributeModifier(OXYGEN_BONUS, 2.0, ADD_VALUE, LEGS),
        new ArmorSet.AttributeModifier(WATER_MOVEMENT_EFFICIENCY, 1.0, ADD_MULTIPLIED_TOTAL, FEET)
    ));

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
