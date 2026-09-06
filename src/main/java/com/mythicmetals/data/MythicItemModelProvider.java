package com.mythicmetals.data;

import com.mythicmetals.api.v2.Material;
import com.mythicmetals.item.MythicMaterials;
import com.mythicmetals.item.tools.MythicTools;
import com.mythicmetals.api.v2.MaterialHelper;
import com.mythicmetals.misc.RegistryHelper;
import io.wispforest.owo.util.ReflectionUtils;
import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.model.ModelTemplates;

public class MythicItemModelProvider extends FabricModelProvider {

    public MythicItemModelProvider(FabricPackOutput output) {
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
            if (material.baseMaterial() != null) {
                itemModelGenerator.generateFlatItem(material.baseMaterial(), ModelTemplates.FLAT_ITEM);
            }
            if (material.rawOre() != null) {
                itemModelGenerator.generateFlatItem(material.rawOre(), ModelTemplates.FLAT_ITEM);
            }
            if (material.nugget() != null) {
                itemModelGenerator.generateFlatItem(material.nugget(), ModelTemplates.FLAT_ITEM);
            }
            if (material.armorSet() != null) {
                itemModelGenerator.generateFlatItem(material.armorSet().getHelmet(), ModelTemplates.FLAT_ITEM);
                itemModelGenerator.generateFlatItem(material.armorSet().getChestplate(), ModelTemplates.FLAT_ITEM);
                itemModelGenerator.generateFlatItem(material.armorSet().getLeggings(), ModelTemplates.FLAT_ITEM);
                itemModelGenerator.generateFlatItem(material.armorSet().getBoots(), ModelTemplates.FLAT_ITEM);
                if (material.armorSet().getHorse() != null) {
                    itemModelGenerator.generateFlatItem(material.armorSet().getHorse(), ModelTemplates.FLAT_ITEM);
                }
                if (material.armorSet().getNautilus() != null) {
                    itemModelGenerator.generateFlatItem(material.armorSet().getNautilus(), ModelTemplates.FLAT_ITEM);
                }
            }
        });
        // Tools
        MaterialHelper.TOOL_MAP.forEach((s, toolSet) -> {
            if (!s.equals("legendary_banglum")) {
                itemModelGenerator.generateFlatItem(toolSet.getSword(), ModelTemplates.FLAT_HANDHELD_ITEM);
                itemModelGenerator.generateFlatItem(toolSet.getAxe(), ModelTemplates.FLAT_HANDHELD_ITEM);
                itemModelGenerator.generateFlatItem(toolSet.getPickaxe(), ModelTemplates.FLAT_HANDHELD_ITEM);
                itemModelGenerator.generateFlatItem(toolSet.getShovel(), ModelTemplates.FLAT_HANDHELD_ITEM);
                itemModelGenerator.generateFlatItem(toolSet.getHoe(), ModelTemplates.FLAT_HANDHELD_ITEM);
            }
        });
        var tippedRuniteArrowLayers = itemModelGenerator.generateLayeredItem(
            MythicTools.TIPPED_RUNITE_ARROW,
            new net.minecraft.client.resources.model.sprite.Material(RegistryHelper.id("item/weapons/tipped_runite_arrow_head")),
            new net.minecraft.client.resources.model.sprite.Material(RegistryHelper.id("item/weapons/tipped_runite_arrow_base"))
        );
        itemModelGenerator.addPotionTint(MythicTools.TIPPED_RUNITE_ARROW, tippedRuniteArrowLayers);
    }
}
