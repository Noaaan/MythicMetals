package nourl.mythicmetals.world;

import com.google.common.collect.ImmutableList;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.structure.rule.BlockMatchRuleTest;
import net.minecraft.structure.rule.RuleTest;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.decorator.Decorator;
import net.minecraft.world.gen.decorator.RangeDecoratorConfig;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.heightprovider.UniformHeightProvider;
import nourl.mythicmetals.MythicMetals;
import nourl.mythicmetals.blocks.MythicBlocks;
import nourl.mythicmetals.config.DefaultConfig;

@SuppressWarnings("deprecation")
public class MythicOreFeatures {
    // Defines the config that applies to the ore features
    public static final DefaultConfig.MythicOreConfig CONFIG = MythicMetals.CONFIG.mythores;

    // Defines new RuleTest(s), which checks what blocks an ore can spawn in
    public static final RuleTest CALCITE_RULE = new BlockMatchRuleTest(Blocks.CALCITE);
    public static final RuleTest DEEPSLATE_RULE = OreFeatureConfig.Rules.DEEPSLATE_ORE_REPLACEABLES;
    public static final RuleTest SMOOTH_BASALT_RULE = new BlockMatchRuleTest(Blocks.SMOOTH_BASALT);
    public static final RuleTest STONE_RULE = OreFeatureConfig.Rules.STONE_ORE_REPLACEABLES;
    public static final RuleTest TUFF_RULE = new BlockMatchRuleTest(Blocks.TUFF);

    // Defines a list of targets, which can check for multiple blocks and dynamically replace them when generating ore
    public static final ImmutableList<OreFeatureConfig.Target> ADAMANTITE_TARGETS = ImmutableList.of(OreFeatureConfig.createTarget(STONE_RULE, MythicBlocks.ADAMANTITE_ORE.getDefaultState()), OreFeatureConfig.createTarget(STONE_RULE, MythicBlocks.DEEPSLATE_ADAMANTITE_ORE.getDefaultState()));
    public static final ImmutableList<OreFeatureConfig.Target> MYTHRIL_TARGETS = ImmutableList.of(OreFeatureConfig.createTarget(STONE_RULE, MythicBlocks.MYTHRIL_ORE.getDefaultState()), OreFeatureConfig.createTarget(DEEPSLATE_RULE, MythicBlocks.DEEPSLATE_MYTHRIL_ORE.getDefaultState()));
    public static final ImmutableList<OreFeatureConfig.Target> ORICHALCUM_TARGETS = ImmutableList.of(OreFeatureConfig.createTarget(TUFF_RULE, MythicBlocks.TUFF_ORICHALCUM_ORE.getDefaultState()), OreFeatureConfig.createTarget(SMOOTH_BASALT_RULE, MythicBlocks.SMOOTH_BASALT_ORICHALCUM_ORE.getDefaultState()), OreFeatureConfig.createTarget(DEEPSLATE_RULE, MythicBlocks.DEEPSLATE_ORICHALCUM_ORE.getDefaultState()));
    public static final ImmutableList<OreFeatureConfig.Target> PROMETHEUM_TARGETS = ImmutableList.of(OreFeatureConfig.createTarget(STONE_RULE, MythicBlocks.PROMETHEUM_ORE.getDefaultState()), OreFeatureConfig.createTarget(DEEPSLATE_RULE, MythicBlocks.DEEPSLATE_PROMETHEUM_ORE.getDefaultState()));
    public static final ImmutableList<OreFeatureConfig.Target> STARRITE_TARGETS = ImmutableList.of(OreFeatureConfig.createTarget(STONE_RULE, MythicBlocks.STARRITE_ORE.getDefaultState()), OreFeatureConfig.createTarget(CALCITE_RULE, MythicBlocks.CALCITE_STARRITE_ORE.getDefaultState()));
    public static final ImmutableList<OreFeatureConfig.Target> STORMYX_TARGETS = ImmutableList.of(OreFeatureConfig.createTarget(OreFeatureConfig.Rules.NETHERRACK, MythicBlocks.STORMYX_ORE.getDefaultState()), OreFeatureConfig.createTarget(OreFeatureConfig.Rules.BASE_STONE_NETHER, MythicBlocks.BLACKSTONE_STORMYX_ORE.getDefaultState()));
    public static final ImmutableList<OreFeatureConfig.Target> UNOBTAINIUM_TARGETS = ImmutableList.of(OreFeatureConfig.createTarget(STONE_RULE, MythicBlocks.UNOBTAINIUM_ORE.getDefaultState()), OreFeatureConfig.createTarget(DEEPSLATE_RULE, MythicBlocks.DEEPSLATE_UNOBTAINIUM_ORE.getDefaultState()));

