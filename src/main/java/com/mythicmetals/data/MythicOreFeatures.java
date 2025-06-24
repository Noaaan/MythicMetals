package com.mythicmetals.data;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.PlacedFeature;
import com.mythicmetals.misc.RegistryHelper;

import static com.mythicmetals.MythicMetals.CONFIG;

public class MythicOreFeatures {
    public static final RegistryKey<ConfiguredFeature<?, ?>> ORE_AQUARIUM = RegistryHelper.configuredFeatureKey("ore_aquarium");
    public static final RegistryKey<ConfiguredFeature<?, ?>> ORE_BANGLUM = RegistryHelper.configuredFeatureKey("ore_banglum");
    public static final RegistryKey<ConfiguredFeature<?, ?>> ORE_KYBER = RegistryHelper.configuredFeatureKey("ore_kyber");
    public static final RegistryKey<ConfiguredFeature<?, ?>> ORE_MANGANESE = RegistryHelper.configuredFeatureKey("ore_manganese");
    public static final RegistryKey<ConfiguredFeature<?, ?>> ORE_OSMIUM = RegistryHelper.configuredFeatureKey("ore_osmium");
    public static final RegistryKey<ConfiguredFeature<?, ?>> ORE_PLATINUM = RegistryHelper.configuredFeatureKey("ore_platinum");
    public static final RegistryKey<ConfiguredFeature<?, ?>> ORE_QUADRILLUM = RegistryHelper.configuredFeatureKey("ore_quadrillum");
    public static final RegistryKey<ConfiguredFeature<?, ?>> ORE_RUNITE = RegistryHelper.configuredFeatureKey("ore_runite");
    public static final RegistryKey<ConfiguredFeature<?, ?>> ORE_SILVER = RegistryHelper.configuredFeatureKey("ore_silver");
    public static final RegistryKey<ConfiguredFeature<?, ?>> ORE_TIN = RegistryHelper.configuredFeatureKey("ore_tin");

    public static final RegistryKey<ConfiguredFeature<?, ?>> ORE_ADAMANTITE = RegistryHelper.configuredFeatureKey("ore_adamantite");
    public static final RegistryKey<ConfiguredFeature<?, ?>> ORE_CALCITE_KYBER = RegistryHelper.configuredFeatureKey("ore_calcite_kyber");
    public static final RegistryKey<ConfiguredFeature<?, ?>> ORE_CARMOT = RegistryHelper.configuredFeatureKey("ore_carmot");
    public static final RegistryKey<ConfiguredFeature<?, ?>> ORE_DEEPSLATE_RUNITE = RegistryHelper.configuredFeatureKey("ore_deepslate_runite");
    public static final RegistryKey<ConfiguredFeature<?, ?>> ORE_MORKITE = RegistryHelper.configuredFeatureKey("ore_morkite");
    public static final RegistryKey<ConfiguredFeature<?, ?>> ORE_MYTHRIL = RegistryHelper.configuredFeatureKey("ore_mythril");
    public static final RegistryKey<ConfiguredFeature<?, ?>> ORE_ORICHALCUM = RegistryHelper.configuredFeatureKey("ore_orichalcum");
    public static final RegistryKey<ConfiguredFeature<?, ?>> ORE_PROMETHEUM = RegistryHelper.configuredFeatureKey("ore_prometheum");
    public static final RegistryKey<ConfiguredFeature<?, ?>> ORE_STARRITE = RegistryHelper.configuredFeatureKey("ore_starrite");
    public static final RegistryKey<ConfiguredFeature<?, ?>> ORE_UNOBTAINIUM = RegistryHelper.configuredFeatureKey("ore_unobtainium");

    public static RegistryKey<ConfiguredFeature<?, ?>> ORE_NETHER_BANGLUM = RegistryHelper.configuredFeatureKey("ore_nether_banglum");
    public static RegistryKey<ConfiguredFeature<?, ?>> ORE_MIDAS_GOLD = RegistryHelper.configuredFeatureKey("ore_midas_gold");
    public static RegistryKey<ConfiguredFeature<?, ?>> ORE_PALLADIUM = RegistryHelper.configuredFeatureKey("ore_palladium");
    public static RegistryKey<ConfiguredFeature<?, ?>> ORE_STORMYX = RegistryHelper.configuredFeatureKey("ore_stormyx");
    public static RegistryKey<ConfiguredFeature<?, ?>> ORE_END_STARRITE = RegistryHelper.configuredFeatureKey("ore_end_starrite");

