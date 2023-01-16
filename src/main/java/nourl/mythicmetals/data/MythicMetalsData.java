package nourl.mythicmetals.data;

import io.wispforest.owo.util.ReflectionUtils;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import nourl.mythicmetals.blocks.BlockSet;
import nourl.mythicmetals.blocks.MythicBlocks;
import nourl.mythicmetals.item.ItemSet;
import nourl.mythicmetals.item.MythicItems;
import nourl.mythicmetals.misc.RegistryHelper;

public class MythicMetalsData implements DataGeneratorEntrypoint {

    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        fabricDataGenerator.addProvider(new MythicBlockDataProvider(fabricDataGenerator));
        fabricDataGenerator.addProvider(new MythicItemDataProvider(fabricDataGenerator));
    }

    public static class MythicBlockDataProvider extends FabricTagProvider.BlockTagProvider {

        public MythicBlockDataProvider(FabricDataGenerator dataGenerator) {
            super(dataGenerator);
        }

        @SuppressWarnings("UnstableApiUsage")
        @Override
        protected void generateTags() {
            ReflectionUtils.iterateAccessibleStaticFields(MythicBlocks.class, BlockSet.class, (blockSet, name, field) -> {
                if (blockSet.getOre() != null) {
                    var string = name + "_ores";
                    var modTag = createModBlockTag(string);
                    var commonTag = createCommonBlockTag(string);
                    var tagBuilder = getOrCreateTagBuilder(modTag).add(blockSet.getOre());
                    getOrCreateTagBuilder(commonTag).addTag(modTag);

                    if (!blockSet.getOreVariants().isEmpty()) {
                        blockSet.getOreVariants().forEach(tagBuilder::add);
                    }
                }

                if (blockSet.getOreStorageBlock() != null) {
                    var string = "raw_" + name + "_blocks";
                    var modTag = createModBlockTag(string);
                    var commonTag = createCommonBlockTag(string);
                    getOrCreateTagBuilder(modTag).add(blockSet.getOreStorageBlock());
                    getOrCreateTagBuilder(commonTag).addTag(modTag);
                }

                if (blockSet.getStorageBlock() != null) {
                    var string = name + "_blocks";
                    var modTag = createModBlockTag(string);
                    var commonTag = createCommonBlockTag(string);
                    getOrCreateTagBuilder(modTag).add(blockSet.getStorageBlock());
                    getOrCreateTagBuilder(commonTag).addTag(modTag);
                }

            });
        }
    }

    public static class MythicItemDataProvider extends FabricTagProvider.ItemTagProvider {

        public MythicItemDataProvider(FabricDataGenerator dataGenerator) {
            super(dataGenerator);
        }

        @SuppressWarnings("UnstableApiUsage")
        @Override
        protected void generateTags() {
            ReflectionUtils.iterateAccessibleStaticFields(MythicBlocks.class, BlockSet.class, (blockSet, name, field) -> {
                if (blockSet.getOre() != null) {
                    var string = name + "_ores";
                    var modTag = createModItemTag(string);
                    var commonTag = createCommonItemTag(string);
                    var tagBuilder = getOrCreateTagBuilder(modTag).add(blockSet.getOre().asItem());
                    getOrCreateTagBuilder(commonTag).addTag(modTag);

                    if (!blockSet.getOreVariants().isEmpty()) {
                        blockSet.getOreVariants().forEach(block -> tagBuilder.add(block.asItem()));
                    }
                }

                if (blockSet.getOreStorageBlock() != null) {
                    var string = "raw_" + name + "_blocks";
                    var modTag = createModItemTag(string);
                    var commonTag = createCommonItemTag(string);
                    getOrCreateTagBuilder(modTag).add(blockSet.getOreStorageBlock().asItem());
                    getOrCreateTagBuilder(commonTag).addTag(modTag);
                }

                if (blockSet.getStorageBlock() != null) {
                    var string = name + "_blocks";
                    var modTag = createModItemTag(string);
                    var commonTag = createCommonItemTag(string);
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
                    var modTag = createModItemTag(string);
                    var commonTag = createCommonItemTag(string);
                    getOrCreateTagBuilder(modTag).add(itemSet.getIngot());
                    getOrCreateTagBuilder(commonTag).addTag(modTag);
                }

                if (itemSet.getRawOre() != null) {
                    var string = "raw_" + name + "_ores";
                    var modTag = createModItemTag(string);
                    var commonTag = createCommonItemTag(string);
                    getOrCreateTagBuilder(modTag).add(itemSet.getRawOre());
                    getOrCreateTagBuilder(commonTag).addTag(modTag);
                }
            });

            ReflectionUtils.iterateAccessibleStaticFields(MythicItems.Mats.class, Item.class, (item, name, field) -> {
                var modTag = createModItemTag(name);
                var commonTag = createCommonItemTag(name);
                getOrCreateTagBuilder(modTag).add(item);
                getOrCreateTagBuilder(commonTag).addTag(modTag);
            });
        }
    }

    public static TagKey<Item> createCommonItemTag(String path) {
        return TagKey.of(Registry.ITEM_KEY, new Identifier("c", path));
    }

    public static TagKey<Item> createModItemTag(String path) {
        return TagKey.of(Registry.ITEM_KEY, RegistryHelper.id(path));
    }

    public static TagKey<Block> createCommonBlockTag(String path) {
        return TagKey.of(Registry.BLOCK_KEY, new Identifier("c", path));
    }

    public static TagKey<Block> createModBlockTag(String path) {
        return TagKey.of(Registry.BLOCK_KEY, RegistryHelper.id(path));
    }
}