    public static ConfiguredFeature<?, ?> ORE_AETHERIUM = topOffsetOre(STONE_RULE, MythicBlocks.AETHERIUM_ORE.getDefaultState(), CONFIG.oreAetheriumVeinSize, CONFIG.oreAetheriumMinHeight, CONFIG.oreAetheriumTopOffset, CONFIG.oreAetheriumPerChunk, 0.25F);
    public static ConfiguredFeature<?, ?> ORE_AQUARIUM = fixedOre(STONE_RULE, MythicBlocks.AQUARIUM_ORE.getDefaultState(), CONFIG.oreAquariumVeinSize,CONFIG.oreAquariumMinHeight, CONFIG.oreAquariumMaxHeight,CONFIG.oreAquariumPerChunk, 0.0F);
    public static ConfiguredFeature<?, ?> ORE_BANGLUM = fixedOre(STONE_RULE, MythicBlocks.BANGLUM_ORE.getDefaultState(), CONFIG.oreBanglumVeinSize,CONFIG.oreBanglumMinHeight, CONFIG.oreBanglumMaxHeight,CONFIG.oreBanglumPerChunk, 0.2F);
    public static ConfiguredFeature<?, ?> ORE_CARMOT = fixedOre(STONE_RULE, MythicBlocks.CARMOT_ORE.getDefaultState(), CONFIG.oreCarmotVeinSize,CONFIG.oreCarmotMinHeight, CONFIG.oreCarmotMaxHeight,CONFIG.oreCarmotPerChunk, 0.0F);
    public static ConfiguredFeature<?, ?> ORE_KYBER = fixedOre(STONE_RULE, MythicBlocks.KYBER_ORE.getDefaultState(), CONFIG.oreKyberVeinSize, CONFIG.oreKyberMinHeight, CONFIG.oreKyberMaxHeight,CONFIG.oreKyberPerChunk, 0.6F);
    public static ConfiguredFeature<?, ?> ORE_MANGANESE = fixedOre(STONE_RULE, MythicBlocks.MANGANESE_ORE.getDefaultState(), CONFIG.oreManganeseVeinSize,CONFIG.oreManganeseMinHeight, CONFIG.oreManganeseMaxHeight,CONFIG.oreManganesePerChunk, 0.15F);
    public static ConfiguredFeature<?, ?> ORE_OSMIUM = fixedOre(STONE_RULE, MythicBlocks.OSMIUM_ORE.getDefaultState(), CONFIG.oreOsmiumVeinSize,CONFIG.oreOsmiumMinHeight, CONFIG.oreOsmiumMaxHeight,CONFIG.oreOsmiumPerChunk, 0.2F);
    public static ConfiguredFeature<?, ?> ORE_PLATINUM = triangleOre(STONE_RULE, MythicBlocks.PLATINUM_ORE.getDefaultState(), CONFIG.orePlatinumVeinSize, CONFIG.orePlatinumMinHeight, CONFIG.orePlatinumMaxHeight, CONFIG.orePlatinumPerChunk, 0.0F);
    public static ConfiguredFeature<?, ?> ORE_QUADRILLUM = fixedOre(STONE_RULE, MythicBlocks.QUADRILLUM_ORE.getDefaultState(), CONFIG.oreQuadrillumVeinSize,CONFIG.oreQuadrillumMinHeight, CONFIG.oreQuadrillumMaxHeight,CONFIG.oreQuadrillumPerChunk, 0.25F);
    public static ConfiguredFeature<?, ?> ORE_RUNITE = triangleOre(STONE_RULE, MythicBlocks.RUNITE_ORE.getDefaultState(), CONFIG.oreRuniteVeinSize,CONFIG.oreRuniteMinHeight, CONFIG.oreRuniteMaxHeight,CONFIG.oreRunitePerChunk, 0.1F);
    public static ConfiguredFeature<?, ?> ORE_SILVER = fixedOre(STONE_RULE, MythicBlocks.SILVER_ORE.getDefaultState(), CONFIG.oreSilverVeinSize,CONFIG.oreSilverMinHeight, CONFIG.oreSilverMaxHeight,CONFIG.oreSilverPerChunk, 0.2F);
    public static ConfiguredFeature<?, ?> ORE_TIN = fixedOre(STONE_RULE, MythicBlocks.TIN_ORE.getDefaultState(), CONFIG.oreTinVeinSize,CONFIG.oreTinMinHeight, CONFIG.oreTinMaxHeight,CONFIG.oreTinPerChunk, 0.3F);
    public static ConfiguredFeature<?, ?> ORE_VERMICULITE = fixedOre(STONE_RULE, MythicBlocks.VERMICULITE_ORE.getDefaultState(), CONFIG.oreVermiculiteVeinSize,CONFIG.oreVermiculiteMinHeight, CONFIG.oreVermiculiteMaxHeight,CONFIG.oreVermiculitePerChunk, 0.3F);
    // Ores below zero - Reaches Deep Dark
    public static ConfiguredFeature<?, ?> ORE_ADAMANTITE = triangleOre(ADAMANTITE_TARGETS, CONFIG.oreAdamantiteVeinSize, CONFIG.oreAdamantiteMinHeight, CONFIG.oreAdamantiteMaxHeight, CONFIG.oreAdamantitePerChunk, 0.2F);
    public static ConfiguredFeature<?, ?> ORE_CALCITE_KYBER = bottomOffsetOre(CALCITE_RULE, MythicBlocks.CALCITE_KYBER_ORE.getDefaultState(), 18, 4, 70, 40, 0.1F);
    public static ConfiguredFeature<?, ?> ORE_MYTHRIL = triangleOre(MYTHRIL_TARGETS, CONFIG.oreMythrilVeinSize, CONFIG.oreMythrilMinHeight, CONFIG.oreMythrilMaxHeight, CONFIG.oreMythrilPerChunk, 0.2F);
    public static ConfiguredFeature<?, ?> ORE_ORICHALCUM = bottomOffsetOre(ORICHALCUM_TARGETS, CONFIG.oreOrichalcumVeinSize, CONFIG.oreOrichalcumBottomOffset, CONFIG.oreOrichalcumMaxHeight, CONFIG.oreOrichalcumPerChunk, 0.15F);
    public static ConfiguredFeature<?, ?> ORE_PROMETHEUM = bottomOffsetOre(PROMETHEUM_TARGETS, CONFIG.orePrometheumVeinSize, CONFIG.orePrometheumBottomOffset, CONFIG.orePrometheumMaxHeight, CONFIG.orePrometheumPerChunk, 0.2F);
    public static ConfiguredFeature<?, ?> ORE_STARRITE = bottomOffsetOre(STARRITE_TARGETS, CONFIG.oreStarriteVeinSize,CONFIG.oreStarriteBottomOffset, CONFIG.oreStarriteMaxHeight,CONFIG.oreStarritePerChunk, 0.5F);
    public static ConfiguredFeature<?, ?> ORE_UNOBTAINIUM = Feature.SCATTERED_ORE.configure(new OreFeatureConfig(UNOBTAINIUM_TARGETS, CONFIG.oreUnobtainiumVeinSize, CONFIG.oreUnobtainiumDiscardChance)).triangleRange(YOffset.aboveBottom(CONFIG.oreUnobtainiumMinHeight), YOffset.fixed(CONFIG.oreUnobtainiumMaxHeight)).spreadHorizontally().applyChance(2);
    // Nether Ores
    public static ConfiguredFeature<?, ?> ORE_MIDAS_GOLD = triangleOre(OreFeatureConfig.Rules.NETHERRACK, MythicBlocks.MIDAS_GOLD_ORE.getDefaultState(), CONFIG.oreMidasgoldVeinSize,CONFIG.oreMidasgoldMinHeight, CONFIG.oreMidasgoldMaxHeight,CONFIG.oreMidasgoldPerChunk, 0.1F);
    public static ConfiguredFeature<?, ?> ORE_PALLADIUM = fixedOre(OreFeatureConfig.Rules.NETHERRACK, MythicBlocks.PALLADIUM_ORE.getDefaultState(), CONFIG.orePalladiumVeinSize,CONFIG.orePalladiumMinHeight, CONFIG.orePalladiumMaxHeight,CONFIG.orePalladiumPerChunk, 0.1F);
    public static ConfiguredFeature<?, ?> ORE_STORMYX = bottomOffsetOre(STORMYX_TARGETS, CONFIG.oreStormyxVeinSize, CONFIG.oreStormyxMinHeight, CONFIG.oreStormyxMaxHeight, CONFIG.oreStormyxPerChunk, 0.1F);

