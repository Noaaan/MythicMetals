package com.mythicmetals.item;

import com.mythicmetals.MythicAttributeModifier;
import com.mythicmetals.MythicMetals;
import com.mythicmetals.api.v2.*;
import com.mythicmetals.api.v2.ToolSet;
import com.mythicmetals.item.armor.*;
import com.mythicmetals.block.*;
import com.mythicmetals.client.models.MythicModelHandler;
import com.mythicmetals.component.*;
import com.mythicmetals.data.MythicTags;
import com.mythicmetals.entity.MythicEntities;
import com.mythicmetals.entity.MythicEntityAttributes;
import com.mythicmetals.item.tools.*;
import com.mythicmetals.misc.RegistryHelper;
import com.mythicmetals.registry.RegisterSounds;
import de.dafuqs.additionalentityattributes.AdditionalEntityAttributes;
import net.minecraft.core.component.DataComponents;
import net.minecraft.util.Unit;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.*;
import net.minecraft.world.item.equipment.Equippable;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import java.util.List;
import java.util.function.Predicate;

import static com.mythicmetals.api.v2.Material.*;
import static com.mythicmetals.item.MythicResourceKeys.*;
import static net.minecraft.world.entity.EquipmentSlotGroup.*;
import static net.minecraft.world.entity.EquipmentSlotGroup.ARMOR;
import static net.minecraft.world.entity.ai.attributes.AttributeModifier.Operation.*;
import static net.minecraft.world.entity.ai.attributes.Attributes.*;

public class MythicMaterials {

    public static final Material ADAMANTITE = Material.Builder.create("adamantite", MaterialType.INGOT)
        .createBlockSetFromBuilder(DIAMOND_MINING_LEVEL, blockSetBuilder -> blockSetBuilder
            .createDefaultBlocks(5.0f)
            .createOreVariant("deepslate", 6.0f, 7.0f)
            .finish()
        )
        .createCustomHelmetArmorSet(
            MythicArmorMaterials.ADAMANTITE,
            MythicModelHandler.ADAMANTITE_ARMOR,
            RegistryHelper.id("textures/models/adamantite_model.png")
        )
        .createDefaultTools(MythicToolMaterials.ADAMANTITE, ToolSet.AttackSpeeds.BETTER_AXE, MythicSpearStats.ADAMANTITE)
        .finish();

    public static final Material AQUARIUM = Material.Builder.create("aquarium", MaterialType.INGOT)
        .createDefaultBlockSet(IRON_MINING_LEVEL, 4.0f)
        .createToolSet(MythicToolMaterials.AQUARIUM, ToolSet.AttackSpeeds.DEFAULT, MythicSpearStats.AQUARIUM, List.of(
            new MythicAttributeModifier(SUBMERGED_MINING_SPEED, 1.0, ADD_MULTIPLIED_TOTAL, MAINHAND)
        ))
        .addExtraBlock(
            AQUARIUM_GLASS,
            properties ->
                new AquariumGlassBlock(
                    BlockBehaviour.Properties.ofFullCopy(Blocks.BLUE_STAINED_GLASS)
                        .setId(AQUARIUM_GLASS)
                )
        )
        .createDefaultArmor(MythicArmorMaterials.AQUARIUM, List.of(
            new MythicAttributeModifier(SUBMERGED_MINING_SPEED, 1.0, ADD_MULTIPLIED_TOTAL, HEAD),
            new MythicAttributeModifier(OXYGEN_BONUS, 1.0, ADD_VALUE, CHEST),
            new MythicAttributeModifier(OXYGEN_BONUS, 1.0, ADD_VALUE, LEGS),
            new MythicAttributeModifier(OXYGEN_BONUS, 1.0, ADD_VALUE, BODY),
            new MythicAttributeModifier(WATER_MOVEMENT_EFFICIENCY, 0.5, ADD_VALUE, FEET),
            new MythicAttributeModifier(WATER_MOVEMENT_EFFICIENCY, 0.5, ADD_VALUE, BODY)
        ))
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
        .createDefaultTools(MythicToolMaterials.BANGLUM, ToolSet.AttackSpeeds.DEFAULT, MythicSpearStats.BANGLUM)
        .createDefaultArmor(MythicArmorMaterials.BANGLUM, List.of())
        .finish();

