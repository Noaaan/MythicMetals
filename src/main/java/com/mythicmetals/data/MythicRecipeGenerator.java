package com.mythicmetals.data;

import com.mythicmetals.armor.ArmorSet;
import com.mythicmetals.armor.MythicArmor;
import com.mythicmetals.block.BlockSet;
import com.mythicmetals.block.MythicBlocks;
import com.mythicmetals.component.MythicDataComponents;
import com.mythicmetals.component.TidesingerPatternComponent;
import com.mythicmetals.item.ItemSet;
import com.mythicmetals.item.MythicItems;
import com.mythicmetals.item.tools.MythicTools;
import com.mythicmetals.item.tools.ToolSet;
import com.mythicmetals.misc.RegistryHelper;
import io.wispforest.owo.util.ReflectionUtils;
import net.minecraft.block.Block;
import net.minecraft.data.recipe.*;
import net.minecraft.item.*;
import net.minecraft.predicate.item.ItemPredicate;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.SmithingTransformRecipe;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.*;
import net.minecraft.registry.tag.TagKey;
import java.util.*;

import static com.mythicmetals.misc.RegistryHelper.recipeKey;
import static net.fabricmc.fabric.api.tag.convention.v2.ConventionalItemTags.WOODEN_RODS;

@SuppressWarnings("UnstableApiUsage")
public class MythicRecipeGenerator extends RecipeGenerator {

    public MythicRecipeGenerator(RegistryWrapper.WrapperLookup registries, RecipeExporter exporter, RecipeExporter nuggetExporter) {
        super(registries, exporter);
        itemLookup = registries.getOrThrow(RegistryKeys.ITEM);
        this.nuggetExporter = nuggetExporter;
    }

    private final RegistryEntryLookup<Item> itemLookup;
    private final RecipeExporter nuggetExporter;

    @Override
    public void generate() {

        var itemSets = new HashMap<String, ItemSet>();
        var blockSets = new HashMap<String, BlockSet>();

        // Handle items first, as they store whether the items need blasting to be smelted
        ReflectionUtils.iterateAccessibleStaticFields(MythicItems.class, ItemSet.class, (itemSet, name, field) -> {
            itemSets.put(name, itemSet);
        });

        ReflectionUtils.iterateAccessibleStaticFields(MythicBlocks.class, BlockSet.class, (blockSet, name, field) -> {
            blockSets.put(name, blockSet);
        });

        createItemRecipes(itemSets);
        createBlockRecipes(itemSets, blockSets);
        createNuggetRecipes(itemSets);
        createArmorRecipes();
        createToolRecipes();
        createSmithingTemplateRecipes();
    }

