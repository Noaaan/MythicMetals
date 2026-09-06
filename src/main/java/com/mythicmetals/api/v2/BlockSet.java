package com.mythicmetals.api.v2;

import com.mythicmetals.misc.RegistryHelper;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.TagKey;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.block.AnvilBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import org.jetbrains.annotations.NotNull;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public record BlockSet(
    String name,
    @NotNull
    BlockWithMiningLevel storage,
    @Nullable
    BlockWithMiningLevel ore,
    @Nullable
    BlockWithMiningLevel rawStorage,
    @Nullable
    BlockWithMiningLevel anvil,
    Map<String, BlockWithMiningLevel> oreVariants
) {
    // FIXME - Sounds
    // TODO - Map Colors and Instruments
    public static class Builder {
        private final @NonNull ResourceKey<Block> storageKey;
        private final @NonNull ResourceKey<Item> storageItemKey;
        private final @Nullable ResourceKey<Block> oreKey;
        private final @Nullable ResourceKey<Item> oreItemKey;
        private final @Nullable ResourceKey<Block> rawStorageKey;
        private final @Nullable ResourceKey<Item> rawStorageItemKey;
        private final @Nullable ResourceKey<Block> anvilKey;
        private final @Nullable ResourceKey<Item> anvilItemKey;
        protected Block storage;
        protected @Nullable Block ore;
        protected @Nullable Block rawStorage;
        protected @Nullable Block anvil;

        private final String name;
        public final TagKey<Block> miningLevel;
        private boolean fireproof = false;
        private Rarity rarity = Rarity.COMMON;
        private final Map<String, BlockWithMiningLevel> oreVariants;

        private Builder(String name, TagKey<Block> requiredMiningLevel) {
            this.name = name;
            this.oreKey = RegistryHelper.blockKey(name + "_ore");
            this.oreItemKey = RegistryHelper.itemKey(name + "_ore");
            this.storageKey = RegistryHelper.blockKey(name + "_block");
            this.storageItemKey = RegistryHelper.itemKey(name + "_block");
            this.rawStorageKey = RegistryHelper.blockKey("raw_" + name + "_block");
            this.rawStorageItemKey = RegistryHelper.itemKey("raw_" + name + "_block");
            this.anvilKey = RegistryHelper.blockKey(name + "_anvil");
            this.anvilItemKey = RegistryHelper.itemKey(name + "_anvil");
            this.oreVariants = new HashMap<>();
            this.miningLevel = requiredMiningLevel;
        }

        public static Builder begin(String name, TagKey<Block> miningLevel) {
            return new Builder(name, miningLevel);
        }

        public Builder fireproof() {
            this.fireproof = true;
            return this;
        }

        public Builder rarity(Rarity rarity) {
            this.rarity = rarity;
            return this;
        }

        public BlockSet finish() {
            if (storage == null) throw new IllegalStateException("Storage Block must not be null for a block set!");
            BlockWithMiningLevel storageRecord = BlockWithMiningLevel.create(storage, storageKey, storageItemKey, miningLevel);
            BlockWithMiningLevel oreRecord = null;
            BlockWithMiningLevel rawStorageRecord = null;
            BlockWithMiningLevel anvilRecord = null;
            if (ore != null) {
                oreRecord = BlockWithMiningLevel.create(ore, oreKey, oreItemKey, miningLevel);
            }
            if (rawStorage != null) {
                rawStorageRecord = BlockWithMiningLevel.create(rawStorage, rawStorageKey, rawStorageItemKey, miningLevel);
            }
            if (anvil != null) {
                anvilRecord = BlockWithMiningLevel.create(anvil, anvilKey, anvilItemKey, miningLevel);
            }
            return new BlockSet(
                this.name,
                storageRecord,
                oreRecord,
                rawStorageRecord,
                anvilRecord,
                this.oreVariants
            );
        }

        public Builder createStorageBlock(float strength, float resistance) {
            this.storage = RegistryHelper.block(storageKey, storageItemKey, new Block(
                BlockBehaviour.Properties.of()
                    .setId(storageKey)
                    .strength(strength, resistance)
                    .requiresCorrectToolForDrops()
                    .forceSolidOn()
            ), fireproof, rarity);
            return this;
        }

        public Builder createOre(float strength, UniformInt exp) {
            this.ore = RegistryHelper.block(oreKey, oreItemKey, new DropExperienceBlock(exp,
                baseBlockSettings(oreKey, strength, strength + 1.0f)
            ), fireproof, rarity);
            return this;
        }

        public Builder createOreStorageBlock(float strength, float resistance) {
            this.rawStorage = RegistryHelper.block(rawStorageKey, rawStorageItemKey, new Block(
                baseBlockSettings(rawStorageKey, strength, resistance)
            ), fireproof, rarity);
            return this;
        }

        public Builder createAnvil(float strength) {
            this.anvil = RegistryHelper.block(anvilKey, anvilItemKey, new AnvilBlock(
                baseBlockSettings(anvilKey, strength, 15000f)
            ), fireproof, rarity);
            return this;
        }

        public Builder createDefaultBlocks(float strength) {
            return createOre(strength, UniformInt.of(0, 0))
                .createOreStorageBlock(strength, strength + 1.0f)
                .createStorageBlock(strength + 1.0f, strength + 2.0f)
                .createAnvil(strength + 1.0f);
        }

        public Builder createAlloyBlockSet(float strength, float resistance) {
            return createStorageBlock(strength, resistance)
                .createAnvil(strength);
        }

        public Builder createCustomOre(float strength, Function<BlockBehaviour.Properties, Block> func) {
            return createCustomOre(strength, strength + 1.0f, func);
        }

        public Builder createCustomOre(float strength, float resistance, Function<BlockBehaviour.Properties, Block> func) {
            var oreItemKey = RegistryHelper.itemKey(oreKey.identifier().getPath());
            this.ore = RegistryHelper.block(oreKey, oreItemKey, func.apply(baseBlockSettings(oreKey, strength, resistance)));
            return this;
        }

        public Builder createCustomOreVariant(String variant, float strength, float resistance, TagKey<Block> customMiningLevel, Function<BlockBehaviour.Properties, Block> func) {
            var variantKey = RegistryHelper.blockKey("%s_%s_ore".formatted(variant, name));
            var variantItemKey = RegistryHelper.itemKey("%s_%s_ore".formatted(variant, name));
            var oreBlock = RegistryHelper.block(
                variantKey, variantItemKey, func.apply(baseBlockSettings(variantKey, strength, resistance))
            );
            oreVariants.put(variant, BlockWithMiningLevel.create(oreBlock, variantKey, variantItemKey, customMiningLevel));
            return this;
        }

        public Builder createOreVariant(String variant, float strength, float resistance) {
            return createOreVariant(variant, strength, resistance, UniformInt.of(0, 0));
        }

        public Builder createOreVariant(String variant, float strength, float resistance, IntProvider xp) {
            return createOreVariant(variant, strength, resistance, xp, miningLevel);
        }

        public Builder createOreVariant(String variant, float strength, float resistance, IntProvider xp, TagKey<Block> customMiningLevel) {
            var variantKey = RegistryHelper.blockKey("%s_%s_ore".formatted(variant, name));
            var variantItemKey = RegistryHelper.itemKey("%s_%s_ore".formatted(variant, name));
            var block = RegistryHelper.block(
                variantKey, variantItemKey, new DropExperienceBlock(xp, baseBlockSettings(variantKey, strength, resistance)
                ));
            oreVariants.put(variant, BlockWithMiningLevel.create(block, variantKey, variantItemKey, customMiningLevel));
            return this;
        }

        public Builder createCustomStorageBlock(float strength, Function<BlockBehaviour.Properties, Block> settings) {
            var props = baseBlockSettings(storageKey, strength, strength + 1f);
            this.storage = RegistryHelper.block(storageKey, storageItemKey, settings.apply(props));
            return this;
        }

        protected BlockBehaviour.Properties baseBlockSettings(ResourceKey<Block> key, float strength, float resistance) {
            return BlockBehaviour.Properties.of()
                .setId(key)
                .strength(strength, resistance)
                .requiresCorrectToolForDrops()
                .forceSolidOn();
        }
    }

    public static BlockBehaviour.Properties createBlockSettings(ResourceKey<Block> key, float strength) {
        return BlockBehaviour.Properties.of()
            .setId(key)
            .strength(strength, strength + 0.5f)
            .requiresCorrectToolForDrops()
            .forceSolidOn();
    }

    /**
     * @apiNote This particular method does NOT call {@link net.minecraft.world.level.block.state.BlockBehaviour.Properties#strength(float)}.
     * You will need to do that yourself.
     */
    public static BlockBehaviour.Properties createBlockSettings(ResourceKey<Block> key) {
        return BlockBehaviour.Properties.of()
            .setId(key)
            .requiresCorrectToolForDrops()
            .forceSolidOn();
    }
}