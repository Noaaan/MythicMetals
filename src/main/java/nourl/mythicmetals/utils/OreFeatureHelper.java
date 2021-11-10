package nourl.mythicmetals.utils;

import com.google.common.collect.ImmutableList;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.block.BlockState;
import net.minecraft.structure.rule.RuleTest;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.decorator.Decorator;
import net.minecraft.world.gen.decorator.RangeDecoratorConfig;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.heightprovider.UniformHeightProvider;
import nourl.mythicmetals.MythicMetals;
import nourl.mythicmetals.config.MythicConfig;
import nourl.mythicmetals.config.OreConfig;
import nourl.mythicmetals.config.VariantConfig;

@SuppressWarnings("unused")
public class OreFeatureHelper {
    public static final MythicConfig CONFIG = MythicMetals.CONFIG;

    public static void ore(RegistryKey<ConfiguredFeature<?, ?>> ore) {
        var blacklist = CONFIG.blacklist.stream()
                .map(element -> (RegistryKey.of(Registry.BIOME_KEY, new Identifier(element))))
                .toList();
        BiomeModifications.addFeature(BiomeSelectors.excludeByKey(blacklist), GenerationStep.Feature.UNDERGROUND_ORES, ore);
    }

    public static void netherOre(RegistryKey<ConfiguredFeature<?, ?>> ore) {
        BiomeModifications.addFeature(BiomeSelectors.foundInTheNether(), GenerationStep.Feature.UNDERGROUND_DECORATION, ore);
    }

    public static void endOre(RegistryKey<ConfiguredFeature<?, ?>> ore) {
        BiomeModifications.addFeature(BiomeSelectors.foundInTheEnd(), GenerationStep.Feature.UNDERGROUND_DECORATION, ore);
    }

    public static void modBiomeOres(String modId, String path, RegistryKey<ConfiguredFeature<?, ?>> ore) {
        if (FabricLoader.getInstance().isModLoaded(modId)) {
            BiomeModifications.addFeature(BiomeSelectors.includeByKey(RegistryKey.of(Registry.BIOME_KEY, new Identifier(modId, path))), GenerationStep.Feature.UNDERGROUND_ORES, ore);
        }
    }

    public static ConfiguredFeature<?, ?> fixedOre(RuleTest rule, BlockState defaultState, int veinSize, int minHeight, int maxHeight, int spawnRate, float discardChance) {
        return Feature.ORE.configure(new OreFeatureConfig(rule, defaultState, veinSize, discardChance)).decorate(Decorator.RANGE.configure(new RangeDecoratorConfig(UniformHeightProvider.create(YOffset.fixed(minHeight), YOffset.fixed(maxHeight))))).spreadHorizontally().repeat(spawnRate);
    }

    public static ConfiguredFeature<?, ?> fixedOre(RuleTest rule, BlockState defaultState, OreConfig config) {
        return Feature.ORE.configure(new OreFeatureConfig(rule, defaultState, config.veinSize, config.discardChance)).decorate(Decorator.RANGE.configure(new RangeDecoratorConfig(UniformHeightProvider.create(YOffset.fixed(config.bottom), YOffset.fixed(config.top))))).spreadHorizontally().repeat(config.perChunk);
    }

    public static ConfiguredFeature<?, ?> fixedOre(RuleTest rule, BlockState defaultState, VariantConfig config) {
        return Feature.ORE.configure(new OreFeatureConfig(rule, defaultState, config.veinSize, config.discardChance)).decorate(Decorator.RANGE.configure(new RangeDecoratorConfig(UniformHeightProvider.create(YOffset.fixed(config.bottom), YOffset.fixed(config.top))))).spreadHorizontally().repeat(config.perChunk);
    }

    public static ConfiguredFeature<?, ?> bottomOffsetOre(RuleTest rule, BlockState state, int veinSize, int bottomOffset, int maxHeight, int spawnRate, float discardChance) {
        return Feature.ORE.configure(new OreFeatureConfig(rule, state, veinSize, discardChance)).decorate(Decorator.RANGE.configure(new RangeDecoratorConfig(UniformHeightProvider.create(YOffset.aboveBottom(bottomOffset), YOffset.fixed(maxHeight))))).spreadHorizontally().repeat(spawnRate);
    }

    public static ConfiguredFeature<?, ?> bottomOffsetOre(RuleTest rule, BlockState state, OreConfig config) {
        return Feature.ORE.configure(new OreFeatureConfig(rule, state, config.veinSize, config.discardChance)).decorate(Decorator.RANGE.configure(new RangeDecoratorConfig(UniformHeightProvider.create(YOffset.aboveBottom(config.bottom), YOffset.fixed(config.top))))).spreadHorizontally().repeat(config.perChunk);
    }

    public static ConfiguredFeature<?, ?> bottomOffsetOre(ImmutableList<OreFeatureConfig.Target> targets, int veinSize, int bottomOffset, int maxHeight, int spawnRate, float discardChance) {
        return Feature.ORE.configure(new OreFeatureConfig(targets, veinSize, discardChance)).decorate(Decorator.RANGE.configure(new RangeDecoratorConfig(UniformHeightProvider.create(YOffset.aboveBottom(bottomOffset), YOffset.fixed(maxHeight))))).spreadHorizontally().repeat(spawnRate);
    }

