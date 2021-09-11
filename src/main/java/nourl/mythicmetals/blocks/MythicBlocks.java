package nourl.mythicmetals.blocks;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.tool.attribute.v1.FabricToolTags;
import net.minecraft.block.*;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.math.intprovider.UniformIntProvider;

import java.util.HashSet;

public class MythicBlocks {
    public static final BlockSet ADAMANTITE = BlockSet.Builder.begin("adamantite", false)
            .createDefaultSet(4, 3, 5, 4)
            .strength(6, 12).sounds(BlockSoundGroup.DEEPSLATE)
            .createOreVariant("deepslate", 3)
            .finish();

    public static final BlockSet AETHERIUM = BlockSet.Builder.begin("aetherium", false)
            .createDefaultSet(5, 3).finish();

    public static final BlockSet AQUARIUM = BlockSet.Builder.begin("aquarium", false)
            .createDefaultSet(4, 2).finish();

    public static final BlockSet BANGLUM = BlockSet.Builder.begin("banglum", false)
            .createDefaultSet(3, 1).finish();

    public static final BlockSet CARMOT = BlockSet.Builder.begin("carmot", false)
            .createDefaultSet(4.0F, 2).finish();

    public static final BlockSet KYBER = BlockSet.Builder.begin("kyber", false)
            .createDefaultSet(3.0F, 2)
            .strength(3.0F, 3.0F)
            .createOreVariant("calcite", 2)
            .finish();

    public static final BlockSet MANGANESE = BlockSet.Builder.begin("manganese", false)
            .createDefaultSet(3.0F, 2).finish();

    public static final BlockSet MIDAS_GOLD = BlockSet.Builder.begin("midas_gold", false)
            .strength(3.5F).sounds(BlockSoundGroup.NETHER_GOLD_ORE)
            .createOre(2)
            .strength(4.5F).sounds(BlockSoundGroup.METAL)
            .createOreStorageBlock(3).createStorageBlock(3)
            .finish();

    public static final BlockSet MYTHRIL = BlockSet.Builder.begin("mythril", false)
            .createDefaultSet(4.5F, 3)
            .strength(5.0F).sounds(BlockSoundGroup.DEEPSLATE)
            .createOreVariant("deepslate", 3)
            .finish();

    public static final BlockSet ORICHALCUM = BlockSet.Builder.begin("orichalcum", false)
            .createDefaultSet(5.0F, 3)
            .sounds(BlockSoundGroup.TUFF)
            .createOreVariant("tuff", 3)
            .sounds(BlockSoundGroup.BASALT)
            .createOreVariant("smooth_basalt", 3)
            .strength(5.5F).sounds(BlockSoundGroup.DEEPSLATE)
            .createOreVariant("deepslate", 3)
            .finish();

    public static final BlockSet OSMIUM = BlockSet.Builder.begin("osmium", false)
            .createDefaultSet(4.0F, 2).finish();

    public static final BlockSet PALLADIUM = BlockSet.Builder.begin("palladium", true)
            .createDefaultSet(4.5F, 3).finish();

    public static final BlockSet PLATINUM = BlockSet.Builder.begin("platinum", false)
            .createDefaultSet(3.5F, 2).finish();

    public static final BlockSet PROMETHEUM = BlockSet.Builder.begin("prometheum", false)
            .createDefaultSet(5.0F, 3)
            .sounds(BlockSoundGroup.DEEPSLATE)
            .createOreVariant("deepslate", 3)
            .finish();

    public static final BlockSet QUADRILLUM = BlockSet.Builder.begin("quadrillum", false)
            .createDefaultSet(4.0F, 2).finish();

    public static final BlockSet RUNITE = BlockSet.Builder.begin("runite", false)
            .createDefaultSet(4.0F, 2).finish();

    public static final BlockSet SILVER = BlockSet.Builder.begin("silver", false)
            .createDefaultSet(3.0F, 1).finish();

    public static final BlockSet STARRITE = BlockSet.Builder.begin("starrite", false)
            .strength(3.0F).createOre(3, UniformIntProvider.create(3, 6))
            .sounds(BlockSoundGroup.CALCITE)
            .createOreVariant("calcite", 3, UniformIntProvider.create(3, 6))
            .sounds(BlockSoundGroup.STONE).strength(4.0F)
            .createOreVariant("end_stone", 4, UniformIntProvider.create(3, 6))
            .sounds(BlockSoundGroup.AMETHYST_BLOCK).createStorageBlock(4)
            .finish();

