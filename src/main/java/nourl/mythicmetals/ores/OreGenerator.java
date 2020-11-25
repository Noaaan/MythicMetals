package nourl.mythicmetals.ores;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.util.Identifier;
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

import nourl.mythicmetals.MythicMetalsMain;
import nourl.mythicmetals.config.MythicConfig;
import nourl.mythicmetals.registry.RegisterOres;

public class OreGenerator {
	public static final MythicConfig.MythicOreConfig CONFIG = MythicMetalsMain.CONFIG.mythores;
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
	public static ConfiguredFeature<?, ?> ORE_LUTETIUM = Feature.ORE.configure(new OreFeatureConfig(OreFeatureConfig.Rules.BASE_STONE_OVERWORLD, MythicMetalsOres.LUTETIUM_ORE.getDefaultState(), CONFIG.lutetiumVeinSize)).decorate(Decorator.RANGE.configure(new RangeDecoratorConfig(0,CONFIG.lutetiumMinHeight,CONFIG.lutetiumMaxHeight))).spreadHorizontally().repeat(CONFIG.lutetiumPerChunk);
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

	public static final MythicConfig.MythicOreConfig CONFIG2 = MythicMetalsMain.CONFIG.mythores;

	public static void init() {}

	public static void generate() {
		RegistryKey<ConfiguredFeature<?, ?>> oreAdamantite = RegistryKey.of(Registry.CONFIGURED_FEATURE_WORLDGEN, new Identifier(MythicMetalsMain.MOD_ID, "ore_adamantite"));
		RegistryKey<ConfiguredFeature<?, ?>> oreAetherium = RegistryKey.of(Registry.CONFIGURED_FEATURE_WORLDGEN, new Identifier(MythicMetalsMain.MOD_ID, "ore_aetherium"));
		RegistryKey<ConfiguredFeature<?, ?>> oreAquarium = RegistryKey.of(Registry.CONFIGURED_FEATURE_WORLDGEN, new Identifier(MythicMetalsMain.MOD_ID, "ore_aquarium"));
		RegistryKey<ConfiguredFeature<?, ?>> oreBanglum = RegistryKey.of(Registry.CONFIGURED_FEATURE_WORLDGEN, new Identifier(MythicMetalsMain.MOD_ID, "ore_banglum"));
		RegistryKey<ConfiguredFeature<?, ?>> oreCarmot = RegistryKey.of(Registry.CONFIGURED_FEATURE_WORLDGEN, new Identifier(MythicMetalsMain.MOD_ID, "ore_carmot"));
		RegistryKey<ConfiguredFeature<?, ?>> oreCopper = RegistryKey.of(Registry.CONFIGURED_FEATURE_WORLDGEN, new Identifier(MythicMetalsMain.MOD_ID, "ore_copper"));
		RegistryKey<ConfiguredFeature<?, ?>> oreKyber = RegistryKey.of(Registry.CONFIGURED_FEATURE_WORLDGEN, new Identifier(MythicMetalsMain.MOD_ID, "ore_kyber"));
		RegistryKey<ConfiguredFeature<?, ?>> oreLutetium = RegistryKey.of(Registry.CONFIGURED_FEATURE_WORLDGEN, new Identifier(MythicMetalsMain.MOD_ID, "ore_lutetium"));
		RegistryKey<ConfiguredFeature<?, ?>> oreManganese = RegistryKey.of(Registry.CONFIGURED_FEATURE_WORLDGEN, new Identifier(MythicMetalsMain.MOD_ID, "ore_manganese"));
		RegistryKey<ConfiguredFeature<?, ?>> oreMidasGold = RegistryKey.of(Registry.CONFIGURED_FEATURE_WORLDGEN, new Identifier(MythicMetalsMain.MOD_ID, "ore_midas_gold"));
		RegistryKey<ConfiguredFeature<?, ?>> oreMythril = RegistryKey.of(Registry.CONFIGURED_FEATURE_WORLDGEN, new Identifier(MythicMetalsMain.MOD_ID, "ore_mythril"));
		RegistryKey<ConfiguredFeature<?, ?>> oreOrichalcum = RegistryKey.of(Registry.CONFIGURED_FEATURE_WORLDGEN, new Identifier(MythicMetalsMain.MOD_ID, "ore_orichalcum"));
		RegistryKey<ConfiguredFeature<?, ?>> oreOsmium = RegistryKey.of(Registry.CONFIGURED_FEATURE_WORLDGEN, new Identifier(MythicMetalsMain.MOD_ID, "ore_osmium"));
		RegistryKey<ConfiguredFeature<?, ?>> orePlatinum = RegistryKey.of(Registry.CONFIGURED_FEATURE_WORLDGEN, new Identifier(MythicMetalsMain.MOD_ID, "ore_platinum"));
		RegistryKey<ConfiguredFeature<?, ?>> orePrometheum = RegistryKey.of(Registry.CONFIGURED_FEATURE_WORLDGEN, new Identifier(MythicMetalsMain.MOD_ID, "ore_prometheum"));
		RegistryKey<ConfiguredFeature<?, ?>> oreQuadrillum = RegistryKey.of(Registry.CONFIGURED_FEATURE_WORLDGEN, new Identifier(MythicMetalsMain.MOD_ID, "ore_quadrillum"));
		RegistryKey<ConfiguredFeature<?, ?>> oreRunite = RegistryKey.of(Registry.CONFIGURED_FEATURE_WORLDGEN, new Identifier(MythicMetalsMain.MOD_ID, "ore_runite"));
		RegistryKey<ConfiguredFeature<?, ?>> oreSilver = RegistryKey.of(Registry.CONFIGURED_FEATURE_WORLDGEN, new Identifier(MythicMetalsMain.MOD_ID, "ore_silver"));
		RegistryKey<ConfiguredFeature<?, ?>> oreStarrite = RegistryKey.of(Registry.CONFIGURED_FEATURE_WORLDGEN, new Identifier(MythicMetalsMain.MOD_ID, "ore_starrite"));
		RegistryKey<ConfiguredFeature<?, ?>> oreStormyx = RegistryKey.of(Registry.CONFIGURED_FEATURE_WORLDGEN, new Identifier(MythicMetalsMain.MOD_ID, "ore_stormyx"));
		RegistryKey<ConfiguredFeature<?, ?>> oreTantalite = RegistryKey.of(Registry.CONFIGURED_FEATURE_WORLDGEN, new Identifier(MythicMetalsMain.MOD_ID, "ore_tantalite"));
		RegistryKey<ConfiguredFeature<?, ?>> oreTin = RegistryKey.of(Registry.CONFIGURED_FEATURE_WORLDGEN, new Identifier(MythicMetalsMain.MOD_ID, "ore_tin"));
		RegistryKey<ConfiguredFeature<?, ?>> oreTruesilver = RegistryKey.of(Registry.CONFIGURED_FEATURE_WORLDGEN, new Identifier(MythicMetalsMain.MOD_ID, "ore_truesilver"));
		RegistryKey<ConfiguredFeature<?, ?>> oreUnobtainium = RegistryKey.of(Registry.CONFIGURED_FEATURE_WORLDGEN, new Identifier(MythicMetalsMain.MOD_ID, "ore_unobtainium"));
		RegistryKey<ConfiguredFeature<?, ?>> oreUr = RegistryKey.of(Registry.CONFIGURED_FEATURE_WORLDGEN, new Identifier(MythicMetalsMain.MOD_ID, "ore_ur"));
		RegistryKey<ConfiguredFeature<?, ?>> oreVermiculite = RegistryKey.of(Registry.CONFIGURED_FEATURE_WORLDGEN, new Identifier(MythicMetalsMain.MOD_ID, "ore_vermiculite"));
		RegistryKey<ConfiguredFeature<?, ?>> oreZinc = RegistryKey.of(Registry.CONFIGURED_FEATURE_WORLDGEN, new Identifier(MythicMetalsMain.MOD_ID, "ore_zinc"));

		//Overworld Ores
		if(CONFIG2.adamantiteGeneration) {
			BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES, oreAdamantite);}
		if(CONFIG2.banglumGeneration) {
			BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES, oreBanglum); }
		if(CONFIG2.carmotGeneration) {
			BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES, oreCarmot); }
		if(CONFIG2.copperGeneration) {
			BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES, oreCopper); }
		if(CONFIG2.kyberGeneration) {
			BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES, oreKyber ); }
		if(CONFIG2.lutetiumGeneration) {
			BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES, oreLutetium); }
		if(CONFIG2.manganeseGeneration) {
			BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES, oreManganese); }
		if(CONFIG2.mythrilGeneration) {
			BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES, oreMythril); }
		if(CONFIG2.orichalcumGeneration) {
			BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES, oreOrichalcum); }
		if(CONFIG2.osmiumGeneration) {
			BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES, oreOsmium); }
		if(CONFIG2.platinumGeneration) {
			BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES, orePlatinum); }
		if(CONFIG2.prometheumGeneration) {
			BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES, orePrometheum); }
		if(CONFIG2.quadrillumGeneration) {
			BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES, oreQuadrillum); }
		if(CONFIG2.runiteGeneration) {
			BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES, oreRunite); }
		if(CONFIG2.silverGeneration) {
			BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES, oreSilver); }
		if(CONFIG2.tantaliteGeneration) {
			BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES, oreTantalite); }
		if(CONFIG2.tinGeneration) {
			BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES, oreTin); }
		if(CONFIG2.unobtainiumGeneration) {
			BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES, oreUnobtainium); }
		if(CONFIG2.vermiculiteGeneration) {
			BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES, oreVermiculite); }
		if(CONFIG2.zincGeneration) {
			BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES, oreZinc); }
		//Nether Ores
		if(CONFIG2.midasgoldGeneration) {
			BiomeModifications.addFeature(BiomeSelectors.foundInTheNether(), GenerationStep.Feature.UNDERGROUND_DECORATION, oreMidasGold); }
		if(CONFIG2.stormyxGeneration) {
			BiomeModifications.addFeature(BiomeSelectors.foundInTheNether(), GenerationStep.Feature.UNDERGROUND_DECORATION, oreStormyx); }
		if(CONFIG2.truesilverGeneration) {
			BiomeModifications.addFeature(BiomeSelectors.foundInTheNether(), GenerationStep.Feature.UNDERGROUND_DECORATION, oreTruesilver); }
		if(CONFIG2.urGeneration) {
			BiomeModifications.addFeature(BiomeSelectors.foundInTheNether(), GenerationStep.Feature.UNDERGROUND_DECORATION, oreUr); }

		//Biome based generation
		if(CONFIG2.aetheriumGeneration) {
			BiomeModifications.addFeature(BiomeSelectors.foundInTheEnd(), GenerationStep.Feature.UNDERGROUND_ORES, oreAetherium); }
		if(CONFIG2.aquariumGeneration) {
			BiomeModifications.addFeature(BiomeSelectors.includeByKey(BiomeKeys.OCEAN), GenerationStep.Feature.UNDERGROUND_ORES, oreAquarium); }
		if(CONFIG2.starriteGeneration) {
			BiomeModifications.addFeature(BiomeSelectors.includeByKey(BiomeKeys.MOUNTAINS), GenerationStep.Feature.UNDERGROUND_ORES, oreStarrite); }
	}
}