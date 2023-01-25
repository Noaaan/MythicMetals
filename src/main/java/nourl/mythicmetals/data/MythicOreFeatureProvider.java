package nourl.mythicmetals.data;

import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.PlacedFeature;
import nourl.mythicmetals.blocks.MythicBlocks;

import static nourl.mythicmetals.MythicMetals.CONFIG;
import static nourl.mythicmetals.data.MythicOreKeys.*;
import static nourl.mythicmetals.data.MythicOreRules.*;

public class MythicOreFeatureProvider {
    public static void initConfiguredFeatures(Registerable<ConfiguredFeature<?, ?>> registerable) {
        // above 0
        OreFeatureHelper.configuredFeature(registerable, ORE_AQUARIUM, STONE_RULE, MythicBlocks.AQUARIUM.getOre(), CONFIG.aquarium);
        OreFeatureHelper.configuredFeature(registerable, ORE_BANGLUM, STONE_RULE, MythicBlocks.BANGLUM.getOre(), CONFIG.banglum);
        OreFeatureHelper.configuredFeature(registerable, ORE_KYBER, STONE_RULE, MythicBlocks.KYBER.getOre(), CONFIG.kyber);
        OreFeatureHelper.configuredFeature(registerable, ORE_MANGANESE, STONE_RULE, MythicBlocks.MANGANESE.getOre(), CONFIG.manganese);
        OreFeatureHelper.configuredFeature(registerable, ORE_OSMIUM, STONE_RULE, MythicBlocks.OSMIUM.getOre(), CONFIG.osmium);
        OreFeatureHelper.configuredFeature(registerable, ORE_PLATINUM, STONE_RULE, MythicBlocks.PLATINUM.getOre(), CONFIG.platinum);
        OreFeatureHelper.configuredFeature(registerable, ORE_QUADRILLUM, STONE_RULE, MythicBlocks.QUADRILLUM.getOre(), CONFIG.quadrillum);
        OreFeatureHelper.configuredFeature(registerable, ORE_RUNITE, RUNITE_TARGETS, CONFIG.runite);
        OreFeatureHelper.configuredFeature(registerable, ORE_SILVER, STONE_RULE, MythicBlocks.SILVER.getOre(), CONFIG.silver);
        OreFeatureHelper.configuredFeature(registerable, ORE_TIN, STONE_RULE, MythicBlocks.TIN.getOre(), CONFIG.tin);
        // usually below 0
        OreFeatureHelper.configuredFeature(registerable, ORE_ADAMANTITE, ADAMANTITE_TARGETS, CONFIG.adamantite);
        OreFeatureHelper.configuredFeature(registerable, ORE_CALCITE_KYBER, CALCITE_RULE, MythicBlocks.KYBER.getOreVariant("calcite"), CONFIG.calciteKyber);
        OreFeatureHelper.configuredFeature(registerable, ORE_CARMOT, CARMOT_TARGETS, CONFIG.carmot);
        OreFeatureHelper.configuredFeature(registerable, ORE_DEEPSLATE_RUNITE, RUNITE_TARGETS, CONFIG.deepslateRunite);
        OreFeatureHelper.configuredFeature(registerable, ORE_MYTHRIL, MYTHRIL_TARGETS, CONFIG.mythril);
        OreFeatureHelper.configuredFeature(registerable, ORE_MORKITE, MORKITE_TARGETS, CONFIG.morkite);
        OreFeatureHelper.configuredFeature(registerable, ORE_ORICHALCUM, ORICHALCUM_TARGETS, CONFIG.orichalcum);
        OreFeatureHelper.configuredFeature(registerable, ORE_PROMETHEUM, PROMETHEUM_TARGETS, CONFIG.prometheum);
        OreFeatureHelper.configuredFeature(registerable, ORE_STARRITE, STARRITE_TARGETS, CONFIG.starrite);
        OreFeatureHelper.configuredFeature(registerable, ORE_UNOBTAINIUM, UNOBTAINIUM_TARGETS, CONFIG.unobtainium);
        // nether
        OreFeatureHelper.configuredFeature(registerable, ORE_NETHER_BANGLUM, NETHERRACK_RULE, MythicBlocks.BANGLUM.getOreVariant("nether"), CONFIG.netherBanglum);
        OreFeatureHelper.configuredFeature(registerable, ORE_MIDAS_GOLD, NETHERRACK_RULE, MythicBlocks.MIDAS_GOLD.getOre(), CONFIG.midasGold);
        OreFeatureHelper.configuredFeature(registerable, ORE_PALLADIUM, NETHERRACK_RULE, MythicBlocks.PALLADIUM.getOre(), CONFIG.palladium);
        OreFeatureHelper.configuredFeature(registerable, ORE_STORMYX, STORMYX_TARGETS, CONFIG.stormyx);
        // end ore
        OreFeatureHelper.configuredFeature(registerable, ORE_END_STARRITE, END_STONE_RULE, MythicBlocks.STARRITE.getOreVariant("end_stone"), CONFIG.endStarrite);
    }

