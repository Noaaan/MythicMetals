package com.mythicmetals.data;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.Block;
import java.util.concurrent.CompletableFuture;

public class MythicBlockTagProvider extends FabricTagsProvider<Block> {

    public MythicBlockTagProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, Registries.BLOCK, registriesFuture);
    }

    @SuppressWarnings("UnstableApiUsage")
    @Override
    protected void addTags(HolderLookup.Provider arg) {
        // FIXME - This is now considered deprecated. I now need lists of Identifiers for everything.
        // Data Driven Mythic Metals by 2027
//        ReflectionUtils.iterateAccessibleStaticFields(MythicBlocks.class, BlockSet.class, (blockSet, name, field) -> {
//            var modOreTag = MythicMetalsData.createModBlockTag("ores");
//            var commonOreTag = ConventionalBlockTags.ORES;
//
//            if (blockSet.getOre() != null) {
//                var string = "ores/" + name;
//                var modTag = MythicMetalsData.createModBlockTag(string);
//                var commonTag = MythicMetalsData.createCommonBlockTag(string);
//                var tagBuilder = getOrCreateRawBuilder(modTag).addElement(blockSet.getOre());
//                tag(commonTag).addTag(modTag);
//
//                if (!blockSet.getOreVariants().isEmpty()) {
//                    blockSet.getOreVariants().forEach(tagBuilder::add);
//                }
//                tag(modOreTag).addTag(modTag);
//                tag(commonOreTag).addTag(modTag);
//            }
//
//            if (blockSet.getOreStorageBlock() != null) {
//                var string = "storage_blocks/raw_" + name;
//                var modTag = MythicMetalsData.createModBlockTag(string);
//                var commonTag = MythicMetalsData.createCommonBlockTag(string);
//                getOrCreateTagBuilder(modTag).add(blockSet.getOreStorageBlock());
//                tag(commonTag).addTag(modTag);
//            }
//
//            if (blockSet.getStorageBlock() != null) {
//                var string = "storage_blocks/" + name;
//                var modTag = MythicMetalsData.createModBlockTag(string);
//                var commonTag = MythicMetalsData.createCommonBlockTag(string);
//                getOrCreateTagBuilder(modTag).add(blockSet.getStorageBlock());
//                tag(commonTag).addTag(modTag);
//            }
//        });
    }
}