    public static final Material BRONZE = Material.Builder.create("bronze", MaterialType.ALLOY)
        .createDefaultBlockSet(IRON_MINING_LEVEL, 5.5f)
        .createDefaultTools(MythicToolMaterials.BRONZE, ToolSet.AttackSpeeds.DEFAULT, MythicSpearStats.BRONZE)
        .createDefaultArmor(MythicArmorMaterials.BRONZE)
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
        .addSmithingTemplate(CARMOT_SMITHING_TEMPLATE, MythicSmithingTemplates.CARMOT)
        .createDefaultTools(MythicToolMaterials.CARMOT, ToolSet.AttackSpeeds.DEFAULT, MythicSpearStats.CARMOT)
        .createDefaultArmor(MythicArmorMaterials.CARMOT, List.of(
            new MythicAttributeModifier(MAX_HEALTH, 2.0, ADD_VALUE, HEAD),
            new MythicAttributeModifier(MAX_HEALTH, 2.0, ADD_VALUE, CHEST),
            new MythicAttributeModifier(MAX_HEALTH, 2.0, ADD_VALUE, LEGS),
            new MythicAttributeModifier(MAX_HEALTH, 2.0, ADD_VALUE, FEET),
            new MythicAttributeModifier(MAX_HEALTH, 5.0, ADD_VALUE, BODY),
            new MythicAttributeModifier(MythicEntityAttributes.CARMOT_SHIELD, 5.0, ADD_VALUE, HEAD),
            new MythicAttributeModifier(MythicEntityAttributes.CARMOT_SHIELD, 5.0, ADD_VALUE, CHEST),
            new MythicAttributeModifier(MythicEntityAttributes.CARMOT_SHIELD, 5.0, ADD_VALUE, LEGS),
            new MythicAttributeModifier(MythicEntityAttributes.CARMOT_SHIELD, 5.0, ADD_VALUE, FEET),
            new MythicAttributeModifier(MythicEntityAttributes.CARMOT_SHIELD, 10.0, ADD_VALUE, BODY)
        ))
        .finish();

    public static final Material CELESTIUM = Material.Builder.create("celestium", MaterialType.RARE_ALLOY, true)
        .createDefaultBlockSet(NETHERITE_MINING_LEVEL, 13.0f)
        .createDefaultTools(MythicToolMaterials.CELESTIUM, ToolSet.AttackSpeeds.HIGHEST, MythicSpearStats.CELESTIUM)
        .createDefaultArmor(MythicArmorMaterials.CELESTIUM, List.of(
            new MythicAttributeModifier(MOVEMENT_SPEED, 0.1, ADD_MULTIPLIED_TOTAL, ARMOR),
            new MythicAttributeModifier(ATTACK_DAMAGE, 1.0, ADD_VALUE, ARMOR)
        ))
        .addExtraItem(MythicResourceKeys.CELESTIUM_ELYTRA, new CelestiumElytra(
            new Item.Properties()
                .durability(832)
                .setId(RegistryHelper.itemKey("celestium_elytra"))
                .rarity(Rarity.EPIC)
                .component(DataComponents.GLIDER, Unit.INSTANCE)
                .component(DataComponents.EQUIPPABLE, Equippable.builder(EquipmentSlot.CHEST)
                    .setAsset(RegistryHelper.equipmentAsset("celestium_elytra"))
                    .setEquipSound(RegistryHelper.getEntry(RegisterSounds.EQUIP_CELESTIUM_ELYTRA))
                    .build())
                .group(MythicMetals.TABBED_GROUP).tab(3)
                .attributes(CelestiumElytra.createDefaultAttributes())
        ))
        .finish();

    public static final Material DURASTEEL = Material.Builder.create("durasteel", MaterialType.ALLOY)
        .createDefaultBlockSet(DIAMOND_MINING_LEVEL, 5.0f)
        .addExtraItem(DURASTEEL_ENGINE, Rarity.UNCOMMON, Item::new)
        .createDefaultTools(MythicToolMaterials.DURASTEEL, ToolSet.AttackSpeeds.DEFAULT, MythicSpearStats.DURASTEEL)
        .createDefaultArmor(MythicArmorMaterials.DURASTEEL)
        .finish();

