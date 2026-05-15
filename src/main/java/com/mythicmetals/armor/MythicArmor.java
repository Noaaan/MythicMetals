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
