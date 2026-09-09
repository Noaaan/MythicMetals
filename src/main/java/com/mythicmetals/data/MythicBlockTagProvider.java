package com.mythicmetals.data;

import com.mythicmetals.api.v2.BlockWithMiningLevel;
import com.mythicmetals.api.v2.MaterialHelper;
import com.mythicmetals.item.MythicResourceKeys;
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

    @Override
    protected void addTags(HolderLookup.Provider arg) {
        // mining level handling for custom MM progression
        // currently shaped like:
        // wood -> stone/gold -> copper -> iron -> diamond -> netherite -> unobtainium (unused, just like vanilla netherite)
        builder(BlockTags.INCORRECT_FOR_WOODEN_TOOL)
            .addOptionalTag(MythicTags.NEEDS_COPPER_TOOLS)
            .addOptionalTag(MythicTags.NEEDS_NETHERITE_TOOLS)
            .addOptionalTag(MythicTags.NEEDS_UNOBTAINIUM_ALLOY_TOOLS);
        builder(BlockTags.INCORRECT_FOR_STONE_TOOL)
            .addOptionalTag(MythicTags.NEEDS_COPPER_TOOLS)
            .addOptionalTag(MythicTags.NEEDS_NETHERITE_TOOLS)
            .addOptionalTag(MythicTags.NEEDS_UNOBTAINIUM_ALLOY_TOOLS);
        builder(BlockTags.INCORRECT_FOR_GOLD_TOOL)
            .addOptionalTag(MythicTags.NEEDS_COPPER_TOOLS)
            .addOptionalTag(MythicTags.NEEDS_NETHERITE_TOOLS)
            .addOptionalTag(MythicTags.NEEDS_UNOBTAINIUM_ALLOY_TOOLS);
        builder(BlockTags.INCORRECT_FOR_COPPER_TOOL)
            .addOptionalTag(MythicTags.NEEDS_NETHERITE_TOOLS)
            .addOptionalTag(MythicTags.NEEDS_UNOBTAINIUM_ALLOY_TOOLS);
        builder(BlockTags.INCORRECT_FOR_IRON_TOOL)
            .addOptionalTag(MythicTags.NEEDS_NETHERITE_TOOLS)
            .addOptionalTag(MythicTags.NEEDS_UNOBTAINIUM_ALLOY_TOOLS);
        builder(BlockTags.INCORRECT_FOR_DIAMOND_TOOL)
            .addOptionalTag(MythicTags.NEEDS_NETHERITE_TOOLS)
            .addOptionalTag(MythicTags.NEEDS_UNOBTAINIUM_ALLOY_TOOLS);
        builder(BlockTags.INCORRECT_FOR_NETHERITE_TOOL)
            .addOptionalTag(MythicTags.NEEDS_UNOBTAINIUM_ALLOY_TOOLS);

        MaterialHelper.BLOCK_SET_MAP.values().forEach((blockSet) -> {
            addTags(blockSet.storage());
            addTags(blockSet.ore());
            addTags(blockSet.rawStorage());
            addTags(blockSet.anvil());

            blockSet.oreVariants().values().forEach(this::addTags);
        });

        builder(BlockTags.MINEABLE_WITH_PICKAXE)
            .add(MythicResourceKeys.CARMOT_BELL)
            .add(MythicResourceKeys.ENCHANTED_MIDAS_GOLD_BLOCK)
            .addOptionalTag(MythicTags.NUKE_CORES)
            .addOptionalTag(MythicTags.CONDUIT_BLOCKS);
    }

    private void addTags(@Nullable BlockWithMiningLevel blockRecord) {
        if (blockRecord == null) return;
        // mining level
        builder(blockRecord.miningLevel()).add(blockRecord.blockKey());
        // mineable tag
        builder(BlockTags.MINEABLE_WITH_PICKAXE).add(blockRecord.blockKey());
    }
}
