package com.mythicmetals.data.worldgen;

import com.mythicmetals.data.MythicTags;
import com.mythicmetals.misc.RegistryHelper;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

import static com.mythicmetals.MythicMetals.CONFIG;

public class MythicOreFeatures {
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_AQUARIUM = RegistryHelper.configuredFeatureKey("ore_aquarium");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_BANGLUM = RegistryHelper.configuredFeatureKey("ore_banglum");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_KYBER = RegistryHelper.configuredFeatureKey("ore_kyber");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_MANGANESE = RegistryHelper.configuredFeatureKey("ore_manganese");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_OSMIUM = RegistryHelper.configuredFeatureKey("ore_osmium");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_PLATINUM = RegistryHelper.configuredFeatureKey("ore_platinum");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_QUADRILLUM = RegistryHelper.configuredFeatureKey("ore_quadrillum");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_RUNITE = RegistryHelper.configuredFeatureKey("ore_runite");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_SILVER = RegistryHelper.configuredFeatureKey("ore_silver");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_TIN = RegistryHelper.configuredFeatureKey("ore_tin");

    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_ADAMANTITE = RegistryHelper.configuredFeatureKey("ore_adamantite");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_CALCITE_KYBER = RegistryHelper.configuredFeatureKey("ore_calcite_kyber");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_CARMOT = RegistryHelper.configuredFeatureKey("ore_carmot");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_DEEPSLATE_RUNITE = RegistryHelper.configuredFeatureKey("ore_deepslate_runite");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_MORKITE = RegistryHelper.configuredFeatureKey("ore_morkite");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_MYTHRIL = RegistryHelper.configuredFeatureKey("ore_mythril");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_ORICHALCUM = RegistryHelper.configuredFeatureKey("ore_orichalcum");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_PROMETHEUM = RegistryHelper.configuredFeatureKey("ore_prometheum");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_STARRITE = RegistryHelper.configuredFeatureKey("ore_starrite");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_UNOBTAINIUM = RegistryHelper.configuredFeatureKey("ore_unobtainium");

    public static ResourceKey<ConfiguredFeature<?, ?>> ORE_NETHER_BANGLUM = RegistryHelper.configuredFeatureKey("ore_nether_banglum");
    public static ResourceKey<ConfiguredFeature<?, ?>> ORE_MIDAS_GOLD = RegistryHelper.configuredFeatureKey("ore_midas_gold");
    public static ResourceKey<ConfiguredFeature<?, ?>> ORE_PALLADIUM = RegistryHelper.configuredFeatureKey("ore_palladium");
    public static ResourceKey<ConfiguredFeature<?, ?>> ORE_STORMYX = RegistryHelper.configuredFeatureKey("ore_stormyx");
    public static ResourceKey<ConfiguredFeature<?, ?>> ORE_END_STARRITE = RegistryHelper.configuredFeatureKey("ore_end_starrite");

    // RegistryKeys for features
    public static final ResourceKey<PlacedFeature> ADAMANTITE = ResourceKey.create(Registries.PLACED_FEATURE, ORE_ADAMANTITE.identifier());
    public static final ResourceKey<PlacedFeature> AQUARIUM = ResourceKey.create(Registries.PLACED_FEATURE, ORE_AQUARIUM.identifier());
    public static final ResourceKey<PlacedFeature> BANGLUM = ResourceKey.create(Registries.PLACED_FEATURE, ORE_BANGLUM.identifier());
    public static final ResourceKey<PlacedFeature> NETHER_BANGLUM = ResourceKey.create(Registries.PLACED_FEATURE, ORE_NETHER_BANGLUM.identifier());
    public static final ResourceKey<PlacedFeature> CARMOT = ResourceKey.create(Registries.PLACED_FEATURE, ORE_CARMOT.identifier());
    public static final ResourceKey<PlacedFeature> CALCITE_KYBER = ResourceKey.create(Registries.PLACED_FEATURE, ORE_CALCITE_KYBER.identifier());
    public static final ResourceKey<PlacedFeature> END_STARRITE = ResourceKey.create(Registries.PLACED_FEATURE, ORE_END_STARRITE.identifier());
    public static final ResourceKey<PlacedFeature> KYBER = ResourceKey.create(Registries.PLACED_FEATURE, ORE_KYBER.identifier());
    public static final ResourceKey<PlacedFeature> MANGANESE = ResourceKey.create(Registries.PLACED_FEATURE, ORE_MANGANESE.identifier());
    public static final ResourceKey<PlacedFeature> MIDAS_GOLD = ResourceKey.create(Registries.PLACED_FEATURE, ORE_MIDAS_GOLD.identifier());
    public static final ResourceKey<PlacedFeature> MORKITE = ResourceKey.create(Registries.PLACED_FEATURE, ORE_MORKITE.identifier());
    public static final ResourceKey<PlacedFeature> MYTHRIL = ResourceKey.create(Registries.PLACED_FEATURE, ORE_MYTHRIL.identifier());
    public static final ResourceKey<PlacedFeature> ORICHALCUM = ResourceKey.create(Registries.PLACED_FEATURE, ORE_ORICHALCUM.identifier());
    public static final ResourceKey<PlacedFeature> OSMIUM = ResourceKey.create(Registries.PLACED_FEATURE, ORE_OSMIUM.identifier());
    public static final ResourceKey<PlacedFeature> PALLADIUM = ResourceKey.create(Registries.PLACED_FEATURE, ORE_PALLADIUM.identifier());
    public static final ResourceKey<PlacedFeature> PLATINUM = ResourceKey.create(Registries.PLACED_FEATURE, ORE_PLATINUM.identifier());
    public static final ResourceKey<PlacedFeature> PROMETHEUM = ResourceKey.create(Registries.PLACED_FEATURE, ORE_PROMETHEUM.identifier());
    public static final ResourceKey<PlacedFeature> QUADRILLUM = ResourceKey.create(Registries.PLACED_FEATURE, ORE_QUADRILLUM.identifier());
    public static final ResourceKey<PlacedFeature> DEEPSLATE_RUNITE = ResourceKey.create(Registries.PLACED_FEATURE, ORE_DEEPSLATE_RUNITE.identifier());
    public static final ResourceKey<PlacedFeature> RUNITE = ResourceKey.create(Registries.PLACED_FEATURE, ORE_RUNITE.identifier());
    public static final ResourceKey<PlacedFeature> SILVER = ResourceKey.create(Registries.PLACED_FEATURE, ORE_SILVER.identifier());
    public static final ResourceKey<PlacedFeature> STARRITE = ResourceKey.create(Registries.PLACED_FEATURE, ORE_STARRITE.identifier());
    public static final ResourceKey<PlacedFeature> STORMYX = ResourceKey.create(Registries.PLACED_FEATURE, ORE_STORMYX.identifier());
    public static final ResourceKey<PlacedFeature> TIN = ResourceKey.create(Registries.PLACED_FEATURE, ORE_TIN.identifier());
    public static final ResourceKey<PlacedFeature> UNOBTAINIUM = ResourceKey.create(Registries.PLACED_FEATURE, ORE_UNOBTAINIUM.identifier());

