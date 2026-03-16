package com.mythicmetals.api.v2;

import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DropExperienceBlock;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;
import java.util.Map;

import static com.mythicmetals.misc.RegistryHelper.blockKey;
import static com.mythicmetals.misc.RegistryHelper.itemKey;

public class BlockSet {
    private final String name;
    @NonNull
    public final ResourceKey<Block> storageBlockKey;
    @NonNull
    public final ResourceKey<Item> storageBlockItemKey;
    @Nullable
    public final ResourceKey<Block> rawStorageBlockKey;
    @Nullable
    public final ResourceKey<Item> rawStorageBlockItemKey;
    @Nullable
    public final ResourceKey<Block> oreBlockKey;
    @Nullable
    public final ResourceKey<Item> oreBlockItemKey;
    @Nullable
    public final ResourceKey<Block> anvilBlockKey;
    @Nullable
    public final ResourceKey<Item> anvilBlockItemKey;

    private final Map<String, DropExperienceBlock> oreVariants;

    public BlockSet(String name, Map<String, DropExperienceBlock> oreVariants) {
        this.name = name;
        this.storageBlockKey = blockKey(name + "_block");
        this.storageBlockItemKey = itemKey(name + "_block");
        this.rawStorageBlockKey = blockKey("raw_" + name + "_block");
        this.rawStorageBlockItemKey = itemKey("raw_" + name + "_block");
        this.oreBlockKey = blockKey(name + "_ore");
        this.oreBlockItemKey = itemKey(name + "_ore");
        this.anvilBlockKey = blockKey(name + "_anvil");
        this.anvilBlockItemKey = itemKey(name + "_anvil");
        this.oreVariants = oreVariants;
    }

    static class Builder {
        private boolean fireproof = false;
        private Rarity rarity = Rarity.COMMON;
        private float hardness;
        private float resistance;
        // fireproof, mining level, rarity (item), strength (hardness & resistance), sounds
    }
}
