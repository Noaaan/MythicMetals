package nourl.mythicmetals.data;

import io.wispforest.owo.util.ReflectionUtils;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import nourl.mythicmetals.MythicMetals;
import nourl.mythicmetals.blocks.BlockSet;
import nourl.mythicmetals.blocks.MythicBlocks;
import nourl.mythicmetals.item.MythicItems;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@SuppressWarnings("UnstableApiUsage")
public class MythicTagProviders {

    public static class BlockTags extends FabricTagProvider.BlockTagProvider {

        public BlockTags(FabricDataGenerator dataGenerator) {
            super(dataGenerator);
        }

        @Override
        protected void generateTags() {
            ReflectionUtils.iterateAccessibleStaticFields(MythicBlocks.class, BlockSet.class, (blockSet, name, field) -> Optional.ofNullable(MythicTags.Blocks.INSTANCE.ADVANCED_METAL_TAGS.get(blockSet.getName()))
                .ifPresent(metalTagHelper -> {
                    MythicTags.MetalTagHelper<Block> commonTagHelper = metalTagHelper.createCommonVersion();

                    getOrCreateTagBuilder(metalTagHelper.block()).add(blockSet.getStorageBlock());
                    getOrCreateTagBuilder(commonTagHelper.block()).addTag(metalTagHelper.block());

                    if(metalTagHelper.oreBlock() != null) {
                        List<Block> oreBlocks = new ArrayList<>(blockSet.getOreVariants());

                        oreBlocks.add(blockSet.getOre());

                        getOrCreateTagBuilder(metalTagHelper.oreBlock()).add(oreBlocks.toArray(new Block[]{}));
                        getOrCreateTagBuilder(commonTagHelper.oreBlock()).addTag(metalTagHelper.oreBlock());
                    }

                    if(metalTagHelper.rawOreBlock() != null) {
                        getOrCreateTagBuilder(metalTagHelper.rawOreBlock()).add(blockSet.getOreStorageBlock());
                        getOrCreateTagBuilder(commonTagHelper.rawOreBlock()).addTag(metalTagHelper.rawOreBlock());
                    }
                }));
        }

        @Override
        public String getName() {
            return MythicMetals.MOD_ID;
        }
    }

    public static class ItemTags extends FabricTagProvider.ItemTagProvider {

        public ItemTags(FabricDataGenerator dataGenerator) {
            super(dataGenerator);
        }

        @Override
        protected void generateTags() {
            ReflectionUtils.iterateAccessibleStaticFields(MythicBlocks.class, BlockSet.class, (blockSet, name, field) -> Optional.ofNullable(MythicTags.Items.INSTANCE.ADVANCED_METAL_TAGS.get(blockSet.getName()))
                .ifPresent(metalTagHelper -> {
                    MythicTags.MetalTagHelper<Item> commonTagHelper = metalTagHelper.createCommonVersion();

                    getOrCreateTagBuilder(metalTagHelper.block()).add(blockSet.getStorageBlock().asItem());
                    getOrCreateTagBuilder(commonTagHelper.block()).addTag(metalTagHelper.block());

                    if(metalTagHelper.oreBlock() != null) {
                        List<Item> oreBlocks = blockSet.getOreVariants().stream().map(Block::asItem).collect(Collectors.toList());

                        oreBlocks.add(blockSet.getOre().asItem());

                        getOrCreateTagBuilder(metalTagHelper.oreBlock()).add(oreBlocks.toArray(new Item[]{}));
                        getOrCreateTagBuilder(commonTagHelper.oreBlock()).addTag(metalTagHelper.oreBlock());
                    }

                    if(metalTagHelper.rawOreBlock() != null) {
                        getOrCreateTagBuilder(metalTagHelper.rawOreBlock()).add(blockSet.getOreStorageBlock().asItem());
                        getOrCreateTagBuilder(commonTagHelper.rawOreBlock()).addTag(metalTagHelper.rawOreBlock());
                    }

                    getOrCreateTagBuilder(metalTagHelper.itemSpecficHelper().ingot()).add(MythicItems.Ingots.INGOTS.get(blockSet.getName()));
                    getOrCreateTagBuilder(commonTagHelper.itemSpecficHelper().ingot()).addTag(metalTagHelper.itemSpecficHelper().ingot());

                    if(metalTagHelper.itemSpecficHelper().rawOre() != null){
                        getOrCreateTagBuilder(metalTagHelper.itemSpecficHelper().rawOre()).add(MythicItems.RawOres.RAW_ORES.get(blockSet.getName()));
                        getOrCreateTagBuilder(commonTagHelper.itemSpecficHelper().rawOre()).addTag(metalTagHelper.itemSpecficHelper().rawOre());
                    }

                }));
        }

        @Override
        public String getName() {
            return MythicMetals.MOD_ID;
        }
    }

}
