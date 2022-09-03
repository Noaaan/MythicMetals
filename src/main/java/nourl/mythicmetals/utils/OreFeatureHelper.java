package nourl.mythicmetals.utils;

import com.google.common.collect.ImmutableList;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.block.Block;
import net.minecraft.structure.rule.RuleTest;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.placementmodifier.*;
import nourl.mythicmetals.MythicMetals;
import nourl.mythicmetals.config.MythicConfig;
import nourl.mythicmetals.config.OreConfig;

import java.util.List;

/**
 * A helper class for adding creating and adding ore features to the world.
 */
public class OreFeatureHelper {
    public static final MythicConfig CONFIG = MythicMetals.CONFIG;

    public static void ore(RegistryKey<PlacedFeature> ore) {
        var blacklist = CONFIG.blacklist.stream()
                .map(element -> (RegistryKey.of(Registry.BIOME_KEY, new Identifier(element))))
                .toList();
        BiomeModifications.addFeature(BiomeSelectors.excludeByKey(blacklist), GenerationStep.Feature.UNDERGROUND_ORES, ore);
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
                    BiomeSelectors.includeByKey(RegistryKey.of(Registry.BIOME_KEY, new Identifier(modId, path))),
                    GenerationStep.Feature.UNDERGROUND_ORES, ore);
        }
    }

    public static RegistryEntry<ConfiguredFeature<OreFeatureConfig, ?>> createConfiguredFeature(String name, ImmutableList<OreFeatureConfig.Target> targets, OreConfig config) {
        return ConfiguredFeatures.register(name, Feature.ORE, new OreFeatureConfig(targets, config.veinSize, config.discardChance));
    }

    public static RegistryEntry<ConfiguredFeature<OreFeatureConfig, ?>> createConfiguredFeature(String name, RuleTest rule, Block block, OreConfig config) {
        return ConfiguredFeatures.register(name, Feature.ORE, new OreFeatureConfig(rule, block.getDefaultState(), config.veinSize, config.discardChance));
    }

    public static RegistryEntry<PlacedFeature> create(String name, ImmutableList<OreFeatureConfig.Target> targets, OreConfig config) {
        var b = config.offset && config.trapezoid; // Check if both offset and trapezoid is being used at the same time.
        if (b) {
            throw new IllegalArgumentException(name + " cannot be offset and trapezoid at the same time.");
        }
        if (config.offset)
            return aboveBottom(name, targets, config);
        if (config.trapezoid)
            return trapezoid(name, targets, config);
        return uniform(name, targets, config);
    }

    public static RegistryEntry<PlacedFeature> create(String name, RuleTest rule, Block block, OreConfig config) {
        var b = config.offset && config.trapezoid; // Check if both offset and trapezoid is being used at the same time.
        if (b) {
            throw new IllegalArgumentException(name + " cannot be offset and use trapezoid at the same time.");
        }
        if (config.offset)
            return aboveBottom(name, rule, block, config);
        if (config.trapezoid)
            return trapezoid(name, rule, block, config);
        return uniform(name, rule, block, config);
    }

    public static RegistryEntry<PlacedFeature> uniform(String name, ImmutableList<OreFeatureConfig.Target> targets, OreConfig config) {
        var feature = createConfiguredFeature(name, targets, config);
        return PlacedFeatures.register(name, feature, modifiersWithCount(config.perChunk, HeightRangePlacementModifier.uniform(YOffset.fixed(config.bottom), YOffset.fixed(config.top))));
    }

    public static RegistryEntry<PlacedFeature> uniform(String name, RuleTest rule, Block block, OreConfig config) {
        var feature = createConfiguredFeature(name, rule, block, config);
        return PlacedFeatures.register(name, feature, modifiersWithCount(config.perChunk, HeightRangePlacementModifier.uniform(YOffset.fixed(config.bottom), YOffset.fixed(config.top))));
    }

    public static RegistryEntry<PlacedFeature> aboveBottom(String name, ImmutableList<OreFeatureConfig.Target> targets, OreConfig config) {
        var feature = createConfiguredFeature(name, targets, config);
        return PlacedFeatures.register(name, feature, modifiersWithCount(config.perChunk, HeightRangePlacementModifier.uniform(YOffset.aboveBottom(config.bottom), YOffset.fixed(config.top))));
    }

    public static RegistryEntry<PlacedFeature> aboveBottom(String name, RuleTest rule, Block block, OreConfig config) {
        var feature = createConfiguredFeature(name, rule, block, config);
        return PlacedFeatures.register(name, feature, modifiersWithCount(config.perChunk, HeightRangePlacementModifier.uniform(YOffset.aboveBottom(config.bottom), YOffset.fixed(config.top))));
    }

    public static RegistryEntry<PlacedFeature> trapezoid(String name, ImmutableList<OreFeatureConfig.Target> targets, OreConfig config) {
        var feature = createConfiguredFeature(name, targets, config);
        return PlacedFeatures.register(name, feature, modifiersWithCount(config.perChunk, HeightRangePlacementModifier.trapezoid(YOffset.fixed(config.bottom), YOffset.fixed(config.top))));
    }

    public static RegistryEntry<PlacedFeature> trapezoid(String name, RuleTest rule, Block block, OreConfig config) {
        var feature = createConfiguredFeature(name, rule, block, config);
        return PlacedFeatures.register(name, feature, modifiersWithCount(config.perChunk, HeightRangePlacementModifier.trapezoid(YOffset.fixed(config.bottom), YOffset.fixed(config.top))));
    }

    //From Mojanks OrePlacedFeatures
    private static List<PlacementModifier> modifiers(PlacementModifier countModifier, PlacementModifier heightModifier) {
        return List.of(countModifier, SquarePlacementModifier.of(), heightModifier, BiomePlacementModifier.of());
    }

    private static List<PlacementModifier> modifiersWithCount(int count, PlacementModifier heightModifier) {
        return modifiers(CountPlacementModifier.of(count), heightModifier);
    }

}
