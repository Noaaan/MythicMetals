package com.mythicmetals.api.v2;

import com.mythicmetals.misc.RegistryHelper;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.block.AnvilBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public record BlockSet(
    String name,
    ResourceKey<Block> storageKey,
    ResourceKey<Item> storageItemKey,
    ResourceKey<Block> oreKey,
    ResourceKey<Item> oreItemKey,
    ResourceKey<Block> rawStorageKey,
    ResourceKey<Item> rawStorageItemKey,
    ResourceKey<Block> anvilKey,
    ResourceKey<Item> anvilItemKey,
    Block storage,
    Block ore,
    Block rawStorage,
    Block anvil
) {

    public static class Builder {
        @NonNull
        private ResourceKey<Block> storageKey;
        @NonNull
        private ResourceKey<Item> storageItemKey;
        @Nullable
        private ResourceKey<Block> oreKey;
        @Nullable
        private ResourceKey<Item> oreItemKey;
        @Nullable
        private ResourceKey<Block> rawStorageKey;
        @Nullable
        private ResourceKey<Item> rawStorageItemKey;
        @Nullable
        private ResourceKey<Block> anvilKey;
        @Nullable
        private ResourceKey<Item> anvilItemKey;

        protected Block storage;
        @Nullable
        protected Block ore;
        @Nullable
        protected Block rawStorage;
        @Nullable
        protected Block anvil;

        private final String name;
        private final Map<String, Block> oreVariants;
        private final Identifier miningLevel;
        private boolean fireproof = false;
        private Rarity rarity = Rarity.COMMON;

        private Builder(String name, Identifier requiredMiningLevel) {
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

        public static Builder begin(String name, Identifier miningLevel) {
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
            return new BlockSet(
                this.name,
                this.storageKey,
                this.storageItemKey,
                this.oreKey,
                this.oreItemKey,
                this.rawStorageKey,
                this.rawStorageItemKey,
                this.anvilKey,
                this.anvilItemKey,
                this.storage,
                this.ore,
                this.rawStorage,
                this.anvil
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

        public Builder createAnvil(float strength, float resistance) {
            this.anvil = RegistryHelper.block(anvilKey, anvilItemKey, new AnvilBlock(
                baseBlockSettings(anvilKey, strength, resistance)
            ), fireproof, rarity);
            return this;
        }

        public Builder createDefaultBlocks(float strength) {
            return createOre(strength, UniformInt.of(0, 0))
                .createOreStorageBlock(strength, strength + 1.0f)
                .createStorageBlock(strength + 1.0f, strength + 2.0f)
                .createAnvil(strength + 1.0f, 15000f);
        }

        public Builder createAlloyBlockSet(float strength, float resistance) {
            return createStorageBlock(strength, resistance)
                .createAnvil(strength, resistance);
        }

        public Builder createCustomStorageBlock(Function<BlockBehaviour.Properties, Block> settings) {
            var props = BlockBehaviour.Properties.of().setId(storageKey);
            this.storage = settings.apply(props);
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
}