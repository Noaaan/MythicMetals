package nourl.mythicmetals.blocks;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.tool.attribute.v1.FabricToolTags;
import net.minecraft.block.OreBlock;
import net.minecraft.block.Material;
import net.minecraft.sound.BlockSoundGroup;

public class MythicMetalsOres {
    public static final OreBlock ADAMANTITE_ORE = new OreBlock(FabricBlockSettings.of(Material.STONE).strength(5.0f, 4.0f).breakByTool(FabricToolTags.PICKAXES, 3).requiresTool().sounds(BlockSoundGroup.GILDED_BLACKSTONE));
    public static final OreBlock AETHERIUM_ORE = new OreBlock(FabricBlockSettings.of(Material.STONE).strength(4.0f, 4.0f).breakByTool(FabricToolTags.PICKAXES, 3).requiresTool().sounds(BlockSoundGroup.GILDED_BLACKSTONE));
    public static final OreBlock AQUARIUM_ORE = new OreBlock(FabricBlockSettings.of(Material.STONE).strength(3.0f, 4.0f).breakByTool(FabricToolTags.PICKAXES, 1).requiresTool());
    public static final OreBlock BANGLUM_ORE = new OreBlock(FabricBlockSettings.of(Material.STONE).strength(3.0f, 4.0f).breakByTool(FabricToolTags.PICKAXES, 1).requiresTool());
    public static final OreBlock BLACKSTONE_STORMYX_ORE = new OreBlock(FabricBlockSettings.of(Material.STONE).strength(4.0f, 4.0f).breakByTool(FabricToolTags.PICKAXES, 2).requiresTool());
    public static final OreBlock CARMOT_ORE = new OreBlock(FabricBlockSettings.of(Material.STONE).strength(3.0f, 4.0f).breakByTool(FabricToolTags.PICKAXES, 2).requiresTool());
    public static final OreBlock CALCITE_KYBER_ORE = new OreBlock(FabricBlockSettings.of(Material.STONE).strength(3.0f, 4.0f).breakByTool(FabricToolTags.PICKAXES, 2).requiresTool().sounds(BlockSoundGroup.CALCITE));
    public static final OreBlock DEEPSLATE_ADAMANTITE_ORE = new OreBlock(FabricBlockSettings.of(Material.STONE).strength(6.0f, 6.0f).breakByTool(FabricToolTags.PICKAXES, 3).requiresTool().sounds(BlockSoundGroup.DEEPSLATE));
    public static final OreBlock DEEPSLATE_MYTHRIL_ORE = new OreBlock(FabricBlockSettings.of(Material.STONE).strength(6.0f, 6.0f).breakByTool(FabricToolTags.PICKAXES, 3).requiresTool().sounds(BlockSoundGroup.DEEPSLATE));
    public static final OreBlock KYBER_ORE = new OreBlock(FabricBlockSettings.of(Material.STONE).strength(3.0f, 4.0f).breakByTool(FabricToolTags.PICKAXES, 2).requiresTool());
    public static final OreBlock MANGANESE_ORE = new OreBlock(FabricBlockSettings.of(Material.STONE).strength(3.0f, 4.0f).breakByTool(FabricToolTags.PICKAXES, 2).requiresTool());
    public static final OreBlock MIDAS_GOLD_ORE = new OreBlock(FabricBlockSettings.of(Material.STONE).strength(3.0f, 4.0f).breakByTool(FabricToolTags.PICKAXES, 2).requiresTool());
    public static final OreBlock MYTHRIL_ORE = new OreBlock(FabricBlockSettings.of(Material.STONE).strength(5.0f, 4.0f).breakByTool(FabricToolTags.PICKAXES, 3).requiresTool().sounds(BlockSoundGroup.GILDED_BLACKSTONE));
    public static final OreBlock ORICHALCUM_ORE = new OreBlock(FabricBlockSettings.of(Material.STONE).strength(5.0f, 4.0f).breakByTool(FabricToolTags.PICKAXES, 3).requiresTool().sounds(BlockSoundGroup.GILDED_BLACKSTONE));
    public static final OreBlock OSMIUM_ORE = new OreBlock(FabricBlockSettings.of(Material.STONE).strength(4.0f, 4.0f).breakByTool(FabricToolTags.PICKAXES, 2).requiresTool());
    public static final OreBlock PLATINUM_ORE = new OreBlock(FabricBlockSettings.of(Material.STONE).strength(3.0f, 4.0f).breakByTool(FabricToolTags.PICKAXES, 2).requiresTool());
    public static final OreBlock PROMETHEUM_ORE = new OreBlock(FabricBlockSettings.of(Material.STONE).strength(3.0f, 4.0f).breakByTool(FabricToolTags.PICKAXES, 3).requiresTool());
    public static final OreBlock QUADRILLUM_ORE = new OreBlock(FabricBlockSettings.of(Material.STONE).strength(4.0f, 4.0f).breakByTool(FabricToolTags.PICKAXES, 1).requiresTool());
    public static final OreBlock RUNITE_ORE = new OreBlock(FabricBlockSettings.of(Material.STONE).strength(4.0f, 4.0f).breakByTool(FabricToolTags.PICKAXES, 2).requiresTool());
    public static final OreBlock SILVER_ORE = new OreBlock(FabricBlockSettings.of(Material.STONE).strength(3.0f, 4.0f).breakByTool(FabricToolTags.PICKAXES, 1).requiresTool());
    public static final OreBlock STARRITE_ORE = new StarriteOreBlock(FabricBlockSettings.of(Material.STONE).strength(4.0f, 5.0f).breakByTool(FabricToolTags.PICKAXES, 3).requiresTool());
    public static final OreBlock STORMYX_ORE = new OreBlock(FabricBlockSettings.of(Material.STONE).strength(4.0f, 4.0f).breakByTool(FabricToolTags.PICKAXES, 2).requiresTool());
    public static final OreBlock TIN_ORE = new OreBlock(FabricBlockSettings.of(Material.STONE).strength(2.0f, 4.0f).breakByTool(FabricToolTags.PICKAXES, 1).requiresTool());
    public static final OreBlock TUFF_ORICHALCUM_ORE = new OreBlock(FabricBlockSettings.of(Material.STONE).strength(6.0f, 6.0f).breakByTool(FabricToolTags.PICKAXES, 3).requiresTool().sounds(BlockSoundGroup.TUFF));
    public static final OreBlock TRUESILVER_ORE = new OreBlock(FabricBlockSettings.of(Material.STONE).strength(2.0f, 4.0f).breakByTool(FabricToolTags.PICKAXES, 2).requiresTool());
    public static final OreBlock UNOBTAINIUM_ORE = new OreBlock(FabricBlockSettings.of(Material.STONE).strength(4.0f, 12000.0f).breakByTool(FabricToolTags.PICKAXES, 4).requiresTool().sounds(BlockSoundGroup.LODESTONE));
    public static final OreBlock VERMICULITE_ORE = new OreBlock(FabricBlockSettings.of(Material.STONE).strength(2.0f, 4.0f).breakByTool(FabricToolTags.PICKAXES, 1).requiresTool());

}
