package com.mythicmetals.data.worldgen;

import com.mythicmetals.data.MythicOreBiomeTags;
import com.mythicmetals.data.MythicTags;
import io.wispforest.owo.util.ReflectionUtils;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalBiomeTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

public class MythicBiomeTagProvider extends FabricTagsProvider<Biome> {

    public MythicBiomeTagProvider(FabricPackOutput output, ResourceKey<? extends Registry<Biome>> registryKey, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registryKey, registriesFuture);
    }

    @SuppressWarnings("UnstableApiUsage")
    @Override
    protected void addTags(HolderLookup.Provider wrapperLookup) {
        ReflectionUtils.iterateAccessibleStaticFields(MythicOreBiomeTags.class, TagKey.class, (value, name, field) -> {
            Optional<TagKey<Biome>> tagOpt = ((TagKey<?>) value).cast(Registries.BIOME);
            if (tagOpt.isPresent()) {
                var tag = tagOpt.get();

                if (tag.equals(MythicTags.END_STARRITE_BIOMES)) {
                    getOrCreateRawBuilder(tag)
                        .addTag(ConventionalBiomeTags.IS_END.location());
                } else if (tag.equals(MythicTags.STORMYX_BIOMES) || tag.equals(MythicTags.NETHER_BANGLUM_BIOMES) || tag.equals(MythicTags.PALLADIUM_BIOMES)) {
                    getOrCreateRawBuilder(tag)
                        .addTag(ConventionalBiomeTags.IS_NETHER.location());
                } else if (tag.equals(MythicTags.OSMIUM_BIOMES)) {
                    getOrCreateRawBuilder(tag)
                        .addTag(ConventionalBiomeTags.IS_MOUNTAIN.location())
                        .addTag(ConventionalBiomeTags.IS_HILL.location());
                } else if (tag.equals(MythicTags.PROMETHEUM_BIOMES)) {
                    getOrCreateRawBuilder(tag)
                        .addTag(ConventionalBiomeTags.IS_JUNGLE.location())
                        .addElement(Biomes.LUSH_CAVES.identifier());
                } else if (tag.equals(MythicTags.AQUARIUM_BIOMES)) {
                    getOrCreateRawBuilder(tag)
                        .addTag(ConventionalBiomeTags.IS_AQUATIC.location());
                } else if (!tag.equals(MythicTags.MYTHIC_ORE_BIOMES)) {
                    getOrCreateRawBuilder(tag)
                        .addTag(MythicTags.MYTHIC_ORE_BIOMES.location());
                }
            }
        });
        getOrCreateRawBuilder(MythicTags.MYTHIC_ORE_BIOMES)
            .addTag(ConventionalBiomeTags.IS_OVERWORLD.location());
    }
}