    private void createBlockRecipes(HashMap<String, ItemSet> itemSets, HashMap<String, BlockSet> blockSets) {
        // Smelting ore blocks into ingots
        itemSets.forEach((name, itemSet) -> {
            if (!blockSets.containsKey(name)) {
                // no-op
            } else {
                var blockSet = blockSets.get(name);
                // Smelting Ore Blocks into ingots
                if (blockSet.getOre() != null) {
                    var oreList = new ArrayList<>(blockSet.getOreVariants());
                    oreList.add(blockSet.getOre());
                    var items = oreList.stream().map(Block::asItem).toList().toArray(new Item[0]);

                    var ingot = itemSet.getIngot();
                    var xp = itemSet.getXp();
                    boolean requiresBlasting = itemSet.requiresBlasting();
                    var critera = conditionsFromItemPredicates(ItemPredicate.Builder.create().items(this.itemLookup, items).build());

                    // ingot from ores
                    if (!requiresBlasting) {
                        CookingRecipeJsonBuilder.createSmelting(Ingredient.ofItems(items), RecipeCategory.MISC, ingot, xp, 200)
                            .criterion("has_material", critera)
                            .offerTo(exporter, recipeKey("smelting/" + name.toLowerCase(Locale.ROOT) + "_from_ores"));
                    }
                    CookingRecipeJsonBuilder.createBlasting(Ingredient.ofItems(items), RecipeCategory.MISC, ingot, xp, 100)
                        .criterion("has_material", critera)
                        .offerTo(exporter, recipeKey("blasting/" + name.toLowerCase(Locale.ROOT) + "_from_ores"));
                }
                if (itemSet.getRawOre() != null && blockSet.getOreStorageBlock() != null) {
                    // Raw Ores to Raw Ore Block
                    ShapelessRecipeJsonBuilder.create(itemLookup, RecipeCategory.BUILDING_BLOCKS, blockSet.getOreStorageBlock().asItem())
                        .criterion("has_material", conditionsFromItem(blockSet.getOreStorageBlock().asItem()))
                        .input(itemSet.getRawOre(), 9)
                        .offerTo(exporter, recipeKey("blocks/raw_" + name));
                    // Raw Ores from Raw Ore Block
                    ShapelessRecipeJsonBuilder.create(itemLookup, RecipeCategory.BUILDING_BLOCKS, itemSet.getRawOre(), 9)
                        .criterion("has_material", conditionsFromItem(itemSet.getRawOre()))
                        .input(blockSet.getOreStorageBlock().asItem())
                        .offerTo(exporter, recipeKey("crafting/raw_" + name + "_from_block"));
                }
                if (blockSet.getStorageBlock() != null) {
                    // Ingots to Storage Block
                    ShapelessRecipeJsonBuilder.create(itemLookup, RecipeCategory.BUILDING_BLOCKS, blockSet.getStorageBlock().asItem())
                        .criterion("has_material", conditionsFromItem(blockSet.getStorageBlock().asItem()))
                        .input(itemSet.getIngot(), 9)
                        .offerTo(exporter, recipeKey("blocks/" + name));
                    // Ingots from Storage Block
                    ShapelessRecipeJsonBuilder.create(itemLookup, RecipeCategory.BUILDING_BLOCKS, itemSet.getIngot(), 9)
                        .criterion("has_material", conditionsFromItem(itemSet.getIngot()))
                        .input(blockSet.getStorageBlock().asItem())
                        .offerTo(exporter, recipeKey("ingots/" + name + "_from_block"));
                }
                if (blockSet.getStorageBlock() != null && blockSet.getAnvil() != null) {
                    ShapedRecipeJsonBuilder.create(itemLookup, RecipeCategory.BUILDING_BLOCKS, blockSet.getAnvil())
                        .pattern("###")
                        .pattern(" I ")
                        .pattern("III")
                        .input('#', blockSet.getStorageBlock())
                        .input('I', itemSet.getIngot())
                        .criterion("has_block", conditionsFromItem(blockSet.getStorageBlock()))
                        .criterion("has_ingot", conditionsFromItem(itemSet.getIngot()))
                        .offerTo(exporter, recipeKey("anvils/" + name));
                }
            }
        });

        // special case for materials
        ReflectionUtils.iterateAccessibleStaticFields(MythicItems.Mats.class, Item.class, (value, name, field) -> {
            if (blockSets.containsKey(name)) {
                var blockSet = blockSets.get(name);
                // Ingots to Storage Block
                ShapelessRecipeJsonBuilder.create(itemLookup, RecipeCategory.BUILDING_BLOCKS, blockSet.getStorageBlock())
                    .criterion("has_material", conditionsFromItem(blockSet.getStorageBlock()))
                    .input(value, 9)
                    .offerTo(exporter, recipeKey("blocks/" + name));
                // Ingots from Storage Block
                ShapelessRecipeJsonBuilder.create(itemLookup, RecipeCategory.BUILDING_BLOCKS, value, 9)
                    .criterion("has_material", conditionsFromItem(value))
                    .input(blockSet.getStorageBlock())
                    .offerTo(exporter, recipeKey("crafting/" + name));
            }
        });

        // misc blocks
        ShapedRecipeJsonBuilder.create(itemLookup, RecipeCategory.BUILDING_BLOCKS, MythicBlocks.AQUARIUM_GLASS)
            .input('#', MythicItems.AQUARIUM.getRawOre())
            .input('S', Items.GLASS)
            .pattern(" # ")
            .pattern("#S#")
            .pattern(" # ")
            .criterion("has_material", conditionsFromItem(MythicItems.AQUARIUM.getRawOre()))
            .offerTo(exporter, recipeKey("blocks/aquarium_glass"));
        // TODO - Make Aquarium Resonator craftable once ready
//        ShapedRecipeJsonBuilder.create(itemLookup, RecipeCategory.TOOLS, MythicBlocks.AQUARIUM_RESONATOR)
//            .input('#', ABC123)
//            .pattern("###")
//            .pattern("###")
//            .pattern("###")
//            .criterion("has_pearl", conditionsFromItem(MythicItems.Mats.AQUARIUM_PEARL))
//            .offerTo(exporter, RegistryHelper.recipeKey("blocks/aquarium_resonator"));
        ShapedRecipeJsonBuilder.create(itemLookup, RecipeCategory.TOOLS, MythicBlocks.BANGLUM_TNT_BLOCK)
            .input('#', MythicItems.BANGLUM.getRawOre())
            .input('S', MythicItems.Mats.MORKITE)
            .pattern("#S#")
            .pattern("S#S")
            .pattern("#S#")
            .criterion("has_big_material", conditionsFromItem(MythicItems.BANGLUM.getRawOre()))
            .criterion("has_big_real_material", conditionsFromItem(MythicItems.Mats.MORKITE))
            .offerTo(exporter, recipeKey("blocks/banglum_tnt"));
        ShapedRecipeJsonBuilder.create(itemLookup, RecipeCategory.TOOLS, MythicBlocks.BANGLUM_NUKE_CORE)
            .input('#', MythicBlocks.BANGLUM.getOreStorageBlock())
            .input('S', MythicBlocks.MORKITE.getStorageBlock())
            .input('C', MythicItems.Mats.BANGLUM_CHUNK)
            .pattern("#S#")
            .pattern("SCS")
            .pattern("#S#")
            .criterion("has_big_material", conditionsFromItem(MythicBlocks.BANGLUM.getOreStorageBlock()))
            .criterion("has_big_real_material", conditionsFromItem(MythicItems.Mats.MORKITE))
            .criterion("has_chunk", conditionsFromItem(MythicItems.Mats.BANGLUM_CHUNK))
            .offerTo(exporter, recipeKey("blocks/banglum_nuke_core"));
        ShapedRecipeJsonBuilder.create(itemLookup, RecipeCategory.TOOLS, MythicBlocks.CARMOT_NUKE_CORE)
            .input('#', MythicItems.CARMOT.getIngot())
            .input('C', MythicBlocks.BANGLUM_NUKE_CORE)
            .pattern("###")
            .pattern("#C#")
            .pattern("###")
            .criterion("has_nuke_core", conditionsFromItem(MythicBlocks.BANGLUM_NUKE_CORE))
            .criterion("has_material", conditionsFromItem(MythicItems.CARMOT.getIngot()))
            .offerTo(exporter, recipeKey("blocks/carmot_nuke_core"));
        ShapedRecipeJsonBuilder.create(itemLookup, RecipeCategory.TOOLS, MythicBlocks.PALLADIUM_RAIL_ITEM, 16)
            .input('#', MythicItems.PALLADIUM.getIngot())
            .input('S', Items.IRON_INGOT)
            .pattern("# #")
            .pattern("#S#")
            .pattern("# #")
            .criterion("has_material", conditionsFromItem(MythicItems.PALLADIUM.getIngot()))
            .offerTo(exporter, recipeKey("crafting/palladium_rail"));
        ShapedRecipeJsonBuilder.create(itemLookup, RecipeCategory.TOOLS, MythicBlocks.QUADRILLUM_NUKE_CORE)
            .input('#', MythicItems.QUADRILLUM.getRawOre())
            .input('C', MythicBlocks.BANGLUM_NUKE_CORE)
            .pattern("###")
            .pattern("#C#")
            .pattern("###")
            .criterion("has_nuke_core", conditionsFromItem(MythicBlocks.BANGLUM_NUKE_CORE))
            .criterion("has_material", conditionsFromItem(MythicItems.QUADRILLUM.getRawOre()))
            .offerTo(exporter, recipeKey("blocks/quadrillum_nuke_core"));
        ShapelessRecipeJsonBuilder.create(itemLookup, RecipeCategory.TOOLS, MythicBlocks.SPONGE_NUKE_CORE)
            .input(MythicBlocks.BANGLUM_NUKE_CORE)
            .input(Items.SPONGE)
            .criterion("has_block", conditionsFromItem(MythicBlocks.BANGLUM_NUKE_CORE))
            .offerTo(exporter, recipeKey("blocks/sponge_nuke_core"));
    }

