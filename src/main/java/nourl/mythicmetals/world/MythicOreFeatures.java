package nourl.mythicmetals.world;

import com.google.common.collect.ImmutableList;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.block.Blocks;
import net.minecraft.structure.rule.BlockMatchRuleTest;
import net.minecraft.structure.rule.RuleTest;
import net.minecraft.structure.rule.TagMatchRuleTest;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.feature.PlacedFeature;
import nourl.mythicmetals.MythicMetals;
import nourl.mythicmetals.blocks.MythicBlocks;
import nourl.mythicmetals.config.MythicConfig;
import nourl.mythicmetals.utils.OreFeatureHelper;

@SuppressWarnings("unused")
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
    public static final ImmutableList<OreFeatureConfig.Target> CARMOT_TARGETS = ImmutableList.of(OreFeatureConfig.createTarget(STONE_RULE, MythicBlocks.CARMOT.getOre().getDefaultState()), OreFeatureConfig.createTarget(DEEPSLATE_RULE, MythicBlocks.CARMOT.getOreVariant("deepslate").getDefaultState()));
    public static final ImmutableList<OreFeatureConfig.Target> MYTHRIL_TARGETS = ImmutableList.of(OreFeatureConfig.createTarget(STONE_RULE, MythicBlocks.MYTHRIL.getOre().getDefaultState()), OreFeatureConfig.createTarget(DEEPSLATE_RULE, MythicBlocks.MYTHRIL.getOreVariant("deepslate").getDefaultState()));
    public static final ImmutableList<OreFeatureConfig.Target> MORKITE_TARGETS = ImmutableList.of(OreFeatureConfig.createTarget(STONE_RULE, MythicBlocks.MORKITE.getOre().getDefaultState()), OreFeatureConfig.createTarget(DEEPSLATE_RULE, MythicBlocks.MORKITE.getOreVariant("deepslate").getDefaultState()));
    public static final ImmutableList<OreFeatureConfig.Target> ORICHALCUM_TARGETS = ImmutableList.of(OreFeatureConfig.createTarget(STONE_RULE, MythicBlocks.ORICHALCUM.getOre().getDefaultState()), OreFeatureConfig.createTarget(TUFF_RULE, MythicBlocks.ORICHALCUM.getOreVariant("tuff").getDefaultState()), OreFeatureConfig.createTarget(SMOOTH_BASALT_RULE, MythicBlocks.ORICHALCUM.getOreVariant("smooth_basalt").getDefaultState()), OreFeatureConfig.createTarget(DEEPSLATE_RULE, MythicBlocks.ORICHALCUM.getOreVariant("deepslate").getDefaultState()));
    public static final ImmutableList<OreFeatureConfig.Target> OVERWORLD_NETHER_ORE_TARGETS = ImmutableList.of(OreFeatureConfig.createTarget(NETHERRACK_RULE, MythicBlocks.MIDAS_GOLD.getOre().getDefaultState()), OreFeatureConfig.createTarget(BLACKSTONE_RULE, MythicBlocks.STORMYX.getOreVariant("blackstone").getDefaultState()));
    public static final ImmutableList<OreFeatureConfig.Target> PROMETHEUM_TARGETS = ImmutableList.of(OreFeatureConfig.createTarget(STONE_RULE, MythicBlocks.PROMETHEUM.getOre().getDefaultState()), OreFeatureConfig.createTarget(DEEPSLATE_RULE, MythicBlocks.PROMETHEUM.getOreVariant("deepslate").getDefaultState()));
    public static final ImmutableList<OreFeatureConfig.Target> STARRITE_TARGETS = ImmutableList.of(OreFeatureConfig.createTarget(STONE_RULE, MythicBlocks.STARRITE.getOre().getDefaultState()), OreFeatureConfig.createTarget(CALCITE_RULE, MythicBlocks.STARRITE.getOreVariant("calcite").getDefaultState()));
    public static final ImmutableList<OreFeatureConfig.Target> STORMYX_TARGETS = ImmutableList.of(OreFeatureConfig.createTarget(NETHERRACK_RULE, MythicBlocks.STORMYX.getOre().getDefaultState()), OreFeatureConfig.createTarget(BLACKSTONE_RULE, MythicBlocks.STORMYX.getOreVariant("blackstone").getDefaultState()));
    public static final ImmutableList<OreFeatureConfig.Target> UNOBTAINIUM_TARGETS = ImmutableList.of(OreFeatureConfig.createTarget(STONE_RULE, MythicBlocks.UNOBTAINIUM.getOre().getDefaultState()), OreFeatureConfig.createTarget(DEEPSLATE_RULE, MythicBlocks.UNOBTAINIUM.getOreVariant("deepslate").getDefaultState()));

    // Placed ore features
    public static RegistryEntry<PlacedFeature> ORE_AQUARIUM = OreFeatureHelper.create("ore_aquarium", STONE_RULE, MythicBlocks.AQUARIUM.getOre(), CONFIG.aquarium);
    public static RegistryEntry<PlacedFeature> ORE_BANGLUM = OreFeatureHelper.create("ore_banglum", STONE_RULE, MythicBlocks.BANGLUM.getOre(), CONFIG.banglum);
    public static RegistryEntry<PlacedFeature> ORE_KYBER = OreFeatureHelper.create("ore_kyber", STONE_RULE, MythicBlocks.KYBER.getOre(), CONFIG.kyber);
    public static RegistryEntry<PlacedFeature> ORE_MANGANESE = OreFeatureHelper.create("ore_manganese", STONE_RULE, MythicBlocks.MANGANESE.getOre(), CONFIG.manganese);
    public static RegistryEntry<PlacedFeature> ORE_OSMIUM = OreFeatureHelper.create("ore_osmium", STONE_RULE, MythicBlocks.OSMIUM.getOre(), CONFIG.osmium);
    public static RegistryEntry<PlacedFeature> ORE_PLATINUM = OreFeatureHelper.create("ore_platinum", STONE_RULE, MythicBlocks.PLATINUM.getOre(), CONFIG.platinum);
    public static RegistryEntry<PlacedFeature> ORE_QUADRILLUM = OreFeatureHelper.create("ore_quadrillum", STONE_RULE, MythicBlocks.QUADRILLUM.getOre(), CONFIG.quadrillum);
    public static RegistryEntry<PlacedFeature> ORE_RUNITE = OreFeatureHelper.create("ore_runite", STONE_RULE, MythicBlocks.RUNITE.getOre(), CONFIG.runite);
    public static RegistryEntry<PlacedFeature> ORE_SILVER = OreFeatureHelper.create("ore_silver", STONE_RULE, MythicBlocks.SILVER.getOre(), CONFIG.silver);
    public static RegistryEntry<PlacedFeature> ORE_TIN = OreFeatureHelper.create("ore_tin", STONE_RULE, MythicBlocks.TIN.getOre(), CONFIG.tin);

    // Ores below zero - Reaches Deep Dark
    public static RegistryEntry<PlacedFeature> ORE_ADAMANTITE = OreFeatureHelper.create("ore_adamantite", ADAMANTITE_TARGETS, CONFIG.adamantite);
    public static RegistryEntry<PlacedFeature> ORE_CALCITE_KYBER = OreFeatureHelper.create("ore_calcite_kyber", CALCITE_RULE, MythicBlocks.KYBER.getOreVariant("calcite"), CONFIG.kyber.getVariant());
    public static RegistryEntry<PlacedFeature> ORE_CARMOT = OreFeatureHelper.create("ore_carmot", CARMOT_TARGETS, CONFIG.carmot);
    public static RegistryEntry<PlacedFeature> ORE_MORKITE = OreFeatureHelper.create("ore_morkite", MORKITE_TARGETS, CONFIG.morkite);
    public static RegistryEntry<PlacedFeature> ORE_MYTHRIL = OreFeatureHelper.create("ore_mythril", MYTHRIL_TARGETS, CONFIG.mythril);
    public static RegistryEntry<PlacedFeature> ORE_ORICHALCUM = OreFeatureHelper.create("ore_orichalcum", ORICHALCUM_TARGETS, CONFIG.orichalcum);
    public static RegistryEntry<PlacedFeature> ORE_PROMETHEUM = OreFeatureHelper.create("ore_prometheum", PROMETHEUM_TARGETS, CONFIG.prometheum);
    public static RegistryEntry<PlacedFeature> ORE_STARRITE = OreFeatureHelper.create("ore_starrite", STARRITE_TARGETS, CONFIG.starrite);
    public static RegistryEntry<PlacedFeature> ORE_UNOBTAINIUM = OreFeatureHelper.create("ore_unobtainium", UNOBTAINIUM_TARGETS, CONFIG.unobtainium);

    // Nether Ores
    public static RegistryEntry<PlacedFeature> ORE_NETHER_BANGLUM = OreFeatureHelper.create("ore_nether_banglum", NETHERRACK_RULE, MythicBlocks.BANGLUM.getOreVariant("nether"), CONFIG.banglum.getVariant());
    public static RegistryEntry<PlacedFeature> ORE_MIDAS_GOLD = OreFeatureHelper.create("ore_midas_gold", NETHERRACK_RULE, MythicBlocks.MIDAS_GOLD.getOre(), CONFIG.midas_gold);
    public static RegistryEntry<PlacedFeature> ORE_PALLADIUM = OreFeatureHelper.create("ore_palladium", NETHERRACK_RULE, MythicBlocks.PALLADIUM.getOre(), CONFIG.palladium);
    public static RegistryEntry<PlacedFeature> ORE_STORMYX = OreFeatureHelper.create("ore_stormyx", STORMYX_TARGETS, CONFIG.stormyx);
    public static RegistryEntry<PlacedFeature> ORE_OVERWORLD_NETHER_ORES = OreFeatureHelper.create("ore_overworld_nether", OVERWORLD_NETHER_ORE_TARGETS, CONFIG.overworld_nether_ores);

    // End Ores
    public static RegistryEntry<PlacedFeature> ORE_END_STARRITE = OreFeatureHelper.create("ore_end_starrite", END_STONE_RULE, MythicBlocks.STARRITE.getOreVariant("end_stone"), CONFIG.starrite.getVariant());

    // RegistryKeys for features
    public static final RegistryKey<PlacedFeature> ADAMANTITE = OreFeatureHelper.placedFeatureKey("ore_adamantite");
    public static final RegistryKey<PlacedFeature> AQUARIUM = OreFeatureHelper.placedFeatureKey("ore_aquarium");
    public static final RegistryKey<PlacedFeature> BANGLUM = OreFeatureHelper.placedFeatureKey("ore_banglum");
    public static final RegistryKey<PlacedFeature> NETHER_BANGLUM = OreFeatureHelper.placedFeatureKey("ore_nether_banglum");
    public static final RegistryKey<PlacedFeature> CARMOT = OreFeatureHelper.placedFeatureKey("ore_carmot");
    public static final RegistryKey<PlacedFeature> CALCITE_KYBER = OreFeatureHelper.placedFeatureKey("ore_calcite_kyber");
    public static final RegistryKey<PlacedFeature> END_STARRITE = OreFeatureHelper.placedFeatureKey("ore_end_starrite");
    public static final RegistryKey<PlacedFeature> KYBER = OreFeatureHelper.placedFeatureKey("ore_kyber");
    public static final RegistryKey<PlacedFeature> MANGANESE = OreFeatureHelper.placedFeatureKey("ore_manganese");
    public static final RegistryKey<PlacedFeature> MIDAS_GOLD = OreFeatureHelper.placedFeatureKey("ore_midas_gold");
    public static final RegistryKey<PlacedFeature> MORKITE = OreFeatureHelper.placedFeatureKey("ore_morkite");
    public static final RegistryKey<PlacedFeature> MYTHRIL = OreFeatureHelper.placedFeatureKey("ore_mythril");
    public static final RegistryKey<PlacedFeature> ORICHALCUM = OreFeatureHelper.placedFeatureKey("ore_orichalcum");
    public static final RegistryKey<PlacedFeature> OSMIUM = OreFeatureHelper.placedFeatureKey("ore_osmium");
    public static final RegistryKey<PlacedFeature> OVERWORLD_NETHER_ORES = OreFeatureHelper.placedFeatureKey("ore_overworld_nether");
    public static final RegistryKey<PlacedFeature> PALLADIUM = OreFeatureHelper.placedFeatureKey("ore_palladium");
    public static final RegistryKey<PlacedFeature> PLATINUM = OreFeatureHelper.placedFeatureKey("ore_platinum");
    public static final RegistryKey<PlacedFeature> PROMETHEUM = OreFeatureHelper.placedFeatureKey("ore_prometheum");
    public static final RegistryKey<PlacedFeature> QUADRILLUM = OreFeatureHelper.placedFeatureKey("ore_quadrillum");
    public static final RegistryKey<PlacedFeature> RUNITE = OreFeatureHelper.placedFeatureKey("ore_runite");
    public static final RegistryKey<PlacedFeature> SILVER = OreFeatureHelper.placedFeatureKey("ore_silver");
    public static final RegistryKey<PlacedFeature> STARRITE = OreFeatureHelper.placedFeatureKey("ore_starrite");
    public static final RegistryKey<PlacedFeature> STORMYX = OreFeatureHelper.placedFeatureKey("ore_stormyx");
    public static final RegistryKey<PlacedFeature> TIN = OreFeatureHelper.placedFeatureKey("ore_tin");
    public static final RegistryKey<PlacedFeature> UNOBTAINIUM = OreFeatureHelper.placedFeatureKey("ore_unobtainium");

    public static void init() {

        //Overworld Ores
        if (CONFIG.adamantite.enabled) {
            OreFeatureHelper.ore(ADAMANTITE);
        }
        if (CONFIG.banglum.enabled) {
            OreFeatureHelper.ore(BANGLUM);
        }
        if (CONFIG.carmot.enabled) {
            OreFeatureHelper.ore(CARMOT);
        }
        if (CONFIG.kyber.enabled) {
            OreFeatureHelper.ore(KYBER);
            OreFeatureHelper.ore(CALCITE_KYBER);
        }
        if (CONFIG.mythril.enabled) {
            OreFeatureHelper.ore(MYTHRIL);
        }
        if (CONFIG.orichalcum.enabled) {
            OreFeatureHelper.ore(ORICHALCUM);
        }
        if (CONFIG.manganese.enabled) {
            OreFeatureHelper.ore(MANGANESE);
        }
        if (CONFIG.overworld_nether_ores.enabled) {
            OreFeatureHelper.ore(OVERWORLD_NETHER_ORES);
        }
        if (CONFIG.platinum.enabled) {
            OreFeatureHelper.ore(PLATINUM);
        }
        if (CONFIG.quadrillum.enabled) {
            OreFeatureHelper.ore(QUADRILLUM);
        }
        if (CONFIG.runite.enabled) {
            OreFeatureHelper.ore(RUNITE);
        }
        if (CONFIG.silver.enabled) {
            OreFeatureHelper.ore(SILVER);
        }
        if (CONFIG.starrite.enabled) {
            OreFeatureHelper.ore(STARRITE);
            OreFeatureHelper.endOre(END_STARRITE);
        }
        if (CONFIG.tin.enabled) {
            OreFeatureHelper.ore(TIN);
        }
        if (CONFIG.unobtainium.enabled) {
            OreFeatureHelper.ore(UNOBTAINIUM);
        }
        if (CONFIG.morkite.enabled) {
            OreFeatureHelper.ore(MORKITE);
        }

        //Nether Ores
        if (CONFIG.banglum.enabled) {
            OreFeatureHelper.netherOre(NETHER_BANGLUM);
        }
        if (CONFIG.midas_gold.enabled) {
            OreFeatureHelper.netherOre(MIDAS_GOLD);
        }
        if (CONFIG.stormyx.enabled) {
            OreFeatureHelper.netherOre(STORMYX);
        }
        if (CONFIG.palladium.enabled) {
            OreFeatureHelper.netherOre(PALLADIUM);
        }

        //Ocean only ores
        if (CONFIG.aquarium.enabled) {
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
        }
        //Jungle only ores
        if (CONFIG.prometheum.enabled) {
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
            OreFeatureHelper.modBiomeOres("terralith", "amethyst_canyon", PROMETHEUM);
            OreFeatureHelper.modBiomeOres("terralith", "coastal_jungle", PROMETHEUM);
            OreFeatureHelper.modBiomeOres("terralith", "hot_shrubland", PROMETHEUM);
            OreFeatureHelper.modBiomeOres("terralith", "jungle_mountains", PROMETHEUM);
            OreFeatureHelper.modBiomeOres("terralith", "rocky_jungle", PROMETHEUM);
            OreFeatureHelper.modBiomeOres("terralith", "sakura_grove", PROMETHEUM);
            OreFeatureHelper.modBiomeOres("terralith", "sakura_valley", PROMETHEUM);
            OreFeatureHelper.modBiomeOres("terralith", "tropical_jungle", PROMETHEUM);
        }
        //Mountain only ores
        if (CONFIG.osmium.enabled) {
            BiomeModifications.addFeature(BiomeSelectors.includeByKey(
                    BiomeKeys.SNOWY_SLOPES,
                    BiomeKeys.FROZEN_PEAKS,
                    BiomeKeys.JAGGED_PEAKS,
                    BiomeKeys.STONY_PEAKS
            ), GenerationStep.Feature.UNDERGROUND_ORES, OSMIUM);

            OreFeatureHelper.modBiomeOres("byg", "alpine_foothills", OSMIUM);
            OreFeatureHelper.modBiomeOres("byg", "alps", OSMIUM);
            OreFeatureHelper.modBiomeOres("byg", "black_forest_hills", OSMIUM);
            OreFeatureHelper.modBiomeOres("byg", "blue_taiga_hills", OSMIUM);
            OreFeatureHelper.modBiomeOres("byg", "bluff_peaks", OSMIUM);
            OreFeatureHelper.modBiomeOres("byg", "bluff_steeps", OSMIUM);
            OreFeatureHelper.modBiomeOres("byg", "boreal_forest_hills", OSMIUM);
            OreFeatureHelper.modBiomeOres("byg", "cika_mountains", OSMIUM);
            OreFeatureHelper.modBiomeOres("byg", "coniferous_forest_hills", OSMIUM);
            OreFeatureHelper.modBiomeOres("byg", "dover_mountains", OSMIUM);
            OreFeatureHelper.modBiomeOres("byg", "ebony_hills", OSMIUM);
            OreFeatureHelper.modBiomeOres("byg", "enchatned_forest_hills", OSMIUM);
            OreFeatureHelper.modBiomeOres("byg", "evergreen_hills", OSMIUM);
            OreFeatureHelper.modBiomeOres("byg", "grassland_plateau", OSMIUM);
            OreFeatureHelper.modBiomeOres("byg", "guiana_clearing", OSMIUM);
            OreFeatureHelper.modBiomeOres("byg", "guiana_shield", OSMIUM);
            OreFeatureHelper.modBiomeOres("byg", "jacaranda_forest_hills", OSMIUM);
            OreFeatureHelper.modBiomeOres("byg", "redwood_mountians", OSMIUM);
            OreFeatureHelper.modBiomeOres("byg", "skyris_highlands", OSMIUM);
            OreFeatureHelper.modBiomeOres("byg", "wooded_grassland_plateau", OSMIUM);

            OreFeatureHelper.modBiomeOres("terrestria", "caldera_foothills", OSMIUM);
            OreFeatureHelper.modBiomeOres("terrestria", "caldera_ridge", OSMIUM);
            OreFeatureHelper.modBiomeOres("terrestria", "outback_uluru", OSMIUM);
            OreFeatureHelper.modBiomeOres("terrestria", "rainbow_rainforest", OSMIUM);
            OreFeatureHelper.modBiomeOres("terrestria", "rainbow_rainforest_mountains", OSMIUM);

            OreFeatureHelper.modBiomeOres("traverse", "arid_highlands", OSMIUM);
            OreFeatureHelper.modBiomeOres("traverse", "cliffs", OSMIUM);
            OreFeatureHelper.modBiomeOres("traverse", "rolling_hills", OSMIUM);

            OreFeatureHelper.modBiomeOres("lakeside", "mountain_lake", OSMIUM);
            
            OreFeatureHelper.modBiomeOres("terralith", "caldera", OSMIUM);
            OreFeatureHelper.modBiomeOres("terralith", "cloud_forest", OSMIUM);
            OreFeatureHelper.modBiomeOres("terralith", "emerald_peaks", OSMIUM);
            OreFeatureHelper.modBiomeOres("terralith", "haze_mountain", OSMIUM);
            OreFeatureHelper.modBiomeOres("terralith", "jungle_mountains", OSMIUM);
            OreFeatureHelper.modBiomeOres("terralith", "painted_mountains", OSMIUM);
            OreFeatureHelper.modBiomeOres("terralith", "rocky_mountains", OSMIUM);
            OreFeatureHelper.modBiomeOres("terralith", "scarlet_mountains", OSMIUM);
            OreFeatureHelper.modBiomeOres("terralith", "siberian_taiga", OSMIUM);
            OreFeatureHelper.modBiomeOres("terralith", "stony_spires", OSMIUM);
            OreFeatureHelper.modBiomeOres("terralith", "volcanic_peaks", OSMIUM);
            OreFeatureHelper.modBiomeOres("terralith", "volcanic_crater", OSMIUM);
            OreFeatureHelper.modBiomeOres("terralith", "windswept_spires", OSMIUM);
            OreFeatureHelper.modBiomeOres("terralith", "yosemite_cliffs", OSMIUM);
        }

    }

}