    public static ConfiguredFeature<?, ?> bottomOffsetOre(ImmutableList<OreFeatureConfig.Target> targets, OreConfig config) {
        return Feature.ORE.configure(new OreFeatureConfig(targets, config.veinSize, config.discardChance)).decorate(Decorator.RANGE.configure(new RangeDecoratorConfig(UniformHeightProvider.create(YOffset.aboveBottom(config.bottom), YOffset.fixed(config.top))))).spreadHorizontally().repeat(config.perChunk);
    }

    public static ConfiguredFeature<?, ?> topOffsetOre(RuleTest rule, BlockState state, int veinSize, int minHeight, int topOffset, int spawnRate, float discardChance) {
        return Feature.ORE.configure(new OreFeatureConfig(rule, state, veinSize, discardChance)).decorate(Decorator.RANGE.configure(new RangeDecoratorConfig(UniformHeightProvider.create(YOffset.fixed(minHeight), YOffset.belowTop(topOffset))))).spreadHorizontally().repeat(spawnRate);
    }

    public static ConfiguredFeature<?, ?> topOffsetOre(RuleTest rule, BlockState state, OreConfig config) {
        return Feature.ORE.configure(new OreFeatureConfig(rule, state, config.veinSize, config.discardChance)).decorate(Decorator.RANGE.configure(new RangeDecoratorConfig(UniformHeightProvider.create(YOffset.fixed(config.bottom), YOffset.belowTop(config.top))))).spreadHorizontally().repeat(config.perChunk);
    }

    public static ConfiguredFeature<?, ?> topOffsetOre(ImmutableList<OreFeatureConfig.Target> targets, int veinSize, int minHeight, int topOffset, int spawnRate, float discardChance) {
        return Feature.ORE.configure(new OreFeatureConfig(targets, veinSize, discardChance)).decorate(Decorator.RANGE.configure(new RangeDecoratorConfig(UniformHeightProvider.create(YOffset.fixed(minHeight), YOffset.belowTop(topOffset))))).spreadHorizontally().repeat(spawnRate);
    }

    public static ConfiguredFeature<?, ?> topOffsetOre(ImmutableList<OreFeatureConfig.Target> targets, OreConfig config) {
        return Feature.ORE.configure(new OreFeatureConfig(targets, config.veinSize, config.discardChance)).decorate(Decorator.RANGE.configure(new RangeDecoratorConfig(UniformHeightProvider.create(YOffset.fixed(config.bottom), YOffset.belowTop(config.top))))).spreadHorizontally().repeat(config.perChunk);
    }

    public static ConfiguredFeature<?, ?> topOffsetOre(ImmutableList<OreFeatureConfig.Target> targets, VariantConfig config) {
        return Feature.ORE.configure(new OreFeatureConfig(targets, config.veinSize, config.discardChance)).decorate(Decorator.RANGE.configure(new RangeDecoratorConfig(UniformHeightProvider.create(YOffset.fixed(config.bottom), YOffset.belowTop(config.top))))).spreadHorizontally().repeat(config.perChunk);
    }

    public static ConfiguredFeature<?, ?> triangleOre(RuleTest rule, BlockState defaultState, int veinSize, int minHeight, int maxHeight, int spawnRate, float discardChance) {
        return Feature.ORE.configure(new OreFeatureConfig(rule, defaultState, veinSize, discardChance)).triangleRange(YOffset.fixed(minHeight), YOffset.fixed(maxHeight)).spreadHorizontally().repeat(spawnRate);
    }

    public static ConfiguredFeature<?, ?> triangleOre(RuleTest rule, BlockState defaultState, OreConfig config) {
        return Feature.ORE.configure(new OreFeatureConfig(rule, defaultState, config.veinSize, config.discardChance)).triangleRange(YOffset.fixed(config.bottom), YOffset.fixed(config.top)).spreadHorizontally().repeat(config.perChunk);
    }

    public static ConfiguredFeature<?, ?> triangleOre(ImmutableList<OreFeatureConfig.Target> targets, int veinSize, int minHeight, int maxHeight, int spawnRate, float discardChance) {
        return Feature.ORE.configure(new OreFeatureConfig(targets, veinSize, discardChance)).triangleRange(YOffset.fixed(minHeight), YOffset.fixed(maxHeight)).spreadHorizontally().repeat(spawnRate);
    }

    public static ConfiguredFeature<?, ?> triangleOre(ImmutableList<OreFeatureConfig.Target> targets, OreConfig config) {
        return Feature.ORE.configure(new OreFeatureConfig(targets, config.veinSize, config.discardChance)).triangleRange(YOffset.fixed(config.bottom), YOffset.fixed(config.top)).spreadHorizontally().repeat(config.perChunk);
    }

    public static ConfiguredFeature<?, ?> scatteredOre(ImmutableList<OreFeatureConfig.Target> targets, OreConfig config) {
        return Feature.SCATTERED_ORE.configure(new OreFeatureConfig(targets, config.veinSize, config.discardChance)).triangleRange(YOffset.aboveBottom(config.bottom), YOffset.fixed(config.top)).spreadHorizontally();
    }

}