    public static final Material HALLOWED = Material.Builder.create("hallowed", MaterialType.ALLOY)
        .createDefaultBlockSet(NETHERITE_MINING_LEVEL, 5.0f)
        .createDefaultTools(MythicToolMaterials.HALLOWED, ToolSet.AttackSpeeds.DEFAULT, MythicSpearStats.HALLOWED)
        .createCustomHelmetArmorSet(
            MythicArmorMaterials.HALLOWED,
            MythicModelHandler.HALLOWED_ARMOR,
            RegistryHelper.id("textures/models/hallowed_model.png")
        )
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
        .createDefaultTools(MythicToolMaterials.KYBER, ToolSet.AttackSpeeds.DEFAULT, MythicSpearStats.KYBER)
        .createDefaultArmor(MythicArmorMaterials.KYBER)
        .finish();

    public static final Material LEGENDARY_BANGLUM = Material.Builder.createRawBuilder("legendary_banglum", MaterialType.SPECIAL)
        .createBaseMaterial(BANGLUM_CHUNK, Rarity.UNCOMMON, Item::new)
        .createCustomToolset(
            new LegendaryBanglumToolSet(MythicToolMaterials.LEGENDARY_BANGLUM),
            toolSet -> toolSet.createLegendaryBanglumTools(
                MythicToolMaterials.LEGENDARY_BANGLUM,
                ToolSet.AttackSpeeds.BETTER_AXE,
                MythicSpearStats.LEGENDARY_BANGLUM
            )
        )
        .addSmithingTemplate(LEGENDARY_BANGLUM_SMITHING_TEMPLATE, MythicSmithingTemplates.LEGENDARY_BANGLUM)
        .createCustomHelmetArmorSet(
            MythicArmorMaterials.LEGENDARY_BANGLUM,
            List.of(
                new MythicAttributeModifier(SAFE_FALL_DISTANCE, 15, ADD_VALUE, FEET)
            ),
            MythicModelHandler.LEGENDARY_BANGLUM_ARMOR,
            RegistryHelper.id("textures/models/banglum_model.png"),
            true
        )
        .finish();

    public static final Material MANGANESE = Material.Builder.create("manganese", MaterialType.INGOT)
        .createDefaultBlockSet(STONE_MINING_LEVEL, 3.0f)
        .finish();

    public static final Material METALLURGIUM = Material.Builder.create("metallurgium", MaterialType.RARE_ALLOY, true)
        .createDefaultBlockSet(NETHERITE_MINING_LEVEL, 60.0f)
        .createDefaultTools(MythicToolMaterials.METALLURGIUM, ToolSet.AttackSpeeds.BETTER_AXE, MythicSpearStats.METALLURGIUM)
        .createCustomHelmetArmorSet(
            MythicArmorMaterials.METALLURGIUM,
            MythicModelHandler.METALLURGIUM,
            RegistryHelper.id("textures/models/metallurgium_model.png")
        )
        .finish();

    public static final Material MIDAS_GOLD = Material.Builder.create("midas_gold", MaterialType.INGOT)
        .createDefaultBlockSet(IRON_MINING_LEVEL, 4.0f)
        .addExtraBlock(ENCHANTED_MIDAS_GOLD_BLOCK, Rarity.UNCOMMON, EnchantedMidasGoldBlock::new)
        .addSmithingTemplate(MIDAS_FOLDING_TEMPLATE, MythicSmithingTemplates.MIDAS_FOLDING)
        .addSmithingTemplate(ROYAL_MIDAS_SMITHING_TEMPLATE, MythicSmithingTemplates.ROYAL_MIDAS)
        // TODO - Midas Gold Swords
        .createDefaultArmor(
            MythicArmorMaterials.MIDAS_GOLD,
            List.of(
                new MythicAttributeModifier(LUCK, 1.0, ADD_VALUE, ARMOR)
            )
        )
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
        .addSmithingTemplate(MYTHRIL_DRILL_SMITHING_TEMPLATE, MythicSmithingTemplates.MYTHRIL_DRILL)
        .createDefaultTools(MythicToolMaterials.MYTHRIL, ToolSet.AttackSpeeds.FASTER, MythicSpearStats.MYTHRIL)
        .createDefaultArmor(MythicArmorMaterials.MYTHRIL)
        .finish();