    private void createItemRecipes(HashMap<String, ItemSet> itemSets) {
        itemSets.forEach((name, itemSet) -> {
            // Blasting/Smelting Raw Ores into ingots
            if (itemSet.getRawOre() != null) {
                if (!itemSet.requiresBlasting()) {
                    CookingRecipeJsonBuilder.createSmelting(Ingredient.ofItems(itemSet.getRawOre()), RecipeCategory.MISC, itemSet.getIngot(), itemSet.getXp(), 200)
                        .criterion("has_material", conditionsFromItem(itemSet.getRawOre()))
                        .offerTo(exporter, recipeKey("smelting/" + name.toLowerCase(Locale.ROOT) + "_from_raw_ore"));
                }
                CookingRecipeJsonBuilder.createBlasting(Ingredient.ofItems(itemSet.getRawOre()), RecipeCategory.MISC, itemSet.getIngot(), itemSet.getXp(), 100)
                    .criterion("has_material", conditionsFromItem(itemSet.getRawOre()))
                    .offerTo(exporter, recipeKey("blasting/" + name.toLowerCase(Locale.ROOT) + "_from_raw_ore"));
            }
        });
    }

    private void createNuggetRecipes(HashMap<String, ItemSet> itemSets) {
        ReflectionUtils.iterateAccessibleStaticFields(MythicArmor.class, ArmorSet.class, (armorSet, name, field) -> {
            if (itemSets.containsKey(name) && itemSets.get(name).getNugget() != null) {
                var itemSet = itemSets.get(name);
                boolean requiresBlasting = itemSet.requiresBlasting();
                var nugget = itemSet.getNugget();
                assert nugget != null;
                ItemConvertible[] armorItems = new ItemConvertible[0];
                armorItems = armorSet.getArmorItems().toArray(armorItems);

                // smelt armor into nuggets
                if (!requiresBlasting) {
                    CookingRecipeJsonBuilder.createSmelting(Ingredient.ofItems(armorItems), RecipeCategory.MISC, nugget, 0.1f, 200)
                        .criterion("has_material", conditionsFromTag(TagKey.of(RegistryKeys.ITEM, RegistryHelper.id("nuggets/" + name))))
                        .offerTo(nuggetExporter, recipeKey("smelting/" + name.toLowerCase(Locale.ROOT) + "_nugget_from_armor"));
                }
                // blast armor into nuggets
                CookingRecipeJsonBuilder.createBlasting(Ingredient.ofItems(armorItems), RecipeCategory.MISC, nugget, 0.1f, 100)
                    .criterion("has_material", conditionsFromTag(TagKey.of(RegistryKeys.ITEM, RegistryHelper.id("nuggets/" + name))))
                    .offerTo(nuggetExporter, recipeKey("blasting/" + name.toLowerCase(Locale.ROOT) + "_nugget_from_armor"));
            }
        });

        itemSets.values().forEach(itemSet -> {
            if (itemSet.getNugget() != null) {
                var name = itemSet.getName().toLowerCase(Locale.ROOT);
                // crafting ingots from nuggets
                ShapelessRecipeJsonBuilder.create(itemLookup, RecipeCategory.MISC, itemSet.getIngot())
                    .criterion("has_material", conditionsFromTag(TagKey.of(RegistryKeys.ITEM, RegistryHelper.id("nuggets/" + itemSet.getName()))))
                    .input(itemSet.getNugget(), 9)
                    .group("mm_" + name)
                    .offerTo(nuggetExporter, recipeKey("ingots/" + name + "_from_nuggets"));
                // craft ingots into nuggets
                ShapelessRecipeJsonBuilder.create(itemLookup, RecipeCategory.MISC, itemSet.getNugget(), 9)
                    .criterion("has_material", conditionsFromTag(TagKey.of(RegistryKeys.ITEM, RegistryHelper.id(itemSet.getName() + "_ingot"))))
                    .input(itemSet.getIngot())
                    .offerTo(nuggetExporter, recipeKey("crafting/" + name + "_nuggets"));
            }
        });
    }

