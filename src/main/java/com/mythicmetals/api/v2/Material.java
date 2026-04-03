package com.mythicmetals.api.v2;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.mythicmetals.MythicMetals;
import com.mythicmetals.misc.RegistryHelper;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.ToolMaterial;
import net.minecraft.world.item.equipment.ArmorMaterial;
import net.minecraft.world.level.block.Block;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

import java.util.function.Function;

public class Material {

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
    public final BiMap<ResourceKey<Item>, Item> extraItems;
    public final BiMap<ResourceKey<Block>, Block> extraBlocks;

    public static final Identifier STONE_MINING_LEVEL = BlockTags.NEEDS_STONE_TOOL.location();
    public static final Identifier IRON_MINING_LEVEL = BlockTags.NEEDS_IRON_TOOL.location();
    public static final Identifier DIAMOND_MINING_LEVEL = BlockTags.NEEDS_DIAMOND_TOOL.location();
    public static final Identifier NETHERITE_MINING_LEVEL = RegistryHelper.id("needs_netherite_tool");
    public static final Identifier MYTHIC_MINING_LEVEL = RegistryHelper.id("needs_unobtainable_tool");

    public Material(
        @NonNull Item baseMaterial,
        @Nullable Item nugget,
        @Nullable BlockSet blockSet,
        @Nullable ToolSet toolSet,
        @Nullable ArmorSet armorSet,
        BiMap<ResourceKey<Item>, Item> extraItems,
        BiMap<ResourceKey<Block>, Block> extraBlocks
    ) {
        this.baseMaterial = baseMaterial;
        this.nugget = nugget;
        this.blockSet = blockSet;
        this.toolSet = toolSet;
        this.armorSet = armorSet;
        this.extraItems = extraItems;
        this.extraBlocks = extraBlocks;
    }

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

        public Builder(String materialName) {
            this.name = materialName;
        }

        public Builder create(String materialName, MaterialType type) {
            this.type = type;
            return new Builder(materialName);
        }

        public Builder createBaseMaterial() {
            Item.Properties props;
            switch (type) {
                case RARE_ALLOY -> {
                    baseMaterialKey = RegistryHelper.itemKey(name + INGOT_POSTFIX);
                    props = baseProperties(baseMaterialKey, 0, Rarity.RARE);
                    createNugget(Rarity.RARE);
                }
                case ALLOY -> {
                    baseMaterialKey = RegistryHelper.itemKey(name + INGOT_POSTFIX);
                    props = baseProperties(baseMaterialKey, 0, Rarity.UNCOMMON);
                    createNugget(Rarity.UNCOMMON);
                }
                case INGOT -> {
                    baseMaterialKey = RegistryHelper.itemKey(name);
                    props = baseProperties(baseMaterialKey, 0, Rarity.COMMON);
                    createNugget(Rarity.COMMON);
                }
                default -> props = baseProperties(RegistryHelper.itemKey(name), 0, Rarity.COMMON);
            }
            this.baseMaterial = RegistryHelper.item(baseMaterialKey, new Item(props));
            return this;
        }

        protected void createNugget(Rarity rarity) {
            var key = RegistryHelper.itemKey(name + "_nugget");
            this.nugget = RegistryHelper.item(key, new Item(baseProperties(key, 0, rarity)));
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

        public Builder addExtraBlock(ResourceKey<Block> key, Block block) {
            extraBlocks.put(key, block);
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
            return new Material(baseMaterial, nugget, blockSet, toolSet, armorSet, extraItems, extraBlocks);
        }

        protected void registerExtras() {
            // TODO - Register both extra items and blocks
        }
    }
}
