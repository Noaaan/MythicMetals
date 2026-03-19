package com.mythicmetals.data;

import com.mythicmetals.armor.ArmorSet;
import com.mythicmetals.armor.MythicArmor;
import com.mythicmetals.block.BlockSet;
import com.mythicmetals.block.MythicBlocks;
import com.mythicmetals.item.ItemSet;
import com.mythicmetals.item.MythicItems;
import com.mythicmetals.item.tools.MythicTools;
import com.mythicmetals.item.tools.ToolSet;
import com.mythicmetals.misc.RegistryHelper;
import io.wispforest.owo.util.ReflectionUtils;
import net.minecraft.advancements.criterion.ItemPredicate;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.recipes.*;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Block;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;

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
            if (blockSets.containsKey(name)) {
                var blockSet = blockSets.get(name);
                // Smelting Ore Blocks into ingots
                if (blockSet.getOre() != null) {
                    var oreList = new ArrayList<>(blockSet.getOreVariants());
                    oreList.add(blockSet.getOre());
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
                if (itemSet.getRawOre() != null && blockSet.getOreStorageBlock() != null) {
                    // Raw Ores to Raw Ore Block
                    ShapelessRecipeBuilder.shapeless(itemLookup, RecipeCategory.BUILDING_BLOCKS, blockSet.getOreStorageBlock().asItem())
                        .unlockedBy("has_material", has(blockSet.getOreStorageBlock().asItem()))
                        .requires(itemSet.getRawOre(), 9)
                        .save(output, recipeKey("blocks/raw_" + name));
                    // Raw Ores from Raw Ore Block
                    ShapelessRecipeBuilder.shapeless(itemLookup, RecipeCategory.BUILDING_BLOCKS, itemSet.getRawOre(), 9)
                        .unlockedBy("has_material", has(itemSet.getRawOre()))
                        .requires(blockSet.getOreStorageBlock().asItem())
                        .save(output, recipeKey("crafting/raw_" + name + "_from_block"));
                }
                if (blockSet.getStorageBlock() != null) {
                    // Ingots to Storage Block
                    ShapelessRecipeBuilder.shapeless(itemLookup, RecipeCategory.BUILDING_BLOCKS, blockSet.getStorageBlock().asItem())
                        .unlockedBy("has_material", has(blockSet.getStorageBlock().asItem()))
                        .requires(itemSet.getIngot(), 9)
                        .save(output, recipeKey("blocks/" + name));
                    // Ingots from Storage Block
                    ShapelessRecipeBuilder.shapeless(itemLookup, RecipeCategory.BUILDING_BLOCKS, itemSet.getIngot(), 9)
                        .unlockedBy("has_material", has(itemSet.getIngot()))
                        .requires(blockSet.getStorageBlock().asItem())
                        .save(output, recipeKey("ingots/" + name + "_from_block"));
                }
                if (blockSet.getStorageBlock() != null && blockSet.getAnvil() != null) {
                    ShapedRecipeBuilder.shaped(itemLookup, RecipeCategory.BUILDING_BLOCKS, blockSet.getAnvil())
                        .pattern("###")
                        .pattern(" I ")
                        .pattern("III")
                        .define('#', blockSet.getStorageBlock())
                        .define('I', itemSet.getIngot())
                        .unlockedBy("has_block", has(blockSet.getStorageBlock()))
                        .unlockedBy("has_ingot", has(itemSet.getIngot()))
                        .save(output, recipeKey("anvils/" + name));
                }
            }
        });

        // special case for materials
        ReflectionUtils.iterateAccessibleStaticFields(MythicItems.Mats.class, Item.class, (value, name, field) -> {
            if (blockSets.containsKey(name)) {
                var blockSet = blockSets.get(name);
                // Ingots to Storage Block
                ShapelessRecipeBuilder.shapeless(itemLookup, RecipeCategory.BUILDING_BLOCKS, blockSet.getStorageBlock())
                    .unlockedBy("has_material", has(blockSet.getStorageBlock()))
                    .requires(value, 9)
                    .save(output, recipeKey("blocks/" + name));
                // Ingots from Storage Block
                ShapelessRecipeBuilder.shapeless(itemLookup, RecipeCategory.BUILDING_BLOCKS, value, 9)
                    .unlockedBy("has_material", has(value))
                    .requires(blockSet.getStorageBlock())
                    .save(output, recipeKey("crafting/" + name));
            }
        });

        // misc blocks
        ShapedRecipeBuilder.shaped(itemLookup, RecipeCategory.BUILDING_BLOCKS, MythicBlocks.AQUARIUM_GLASS)
            .define('#', MythicItems.AQUARIUM.getRawOre())
            .define('S', Items.GLASS)
            .pattern(" # ")
            .pattern("#S#")
            .pattern(" # ")
            .unlockedBy("has_material", has(MythicItems.AQUARIUM.getRawOre()))
            .save(output, recipeKey("blocks/aquarium_glass"));
        // TODO - Make Aquarium Resonator craftable once ready