    // RegistryKeys for features
    public static final RegistryKey<PlacedFeature> ADAMANTITE = RegistryKey.of(RegistryKeys.PLACED_FEATURE, ORE_ADAMANTITE.getValue());
    public static final RegistryKey<PlacedFeature> AQUARIUM = RegistryKey.of(RegistryKeys.PLACED_FEATURE, ORE_AQUARIUM.getValue());
    public static final RegistryKey<PlacedFeature> BANGLUM = RegistryKey.of(RegistryKeys.PLACED_FEATURE, ORE_BANGLUM.getValue());
    public static final RegistryKey<PlacedFeature> NETHER_BANGLUM = RegistryKey.of(RegistryKeys.PLACED_FEATURE, ORE_NETHER_BANGLUM.getValue());
    public static final RegistryKey<PlacedFeature> CARMOT = RegistryKey.of(RegistryKeys.PLACED_FEATURE, ORE_CARMOT.getValue());
    public static final RegistryKey<PlacedFeature> CALCITE_KYBER = RegistryKey.of(RegistryKeys.PLACED_FEATURE, ORE_CALCITE_KYBER.getValue());
    public static final RegistryKey<PlacedFeature> END_STARRITE = RegistryKey.of(RegistryKeys.PLACED_FEATURE, ORE_END_STARRITE.getValue());
    public static final RegistryKey<PlacedFeature> KYBER = RegistryKey.of(RegistryKeys.PLACED_FEATURE, ORE_KYBER.getValue());
    public static final RegistryKey<PlacedFeature> MANGANESE = RegistryKey.of(RegistryKeys.PLACED_FEATURE, ORE_MANGANESE.getValue());
    public static final RegistryKey<PlacedFeature> MIDAS_GOLD = RegistryKey.of(RegistryKeys.PLACED_FEATURE, ORE_MIDAS_GOLD.getValue());
    public static final RegistryKey<PlacedFeature> MORKITE = RegistryKey.of(RegistryKeys.PLACED_FEATURE, ORE_MORKITE.getValue());
    public static final RegistryKey<PlacedFeature> MYTHRIL = RegistryKey.of(RegistryKeys.PLACED_FEATURE, ORE_MYTHRIL.getValue());
    public static final RegistryKey<PlacedFeature> ORICHALCUM = RegistryKey.of(RegistryKeys.PLACED_FEATURE, ORE_ORICHALCUM.getValue());
    public static final RegistryKey<PlacedFeature> OSMIUM = RegistryKey.of(RegistryKeys.PLACED_FEATURE, ORE_OSMIUM.getValue());
    public static final RegistryKey<PlacedFeature> PALLADIUM = RegistryKey.of(RegistryKeys.PLACED_FEATURE, ORE_PALLADIUM.getValue());
    public static final RegistryKey<PlacedFeature> PLATINUM = RegistryKey.of(RegistryKeys.PLACED_FEATURE, ORE_PLATINUM.getValue());
    public static final RegistryKey<PlacedFeature> PROMETHEUM = RegistryKey.of(RegistryKeys.PLACED_FEATURE, ORE_PROMETHEUM.getValue());
    public static final RegistryKey<PlacedFeature> QUADRILLUM = RegistryKey.of(RegistryKeys.PLACED_FEATURE, ORE_QUADRILLUM.getValue());
    public static final RegistryKey<PlacedFeature> DEEPSLATE_RUNITE = RegistryKey.of(RegistryKeys.PLACED_FEATURE, ORE_DEEPSLATE_RUNITE.getValue());
    public static final RegistryKey<PlacedFeature> RUNITE = RegistryKey.of(RegistryKeys.PLACED_FEATURE, ORE_RUNITE.getValue());
    public static final RegistryKey<PlacedFeature> SILVER = RegistryKey.of(RegistryKeys.PLACED_FEATURE, ORE_SILVER.getValue());
    public static final RegistryKey<PlacedFeature> STARRITE = RegistryKey.of(RegistryKeys.PLACED_FEATURE, ORE_STARRITE.getValue());
    public static final RegistryKey<PlacedFeature> STORMYX = RegistryKey.of(RegistryKeys.PLACED_FEATURE, ORE_STORMYX.getValue());
    public static final RegistryKey<PlacedFeature> TIN = RegistryKey.of(RegistryKeys.PLACED_FEATURE, ORE_TIN.getValue());
    public static final RegistryKey<PlacedFeature> UNOBTAINIUM = RegistryKey.of(RegistryKeys.PLACED_FEATURE, ORE_UNOBTAINIUM.getValue());

