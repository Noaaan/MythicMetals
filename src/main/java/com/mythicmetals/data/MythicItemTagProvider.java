package com.mythicmetals.data;

import com.mythicmetals.api.v2.*;
import com.mythicmetals.item.MythicMaterials;
import com.mythicmetals.item.armor.MythicArmorSets;
import com.mythicmetals.item.tools.MythicTools;
import io.wispforest.owo.util.ReflectionUtils;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalItemTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.tags.ItemTags;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import static com.mythicmetals.data.MythicMetalsDatagen.*;

@SuppressWarnings("UnstableApiUsage")
public class MythicItemTagProvider extends FabricTagsProvider.ItemTagsProvider {

    public MythicItemTagProvider(
        FabricPackOutput output,
        CompletableFuture<HolderLookup.Provider> registriesFuture
    ) {
        super(output, registriesFuture);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        ReflectionUtils.iterateAccessibleStaticFields(MythicMaterials.class, Material.class, (material, name, _) -> {
            switch (material.materialType()) {
                case RARE_ALLOY, ALLOY, INGOT -> {
                    valueLookupBuilder(createModItemTag("ingots/" + material.name())).add(material.baseMaterial());
                    valueLookupBuilder(ConventionalItemTags.INGOTS).add(material.baseMaterial());
                }
                case SPECIAL -> {
                    valueLookupBuilder(MythicTags.RARE_MATERIALS).add(material.baseMaterial());
                }
                default -> {
                    valueLookupBuilder(MythicTags.MATERIALS).add(material.baseMaterial());
                }
            }
            if (material.toolSet() != null) {
                buildToolTags(material.toolSet(), material.name());
            }
            if (material.armorSet() != null) {
                buildArmorTags(material.armorSet(), material.name());
            }
            if (material.blockSet() != null) {
                buildBlockSetItemTags(material.blockSet(), material.name());
            }
            if (material.nugget() != null) {
                valueLookupBuilder(MythicTags.NUGGETS).add(material.nugget());
                valueLookupBuilder(createModItemTag("nuggets/" + material.name())).add(material.nugget());
                valueLookupBuilder(ConventionalItemTags.NUGGETS).add(material.nugget());
            }

            if (material.rawOre() != null) {
                valueLookupBuilder(MythicTags.RAW_MATERIALS).add(material.rawOre());
                valueLookupBuilder(createModItemTag("raw_materials/" + material.name())).add(material.rawOre());
                valueLookupBuilder(ConventionalItemTags.RAW_MATERIALS).add(material.rawOre());
            }
        });

        ReflectionUtils.iterateAccessibleStaticFields(MythicArmorSets.class, ArmorSet.class, (armorSet, name, _) -> {
            buildArmorTags(armorSet, name);
        });

        // extra swords
        var extraSwords = List.of(
            MythicTools.MIDAS_GOLD_SWORD,
            MythicTools.GILDED_MIDAS_GOLD_SWORD,
            MythicTools.ROYAL_MIDAS_GOLD_SWORD,
            MythicTools.RED_AEGIS_SWORD,
            MythicTools.WHITE_AEGIS_SWORD
        );
        valueLookupBuilder(MythicTags.SWORDS).addAll(extraSwords);
    }

    private void buildToolTags(ToolSet toolSet, String name) {
        valueLookupBuilder(ItemTags.SWORDS).add(toolSet.getSword());
        valueLookupBuilder(ItemTags.PICKAXES).add(toolSet.getPickaxe());
        valueLookupBuilder(ItemTags.AXES).add(toolSet.getAxe());
        valueLookupBuilder(ItemTags.SHOVELS).add(toolSet.getShovel());
        valueLookupBuilder(ItemTags.HOES).add(toolSet.getHoe());
        valueLookupBuilder(ItemTags.SPEARS).add(toolSet.getSpear());

        valueLookupBuilder(MythicTags.SWORDS).add(toolSet.getSword());
        valueLookupBuilder(MythicTags.PICKAXES).add(toolSet.getPickaxe());
        valueLookupBuilder(MythicTags.AXES).add(toolSet.getAxe());
        valueLookupBuilder(MythicTags.SHOVELS).add(toolSet.getShovel());
        valueLookupBuilder(MythicTags.HOES).add(toolSet.getHoe());
        valueLookupBuilder(MythicTags.SPEARS).add(toolSet.getSpear());

        var materialToolTag = createModItemTag("tools/" + name);
        var materialEquipmentTag = createModItemTag("equipment/" + name);
        valueLookupBuilder(materialToolTag).addAll(toolSet.getItems());
        valueLookupBuilder(MythicTags.TOOLS).addTag(materialToolTag);
        valueLookupBuilder(ConventionalItemTags.TOOLS).addTag(materialToolTag);

        valueLookupBuilder(ConventionalItemTags.TOOLS).addAll(toolSet.getItems());
        valueLookupBuilder(MythicTags.EQUIPMENT).addTag(materialToolTag);
        valueLookupBuilder(materialEquipmentTag).addAll(toolSet.getItems());
    }

    private void buildArmorTags(ArmorSet armorSet, String name) {
        valueLookupBuilder(ItemTags.HEAD_ARMOR).add(armorSet.getHelmet());
        valueLookupBuilder(ItemTags.CHEST_ARMOR).add(armorSet.getChestplate());
        valueLookupBuilder(ItemTags.LEG_ARMOR).add(armorSet.getLeggings());
        valueLookupBuilder(ItemTags.FOOT_ARMOR).add(armorSet.getBoots());
        var materialArmorTag = createModItemTag("armor/" + name);
        valueLookupBuilder(materialArmorTag).addAll(armorSet.getPlayerItems());
        valueLookupBuilder(MythicTags.ARMOR).addTag(materialArmorTag);
        var materialEquipmentTag = createModItemTag("equipment/" + name);
        var equipmentBuilder = valueLookupBuilder(materialEquipmentTag).addAll(armorSet.getPlayerItems());
        valueLookupBuilder(ConventionalItemTags.HUMANOID_ARMORS).addTag(materialArmorTag);

        if (armorSet.getHorse() != null) {
            equipmentBuilder.add(armorSet.getHorse());
            valueLookupBuilder(ConventionalItemTags.HORSE_ARMORS).add(armorSet.getHorse());
        }
        if (armorSet.getNautilus() != null) {
            equipmentBuilder.add(armorSet.getNautilus());
            valueLookupBuilder(ConventionalItemTags.NAUTILUS_ARMORS).add(armorSet.getNautilus());
        }

        valueLookupBuilder(MythicTags.EQUIPMENT).addTag(materialEquipmentTag);
    }

    private void buildBlockSetItemTags(BlockSet blockSet, String name) {
        valueLookupBuilder(MythicTags.STORAGE_BLOCKS).add(blockSet.storage().asItem());
        valueLookupBuilder(createModItemTag("storage_blocks/" + name)).add(blockSet.storage().asItem());

        if (blockSet.ore() != null) {
            valueLookupBuilder(ConventionalItemTags.ORES).add(blockSet.ore().asItem());
            var materialOreTag = createModItemTag("ores/" + name);
            valueLookupBuilder(materialOreTag).add(blockSet.ore().asItem());

            if (!blockSet.oreVariants().isEmpty()) {
                blockSet.oreVariants().forEach((s, resourceKeyBlockTuple) -> {
                    valueLookupBuilder(ConventionalItemTags.ORES).add(resourceKeyBlockTuple.getB().asItem());
                    valueLookupBuilder(materialOreTag).add(resourceKeyBlockTuple.getB().asItem());
                });
            }
        }
    }
}
