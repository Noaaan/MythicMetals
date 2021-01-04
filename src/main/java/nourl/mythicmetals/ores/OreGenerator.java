package nourl.mythicmetals.ores;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
//import net.minecraft.structure.rule.RuleTest;
//import net.minecraft.structure.rule.TagMatchRuleTest;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.decorator.Decorator;
import net.minecraft.world.gen.decorator.DepthAverageDecoratorConfig;
import net.minecraft.world.gen.decorator.RangeDecoratorConfig;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import nourl.mythicmetals.MythicMetals;
import nourl.mythicmetals.config.MythicConfig;

@SuppressWarnings("deprecation")
public class OreGenerator {
	public static final MythicConfig.MythicOreConfig CONFIG = MythicMetals.CONFIG.mythores;
	public static ConfiguredFeature<?, ?> ORE_ADAMANTITE = Feature.ORE.configure(new OreFeatureConfig(OreFeatureConfig.Rules.BASE_STONE_OVERWORLD, MythicMetalsOres.ADAMANTITE_ORE.getDefaultState(),
		    CONFIG.adamantiteVeinSize)) //Vein Size
			.decorate(Decorator.RANGE.configure(new RangeDecoratorConfig(0, // Bottom Offset
			CONFIG.adamantiteMinHeight, // MIN y level
			CONFIG.adamantiteMaxHeight))) // MAX y level
			.spreadHorizontally().repeat(CONFIG.adamantitePerChunk); // number of veins per chunk
	public static ConfiguredFeature<?, ?> ORE_AETHERIUM = Feature.ORE.configure(new OreFeatureConfig(OreFeatureConfig.Rules.BASE_STONE_OVERWORLD, MythicMetalsOres.AETHERIUM_ORE.getDefaultState(), CONFIG.aetheriumVeinSize)).decorate(Decorator.RANGE.configure(new RangeDecoratorConfig(0,CONFIG.aetheriumMinHeight,CONFIG.aetheriumMaxHeight))).spreadHorizontally().repeat(CONFIG.aetheriumPerChunk);
	public static ConfiguredFeature<?, ?> ORE_AQUARIUM = Feature.ORE.configure(new OreFeatureConfig(OreFeatureConfig.Rules.BASE_STONE_OVERWORLD, MythicMetalsOres.AQUARIUM_ORE.getDefaultState(), CONFIG.aquariumVeinSize)).decorate(Decorator.RANGE.configure(new RangeDecoratorConfig(0,CONFIG.aquariumMinHeight,CONFIG.aquariumMaxHeight))).spreadHorizontally().repeat(CONFIG.aquariumPerChunk);
	public static ConfiguredFeature<?, ?> ORE_BANGLUM = Feature.ORE.configure(new OreFeatureConfig(OreFeatureConfig.Rules.BASE_STONE_OVERWORLD, MythicMetalsOres.BANGLUM_ORE.getDefaultState(), CONFIG.banglumVeinSize)).decorate(Decorator.RANGE.configure(new RangeDecoratorConfig(0,CONFIG.banglumMinHeight,CONFIG.banglumMaxHeight))).spreadHorizontally().repeat(CONFIG.banglumPerChunk);
	public static ConfiguredFeature<?, ?> ORE_CARMOT = Feature.ORE.configure(new OreFeatureConfig(OreFeatureConfig.Rules.BASE_STONE_OVERWORLD, MythicMetalsOres.CARMOT_ORE.getDefaultState(), CONFIG.carmotVeinSize)).decorate(Decorator.RANGE.configure(new RangeDecoratorConfig(0,CONFIG.carmotMinHeight,CONFIG.carmotMaxHeight))).spreadHorizontally().repeat(CONFIG.carmotPerChunk);
	public static ConfiguredFeature<?, ?> ORE_COPPER = Feature.ORE.configure(new OreFeatureConfig(OreFeatureConfig.Rules.BASE_STONE_OVERWORLD, MythicMetalsOres.COPPER_ORE.getDefaultState(), CONFIG.copperVeinSize)).decorate(Decorator.RANGE.configure(new RangeDecoratorConfig(0,CONFIG.copperMinHeight,CONFIG.copperMaxHeight))).spreadHorizontally().repeat(CONFIG.copperPerChunk);
	public static ConfiguredFeature<?, ?> ORE_KYBER = Feature.ORE.configure(new OreFeatureConfig(OreFeatureConfig.Rules.BASE_STONE_OVERWORLD, MythicMetalsOres.KYBER_ORE.getDefaultState(), CONFIG.kyberVeinSize)).decorate(Decorator.RANGE.configure(new RangeDecoratorConfig(0,CONFIG.kyberMinHeight,CONFIG.kyberMaxHeight))).spreadHorizontally().repeat(CONFIG.kyberPerChunk);
	public static ConfiguredFeature<?, ?> ORE_MANGANESE = Feature.ORE.configure(new OreFeatureConfig(OreFeatureConfig.Rules.BASE_STONE_OVERWORLD, MythicMetalsOres.MANGANESE_ORE.getDefaultState(), CONFIG.manganeseVeinSize)).decorate(Decorator.RANGE.configure(new RangeDecoratorConfig(0,CONFIG.manganeseMinHeight,CONFIG.manganeseMaxHeight))).spreadHorizontally().repeat(CONFIG.manganesePerChunk);
	public static ConfiguredFeature<?, ?> ORE_MIDAS_GOLD = Feature.ORE.configure(new OreFeatureConfig(OreFeatureConfig.Rules.NETHERRACK, MythicMetalsOres.MIDAS_GOLD_ORE.getDefaultState(), CONFIG.midasgoldVeinSize)).decorate(Decorator.RANGE.configure(new RangeDecoratorConfig(0,CONFIG.midasgoldMinHeight,CONFIG.midasgoldMaxHeight))).spreadHorizontally().repeat(CONFIG.midasgoldPerChunk);
	public static ConfiguredFeature<?, ?> ORE_MYTHRIL = Feature.ORE.configure(new OreFeatureConfig(OreFeatureConfig.Rules.BASE_STONE_OVERWORLD, MythicMetalsOres.MYTHRIL_ORE.getDefaultState(), CONFIG.mythrilVeinSize)).decorate(Decorator.RANGE.configure(new RangeDecoratorConfig(0,CONFIG.mythrilMinHeight,CONFIG.mythrilMaxHeight))).spreadHorizontally().repeat(CONFIG.mythrilPerChunk);
	public static ConfiguredFeature<?, ?> ORE_ORICHALCUM = Feature.ORE.configure(new OreFeatureConfig(OreFeatureConfig.Rules.BASE_STONE_OVERWORLD, MythicMetalsOres.ORICHALCUM_ORE.getDefaultState(), CONFIG.orichalcumVeinSize)).decorate(Decorator.RANGE.configure(new RangeDecoratorConfig(0,CONFIG.orichalcumMinHeight,CONFIG.orichalcumMaxHeight))).spreadHorizontally().repeat(CONFIG.orichalcumPerChunk);
	public static ConfiguredFeature<?, ?> ORE_OSMIUM = Feature.ORE.configure(new OreFeatureConfig(OreFeatureConfig.Rules.BASE_STONE_OVERWORLD, MythicMetalsOres.OSMIUM_ORE.getDefaultState(), CONFIG.osmiumVeinSize)).decorate(Decorator.RANGE.configure(new RangeDecoratorConfig(0,CONFIG.osmiumMinHeight,CONFIG.osmiumMaxHeight))).spreadHorizontally().repeat(CONFIG.osmiumPerChunk);
	public static ConfiguredFeature<?, ?> ORE_PLATINUM = Feature.ORE.configure(new OreFeatureConfig(OreFeatureConfig.Rules.BASE_STONE_OVERWORLD, MythicMetalsOres.PLATINUM_ORE.getDefaultState(), CONFIG.platinumVeinSize)).decorate(Decorator.RANGE.configure(new RangeDecoratorConfig(0,CONFIG.platinumMinHeight,CONFIG.platinumMaxHeight))).spreadHorizontally().repeat(CONFIG.platinumPerChunk);
	public static ConfiguredFeature<?, ?> ORE_PROMETHEUM = Feature.ORE.configure(new OreFeatureConfig(OreFeatureConfig.Rules.BASE_STONE_OVERWORLD, MythicMetalsOres.PROMETHEUM_ORE.getDefaultState(), CONFIG.prometheumVeinSize)).decorate(Decorator.RANGE.configure(new RangeDecoratorConfig(0,CONFIG.prometheumMinHeight,CONFIG.prometheumMaxHeight))).spreadHorizontally().repeat(CONFIG.prometheumPerChunk);
	public static ConfiguredFeature<?, ?> ORE_QUADRILLUM = Feature.ORE.configure(new OreFeatureConfig(OreFeatureConfig.Rules.BASE_STONE_OVERWORLD, MythicMetalsOres.QUADRILLUM_ORE.getDefaultState(), CONFIG.quadrillumVeinSize)).decorate(Decorator.RANGE.configure(new RangeDecoratorConfig(0,CONFIG.quadrillumMinHeight,CONFIG.quadrillumMaxHeight))).spreadHorizontally().repeat(CONFIG.quadrillumPerChunk);
	public static ConfiguredFeature<?, ?> ORE_RUNITE = Feature.ORE.configure(new OreFeatureConfig(OreFeatureConfig.Rules.BASE_STONE_OVERWORLD, MythicMetalsOres.RUNITE_ORE.getDefaultState(), CONFIG.runiteVeinSize)).decorate(Decorator.RANGE.configure(new RangeDecoratorConfig(0,CONFIG.runiteMinHeight,CONFIG.runiteMaxHeight))).spreadHorizontally().repeat(CONFIG.runitePerChunk);
	public static ConfiguredFeature<?, ?> ORE_SILVER = Feature.ORE.configure(new OreFeatureConfig(OreFeatureConfig.Rules.BASE_STONE_OVERWORLD, MythicMetalsOres.SILVER_ORE.getDefaultState(), CONFIG.silverVeinSize)).decorate(Decorator.RANGE.configure(new RangeDecoratorConfig(0,CONFIG.silverMinHeight,CONFIG.silverMaxHeight))).spreadHorizontally().repeat(CONFIG.silverPerChunk);
	public static ConfiguredFeature<?, ?> ORE_STARRITE = Feature.ORE.configure(new OreFeatureConfig(OreFeatureConfig.Rules.BASE_STONE_OVERWORLD, MythicMetalsOres.STARRITE_ORE.getDefaultState(), CONFIG.starriteVeinSize)).decorate(Decorator.RANGE.configure(new RangeDecoratorConfig(0,CONFIG.starriteMinHeight,CONFIG.starriteMaxHeight))).spreadHorizontally().repeat(CONFIG.starritePerChunk);
	public static ConfiguredFeature<?, ?> ORE_STORMYX = Feature.ORE.configure(new OreFeatureConfig(OreFeatureConfig.Rules.NETHERRACK, MythicMetalsOres.STORMYX_ORE.getDefaultState(), CONFIG.stormyxVeinSize)).decorate(Decorator.RANGE.configure(new RangeDecoratorConfig(0,CONFIG.stormyxMinHeight,CONFIG.stormyxMaxHeight))).spreadHorizontally().repeat(CONFIG.stormyxPerChunk);
	public static ConfiguredFeature<?, ?> ORE_TANTALITE = Feature.ORE.configure(new OreFeatureConfig(OreFeatureConfig.Rules.BASE_STONE_OVERWORLD, MythicMetalsOres.TANTALITE_ORE.getDefaultState(), CONFIG.tantaliteVeinSize)).decorate(Decorator.RANGE.configure(new RangeDecoratorConfig(0,CONFIG.tantaliteMinHeight,CONFIG.tantaliteMaxHeight))).spreadHorizontally().repeat(CONFIG.tantalitePerChunk);
	public static ConfiguredFeature<?, ?> ORE_TIN = Feature.ORE.configure(new OreFeatureConfig(OreFeatureConfig.Rules.BASE_STONE_OVERWORLD, MythicMetalsOres.TIN_ORE.getDefaultState(), CONFIG.tinVeinSize)).decorate(Decorator.RANGE.configure(new RangeDecoratorConfig(0,CONFIG.tinMinHeight,CONFIG.tinMaxHeight))).spreadHorizontally().repeat(CONFIG.tinPerChunk);
	public static ConfiguredFeature<?, ?> ORE_TRUESILVER = Feature.ORE.configure(new OreFeatureConfig(OreFeatureConfig.Rules.NETHERRACK, MythicMetalsOres.TRUESILVER_ORE.getDefaultState(), CONFIG.truesilverVeinSize)).decorate(Decorator.RANGE.configure(new RangeDecoratorConfig(0,CONFIG.truesilverMinHeight,CONFIG.truesilverMaxHeight))).spreadHorizontally().repeat(CONFIG.truesilverPerChunk);
	public static ConfiguredFeature<?, ?> ORE_UNOBTAINIUM = Feature.NO_SURFACE_ORE.configure(new OreFeatureConfig(OreFeatureConfig.Rules.BASE_STONE_OVERWORLD, MythicMetalsOres.UNOBTAINIUM_ORE.getDefaultState(), CONFIG.unobtainiumVeinSize)).decorate(Decorator.DEPTH_AVERAGE.configure(new DepthAverageDecoratorConfig(CONFIG.unobtainiumAverageHeight, CONFIG.unobtainiumSpread)).spreadHorizontally());
	public static ConfiguredFeature<?, ?> ORE_UR = Feature.ORE.configure(new OreFeatureConfig(OreFeatureConfig.Rules.NETHERRACK, MythicMetalsOres.UR_ORE.getDefaultState(), CONFIG.urVeinSize)).decorate(Decorator.RANGE.configure(new RangeDecoratorConfig(0,CONFIG.urMinHeight,CONFIG.urMaxHeight))).spreadHorizontally().repeat(CONFIG.urPerChunk);
	public static ConfiguredFeature<?, ?> ORE_VERMICULITE = Feature.ORE.configure(new OreFeatureConfig(OreFeatureConfig.Rules.BASE_STONE_OVERWORLD, MythicMetalsOres.VERMICULITE_ORE.getDefaultState(), CONFIG.vermiculiteVeinSize)).decorate(Decorator.RANGE.configure(new RangeDecoratorConfig(0,CONFIG.vermiculiteMinHeight,CONFIG.vermiculiteMaxHeight))).spreadHorizontally().repeat(CONFIG.vermiculitePerChunk);
	public static ConfiguredFeature<?, ?> ORE_ZINC = Feature.ORE.configure(new OreFeatureConfig(OreFeatureConfig.Rules.BASE_STONE_OVERWORLD, MythicMetalsOres.ZINC_ORE.getDefaultState(), CONFIG.zincVeinSize)).decorate(Decorator.RANGE.configure(new RangeDecoratorConfig(0,CONFIG.zincMinHeight,CONFIG.zincMaxHeight))).spreadHorizontally().repeat(CONFIG.zincPerChunk);

