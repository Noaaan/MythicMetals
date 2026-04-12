package com.mythicmetals.api.v2;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.mythicmetals.MythicMetals;
import com.mythicmetals.misc.RegistryHelper;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.*;
import net.minecraft.world.item.equipment.ArmorMaterial;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;
import java.util.function.BiFunction;
import java.util.function.Function;

public class Material {
    public final String name;
    @NonNull
    public final Item baseMaterial;
    @Nullable
    public final Item nugget;
    @Nullable
    public final ToolSet toolSet;
    @Nullable
    public final BlockSet blockSet;
    @Nullable
    public final ArmorSet armorSet;

    private final BiMap<ResourceKey<Item>, Item> extraItems;

    private final BiMap<ResourceKey<Block>, Block> extraBlocks;

    public static final Identifier STONE_MINING_LEVEL = BlockTags.NEEDS_STONE_TOOL.location();

    public static final Identifier IRON_MINING_LEVEL = BlockTags.NEEDS_IRON_TOOL.location();
    public static final Identifier DIAMOND_MINING_LEVEL = BlockTags.NEEDS_DIAMOND_TOOL.location();
    public static final Identifier NETHERITE_MINING_LEVEL = RegistryHelper.id("needs_netherite_tool");
    public static final Identifier MYTHIC_MINING_LEVEL = RegistryHelper.id("needs_unobtainable_tool");
    public Material(
        String name, @NonNull Item baseMaterial,
        @Nullable Item nugget,
        @Nullable BlockSet blockSet,
        @Nullable ToolSet toolSet,
        @Nullable ArmorSet armorSet,
        BiMap<ResourceKey<Item>, Item> extraItems,
        BiMap<ResourceKey<Block>, Block> extraBlocks
    ) {
        this.name = name;
        this.baseMaterial = baseMaterial;
        this.nugget = nugget;
        this.blockSet = blockSet;
        this.toolSet = toolSet;
        this.armorSet = armorSet;
        this.extraItems = extraItems;
        this.extraBlocks = extraBlocks;
    }

    public BiMap<ResourceKey<Block>, Block> getExtraBlocks() {
        return extraBlocks;
    }

    public BiMap<ResourceKey<Item>, Item> getExtraItems() {
        return extraItems;
    }

    ///
    /// Builder for the [Material] class
    ///
    public static class Builder {
        private final String name;
        private Item baseMaterial;
        private Item nugget;
        private ResourceKey<Item> baseMaterialKey;
        private BlockSet blockSet = null;
        private ToolSet toolSet = null;
        private ArmorSet armorSet = null;
        private final BiMap<ResourceKey<Item>, Item> extraItems = HashBiMap.create();
        private final BiMap<ResourceKey<Block>, Block> extraBlocks = HashBiMap.create();

        private static final String INGOT_POSTFIX = "_ingot";
        private MaterialType type;

        private Builder(String materialName, MaterialType type) {
            this.name = materialName;
            this.type = type;
        }

        public static Builder create(String materialName, MaterialType type) {
            return new Builder(materialName, type)
                .createBaseMaterial();
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
                    baseMaterialKey = RegistryHelper.itemKey(name);
                    props = baseProperties(baseMaterialKey, 0, computeRarity(type));
                    createNugget(computeRarity(type));
                }
                default -> props = baseProperties(RegistryHelper.itemKey(name), 0, computeRarity(type));
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
            this.nugget = RegistryHelper.item(key, new Item(baseProperties(key, 0, rarity)));
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

        public Builder createDefaultTools(ToolMaterial material, ToolSet.AttackSpeeds attackSpeeds) {
            this.toolSet = new ToolSet(name, material).createDefault(attackSpeeds);
            return this;
        }

        public Builder createDefaultArmor(ArmorMaterial material) {
            this.armorSet = new ArmorSet(name, material).createDefault();
            return this;
        }

        public Builder addExtraItem(ResourceKey<Item> itemKey, Item item) {
            extraItems.put(itemKey, item);
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

        public Builder addExtraBlock(ResourceKey<Block> key, Block block) {
            return addExtraBlock(key, block, Rarity.COMMON);
        }

        public Builder addExtraBlock(ResourceKey<Block> key, Block block, Rarity rarity) {
            extraBlocks.put(key, block);
            var itemKey = RegistryHelper.itemKey(key.identifier().getPath());
            extraItems.put(itemKey, new BlockItem(block, baseProperties(itemKey, 0, rarity)));
            return this;
        }

        public Builder addExtraBlockAndItem(String name, Function<BlockBehaviour.Properties, Block> blockFunction, BiFunction<Block, Item.Properties, Item> itemFunction) {
            var itemKey = RegistryHelper.itemKey(name);
            var blockKey = RegistryHelper.blockKey(name);
            var block = blockFunction.apply(BlockSet.createBlockSettings(blockKey));
            extraBlocks.put(blockKey, block);
            extraItems.put(itemKey, itemFunction.apply(block, baseProperties(itemKey, 0, computeRarity(type))));
            return this;
        }

        protected Item.Properties baseProperties(ResourceKey<Item> idKey, int tab, Rarity rarity) {
            return new Item.Properties()
                .setId(idKey)
                .group(MythicMetals.TABBED_GROUP)
                .rarity(rarity)
                .tab(tab);
        }

        /**
         * Registers and returns the finished Material
         */
        public Material finish() {
            if (baseMaterial == null) {
                throw new IllegalStateException("Base material must be registered! Call 'Material#createBaseMaterial()' on the Material builder.");
            }
            registerExtras();
            return new Material(name, baseMaterial, nugget, blockSet, toolSet, armorSet, extraItems, extraBlocks);
        }

        protected void registerExtras() {
            // TODO - Register both extra items and blocks
        }
    }
}
