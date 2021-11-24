package nourl.mythicmetals.world;

import com.google.common.collect.ImmutableList;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.block.Blocks;
import net.minecraft.structure.rule.BlockMatchRuleTest;
import net.minecraft.structure.rule.RuleTest;
import net.minecraft.structure.rule.TagMatchRuleTest;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.feature.PlacedFeature;
import nourl.mythicmetals.MythicMetals;
import nourl.mythicmetals.blocks.MythicBlocks;
import nourl.mythicmetals.config.MythicConfig;
import nourl.mythicmetals.utils.OreFeatureHelper;
import nourl.mythicmetals.utils.RegistryHelper;

public class MythicOreFeatures {
    // Defines the config that applies to the ore features
    public static final MythicConfig CONFIG = MythicMetals.CONFIG;

    // Defines new RuleTest(s), which checks what blocks an ore can spawn in
    public static final RuleTest BLACKSTONE_RULE = new BlockMatchRuleTest(Blocks.BLACKSTONE);
    public static final RuleTest CALCITE_RULE = new BlockMatchRuleTest(Blocks.CALCITE);
    public static final RuleTest NETHERRACK_RULE = new BlockMatchRuleTest(Blocks.NETHERRACK);
    public static final RuleTest DEEPSLATE_RULE = new TagMatchRuleTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES);
    public static final RuleTest END_STONE_RULE = new BlockMatchRuleTest(Blocks.END_STONE);
    public static final RuleTest SMOOTH_BASALT_RULE = new BlockMatchRuleTest(Blocks.SMOOTH_BASALT);
    public static final RuleTest STONE_RULE = new TagMatchRuleTest(BlockTags.STONE_ORE_REPLACEABLES);
    public static final RuleTest TUFF_RULE = new BlockMatchRuleTest(Blocks.TUFF);


    // Defines a list of targets, which can check for multiple blocks and dynamically replace them when generating ore
    public static final ImmutableList<OreFeatureConfig.Target> ADAMANTITE_TARGETS = ImmutableList.of(OreFeatureConfig.createTarget(STONE_RULE, MythicBlocks.ADAMANTITE.getOre().getDefaultState()), OreFeatureConfig.createTarget(DEEPSLATE_RULE, MythicBlocks.ADAMANTITE.getOreVariant("deepslate").getDefaultState()));
    public static final ImmutableList<OreFeatureConfig.Target> MYTHRIL_TARGETS = ImmutableList.of(OreFeatureConfig.createTarget(STONE_RULE, MythicBlocks.MYTHRIL.getOre().getDefaultState()), OreFeatureConfig.createTarget(DEEPSLATE_RULE, MythicBlocks.MYTHRIL.getOreVariant("deepslate").getDefaultState()));
    public static final ImmutableList<OreFeatureConfig.Target> ORICHALCUM_TARGETS = ImmutableList.of(OreFeatureConfig.createTarget(STONE_RULE, MythicBlocks.ORICHALCUM.getOre().getDefaultState()), OreFeatureConfig.createTarget(TUFF_RULE, MythicBlocks.ORICHALCUM.getOreVariant("tuff").getDefaultState()), OreFeatureConfig.createTarget(SMOOTH_BASALT_RULE, MythicBlocks.ORICHALCUM.getOreVariant("smooth_basalt").getDefaultState()), OreFeatureConfig.createTarget(DEEPSLATE_RULE, MythicBlocks.ORICHALCUM.getOreVariant("deepslate").getDefaultState()));
    public static final ImmutableList<OreFeatureConfig.Target> PROMETHEUM_TARGETS = ImmutableList.of(OreFeatureConfig.createTarget(STONE_RULE, MythicBlocks.PROMETHEUM.getOre().getDefaultState()), OreFeatureConfig.createTarget(DEEPSLATE_RULE, MythicBlocks.PROMETHEUM.getOreVariant("deepslate").getDefaultState()));
    public static final ImmutableList<OreFeatureConfig.Target> STARRITE_TARGETS = ImmutableList.of(OreFeatureConfig.createTarget(STONE_RULE, MythicBlocks.STARRITE.getOre().getDefaultState()), OreFeatureConfig.createTarget(CALCITE_RULE, MythicBlocks.STARRITE.getOreVariant("calcite").getDefaultState()));
    public static final ImmutableList<OreFeatureConfig.Target> STORMYX_TARGETS = ImmutableList.of(OreFeatureConfig.createTarget(NETHERRACK_RULE, MythicBlocks.STORMYX.getOre().getDefaultState()), OreFeatureConfig.createTarget(BLACKSTONE_RULE, MythicBlocks.STORMYX.getOreVariant("blackstone").getDefaultState()));
    public static final ImmutableList<OreFeatureConfig.Target> OVERWORLD_NETHER_ORE_TARGETS = ImmutableList.of(OreFeatureConfig.createTarget(NETHERRACK_RULE, MythicBlocks.MIDAS_GOLD.getOre().getDefaultState()), OreFeatureConfig.createTarget(BLACKSTONE_RULE, MythicBlocks.STORMYX.getOreVariant("blackstone").getDefaultState()));
    public static final ImmutableList<OreFeatureConfig.Target> UNOBTAINIUM_TARGETS = ImmutableList.of(OreFeatureConfig.createTarget(STONE_RULE, MythicBlocks.UNOBTAINIUM.getOre().getDefaultState()), OreFeatureConfig.createTarget(DEEPSLATE_RULE, MythicBlocks.UNOBTAINIUM.getOreVariant("deepslate").getDefaultState()));

    // Placed ore features
    public static PlacedFeature ORE_AQUARIUM = OreFeatureHelper.uniform("ore_aquarium", STONE_RULE, MythicBlocks.AQUARIUM.getOre(), CONFIG.aquarium);
    public static PlacedFeature ORE_BANGLUM = OreFeatureHelper.uniform("ore_banglum", STONE_RULE, MythicBlocks.BANGLUM.getOre(), CONFIG.banglum);
    public static PlacedFeature ORE_CARMOT = OreFeatureHelper.uniform("ore_carmot", STONE_RULE, MythicBlocks.CARMOT.getOre(), CONFIG.carmot);
    public static PlacedFeature ORE_KYBER = OreFeatureHelper.uniform("ore_kyber", STONE_RULE, MythicBlocks.KYBER.getOre(), CONFIG.kyber);
    public static PlacedFeature ORE_MANGANESE = OreFeatureHelper.uniform("ore_manganese", STONE_RULE, MythicBlocks.MANGANESE.getOre(), CONFIG.manganese);
    public static PlacedFeature ORE_OSMIUM = OreFeatureHelper.uniform("ore_osmium", STONE_RULE, MythicBlocks.OSMIUM.getOre(), CONFIG.osmium);
    public static PlacedFeature ORE_PLATINUM = OreFeatureHelper.trapezoid("ore_platinum", STONE_RULE, MythicBlocks.PLATINUM.getOre(), CONFIG.platinum);
    public static PlacedFeature ORE_QUADRILLUM = OreFeatureHelper.uniform("ore_quadrillum", STONE_RULE, MythicBlocks.QUADRILLUM.getOre(), CONFIG.quadrillum);
    public static PlacedFeature ORE_RUNITE = OreFeatureHelper.trapezoid("ore_runite", STONE_RULE, MythicBlocks.RUNITE.getOre(), CONFIG.runite);
    public static PlacedFeature ORE_SILVER = OreFeatureHelper.uniform("ore_silver", STONE_RULE, MythicBlocks.SILVER.getOre(), CONFIG.silver);
    public static PlacedFeature ORE_TIN = OreFeatureHelper.uniform("ore_tin", STONE_RULE, MythicBlocks.TIN.getOre(), CONFIG.tin);
    public static PlacedFeature ORE_VERMICULITE = OreFeatureHelper.uniform("ore_vermiuclite", STONE_RULE, MythicBlocks.VERMICULITE.getOre(), CONFIG.vermiculite);

    // Ores below zero - Reaches Deep Dark
    public static PlacedFeature ORE_ADAMANTITE = OreFeatureHelper.trapezoid("ore_adamantite", ADAMANTITE_TARGETS, CONFIG.adamantite);
    public static PlacedFeature ORE_CALCITE_KYBER = OreFeatureHelper.aboveBottom("ore_calcite_kyber", CALCITE_RULE, MythicBlocks.KYBER.getOreVariant("calcite"), CONFIG.kyber.getVariant());
    public static PlacedFeature ORE_MYTHRIL = OreFeatureHelper.trapezoid("ore_mythril", MYTHRIL_TARGETS, CONFIG.mythril);
    public static PlacedFeature ORE_ORICHALCUM = OreFeatureHelper.aboveBottom("ore_orichalcum", ORICHALCUM_TARGETS, CONFIG.orichalcum);
    public static PlacedFeature ORE_PROMETHEUM = OreFeatureHelper.aboveBottom("ore_prometheum", PROMETHEUM_TARGETS, CONFIG.prometheum);
    public static PlacedFeature ORE_STARRITE = OreFeatureHelper.belowTop("ore_starrite", STARRITE_TARGETS, CONFIG.starrite);
    public static PlacedFeature ORE_UNOBTAINIUM = OreFeatureHelper.trapezoid("ore_unobtainium", UNOBTAINIUM_TARGETS, CONFIG.unobtainium);

    // Nether Ores
    public static PlacedFeature ORE_MIDAS_GOLD = OreFeatureHelper.trapezoid("ore_midas_gold", NETHERRACK_RULE, MythicBlocks.MIDAS_GOLD.getOre(), CONFIG.midas_gold);
    public static PlacedFeature ORE_PALLADIUM = OreFeatureHelper.uniform("ore_palladium", NETHERRACK_RULE, MythicBlocks.PALLADIUM.getOre(), CONFIG.palladium);
    public static PlacedFeature ORE_STORMYX = OreFeatureHelper.belowTop("ore_stormyx", STORMYX_TARGETS, CONFIG.stormyx);
    public static PlacedFeature ORE_OVERWORLD_NETHER_ORES = OreFeatureHelper.uniform("ore_nether_in_overworld", OVERWORLD_NETHER_ORE_TARGETS, CONFIG.overworld_nether_ores);

    // End Ores
    public static PlacedFeature ORE_END_STARRITE = OreFeatureHelper.belowTop("ore_end_starrite", END_STONE_RULE, MythicBlocks.STARRITE.getOreVariant("end_stone"), CONFIG.starrite.getVariant());

    // RegistryKeys for features
    public static final RegistryKey<PlacedFeature> ADAMANTITE = OreFeatureHelper.placedFeatureKey("ore_adamantite");
    public static final RegistryKey<PlacedFeature> AQUARIUM = OreFeatureHelper.placedFeatureKey("ore_aquarium");
    public static final RegistryKey<PlacedFeature> BANGLUM = OreFeatureHelper.placedFeatureKey("ore_banglum");
    public static final RegistryKey<PlacedFeature> CARMOT = OreFeatureHelper.placedFeatureKey("ore_carmot");
    public static final RegistryKey<PlacedFeature> CALCITE_KYBER = OreFeatureHelper.placedFeatureKey("ore_calcite_kyber");
    public static final RegistryKey<PlacedFeature> KYBER = OreFeatureHelper.placedFeatureKey("ore_kyber");
    public static final RegistryKey<PlacedFeature> OVERWORLD_NETHER_ORES = OreFeatureHelper.placedFeatureKey("ore_overworld_nether_ores");
    public static final RegistryKey<PlacedFeature> MANGANESE = OreFeatureHelper.placedFeatureKey("ore_manganese");
    public static final RegistryKey<PlacedFeature> MIDAS_GOLD = OreFeatureHelper.placedFeatureKey("ore_midas_gold");
    public static final RegistryKey<PlacedFeature> MYTHRIL = OreFeatureHelper.placedFeatureKey("ore_mythril");
    public static final RegistryKey<PlacedFeature> ORICHALCUM = OreFeatureHelper.placedFeatureKey("ore_orichalcum");
    public static final RegistryKey<PlacedFeature> OSMIUM = OreFeatureHelper.placedFeatureKey("ore_osmium");
    public static final RegistryKey<PlacedFeature> PLATINUM = OreFeatureHelper.placedFeatureKey("ore_platinum");
    public static final RegistryKey<PlacedFeature> PROMETHEUM = OreFeatureHelper.placedFeatureKey("ore_prometheum");
    public static final RegistryKey<PlacedFeature> QUADRILLUM = OreFeatureHelper.placedFeatureKey("ore_quadrillum");
    public static final RegistryKey<PlacedFeature> RUNITE = OreFeatureHelper.placedFeatureKey("ore_runite");
    public static final RegistryKey<PlacedFeature> SILVER = OreFeatureHelper.placedFeatureKey("ore_silver");
    public static final RegistryKey<PlacedFeature> STARRITE = OreFeatureHelper.placedFeatureKey("ore_starrite");
    public static final RegistryKey<PlacedFeature> END_STARRITE = OreFeatureHelper.placedFeatureKey("ore_end_starrite");
    public static final RegistryKey<PlacedFeature> STORMYX = OreFeatureHelper.placedFeatureKey("ore_stormyx");
    public static final RegistryKey<PlacedFeature> TIN = OreFeatureHelper.placedFeatureKey("ore_tin");
    public static final RegistryKey<PlacedFeature> PALLADIUM = OreFeatureHelper.placedFeatureKey("ore_palladium");
    public static final RegistryKey<PlacedFeature> UNOBTAINIUM = OreFeatureHelper.placedFeatureKey("ore_unobtainium");
    public static final RegistryKey<PlacedFeature> VERMICULITE = OreFeatureHelper.placedFeatureKey("ore_vermiculite");

    public static void init() {

        //Overworld Ores
        if (CONFIG.adamantite.enabled) {
            RegistryHelper.placedFeature(ADAMANTITE.getValue().getPath(), ORE_ADAMANTITE);
            OreFeatureHelper.ore(ADAMANTITE);
        }
        if (CONFIG.banglum.enabled) {
            RegistryHelper.placedFeature(BANGLUM.getValue().getPath(), ORE_BANGLUM);
            OreFeatureHelper.ore(BANGLUM);
        }
        if (CONFIG.carmot.enabled) {
            RegistryHelper.placedFeature(CARMOT.getValue().getPath(), ORE_CARMOT);
            OreFeatureHelper.ore(CARMOT);
        }
        if (CONFIG.kyber.enabled) {
            RegistryHelper.placedFeature(KYBER.getValue().getPath(), ORE_KYBER);
            RegistryHelper.placedFeature(CALCITE_KYBER.getValue().getPath(), ORE_CALCITE_KYBER);
            OreFeatureHelper.ore(KYBER);
            OreFeatureHelper.ore(CALCITE_KYBER);
        }
        if (CONFIG.mythril.enabled) {
            RegistryHelper.placedFeature(MYTHRIL.getValue().getPath(), ORE_MYTHRIL);
            OreFeatureHelper.ore(MYTHRIL);
        }
        if (CONFIG.orichalcum.enabled) {
            RegistryHelper.placedFeature(ORICHALCUM.getValue().getPath(), ORE_ORICHALCUM);
            OreFeatureHelper.ore(ORICHALCUM);
        }
        if (CONFIG.osmium.enabled) {
            RegistryHelper.placedFeature(OSMIUM.getValue().getPath(), ORE_OSMIUM);
            OreFeatureHelper.ore(OSMIUM);
        }
        if (CONFIG.overworld_nether_ores.enabled) {
            RegistryHelper.placedFeature(OVERWORLD_NETHER_ORES.getValue().getPath(), ORE_OVERWORLD_NETHER_ORES);
            OreFeatureHelper.ore(OVERWORLD_NETHER_ORES);
        }
        if (CONFIG.palladium.enabled) {
            RegistryHelper.placedFeature(PALLADIUM.getValue().getPath(), ORE_PALLADIUM);
            OreFeatureHelper.netherOre(PALLADIUM);
        }
        if (CONFIG.platinum.enabled) {
            RegistryHelper.placedFeature(PLATINUM.getValue().getPath(), ORE_PLATINUM);
            OreFeatureHelper.ore(PLATINUM);
        }
        if (CONFIG.quadrillum.enabled) {
            RegistryHelper.placedFeature(QUADRILLUM.getValue().getPath(), ORE_QUADRILLUM);
            OreFeatureHelper.ore(QUADRILLUM);
        }
        if (CONFIG.runite.enabled) {
            RegistryHelper.placedFeature(RUNITE.getValue().getPath(), ORE_RUNITE);
            OreFeatureHelper.ore(RUNITE);
        }
        if (CONFIG.silver.enabled) {
            RegistryHelper.placedFeature(SILVER.getValue().getPath(), ORE_SILVER);
            OreFeatureHelper.ore(SILVER);
        }
        if (CONFIG.starrite.enabled) {
            RegistryHelper.placedFeature(STARRITE.getValue().getPath(), ORE_STARRITE);
            RegistryHelper.placedFeature(END_STARRITE.getValue().getPath(), ORE_END_STARRITE);
            OreFeatureHelper.ore(STARRITE);
            OreFeatureHelper.endOre(END_STARRITE);
        }
        if (CONFIG.tin.enabled) {
            RegistryHelper.placedFeature(TIN.getValue().getPath(), ORE_TIN);
            OreFeatureHelper.ore(TIN);
        }
        if (CONFIG.unobtainium.enabled) {
            RegistryHelper.placedFeature(UNOBTAINIUM.getValue().getPath(), ORE_UNOBTAINIUM);
            OreFeatureHelper.ore(UNOBTAINIUM);
        }
        if (CONFIG.vermiculite.enabled) {
            RegistryHelper.placedFeature(VERMICULITE.getValue().getPath(), ORE_VERMICULITE);
            OreFeatureHelper.ore(VERMICULITE);
        }

        //Nether Ores
        if (CONFIG.midas_gold.enabled) {
            RegistryHelper.placedFeature(MIDAS_GOLD.getValue().getPath(), ORE_MIDAS_GOLD);
            OreFeatureHelper.netherOre(MIDAS_GOLD);
        }
        if (CONFIG.stormyx.enabled) {
            RegistryHelper.placedFeature(STORMYX.getValue().getPath(), ORE_STORMYX);
            OreFeatureHelper.netherOre(STORMYX);
        }
        if (CONFIG.prometheum.enabled) {
            OreFeatureHelper.netherOre(PALLADIUM);
        }
        if (CONFIG.midas_gold.enabled & CONFIG.stormyx.enabled) {
            OreFeatureHelper.netherOre(OVERWORLD_NETHER_ORES);
        }

        //Ocean only ores
        if (CONFIG.aquarium.enabled) {
            RegistryHelper.placedFeature(AQUARIUM.getValue().getPath(), ORE_AQUARIUM);
            BiomeModifications.addFeature(BiomeSelectors.includeByKey(
                    BiomeKeys.COLD_OCEAN,
                    BiomeKeys.DEEP_COLD_OCEAN,
                    BiomeKeys.DEEP_FROZEN_OCEAN,
                    BiomeKeys.DEEP_LUKEWARM_OCEAN,
                    BiomeKeys.DEEP_OCEAN,
                    BiomeKeys.FROZEN_OCEAN,
                    BiomeKeys.LUKEWARM_OCEAN,
                    BiomeKeys.OCEAN,
                    BiomeKeys.WARM_OCEAN
            ), GenerationStep.Feature.UNDERGROUND_ORES, AQUARIUM);

            OreFeatureHelper.modBiomeOres("byg", "tropical_islands", AQUARIUM);

            OreFeatureHelper.modBiomeOres("terrestria", "caldera", AQUARIUM);
            OreFeatureHelper.modBiomeOres("terrestria", "rainbow_rainforest_lake", AQUARIUM);
            OreFeatureHelper.modBiomeOres("terrestria", "volcanic_island", AQUARIUM);
            OreFeatureHelper.modBiomeOres("terrestria", "volcanic_island_shore", AQUARIUM);

            OreFeatureHelper.modBiomeOres("traverse", "wooded_island", AQUARIUM);

            OreFeatureHelper.modBiomeOres("lakeside", "warm_lake", AQUARIUM);
            OreFeatureHelper.modBiomeOres("lakeside", "jungle_lake", AQUARIUM);
            OreFeatureHelper.modBiomeOres("lakeside", "cold_lake", AQUARIUM);
            OreFeatureHelper.modBiomeOres("lakeside", "mountain_lake", AQUARIUM);

            OreFeatureHelper.modBiomeOres("terralith", "dark_ocean", AQUARIUM);
            OreFeatureHelper.modBiomeOres("terralith", "dark_swamp", AQUARIUM);
            OreFeatureHelper.modBiomeOres("terralith", "tropical_archipelago", AQUARIUM);
        }
        //Jungle only ores
        if (CONFIG.prometheum.enabled) {
            RegistryHelper.placedFeature(PROMETHEUM.getValue().getPath(), ORE_PROMETHEUM);
            BiomeModifications.addFeature(BiomeSelectors.includeByKey(
                    BiomeKeys.BAMBOO_JUNGLE,
                    BiomeKeys.JUNGLE,
                    BiomeKeys.SPARSE_JUNGLE,
                    BiomeKeys.LUSH_CAVES
            ), GenerationStep.Feature.UNDERGROUND_ORES, PROMETHEUM);

            OreFeatureHelper.modBiomeOres("byg", "araucaria_forest", PROMETHEUM);
            OreFeatureHelper.modBiomeOres("byg", "cherry_blossom_forest", PROMETHEUM);
            OreFeatureHelper.modBiomeOres("byg", "ebony_woods", PROMETHEUM);
            OreFeatureHelper.modBiomeOres("byg", "jacaranda_forest", PROMETHEUM);
            OreFeatureHelper.modBiomeOres("byg", "redwood_tropics", PROMETHEUM);
            OreFeatureHelper.modBiomeOres("byg", "tropical_islands", PROMETHEUM);
            OreFeatureHelper.modBiomeOres("byg", "tropical_fungal_rainforest", PROMETHEUM);
            OreFeatureHelper.modBiomeOres("byg", "tropical_rainforest", PROMETHEUM);

            OreFeatureHelper.modBiomeOres("terrestria", "oasis", PROMETHEUM);
            OreFeatureHelper.modBiomeOres("terrestria", "hemlock_rainforest", PROMETHEUM);
            OreFeatureHelper.modBiomeOres("terrestria", "hemlock_clearing", PROMETHEUM);
            OreFeatureHelper.modBiomeOres("terrestria", "lush_redwood_forest", PROMETHEUM);
            OreFeatureHelper.modBiomeOres("terrestria", "rainbow_rainforest", PROMETHEUM);
            OreFeatureHelper.modBiomeOres("terrestria", "rainbow_rainforest_lake", PROMETHEUM);
            OreFeatureHelper.modBiomeOres("terrestria", "volcanic_island", PROMETHEUM);

            OreFeatureHelper.modBiomeOres("traverse", "lush_swamp", PROMETHEUM);
            OreFeatureHelper.modBiomeOres("traverse", "mini_jungle", PROMETHEUM);

            OreFeatureHelper.modBiomeOres("lakeside", "jungle_lake", PROMETHEUM);
            OreFeatureHelper.modBiomeOres("lakeside", "jungle_island", PROMETHEUM);

            OreFeatureHelper.modBiomeOres("terralith", "amethyst_rainforest", PROMETHEUM);
            OreFeatureHelper.modBiomeOres("terralith", "amethyst_rainforest_lowlands", PROMETHEUM);
            OreFeatureHelper.modBiomeOres("terralith", "coastal_jungle", PROMETHEUM);
            OreFeatureHelper.modBiomeOres("terralith", "hot_shrubland", PROMETHEUM);
            OreFeatureHelper.modBiomeOres("terralith", "jungle_mountains", PROMETHEUM);
            OreFeatureHelper.modBiomeOres("terralith", "lush_canyon", PROMETHEUM);
            OreFeatureHelper.modBiomeOres("terralith", "mega_jungle", PROMETHEUM);
            OreFeatureHelper.modBiomeOres("terralith", "sakura_grove", PROMETHEUM);
            OreFeatureHelper.modBiomeOres("terralith", "tropical_archipelago", PROMETHEUM);
        }
        //Mountain only ores
        if (CONFIG.manganese.enabled) {
            RegistryHelper.placedFeature(MANGANESE.getValue().getPath(), ORE_MANGANESE);
            BiomeModifications.addFeature(BiomeSelectors.includeByKey(
                    BiomeKeys.SNOWY_SLOPES,
                    BiomeKeys.FROZEN_PEAKS,
                    BiomeKeys.JAGGED_PEAKS,
                    BiomeKeys.STONY_PEAKS
            ), GenerationStep.Feature.UNDERGROUND_ORES, MANGANESE);

            OreFeatureHelper.modBiomeOres("byg", "alpine_foothills", MANGANESE);
            OreFeatureHelper.modBiomeOres("byg", "alps", MANGANESE);
            OreFeatureHelper.modBiomeOres("byg", "black_forest_hills", MANGANESE);
            OreFeatureHelper.modBiomeOres("byg", "blue_taiga_hills", MANGANESE);
            OreFeatureHelper.modBiomeOres("byg", "bluff_peaks", MANGANESE);
            OreFeatureHelper.modBiomeOres("byg", "bluff_steeps", MANGANESE);
            OreFeatureHelper.modBiomeOres("byg", "boreal_forest_hills", MANGANESE);
            OreFeatureHelper.modBiomeOres("byg", "cika_mountains", MANGANESE);
            OreFeatureHelper.modBiomeOres("byg", "coniferous_forest_hills", MANGANESE);
            OreFeatureHelper.modBiomeOres("byg", "dover_mountains", MANGANESE);
            OreFeatureHelper.modBiomeOres("byg", "ebony_hills", MANGANESE);
            OreFeatureHelper.modBiomeOres("byg", "enchatned_forest_hills", MANGANESE);
            OreFeatureHelper.modBiomeOres("byg", "evergreen_hills", MANGANESE);
            OreFeatureHelper.modBiomeOres("byg", "grassland_plateau", MANGANESE);
            OreFeatureHelper.modBiomeOres("byg", "guiana_clearing", MANGANESE);
            OreFeatureHelper.modBiomeOres("byg", "guiana_shield", MANGANESE);
            OreFeatureHelper.modBiomeOres("byg", "jacaranda_forest_hills", MANGANESE);
            OreFeatureHelper.modBiomeOres("byg", "redwood_mountians", MANGANESE);
            OreFeatureHelper.modBiomeOres("byg", "skyris_highlands", MANGANESE);
            OreFeatureHelper.modBiomeOres("byg", "wooded_grassland_plateau", MANGANESE);

            OreFeatureHelper.modBiomeOres("terrestria", "caldera_foothills", MANGANESE);
            OreFeatureHelper.modBiomeOres("terrestria", "caldera_ridge", MANGANESE);
            OreFeatureHelper.modBiomeOres("terrestria", "outback_uluru", MANGANESE);
            OreFeatureHelper.modBiomeOres("terrestria", "rainbow_rainforest", MANGANESE);
            OreFeatureHelper.modBiomeOres("terrestria", "rainbow_rainforest_mountains", MANGANESE);

            OreFeatureHelper.modBiomeOres("traverse", "arid_highlands", MANGANESE);
            OreFeatureHelper.modBiomeOres("traverse", "cliffs", MANGANESE);
            OreFeatureHelper.modBiomeOres("traverse", "rolling_hills", MANGANESE);

            OreFeatureHelper.modBiomeOres("lakeside", "mountain_lake", MANGANESE);

            OreFeatureHelper.modBiomeOres("terralith", "emerald_peaks", MANGANESE);
            OreFeatureHelper.modBiomeOres("terralith", "haze_mountain", MANGANESE);
            OreFeatureHelper.modBiomeOres("terralith", "jungle_mountains", MANGANESE);
            OreFeatureHelper.modBiomeOres("terralith", "red_mountains", MANGANESE);
            OreFeatureHelper.modBiomeOres("terralith", "salt_mountains", MANGANESE);
            OreFeatureHelper.modBiomeOres("terralith", "sandstone_valley", MANGANESE);
            OreFeatureHelper.modBiomeOres("terralith", "sandstone_valley_r", MANGANESE);
            OreFeatureHelper.modBiomeOres("terralith", "savanna_heights", MANGANESE);
            OreFeatureHelper.modBiomeOres("terralith", "scarlet_mountains", MANGANESE);
            OreFeatureHelper.modBiomeOres("terralith", "siberian_taiga", MANGANESE);
            OreFeatureHelper.modBiomeOres("terralith", "stardust_valley", MANGANESE);
            OreFeatureHelper.modBiomeOres("terralith", "tropical_volcano", MANGANESE);
            OreFeatureHelper.modBiomeOres("terralith", "yellowstone", MANGANESE);
            OreFeatureHelper.modBiomeOres("terralith", "yosemite_cliffs", MANGANESE);
        }

    }

}