	public static final RegistryKey<ConfiguredFeature<?, ?>> oreAdamantite = RegistryKey.of(Registry.CONFIGURED_FEATURE_WORLDGEN, new Identifier(MythicMetals.MOD_ID, "ore_adamantite"));
	public static final RegistryKey<ConfiguredFeature<?, ?>> oreAetherium = RegistryKey.of(Registry.CONFIGURED_FEATURE_WORLDGEN, new Identifier(MythicMetals.MOD_ID, "ore_aetherium"));
	public static final RegistryKey<ConfiguredFeature<?, ?>> oreAquarium = RegistryKey.of(Registry.CONFIGURED_FEATURE_WORLDGEN, new Identifier(MythicMetals.MOD_ID, "ore_aquarium"));
	public static final RegistryKey<ConfiguredFeature<?, ?>> oreBanglum = RegistryKey.of(Registry.CONFIGURED_FEATURE_WORLDGEN, new Identifier(MythicMetals.MOD_ID, "ore_banglum"));
	public static final RegistryKey<ConfiguredFeature<?, ?>> oreCarmot = RegistryKey.of(Registry.CONFIGURED_FEATURE_WORLDGEN, new Identifier(MythicMetals.MOD_ID, "ore_carmot"));
	public static final RegistryKey<ConfiguredFeature<?, ?>> oreCopper = RegistryKey.of(Registry.CONFIGURED_FEATURE_WORLDGEN, new Identifier(MythicMetals.MOD_ID, "ore_copper"));
	public static final RegistryKey<ConfiguredFeature<?, ?>> oreKyber = RegistryKey.of(Registry.CONFIGURED_FEATURE_WORLDGEN, new Identifier(MythicMetals.MOD_ID, "ore_kyber"));
	public static final RegistryKey<ConfiguredFeature<?, ?>> oreManganese = RegistryKey.of(Registry.CONFIGURED_FEATURE_WORLDGEN, new Identifier(MythicMetals.MOD_ID, "ore_manganese"));
	public static final RegistryKey<ConfiguredFeature<?, ?>> oreMidasGold = RegistryKey.of(Registry.CONFIGURED_FEATURE_WORLDGEN, new Identifier(MythicMetals.MOD_ID, "ore_midas_gold"));
	public static final RegistryKey<ConfiguredFeature<?, ?>> oreMythril = RegistryKey.of(Registry.CONFIGURED_FEATURE_WORLDGEN, new Identifier(MythicMetals.MOD_ID, "ore_mythril"));
	public static final RegistryKey<ConfiguredFeature<?, ?>> oreOrichalcum = RegistryKey.of(Registry.CONFIGURED_FEATURE_WORLDGEN, new Identifier(MythicMetals.MOD_ID, "ore_orichalcum"));
	public static final RegistryKey<ConfiguredFeature<?, ?>> oreOsmium = RegistryKey.of(Registry.CONFIGURED_FEATURE_WORLDGEN, new Identifier(MythicMetals.MOD_ID, "ore_osmium"));
	public static final RegistryKey<ConfiguredFeature<?, ?>> orePlatinum = RegistryKey.of(Registry.CONFIGURED_FEATURE_WORLDGEN, new Identifier(MythicMetals.MOD_ID, "ore_platinum"));
	public static final RegistryKey<ConfiguredFeature<?, ?>> orePrometheum = RegistryKey.of(Registry.CONFIGURED_FEATURE_WORLDGEN, new Identifier(MythicMetals.MOD_ID, "ore_prometheum"));
	public static final RegistryKey<ConfiguredFeature<?, ?>> oreQuadrillum = RegistryKey.of(Registry.CONFIGURED_FEATURE_WORLDGEN, new Identifier(MythicMetals.MOD_ID, "ore_quadrillum"));
	public static final RegistryKey<ConfiguredFeature<?, ?>> oreRunite = RegistryKey.of(Registry.CONFIGURED_FEATURE_WORLDGEN, new Identifier(MythicMetals.MOD_ID, "ore_runite"));
	public static final RegistryKey<ConfiguredFeature<?, ?>> oreSilver = RegistryKey.of(Registry.CONFIGURED_FEATURE_WORLDGEN, new Identifier(MythicMetals.MOD_ID, "ore_silver"));
	public static final RegistryKey<ConfiguredFeature<?, ?>> oreStarrite = RegistryKey.of(Registry.CONFIGURED_FEATURE_WORLDGEN, new Identifier(MythicMetals.MOD_ID, "ore_starrite"));
	public static final RegistryKey<ConfiguredFeature<?, ?>> oreStormyx = RegistryKey.of(Registry.CONFIGURED_FEATURE_WORLDGEN, new Identifier(MythicMetals.MOD_ID, "ore_stormyx"));
	public static final RegistryKey<ConfiguredFeature<?, ?>> oreTantalite = RegistryKey.of(Registry.CONFIGURED_FEATURE_WORLDGEN, new Identifier(MythicMetals.MOD_ID, "ore_tantalite"));
	public static final RegistryKey<ConfiguredFeature<?, ?>> oreTin = RegistryKey.of(Registry.CONFIGURED_FEATURE_WORLDGEN, new Identifier(MythicMetals.MOD_ID, "ore_tin"));
	public static final RegistryKey<ConfiguredFeature<?, ?>> oreTruesilver = RegistryKey.of(Registry.CONFIGURED_FEATURE_WORLDGEN, new Identifier(MythicMetals.MOD_ID, "ore_truesilver"));
	public static final RegistryKey<ConfiguredFeature<?, ?>> oreUnobtainium = RegistryKey.of(Registry.CONFIGURED_FEATURE_WORLDGEN, new Identifier(MythicMetals.MOD_ID, "ore_unobtainium"));
	public static final RegistryKey<ConfiguredFeature<?, ?>> oreUr = RegistryKey.of(Registry.CONFIGURED_FEATURE_WORLDGEN, new Identifier(MythicMetals.MOD_ID, "ore_ur"));
	public static final RegistryKey<ConfiguredFeature<?, ?>> oreVermiculite = RegistryKey.of(Registry.CONFIGURED_FEATURE_WORLDGEN, new Identifier(MythicMetals.MOD_ID, "ore_vermiculite"));
	public static final RegistryKey<ConfiguredFeature<?, ?>> oreZinc = RegistryKey.of(Registry.CONFIGURED_FEATURE_WORLDGEN, new Identifier(MythicMetals.MOD_ID, "ore_zinc"));

