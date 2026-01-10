package com.mythicmetals.data;

import com.mythicmetals.block.MythicBlocks;
import com.mythicmetals.config.MythicOreConfigs;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

import static com.mythicmetals.data.MythicOreFeatures.*;
import static com.mythicmetals.data.MythicOreRules.*;

public class MythicOreFeatureProvider {
    public static void initConfiguredFeatures(BootstrapContext<ConfiguredFeature<?, ?>> registerable) {
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

    public static void initPlacedFeatures(BootstrapContext<PlacedFeature> registerable) {
        var featureLookup = registerable.lookup(Registries.CONFIGURED_FEATURE);
        OreFeatureHelper.create(registerable, featureLookup.getOrThrow(ORE_AQUARIUM).key(), AQUARIUM, MythicOreConfigs.AQUARIUM);
        OreFeatureHelper.create(registerable, featureLookup.getOrThrow(ORE_BANGLUM).key(), BANGLUM, MythicOreConfigs.BANGLUM);
        OreFeatureHelper.create(registerable, featureLookup.getOrThrow(ORE_KYBER).key(), KYBER, MythicOreConfigs.KYBER);
        OreFeatureHelper.create(registerable, featureLookup.getOrThrow(ORE_MANGANESE).key(), MANGANESE, MythicOreConfigs.MANGANESE);
        OreFeatureHelper.create(registerable, featureLookup.getOrThrow(ORE_OSMIUM).key(), OSMIUM, MythicOreConfigs.OSMIUM);
        OreFeatureHelper.create(registerable, featureLookup.getOrThrow(ORE_PLATINUM).key(), PLATINUM, MythicOreConfigs.PLATINUM);
        OreFeatureHelper.create(registerable, featureLookup.getOrThrow(ORE_QUADRILLUM).key(), QUADRILLUM, MythicOreConfigs.QUADRILLUM);
        OreFeatureHelper.create(registerable, featureLookup.getOrThrow(ORE_RUNITE).key(), RUNITE, MythicOreConfigs.RUNITE);
        OreFeatureHelper.create(registerable, featureLookup.getOrThrow(ORE_SILVER).key(), SILVER, MythicOreConfigs.SILVER);
        OreFeatureHelper.create(registerable, featureLookup.getOrThrow(ORE_TIN).key(), TIN, MythicOreConfigs.TIN);
        OreFeatureHelper.create(registerable, featureLookup.getOrThrow(ORE_ADAMANTITE).key(), ADAMANTITE, MythicOreConfigs.ADAMANTITE);
        OreFeatureHelper.create(registerable, featureLookup.getOrThrow(ORE_CALCITE_KYBER).key(), CALCITE_KYBER, MythicOreConfigs.CALCITE_KYBER);
        OreFeatureHelper.create(registerable, featureLookup.getOrThrow(ORE_CARMOT).key(), CARMOT, MythicOreConfigs.CARMOT);
        OreFeatureHelper.create(registerable, featureLookup.getOrThrow(ORE_DEEPSLATE_RUNITE).key(), DEEPSLATE_RUNITE, MythicOreConfigs.DEEPSLATE_RUNITE);
        OreFeatureHelper.create(registerable, featureLookup.getOrThrow(ORE_MYTHRIL).key(), MYTHRIL, MythicOreConfigs.MYTHRIL);
        OreFeatureHelper.create(registerable, featureLookup.getOrThrow(ORE_MORKITE).key(), MORKITE, MythicOreConfigs.MORKITE);
        OreFeatureHelper.create(registerable, featureLookup.getOrThrow(ORE_ORICHALCUM).key(), ORICHALCUM, MythicOreConfigs.ORICHALCUM);
        OreFeatureHelper.create(registerable, featureLookup.getOrThrow(ORE_PROMETHEUM).key(), PROMETHEUM, MythicOreConfigs.PROMETHEUM);
        OreFeatureHelper.create(registerable, featureLookup.getOrThrow(ORE_STARRITE).key(), STARRITE, MythicOreConfigs.STARRITE);
        OreFeatureHelper.create(registerable, featureLookup.getOrThrow(ORE_UNOBTAINIUM).key(), UNOBTAINIUM, MythicOreConfigs.UNOBTAINIUM);
        OreFeatureHelper.create(registerable, featureLookup.getOrThrow(ORE_NETHER_BANGLUM).key(), NETHER_BANGLUM, MythicOreConfigs.NETHER_BANGLUM);
        OreFeatureHelper.create(registerable, featureLookup.getOrThrow(ORE_MIDAS_GOLD).key(), MIDAS_GOLD, MythicOreConfigs.MIDAS_GOLD);
        OreFeatureHelper.create(registerable, featureLookup.getOrThrow(ORE_PALLADIUM).key(), PALLADIUM, MythicOreConfigs.PALLADIUM);
        OreFeatureHelper.create(registerable, featureLookup.getOrThrow(ORE_STORMYX).key(), STORMYX, MythicOreConfigs.STORMYX);
        OreFeatureHelper.create(registerable, featureLookup.getOrThrow(ORE_END_STARRITE).key(), END_STARRITE, MythicOreConfigs.END_STARRITE);

    }
}
