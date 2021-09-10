package nourl.mythicmetals.blocks;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.tool.attribute.v1.FabricToolTags;
import net.minecraft.block.*;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.math.intprovider.UniformIntProvider;

import java.util.HashSet;

public class MythicBlocks {

    // Resistance arrays for block sets
    public static final float[] SOFT_STONE = new float[]{3.0F, 4.0F, 5.0F};
    public static final float[] HARD_STONE = new float[]{4.0F, 5.0F, 5.0F};
    public static final float[] EXPLOSIVEPROOF = new float[]{12000.0F, 13000F, 15000F};

    // Mining level tiers
    public static final int[] LOW_TIER = new int[]{1, 2};
    public static final int[] MID_TIER = new int[]{2, 3};
    public static final int[] HIGH_TIER = new int[]{3, 4};
    public static final int[] TOP_TIER = new int[]{4, 5};

    public static final BlockSet ADAMANTITE = BlockSet.Builder.begin("adamantite", false)
            .createDefaultSet(4 ,3 ,5 ,4)
            .strength(6, 12)
            .sounds(BlockSoundGroup.DEEPSLATE)
            .createOreVariant("deepslate", 3)
            .finish();

    //public static final BlockSet ADAMANTITE = new BlockSet(5.0F, HARD_STONE, BlockSoundGroup.STONE, BlockSoundGroup.METAL, HIGH_TIER);
    public static final BlockSet AETHERIUM = new BlockSet(5.0F, HARD_STONE, BlockSoundGroup.STONE, BlockSoundGroup.METAL, HIGH_TIER);
    public static final BlockSet AQUARIUM = new BlockSet(4.0F, SOFT_STONE, BlockSoundGroup.STONE, BlockSoundGroup.METAL, MID_TIER);
    public static final BlockSet BANGLUM = new BlockSet(3.0F, SOFT_STONE, BlockSoundGroup.STONE, BlockSoundGroup.METAL, LOW_TIER);
    public static final BlockSet CARMOT = new BlockSet(4.0F, HARD_STONE, BlockSoundGroup.STONE, BlockSoundGroup.METAL, HIGH_TIER);
    public static final BlockSet KYBER = new BlockSet(3.0F, SOFT_STONE, BlockSoundGroup.STONE, BlockSoundGroup.METAL, MID_TIER);
    public static final BlockSet MANGANESE = new BlockSet(3.0F, SOFT_STONE, BlockSoundGroup.STONE, BlockSoundGroup.METAL, LOW_TIER);
    public static final BlockSet MIDAS_GOLD = new BlockSet(4.0F, HARD_STONE, BlockSoundGroup.NETHER_GOLD_ORE, BlockSoundGroup.METAL, MID_TIER);
    public static final BlockSet MYTHRIL = new BlockSet(5.0F, HARD_STONE, BlockSoundGroup.GILDED_BLACKSTONE, BlockSoundGroup.METAL, HIGH_TIER);
    public static final BlockSet ORICHALCUM = new BlockSet(5.5F, HARD_STONE, BlockSoundGroup.GILDED_BLACKSTONE, BlockSoundGroup.METAL, HIGH_TIER);
    public static final BlockSet OSMIUM = new BlockSet(4.0F, HARD_STONE, BlockSoundGroup.STONE, BlockSoundGroup.METAL, MID_TIER);
    public static final BlockSet PALLADIUM = new BlockSet(4.5F, HARD_STONE, BlockSoundGroup.GILDED_BLACKSTONE, BlockSoundGroup.METAL, HIGH_TIER);
    public static final BlockSet PLATINUM = new BlockSet(4.0F, SOFT_STONE, BlockSoundGroup.STONE, BlockSoundGroup.METAL, MID_TIER);
    public static final BlockSet PROMETHEUM = new BlockSet(5.0F, HARD_STONE, BlockSoundGroup.STONE, BlockSoundGroup.METAL, HIGH_TIER);
    public static final BlockSet QUADRILLUM = new BlockSet(4.0F, HARD_STONE, BlockSoundGroup.STONE, BlockSoundGroup.METAL, MID_TIER);
    public static final BlockSet RUNITE = new BlockSet(4.0F, HARD_STONE, BlockSoundGroup.STONE, BlockSoundGroup.METAL, MID_TIER);
    public static final BlockSet SILVER = new BlockSet(3.0F, SOFT_STONE, BlockSoundGroup.STONE, BlockSoundGroup.METAL, LOW_TIER);
    public static final BlockSet STORMYX = new BlockSet(3.0F, SOFT_STONE, BlockSoundGroup.NETHER_ORE, BlockSoundGroup.METAL, MID_TIER);
    public static final BlockSet TIN = new BlockSet(3.0F, SOFT_STONE, BlockSoundGroup.STONE, BlockSoundGroup.METAL, LOW_TIER);
    public static final BlockSet UNOBTAINIUM = new BlockSet(6.0F, EXPLOSIVEPROOF, BlockSoundGroup.LODESTONE, BlockSoundGroup.LODESTONE, TOP_TIER);