    public static final Material ORICHALCUM = Material.Builder.create("orichalcum", MaterialType.INGOT)
        .createBlockSetFromBuilder(DIAMOND_MINING_LEVEL, builder -> builder
            .createDefaultBlocks(5.5f)
            .createOreVariant("tuff", 4.5f, 5.0f)
            .createOreVariant("smooth_basalt", 4.5f, 5.0f)
            .createOreVariant("deepslate", 6.0f, 7.0f)
            .finish()
        )
        .createDefaultTools(MythicToolMaterials.ORICHALCUM, ToolSet.AttackSpeeds.DEFAULT, MythicSpearStats.ORICHALCUM)
        .createDefaultArmor(MythicArmorMaterials.ORICHALCUM)
        .finish();

    public static final Material OSMIUM = Material.Builder.create("osmium", MaterialType.INGOT)
        .createDefaultBlockSet(IRON_MINING_LEVEL, 4.0f)
        .addSmithingTemplate(OSMIUM_CHAINMAIL_SMITHING_TEMPLATE, MythicSmithingTemplates.OSMIUM_CHAINMAIL)
        .createDefaultTools(MythicToolMaterials.OSMIUM, ToolSet.AttackSpeeds.DEFAULT, MythicSpearStats.OSMIUM)
        .createDefaultArmor(MythicArmorMaterials.OSMIUM)
        .finish();

    // FIXME - This kind of material creates an anti-pattern of this API
    // This should just stay an armor set, and should be registered somewhere else
    public static final Material OSMIUM_CHAINMAIL = Material.Builder.createRawBuilder("osmium_chainmail", MaterialType.ARMOR)
        .createCustomArmorSet(new ArmorSet("osmium_chainmail", MythicArmorMaterials.OSMIUM_CHAINMAIL), armorSet -> armorSet.initialize(false, List.of()))
        .finish();

    public static final Material PALLADIUM = Material.Builder.create("palladium", MaterialType.INGOT, true)
        .createDefaultBlockSet(DIAMOND_MINING_LEVEL, 5.0f)
        .addExtraBlock(PALLADIUM_RAIL, properties -> new PalladiumRailBlock(
            properties
                .noCollision()
                .lightLevel(blockState -> blockState.getValue(Lavaloggable.LAVALOGGED) ? 15 : 0)
                .strength(2.5f, 7.0f)
                .sound(SoundType.METAL)
        ))
        .addExtraItem(PALLADIUM_MINECART, Rarity.UNCOMMON, properties -> new MinecartItem(MythicEntities.PALLADIUM_MINECART_ENTITY_TYPE, properties))
        .createToolSet(
            properties -> properties.component(MythicDataComponents.BRANDING, new BrandingComponent(6)),
            MythicToolMaterials.PALLADIUM,
            ToolSet.AttackSpeeds.BETTER_AXE,
            MythicSpearStats.PALLADIUM
        ).createCustomHelmetArmorSet(
            MythicArmorMaterials.PALLADIUM,
            List.of(
                new MythicAttributeModifier(AdditionalEntityAttributes.LAVA_VISIBILITY, 2.0, ADD_VALUE, HEAD),
                new MythicAttributeModifier(AdditionalEntityAttributes.LAVA_SPEED, 2.0, ADD_VALUE, CHEST),
                new MythicAttributeModifier(AdditionalEntityAttributes.LAVA_SPEED, 2.0, ADD_VALUE, LEGS),
                new MythicAttributeModifier(AdditionalEntityAttributes.LAVA_SPEED, 2.0, ADD_VALUE, FEET),
                new MythicAttributeModifier(BURNING_TIME, -0.25, ADD_MULTIPLIED_BASE, HEAD),
                new MythicAttributeModifier(BURNING_TIME, -0.25, ADD_MULTIPLIED_BASE, CHEST),
                new MythicAttributeModifier(BURNING_TIME, -0.25, ADD_MULTIPLIED_BASE, LEGS),
                new MythicAttributeModifier(BURNING_TIME, -0.25, ADD_MULTIPLIED_BASE, FEET),
                new MythicAttributeModifier(BURNING_TIME, -0.80, ADD_MULTIPLIED_BASE, BODY)
            ),
            MythicModelHandler.PALLADIUM_ARMOR,
            RegistryHelper.id("textures/models/palladium_model.png"),
            true
        )
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
        .createToolSet(
            properties -> properties.component(MythicDataComponents.PROMETHEUM, PrometheumComponent.DEFAULT),
            MythicToolMaterials.PROMETHEUM,
            ToolSet.AttackSpeeds.BETTER_AXE,
            MythicSpearStats.PROMETHEUM
        )
        .createCustomArmorSet(
            new ArmorSet("prometheum", MythicArmorMaterials.PROMETHEUM),
            armorSet -> armorSet.initialize(
                properties -> properties.component(MythicDataComponents.PROMETHEUM, PrometheumComponent.DEFAULT)
            )
        )
        .finish();

