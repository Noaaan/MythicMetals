package nourl.mythicmetals.blocks;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.tool.attribute.v1.FabricToolTags;
import net.minecraft.block.AnvilBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.sound.BlockSoundGroup;

import java.util.HashSet;

public class MythicAnvils {

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