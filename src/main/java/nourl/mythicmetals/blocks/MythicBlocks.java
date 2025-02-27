package nourl.mythicmetals.blocks;

import net.minecraft.block.*;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import nourl.mythicmetals.MythicMetals;
import nourl.mythicmetals.misc.RegistryHelper;
import nourl.mythicmetals.registry.RegisterSounds;
import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("unused")
public class MythicBlocks {

    public static final Map<String, BlockSet> BLOCKSET_MAP = new HashMap<>();
    private static final Identifier STONE_MINING_LEVEL = BlockTags.NEEDS_STONE_TOOL.id();
    private static final Identifier IRON_MINING_LEVEL = BlockTags.NEEDS_IRON_TOOL.id();
    private static final Identifier DIAMOND_MINING_LEVEL = BlockTags.NEEDS_DIAMOND_TOOL.id();
    private static final Identifier NETHERITE_MINING_LEVEL = Identifier.of("needs_netherite_tool");
    private static final Identifier MYTHIC_MINING_LEVEL = RegistryHelper.id("needs_unobtainable_tool");

    public static final BlockSet ADAMANTITE = BlockSet.Builder.begin("adamantite", false)
        .createDefaultSet(4, DIAMOND_MINING_LEVEL, DIAMOND_MINING_LEVEL)
        .strength(6, 12).sounds(BlockSoundGroup.DEEPSLATE)
        .createOreVariant("deepslate", DIAMOND_MINING_LEVEL)
        .finish();

    public static final BlockSet AQUARIUM = BlockSet.Builder.begin("aquarium", false)
        .createDefaultSet(4F, IRON_MINING_LEVEL, 4.5F, IRON_MINING_LEVEL)
        .createAnvil(IRON_MINING_LEVEL)
        .finish();
    public static final Block AQUARIUM_GLASS = new TransparentBlock(AbstractBlock.Settings.copy(Blocks.BLUE_STAINED_GLASS));
    public static final AquariumResonatorBlock AQUARIUM_RESONATOR = new AquariumResonatorBlock(AbstractBlock.Settings.copy(Blocks.CONDUIT));

    public static final BlockSet BANGLUM = BlockSet.Builder.begin("banglum", false)
        .strength(5.0F, 5.5F)
        .createBanglumOre(IRON_MINING_LEVEL)
        .createOreStorageBlock(IRON_MINING_LEVEL)
        .createStorageBlock(IRON_MINING_LEVEL)
        .createAnvil(IRON_MINING_LEVEL)
        .sounds(BlockSoundGroup.NETHER_ORE)
        .createBanglumOreVariant("nether", IRON_MINING_LEVEL)
        .finish();

    public static final BanglumTntBlock BANGLUM_TNT_BLOCK = new BanglumTntBlock(AbstractBlock.Settings.copy(Blocks.TNT));
    public static final Block BANGLUM_NUKE_CORE = new Block(AbstractBlock.Settings.copy(BANGLUM.getStorageBlock()));

    public static final BlockSet BRONZE = BlockSet.Builder.begin("bronze", false)
        .createAnvilSet(5, IRON_MINING_LEVEL).finish();

    public static final BlockSet CARMOT = BlockSet.Builder.begin("carmot", false)
        .createDefaultSet(5.5F, IRON_MINING_LEVEL, IRON_MINING_LEVEL)
        .strength(6.5F, 12).sounds(BlockSoundGroup.DEEPSLATE)
        .createOreVariant("deepslate", DIAMOND_MINING_LEVEL)
        .finish();

public static final Block CARMOT_NUKE_CORE = new Block(AbstractBlock.Settings.copy(BANGLUM_NUKE_CORE));

    public static final BlockSet CELESTIUM = BlockSet.Builder.begin("celestium", false)
        .createAnvilSet(10F, 15F, MYTHIC_MINING_LEVEL).finish();

    public static final BlockSet DURASTEEL = BlockSet.Builder.begin("durasteel", false)
        .createAnvilSet(5F, DIAMOND_MINING_LEVEL).finish();

    public static final BlockSet HALLOWED = BlockSet.Builder.begin("hallowed", false)
        .createAnvilSet(5F, NETHERITE_MINING_LEVEL).finish();

    public static final BlockSet KYBER = BlockSet.Builder.begin("kyber", false)
        .strength(3.0f)
        .createOre(IRON_MINING_LEVEL)
        .createOreStorageBlock(IRON_MINING_LEVEL)
        .strength(4.0f)
        .createCustomStorageBlock(IRON_MINING_LEVEL, AbstractBlock.Settings.create().nonOpaque())
        .createAnvil(IRON_MINING_LEVEL)
        .strength(3.0F, 3.0F)
        .sounds(BlockSoundGroup.CALCITE)
        .createOreVariant("calcite", IRON_MINING_LEVEL)
        .finish();

