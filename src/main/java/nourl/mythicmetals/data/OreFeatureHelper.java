package nourl.mythicmetals.data;

import com.google.common.collect.ImmutableList;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.block.Block;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.structure.rule.RuleTest;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.placementmodifier.*;
import nourl.mythicmetals.config.OreConfig;

import java.util.List;

/**
 * A helper class for adding creating and adding ore features to the world.
 */
public class OreFeatureHelper {

    public static void ore(RegistryKey<PlacedFeature> ore) {
        BiomeModifications.addFeature(BiomeSelectors.tag(MythicTags.VALID_ORE_BIOMES), GenerationStep.Feature.UNDERGROUND_ORES, ore);
    }

    public static void netherOre(RegistryKey<PlacedFeature> ore) {
        BiomeModifications.addFeature(BiomeSelectors.foundInTheNether(), GenerationStep.Feature.UNDERGROUND_DECORATION, ore);
    }

    public static void endOre(RegistryKey<PlacedFeature> ore) {
        BiomeModifications.addFeature(BiomeSelectors.foundInTheEnd(), GenerationStep.Feature.UNDERGROUND_DECORATION, ore);
    }

    public static void modBiomeOres(String modId, String path, RegistryKey<PlacedFeature> ore) {
        if (FabricLoader.getInstance().isModLoaded(modId)) {
            BiomeModifications.addFeature(
                    BiomeSelectors.includeByKey(RegistryKey.of(RegistryKeys.BIOME, new Identifier(modId, path))),
                    GenerationStep.Feature.UNDERGROUND_ORES, ore);
        }
    }

    public static void configuredFeature(Registerable<ConfiguredFeature<?, ?>> registerable, RegistryKey<ConfiguredFeature<?, ?>> featureKey, RuleTest rule, Block oreBlock, OreConfig config) {
        ConfiguredFeatures.register(registerable, featureKey, Feature.ORE, configuredConfig(rule, oreBlock, config));
    }

    public static void configuredFeature(Registerable<ConfiguredFeature<?, ?>> registerable, RegistryKey<ConfiguredFeature<?, ?>> featureKey, ImmutableList<OreFeatureConfig.Target> target, OreConfig config) {
        ConfiguredFeatures.register(registerable, featureKey, Feature.ORE, configuredConfig(target, config));
    }

    public static OreFeatureConfig configuredConfig(RuleTest test, Block block, OreConfig config) {
        return new OreFeatureConfig(test, block.getDefaultState(), config.veinSize, config.discardChance);
    }

    public static OreFeatureConfig configuredConfig(ImmutableList<OreFeatureConfig.Target> target, OreConfig config) {
        return new OreFeatureConfig(target, config.veinSize, config.discardChance);
    }

    public static void create(Registerable<PlacedFeature> registerable, RegistryKey<ConfiguredFeature<?, ?>> configuredFeatureKey, RegistryKey<PlacedFeature> placedFeatureKey, OreConfig config) {
        var b = config.offset && config.trapezoid; // Check if both offset and trapezoid is being used at the same time.
        if (b) {
            throw new IllegalArgumentException(registerable.toString() + " cannot be offset and trapezoid at the same time.");
        }
        else if (config.offset) {
            placeAboveBottom(registerable, configuredFeatureKey, placedFeatureKey, config);
        }
        else if (config.trapezoid) {
            placeTrapezoid(registerable, configuredFeatureKey, placedFeatureKey, config);
        }
        else {
            placeUniform(registerable, configuredFeatureKey, placedFeatureKey, config);
        }
    }

    public static void placeUniform(Registerable<PlacedFeature> registerable, RegistryKey<ConfiguredFeature<?, ?>> configuredFeatureKey, RegistryKey<PlacedFeature> placedFeatureKey, OreConfig config) {
        var featureLookup = registerable.getRegistryLookup(RegistryKeys.CONFIGURED_FEATURE);
        PlacedFeatures.register(registerable, placedFeatureKey, featureLookup.getOrThrow(configuredFeatureKey), modifiersWithCount(config.perChunk, HeightRangePlacementModifier.uniform(YOffset.fixed(config.bottom), YOffset.fixed(config.top))));
    }

    public static void placeAboveBottom(Registerable<PlacedFeature> registerable, RegistryKey<ConfiguredFeature<?, ?>> configuredFeatureKey, RegistryKey<PlacedFeature> placedFeatureKey, OreConfig config) {
        var featureLookup = registerable.getRegistryLookup(RegistryKeys.CONFIGURED_FEATURE);
        PlacedFeatures.register(registerable, placedFeatureKey, featureLookup.getOrThrow(configuredFeatureKey), modifiersWithCount(config.perChunk, HeightRangePlacementModifier.uniform(YOffset.aboveBottom(config.bottom), YOffset.fixed(config.top))));
    }

    public static void placeTrapezoid(Registerable<PlacedFeature> registerable, RegistryKey<ConfiguredFeature<?, ?>> configuredFeatureKey, RegistryKey<PlacedFeature> placedFeatureKey, OreConfig config) {
        var featureLookup = registerable.getRegistryLookup(RegistryKeys.CONFIGURED_FEATURE);
        PlacedFeatures.register(registerable, placedFeatureKey, featureLookup.getOrThrow(configuredFeatureKey), modifiersWithCount(config.perChunk, HeightRangePlacementModifier.trapezoid(YOffset.fixed(config.bottom), YOffset.fixed(config.top))));
    }

    //From Mojanks OrePlacedFeatures
    private static List<PlacementModifier> modifiers(PlacementModifier countModifier, PlacementModifier heightModifier) {
        return List.of(countModifier, SquarePlacementModifier.of(), heightModifier, BiomePlacementModifier.of());
    }

    private static List<PlacementModifier> modifiersWithCount(int count, PlacementModifier heightModifier) {
        return modifiers(CountPlacementModifier.of(count), heightModifier);
    }

}
