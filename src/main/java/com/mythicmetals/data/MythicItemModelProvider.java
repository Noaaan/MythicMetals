package com.mythicmetals.data;

import com.mythicmetals.api.v2.Material;
import com.mythicmetals.armor.MythicArmor;
import com.mythicmetals.item.MythicItems;
import com.mythicmetals.item.MythicMaterials;
import com.mythicmetals.item.MythicResourceKeys;
import com.mythicmetals.item.tools.MythicTools;
import com.mythicmetals.misc.RegistryHelper;
import io.wispforest.owo.util.ReflectionUtils;
import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.world.item.Item;

public class MythicItemModelProvider extends FabricModelProvider {

    public MythicItemModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockModelGenerators blockStateModelGenerator) {
        // TODO
    }

    @SuppressWarnings("UnstableApiUsage")
    @Override
    public void generateItemModels(ItemModelGenerators itemModelGenerator) {
        // Items
        ReflectionUtils.iterateAccessibleStaticFields(MythicMaterials.class, Material.class, (material, name, field) -> {
            itemModelGenerator.generateFlatItem(material.baseMaterial(), ModelTemplates.FLAT_ITEM);
            if (material.rawOre() != null) {
                itemModelGenerator.generateFlatItem(material.rawOre(), ModelTemplates.FLAT_ITEM);
            }
            if (material.nugget() != null) {
                itemModelGenerator.generateFlatItem(material.nugget(), ModelTemplates.FLAT_ITEM);
            }
        });
//        ReflectionUtils.iterateAccessibleStaticFields(MythicItems.Mats.class, Item.class, (value, name, field) -> {
//            itemModelGenerator.generateFlatItem(value, ModelTemplates.FLAT_ITEM);
//        });
        ReflectionUtils.iterateAccessibleStaticFields(MythicItems.Templates.class, Item.class, (value, name, field) -> {
            itemModelGenerator.generateFlatItem(value, ModelTemplates.FLAT_ITEM);
        });
        // Armor
        MythicArmor.ARMOR_MAP.forEach((s, armorSet) -> {
            itemModelGenerator.generateFlatItem(armorSet.getHelmet(), ModelTemplates.FLAT_ITEM);
            itemModelGenerator.generateFlatItem(armorSet.getChestplate(), ModelTemplates.FLAT_ITEM);
            itemModelGenerator.generateFlatItem(armorSet.getLeggings(), ModelTemplates.FLAT_ITEM);
            itemModelGenerator.generateFlatItem(armorSet.getBoots(), ModelTemplates.FLAT_ITEM);
        });
        itemModelGenerator.generateFlatItem(MythicArmor.CELESTIUM_ELYTRA, ModelTemplates.FLAT_ITEM);

        // Tools
        MythicTools.TOOL_MAP.forEach((s, toolSet) -> {
            if (!s.equals("legendary_banglum")) {
                itemModelGenerator.generateFlatItem(toolSet.getSword(), ModelTemplates.FLAT_HANDHELD_ITEM);
                itemModelGenerator.generateFlatItem(toolSet.getAxe(), ModelTemplates.FLAT_HANDHELD_ITEM);
                itemModelGenerator.generateFlatItem(toolSet.getPickaxe(), ModelTemplates.FLAT_HANDHELD_ITEM);
                itemModelGenerator.generateFlatItem(toolSet.getShovel(), ModelTemplates.FLAT_HANDHELD_ITEM);
                itemModelGenerator.generateFlatItem(toolSet.getHoe(), ModelTemplates.FLAT_HANDHELD_ITEM);
            }
        });
        itemModelGenerator.generateFlatItem(MythicMaterials.BANGLUM.extraItems().get(MythicResourceKeys.BANGLUM_TNT_MINECART), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(MythicMaterials.PALLADIUM.extraItems().get(MythicResourceKeys.PALLADIUM_MINECART), ModelTemplates.FLAT_ITEM);
//        itemModelGenerator.generateFlatItem(MythicTools.CARMOT_BELL, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(MythicTools.STAR_PLATINUM_ARROW, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(MythicTools.RUNITE_ARROW, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(MythicTools.RED_AEGIS_SWORD, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(MythicTools.WHITE_AEGIS_SWORD, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(MythicTools.ORICHALCUM_HAMMER, ModelTemplates.FLAT_HANDHELD_ITEM);
        var tippedRuniteArrowLayers = itemModelGenerator.generateLayeredItem(
            MythicTools.TIPPED_RUNITE_ARROW,
            RegistryHelper.id("item/weapons/tipped_runite_arrow_head"),
            RegistryHelper.id("item/weapons/tipped_runite_arrow_base")
        );
        itemModelGenerator.addPotionTint(MythicTools.TIPPED_RUNITE_ARROW, tippedRuniteArrowLayers);
    }
}
