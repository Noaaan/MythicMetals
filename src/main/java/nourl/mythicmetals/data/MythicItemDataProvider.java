package nourl.mythicmetals.data;

import io.wispforest.owo.util.ReflectionUtils;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryWrapper;
import nourl.mythicmetals.blocks.BlockSet;
import nourl.mythicmetals.blocks.MythicBlocks;
import nourl.mythicmetals.item.ItemSet;
import nourl.mythicmetals.item.MythicItems;

import java.util.concurrent.CompletableFuture;

public class MythicItemDataProvider extends FabricTagProvider.ItemTagProvider {

    public MythicItemDataProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture) {
        super(output, completableFuture);
    }

    @SuppressWarnings("UnstableApiUsage")
    @Override
    protected void configure(RegistryWrapper.WrapperLookup arg) {
        ReflectionUtils.iterateAccessibleStaticFields(MythicBlocks.class, BlockSet.class, (blockSet, name, field) -> {
            if (blockSet.getOre() != null) {
                var string = name + "_ores";
                var modTag = MythicMetalsData.createModItemTag(string);
                var commonTag = MythicMetalsData.createCommonItemTag(string);
                var tagBuilder = getOrCreateTagBuilder(modTag).add(blockSet.getOre().asItem());
                getOrCreateTagBuilder(commonTag).addTag(modTag);

                if (!blockSet.getOreVariants().isEmpty()) {
                    blockSet.getOreVariants().forEach(block -> tagBuilder.add(block.asItem()));
                }
            }

            if (blockSet.getOreStorageBlock() != null) {
                var string = "raw_" + name + "_blocks";
                var modTag = MythicMetalsData.createModItemTag(string);
                var commonTag = MythicMetalsData.createCommonItemTag(string);
                getOrCreateTagBuilder(modTag).add(blockSet.getOreStorageBlock().asItem());
                getOrCreateTagBuilder(commonTag).addTag(modTag);
            }

            if (blockSet.getStorageBlock() != null) {
                var string = name + "_blocks";
                var modTag = MythicMetalsData.createModItemTag(string);
                var commonTag = MythicMetalsData.createCommonItemTag(string);
                getOrCreateTagBuilder(modTag).add(blockSet.getStorageBlock().asItem());
                getOrCreateTagBuilder(commonTag).addTag(modTag);
            }
        });

        ReflectionUtils.iterateAccessibleStaticFields(MythicItems.class, ItemSet.class, (itemSet, name, field) -> {
            if (itemSet.getIngot() != null) {
                var string = name + "_ingots";
                // Star Platinum is explicitly named, so this is for handling that edge case
                if (itemSet.equals(MythicItems.STAR_PLATINUM)) {
                    string = name;
                }
                var modTag = MythicMetalsData.createModItemTag(string);
                var commonTag = MythicMetalsData.createCommonItemTag(string);
                getOrCreateTagBuilder(modTag).add(itemSet.getIngot());
                getOrCreateTagBuilder(commonTag).addTag(modTag);
            }

            if (itemSet.getRawOre() != null) {
                var string = "raw_" + name + "_ores";
                var modTag = MythicMetalsData.createModItemTag(string);
                var commonTag = MythicMetalsData.createCommonItemTag(string);
                getOrCreateTagBuilder(modTag).add(itemSet.getRawOre());
                getOrCreateTagBuilder(commonTag).addTag(modTag);
            }
        });

        ReflectionUtils.iterateAccessibleStaticFields(MythicItems.Mats.class, Item.class, (item, name, field) -> {
            var modTag = MythicMetalsData.createModItemTag(name);
            var commonTag = MythicMetalsData.createCommonItemTag(name);
            getOrCreateTagBuilder(modTag).add(item);
            getOrCreateTagBuilder(commonTag).addTag(modTag);
        });
    }
}