    public static final Material QUADRILLUM = Material.Builder.create("quadrillum", MaterialType.INGOT)
        .createDefaultBlockSet(IRON_MINING_LEVEL, 4.0f)
        .createDefaultTools(MythicToolMaterials.QUADRILLUM, ToolSet.AttackSpeeds.BETTER_AXE, MythicSpearStats.QUADRILLUM)
        .addExtraBlock(QUADRILLUM_NUKE_CORE, properties -> new BanglumNukeCore(properties) {
            @Override
            public float damageModifier() {
                return 2.0f;
            }

            @Override
            public float radiusModifier() {
                return 2f / 3f;
            }
        })
        .finish();

    public static final Material RUNITE = Material.Builder.create("runite", MaterialType.INGOT)
        .createBlockSetFromBuilder(IRON_MINING_LEVEL, blockSetBuilder -> blockSetBuilder
            .createDefaultBlocks(8.0f)
            .createOreVariant("deepslate", 8.8f, 9f)
            .finish()
        )
        .createDefaultTools(MythicToolMaterials.RUNITE, ToolSet.AttackSpeeds.BETTER_AXE, MythicSpearStats.RUNITE)
        .createCustomHelmetArmorSet(
            MythicArmorMaterials.RUNITE,
            MythicModelHandler.RUNITE_ARMOR,
            RegistryHelper.id("textures/models/runite_model.png"),
            false
        )
        .finish();

    public static final Material SILVER = Material.Builder.create("silver", MaterialType.INGOT)
        .createDefaultBlockSet(STONE_MINING_LEVEL, 2.5f)
        .createDefaultArmor(MythicArmorMaterials.SILVER)
        .finish();

    public static final Material STAR_PLATINUM = Material.Builder.create("star_platinum", MaterialType.ALLOY)
        .createDefaultBlockSet(DIAMOND_MINING_LEVEL, 5.5f)
        .createDefaultTools(MythicToolMaterials.STAR_PLATINUM, ToolSet.AttackSpeeds.BETTER_AXE, MythicSpearStats.STAR_PLATINUM)
        .createDefaultArmor(MythicArmorMaterials.STAR_PLATINUM, List.of(
            new MythicAttributeModifier(ATTACK_DAMAGE, 1.0, ADD_VALUE, HEAD),
            new MythicAttributeModifier(ATTACK_DAMAGE, 1.0, ADD_VALUE, CHEST),
            new MythicAttributeModifier(ATTACK_DAMAGE, 1.0, ADD_VALUE, LEGS),
            new MythicAttributeModifier(ATTACK_DAMAGE, 1.0, ADD_VALUE, FEET),
            new MythicAttributeModifier(JUMP_STRENGTH, 0.30, ADD_MULTIPLIED_TOTAL, BODY)
        ))
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
        .createDefaultArmor(MythicArmorMaterials.STEEL)
        .createDefaultTools(MythicToolMaterials.STEEL, ToolSet.AttackSpeeds.BETTER_AXE, MythicSpearStats.STEEL)
        .finish();

