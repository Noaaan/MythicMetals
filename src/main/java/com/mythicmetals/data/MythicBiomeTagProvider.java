package com.mythicmetals.data;

import io.wispforest.owo.util.ReflectionUtils;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalBiomeTags;
import net.minecraft.registry.*;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeKeys;
import java.util.concurrent.CompletableFuture;

import static com.mythicmetals.data.MythicOreBiomeTags.*;

public class MythicBiomeTagProvider extends FabricTagProvider<Biome> {

    /**
     * Constructs a new {@link FabricTagProvider} with the default computed path.
     *
     * <p>Common implementations of this class are provided.
     *
     * @param output           the {@link FabricDataOutput} instance
     * @param registriesFuture the backing registry for the tag type
     */
    public MythicBiomeTagProvider(FabricDataOutput output, RegistryKey<? extends Registry<Biome>> registryKey, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registryKey, registriesFuture);
    }

    @SuppressWarnings("UnstableApiUsage")
    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        ReflectionUtils.iterateAccessibleStaticFields(MythicOreBiomeTags.class, TagKey.class, (value, name, field) -> {
            var tag = (TagKey<Biome>) value;
            if (tag.equals(END_STARRITE_BIOMES)) {
                getOrCreateTagBuilder(tag)
                    .forceAddTag(ConventionalBiomeTags.IS_END);
            } else if (tag.equals(STORMYX_BIOMES) || tag.equals(NETHER_BANGLUM_BIOMES) || tag.equals(PALLADIUM_BIOMES)) {
                getOrCreateTagBuilder(tag)
                    .forceAddTag(ConventionalBiomeTags.IS_NETHER);
            } else if (tag.equals(OSMIUM_BIOMES)) {
                getOrCreateTagBuilder(tag)
                    .forceAddTag(ConventionalBiomeTags.IS_MOUNTAIN)
                    .forceAddTag(ConventionalBiomeTags.IS_HILL);
            } else if (tag.equals(PROMETHEUM_BIOMES)) {
                getOrCreateTagBuilder(tag)
                    .forceAddTag(ConventionalBiomeTags.IS_JUNGLE)
                    .add(BiomeKeys.LUSH_CAVES);
            } else if (tag.equals(AQUARIUM_BIOMES)) {
                getOrCreateTagBuilder(tag)
                    .forceAddTag(ConventionalBiomeTags.IS_AQUATIC);
            } else if (!tag.equals(MYTHIC_ORE_BIOMES)) {
                getOrCreateTagBuilder(tag)
                    .forceAddTag(MYTHIC_ORE_BIOMES);
            }
        });
        getOrCreateTagBuilder(MYTHIC_ORE_BIOMES)
            .forceAddTag(ConventionalBiomeTags.IS_OVERWORLD);
    }
}