    public static final BlockSet MANGANESE = BlockSet.Builder.begin("manganese", false)
        .createDefaultSet(3.0F, STONE_MINING_LEVEL, IRON_MINING_LEVEL).finish();

    public static final BlockSet METALLURGIUM = BlockSet.Builder.begin("metallurgium", true)
        .sounds(BlockSoundGroup.NETHERITE)
        .createAnvilSet(60.0F, 15000F, MYTHIC_MINING_LEVEL)
        .finish();

    public static final BlockSet MORKITE = BlockSet.Builder.begin("morkite", false)
        .strength(3.0F)
        .createStorageBlock(IRON_MINING_LEVEL)
        .sounds(RegisterSounds.MORKITE_ORE)
        .createOre(IRON_MINING_LEVEL, UniformIntProvider.create(0, 2))
        .sounds(RegisterSounds.DEEPSLATE_MORKITE_ORE)
        .createOreVariant("deepslate", IRON_MINING_LEVEL, UniformIntProvider.create(0, 2))
        .finish();

    public static final BlockSet MIDAS_GOLD = BlockSet.Builder.begin("midas_gold", false)
        .strength(4F).sounds(BlockSoundGroup.NETHER_GOLD_ORE)
        .createOre(STONE_MINING_LEVEL)
        .strength(5F)
        .createOreStorageBlock(IRON_MINING_LEVEL)
        .sounds(BlockSoundGroup.METAL)
        .createStorageBlock(IRON_MINING_LEVEL)
        .createAnvil(IRON_MINING_LEVEL)
        .finish();

    public static final Block ENCHANTED_MIDAS_GOLD_BLOCK = new EnchantedMidasGoldBlock(AbstractBlock.Settings.copy(MIDAS_GOLD.getStorageBlock()));
    public static final Item ENCHANTED_MIDAS_GOLD_BLOCK_ITEM = new BlockItem(ENCHANTED_MIDAS_GOLD_BLOCK, new Item.Settings().group(MythicMetals.TABBED_GROUP).tab(1).rarity(Rarity.UNCOMMON)) {
        @Override
        public boolean hasGlint(ItemStack stack) {
            return true;
        }
    };

    public static final BlockSet MYTHRIL = BlockSet.Builder.begin("mythril", false)
        .createDefaultSet(5F, DIAMOND_MINING_LEVEL, DIAMOND_MINING_LEVEL)
        .strength(5.5F)
        .sounds(BlockSoundGroup.DEEPSLATE)
        .createOreVariant("deepslate", DIAMOND_MINING_LEVEL)
        .finish();

    public static final BlockSet ORICHALCUM = BlockSet.Builder.begin("orichalcum", false)
        .createDefaultSet(5.5F, DIAMOND_MINING_LEVEL, DIAMOND_MINING_LEVEL)
        .sounds(BlockSoundGroup.TUFF)
        .createOreVariant("tuff", DIAMOND_MINING_LEVEL)
        .sounds(BlockSoundGroup.BASALT)
        .createOreVariant("smooth_basalt", DIAMOND_MINING_LEVEL)
        .strength(6F).sounds(BlockSoundGroup.DEEPSLATE)
        .createOreVariant("deepslate", DIAMOND_MINING_LEVEL)
        .finish();

    public static final BlockSet OSMIUM = BlockSet.Builder.begin("osmium", false)
        .createDefaultSet(4.0F, IRON_MINING_LEVEL, IRON_MINING_LEVEL).finish();

    public static final BlockSet PALLADIUM = BlockSet.Builder.begin("palladium", true)
        .strength(5.0F).sounds(BlockSoundGroup.NETHER_ORE)
        .createOre(DIAMOND_MINING_LEVEL).strength(6.0F).sounds(BlockSoundGroup.METAL)
        .createOreStorageBlock(DIAMOND_MINING_LEVEL)
        .createStorageBlock(DIAMOND_MINING_LEVEL)
        .createAnvil(DIAMOND_MINING_LEVEL)
        .finish();

    public static final BlockSet PLATINUM = BlockSet.Builder.begin("platinum", false)
        .createDefaultSet(3.5F, IRON_MINING_LEVEL, IRON_MINING_LEVEL).finish();

    public static final BlockSet PROMETHEUM = BlockSet.Builder.begin("prometheum", false)
        .createDefaultSet(5.0F, IRON_MINING_LEVEL, DIAMOND_MINING_LEVEL)
        .sounds(BlockSoundGroup.DEEPSLATE)
        .createOreVariant("deepslate", DIAMOND_MINING_LEVEL)
        .finish();