    // Add keys for features
    public static final RegistryKey<ConfiguredFeature<?, ?>> oreAdamantite = RegistryKey.of(Registry.CONFIGURED_FEATURE_KEY, new Identifier(MythicMetals.MOD_ID, "ore_adamantite"));
    public static final RegistryKey<ConfiguredFeature<?, ?>> oreAetherium = RegistryKey.of(Registry.CONFIGURED_FEATURE_KEY, new Identifier(MythicMetals.MOD_ID, "ore_aetherium"));
    public static final RegistryKey<ConfiguredFeature<?, ?>> oreAquarium = RegistryKey.of(Registry.CONFIGURED_FEATURE_KEY, new Identifier(MythicMetals.MOD_ID, "ore_aquarium"));
    public static final RegistryKey<ConfiguredFeature<?, ?>> oreBanglum = RegistryKey.of(Registry.CONFIGURED_FEATURE_KEY, new Identifier(MythicMetals.MOD_ID, "ore_banglum"));
    public static final RegistryKey<ConfiguredFeature<?, ?>> oreCarmot = RegistryKey.of(Registry.CONFIGURED_FEATURE_KEY, new Identifier(MythicMetals.MOD_ID, "ore_carmot"));
    public static final RegistryKey<ConfiguredFeature<?, ?>> oreCalciteKyber = RegistryKey.of(Registry.CONFIGURED_FEATURE_KEY, new Identifier(MythicMetals.MOD_ID, "ore_calcite_kyber"));
    public static final RegistryKey<ConfiguredFeature<?, ?>> oreKyber = RegistryKey.of(Registry.CONFIGURED_FEATURE_KEY, new Identifier(MythicMetals.MOD_ID, "ore_kyber"));
    public static final RegistryKey<ConfiguredFeature<?, ?>> oreManganese = RegistryKey.of(Registry.CONFIGURED_FEATURE_KEY, new Identifier(MythicMetals.MOD_ID, "ore_manganese"));
    public static final RegistryKey<ConfiguredFeature<?, ?>> oreMidasGold = RegistryKey.of(Registry.CONFIGURED_FEATURE_KEY, new Identifier(MythicMetals.MOD_ID, "ore_midas_gold"));
    public static final RegistryKey<ConfiguredFeature<?, ?>> oreMythril = RegistryKey.of(Registry.CONFIGURED_FEATURE_KEY, new Identifier(MythicMetals.MOD_ID, "ore_mythril"));
    public static final RegistryKey<ConfiguredFeature<?, ?>> oreOrichalcum = RegistryKey.of(Registry.CONFIGURED_FEATURE_KEY, new Identifier(MythicMetals.MOD_ID, "ore_orichalcum"));
    public static final RegistryKey<ConfiguredFeature<?, ?>> oreOsmium = RegistryKey.of(Registry.CONFIGURED_FEATURE_KEY, new Identifier(MythicMetals.MOD_ID, "ore_osmium"));
    public static final RegistryKey<ConfiguredFeature<?, ?>> orePlatinum = RegistryKey.of(Registry.CONFIGURED_FEATURE_KEY, new Identifier(MythicMetals.MOD_ID, "ore_platinum"));
    public static final RegistryKey<ConfiguredFeature<?, ?>> orePrometheum = RegistryKey.of(Registry.CONFIGURED_FEATURE_KEY, new Identifier(MythicMetals.MOD_ID, "ore_prometheum"));
    public static final RegistryKey<ConfiguredFeature<?, ?>> oreQuadrillum = RegistryKey.of(Registry.CONFIGURED_FEATURE_KEY, new Identifier(MythicMetals.MOD_ID, "ore_quadrillum"));
    public static final RegistryKey<ConfiguredFeature<?, ?>> oreRunite = RegistryKey.of(Registry.CONFIGURED_FEATURE_KEY, new Identifier(MythicMetals.MOD_ID, "ore_runite"));
    public static final RegistryKey<ConfiguredFeature<?, ?>> oreSilver = RegistryKey.of(Registry.CONFIGURED_FEATURE_KEY, new Identifier(MythicMetals.MOD_ID, "ore_silver"));
    public static final RegistryKey<ConfiguredFeature<?, ?>> oreStarrite = RegistryKey.of(Registry.CONFIGURED_FEATURE_KEY, new Identifier(MythicMetals.MOD_ID, "ore_starrite"));
    public static final RegistryKey<ConfiguredFeature<?, ?>> oreStormyx = RegistryKey.of(Registry.CONFIGURED_FEATURE_KEY, new Identifier(MythicMetals.MOD_ID, "ore_stormyx"));
    public static final RegistryKey<ConfiguredFeature<?, ?>> oreTin = RegistryKey.of(Registry.CONFIGURED_FEATURE_KEY, new Identifier(MythicMetals.MOD_ID, "ore_tin"));
    public static final RegistryKey<ConfiguredFeature<?, ?>> orePalladium = RegistryKey.of(Registry.CONFIGURED_FEATURE_KEY, new Identifier(MythicMetals.MOD_ID, "ore_palladium"));
    public static final RegistryKey<ConfiguredFeature<?, ?>> oreUnobtainium = RegistryKey.of(Registry.CONFIGURED_FEATURE_KEY, new Identifier(MythicMetals.MOD_ID, "ore_unobtainium"));
    public static final RegistryKey<ConfiguredFeature<?, ?>> oreVermiculite = RegistryKey.of(Registry.CONFIGURED_FEATURE_KEY, new Identifier(MythicMetals.MOD_ID, "ore_vermiculite"));