    public static void initPlacedFeatures(Registerable<PlacedFeature> registerable) {
        var featureLookup = registerable.getRegistryLookup(RegistryKeys.CONFIGURED_FEATURE);
        OreFeatureHelper.create(registerable, featureLookup.getOrThrow(ORE_AQUARIUM).registryKey(), AQUARIUM, CONFIG.aquarium);
        OreFeatureHelper.create(registerable, featureLookup.getOrThrow(ORE_BANGLUM).registryKey(), BANGLUM, CONFIG.banglum);
        OreFeatureHelper.create(registerable, featureLookup.getOrThrow(ORE_KYBER).registryKey(), KYBER, CONFIG.kyber);
        OreFeatureHelper.create(registerable, featureLookup.getOrThrow(ORE_MANGANESE).registryKey(), MANGANESE, CONFIG.manganese);
        OreFeatureHelper.create(registerable, featureLookup.getOrThrow(ORE_OSMIUM).registryKey(), OSMIUM, CONFIG.osmium);
        OreFeatureHelper.create(registerable, featureLookup.getOrThrow(ORE_PLATINUM).registryKey(), PLATINUM, CONFIG.platinum);
        OreFeatureHelper.create(registerable, featureLookup.getOrThrow(ORE_QUADRILLUM).registryKey(), QUADRILLUM, CONFIG.quadrillum);
        OreFeatureHelper.create(registerable, featureLookup.getOrThrow(ORE_RUNITE).registryKey(), RUNITE, CONFIG.runite);
        OreFeatureHelper.create(registerable, featureLookup.getOrThrow(ORE_SILVER).registryKey(), SILVER, CONFIG.silver);
        OreFeatureHelper.create(registerable, featureLookup.getOrThrow(ORE_TIN).registryKey(), TIN, CONFIG.tin);
        OreFeatureHelper.create(registerable, featureLookup.getOrThrow(ORE_ADAMANTITE).registryKey(), ADAMANTITE, CONFIG.adamantite);
        OreFeatureHelper.create(registerable, featureLookup.getOrThrow(ORE_CALCITE_KYBER).registryKey(), CALCITE_KYBER, CONFIG.calciteKyber);
        OreFeatureHelper.create(registerable, featureLookup.getOrThrow(ORE_CARMOT).registryKey(), CARMOT, CONFIG.carmot);
        OreFeatureHelper.create(registerable, featureLookup.getOrThrow(ORE_DEEPSLATE_RUNITE).registryKey(), DEEPSLATE_RUNITE, CONFIG.deepslateRunite);
        OreFeatureHelper.create(registerable, featureLookup.getOrThrow(ORE_MYTHRIL).registryKey(), MYTHRIL, CONFIG.mythril);
        OreFeatureHelper.create(registerable, featureLookup.getOrThrow(ORE_MORKITE).registryKey(), MORKITE, CONFIG.morkite);
        OreFeatureHelper.create(registerable, featureLookup.getOrThrow(ORE_ORICHALCUM).registryKey(), ORICHALCUM, CONFIG.orichalcum);
        OreFeatureHelper.create(registerable, featureLookup.getOrThrow(ORE_PROMETHEUM).registryKey(), PROMETHEUM, CONFIG.prometheum);
        OreFeatureHelper.create(registerable, featureLookup.getOrThrow(ORE_STARRITE).registryKey(), STARRITE, CONFIG.starrite);
        OreFeatureHelper.create(registerable, featureLookup.getOrThrow(ORE_UNOBTAINIUM).registryKey(), UNOBTAINIUM, CONFIG.unobtainium);
        OreFeatureHelper.create(registerable, featureLookup.getOrThrow(ORE_NETHER_BANGLUM).registryKey(), NETHER_BANGLUM, CONFIG.netherBanglum);
        OreFeatureHelper.create(registerable, featureLookup.getOrThrow(ORE_MIDAS_GOLD).registryKey(), MIDAS_GOLD, CONFIG.midasGold);
        OreFeatureHelper.create(registerable, featureLookup.getOrThrow(ORE_PALLADIUM).registryKey(), PALLADIUM, CONFIG.palladium);
        OreFeatureHelper.create(registerable, featureLookup.getOrThrow(ORE_STORMYX).registryKey(), STORMYX, CONFIG.stormyx);
        OreFeatureHelper.create(registerable, featureLookup.getOrThrow(ORE_END_STARRITE).registryKey(), END_STARRITE, CONFIG.endStarrite);

    }
}
