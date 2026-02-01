package com.mythicmetals.block;

import com.mythicmetals.MythicMetals;
import com.mythicmetals.misc.RegistryHelper;
import com.mythicmetals.registry.RegisterSounds;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import java.util.*;

import static com.mythicmetals.misc.RegistryHelper.blockKey;
import static com.mythicmetals.misc.RegistryHelper.itemKey;

@SuppressWarnings("unused")
public class MythicBlocks {

    public static final Map<String, BlockSet> BLOCKSET_MAP = new HashMap<>();
    private static final Identifier STONE_MINING_LEVEL = BlockTags.NEEDS_STONE_TOOL.identifier();
    private static final Identifier IRON_MINING_LEVEL = BlockTags.NEEDS_IRON_TOOL.identifier();
    private static final Identifier DIAMOND_MINING_LEVEL = BlockTags.NEEDS_DIAMOND_TOOL.identifier();
    private static final Identifier NETHERITE_MINING_LEVEL = Identifier.parse("needs_netherite_tool");
    private static final Identifier MYTHIC_MINING_LEVEL = RegistryHelper.id("needs_unobtainable_tool");

    public static final BlockSet ADAMANTITE = BlockSet.Builder.begin("adamantite", false)
        .createDefaultSet(4, DIAMOND_MINING_LEVEL, DIAMOND_MINING_LEVEL)
        .strength(6, 12).sounds(SoundType.DEEPSLATE)
        .createOreVariant("deepslate", DIAMOND_MINING_LEVEL)
        .finish();