//        ShapedRecipeJsonBuilder.create(itemLookup, RecipeCategory.TOOLS, MythicBlocks.AQUARIUM_RESONATOR)
//            .input('#', ABC123)
//            .pattern("###")
//            .pattern("###")
//            .pattern("###")
//            .criterion("has_pearl", conditionsFromItem(MythicItems.Mats.AQUARIUM_PEARL))
//            .offerTo(exporter, RegistryHelper.recipeKey("blocks/aquarium_resonator"));
        ShapedRecipeBuilder.shaped(itemLookup, RecipeCategory.TOOLS, MythicBlocks.BANGLUM_TNT_BLOCK)
            .define('#', MythicItems.BANGLUM.getRawOre())
            .define('S', MythicItems.Mats.MORKITE)
            .pattern("#S#")
            .pattern("S#S")
            .pattern("#S#")
            .unlockedBy("has_big_material", has(MythicItems.BANGLUM.getRawOre()))
            .unlockedBy("has_big_real_material", has(MythicItems.Mats.MORKITE))
            .save(output, recipeKey("blocks/banglum_tnt"));
        ShapedRecipeBuilder.shaped(itemLookup, RecipeCategory.TOOLS, MythicBlocks.BANGLUM_TNT_BLOCK)
            .define('#', MythicItems.BANGLUM.getRawOre())
            .define('S', Items.GUNPOWDER)
            .pattern("#S#")
            .pattern("S#S")
            .pattern("#S#")
            .unlockedBy("has_big_material", has(MythicItems.BANGLUM.getRawOre()))
            .unlockedBy("has_big_real_material", has(Items.GUNPOWDER))
            .save(output, recipeKey("blocks/banglum_tnt_from_gunpowder"));
        ShapedRecipeBuilder.shaped(itemLookup, RecipeCategory.TOOLS, MythicBlocks.BANGLUM_NUKE_CORE)
            .define('#', MythicBlocks.BANGLUM.getOreStorageBlock())
            .define('S', MythicBlocks.MORKITE.getStorageBlock())
            .define('C', MythicItems.Mats.BANGLUM_CHUNK)
            .pattern("#S#")
            .pattern("SCS")
            .pattern("#S#")
            .unlockedBy("has_big_material", has(MythicBlocks.BANGLUM.getOreStorageBlock()))
            .unlockedBy("has_big_real_material", has(MythicItems.Mats.MORKITE))
            .unlockedBy("has_chunk", has(MythicItems.Mats.BANGLUM_CHUNK))
            .save(output, recipeKey("blocks/banglum_nuke_core"));
        ShapedRecipeBuilder.shaped(itemLookup, RecipeCategory.TOOLS, MythicBlocks.CARMOT_NUKE_CORE)
            .define('#', MythicItems.CARMOT.getIngot())
            .define('C', MythicBlocks.BANGLUM_NUKE_CORE)
            .pattern("###")
            .pattern("#C#")
            .pattern("###")
            .unlockedBy("has_nuke_core", has(MythicBlocks.BANGLUM_NUKE_CORE))
            .unlockedBy("has_material", has(MythicItems.CARMOT.getIngot()))
            .save(output, recipeKey("blocks/carmot_nuke_core"));
        ShapedRecipeBuilder.shaped(itemLookup, RecipeCategory.TOOLS, MythicBlocks.PALLADIUM_RAIL_ITEM, 16)
            .define('#', MythicItems.PALLADIUM.getIngot())
            .define('S', Items.IRON_INGOT)
            .pattern("# #")
            .pattern("#S#")
            .pattern("# #")
            .unlockedBy("has_material", has(MythicItems.PALLADIUM.getIngot()))
            .save(output, recipeKey("crafting/palladium_rail"));
        ShapedRecipeBuilder.shaped(itemLookup, RecipeCategory.TOOLS, MythicBlocks.QUADRILLUM_NUKE_CORE)
            .define('#', MythicItems.QUADRILLUM.getRawOre())
            .define('C', MythicBlocks.BANGLUM_NUKE_CORE)
            .pattern("###")
            .pattern("#C#")
            .pattern("###")
            .unlockedBy("has_nuke_core", has(MythicBlocks.BANGLUM_NUKE_CORE))
            .unlockedBy("has_material", has(MythicItems.QUADRILLUM.getRawOre()))
            .save(output, recipeKey("blocks/quadrillum_nuke_core"));
        ShapelessRecipeBuilder.shapeless(itemLookup, RecipeCategory.TOOLS, MythicBlocks.SPONGE_NUKE_CORE)
            .requires(MythicBlocks.BANGLUM_NUKE_CORE)
            .requires(Items.SPONGE)
            .unlockedBy("has_block", has(MythicBlocks.BANGLUM_NUKE_CORE))
            .save(output, recipeKey("blocks/sponge_nuke_core"));
        ShapedRecipeBuilder.shaped(itemLookup, RecipeCategory.TOOLS, Items.TNT)
            .define('#', Items.SAND)
            .define('S', MythicItems.Mats.MORKITE)
            .pattern("#S#")
            .pattern("S#S")
            .pattern("#S#")
            .unlockedBy("has_real_material", has(MythicItems.Mats.MORKITE))
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

        ShapedRecipeBuilder.shaped(itemLookup, RecipeCategory.MISC, MythicItems.Mats.DURASTEEL_ENGINE)
            .define('#', MythicItems.DURASTEEL.getIngot())
            .define('B', MythicBlocks.DURASTEEL.getStorageBlock())
            .define('M', MythicItems.Mats.MORKITE)
            .define('H', Items.HOPPER)
            .pattern("#H#")
            .pattern("MBM")
            .pattern("###")
            .unlockedBy("has_material", has(MythicItems.DURASTEEL.getIngot()))
            .unlockedBy("has_fuel", has(MythicItems.Mats.MORKITE))
            .save(output, recipeKey("crafting/durasteel_engine"));
        ShapedRecipeBuilder.shaped(itemLookup, RecipeCategory.MISC, MythicItems.Mats.PROMETHEUM_ROSE)
            .define('#', MythicItems.PROMETHEUM.getRawOre())
            .define('I', MythicItems.PROMETHEUM.getIngot())
            .define('F', ItemTags.SMALL_FLOWERS)
            .pattern("#F#")
            .pattern("FIF")
            .pattern("#F#")
            .unlockedBy("has_material", has(MythicItems.PROMETHEUM.getRawOre()))
            .save(output, recipeKey("crafting/prometheum_rose"));
        ShapedRecipeBuilder.shaped(itemLookup, RecipeCategory.MISC, MythicItems.Mats.PROMETHEUM_ROSE)
            .define('#', MythicItems.PROMETHEUM.getRawOre())
            .define('I', MythicItems.PROMETHEUM.getIngot())
            .define('F', ItemTags.SMALL_FLOWERS)
            .pattern("F#F")
            .pattern("#I#")
            .pattern("F#F")
            .unlockedBy("has_material", has(MythicItems.PROMETHEUM.getRawOre()))
            .save(output, recipeKey("crafting/prometheum_rose_alt"));
    }

    private void createNuggetRecipes(HashMap<String, ItemSet> itemSets) {
        ReflectionUtils.iterateAccessibleStaticFields(MythicItems.class, ItemSet.class, (itemSet, name, field) -> {
            boolean requiresBlasting = itemSet.requiresBlasting();
            var nugget = itemSet.getNugget();
            assert nugget != null;

            // smelt equipment into nuggets
            if (!requiresBlasting) {
                SimpleCookingRecipeBuilder.smelting(Ingredient.of(itemLookup.getOrThrow(RegistryHelper.itemTag("equipment/" + name))), RecipeCategory.MISC, nugget, 0.1f, 200)
                    .unlockedBy("has_material", has(TagKey.create(Registries.ITEM, RegistryHelper.id("nuggets/" + name))))
                    .save(nuggetExporter, recipeKey("smelting/" + name.toLowerCase(Locale.ROOT) + "_nugget_from_equipment"));
            }
            // blast equipment into nuggets
            SimpleCookingRecipeBuilder.blasting(Ingredient.of(itemLookup.getOrThrow(RegistryHelper.itemTag("equipment/" + name))), RecipeCategory.MISC, nugget, 0.1f, 100)
                .unlockedBy("has_material", has(TagKey.create(Registries.ITEM, RegistryHelper.id("nuggets/" + name))))
                .save(nuggetExporter, recipeKey("blasting/" + name.toLowerCase(Locale.ROOT) + "_nugget_from_equipment"));
        });

        itemSets.values().forEach(itemSet -> {
            if (itemSet.getNugget() != null) {
                var name = itemSet.getName().toLowerCase(Locale.ROOT);
                // crafting ingots from nuggets
                ShapelessRecipeBuilder.shapeless(itemLookup, RecipeCategory.MISC, itemSet.getIngot())
                    .unlockedBy("has_material", has(TagKey.create(Registries.ITEM, RegistryHelper.id("nuggets/" + itemSet.getName()))))
                    .requires(itemSet.getNugget(), 9)
                    .group("mm_" + name)
                    .save(nuggetExporter, recipeKey("ingots/" + name + "_from_nuggets"));
                // craft ingots into nuggets
                ShapelessRecipeBuilder.shapeless(itemLookup, RecipeCategory.MISC, itemSet.getNugget(), 9)
                    .unlockedBy("has_material", has(TagKey.create(Registries.ITEM, RegistryHelper.id(itemSet.getName() + "_ingot"))))
                    .requires(itemSet.getIngot())
                    .save(nuggetExporter, recipeKey("crafting/" + name + "_nuggets"));
            }
        });
    }

    private void createToolRecipes() {
        // Tool recipes
        createToolCraftingRecipes(MythicTools.ADAMANTITE, MythicItems.ADAMANTITE.getIngot(), itemLookup);
        createToolCraftingRecipes(MythicTools.AQUARIUM, MythicItems.AQUARIUM.getIngot(), itemLookup);
        createToolCraftingRecipes(MythicTools.BANGLUM, MythicItems.BANGLUM.getIngot(), itemLookup);
        createToolCraftingRecipes(MythicTools.BRONZE, MythicItems.BRONZE.getIngot(), itemLookup);
//        createToolCraftingRecipes(MythicTools.COPPER, Items.COPPER_INGOT, itemLookup);
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
            Ingredient.of(MythicItems.CARMOT.getIngot()),
            MythicTools.CARMOT
        );
        createToolSmithingRecipes(
            MythicItems.Templates.LEGENDARY_BANGLUM_SMITHING_TEMPLATE,
            MythicTools.BANGLUM,
            Ingredient.of(MythicItems.Mats.BANGLUM_CHUNK),
            MythicTools.LEGENDARY_BANGLUM
        );
        createToolSmithingRecipes(
            MythicItems.Templates.TIDESINGER_SMITHING_TEMPLATE,
            MythicTools.AQUARIUM,
            Ingredient.of(itemLookup.getOrThrow(MythicTags.TIDESINGER_CORAL)),
            MythicTools.TIDESINGER
        );
        createToolSmithingRecipes(
            MythicItems.Templates.UNOBTAINIUM_SMITHING_TEMPLATE,
            Items.DIAMOND_SWORD,
            Items.DIAMOND_AXE,
            Items.DIAMOND_PICKAXE,
            Items.DIAMOND_SHOVEL,
            Items.DIAMOND_HOE,
            Ingredient.of(MythicItems.CELESTIUM.getIngot()),
            MythicTools.CELESTIUM
        );
        createToolSmithingRecipes(
            MythicItems.Templates.UNOBTAINIUM_SMITHING_TEMPLATE,
            Items.NETHERITE_SWORD,
            Items.NETHERITE_AXE,
            Items.NETHERITE_PICKAXE,
            Items.NETHERITE_SHOVEL,
            Items.NETHERITE_HOE,
            Ingredient.of(MythicItems.METALLURGIUM.getIngot()),
            MythicTools.METALLURGIUM
        );

        ShapedRecipeBuilder.shaped(itemLookup, RecipeCategory.TOOLS, MythicTools.BANGLUM_TNT_MINECART)
            .define('#', Items.MINECART)
            .define('S', MythicBlocks.BANGLUM_TNT_BLOCK.asItem())
            .pattern("S")
            .pattern("#")
            .unlockedBy("has_material", has(MythicBlocks.BANGLUM_TNT_BLOCK))
            .save(output, recipeKey("tools/banglum_tnt_minecart"));
        ShapedRecipeBuilder.shaped(itemLookup, RecipeCategory.TOOLS, MythicTools.CARMOT_BELL)
            .define('#', MythicItems.CARMOT.getIngot())
            .define('S', MythicItems.Mats.CARMOT_STONE)
            .pattern(" # ")
            .pattern("#S#")
            .pattern("# #")
            .unlockedBy("has_material", has(MythicItems.CARMOT.getIngot()))
            .unlockedBy("has_secret_stone", has(MythicItems.Mats.CARMOT_STONE))
            .save(output, recipeKey("tools/carmot_bell"));
        ShapedRecipeBuilder.shaped(itemLookup, RecipeCategory.TOOLS, MythicTools.PALLADIUM_MINECART)
            .define('#', MythicItems.PALLADIUM.getIngot())
            .pattern("# #")
            .pattern("###")
            .unlockedBy("has_material", has(MythicItems.PALLADIUM.getIngot()))
            .save(output, recipeKey("tools/palladium_minecart"));
        ShapedRecipeBuilder.shaped(itemLookup, RecipeCategory.TOOLS, MythicTools.ORICHALCUM_HAMMER)
            .define('#', MythicBlocks.ORICHALCUM.getStorageBlock())
            .define('S', Items.STICK)
            .pattern(" # ")
            .pattern(" S#")
            .pattern("S  ")
            .unlockedBy("has_material", has(MythicItems.ORICHALCUM.getIngot()))
            .save(output, recipeKey("tools/orichalcum_hammer"));
        SmithingTransformRecipeBuilder.smithing(
                Ingredient.of(MythicItems.Templates.MYTHRIL_DRILL_SMITHING_TEMPLATE),
                Ingredient.of(MythicTools.MYTHRIL.getPickaxe()),
                Ingredient.of(MythicItems.Mats.DURASTEEL_ENGINE),
                RecipeCategory.TOOLS,
                MythicTools.MYTHRIL_DRILL
            )
            .unlocks("has_material_for_pick", has(MythicItems.MYTHRIL.getIngot()))
            .unlocks("has_engine", has(MythicItems.Mats.DURASTEEL_ENGINE))
            .save(output, recipeKey("tools/mythril_drill"));
        ShapedRecipeBuilder.shaped(itemLookup, RecipeCategory.TOOLS, MythicTools.STAR_PLATINUM_ARROW, 2)
            .define('#', MythicItems.STAR_PLATINUM.getNugget())
            .define('S', Items.STICK)
            .define('F', Items.FEATHER)
            .pattern("  #")
            .pattern(" S ")
            .pattern("F  ")
            .unlockedBy("has_material", has(MythicItems.STAR_PLATINUM.getIngot()))
            .save(nuggetExporter, recipeKey("weapons/star_platinum_arrow"));
        ShapedRecipeBuilder.shaped(itemLookup, RecipeCategory.TOOLS, MythicTools.RUNITE_ARROW, 4)
            .define('#', MythicItems.RUNITE.getNugget())
            .define('S', Items.STICK)
            .define('F', Items.FEATHER)
            .pattern("  #")
            .pattern(" S ")
            .pattern("F  ")
            .unlockedBy("has_material", has(MythicItems.RUNITE.getIngot()))
            .save(nuggetExporter, recipeKey("weapons/runite_arrow"));
        ShapedRecipeBuilder.shaped(itemLookup, RecipeCategory.TOOLS, MythicTools.STORMYX_SHIELD)
            .define('#', MythicItems.STORMYX.getIngot())
            .define('S', MythicItems.Mats.STORMYX_SHELL)
            .pattern("#S#")
            .pattern("###")
            .pattern(" # ")
            .unlockedBy("has_shell", has(MythicItems.Mats.STORMYX_SHELL))
            .save(output, recipeKey("tools/stormyx_shield"));
        ShapedRecipeBuilder.shaped(itemLookup, RecipeCategory.TOOLS, MythicTools.PLATINUM_WATCH)
            .define('#', MythicItems.PLATINUM.getIngot())
            .define('R', Items.REDSTONE)
            .pattern(" # ")
            .pattern("#R#")
            .pattern(" # ")
            .unlockedBy("has_material", has(MythicItems.PLATINUM.getIngot()))
            .save(output, recipeKey("tools/platinum_watch"));
        SmithingTransformRecipeBuilder.smithing(
                Ingredient.of(MythicItems.Templates.AEGIS_SMITHING_TEMPLATE),
                Ingredient.of(MythicTools.ADAMANTITE.getSword()),
                Ingredient.of(MythicBlocks.PALLADIUM.getStorageBlock()),
                RecipeCategory.COMBAT,
                MythicTools.RED_AEGIS_SWORD
            )
            .unlocks("has_template", has(MythicItems.Templates.AEGIS_SMITHING_TEMPLATE))
            .save(output, recipeKey("weapons/red_aegis_sword"));
        SmithingTransformRecipeBuilder.smithing(
                Ingredient.of(MythicItems.Templates.AEGIS_SMITHING_TEMPLATE),
                Ingredient.of(MythicTools.HALLOWED.getSword()),
                Ingredient.of(MythicBlocks.HALLOWED.getStorageBlock()),
                RecipeCategory.COMBAT,
                MythicTools.WHITE_AEGIS_SWORD
            )
            .unlocks("has_template", has(MythicItems.Templates.AEGIS_SMITHING_TEMPLATE))
            .save(output, recipeKey("weapons/white_aegis_sword"));
    }

    public void createSmithingTemplateRecipes() {
        // Crafted Smithing Templates
        ShapedRecipeBuilder.shaped(itemLookup, RecipeCategory.TOOLS, MythicItems.Templates.CARMOT_SMITHING_TEMPLATE)
            .define('M', Items.SMOOTH_BASALT)
            .define('C', MythicItems.CARMOT.getIngot())
            .define('D', Items.DIAMOND)
            .pattern("DCD")
            .pattern("CMC")
            .pattern("DCD")
            .unlockedBy("has_material", has(MythicItems.CARMOT.getIngot()))
            .group("mm_carmot_template")
            .save(output, recipeKey("smithing_templates/carmot"));

        ShapedRecipeBuilder.shaped(itemLookup, RecipeCategory.TOOLS, MythicItems.Templates.LEGENDARY_BANGLUM_SMITHING_TEMPLATE)
            .define('M', Items.TUFF)
            .define('C', MythicItems.BANGLUM.getIngot())
            .pattern("MMM")
            .pattern("MCM")
            .pattern("MMM")
            .unlockedBy("has_material", has(MythicItems.BANGLUM.getIngot()))
            .group("mm_banglum_template")
            .save(output, recipeKey("smithing_templates/legendary_banglum"));

        ShapedRecipeBuilder.shaped(itemLookup, RecipeCategory.TOOLS, MythicItems.Templates.ROYAL_MIDAS_SMITHING_TEMPLATE)
            .define('M', Items.NETHERRACK)
            .define('C', MythicBlocks.MIDAS_GOLD.getStorageBlock())
            .define('T', MythicBlocks.ENCHANTED_MIDAS_GOLD_BLOCK_ITEM)
            .pattern("CTC")
            .pattern("CMC")
            .pattern("CCC")
            .unlockedBy("has_material", has(MythicBlocks.MIDAS_GOLD.getStorageBlock()))
            .unlockedBy("has_enchanted_material", has(MythicBlocks.ENCHANTED_MIDAS_GOLD_BLOCK_ITEM))
            .group("mm_royal_midas_template")
            .save(output, recipeKey("smithing_templates/royal_midas"));

        ShapedRecipeBuilder.shaped(itemLookup, RecipeCategory.TOOLS, MythicItems.Templates.OSMIUM_CHAINMAIL_SMITHING_TEMPLATE)
            .define('M', Items.ANDESITE)
            .define('C', MythicItems.OSMIUM.getNugget())
            .define('T', MythicItems.OSMIUM.getIngot())
            .pattern("CTC")
            .pattern("CMC")
            .pattern("CTC")
            .unlockedBy("has_material", has(MythicItems.OSMIUM.getIngot()))
            .group("mm_osmium_template")
            .save(output, recipeKey("smithing_templates/osmium_chainmail"));

        ShapedRecipeBuilder.shaped(itemLookup, RecipeCategory.TOOLS, MythicItems.Templates.MIDAS_FOLDING_TEMPLATE)
            .define('M', Items.NETHERRACK)
            .define('C', MythicItems.MIDAS_GOLD.getIngot())
            .pattern("MMM")
            .pattern("MCM")
            .pattern("MMM")
            .unlockedBy("has_material", has(MythicItems.MIDAS_GOLD.getIngot()))
            .group("mm_midas_folding_template")
            .save(output, recipeKey("smithing_templates/midas_folding"));

        ShapedRecipeBuilder.shaped(itemLookup, RecipeCategory.TOOLS, MythicItems.Templates.TIDESINGER_SMITHING_TEMPLATE)
            .define('O', Items.PRISMARINE)
            .define('L', MythicItems.Mats.AQUARIUM_PEARL)
            .define('R', Items.BRAIN_CORAL)
            .define('G', MythicItems.AQUARIUM.getIngot())
            .define('B', Items.FIRE_CORAL)
            .define('M', Items.BUBBLE_CORAL)
            .define('E', Items.TUBE_CORAL)
            .define('N', Items.HORN_CORAL)
            .pattern("RGB")
            .pattern("LOL")
            .pattern("MEN")
            .unlockedBy("has_material", has(MythicItems.Mats.AQUARIUM_PEARL))
            .unlockedBy("has_coral", has(MythicTags.TIDESINGER_CORAL))
            .group("mm_tidesinger_template")
            .save(output, recipeKey("smithing_templates/tidesinger"));

        // Smithing Template Duplication recipes
        ShapedRecipeBuilder.shaped(itemLookup, RecipeCategory.TOOLS, MythicItems.Templates.AEGIS_SMITHING_TEMPLATE, 2)
            .define('C', Items.EMERALD)
            .define('T', MythicItems.Templates.AEGIS_SMITHING_TEMPLATE)
            .define('M', Items.DEEPSLATE)
            .pattern("CTC")
            .pattern("CMC")
            .pattern("CCC")
            .unlockedBy("has_material", has(MythicItems.Templates.AEGIS_SMITHING_TEMPLATE))
            .group("mm_aegis_template")
            .save(output, recipeKey("smithing_templates/aegis_duplicate"));
        ShapedRecipeBuilder.shaped(itemLookup, RecipeCategory.TOOLS, MythicItems.Templates.CARMOT_SMITHING_TEMPLATE, 2)
            .define('C', MythicItems.CARMOT.getNugget())
            .define('P', Items.DIAMOND)
            .define('T', MythicItems.Templates.CARMOT_SMITHING_TEMPLATE)
            .define('M', Items.SMOOTH_BASALT)
            .pattern("CTC")
            .pattern("CMC")
            .pattern("CPC")
            .unlockedBy("has_material", has(MythicItems.Templates.CARMOT_SMITHING_TEMPLATE))
            .group("mm_carmot_template")
            .save(nuggetExporter, recipeKey("smithing_templates/carmot_duplicate"));
        ShapedRecipeBuilder.shaped(itemLookup, RecipeCategory.TOOLS, MythicItems.Templates.MYTHRIL_DRILL_SMITHING_TEMPLATE, 2)
            .define('C', Items.DIAMOND)
            .define('T', MythicItems.Templates.MYTHRIL_DRILL_SMITHING_TEMPLATE)
            .define('M', Items.DEEPSLATE)
            .pattern("CTC")
            .pattern("CMC")
            .pattern("CCC")
            .unlockedBy("has_material", has(MythicItems.Templates.MYTHRIL_DRILL_SMITHING_TEMPLATE))
            .group("mm_mythril_drill_template")
            .save(output, recipeKey("smithing_templates/mythril_drill_duplicate"));
        ShapedRecipeBuilder.shaped(itemLookup, RecipeCategory.TOOLS, MythicItems.Templates.MIDAS_FOLDING_TEMPLATE, 2)
            .define('M', MythicItems.MIDAS_GOLD.getNugget())
            .define('C', MythicItems.Templates.MIDAS_FOLDING_TEMPLATE)
            .pattern("MMM")
            .pattern("MCM")
            .pattern("MMM")
            .unlockedBy("has_material", has(MythicItems.Templates.MIDAS_FOLDING_TEMPLATE))
            .group("mm_midas_folding_template")
            .save(nuggetExporter, recipeKey("smithing_templates/midas_folding_duplicate"));
        ShapedRecipeBuilder.shaped(itemLookup, RecipeCategory.TOOLS, MythicItems.Templates.TIDESINGER_SMITHING_TEMPLATE, 2)
            .define('C', MythicItems.AQUARIUM.getIngot())
            .define('P', MythicItems.Mats.AQUARIUM_PEARL)
            .define('T', MythicItems.Templates.TIDESINGER_SMITHING_TEMPLATE)
            .define('M', Items.PRISMARINE)
            .pattern("CTC")
            .pattern("CMC")
            .pattern("CPC")
            .unlockedBy("has_material", has(MythicItems.Templates.TIDESINGER_SMITHING_TEMPLATE))
            .group("mm_tidesinger_template")
            .save(output, recipeKey("smithing_templates/tidesinger_duplicate"));
        ShapedRecipeBuilder.shaped(itemLookup, RecipeCategory.TOOLS, MythicItems.Templates.ROYAL_MIDAS_SMITHING_TEMPLATE, 2)
            .define('C', MythicBlocks.MIDAS_GOLD.getStorageBlock())
            .define('T', MythicItems.Templates.ROYAL_MIDAS_SMITHING_TEMPLATE)
            .define('M', Items.NETHERRACK)
            .pattern("CTC")
            .pattern("CMC")
            .pattern("CCC")
            .unlockedBy("has_material", has(MythicItems.Templates.ROYAL_MIDAS_SMITHING_TEMPLATE))
            .group("mm_royal_midas_template")
            .save(output, recipeKey("smithing_templates/royal_midas_duplicate"));
        ShapedRecipeBuilder.shaped(itemLookup, RecipeCategory.TOOLS, MythicItems.Templates.UNOBTAINIUM_SMITHING_TEMPLATE, 2)
            .define('C', Items.DIAMOND)
            .define('T', MythicItems.Templates.UNOBTAINIUM_SMITHING_TEMPLATE)
            .define('M', Items.DEEPSLATE)
            .pattern("CTC")
            .pattern("CMC")
            .pattern("CCC")
            .unlockedBy("has_material", has(MythicItems.Templates.UNOBTAINIUM_SMITHING_TEMPLATE))
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
        createArmorCraftingRecipes(MythicArmor.ADAMANTITE, MythicItems.ADAMANTITE.getIngot());
        createArmorCraftingRecipes(MythicArmor.AQUARIUM, MythicItems.AQUARIUM.getIngot());
        createArmorCraftingRecipes(MythicArmor.BANGLUM, MythicItems.BANGLUM.getIngot());
        createArmorCraftingRecipes(MythicArmor.BRONZE, MythicItems.BRONZE.getIngot());
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
            Ingredient.of(MythicItems.CARMOT.getIngot()),
            MythicArmor.CARMOT
        );
        createArmorSmithingRecipes(
            MythicItems.Templates.UNOBTAINIUM_SMITHING_TEMPLATE,
            Items.NETHERITE_HELMET,
            Items.NETHERITE_CHESTPLATE,
            Items.NETHERITE_LEGGINGS,
            Items.NETHERITE_BOOTS,
            Ingredient.of(MythicItems.METALLURGIUM.getIngot()),
            MythicArmor.METALLURGIUM
        );
        createArmorSmithingRecipes(
            MythicItems.Templates.UNOBTAINIUM_SMITHING_TEMPLATE,
            Items.DIAMOND_HELMET,
            Items.DIAMOND_CHESTPLATE,
            Items.DIAMOND_LEGGINGS,
            Items.DIAMOND_BOOTS,
            Ingredient.of(MythicItems.CELESTIUM.getIngot()),
            MythicArmor.CELESTIUM
        );
        createArmorSmithingRecipes(
            MythicItems.Templates.LEGENDARY_BANGLUM_SMITHING_TEMPLATE,
            MythicArmor.BANGLUM,
            Ingredient.of(MythicItems.Mats.BANGLUM_CHUNK),
            MythicArmor.LEGENDARY_BANGLUM
        );
        createArmorSmithingRecipes(
            MythicItems.Templates.OSMIUM_CHAINMAIL_SMITHING_TEMPLATE,
            Items.DIAMOND_HELMET,
            Items.DIAMOND_CHESTPLATE,
            Items.DIAMOND_LEGGINGS,
            Items.DIAMOND_BOOTS,
            Ingredient.of(MythicItems.OSMIUM.getIngot()),
            MythicArmor.OSMIUM_CHAINMAIL
        );
        createTidesingerArmorRecipes();
    }

    private void createTidesingerArmorRecipes() {
        // FIXME - Might be time to consider "configurable" attributes via recipes, if data generated
        //  is going to include these anyways
//        var template = MythicItems.Templates.TIDESINGER_SMITHING_TEMPLATE;
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

    public void createArmorCraftingRecipes(ArmorSet output, Item material) {
        createArmorCraftingRecipes(output, Ingredient.of(material));
    }

    public void createArmorCraftingRecipes(ArmorSet output, Ingredient material) {
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
