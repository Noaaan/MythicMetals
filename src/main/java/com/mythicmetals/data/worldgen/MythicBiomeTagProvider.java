package com.mythicmetals.data.worldgen;

import com.mythicmetals.data.MythicOreBiomeTags;
import com.mythicmetals.data.MythicTags;
import io.wispforest.owo.util.ReflectionUtils;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalBiomeTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import java.util.concurrent.CompletableFuture;

public class MythicBiomeTagProvider extends FabricTagsProvider<Biome> {

    public MythicBiomeTagProvider(FabricPackOutput output, ResourceKey<? extends Registry<Biome>> registryKey, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registryKey, registriesFuture);
    }

    @SuppressWarnings("UnstableApiUsage")
    @Override
    protected void addTags(HolderLookup.Provider wrapperLookup) {

        ReflectionUtils.iterateAccessibleStaticFields(MythicOreBiomeTags.class, MythicOreBiomeTags.BiomeTag.class, (biomeTag, name, field) -> {
            var tag = biomeTag.tag();
            switch (biomeTag.dimension()) {
                case OVERWORLD -> builder(tag)
                    .addOptionalTag(MythicTags.MYTHIC_ORE_BIOMES);
                case NETHER -> builder(tag)
                    .addOptionalTag(ConventionalBiomeTags.IS_NETHER);
                case MOUNTAIN -> builder(tag)
                    .addOptionalTag(ConventionalBiomeTags.IS_MOUNTAIN)
                    .addOptionalTag(ConventionalBiomeTags.IS_HILL);
                case AQUATIC -> builder(tag)
                    .addOptionalTag(ConventionalBiomeTags.IS_AQUATIC);
                case LUSH -> builder(tag)
                    .addOptionalTag(ConventionalBiomeTags.IS_JUNGLE)
                    .add(Biomes.LUSH_CAVES);
                case END -> builder(tag)
                    .addOptionalTag(ConventionalBiomeTags.IS_END);
            }
        });
        builder(MythicTags.MYTHIC_ORE_BIOMES)
            .addOptionalTag(ConventionalBiomeTags.IS_OVERWORLD);
    }
}
