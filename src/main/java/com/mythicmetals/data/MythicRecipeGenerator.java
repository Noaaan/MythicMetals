package com.mythicmetals.data;

import com.mythicmetals.api.v2.ArmorSet;
import com.mythicmetals.api.v2.BlockSet;
import com.mythicmetals.item.*;
import com.mythicmetals.item.armor.MythicArmorSets;
import com.mythicmetals.item.tools.ToolSet;
import net.minecraft.advancements.criterion.ItemPredicate;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.recipes.*;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.Tuple;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Block;
import java.util.*;

import static com.mythicmetals.misc.RegistryHelper.recipeKey;
import static net.fabricmc.fabric.api.tag.convention.v2.ConventionalItemTags.WOODEN_RODS;

// FIXME - Guess we're redoing datagen
@SuppressWarnings("UnstableApiUsage")
public class MythicRecipeGenerator extends RecipeProvider {

    public MythicRecipeGenerator(HolderLookup.Provider registries, RecipeOutput recipeOutput, RecipeOutput nuggetExporter) {
        super(registries, recipeOutput);
        itemLookup = registries.lookupOrThrow(Registries.ITEM);
        this.nuggetExporter = nuggetExporter;
    }

    private final HolderGetter<Item> itemLookup;
    private final RecipeOutput nuggetExporter;

    @Override
    public void buildRecipes() {

        var itemSets = new HashMap<String, ItemSet>();
        var blockSets = new HashMap<String, BlockSet>();

        // Handle items first, as they store whether the items need blasting to be smelted
//        ReflectionUtils.iterateAccessibleStaticFields(MythicItems.class, ItemSet.class, (itemSet, name, field) -> {
//            itemSets.put(name, itemSet);
//        });

//        ReflectionUtils.iterateAccessibleStaticFields(MythicBlocks.class, BlockSet.class, (blockSet, name, field) -> {
//            blockSets.put(name, blockSet);
//        });

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
            if (blockSets.containsKey(name)) {
                var blockSet = blockSets.get(name);
                // Smelting Ore Blocks into ingots
                if (blockSet.ore() != null) {
                    var oreList = new ArrayList<>(blockSet.oreVariants().values().stream().map(Tuple::getB).toList());
                    oreList.add(blockSet.ore());
                    var items = oreList.stream().map(Block::asItem).toList().toArray(new Item[0]);

                    var ingot = itemSet.getIngot();
                    var xp = itemSet.getXp();
                    boolean requiresBlasting = itemSet.requiresBlasting();
                    var critera = inventoryTrigger(ItemPredicate.Builder.item().of(this.itemLookup, items).build());

                    // ingot from ores
                    if (!requiresBlasting) {
                        SimpleCookingRecipeBuilder.smelting(Ingredient.of(items), RecipeCategory.MISC, ingot, xp, 200)
                            .unlockedBy("has_material", critera)
                            .save(output, recipeKey("smelting/" + name.toLowerCase(Locale.ROOT) + "_from_ores"));
                    }
                    SimpleCookingRecipeBuilder.blasting(Ingredient.of(items), RecipeCategory.MISC, ingot, xp, 100)
                        .unlockedBy("has_material", critera)
                        .save(output, recipeKey("blasting/" + name.toLowerCase(Locale.ROOT) + "_from_ores"));
                }
                if (itemSet.getRawOre() != null && blockSet.rawStorage() != null) {
                    // Raw Ores to Raw Ore Block
                    ShapelessRecipeBuilder.shapeless(itemLookup, RecipeCategory.BUILDING_BLOCKS, blockSet.rawStorage().asItem())
                        .unlockedBy("has_material", has(blockSet.rawStorage().asItem()))
                        .requires(itemSet.getRawOre(), 9)
                        .save(output, recipeKey("blocks/raw_" + name));
                    // Raw Ores from Raw Ore Block
                    ShapelessRecipeBuilder.shapeless(itemLookup, RecipeCategory.BUILDING_BLOCKS, itemSet.getRawOre(), 9)
                        .unlockedBy("has_material", has(itemSet.getRawOre()))
                        .requires(blockSet.rawStorage().asItem())
                        .save(output, recipeKey("crafting/raw_" + name + "_from_block"));
                }
                if (blockSet.storage() != null) {
                    // Ingots to Storage Block
                    ShapelessRecipeBuilder.shapeless(itemLookup, RecipeCategory.BUILDING_BLOCKS, blockSet.storage().asItem())
                        .unlockedBy("has_material", has(blockSet.storage().asItem()))
                        .requires(itemSet.getIngot(), 9)
                        .save(output, recipeKey("blocks/" + name));
                    // Ingots from Storage Block
                    ShapelessRecipeBuilder.shapeless(itemLookup, RecipeCategory.BUILDING_BLOCKS, itemSet.getIngot(), 9)
                        .unlockedBy("has_material", has(itemSet.getIngot()))
                        .requires(blockSet.storage().asItem())
                        .save(output, recipeKey("ingots/" + name + "_from_block"));
                }
                if (blockSet.storage() != null && blockSet.anvil() != null) {
                    ShapedRecipeBuilder.shaped(itemLookup, RecipeCategory.BUILDING_BLOCKS, blockSet.anvil())
                        .pattern("###")
                        .pattern(" I ")
                        .pattern("III")
                        .define('#', blockSet.storage())
                        .define('I', itemSet.getIngot())
                        .unlockedBy("has_block", has(blockSet.storage()))
                        .unlockedBy("has_ingot", has(itemSet.getIngot()))
                        .save(output, recipeKey("anvils/" + name));
                }
            }
        });

        // special case for materials
        // FIXME
//        ReflectionUtils.iterateAccessibleStaticFields(MythicItems.Mats.class, Item.class, (value, name, field) -> {
//            if (blockSets.containsKey(name)) {
//                var blockSet = blockSets.get(name);
//                // Ingots to Storage Block
//                ShapelessRecipeBuilder.shapeless(itemLookup, RecipeCategory.BUILDING_BLOCKS, blockSet.storage())
//                    .unlockedBy("has_material", has(blockSet.storage()))
//                    .requires(value, 9)
//                    .save(output, recipeKey("blocks/" + name));
//                // Ingots from Storage Block
//                ShapelessRecipeBuilder.shapeless(itemLookup, RecipeCategory.BUILDING_BLOCKS, value, 9)
//                    .unlockedBy("has_material", has(value))
//                    .requires(blockSet.storage())
//                    .save(output, recipeKey("crafting/" + name));
//            }
//        });

        // misc blocks
        var bangNukeCore = MythicMaterials.BANGLUM.extraBlocks().get(MythicResourceKeys.BANGLUM_NUKE_CORE);
        ShapedRecipeBuilder.shaped(itemLookup, RecipeCategory.BUILDING_BLOCKS, MythicMaterials.AQUARIUM.extraBlocks().get(MythicResourceKeys.AQUARIUM_GLASS))
            .define('#', MythicMaterials.AQUARIUM.rawOre())
            .define('S', Items.GLASS)
            .pattern(" # ")
            .pattern("#S#")
            .pattern(" # ")
            .unlockedBy("has_material", has(MythicMaterials.AQUARIUM.rawOre()))
            .save(output, recipeKey("blocks/aquarium_glass"));
        // TODO - Make Aquarium Resonator craftable once ready