    private void createToolRecipes() {
        // Tool recipes
        createToolCraftingRecipes(MythicTools.ADAMANTITE, MythicItems.ADAMANTITE.getIngot(), itemLookup);
        createToolCraftingRecipes(MythicTools.AQUARIUM, MythicItems.AQUARIUM.getIngot(), itemLookup);
        createToolCraftingRecipes(MythicTools.BANGLUM, MythicItems.BANGLUM.getIngot(), itemLookup);
        createToolCraftingRecipes(MythicTools.BRONZE, MythicItems.BRONZE.getIngot(), itemLookup);
        createToolCraftingRecipes(MythicTools.COPPER, Items.COPPER_INGOT, itemLookup);
        createToolCraftingRecipes(MythicTools.DURASTEEL, MythicItems.DURASTEEL.getIngot(), itemLookup);
        createToolCraftingRecipes(MythicTools.KYBER, MythicItems.KYBER.getIngot(), itemLookup);
        createToolCraftingRecipes(MythicTools.MYTHRIL, MythicItems.MYTHRIL.getIngot(), itemLookup);
        createToolCraftingRecipes(MythicTools.HALLOWED, MythicItems.HALLOWED.getIngot(), itemLookup);
        createToolCraftingRecipes(MythicTools.ORICHALCUM, MythicItems.ORICHALCUM.getIngot(), itemLookup);
        createToolCraftingRecipes(MythicTools.OSMIUM, MythicItems.OSMIUM.getIngot(), itemLookup);
        createToolCraftingRecipes(MythicTools.PALLADIUM, MythicItems.PALLADIUM.getIngot(), itemLookup);
        createToolCraftingRecipes(MythicTools.PROMETHEUM, MythicItems.PROMETHEUM.getIngot(), itemLookup);
        createToolCraftingRecipes(MythicTools.QUADRILLUM, MythicItems.QUADRILLUM.getIngot(), itemLookup);
        createToolCraftingRecipes(MythicTools.RUNITE, MythicItems.RUNITE.getIngot(), itemLookup);
        createToolCraftingRecipes(MythicTools.STAR_PLATINUM, MythicItems.STAR_PLATINUM.getIngot(), itemLookup);
        createToolCraftingRecipes(MythicTools.STEEL, MythicItems.STEEL.getIngot(), itemLookup);
        createToolCraftingRecipes(MythicTools.STORMYX, MythicItems.STORMYX.getIngot(), itemLookup);
        createToolSmithingRecipes(
            MythicItems.Templates.CARMOT_SMITHING_TEMPLATE,
            MythicTools.KYBER,
            Ingredient.ofItem(MythicItems.CARMOT.getIngot()),
            MythicTools.CARMOT
        );
        createToolSmithingRecipes(
            MythicItems.Templates.LEGENDARY_BANGLUM_SMITHING_TEMPLATE,
            MythicTools.BANGLUM,
            Ingredient.ofItem(MythicItems.Mats.BANGLUM_CHUNK),
            MythicTools.LEGENDARY_BANGLUM
        );
        createToolSmithingRecipes(
            MythicItems.Templates.TIDESINGER_SMITHING_TEMPLATE,
            MythicTools.AQUARIUM,
            Ingredient.fromTag(itemLookup.getOrThrow(MythicTags.TIDESINGER_CORAL)),
            MythicTools.TIDESINGER
        );
        createToolSmithingRecipes(
            MythicItems.Templates.UNOBTAINIUM_SMITHING_TEMPLATE,
            Items.DIAMOND_SWORD,
            Items.DIAMOND_AXE,
            Items.DIAMOND_PICKAXE,
            Items.DIAMOND_SHOVEL,
            Items.DIAMOND_HOE,
            Ingredient.ofItem(MythicItems.CELESTIUM.getIngot()),
            MythicTools.CELESTIUM
        );
        createToolSmithingRecipes(
            MythicItems.Templates.UNOBTAINIUM_SMITHING_TEMPLATE,
            Items.NETHERITE_SWORD,
            Items.NETHERITE_AXE,
            Items.NETHERITE_PICKAXE,
            Items.NETHERITE_SHOVEL,
            Items.NETHERITE_HOE,
            Ingredient.ofItem(MythicItems.METALLURGIUM.getIngot()),
            MythicTools.METALLURGIUM
        );
    }

