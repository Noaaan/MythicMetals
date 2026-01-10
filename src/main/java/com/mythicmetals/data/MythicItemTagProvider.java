package com.mythicmetals.data;


import com.mythicmetals.armor.ArmorSet;
import com.mythicmetals.armor.MythicArmor;
import com.mythicmetals.block.BlockSet;
import com.mythicmetals.block.MythicBlocks;
import com.mythicmetals.item.ItemSet;
import com.mythicmetals.item.MythicItems;
import com.mythicmetals.item.tools.MythicTools;
import com.mythicmetals.item.tools.ToolSet;
import io.wispforest.owo.util.ReflectionUtils;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalItemTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import java.util.concurrent.CompletableFuture;

public class MythicItemTagProvider extends FabricTagProvider.ItemTagProvider {

    public MythicItemTagProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> completableFuture) {
        super(output, completableFuture);
    }

    @SuppressWarnings("UnstableApiUsage")
    @Override
    protected void addTags(HolderLookup.Provider arg) {
        ReflectionUtils.iterateAccessibleStaticFields(MythicBlocks.class, BlockSet.class, (blockSet, name, field) -> {
            if (blockSet.getOre() != null) {
                var string = "ores/" + name;
                var modTag = MythicMetalsData.createModItemTag(string);
                var commonTag = ConventionalItemTags.ORES;
                var tagBuilder = getOrCreateTagBuilder(modTag).add(blockSet.getOre().asItem());
                getOrCreateTagBuilder(commonTag).addTag(modTag);

                if (!blockSet.getOreVariants().isEmpty()) {
                    blockSet.getOreVariants().forEach(block -> tagBuilder.add(block.asItem()));
                }
            }

            if (blockSet.getStorageBlock() != null) {
                var string = "storage_blocks/" + name;
                var modTag = MythicMetalsData.createModItemTag(string);
                var commonTag = MythicMetalsData.createCommonItemTag(string);
                var commonBlocksTag = ConventionalItemTags.STORAGE_BLOCKS;
                getOrCreateTagBuilder(modTag).add(blockSet.getStorageBlock().asItem());
                getOrCreateTagBuilder(commonTag).add(blockSet.getStorageBlock().asItem());
                getOrCreateTagBuilder(commonBlocksTag).addTag(modTag);
                if (blockSet.getOreStorageBlock() != null) {
                    string = "storage_blocks/raw_" + name;
                    modTag = MythicMetalsData.createModItemTag(string);
                    getOrCreateTagBuilder(modTag)
                        .add(blockSet.getOreStorageBlock().asItem());
                    getOrCreateTagBuilder(commonBlocksTag)
                        .addTag(modTag);
                }
            }
        });

        ReflectionUtils.iterateAccessibleStaticFields(MythicItems.class, ItemSet.class, (itemSet, name, field) -> {
            /*
             * Create ingot tags. Example:
             * Adamantite Ingot is added to the following:
             * #mythicmetals:adamantite_ingots
             * #c:adamantite_ingots
             * #mythicmetals:ingots
             * At the end #mythicmetals:ingots is nested into #c:ingots
             */
            var modIngotTag = MythicMetalsData.createModItemTag(ConventionalItemTags.INGOTS.location().getPath());
            var commonIngotTag = ConventionalItemTags.INGOTS;
            if (itemSet.getIngot() != null) {
                // Star Platinum is explicitly named, so this is for handling that edge case
                var string = ConventionalItemTags.INGOTS.location().getPath() + "/" + name;
                var modTag = MythicMetalsData.createModItemTag(string);
                var commonTag = MythicMetalsData.createCommonItemTag(string);
                getOrCreateTagBuilder(modTag).add(itemSet.getIngot());
                getOrCreateTagBuilder(commonTag).addTag(modTag);
                getOrCreateTagBuilder(modIngotTag).add(itemSet.getIngot());
            }
            getOrCreateTagBuilder(commonIngotTag).addTag(modIngotTag);

            /*
             * Create raw ore tags. Example:
             * Raw Adamantite is added to the following:
             * - #mythicmetals:raw_materials/adamantite
             * - #c:raw_materials/adamantite
             * - #mythicmetals:raw_materials
             */
            if (itemSet.getRawOre() != null) {
                var string = "raw_materials/" + name;
                var modRawOreTag = MythicMetalsData.createModItemTag(ConventionalItemTags.RAW_MATERIALS.location().getPath());

                // Edge case: Midas Gold can combine with any raw ore to make gold, except itself
                var midasRawOreTag = MythicMetalsData.createModItemTag("midas_raw_ores");
                if (!itemSet.equals(MythicItems.MIDAS_GOLD)) {
                    getOrCreateTagBuilder(midasRawOreTag).add(itemSet.getRawOre());
                }
                var modTag = MythicMetalsData.createModItemTag(string);
                var commonTag = ConventionalItemTags.RAW_MATERIALS;
                getOrCreateTagBuilder(modTag)
                    .add(itemSet.getRawOre());
                getOrCreateTagBuilder(modRawOreTag)
                    .add(itemSet.getRawOre());
                getOrCreateTagBuilder(commonTag)
                    .addTag(modTag);
            }

            /*
             * Create nugget tags. Example:
             * Adamantite Nugget is added to the following:
             * #mythicmetals:nuggets/adamantite
             * #c:nuggets/adamantite
             * #mythicmetals:nuggets
             */
            if (itemSet.getNugget() != null) {
                var string = "nuggets/" + name;
                var modRawOreTag = MythicMetalsData.createModItemTag("nuggets");

                var modTag = MythicMetalsData.createModItemTag(string);
                var commonTag = ConventionalItemTags.NUGGETS;
                getOrCreateTagBuilder(modTag)
                    .addOptional(BuiltInRegistries.ITEM.getKey(itemSet.getNugget()));
                getOrCreateTagBuilder(modRawOreTag)
                    .addOptional(BuiltInRegistries.ITEM.getKey(itemSet.getNugget()));
                getOrCreateTagBuilder(commonTag)
                    .addOptionalTag(modTag);
            }
        });

        ReflectionUtils.iterateAccessibleStaticFields(MythicItems.Mats.class, Item.class, (item, name, field) -> {
            if (item.equals(MythicItems.Mats.STARRITE) || item.equals(MythicItems.Mats.UNOBTAINIUM)) {
                var modTag = MythicMetalsData.createModItemTag(name);
                var commonTag = MythicMetalsData.createCommonItemTag(name);
                getOrCreateTagBuilder(modTag).add(item);
                getOrCreateTagBuilder(commonTag).addTag(modTag);
            } else {
                var rareMaterials = MythicMetalsData.createModItemTag("rare_materials");
                getOrCreateTagBuilder(rareMaterials).add(item);
            }
        });

        ReflectionUtils.iterateAccessibleStaticFields(MythicTools.class, ToolSet.class, (toolSet, name, field) -> {
            var toolModTag = MythicMetalsData.createModItemTag("tools/" + name);
            var equipmentModTag = MythicMetalsData.createModItemTag("equipment/" + name);
            var toolsModTag = MythicMetalsData.createModItemTag("tools");
            var commonTag = ConventionalItemTags.TOOLS;
            var commonEquipmentTag = MythicMetalsData.createCommonItemTag("equipment");

            // Add to tool tags
            var toolArray = toolSet.get().toArray(new Item[0]);
            getOrCreateTagBuilder(toolModTag)
                .add(toolArray);
            getOrCreateTagBuilder(toolsModTag)
                .add(toolArray);
            getOrCreateTagBuilder(equipmentModTag)
                .add(toolArray);
            getOrCreateTagBuilder(commonTag)
                .addTag(toolModTag);
            getOrCreateTagBuilder(commonEquipmentTag)
                .addTag(equipmentModTag);

            // Melee weapons
            getOrCreateTagBuilder(MythicMetalsData.createModItemTag(ConventionalItemTags.MELEE_WEAPON_TOOLS.location().getPath()))
                .add(toolSet.getSword())
                .add(toolSet.getAxe());
            getOrCreateTagBuilder(ConventionalItemTags.MELEE_WEAPON_TOOLS)
                .add(toolSet.getSword())
                .add(toolSet.getAxe());

            // Swords
            getOrCreateTagBuilder(MythicMetalsData.createModItemTag("swords"))
                .add(toolSet.getSword());
            getOrCreateTagBuilder(ItemTags.SWORDS)
                .add(toolSet.getSword());

            // Mining tools
            getOrCreateTagBuilder(MythicMetalsData.createModItemTag(ConventionalItemTags.MINING_TOOL_TOOLS.location().getPath()))
                .add(toolSet.getPickaxe());
            getOrCreateTagBuilder(ConventionalItemTags.MINING_TOOL_TOOLS)
                .add(toolSet.getPickaxe());

            // Pickaxes
            getOrCreateTagBuilder(MythicMetalsData.createModItemTag("pickaxes"))
                .add(toolSet.getPickaxe());
            getOrCreateTagBuilder(ItemTags.PICKAXES)
                .add(toolSet.getPickaxe());

            // Axes
            getOrCreateTagBuilder(MythicMetalsData.createModItemTag("axes"))
                .add(toolSet.getAxe());
            getOrCreateTagBuilder(ItemTags.AXES)
                .add(toolSet.getAxe());

            // Shovels
            getOrCreateTagBuilder(MythicMetalsData.createModItemTag("shovels"))
                .add(toolSet.getShovel());
            getOrCreateTagBuilder(ItemTags.SHOVELS)
                .add(toolSet.getShovel());

            // Hoes
            getOrCreateTagBuilder(MythicMetalsData.createModItemTag("hoes"))
                .add(toolSet.getHoe());
            getOrCreateTagBuilder(ItemTags.HOES)
                .add(toolSet.getHoe());


        });

        // Edge cases from Mythic Tools
        // Swords
        getOrCreateTagBuilder(MythicMetalsData.createModItemTag(ConventionalItemTags.MELEE_WEAPON_TOOLS.location().getPath()))
            .add(MythicTools.RED_AEGIS_SWORD)
            .add(MythicTools.WHITE_AEGIS_SWORD)
            .add(MythicTools.MIDAS_GOLD_SWORD)
            .add(MythicTools.GILDED_MIDAS_GOLD_SWORD)
            .add(MythicTools.ROYAL_MIDAS_GOLD_SWORD);
        getOrCreateTagBuilder(MythicMetalsData.createModItemTag("swords"))
            .add(MythicTools.RED_AEGIS_SWORD)
            .add(MythicTools.WHITE_AEGIS_SWORD)
            .add(MythicTools.MIDAS_GOLD_SWORD)
            .add(MythicTools.GILDED_MIDAS_GOLD_SWORD)
            .add(MythicTools.ROYAL_MIDAS_GOLD_SWORD);
        getOrCreateTagBuilder(ConventionalItemTags.MELEE_WEAPON_TOOLS)
            .add(MythicTools.RED_AEGIS_SWORD)
            .add(MythicTools.WHITE_AEGIS_SWORD)
            .add(MythicTools.MIDAS_GOLD_SWORD)
            .add(MythicTools.GILDED_MIDAS_GOLD_SWORD)
            .add(MythicTools.ROYAL_MIDAS_GOLD_SWORD);
        getOrCreateTagBuilder(ItemTags.SWORD_ENCHANTABLE)
            .add(MythicTools.RED_AEGIS_SWORD)
            .add(MythicTools.WHITE_AEGIS_SWORD)
            .add(MythicTools.MIDAS_GOLD_SWORD)
            .add(MythicTools.GILDED_MIDAS_GOLD_SWORD)
            .add(MythicTools.ROYAL_MIDAS_GOLD_SWORD);
        // Mining Tools + Pickaxe Tag
        getOrCreateTagBuilder(ItemTags.PICKAXES)
            .add(MythicTools.MYTHRIL_DRILL)
            .add(MythicTools.ORICHALCUM_HAMMER);
        getOrCreateTagBuilder(MythicMetalsData.createModItemTag("pickaxes"))
            .add(MythicTools.MYTHRIL_DRILL)
            .add(MythicTools.ORICHALCUM_HAMMER);
        getOrCreateTagBuilder(MythicMetalsData.createModItemTag(ConventionalItemTags.MINING_TOOL_TOOLS.location().getPath()))
            .add(MythicTools.MYTHRIL_DRILL)
            .add(MythicTools.ORICHALCUM_HAMMER);
        getOrCreateTagBuilder(ConventionalItemTags.MINING_TOOL_TOOLS)
            .add(MythicTools.MYTHRIL_DRILL)
            .add(MythicTools.ORICHALCUM_HAMMER);
        // Arrows
        getOrCreateTagBuilder(MythicMetalsData.createModItemTag("arrows"))
            .add(MythicTools.RUNITE_ARROW)
            .add(MythicTools.TIPPED_RUNITE_ARROW)
            .add(MythicTools.STAR_PLATINUM_ARROW);
        getOrCreateTagBuilder(MythicMetalsData.createCommonItemTag("arrows"))
            .add(MythicTools.RUNITE_ARROW)
            .add(MythicTools.TIPPED_RUNITE_ARROW)
            .add(MythicTools.STAR_PLATINUM_ARROW);
        // Shields
        getOrCreateTagBuilder(MythicMetalsData.createModItemTag(ConventionalItemTags.SHIELD_TOOLS.location().getPath()))
            .add(MythicTools.STORMYX_SHIELD);
        getOrCreateTagBuilder(ConventionalItemTags.SHIELD_TOOLS)
            .add(MythicTools.STORMYX_SHIELD);

        ReflectionUtils.iterateAccessibleStaticFields(MythicArmor.class, ArmorSet.class, (armorSet, name, field) -> {
            var modTag = MythicMetalsData.createModItemTag("armor/" + name);
            var modArmorTag = MythicMetalsData.createModItemTag("armor");
            TagKey<Item> modEquipmentTag;
            var commonTag = ConventionalItemTags.ARMORS;
            var commonEquipmentTag = MythicMetalsData.createModItemTag("equipment");
            // Edge case - Osmium Chainmail is Osmium Equipment
            if (armorSet.equals(MythicArmor.OSMIUM_CHAINMAIL)) {
                modEquipmentTag = MythicMetalsData.createModItemTag("equipment/osmium");
                armorSet.getArmorItems().forEach(armorItem -> {
                    getOrCreateTagBuilder(modTag).add(armorItem);
                    getOrCreateTagBuilder(modEquipmentTag).add(armorItem);
                });
            } else {
                modEquipmentTag = MythicMetalsData.createModItemTag("equipment/" + name);
                getOrCreateTagBuilder(ItemTags.HEAD_ARMOR_ENCHANTABLE).add(armorSet.getHelmet());
                getOrCreateTagBuilder(ItemTags.HEAD_ARMOR).add(armorSet.getHelmet());
                getOrCreateTagBuilder(ItemTags.CHEST_ARMOR_ENCHANTABLE).add(armorSet.getChestplate());
                getOrCreateTagBuilder(ItemTags.CHEST_ARMOR).add(armorSet.getChestplate());
                getOrCreateTagBuilder(ItemTags.LEG_ARMOR_ENCHANTABLE).add(armorSet.getLeggings());
                getOrCreateTagBuilder(ItemTags.LEG_ARMOR).add(armorSet.getLeggings());
                getOrCreateTagBuilder(ItemTags.FOOT_ARMOR_ENCHANTABLE).add(armorSet.getBoots());
                getOrCreateTagBuilder(ItemTags.FOOT_ARMOR).add(armorSet.getBoots());
                getOrCreateTagBuilder(modTag).add(armorSet.getHelmet(), armorSet.getChestplate(), armorSet.getLeggings(), armorSet.getBoots());
                getOrCreateTagBuilder(modEquipmentTag).add(armorSet.getHelmet(), armorSet.getChestplate(), armorSet.getLeggings(), armorSet.getBoots());
            }
            tag(modArmorTag).addTag(modTag);
            tag(commonTag).addTag(modTag);
            tag(commonEquipmentTag).addTag(modEquipmentTag);
        });

        /*
         * Edge cases for Mythic Armor (The Celestium Elytra)
         */
        getOrCreateTagBuilder(MythicMetalsData.createModItemTag("equipment/celestium"))
            .add(MythicArmor.CELESTIUM_ELYTRA);
        getOrCreateTagBuilder(MythicMetalsData.createModItemTag("armor/celestium"))
            .add(MythicArmor.CELESTIUM_ELYTRA);
        getOrCreateTagBuilder(MythicMetalsData.createModItemTag("elytra"))
            .add(MythicArmor.CELESTIUM_ELYTRA);
        getOrCreateTagBuilder(MythicMetalsData.createCommonItemTag("elytra"))
            .add(MythicArmor.CELESTIUM_ELYTRA);

        ReflectionUtils.iterateAccessibleStaticFields(MythicItems.Templates.class, Item.class, (item, name, field) -> {
            var modTag = MythicMetalsData.createModItemTag("smithing_templates");
            var commonTag = MythicMetalsData.createCommonItemTag("smithing_templates");
            getOrCreateTagBuilder(modTag).add(item);
            getOrCreateTagBuilder(commonTag).add(item);
        });

    }
}
