package com.mythicmetals.data;

import com.mythicmetals.armor.MythicArmor;
import com.mythicmetals.item.ItemSet;
import com.mythicmetals.item.MythicItems;
import com.mythicmetals.item.tools.MythicTools;
import com.mythicmetals.misc.RegistryHelper;
import io.wispforest.owo.util.ReflectionUtils;
import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.client.data.*;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.resources.ResourceLocation;
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
        ReflectionUtils.iterateAccessibleStaticFields(MythicItems.class, ItemSet.class, (itemSet, name, field) -> {
            itemModelGenerator.generateFlatItem(itemSet.getIngot(), ModelTemplates.FLAT_ITEM);
            if (itemSet.getRawOre() != null) {
                itemModelGenerator.generateFlatItem(itemSet.getRawOre(), ModelTemplates.FLAT_ITEM);
            }
            if (itemSet.getNugget() != null) {
                itemModelGenerator.generateFlatItem(itemSet.getNugget(), ModelTemplates.FLAT_ITEM);
            }
        });
        ReflectionUtils.iterateAccessibleStaticFields(MythicItems.Mats.class, Item.class, (value, name, field) -> {
            itemModelGenerator.generateFlatItem(value, ModelTemplates.FLAT_ITEM);
        });
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
        itemModelGenerator.generateFlatItem(MythicTools.BANGLUM_TNT_MINECART, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(MythicTools.PALLADIUM_MINECART, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(MythicTools.CARMOT_BELL, ModelTemplates.FLAT_ITEM);
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