    public void createSmithingTemplateRecipes() {
        // Crafted Smithing Templates
        ShapedRecipeJsonBuilder.create(itemLookup, RecipeCategory.TOOLS, MythicItems.Templates.CARMOT_SMITHING_TEMPLATE)
            .input('M', Items.SMOOTH_BASALT)
            .input('C', MythicItems.CARMOT.getIngot())
            .input('D', Items.DIAMOND)
            .pattern("DCD")
            .pattern("CMC")
            .pattern("DCD")
            .criterion("has_material", conditionsFromItem(MythicItems.CARMOT.getIngot()))
            .group("mm_carmot_template")
            .offerTo(exporter, recipeKey("smithing_templates/carmot"));

        ShapedRecipeJsonBuilder.create(itemLookup, RecipeCategory.TOOLS, MythicItems.Templates.LEGENDARY_BANGLUM_SMITHING_TEMPLATE)
            .input('M', Items.TUFF)
            .input('C', MythicItems.BANGLUM.getIngot())
            .pattern("MMM")
            .pattern("MCM")
            .pattern("MMM")
            .criterion("has_material", conditionsFromItem(MythicItems.BANGLUM.getIngot()))
            .group("mm_banglum_template")
            .offerTo(exporter, recipeKey("smithing_templates/legendary_banglum"));

        ShapedRecipeJsonBuilder.create(itemLookup, RecipeCategory.TOOLS, MythicItems.Templates.ROYAL_MIDAS_SMITHING_TEMPLATE)
            .input('M', Items.NETHERRACK)
            .input('C', MythicBlocks.MIDAS_GOLD.getStorageBlock())
            .input('T', MythicBlocks.ENCHANTED_MIDAS_GOLD_BLOCK_ITEM)
            .pattern("CTC")
            .pattern("CMC")
            .pattern("CCC")
            .criterion("has_material", conditionsFromItem(MythicBlocks.MIDAS_GOLD.getStorageBlock()))
            .criterion("has_enchanted_material", conditionsFromItem(MythicBlocks.ENCHANTED_MIDAS_GOLD_BLOCK_ITEM))
            .group("mm_royal_midas_template")
            .offerTo(exporter, recipeKey("smithing_templates/royal_midas"));

        ShapedRecipeJsonBuilder.create(itemLookup, RecipeCategory.TOOLS, MythicItems.Templates.OSMIUM_CHAINMAIL_SMITHING_TEMPLATE)
            .input('M', Items.ANDESITE)
            .input('C', MythicItems.OSMIUM.getNugget())
            .input('T', MythicItems.OSMIUM.getIngot())
            .pattern("CTC")
            .pattern("CMC")
            .pattern("CTC")
            .criterion("has_material", conditionsFromItem(MythicItems.OSMIUM.getIngot()))
            .group("mm_osmium_template")
            .offerTo(exporter, recipeKey("smithing_templates/osmium_chainmail"));

        ShapedRecipeJsonBuilder.create(itemLookup, RecipeCategory.TOOLS, MythicItems.Templates.MIDAS_FOLDING_TEMPLATE)
            .input('M', Items.NETHERRACK)
            .input('C', MythicItems.MIDAS_GOLD.getIngot())
            .pattern("MMM")
            .pattern("MCM")
            .pattern("MMM")
            .criterion("has_material", conditionsFromItem(MythicItems.MIDAS_GOLD.getIngot()))
            .group("mm_midas_folding_template")
            .offerTo(exporter, recipeKey("smithing_templates/midas_folding"));

        ShapedRecipeJsonBuilder.create(itemLookup, RecipeCategory.TOOLS, MythicItems.Templates.TIDESINGER_SMITHING_TEMPLATE)
            .input('O', Items.PRISMARINE)
            .input('L', MythicItems.Mats.AQUARIUM_PEARL)
            .input('R', Items.BRAIN_CORAL)
            .input('G', MythicItems.AQUARIUM.getIngot())
            .input('B', Items.FIRE_CORAL)
            .input('M', Items.BUBBLE_CORAL)
            .input('E', Items.TUBE_CORAL)
            .input('N', Items.HORN_CORAL)
            .pattern("RGB")
            .pattern("LOL")
            .pattern("MEN")
            .criterion("has_material", conditionsFromItem(MythicItems.Mats.AQUARIUM_PEARL))
            .criterion("has_coral", conditionsFromTag(MythicTags.TIDESINGER_CORAL))
            .group("mm_tidesinger_template")
            .offerTo(exporter, recipeKey("smithing_templates/tidesinger"));

        // Smithing Template Duplication recipes
        ShapedRecipeJsonBuilder.create(itemLookup, RecipeCategory.TOOLS, MythicItems.Templates.AEGIS_SMITHING_TEMPLATE, 2)
            .input('C', Items.EMERALD)
            .input('T', MythicItems.Templates.AEGIS_SMITHING_TEMPLATE)
            .input('M', Items.DEEPSLATE)
            .pattern("CTC")
            .pattern("CMC")
            .pattern("CCC")
            .criterion("has_material", conditionsFromItem(MythicItems.Templates.AEGIS_SMITHING_TEMPLATE))
            .group("mm_aegis_template")
            .offerTo(exporter, recipeKey("smithing_templates/aegis_duplicate"));
        ShapedRecipeJsonBuilder.create(itemLookup, RecipeCategory.TOOLS, MythicItems.Templates.CARMOT_SMITHING_TEMPLATE, 2)
            .input('C', MythicItems.CARMOT.getNugget())
            .input('P', Items.DIAMOND)
            .input('T', MythicItems.Templates.CARMOT_SMITHING_TEMPLATE)
            .input('M', Items.SMOOTH_BASALT)
            .pattern("CTC")
            .pattern("CMC")
            .pattern("CPC")
            .criterion("has_material", conditionsFromItem(MythicItems.Templates.CARMOT_SMITHING_TEMPLATE))
            .group("mm_carmot_template")
            .offerTo(nuggetExporter, recipeKey("smithing_templates/carmot_duplicate"));
        ShapedRecipeJsonBuilder.create(itemLookup, RecipeCategory.TOOLS, MythicItems.Templates.MYTHRIL_DRILL_SMITHING_TEMPLATE, 2)
            .input('C', Items.DIAMOND)
            .input('T', MythicItems.Templates.MYTHRIL_DRILL_SMITHING_TEMPLATE)
            .input('M', Items.DEEPSLATE)
            .pattern("CTC")
            .pattern("CMC")
            .pattern("CCC")
            .criterion("has_material", conditionsFromItem(MythicItems.Templates.MYTHRIL_DRILL_SMITHING_TEMPLATE))
            .group("mm_mythril_drill_template")
            .offerTo(exporter, recipeKey("smithing_templates/mythril_drill_duplicate"));
        ShapedRecipeJsonBuilder.create(itemLookup, RecipeCategory.TOOLS, MythicItems.Templates.MIDAS_FOLDING_TEMPLATE, 2)
            .input('M', MythicItems.MIDAS_GOLD.getNugget())
            .input('C', MythicItems.Templates.MIDAS_FOLDING_TEMPLATE)
            .pattern("MMM")
            .pattern("MCM")
            .pattern("MMM")
            .criterion("has_material", conditionsFromItem(MythicItems.Templates.MIDAS_FOLDING_TEMPLATE))
            .group("mm_midas_folding_template")
            .offerTo(nuggetExporter, recipeKey("smithing_templates/midas_folding_duplicate"));
        ShapedRecipeJsonBuilder.create(itemLookup, RecipeCategory.TOOLS, MythicItems.Templates.TIDESINGER_SMITHING_TEMPLATE, 2)
            .input('C', MythicItems.AQUARIUM.getIngot())
            .input('P', MythicItems.Mats.AQUARIUM_PEARL)
            .input('T', MythicItems.Templates.TIDESINGER_SMITHING_TEMPLATE)
            .input('M', Items.PRISMARINE)
            .pattern("CTC")
            .pattern("CMC")
            .pattern("CPC")
            .criterion("has_material", conditionsFromItem(MythicItems.Templates.TIDESINGER_SMITHING_TEMPLATE))
            .group("mm_tidesinger_template")
            .offerTo(exporter, recipeKey("smithing_templates/tidesinger_duplicate"));
        ShapedRecipeJsonBuilder.create(itemLookup, RecipeCategory.TOOLS, MythicItems.Templates.ROYAL_MIDAS_SMITHING_TEMPLATE, 2)
            .input('C', MythicBlocks.MIDAS_GOLD.getStorageBlock())
            .input('T', MythicItems.Templates.ROYAL_MIDAS_SMITHING_TEMPLATE)
            .input('M', Items.NETHERRACK)
            .pattern("CTC")
            .pattern("CMC")
            .pattern("CCC")
            .criterion("has_material", conditionsFromItem(MythicItems.Templates.ROYAL_MIDAS_SMITHING_TEMPLATE))
            .group("mm_royal_midas_template")
            .offerTo(exporter, recipeKey("smithing_templates/royal_midas_duplicate"));
        ShapedRecipeJsonBuilder.create(itemLookup, RecipeCategory.TOOLS, MythicItems.Templates.UNOBTAINIUM_SMITHING_TEMPLATE, 2)
            .input('C', Items.DIAMOND)
            .input('T', MythicItems.Templates.UNOBTAINIUM_SMITHING_TEMPLATE)
            .input('M', Items.DEEPSLATE)
            .pattern("CTC")
            .pattern("CMC")
            .pattern("CCC")
            .criterion("has_material", conditionsFromItem(MythicItems.Templates.UNOBTAINIUM_SMITHING_TEMPLATE))
            .group("mm_unobtainium_template")
            .offerTo(exporter, recipeKey("smithing_templates/unobtainium_alloy"));
    }

