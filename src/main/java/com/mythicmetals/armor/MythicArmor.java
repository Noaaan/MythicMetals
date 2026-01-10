package com.mythicmetals.armor;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.mythicmetals.MythicAttributeModifier;
import com.mythicmetals.MythicMetals;
import com.mythicmetals.component.MythicDataComponents;
import com.mythicmetals.component.PrometheumComponent;
import com.mythicmetals.entity.MythicEntityAttributes;
import com.mythicmetals.misc.RegistryHelper;
import com.mythicmetals.registry.RegisterSounds;
import de.dafuqs.additionalentityattributes.AdditionalEntityAttributes;
import io.wispforest.owo.registration.reflect.SimpleFieldProcessingSubject;
import net.minecraft.core.component.DataComponents;
import net.minecraft.util.Unit;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.equipment.Equippable;
import java.lang.reflect.Field;
import java.util.List;

import static net.minecraft.world.entity.EquipmentSlotGroup.*;
import static net.minecraft.world.entity.EquipmentSlotGroup.ARMOR;
import static net.minecraft.world.entity.ai.attributes.AttributeModifier.Operation.*;
import static net.minecraft.world.entity.ai.attributes.Attributes.*;

// TODO - Maybe configurable armor attributes is viable?
@SuppressWarnings("unused")
public class MythicArmor implements SimpleFieldProcessingSubject<ArmorSet> {
    public static final BiMap<String, ArmorSet> ARMOR_MAP = HashBiMap.create();
    public static final ArmorSet ADAMANTITE = new AdamantiteArmorSet(MythicArmorMaterials.ADAMANTITE);
    public static final ArmorSet AQUARIUM = new ArmorSet(
        "aquarium",
        MythicArmorMaterials.AQUARIUM,
        List.of(
            new MythicAttributeModifier(SUBMERGED_MINING_SPEED, 1.0, ADD_MULTIPLIED_TOTAL, HEAD),
            new MythicAttributeModifier(OXYGEN_BONUS, 1.0, ADD_VALUE, CHEST),
            new MythicAttributeModifier(OXYGEN_BONUS, 1.0, ADD_VALUE, LEGS),
            new MythicAttributeModifier(WATER_MOVEMENT_EFFICIENCY, 0.5, ADD_MULTIPLIED_TOTAL, FEET)
        )
    );
    public static final ArmorSet BANGLUM = new ArmorSet("banglum", MythicArmorMaterials.BANGLUM);
    public static final ArmorSet BRONZE = new ArmorSet("bronze", MythicArmorMaterials.BRONZE);
    public static final ArmorSet CARMOT = new ArmorSet(
        "carmot",
        MythicArmorMaterials.CARMOT,
        List.of(
            new MythicAttributeModifier(MAX_HEALTH, 2.0, ADD_VALUE, ARMOR),
            new MythicAttributeModifier(MythicEntityAttributes.CARMOT_SHIELD, 5.0, ADD_VALUE, ARMOR)
        ),
        settings -> settings.rarity(Rarity.UNCOMMON)
    );
    public static final ArmorSet CELESTIUM = new ArmorSet(
        "celestium",
        MythicArmorMaterials.CELESTIUM,
        List.of(
            new MythicAttributeModifier(MOVEMENT_SPEED, 0.1, ADD_MULTIPLIED_TOTAL, ARMOR),
            new MythicAttributeModifier(ATTACK_DAMAGE, 1.0, ADD_VALUE, ARMOR)
        ),
        settings -> settings.rarity(Rarity.RARE).fireResistant()
    );
    public static final Item CELESTIUM_ELYTRA = new CelestiumElytra(new Item.Properties()
        .durability(832)
        .setId(RegistryHelper.itemKey("celestium_elytra"))
        .rarity(Rarity.EPIC)
        .component(DataComponents.GLIDER, Unit.INSTANCE)
        .component(DataComponents.EQUIPPABLE, Equippable.builder(EquipmentSlot.CHEST)
            .setAsset(RegistryHelper.equipmentAsset("celestium_elytra"))
            .setEquipSound(RegistryHelper.getEntry(RegisterSounds.EQUIP_CELESTIUM_ELYTRA))
            .build())
        .group(MythicMetals.TABBED_GROUP).tab(3)
        .attributes(CelestiumElytra.createDefaultAttributes())
    );
    public static final ArmorSet COPPER = new ArmorSet("copper", MythicArmorMaterials.COPPER);
    public static final ArmorSet DURASTEEL = new ArmorSet("durasteel", MythicArmorMaterials.DURASTEEL);
    public static final ArmorSet HALLOWED = new HallowedArmorSet(MythicArmorMaterials.HALLOWED, settings -> settings.rarity(Rarity.UNCOMMON));
    public static final ArmorSet KYBER = new ArmorSet("kyber", MythicArmorMaterials.KYBER);
    public static final ArmorSet LEGENDARY_BANGLUM = new BanglumArmorSet(
        MythicArmorMaterials.LEGENDARY_BANGLUM,
        settings -> settings.rarity(Rarity.UNCOMMON),
        List.of(
            new MythicAttributeModifier(SAFE_FALL_DISTANCE, 15, ADD_VALUE, FEET)
        )
    );
    public static final ArmorSet METALLURGIUM = new MetallurgiumArmorSet(MythicArmorMaterials.METALLURGIUM, settings -> settings.fireResistant().rarity(Rarity.RARE));
    public static final ArmorSet MIDAS_GOLD = new ArmorSet(
        "midas_gold",
        MythicArmorMaterials.MIDAS_GOLD,
        List.of(
            new MythicAttributeModifier(LUCK, 1.0, ADD_VALUE, ARMOR)
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
            new MythicAttributeModifier(AdditionalEntityAttributes.LAVA_VISIBILITY, 2.0, ADD_VALUE, HEAD),
            new MythicAttributeModifier(AdditionalEntityAttributes.LAVA_SPEED, 2.0, ADD_VALUE, CHEST),
            new MythicAttributeModifier(AdditionalEntityAttributes.LAVA_SPEED, 2.0, ADD_VALUE, LEGS),
            new MythicAttributeModifier(AdditionalEntityAttributes.LAVA_SPEED, 2.0, ADD_VALUE, FEET),
            new MythicAttributeModifier(BURNING_TIME, -0.25, ADD_MULTIPLIED_BASE, ARMOR)
        ),
        Item.Properties::fireResistant
    );
    public static final ArmorSet PROMETHEUM = new ArmorSet("prometheum", MythicArmorMaterials.PROMETHEUM, settings -> settings.component(MythicDataComponents.PROMETHEUM, PrometheumComponent.DEFAULT));
    public static final ArmorSet RUNITE = new RuniteArmorSet(MythicArmorMaterials.RUNITE);
    public static final ArmorSet SILVER = new ArmorSet("silver", MythicArmorMaterials.SILVER);
    public static final ArmorSet STAR_PLATINUM = new ArmorSet(
        "star_platinum",
        MythicArmorMaterials.STAR_PLATINUM,
        List.of(
            new MythicAttributeModifier(ATTACK_DAMAGE, 1.0, ADD_VALUE, ARMOR)
        )
    );
    public static final ArmorSet STEEL = new ArmorSet("steel", MythicArmorMaterials.STEEL);
    public static final ArmorSet STORMYX = new ArmorSet(
        "stormyx",
        MythicArmorMaterials.STORMYX,
        List.of(
            new MythicAttributeModifier(AdditionalEntityAttributes.MAGIC_PROTECTION, 2.0, ADD_VALUE, ARMOR)
        )
    );
    public static final ArmorSet TIDESINGER = new TidesingerArmorSet(MythicArmorMaterials.TIDESINGER, List.of(
        new MythicAttributeModifier(AdditionalEntityAttributes.WATER_VISIBILITY, 0.3, ADD_MULTIPLIED_TOTAL, HEAD),
        new MythicAttributeModifier(SUBMERGED_MINING_SPEED, 3.0, ADD_MULTIPLIED_TOTAL, HEAD),
        new MythicAttributeModifier(OXYGEN_BONUS, 2.0, ADD_VALUE, CHEST),
        new MythicAttributeModifier(OXYGEN_BONUS, 2.0, ADD_VALUE, LEGS),
        new MythicAttributeModifier(WATER_MOVEMENT_EFFICIENCY, 1.0, ADD_VALUE, FEET)
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