//        ShapedRecipeJsonBuilder.create(itemLookup, RecipeCategory.TOOLS, MythicBlocks.AQUARIUM_RESONATOR)
//            .input('#', ABC123)
//            .pattern("###")
//            .pattern("###")
//            .pattern("###")
//            .criterion("has_pearl", conditionsFromItem(MythicMaterials.AQUARIUM.extraItems().get(MythicResourceKeys.AQUARIUM_PEARL)))
//            .offerTo(exporter, RegistryHelper.recipeKey("blocks/aquarium_resonator"));
        var tnt = MythicMaterials.BANGLUM.extraBlocks().get(MythicResourceKeys.BANGLUM_TNT);
        ShapedRecipeBuilder.shaped(itemLookup, RecipeCategory.TOOLS, tnt)
            .define('#', MythicMaterials.BANGLUM.rawOre())
            .define('S', MythicMaterials.MORKITE.baseMaterial())
            .pattern("#S#")
            .pattern("S#S")
            .pattern("#S#")
            .unlockedBy("has_big_material", has(MythicMaterials.BANGLUM.rawOre()))
            .unlockedBy("has_big_real_material", has(MythicMaterials.MORKITE.baseMaterial()))
            .save(output, recipeKey("blocks/banglum_tnt"));
        ShapedRecipeBuilder.shaped(itemLookup, RecipeCategory.TOOLS, tnt)
            .define('#', MythicMaterials.BANGLUM.rawOre())
            .define('S', Items.GUNPOWDER)
            .pattern("#S#")
            .pattern("S#S")
            .pattern("#S#")
            .unlockedBy("has_big_material", has(MythicMaterials.BANGLUM.rawOre()))
            .unlockedBy("has_big_real_material", has(Items.GUNPOWDER))
            .save(output, recipeKey("blocks/banglum_tnt_from_gunpowder"));
        ShapedRecipeBuilder.shaped(itemLookup, RecipeCategory.TOOLS, bangNukeCore)
            .define('#', MythicMaterials.BANGLUM.blockSet().rawStorage())
            .define('S', MythicMaterials.MORKITE.blockSet().storage())
            .define('C', MythicMaterials.LEGENDARY_BANGLUM.baseMaterial())
            .pattern("#S#")
            .pattern("SCS")
            .pattern("#S#")
            .unlockedBy("has_big_material", has(MythicMaterials.BANGLUM.blockSet().rawStorage()))
            .unlockedBy("has_big_real_material", has(MythicMaterials.MORKITE.baseMaterial()))
            .unlockedBy("has_chunk", has(MythicMaterials.LEGENDARY_BANGLUM.baseMaterial()))
            .save(output, recipeKey("blocks/banglum_nuke_core"));
        ShapedRecipeBuilder.shaped(itemLookup, RecipeCategory.TOOLS, MythicMaterials.CARMOT.extraBlocks().get(MythicResourceKeys.CARMOT_NUKE_CORE))
            .define('#', MythicMaterials.CARMOT.baseMaterial())
            .define('C', bangNukeCore)
            .pattern("###")
            .pattern("#C#")
            .pattern("###")
            .unlockedBy("has_nuke_core", has(bangNukeCore))
            .unlockedBy("has_material", has(MythicMaterials.CARMOT.baseMaterial()))
            .save(output, recipeKey("blocks/carmot_nuke_core"));
        ShapedRecipeBuilder.shaped(itemLookup, RecipeCategory.TOOLS, MythicMaterials.PALLADIUM.extraItems().get(MythicResourceKeys.PALLADIUM_RAIL_ITEM), 16)
            .define('#', MythicMaterials.PALLADIUM.baseMaterial())
            .define('S', Items.IRON_INGOT)
            .pattern("# #")
            .pattern("#S#")
            .pattern("# #")
            .unlockedBy("has_material", has(MythicMaterials.PALLADIUM.baseMaterial()))
            .save(output, recipeKey("crafting/palladium_rail"));
        ShapedRecipeBuilder.shaped(itemLookup, RecipeCategory.TOOLS, MythicMaterials.QUADRILLUM.extraBlocks().get(MythicResourceKeys.QUADRILLUM_NUKE_CORE))
            .define('#', MythicMaterials.QUADRILLUM.rawOre())
            .define('C', bangNukeCore)
            .pattern("###")
            .pattern("#C#")
            .pattern("###")
            .unlockedBy("has_nuke_core", has(bangNukeCore))
            .unlockedBy("has_material", has(MythicMaterials.QUADRILLUM.rawOre()))
            .save(output, recipeKey("blocks/quadrillum_nuke_core"));
        ShapelessRecipeBuilder.shapeless(itemLookup, RecipeCategory.TOOLS, MythicMaterials.BANGLUM.extraBlocks().get(MythicResourceKeys.SPONGE_NUKE_CORE))
            .requires(bangNukeCore)
            .requires(Items.SPONGE)
            .unlockedBy("has_block", has(bangNukeCore))
            .save(output, recipeKey("blocks/sponge_nuke_core"));
        ShapedRecipeBuilder.shaped(itemLookup, RecipeCategory.TOOLS, Items.TNT)
            .define('#', Items.SAND)
            .define('S', MythicMaterials.MORKITE.baseMaterial())
            .pattern("#S#")
            .pattern("S#S")
            .pattern("#S#")
            .unlockedBy("has_real_material", has(MythicMaterials.MORKITE.baseMaterial()))
            .save(output, recipeKey("blocks/tnt_from_morkite"));
    }

    private void createItemRecipes(HashMap<String, ItemSet> itemSets) {
        itemSets.forEach((name, itemSet) -> {
            // Blasting/Smelting Raw Ores into ingots
            if (itemSet.getRawOre() != null) {
                if (!itemSet.requiresBlasting()) {
                    SimpleCookingRecipeBuilder.smelting(Ingredient.of(itemSet.getRawOre()), RecipeCategory.MISC, itemSet.getIngot(), itemSet.getXp(), 200)
                        .unlockedBy("has_material", has(itemSet.getRawOre()))
                        .save(output, recipeKey("smelting/" + name.toLowerCase(Locale.ROOT) + "_from_raw_ore"));
                }
                SimpleCookingRecipeBuilder.blasting(Ingredient.of(itemSet.getRawOre()), RecipeCategory.MISC, itemSet.getIngot(), itemSet.getXp(), 100)
                    .unlockedBy("has_material", has(itemSet.getRawOre()))
                    .save(output, recipeKey("blasting/" + name.toLowerCase(Locale.ROOT) + "_from_raw_ore"));
            }
        });

        ShapedRecipeBuilder.shaped(itemLookup, RecipeCategory.MISC, MythicMaterials.DURASTEEL.extraItems().get(MythicResourceKeys.DURASTEEL_ENGINE))
            .define('#', MythicMaterials.DURASTEEL.baseMaterial())
            .define('B', MythicMaterials.DURASTEEL.blockSet().storage())
            .define('M', MythicMaterials.MORKITE.baseMaterial())
            .define('H', Items.HOPPER)
            .pattern("#H#")
            .pattern("MBM")
            .pattern("###")
            .unlockedBy("has_material", has(MythicMaterials.DURASTEEL.baseMaterial()))
            .unlockedBy("has_fuel", has(MythicMaterials.MORKITE.baseMaterial()))
            .save(output, recipeKey("crafting/durasteel_engine"));
        ShapedRecipeBuilder.shaped(itemLookup, RecipeCategory.MISC, MythicMaterials.PROMETHEUM.extraItems().get(MythicResourceKeys.PROMETHEUM_ROSE))
            .define('#', MythicMaterials.PROMETHEUM.rawOre())
            .define('I', MythicMaterials.PROMETHEUM.baseMaterial())
            .define('F', ItemTags.SMALL_FLOWERS)
            .pattern("#F#")
            .pattern("FIF")
            .pattern("#F#")
            .unlockedBy("has_material", has(MythicMaterials.PROMETHEUM.rawOre()))
            .save(output, recipeKey("crafting/prometheum_rose"));
        ShapedRecipeBuilder.shaped(itemLookup, RecipeCategory.MISC, MythicMaterials.PROMETHEUM.extraItems().get(MythicResourceKeys.PROMETHEUM_ROSE))
            .define('#', MythicMaterials.PROMETHEUM.rawOre())
            .define('I', MythicMaterials.PROMETHEUM.baseMaterial())
            .define('F', ItemTags.SMALL_FLOWERS)
            .pattern("F#F")
            .pattern("#I#")
            .pattern("F#F")
            .unlockedBy("has_material", has(MythicMaterials.PROMETHEUM.rawOre()))
            .save(output, recipeKey("crafting/prometheum_rose_alt"));
    }

    private void createNuggetRecipes(HashMap<String, ItemSet> itemSets) {
//        ReflectionUtils.iterateAccessibleStaticFields(MythicItems.class, ItemSet.class, (itemSet, name, field) -> {
//            boolean requiresBlasting = itemSet.requiresBlasting();
//            var nugget = itemSet.getNugget();
//            assert nugget != null;
//
//            // smelt equipment into nuggets
//            if (!requiresBlasting) {
//                SimpleCookingRecipeBuilder.smelting(Ingredient.of(itemLookup.getOrThrow(RegistryHelper.itemTag("equipment/" + name))), RecipeCategory.MISC, nugget, 0.1f, 200)
//                    .unlockedBy("has_material", has(TagKey.create(Registries.ITEM, RegistryHelper.id("nuggets/" + name))))
//                    .save(nuggetExporter, recipeKey("smelting/" + name.toLowerCase(Locale.ROOT) + "_nugget_from_equipment"));
//            }
//            // blast equipment into nuggets
//            SimpleCookingRecipeBuilder.blasting(Ingredient.of(itemLookup.getOrThrow(RegistryHelper.itemTag("equipment/" + name))), RecipeCategory.MISC, nugget, 0.1f, 100)
//                .unlockedBy("has_material", has(TagKey.create(Registries.ITEM, RegistryHelper.id("nuggets/" + name))))
//                .save(nuggetExporter, recipeKey("blasting/" + name.toLowerCase(Locale.ROOT) + "_nugget_from_equipment"));
//        });
//
//        itemSets.values().forEach(itemSet -> {
//            if (itemSet.getNugget() != null) {
//                var name = itemSet.getName().toLowerCase(Locale.ROOT);
//                // crafting ingots from nuggets
//                ShapelessRecipeBuilder.shapeless(itemLookup, RecipeCategory.MISC, itemSet.getIngot())
//                    .unlockedBy("has_material", has(TagKey.create(Registries.ITEM, RegistryHelper.id("nuggets/" + itemSet.getName()))))
//                    .requires(itemSet.getNugget(), 9)
//                    .group("mm_" + name)
//                    .save(nuggetExporter, recipeKey("ingots/" + name + "_from_nuggets"));
//                // craft ingots into nuggets
//                ShapelessRecipeBuilder.shapeless(itemLookup, RecipeCategory.MISC, itemSet.getNugget(), 9)
//                    .unlockedBy("has_material", has(TagKey.create(Registries.ITEM, RegistryHelper.id(itemSet.getName() + "_ingot"))))
//                    .requires(itemSet.getIngot())
//                    .save(nuggetExporter, recipeKey("crafting/" + name + "_nuggets"));
//            }
//        });
    }

    private void createToolRecipes() {
        // Tool recipes
        // FIXME
//        createToolCraftingRecipes(MythicTools.ADAMANTITE, MythicMaterials.ADAMANTITE.baseMaterial(), itemLookup);
//        createToolCraftingRecipes(MythicTools.AQUARIUM, MythicMaterials.AQUARIUM.baseMaterial(), itemLookup);
//        createToolCraftingRecipes(MythicTools.BANGLUM, MythicMaterials.BANGLUM.baseMaterial(), itemLookup);
//        createToolCraftingRecipes(MythicTools.BRONZE, MythicMaterials.BRONZE.baseMaterial(), itemLookup);
////        createToolCraftingRecipes(MythicTools.COPPER, Items.COPPER_INGOT, itemLookup);
//        createToolCraftingRecipes(MythicTools.DURASTEEL, MythicMaterials.DURASTEEL.baseMaterial(), itemLookup);
//        createToolCraftingRecipes(MythicTools.KYBER, MythicMaterials.KYBER.baseMaterial(), itemLookup);
//        createToolCraftingRecipes(MythicTools.MYTHRIL, MythicMaterials.MYTHRIL.baseMaterial(), itemLookup);
//        createToolCraftingRecipes(MythicTools.HALLOWED, MythicMaterials.HALLOWED.baseMaterial(), itemLookup);
//        createToolCraftingRecipes(MythicTools.ORICHALCUM, MythicMaterials.ORICHALCUM.baseMaterial(), itemLookup);
//        createToolCraftingRecipes(MythicTools.OSMIUM, MythicMaterials.OSMIUM.baseMaterial(), itemLookup);
//        createToolCraftingRecipes(MythicTools.PALLADIUM, MythicMaterials.PALLADIUM.baseMaterial(), itemLookup);
//        createToolCraftingRecipes(MythicTools.PROMETHEUM, MythicMaterials.PROMETHEUM.baseMaterial(), itemLookup);
//        createToolCraftingRecipes(MythicTools.QUADRILLUM, MythicMaterials.QUADRILLUM.baseMaterial(), itemLookup);
//        createToolCraftingRecipes(MythicTools.RUNITE, MythicMaterials.RUNITE.baseMaterial(), itemLookup);
//        createToolCraftingRecipes(MythicTools.STAR_PLATINUM, MythicMaterials.STAR_PLATINUM.baseMaterial(), itemLookup);
//        createToolCraftingRecipes(MythicTools.STEEL, MythicMaterials.STEEL.baseMaterial(), itemLookup);
//        createToolCraftingRecipes(MythicTools.STORMYX, MythicMaterials.STORMYX.baseMaterial(), itemLookup);
//        createToolSmithingRecipes(
//            MythicMaterials.CARMOT.extraItems().get(MythicResourceKeys.CARMOT_SMITHING_TEMPLATE),
//            MythicTools.KYBER,
//            Ingredient.of(MythicMaterials.CARMOT.baseMaterial()),
//            MythicTools.CARMOT
//        );
//        createToolSmithingRecipes(
//            MythicMaterials.LEGENDARY_BANGLUM.extraItems().get(MythicResourceKeys.LEGENDARY_BANGLUM_SMITHING_TEMPLATE),
//            MythicTools.BANGLUM,
//            Ingredient.of(MythicMaterials.LEGENDARY_BANGLUM.baseMaterial()),
//            MythicTools.LEGENDARY_BANGLUM
//        );
//        createToolSmithingRecipes(
//            MythicMaterials.TIDESINGER.extraItems().get(MythicResourceKeys.TIDESINGER_SMITHING_TEMPLATE),
//            MythicTools.AQUARIUM,
//            Ingredient.of(itemLookup.getOrThrow(MythicTags.TIDESINGER_CORAL)),
//            MythicTools.TIDESINGER
//        );
//        createToolSmithingRecipes(
//            MythicMaterials.UNOBTAINIUM.extraItems().get(MythicResourceKeys.UNOBTAINIUM_SMITHING_TEMPLATE),
//            Items.DIAMOND_SWORD,
//            Items.DIAMOND_AXE,
//            Items.DIAMOND_PICKAXE,
//            Items.DIAMOND_SHOVEL,
//            Items.DIAMOND_HOE,
//            Ingredient.of(MythicMaterials.CELESTIUM.baseMaterial()),
//            MythicTools.CELESTIUM
//        );
//        createToolSmithingRecipes(
//            MythicMaterials.UNOBTAINIUM.extraItems().get(MythicResourceKeys.UNOBTAINIUM_SMITHING_TEMPLATE),
//            Items.NETHERITE_SWORD,
//            Items.NETHERITE_AXE,
//            Items.NETHERITE_PICKAXE,
//            Items.NETHERITE_SHOVEL,
//            Items.NETHERITE_HOE,
//            Ingredient.of(MythicMaterials.METALLURGIUM.baseMaterial()),
//            MythicTools.METALLURGIUM
//        );
//
//        ShapedRecipeBuilder.shaped(itemLookup, RecipeCategory.TOOLS, MythicMaterials.BANGLUM.extraItems().get(MythicResourceKeys.BANGLUM_TNT_MINECART))
//            .define('#', Items.MINECART)
//            .define('S', MythicMaterials.BANGLUM.extraBlocks().get(MythicResourceKeys.BANGLUM_TNT).asItem())
//            .pattern("S")
//            .pattern("#")
//            .unlockedBy("has_material", has(MythicMaterials.BANGLUM.extraBlocks().get(MythicResourceKeys.BANGLUM_TNT)))
//            .save(output, recipeKey("tools/banglum_tnt_minecart"));
//        ShapedRecipeBuilder.shaped(itemLookup, RecipeCategory.TOOLS, MythicMaterials.CARMOT.extraBlocks().get(MythicResourceKeys.CARMOT_BELL))
//            .define('#', MythicMaterials.CARMOT.baseMaterial())
//            .define('S', MythicMaterials.CARMOT.extraItems().get(MythicResourceKeys.CARMOT_STONE))
//            .pattern(" # ")
//            .pattern("#S#")
//            .pattern("# #")
//            .unlockedBy("has_material", has(MythicMaterials.CARMOT.baseMaterial()))
//            .unlockedBy("has_secret_stone", has(MythicMaterials.CARMOT.extraItems().get(MythicResourceKeys.CARMOT_STONE)))
//            .save(output, recipeKey("tools/carmot_bell"));
//        ShapedRecipeBuilder.shaped(itemLookup, RecipeCategory.TOOLS, MythicMaterials.PALLADIUM.extraItems().get(MythicResourceKeys.PALLADIUM_MINECART))
//            .define('#', MythicMaterials.PALLADIUM.baseMaterial())
//            .pattern("# #")
//            .pattern("###")
//            .unlockedBy("has_material", has(MythicMaterials.PALLADIUM.baseMaterial()))
//            .save(output, recipeKey("tools/palladium_minecart"));
//        ShapedRecipeBuilder.shaped(itemLookup, RecipeCategory.TOOLS, MythicTools.ORICHALCUM_HAMMER)
//            .define('#', MythicMaterials.ORICHALCUM.blockSet().storage())
//            .define('S', Items.STICK)
//            .pattern(" # ")
//            .pattern(" S#")
//            .pattern("S  ")
//            .unlockedBy("has_material", has(MythicMaterials.ORICHALCUM.baseMaterial()))
//            .save(output, recipeKey("tools/orichalcum_hammer"));
//        SmithingTransformRecipeBuilder.smithing(
//                Ingredient.of(MythicMaterials.MYTHRIL.extraItems().get(MythicResourceKeys.MYTHRIL_DRILL_SMITHING_TEMPLATE)),
//                Ingredient.of(MythicTools.MYTHRIL.getPickaxe()),
//                Ingredient.of(MythicMaterials.DURASTEEL.extraItems().get(MythicResourceKeys.DURASTEEL_ENGINE)),
//                RecipeCategory.TOOLS,
//                MythicTools.MYTHRIL_DRILL
//            )
//            .unlocks("has_material_for_pick", has(MythicMaterials.MYTHRIL.baseMaterial()))
//            .unlocks("has_engine", has(MythicMaterials.DURASTEEL.extraItems().get(MythicResourceKeys.DURASTEEL_ENGINE)))
//            .save(output, recipeKey("tools/mythril_drill"));
//        ShapedRecipeBuilder.shaped(itemLookup, RecipeCategory.TOOLS, MythicTools.STAR_PLATINUM_ARROW, 2)
//            .define('#', MythicMaterials.STAR_PLATINUM.nugget())
//            .define('S', Items.STICK)
//            .define('F', Items.FEATHER)
//            .pattern("  #")
//            .pattern(" S ")
//            .pattern("F  ")
//            .unlockedBy("has_material", has(MythicMaterials.STAR_PLATINUM.baseMaterial()))
//            .save(nuggetExporter, recipeKey("weapons/star_platinum_arrow"));
//        ShapedRecipeBuilder.shaped(itemLookup, RecipeCategory.TOOLS, MythicTools.RUNITE_ARROW, 4)
//            .define('#', MythicMaterials.RUNITE.nugget())
//            .define('S', Items.STICK)
//            .define('F', Items.FEATHER)
//            .pattern("  #")
//            .pattern(" S ")
//            .pattern("F  ")
//            .unlockedBy("has_material", has(MythicMaterials.RUNITE.baseMaterial()))
//            .save(nuggetExporter, recipeKey("weapons/runite_arrow"));
//        ShapedRecipeBuilder.shaped(itemLookup, RecipeCategory.TOOLS, MythicTools.STORMYX_SHIELD)
//            .define('#', MythicMaterials.STORMYX.baseMaterial())
//            .define('S', MythicMaterials.STORMYX.extraItems().get(MythicResourceKeys.STORMYX_SHELL))
//            .pattern("#S#")
//            .pattern("###")
//            .pattern(" # ")
//            .unlockedBy("has_shell", has(MythicMaterials.STORMYX.extraItems().get(MythicResourceKeys.STORMYX_SHELL)))
//            .save(output, recipeKey("tools/stormyx_shield"));
//        ShapedRecipeBuilder.shaped(itemLookup, RecipeCategory.TOOLS, MythicTools.PLATINUM_WATCH)
//            .define('#', MythicMaterials.PLATINUM.baseMaterial())
//            .define('R', Items.REDSTONE)
//            .pattern(" # ")
//            .pattern("#R#")
//            .pattern(" # ")
//            .unlockedBy("has_material", has(MythicMaterials.PLATINUM.baseMaterial()))
//            .save(output, recipeKey("tools/platinum_watch"));
//        SmithingTransformRecipeBuilder.smithing(
//                Ingredient.of(MythicMaterials.AEGIS.extraItems().get(MythicResourceKeys.AEGIS_SMITHING_TEMPLATE)),
//                Ingredient.of(MythicTools.ADAMANTITE.getSword()),
//                Ingredient.of(MythicMaterials.PALLADIUM.blockSet().storage()),
//                RecipeCategory.COMBAT,
//                MythicTools.RED_AEGIS_SWORD
//            )
//            .unlocks("has_template", has(MythicMaterials.AEGIS.extraItems().get(MythicResourceKeys.AEGIS_SMITHING_TEMPLATE)))
//            .save(output, recipeKey("weapons/red_aegis_sword"));
//        SmithingTransformRecipeBuilder.smithing(
//                Ingredient.of(MythicMaterials.AEGIS.extraItems().get(MythicResourceKeys.AEGIS_SMITHING_TEMPLATE)),
//                Ingredient.of(MythicTools.HALLOWED.getSword()),
//                Ingredient.of(MythicMaterials.HALLOWED.blockSet().storage()),
//                RecipeCategory.COMBAT,
//                MythicTools.WHITE_AEGIS_SWORD
//            )
//            .unlocks("has_template", has(MythicMaterials.AEGIS.extraItems().get(MythicResourceKeys.AEGIS_SMITHING_TEMPLATE)))
//            .save(output, recipeKey("weapons/white_aegis_sword"));
    }

    public void createSmithingTemplateRecipes() {
        // Crafted Smithing Templates
        ShapedRecipeBuilder.shaped(itemLookup, RecipeCategory.TOOLS, MythicMaterials.CARMOT.extraItems().get(MythicResourceKeys.CARMOT_SMITHING_TEMPLATE))
            .define('M', Items.SMOOTH_BASALT)
            .define('C', MythicMaterials.CARMOT.baseMaterial())
            .define('D', Items.DIAMOND)
            .pattern("DCD")
            .pattern("CMC")
            .pattern("DCD")
            .unlockedBy("has_material", has(MythicMaterials.CARMOT.baseMaterial()))
            .group("mm_carmot_template")
            .save(output, recipeKey("smithing_templates/carmot"));

        ShapedRecipeBuilder.shaped(itemLookup, RecipeCategory.TOOLS, MythicMaterials.LEGENDARY_BANGLUM.extraItems().get(MythicResourceKeys.LEGENDARY_BANGLUM_SMITHING_TEMPLATE))
            .define('M', Items.TUFF)
            .define('C', MythicMaterials.BANGLUM.baseMaterial())
            .pattern("MMM")
            .pattern("MCM")
            .pattern("MMM")
            .unlockedBy("has_material", has(MythicMaterials.BANGLUM.baseMaterial()))
            .group("mm_banglum_template")
            .save(output, recipeKey("smithing_templates/legendary_banglum"));

        var enchantedMidas = MythicMaterials.MIDAS_GOLD.extraBlocks().get(MythicResourceKeys.ENCHANTED_MIDAS_GOLD_BLOCK);
        ShapedRecipeBuilder.shaped(itemLookup, RecipeCategory.TOOLS, MythicMaterials.MIDAS_GOLD.extraItems().get(MythicResourceKeys.ROYAL_MIDAS_SMITHING_TEMPLATE))
            .define('M', Items.NETHERRACK)
            .define('C', MythicMaterials.MIDAS_GOLD.blockSet().storage())
            .define('T', enchantedMidas)
            .pattern("CTC")
            .pattern("CMC")
            .pattern("CCC")
            .unlockedBy("has_material", has(MythicMaterials.MIDAS_GOLD.blockSet().storage()))
            .unlockedBy("has_enchanted_material", has(enchantedMidas))
            .group("mm_royal_midas_template")
            .save(output, recipeKey("smithing_templates/royal_midas"));

        ShapedRecipeBuilder.shaped(itemLookup, RecipeCategory.TOOLS, MythicMaterials.OSMIUM.extraItems().get(MythicResourceKeys.OSMIUM_CHAINMAIL_SMITHING_TEMPLATE))
            .define('M', Items.ANDESITE)
            .define('C', MythicMaterials.OSMIUM.nugget())
            .define('T', MythicMaterials.OSMIUM.baseMaterial())
            .pattern("CTC")
            .pattern("CMC")
            .pattern("CTC")
            .unlockedBy("has_material", has(MythicMaterials.OSMIUM.baseMaterial()))
            .group("mm_osmium_template")
            .save(output, recipeKey("smithing_templates/osmium_chainmail"));

        ShapedRecipeBuilder.shaped(itemLookup, RecipeCategory.TOOLS, MythicMaterials.MIDAS_GOLD.extraItems().get(MythicResourceKeys.MIDAS_FOLDING_TEMPLATE))
            .define('M', Items.NETHERRACK)
            .define('C', MythicMaterials.MIDAS_GOLD.baseMaterial())
            .pattern("MMM")
            .pattern("MCM")
            .pattern("MMM")
            .unlockedBy("has_material", has(MythicMaterials.MIDAS_GOLD.baseMaterial()))
            .group("mm_midas_folding_template")
            .save(output, recipeKey("smithing_templates/midas_folding"));

        ShapedRecipeBuilder.shaped(itemLookup, RecipeCategory.TOOLS, MythicMaterials.TIDESINGER.extraItems().get(MythicResourceKeys.TIDESINGER_SMITHING_TEMPLATE))
            .define('O', Items.PRISMARINE)
            .define('L', MythicMaterials.AQUARIUM.extraItems().get(MythicResourceKeys.AQUARIUM_PEARL))
            .define('R', Items.BRAIN_CORAL)
            .define('G', MythicMaterials.AQUARIUM.baseMaterial())
            .define('B', Items.FIRE_CORAL)
            .define('M', Items.BUBBLE_CORAL)
            .define('E', Items.TUBE_CORAL)
            .define('N', Items.HORN_CORAL)
            .pattern("RGB")
            .pattern("LOL")
            .pattern("MEN")
            .unlockedBy("has_material", has(MythicMaterials.AQUARIUM.extraItems().get(MythicResourceKeys.AQUARIUM_PEARL)))
            .unlockedBy("has_coral", has(MythicTags.TIDESINGER_CORAL))
            .group("mm_tidesinger_template")
            .save(output, recipeKey("smithing_templates/tidesinger"));

        // Smithing Template Duplication recipes
        ShapedRecipeBuilder.shaped(itemLookup, RecipeCategory.TOOLS, MythicMaterials.AEGIS.extraItems().get(MythicResourceKeys.AEGIS_SMITHING_TEMPLATE), 2)
            .define('C', Items.EMERALD)
            .define('T', MythicMaterials.AEGIS.extraItems().get(MythicResourceKeys.AEGIS_SMITHING_TEMPLATE))
            .define('M', Items.DEEPSLATE)
            .pattern("CTC")
            .pattern("CMC")
            .pattern("CCC")
            .unlockedBy("has_material", has(MythicMaterials.AEGIS.extraItems().get(MythicResourceKeys.AEGIS_SMITHING_TEMPLATE)))
            .group("mm_aegis_template")
            .save(output, recipeKey("smithing_templates/aegis_duplicate"));
        ShapedRecipeBuilder.shaped(itemLookup, RecipeCategory.TOOLS, MythicMaterials.CARMOT.extraItems().get(MythicResourceKeys.CARMOT_SMITHING_TEMPLATE), 2)
            .define('C', MythicMaterials.CARMOT.nugget())
            .define('P', Items.DIAMOND)
            .define('T', MythicMaterials.CARMOT.extraItems().get(MythicResourceKeys.CARMOT_SMITHING_TEMPLATE))
            .define('M', Items.SMOOTH_BASALT)
            .pattern("CTC")
            .pattern("CMC")
            .pattern("CPC")
            .unlockedBy("has_material", has(MythicMaterials.CARMOT.extraItems().get(MythicResourceKeys.CARMOT_SMITHING_TEMPLATE)))
            .group("mm_carmot_template")
            .save(nuggetExporter, recipeKey("smithing_templates/carmot_duplicate"));
        ShapedRecipeBuilder.shaped(itemLookup, RecipeCategory.TOOLS, MythicMaterials.MYTHRIL.extraItems().get(MythicResourceKeys.MYTHRIL_DRILL_SMITHING_TEMPLATE), 2)
            .define('C', Items.DIAMOND)
            .define('T', MythicMaterials.MYTHRIL.extraItems().get(MythicResourceKeys.MYTHRIL_DRILL_SMITHING_TEMPLATE))
            .define('M', Items.DEEPSLATE)
            .pattern("CTC")
            .pattern("CMC")
            .pattern("CCC")
            .unlockedBy("has_material", has(MythicMaterials.MYTHRIL.extraItems().get(MythicResourceKeys.MYTHRIL_DRILL_SMITHING_TEMPLATE)))
            .group("mm_mythril_drill_template")
            .save(output, recipeKey("smithing_templates/mythril_drill_duplicate"));
        ShapedRecipeBuilder.shaped(itemLookup, RecipeCategory.TOOLS, MythicMaterials.MIDAS_GOLD.extraItems().get(MythicResourceKeys.MIDAS_FOLDING_TEMPLATE), 2)
            .define('M', MythicMaterials.MIDAS_GOLD.nugget())
            .define('C', MythicMaterials.MIDAS_GOLD.extraItems().get(MythicResourceKeys.MIDAS_FOLDING_TEMPLATE))
            .pattern("MMM")
            .pattern("MCM")
            .pattern("MMM")
            .unlockedBy("has_material", has(MythicMaterials.MIDAS_GOLD.extraItems().get(MythicResourceKeys.MIDAS_FOLDING_TEMPLATE)))
            .group("mm_midas_folding_template")
            .save(nuggetExporter, recipeKey("smithing_templates/midas_folding_duplicate"));
        ShapedRecipeBuilder.shaped(itemLookup, RecipeCategory.TOOLS, MythicMaterials.TIDESINGER.extraItems().get(MythicResourceKeys.TIDESINGER_SMITHING_TEMPLATE), 2)
            .define('C', MythicMaterials.AQUARIUM.baseMaterial())
            .define('P', MythicMaterials.AQUARIUM.extraItems().get(MythicResourceKeys.AQUARIUM_PEARL))
            .define('T', MythicMaterials.TIDESINGER.extraItems().get(MythicResourceKeys.TIDESINGER_SMITHING_TEMPLATE))
            .define('M', Items.PRISMARINE)
            .pattern("CTC")
            .pattern("CMC")
            .pattern("CPC")
            .unlockedBy("has_material", has(MythicMaterials.TIDESINGER.extraItems().get(MythicResourceKeys.TIDESINGER_SMITHING_TEMPLATE)))
            .group("mm_tidesinger_template")
            .save(output, recipeKey("smithing_templates/tidesinger_duplicate"));
        ShapedRecipeBuilder.shaped(itemLookup, RecipeCategory.TOOLS, MythicMaterials.MIDAS_GOLD.extraItems().get(MythicResourceKeys.ROYAL_MIDAS_SMITHING_TEMPLATE), 2)
            .define('C', MythicMaterials.MIDAS_GOLD.blockSet().storage())
            .define('T', MythicMaterials.MIDAS_GOLD.extraItems().get(MythicResourceKeys.ROYAL_MIDAS_SMITHING_TEMPLATE))
            .define('M', Items.NETHERRACK)
            .pattern("CTC")
            .pattern("CMC")
            .pattern("CCC")
            .unlockedBy("has_material", has(MythicMaterials.MIDAS_GOLD.extraItems().get(MythicResourceKeys.ROYAL_MIDAS_SMITHING_TEMPLATE)))
            .group("mm_royal_midas_template")
            .save(output, recipeKey("smithing_templates/royal_midas_duplicate"));
        ShapedRecipeBuilder.shaped(itemLookup, RecipeCategory.TOOLS, MythicMaterials.UNOBTAINIUM.extraItems().get(MythicResourceKeys.UNOBTAINIUM_SMITHING_TEMPLATE), 2)
            .define('C', Items.DIAMOND)
            .define('T', MythicMaterials.UNOBTAINIUM.extraItems().get(MythicResourceKeys.UNOBTAINIUM_SMITHING_TEMPLATE))
            .define('M', Items.DEEPSLATE)
            .pattern("CTC")
            .pattern("CMC")
            .pattern("CCC")
            .unlockedBy("has_material", has(MythicMaterials.UNOBTAINIUM.extraItems().get(MythicResourceKeys.UNOBTAINIUM_SMITHING_TEMPLATE)))
            .group("mm_unobtainium_template")
            .save(output, recipeKey("smithing_templates/unobtainium_alloy"));
    }

    public void createToolCraftingRecipes(ToolSet toolSet, Item material, HolderGetter<Item> lookup) {
        // sword
        ShapedRecipeBuilder.shaped(lookup, RecipeCategory.TOOLS, toolSet.getSword())
            .define('#', material)
            .define('S', WOODEN_RODS)
            .pattern("#")
            .pattern("#")
            .pattern("S")
            .unlockedBy("has_sword", has(toolSet.getSword()))
            .save(output, recipeKey("sword/" + toolSet.getName()));
        // axe
        ShapedRecipeBuilder.shaped(lookup, RecipeCategory.TOOLS, toolSet.getAxe())
            .define('#', material)
            .define('S', WOODEN_RODS)
            .pattern("## ")
            .pattern("#S ")
            .pattern(" S ")
            .unlockedBy("has_axe", has(toolSet.getAxe()))
            .save(output, recipeKey("axe/" + toolSet.getName()));
        // pickaxe
        ShapedRecipeBuilder.shaped(lookup, RecipeCategory.TOOLS, toolSet.getPickaxe())
            .define('#', material)
            .define('S', WOODEN_RODS)
            .pattern("###")
            .pattern(" S ")
            .pattern(" S ")
            .unlockedBy("has_pickaxe", has(toolSet.getPickaxe()))
            .save(output, recipeKey("pickaxe/" + toolSet.getName()));
        // shovel
        ShapedRecipeBuilder.shaped(lookup, RecipeCategory.TOOLS, toolSet.getShovel())
            .define('#', material)
            .define('S', WOODEN_RODS)
            .pattern("#")
            .pattern("#")
            .pattern("S")
            .unlockedBy("has_shovel", has(toolSet.getShovel()))
            .save(output, recipeKey("shovel/" + toolSet.getName()));
        // hoe
        ShapedRecipeBuilder.shaped(lookup, RecipeCategory.TOOLS, toolSet.getHoe())
            .define('#', material)
            .define('S', WOODEN_RODS)
            .pattern("## ")
            .pattern(" S ")
            .pattern(" S ")
            .unlockedBy("has_hoe", has(toolSet.getHoe()))
            .save(output, recipeKey("hoe/" + toolSet.getName()));
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
        var templateIngredient = Ingredient.of(template);
        SmithingTransformRecipeBuilder.smithing(templateIngredient, Ingredient.of(baseSword), addition, RecipeCategory.TOOLS, resultToolset.getSword())
            .unlocks("has_template", has(template))
            .unlocks("has_component_tool", has(baseSword))
            .unlocks("has_sword", has(resultToolset.getSword()))
            .save(output, recipeKey("sword/" + resultToolset.getName()));
        SmithingTransformRecipeBuilder.smithing(templateIngredient, Ingredient.of(baseAxe), addition, RecipeCategory.TOOLS, resultToolset.getAxe())
            .unlocks("has_template", has(template))
            .unlocks("has_component_tool", has(baseAxe))
            .unlocks("has_axe", has(resultToolset.getAxe()))
            .save(output, recipeKey("axe/" + resultToolset.getName()));
        SmithingTransformRecipeBuilder.smithing(templateIngredient, Ingredient.of(basePickaxe), addition, RecipeCategory.TOOLS, resultToolset.getPickaxe())
            .unlocks("has_template", has(template))
            .unlocks("has_component_tool", has(basePickaxe))
            .unlocks("has_pickaxe", has(resultToolset.getPickaxe()))
            .save(output, recipeKey("pickaxe/" + resultToolset.getName()));
        SmithingTransformRecipeBuilder.smithing(templateIngredient, Ingredient.of(baseShovel), addition, RecipeCategory.TOOLS, resultToolset.getShovel())
            .unlocks("has_template", has(template))
            .unlocks("has_component_tool", has(baseShovel))
            .unlocks("has_shovel", has(resultToolset.getShovel()))
            .save(output, recipeKey("shovel/" + resultToolset.getName()));
        SmithingTransformRecipeBuilder.smithing(templateIngredient, Ingredient.of(baseHoe), addition, RecipeCategory.TOOLS, resultToolset.getHoe())
            .unlocks("has_template", has(template))
            .unlocks("has_component_tool", has(baseHoe))
            .unlocks("has_hoe", has(resultToolset.getHoe()))
            .save(output, recipeKey("hoe/" + resultToolset.getName()));
    }

    public void createToolSmithingRecipes(Item template, ToolSet baseToolset, Ingredient addition, ToolSet resultToolset) {
        createToolSmithingRecipes(template, baseToolset.getSword(), baseToolset.getAxe(), baseToolset.getPickaxe(), baseToolset.getShovel(), baseToolset.getHoe(), addition, resultToolset);
    }

    public void createArmorRecipes() {
        createArmorCraftingRecipes(MythicMaterials.ADAMANTITE.armorSet(), MythicMaterials.ADAMANTITE.baseMaterial());
        createArmorCraftingRecipes(MythicMaterials.AQUARIUM.armorSet(), MythicMaterials.AQUARIUM.baseMaterial());
        createArmorCraftingRecipes(MythicMaterials.BANGLUM.armorSet(), MythicMaterials.BANGLUM.baseMaterial());
        createArmorCraftingRecipes(MythicMaterials.BRONZE.armorSet(), MythicMaterials.BRONZE.baseMaterial());
        createArmorCraftingRecipes(MythicMaterials.DURASTEEL.armorSet(), MythicMaterials.DURASTEEL.baseMaterial());
        createArmorCraftingRecipes(MythicMaterials.HALLOWED.armorSet(), MythicMaterials.HALLOWED.baseMaterial());
        createArmorCraftingRecipes(MythicMaterials.KYBER.armorSet(), MythicMaterials.KYBER.baseMaterial());
        createArmorCraftingRecipes(MythicMaterials.MIDAS_GOLD.armorSet(), MythicMaterials.MIDAS_GOLD.baseMaterial());
        createArmorCraftingRecipes(MythicMaterials.MYTHRIL.armorSet(), MythicMaterials.MYTHRIL.baseMaterial());
        createArmorCraftingRecipes(MythicMaterials.ORICHALCUM.armorSet(), MythicMaterials.ORICHALCUM.baseMaterial());
        createArmorCraftingRecipes(MythicMaterials.OSMIUM.armorSet(), MythicMaterials.OSMIUM.baseMaterial());
        createArmorCraftingRecipes(MythicMaterials.PALLADIUM.armorSet(), MythicMaterials.PALLADIUM.baseMaterial());
        createArmorCraftingRecipes(MythicMaterials.PROMETHEUM.armorSet(), MythicMaterials.PROMETHEUM.baseMaterial());
        createArmorCraftingRecipes(MythicMaterials.RUNITE.armorSet(), MythicMaterials.RUNITE.baseMaterial());
        createArmorCraftingRecipes(MythicMaterials.SILVER.armorSet(), MythicMaterials.SILVER.baseMaterial());
        createArmorCraftingRecipes(MythicMaterials.STAR_PLATINUM.armorSet(), MythicMaterials.STAR_PLATINUM.baseMaterial());
        createArmorCraftingRecipes(MythicMaterials.STEEL.armorSet(), MythicMaterials.STEEL.baseMaterial());
        createArmorCraftingRecipes(MythicMaterials.STORMYX.armorSet(), MythicMaterials.STORMYX.baseMaterial());

        createArmorSmithingRecipes(
            MythicMaterials.CARMOT.extraItems().get(MythicResourceKeys.CARMOT_SMITHING_TEMPLATE),
            MythicMaterials.KYBER.armorSet(),
            Ingredient.of(MythicMaterials.CARMOT.baseMaterial()),
            MythicMaterials.CARMOT.armorSet()
            );
        createArmorSmithingRecipes(
            MythicMaterials.UNOBTAINIUM.extraItems().get(MythicResourceKeys.UNOBTAINIUM_SMITHING_TEMPLATE),
            Items.NETHERITE_HELMET,
            Items.NETHERITE_CHESTPLATE,
            Items.NETHERITE_LEGGINGS,
            Items.NETHERITE_BOOTS,
            Ingredient.of(MythicMaterials.METALLURGIUM.baseMaterial()),
            MythicMaterials.METALLURGIUM.armorSet()
        );
        createArmorSmithingRecipes(
            MythicMaterials.UNOBTAINIUM.extraItems().get(MythicResourceKeys.UNOBTAINIUM_SMITHING_TEMPLATE),
            Items.DIAMOND_HELMET,
            Items.DIAMOND_CHESTPLATE,
            Items.DIAMOND_LEGGINGS,
            Items.DIAMOND_BOOTS,
            Ingredient.of(MythicMaterials.CELESTIUM.baseMaterial()),
            MythicMaterials.CELESTIUM.armorSet()
        );
        createArmorSmithingRecipes(
            MythicMaterials.LEGENDARY_BANGLUM.extraItems().get(MythicResourceKeys.LEGENDARY_BANGLUM_SMITHING_TEMPLATE),
            MythicMaterials.BANGLUM.armorSet(),
            Ingredient.of(MythicMaterials.LEGENDARY_BANGLUM.baseMaterial()),
            MythicMaterials.LEGENDARY_BANGLUM.armorSet()
        );
        createArmorSmithingRecipes(
            MythicMaterials.OSMIUM.extraItems().get(MythicResourceKeys.OSMIUM_CHAINMAIL_SMITHING_TEMPLATE),
            Items.DIAMOND_HELMET,
            Items.DIAMOND_CHESTPLATE,
            Items.DIAMOND_LEGGINGS,
            Items.DIAMOND_BOOTS,
            Ingredient.of(MythicMaterials.OSMIUM.baseMaterial()),
            MythicArmorSets.OSMIUM_CHAINMAIL
        );
        createTidesingerArmorRecipes();
    }

    private void createTidesingerArmorRecipes() {
        // FIXME - Might be time to consider "configurable" attributes via recipes, if data generated
        //  is going to include these anyways
//        var template = MythicMaterials.TIDESINGER.extraItems().get(MythicResourceKeys.TIDESINGER_SMITHING_TEMPLATE);
//        for (var coral : TidesingerPatternComponent.TIDESINGER_VARIANTS.keySet()) {
//            var addition = Ingredient.of(coral);
//            var name = TidesingerPatternComponent.TIDESINGER_VARIANTS.get(coral);
//            // helmet
//            var helmetOutput = new ItemStack(MythicArmor.TIDESINGER.getHelmet(), 1);
//            helmetOutput.set(MythicDataComponents.TIDESINGER, TidesingerPatternComponent.fromItem(coral));
//            var helmetRecipe = new SmithingTransformRecipe(
//                Optional.of(Ingredient.of(template)),
//                Ingredient.of(MythicArmor.AQUARIUM.getHelmet()),
//                Optional.of(addition),
//                helmetOutput
//            );
//            // chestplate
//            var chestplateOutput = new ItemStack(MythicArmor.TIDESINGER.getChestplate(), 1);
//            chestplateOutput.set(MythicDataComponents.TIDESINGER, TidesingerPatternComponent.fromItem(coral));
//            var chestplateRecipe = new SmithingTransformRecipe(
//                Optional.of(Ingredient.of(template)),
//                Ingredient.of(MythicArmor.AQUARIUM.getChestplate()),
//                Optional.of(addition),
//                chestplateOutput
//            );
//            // leggings
//            var leggingsOutput = new ItemStack(MythicArmor.TIDESINGER.getLeggings(), 1);
//            leggingsOutput.set(MythicDataComponents.TIDESINGER, TidesingerPatternComponent.fromItem(coral));
//            var leggingsRecipe = new SmithingTransformRecipe(
//                Optional.of(Ingredient.of(template)),
//                Optional.of(Ingredient.of(MythicArmor.AQUARIUM.getLeggings())),
//                Optional.of(addition),
//                leggingsOutput
//            );
//            // boots
//            var bootsOutput = new ItemStack(MythicArmor.TIDESINGER.getBoots(), 1);
//            bootsOutput.set(MythicDataComponents.TIDESINGER, TidesingerPatternComponent.fromItem(coral));
//            var bootsRecipe = new SmithingTransformRecipe(
//                Optional.of(Ingredient.of(template)),
//                Optional.of(Ingredient.of(MythicArmor.AQUARIUM.getBoots())),
//                Optional.of(addition),
//                bootsOutput
//            );
//            output.accept(recipeKey("armor/tidesinger_helmet_" + name), helmetRecipe, null);
//            output.accept(recipeKey("armor/tidesinger_chestplate_" + name), chestplateRecipe, null);
//            output.accept(recipeKey("armor/tidesinger_leggings_" + name), leggingsRecipe, null);
//            output.accept(recipeKey("armor/tidesinger_boots_" + name), bootsRecipe, null);
//        }
    }

    public void createArmorCraftingRecipes (ArmorSet output, Item item) {
        createArmorCraftingRecipes(output, Ingredient.of(item));
    }

    public void createArmorCraftingRecipes(ArmorSet output, Ingredient material) {
        if (output == null) return;
        // helmet
        ShapedRecipeBuilder.shaped(itemLookup, RecipeCategory.COMBAT, output.getHelmet())
            .define('#', material)
            .pattern("###")
            .pattern("# #")
            .unlockedBy("has_helmet", has(output.getHelmet()))
            .save(this.output, recipeKey("armor/" + output.getName() + "_helmet"));
        // chestplate
        ShapedRecipeBuilder.shaped(itemLookup, RecipeCategory.COMBAT, output.getChestplate())
            .define('#', material)
            .pattern("# #")
            .pattern("###")
            .pattern("###")
            .unlockedBy("has_chestplate", has(output.getChestplate()))
            .save(this.output, recipeKey("armor/" + output.getName() + "_chestplate"));
        // leggings
        ShapedRecipeBuilder.shaped(itemLookup, RecipeCategory.COMBAT, output.getLeggings())
            .define('#', material)
            .pattern("###")
            .pattern("# #")
            .pattern("# #")
            .unlockedBy("has_leggings", has(output.getLeggings()))
            .save(this.output, recipeKey("armor/" + output.getName() + "_leggings"));
        // boots
        ShapedRecipeBuilder.shaped(itemLookup, RecipeCategory.COMBAT, output.getBoots())
            .define('#', material)
            .pattern("# #")
            .pattern("# #")
            .unlockedBy("has_boots", has(output.getBoots()))
            .save(this.output, recipeKey("armor/" + output.getName() + "_boots"));
    }

    public void createArmorSmithingRecipes(Item template, Item baseHelmet, Item baseChestplate, Item baseLeggings, Item baseBoots, Ingredient addition, ArmorSet outputArmorSet) {
        if (outputArmorSet == null) return;
        // helmet
        SmithingTransformRecipeBuilder.smithing(
                Ingredient.of(template),
                Ingredient.of(baseHelmet),
                addition,
                RecipeCategory.COMBAT,
                outputArmorSet.getHelmet()
            )
            .unlocks("has_helmet", has(outputArmorSet.getHelmet()))
            .save(output, recipeKey("armor/" + outputArmorSet.getName() + "_helmet"));
        // chestplate
        SmithingTransformRecipeBuilder.smithing(
                Ingredient.of(template),
                Ingredient.of(baseChestplate),
                addition,
                RecipeCategory.COMBAT,
                outputArmorSet.getChestplate()
            )
            .unlocks("has_chestplate", has(outputArmorSet.getChestplate()))
            .save(output, recipeKey("armor/" + outputArmorSet.getName() + "_chestplate"));
        // leggings
        SmithingTransformRecipeBuilder.smithing(
                Ingredient.of(template),
                Ingredient.of(baseLeggings),
                addition,
                RecipeCategory.COMBAT,
                outputArmorSet.getLeggings()
            )
            .unlocks("has_leggings", has(outputArmorSet.getLeggings()))
            .save(output, recipeKey("armor/" + outputArmorSet.getName() + "_leggings"));
        // boots
        SmithingTransformRecipeBuilder.smithing(
                Ingredient.of(template),
                Ingredient.of(baseBoots),
                addition,
                RecipeCategory.COMBAT,
                outputArmorSet.getBoots()
            )
            .unlocks("has_boots", has(outputArmorSet.getBoots()))
            .save(output, recipeKey("armor/" + outputArmorSet.getName() + "_boots"));
    }

    public void createArmorSmithingRecipes(Item template, ArmorSet baseArmorSet, Ingredient addition, ArmorSet outputArmorSet) {
        if (baseArmorSet == null) return;
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
