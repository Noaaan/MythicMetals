package nourl.mythicmetals.data;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.tag.convention.v1.ConventionalBiomeTags;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.PlacedFeature;
import nourl.mythicmetals.misc.RegistryHelper;

import static nourl.mythicmetals.MythicMetals.CONFIG;

public class MythicOreKeys {
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
        if (CONFIG.adamantite.enabled()) {
            OreFeatureHelper.ore(ADAMANTITE);
        }
        if (CONFIG.banglum.enabled()) {
            OreFeatureHelper.ore(BANGLUM);
        }
        if (CONFIG.carmot.enabled()) {
            OreFeatureHelper.ore(CARMOT);
        }
        if (CONFIG.kyber.enabled()) {
            OreFeatureHelper.ore(KYBER);
            OreFeatureHelper.ore(CALCITE_KYBER);
        }
        if (CONFIG.mythril.enabled()) {
            OreFeatureHelper.ore(MYTHRIL);
        }
        if (CONFIG.orichalcum.enabled()) {
            OreFeatureHelper.ore(ORICHALCUM);
        }
        if (CONFIG.manganese.enabled()) {
            OreFeatureHelper.ore(MANGANESE);
        }
        if (CONFIG.platinum.enabled()) {
            OreFeatureHelper.ore(PLATINUM);
        }
        if (CONFIG.quadrillum.enabled()) {
            OreFeatureHelper.ore(QUADRILLUM);
        }
        if (CONFIG.runite.enabled()) {
            OreFeatureHelper.ore(RUNITE);
            OreFeatureHelper.ore(DEEPSLATE_RUNITE);
        }
        if (CONFIG.silver.enabled()) {
            OreFeatureHelper.ore(SILVER);
        }
        if (CONFIG.starrite.enabled()) {
            OreFeatureHelper.ore(STARRITE);
        }
        if (CONFIG.endStarrite.enabled()) {
            OreFeatureHelper.endOre(END_STARRITE);
        }
        if (CONFIG.tin.enabled()) {
            OreFeatureHelper.ore(TIN);
        }
        if (CONFIG.unobtainium.enabled()) {
            OreFeatureHelper.ore(UNOBTAINIUM);
        }
        if (CONFIG.morkite.enabled()) {
            OreFeatureHelper.ore(MORKITE);
        }

        //Nether Ores
        if (CONFIG.banglum.enabled()) {
            OreFeatureHelper.netherOre(NETHER_BANGLUM);
        }
        if (CONFIG.midasGold.enabled()) {
            OreFeatureHelper.netherOre(MIDAS_GOLD);
        }
        if (CONFIG.stormyx.enabled()) {
            OreFeatureHelper.netherOre(STORMYX);
        }
        if (CONFIG.palladium.enabled()) {
            OreFeatureHelper.netherOre(PALLADIUM);
        }

        // Add Aquarium to Aquatic Biomes
        if (CONFIG.aquarium.enabled()) {
            BiomeModifications.addFeature(BiomeSelectors.tag(ConventionalBiomeTags.AQUATIC), GenerationStep.Feature.UNDERGROUND_ORES, AQUARIUM);
        }
        // Add Prometheum to hot biomes
        if (CONFIG.prometheum.enabled()) {
            BiomeModifications.addFeature(BiomeSelectors.tag(MythicTags.PROMETHEUM_BIOMES), GenerationStep.Feature.UNDERGROUND_ORES, PROMETHEUM);

            OreFeatureHelper.modBiomeOres("terralith", "hot_shrubland", PROMETHEUM);
            OreFeatureHelper.modBiomeOres("terralith", "sakura_grove", PROMETHEUM);
            OreFeatureHelper.modBiomeOres("terralith", "sakura_valley", PROMETHEUM);
        }
        // Add Osmium to mountainous biomes
        if (CONFIG.osmium.enabled()) {
            BiomeModifications.addFeature(BiomeSelectors.tag(MythicTags.OSMIUM_BIOMES), GenerationStep.Feature.UNDERGROUND_ORES, OSMIUM);

        }

    }

}
