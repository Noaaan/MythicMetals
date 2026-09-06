package com.mythicmetals.api.v2;

import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

/// Simple record that contains all necessary information for registering a block
public record BlockWithMiningLevel(
    Block block,
    ResourceKey<Block> blockKey,
    ResourceKey<Item> blockItemKey,
    TagKey<Block> miningLevel
) {
    public static BlockWithMiningLevel create(
        Block block,
        ResourceKey<Block> blockKey,
        ResourceKey<Item> itemKey,
        TagKey<Block> miningLevel
    ) {
        return new BlockWithMiningLevel(block, blockKey, itemKey, miningLevel);
    }
}