    public static final Block BRONZE_BLOCK = new Block(FabricBlockSettings.of(Material.METAL).strength(5.0F, 6.0F).sounds(BlockSoundGroup.COPPER).breakByTool(FabricToolTags.PICKAXES, 2).requiresTool());
    public static final Block CELESTIUM_BLOCK = new Block(FabricBlockSettings.of(Material.METAL).strength(5.0F, 6.0F).sounds(BlockSoundGroup.METAL).breakByTool(FabricToolTags.PICKAXES, 3).requiresTool());
    public static final Block DISCORDIUM_BLOCK = new Block(FabricBlockSettings.of(Material.METAL).strength(5.0F, 6.0F).sounds(BlockSoundGroup.METAL).breakByTool(FabricToolTags.PICKAXES, 3).requiresTool());
    public static final Block DURASTEEL_BLOCK = new Block(FabricBlockSettings.of(Material.METAL).strength(5.0F, 6.0F).sounds(BlockSoundGroup.NETHERITE).breakByTool(FabricToolTags.PICKAXES, 3).requiresTool());
    public static final Block ETHERITE_BLOCK = new Block(FabricBlockSettings.of(Material.METAL).strength(5.0F, 6.0F).sounds(BlockSoundGroup.METAL).breakByTool(FabricToolTags.PICKAXES, 4).requiresTool());
    public static final Block HALLOWED_BLOCK = new Block(FabricBlockSettings.of(Material.METAL).strength(5.0F, 6.0F).sounds(BlockSoundGroup.NETHERITE).breakByTool(FabricToolTags.PICKAXES, 3).requiresTool());
    public static final Block METALLURGIUM_BLOCK = new Block(FabricBlockSettings.of(Material.METAL).strength(5.0F, 6.0F).sounds(BlockSoundGroup.NETHERITE).breakByTool(FabricToolTags.PICKAXES, 4).requiresTool());
    public static final Block QUICKSILVER_BLOCK = new Block(FabricBlockSettings.of(Material.METAL).strength(5.0F, 6.0F).sounds(BlockSoundGroup.METAL).breakByTool(FabricToolTags.PICKAXES, 3).requiresTool());
    public static final Block STARRITE_BLOCK = new Block(FabricBlockSettings.of(Material.AMETHYST).strength(5.0F, 6.0F).sounds(BlockSoundGroup.LARGE_AMETHYST_BUD).breakByTool(FabricToolTags.PICKAXES, 3).requiresTool());
    public static final Block STAR_PLATINUM_BLOCK = new Block(FabricBlockSettings.of(Material.METAL).strength(5.0F, 6.0F).sounds(BlockSoundGroup.METAL).breakByTool(FabricToolTags.PICKAXES, 3).requiresTool());
    public static final Block STEEL_BLOCK = new Block(FabricBlockSettings.of(Material.METAL).strength(5.0F, 6.0F).sounds(BlockSoundGroup.METAL).breakByTool(FabricToolTags.PICKAXES, 2).requiresTool());
    public static final Block VERMICULITE_BLOCK = new FallingBlock(FabricBlockSettings.of(Material.AGGREGATE).strength(3.0F, 4.0F).sounds(BlockSoundGroup.GRAVEL).breakByTool(FabricToolTags.SHOVELS, 1).requiresTool());

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