    public void createToolCraftingRecipes(ToolSet toolSet, Item material, RegistryEntryLookup<Item> lookup) {
        // sword
        ShapedRecipeJsonBuilder.create(lookup, RecipeCategory.TOOLS, toolSet.getSword())
            .input('#', material)
            .input('S', WOODEN_RODS)
            .pattern("#")
            .pattern("#")
            .pattern("S")
            .criterion("has_sword", conditionsFromItem(toolSet.getSword()))
            .offerTo(exporter, recipeKey("sword/" + toolSet.getName()));
        // axe
        ShapedRecipeJsonBuilder.create(lookup, RecipeCategory.TOOLS, toolSet.getAxe())
            .input('#', material)
            .input('S', WOODEN_RODS)
            .pattern("## ")
            .pattern("#S ")
            .pattern(" S ")
            .criterion("has_axe", conditionsFromItem(toolSet.getAxe()))
            .offerTo(exporter, recipeKey("axe/" + toolSet.getName()));
        // pickaxe
        ShapedRecipeJsonBuilder.create(lookup, RecipeCategory.TOOLS, toolSet.getPickaxe())
            .input('#', material)
            .input('S', WOODEN_RODS)
            .pattern("###")
            .pattern(" S ")
            .pattern(" S ")
            .criterion("has_pickaxe", conditionsFromItem(toolSet.getPickaxe()))
            .offerTo(exporter, recipeKey("pickaxe/" + toolSet.getName()));
        // shovel
        ShapedRecipeJsonBuilder.create(lookup, RecipeCategory.TOOLS, toolSet.getShovel())
            .input('#', material)
            .input('S', WOODEN_RODS)
            .pattern("#")
            .pattern("#")
            .pattern("S")
            .criterion("has_shovel", conditionsFromItem(toolSet.getShovel()))
            .offerTo(exporter, recipeKey("shovel/" + toolSet.getName()));
        // hoe
        ShapedRecipeJsonBuilder.create(lookup, RecipeCategory.TOOLS, toolSet.getHoe())
            .input('#', material)
            .input('S', WOODEN_RODS)
            .pattern("## ")
            .pattern(" S ")
            .pattern(" S ")
            .criterion("has_hoe", conditionsFromItem(toolSet.getHoe()))
            .offerTo(exporter, recipeKey("hoe/" + toolSet.getName()));
    }

    public void createToolSmithingRecipes(
        Item template,
        Item baseSword,
        Item baseAxe,
        Item basePickaxe,
        Item baseShovel,
        Item baseHoe,
        Ingredient addition,
        ToolSet resultToolset
    ) {
        var templateIngredient = Ingredient.ofItem(template);
        SmithingTransformRecipeJsonBuilder.create(templateIngredient, Ingredient.ofItem(baseSword), addition, RecipeCategory.TOOLS, resultToolset.getSword())
            .criterion("has_template", conditionsFromItem(template))
            .criterion("has_component_tool", conditionsFromItem(baseSword))
            .criterion("has_sword", conditionsFromItem(resultToolset.getSword()))
            .offerTo(exporter, recipeKey("sword/" + resultToolset.getName()));
        SmithingTransformRecipeJsonBuilder.create(templateIngredient, Ingredient.ofItem(baseAxe), addition, RecipeCategory.TOOLS, resultToolset.getAxe())
            .criterion("has_template", conditionsFromItem(template))
            .criterion("has_component_tool", conditionsFromItem(baseAxe))
            .criterion("has_axe", conditionsFromItem(resultToolset.getAxe()))
            .offerTo(exporter, recipeKey("axe/" + resultToolset.getName()));
        SmithingTransformRecipeJsonBuilder.create(templateIngredient, Ingredient.ofItem(basePickaxe), addition, RecipeCategory.TOOLS, resultToolset.getPickaxe())
            .criterion("has_template", conditionsFromItem(template))
            .criterion("has_component_tool", conditionsFromItem(basePickaxe))
            .criterion("has_pickaxe", conditionsFromItem(resultToolset.getPickaxe()))
            .offerTo(exporter, recipeKey("pickaxe/" + resultToolset.getName()));
        SmithingTransformRecipeJsonBuilder.create(templateIngredient, Ingredient.ofItem(baseShovel), addition, RecipeCategory.TOOLS, resultToolset.getShovel())
            .criterion("has_template", conditionsFromItem(template))
            .criterion("has_component_tool", conditionsFromItem(baseShovel))
            .criterion("has_shovel", conditionsFromItem(resultToolset.getShovel()))
            .offerTo(exporter, recipeKey("shovel/" + resultToolset.getName()));
        SmithingTransformRecipeJsonBuilder.create(templateIngredient, Ingredient.ofItem(baseHoe), addition, RecipeCategory.TOOLS, resultToolset.getHoe())
            .criterion("has_template", conditionsFromItem(template))
            .criterion("has_component_tool", conditionsFromItem(baseHoe))
            .criterion("has_hoe", conditionsFromItem(resultToolset.getHoe()))
            .offerTo(exporter, recipeKey("hoe/" + resultToolset.getName()));
    }

