package com.mythicmetals.item;

import com.mythicmetals.MythicMetals;
import com.mythicmetals.api.v2.Material;
import com.mythicmetals.api.v2.MaterialType;
import com.mythicmetals.block.*;
import com.mythicmetals.data.MythicTags;
import com.mythicmetals.entity.MythicEntities;
import com.mythicmetals.item.tools.CarmotBellItem;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.MinecartItem;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;

import java.util.function.Predicate;

import static com.mythicmetals.api.v2.Material.*;
import static com.mythicmetals.item.MythicResourceKeys.*;

public class MythicMaterials {
    private MythicMaterials() {
    }

    public static final Material ADAMANTITE = Material.Builder.create("adamantite", MaterialType.INGOT)
        .createBlockSetFromBuilder(DIAMOND_MINING_LEVEL, blockSetBuilder -> blockSetBuilder
            .createDefaultBlocks(5.0f)
            .createOreVariant("deepslate", 6.0f, 7.0f)
            .finish()
        )
//        .createDefaultTools(MythicToolMaterials.ADAMANTITE, ToolSet.AttackSpeeds.BETTER_AXE)
//        .createDefaultArmor(MythicArmorMaterials.ADAMANTITE)
        .finish();

    public static final Material AQUARIUM = Material.Builder.create("aquarium", MaterialType.INGOT)
        .createDefaultBlockSet(IRON_MINING_LEVEL, 4.0f)
//        .createDefaultTools(MythicToolMaterials.AQUARIUM, ToolSet.AttackSpeeds.DEFAULT)
//        .createDefaultArmor(MythicArmorMaterials.AQUARIUM)
        .addExtraItem(AQUARIUM_PEARL, Rarity.UNCOMMON, Item::new)
        .addExtraBlock(
            AQUARIUM_GLASS,
            properties ->
                new AquariumGlassBlock(
                    BlockBehaviour.Properties.ofFullCopy(Blocks.BLUE_STAINED_GLASS)
                        .setId(AQUARIUM_GLASS)
                )
        )
        .addExtraBlock(AQUARIUM_RESONATOR, 4.0f, AquariumResonatorBlock::new)
        .finish();

    public static final Material BANGLUM = Material.Builder.create("banglum", MaterialType.INGOT)
        .createBlockSetFromBuilder(IRON_MINING_LEVEL, builder -> builder
            .createStorageBlock(5.0f, 5.5f)
            .createCustomOre(5.0f, BanglumOreBlock::new)
            .createCustomOreVariant("nether", 5.0f, 5.5f, BanglumOreBlock::new)
            .createAnvil(5.5f, 15000f)
            .finish()
        )
        .addExtraBlock(BANGLUM_TNT, properties -> new BanglumTntBlock(properties.instabreak().ignitedByLava()))
        .addExtraBlock(BANGLUM_NUKE_CORE, 5.0f, BanglumNukeCore::new)
        .addExtraBlock(SPONGE_NUKE_CORE, 5.0f, properties -> new BanglumNukeCore(properties) {
            @Override
            public float damageModifier() {
                return 0.5f;
            }
            @Override
            public Predicate<BlockState> getPredicate() {
                return state -> !state.getFluidState().isEmpty();
            }
        })
        .addExtraItem(
            BANGLUM_TNT_MINECART,
            new MinecartItem(
                MythicEntities.BANGLUM_TNT_MINECART_ENTITY_TYPE,
                new Item.Properties()
                    .group(MythicMetals.TABBED_GROUP)
                    .setId(BANGLUM_TNT_MINECART)
            )
        )
        .addExtraItem(BANGLUM_CHUNK, Rarity.UNCOMMON, Item::new)
        .finish();

    public static final Material BRONZE = Material.Builder.create("bronze", MaterialType.ALLOY)
        .createDefaultBlockSet(IRON_MINING_LEVEL, 5.5f)
        .finish();

    public static final Material CARMOT = Material.Builder.create("carmot", MaterialType.INGOT)
        .createBlockSetFromBuilder(IRON_MINING_LEVEL, builder -> builder
            .createDefaultBlocks(5.5f)
            .createOreVariant("deepslate", 6.5f, 7.5f)
            .finish()
        )
        .addExtraBlockAndItem("carmot_bell", properties -> new CarmotBellBlock(
                properties
                    .noOcclusion()
                    .strength(0.5f, 4.0f)
            ), CarmotBellItem::new
        )
        .addExtraBlock(CARMOT_NUKE_CORE, properties -> new BanglumNukeCore(properties) {
            @Override
            public Predicate<BlockState> getPredicate() {
                return state -> !state.is(MythicTags.CARMOT_NUKE_IGNORED);
            }
        })
        .addExtraItem(CARMOT_STONE, Rarity.UNCOMMON, Item::new)
        .finish();

