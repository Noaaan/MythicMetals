package com.mythicmetals.api.v2;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.mythicmetals.misc.RegistryHelper;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ToolMaterial;
import net.minecraft.world.item.equipment.ArmorMaterial;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;
import java.util.function.Consumer;
import java.util.function.Function;

public class Material {

    @NonNull
    public final Item baseMaterial;
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
        @Nullable BlockSet blockSet,
        @Nullable ToolSet toolSet,
        @Nullable ArmorSet armorSet,
        BiMap<ResourceKey<Item>, Item> extraItems,
        BiMap<ResourceKey<Block>, Block> extraBlocks
    ) {
        this.baseMaterial = baseMaterial;
        this.blockSet = blockSet;
        this.toolSet = toolSet;
        this.armorSet = armorSet;
        this.extraItems = extraItems;
        this.extraBlocks = extraBlocks;
    }

    static class Builder {
        private final String name;
        private Item baseMaterial;
        private BlockSet blockSet = null;
        private ToolSet toolSet = null;
        private ArmorSet armorSet = null;
        private final BiMap<ResourceKey<Item>, Item> extraItems = HashBiMap.create();
        private final BiMap<ResourceKey<Block>, Block> extraBlocks = HashBiMap.create();

        public Builder(String materialName) {
            this.name = materialName;
        }

        public Builder create(String materialName) {
            return new Builder(materialName);
        }

        public Builder createBaseMaterial(Consumer<Item.Properties> propsConsumer) {
            var props = new Item.Properties();
            propsConsumer.accept(props);
            this.baseMaterial = new Item(props);
            return this;
        }

        public Builder createDefaultBlocks(float strength, Identifier oreMiningLevel, Identifier storageMiningLevel) {
            this.blockSet = BlockSet.Builder.begin(name)
                .strength(strength)
                .createOre(oreMiningLevel)
                .strength(strength + 1.0f)
                .createOreStorageBlock(storageMiningLevel)
                .createStorageBlock(storageMiningLevel)
                .createAnvil(storageMiningLevel)
                .finish();
            return this;
        }

        public Builder createAlloyBlockSet(float strength, float resistance, Identifier miningLevel) {
            this.blockSet = BlockSet.Builder.begin(name)
                .strength(strength, resistance)
                .sounds(SoundType.METAL)
                .createStorageBlock(miningLevel)
                .createAnvil(miningLevel)
                .finish();
            return this;
        }

        public Builder createBlockSetFromBuilder(Function<BlockSet.Builder, BlockSet> blockSetBuilder) {
            this.blockSet = blockSetBuilder.apply(BlockSet.Builder.begin(this.name));
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

        /**
         * Registers and returns the finished Material
         */
        public Material finish() {
            // TODO - Register stuff
            if (baseMaterial == null) {
                throw new IllegalStateException("Base material must be registered!");
            }
            return new Material(baseMaterial, blockSet, toolSet, armorSet, extraItems, extraBlocks);
        }
    }
}
