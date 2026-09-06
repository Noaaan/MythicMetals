package com.mythicmetals.data;

import com.mythicmetals.api.v2.BlockWithMiningLevel;
import com.mythicmetals.misc.MaterialHelper;
import com.mythicmetals.api.v2.MaterialHelper;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.tags.BlockTags;
import org.jspecify.annotations.Nullable;
import java.util.concurrent.CompletableFuture;

public class MythicBlockTagProvider extends FabricTagsProvider.BlockTagsProvider {

    public MythicBlockTagProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @SuppressWarnings("UnstableApiUsage")
    @Override
    protected void addTags(HolderLookup.Provider arg) {
        // mining level handling for custom MM progression
        // currently shaped like:
        // stone/gold -> copper -> iron -> diamond -> netherite -> unobtainium (unused, just like vanilla netherite)
        builder(BlockTags.INCORRECT_FOR_STONE_TOOL)
            .addOptionalTag(MythicTags.NEEDS_COPPER_TOOLS)
            .addOptionalTag(MythicTags.NEEDS_UNOBTAINIUM_ALLOY_TOOLS);
        builder(BlockTags.INCORRECT_FOR_GOLD_TOOL)
            .addOptionalTag(MythicTags.NEEDS_COPPER_TOOLS)
            .addOptionalTag(MythicTags.NEEDS_UNOBTAINIUM_ALLOY_TOOLS);
        builder(BlockTags.INCORRECT_FOR_IRON_TOOL)
            .addOptionalTag(MythicTags.NEEDS_UNOBTAINIUM_ALLOY_TOOLS);
        builder(BlockTags.INCORRECT_FOR_DIAMOND_TOOL)
            .addOptionalTag(MythicTags.NEEDS_UNOBTAINIUM_ALLOY_TOOLS);
        builder(BlockTags.INCORRECT_FOR_NETHERITE_TOOL)
            .addOptionalTag(MythicTags.NEEDS_UNOBTAINIUM_ALLOY_TOOLS);

        MaterialHelper.BLOCK_SET_MAP.values().forEach((blockSet) -> {
            addMiningLevel(blockSet.storage());
            addMiningLevel(blockSet.ore());
            addMiningLevel(blockSet.rawStorage());
            addMiningLevel(blockSet.anvil());

            blockSet.oreVariants().values().forEach(this::addMiningLevel);
        });
    }

    private void addMiningLevel(@Nullable BlockWithMiningLevel blockRecord) {
        if (blockRecord == null) return;
        builder(blockRecord.miningLevel()).add(blockRecord.blockKey());
    }
}
