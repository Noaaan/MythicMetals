package com.mythicmetals.data.worldgen;

import com.google.common.collect.ImmutableList;
import com.mythicmetals.item.MythicMaterials;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.structure.templatesystem.*;

public class MythicOreRules {
    // Defines new RuleTest(s), which checks what blocks an ore can spawn in
    public static final RuleTest BLACKSTONE_RULE = new BlockMatchTest(Blocks.BLACKSTONE);
    public static final RuleTest CALCITE_RULE = new BlockMatchTest(Blocks.CALCITE);
    public static final RuleTest NETHERRACK_RULE = new BlockMatchTest(Blocks.NETHERRACK);
    public static final RuleTest DEEPSLATE_RULE = new TagMatchTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES);
    public static final RuleTest END_STONE_RULE = new BlockMatchTest(Blocks.END_STONE);
    public static final RuleTest SMOOTH_BASALT_RULE = new BlockMatchTest(Blocks.SMOOTH_BASALT);
    public static final RuleTest STONE_RULE = new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES);
    public static final RuleTest TUFF_RULE = new BlockMatchTest(Blocks.TUFF);


    // Defines a list of targets, which can check for multiple blocks and dynamically replace them when generating ore
    public static final ImmutableList<OreConfiguration.TargetBlockState> ADAMANTITE_TARGETS = ImmutableList.of(
        OreConfiguration.target(STONE_RULE, MythicMaterials.ADAMANTITE.blockSet().ore().defaultBlockState()),
        OreConfiguration.target(DEEPSLATE_RULE, MythicMaterials.ADAMANTITE.blockSet().oreVariants().get("deepslate").getB().defaultBlockState()));
    public static final ImmutableList<OreConfiguration.TargetBlockState> CARMOT_TARGETS = ImmutableList.of(
        OreConfiguration.target(STONE_RULE, MythicMaterials.CARMOT.blockSet().ore().defaultBlockState()),
        OreConfiguration.target(DEEPSLATE_RULE, MythicMaterials.CARMOT.blockSet().oreVariants().get("deepslate").getB().defaultBlockState()));
    public static final ImmutableList<OreConfiguration.TargetBlockState> MYTHRIL_TARGETS = ImmutableList.of(
        OreConfiguration.target(STONE_RULE, MythicMaterials.MYTHRIL.blockSet().ore().defaultBlockState()),
        OreConfiguration.target(DEEPSLATE_RULE, MythicMaterials.MYTHRIL.blockSet().oreVariants().get("deepslate").getB().defaultBlockState()));
    public static final ImmutableList<OreConfiguration.TargetBlockState> MORKITE_TARGETS = ImmutableList.of(
        OreConfiguration.target(STONE_RULE, MythicMaterials.MORKITE.blockSet().ore().defaultBlockState()),
        OreConfiguration.target(DEEPSLATE_RULE, MythicMaterials.MORKITE.blockSet().oreVariants().get("deepslate").getB().defaultBlockState()));
    public static final ImmutableList<OreConfiguration.TargetBlockState> ORICHALCUM_TARGETS = ImmutableList.of(
        OreConfiguration.target(STONE_RULE, MythicMaterials.ORICHALCUM.blockSet().ore().defaultBlockState()),
        OreConfiguration.target(TUFF_RULE, MythicMaterials.ORICHALCUM.blockSet().oreVariants().get("tuff").getB().defaultBlockState()),
        OreConfiguration.target(SMOOTH_BASALT_RULE, MythicMaterials.ORICHALCUM.blockSet().oreVariants().get("smooth_basalt").getB().defaultBlockState()),
        OreConfiguration.target(DEEPSLATE_RULE, MythicMaterials.ORICHALCUM.blockSet().oreVariants().get("deepslate").getB().defaultBlockState()));
    public static final ImmutableList<OreConfiguration.TargetBlockState> PROMETHEUM_TARGETS = ImmutableList.of(
        OreConfiguration.target(STONE_RULE, MythicMaterials.PROMETHEUM.blockSet().ore().defaultBlockState()),
        OreConfiguration.target(DEEPSLATE_RULE, MythicMaterials.PROMETHEUM.blockSet().oreVariants().get("deepslate").getB().defaultBlockState()));
    public static final ImmutableList<OreConfiguration.TargetBlockState> RUNITE_TARGETS = ImmutableList.of(
        OreConfiguration.target(STONE_RULE, MythicMaterials.RUNITE.blockSet().ore().defaultBlockState()),
        OreConfiguration.target(DEEPSLATE_RULE, MythicMaterials.RUNITE.blockSet().oreVariants().get("deepslate").getB().defaultBlockState()));
    public static final ImmutableList<OreConfiguration.TargetBlockState> STARRITE_TARGETS = ImmutableList.of(
        OreConfiguration.target(STONE_RULE, MythicMaterials.STARRITE.blockSet().ore().defaultBlockState()),
        OreConfiguration.target(CALCITE_RULE, MythicMaterials.STARRITE.blockSet().oreVariants().get("calcite").getB().defaultBlockState()));
    public static final ImmutableList<OreConfiguration.TargetBlockState> STORMYX_TARGETS = ImmutableList.of(
        OreConfiguration.target(NETHERRACK_RULE, MythicMaterials.STORMYX.blockSet().ore().defaultBlockState()),
        OreConfiguration.target(BLACKSTONE_RULE, MythicMaterials.STORMYX.blockSet().oreVariants().get("blackstone").getB().defaultBlockState()));
    public static final ImmutableList<OreConfiguration.TargetBlockState> UNOBTAINIUM_TARGETS = ImmutableList.of(
        OreConfiguration.target(STONE_RULE, MythicMaterials.UNOBTAINIUM.blockSet().ore().defaultBlockState()),
        OreConfiguration.target(DEEPSLATE_RULE, MythicMaterials.UNOBTAINIUM.blockSet().oreVariants().get("deepslate").getB().defaultBlockState()));

}
