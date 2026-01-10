package com.mythicmetals.data;


import com.google.common.collect.ImmutableList;
import com.mythicmetals.config.OreConfig;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.placement.PlacementUtils;

import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.placement.BiomeFilter;
import net.minecraft.world.level.levelgen.placement.CountPlacement;
import net.minecraft.world.level.levelgen.placement.HeightRangePlacement;
import net.minecraft.world.level.levelgen.placement.InSquarePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import java.util.List;

/**
 * A helper class for adding creating and adding ore features to the world.
 */
public class OreFeatureHelper {

    public static void ore(ResourceKey<PlacedFeature> ore, TagKey<Biome> tag) {
        BiomeModifications.addFeature(BiomeSelectors.tag(tag), GenerationStep.Decoration.UNDERGROUND_ORES, ore);
    }

    public static void configuredFeature(BootstrapContext<ConfiguredFeature<?, ?>> registerable, ResourceKey<ConfiguredFeature<?, ?>> featureKey, RuleTest rule, Block oreBlock, OreConfig config) {
        FeatureUtils.register(registerable, featureKey, Feature.ORE, configuredConfig(rule, oreBlock, config));
    }

    public static void configuredFeature(BootstrapContext<ConfiguredFeature<?, ?>> registerable, ResourceKey<ConfiguredFeature<?, ?>> featureKey, ImmutableList<OreConfiguration.TargetBlockState> target, OreConfig config) {
        FeatureUtils.register(registerable, featureKey, Feature.ORE, configuredConfig(target, config));
    }

    public static OreConfiguration configuredConfig(RuleTest test, Block block, OreConfig config) {
        return new OreConfiguration(test, block.defaultBlockState(), config.veinSize, config.discardChance);
    }

    public static OreConfiguration configuredConfig(ImmutableList<OreConfiguration.TargetBlockState> target, OreConfig config) {
        return new OreConfiguration(target, config.veinSize, config.discardChance);
    }

    public static void create(BootstrapContext<PlacedFeature> registerable, ResourceKey<ConfiguredFeature<?, ?>> configuredFeatureKey, ResourceKey<PlacedFeature> placedFeatureKey, OreConfig config) {
        var b = config.offset && config.trapezoid; // Check if both offset and trapezoid is being used at the same time.
        if (b) {
            throw new IllegalArgumentException(registerable.toString() + " cannot be offset and trapezoid at the same time.");
        } else if (config.offset) {
            placeAboveBottom(registerable, configuredFeatureKey, placedFeatureKey, config);
        } else if (config.trapezoid) {
            placeTrapezoid(registerable, configuredFeatureKey, placedFeatureKey, config);
        } else {
            placeUniform(registerable, configuredFeatureKey, placedFeatureKey, config);
        }
    }

    public static void placeUniform(BootstrapContext<PlacedFeature> registerable, ResourceKey<ConfiguredFeature<?, ?>> configuredFeatureKey, ResourceKey<PlacedFeature> placedFeatureKey, OreConfig config) {
        var featureLookup = registerable.lookup(Registries.CONFIGURED_FEATURE);
        PlacementUtils.register(registerable, placedFeatureKey, featureLookup.getOrThrow(configuredFeatureKey), modifiersWithCount(config.perChunk, HeightRangePlacement.uniform(VerticalAnchor.absolute(config.bottom), VerticalAnchor.absolute(config.top))));
    }

    public static void placeAboveBottom(BootstrapContext<PlacedFeature> registerable, ResourceKey<ConfiguredFeature<?, ?>> configuredFeatureKey, ResourceKey<PlacedFeature> placedFeatureKey, OreConfig config) {
        var featureLookup = registerable.lookup(Registries.CONFIGURED_FEATURE);
        PlacementUtils.register(registerable, placedFeatureKey, featureLookup.getOrThrow(configuredFeatureKey), modifiersWithCount(config.perChunk, HeightRangePlacement.uniform(VerticalAnchor.aboveBottom(config.bottom), VerticalAnchor.absolute(config.top))));
    }

    public static void placeTrapezoid(BootstrapContext<PlacedFeature> registerable, ResourceKey<ConfiguredFeature<?, ?>> configuredFeatureKey, ResourceKey<PlacedFeature> placedFeatureKey, OreConfig config) {
        var featureLookup = registerable.lookup(Registries.CONFIGURED_FEATURE);
        PlacementUtils.register(registerable, placedFeatureKey, featureLookup.getOrThrow(configuredFeatureKey), modifiersWithCount(config.perChunk, HeightRangePlacement.triangle(VerticalAnchor.absolute(config.bottom), VerticalAnchor.absolute(config.top))));
    }

    //From Mojanks OrePlacedFeatures
    private static List<PlacementModifier> modifiers(PlacementModifier countModifier, PlacementModifier heightModifier) {
        return List.of(countModifier, InSquarePlacement.spread(), heightModifier, BiomeFilter.biome());
    }

    private static List<PlacementModifier> modifiersWithCount(int count, PlacementModifier heightModifier) {
        return modifiers(CountPlacement.of(count), heightModifier);
    }

}
