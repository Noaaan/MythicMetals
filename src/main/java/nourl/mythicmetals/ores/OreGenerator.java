package nourl.mythicmetals.ores;

import net.minecraft.world.gen.decorator.Decorator;
import net.minecraft.world.gen.decorator.DepthAverageDecoratorConfig;
import net.minecraft.world.gen.decorator.RangeDecoratorConfig;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;

public class OreGenerator{
	public static ConfiguredFeature<?, ?> ORE_ADAMANTITE = Feature.ORE
		      .configure(new OreFeatureConfig(OreFeatureConfig.Rules.BASE_STONE_OVERWORLD, MythicMetalsOres.ADAMANTITE_ORE.getDefaultState(),
		    	        5)) //Vein Size
.decorate(Decorator.RANGE.configure(new RangeDecoratorConfig(0, // Bottom Offset
															4, // MIN y level
														24))) // MAX y level
						   .spreadHorizontally().repeat(1); // number of veins per chunk
	public static ConfiguredFeature<?, ?> ORE_AETHERIUM = Feature.ORE.configure(new OreFeatureConfig(OreFeatureConfig.Rules.BASE_STONE_OVERWORLD, MythicMetalsOres.AETHERIUM_ORE.getDefaultState(), 5)).decorate(Decorator.RANGE.configure(new RangeDecoratorConfig(0,70,180))).spreadHorizontally().repeat(2);
	public static ConfiguredFeature<?, ?> ORE_AQUARIUM = Feature.ORE.configure(new OreFeatureConfig(OreFeatureConfig.Rules.BASE_STONE_OVERWORLD, MythicMetalsOres.AQUARIUM_ORE.getDefaultState(), 16)).decorate(Decorator.RANGE.configure(new RangeDecoratorConfig(0,6,63))).spreadHorizontally().repeat(3);
	public static ConfiguredFeature<?, ?> ORE_BANGLUM = Feature.ORE.configure(new OreFeatureConfig(OreFeatureConfig.Rules.BASE_STONE_OVERWORLD, MythicMetalsOres.BANGLUM_ORE.getDefaultState(), 6)).decorate(Decorator.RANGE.configure(new RangeDecoratorConfig(0,6,32))).spreadHorizontally().repeat(3);
	public static ConfiguredFeature<?, ?> ORE_CARMOT = Feature.ORE.configure(new OreFeatureConfig(OreFeatureConfig.Rules.BASE_STONE_OVERWORLD, MythicMetalsOres.CARMOT_ORE.getDefaultState(), 2)).decorate(Decorator.RANGE.configure(new RangeDecoratorConfig(0,6,64))).spreadHorizontally().repeat(3);
	public static ConfiguredFeature<?, ?> ORE_COPPER = Feature.ORE.configure(new OreFeatureConfig(OreFeatureConfig.Rules.BASE_STONE_OVERWORLD, MythicMetalsOres.COPPER_ORE.getDefaultState(), 12)).decorate(Decorator.RANGE.configure(new RangeDecoratorConfig(0,32,128))).spreadHorizontally().repeat(6);
	public static ConfiguredFeature<?, ?> ORE_KYBER = Feature.ORE.configure(new OreFeatureConfig(OreFeatureConfig.Rules.BASE_STONE_OVERWORLD, MythicMetalsOres.KYBER_ORE.getDefaultState(), 14)).decorate(Decorator.RANGE.configure(new RangeDecoratorConfig(0,6,12))).spreadHorizontally().repeat(2);
	public static ConfiguredFeature<?, ?> ORE_LUTETIUM = Feature.ORE.configure(new OreFeatureConfig(OreFeatureConfig.Rules.BASE_STONE_OVERWORLD, MythicMetalsOres.LUTETIUM_ORE.getDefaultState(), 5)).decorate(Decorator.RANGE.configure(new RangeDecoratorConfig(0,6,64))).spreadHorizontally().repeat(3);
	public static ConfiguredFeature<?, ?> ORE_MANGANESE = Feature.ORE.configure(new OreFeatureConfig(OreFeatureConfig.Rules.BASE_STONE_OVERWORLD, MythicMetalsOres.MANGANESE_ORE.getDefaultState(), 6)).decorate(Decorator.RANGE.configure(new RangeDecoratorConfig(0,6,48))).spreadHorizontally().repeat(4);
	public static ConfiguredFeature<?, ?> ORE_MIDAS_GOLD = Feature.ORE.configure(new OreFeatureConfig(OreFeatureConfig.Rules.NETHERRACK, MythicMetalsOres.MIDAS_GOLD_ORE.getDefaultState(), 6)).decorate(Decorator.RANGE.configure(new RangeDecoratorConfig(0,10,128))).spreadHorizontally().repeat(10);
	public static ConfiguredFeature<?, ?> ORE_MYTHRIL = Feature.ORE.configure(new OreFeatureConfig(OreFeatureConfig.Rules.BASE_STONE_OVERWORLD, MythicMetalsOres.MYTHRIL_ORE.getDefaultState(), 5)).decorate(Decorator.RANGE.configure(new RangeDecoratorConfig(0,4,24))).spreadHorizontally().repeat(1);
	public static ConfiguredFeature<?, ?> ORE_ORICHALCUM = Feature.ORE.configure(new OreFeatureConfig(OreFeatureConfig.Rules.BASE_STONE_OVERWORLD, MythicMetalsOres.ORICHALCUM_ORE.getDefaultState(), 5)).decorate(Decorator.RANGE.configure(new RangeDecoratorConfig(0,4,24))).spreadHorizontally().repeat(1);
	public static ConfiguredFeature<?, ?> ORE_OSMIUM = Feature.ORE.configure(new OreFeatureConfig(OreFeatureConfig.Rules.BASE_STONE_OVERWORLD, MythicMetalsOres.OSMIUM_ORE.getDefaultState(), 9)).decorate(Decorator.RANGE.configure(new RangeDecoratorConfig(0,6,48))).spreadHorizontally().repeat(6);
	public static ConfiguredFeature<?, ?> ORE_PLATINUM = Feature.ORE.configure(new OreFeatureConfig(OreFeatureConfig.Rules.BASE_STONE_OVERWORLD, MythicMetalsOres.PLATINUM_ORE.getDefaultState(), 6)).decorate(Decorator.RANGE.configure(new RangeDecoratorConfig(0,6,24))).spreadHorizontally().repeat(2);
	public static ConfiguredFeature<?, ?> ORE_PROMETHEUM = Feature.ORE.configure(new OreFeatureConfig(OreFeatureConfig.Rules.BASE_STONE_OVERWORLD, MythicMetalsOres.PROMETHEUM_ORE.getDefaultState(), 5)).decorate(Decorator.RANGE.configure(new RangeDecoratorConfig(0,6,24))).spreadHorizontally().repeat(2);
	public static ConfiguredFeature<?, ?> ORE_QUADRILLUM = Feature.ORE.configure(new OreFeatureConfig(OreFeatureConfig.Rules.BASE_STONE_OVERWORLD, MythicMetalsOres.QUADRILLUM_ORE.getDefaultState(), 9)).decorate(Decorator.RANGE.configure(new RangeDecoratorConfig(0,6,56))).spreadHorizontally().repeat(15);
	public static ConfiguredFeature<?, ?> ORE_RUNITE = Feature.ORE.configure(new OreFeatureConfig(OreFeatureConfig.Rules.BASE_STONE_OVERWORLD, MythicMetalsOres.RUNITE_ORE.getDefaultState(), 3)).decorate(Decorator.RANGE.configure(new RangeDecoratorConfig(0,6,32))).spreadHorizontally().repeat(5);
	public static ConfiguredFeature<?, ?> ORE_SILVER = Feature.ORE.configure(new OreFeatureConfig(OreFeatureConfig.Rules.BASE_STONE_OVERWORLD, MythicMetalsOres.SILVER_ORE.getDefaultState(), 6)).decorate(Decorator.RANGE.configure(new RangeDecoratorConfig(0,6,48))).spreadHorizontally().repeat(5);
	public static ConfiguredFeature<?, ?> ORE_STARRITE = Feature.ORE.configure(new OreFeatureConfig(OreFeatureConfig.Rules.BASE_STONE_OVERWORLD, MythicMetalsOres.STARRITE_ORE.getDefaultState(), 5)).decorate(Decorator.RANGE.configure(new RangeDecoratorConfig(0,150,200))).spreadHorizontally().repeat(2);
	public static ConfiguredFeature<?, ?> ORE_STORMYX = Feature.ORE.configure(new OreFeatureConfig(OreFeatureConfig.Rules.NETHERRACK, MythicMetalsOres.STORMYX_ORE.getDefaultState(), 8)).decorate(Decorator.RANGE.configure(new RangeDecoratorConfig(0,6,48))).spreadHorizontally().repeat(2);
	public static ConfiguredFeature<?, ?> ORE_TANTALITE = Feature.ORE.configure(new OreFeatureConfig(OreFeatureConfig.Rules.BASE_STONE_OVERWORLD, MythicMetalsOres.TANTALITE_ORE.getDefaultState(), 12)).decorate(Decorator.RANGE.configure(new RangeDecoratorConfig(0,6,48))).spreadHorizontally().repeat(3);
	public static ConfiguredFeature<?, ?> ORE_TIN = Feature.ORE.configure(new OreFeatureConfig(OreFeatureConfig.Rules.BASE_STONE_OVERWORLD, MythicMetalsOres.TIN_ORE.getDefaultState(), 8)).decorate(Decorator.RANGE.configure(new RangeDecoratorConfig(0,6,128))).spreadHorizontally().repeat(4);
	public static ConfiguredFeature<?, ?> ORE_TRUESILVER = Feature.ORE.configure(new OreFeatureConfig(OreFeatureConfig.Rules.NETHERRACK, MythicMetalsOres.TRUESILVER_ORE.getDefaultState(), 2)).decorate(Decorator.RANGE.configure(new RangeDecoratorConfig(0,6,128))).spreadHorizontally().repeat(6);
	public static ConfiguredFeature<?, ?> ORE_UNOBTAINIUM = Feature.NO_SURFACE_ORE.configure(new OreFeatureConfig(OreFeatureConfig.Rules.BASE_STONE_OVERWORLD, MythicMetalsOres.UNOBTAINIUM_ORE.getDefaultState(), 2)).decorate(Decorator.DEPTH_AVERAGE.configure(new DepthAverageDecoratorConfig(7, 24)).spreadHorizontally());
	public static ConfiguredFeature<?, ?> ORE_UR = Feature.ORE.configure(new OreFeatureConfig(OreFeatureConfig.Rules.NETHERRACK, MythicMetalsOres.UR_ORE.getDefaultState(), 8)).decorate(Decorator.RANGE.configure(new RangeDecoratorConfig(0,10,70))).spreadHorizontally().repeat(5);
	public static ConfiguredFeature<?, ?> ORE_VERMICULITE = Feature.ORE.configure(new OreFeatureConfig(OreFeatureConfig.Rules.BASE_STONE_OVERWORLD, MythicMetalsOres.VERMICULITE_ORE.getDefaultState(), 8)).decorate(Decorator.RANGE.configure(new RangeDecoratorConfig(0,6,128))).spreadHorizontally().repeat(8);
	public static ConfiguredFeature<?, ?> ORE_ZINC = Feature.ORE.configure(new OreFeatureConfig(OreFeatureConfig.Rules.BASE_STONE_OVERWORLD, MythicMetalsOres.ZINC_ORE.getDefaultState(), 6)).decorate(Decorator.RANGE.configure(new RangeDecoratorConfig(0,40,128))).spreadHorizontally().repeat(6);
	


}