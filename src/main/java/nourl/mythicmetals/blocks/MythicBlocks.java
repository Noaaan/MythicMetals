package nourl.mythicmetals.blocks;

import com.glisco.owo.registration.reflect.SimpleFieldProcessingSubject;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.tool.attribute.v1.FabricToolTags;
import net.minecraft.block.ChainBlock;
import net.minecraft.block.Material;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import nourl.mythicmetals.utils.RegistryHelper;

@SuppressWarnings("unused")
public class MythicBlocks implements SimpleFieldProcessingSubject<ChainBlock> {
    public static final BlockSet ADAMANTITE = BlockSet.Builder.begin("adamantite", false)
            .createDefaultSet(4, 3)
            .strength(6, 12).sounds(BlockSoundGroup.DEEPSLATE)
            .createOreVariant("deepslate", 3)
            .finish();

    public static final BlockSet AETHERIUM = BlockSet.Builder.begin("aetherium", false)
            .createDefaultSet(5, 3).finish();

    public static final BlockSet AQUARIUM = BlockSet.Builder.begin("aquarium", false)
            .createDefaultSet(4F, 2, 4.5F, 2)
            .createAnvil(2)
            .finish();

    public static final BlockSet BANGLUM = BlockSet.Builder.begin("banglum", false)
            .createDefaultSet(3, 1).finish();

    public static final BlockSet BRONZE = BlockSet.Builder.begin("bronze", false)
            .createAnvilSet(5, 2).finish();

    public static final BlockSet CARMOT = BlockSet.Builder.begin("carmot", false)
            .createDefaultSet(4, 2).finish();

    public static final BlockSet CELESTIUM = BlockSet.Builder.begin("celestium", false)
            .createAnvilSet(10, 15, 4).finish();

    public static final BlockSet DISCORDIUM = BlockSet.Builder.begin("discordium", false)
            .createAnvilSet(5, 3).finish();

    public static final BlockSet DURASTEEL = BlockSet.Builder.begin("durasteel", false)
            .createAnvilSet(5, 3).finish();

    public static final BlockSet ETHERITE = BlockSet.Builder.begin("etherite", false)
            .createAnvilSet(5, 3).finish();

    public static final BlockSet HALLOWED = BlockSet.Builder.begin("hallowed", false)
            .createAnvilSet(5, 4).finish();

    public static final BlockSet KYBER = BlockSet.Builder.begin("kyber", false)
            .createDefaultSet(3.0F, 2, 4.0F, 2)
            .createAnvil(2)
            .strength(3.0F, 3.0F)
            .sounds(BlockSoundGroup.CALCITE)
            .createOreVariant("calcite", 2)
            .finish();

    public static final BlockSet MANGANESE = BlockSet.Builder.begin("manganese", false)
            .createDefaultSet(3.0F, 1).finish();

    public static final BlockSet MIDAS_GOLD = BlockSet.Builder.begin("midas_gold", false)
            .strength(3.5F).sounds(BlockSoundGroup.NETHER_GOLD_ORE)
            .createOre(2)
            .strength(4.5F).sounds(BlockSoundGroup.METAL)
            .createOreStorageBlock(2).createStorageBlock(3)
            .createAnvil(2)
            .finish();

    public static final BlockSet METALLURGIUM = BlockSet.Builder.begin("metallurgium", true)
            .sounds(BlockSoundGroup.NETHERITE)
            .createAnvilSet(60.0F, 15000F, 5)
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
            .strength(5.0F).sounds(BlockSoundGroup.NETHER_ORE)
            .createOre(3)
            .strength(6.0F).sounds(BlockSoundGroup.METAL)
            .createOreStorageBlock(3).createStorageBlock(3)
            .createAnvil(3)
            .finish();

    public static final BlockSet PLATINUM = BlockSet.Builder.begin("platinum", false)
            .createDefaultSet(3.5F, 2).finish();

    public static final BlockSet PROMETHEUM = BlockSet.Builder.begin("prometheum", false)
            .createDefaultSet(5.0F, 2)
            .sounds(BlockSoundGroup.DEEPSLATE)
            .createOreVariant("deepslate", 3)
            .finish();

    public static final BlockSet QUADRILLUM = BlockSet.Builder.begin("quadrillum", false)
            .createDefaultSet(4.0F, 2).finish();

    public static final BlockSet QUICKSILVER = BlockSet.Builder.begin("quicksilver", false)
            .createAnvilSet(5, 3).finish();


    public static final BlockSet RUNITE = BlockSet.Builder.begin("runite", false)
            .createDefaultSet(4.0F, 2).finish();

    public static final BlockSet SILVER = BlockSet.Builder.begin("silver", false)
            .createDefaultSet(3.0F, 1).finish();

    public static final BlockSet STAR_PLATINUM = BlockSet.Builder.begin("star_platinum", false)
            .createAnvilSet(5, 3).finish();

    public static final BlockSet STARRITE = BlockSet.Builder.begin("starrite", false)
            .strength(4.0F).createStarriteOre(3, UniformIntProvider.create(3, 6))
            .sounds(BlockSoundGroup.CALCITE)
            .createStarriteOreVariant("calcite", 3, UniformIntProvider.create(3, 6))
            .sounds(BlockSoundGroup.STONE).strength(5.0F)
            .createStarriteOreVariant("end_stone", 4, UniformIntProvider.create(3, 6))
            .sounds(BlockSoundGroup.AMETHYST_BLOCK)
            .createStorageBlock(4)
            .finish();

    public static final BlockSet STEEL = BlockSet.Builder.begin("steel", false)
            .createAnvilSet(5, 2).finish();

    public static final BlockSet STORMYX = BlockSet.Builder.begin("stormyx", false)
            .strength(4.5F).sounds(BlockSoundGroup.NETHER_ORE)
            .createOre(2, UniformIntProvider.create(2, 4))
            .sounds(BlockSoundGroup.GILDED_BLACKSTONE)
            .createOreVariant("blackstone", 2)
            .strength(6.0F)
            .createOreStorageBlock(3)
            .createStorageBlock(3)
            .createAnvil(3)
            .finish();

    public static final BlockSet TIN = BlockSet.Builder.begin("tin", false)
            .createDefaultSet(2.0F, 1, 2.5F, 1).finish();

    public static final BlockSet VERMICULITE = BlockSet.Builder.begin("vermiculite", false)
            .strength(1.5F).createOre(1, UniformIntProvider.create(0, 2))
            .sounds(BlockSoundGroup.GRAVEL)
            .createFallingStorageBlock(Material.AGGREGATE, 0, FabricToolTags.SHOVELS)
            .finish();

    public static final BlockSet UNOBTAINIUM = BlockSet.Builder.begin("unobtainium", true)
            .strength(8.0F, 13000F)
            .sounds(BlockSoundGroup.LODESTONE)
            .createOre(4, UniformIntProvider.create(4, 7))
            .strength(10F, 14000F)
            .sounds(BlockSoundGroup.DEEPSLATE)
            .createOreVariant("deepslate", 4)
            .sounds(BlockSoundGroup.LODESTONE)
            .strength(9F, 15000F)
            .createStorageBlock(5)
            .finish();

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

    @Override
    public Class<ChainBlock> getTargetFieldType() {
        return ChainBlock.class;
    }

    @Override
    public void afterFieldProcessing() {
        BlockSet.Builder.register();
    }

    @Override
    public void processField(ChainBlock chainBlock, String name) {
        RegistryHelper.chain(name, chainBlock, chainBlock == METALLURGIUM_CHAIN || chainBlock == PALLADIUM_CHAIN);
    }
}
