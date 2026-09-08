package com.mythicmetals.data;

import com.mythicmetals.api.v2.*;
import com.mythicmetals.item.MythicMaterials;
import com.mythicmetals.item.MythicResourceKeys;
import com.mythicmetals.item.armor.MythicArmorSets;
import com.mythicmetals.item.component.MythicDataComponents;
import com.mythicmetals.item.component.TidesingerPatternComponent;
import com.mythicmetals.item.tools.MythicTools;
import com.mythicmetals.misc.RegistryHelper;
import io.wispforest.owo.util.ReflectionUtils;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.component.DataComponentPatch;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.recipes.*;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.CookingBookCategory;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.SmithingTransformRecipe;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import java.util.*;

import static com.mythicmetals.misc.RegistryHelper.recipeKey;
import static net.fabricmc.fabric.api.tag.convention.v2.ConventionalItemTags.WOODEN_RODS;

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

        var materials = new HashSet<Material>();

        ReflectionUtils.iterateAccessibleStaticFields(MythicMaterials.class, Material.class, (value, name, field) -> {
            materials.add(value);
        });

        materials.forEach(this::createRecipesFromMaterial);
        createBlockRecipes();
        createItemRecipes();
        createArmorRecipes();
        createToolRecipes();
        createSmithingTemplateRecipes();
    }

    private void createRecipesFromMaterial(Material material) {
        if (material.toolSet() != null && !material.requiresSmithing()) {
            createToolCraftingRecipes(material.toolSet(), material.baseMaterial());
        }
        if (material.armorSet() != null && !material.requiresSmithing()) {
            createArmorCraftingRecipes(material.armorSet(), material.baseMaterial());
        }
        if (material.blockSet() != null) {

        }
        createSmeltingRecipes(material);
        if (material.nugget() != null) {
            createNuggetRecipes(material);
        }
    }

    private void createSmeltingRecipes(Material material) {
        boolean requiresBlasting = switch (material.materialType()) {
            case RARE_ALLOY, INGOT_BLASTING, ALLOY -> true;
            default -> false;
        };
        // TODO - Better unlock criteria
        // TODO - XP
        if (material.blockSet() != null) {
            var blockSet = material.blockSet();
            var outputItem = material.baseMaterial();
            var smeltables = new ArrayList<ItemLike>();
            if (blockSet.rawStorage() != null) {
                // TODO - alloy forgery smeltin
            }
            if (blockSet.ore() != null) {
                smeltables.add(blockSet.ore().block().asItem());
            }
            if (!blockSet.oreVariants().isEmpty()) {
                blockSet.oreVariants().values().forEach(blockRecord -> smeltables.add(blockRecord.block().asItem()));
            }
            if (material.rawOre() != null) {
                smeltables.add(material.rawOre());
                if (blockSet.rawStorage() != null) {
                    createRawToStorageAndBackRecipes(material.name(), blockSet.rawStorage().block(), material.rawOre());
                }
            }
            if (!smeltables.isEmpty()) {
                // smeltables into ingots
                if (!requiresBlasting) {
                    SimpleCookingRecipeBuilder.smelting(Ingredient.of(smeltables.stream()), RecipeCategory.MISC, CookingBookCategory.MISC, outputItem, 0.1f, 200)
                        .unlockedBy("has_material", has(outputItem))
                        .save(output, recipeKey("smelting/" + material.name()));
                }
                // smeltables into ingots
                SimpleCookingRecipeBuilder.blasting(Ingredient.of(smeltables.stream()), RecipeCategory.MISC, CookingBookCategory.MISC, outputItem, 0.1f, 100)
                    .unlockedBy("has_material", has(outputItem))
                    .save(output, recipeKey("blasting/" + material.name()));
            }

        }
    }

    private void createRawToStorageAndBackRecipes(String name, Block rawStorageBlock, Item rawItem) {
        // Raw Ores to Raw Ore Block
        ShapelessRecipeBuilder.shapeless(itemLookup, RecipeCategory.BUILDING_BLOCKS, rawStorageBlock.asItem())
            .unlockedBy("has_material", has(rawStorageBlock.asItem()))
            .requires(rawItem, 9)
            .save(output, recipeKey("blocks/raw_" + name));
        // Raw Ores from Raw Ore Block
        ShapelessRecipeBuilder.shapeless(itemLookup, RecipeCategory.MISC, rawItem, 9)
            .unlockedBy("has_material", has(rawItem))
            .requires(rawStorageBlock.asItem())
            .save(output, recipeKey("crafting/raw_" + name + "_from_block"));
    }

    private void createBlockRecipes() {
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
//            .criterion("has_pearl", conditionsFromItem(MythicMaterials.TIDESINGER.baseMaterial()))
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
            .define('#', MythicMaterials.BANGLUM.blockSet().rawStorage().block())
            .define('S', MythicMaterials.MORKITE.blockSet().storage().block())
            .define('C', MythicMaterials.LEGENDARY_BANGLUM.baseMaterial())
            .pattern("#S#")
            .pattern("SCS")
            .pattern("#S#")
            .unlockedBy("has_big_material", has(MythicMaterials.BANGLUM.blockSet().rawStorage().block()))
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

    private void createItemRecipes() {
        ShapedRecipeBuilder.shaped(itemLookup, RecipeCategory.MISC, MythicMaterials.DURASTEEL.extraItems().get(MythicResourceKeys.DURASTEEL_ENGINE))
            .define('#', MythicMaterials.DURASTEEL.baseMaterial())
            .define('B', MythicMaterials.DURASTEEL.blockSet().storage().block())
            .define('M', MythicMaterials.MORKITE.baseMaterial())
            .define('H', Items.HOPPER)
            .pattern("#H#")
            .pattern("MBM")
            .pattern("###")
            .unlockedBy("has_material", has(MythicMaterials.DURASTEEL.baseMaterial()))
            .unlockedBy("has_fuel", has(MythicMaterials.MORKITE.baseMaterial()))
            .save(output, recipeKey("crafting/durasteel_engine"));
        ShapedRecipeBuilder.shaped(itemLookup, RecipeCategory.MISC, MythicMaterials.PROMETHEUM.extraItems().get(MythicResourceKeys.PROMETHEUM_ROSE))
            .define('G', MythicMaterials.PROMETHEUM.nugget())
            .define('R', Items.ROSE_BUSH)
            .pattern("GGG")
            .pattern("GRG")
            .pattern("GGG")
            .unlockedBy("has_material", has(MythicMaterials.PROMETHEUM.rawOre()))
            .save(output, recipeKey("crafting/prometheum_rose"));
        ShapedRecipeBuilder.shaped(itemLookup, RecipeCategory.MISC, MythicMaterials.PROMETHEUM.extraItems().get(MythicResourceKeys.PROMETHEUM_ROSE))
            .define('G', MythicMaterials.PROMETHEUM.nugget())
            .define('R', Items.WITHER_ROSE)
            .pattern("GGG")
            .pattern("GRG")
            .pattern("GGG")
            .unlockedBy("has_material", has(MythicMaterials.PROMETHEUM.rawOre()))
            .save(output, recipeKey("crafting/prometheum_rose_alt"));
    }

    private void createNuggetRecipes(Material material) {
        boolean requiresBlasting = material.materialType().equals(MaterialType.INGOT_BLASTING);
        var name = material.name();
        var nugget = material.nugget();
        assert nugget != null;

        if (material.armorSet() != null || material.toolSet() != null) {
            // smelt equipment into nuggets
            if (!requiresBlasting) {
                SimpleCookingRecipeBuilder.smelting(Ingredient.of(itemLookup.getOrThrow(RegistryHelper.itemTag("equipment/" + name))), RecipeCategory.MISC, CookingBookCategory.MISC, nugget, 0.1f, 200)
                    .unlockedBy("has_material", has(TagKey.create(Registries.ITEM, RegistryHelper.id("nuggets/" + name))))
                    .save(nuggetExporter, recipeKey("smelting/" + name.toLowerCase(Locale.ROOT) + "_nugget_from_equipment"));
            }
            // blast equipment into nuggets
            SimpleCookingRecipeBuilder.blasting(Ingredient.of(itemLookup.getOrThrow(RegistryHelper.itemTag("equipment/" + name))), RecipeCategory.MISC, CookingBookCategory.MISC, nugget, 0.1f, 100)
                .unlockedBy("has_material", has(TagKey.create(Registries.ITEM, RegistryHelper.id("nuggets/" + name))))
                .save(nuggetExporter, recipeKey("blasting/" + name.toLowerCase(Locale.ROOT) + "_nugget_from_equipment"));
        }

        // crafting ingots from nuggets
        ShapelessRecipeBuilder.shapeless(itemLookup, RecipeCategory.MISC, material.baseMaterial())
            .unlockedBy("has_material", has(TagKey.create(Registries.ITEM, RegistryHelper.id("nuggets/" + name))))
            .requires(nugget, 9)
            .group("mm_" + name)
            .save(nuggetExporter, recipeKey("ingots/" + name + "_from_nuggets"));

        // craft ingots into nuggets
        ShapelessRecipeBuilder.shapeless(itemLookup, RecipeCategory.MISC, nugget, 9)
            .unlockedBy("has_material", has(TagKey.create(Registries.ITEM, RegistryHelper.id(name + "_ingot"))))
            .requires(material.baseMaterial())
            .save(nuggetExporter, recipeKey("crafting/" + name + "_nuggets"));
    }

    private void createToolRecipes() {
        // Tool recipes
        createToolSmithingRecipes(
            MythicMaterials.CARMOT.extraItems().get(MythicResourceKeys.CARMOT_SMITHING_TEMPLATE),
            MythicMaterials.KYBER.toolSet(),
            Ingredient.of(MythicMaterials.CARMOT.baseMaterial()),
            MythicMaterials.CARMOT.toolSet()
        );
        createToolSmithingRecipes(
            MythicMaterials.LEGENDARY_BANGLUM.extraItems().get(MythicResourceKeys.LEGENDARY_BANGLUM_SMITHING_TEMPLATE),
            MythicMaterials.BANGLUM.toolSet(),
            Ingredient.of(MythicMaterials.LEGENDARY_BANGLUM.baseMaterial()),
            MythicMaterials.LEGENDARY_BANGLUM.toolSet()
        );
        createToolSmithingRecipes(
            MythicMaterials.TIDESINGER.extraItems().get(MythicResourceKeys.TIDESINGER_SMITHING_TEMPLATE),
            MythicMaterials.AQUARIUM.toolSet(),
            Ingredient.of(itemLookup.getOrThrow(MythicTags.TIDESINGER_CORAL)),
            MythicMaterials.TIDESINGER.toolSet()
        );
        createToolSmithingRecipes(
            MythicMaterials.UNOBTAINIUM.extraItems().get(MythicResourceKeys.UNOBTAINIUM_SMITHING_TEMPLATE),
            Items.DIAMOND_SWORD,
            Items.DIAMOND_AXE,
            Items.DIAMOND_PICKAXE,
            Items.DIAMOND_SHOVEL,
            Items.DIAMOND_HOE,
            Ingredient.of(MythicMaterials.CELESTIUM.baseMaterial()),
            MythicMaterials.CELESTIUM.toolSet()
        );
        createToolSmithingRecipes(
            MythicMaterials.UNOBTAINIUM.extraItems().get(MythicResourceKeys.UNOBTAINIUM_SMITHING_TEMPLATE),
            Items.NETHERITE_SWORD,
            Items.NETHERITE_AXE,
            Items.NETHERITE_PICKAXE,
            Items.NETHERITE_SHOVEL,
            Items.NETHERITE_HOE,
            Ingredient.of(MythicMaterials.METALLURGIUM.baseMaterial()),
            MythicMaterials.METALLURGIUM.toolSet()
        );

        ShapedRecipeBuilder.shaped(itemLookup, RecipeCategory.TOOLS, MythicMaterials.BANGLUM.extraItems().get(MythicResourceKeys.BANGLUM_TNT_MINECART))
            .define('#', Items.MINECART)
            .define('S', MythicMaterials.BANGLUM.extraBlocks().get(MythicResourceKeys.BANGLUM_TNT).asItem())
            .pattern("S")
            .pattern("#")
            .unlockedBy("has_material", has(MythicMaterials.BANGLUM.extraBlocks().get(MythicResourceKeys.BANGLUM_TNT)))
            .save(output, recipeKey("tools/banglum_tnt_minecart"));
        ShapedRecipeBuilder.shaped(itemLookup, RecipeCategory.TOOLS, MythicMaterials.CARMOT.extraBlocks().get(MythicResourceKeys.CARMOT_BELL))
            .define('#', MythicMaterials.CARMOT.baseMaterial())
            .define('S', MythicMaterials.CARMOT.extraItems().get(MythicResourceKeys.CARMOT_STONE))
            .pattern(" # ")
            .pattern("#S#")
            .pattern("# #")
            .unlockedBy("has_material", has(MythicMaterials.CARMOT.baseMaterial()))
            .unlockedBy("has_secret_stone", has(MythicMaterials.CARMOT.extraItems().get(MythicResourceKeys.CARMOT_STONE)))
            .save(output, recipeKey("tools/carmot_bell"));
        ShapedRecipeBuilder.shaped(itemLookup, RecipeCategory.TOOLS, MythicMaterials.PALLADIUM.extraItems().get(MythicResourceKeys.PALLADIUM_MINECART))
            .define('#', MythicMaterials.PALLADIUM.baseMaterial())
            .pattern("# #")
            .pattern("###")
            .unlockedBy("has_material", has(MythicMaterials.PALLADIUM.baseMaterial()))
            .save(output, recipeKey("tools/palladium_minecart"));
        ShapedRecipeBuilder.shaped(itemLookup, RecipeCategory.TOOLS, MythicTools.ORICHALCUM_HAMMER)
            .define('#', MythicMaterials.ORICHALCUM.blockSet().storage().block())
            .define('S', Items.STICK)
            .pattern(" # ")
            .pattern(" S#")
            .pattern("S  ")
            .unlockedBy("has_material", has(MythicMaterials.ORICHALCUM.baseMaterial()))
            .save(output, recipeKey("tools/orichalcum_hammer"));
        SmithingTransformRecipeBuilder.smithing(
                Ingredient.of(MythicMaterials.MYTHRIL.extraItems().get(MythicResourceKeys.MYTHRIL_DRILL_SMITHING_TEMPLATE)),
                Ingredient.of(MythicMaterials.MYTHRIL.toolSet().getPickaxe()),
                Ingredient.of(MythicMaterials.DURASTEEL.extraItems().get(MythicResourceKeys.DURASTEEL_ENGINE)),
                RecipeCategory.TOOLS,
                MythicTools.MYTHRIL_DRILL
            )
            .unlocks("has_material_for_pick", has(MythicMaterials.MYTHRIL.baseMaterial()))
            .unlocks("has_engine", has(MythicMaterials.DURASTEEL.extraItems().get(MythicResourceKeys.DURASTEEL_ENGINE)))
            .save(output, recipeKey("tools/mythril_drill"));
        ShapedRecipeBuilder.shaped(itemLookup, RecipeCategory.TOOLS, MythicTools.STAR_PLATINUM_ARROW, 2)
            .define('#', MythicMaterials.STAR_PLATINUM.nugget())
            .define('S', Items.STICK)
            .define('F', Items.FEATHER)
            .pattern("  #")
            .pattern(" S ")
            .pattern("F  ")
            .unlockedBy("has_material", has(MythicMaterials.STAR_PLATINUM.baseMaterial()))
            .save(nuggetExporter, recipeKey("weapons/star_platinum_arrow"));
        ShapedRecipeBuilder.shaped(itemLookup, RecipeCategory.TOOLS, MythicTools.RUNITE_ARROW, 4)
            .define('#', MythicMaterials.RUNITE.nugget())
            .define('S', Items.STICK)
            .define('F', Items.FEATHER)
            .pattern("  #")
            .pattern(" S ")
            .pattern("F  ")
            .unlockedBy("has_material", has(MythicMaterials.RUNITE.baseMaterial()))
            .save(nuggetExporter, recipeKey("weapons/runite_arrow"));
        ShapedRecipeBuilder.shaped(itemLookup, RecipeCategory.TOOLS, MythicTools.STORMYX_SHIELD)
            .define('#', MythicMaterials.STORMYX.baseMaterial())
            .define('S', MythicMaterials.STORMYX.extraItems().get(MythicResourceKeys.STORMYX_SHELL))
            .pattern("#S#")
            .pattern("###")
            .pattern(" # ")
            .unlockedBy("has_shell", has(MythicMaterials.STORMYX.extraItems().get(MythicResourceKeys.STORMYX_SHELL)))
            .save(output, recipeKey("tools/stormyx_shield"));
        ShapedRecipeBuilder.shaped(itemLookup, RecipeCategory.TOOLS, MythicTools.PLATINUM_WATCH)
            .define('#', MythicMaterials.PLATINUM.baseMaterial())
            .define('R', Items.REDSTONE)
            .pattern(" # ")
            .pattern("#R#")
            .pattern(" # ")
            .unlockedBy("has_material", has(MythicMaterials.PLATINUM.baseMaterial()))
            .save(output, recipeKey("tools/platinum_watch"));
        SmithingTransformRecipeBuilder.smithing(
                Ingredient.of(MythicMaterials.AEGIS.extraItems().get(MythicResourceKeys.AEGIS_SMITHING_TEMPLATE)),
                Ingredient.of(MythicMaterials.ADAMANTITE.toolSet().getSword()),
                Ingredient.of(MythicMaterials.PALLADIUM.blockSet().storage().block()),
                RecipeCategory.COMBAT,
                MythicTools.RED_AEGIS_SWORD
            )
            .unlocks("has_template", has(MythicMaterials.AEGIS.extraItems().get(MythicResourceKeys.AEGIS_SMITHING_TEMPLATE)))
            .save(output, recipeKey("weapons/red_aegis_sword"));
        SmithingTransformRecipeBuilder.smithing(
                Ingredient.of(MythicMaterials.AEGIS.extraItems().get(MythicResourceKeys.AEGIS_SMITHING_TEMPLATE)),
                Ingredient.of(MythicMaterials.HALLOWED.toolSet().getSword()),
                Ingredient.of(MythicMaterials.HALLOWED.blockSet().storage().block()),
                RecipeCategory.COMBAT,
                MythicTools.WHITE_AEGIS_SWORD
            )
            .unlocks("has_template", has(MythicMaterials.AEGIS.extraItems().get(MythicResourceKeys.AEGIS_SMITHING_TEMPLATE)))
            .save(output, recipeKey("weapons/white_aegis_sword"));
    }

    public void createSmithingTemplateRecipes() {
        // Crafted Smithing Templates
        ShapedRecipeBuilder.shaped(itemLookup, RecipeCategory.MISC, MythicMaterials.CARMOT.extraItems().get(MythicResourceKeys.CARMOT_SMITHING_TEMPLATE))
            .define('M', Items.SMOOTH_BASALT)
            .define('C', MythicMaterials.CARMOT.baseMaterial())
            .define('D', Items.DIAMOND)
            .pattern("DCD")
            .pattern("CMC")
            .pattern("DCD")
            .unlockedBy("has_material", has(MythicMaterials.CARMOT.baseMaterial()))
            .group("mm_carmot_template")
            .save(output, recipeKey("smithing_templates/carmot"));

        ShapedRecipeBuilder.shaped(itemLookup, RecipeCategory.MISC, MythicMaterials.LEGENDARY_BANGLUM.extraItems().get(MythicResourceKeys.LEGENDARY_BANGLUM_SMITHING_TEMPLATE))
            .define('M', Items.TUFF)
            .define('C', MythicMaterials.BANGLUM.baseMaterial())
            .pattern("MMM")
            .pattern("MCM")
            .pattern("MMM")
            .unlockedBy("has_material", has(MythicMaterials.BANGLUM.baseMaterial()))
            .group("mm_banglum_template")
            .save(output, recipeKey("smithing_templates/legendary_banglum"));

        var enchantedMidas = MythicMaterials.MIDAS_GOLD.extraBlocks().get(MythicResourceKeys.ENCHANTED_MIDAS_GOLD_BLOCK);
        ShapedRecipeBuilder.shaped(itemLookup, RecipeCategory.MISC, MythicMaterials.MIDAS_GOLD.extraItems().get(MythicResourceKeys.ROYAL_MIDAS_SMITHING_TEMPLATE))
            .define('M', Items.NETHERRACK)
            .define('C', MythicMaterials.MIDAS_GOLD.blockSet().storage().block())
            .define('T', enchantedMidas)
            .pattern("CTC")
            .pattern("CMC")
            .pattern("CCC")
            .unlockedBy("has_material", has(MythicMaterials.MIDAS_GOLD.blockSet().storage().block()))
            .unlockedBy("has_enchanted_material", has(enchantedMidas))
            .group("mm_royal_midas_template")
            .save(output, recipeKey("smithing_templates/royal_midas"));

        ShapedRecipeBuilder.shaped(itemLookup, RecipeCategory.MISC, MythicMaterials.OSMIUM.extraItems().get(MythicResourceKeys.OSMIUM_CHAINMAIL_SMITHING_TEMPLATE))
            .define('M', Items.ANDESITE)
            .define('C', MythicMaterials.OSMIUM.nugget())
            .define('T', MythicMaterials.OSMIUM.baseMaterial())
            .pattern("CTC")
            .pattern("CMC")
            .pattern("CTC")
            .unlockedBy("has_material", has(MythicMaterials.OSMIUM.baseMaterial()))
            .group("mm_osmium_template")
            .save(output, recipeKey("smithing_templates/osmium_chainmail"));

        ShapedRecipeBuilder.shaped(itemLookup, RecipeCategory.MISC, MythicMaterials.MIDAS_GOLD.extraItems().get(MythicResourceKeys.MIDAS_FOLDING_TEMPLATE))
            .define('M', Items.NETHERRACK)
            .define('C', MythicMaterials.MIDAS_GOLD.baseMaterial())
            .pattern("MMM")
            .pattern("MCM")
            .pattern("MMM")
            .unlockedBy("has_material", has(MythicMaterials.MIDAS_GOLD.baseMaterial()))
            .group("mm_midas_folding_template")
            .save(output, recipeKey("smithing_templates/midas_folding"));

        ShapedRecipeBuilder.shaped(itemLookup, RecipeCategory.MISC, MythicMaterials.TIDESINGER.extraItems().get(MythicResourceKeys.TIDESINGER_SMITHING_TEMPLATE))
            .define('O', Items.PRISMARINE)
            .define('L', MythicMaterials.TIDESINGER.baseMaterial())
            .define('R', Items.BRAIN_CORAL)
            .define('G', MythicMaterials.AQUARIUM.baseMaterial())
            .define('B', Items.FIRE_CORAL)
            .define('M', Items.BUBBLE_CORAL)
            .define('E', Items.TUBE_CORAL)
            .define('N', Items.HORN_CORAL)
            .pattern("RGB")
            .pattern("LOL")
            .pattern("MEN")
            .unlockedBy("has_material", has(MythicMaterials.TIDESINGER.baseMaterial()))
            .unlockedBy("has_coral", has(MythicTags.TIDESINGER_CORAL))
            .group("mm_tidesinger_template")
            .save(output, recipeKey("smithing_templates/tidesinger"));

        // Smithing Template Duplication recipes
        ShapedRecipeBuilder.shaped(itemLookup, RecipeCategory.MISC, MythicMaterials.AEGIS.extraItems().get(MythicResourceKeys.AEGIS_SMITHING_TEMPLATE), 2)
            .define('C', Items.EMERALD)
            .define('T', MythicMaterials.AEGIS.extraItems().get(MythicResourceKeys.AEGIS_SMITHING_TEMPLATE))
            .define('M', Items.DEEPSLATE)
            .pattern("CTC")
            .pattern("CMC")
            .pattern("CCC")
            .unlockedBy("has_material", has(MythicMaterials.AEGIS.extraItems().get(MythicResourceKeys.AEGIS_SMITHING_TEMPLATE)))
            .group("mm_aegis_template")
            .save(output, recipeKey("smithing_templates/aegis_duplicate"));
        ShapedRecipeBuilder.shaped(itemLookup, RecipeCategory.MISC, MythicMaterials.CARMOT.extraItems().get(MythicResourceKeys.CARMOT_SMITHING_TEMPLATE), 2)
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
        ShapedRecipeBuilder.shaped(itemLookup, RecipeCategory.MISC, MythicMaterials.MYTHRIL.extraItems().get(MythicResourceKeys.MYTHRIL_DRILL_SMITHING_TEMPLATE), 2)
            .define('C', Items.DIAMOND)
            .define('T', MythicMaterials.MYTHRIL.extraItems().get(MythicResourceKeys.MYTHRIL_DRILL_SMITHING_TEMPLATE))
            .define('M', Items.DEEPSLATE)
            .pattern("CTC")
            .pattern("CMC")
            .pattern("CCC")
            .unlockedBy("has_material", has(MythicMaterials.MYTHRIL.extraItems().get(MythicResourceKeys.MYTHRIL_DRILL_SMITHING_TEMPLATE)))
            .group("mm_mythril_drill_template")
            .save(output, recipeKey("smithing_templates/mythril_drill_duplicate"));
        ShapedRecipeBuilder.shaped(itemLookup, RecipeCategory.MISC, MythicMaterials.MIDAS_GOLD.extraItems().get(MythicResourceKeys.MIDAS_FOLDING_TEMPLATE), 2)
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
            .define('P', MythicMaterials.TIDESINGER.baseMaterial())
            .define('T', MythicMaterials.TIDESINGER.extraItems().get(MythicResourceKeys.TIDESINGER_SMITHING_TEMPLATE))
            .define('M', Items.PRISMARINE)
            .pattern("CTC")
            .pattern("CMC")
            .pattern("CPC")
            .unlockedBy("has_material", has(MythicMaterials.TIDESINGER.extraItems().get(MythicResourceKeys.TIDESINGER_SMITHING_TEMPLATE)))
            .group("mm_tidesinger_template")
            .save(output, recipeKey("smithing_templates/tidesinger_duplicate"));
        ShapedRecipeBuilder.shaped(itemLookup, RecipeCategory.MISC, MythicMaterials.MIDAS_GOLD.extraItems().get(MythicResourceKeys.ROYAL_MIDAS_SMITHING_TEMPLATE), 2)
            .define('C', MythicMaterials.MIDAS_GOLD.blockSet().storage().block())
            .define('T', MythicMaterials.MIDAS_GOLD.extraItems().get(MythicResourceKeys.ROYAL_MIDAS_SMITHING_TEMPLATE))
            .define('M', Items.NETHERRACK)
            .pattern("CTC")
            .pattern("CMC")
            .pattern("CCC")
            .unlockedBy("has_material", has(MythicMaterials.MIDAS_GOLD.extraItems().get(MythicResourceKeys.ROYAL_MIDAS_SMITHING_TEMPLATE)))
            .group("mm_royal_midas_template")
            .save(output, recipeKey("smithing_templates/royal_midas_duplicate"));
        ShapedRecipeBuilder.shaped(itemLookup, RecipeCategory.MISC, MythicMaterials.UNOBTAINIUM.extraItems().get(MythicResourceKeys.UNOBTAINIUM_SMITHING_TEMPLATE), 2)
            .define('C', Items.DIAMOND)
            .define('T', MythicMaterials.UNOBTAINIUM.extraItems().get(MythicResourceKeys.UNOBTAINIUM_SMITHING_TEMPLATE))
            .define('M', Items.DEEPSLATE)
            .pattern("CTC")
            .pattern("CMC")
            .pattern("CCC")
            .unlockedBy("has_material", has(MythicMaterials.UNOBTAINIUM.baseMaterial()))
            .group("mm_unobtainium_template")
            .save(output, recipeKey("smithing_templates/unobtainium_alloy"));
    }

    public void createToolCraftingRecipes(ToolSet toolSet, Item material) {
        // sword
        ShapedRecipeBuilder.shaped(itemLookup, RecipeCategory.TOOLS, toolSet.getSword())
            .define('#', material)
            .define('S', WOODEN_RODS)
            .pattern("#")
            .pattern("#")
            .pattern("S")
            .unlockedBy("has_material", has(material))
            .save(output, recipeKey("sword/" + toolSet.getName()));
        // axe
        ShapedRecipeBuilder.shaped(itemLookup, RecipeCategory.TOOLS, toolSet.getAxe())
            .define('#', material)
            .define('S', WOODEN_RODS)
            .pattern("## ")
            .pattern("#S ")
            .pattern(" S ")
            .unlockedBy("has_material", has(material))
            .save(output, recipeKey("axe/" + toolSet.getName()));
        // pickaxe
        ShapedRecipeBuilder.shaped(itemLookup, RecipeCategory.TOOLS, toolSet.getPickaxe())
            .define('#', material)
            .define('S', WOODEN_RODS)
            .pattern("###")
            .pattern(" S ")
            .pattern(" S ")
            .unlockedBy("has_material", has(material))
            .save(output, recipeKey("pickaxe/" + toolSet.getName()));
        // shovel
        ShapedRecipeBuilder.shaped(itemLookup, RecipeCategory.TOOLS, toolSet.getShovel())
            .define('#', material)
            .define('S', WOODEN_RODS)
            .pattern("#")
            .pattern("#")
            .pattern("S")
            .unlockedBy("has_material", has(material))
            .save(output, recipeKey("shovel/" + toolSet.getName()));
        // hoe
        ShapedRecipeBuilder.shaped(itemLookup, RecipeCategory.TOOLS, toolSet.getHoe())
            .define('#', material)
            .define('S', WOODEN_RODS)
            .pattern("## ")
            .pattern(" S ")
            .pattern(" S ")
            .unlockedBy("has_material", has(material))
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
            Items.CHAINMAIL_HELMET, Items.CHAINMAIL_CHESTPLATE, Items.CHAINMAIL_LEGGINGS, Items.CHAINMAIL_BOOTS,
            Ingredient.of(MythicMaterials.OSMIUM.baseMaterial()),
            MythicArmorSets.OSMIUM_CHAINMAIL
        );
        createTidesingerArmorRecipes();
    }

    private void createTidesingerArmorRecipes() {
        var template = MythicMaterials.TIDESINGER.extraItems().get(MythicResourceKeys.TIDESINGER_SMITHING_TEMPLATE);
        for (var coral : TidesingerPatternComponent.TIDESINGER_VARIANTS.keySet()) {
            var patch = DataComponentPatch.builder().set(MythicDataComponents.TIDESINGER, TidesingerPatternComponent.fromItem(coral)).build();
            var addition = Ingredient.of(coral);
            var name = TidesingerPatternComponent.TIDESINGER_VARIANTS.get(coral);
            var aquariumArmor = MythicMaterials.AQUARIUM.armorSet();
            var tidesingerArmor = MythicMaterials.TIDESINGER.armorSet();
            // helmet
            var helmetTemplate = new ItemStackTemplate(tidesingerArmor.getHelmet(), patch);
            var helmetRecipe = new SmithingTransformRecipe(
                new Recipe.CommonInfo(true),
                Optional.of(Ingredient.of(template)),
                Ingredient.of(aquariumArmor.getHelmet()),
                Optional.of(addition),
                helmetTemplate
            );
            // chestplate
            var chestplateTemplate = new ItemStackTemplate(tidesingerArmor.getChestplate(), patch);
            var chestplateRecipe = new SmithingTransformRecipe(
                new Recipe.CommonInfo(true),
                Optional.of(Ingredient.of(template)),
                Ingredient.of(aquariumArmor.getChestplate()),
                Optional.of(addition),
                chestplateTemplate
            );
            // leggings
            var leggingsTemplate = new ItemStackTemplate(tidesingerArmor.getLeggings(), patch);
            var leggingsRecipe = new SmithingTransformRecipe(
                new Recipe.CommonInfo(true),
                Optional.of(Ingredient.of(template)),
                Ingredient.of(aquariumArmor.getLeggings()),
                Optional.of(addition),
                leggingsTemplate
            );
            // boots
            var bootsTemplate = new ItemStackTemplate(tidesingerArmor.getBoots(), patch);
            var bootsRecipe = new SmithingTransformRecipe(
                new Recipe.CommonInfo(true),
                Optional.of(Ingredient.of(template)),
                Ingredient.of(aquariumArmor.getBoots()),
                Optional.of(addition),
                bootsTemplate
            );
            output.accept(recipeKey("armor/tidesinger_helmet_" + name), helmetRecipe, null);
            output.accept(recipeKey("armor/tidesinger_chestplate_" + name), chestplateRecipe, null);
            output.accept(recipeKey("armor/tidesinger_leggings_" + name), leggingsRecipe, null);
            output.accept(recipeKey("armor/tidesinger_boots_" + name), bootsRecipe, null);
        }
    }
    public void createArmorCraftingRecipes(ArmorSet output, Item armorMaterial) {
        if (output == null) return;
        // helmet
        var ingredient = Ingredient.of(armorMaterial);
        ShapedRecipeBuilder.shaped(itemLookup, RecipeCategory.COMBAT, output.getHelmet())
            .define('#', ingredient)
            .pattern("###")
            .pattern("# #")
            .unlockedBy("has_material", has(armorMaterial))
            .save(this.output, recipeKey("armor/" + output.getName() + "_helmet"));
        // chestplate
        ShapedRecipeBuilder.shaped(itemLookup, RecipeCategory.COMBAT, output.getChestplate())
            .define('#', ingredient)
            .pattern("# #")
            .pattern("###")
            .pattern("###")
            .unlockedBy("has_material", has(armorMaterial))
            .save(this.output, recipeKey("armor/" + output.getName() + "_chestplate"));
        // leggings
        ShapedRecipeBuilder.shaped(itemLookup, RecipeCategory.COMBAT, output.getLeggings())
            .define('#', ingredient)
            .pattern("###")
            .pattern("# #")
            .pattern("# #")
            .unlockedBy("has_material", has(armorMaterial))
            .save(this.output, recipeKey("armor/" + output.getName() + "_leggings"));
        // boots
        ShapedRecipeBuilder.shaped(itemLookup, RecipeCategory.COMBAT, output.getBoots())
            .define('#', ingredient)
            .pattern("# #")
            .pattern("# #")
            .unlockedBy("has_material", has(armorMaterial))
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
            .unlocks("has_template", has(template))
            .save(output, recipeKey("armor/" + outputArmorSet.getName() + "_helmet"));
        // chestplate
        SmithingTransformRecipeBuilder.smithing(
                Ingredient.of(template),
                Ingredient.of(baseChestplate),
                addition,
                RecipeCategory.COMBAT,
                outputArmorSet.getChestplate()
            )
            .unlocks("has_template", has(template))
            .save(output, recipeKey("armor/" + outputArmorSet.getName() + "_chestplate"));
        // leggings
        SmithingTransformRecipeBuilder.smithing(
                Ingredient.of(template),
                Ingredient.of(baseLeggings),
                addition,
                RecipeCategory.COMBAT,
                outputArmorSet.getLeggings()
            )
            .unlocks("has_template", has(template))
            .save(output, recipeKey("armor/" + outputArmorSet.getName() + "_leggings"));
        // boots
        SmithingTransformRecipeBuilder.smithing(
                Ingredient.of(template),
                Ingredient.of(baseBoots),
                addition,
                RecipeCategory.COMBAT,
                outputArmorSet.getBoots()
            )
            .unlocks("has_template", has(template))
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
