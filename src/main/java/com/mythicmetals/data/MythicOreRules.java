package com.mythicmetals.data;

import com.google.common.collect.ImmutableList;
import com.mythicmetals.block.MythicBlocks;

import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockMatchTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;

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
        OreConfiguration.target(STONE_RULE, MythicBlocks.ADAMANTITE.getOre().defaultBlockState()),
        OreConfiguration.target(DEEPSLATE_RULE, MythicBlocks.ADAMANTITE.getOreVariant("deepslate").defaultBlockState()));
    public static final ImmutableList<OreConfiguration.TargetBlockState> CARMOT_TARGETS = ImmutableList.of(
        OreConfiguration.target(STONE_RULE, MythicBlocks.CARMOT.getOre().defaultBlockState()),
        OreConfiguration.target(DEEPSLATE_RULE, MythicBlocks.CARMOT.getOreVariant("deepslate").defaultBlockState()));
    public static final ImmutableList<OreConfiguration.TargetBlockState> MYTHRIL_TARGETS = ImmutableList.of(
        OreConfiguration.target(STONE_RULE, MythicBlocks.MYTHRIL.getOre().defaultBlockState()),
        OreConfiguration.target(DEEPSLATE_RULE, MythicBlocks.MYTHRIL.getOreVariant("deepslate").defaultBlockState()));
    public static final ImmutableList<OreConfiguration.TargetBlockState> MORKITE_TARGETS = ImmutableList.of(
        OreConfiguration.target(STONE_RULE, MythicBlocks.MORKITE.getOre().defaultBlockState()),
        OreConfiguration.target(DEEPSLATE_RULE, MythicBlocks.MORKITE.getOreVariant("deepslate").defaultBlockState()));
    public static final ImmutableList<OreConfiguration.TargetBlockState> ORICHALCUM_TARGETS = ImmutableList.of(
        OreConfiguration.target(STONE_RULE, MythicBlocks.ORICHALCUM.getOre().defaultBlockState()),
        OreConfiguration.target(TUFF_RULE, MythicBlocks.ORICHALCUM.getOreVariant("tuff").defaultBlockState()),
        OreConfiguration.target(SMOOTH_BASALT_RULE, MythicBlocks.ORICHALCUM.getOreVariant("smooth_basalt").defaultBlockState()),
        OreConfiguration.target(DEEPSLATE_RULE, MythicBlocks.ORICHALCUM.getOreVariant("deepslate").defaultBlockState()));
    public static final ImmutableList<OreConfiguration.TargetBlockState> PROMETHEUM_TARGETS = ImmutableList.of(
        OreConfiguration.target(STONE_RULE, MythicBlocks.PROMETHEUM.getOre().defaultBlockState()),
        OreConfiguration.target(DEEPSLATE_RULE, MythicBlocks.PROMETHEUM.getOreVariant("deepslate").defaultBlockState()));
    public static final ImmutableList<OreConfiguration.TargetBlockState> RUNITE_TARGETS = ImmutableList.of(
        OreConfiguration.target(STONE_RULE, MythicBlocks.RUNITE.getOre().defaultBlockState()),
        OreConfiguration.target(DEEPSLATE_RULE, MythicBlocks.RUNITE.getOreVariant("deepslate").defaultBlockState()));
    public static final ImmutableList<OreConfiguration.TargetBlockState> STARRITE_TARGETS = ImmutableList.of(
        OreConfiguration.target(STONE_RULE, MythicBlocks.STARRITE.getOre().defaultBlockState()),
        OreConfiguration.target(CALCITE_RULE, MythicBlocks.STARRITE.getOreVariant("calcite").defaultBlockState()));
    public static final ImmutableList<OreConfiguration.TargetBlockState> STORMYX_TARGETS = ImmutableList.of(
        OreConfiguration.target(NETHERRACK_RULE, MythicBlocks.STORMYX.getOre().defaultBlockState()),
        OreConfiguration.target(BLACKSTONE_RULE, MythicBlocks.STORMYX.getOreVariant("blackstone").defaultBlockState()));
    public static final ImmutableList<OreConfiguration.TargetBlockState> UNOBTAINIUM_TARGETS = ImmutableList.of(
        OreConfiguration.target(STONE_RULE, MythicBlocks.UNOBTAINIUM.getOre().defaultBlockState()),
        OreConfiguration.target(DEEPSLATE_RULE, MythicBlocks.UNOBTAINIUM.getOreVariant("deepslate").defaultBlockState()));

}