    public static void init() {

        //Overworld Ores
        if (CONFIG.adamantite()) {
            OreFeatureHelper.ore(ADAMANTITE, MythicOreBiomeTags.ADAMANTITE_BIOMES);
        }
        if (CONFIG.banglum()) {
            OreFeatureHelper.ore(BANGLUM, MythicOreBiomeTags.BANGLUM_BIOMES);
        }
        if (CONFIG.carmot()) {
            OreFeatureHelper.ore(CARMOT, MythicOreBiomeTags.CARMOT_BIOMES);
        }
        if (CONFIG.kyber()) {
            OreFeatureHelper.ore(KYBER, MythicOreBiomeTags.KYBER_BIOMES);
            OreFeatureHelper.ore(CALCITE_KYBER, MythicOreBiomeTags.CALCITE_KYBER_BIOMES);
        }
        if (CONFIG.mythril()) {
            OreFeatureHelper.ore(MYTHRIL, MythicOreBiomeTags.MYTHRIL_BIOMES);
        }
        if (CONFIG.orichalcum()) {
            OreFeatureHelper.ore(ORICHALCUM, MythicOreBiomeTags.ORICHALCUM_BIOMES);
        }
        if (CONFIG.manganese()) {
            OreFeatureHelper.ore(MANGANESE, MythicOreBiomeTags.MANGANESE_BIOMES);
        }
        if (CONFIG.platinum()) {
            OreFeatureHelper.ore(PLATINUM, MythicOreBiomeTags.PLATINUM_BIOMES);
        }
        if (CONFIG.quadrillum()) {
            OreFeatureHelper.ore(QUADRILLUM, MythicOreBiomeTags.QUADRILLUM_BIOMES);
        }
        if (CONFIG.runite()) {
            OreFeatureHelper.ore(RUNITE, MythicOreBiomeTags.RUNITE_BIOMES);
            OreFeatureHelper.ore(DEEPSLATE_RUNITE, MythicOreBiomeTags.DEEPSLATE_RUNITE_BIOMES);
        }
        if (CONFIG.silver()) {
            OreFeatureHelper.ore(SILVER, MythicOreBiomeTags.SILVER_BIOMES);
        }
        if (CONFIG.starrite()) {
            OreFeatureHelper.ore(STARRITE, MythicOreBiomeTags.STARRITE_BIOMES);
        }
        if (CONFIG.endStarrite()) {
            OreFeatureHelper.ore(END_STARRITE, MythicOreBiomeTags.END_STARRITE_BIOMES);
        }
        if (CONFIG.tin()) {
            OreFeatureHelper.ore(TIN, MythicOreBiomeTags.TIN_BIOMES);
        }
        if (CONFIG.unobtainium()) {
            OreFeatureHelper.ore(UNOBTAINIUM, MythicOreBiomeTags.UNOBTAINIUM_BIOMES);
        }
        if (CONFIG.morkite()) {
            OreFeatureHelper.ore(MORKITE, MythicOreBiomeTags.MORKITE_BIOMES);
        }

        //Nether Ores
        if (CONFIG.banglum()) {
            OreFeatureHelper.ore(NETHER_BANGLUM, MythicOreBiomeTags.NETHER_BANGLUM_BIOMES);
        }
        if (CONFIG.midasGold()) {
            OreFeatureHelper.ore(MIDAS_GOLD, MythicOreBiomeTags.MIDAS_GOLD_BIOMES);
        }
        if (CONFIG.stormyx()) {
            OreFeatureHelper.ore(STORMYX, MythicOreBiomeTags.STORMYX_BIOMES);
        }
        if (CONFIG.palladium()) {
            OreFeatureHelper.ore(PALLADIUM, MythicOreBiomeTags.PALLADIUM_BIOMES);
        }

        // Add Aquarium to Aquatic Biomes
        if (CONFIG.aquarium()) {
            BiomeModifications.addFeature(BiomeSelectors.tag(MythicOreBiomeTags.AQUARIUM_BIOMES), GenerationStep.Feature.UNDERGROUND_ORES, AQUARIUM);
        }
        // Add Prometheum to hot biomes
        if (CONFIG.prometheum()) {
            BiomeModifications.addFeature(BiomeSelectors.tag(MythicOreBiomeTags.PROMETHEUM_BIOMES), GenerationStep.Feature.UNDERGROUND_ORES, PROMETHEUM);
        }
        // Add Osmium to mountainous biomes
        if (CONFIG.osmium()) {
            BiomeModifications.addFeature(BiomeSelectors.tag(MythicOreBiomeTags.OSMIUM_BIOMES), GenerationStep.Feature.UNDERGROUND_ORES, OSMIUM);
        }
    }

}
