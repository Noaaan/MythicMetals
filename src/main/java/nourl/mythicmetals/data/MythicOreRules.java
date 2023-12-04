package nourl.mythicmetals.data;

import com.google.common.collect.ImmutableList;
import net.minecraft.block.Blocks;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.structure.rule.*;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import nourl.mythicmetals.blocks.MythicBlocks;

public class MythicOreRules {
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
    public static final ImmutableList<OreFeatureConfig.Target> ADAMANTITE_TARGETS = ImmutableList.of(
            OreFeatureConfig.createTarget(STONE_RULE, MythicBlocks.ADAMANTITE.getOre().getDefaultState()),
            OreFeatureConfig.createTarget(DEEPSLATE_RULE, MythicBlocks.ADAMANTITE.getOreVariant("deepslate").getDefaultState()));
    public static final ImmutableList<OreFeatureConfig.Target> CARMOT_TARGETS = ImmutableList.of(
            OreFeatureConfig.createTarget(STONE_RULE, MythicBlocks.CARMOT.getOre().getDefaultState()),
            OreFeatureConfig.createTarget(DEEPSLATE_RULE, MythicBlocks.CARMOT.getOreVariant("deepslate").getDefaultState()));
    public static final ImmutableList<OreFeatureConfig.Target> MYTHRIL_TARGETS = ImmutableList.of(
            OreFeatureConfig.createTarget(STONE_RULE, MythicBlocks.MYTHRIL.getOre().getDefaultState()),
            OreFeatureConfig.createTarget(DEEPSLATE_RULE, MythicBlocks.MYTHRIL.getOreVariant("deepslate").getDefaultState()));
    public static final ImmutableList<OreFeatureConfig.Target> MORKITE_TARGETS = ImmutableList.of(
            OreFeatureConfig.createTarget(STONE_RULE, MythicBlocks.MORKITE.getOre().getDefaultState()),
            OreFeatureConfig.createTarget(DEEPSLATE_RULE, MythicBlocks.MORKITE.getOreVariant("deepslate").getDefaultState()));
    public static final ImmutableList<OreFeatureConfig.Target> ORICHALCUM_TARGETS = ImmutableList.of(
            OreFeatureConfig.createTarget(STONE_RULE, MythicBlocks.ORICHALCUM.getOre().getDefaultState()),
            OreFeatureConfig.createTarget(TUFF_RULE, MythicBlocks.ORICHALCUM.getOreVariant("tuff").getDefaultState()),
            OreFeatureConfig.createTarget(SMOOTH_BASALT_RULE, MythicBlocks.ORICHALCUM.getOreVariant("smooth_basalt").getDefaultState()),
            OreFeatureConfig.createTarget(DEEPSLATE_RULE, MythicBlocks.ORICHALCUM.getOreVariant("deepslate").getDefaultState()));
    public static final ImmutableList<OreFeatureConfig.Target> PROMETHEUM_TARGETS = ImmutableList.of(
            OreFeatureConfig.createTarget(STONE_RULE, MythicBlocks.PROMETHEUM.getOre().getDefaultState()),
            OreFeatureConfig.createTarget(DEEPSLATE_RULE, MythicBlocks.PROMETHEUM.getOreVariant("deepslate").getDefaultState()));
    public static final ImmutableList<OreFeatureConfig.Target> RUNITE_TARGETS = ImmutableList.of(
            OreFeatureConfig.createTarget(STONE_RULE, MythicBlocks.RUNITE.getOre().getDefaultState()),
            OreFeatureConfig.createTarget(DEEPSLATE_RULE, MythicBlocks.RUNITE.getOreVariant("deepslate").getDefaultState()));
    public static final ImmutableList<OreFeatureConfig.Target> STARRITE_TARGETS = ImmutableList.of(
            OreFeatureConfig.createTarget(STONE_RULE, MythicBlocks.STARRITE.getOre().getDefaultState()),
            OreFeatureConfig.createTarget(CALCITE_RULE, MythicBlocks.STARRITE.getOreVariant("calcite").getDefaultState()));
    public static final ImmutableList<OreFeatureConfig.Target> STORMYX_TARGETS = ImmutableList.of(
            OreFeatureConfig.createTarget(NETHERRACK_RULE, MythicBlocks.STORMYX.getOre().getDefaultState()),
            OreFeatureConfig.createTarget(BLACKSTONE_RULE, MythicBlocks.STORMYX.getOreVariant("blackstone").getDefaultState()));
    public static final ImmutableList<OreFeatureConfig.Target> UNOBTAINIUM_TARGETS = ImmutableList.of(
            OreFeatureConfig.createTarget(STONE_RULE, MythicBlocks.UNOBTAINIUM.getOre().getDefaultState()),
            OreFeatureConfig.createTarget(DEEPSLATE_RULE, MythicBlocks.UNOBTAINIUM.getOreVariant("deepslate").getDefaultState()));

}
