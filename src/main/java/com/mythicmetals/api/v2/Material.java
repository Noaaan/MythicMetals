package com.mythicmetals.api.v2;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.mythicmetals.MythicAttributeModifier;
import com.mythicmetals.MythicMetals;
import com.mythicmetals.armor.CustomHelmetArmorSet;
import com.mythicmetals.item.MythicSpearStats;
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
    BiMap<ResourceKey<Block>, Block> extraBlocks
) {

    public static final Identifier STONE_MINING_LEVEL = BlockTags.NEEDS_STONE_TOOL.location();
    public static final Identifier IRON_MINING_LEVEL = BlockTags.NEEDS_IRON_TOOL.location();
    public static final Identifier DIAMOND_MINING_LEVEL = BlockTags.NEEDS_DIAMOND_TOOL.location();
    public static final Identifier NETHERITE_MINING_LEVEL = RegistryHelper.id("needs_netherite_tool");
    public static final Identifier MYTHIC_MINING_LEVEL = RegistryHelper.id("needs_unobtainable_tool");

    ///
    /// Builder for the [Material] class
    ///
    public static class Builder {
        private final String name;
        private Item baseMaterial;
        private Item nugget;
        private Item rawOre;
        private ResourceKey<Item> baseMaterialKey;
        private BlockSet blockSet = null;
        private ToolSet toolSet = null;
        private ArmorSet armorSet = null;
        private final BiMap<ResourceKey<Item>, Item> extraItems = HashBiMap.create();
        private final BiMap<ResourceKey<Block>, Block> extraBlocks = HashBiMap.create();

        private static final String INGOT_POSTFIX = "_ingot";
        // TODO - Handle fireproofing
        private final MaterialType type;

        private Builder(String materialName, MaterialType type) {
            this.name = materialName;
            this.type = type;
        }

        public static Builder createRawBuilder(String materialName, MaterialType type) {
            return new Builder(materialName, type);
        }

        public static Builder create(String materialName, MaterialType type) {
            return new Builder(materialName, type)
                .createBaseMaterial();
        }

        public Builder createBaseMaterial(ResourceKey<Item> key, Rarity rarity, Function<Item.Properties, Item> function) {
            this.baseMaterial = RegistryHelper.item(key, function.apply(baseProperties(key, 0, rarity)));
            return this;
        }

        protected Builder createBaseMaterial() {
            Item.Properties props;
            switch (type) {
                case RARE_ALLOY, ALLOY -> {
                    baseMaterialKey = RegistryHelper.itemKey(name + INGOT_POSTFIX);
                    props = baseProperties(baseMaterialKey, 0, computeRarity(type));
                    createNugget(computeRarity(type));
                }
                case INGOT -> {
                    baseMaterialKey = RegistryHelper.itemKey(name + INGOT_POSTFIX);
                    props = baseProperties(baseMaterialKey, 0, computeRarity(type));
                    createNugget(computeRarity(type));
                    createRawOre(computeRarity(type));
                }
                default -> {
                    baseMaterialKey = RegistryHelper.itemKey(name);
                    props = baseProperties(RegistryHelper.itemKey(name), 0, computeRarity(type));
                }
            }
            this.baseMaterial = RegistryHelper.item(baseMaterialKey, new Item(props));
            return this;
        }

        private Rarity computeRarity(MaterialType type) {
            return switch (type) {
                case RARE_ALLOY -> Rarity.RARE;
                case ALLOY, ARMOR, SPECIAL -> Rarity.UNCOMMON;
                case INGOT, BASIC -> Rarity.COMMON;
            };
        }

        protected void createNugget(Rarity rarity) {
            var key = RegistryHelper.itemKey(name + "_nugget");
            this.nugget = RegistryHelper.item(key, new Item(baseProperties(key, 0, rarity)));
        }

        private void createRawOre(Rarity rarity) {
            var key = RegistryHelper.itemKey("raw_" + name);
            this.rawOre = RegistryHelper.item(key, new Item(baseProperties(key, 0, rarity)));
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

        public Builder createDefaultTools(ToolMaterial material, ToolSet.AttackSpeeds attackSpeeds, MythicSpearStats.SpearStats spearStats) {
            this.toolSet = new ToolSet(name, material).createDefault(attackSpeeds, spearStats);
            return this;
        }

        public Builder createDefaultArmor(ArmorMaterial material) {
            this.armorSet = new ArmorSet(name, material).initialize();
            return this;
        }

        public Builder addExtraItem(ResourceKey<Item> itemKey, Rarity rarity, Function<Item.Properties, Item> function) {
            extraItems.putIfAbsent(itemKey, RegistryHelper.item(itemKey, function.apply(baseProperties(itemKey, 0, rarity))));
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
            extraBlocks.putIfAbsent(key, RegistryHelper.block(key, block));
            var itemKey = RegistryHelper.itemKey(key.identifier().getPath());
            extraItems.putIfAbsent(itemKey, RegistryHelper.item(itemKey, new BlockItem(block, baseProperties(itemKey, 0, rarity))));
            return this;
        }

        public Builder addExtraBlockAndItem(String name, Function<BlockBehaviour.Properties, Block> blockFunction, BiFunction<Block, Item.Properties, Item> itemFunction) {
            var itemKey = RegistryHelper.itemKey(name);
            var blockKey = RegistryHelper.blockKey(name);
            var block = RegistryHelper.block(blockKey, blockFunction.apply(BlockSet.createBlockSettings(blockKey)));
            extraBlocks.putIfAbsent(blockKey, block);
            addExtraItem(itemKey, itemFunction.apply(block, baseProperties(itemKey, 0, computeRarity(type))));
            return this;
        }

        protected Item.Properties baseProperties(ResourceKey<Item> idKey, int tab, Rarity rarity) {
            return new Item.Properties()
                .setId(idKey)
                .group(MythicMetals.TABBED_GROUP)
                .rarity(rarity)
                .tab(tab);
        }

        public Builder addSmithingTemplate(ResourceKey<Item> key, SmithingTemplateComponents templateComponents) {
            return addExtraItem(key, computeRarity(this.type), templateComponents::toItem);
        }

        public Builder createDefaultArmor(ArmorMaterial armorMaterial, List<MythicAttributeModifier> extraModifiers) {
            var set = new ArmorSet(this.name, armorMaterial);
            this.armorSet = set.initialize(settings -> {}, extraModifiers, true);
            return this;
        }

        public Builder createCustomHelmetArmorSet(ArmorMaterial material, ModelLayerLocation model, Identifier texture, Boolean initMountArmor) {
            return createCustomHelmetArmorSet(material, List.of(), model, texture, initMountArmor);
        }

        public Builder createCustomHelmetArmorSet(ArmorMaterial material, ModelLayerLocation model, Identifier texture) {
            return createCustomHelmetArmorSet(material, List.of(), model, texture, true);
        }

        public Builder createCustomHelmetArmorSet(ArmorMaterial material, List<MythicAttributeModifier> extraModifiers, ModelLayerLocation model, Identifier texture, Boolean initMountArmor) {
            var armorSet = new CustomHelmetArmorSet(this.name, material, model, texture);
            this.armorSet = armorSet.initialize(settings -> {}, extraModifiers, initMountArmor);
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
            if (baseMaterial == null && type != MaterialType.ARMOR) {
                throw new IllegalStateException("Base material must be registered!");
            }
            return new Material(name, baseMaterial, type, nugget, rawOre, blockSet, toolSet, armorSet, extraItems, extraBlocks);
        }
    }
}
