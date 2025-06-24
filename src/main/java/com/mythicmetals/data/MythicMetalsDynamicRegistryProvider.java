package com.mythicmetals.data;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import java.util.concurrent.CompletableFuture;

public class MythicMetalsDynamicRegistryProvider extends FabricDynamicRegistryProvider {
    public MythicMetalsDynamicRegistryProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup registries, Entries entries) {
        entries.add(MythicOreFeatures.ADAMANTITE, registries.getWrapperOrThrow(RegistryKeys.PLACED_FEATURE).getOrThrow(MythicOreFeatures.ADAMANTITE).value());
        entries.add(MythicOreFeatures.AQUARIUM, registries.getWrapperOrThrow(RegistryKeys.PLACED_FEATURE).getOrThrow(MythicOreFeatures.AQUARIUM).value());
        entries.add(MythicOreFeatures.BANGLUM, registries.getWrapperOrThrow(RegistryKeys.PLACED_FEATURE).getOrThrow(MythicOreFeatures.BANGLUM).value());
        entries.add(MythicOreFeatures.NETHER_BANGLUM, registries.getWrapperOrThrow(RegistryKeys.PLACED_FEATURE).getOrThrow(MythicOreFeatures.NETHER_BANGLUM).value());
        entries.add(MythicOreFeatures.CARMOT, registries.getWrapperOrThrow(RegistryKeys.PLACED_FEATURE).getOrThrow(MythicOreFeatures.CARMOT).value());
        entries.add(MythicOreFeatures.CALCITE_KYBER, registries.getWrapperOrThrow(RegistryKeys.PLACED_FEATURE).getOrThrow(MythicOreFeatures.CALCITE_KYBER).value());
        entries.add(MythicOreFeatures.END_STARRITE, registries.getWrapperOrThrow(RegistryKeys.PLACED_FEATURE).getOrThrow(MythicOreFeatures.END_STARRITE).value());
        entries.add(MythicOreFeatures.KYBER, registries.getWrapperOrThrow(RegistryKeys.PLACED_FEATURE).getOrThrow(MythicOreFeatures.KYBER).value());
        entries.add(MythicOreFeatures.MANGANESE, registries.getWrapperOrThrow(RegistryKeys.PLACED_FEATURE).getOrThrow(MythicOreFeatures.MANGANESE).value());
        entries.add(MythicOreFeatures.MIDAS_GOLD, registries.getWrapperOrThrow(RegistryKeys.PLACED_FEATURE).getOrThrow(MythicOreFeatures.MIDAS_GOLD).value());
        entries.add(MythicOreFeatures.MORKITE, registries.getWrapperOrThrow(RegistryKeys.PLACED_FEATURE).getOrThrow(MythicOreFeatures.MORKITE).value());
        entries.add(MythicOreFeatures.MYTHRIL, registries.getWrapperOrThrow(RegistryKeys.PLACED_FEATURE).getOrThrow(MythicOreFeatures.MYTHRIL).value());
        entries.add(MythicOreFeatures.ORICHALCUM, registries.getWrapperOrThrow(RegistryKeys.PLACED_FEATURE).getOrThrow(MythicOreFeatures.ORICHALCUM).value());
        entries.add(MythicOreFeatures.OSMIUM, registries.getWrapperOrThrow(RegistryKeys.PLACED_FEATURE).getOrThrow(MythicOreFeatures.OSMIUM).value());
        entries.add(MythicOreFeatures.PALLADIUM, registries.getWrapperOrThrow(RegistryKeys.PLACED_FEATURE).getOrThrow(MythicOreFeatures.PALLADIUM).value());
        entries.add(MythicOreFeatures.PLATINUM, registries.getWrapperOrThrow(RegistryKeys.PLACED_FEATURE).getOrThrow(MythicOreFeatures.PLATINUM).value());
        entries.add(MythicOreFeatures.PROMETHEUM, registries.getWrapperOrThrow(RegistryKeys.PLACED_FEATURE).getOrThrow(MythicOreFeatures.PROMETHEUM).value());
        entries.add(MythicOreFeatures.QUADRILLUM, registries.getWrapperOrThrow(RegistryKeys.PLACED_FEATURE).getOrThrow(MythicOreFeatures.QUADRILLUM).value());
        entries.add(MythicOreFeatures.DEEPSLATE_RUNITE, registries.getWrapperOrThrow(RegistryKeys.PLACED_FEATURE).getOrThrow(MythicOreFeatures.DEEPSLATE_RUNITE).value());
        entries.add(MythicOreFeatures.RUNITE, registries.getWrapperOrThrow(RegistryKeys.PLACED_FEATURE).getOrThrow(MythicOreFeatures.RUNITE).value());
        entries.add(MythicOreFeatures.SILVER, registries.getWrapperOrThrow(RegistryKeys.PLACED_FEATURE).getOrThrow(MythicOreFeatures.SILVER).value());
        entries.add(MythicOreFeatures.STARRITE, registries.getWrapperOrThrow(RegistryKeys.PLACED_FEATURE).getOrThrow(MythicOreFeatures.STARRITE).value());
        entries.add(MythicOreFeatures.STORMYX, registries.getWrapperOrThrow(RegistryKeys.PLACED_FEATURE).getOrThrow(MythicOreFeatures.STORMYX).value());
        entries.add(MythicOreFeatures.TIN, registries.getWrapperOrThrow(RegistryKeys.PLACED_FEATURE).getOrThrow(MythicOreFeatures.TIN).value());
        entries.add(MythicOreFeatures.UNOBTAINIUM, registries.getWrapperOrThrow(RegistryKeys.PLACED_FEATURE).getOrThrow(MythicOreFeatures.UNOBTAINIUM).value());

        entries.add(MythicOreFeatures.ORE_ADAMANTITE, registries.getWrapperOrThrow(RegistryKeys.CONFIGURED_FEATURE).getOrThrow(MythicOreFeatures.ORE_ADAMANTITE).value());
        entries.add(MythicOreFeatures.ORE_AQUARIUM, registries.getWrapperOrThrow(RegistryKeys.CONFIGURED_FEATURE).getOrThrow(MythicOreFeatures.ORE_AQUARIUM).value());
        entries.add(MythicOreFeatures.ORE_BANGLUM, registries.getWrapperOrThrow(RegistryKeys.CONFIGURED_FEATURE).getOrThrow(MythicOreFeatures.ORE_BANGLUM).value());
        entries.add(MythicOreFeatures.ORE_NETHER_BANGLUM, registries.getWrapperOrThrow(RegistryKeys.CONFIGURED_FEATURE).getOrThrow(MythicOreFeatures.ORE_NETHER_BANGLUM).value());
        entries.add(MythicOreFeatures.ORE_CARMOT, registries.getWrapperOrThrow(RegistryKeys.CONFIGURED_FEATURE).getOrThrow(MythicOreFeatures.ORE_CARMOT).value());
        entries.add(MythicOreFeatures.ORE_CALCITE_KYBER, registries.getWrapperOrThrow(RegistryKeys.CONFIGURED_FEATURE).getOrThrow(MythicOreFeatures.ORE_CALCITE_KYBER).value());
        entries.add(MythicOreFeatures.ORE_END_STARRITE, registries.getWrapperOrThrow(RegistryKeys.CONFIGURED_FEATURE).getOrThrow(MythicOreFeatures.ORE_END_STARRITE).value());
        entries.add(MythicOreFeatures.ORE_KYBER, registries.getWrapperOrThrow(RegistryKeys.CONFIGURED_FEATURE).getOrThrow(MythicOreFeatures.ORE_KYBER).value());
        entries.add(MythicOreFeatures.ORE_MANGANESE, registries.getWrapperOrThrow(RegistryKeys.CONFIGURED_FEATURE).getOrThrow(MythicOreFeatures.ORE_MANGANESE).value());
        entries.add(MythicOreFeatures.ORE_MIDAS_GOLD, registries.getWrapperOrThrow(RegistryKeys.CONFIGURED_FEATURE).getOrThrow(MythicOreFeatures.ORE_MIDAS_GOLD).value());
        entries.add(MythicOreFeatures.ORE_MORKITE, registries.getWrapperOrThrow(RegistryKeys.CONFIGURED_FEATURE).getOrThrow(MythicOreFeatures.ORE_MORKITE).value());
        entries.add(MythicOreFeatures.ORE_MYTHRIL, registries.getWrapperOrThrow(RegistryKeys.CONFIGURED_FEATURE).getOrThrow(MythicOreFeatures.ORE_MYTHRIL).value());
        entries.add(MythicOreFeatures.ORE_ORICHALCUM, registries.getWrapperOrThrow(RegistryKeys.CONFIGURED_FEATURE).getOrThrow(MythicOreFeatures.ORE_ORICHALCUM).value());
        entries.add(MythicOreFeatures.ORE_OSMIUM, registries.getWrapperOrThrow(RegistryKeys.CONFIGURED_FEATURE).getOrThrow(MythicOreFeatures.ORE_OSMIUM).value());
        entries.add(MythicOreFeatures.ORE_PALLADIUM, registries.getWrapperOrThrow(RegistryKeys.CONFIGURED_FEATURE).getOrThrow(MythicOreFeatures.ORE_PALLADIUM).value());
        entries.add(MythicOreFeatures.ORE_PLATINUM, registries.getWrapperOrThrow(RegistryKeys.CONFIGURED_FEATURE).getOrThrow(MythicOreFeatures.ORE_PLATINUM).value());
        entries.add(MythicOreFeatures.ORE_PROMETHEUM, registries.getWrapperOrThrow(RegistryKeys.CONFIGURED_FEATURE).getOrThrow(MythicOreFeatures.ORE_PROMETHEUM).value());
        entries.add(MythicOreFeatures.ORE_QUADRILLUM, registries.getWrapperOrThrow(RegistryKeys.CONFIGURED_FEATURE).getOrThrow(MythicOreFeatures.ORE_QUADRILLUM).value());
        entries.add(MythicOreFeatures.ORE_DEEPSLATE_RUNITE, registries.getWrapperOrThrow(RegistryKeys.CONFIGURED_FEATURE).getOrThrow(MythicOreFeatures.ORE_DEEPSLATE_RUNITE).value());
        entries.add(MythicOreFeatures.ORE_RUNITE, registries.getWrapperOrThrow(RegistryKeys.CONFIGURED_FEATURE).getOrThrow(MythicOreFeatures.ORE_RUNITE).value());
        entries.add(MythicOreFeatures.ORE_SILVER, registries.getWrapperOrThrow(RegistryKeys.CONFIGURED_FEATURE).getOrThrow(MythicOreFeatures.ORE_SILVER).value());
        entries.add(MythicOreFeatures.ORE_STARRITE, registries.getWrapperOrThrow(RegistryKeys.CONFIGURED_FEATURE).getOrThrow(MythicOreFeatures.ORE_STARRITE).value());
        entries.add(MythicOreFeatures.ORE_STORMYX, registries.getWrapperOrThrow(RegistryKeys.CONFIGURED_FEATURE).getOrThrow(MythicOreFeatures.ORE_STORMYX).value());
        entries.add(MythicOreFeatures.ORE_TIN, registries.getWrapperOrThrow(RegistryKeys.CONFIGURED_FEATURE).getOrThrow(MythicOreFeatures.ORE_TIN).value());
        entries.add(MythicOreFeatures.ORE_UNOBTAINIUM, registries.getWrapperOrThrow(RegistryKeys.CONFIGURED_FEATURE).getOrThrow(MythicOreFeatures.ORE_UNOBTAINIUM).value());
    }

    @Override
    public String getName() {
        return "Mythic Metals Ore Generation";
    }
}