	public static final MythicConfig.MythicOreConfig CONFIG2 = MythicMetals.CONFIG.mythores;
	// public static final RuleTest RULE_NETHERRACK = new TagMatchRuleTest();

	public static void init() {

		Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, oreAdamantite.getValue(), OreGenerator.ORE_ADAMANTITE);
		Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, oreAetherium.getValue(), OreGenerator.ORE_AETHERIUM);
		Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, oreAquarium.getValue(), OreGenerator.ORE_AQUARIUM);
		Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, oreBanglum.getValue(), OreGenerator.ORE_BANGLUM);
		Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, oreCarmot.getValue(), OreGenerator.ORE_CARMOT);
		Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, oreCopper.getValue(), OreGenerator.ORE_COPPER);
		Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, oreKyber.getValue(), OreGenerator.ORE_KYBER);
		Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, oreManganese.getValue(), OreGenerator.ORE_MANGANESE);
		Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, oreMidasGold.getValue(), OreGenerator.ORE_MIDAS_GOLD);
		Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, oreMythril.getValue(), OreGenerator.ORE_MYTHRIL);
		Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, oreOrichalcum.getValue(), OreGenerator.ORE_ORICHALCUM);
		Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, oreOsmium.getValue(), OreGenerator.ORE_OSMIUM);
		Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, orePlatinum.getValue(), OreGenerator.ORE_PLATINUM);
		Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, orePrometheum.getValue(), OreGenerator.ORE_PROMETHEUM);
		Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, oreQuadrillum.getValue(), OreGenerator.ORE_QUADRILLUM);
		Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, oreRunite.getValue(), OreGenerator.ORE_RUNITE);
		Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, oreSilver.getValue(), OreGenerator.ORE_SILVER);
		Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, oreStarrite.getValue(), OreGenerator.ORE_STARRITE);
		Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, oreStormyx.getValue(), OreGenerator.ORE_STORMYX);
		Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, oreTantalite.getValue(), OreGenerator.ORE_TANTALITE);
		Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, oreTin.getValue(), OreGenerator.ORE_TIN);
		Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, oreTruesilver.getValue(), OreGenerator.ORE_TRUESILVER);
		Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, oreUnobtainium.getValue(), OreGenerator.ORE_UNOBTAINIUM);
		Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, oreUr.getValue(), OreGenerator.ORE_UR);
		Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, oreVermiculite.getValue(), OreGenerator.ORE_VERMICULITE);
		Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, oreZinc.getValue(), OreGenerator.ORE_ZINC);


	}
	@SuppressWarnings("deprecation")
	public static void generate() {

		//Overworld Ores
		if(CONFIG2.adamantiteGeneration) {
			BiomeModifications.addFeature(BiomeSelectors.builtIn(), GenerationStep.Feature.UNDERGROUND_ORES, oreAdamantite);}
		if(CONFIG2.aetheriumGeneration) {
			BiomeModifications.addFeature(BiomeSelectors.builtIn(), GenerationStep.Feature.UNDERGROUND_ORES, oreAetherium); }
		if(CONFIG2.banglumGeneration) {
			BiomeModifications.addFeature(BiomeSelectors.builtIn(), GenerationStep.Feature.UNDERGROUND_ORES, oreBanglum); }
		if(CONFIG2.carmotGeneration) {
			BiomeModifications.addFeature(BiomeSelectors.builtIn(), GenerationStep.Feature.UNDERGROUND_ORES, oreCarmot); }
		if(CONFIG2.copperGeneration) {
			BiomeModifications.addFeature(BiomeSelectors.builtIn(), GenerationStep.Feature.UNDERGROUND_ORES, oreCopper); }
		if(CONFIG2.kyberGeneration) {
			BiomeModifications.addFeature(BiomeSelectors.builtIn(), GenerationStep.Feature.UNDERGROUND_ORES, oreKyber ); }
		if(CONFIG2.manganeseGeneration) {
			BiomeModifications.addFeature(BiomeSelectors.builtIn(), GenerationStep.Feature.UNDERGROUND_ORES, oreManganese); }
		if(CONFIG2.mythrilGeneration) {
			BiomeModifications.addFeature(BiomeSelectors.builtIn(), GenerationStep.Feature.UNDERGROUND_ORES, oreMythril); }
		if(CONFIG2.orichalcumGeneration) {
			BiomeModifications.addFeature(BiomeSelectors.builtIn(), GenerationStep.Feature.UNDERGROUND_ORES, oreOrichalcum); }
		if(CONFIG2.osmiumGeneration) {
			BiomeModifications.addFeature(BiomeSelectors.builtIn(), GenerationStep.Feature.UNDERGROUND_ORES, oreOsmium); }
		if(CONFIG2.platinumGeneration) {
			BiomeModifications.addFeature(BiomeSelectors.builtIn(), GenerationStep.Feature.UNDERGROUND_ORES, orePlatinum); }
		if(CONFIG2.quadrillumGeneration) {
			BiomeModifications.addFeature(BiomeSelectors.builtIn(), GenerationStep.Feature.UNDERGROUND_ORES, oreQuadrillum); }
		if(CONFIG2.runiteGeneration) {
			BiomeModifications.addFeature(BiomeSelectors.builtIn(), GenerationStep.Feature.UNDERGROUND_ORES, oreRunite); }
		if(CONFIG2.silverGeneration) {
			BiomeModifications.addFeature(BiomeSelectors.builtIn(), GenerationStep.Feature.UNDERGROUND_ORES, oreSilver); }
		if(CONFIG2.tantaliteGeneration) {
			BiomeModifications.addFeature(BiomeSelectors.builtIn(), GenerationStep.Feature.UNDERGROUND_ORES, oreTantalite); }
		if(CONFIG2.tinGeneration) {
			BiomeModifications.addFeature(BiomeSelectors.builtIn(), GenerationStep.Feature.UNDERGROUND_ORES, oreTin); }
		if(CONFIG2.unobtainiumGeneration) {
			BiomeModifications.addFeature(BiomeSelectors.builtIn(), GenerationStep.Feature.UNDERGROUND_ORES, oreUnobtainium); }
		if(CONFIG2.vermiculiteGeneration) {
			BiomeModifications.addFeature(BiomeSelectors.builtIn(), GenerationStep.Feature.UNDERGROUND_ORES, oreVermiculite); }
		if(CONFIG2.zincGeneration) {
			BiomeModifications.addFeature(BiomeSelectors.builtIn(), GenerationStep.Feature.UNDERGROUND_ORES, oreZinc); }
		//Nether Ores
		if(CONFIG2.midasgoldGeneration) {
			BiomeModifications.addFeature(BiomeSelectors.builtIn(), GenerationStep.Feature.UNDERGROUND_DECORATION, oreMidasGold); }
		if(CONFIG2.stormyxGeneration) {
			BiomeModifications.addFeature(BiomeSelectors.builtIn(), GenerationStep.Feature.UNDERGROUND_DECORATION, oreStormyx); }
		if(CONFIG2.truesilverGeneration) {
			BiomeModifications.addFeature(BiomeSelectors.builtIn(), GenerationStep.Feature.UNDERGROUND_DECORATION, oreTruesilver); }
		if(CONFIG2.urGeneration) {
			BiomeModifications.addFeature(BiomeSelectors.builtIn(), GenerationStep.Feature.UNDERGROUND_DECORATION, oreUr); }

		//Ocean only ores
		if(CONFIG2.aquariumGeneration) {
			BiomeModifications.addFeature(BiomeSelectors.includeByKey(BiomeKeys.COLD_OCEAN, BiomeKeys.DEEP_COLD_OCEAN, BiomeKeys.DEEP_FROZEN_OCEAN, BiomeKeys.DEEP_LUKEWARM_OCEAN, BiomeKeys.DEEP_OCEAN, BiomeKeys.FROZEN_OCEAN, BiomeKeys.LUKEWARM_OCEAN, BiomeKeys.OCEAN, BiomeKeys.WARM_OCEAN), GenerationStep.Feature.UNDERGROUND_ORES, oreAquarium); }
		//Jungle only ores
		if(CONFIG2.prometheumGeneration) {
			BiomeModifications.addFeature(BiomeSelectors.includeByKey(BiomeKeys.BAMBOO_JUNGLE, BiomeKeys.BAMBOO_JUNGLE_HILLS, BiomeKeys.JUNGLE, BiomeKeys.JUNGLE_EDGE, BiomeKeys.JUNGLE_HILLS, BiomeKeys.MODIFIED_JUNGLE, BiomeKeys.MODIFIED_JUNGLE_EDGE), GenerationStep.Feature.UNDERGROUND_ORES, orePrometheum); }
		//Mountain only ores
		if(CONFIG2.starriteGeneration) {
			BiomeModifications.addFeature(BiomeSelectors.includeByKey(BiomeKeys.GRAVELLY_MOUNTAINS, BiomeKeys.MODIFIED_GRAVELLY_MOUNTAINS, BiomeKeys.MOUNTAINS, BiomeKeys.MOUNTAIN_EDGE, BiomeKeys.SHATTERED_SAVANNA, BiomeKeys.SHATTERED_SAVANNA_PLATEAU, BiomeKeys.SNOWY_MOUNTAINS, BiomeKeys.SNOWY_TAIGA_MOUNTAINS, BiomeKeys.TAIGA_MOUNTAINS), GenerationStep.Feature.UNDERGROUND_ORES, oreStarrite); }
		if (FabricLoader.getInstance().isModLoaded("byg")) {
			BiomeModifications.addFeature(BiomeSelectors.includeByKey(RegistryKey.of(Registry.BIOME_KEY, new Identifier("byg", "alps"))), GenerationStep.Feature.UNDERGROUND_ORES, oreStarrite);
		}
	}
}