    public static void init() {
        //Register keys on init
        registerFeature(oreAdamantite.getValue(), ORE_ADAMANTITE);
        registerFeature(oreAetherium.getValue(), ORE_AETHERIUM);
        registerFeature(oreAquarium.getValue(), ORE_AQUARIUM);
        registerFeature(oreBanglum.getValue(), ORE_BANGLUM);
        registerFeature(oreCarmot.getValue(), ORE_CARMOT);
        registerFeature(oreCalciteKyber.getValue(), ORE_CALCITE_KYBER);
        registerFeature(oreKyber.getValue(), ORE_KYBER);
        registerFeature(oreManganese.getValue(), ORE_MANGANESE);
        registerFeature(oreMidasGold.getValue(), ORE_MIDAS_GOLD);
        registerFeature(oreMythril.getValue(), ORE_MYTHRIL);
        registerFeature(oreOrichalcum.getValue(), ORE_ORICHALCUM);
        registerFeature(oreOsmium.getValue(), ORE_OSMIUM);
        registerFeature(orePlatinum.getValue(), ORE_PLATINUM);
        registerFeature(orePrometheum.getValue(), ORE_PROMETHEUM);
        registerFeature(oreQuadrillum.getValue(), ORE_QUADRILLUM);
        registerFeature(oreRunite.getValue(), ORE_RUNITE);
        registerFeature(oreSilver.getValue(), ORE_SILVER);
        registerFeature(oreStarrite.getValue(), ORE_STARRITE);
        registerFeature(oreStormyx.getValue(), ORE_STORMYX);
        registerFeature(oreTin.getValue(), ORE_TIN);
        registerFeature(orePalladium.getValue(), ORE_PALLADIUM);
        registerFeature(oreUnobtainium.getValue(), ORE_UNOBTAINIUM);
        registerFeature(oreVermiculite.getValue(), ORE_VERMICULITE);
    }

