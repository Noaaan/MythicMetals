package nourl.mythicmetals.world;

import com.google.common.collect.ImmutableList;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.tag.convention.v1.ConventionalBiomeTags;
import net.minecraft.block.Blocks;
import net.minecraft.structure.rule.BlockMatchRuleTest;
import net.minecraft.structure.rule.RuleTest;
import net.minecraft.structure.rule.TagMatchRuleTest;
import net.minecraft.tag.BiomeTags;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.feature.PlacedFeature;
import nourl.mythicmetals.MythicMetals;
import nourl.mythicmetals.blocks.MythicBlocks;
import nourl.mythicmetals.config.MythicConfig;
import nourl.mythicmetals.registry.RegisterTags;
import nourl.mythicmetals.utils.OreFeatureHelper;

@SuppressWarnings("ALL")
public class MythicOreFeatures {
    // Defines the config that applies to the ore features
    public static final MythicConfig CONFIG = MythicMetals.CONFIG;

    // Defines new RuleTest(s), which checks what blocks an ore can spawn in
    public static final RuleTest BLACKSTONE_RULE = new BlockMatchRuleTest(Blocks.BLACKSTONE);
    public static final RuleTest CALCITE_RULE = new BlockMatchRuleTest(Blocks.CALCITE);
    public static final RuleTest NETHERRACK_RULE = new BlockMatchRuleTest(Blocks.NETHERRACK);
    public static final RuleTest DEEPSLATE_RULE = new TagMatchRuleTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES);
    public static final RuleTest END_STONE_RULE = new BlockMatchRuleTest(Blocks.END_STONE);
    public static final RuleTest SMOOTH_BASALT_RULE = new BlockMatchRuleTest(Blocks.SMOOTH_BASALT);
    public static final RuleTest STONE_RULE = new TagMatchRuleTest(BlockTags.STONE_ORE_REPLACEABLES);
    public static final RuleTest TUFF_RULE = new BlockMatchRuleTest(Blocks.TUFF);


    // Defines a list of targets, which can check for multiple blocks and dynamically replace them when generating ore
    public static final ImmutableList<OreFeatureConfig.Target> ADAMANTITE_TARGETS = ImmutableList.of(OreFeatureConfig.createTarget(STONE_RULE, MythicBlocks.ADAMANTITE.getOre().getDefaultState()), OreFeatureConfig.createTarget(DEEPSLATE_RULE, MythicBlocks.ADAMANTITE.getOreVariant("deepslate").getDefaultState()));
    public static final ImmutableList<OreFeatureConfig.Target> CARMOT_TARGETS = ImmutableList.of(OreFeatureConfig.createTarget(STONE_RULE, MythicBlocks.CARMOT.getOre().getDefaultState()), OreFeatureConfig.createTarget(DEEPSLATE_RULE, MythicBlocks.CARMOT.getOreVariant("deepslate").getDefaultState()));
    public static final ImmutableList<OreFeatureConfig.Target> MYTHRIL_TARGETS = ImmutableList.of(OreFeatureConfig.createTarget(STONE_RULE, MythicBlocks.MYTHRIL.getOre().getDefaultState()), OreFeatureConfig.createTarget(DEEPSLATE_RULE, MythicBlocks.MYTHRIL.getOreVariant("deepslate").getDefaultState()));
    public static final ImmutableList<OreFeatureConfig.Target> MORKITE_TARGETS = ImmutableList.of(OreFeatureConfig.createTarget(STONE_RULE, MythicBlocks.MORKITE.getOre().getDefaultState()), OreFeatureConfig.createTarget(DEEPSLATE_RULE, MythicBlocks.MORKITE.getOreVariant("deepslate").getDefaultState()));
    public static final ImmutableList<OreFeatureConfig.Target> ORICHALCUM_TARGETS = ImmutableList.of(OreFeatureConfig.createTarget(STONE_RULE, MythicBlocks.ORICHALCUM.getOre().getDefaultState()), OreFeatureConfig.createTarget(TUFF_RULE, MythicBlocks.ORICHALCUM.getOreVariant("tuff").getDefaultState()), OreFeatureConfig.createTarget(SMOOTH_BASALT_RULE, MythicBlocks.ORICHALCUM.getOreVariant("smooth_basalt").getDefaultState()), OreFeatureConfig.createTarget(DEEPSLATE_RULE, MythicBlocks.ORICHALCUM.getOreVariant("deepslate").getDefaultState()));
    public static final ImmutableList<OreFeatureConfig.Target> OVERWORLD_NETHER_ORE_TARGETS = ImmutableList.of(OreFeatureConfig.createTarget(NETHERRACK_RULE, MythicBlocks.MIDAS_GOLD.getOre().getDefaultState()), OreFeatureConfig.createTarget(BLACKSTONE_RULE, MythicBlocks.STORMYX.getOreVariant("blackstone").getDefaultState()));
    public static final ImmutableList<OreFeatureConfig.Target> PROMETHEUM_TARGETS = ImmutableList.of(OreFeatureConfig.createTarget(STONE_RULE, MythicBlocks.PROMETHEUM.getOre().getDefaultState()), OreFeatureConfig.createTarget(DEEPSLATE_RULE, MythicBlocks.PROMETHEUM.getOreVariant("deepslate").getDefaultState()));
    public static final ImmutableList<OreFeatureConfig.Target> RUNITE_TARGETS = ImmutableList.of(OreFeatureConfig.createTarget(STONE_RULE, MythicBlocks.RUNITE.getOre().getDefaultState()), OreFeatureConfig.createTarget(DEEPSLATE_RULE, MythicBlocks.RUNITE.getOreVariant("deepslate").getDefaultState()));
    public static final ImmutableList<OreFeatureConfig.Target> STARRITE_TARGETS = ImmutableList.of(OreFeatureConfig.createTarget(STONE_RULE, MythicBlocks.STARRITE.getOre().getDefaultState()), OreFeatureConfig.createTarget(CALCITE_RULE, MythicBlocks.STARRITE.getOreVariant("calcite").getDefaultState()));
    public static final ImmutableList<OreFeatureConfig.Target> STORMYX_TARGETS = ImmutableList.of(OreFeatureConfig.createTarget(NETHERRACK_RULE, MythicBlocks.STORMYX.getOre().getDefaultState()), OreFeatureConfig.createTarget(BLACKSTONE_RULE, MythicBlocks.STORMYX.getOreVariant("blackstone").getDefaultState()));
    public static final ImmutableList<OreFeatureConfig.Target> UNOBTAINIUM_TARGETS = ImmutableList.of(OreFeatureConfig.createTarget(STONE_RULE, MythicBlocks.UNOBTAINIUM.getOre().getDefaultState()), OreFeatureConfig.createTarget(DEEPSLATE_RULE, MythicBlocks.UNOBTAINIUM.getOreVariant("deepslate").getDefaultState()));

    // Placed ore features
    public static RegistryEntry<PlacedFeature> ORE_AQUARIUM = OreFeatureHelper.create("ore_aquarium", STONE_RULE, MythicBlocks.AQUARIUM.getOre(), CONFIG.aquarium);
    public static RegistryEntry<PlacedFeature> ORE_BANGLUM = OreFeatureHelper.create("ore_banglum", STONE_RULE, MythicBlocks.BANGLUM.getOre(), CONFIG.banglum);
    public static RegistryEntry<PlacedFeature> ORE_KYBER = OreFeatureHelper.create("ore_kyber", STONE_RULE, MythicBlocks.KYBER.getOre(), CONFIG.kyber);
    public static RegistryEntry<PlacedFeature> ORE_MANGANESE = OreFeatureHelper.create("ore_manganese", STONE_RULE, MythicBlocks.MANGANESE.getOre(), CONFIG.manganese);
    public static RegistryEntry<PlacedFeature> ORE_OSMIUM = OreFeatureHelper.create("ore_osmium", STONE_RULE, MythicBlocks.OSMIUM.getOre(), CONFIG.osmium);
    public static RegistryEntry<PlacedFeature> ORE_PLATINUM = OreFeatureHelper.create("ore_platinum", STONE_RULE, MythicBlocks.PLATINUM.getOre(), CONFIG.platinum);
    public static RegistryEntry<PlacedFeature> ORE_QUADRILLUM = OreFeatureHelper.create("ore_quadrillum", STONE_RULE, MythicBlocks.QUADRILLUM.getOre(), CONFIG.quadrillum);
    public static RegistryEntry<PlacedFeature> ORE_RUNITE = OreFeatureHelper.create("ore_runite", STONE_RULE, MythicBlocks.RUNITE.getOre(), CONFIG.runite);
    public static RegistryEntry<PlacedFeature> ORE_SILVER = OreFeatureHelper.create("ore_silver", STONE_RULE, MythicBlocks.SILVER.getOre(), CONFIG.silver);
    public static RegistryEntry<PlacedFeature> ORE_TIN = OreFeatureHelper.create("ore_tin", STONE_RULE, MythicBlocks.TIN.getOre(), CONFIG.tin);

    // Ores below zero - Reaches Deep Dark
    public static RegistryEntry<PlacedFeature> ORE_ADAMANTITE = OreFeatureHelper.create("ore_adamantite", ADAMANTITE_TARGETS, CONFIG.adamantite);
    public static RegistryEntry<PlacedFeature> ORE_CALCITE_KYBER = OreFeatureHelper.create("ore_calcite_kyber", CALCITE_RULE, MythicBlocks.KYBER.getOreVariant("calcite"), CONFIG.kyber.getVariant());
    public static RegistryEntry<PlacedFeature> ORE_CARMOT = OreFeatureHelper.create("ore_carmot", CARMOT_TARGETS, CONFIG.carmot);
    public static RegistryEntry<PlacedFeature> ORE_DEEPSLATE_RUNITE = OreFeatureHelper.create("ore_deepslate_runite", RUNITE_TARGETS, CONFIG.runite.getVariant());
    public static RegistryEntry<PlacedFeature> ORE_MORKITE = OreFeatureHelper.create("ore_morkite", MORKITE_TARGETS, CONFIG.morkite);
    public static RegistryEntry<PlacedFeature> ORE_MYTHRIL = OreFeatureHelper.create("ore_mythril", MYTHRIL_TARGETS, CONFIG.mythril);
    public static RegistryEntry<PlacedFeature> ORE_ORICHALCUM = OreFeatureHelper.create("ore_orichalcum", ORICHALCUM_TARGETS, CONFIG.orichalcum);
    public static RegistryEntry<PlacedFeature> ORE_PROMETHEUM = OreFeatureHelper.create("ore_prometheum", PROMETHEUM_TARGETS, CONFIG.prometheum);
    public static RegistryEntry<PlacedFeature> ORE_STARRITE = OreFeatureHelper.create("ore_starrite", STARRITE_TARGETS, CONFIG.starrite);
    public static RegistryEntry<PlacedFeature> ORE_UNOBTAINIUM = OreFeatureHelper.create("ore_unobtainium", UNOBTAINIUM_TARGETS, CONFIG.unobtainium);

    // Nether Ores
    public static RegistryEntry<PlacedFeature> ORE_NETHER_BANGLUM = OreFeatureHelper.create("ore_nether_banglum", NETHERRACK_RULE, MythicBlocks.BANGLUM.getOreVariant("nether"), CONFIG.banglum.getVariant());
    public static RegistryEntry<PlacedFeature> ORE_MIDAS_GOLD = OreFeatureHelper.create("ore_midas_gold", NETHERRACK_RULE, MythicBlocks.MIDAS_GOLD.getOre(), CONFIG.midas_gold);
    public static RegistryEntry<PlacedFeature> ORE_PALLADIUM = OreFeatureHelper.create("ore_palladium", NETHERRACK_RULE, MythicBlocks.PALLADIUM.getOre(), CONFIG.palladium);
    public static RegistryEntry<PlacedFeature> ORE_STORMYX = OreFeatureHelper.create("ore_stormyx", STORMYX_TARGETS, CONFIG.stormyx);
    public static RegistryEntry<PlacedFeature> ORE_OVERWORLD_NETHER_ORES = OreFeatureHelper.create("ore_overworld_nether", OVERWORLD_NETHER_ORE_TARGETS, CONFIG.overworld_nether_ores);

    // End Ores
    public static RegistryEntry<PlacedFeature> ORE_END_STARRITE = OreFeatureHelper.create("ore_end_starrite", END_STONE_RULE, MythicBlocks.STARRITE.getOreVariant("end_stone"), CONFIG.starrite.getVariant());

    // RegistryKeys for features
    public static final RegistryKey<PlacedFeature> ADAMANTITE = ORE_ADAMANTITE.getKey().get();
    public static final RegistryKey<PlacedFeature> AQUARIUM = ORE_AQUARIUM.getKey().get();
    public static final RegistryKey<PlacedFeature> BANGLUM = ORE_BANGLUM.getKey().get();
    public static final RegistryKey<PlacedFeature> NETHER_BANGLUM = ORE_NETHER_BANGLUM.getKey().get();
    public static final RegistryKey<PlacedFeature> CARMOT = ORE_CARMOT.getKey().get();
    public static final RegistryKey<PlacedFeature> CALCITE_KYBER = ORE_CALCITE_KYBER.getKey().get();
    public static final RegistryKey<PlacedFeature> END_STARRITE = ORE_END_STARRITE.getKey().get();
    public static final RegistryKey<PlacedFeature> KYBER = ORE_KYBER.getKey().get();
    public static final RegistryKey<PlacedFeature> MANGANESE = ORE_MANGANESE.getKey().get();
    public static final RegistryKey<PlacedFeature> MIDAS_GOLD = ORE_MIDAS_GOLD.getKey().get();
    public static final RegistryKey<PlacedFeature> MORKITE = ORE_MORKITE.getKey().get();
    public static final RegistryKey<PlacedFeature> MYTHRIL = ORE_MYTHRIL.getKey().get();
    public static final RegistryKey<PlacedFeature> ORICHALCUM = ORE_ORICHALCUM.getKey().get();
    public static final RegistryKey<PlacedFeature> OSMIUM = ORE_OSMIUM.getKey().get();
    public static final RegistryKey<PlacedFeature> OVERWORLD_NETHER_ORES = ORE_OVERWORLD_NETHER_ORES.getKey().get();
    public static final RegistryKey<PlacedFeature> PALLADIUM = ORE_PALLADIUM.getKey().get();
    public static final RegistryKey<PlacedFeature> PLATINUM = ORE_PLATINUM.getKey().get();
    public static final RegistryKey<PlacedFeature> PROMETHEUM = ORE_PROMETHEUM.getKey().get();
    public static final RegistryKey<PlacedFeature> QUADRILLUM = ORE_QUADRILLUM.getKey().get();
    public static final RegistryKey<PlacedFeature> DEEPSLATE_RUNITE = ORE_DEEPSLATE_RUNITE.getKey().get();
    public static final RegistryKey<PlacedFeature> RUNITE = ORE_RUNITE.getKey().get();
    public static final RegistryKey<PlacedFeature> SILVER = ORE_SILVER.getKey().get();
    public static final RegistryKey<PlacedFeature> STARRITE = ORE_STARRITE.getKey().get();
    public static final RegistryKey<PlacedFeature> STORMYX = ORE_STORMYX.getKey().get();
    public static final RegistryKey<PlacedFeature> TIN = ORE_TIN.getKey().get();
    public static final RegistryKey<PlacedFeature> UNOBTAINIUM = ORE_UNOBTAINIUM.getKey().get();

    public static void init() {

        //Overworld Ores
        if (CONFIG.adamantite.enabled) {
            OreFeatureHelper.ore(ADAMANTITE);
        }
        if (CONFIG.banglum.enabled) {
            OreFeatureHelper.ore(BANGLUM);
        }
        if (CONFIG.carmot.enabled) {
            OreFeatureHelper.ore(CARMOT);
        }
        if (CONFIG.kyber.enabled) {
            OreFeatureHelper.ore(KYBER);
            OreFeatureHelper.ore(CALCITE_KYBER);
        }
        if (CONFIG.mythril.enabled) {
            OreFeatureHelper.ore(MYTHRIL);
        }
        if (CONFIG.orichalcum.enabled) {
            OreFeatureHelper.ore(ORICHALCUM);
        }
        if (CONFIG.manganese.enabled) {
            OreFeatureHelper.ore(MANGANESE);
        }
        if (CONFIG.overworld_nether_ores.enabled) {
            OreFeatureHelper.ore(OVERWORLD_NETHER_ORES);
        }
        if (CONFIG.platinum.enabled) {
            OreFeatureHelper.ore(PLATINUM);
        }
        if (CONFIG.quadrillum.enabled) {
            OreFeatureHelper.ore(QUADRILLUM);
        }
        if (CONFIG.runite.enabled) {
            OreFeatureHelper.ore(RUNITE);
            OreFeatureHelper.ore(DEEPSLATE_RUNITE);
        }
        if (CONFIG.silver.enabled) {
            OreFeatureHelper.ore(SILVER);
        }
        if (CONFIG.starrite.enabled) {
            OreFeatureHelper.ore(STARRITE);
            OreFeatureHelper.endOre(END_STARRITE);
        }
        if (CONFIG.tin.enabled) {
            OreFeatureHelper.ore(TIN);
        }
        if (CONFIG.unobtainium.enabled) {
            OreFeatureHelper.ore(UNOBTAINIUM);
        }
        if (CONFIG.morkite.enabled) {
            OreFeatureHelper.ore(MORKITE);
        }

        //Nether Ores
        if (CONFIG.banglum.enabled) {
            OreFeatureHelper.netherOre(NETHER_BANGLUM);
        }
        if (CONFIG.midas_gold.enabled) {
            OreFeatureHelper.netherOre(MIDAS_GOLD);
        }
        if (CONFIG.stormyx.enabled) {
            OreFeatureHelper.netherOre(STORMYX);
        }
        if (CONFIG.palladium.enabled) {
            OreFeatureHelper.netherOre(PALLADIUM);
        }

        // Add Aquarium to Aquatic Biomes
        if (CONFIG.aquarium.enabled) {
            BiomeModifications.addFeature(BiomeSelectors.tag(ConventionalBiomeTags.AQUATIC), GenerationStep.Feature.UNDERGROUND_ORES, AQUARIUM);
        }
        // Add Prometheum to hot biomes
        if (CONFIG.prometheum.enabled) {
            BiomeModifications.addFeature(BiomeSelectors.tag(RegisterTags.HUMID_BIOMES), GenerationStep.Feature.UNDERGROUND_ORES, PROMETHEUM);

            OreFeatureHelper.modBiomeOres("terralith", "hot_shrubland", PROMETHEUM);
            OreFeatureHelper.modBiomeOres("terralith", "sakura_grove", PROMETHEUM);
            OreFeatureHelper.modBiomeOres("terralith", "sakura_valley", PROMETHEUM);
        }
        // Add Osmium to mountainous biomes
        if (CONFIG.osmium.enabled) {
            BiomeModifications.addFeature(BiomeSelectors.tag(ConventionalBiomeTags.MOUNTAIN), GenerationStep.Feature.UNDERGROUND_ORES, OSMIUM);
            BiomeModifications.addFeature(BiomeSelectors.tag(BiomeTags.IS_HILL), GenerationStep.Feature.UNDERGROUND_ORES, OSMIUM);

        }

    }

}
