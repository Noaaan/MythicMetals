package com.mythicmetals.data;

import com.mythicmetals.misc.RegistryHelper;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

@SuppressWarnings("CodeBlock2Expr")
public class MythicMetalsData implements DataGeneratorEntrypoint {

    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        var data = fabricDataGenerator.createPack();
        data.addProvider(MythicBlockTagProvider::new);
        data.addProvider(MythicItemTagProvider::new);
        data.addProvider(MythicMetalsDynamicRegistryProvider::new);
        data.addProvider(MythicRecipeProvider::new);
        data.addProvider((output, registriesFuture) -> {
            return new MythicBiomeTagProvider(output, Registries.BIOME, registriesFuture);
        });
        data.addProvider(MythicItemModelProvider::new);
    }

    @Override
    public void buildRegistry(RegistrySetBuilder registryBuilder) {
        registryBuilder.add(Registries.CONFIGURED_FEATURE, MythicOreFeatureProvider::initConfiguredFeatures);
        registryBuilder.add(Registries.PLACED_FEATURE, MythicOreFeatureProvider::initPlacedFeatures);
    }

    public static TagKey<Item> createCommonItemTag(String path) {
        return TagKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath("c", path));
    }

    public static TagKey<Item> createModItemTag(String path) {
        return TagKey.create(Registries.ITEM, RegistryHelper.id(path));
    }

    public static TagKey<Block> createCommonBlockTag(String path) {
        return TagKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath("c", path));
    }

    public static TagKey<Block> createModBlockTag(String path) {
        return TagKey.create(Registries.BLOCK, RegistryHelper.id(path));
    }
}