    public static final BlockSet QUADRILLUM = BlockSet.Builder.begin("quadrillum", false)
        .createDefaultSet(3.5F, IRON_MINING_LEVEL, 5.0f, IRON_MINING_LEVEL)
        .createAnvil(IRON_MINING_LEVEL)
        .finish();

    public static final Block QUADRILLUM_NUKE_CORE = new Block(AbstractBlock.Settings.copy(QUADRILLUM.getStorageBlock()));

    public static final BlockSet RUNITE = BlockSet.Builder.begin("runite", false)
        .createDefaultSet(8.0F, IRON_MINING_LEVEL, IRON_MINING_LEVEL)
        .sounds(BlockSoundGroup.DEEPSLATE).createOreVariant("deepslate", IRON_MINING_LEVEL)
        .finish();

    public static final BlockSet SILVER = BlockSet.Builder.begin("silver", false)
        .createDefaultSet(2.5F, STONE_MINING_LEVEL, IRON_MINING_LEVEL).finish();

    public static final BlockSet STAR_PLATINUM = BlockSet.Builder.begin("star_platinum", false)
        .createAnvilSet(5F, 5.5F, DIAMOND_MINING_LEVEL).finish();

    public static final BlockSet STARRITE = BlockSet.Builder.begin("starrite", false)
        .strength(5.0F)
        .createStarriteOre(DIAMOND_MINING_LEVEL, UniformIntProvider.create(3, 6))
        .sounds(BlockSoundGroup.CALCITE).createStarriteOreVariant("calcite", DIAMOND_MINING_LEVEL, UniformIntProvider.create(3, 6))
        .sounds(BlockSoundGroup.STONE).createStarriteOreVariant("end_stone", NETHERITE_MINING_LEVEL, UniformIntProvider.create(3, 6))
        .createAmethystStorageBlock(NETHERITE_MINING_LEVEL)
        .finish();
    public static final Block SPONGE_NUKE_CORE = new Block(AbstractBlock.Settings.copy(Blocks.SPONGE));

    public static final BlockSet STEEL = BlockSet.Builder.begin("steel", false)
        .createAnvilSet(5, IRON_MINING_LEVEL).finish();

    public static final BlockSet STORMYX = BlockSet.Builder.begin("stormyx", false)
        .strength(5F).sounds(BlockSoundGroup.NETHER_ORE).createOre(IRON_MINING_LEVEL, UniformIntProvider.create(2, 4))
        .sounds(BlockSoundGroup.GILDED_BLACKSTONE).createOreVariant("blackstone", IRON_MINING_LEVEL)
        .strength(6.0F).createOreStorageBlock(IRON_MINING_LEVEL)
        .sounds(BlockSoundGroup.METAL).createStorageBlock(IRON_MINING_LEVEL)
        .createAnvil(IRON_MINING_LEVEL)
        .finish();

    public static final BlockSet TIN = BlockSet.Builder.begin("tin", false)
        .createDefaultSet(2.0F, STONE_MINING_LEVEL, 2.5F, IRON_MINING_LEVEL).finish();


    public static final BlockSet UNOBTAINIUM = BlockSet.Builder.begin("unobtainium", true)
        .uncommon()
        .strength(16.0F, 13000F).sounds(BlockSoundGroup.LODESTONE)
        .createLuminantOre(NETHERITE_MINING_LEVEL, UniformIntProvider.create(4, 7), 1)
        .strength(21F, 14000F).sounds(BlockSoundGroup.DEEPSLATE)
        .createOreVariant("deepslate", NETHERITE_MINING_LEVEL, UniformIntProvider.create(4, 7), 1)
        .sounds(BlockSoundGroup.LODESTONE).strength(25F, 15000F)
        .createStorageBlock(NETHERITE_MINING_LEVEL)
        .finish();


    public static void init() {
        BlockSet.Builder.register();
        RegistryHelper.block("aquarium_glass", AQUARIUM_GLASS);
        RegistryHelper.block("aquarium_resonator", AQUARIUM_RESONATOR);
        RegistryHelper.block("banglum_tnt", BANGLUM_TNT_BLOCK);
        RegistryHelper.block("banglum_nuke_core", BANGLUM_NUKE_CORE);
        RegistryHelper.block("carmot_nuke_core", CARMOT_NUKE_CORE);
        // Manually registering these in order to get the glint
        Registry.register(Registries.BLOCK, RegistryHelper.id("enchanted_midas_gold_block"), ENCHANTED_MIDAS_GOLD_BLOCK);
        Registry.register(Registries.ITEM, RegistryHelper.id("enchanted_midas_gold_block"), ENCHANTED_MIDAS_GOLD_BLOCK_ITEM);
        RegistryHelper.block("quadrillum_nuke_core", QUADRILLUM_NUKE_CORE);
        RegistryHelper.block("sponge_nuke_core", SPONGE_NUKE_CORE);
    }

}