    public static final BlockSet STORMYX = BlockSet.Builder.begin("stormyx", false)
            .strength(3.5F).sounds(BlockSoundGroup.NETHER_ORE)
            .createOre(2, UniformIntProvider.create(2, 4))
            .createOreVariant("blackstone", 2)
            .strength(4.0F).createOreStorageBlock(3).createStorageBlock(3)
            .finish();

    public static final BlockSet TIN = BlockSet.Builder.begin("tin", false)
            .createDefaultSet(3.0F, 1).finish();

    public static final BlockSet VERMICULITE = BlockSet.Builder.begin("vermiculite", false)
            .strength(3.0F).createOre(1, UniformIntProvider.create(0, 2))
            .strength(3.0F).createFallingStorageBlock(Material.AGGREGATE, 2)
            .finish();

    public static final BlockSet UNOBTAINIUM = BlockSet.Builder.begin("unobtainium", true)
            .strength(6.0F, 13000F).createOre(4, UniformIntProvider.create(4, 7))
            .strength(7.0F, 14000F)
            .createOreVariant("deepslate", 4)
            .strength(7.0F, 15000F).createStorageBlock(5)
            .finish();

    // Storage Blocks
    public static final Block BRONZE_BLOCK = new Block(FabricBlockSettings.of(Material.METAL).strength(5.0F, 6.0F).sounds(BlockSoundGroup.COPPER).breakByTool(FabricToolTags.PICKAXES, 2).requiresTool());
    public static final Block CELESTIUM_BLOCK = new Block(FabricBlockSettings.of(Material.METAL).strength(5.0F, 6.0F).sounds(BlockSoundGroup.METAL).breakByTool(FabricToolTags.PICKAXES, 3).requiresTool());
    public static final Block DISCORDIUM_BLOCK = new Block(FabricBlockSettings.of(Material.METAL).strength(5.0F, 6.0F).sounds(BlockSoundGroup.METAL).breakByTool(FabricToolTags.PICKAXES, 3).requiresTool());
    public static final Block DURASTEEL_BLOCK = new Block(FabricBlockSettings.of(Material.METAL).strength(5.0F, 6.0F).sounds(BlockSoundGroup.NETHERITE).breakByTool(FabricToolTags.PICKAXES, 3).requiresTool());
    public static final Block ETHERITE_BLOCK = new Block(FabricBlockSettings.of(Material.METAL).strength(5.0F, 6.0F).sounds(BlockSoundGroup.METAL).breakByTool(FabricToolTags.PICKAXES, 4).requiresTool());
    public static final Block HALLOWED_BLOCK = new Block(FabricBlockSettings.of(Material.METAL).strength(5.0F, 6.0F).sounds(BlockSoundGroup.NETHERITE).breakByTool(FabricToolTags.PICKAXES, 3).requiresTool());
    public static final Block METALLURGIUM_BLOCK = new Block(FabricBlockSettings.of(Material.METAL).strength(5.0F, 6.0F).sounds(BlockSoundGroup.NETHERITE).breakByTool(FabricToolTags.PICKAXES, 4).requiresTool());
    public static final Block QUICKSILVER_BLOCK = new Block(FabricBlockSettings.of(Material.METAL).strength(5.0F, 6.0F).sounds(BlockSoundGroup.METAL).breakByTool(FabricToolTags.PICKAXES, 3).requiresTool());
    public static final Block STAR_PLATINUM_BLOCK = new Block(FabricBlockSettings.of(Material.METAL).strength(5.0F, 6.0F).sounds(BlockSoundGroup.METAL).breakByTool(FabricToolTags.PICKAXES, 3).requiresTool());
    public static final Block STEEL_BLOCK = new Block(FabricBlockSettings.of(Material.METAL).strength(5.0F, 6.0F).sounds(BlockSoundGroup.METAL).breakByTool(FabricToolTags.PICKAXES, 2).requiresTool());

