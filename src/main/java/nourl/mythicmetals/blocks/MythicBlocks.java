package nourl.mythicmetals.blocks;

import net.minecraft.block.Material;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.intprovider.UniformIntProvider;

@SuppressWarnings("unused")
public class MythicBlocks {
    private static final Identifier STONE_MINING_LEVEL = new Identifier("minecraft:needs_stone_tool");
    private static final Identifier IRON_MINING_LEVEL = new Identifier("minecraft:needs_iron_tool");
    private static final Identifier DIAMOND_MINING_LEVEL = new Identifier("minecraft:needs_diamond_tool");
    private static final Identifier NETHERITE_MINING_LEVEL = new Identifier("fabric:needs_tool_level_4");
    private static final Identifier MYTHIC_MINING_LEVEL = new Identifier("fabric:needs_tool_level_5");

    public static final BlockSet ADAMANTITE = BlockSet.Builder.begin("adamantite", false)
            .createDefaultSet(4, DIAMOND_MINING_LEVEL, NETHERITE_MINING_LEVEL)
            .strength(6, 12).sounds(BlockSoundGroup.DEEPSLATE)
            .createOreVariant("deepslate", DIAMOND_MINING_LEVEL)
            .finish();

    public static final BlockSet AQUARIUM = BlockSet.Builder.begin("aquarium", false)
            .createDefaultSet(4F, IRON_MINING_LEVEL, 4.5F, IRON_MINING_LEVEL)
            .createAnvil(IRON_MINING_LEVEL)
            .finish();

    public static final BlockSet BANGLUM = BlockSet.Builder.begin("banglum", false)
            .createDefaultSet(3, STONE_MINING_LEVEL, IRON_MINING_LEVEL).finish();

    public static final BlockSet BRONZE = BlockSet.Builder.begin("bronze", false)
            .createAnvilSet(5, IRON_MINING_LEVEL).finish();

    public static final BlockSet CARMOT = BlockSet.Builder.begin("carmot", false)
            .createDefaultSet(4.5F, IRON_MINING_LEVEL, DIAMOND_MINING_LEVEL)
            .strength(5.5F, 12).sounds(BlockSoundGroup.DEEPSLATE)
            .createOreVariant("deepslate", DIAMOND_MINING_LEVEL)
            .finish();

    public static final BlockSet CELESTIUM = BlockSet.Builder.begin("celestium", false)
            .createAnvilSet(10, 15, MYTHIC_MINING_LEVEL).finish();

    public static final BlockSet DURASTEEL = BlockSet.Builder.begin("durasteel", false)
            .createAnvilSet(5, DIAMOND_MINING_LEVEL).finish();

    public static final BlockSet HALLOWED = BlockSet.Builder.begin("hallowed", false)
            .createAnvilSet(5, NETHERITE_MINING_LEVEL).finish();

    public static final BlockSet KYBER = BlockSet.Builder.begin("kyber", false)
            .createDefaultSet(3.0F, STONE_MINING_LEVEL, 4.0F, IRON_MINING_LEVEL)
            .createAnvil(IRON_MINING_LEVEL)
            .strength(3.0F, 3.0F)
            .sounds(BlockSoundGroup.CALCITE)
            .createOreVariant("calcite", IRON_MINING_LEVEL)
            .finish();

    public static final BlockSet MANGANESE = BlockSet.Builder.begin("manganese", false)
            .createDefaultSet(3.0F, STONE_MINING_LEVEL, IRON_MINING_LEVEL).finish();

    public static final BlockSet MIDAS_GOLD = BlockSet.Builder.begin("midas_gold", false)
            .strength(3.5F).sounds(BlockSoundGroup.NETHER_GOLD_ORE)
            .createOre(STONE_MINING_LEVEL)
            .strength(4.5F)
            .createOreStorageBlock(IRON_MINING_LEVEL)
            .sounds(BlockSoundGroup.METAL)
            .createStorageBlock(IRON_MINING_LEVEL)
            .createAnvil(IRON_MINING_LEVEL)
            .finish();

    public static final BlockSet METALLURGIUM = BlockSet.Builder.begin("metallurgium", true)
            .sounds(BlockSoundGroup.NETHERITE)
            .createAnvilSet(60.0F, 15000F, MYTHIC_MINING_LEVEL)
            .finish();

    public static final BlockSet MYTHRIL = BlockSet.Builder.begin("mythril", false)
            .createDefaultSet(4.5F, DIAMOND_MINING_LEVEL, NETHERITE_MINING_LEVEL)
            .strength(5.0F).sounds(BlockSoundGroup.DEEPSLATE)
            .createOreVariant("deepslate", DIAMOND_MINING_LEVEL)
            .finish();

    public static final BlockSet ORICHALCUM = BlockSet.Builder.begin("orichalcum", false)
            .createDefaultSet(5.0F, DIAMOND_MINING_LEVEL, NETHERITE_MINING_LEVEL)
            .sounds(BlockSoundGroup.TUFF)
            .createOreVariant("tuff", DIAMOND_MINING_LEVEL)
            .sounds(BlockSoundGroup.BASALT)
            .createOreVariant("smooth_basalt", DIAMOND_MINING_LEVEL)
            .strength(5.5F).sounds(BlockSoundGroup.DEEPSLATE)
            .createOreVariant("deepslate", DIAMOND_MINING_LEVEL)
            .finish();