    @SuppressWarnings("deprecation")
    public static void generate() {
        //Overworld Ores
        if (CONFIG.oreAdamantiteGeneration) { AddUOre(oreAdamantite);}
        if (CONFIG.oreAetheriumGeneration) { AddUOre(oreAetherium); }
        if (CONFIG.oreBanglumGeneration) { AddUOre(oreBanglum); }
        if (CONFIG.oreCarmotGeneration) { AddUOre(oreCarmot); }
        if (CONFIG.oreKyberGeneration) { AddUOre(oreKyber); AddUOre(oreCalciteKyber); }
        if (CONFIG.oreManganeseGeneration) { AddUOre(oreManganese); }
        if (CONFIG.oreMythrilGeneration) { AddUOre(oreMythril); }
        if (CONFIG.oreOrichalcumGeneration) { AddUOre(oreOrichalcum); }
        if (CONFIG.oreOsmiumGeneration) { AddUOre(oreOsmium); }
        if (CONFIG.orePlatinumGeneration) { AddUOre(orePlatinum); }
        if (CONFIG.oreQuadrillumGeneration) { AddUOre(oreQuadrillum); }
        if (CONFIG.oreRuniteGeneration) { AddUOre(oreRunite); }
        if (CONFIG.oreSilverGeneration) { AddUOre(oreSilver); }
        if (CONFIG.oreStarriteGeneration) { AddUOre(oreStarrite); }
        if (CONFIG.oreTinGeneration) { AddUOre(oreTin); }
        if (CONFIG.oreUnobtainiumGeneration) { AddUOre(oreUnobtainium); }
        if (CONFIG.oreVermiculiteGeneration) { AddUOre(oreVermiculite); }

        //Nether Ores
        if (CONFIG.oreMidasGoldGeneration) { AddUDecoration(oreMidasGold); }
        if (CONFIG.oreStormyxGeneration) { AddUDecoration(oreStormyx); }
        if (CONFIG.orePalladiumGeneration) { AddUDecoration(orePalladium); }

        //Ocean only ores
        if (CONFIG.oreAquariumGeneration) {
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

            addUOreToModBiomes("byg","tropical_islands", oreAquarium);

            addUOreToModBiomes("terrestria","caldera", oreAquarium);
            addUOreToModBiomes("terrestria","rainbow_rainforest_lake", oreAquarium);
            addUOreToModBiomes("terrestria","volcanic_island", oreAquarium);
            addUOreToModBiomes("terrestria","volcanic_island_shore", oreAquarium);

            addUOreToModBiomes("traverse","wooded_island", oreAquarium);
        }
        //Jungle only ores
        if (CONFIG.orePrometheumGeneration) {
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

            addUOreToModBiomes("byg","araucaria_forest", orePrometheum);
            addUOreToModBiomes("byg","cherry_blossom_forest", orePrometheum);
            addUOreToModBiomes("byg","ebony_woods", orePrometheum);
            addUOreToModBiomes("byg","jacaranda_forest", orePrometheum);
            addUOreToModBiomes("byg","redwood_tropics", orePrometheum);
            addUOreToModBiomes("byg","tropical_islands", orePrometheum);
            addUOreToModBiomes("byg","tropical_fungal_rainforest", orePrometheum);
            addUOreToModBiomes("byg","tropical_rainforest", orePrometheum);

            addUOreToModBiomes("terrestria","oasis", orePrometheum);
            addUOreToModBiomes("terrestria","hemlock_rainforest", orePrometheum);
            addUOreToModBiomes("terrestria","hemlock_clearing", orePrometheum);
            addUOreToModBiomes("terrestria","lush_redwood_forest", orePrometheum);
            addUOreToModBiomes("terrestria","rainbow_rainforest", orePrometheum);
            addUOreToModBiomes("terrestria","rainbow_rainforest_lake", orePrometheum);
            addUOreToModBiomes("terrestria","volcanic_island", orePrometheum);

            addUOreToModBiomes("traverse","lush_swamp", orePrometheum);
            addUOreToModBiomes("traverse","mini_jungle", orePrometheum);
        }
        //Mountain only ores
        if (CONFIG.oreAetheriumGeneration) {
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
            ), GenerationStep.Feature.UNDERGROUND_ORES, oreAetherium);

