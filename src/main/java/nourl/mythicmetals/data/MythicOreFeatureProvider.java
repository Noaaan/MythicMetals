package nourl.mythicmetals.data;

import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.PlacedFeature;
import nourl.mythicmetals.blocks.MythicBlocks;
import nourl.mythicmetals.config.MythicOreConfigs;

import static nourl.mythicmetals.data.MythicOreKeys.*;
import static nourl.mythicmetals.data.MythicOreRules.*;

public class MythicOreFeatureProvider {
    public static void initConfiguredFeatures(Registerable<ConfiguredFeature<?, ?>> registerable) {
        // above 0
        OreFeatureHelper.configuredFeature(registerable, ORE_AQUARIUM, STONE_RULE, MythicBlocks.AQUARIUM.getOre(), MythicOreConfigs.AQUARIUM);
        OreFeatureHelper.configuredFeature(registerable, ORE_BANGLUM, STONE_RULE, MythicBlocks.BANGLUM.getOre(), MythicOreConfigs.BANGLUM);
        OreFeatureHelper.configuredFeature(registerable, ORE_KYBER, STONE_RULE, MythicBlocks.KYBER.getOre(), MythicOreConfigs.KYBER);
        OreFeatureHelper.configuredFeature(registerable, ORE_MANGANESE, STONE_RULE, MythicBlocks.MANGANESE.getOre(), MythicOreConfigs.MANGANESE);
        OreFeatureHelper.configuredFeature(registerable, ORE_OSMIUM, STONE_RULE, MythicBlocks.OSMIUM.getOre(), MythicOreConfigs.OSMIUM);
        OreFeatureHelper.configuredFeature(registerable, ORE_PLATINUM, STONE_RULE, MythicBlocks.PLATINUM.getOre(), MythicOreConfigs.PLATINUM);
        OreFeatureHelper.configuredFeature(registerable, ORE_QUADRILLUM, STONE_RULE, MythicBlocks.QUADRILLUM.getOre(), MythicOreConfigs.QUADRILLUM);
        OreFeatureHelper.configuredFeature(registerable, ORE_RUNITE, RUNITE_TARGETS, MythicOreConfigs.RUNITE);
        OreFeatureHelper.configuredFeature(registerable, ORE_SILVER, STONE_RULE, MythicBlocks.SILVER.getOre(), MythicOreConfigs.SILVER);
        OreFeatureHelper.configuredFeature(registerable, ORE_TIN, STONE_RULE, MythicBlocks.TIN.getOre(), MythicOreConfigs.TIN);
        // usually below 0
        OreFeatureHelper.configuredFeature(registerable, ORE_ADAMANTITE, ADAMANTITE_TARGETS, MythicOreConfigs.ADAMANTITE);
        OreFeatureHelper.configuredFeature(registerable, ORE_CALCITE_KYBER, CALCITE_RULE, MythicBlocks.KYBER.getOreVariant("calcite"), MythicOreConfigs.CALCITE_KYBER);
        OreFeatureHelper.configuredFeature(registerable, ORE_CARMOT, CARMOT_TARGETS, MythicOreConfigs.CARMOT);
        OreFeatureHelper.configuredFeature(registerable, ORE_DEEPSLATE_RUNITE, RUNITE_TARGETS, MythicOreConfigs.DEEPSLATE_RUNITE);
        OreFeatureHelper.configuredFeature(registerable, ORE_MYTHRIL, MYTHRIL_TARGETS, MythicOreConfigs.MYTHRIL);
        OreFeatureHelper.configuredFeature(registerable, ORE_MORKITE, MORKITE_TARGETS, MythicOreConfigs.MORKITE);
        OreFeatureHelper.configuredFeature(registerable, ORE_ORICHALCUM, ORICHALCUM_TARGETS, MythicOreConfigs.ORICHALCUM);
        OreFeatureHelper.configuredFeature(registerable, ORE_PROMETHEUM, PROMETHEUM_TARGETS, MythicOreConfigs.PROMETHEUM);
        OreFeatureHelper.configuredFeature(registerable, ORE_STARRITE, STARRITE_TARGETS, MythicOreConfigs.STARRITE);
        OreFeatureHelper.configuredFeature(registerable, ORE_UNOBTAINIUM, UNOBTAINIUM_TARGETS, MythicOreConfigs.UNOBTAINIUM);
        // nether
        OreFeatureHelper.configuredFeature(registerable, ORE_NETHER_BANGLUM, NETHERRACK_RULE, MythicBlocks.BANGLUM.getOreVariant("nether"), MythicOreConfigs.NETHER_BANGLUM);
        OreFeatureHelper.configuredFeature(registerable, ORE_MIDAS_GOLD, NETHERRACK_RULE, MythicBlocks.MIDAS_GOLD.getOre(), MythicOreConfigs.MIDAS_GOLD);
        OreFeatureHelper.configuredFeature(registerable, ORE_PALLADIUM, NETHERRACK_RULE, MythicBlocks.PALLADIUM.getOre(), MythicOreConfigs.PALLADIUM);
        OreFeatureHelper.configuredFeature(registerable, ORE_STORMYX, STORMYX_TARGETS, MythicOreConfigs.STORMYX);
        // end ore
        OreFeatureHelper.configuredFeature(registerable, ORE_END_STARRITE, END_STONE_RULE, MythicBlocks.STARRITE.getOreVariant("end_stone"), MythicOreConfigs.END_STARRITE);
    }

