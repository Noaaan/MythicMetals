package nourl.mythicmetals.data;

import io.wispforest.owo.util.ReflectionUtils;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalBlockTags;
import net.minecraft.registry.RegistryWrapper;
import nourl.mythicmetals.block.BlockSet;
import nourl.mythicmetals.block.MythicBlocks;
import java.util.concurrent.CompletableFuture;

public class MythicBlockTagProvider extends FabricTagProvider.BlockTagProvider {

    public MythicBlockTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @SuppressWarnings("UnstableApiUsage")
    @Override
    protected void configure(RegistryWrapper.WrapperLookup arg) {
        ReflectionUtils.iterateAccessibleStaticFields(MythicBlocks.class, BlockSet.class, (blockSet, name, field) -> {
            var modOreTag = MythicMetalsData.createModBlockTag("ores");
            var commonOreTag = ConventionalBlockTags.ORES;

            if (blockSet.getOre() != null) {
                var string = "ores/" + name;
                var modTag = MythicMetalsData.createModBlockTag(string);
                var commonTag = MythicMetalsData.createCommonBlockTag(string);
                var tagBuilder = getOrCreateTagBuilder(modTag).add(blockSet.getOre());
                getOrCreateTagBuilder(commonTag).addTag(modTag);

                if (!blockSet.getOreVariants().isEmpty()) {
                    blockSet.getOreVariants().forEach(tagBuilder::add);
                }
                getOrCreateTagBuilder(modOreTag).addTag(modTag);
                getOrCreateTagBuilder(commonOreTag).addTag(modTag);
            }

            if (blockSet.getOreStorageBlock() != null) {
                var string = "storage_blocks/raw_" + name;
                var modTag = MythicMetalsData.createModBlockTag(string);
                var commonTag = MythicMetalsData.createCommonBlockTag(string);
                getOrCreateTagBuilder(modTag).add(blockSet.getOreStorageBlock());
                getOrCreateTagBuilder(commonTag).addTag(modTag);
            }

            if (blockSet.getStorageBlock() != null) {
                var string = "storage_blocks/" + name;
                var modTag = MythicMetalsData.createModBlockTag(string);
                var commonTag = MythicMetalsData.createCommonBlockTag(string);
                getOrCreateTagBuilder(modTag).add(blockSet.getStorageBlock());
                getOrCreateTagBuilder(commonTag).addTag(modTag);
            }
        });
    }
}