            addUOreToModBiomes("byg","alpine_foothills", oreAetherium);
            addUOreToModBiomes("byg","alps", oreAetherium);
            addUOreToModBiomes("byg","black_forest_hills", oreAetherium);
            addUOreToModBiomes("byg","blue_taiga_hills", oreAetherium);
            addUOreToModBiomes("byg","bluff_peaks", oreAetherium);
            addUOreToModBiomes("byg","bluff_steeps", oreAetherium);
            addUOreToModBiomes("byg","boreal_forest_hills", oreAetherium);
            addUOreToModBiomes("byg","cika_mountains", oreAetherium);
            addUOreToModBiomes("byg","coniferous_forest_hills", oreAetherium);
            addUOreToModBiomes("byg","dover_mountains", oreAetherium);
            addUOreToModBiomes("byg","ebony_hills", oreAetherium);
            addUOreToModBiomes("byg","enchatned_forest_hills", oreAetherium);
            addUOreToModBiomes("byg","evergreen_hills", oreAetherium);
            addUOreToModBiomes("byg","grassland_plateau", oreAetherium);
            addUOreToModBiomes("byg","guiana_clearing", oreAetherium);
            addUOreToModBiomes("byg","guiana_shield", oreAetherium);
            addUOreToModBiomes("byg","jacaranda_forest_hills", oreAetherium);
            addUOreToModBiomes("byg","redwood_mountians", oreAetherium);
            addUOreToModBiomes("byg","skyris_highlands", oreAetherium);
            addUOreToModBiomes("byg","wooded_grassland_plateau", oreAetherium);