    public static final Material CELESTIUM = Material.Builder.create("celestium", MaterialType.RARE_ALLOY)
        .createDefaultBlockSet(NETHERITE_MINING_LEVEL, 13.0f)
        .finish();

    public static final Material DURASTEEL = Material.Builder.create("durasteel", MaterialType.ALLOY)
        .createDefaultBlockSet(DIAMOND_MINING_LEVEL, 5.0f)
        .addExtraItem(DURASTEEL_ENGINE, Rarity.UNCOMMON, Item::new)
        .finish();

    public static final Material HALLOWED = Material.Builder.create("hallowed", MaterialType.ALLOY)
        .createDefaultBlockSet(NETHERITE_MINING_LEVEL, 5.0f)
        .finish();

    public static final Material KYBER = Material.Builder.create("kyber", MaterialType.INGOT)
        .createBlockSetFromBuilder(IRON_MINING_LEVEL, builder ->
            builder
                .createOre(3.0f, UniformInt.of(0, 0))
                .createOreStorageBlock(3.0f, 4.0f)
                .createCustomStorageBlock(properties -> new Block(properties.noOcclusion()))
                .createAnvil(4.0f, 15000f)
                .createOreVariant("calcite", 3.0f, 3.0f, UniformInt.of(0, 0))
                .finish()
        )
        .finish();

    public static final Material MANGANESE = Material.Builder.create("manganese", MaterialType.INGOT)
        .createDefaultBlockSet(STONE_MINING_LEVEL, 3.0f)
        .finish();

    public static final Material METALLURGIUM = Material.Builder.create("metallurgium", MaterialType.RARE_ALLOY)
        .createDefaultBlockSet(NETHERITE_MINING_LEVEL, 60.0f)
        .finish();

    public static final Material MIDAS_GOLD = Material.Builder.create("midas_gold", MaterialType.INGOT)
        .createDefaultBlockSet(IRON_MINING_LEVEL, 4.0f)
        .addExtraBlock(ENCHANTED_MIDAS_GOLD_BLOCK, Rarity.UNCOMMON, EnchantedMidasGoldBlock::new)
        .finish();

    public static final Material MORKITE = Material.Builder.create("morkite", MaterialType.BASIC)
        .createBlockSetFromBuilder(IRON_MINING_LEVEL, builder -> builder
            .createOre(3.0f, UniformInt.of(1, 3))
            .createStorageBlock(3.0f, 4.0f)
            .createOreVariant("deepslate", 3.5f, 4.5f, UniformInt.of(1, 4))
            .finish())
        .finish();

    public static final Material MYTHRIL = Material.Builder.create("mythril", MaterialType.INGOT)
        .createBlockSetFromBuilder(DIAMOND_MINING_LEVEL, blockSetBuilder -> blockSetBuilder
            .createDefaultBlocks(5.0f)
            .createOreVariant("deepslate", 5.5f, 6.5f)
            .finish()
        )
        .finish();

    public static final Material ORICHALCUM = Material.Builder.create("orichalcum", MaterialType.INGOT)
        .createBlockSetFromBuilder(DIAMOND_MINING_LEVEL, builder -> builder
            .createDefaultBlocks(5.5f)
            .createOreVariant("tuff", 4.5f, 5.0f)
            .createOreVariant("smooth_basalt", 4.5f, 5.0f)
            .createOreVariant("deepslate", 6.0f, 7.0f)
            .finish()
        )
        .finish();

    public static final Material OSMIUM = Material.Builder.create("osmium", MaterialType.INGOT)
        .createDefaultBlockSet(IRON_MINING_LEVEL, 4.0f)
        .finish();

    public static final Material PALLADIUM = Material.Builder.create("palladium", MaterialType.INGOT)
        .createDefaultBlockSet(DIAMOND_MINING_LEVEL, 5.0f)
        .addExtraBlockAndItem("palladium_rail", properties -> new PalladiumRailBlock(
            properties
                .noCollision()
                .lightLevel(blockState -> blockState.hasProperty(PalladiumRailBlock.LAVALOGGED) ? 15 : 0)
                .strength(2.5f, 7.0f)
                .sound(SoundType.METAL)
        ), (block, properties) -> new MinecartItem(
            MythicEntities.PALLADIUM_MINECART_ENTITY_TYPE, properties
        ) {
            // FIXME - Tooltip
        })
        .finish();