    // Chains
    public static final ChainBlock ADAMANTITE_CHAIN = new ChainBlock(FabricBlockSettings.of(Material.METAL).strength(5.0F, 5.0F).sounds(BlockSoundGroup.CHAIN).breakByTool(FabricToolTags.PICKAXES, 3).requiresTool().nonOpaque());
    public static final ChainBlock AETHERIUM_CHAIN = new ChainBlock(FabricBlockSettings.of(Material.METAL).strength(5.0F, 5.0F).sounds(BlockSoundGroup.CHAIN).breakByTool(FabricToolTags.PICKAXES, 3).requiresTool().nonOpaque());
    public static final ChainBlock AQUARIUM_CHAIN = new ChainBlock(FabricBlockSettings.of(Material.METAL).strength(5.0F, 5.0F).sounds(BlockSoundGroup.CHAIN).breakByTool(FabricToolTags.PICKAXES, 1).requiresTool().nonOpaque());
    public static final ChainBlock BANGLUM_CHAIN = new ChainBlock(FabricBlockSettings.of(Material.METAL).strength(5.0F, 5.0F).sounds(BlockSoundGroup.CHAIN).breakByTool(FabricToolTags.PICKAXES, 1).requiresTool().nonOpaque());
    public static final ChainBlock BRONZE_CHAIN = new ChainBlock(FabricBlockSettings.of(Material.METAL).strength(5.0F, 5.0F).sounds(BlockSoundGroup.CHAIN).breakByTool(FabricToolTags.PICKAXES, 2).requiresTool().nonOpaque());
    public static final ChainBlock CARMOT_CHAIN = new ChainBlock(FabricBlockSettings.of(Material.METAL).strength(5.0F, 5.0F).sounds(BlockSoundGroup.CHAIN).breakByTool(FabricToolTags.PICKAXES, 2).requiresTool().nonOpaque());
    public static final ChainBlock CELESTIUM_CHAIN = new ChainBlock(FabricBlockSettings.of(Material.METAL).strength(5.0F, 5.0F).sounds(BlockSoundGroup.CHAIN).breakByTool(FabricToolTags.PICKAXES, 3).requiresTool().nonOpaque());
    public static final ChainBlock DISCORDIUM_CHAIN = new ChainBlock(FabricBlockSettings.of(Material.METAL).strength(5.0F, 5.0F).sounds(BlockSoundGroup.CHAIN).breakByTool(FabricToolTags.PICKAXES, 3).requiresTool().nonOpaque());
    public static final ChainBlock DURASTEEL_CHAIN = new ChainBlock(FabricBlockSettings.of(Material.METAL).strength(5.0F, 5.0F).sounds(BlockSoundGroup.CHAIN).breakByTool(FabricToolTags.PICKAXES, 3).requiresTool().nonOpaque());
    public static final ChainBlock ETHERITE_CHAIN = new ChainBlock(FabricBlockSettings.of(Material.METAL).strength(5.0F, 5.0F).sounds(BlockSoundGroup.CHAIN).breakByTool(FabricToolTags.PICKAXES, 4).requiresTool().nonOpaque());
    public static final ChainBlock HALLOWED_CHAIN = new ChainBlock(FabricBlockSettings.of(Material.METAL).strength(5.0F, 5.0F).sounds(BlockSoundGroup.CHAIN).breakByTool(FabricToolTags.PICKAXES, 3).requiresTool().nonOpaque());
    public static final ChainBlock KYBER_CHAIN = new ChainBlock(FabricBlockSettings.of(Material.METAL).strength(5.0F, 5.0F).sounds(BlockSoundGroup.CHAIN).breakByTool(FabricToolTags.PICKAXES, 2).requiresTool().nonOpaque());
    public static final ChainBlock MANGANESE_CHAIN = new ChainBlock(FabricBlockSettings.of(Material.METAL).strength(5.0F, 5.0F).sounds(BlockSoundGroup.CHAIN).breakByTool(FabricToolTags.PICKAXES, 2).requiresTool().nonOpaque());
    public static final ChainBlock METALLURGIUM_CHAIN = new ChainBlock(FabricBlockSettings.of(Material.METAL).strength(5.0F, 5.0F).sounds(BlockSoundGroup.CHAIN).breakByTool(FabricToolTags.PICKAXES, 4).requiresTool().nonOpaque());
    public static final ChainBlock MIDAS_GOLD_CHAIN = new ChainBlock(FabricBlockSettings.of(Material.METAL).strength(5.0F, 5.0F).sounds(BlockSoundGroup.CHAIN).breakByTool(FabricToolTags.PICKAXES, 2).requiresTool().nonOpaque());
    public static final ChainBlock MYTHRIL_CHAIN = new ChainBlock(FabricBlockSettings.of(Material.METAL).strength(5.0F, 5.0F).sounds(BlockSoundGroup.CHAIN).breakByTool(FabricToolTags.PICKAXES, 3).requiresTool().nonOpaque());
    public static final ChainBlock ORICHALCUM_CHAIN = new ChainBlock(FabricBlockSettings.of(Material.METAL).strength(5.0F, 5.0F).sounds(BlockSoundGroup.CHAIN).breakByTool(FabricToolTags.PICKAXES, 3).requiresTool().nonOpaque());
    public static final ChainBlock OSMIUM_CHAIN = new ChainBlock(FabricBlockSettings.of(Material.METAL).strength(5.0F, 5.0F).sounds(BlockSoundGroup.CHAIN).breakByTool(FabricToolTags.PICKAXES, 2).requiresTool().nonOpaque());
    public static final ChainBlock PALLADIUM_CHAIN = new ChainBlock(FabricBlockSettings.of(Material.METAL).strength(5.0F, 5.0F).sounds(BlockSoundGroup.CHAIN).breakByTool(FabricToolTags.PICKAXES, 2).requiresTool().nonOpaque());
    public static final ChainBlock PLATINUM_CHAIN = new ChainBlock(FabricBlockSettings.of(Material.METAL).strength(5.0F, 5.0F).sounds(BlockSoundGroup.CHAIN).breakByTool(FabricToolTags.PICKAXES, 2).requiresTool().nonOpaque());
    public static final ChainBlock PROMETHEUM_CHAIN = new ChainBlock(FabricBlockSettings.of(Material.METAL).strength(5.0F, 5.0F).sounds(BlockSoundGroup.CHAIN).breakByTool(FabricToolTags.PICKAXES, 3).requiresTool().nonOpaque());
    public static final ChainBlock QUADRILLUM_CHAIN = new ChainBlock(FabricBlockSettings.of(Material.METAL).strength(5.0F, 5.0F).sounds(BlockSoundGroup.CHAIN).breakByTool(FabricToolTags.PICKAXES, 1).requiresTool().nonOpaque());
    public static final ChainBlock QUICKSILVER_CHAIN = new ChainBlock(FabricBlockSettings.of(Material.METAL).strength(5.0F, 5.0F).sounds(BlockSoundGroup.CHAIN).breakByTool(FabricToolTags.PICKAXES, 2).requiresTool().nonOpaque());
    public static final ChainBlock RUNITE_CHAIN = new ChainBlock(FabricBlockSettings.of(Material.METAL).strength(5.0F, 5.0F).sounds(BlockSoundGroup.CHAIN).breakByTool(FabricToolTags.PICKAXES, 2).requiresTool().nonOpaque());
    public static final ChainBlock SILVER_CHAIN = new ChainBlock(FabricBlockSettings.of(Material.METAL).strength(5.0F, 5.0F).sounds(BlockSoundGroup.CHAIN).breakByTool(FabricToolTags.PICKAXES, 1).requiresTool().nonOpaque());
    public static final ChainBlock STAR_PLATINUM_CHAIN = new ChainBlock(FabricBlockSettings.of(Material.METAL).strength(5.0F, 5.0F).sounds(BlockSoundGroup.CHAIN).breakByTool(FabricToolTags.PICKAXES, 3).requiresTool().nonOpaque());
    public static final ChainBlock STEEL_CHAIN = new ChainBlock(FabricBlockSettings.of(Material.METAL).strength(5.0F, 5.0F).sounds(BlockSoundGroup.CHAIN).breakByTool(FabricToolTags.PICKAXES, 2).requiresTool().nonOpaque());
    public static final ChainBlock STORMYX_CHAIN = new ChainBlock(FabricBlockSettings.of(Material.METAL).strength(5.0F, 5.0F).sounds(BlockSoundGroup.CHAIN).breakByTool(FabricToolTags.PICKAXES, 2).requiresTool().nonOpaque());
    public static final AnvilBlock ADAMANTITE_ANVIL = new AnvilBlock(FabricBlockSettings.of(Material.REPAIR_STATION).strength(5.0F, 15000.0F).sounds(BlockSoundGroup.ANVIL).breakByTool(FabricToolTags.PICKAXES, 3).requiresTool());
    public static final AnvilBlock AETHERIUM_ANVIL = new AnvilBlock(FabricBlockSettings.of(Material.REPAIR_STATION).strength(5.0F, 15000.0F).sounds(BlockSoundGroup.ANVIL).breakByTool(FabricToolTags.PICKAXES, 3).requiresTool());
    public static final AnvilBlock AQUARIUM_ANVIL = new AnvilBlock(FabricBlockSettings.of(Material.REPAIR_STATION).strength(5.0F, 15000.0F).sounds(BlockSoundGroup.ANVIL).breakByTool(FabricToolTags.PICKAXES, 1).requiresTool());
    public static final AnvilBlock BANGLUM_ANVIL = new AnvilBlock(FabricBlockSettings.of(Material.REPAIR_STATION).strength(5.0F, 15000.0F).sounds(BlockSoundGroup.ANVIL).breakByTool(FabricToolTags.PICKAXES, 1).requiresTool());
    public static final AnvilBlock BRONZE_ANVIL = new AnvilBlock(FabricBlockSettings.of(Material.REPAIR_STATION).strength(5.0F, 15000.0F).sounds(BlockSoundGroup.ANVIL).breakByTool(FabricToolTags.PICKAXES, 2).requiresTool());
    public static final AnvilBlock CARMOT_ANVIL = new AnvilBlock(FabricBlockSettings.of(Material.REPAIR_STATION).strength(5.0F, 15000.0F).sounds(BlockSoundGroup.ANVIL).breakByTool(FabricToolTags.PICKAXES, 2).requiresTool());
    public static final AnvilBlock CELESTIUM_ANVIL = new AnvilBlock(FabricBlockSettings.of(Material.REPAIR_STATION).strength(5.0F, 15000.0F).sounds(BlockSoundGroup.ANVIL).breakByTool(FabricToolTags.PICKAXES, 3).requiresTool());
    public static final AnvilBlock DISCORDIUM_ANVIL = new AnvilBlock(FabricBlockSettings.of(Material.REPAIR_STATION).strength(5.0F, 15000.0F).sounds(BlockSoundGroup.ANVIL).breakByTool(FabricToolTags.PICKAXES, 3).requiresTool());
    public static final AnvilBlock DURASTEEL_ANVIL = new AnvilBlock(FabricBlockSettings.of(Material.REPAIR_STATION).strength(5.0F, 15000.0F).sounds(BlockSoundGroup.ANVIL).breakByTool(FabricToolTags.PICKAXES, 3).requiresTool());
    public static final AnvilBlock ETHERITE_ANVIL = new AnvilBlock(FabricBlockSettings.of(Material.REPAIR_STATION).strength(5.0F, 15000.0F).sounds(BlockSoundGroup.ANVIL).breakByTool(FabricToolTags.PICKAXES, 4).requiresTool());
    public static final AnvilBlock HALLOWED_ANVIL = new AnvilBlock(FabricBlockSettings.of(Material.REPAIR_STATION).strength(5.0F, 15000.0F).sounds(BlockSoundGroup.ANVIL).breakByTool(FabricToolTags.PICKAXES, 3).requiresTool());
    public static final AnvilBlock KYBER_ANVIL = new AnvilBlock(FabricBlockSettings.of(Material.REPAIR_STATION).strength(5.0F, 15000.0F).sounds(BlockSoundGroup.ANVIL).breakByTool(FabricToolTags.PICKAXES, 2).requiresTool());
    public static final AnvilBlock MANGANESE_ANVIL = new AnvilBlock(FabricBlockSettings.of(Material.REPAIR_STATION).strength(5.0F, 15000.0F).sounds(BlockSoundGroup.ANVIL).breakByTool(FabricToolTags.PICKAXES, 2).requiresTool());
    public static final AnvilBlock METALLURGIUM_ANVIL = new AnvilBlock(FabricBlockSettings.of(Material.REPAIR_STATION).strength(5.0F, 15000.0F).sounds(BlockSoundGroup.ANVIL).breakByTool(FabricToolTags.PICKAXES, 4).requiresTool());
    public static final AnvilBlock MIDAS_GOLD_ANVIL = new AnvilBlock(FabricBlockSettings.of(Material.REPAIR_STATION).strength(5.0F, 15000.0F).sounds(BlockSoundGroup.ANVIL).breakByTool(FabricToolTags.PICKAXES, 2).requiresTool());
    public static final AnvilBlock MYTHRIL_ANVIL = new AnvilBlock(FabricBlockSettings.of(Material.REPAIR_STATION).strength(5.0F, 15000.0F).sounds(BlockSoundGroup.ANVIL).breakByTool(FabricToolTags.PICKAXES, 3).requiresTool());
    public static final AnvilBlock ORICHALCUM_ANVIL = new AnvilBlock(FabricBlockSettings.of(Material.REPAIR_STATION).strength(5.0F, 15000.0F).sounds(BlockSoundGroup.ANVIL).breakByTool(FabricToolTags.PICKAXES, 3).requiresTool());
    public static final AnvilBlock OSMIUM_ANVIL = new AnvilBlock(FabricBlockSettings.of(Material.REPAIR_STATION).strength(5.0F, 15000.0F).sounds(BlockSoundGroup.ANVIL).breakByTool(FabricToolTags.PICKAXES, 2).requiresTool());
    public static final AnvilBlock PALLADIUM_ANVIL = new AnvilBlock(FabricBlockSettings.of(Material.REPAIR_STATION).strength(5.0F, 15000.0F).sounds(BlockSoundGroup.ANVIL).breakByTool(FabricToolTags.PICKAXES, 2).requiresTool());
    public static final AnvilBlock PLATINUM_ANVIL = new AnvilBlock(FabricBlockSettings.of(Material.REPAIR_STATION).strength(5.0F, 15000.0F).sounds(BlockSoundGroup.ANVIL).breakByTool(FabricToolTags.PICKAXES, 2).requiresTool());
    public static final AnvilBlock PROMETHEUM_ANVIL = new AnvilBlock(FabricBlockSettings.of(Material.REPAIR_STATION).strength(5.0F, 15000.0F).sounds(BlockSoundGroup.ANVIL).breakByTool(FabricToolTags.PICKAXES, 3).requiresTool());
    public static final AnvilBlock QUADRILLUM_ANVIL = new AnvilBlock(FabricBlockSettings.of(Material.REPAIR_STATION).strength(5.0F, 15000.0F).sounds(BlockSoundGroup.ANVIL).breakByTool(FabricToolTags.PICKAXES, 1).requiresTool());
    public static final AnvilBlock QUICKSILVER_ANVIL = new AnvilBlock(FabricBlockSettings.of(Material.REPAIR_STATION).strength(5.0F, 15000.0F).sounds(BlockSoundGroup.ANVIL).breakByTool(FabricToolTags.PICKAXES, 2).requiresTool());
    public static final AnvilBlock RUNITE_ANVIL = new AnvilBlock(FabricBlockSettings.of(Material.REPAIR_STATION).strength(5.0F, 15000.0F).sounds(BlockSoundGroup.ANVIL).breakByTool(FabricToolTags.PICKAXES, 2).requiresTool());
    public static final AnvilBlock SILVER_ANVIL = new AnvilBlock(FabricBlockSettings.of(Material.REPAIR_STATION).strength(5.0F, 15000.0F).sounds(BlockSoundGroup.ANVIL).breakByTool(FabricToolTags.PICKAXES, 1).requiresTool());
    public static final AnvilBlock STAR_PLATINUM_ANVIL = new AnvilBlock(FabricBlockSettings.of(Material.REPAIR_STATION).strength(5.0F, 15000.0F).sounds(BlockSoundGroup.ANVIL).breakByTool(FabricToolTags.PICKAXES, 3).requiresTool());
    public static final AnvilBlock STEEL_ANVIL = new AnvilBlock(FabricBlockSettings.of(Material.REPAIR_STATION).strength(5.0F, 15000.0F).sounds(BlockSoundGroup.ANVIL).breakByTool(FabricToolTags.PICKAXES, 2).requiresTool());
    public static final AnvilBlock STORMYX_ANVIL = new AnvilBlock(FabricBlockSettings.of(Material.REPAIR_STATION).strength(5.0F, 15000.0F).sounds(BlockSoundGroup.ANVIL).breakByTool(FabricToolTags.PICKAXES, 2).requiresTool());
    // Anvils
    public static HashSet<Block> ANVILS = new HashSet<>();

    public static void init() {
        BlockSet.Builder.register();
    }
}
