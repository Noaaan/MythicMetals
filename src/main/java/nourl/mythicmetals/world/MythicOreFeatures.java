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
import nourl.mythicmetals.blocks.MythicOres;
import nourl.mythicmetals.config.MythicConfig;

@SuppressWarnings("deprecation")
public class MythicOreFeatures {
    // Defines the config that applies to the ore features
    public static final MythicConfig.MythicOreConfig CONFIG = MythicMetals.CONFIG.mythores;

    // Defines new RuleTest(s), which checks what blocks an ore can spawn in
    public static final RuleTest CALCITE_RULE = new BlockMatchRuleTest(Blocks.CALCITE);
    public static final RuleTest TUFF_RULE = new BlockMatchRuleTest(Blocks.TUFF);
    // Defines a target, which can check for multiple blocks and dynamically replace it
    public static final ImmutableList<OreFeatureConfig.Target> STORMYX_TARGETS = ImmutableList.of(OreFeatureConfig.createTarget(OreFeatureConfig.Rules.NETHERRACK, MythicOres.STORMYX_ORE.getDefaultState()), OreFeatureConfig.createTarget(OreFeatureConfig.Rules.BASE_STONE_NETHER, MythicOres.BLACKSTONE_STORMYX_ORE.getDefaultState()));

    // Template for a new ore
    public static ConfiguredFeature<?, ?> ORE_ADAMANTITE = Feature.ORE.configure(new OreFeatureConfig(OreFeatureConfig.Rules.STONE_ORE_REPLACEABLES, MythicOres.ADAMANTITE_ORE.getDefaultState(),
            CONFIG.oreAdamantiteVeinSize, 0.4F)) //Vein Size and discard chance (chance that ore is deleted if exposed to air)
            .decorate(Decorator.RANGE.configure(new RangeDecoratorConfig(UniformHeightProvider.create(YOffset.fixed(CONFIG.oreAdamantiteMinHeight), // Bottom offset, minimum height
            YOffset.fixed(CONFIG.oreAdamantiteMaxHeight))))) // Top offset, max height
            .spreadHorizontally().repeat(CONFIG.oreAdamantitePerChunk); // Number of veins per chunk
    public static ConfiguredFeature<?, ?> ORE_AETHERIUM = configureOre(OreFeatureConfig.Rules.STONE_ORE_REPLACEABLES, MythicOres.AETHERIUM_ORE.getDefaultState(), CONFIG.oreAetheriumVeinSize, CONFIG.oreAetheriumMinHeight, CONFIG.oreAetheriumMaxHeight, CONFIG.oreAetheriumPerChunk, 0.25F);
    public static ConfiguredFeature<?, ?> ORE_AQUARIUM = configureOre(OreFeatureConfig.Rules.STONE_ORE_REPLACEABLES, MythicOres.AQUARIUM_ORE.getDefaultState(), CONFIG.oreAquariumVeinSize,CONFIG.oreAquariumMinHeight, CONFIG.oreAquariumMaxHeight,CONFIG.oreAquariumPerChunk, 0.1F);
    public static ConfiguredFeature<?, ?> ORE_BANGLUM = configureOre(OreFeatureConfig.Rules.STONE_ORE_REPLACEABLES, MythicOres.BANGLUM_ORE.getDefaultState(), CONFIG.oreBanglumVeinSize,CONFIG.oreBanglumMinHeight, CONFIG.oreBanglumMaxHeight,CONFIG.oreBanglumPerChunk, 0.2F);
    public static ConfiguredFeature<?, ?> ORE_CARMOT = configureOre(OreFeatureConfig.Rules.STONE_ORE_REPLACEABLES, MythicOres.CARMOT_ORE.getDefaultState(), CONFIG.oreCarmotVeinSize,CONFIG.oreCarmotMinHeight, CONFIG.oreCarmotMaxHeight,CONFIG.oreCarmotPerChunk, 0.1F);
    public static ConfiguredFeature<?, ?> ORE_KYBER = configureOre(OreFeatureConfig.Rules.STONE_ORE_REPLACEABLES, MythicOres.KYBER_ORE.getDefaultState(), CONFIG.oreKyberVeinSize, CONFIG.oreKyberMinHeight, CONFIG.oreKyberMaxHeight,CONFIG.oreKyberPerChunk, 0.3F);
    public static ConfiguredFeature<?, ?> ORE_CALCITE_KYBER = Feature.ORE.configure(new OreFeatureConfig(CALCITE_RULE, MythicOres.CALCITE_KYBER_ORE.getDefaultState(), 18)).decorate(Decorator.RANGE.configure(new RangeDecoratorConfig(UniformHeightProvider.create(YOffset.aboveBottom(4), YOffset.fixed(70))))).spreadHorizontally().repeat(40);
    public static ConfiguredFeature<?, ?> ORE_DEEPSLATE_ADAMANTITE = Feature.ORE.configure(new OreFeatureConfig(OreFeatureConfig.Rules.DEEPSLATE_ORE_REPLACEABLES, MythicOres.DEEPSLATE_ADAMANTITE_ORE.getDefaultState(), 5, 0.2F)).decorate(Decorator.RANGE.configure(new RangeDecoratorConfig(UniformHeightProvider.create(YOffset.aboveBottom(5), YOffset.fixed(16))))).spreadHorizontally().repeat(1);
    public static ConfiguredFeature<?, ?> ORE_DEEPSLATE_MYTHRIL = Feature.ORE.configure(new OreFeatureConfig(OreFeatureConfig.Rules.DEEPSLATE_ORE_REPLACEABLES, MythicOres.DEEPSLATE_MYTHRIL_ORE.getDefaultState(), 5, 0.2F)).decorate(Decorator.RANGE.configure(new RangeDecoratorConfig(UniformHeightProvider.create(YOffset.aboveBottom(5), YOffset.fixed(16))))).spreadHorizontally().repeat(1);
    public static ConfiguredFeature<?, ?> ORE_MANGANESE = configureOre(OreFeatureConfig.Rules.STONE_ORE_REPLACEABLES, MythicOres.MANGANESE_ORE.getDefaultState(), CONFIG.oreManganeseVeinSize,CONFIG.oreManganeseMinHeight, CONFIG.oreManganeseMaxHeight,CONFIG.oreManganesePerChunk, 0.15F);
    public static ConfiguredFeature<?, ?> ORE_MYTHRIL = configureOre(OreFeatureConfig.Rules.STONE_ORE_REPLACEABLES, MythicOres.MYTHRIL_ORE.getDefaultState(), CONFIG.oreMythrilVeinSize,CONFIG.oreMythrilMinHeight, CONFIG.oreMythrilMaxHeight,CONFIG.oreMythrilPerChunk, 0.4F);
    public static ConfiguredFeature<?, ?> ORE_ORICHALCUM = configureOre(OreFeatureConfig.Rules.STONE_ORE_REPLACEABLES, MythicOres.ORICHALCUM_ORE.getDefaultState(), CONFIG.oreOrichalcumVeinSize,CONFIG.oreOrichalcumMinHeight, CONFIG.oreOrichalcumMaxHeight,CONFIG.oreOrichalcumPerChunk, 0.4F);
    public static ConfiguredFeature<?, ?> ORE_OSMIUM = configureOre(OreFeatureConfig.Rules.STONE_ORE_REPLACEABLES, MythicOres.OSMIUM_ORE.getDefaultState(), CONFIG.oreOsmiumVeinSize,CONFIG.oreOsmiumMinHeight, CONFIG.oreOsmiumMaxHeight,CONFIG.oreOsmiumPerChunk, 0.2F);
    public static ConfiguredFeature<?, ?> ORE_PLATINUM = configureOre(OreFeatureConfig.Rules.STONE_ORE_REPLACEABLES, MythicOres.PLATINUM_ORE.getDefaultState(), CONFIG.orePlatinumVeinSize,CONFIG.orePlatinumMinHeight, CONFIG.orePlatinumMaxHeight,CONFIG.orePlatinumPerChunk, 0.3F);
    public static ConfiguredFeature<?, ?> ORE_PROMETHEUM = configureOre(OreFeatureConfig.Rules.STONE_ORE_REPLACEABLES, MythicOres.PROMETHEUM_ORE.getDefaultState(), CONFIG.orePrometheumVeinSize,CONFIG.orePrometheumMinHeight, CONFIG.orePrometheumMaxHeight,CONFIG.orePrometheumPerChunk, 0.1F);
    public static ConfiguredFeature<?, ?> ORE_QUADRILLUM = configureOre(OreFeatureConfig.Rules.STONE_ORE_REPLACEABLES, MythicOres.QUADRILLUM_ORE.getDefaultState(), CONFIG.oreQuadrillumVeinSize,CONFIG.oreQuadrillumMinHeight, CONFIG.oreQuadrillumMaxHeight,CONFIG.oreQuadrillumPerChunk, 0.2F);
    public static ConfiguredFeature<?, ?> ORE_RUNITE = configureOre(OreFeatureConfig.Rules.STONE_ORE_REPLACEABLES, MythicOres.RUNITE_ORE.getDefaultState(), CONFIG.oreRuniteVeinSize,CONFIG.oreRuniteMinHeight, CONFIG.oreRuniteMaxHeight,CONFIG.oreRunitePerChunk, 0.0F);
    public static ConfiguredFeature<?, ?> ORE_SILVER = configureOre(OreFeatureConfig.Rules.STONE_ORE_REPLACEABLES, MythicOres.SILVER_ORE.getDefaultState(), CONFIG.oreSilverVeinSize,CONFIG.oreSilverMinHeight, CONFIG.oreSilverMaxHeight,CONFIG.oreSilverPerChunk, 0.1F);
    public static ConfiguredFeature<?, ?> ORE_STARRITE = configureOre(OreFeatureConfig.Rules.STONE_ORE_REPLACEABLES, MythicOres.STARRITE_ORE.getDefaultState(), CONFIG.oreStarriteVeinSize,CONFIG.oreStarriteMinHeight, CONFIG.oreStarriteMaxHeight,CONFIG.oreStarritePerChunk, 0.7F);
    public static ConfiguredFeature<?, ?> ORE_TIN = configureOre(OreFeatureConfig.Rules.STONE_ORE_REPLACEABLES, MythicOres.TIN_ORE.getDefaultState(), CONFIG.oreTinVeinSize,CONFIG.oreTinMinHeight, CONFIG.oreTinMaxHeight,CONFIG.oreTinPerChunk, 0.3F);
    public static ConfiguredFeature<?, ?> ORE_TUFF_ORICHALCUM = Feature.ORE.configure(new OreFeatureConfig(TUFF_RULE, MythicOres.TUFF_ORICHALCUM_ORE.getDefaultState(), 5, 0.1F)).decorate(Decorator.RANGE.configure(new RangeDecoratorConfig(UniformHeightProvider.create(YOffset.aboveBottom(4), YOffset.fixed(17))))).spreadHorizontally().repeat(2);
    public static ConfiguredFeature<?, ?> ORE_UNOBTAINIUM = configureUnobtainium(OreFeatureConfig.Rules.BASE_STONE_OVERWORLD, MythicOres.UNOBTAINIUM_ORE.getDefaultState(), CONFIG.oreUnobtainiumVeinSize, CONFIG.oreUnobtainiumMinHeight, CONFIG.oreUnobtainiumMaxHeight, CONFIG.oreUnobtainiumDiscardChance);
    public static ConfiguredFeature<?, ?> ORE_VERMICULITE = configureOre(OreFeatureConfig.Rules.STONE_ORE_REPLACEABLES, MythicOres.VERMICULITE_ORE.getDefaultState(), CONFIG.oreVermiculiteVeinSize,CONFIG.oreVermiculiteMinHeight, CONFIG.oreVermiculiteMaxHeight,CONFIG.oreVermiculitePerChunk, 0.3F);
    // Nether Ores
    public static ConfiguredFeature<?, ?> ORE_MIDAS_GOLD = configureOre(OreFeatureConfig.Rules.NETHERRACK, MythicOres.MIDAS_GOLD_ORE.getDefaultState(), CONFIG.oreMidasgoldVeinSize,CONFIG.oreMidasgoldMinHeight, CONFIG.oreMidasgoldMaxHeight,CONFIG.oreMidasgoldPerChunk, 0.1F);
    public static ConfiguredFeature<?, ?> ORE_TRUESILVER = configureOre(OreFeatureConfig.Rules.NETHERRACK, MythicOres.TRUESILVER_ORE.getDefaultState(), CONFIG.oreTruesilverVeinSize,CONFIG.oreTruesilverMinHeight, CONFIG.oreTruesilverMaxHeight,CONFIG.oreTruesilverPerChunk, 0.1F);
    public static ConfiguredFeature<?, ?> ORE_STORMYX = Feature.ORE.configure(new OreFeatureConfig(STORMYX_TARGETS, CONFIG.oreStormyxVeinSize, 0.1F)).decorate(Decorator.RANGE.configure(new RangeDecoratorConfig(UniformHeightProvider.create(YOffset.fixed(CONFIG.oreStormyxMinHeight), YOffset.fixed(CONFIG.oreStormyxMaxHeight)))).repeat(CONFIG.oreStormyxPerChunk));