    public void createToolSmithingRecipes(Item template, ToolSet baseToolset, Ingredient addition, ToolSet resultToolset) {
        createToolSmithingRecipes(template, baseToolset.getSword(), baseToolset.getAxe(), baseToolset.getPickaxe(), baseToolset.getShovel(), baseToolset.getHoe(), addition, resultToolset);
    }

    public void createArmorRecipes() {
        createArmorCraftingRecipes(MythicArmor.ADAMANTITE, MythicItems.ADAMANTITE.getIngot());
        createArmorCraftingRecipes(MythicArmor.AQUARIUM, MythicItems.AQUARIUM.getIngot());
        createArmorCraftingRecipes(MythicArmor.BANGLUM, MythicItems.BANGLUM.getIngot());
        createArmorCraftingRecipes(MythicArmor.BRONZE, MythicItems.BRONZE.getIngot());
        createArmorCraftingRecipes(MythicArmor.COPPER, Items.COPPER_INGOT);
        createArmorCraftingRecipes(MythicArmor.DURASTEEL, MythicItems.DURASTEEL.getIngot());
        createArmorCraftingRecipes(MythicArmor.HALLOWED, MythicItems.HALLOWED.getIngot());
        createArmorCraftingRecipes(MythicArmor.KYBER, MythicItems.KYBER.getIngot());
        createArmorCraftingRecipes(MythicArmor.MIDAS_GOLD, MythicItems.MIDAS_GOLD.getIngot());
        createArmorCraftingRecipes(MythicArmor.MYTHRIL, MythicItems.MYTHRIL.getIngot());
        createArmorCraftingRecipes(MythicArmor.ORICHALCUM, MythicItems.ORICHALCUM.getIngot());
        createArmorCraftingRecipes(MythicArmor.OSMIUM, MythicItems.OSMIUM.getIngot());
        createArmorCraftingRecipes(MythicArmor.PALLADIUM, MythicItems.PALLADIUM.getIngot());
        createArmorCraftingRecipes(MythicArmor.PROMETHEUM, MythicItems.PROMETHEUM.getIngot());
        createArmorCraftingRecipes(MythicArmor.RUNITE, MythicItems.RUNITE.getIngot());
        createArmorCraftingRecipes(MythicArmor.SILVER, MythicItems.SILVER.getIngot());
        createArmorCraftingRecipes(MythicArmor.STAR_PLATINUM, MythicItems.STAR_PLATINUM.getIngot());
        createArmorCraftingRecipes(MythicArmor.STEEL, MythicItems.STEEL.getIngot());
        createArmorCraftingRecipes(MythicArmor.STORMYX, MythicItems.STORMYX.getIngot());

        createArmorSmithingRecipes(
            MythicItems.Templates.CARMOT_SMITHING_TEMPLATE,
            MythicArmor.KYBER,
            Ingredient.ofItem(MythicItems.CARMOT.getIngot()),
            MythicArmor.CARMOT
        );
        createArmorSmithingRecipes(
            MythicItems.Templates.UNOBTAINIUM_SMITHING_TEMPLATE,
            Items.NETHERITE_HELMET,
            Items.NETHERITE_CHESTPLATE,
            Items.NETHERITE_LEGGINGS,
            Items.NETHERITE_BOOTS,
            Ingredient.ofItem(MythicItems.METALLURGIUM.getIngot()),
            MythicArmor.METALLURGIUM
        );
        createArmorSmithingRecipes(
            MythicItems.Templates.UNOBTAINIUM_SMITHING_TEMPLATE,
            Items.DIAMOND_HELMET,
            Items.DIAMOND_CHESTPLATE,
            Items.DIAMOND_LEGGINGS,
            Items.DIAMOND_BOOTS,
            Ingredient.ofItem(MythicItems.CELESTIUM.getIngot()),
            MythicArmor.CELESTIUM
        );
        createArmorSmithingRecipes(
            MythicItems.Templates.LEGENDARY_BANGLUM_SMITHING_TEMPLATE,
            MythicArmor.BANGLUM,
            Ingredient.ofItem(MythicItems.Mats.BANGLUM_CHUNK),
            MythicArmor.LEGENDARY_BANGLUM
        );
        createArmorSmithingRecipes(
            MythicItems.Templates.OSMIUM_CHAINMAIL_SMITHING_TEMPLATE,
            Items.DIAMOND_HELMET,
            Items.DIAMOND_CHESTPLATE,
            Items.DIAMOND_LEGGINGS,
            Items.DIAMOND_BOOTS,
            Ingredient.ofItem(MythicItems.OSMIUM.getIngot()),
            MythicArmor.OSMIUM_CHAINMAIL
        );
        createTidesingerArmorRecipes();
    }