    public static final Material STORMYX = Material.Builder.create("stormyx", MaterialType.INGOT)
        .createBlockSetFromBuilder(IRON_MINING_LEVEL, builder -> builder
            .createDefaultBlocks(5.5f)
            .createOreVariant("blackstone", 5.5f, 6.0f)
            .finish()
        )
        .addExtraItem(STORMYX_SHELL, Rarity.UNCOMMON, Item::new)
        .createDefaultTools(MythicToolMaterials.STORMYX, ToolSet.AttackSpeeds.BETTER_AXE, MythicSpearStats.STORMYX)
        .createDefaultArmor(MythicArmorMaterials.STORMYX, List.of(
            new MythicAttributeModifier(AdditionalEntityAttributes.MAGIC_PROTECTION, 1.0, ADD_VALUE, HEAD),
            new MythicAttributeModifier(AdditionalEntityAttributes.MAGIC_PROTECTION, 1.0, ADD_VALUE, CHEST),
            new MythicAttributeModifier(AdditionalEntityAttributes.MAGIC_PROTECTION, 1.0, ADD_VALUE, LEGS),
            new MythicAttributeModifier(AdditionalEntityAttributes.MAGIC_PROTECTION, 1.0, ADD_VALUE, FEET),
            new MythicAttributeModifier(AdditionalEntityAttributes.MAGIC_PROTECTION, 3.0, ADD_VALUE, BODY)
        ))
        .finish();

    public static final Material TIDESINGER = Material.Builder.createRawBuilder("tidesinger", MaterialType.SPECIAL)
        .createBaseMaterial(AQUARIUM_PEARL, Rarity.UNCOMMON, Item::new)
        .addSmithingTemplate(TIDESINGER_SMITHING_TEMPLATE, MythicSmithingTemplates.TIDESINGER)
        .createCustomToolset(
            new TidesingerToolSet(MythicToolMaterials.TIDESINGER), toolSet -> toolSet.createTidesingerTools(
                MythicToolMaterials.TIDESINGER,
                ToolSet.AttackSpeeds.BETTER_AXE,
                MythicSpearStats.TIDESINGER
            )
        )
        .createCustomArmorSet(
            new TidesingerArmorSet(MythicArmorMaterials.TIDESINGER),
            armorSet -> armorSet.initialize(List.of(
                new MythicAttributeModifier(AdditionalEntityAttributes.WATER_VISIBILITY, 0.3, ADD_MULTIPLIED_TOTAL, HEAD),
                new MythicAttributeModifier(SUBMERGED_MINING_SPEED, 3.0, ADD_MULTIPLIED_TOTAL, HEAD),
                new MythicAttributeModifier(OXYGEN_BONUS, 2.0, ADD_VALUE, CHEST),
                new MythicAttributeModifier(OXYGEN_BONUS, 2.0, ADD_VALUE, LEGS),
                new MythicAttributeModifier(OXYGEN_BONUS, 2.0, ADD_VALUE, BODY),
                new MythicAttributeModifier(WATER_MOVEMENT_EFFICIENCY, 1.0, ADD_VALUE, FEET),
                new MythicAttributeModifier(WATER_MOVEMENT_EFFICIENCY, 1.0, ADD_VALUE, BODY)
            ))
        )
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
        .addSmithingTemplate(UNOBTAINIUM_SMITHING_TEMPLATE, MythicSmithingTemplates.UNOBTAINIUM)
        .finish();

    public static final Material AEGIS = Material.Builder.createRawBuilder("aegis", MaterialType.SPECIAL)
        .createBaseMaterial(MythicResourceKeys.EMERALD_CRYSTAL, Rarity.UNCOMMON, Item::new)
        .addSmithingTemplate(AEGIS_SMITHING_TEMPLATE, MythicSmithingTemplates.AEGIS)
        .finish();

    public static void init() {
        // no-op
    }
}