    public static final BlockSet AQUARIUM = BlockSet.Builder.begin("aquarium", false)
        .createDefaultSet(4F, IRON_MINING_LEVEL, 4.5F, IRON_MINING_LEVEL)
        .createAnvil(IRON_MINING_LEVEL)
        .finish();
    public static final Block AQUARIUM_GLASS = new AquariumGlassBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.BLUE_STAINED_GLASS).setId(blockKey("aquarium_glass")));
    public static final AquariumResonatorBlock AQUARIUM_RESONATOR = new AquariumResonatorBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CONDUIT).setId(blockKey("aquarium_resonator")));

    public static final BlockSet BANGLUM = BlockSet.Builder.begin("banglum", false)
        .strength(5.0F, 5.5F)
        .createBanglumOre(IRON_MINING_LEVEL)
        .createOreStorageBlock(IRON_MINING_LEVEL)
        .createStorageBlock(IRON_MINING_LEVEL)
        .createAnvil(IRON_MINING_LEVEL)
        .sounds(SoundType.NETHER_ORE)
        .createBanglumOreVariant("nether", IRON_MINING_LEVEL)
        .finish();

    public static final BanglumTntBlock BANGLUM_TNT_BLOCK = new BanglumTntBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.TNT).setId(blockKey("banglum_tnt")));
    public static final Block BANGLUM_NUKE_CORE = new Block(BlockBehaviour.Properties.ofFullCopy(BANGLUM.getStorageBlock()).setId(blockKey("banglum_nuke_core")));

    public static final BlockSet BRONZE = BlockSet.Builder.begin("bronze", false)
        .createAnvilSet(5, IRON_MINING_LEVEL).finish();

    public static final BlockSet CARMOT = BlockSet.Builder.begin("carmot", false)
        .createDefaultSet(5.5F, IRON_MINING_LEVEL, IRON_MINING_LEVEL)
        .strength(6.5F, 12).sounds(SoundType.DEEPSLATE)
        .createOreVariant("deepslate", DIAMOND_MINING_LEVEL)
        .finish();
    public static final Block CARMOT_BELL_BLOCK = new CarmotBellBlock(BlockBehaviour.Properties.of()
        .setId(blockKey("carmot_bell"))
        .noOcclusion()
        .strength(0.5f, 4.0f));

    public static final Block CARMOT_NUKE_CORE = new Block(BlockBehaviour.Properties.ofFullCopy(BANGLUM_NUKE_CORE).setId(blockKey("carmot_nuke_core")));

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
        .createCustomStorageBlock(IRON_MINING_LEVEL, BlockBehaviour.Properties.of().noOcclusion())
        .createAnvil(IRON_MINING_LEVEL)
        .strength(3.0F, 3.0F)
        .sounds(SoundType.CALCITE)
        .createOreVariant("calcite", IRON_MINING_LEVEL)
        .finish();

    public static final BlockSet MANGANESE = BlockSet.Builder.begin("manganese", false)
        .createDefaultSet(3.0F, STONE_MINING_LEVEL, IRON_MINING_LEVEL).finish();

    public static final BlockSet METALLURGIUM = BlockSet.Builder.begin("metallurgium", true)
        .sounds(SoundType.NETHERITE_BLOCK)
        .createAnvilSet(60.0F, 15000F, MYTHIC_MINING_LEVEL)
        .finish();

    public static final BlockSet MORKITE = BlockSet.Builder.begin("morkite", false)
        .strength(3.0F)
        .createStorageBlock(IRON_MINING_LEVEL)
        .sounds(RegisterSounds.MORKITE_ORE)
        .createOre(IRON_MINING_LEVEL, UniformInt.of(1, 2))
        .sounds(RegisterSounds.DEEPSLATE_MORKITE_ORE)
        .createOreVariant("deepslate", IRON_MINING_LEVEL, UniformInt.of(1, 3))
        .finish();

    public static final BlockSet MIDAS_GOLD = BlockSet.Builder.begin("midas_gold", false)
        .strength(4F).sounds(SoundType.NETHER_GOLD_ORE)
        .createOre(STONE_MINING_LEVEL)
        .strength(5F)
        .createOreStorageBlock(IRON_MINING_LEVEL)
        .sounds(SoundType.METAL)
        .createStorageBlock(IRON_MINING_LEVEL)
        .createAnvil(IRON_MINING_LEVEL)
        .finish();

    public static final Block ENCHANTED_MIDAS_GOLD_BLOCK = new EnchantedMidasGoldBlock(BlockBehaviour.Properties.ofFullCopy(MIDAS_GOLD.getStorageBlock()).setId(blockKey("enchanted_midas_gold_block")));
    public static final Item ENCHANTED_MIDAS_GOLD_BLOCK_ITEM = new BlockItem(ENCHANTED_MIDAS_GOLD_BLOCK, new Item.Properties().group(MythicMetals.TABBED_GROUP).tab(1).rarity(Rarity.UNCOMMON).setId(itemKey("enchanted_midas_gold_block"))) {
        @Override
        public boolean isFoil(ItemStack stack) {
            return true;
        }
    };

    public static final BlockSet MYTHRIL = BlockSet.Builder.begin("mythril", false)
        .createDefaultSet(5F, DIAMOND_MINING_LEVEL, DIAMOND_MINING_LEVEL)
        .strength(5.5F)
        .sounds(SoundType.DEEPSLATE)
        .createOreVariant("deepslate", DIAMOND_MINING_LEVEL)
        .finish();

    public static final BlockSet ORICHALCUM = BlockSet.Builder.begin("orichalcum", false)
        .createDefaultSet(5.5F, DIAMOND_MINING_LEVEL, DIAMOND_MINING_LEVEL)
        .sounds(SoundType.TUFF)
        .createOreVariant("tuff", DIAMOND_MINING_LEVEL)
        .sounds(SoundType.BASALT)
        .createOreVariant("smooth_basalt", DIAMOND_MINING_LEVEL)
        .strength(6F).sounds(SoundType.DEEPSLATE)
        .createOreVariant("deepslate", DIAMOND_MINING_LEVEL)
        .finish();

    public static final BlockSet OSMIUM = BlockSet.Builder.begin("osmium", false)
        .createDefaultSet(4.0F, IRON_MINING_LEVEL, IRON_MINING_LEVEL).finish();

    public static final BlockSet PALLADIUM = BlockSet.Builder.begin("palladium", true)
        .strength(5.0F).sounds(SoundType.NETHER_ORE)
        .createOre(DIAMOND_MINING_LEVEL).strength(6.0F).sounds(SoundType.METAL)
        .createOreStorageBlock(DIAMOND_MINING_LEVEL)
        .createStorageBlock(DIAMOND_MINING_LEVEL)
        .createAnvil(DIAMOND_MINING_LEVEL)
        .finish();

    public static final Block PALLADIUM_RAIL = new PalladiumRailBlock(BlockBehaviour.Properties.of()
        .noCollission()
        .setId(blockKey("palladium_rail"))
        .lightLevel(blockState -> blockState.getValue(PalladiumRailBlock.LAVALOGGED) ? 15 : 0)
        .strength(2.5f, 7.0f)
        .sound(SoundType.METAL)
    );

    public static final Item PALLADIUM_RAIL_ITEM = new BlockItem(PALLADIUM_RAIL, new Item.Properties().group(MythicMetals.TABBED_GROUP).tab(1).fireResistant().setId(itemKey("palladium_rail"))) {
        @Override
        public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag type) {
            super.appendHoverText(stack, context, tooltip, type);
            tooltip.add(Component.translatable("tooltip.palladium_rail.info"));
        }
    };

    public static final BlockSet PLATINUM = BlockSet.Builder.begin("platinum", false)
        .createDefaultSet(3.5F, IRON_MINING_LEVEL, IRON_MINING_LEVEL).finish();

    public static final BlockSet PROMETHEUM = BlockSet.Builder.begin("prometheum", false)
        .createDefaultSet(5.0F, IRON_MINING_LEVEL, DIAMOND_MINING_LEVEL)
        .sounds(SoundType.DEEPSLATE)
        .createOreVariant("deepslate", DIAMOND_MINING_LEVEL)
        .finish();

    public static final BlockSet QUADRILLUM = BlockSet.Builder.begin("quadrillum", false)
        .createDefaultSet(3.5F, IRON_MINING_LEVEL, 5.0f, IRON_MINING_LEVEL)
        .createAnvil(IRON_MINING_LEVEL)
        .finish();

    public static final Block QUADRILLUM_NUKE_CORE = new Block(BlockBehaviour.Properties.ofFullCopy(QUADRILLUM.getStorageBlock()).setId(blockKey("quadrillum_nuke_core")));

    public static final BlockSet RUNITE = BlockSet.Builder.begin("runite", false)
        .createDefaultSet(8.0F, IRON_MINING_LEVEL, IRON_MINING_LEVEL)
        .sounds(SoundType.DEEPSLATE).createOreVariant("deepslate", IRON_MINING_LEVEL)
        .finish();

    public static final BlockSet SILVER = BlockSet.Builder.begin("silver", false)
        .createDefaultSet(2.5F, STONE_MINING_LEVEL, IRON_MINING_LEVEL).finish();

    public static final BlockSet STAR_PLATINUM = BlockSet.Builder.begin("star_platinum", false)
        .createAnvilSet(5F, 5.5F, DIAMOND_MINING_LEVEL).finish();

    public static final BlockSet STARRITE = BlockSet.Builder.begin("starrite", false)
        .strength(5.0F)
        .createStarriteOre(DIAMOND_MINING_LEVEL, UniformInt.of(3, 6))
        .sounds(SoundType.CALCITE).createStarriteOreVariant("calcite", DIAMOND_MINING_LEVEL, UniformInt.of(3, 6))
        .sounds(SoundType.STONE).createStarriteOreVariant("end_stone", NETHERITE_MINING_LEVEL, UniformInt.of(3, 6))
        .createAmethystStorageBlock(NETHERITE_MINING_LEVEL)
        .finish();
    public static final Block SPONGE_NUKE_CORE = new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.SPONGE).setId(blockKey("sponge_nuke_core")));

    public static final BlockSet STEEL = BlockSet.Builder.begin("steel", false)
        .createCustomStorageBlock(new BlockWithFacing(BlockSet.Builder.blockSettings(5.0f, 5.0f, SoundType.METAL).setId(blockKey("steel_block"))), IRON_MINING_LEVEL)
        .createAnvil(IRON_MINING_LEVEL).finish();

    public static final BlockSet STORMYX = BlockSet.Builder.begin("stormyx", false)
        .strength(5F).sounds(SoundType.NETHER_ORE).createOre(IRON_MINING_LEVEL, UniformInt.of(2, 4))
        .sounds(SoundType.GILDED_BLACKSTONE).createOreVariant("blackstone", IRON_MINING_LEVEL)
        .strength(6.0F).createOreStorageBlock(IRON_MINING_LEVEL)
        .sounds(SoundType.METAL).createStorageBlock(IRON_MINING_LEVEL)
        .createAnvil(IRON_MINING_LEVEL)
        .finish();

    public static final BlockSet TIN = BlockSet.Builder.begin("tin", false)
        .createDefaultSet(2.0F, STONE_MINING_LEVEL, 2.5F, IRON_MINING_LEVEL).finish();


    public static final BlockSet UNOBTAINIUM = BlockSet.Builder.begin("unobtainium", true)
        .uncommon()
        .strength(16.0F, 13000F).sounds(SoundType.LODESTONE)
        .createLuminantOre(NETHERITE_MINING_LEVEL, UniformInt.of(4, 7), 1)
        .strength(21F, 14000F).sounds(SoundType.DEEPSLATE)
        .createOreVariant("deepslate", NETHERITE_MINING_LEVEL, UniformInt.of(4, 7), 1)
        .sounds(SoundType.LODESTONE).strength(25F, 15000F)
        .createStorageBlock(NETHERITE_MINING_LEVEL)
        .finish();


    public static void init() {
        BlockSet.Builder.register();
        RegistryHelper.block("aquarium_glass", AQUARIUM_GLASS);
        RegistryHelper.block("aquarium_resonator", AQUARIUM_RESONATOR);
        RegistryHelper.block("banglum_tnt", BANGLUM_TNT_BLOCK);
        RegistryHelper.block("banglum_nuke_core", BANGLUM_NUKE_CORE);
        RegistryHelper.blockOnly("carmot_bell", CARMOT_BELL_BLOCK);
        RegistryHelper.block("carmot_nuke_core", CARMOT_NUKE_CORE);
        // Manually registering these in order to get the glint
        RegistryHelper.blockOnly("enchanted_midas_gold_block", ENCHANTED_MIDAS_GOLD_BLOCK);
        RegistryHelper.item("enchanted_midas_gold_block", ENCHANTED_MIDAS_GOLD_BLOCK_ITEM);
        // Manually registering to get a tooltip and fireproofing
        RegistryHelper.blockOnly("palladium_rail", PALLADIUM_RAIL);
        RegistryHelper.item("palladium_rail", PALLADIUM_RAIL_ITEM);
        RegistryHelper.block("quadrillum_nuke_core", QUADRILLUM_NUKE_CORE);
        RegistryHelper.block("sponge_nuke_core", SPONGE_NUKE_CORE);
    }

}
