package com.mythicmetals.api.v2;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.mythicmetals.MythicMetals;
import com.mythicmetals.item.MythicAttributeModifier;
import com.mythicmetals.item.MythicSpearStats;
import com.mythicmetals.item.armor.CustomHelmetArmorSet;
import com.mythicmetals.misc.RegistryHelper;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.*;
import net.minecraft.world.item.equipment.ArmorMaterial;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import org.jspecify.annotations.Nullable;
import java.util.List;
import java.util.function.*;

public record Material(
    String name,
    Item baseMaterial,
    MaterialType materialType,
    @Nullable Item nugget,
    @Nullable Item rawOre,
    @Nullable BlockSet blockSet,
    @Nullable ToolSet toolSet,
    @Nullable ArmorSet armorSet,
    BiMap<ResourceKey<Item>, Item> extraItems,
    BiMap<ResourceKey<Block>, Block> extraBlocks,
    boolean fireproof
) {

    public Material(
        String name,
        Item baseMaterial,
        MaterialType materialType,
        @Nullable Item nugget,
        @Nullable Item rawOre,
        @Nullable BlockSet blockSet,
        @Nullable ToolSet toolSet,
        @Nullable ArmorSet armorSet,
        BiMap<ResourceKey<Item>, Item> extraItems,
        BiMap<ResourceKey<Block>, Block> extraBlocks
    ) {
        this(name, baseMaterial, materialType, nugget, rawOre, blockSet, toolSet, armorSet, extraItems, extraBlocks, false);
    }

    public static final Identifier STONE_MINING_LEVEL = BlockTags.NEEDS_STONE_TOOL.location();
    public static final Identifier IRON_MINING_LEVEL = BlockTags.NEEDS_IRON_TOOL.location();
    public static final Identifier DIAMOND_MINING_LEVEL = BlockTags.NEEDS_DIAMOND_TOOL.location();
    public static final Identifier NETHERITE_MINING_LEVEL = RegistryHelper.id("needs_netherite_tool");
    public static final Identifier MYTHIC_MINING_LEVEL = RegistryHelper.id("needs_unobtainable_tool");

    public void getMaterialProperties(BiConsumer<@Nullable ArmorMaterial, @Nullable ToolMaterial> executor) {
        ArmorMaterial armorMat = null;
        ToolMaterial toolMat = null;
        if (this.toolSet != null) {
            toolMat = this.toolSet.getToolMaterial();
        }
        if (this.armorSet != null) {
            armorMat = this.armorSet.getArmorMaterial();
        }
        executor.accept(armorMat, toolMat);
    }

    ///
    /// Builder for the [Material] class
    ///
    public static class Builder {
        private final String name;
        private Item baseMaterial;
        private Item nugget;
        private Item rawOre;
        private BlockSet blockSet = null;
        private ToolSet toolSet = null;
        private ArmorSet armorSet = null;
        private final BiMap<ResourceKey<Item>, Item> extraItems = HashBiMap.create();
        private final BiMap<ResourceKey<Block>, Block> extraBlocks = HashBiMap.create();
        private final boolean fireproof;

        private static final String INGOT_POSTFIX = "_ingot";
        private final MaterialType type;

        private Builder(String materialName, MaterialType type, boolean fireproof) {
            this.name = materialName;
            this.type = type;
            this.fireproof = fireproof;
        }

        private Builder(String materialName, MaterialType type) {
            this(materialName, type, false);
        }

        public static Builder createRawBuilder(String materialName, MaterialType type) {
            return new Builder(materialName, type);
        }

        public static Builder create(String materialName, MaterialType type) {
            return new Builder(materialName, type)
                .createBaseMaterial();
        }

        public static Builder create(String materialName, MaterialType type, boolean fireproof) {
            return new Builder(materialName, type, fireproof)
                .createBaseMaterial();
        }

        public Builder createBaseMaterial(ResourceKey<Item> key, Rarity rarity, Function<Item.Properties, Item> function) {
            this.baseMaterial = RegistryHelper.item(key, function.apply(baseProperties(key, rarity)));
            return this;
        }

        protected Builder createBaseMaterial() {
            ResourceKey<Item> baseMaterialKey;
            Item.Properties props;
            switch (type) {
                case RARE_ALLOY, ALLOY -> {
                    baseMaterialKey = RegistryHelper.itemKey(name + INGOT_POSTFIX);
                    props = baseProperties(baseMaterialKey, computeRarity(type));
                    createNugget(computeRarity(type));
                }
                case INGOT -> {
                    baseMaterialKey = RegistryHelper.itemKey(name + INGOT_POSTFIX);
                    props = baseProperties(baseMaterialKey, computeRarity(type));
                    createNugget(computeRarity(type));
                    createRawOre(computeRarity(type));
                }
                default -> {
                    baseMaterialKey = RegistryHelper.itemKey(name);
                    props = baseProperties(RegistryHelper.itemKey(name), computeRarity(type));
                }
            }
            this.baseMaterial = RegistryHelper.item(baseMaterialKey, new Item(props));
            return this;
        }

        private Rarity computeRarity(MaterialType type) {
            return switch (type) {
                case RARE_ALLOY -> Rarity.RARE;
                case ALLOY, SPECIAL -> Rarity.UNCOMMON;
                case INGOT, BASIC -> Rarity.COMMON;
            };
        }

        protected void createNugget(Rarity rarity) {
            var key = RegistryHelper.itemKey(name + "_nugget");
            this.nugget = RegistryHelper.item(key, new Item(baseProperties(key, rarity)));
        }

        private void createRawOre(Rarity rarity) {
            var key = RegistryHelper.itemKey("raw_" + name);
            this.rawOre = RegistryHelper.item(key, new Item(baseProperties(key, rarity)));
        }

        public Builder createDefaultBlockSet(Identifier miningLevel, float strength) {
            var set = BlockSet.Builder.begin(name, miningLevel);
            switch (type) {
                case ALLOY, RARE_ALLOY -> set = set.createAlloyBlockSet(strength, strength + 1.0f);
                default -> set = set.createDefaultBlocks(strength);
            }
            this.blockSet = set.finish();
            return this;
        }

        public Builder createBlockSetFromBuilder(Identifier miningLevel, Function<BlockSet.Builder, BlockSet> blockSetBuilder) {
            this.blockSet = blockSetBuilder.apply(BlockSet.Builder.begin(this.name, miningLevel));
            return this;
        }

        public Builder createToolSet(UnaryOperator<Item.Properties> propertiesConsumer, ToolMaterial material, ToolSet.AttackSpeeds attackSpeeds, MythicSpearStats.SpearStats spearStats) {
            this.toolSet = new ToolSet(name, material).createDefault(properties -> {
                if (fireproof) {
                    return propertiesConsumer.apply(properties.fireResistant());
                }
                return propertiesConsumer.apply(properties);
            }, attackSpeeds, spearStats, List.of());
            return this;
        }

        public Builder createDefaultTools(ToolMaterial material, ToolSet.AttackSpeeds attackSpeeds, MythicSpearStats.SpearStats spearStats) {
            this.toolSet = new ToolSet(name, material).createDefault(properties -> {
                if (fireproof) {
                    return properties.fireResistant();
                }
                return properties;
            }, attackSpeeds, spearStats, List.of());
            return this;
        }

        public Builder createToolSet(ToolMaterial material, ToolSet.AttackSpeeds attackSpeeds, MythicSpearStats.SpearStats spearStats, List<MythicAttributeModifier> extraModifiers) {
            this.toolSet = new ToolSet(name, material).createDefault(properties -> {
                if (fireproof) {
                    return properties.fireResistant();
                }
                return properties;
            }, attackSpeeds, spearStats, extraModifiers);
            return this;
        }

        public <T extends ToolSet> Builder createCustomToolset(T toolSet, Consumer<T> executor) {
            executor.accept(toolSet);
            this.toolSet = toolSet;
            return this;
        }

        public Builder createDefaultArmor(ArmorMaterial material) {
            this.armorSet = new ArmorSet(name, material).initialize();
            return this;
        }

        public Builder addExtraItem(ResourceKey<Item> itemKey, Rarity rarity, Function<Item.Properties, Item> function) {
            extraItems.putIfAbsent(itemKey, RegistryHelper.item(itemKey, function.apply(baseProperties(itemKey, rarity))));
            return this;
        }

        public Builder addExtraItem(ResourceKey<Item> itemKey, Item item) {
            extraItems.putIfAbsent(itemKey, RegistryHelper.item(itemKey, item));
            return this;
        }

        public Builder addExtraBlock(ResourceKey<Block> key, float strength, Function<BlockBehaviour.Properties, Block> function) {
            return addExtraBlock(key, function.apply(BlockSet.createBlockSettings(key, strength)), Rarity.COMMON);
        }

        public Builder addExtraBlock(ResourceKey<Block> key, Function<BlockBehaviour.Properties, Block> function) {
            return addExtraBlock(key, function.apply(BlockSet.createBlockSettings(key)), Rarity.COMMON);
        }

        public Builder addExtraBlock(ResourceKey<Block> key, Rarity rarity, Function<BlockBehaviour.Properties, Block> function) {
            return addExtraBlock(key, function.apply(BlockSet.createBlockSettings(key)), rarity);
        }

        public Builder addExtraBlock(ResourceKey<Block> key, Block block, Rarity rarity) {
            extraBlocks.putIfAbsent(key, RegistryHelper.blockOnly(key, block));
            var itemKey = RegistryHelper.itemKey(key.identifier().getPath());
            extraItems.putIfAbsent(itemKey, RegistryHelper.item(itemKey, new BlockItem(block, baseProperties(itemKey, rarity))));
            return this;
        }

        public Builder addExtraBlockAndItem(String name, Function<BlockBehaviour.Properties, Block> blockFunction, BiFunction<Block, Item.Properties, Item> itemFunction) {
            var itemKey = RegistryHelper.itemKey(name);
            var blockKey = RegistryHelper.blockKey(name);
            var block = RegistryHelper.blockOnly(blockKey, blockFunction.apply(BlockSet.createBlockSettings(blockKey)));
            extraBlocks.putIfAbsent(blockKey, block);
            addExtraItem(itemKey, itemFunction.apply(block, baseProperties(itemKey, computeRarity(type))));
            return this;
        }

        protected Item.Properties baseProperties(ResourceKey<Item> idKey, Rarity rarity) {
            var props = new Item.Properties();
            if (fireproof) {
                props = props.fireResistant();
            }
            return props
                .setId(idKey)
                .group(MythicMetals.TABBED_GROUP)
                .rarity(rarity)
                .tab(0);
        }

        public Builder addSmithingTemplate(ResourceKey<Item> key, SmithingTemplateComponents templateComponents) {
            return addExtraItem(key, computeRarity(this.type), templateComponents::toItem);
        }

        public Builder createDefaultArmor(ArmorMaterial armorMaterial, List<MythicAttributeModifier> extraModifiers) {
            var set = new ArmorSet(this.name, armorMaterial);
            this.armorSet = set.initialize(settings -> {
                if (fireproof) {
                    return settings.fireResistant();
                }
                return settings;
            }, extraModifiers, true);
            return this;
        }

        public Builder createCustomHelmetArmorSet(ArmorMaterial material, ModelLayerLocation model, Identifier texture, Boolean initMountArmor) {
            return createCustomHelmetArmorSet(material, List.of(), model, texture, initMountArmor);
        }

        public Builder createCustomHelmetArmorSet(ArmorMaterial material, ModelLayerLocation model, Identifier texture) {
            return createCustomHelmetArmorSet(material, List.of(), model, texture, true);
        }

        public Builder createCustomHelmetArmorSet(ArmorMaterial material, List<MythicAttributeModifier> extraModifiers, ModelLayerLocation model, Identifier texture, Boolean initMountArmor) {
            var customSet = new CustomHelmetArmorSet(this.name, material, model, texture);
            this.armorSet = customSet.initialize(settings -> {
                if (fireproof) {
                    return settings.fireResistant();
                }
                return settings;
            }, extraModifiers, initMountArmor);
            return this;
        }

        public Builder createCustomArmorSet(ArmorSet armorSet, Consumer<ArmorSet> executor) {
            this.armorSet = armorSet;
            executor.accept(this.armorSet);
            return this;
        }

        /**
         * Registers and returns the finished Material
         */
        public Material finish() {
            if (baseMaterial == null) {
                throw new IllegalStateException("Base material must be registered!");
            }
            return new Material(name, baseMaterial, type, nugget, rawOre, blockSet, toolSet, armorSet, extraItems, extraBlocks);
        }
    }
}