    // Ore Variants
    public static final OreBlock BLACKSTONE_STORMYX_ORE = new OreBlock(FabricBlockSettings.of(Material.STONE).strength(4.0f, 4.0f).breakByTool(FabricToolTags.PICKAXES, 2).requiresTool().sounds(BlockSoundGroup.NETHER_ORE));
    public static final OreBlock CALCITE_KYBER_ORE = new OreBlock(FabricBlockSettings.of(Material.STONE).strength(3.0f, 3.0f).breakByTool(FabricToolTags.PICKAXES, 2).requiresTool().sounds(BlockSoundGroup.CALCITE));
    public static final OreBlock CALCITE_STARRITE_ORE = new StarriteOreBlock(FabricBlockSettings.of(Material.STONE).strength(3.0f, 3.0f).breakByTool(FabricToolTags.PICKAXES, 3).requiresTool().sounds(BlockSoundGroup.CALCITE), UniformIntProvider.create(3, 6));
    public static final OreBlock DEEPSLATE_ADAMANTITE_ORE = new OreBlock(FabricBlockSettings.of(Material.STONE).strength(6.0f, 12.0f).breakByTool(FabricToolTags.PICKAXES, 3).requiresTool().sounds(BlockSoundGroup.DEEPSLATE));
    public static final OreBlock DEEPSLATE_MYTHRIL_ORE = new OreBlock(FabricBlockSettings.of(Material.STONE).strength(6.0f, 12.0f).breakByTool(FabricToolTags.PICKAXES, 3).requiresTool().sounds(BlockSoundGroup.DEEPSLATE));
    public static final OreBlock DEEPSLATE_ORICHALCUM_ORE = new OreBlock(FabricBlockSettings.of(Material.STONE).strength(6.0f, 12.0f).breakByTool(FabricToolTags.PICKAXES, 3).requiresTool().sounds(BlockSoundGroup.DEEPSLATE));
    public static final OreBlock DEEPSLATE_PROMETHEUM_ORE = new OreBlock(FabricBlockSettings.of(Material.STONE).strength(5.0f, 12.0f).breakByTool(FabricToolTags.PICKAXES, 3).requiresTool().sounds(BlockSoundGroup.DEEPSLATE));
    public static final OreBlock DEEPSLATE_UNOBTAINIUM_ORE = new OreBlock(FabricBlockSettings.of(Material.STONE).strength(7.0f, 13000.0f).breakByTool(FabricToolTags.PICKAXES, 3).requiresTool().sounds(BlockSoundGroup.DEEPSLATE));
    public static final OreBlock END_STONE_STARRITE_ORE = new StarriteOreBlock(FabricBlockSettings.of(Material.STONE).strength(9.0f, 10.0f).breakByTool(FabricToolTags.PICKAXES, 3).requiresTool(), UniformIntProvider.create(3, 6));
    public static final OreBlock SMOOTH_BASALT_ORICHALCUM_ORE = new OreBlock(FabricBlockSettings.of(Material.STONE).strength(5.0f, 6.0f).breakByTool(FabricToolTags.PICKAXES, 3).requiresTool().sounds(BlockSoundGroup.BASALT));
    public static final OreBlock STARRITE_ORE = new StarriteOreBlock(FabricBlockSettings.of(Material.STONE).strength(4.0f, 5.0f).breakByTool(FabricToolTags.PICKAXES, 3).requiresTool(), UniformIntProvider.create(3, 6));
    public static final OreBlock TUFF_ORICHALCUM_ORE = new OreBlock(FabricBlockSettings.of(Material.STONE).strength(6.0f, 6.0f).breakByTool(FabricToolTags.PICKAXES, 3).requiresTool().sounds(BlockSoundGroup.TUFF));
    public static final OreBlock VERMICULITE_ORE = new OreBlock(FabricBlockSettings.of(Material.STONE).strength(2.0f, 4.0f).breakByTool(FabricToolTags.PICKAXES, 1).requiresTool(), UniformIntProvider.create(0, 2));

    // Anvils
    public static HashSet<Block> ANVILS = new HashSet<>();
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
}