    public static void initPlacedFeatures(Registerable<PlacedFeature> registerable) {
        var featureLookup = registerable.getRegistryLookup(RegistryKeys.CONFIGURED_FEATURE);
        OreFeatureHelper.create(registerable, featureLookup.getOrThrow(ORE_AQUARIUM).registryKey(), AQUARIUM, MythicOreConfigs.AQUARIUM);
        OreFeatureHelper.create(registerable, featureLookup.getOrThrow(ORE_BANGLUM).registryKey(), BANGLUM, MythicOreConfigs.BANGLUM);
        OreFeatureHelper.create(registerable, featureLookup.getOrThrow(ORE_KYBER).registryKey(), KYBER, MythicOreConfigs.KYBER);
        OreFeatureHelper.create(registerable, featureLookup.getOrThrow(ORE_MANGANESE).registryKey(), MANGANESE, MythicOreConfigs.MANGANESE);
        OreFeatureHelper.create(registerable, featureLookup.getOrThrow(ORE_OSMIUM).registryKey(), OSMIUM, MythicOreConfigs.OSMIUM);
        OreFeatureHelper.create(registerable, featureLookup.getOrThrow(ORE_PLATINUM).registryKey(), PLATINUM, MythicOreConfigs.PLATINUM);
        OreFeatureHelper.create(registerable, featureLookup.getOrThrow(ORE_QUADRILLUM).registryKey(), QUADRILLUM, MythicOreConfigs.QUADRILLUM);
        OreFeatureHelper.create(registerable, featureLookup.getOrThrow(ORE_RUNITE).registryKey(), RUNITE, MythicOreConfigs.RUNITE);
        OreFeatureHelper.create(registerable, featureLookup.getOrThrow(ORE_SILVER).registryKey(), SILVER, MythicOreConfigs.SILVER);
        OreFeatureHelper.create(registerable, featureLookup.getOrThrow(ORE_TIN).registryKey(), TIN, MythicOreConfigs.TIN);
        OreFeatureHelper.create(registerable, featureLookup.getOrThrow(ORE_ADAMANTITE).registryKey(), ADAMANTITE, MythicOreConfigs.ADAMANTITE);
        OreFeatureHelper.create(registerable, featureLookup.getOrThrow(ORE_CALCITE_KYBER).registryKey(), CALCITE_KYBER, MythicOreConfigs.CALCITE_KYBER);
        OreFeatureHelper.create(registerable, featureLookup.getOrThrow(ORE_CARMOT).registryKey(), CARMOT, MythicOreConfigs.CARMOT);
        OreFeatureHelper.create(registerable, featureLookup.getOrThrow(ORE_DEEPSLATE_RUNITE).registryKey(), DEEPSLATE_RUNITE, MythicOreConfigs.DEEPSLATE_RUNITE);
        OreFeatureHelper.create(registerable, featureLookup.getOrThrow(ORE_MYTHRIL).registryKey(), MYTHRIL, MythicOreConfigs.MYTHRIL);
        OreFeatureHelper.create(registerable, featureLookup.getOrThrow(ORE_MORKITE).registryKey(), MORKITE, MythicOreConfigs.MORKITE);
        OreFeatureHelper.create(registerable, featureLookup.getOrThrow(ORE_ORICHALCUM).registryKey(), ORICHALCUM, MythicOreConfigs.ORICHALCUM);
        OreFeatureHelper.create(registerable, featureLookup.getOrThrow(ORE_PROMETHEUM).registryKey(), PROMETHEUM, MythicOreConfigs.PROMETHEUM);
        OreFeatureHelper.create(registerable, featureLookup.getOrThrow(ORE_STARRITE).registryKey(), STARRITE, MythicOreConfigs.STARRITE);
        OreFeatureHelper.create(registerable, featureLookup.getOrThrow(ORE_UNOBTAINIUM).registryKey(), UNOBTAINIUM, MythicOreConfigs.UNOBTAINIUM);
        OreFeatureHelper.create(registerable, featureLookup.getOrThrow(ORE_NETHER_BANGLUM).registryKey(), NETHER_BANGLUM, MythicOreConfigs.NETHER_BANGLUM);
        OreFeatureHelper.create(registerable, featureLookup.getOrThrow(ORE_MIDAS_GOLD).registryKey(), MIDAS_GOLD, MythicOreConfigs.MIDAS_GOLD);
        OreFeatureHelper.create(registerable, featureLookup.getOrThrow(ORE_PALLADIUM).registryKey(), PALLADIUM, MythicOreConfigs.PALLADIUM);
        OreFeatureHelper.create(registerable, featureLookup.getOrThrow(ORE_STORMYX).registryKey(), STORMYX, MythicOreConfigs.STORMYX);
        OreFeatureHelper.create(registerable, featureLookup.getOrThrow(ORE_END_STARRITE).registryKey(), END_STARRITE, MythicOreConfigs.END_STARRITE);

    }
}