    // Add keys for features
    public static final RegistryKey<ConfiguredFeature<?, ?>> oreAdamantite = RegistryKey.of(Registry.CONFIGURED_FEATURE_KEY, new Identifier(MythicMetals.MOD_ID, "ore_adamantite"));
    public static final RegistryKey<ConfiguredFeature<?, ?>> oreAetherium = RegistryKey.of(Registry.CONFIGURED_FEATURE_KEY, new Identifier(MythicMetals.MOD_ID, "ore_aetherium"));
    public static final RegistryKey<ConfiguredFeature<?, ?>> oreAquarium = RegistryKey.of(Registry.CONFIGURED_FEATURE_KEY, new Identifier(MythicMetals.MOD_ID, "ore_aquarium"));
    public static final RegistryKey<ConfiguredFeature<?, ?>> oreBanglum = RegistryKey.of(Registry.CONFIGURED_FEATURE_KEY, new Identifier(MythicMetals.MOD_ID, "ore_banglum"));
    public static final RegistryKey<ConfiguredFeature<?, ?>> oreCarmot = RegistryKey.of(Registry.CONFIGURED_FEATURE_KEY, new Identifier(MythicMetals.MOD_ID, "ore_carmot"));
    public static final RegistryKey<ConfiguredFeature<?, ?>> oreCalciteKyber = RegistryKey.of(Registry.CONFIGURED_FEATURE_KEY, new Identifier(MythicMetals.MOD_ID, "ore_calcite_kyber"));
    public static final RegistryKey<ConfiguredFeature<?, ?>> oreDeepslateAdamantite = RegistryKey.of(Registry.CONFIGURED_FEATURE_KEY, new Identifier(MythicMetals.MOD_ID, "ore_deepslate_adamantite"));
    public static final RegistryKey<ConfiguredFeature<?, ?>> oreDeepslateMythril = RegistryKey.of(Registry.CONFIGURED_FEATURE_KEY, new Identifier(MythicMetals.MOD_ID, "ore_deepslate_mythril"));
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
    public static final RegistryKey<ConfiguredFeature<?, ?>> oreTuffOrichalcum = RegistryKey.of(Registry.CONFIGURED_FEATURE_KEY, new Identifier(MythicMetals.MOD_ID, "ore_tuff_orichalcum"));
    public static final RegistryKey<ConfiguredFeature<?, ?>> oreTruesilver = RegistryKey.of(Registry.CONFIGURED_FEATURE_KEY, new Identifier(MythicMetals.MOD_ID, "ore_truesilver"));
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
        registerFeature(oreDeepslateAdamantite.getValue(), ORE_DEEPSLATE_ADAMANTITE);
        registerFeature(oreDeepslateMythril.getValue(), ORE_DEEPSLATE_MYTHRIL);
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
        registerFeature(oreTuffOrichalcum.getValue(), ORE_TUFF_ORICHALCUM);
        registerFeature(oreTruesilver.getValue(), ORE_TRUESILVER);
        registerFeature(oreUnobtainium.getValue(), ORE_UNOBTAINIUM);
        registerFeature(oreVermiculite.getValue(), ORE_VERMICULITE);
    }

    @SuppressWarnings("deprecation")
    public static void generate() {
        //Overworld Ores
        if (CONFIG.oreAdamantiteGeneration) { AddUOre(oreAdamantite); AddUOre(oreDeepslateAdamantite);}
        if (CONFIG.oreAetheriumGeneration) { AddUOre(oreAetherium); }
        if (CONFIG.oreBanglumGeneration) { AddUOre(oreBanglum); }
        if (CONFIG.oreCarmotGeneration) { AddUOre(oreCarmot); }
        if (CONFIG.oreKyberGeneration) { AddUOre(oreKyber); AddUOre(oreCalciteKyber); }
        if (CONFIG.oreManganeseGeneration) { AddUOre(oreManganese); }
        if (CONFIG.oreMythrilGeneration) { AddUOre(oreMythril); AddUOre(oreDeepslateMythril);}
        if (CONFIG.oreOrichalcumGeneration) { AddUOre(oreOrichalcum); AddUOre(oreTuffOrichalcum);}
        if (CONFIG.oreOsmiumGeneration) { AddUOre(oreOsmium); }
        if (CONFIG.orePlatinumGeneration) { AddUOre(orePlatinum); }
        if (CONFIG.oreQuadrillumGeneration) { AddUOre(oreQuadrillum); }
        if (CONFIG.oreRuniteGeneration) { AddUOre(oreRunite); }
        if (CONFIG.oreSilverGeneration) { AddUOre(oreSilver); }
        if (CONFIG.oreTinGeneration) { AddUOre(oreTin); }
        if (CONFIG.oreUnobtainiumGeneration) { AddUOre(oreUnobtainium); }
        if (CONFIG.oreVermiculiteGeneration) { AddUOre(oreVermiculite); }

        //Nether Ores
        if (CONFIG.oreMidasGoldGeneration) { AddUDecoration(oreMidasGold); }
        if (CONFIG.oreStormyxGeneration) { AddUDecoration(oreStormyx); }
        if (CONFIG.oreTruesilverGeneration) { AddUDecoration(oreTruesilver); }

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
                    BiomeKeys.MODIFIED_JUNGLE_EDGE
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
        if (CONFIG.oreStarriteGeneration) {
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
            ), GenerationStep.Feature.UNDERGROUND_ORES, oreStarrite);

            addUOreToModBiomes("byg","alpine_foothills", oreStarrite);
            addUOreToModBiomes("byg","alps", oreStarrite);
            addUOreToModBiomes("byg","black_forest_hills", oreStarrite);
            addUOreToModBiomes("byg","blue_taiga_hills", oreStarrite);
            addUOreToModBiomes("byg","bluff_peaks", oreStarrite);
            addUOreToModBiomes("byg","bluff_steeps", oreStarrite);
            addUOreToModBiomes("byg","boreal_forest_hills", oreStarrite);
            addUOreToModBiomes("byg","cika_mountains", oreStarrite);
            addUOreToModBiomes("byg","coniferous_forest_hills", oreStarrite);
            addUOreToModBiomes("byg","dover_mountains", oreStarrite);
            addUOreToModBiomes("byg","ebony_hills", oreStarrite);
            addUOreToModBiomes("byg","enchatned_forest_hills", oreStarrite);
            addUOreToModBiomes("byg","evergreen_hills", oreStarrite);
            addUOreToModBiomes("byg","grassland_plateau", oreStarrite);
            addUOreToModBiomes("byg","guiana_clearing", oreStarrite);
            addUOreToModBiomes("byg","guiana_shield", oreStarrite);
            addUOreToModBiomes("byg","jacaranda_forest_hills", oreStarrite);
            addUOreToModBiomes("byg","redwood_mountians", oreStarrite);
            addUOreToModBiomes("byg","skyris_highlands", oreStarrite);

            addUOreToModBiomes("terrestria","caldera_foothills", oreStarrite);
            addUOreToModBiomes("terrestria","caldera_ridge", oreStarrite);
            addUOreToModBiomes("terrestria","outback_uluru", oreStarrite);
            addUOreToModBiomes("terrestria","rainbow_rainforest", oreStarrite);
            addUOreToModBiomes("terrestria","rainbow_rainforest_mountains", oreStarrite);

            addUOreToModBiomes("traverse","arid_highlands", oreStarrite);
            addUOreToModBiomes("traverse","cliffs", oreStarrite);
            addUOreToModBiomes("traverse","rolling_hills", oreStarrite);
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
    public static ConfiguredFeature<?, ?> configureOre(RuleTest rule, BlockState defaultState, Integer veinSize, Integer minHeight, Integer maxHeight, Integer spawnRate, Float discardChance) {
        return Feature.ORE.configure(new OreFeatureConfig(rule, defaultState, veinSize, discardChance)).decorate(Decorator.RANGE.configure(new RangeDecoratorConfig(UniformHeightProvider.create(YOffset.fixed(minHeight), YOffset.fixed(maxHeight))))).spreadHorizontally().repeat(spawnRate);
    }

    public static ConfiguredFeature<?, ?> configureUnobtainium(RuleTest rule, BlockState defaultState, Integer veinSize, Integer minHeight, Integer maxHeight, Float discardChance) {
        return Feature.SCATTERED_ORE.configure(new OreFeatureConfig(rule, defaultState, veinSize, discardChance)).triangleRange(YOffset.aboveBottom(minHeight), YOffset.fixed(maxHeight)).spreadHorizontally().applyChance(2);
    }
    public static void registerFeature(Identifier identifier, ConfiguredFeature<?,?> feature) {
        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, identifier, feature);
    }
}