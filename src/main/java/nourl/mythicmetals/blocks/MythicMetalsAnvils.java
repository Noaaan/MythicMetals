package nourl.mythicmetals.blocks;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.tool.attribute.v1.FabricToolTags;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.sound.BlockSoundGroup;

import java.util.HashSet;

public class MythicMetalsAnvils {

    public static HashSet<Block> ANVILS = new HashSet<>();

    public static final AnvilBase ADAMANTITE_ANVIL = new AnvilBase(FabricBlockSettings.of(Material.REPAIR_STATION).strength(5.0F, 15000.0F).sounds(BlockSoundGroup.ANVIL).breakByTool(FabricToolTags.PICKAXES, 3).requiresTool());
    public static final AnvilBase AETHERIUM_ANVIL = new AnvilBase(FabricBlockSettings.of(Material.REPAIR_STATION).strength(5.0F, 15000.0F).sounds(BlockSoundGroup.ANVIL).breakByTool(FabricToolTags.PICKAXES, 3).requiresTool());
    public static final AnvilBase AQUARIUM_ANVIL = new AnvilBase(FabricBlockSettings.of(Material.REPAIR_STATION).strength(5.0F, 15000.0F).sounds(BlockSoundGroup.ANVIL).breakByTool(FabricToolTags.PICKAXES, 1).requiresTool());
    public static final AnvilBase ARGONIUM_ANVIL = new AnvilBase(FabricBlockSettings.of(Material.REPAIR_STATION).strength(5.0F, 15000.0F).sounds(BlockSoundGroup.ANVIL).breakByTool(FabricToolTags.PICKAXES, 2).requiresTool());
    public static final AnvilBase BANGLUM_ANVIL = new AnvilBase(FabricBlockSettings.of(Material.REPAIR_STATION).strength(5.0F, 15000.0F).sounds(BlockSoundGroup.ANVIL).breakByTool(FabricToolTags.PICKAXES, 1).requiresTool());
    public static final AnvilBase BRASS_ANVIL = new AnvilBase(FabricBlockSettings.of(Material.REPAIR_STATION).strength(5.0F, 15000.0F).sounds(BlockSoundGroup.ANVIL).breakByTool(FabricToolTags.PICKAXES, 1).requiresTool());
    public static final AnvilBase BRONZE_ANVIL = new AnvilBase(FabricBlockSettings.of(Material.REPAIR_STATION).strength(5.0F, 15000.0F).sounds(BlockSoundGroup.ANVIL).breakByTool(FabricToolTags.PICKAXES, 2).requiresTool());
    public static final AnvilBase CARMOT_ANVIL = new AnvilBase(FabricBlockSettings.of(Material.REPAIR_STATION).strength(5.0F, 15000.0F).sounds(BlockSoundGroup.ANVIL).breakByTool(FabricToolTags.PICKAXES, 2).requiresTool());
    public static final AnvilBase CELESTIUM_ANVIL = new AnvilBase(FabricBlockSettings.of(Material.REPAIR_STATION).strength(5.0F, 15000.0F).sounds(BlockSoundGroup.ANVIL).breakByTool(FabricToolTags.PICKAXES, 3).requiresTool());
    public static final AnvilBase COPPER_ANVIL = new AnvilBase(FabricBlockSettings.of(Material.REPAIR_STATION).strength(5.0F, 15000.0F).sounds(BlockSoundGroup.ANVIL).breakByTool(FabricToolTags.PICKAXES, 1).requiresTool());
    public static final AnvilBase DISCORDIUM_ANVIL = new AnvilBase(FabricBlockSettings.of(Material.REPAIR_STATION).strength(5.0F, 15000.0F).sounds(BlockSoundGroup.ANVIL).breakByTool(FabricToolTags.PICKAXES, 3).requiresTool());
    public static final AnvilBase DURASTEEL_ANVIL = new AnvilBase(FabricBlockSettings.of(Material.REPAIR_STATION).strength(5.0F, 15000.0F).sounds(BlockSoundGroup.ANVIL).breakByTool(FabricToolTags.PICKAXES, 3).requiresTool());
    public static final AnvilBase ELECTRUM_ANVIL = new AnvilBase(FabricBlockSettings.of(Material.REPAIR_STATION).strength(5.0F, 15000.0F).sounds(BlockSoundGroup.ANVIL).breakByTool(FabricToolTags.PICKAXES, 1).requiresTool());
    public static final AnvilBase ETHERITE_ANVIL = new AnvilBase(FabricBlockSettings.of(Material.REPAIR_STATION).strength(5.0F, 15000.0F).sounds(BlockSoundGroup.ANVIL).breakByTool(FabricToolTags.PICKAXES, 4).requiresTool());
    public static final AnvilBase KYBER_ANVIL = new AnvilBase(FabricBlockSettings.of(Material.REPAIR_STATION).strength(5.0F, 15000.0F).sounds(BlockSoundGroup.ANVIL).breakByTool(FabricToolTags.PICKAXES, 2).requiresTool());
    public static final AnvilBase LUTETIUM_ANVIL = new AnvilBase(FabricBlockSettings.of(Material.REPAIR_STATION).strength(5.0F, 15000.0F).sounds(BlockSoundGroup.ANVIL).breakByTool(FabricToolTags.PICKAXES, 2).requiresTool());
    public static final AnvilBase MANGANESE_ANVIL = new AnvilBase(FabricBlockSettings.of(Material.REPAIR_STATION).strength(5.0F, 15000.0F).sounds(BlockSoundGroup.ANVIL).breakByTool(FabricToolTags.PICKAXES, 2).requiresTool());
    public static final AnvilBase METALLURGIUM_ANVIL = new AnvilBase(FabricBlockSettings.of(Material.REPAIR_STATION).strength(5.0F, 15000.0F).sounds(BlockSoundGroup.ANVIL).breakByTool(FabricToolTags.PICKAXES, 4).requiresTool());
    public static final AnvilBase MIDAS_GOLD_ANVIL = new AnvilBase(FabricBlockSettings.of(Material.REPAIR_STATION).strength(5.0F, 15000.0F).sounds(BlockSoundGroup.ANVIL).breakByTool(FabricToolTags.PICKAXES, 2).requiresTool());
    public static final AnvilBase MYTHRIL_ANVIL = new AnvilBase(FabricBlockSettings.of(Material.REPAIR_STATION).strength(5.0F, 15000.0F).sounds(BlockSoundGroup.ANVIL).breakByTool(FabricToolTags.PICKAXES, 3).requiresTool());
    public static final AnvilBase ORICHALCUM_ANVIL = new AnvilBase(FabricBlockSettings.of(Material.REPAIR_STATION).strength(5.0F, 15000.0F).sounds(BlockSoundGroup.ANVIL).breakByTool(FabricToolTags.PICKAXES, 3).requiresTool());
    public static final AnvilBase OSMIUM_ANVIL = new AnvilBase(FabricBlockSettings.of(Material.REPAIR_STATION).strength(5.0F, 15000.0F).sounds(BlockSoundGroup.ANVIL).breakByTool(FabricToolTags.PICKAXES, 2).requiresTool());
    public static final AnvilBase PLATINUM_ANVIL = new AnvilBase(FabricBlockSettings.of(Material.REPAIR_STATION).strength(5.0F, 15000.0F).sounds(BlockSoundGroup.ANVIL).breakByTool(FabricToolTags.PICKAXES, 2).requiresTool());
    public static final AnvilBase PROMETHEUM_ANVIL = new AnvilBase(FabricBlockSettings.of(Material.REPAIR_STATION).strength(5.0F, 15000.0F).sounds(BlockSoundGroup.ANVIL).breakByTool(FabricToolTags.PICKAXES, 3).requiresTool());
    public static final AnvilBase QUADRILLUM_ANVIL = new AnvilBase(FabricBlockSettings.of(Material.REPAIR_STATION).strength(5.0F, 15000.0F).sounds(BlockSoundGroup.ANVIL).breakByTool(FabricToolTags.PICKAXES, 1).requiresTool());
    public static final AnvilBase QUICKSILVER_ANVIL = new AnvilBase(FabricBlockSettings.of(Material.REPAIR_STATION).strength(5.0F, 15000.0F).sounds(BlockSoundGroup.ANVIL).breakByTool(FabricToolTags.PICKAXES, 2).requiresTool());
    public static final AnvilBase RUNITE_ANVIL = new AnvilBase(FabricBlockSettings.of(Material.REPAIR_STATION).strength(5.0F, 15000.0F).sounds(BlockSoundGroup.ANVIL).breakByTool(FabricToolTags.PICKAXES, 2).requiresTool());
    public static final AnvilBase SILVER_ANVIL = new AnvilBase(FabricBlockSettings.of(Material.REPAIR_STATION).strength(5.0F, 15000.0F).sounds(BlockSoundGroup.ANVIL).breakByTool(FabricToolTags.PICKAXES, 1).requiresTool());
    public static final AnvilBase SLOWSILVER_ANVIL = new AnvilBase(FabricBlockSettings.of(Material.REPAIR_STATION).strength(5.0F, 15000.0F).sounds(BlockSoundGroup.ANVIL).breakByTool(FabricToolTags.PICKAXES, 2).requiresTool());
    public static final AnvilBase STARRITE_ANVIL = new AnvilBase(FabricBlockSettings.of(Material.REPAIR_STATION).strength(5.0F, 15000.0F).sounds(BlockSoundGroup.ANVIL).breakByTool(FabricToolTags.PICKAXES, 3).requiresTool());
    public static final AnvilBase STEEL_ANVIL = new AnvilBase(FabricBlockSettings.of(Material.REPAIR_STATION).strength(5.0F, 15000.0F).sounds(BlockSoundGroup.ANVIL).breakByTool(FabricToolTags.PICKAXES, 2).requiresTool());
    public static final AnvilBase STORMYX_ANVIL = new AnvilBase(FabricBlockSettings.of(Material.REPAIR_STATION).strength(5.0F, 15000.0F).sounds(BlockSoundGroup.ANVIL).breakByTool(FabricToolTags.PICKAXES, 2).requiresTool());
    public static final AnvilBase TANTALITE_ANVIL = new AnvilBase(FabricBlockSettings.of(Material.REPAIR_STATION).strength(5.0F, 15000.0F).sounds(BlockSoundGroup.ANVIL).breakByTool(FabricToolTags.PICKAXES, 2).requiresTool());
    public static final AnvilBase TIN_ANVIL = new AnvilBase(FabricBlockSettings.of(Material.REPAIR_STATION).strength(5.0F, 15000.0F).sounds(BlockSoundGroup.ANVIL).breakByTool(FabricToolTags.PICKAXES, 1).requiresTool());
    public static final AnvilBase TRUESILVER_ANVIL = new AnvilBase(FabricBlockSettings.of(Material.REPAIR_STATION).strength(5.0F, 15000.0F).sounds(BlockSoundGroup.ANVIL).breakByTool(FabricToolTags.PICKAXES, 2).requiresTool());
    public static final AnvilBase UNOBTAINIUM_ANVIL = new AnvilBase(FabricBlockSettings.of(Material.REPAIR_STATION).strength(5.0F, 15000.0F).sounds(BlockSoundGroup.ANVIL).breakByTool(FabricToolTags.PICKAXES, 4).requiresTool());
    public static final AnvilBase UR_ANVIL = new AnvilBase(FabricBlockSettings.of(Material.REPAIR_STATION).strength(5.0F, 15000.0F).sounds(BlockSoundGroup.ANVIL).breakByTool(FabricToolTags.PICKAXES, 2).requiresTool());
    public static final AnvilBase ZINC_ANVIL = new AnvilBase(FabricBlockSettings.of(Material.REPAIR_STATION).strength(5.0F, 15000.0F).sounds(BlockSoundGroup.ANVIL).breakByTool(FabricToolTags.PICKAXES, 1).requiresTool());}