    public static final BlockSet OSMIUM = BlockSet.Builder.begin("osmium", false)
            .createDefaultSet(4.0F, IRON_MINING_LEVEL, DIAMOND_MINING_LEVEL).finish();

    public static final BlockSet PALLADIUM = BlockSet.Builder.begin("palladium", true)
            .strength(5.0F).sounds(BlockSoundGroup.NETHER_ORE)
            .createOre(DIAMOND_MINING_LEVEL)
            .strength(6.0F).sounds(BlockSoundGroup.METAL)
            .createOreStorageBlock(DIAMOND_MINING_LEVEL)
            .createStorageBlock(Material.METAL, DIAMOND_MINING_LEVEL)
            .createAnvil(DIAMOND_MINING_LEVEL)
            .finish();

    public static final BlockSet PLATINUM = BlockSet.Builder.begin("platinum", false)
            .createDefaultSet(3.5F, IRON_MINING_LEVEL, DIAMOND_MINING_LEVEL).finish();

    public static final BlockSet PROMETHEUM = BlockSet.Builder.begin("prometheum", false)
            .createDefaultSet(5.0F, IRON_MINING_LEVEL, DIAMOND_MINING_LEVEL)
            .sounds(BlockSoundGroup.DEEPSLATE)
            .createOreVariant("deepslate", DIAMOND_MINING_LEVEL)
            .finish();

    public static final BlockSet QUADRILLUM = BlockSet.Builder.begin("quadrillum", false)
            .createDefaultSet(4.0F, IRON_MINING_LEVEL, DIAMOND_MINING_LEVEL).finish();

    public static final BlockSet RUNITE = BlockSet.Builder.begin("runite", false)
            .createDefaultSet(4.0F, IRON_MINING_LEVEL, DIAMOND_MINING_LEVEL).finish();

    public static final BlockSet SILVER = BlockSet.Builder.begin("silver", false)
            .createDefaultSet(3.0F, STONE_MINING_LEVEL, IRON_MINING_LEVEL).finish();

    public static final BlockSet STAR_PLATINUM = BlockSet.Builder.begin("star_platinum", false)
            .createAnvilSet(5, DIAMOND_MINING_LEVEL).finish();

    public static final BlockSet STARRITE = BlockSet.Builder.begin("starrite", false)
            .strength(4.0F).createStarriteOre(DIAMOND_MINING_LEVEL, UniformIntProvider.create(3, 6))
            .sounds(BlockSoundGroup.CALCITE)
            .createStarriteOreVariant("calcite", DIAMOND_MINING_LEVEL, UniformIntProvider.create(3, 6))
            .sounds(BlockSoundGroup.STONE).strength(5.0F)
            .createStarriteOreVariant("end_stone", NETHERITE_MINING_LEVEL, UniformIntProvider.create(3, 6))
            .createAmethystStorageBlock(NETHERITE_MINING_LEVEL)
            .finish();

    public static final BlockSet STEEL = BlockSet.Builder.begin("steel", false)
            .createAnvilSet(5, IRON_MINING_LEVEL).finish();

    public static final BlockSet STORMYX = BlockSet.Builder.begin("stormyx", false)
            .strength(4.5F).sounds(BlockSoundGroup.NETHER_ORE)
            .createOre(IRON_MINING_LEVEL, UniformIntProvider.create(2, 4))
            .sounds(BlockSoundGroup.GILDED_BLACKSTONE)
            .createOreVariant("blackstone", IRON_MINING_LEVEL)
            .strength(6.0F)
            .createOreStorageBlock(DIAMOND_MINING_LEVEL)
            .sounds(BlockSoundGroup.METAL)
            .createStorageBlock(Material.METAL, DIAMOND_MINING_LEVEL)
            .createAnvil(DIAMOND_MINING_LEVEL)
            .finish();

    public static final BlockSet TIN = BlockSet.Builder.begin("tin", false)
            .createDefaultSet(2.0F, STONE_MINING_LEVEL, 2.5F, STONE_MINING_LEVEL).finish();

    public static final BlockSet VERMICULITE = BlockSet.Builder.begin("vermiculite", false)
            .strength(1.5F).createOre(STONE_MINING_LEVEL, UniformIntProvider.create(0, 2))
            .sounds(BlockSoundGroup.GRAVEL)
            .createFallingStorageBlock(Material.AGGREGATE, STONE_MINING_LEVEL)
            .finish();

    public static final BlockSet UNOBTAINIUM = BlockSet.Builder.begin("unobtainium", true)
            .strength(13.0F, 13000F)
            .sounds(BlockSoundGroup.LODESTONE)
            .createOre(NETHERITE_MINING_LEVEL, UniformIntProvider.create(4, 7))
            .strength(15F, 14000F)
            .sounds(BlockSoundGroup.DEEPSLATE)
            .createOreVariant("deepslate", NETHERITE_MINING_LEVEL)
            .sounds(BlockSoundGroup.LODESTONE)
            .strength(25F, 15000F)
            .createStorageBlock(MYTHIC_MINING_LEVEL)
            .finish();


    public static void init() {
        BlockSet.Builder.register();
    }

}