    public static void init() {

        //Overworld Ores
        if (CONFIG.adamantite()) {
            OreFeatureHelper.ore(ADAMANTITE, MythicTags.ADAMANTITE_BIOMES);
        }
        if (CONFIG.banglum()) {
            OreFeatureHelper.ore(BANGLUM, MythicTags.BANGLUM_BIOMES);
        }
        if (CONFIG.carmot()) {
            OreFeatureHelper.ore(CARMOT, MythicTags.CARMOT_BIOMES);
        }
        if (CONFIG.kyber()) {
            OreFeatureHelper.ore(KYBER, MythicTags.KYBER_BIOMES);
            OreFeatureHelper.ore(CALCITE_KYBER, MythicTags.CALCITE_KYBER_BIOMES);
        }
        if (CONFIG.mythril()) {
            OreFeatureHelper.ore(MYTHRIL, MythicTags.MYTHRIL_BIOMES);
        }
        if (CONFIG.orichalcum()) {
            OreFeatureHelper.ore(ORICHALCUM, MythicTags.ORICHALCUM_BIOMES);
        }
        if (CONFIG.manganese()) {
            OreFeatureHelper.ore(MANGANESE, MythicTags.MANGANESE_BIOMES);
        }
        if (CONFIG.platinum()) {
            OreFeatureHelper.ore(PLATINUM, MythicTags.PLATINUM_BIOMES);
        }
        if (CONFIG.quadrillum()) {
            OreFeatureHelper.ore(QUADRILLUM, MythicTags.QUADRILLUM_BIOMES);
        }
        if (CONFIG.runite()) {
            OreFeatureHelper.ore(RUNITE, MythicTags.RUNITE_BIOMES);
            OreFeatureHelper.ore(DEEPSLATE_RUNITE, MythicTags.DEEPSLATE_RUNITE_BIOMES);
        }
        if (CONFIG.silver()) {
            OreFeatureHelper.ore(SILVER, MythicTags.SILVER_BIOMES);
        }
        if (CONFIG.starrite()) {
            OreFeatureHelper.ore(STARRITE, MythicTags.STARRITE_BIOMES);
        }
        if (CONFIG.endStarrite()) {
            OreFeatureHelper.ore(END_STARRITE, MythicTags.END_STARRITE_BIOMES);
        }
        if (CONFIG.tin()) {
            OreFeatureHelper.ore(TIN, MythicTags.TIN_BIOMES);
        }
        if (CONFIG.unobtainium()) {
            OreFeatureHelper.ore(UNOBTAINIUM, MythicTags.UNOBTAINIUM_BIOMES);
        }
        if (CONFIG.morkite()) {
            OreFeatureHelper.ore(MORKITE, MythicTags.MORKITE_BIOMES);
        }

        //Nether Ores
        if (CONFIG.banglum()) {
            OreFeatureHelper.ore(NETHER_BANGLUM, MythicTags.NETHER_BANGLUM_BIOMES);
        }
        if (CONFIG.midasGold()) {
            OreFeatureHelper.ore(MIDAS_GOLD, MythicTags.MIDAS_GOLD_BIOMES);
        }
        if (CONFIG.stormyx()) {
            OreFeatureHelper.ore(STORMYX, MythicTags.STORMYX_BIOMES);
        }
        if (CONFIG.palladium()) {
            OreFeatureHelper.ore(PALLADIUM, MythicTags.PALLADIUM_BIOMES);
        }

        // Add Aquarium to Aquatic Biomes
        if (CONFIG.aquarium()) {
            BiomeModifications.addFeature(BiomeSelectors.tag(MythicTags.AQUARIUM_BIOMES), GenerationStep.Decoration.UNDERGROUND_ORES, AQUARIUM);
        }
        // Add Prometheum to hot biomes
        if (CONFIG.prometheum()) {
            BiomeModifications.addFeature(BiomeSelectors.tag(MythicTags.PROMETHEUM_BIOMES), GenerationStep.Decoration.UNDERGROUND_ORES, PROMETHEUM);
        }
        // Add Osmium to mountainous biomes
        if (CONFIG.osmium()) {
            BiomeModifications.addFeature(BiomeSelectors.tag(MythicTags.OSMIUM_BIOMES), GenerationStep.Decoration.UNDERGROUND_ORES, OSMIUM);
        }
    }

}