    public static final Material PLATINUM = Material.Builder.create("platinum", MaterialType.INGOT)
        .createDefaultBlockSet(IRON_MINING_LEVEL, 3.5f)
        .finish();

    public static final Material PROMETHEUM = Material.Builder.create("prometheum", MaterialType.INGOT)
        .createBlockSetFromBuilder(IRON_MINING_LEVEL, builder -> builder
            .createDefaultBlocks(5.0f)
            .createOreVariant("deepslate", 5.0f, 6.5f)
            .finish()
        )
        .addExtraItem(PROMETHEUM_ROSE, Rarity.UNCOMMON, Item::new)
        .finish();

    public static final Material QUADRILLUM = Material.Builder.create("quadrillum", MaterialType.INGOT)
        .createDefaultBlockSet(IRON_MINING_LEVEL, 4.0f)
        .finish();

    public static final Material RUNITE = Material.Builder.create("runite", MaterialType.INGOT)
        .createBlockSetFromBuilder(IRON_MINING_LEVEL, blockSetBuilder -> blockSetBuilder
            .createDefaultBlocks(8.0f)
            .createOreVariant("deepslate", 8.8f, 9f)
            .finish()
        )
        .finish();

    public static final Material SILVER = Material.Builder.create("silver", MaterialType.INGOT)
        .createDefaultBlockSet(STONE_MINING_LEVEL, 2.5f)
        .finish();

    public static final Material STAR_PLATINUM = Material.Builder.create("star_platinum", MaterialType.ALLOY)
        .createDefaultBlockSet(DIAMOND_MINING_LEVEL, 5.5f)
        .finish();

    public static final Material STARRITE = Material.Builder.create("starrite", MaterialType.BASIC)
        .createBlockSetFromBuilder(DIAMOND_MINING_LEVEL, builder -> builder
            .createCustomOre(5.0f, properties -> new StarriteOreBlock(properties, UniformInt.of(3, 6)))
            .createCustomOreVariant("calcite", 5.0f, 5.5f, properties ->
                new StarriteOreBlock(
                    properties.sound(SoundType.CALCITE),
                    UniformInt.of(3, 6)
                )
            )
            .createCustomOreVariant("end_stone", 5.0f, 5.5f, properties ->
                new StarriteOreBlock(properties, UniformInt.of(3, 6))
            )
            .createStorageBlock(5.0f, 6.0f)
            .finish()
        )
        .finish();

    public static final Material STEEL = Material.Builder.create("steel", MaterialType.ALLOY)
        .createDefaultBlockSet(IRON_MINING_LEVEL, 5.0f)
        .finish();

    public static final Material STORMYX = Material.Builder.create("stormyx", MaterialType.INGOT)
        .createBlockSetFromBuilder(IRON_MINING_LEVEL, builder -> builder
            .createDefaultBlocks(5.5f)
            .createOreVariant("blackstone", 5.5f, 6.0f)
            .finish()
        )
        .addExtraItem(STORMYX_SHELL, Rarity.UNCOMMON, Item::new)
        .finish();

    public static final Material TIN = Material.Builder.create("tin", MaterialType.INGOT)
        .createBlockSetFromBuilder(STONE_MINING_LEVEL, builder -> builder
            .createOre(2.0f, UniformInt.of(0, 0))
            .createStorageBlock(2.0f, 3.0f)
            .createOreStorageBlock(2.0f, 2.5f)
            .finish()
        )
        .finish();

    public static final Material UNOBTAINIUM = Material.Builder.create("unobtainium", MaterialType.SPECIAL)
        .createBlockSetFromBuilder(NETHERITE_MINING_LEVEL, builder -> builder
            .createCustomOre(16.0f, 14000f, properties ->
                new DropExperienceBlock(
                    UniformInt.of(4, 7),
                    properties.lightLevel(blockState -> 1).sound(SoundType.LODESTONE)
                )
            )
            .createCustomOreVariant("deepslate", 21f, 14000f, properties ->
                new DropExperienceBlock(
                    UniformInt.of(4, 7),
                    properties.lightLevel(blockState -> 1).sound(SoundType.DEEPSLATE)
                )
            )
            .createStorageBlock(25f, 15000f)
            .finish()
        )
        .finish();

    public static void init() {
        // no-op
    }
}
