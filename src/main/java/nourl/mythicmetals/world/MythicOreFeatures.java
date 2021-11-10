package nourl.mythicmetals.world;

import com.google.common.collect.ImmutableList;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.block.Blocks;
import net.minecraft.structure.rule.BlockMatchRuleTest;
import net.minecraft.structure.rule.RuleTest;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.OreFeatureConfig;
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
    public static final RuleTest DEEPSLATE_RULE = OreFeatureConfig.Rules.DEEPSLATE_ORE_REPLACEABLES;
    public static final RuleTest END_STONE_RULE = new BlockMatchRuleTest(Blocks.END_STONE);
    public static final RuleTest SMOOTH_BASALT_RULE = new BlockMatchRuleTest(Blocks.SMOOTH_BASALT);
    public static final RuleTest STONE_RULE = OreFeatureConfig.Rules.STONE_ORE_REPLACEABLES;
    public static final RuleTest TUFF_RULE = new BlockMatchRuleTest(Blocks.TUFF);

    // Defines a list of targets, which can check for multiple blocks and dynamically replace them when generating ore
    public static final ImmutableList<OreFeatureConfig.Target> ADAMANTITE_TARGETS = ImmutableList.of(OreFeatureConfig.createTarget(STONE_RULE, MythicBlocks.ADAMANTITE.getOre().getDefaultState()), OreFeatureConfig.createTarget(DEEPSLATE_RULE, MythicBlocks.ADAMANTITE.getOreVariant("deepslate").getDefaultState()));
    public static final ImmutableList<OreFeatureConfig.Target> MYTHRIL_TARGETS = ImmutableList.of(OreFeatureConfig.createTarget(STONE_RULE, MythicBlocks.MYTHRIL.getOre().getDefaultState()), OreFeatureConfig.createTarget(DEEPSLATE_RULE, MythicBlocks.MYTHRIL.getOreVariant("deepslate").getDefaultState()));
    public static final ImmutableList<OreFeatureConfig.Target> ORICHALCUM_TARGETS = ImmutableList.of(OreFeatureConfig.createTarget(STONE_RULE, MythicBlocks.ORICHALCUM.getOre().getDefaultState()), OreFeatureConfig.createTarget(TUFF_RULE, MythicBlocks.ORICHALCUM.getOreVariant("tuff").getDefaultState()), OreFeatureConfig.createTarget(SMOOTH_BASALT_RULE, MythicBlocks.ORICHALCUM.getOreVariant("smooth_basalt").getDefaultState()), OreFeatureConfig.createTarget(DEEPSLATE_RULE, MythicBlocks.ORICHALCUM.getOreVariant("deepslate").getDefaultState()));
    public static final ImmutableList<OreFeatureConfig.Target> PROMETHEUM_TARGETS = ImmutableList.of(OreFeatureConfig.createTarget(STONE_RULE, MythicBlocks.PROMETHEUM.getOre().getDefaultState()), OreFeatureConfig.createTarget(DEEPSLATE_RULE, MythicBlocks.PROMETHEUM.getOreVariant("deepslate").getDefaultState()));
    public static final ImmutableList<OreFeatureConfig.Target> STARRITE_TARGETS = ImmutableList.of(OreFeatureConfig.createTarget(STONE_RULE, MythicBlocks.STARRITE.getOre().getDefaultState()), OreFeatureConfig.createTarget(CALCITE_RULE, MythicBlocks.STARRITE.getOreVariant("calcite").getDefaultState()));
    public static final ImmutableList<OreFeatureConfig.Target> STORMYX_TARGETS = ImmutableList.of(OreFeatureConfig.createTarget(OreFeatureConfig.Rules.NETHERRACK, MythicBlocks.STORMYX.getOre().getDefaultState()), OreFeatureConfig.createTarget(OreFeatureConfig.Rules.BASE_STONE_NETHER, MythicBlocks.STORMYX.getOreVariant("blackstone").getDefaultState()));
    public static final ImmutableList<OreFeatureConfig.Target> OVERWORLD_NETHER_ORE_TARGETS = ImmutableList.of(OreFeatureConfig.createTarget(OreFeatureConfig.Rules.NETHERRACK, MythicBlocks.MIDAS_GOLD.getOre().getDefaultState()), OreFeatureConfig.createTarget(BLACKSTONE_RULE, MythicBlocks.STORMYX.getOreVariant("blackstone").getDefaultState()));
    public static final ImmutableList<OreFeatureConfig.Target> UNOBTAINIUM_TARGETS = ImmutableList.of(OreFeatureConfig.createTarget(STONE_RULE, MythicBlocks.UNOBTAINIUM.getOre().getDefaultState()), OreFeatureConfig.createTarget(DEEPSLATE_RULE, MythicBlocks.UNOBTAINIUM.getOreVariant("deepslate").getDefaultState()));

    // Configured ore features
    public static ConfiguredFeature<?, ?> ORE_AQUARIUM = OreFeatureHelper.fixedOre(STONE_RULE, MythicBlocks.AQUARIUM.getOre().getDefaultState(), CONFIG.aquarium);
    public static ConfiguredFeature<?, ?> ORE_BANGLUM = OreFeatureHelper.fixedOre(STONE_RULE, MythicBlocks.BANGLUM.getOre().getDefaultState(), CONFIG.banglum);
    public static ConfiguredFeature<?, ?> ORE_CARMOT = OreFeatureHelper.fixedOre(STONE_RULE, MythicBlocks.CARMOT.getOre().getDefaultState(), CONFIG.carmot);
    public static ConfiguredFeature<?, ?> ORE_KYBER = OreFeatureHelper.fixedOre(STONE_RULE, MythicBlocks.KYBER.getOre().getDefaultState(), CONFIG.kyber);
    public static ConfiguredFeature<?, ?> ORE_MANGANESE = OreFeatureHelper.triangleOre(STONE_RULE, MythicBlocks.MANGANESE.getOre().getDefaultState(), CONFIG.manganese);
    public static ConfiguredFeature<?, ?> ORE_OSMIUM = OreFeatureHelper.fixedOre(STONE_RULE, MythicBlocks.OSMIUM.getOre().getDefaultState(), CONFIG.osmium);
    public static ConfiguredFeature<?, ?> ORE_PLATINUM = OreFeatureHelper.triangleOre(STONE_RULE, MythicBlocks.PLATINUM.getOre().getDefaultState(), CONFIG.platinum);
    public static ConfiguredFeature<?, ?> ORE_QUADRILLUM = OreFeatureHelper.fixedOre(STONE_RULE, MythicBlocks.QUADRILLUM.getOre().getDefaultState(), CONFIG.quadrillum);
    public static ConfiguredFeature<?, ?> ORE_RUNITE = OreFeatureHelper.triangleOre(STONE_RULE, MythicBlocks.RUNITE.getOre().getDefaultState(), CONFIG.runite);
    public static ConfiguredFeature<?, ?> ORE_SILVER = OreFeatureHelper.fixedOre(STONE_RULE, MythicBlocks.SILVER.getOre().getDefaultState(), CONFIG.silver);
    public static ConfiguredFeature<?, ?> ORE_TIN = OreFeatureHelper.fixedOre(STONE_RULE, MythicBlocks.TIN.getOre().getDefaultState(), CONFIG.tin);
    public static ConfiguredFeature<?, ?> ORE_VERMICULITE = OreFeatureHelper.fixedOre(STONE_RULE, MythicBlocks.VERMICULITE.getOre().getDefaultState(), CONFIG.vermiculite);

    // Ores below zero - Reaches Deep Dark
    public static ConfiguredFeature<?, ?> ORE_ADAMANTITE = OreFeatureHelper.triangleOre(ADAMANTITE_TARGETS, CONFIG.adamantite);
    public static ConfiguredFeature<?, ?> ORE_CALCITE_KYBER = OreFeatureHelper.bottomOffsetOre(CALCITE_RULE, MythicBlocks.KYBER.getOreVariant("calcite").getDefaultState(), CONFIG.kyber.getVariant());
    public static ConfiguredFeature<?, ?> ORE_MYTHRIL = OreFeatureHelper.triangleOre(MYTHRIL_TARGETS, CONFIG.mythril);
    public static ConfiguredFeature<?, ?> ORE_ORICHALCUM = OreFeatureHelper.bottomOffsetOre(ORICHALCUM_TARGETS, CONFIG.orichalcum);
    public static ConfiguredFeature<?, ?> ORE_PROMETHEUM = OreFeatureHelper.bottomOffsetOre(PROMETHEUM_TARGETS, CONFIG.prometheum);
    public static ConfiguredFeature<?, ?> ORE_STARRITE = OreFeatureHelper.topOffsetOre(STARRITE_TARGETS, CONFIG.starrite);
    public static ConfiguredFeature<?, ?> ORE_UNOBTAINIUM = OreFeatureHelper.scatteredOre(UNOBTAINIUM_TARGETS, CONFIG.unobtainium);

    // Nether Ores
    public static ConfiguredFeature<?, ?> ORE_MIDAS_GOLD = OreFeatureHelper.triangleOre(OreFeatureConfig.Rules.NETHERRACK, MythicBlocks.MIDAS_GOLD.getOre().getDefaultState(), CONFIG.midas_gold);
    public static ConfiguredFeature<?, ?> ORE_PALLADIUM = OreFeatureHelper.fixedOre(OreFeatureConfig.Rules.NETHERRACK, MythicBlocks.PALLADIUM.getOre().getDefaultState(), CONFIG.palladium);
    public static ConfiguredFeature<?, ?> ORE_STORMYX = OreFeatureHelper.bottomOffsetOre(STORMYX_TARGETS, CONFIG.stormyx);
    public static ConfiguredFeature<?, ?> ORE_OVERWORLD_NETHER_ORES = OreFeatureHelper.scatteredOre(OVERWORLD_NETHER_ORE_TARGETS, CONFIG.overworld_nether_ores);

    // End Ores
    public static ConfiguredFeature<?, ?> ORE_END_STARRITE = OreFeatureHelper.topOffsetOre(END_STONE_RULE, MythicBlocks.STARRITE.getOreVariant("end_stone").getDefaultState(), CONFIG.starrite.getVariant());

    // Add keys for features
    public static final RegistryKey<ConfiguredFeature<?, ?>> oreAdamantite = RegistryHelper.registerKey("ore_adamantite");
    public static final RegistryKey<ConfiguredFeature<?, ?>> oreAquarium = RegistryHelper.registerKey("ore_aquarium");
    public static final RegistryKey<ConfiguredFeature<?, ?>> oreBanglum = RegistryHelper.registerKey("ore_banglum");
    public static final RegistryKey<ConfiguredFeature<?, ?>> oreCarmot = RegistryHelper.registerKey("ore_carmot");
    public static final RegistryKey<ConfiguredFeature<?, ?>> oreCalciteKyber = RegistryHelper.registerKey("ore_calcite_kyber");
    public static final RegistryKey<ConfiguredFeature<?, ?>> oreKyber = RegistryHelper.registerKey("ore_kyber");
    public static final RegistryKey<ConfiguredFeature<?, ?>> overworldNetherOres = RegistryHelper.registerKey("ore_overworld_nether_ores");
    public static final RegistryKey<ConfiguredFeature<?, ?>> oreManganese = RegistryHelper.registerKey("ore_manganese");
    public static final RegistryKey<ConfiguredFeature<?, ?>> oreMidasGold = RegistryHelper.registerKey("ore_midas_gold");
    public static final RegistryKey<ConfiguredFeature<?, ?>> oreMythril = RegistryHelper.registerKey("ore_mythril");
    public static final RegistryKey<ConfiguredFeature<?, ?>> oreOrichalcum = RegistryHelper.registerKey("ore_orichalcum");
    public static final RegistryKey<ConfiguredFeature<?, ?>> oreOsmium = RegistryHelper.registerKey("ore_osmium");
    public static final RegistryKey<ConfiguredFeature<?, ?>> orePlatinum = RegistryHelper.registerKey("ore_platinum");
    public static final RegistryKey<ConfiguredFeature<?, ?>> orePrometheum = RegistryHelper.registerKey("ore_prometheum");
    public static final RegistryKey<ConfiguredFeature<?, ?>> oreQuadrillum = RegistryHelper.registerKey("ore_quadrillum");
    public static final RegistryKey<ConfiguredFeature<?, ?>> oreRunite = RegistryHelper.registerKey("ore_runite");
    public static final RegistryKey<ConfiguredFeature<?, ?>> oreSilver = RegistryHelper.registerKey("ore_silver");
    public static final RegistryKey<ConfiguredFeature<?, ?>> oreStarrite = RegistryHelper.registerKey("ore_starrite");
    public static final RegistryKey<ConfiguredFeature<?, ?>> oreEndStarrite = RegistryHelper.registerKey("ore_end_starrite");
    public static final RegistryKey<ConfiguredFeature<?, ?>> oreStormyx = RegistryHelper.registerKey("ore_stormyx");
    public static final RegistryKey<ConfiguredFeature<?, ?>> oreTin = RegistryHelper.registerKey("ore_tin");
    public static final RegistryKey<ConfiguredFeature<?, ?>> orePalladium = RegistryHelper.registerKey("ore_palladium");
    public static final RegistryKey<ConfiguredFeature<?, ?>> oreUnobtainium = RegistryHelper.registerKey("ore_unobtainium");
    public static final RegistryKey<ConfiguredFeature<?, ?>> oreVermiculite = RegistryHelper.registerKey("ore_vermiculite");

    public static void init() {
        //Register keys on init
        RegistryHelper.registerFeature(oreAdamantite.getValue(), ORE_ADAMANTITE);
        RegistryHelper.registerFeature(oreAquarium.getValue(), ORE_AQUARIUM);
        RegistryHelper.registerFeature(oreBanglum.getValue(), ORE_BANGLUM);
        RegistryHelper.registerFeature(oreCarmot.getValue(), ORE_CARMOT);
        RegistryHelper.registerFeature(oreCalciteKyber.getValue(), ORE_CALCITE_KYBER);
        RegistryHelper.registerFeature(oreKyber.getValue(), ORE_KYBER);
        RegistryHelper.registerFeature(oreManganese.getValue(), ORE_MANGANESE);
        RegistryHelper.registerFeature(oreMidasGold.getValue(), ORE_MIDAS_GOLD);
        RegistryHelper.registerFeature(oreMythril.getValue(), ORE_MYTHRIL);
        RegistryHelper.registerFeature(oreOrichalcum.getValue(), ORE_ORICHALCUM);
        RegistryHelper.registerFeature(oreOsmium.getValue(), ORE_OSMIUM);
        RegistryHelper.registerFeature(overworldNetherOres.getValue(), ORE_OVERWORLD_NETHER_ORES);
        RegistryHelper.registerFeature(orePlatinum.getValue(), ORE_PLATINUM);
        RegistryHelper.registerFeature(orePrometheum.getValue(), ORE_PROMETHEUM);
        RegistryHelper.registerFeature(oreQuadrillum.getValue(), ORE_QUADRILLUM);
        RegistryHelper.registerFeature(oreRunite.getValue(), ORE_RUNITE);
        RegistryHelper.registerFeature(oreSilver.getValue(), ORE_SILVER);
        RegistryHelper.registerFeature(oreStarrite.getValue(), ORE_STARRITE);
        RegistryHelper.registerFeature(oreEndStarrite.getValue(), ORE_END_STARRITE);
        RegistryHelper.registerFeature(oreStormyx.getValue(), ORE_STORMYX);
        RegistryHelper.registerFeature(oreTin.getValue(), ORE_TIN);
        RegistryHelper.registerFeature(orePalladium.getValue(), ORE_PALLADIUM);
        RegistryHelper.registerFeature(oreUnobtainium.getValue(), ORE_UNOBTAINIUM);
        RegistryHelper.registerFeature(oreVermiculite.getValue(), ORE_VERMICULITE);

        //Overworld Ores
        if (CONFIG.adamantite.enabled) { OreFeatureHelper.ore(oreAdamantite);}
        if (CONFIG.banglum.enabled) { OreFeatureHelper.ore(oreBanglum); }
        if (CONFIG.carmot.enabled) { OreFeatureHelper.ore(oreCarmot); }
        if (CONFIG.kyber.enabled) { OreFeatureHelper.ore(oreKyber); OreFeatureHelper.ore(oreCalciteKyber); }
        if (CONFIG.manganese.enabled) { OreFeatureHelper.ore(oreManganese); }
        if (CONFIG.mythril.enabled) { OreFeatureHelper.ore(oreMythril); }
        if (CONFIG.orichalcum.enabled) { OreFeatureHelper.ore(oreOrichalcum); }
        if (CONFIG.osmium.enabled) { OreFeatureHelper.ore(oreOsmium); }
        if (CONFIG.overworld_nether_ores.enabled) { OreFeatureHelper.ore(overworldNetherOres); }
        if (CONFIG.platinum.enabled) { OreFeatureHelper.ore(orePlatinum); }
        if (CONFIG.quadrillum.enabled) { OreFeatureHelper.ore(oreQuadrillum); }
        if (CONFIG.runite.enabled) { OreFeatureHelper.ore(oreRunite); }
        if (CONFIG.silver.enabled) { OreFeatureHelper.ore(oreSilver); }
        if (CONFIG.starrite.enabled) {
            OreFeatureHelper.ore(oreStarrite);
            OreFeatureHelper.endOre(oreEndStarrite);
            }
        if (CONFIG.tin.enabled) { OreFeatureHelper.ore(oreTin); }
        if (CONFIG.unobtainium.enabled) { OreFeatureHelper.ore(oreUnobtainium); }
        if (CONFIG.vermiculite.enabled) { OreFeatureHelper.ore(oreVermiculite); }

        //Nether Ores
        if (CONFIG.midas_gold.enabled) { OreFeatureHelper.netherOre(oreMidasGold); }
        if (CONFIG.stormyx.enabled) { OreFeatureHelper.netherOre(oreStormyx); }
        if (CONFIG.prometheum.enabled) { OreFeatureHelper.netherOre(orePalladium); }
        if (CONFIG.midas_gold.enabled & CONFIG.stormyx.enabled) {
            OreFeatureHelper.netherOre(overworldNetherOres);}

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
            ), GenerationStep.Feature.UNDERGROUND_ORES, oreAquarium);

            OreFeatureHelper.modBiomeOres("byg", "tropical_islands", oreAquarium);

            OreFeatureHelper.modBiomeOres("terrestria", "caldera", oreAquarium);
            OreFeatureHelper.modBiomeOres("terrestria", "rainbow_rainforest_lake", oreAquarium);
            OreFeatureHelper.modBiomeOres("terrestria", "volcanic_island", oreAquarium);
            OreFeatureHelper.modBiomeOres("terrestria", "volcanic_island_shore", oreAquarium);

            OreFeatureHelper.modBiomeOres("traverse", "wooded_island", oreAquarium);

            OreFeatureHelper.modBiomeOres("lakeside", "warm_lake", oreAquarium);
            OreFeatureHelper.modBiomeOres("lakeside", "jungle_lake", oreAquarium);
            OreFeatureHelper.modBiomeOres("lakeside", "cold_lake", oreAquarium);
            OreFeatureHelper.modBiomeOres("lakeside", "mountain_lake", oreAquarium);

            OreFeatureHelper.modBiomeOres("terralith", "dark_ocean", oreAquarium);
            OreFeatureHelper.modBiomeOres("terralith", "dark_swamp", oreAquarium);
            OreFeatureHelper.modBiomeOres("terralith", "tropical_archipelago", oreAquarium);
        }
        //Jungle only ores
        if (CONFIG.prometheum.enabled) {
            BiomeModifications.addFeature(BiomeSelectors.includeByKey(
                    BiomeKeys.BAMBOO_JUNGLE,
                    BiomeKeys.BAMBOO_JUNGLE_HILLS,
                    BiomeKeys.JUNGLE,
                    BiomeKeys.JUNGLE_EDGE,
                    BiomeKeys.JUNGLE_HILLS,
                    BiomeKeys.MODIFIED_JUNGLE,
                    BiomeKeys.MODIFIED_JUNGLE_EDGE,
                    BiomeKeys.LUSH_CAVES
            ), GenerationStep.Feature.UNDERGROUND_ORES, orePrometheum);

            OreFeatureHelper.modBiomeOres("byg", "araucaria_forest", orePrometheum);
            OreFeatureHelper.modBiomeOres("byg", "cherry_blossom_forest", orePrometheum);
            OreFeatureHelper.modBiomeOres("byg", "ebony_woods", orePrometheum);
            OreFeatureHelper.modBiomeOres("byg", "jacaranda_forest", orePrometheum);
            OreFeatureHelper.modBiomeOres("byg", "redwood_tropics", orePrometheum);
            OreFeatureHelper.modBiomeOres("byg", "tropical_islands", orePrometheum);
            OreFeatureHelper.modBiomeOres("byg", "tropical_fungal_rainforest", orePrometheum);
            OreFeatureHelper.modBiomeOres("byg", "tropical_rainforest", orePrometheum);

            OreFeatureHelper.modBiomeOres("terrestria", "oasis", orePrometheum);
            OreFeatureHelper.modBiomeOres("terrestria", "hemlock_rainforest", orePrometheum);
            OreFeatureHelper.modBiomeOres("terrestria", "hemlock_clearing", orePrometheum);
            OreFeatureHelper.modBiomeOres("terrestria", "lush_redwood_forest", orePrometheum);
            OreFeatureHelper.modBiomeOres("terrestria", "rainbow_rainforest", orePrometheum);
            OreFeatureHelper.modBiomeOres("terrestria", "rainbow_rainforest_lake", orePrometheum);
            OreFeatureHelper.modBiomeOres("terrestria", "volcanic_island", orePrometheum);

            OreFeatureHelper.modBiomeOres("traverse", "lush_swamp", orePrometheum);
            OreFeatureHelper.modBiomeOres("traverse", "mini_jungle", orePrometheum);

            OreFeatureHelper.modBiomeOres("lakeside", "jungle_lake", orePrometheum);
            OreFeatureHelper.modBiomeOres("lakeside", "jungle_island", orePrometheum);

            OreFeatureHelper.modBiomeOres("terralith", "amethyst_rainforest", orePrometheum);
            OreFeatureHelper.modBiomeOres("terralith", "amethyst_rainforest_lowlands", orePrometheum);
            OreFeatureHelper.modBiomeOres("terralith", "coastal_jungle", orePrometheum);
            OreFeatureHelper.modBiomeOres("terralith", "hot_shrubland", orePrometheum);
            OreFeatureHelper.modBiomeOres("terralith", "jungle_mountains", orePrometheum);
            OreFeatureHelper.modBiomeOres("terralith", "lush_canyon", orePrometheum);
            OreFeatureHelper.modBiomeOres("terralith", "mega_jungle", orePrometheum);
            OreFeatureHelper.modBiomeOres("terralith", "sakura_grove", orePrometheum);
            OreFeatureHelper.modBiomeOres("terralith", "tropical_archipelago", orePrometheum);
        }
        //Mountain only ores
        if (CONFIG.manganese.enabled) {
            BiomeModifications.addFeature(BiomeSelectors.includeByKey(
                    BiomeKeys.GRAVELLY_MOUNTAINS,
                    BiomeKeys.MODIFIED_GRAVELLY_MOUNTAINS,
                    BiomeKeys.MOUNTAINS,
                    BiomeKeys.MOUNTAIN_EDGE,
                    BiomeKeys.SHATTERED_SAVANNA,
                    BiomeKeys.SHATTERED_SAVANNA_PLATEAU,
                    BiomeKeys.SNOWY_MOUNTAINS,
                    BiomeKeys.SNOWY_TAIGA_MOUNTAINS,
                    BiomeKeys.TAIGA_MOUNTAINS
            ), GenerationStep.Feature.UNDERGROUND_ORES, oreManganese);

            OreFeatureHelper.modBiomeOres("byg", "alpine_foothills", oreManganese);
            OreFeatureHelper.modBiomeOres("byg", "alps", oreManganese);
            OreFeatureHelper.modBiomeOres("byg", "black_forest_hills", oreManganese);
            OreFeatureHelper.modBiomeOres("byg", "blue_taiga_hills", oreManganese);
            OreFeatureHelper.modBiomeOres("byg", "bluff_peaks", oreManganese);
            OreFeatureHelper.modBiomeOres("byg", "bluff_steeps", oreManganese);
            OreFeatureHelper.modBiomeOres("byg", "boreal_forest_hills", oreManganese);
            OreFeatureHelper.modBiomeOres("byg", "cika_mountains", oreManganese);
            OreFeatureHelper.modBiomeOres("byg", "coniferous_forest_hills", oreManganese);
            OreFeatureHelper.modBiomeOres("byg", "dover_mountains", oreManganese);
            OreFeatureHelper.modBiomeOres("byg", "ebony_hills", oreManganese);
            OreFeatureHelper.modBiomeOres("byg", "enchatned_forest_hills", oreManganese);
            OreFeatureHelper.modBiomeOres("byg", "evergreen_hills", oreManganese);
            OreFeatureHelper.modBiomeOres("byg", "grassland_plateau", oreManganese);
            OreFeatureHelper.modBiomeOres("byg", "guiana_clearing", oreManganese);
            OreFeatureHelper.modBiomeOres("byg", "guiana_shield", oreManganese);
            OreFeatureHelper.modBiomeOres("byg", "jacaranda_forest_hills", oreManganese);
            OreFeatureHelper.modBiomeOres("byg", "redwood_mountians", oreManganese);
            OreFeatureHelper.modBiomeOres("byg", "skyris_highlands", oreManganese);
            OreFeatureHelper.modBiomeOres("byg", "wooded_grassland_plateau", oreManganese);

            OreFeatureHelper.modBiomeOres("terrestria", "caldera_foothills", oreManganese);
            OreFeatureHelper.modBiomeOres("terrestria", "caldera_ridge", oreManganese);
            OreFeatureHelper.modBiomeOres("terrestria", "outback_uluru", oreManganese);
            OreFeatureHelper.modBiomeOres("terrestria", "rainbow_rainforest", oreManganese);
            OreFeatureHelper.modBiomeOres("terrestria", "rainbow_rainforest_mountains", oreManganese);

            OreFeatureHelper.modBiomeOres("traverse", "arid_highlands", oreManganese);
            OreFeatureHelper.modBiomeOres("traverse", "cliffs", oreManganese);
            OreFeatureHelper.modBiomeOres("traverse", "rolling_hills", oreManganese);

            OreFeatureHelper.modBiomeOres("lakeside", "mountain_lake", oreManganese);

            OreFeatureHelper.modBiomeOres("terralith", "emerald_peaks", oreManganese);
            OreFeatureHelper.modBiomeOres("terralith", "haze_mountain", oreManganese);
            OreFeatureHelper.modBiomeOres("terralith", "jungle_mountains", oreManganese);
            OreFeatureHelper.modBiomeOres("terralith", "red_mountains", oreManganese);
            OreFeatureHelper.modBiomeOres("terralith", "salt_mountains", oreManganese);
            OreFeatureHelper.modBiomeOres("terralith", "sandstone_valley", oreManganese);
            OreFeatureHelper.modBiomeOres("terralith", "sandstone_valley_r", oreManganese);
            OreFeatureHelper.modBiomeOres("terralith", "savanna_heights", oreManganese);
            OreFeatureHelper.modBiomeOres("terralith", "scarlet_mountains", oreManganese);
            OreFeatureHelper.modBiomeOres("terralith", "siberian_taiga", oreManganese);
            OreFeatureHelper.modBiomeOres("terralith", "stardust_valley", oreManganese);
            OreFeatureHelper.modBiomeOres("terralith", "tropical_volcano", oreManganese);
            OreFeatureHelper.modBiomeOres("terralith", "yellowstone", oreManganese);
            OreFeatureHelper.modBiomeOres("terralith", "yosemite_cliffs", oreManganese);
        }

    }

}