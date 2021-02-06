package nourl.mythicmetals.ores;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
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
	// Defines the features that represents the ores, being fully configurable
	public static final MythicConfig.MythicOreConfig CONFIG = MythicMetals.CONFIG.mythores;
	public static ConfiguredFeature<?, ?> ORE_ADAMANTITE = Feature.ORE.configure(new OreFeatureConfig(OreFeatureConfig.Rules.BASE_STONE_OVERWORLD, MythicMetalsOres.ADAMANTITE_ORE.getDefaultState(),
		    CONFIG.adamantiteVeinSize)) //Vein Size
			.decorate(Decorator.RANGE.configure(new RangeDecoratorConfig(CONFIG.adamantiteMinHeight, // Bottom Offset, essentially minimum y level
			0, // Top offset, reduces the max height an ore can spawn by the int
			CONFIG.adamantiteMaxHeight))) // MAX y level
			.spreadHorizontally().repeat(CONFIG.adamantitePerChunk); // number of veins per chunk
	public static ConfiguredFeature<?, ?> ORE_AETHERIUM = Feature.ORE.configure(new OreFeatureConfig(OreFeatureConfig.Rules.BASE_STONE_OVERWORLD, MythicMetalsOres.AETHERIUM_ORE.getDefaultState(), CONFIG.aetheriumVeinSize)).decorate(Decorator.RANGE.configure(new RangeDecoratorConfig(CONFIG.aetheriumMinHeight, 0, CONFIG.aetheriumMaxHeight))).spreadHorizontally().repeat(CONFIG.aetheriumPerChunk);
	public static ConfiguredFeature<?, ?> ORE_AQUARIUM = Feature.ORE.configure(new OreFeatureConfig(OreFeatureConfig.Rules.BASE_STONE_OVERWORLD, MythicMetalsOres.AQUARIUM_ORE.getDefaultState(), CONFIG.aquariumVeinSize)).decorate(Decorator.RANGE.configure(new RangeDecoratorConfig(CONFIG.aquariumMinHeight, 0, CONFIG.aquariumMaxHeight))).spreadHorizontally().repeat(CONFIG.aquariumPerChunk);
	public static ConfiguredFeature<?, ?> ORE_BANGLUM = Feature.ORE.configure(new OreFeatureConfig(OreFeatureConfig.Rules.BASE_STONE_OVERWORLD, MythicMetalsOres.BANGLUM_ORE.getDefaultState(), CONFIG.banglumVeinSize)).decorate(Decorator.RANGE.configure(new RangeDecoratorConfig(CONFIG.banglumMinHeight, 0, CONFIG.banglumMaxHeight))).spreadHorizontally().repeat(CONFIG.banglumPerChunk);
	public static ConfiguredFeature<?, ?> ORE_CARMOT = Feature.ORE.configure(new OreFeatureConfig(OreFeatureConfig.Rules.BASE_STONE_OVERWORLD, MythicMetalsOres.CARMOT_ORE.getDefaultState(), CONFIG.carmotVeinSize)).decorate(Decorator.RANGE.configure(new RangeDecoratorConfig(CONFIG.carmotMinHeight, 0, CONFIG.carmotMaxHeight))).spreadHorizontally().repeat(CONFIG.carmotPerChunk);
	public static ConfiguredFeature<?, ?> ORE_COPPER = Feature.ORE.configure(new OreFeatureConfig(OreFeatureConfig.Rules.BASE_STONE_OVERWORLD, MythicMetalsOres.COPPER_ORE.getDefaultState(), CONFIG.copperVeinSize)).decorate(Decorator.RANGE.configure(new RangeDecoratorConfig(CONFIG.copperMinHeight, 0, CONFIG.copperMaxHeight))).spreadHorizontally().repeat(CONFIG.copperPerChunk);
	public static ConfiguredFeature<?, ?> ORE_KYBER = Feature.ORE.configure(new OreFeatureConfig(OreFeatureConfig.Rules.BASE_STONE_OVERWORLD, MythicMetalsOres.KYBER_ORE.getDefaultState(), CONFIG.kyberVeinSize)).decorate(Decorator.RANGE.configure(new RangeDecoratorConfig(CONFIG.kyberMinHeight, 0, CONFIG.kyberMaxHeight))).spreadHorizontally().repeat(CONFIG.kyberPerChunk);
	public static ConfiguredFeature<?, ?> ORE_MANGANESE = Feature.ORE.configure(new OreFeatureConfig(OreFeatureConfig.Rules.BASE_STONE_OVERWORLD, MythicMetalsOres.MANGANESE_ORE.getDefaultState(), CONFIG.manganeseVeinSize)).decorate(Decorator.RANGE.configure(new RangeDecoratorConfig(CONFIG.manganeseMinHeight, 0, CONFIG.manganeseMaxHeight))).spreadHorizontally().repeat(CONFIG.manganesePerChunk);
	public static ConfiguredFeature<?, ?> ORE_MIDAS_GOLD = Feature.ORE.configure(new OreFeatureConfig(OreFeatureConfig.Rules.NETHERRACK, MythicMetalsOres.MIDAS_GOLD_ORE.getDefaultState(), CONFIG.midasgoldVeinSize)).decorate(Decorator.RANGE.configure(new RangeDecoratorConfig(CONFIG.midasgoldMinHeight, 0, CONFIG.midasgoldMaxHeight))).spreadHorizontally().repeat(CONFIG.midasgoldPerChunk);
	public static ConfiguredFeature<?, ?> ORE_MYTHRIL = Feature.ORE.configure(new OreFeatureConfig(OreFeatureConfig.Rules.BASE_STONE_OVERWORLD, MythicMetalsOres.MYTHRIL_ORE.getDefaultState(), CONFIG.mythrilVeinSize)).decorate(Decorator.RANGE.configure(new RangeDecoratorConfig(CONFIG.mythrilMinHeight, 0, CONFIG.mythrilMaxHeight))).spreadHorizontally().repeat(CONFIG.mythrilPerChunk);
	public static ConfiguredFeature<?, ?> ORE_ORICHALCUM = Feature.ORE.configure(new OreFeatureConfig(OreFeatureConfig.Rules.BASE_STONE_OVERWORLD, MythicMetalsOres.ORICHALCUM_ORE.getDefaultState(), CONFIG.orichalcumVeinSize)).decorate(Decorator.RANGE.configure(new RangeDecoratorConfig(CONFIG.orichalcumMinHeight, 0, CONFIG.orichalcumMaxHeight))).spreadHorizontally().repeat(CONFIG.orichalcumPerChunk);
	public static ConfiguredFeature<?, ?> ORE_OSMIUM = Feature.ORE.configure(new OreFeatureConfig(OreFeatureConfig.Rules.BASE_STONE_OVERWORLD, MythicMetalsOres.OSMIUM_ORE.getDefaultState(), CONFIG.osmiumVeinSize)).decorate(Decorator.RANGE.configure(new RangeDecoratorConfig(CONFIG.osmiumMinHeight, 0, CONFIG.osmiumMaxHeight))).spreadHorizontally().repeat(CONFIG.osmiumPerChunk);
	public static ConfiguredFeature<?, ?> ORE_PLATINUM = Feature.ORE.configure(new OreFeatureConfig(OreFeatureConfig.Rules.BASE_STONE_OVERWORLD, MythicMetalsOres.PLATINUM_ORE.getDefaultState(), CONFIG.platinumVeinSize)).decorate(Decorator.RANGE.configure(new RangeDecoratorConfig(CONFIG.platinumMinHeight, 0, CONFIG.platinumMaxHeight))).spreadHorizontally().repeat(CONFIG.platinumPerChunk);
	public static ConfiguredFeature<?, ?> ORE_PROMETHEUM = Feature.ORE.configure(new OreFeatureConfig(OreFeatureConfig.Rules.BASE_STONE_OVERWORLD, MythicMetalsOres.PROMETHEUM_ORE.getDefaultState(), CONFIG.prometheumVeinSize)).decorate(Decorator.RANGE.configure(new RangeDecoratorConfig(CONFIG.prometheumMinHeight, 0, CONFIG.prometheumMaxHeight))).spreadHorizontally().repeat(CONFIG.prometheumPerChunk);
	public static ConfiguredFeature<?, ?> ORE_QUADRILLUM = Feature.ORE.configure(new OreFeatureConfig(OreFeatureConfig.Rules.BASE_STONE_OVERWORLD, MythicMetalsOres.QUADRILLUM_ORE.getDefaultState(), CONFIG.quadrillumVeinSize)).decorate(Decorator.RANGE.configure(new RangeDecoratorConfig(CONFIG.quadrillumMinHeight, 0, CONFIG.quadrillumMaxHeight))).spreadHorizontally().repeat(CONFIG.quadrillumPerChunk);
	public static ConfiguredFeature<?, ?> ORE_RUNITE = Feature.ORE.configure(new OreFeatureConfig(OreFeatureConfig.Rules.BASE_STONE_OVERWORLD, MythicMetalsOres.RUNITE_ORE.getDefaultState(), CONFIG.runiteVeinSize)).decorate(Decorator.RANGE.configure(new RangeDecoratorConfig(CONFIG.runiteMinHeight, 0, CONFIG.runiteMaxHeight))).spreadHorizontally().repeat(CONFIG.runitePerChunk);
	public static ConfiguredFeature<?, ?> ORE_SILVER = Feature.ORE.configure(new OreFeatureConfig(OreFeatureConfig.Rules.BASE_STONE_OVERWORLD, MythicMetalsOres.SILVER_ORE.getDefaultState(), CONFIG.silverVeinSize)).decorate(Decorator.RANGE.configure(new RangeDecoratorConfig(CONFIG.silverMinHeight, 0, CONFIG.silverMaxHeight))).spreadHorizontally().repeat(CONFIG.silverPerChunk);
	public static ConfiguredFeature<?, ?> ORE_STARRITE = Feature.ORE.configure(new OreFeatureConfig(OreFeatureConfig.Rules.BASE_STONE_OVERWORLD, MythicMetalsOres.STARRITE_ORE.getDefaultState(), CONFIG.starriteVeinSize)).decorate(Decorator.RANGE.configure(new RangeDecoratorConfig(CONFIG.starriteMinHeight, 0, CONFIG.starriteMaxHeight))).spreadHorizontally().repeat(CONFIG.starritePerChunk);
	public static ConfiguredFeature<?, ?> ORE_STORMYX = Feature.ORE.configure(new OreFeatureConfig(OreFeatureConfig.Rules.NETHERRACK, MythicMetalsOres.STORMYX_ORE.getDefaultState(), CONFIG.stormyxVeinSize)).decorate(Decorator.RANGE.configure(new RangeDecoratorConfig(CONFIG.stormyxMinHeight, 0, CONFIG.stormyxMaxHeight))).spreadHorizontally().repeat(CONFIG.stormyxPerChunk);
	public static ConfiguredFeature<?, ?> ORE_TANTALITE = Feature.ORE.configure(new OreFeatureConfig(OreFeatureConfig.Rules.BASE_STONE_OVERWORLD, MythicMetalsOres.TANTALITE_ORE.getDefaultState(), CONFIG.tantaliteVeinSize)).decorate(Decorator.RANGE.configure(new RangeDecoratorConfig(CONFIG.tantaliteMinHeight, 0, CONFIG.tantaliteMaxHeight))).spreadHorizontally().repeat(CONFIG.tantalitePerChunk);
	public static ConfiguredFeature<?, ?> ORE_TIN = Feature.ORE.configure(new OreFeatureConfig(OreFeatureConfig.Rules.BASE_STONE_OVERWORLD, MythicMetalsOres.TIN_ORE.getDefaultState(), CONFIG.tinVeinSize)).decorate(Decorator.RANGE.configure(new RangeDecoratorConfig(CONFIG.tinMinHeight, 0, CONFIG.tinMaxHeight))).spreadHorizontally().repeat(CONFIG.tinPerChunk);
	public static ConfiguredFeature<?, ?> ORE_TRUESILVER = Feature.ORE.configure(new OreFeatureConfig(OreFeatureConfig.Rules.NETHERRACK, MythicMetalsOres.TRUESILVER_ORE.getDefaultState(), CONFIG.truesilverVeinSize)).decorate(Decorator.RANGE.configure(new RangeDecoratorConfig(CONFIG.truesilverMinHeight, 0, CONFIG.truesilverMaxHeight))).spreadHorizontally().repeat(CONFIG.truesilverPerChunk);
	public static ConfiguredFeature<?, ?> ORE_UNOBTAINIUM = Feature.NO_SURFACE_ORE.configure(new OreFeatureConfig(OreFeatureConfig.Rules.BASE_STONE_OVERWORLD, MythicMetalsOres.UNOBTAINIUM_ORE.getDefaultState(), CONFIG.unobtainiumVeinSize)).decorate(Decorator.DEPTH_AVERAGE.configure(new DepthAverageDecoratorConfig(CONFIG.unobtainiumAverageHeight, CONFIG.unobtainiumSpread)).spreadHorizontally());
	public static ConfiguredFeature<?, ?> ORE_UR = Feature.ORE.configure(new OreFeatureConfig(OreFeatureConfig.Rules.NETHERRACK, MythicMetalsOres.UR_ORE.getDefaultState(), CONFIG.urVeinSize)).decorate(Decorator.RANGE.configure(new RangeDecoratorConfig(CONFIG.urMinHeight, 0, CONFIG.urMaxHeight))).spreadHorizontally().repeat(CONFIG.urPerChunk);
	public static ConfiguredFeature<?, ?> ORE_VERMICULITE = Feature.ORE.configure(new OreFeatureConfig(OreFeatureConfig.Rules.BASE_STONE_OVERWORLD, MythicMetalsOres.VERMICULITE_ORE.getDefaultState(), CONFIG.vermiculiteVeinSize)).decorate(Decorator.RANGE.configure(new RangeDecoratorConfig(CONFIG.vermiculiteMinHeight, 0, CONFIG.vermiculiteMaxHeight))).spreadHorizontally().repeat(CONFIG.vermiculitePerChunk);
	public static ConfiguredFeature<?, ?> ORE_ZINC = Feature.ORE.configure(new OreFeatureConfig(OreFeatureConfig.Rules.BASE_STONE_OVERWORLD, MythicMetalsOres.ZINC_ORE.getDefaultState(), CONFIG.zincVeinSize)).decorate(Decorator.RANGE.configure(new RangeDecoratorConfig(CONFIG.zincMinHeight, 0, CONFIG.zincMaxHeight))).spreadHorizontally().repeat(CONFIG.zincPerChunk);

	// Add keys for features
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


	public static void init() {
		//Register keys on init
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
		if(CONFIG.adamantiteGeneration) {
			AddUOre(oreAdamantite);}
		if(CONFIG.aetheriumGeneration) {
			AddUOre(oreAetherium); }
		if(CONFIG.banglumGeneration) {
			AddUOre(oreBanglum); }
		if(CONFIG.carmotGeneration) {
			AddUOre(oreCarmot); }
		if(CONFIG.copperGeneration) {
			AddUOre(oreCopper); }
		if(CONFIG.kyberGeneration) {
			AddUOre(oreKyber ); }
		if(CONFIG.manganeseGeneration) {
			AddUOre(oreManganese); }
		if(CONFIG.mythrilGeneration) {
			AddUOre(oreMythril); }
		if(CONFIG.orichalcumGeneration) {
			AddUOre(oreOrichalcum); }
		if(CONFIG.osmiumGeneration) {
			AddUOre(oreOsmium); }
		if(CONFIG.platinumGeneration) {
			AddUOre(orePlatinum); }
		if(CONFIG.quadrillumGeneration) {
			AddUOre(oreQuadrillum); }
		if(CONFIG.runiteGeneration) {
			AddUOre(oreRunite); }
		if(CONFIG.silverGeneration) {
			AddUOre(oreSilver); }
		if(CONFIG.tantaliteGeneration) {
			AddUOre(oreTantalite); }
		if(CONFIG.tinGeneration) {
			AddUOre(oreTin); }
		if(CONFIG.unobtainiumGeneration) {
			AddUOre(oreUnobtainium); }
		if(CONFIG.vermiculiteGeneration) {
			AddUOre(oreVermiculite); }
		if(CONFIG.zincGeneration) {
			AddUOre(oreZinc); }
		//Nether Ores
		if(CONFIG.midasgoldGeneration) {
			AddUDecoration(oreMidasGold); }
		if(CONFIG.stormyxGeneration) {
			AddUDecoration(oreStormyx); }
		if(CONFIG.truesilverGeneration) {
			AddUDecoration(oreTruesilver); }
		if(CONFIG.urGeneration) {
			AddUDecoration(oreUr); }

		//Ocean only ores
		if(CONFIG.aquariumGeneration) {
			BiomeModifications.addFeature(BiomeSelectors.includeByKey(BiomeKeys.COLD_OCEAN, BiomeKeys.DEEP_COLD_OCEAN, BiomeKeys.DEEP_FROZEN_OCEAN, BiomeKeys.DEEP_LUKEWARM_OCEAN, BiomeKeys.DEEP_OCEAN, BiomeKeys.FROZEN_OCEAN, BiomeKeys.LUKEWARM_OCEAN, BiomeKeys.OCEAN, BiomeKeys.WARM_OCEAN), GenerationStep.Feature.UNDERGROUND_ORES, oreAquarium); }
		//Jungle only ores
		if(CONFIG.prometheumGeneration) {
			BiomeModifications.addFeature(BiomeSelectors.includeByKey(BiomeKeys.BAMBOO_JUNGLE, BiomeKeys.BAMBOO_JUNGLE_HILLS, BiomeKeys.JUNGLE, BiomeKeys.JUNGLE_EDGE, BiomeKeys.JUNGLE_HILLS, BiomeKeys.MODIFIED_JUNGLE, BiomeKeys.MODIFIED_JUNGLE_EDGE), GenerationStep.Feature.UNDERGROUND_ORES, orePrometheum); }
		//Mountain only ores
		if(CONFIG.starriteGeneration) {
			BiomeModifications.addFeature(BiomeSelectors.includeByKey(BiomeKeys.GRAVELLY_MOUNTAINS, BiomeKeys.MODIFIED_GRAVELLY_MOUNTAINS, BiomeKeys.MOUNTAINS, BiomeKeys.MOUNTAIN_EDGE, BiomeKeys.SHATTERED_SAVANNA, BiomeKeys.SHATTERED_SAVANNA_PLATEAU, BiomeKeys.SNOWY_MOUNTAINS, BiomeKeys.SNOWY_TAIGA_MOUNTAINS, BiomeKeys.TAIGA_MOUNTAINS), GenerationStep.Feature.UNDERGROUND_ORES, oreStarrite); }
		// Biomes You'll Go support
		if (FabricLoader.getInstance().isModLoaded("byg")) {
			//Oceans
			BYGGen("tropical_islands", oreAquarium);
			//Jungle
			BYGGen("araucaria_forest", orePrometheum);
			BYGGen("cherry_blossom_forest", orePrometheum);
			BYGGen("ebony_woods", orePrometheum);
			BYGGen("jacaranda_forest", orePrometheum);
			BYGGen("redwood_tropics", orePrometheum);
			BYGGen("tropical_islands", orePrometheum);
			BYGGen("tropical_fungal_rainforest", orePrometheum);
			BYGGen("tropical_rainforest", orePrometheum);
			//Mountains
			BYGGen("alpine_foothills", oreStarrite);
			BYGGen("alps", oreStarrite);
			BYGGen("black_forest_hills", oreStarrite);
			BYGGen("blue_taiga_hills", oreStarrite);
			BYGGen("bluff_peaks", oreStarrite);
			BYGGen("bluff_steeps", oreStarrite);
			BYGGen("boreal_forest_hills", oreStarrite);
			BYGGen("cika_mountains", oreStarrite);
			BYGGen("coniferous_forest_hills", oreStarrite);
			BYGGen("dover_mountains", oreStarrite);
			BYGGen("ebony_hills", oreStarrite);
			BYGGen("enchatned_forest_hills", oreStarrite);
			BYGGen("evergreen_hills", oreStarrite);
			BYGGen("grassland_plateau", oreStarrite);
			BYGGen("guiana_clearing", oreStarrite);
			BYGGen("guiana_shield", oreStarrite);
			BYGGen("jacaranda_forest_hills", oreStarrite);
			BYGGen("redwood_mountians", oreStarrite);
			BYGGen("skyris_highlands", oreStarrite);
			BYGGen("wooded_grassland_plateau", oreStarrite);
		}
		// Terrestria Support
		if (FabricLoader.getInstance().isModLoaded("terrestria")) {
			// Water
			TSGen("caldera", oreAquarium);
			TSGen("rainbow_rainforest_lake", oreAquarium);
			TSGen("volcanic_island", oreAquarium);
			TSGen("volcanic_island_shore", oreAquarium);
			// Jungle
			TSGen("oasis", orePrometheum);
			TSGen("hemlock_rainforest", orePrometheum);
			TSGen("hemlock_clearing", orePrometheum);
			TSGen("lush_redwood_forest", orePrometheum);
			TSGen("rainbow_rainforest", orePrometheum);
			TSGen("rainbow_rainforest_lake", orePrometheum);
			TSGen("volcanic_island", orePrometheum);
			// Mountains
			TSGen("caldera_foothills", oreStarrite);
			TSGen("caldera_ridge", oreStarrite);
			TSGen("outback_uluru", oreStarrite);
			TSGen("rainbow_rainforest", oreStarrite);
			TSGen("rainbow_rainforest_mountains", oreStarrite);
		}

		// Traverse Support
		if (FabricLoader.getInstance().isModLoaded("traverse")) {
			// Water
			TVGen("wooded_island", oreAquarium);
			// Jungle-ish
			TVGen("lush_swamp", orePrometheum);
			TVGen("mini_jungle", orePrometheum);
			// Mountains
			TVGen("arid_highlands", oreStarrite);
			TVGen("cliffs", oreStarrite);
			TVGen("rolling_hills", oreStarrite);
		}
	}
	public static void AddUOre(RegistryKey<ConfiguredFeature<?, ?>> ore) {
		BiomeModifications.addFeature(BiomeSelectors.all(), GenerationStep.Feature.UNDERGROUND_ORES, ore);
	}
	public static void AddUDecoration(RegistryKey<ConfiguredFeature<?, ?>> ore) {
		BiomeModifications.addFeature(BiomeSelectors.all(), GenerationStep.Feature.UNDERGROUND_DECORATION, ore);
	}
	public static void BYGGen(String path, RegistryKey<ConfiguredFeature<?, ?>> ore) {
		BiomeModifications.addFeature(BiomeSelectors.includeByKey(RegistryKey.of(Registry.BIOME_KEY, new Identifier("byg", path))), GenerationStep.Feature.UNDERGROUND_ORES, ore);
	}
	public static void TSGen(String path, RegistryKey<ConfiguredFeature<?, ?>> ore) {
		BiomeModifications.addFeature(BiomeSelectors.includeByKey(RegistryKey.of(Registry.BIOME_KEY, new Identifier("terrestria", path))), GenerationStep.Feature.UNDERGROUND_ORES, ore);
	}
	public static void TVGen(String path, RegistryKey<ConfiguredFeature<?, ?>> ore) {
		BiomeModifications.addFeature(BiomeSelectors.includeByKey(RegistryKey.of(Registry.BIOME_KEY, new Identifier("traverse", path))), GenerationStep.Feature.UNDERGROUND_ORES, ore);
	}
}