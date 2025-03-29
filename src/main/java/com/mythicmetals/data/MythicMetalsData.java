package com.mythicmetals.data;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryBuilder;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import com.mythicmetals.misc.RegistryHelper;

public class MythicMetalsData implements DataGeneratorEntrypoint {

    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        var data = fabricDataGenerator.createPack();
        data.addProvider(MythicBlockTagProvider::new);
        data.addProvider(MythicItemTagProvider::new);
        data.addProvider(MythicMetalsDynamicRegistryProvider::new);
        data.addProvider(MythicRecipeProvider::new);
    }

    @Override
    public void buildRegistry(RegistryBuilder registryBuilder) {
        registryBuilder.addRegistry(RegistryKeys.CONFIGURED_FEATURE, MythicOreFeatureProvider::initConfiguredFeatures);
        registryBuilder.addRegistry(RegistryKeys.PLACED_FEATURE, MythicOreFeatureProvider::initPlacedFeatures);
    }

    public static TagKey<Item> createCommonItemTag(String path) {
        return TagKey.of(RegistryKeys.ITEM, Identifier.of("c", path));
    }

    public static TagKey<Item> createModItemTag(String path) {
        return TagKey.of(RegistryKeys.ITEM, RegistryHelper.id(path));
    }

    public static TagKey<Block> createCommonBlockTag(String path) {
        return TagKey.of(RegistryKeys.BLOCK, Identifier.of("c", path));
    }

    public static TagKey<Block> createModBlockTag(String path) {
        return TagKey.of(RegistryKeys.BLOCK, RegistryHelper.id(path));
    }
}
