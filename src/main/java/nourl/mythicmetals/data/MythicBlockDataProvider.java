package nourl.mythicmetals.data;

import io.wispforest.owo.util.ReflectionUtils;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.registry.RegistryWrapper;
import nourl.mythicmetals.blocks.BlockSet;
import nourl.mythicmetals.blocks.MythicBlocks;

import java.util.concurrent.CompletableFuture;

public class MythicBlockDataProvider extends FabricTagProvider.BlockTagProvider {

    public MythicBlockDataProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @SuppressWarnings("UnstableApiUsage")
    @Override
    protected void configure(RegistryWrapper.WrapperLookup arg) {
        ReflectionUtils.iterateAccessibleStaticFields(MythicBlocks.class, BlockSet.class, (blockSet, name, field) -> {
            if (blockSet.getOre() != null) {
                var string = name + "_ores";
                var modTag = MythicMetalsData.createModBlockTag(string);
                var commonTag = MythicMetalsData.createCommonBlockTag(string);
                var tagBuilder = getOrCreateTagBuilder(modTag).add(blockSet.getOre());
                getOrCreateTagBuilder(commonTag).addTag(modTag);

                if (!blockSet.getOreVariants().isEmpty()) {
                    blockSet.getOreVariants().forEach(tagBuilder::add);
                }
            }

            if (blockSet.getOreStorageBlock() != null) {
                var string = "raw_" + name + "_blocks";
                var modTag = MythicMetalsData.createModBlockTag(string);
                var commonTag = MythicMetalsData.createCommonBlockTag(string);
                getOrCreateTagBuilder(modTag).add(blockSet.getOreStorageBlock());
                getOrCreateTagBuilder(commonTag).addTag(modTag);
            }

            if (blockSet.getStorageBlock() != null) {
                var string = name + "_blocks";
                var modTag = MythicMetalsData.createModBlockTag(string);
                var commonTag = MythicMetalsData.createCommonBlockTag(string);
                getOrCreateTagBuilder(modTag).add(blockSet.getStorageBlock());
                getOrCreateTagBuilder(commonTag).addTag(modTag);
            }

        });
    }
}