            addUOreToModBiomes("terrestria","caldera_foothills", oreAetherium);
            addUOreToModBiomes("terrestria","caldera_ridge", oreAetherium);
            addUOreToModBiomes("terrestria","outback_uluru", oreAetherium);
            addUOreToModBiomes("terrestria","rainbow_rainforest", oreAetherium);
            addUOreToModBiomes("terrestria","rainbow_rainforest_mountains", oreAetherium);

            addUOreToModBiomes("traverse","arid_highlands", oreAetherium);
            addUOreToModBiomes("traverse","cliffs", oreAetherium);
            addUOreToModBiomes("traverse","rolling_hills", oreAetherium);
        }

    }

    public static void AddUOre(RegistryKey<ConfiguredFeature<?, ?>> ore) {
        BiomeModifications.addFeature(BiomeSelectors.all(), GenerationStep.Feature.UNDERGROUND_ORES, ore);
    }

    public static void AddUDecoration(RegistryKey<ConfiguredFeature<?, ?>> ore) {
        BiomeModifications.addFeature(BiomeSelectors.all(), GenerationStep.Feature.UNDERGROUND_DECORATION, ore);
    }

    public static void addUOreToModBiomes(String modId, String path, RegistryKey<ConfiguredFeature<?, ?>> ore) {
        if (FabricLoader.getInstance().isModLoaded(modId)) {
            BiomeModifications.addFeature(BiomeSelectors.includeByKey(RegistryKey.of(Registry.BIOME_KEY, new Identifier(modId, path))), GenerationStep.Feature.UNDERGROUND_ORES, ore);
        }
    }
    public static ConfiguredFeature<?, ?> fixedOre(RuleTest rule, BlockState defaultState, int veinSize, int minHeight, int maxHeight, int spawnRate, float discardChance) {
        return Feature.ORE.configure(new OreFeatureConfig(rule, defaultState, veinSize, discardChance)).decorate(Decorator.RANGE.configure(new RangeDecoratorConfig(UniformHeightProvider.create(YOffset.fixed(minHeight), YOffset.fixed(maxHeight))))).spreadHorizontally().repeat(spawnRate);
    }
    public static ConfiguredFeature<?, ?> bottomOffsetOre(RuleTest rule, BlockState state, int veinSize, int bottomOffset, int maxHeight, int spawnRate, float discardChance) {
        return Feature.ORE.configure(new OreFeatureConfig(rule, state, veinSize, discardChance)).decorate(Decorator.RANGE.configure(new RangeDecoratorConfig(UniformHeightProvider.create(YOffset.aboveBottom(bottomOffset), YOffset.fixed(maxHeight))))).spreadHorizontally().repeat(spawnRate);
    }
    public static ConfiguredFeature<?, ?> bottomOffsetOre(ImmutableList<OreFeatureConfig.Target> targets, int veinSize, int bottomOffset, int maxHeight, int spawnRate, float discardChance) {
        return Feature.ORE.configure(new OreFeatureConfig(targets, veinSize, discardChance)).decorate(Decorator.RANGE.configure(new RangeDecoratorConfig(UniformHeightProvider.create(YOffset.aboveBottom(bottomOffset), YOffset.fixed(maxHeight))))).spreadHorizontally().repeat(spawnRate);
    }
    public static ConfiguredFeature<?, ?> topOffsetOre(RuleTest rule, BlockState state, int veinSize, int minHeight, int topOffset, int spawnRate, float discardChance) {
        return Feature.ORE.configure(new OreFeatureConfig(rule, state, veinSize, discardChance)).decorate(Decorator.RANGE.configure(new RangeDecoratorConfig(UniformHeightProvider.create(YOffset.fixed(minHeight), YOffset.belowTop(topOffset))))).spreadHorizontally().repeat(spawnRate);
    }
    public static ConfiguredFeature<?, ?> triangleOre(RuleTest rule, BlockState defaultState, int veinSize, int minHeight, int maxHeight, int spawnRate, float discardChance) {
        return Feature.ORE.configure(new OreFeatureConfig(rule, defaultState, veinSize, discardChance)).triangleRange(YOffset.fixed(minHeight), YOffset.fixed(maxHeight)).spreadHorizontally().repeat(spawnRate);
    }
    public static ConfiguredFeature<?, ?> triangleOre(ImmutableList<OreFeatureConfig.Target> targets, int veinSize, int minHeight, int maxHeight, int spawnRate, float discardChance) {
        return Feature.ORE.configure(new OreFeatureConfig(targets, veinSize, discardChance)).triangleRange(YOffset.fixed(minHeight), YOffset.fixed(maxHeight)).spreadHorizontally().repeat(spawnRate);
    }

    public static void registerFeature(Identifier identifier, ConfiguredFeature<?,?> feature) {
        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, identifier, feature);
    }
}