    private void createTidesingerArmorRecipes() {
        var template = MythicItems.Templates.TIDESINGER_SMITHING_TEMPLATE;
        for (var coral : TidesingerPatternComponent.TIDESINGER_VARIANTS.keySet()) {
            var addition = Ingredient.ofItem(coral);
            var name = TidesingerPatternComponent.TIDESINGER_VARIANTS.get(coral);
            // helmet
            var helmetOutput = new ItemStack(MythicArmor.TIDESINGER.getHelmet(), 1);
            helmetOutput.set(MythicDataComponents.TIDESINGER, TidesingerPatternComponent.fromItem(coral));
            var helmetRecipe = new SmithingTransformRecipe(
                Optional.of(Ingredient.ofItem(template)),
                Optional.of(Ingredient.ofItem(MythicArmor.AQUARIUM.getHelmet())),
                Optional.of(addition),
                helmetOutput
            );
            // chestplate
            var chestplateOutput = new ItemStack(MythicArmor.TIDESINGER.getChestplate(), 1);
            chestplateOutput.set(MythicDataComponents.TIDESINGER, TidesingerPatternComponent.fromItem(coral));
            var chestplateRecipe = new SmithingTransformRecipe(
                Optional.of(Ingredient.ofItem(template)),
                Optional.of(Ingredient.ofItem(MythicArmor.AQUARIUM.getChestplate())),
                Optional.of(addition),
                chestplateOutput
            );
            // leggings
            var leggingsOutput = new ItemStack(MythicArmor.TIDESINGER.getLeggings(), 1);
            leggingsOutput.set(MythicDataComponents.TIDESINGER, TidesingerPatternComponent.fromItem(coral));
            var leggingsRecipe = new SmithingTransformRecipe(
                Optional.of(Ingredient.ofItem(template)),
                Optional.of(Ingredient.ofItem(MythicArmor.AQUARIUM.getLeggings())),
                Optional.of(addition),
                leggingsOutput
            );
            // boots
            var bootsOutput = new ItemStack(MythicArmor.TIDESINGER.getBoots(), 1);
            bootsOutput.set(MythicDataComponents.TIDESINGER, TidesingerPatternComponent.fromItem(coral));
            var bootsRecipe = new SmithingTransformRecipe(
                Optional.of(Ingredient.ofItem(template)),
                Optional.of(Ingredient.ofItem(MythicArmor.AQUARIUM.getBoots())),
                Optional.of(addition),
                bootsOutput
            );
            exporter.accept(recipeKey("armor/tidesinger_helmet_" + name), helmetRecipe, null);
            exporter.accept(recipeKey("armor/tidesinger_chestplate_" + name), chestplateRecipe, null);
            exporter.accept(recipeKey("armor/tidesinger_leggings_" + name), leggingsRecipe, null);
            exporter.accept(recipeKey("armor/tidesinger_boots_" + name), bootsRecipe, null);
        }
    }

    public void createArmorCraftingRecipes(ArmorSet output, Item material) {
        createArmorCraftingRecipes(output, Ingredient.ofItem(material));
    }

    public void createArmorCraftingRecipes(ArmorSet output, Ingredient material) {
        // helmet
        ShapedRecipeJsonBuilder.create(itemLookup, RecipeCategory.COMBAT, output.getHelmet())
            .input('#', material)
            .pattern("###")
            .pattern("# #")
            .criterion("has_helmet", conditionsFromItem(output.getHelmet()))
            .offerTo(exporter, recipeKey("armor/" + output.getName() + "_helmet"));
        // chestplate
        ShapedRecipeJsonBuilder.create(itemLookup, RecipeCategory.COMBAT, output.getChestplate())
            .input('#', material)
            .pattern("# #")
            .pattern("###")
            .pattern("###")
            .criterion("has_chestplate", conditionsFromItem(output.getChestplate()))
            .offerTo(exporter, recipeKey("armor/" + output.getName() + "_chestplate"));
        // leggings
        ShapedRecipeJsonBuilder.create(itemLookup, RecipeCategory.COMBAT, output.getLeggings())
            .input('#', material)
            .pattern("###")
            .pattern("# #")
            .pattern("# #")
            .criterion("has_leggings", conditionsFromItem(output.getLeggings()))
            .offerTo(exporter, recipeKey("armor/" + output.getName() + "_leggings"));
        // boots
        ShapedRecipeJsonBuilder.create(itemLookup, RecipeCategory.COMBAT, output.getBoots())
            .input('#', material)
            .pattern("# #")
            .pattern("# #")
            .criterion("has_boots", conditionsFromItem(output.getBoots()))
            .offerTo(exporter, recipeKey("armor/" + output.getName() + "_boots"));
    }

    public void createArmorSmithingRecipes(Item template, Item baseHelmet, Item baseChestplate, Item baseLeggings, Item baseBoots, Ingredient addition, ArmorSet outputArmorSet) {
        // helmet
        SmithingTransformRecipeJsonBuilder.create(
                Ingredient.ofItem(template),
                Ingredient.ofItem(baseHelmet),
                addition,
                RecipeCategory.COMBAT,
                outputArmorSet.getHelmet()
            )
            .criterion("has_helmet", conditionsFromItem(outputArmorSet.getHelmet()))
            .offerTo(exporter, recipeKey("armor/" + outputArmorSet.getName() + "_helmet"));
        // chestplate
        SmithingTransformRecipeJsonBuilder.create(
                Ingredient.ofItem(template),
                Ingredient.ofItem(baseChestplate),
                addition,
                RecipeCategory.COMBAT,
                outputArmorSet.getChestplate()
            )
            .criterion("has_chestplate", conditionsFromItem(outputArmorSet.getChestplate()))
            .offerTo(exporter, recipeKey("armor/" + outputArmorSet.getName() + "_chestplate"));
        // leggings
        SmithingTransformRecipeJsonBuilder.create(
                Ingredient.ofItem(template),
                Ingredient.ofItem(baseLeggings),
                addition,
                RecipeCategory.COMBAT,
                outputArmorSet.getLeggings()
            )
            .criterion("has_leggings", conditionsFromItem(outputArmorSet.getLeggings()))
            .offerTo(exporter, recipeKey("armor/" + outputArmorSet.getName() + "_leggings"));
        // boots
        SmithingTransformRecipeJsonBuilder.create(
                Ingredient.ofItem(template),
                Ingredient.ofItem(baseBoots),
                addition,
                RecipeCategory.COMBAT,
                outputArmorSet.getBoots()
            )
            .criterion("has_boots", conditionsFromItem(outputArmorSet.getBoots()))
            .offerTo(exporter, recipeKey("armor/" + outputArmorSet.getName() + "_boots"));
    }

    public void createArmorSmithingRecipes(Item template, ArmorSet baseArmorSet, Ingredient addition, ArmorSet outputArmorSet) {
        createArmorSmithingRecipes(
            template,
            baseArmorSet.getHelmet(),
            baseArmorSet.getChestplate(),
            baseArmorSet.getLeggings(),
            baseArmorSet.getBoots(),
            addition,
            outputArmorSet
        );
    }

}
