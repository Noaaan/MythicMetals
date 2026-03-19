package com.mythicmetals.data;

import com.mythicmetals.data.worldgen.MythicOreFeatures;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import java.util.concurrent.CompletableFuture;

public class MythicDynamicRegistryProvider extends FabricDynamicRegistryProvider {
    public MythicDynamicRegistryProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(HolderLookup.Provider registries, Entries entries) {
        entries.add(MythicOreFeatures.ADAMANTITE, registries.lookupOrThrow(Registries.PLACED_FEATURE).getOrThrow(MythicOreFeatures.ADAMANTITE).value());
        entries.add(MythicOreFeatures.AQUARIUM, registries.lookupOrThrow(Registries.PLACED_FEATURE).getOrThrow(MythicOreFeatures.AQUARIUM).value());
        entries.add(MythicOreFeatures.BANGLUM, registries.lookupOrThrow(Registries.PLACED_FEATURE).getOrThrow(MythicOreFeatures.BANGLUM).value());
        entries.add(MythicOreFeatures.NETHER_BANGLUM, registries.lookupOrThrow(Registries.PLACED_FEATURE).getOrThrow(MythicOreFeatures.NETHER_BANGLUM).value());
        entries.add(MythicOreFeatures.CARMOT, registries.lookupOrThrow(Registries.PLACED_FEATURE).getOrThrow(MythicOreFeatures.CARMOT).value());
        entries.add(MythicOreFeatures.CALCITE_KYBER, registries.lookupOrThrow(Registries.PLACED_FEATURE).getOrThrow(MythicOreFeatures.CALCITE_KYBER).value());
        entries.add(MythicOreFeatures.END_STARRITE, registries.lookupOrThrow(Registries.PLACED_FEATURE).getOrThrow(MythicOreFeatures.END_STARRITE).value());
        entries.add(MythicOreFeatures.KYBER, registries.lookupOrThrow(Registries.PLACED_FEATURE).getOrThrow(MythicOreFeatures.KYBER).value());
        entries.add(MythicOreFeatures.MANGANESE, registries.lookupOrThrow(Registries.PLACED_FEATURE).getOrThrow(MythicOreFeatures.MANGANESE).value());
        entries.add(MythicOreFeatures.MIDAS_GOLD, registries.lookupOrThrow(Registries.PLACED_FEATURE).getOrThrow(MythicOreFeatures.MIDAS_GOLD).value());
        entries.add(MythicOreFeatures.MORKITE, registries.lookupOrThrow(Registries.PLACED_FEATURE).getOrThrow(MythicOreFeatures.MORKITE).value());
        entries.add(MythicOreFeatures.MYTHRIL, registries.lookupOrThrow(Registries.PLACED_FEATURE).getOrThrow(MythicOreFeatures.MYTHRIL).value());
        entries.add(MythicOreFeatures.ORICHALCUM, registries.lookupOrThrow(Registries.PLACED_FEATURE).getOrThrow(MythicOreFeatures.ORICHALCUM).value());
        entries.add(MythicOreFeatures.OSMIUM, registries.lookupOrThrow(Registries.PLACED_FEATURE).getOrThrow(MythicOreFeatures.OSMIUM).value());
        entries.add(MythicOreFeatures.PALLADIUM, registries.lookupOrThrow(Registries.PLACED_FEATURE).getOrThrow(MythicOreFeatures.PALLADIUM).value());
        entries.add(MythicOreFeatures.PLATINUM, registries.lookupOrThrow(Registries.PLACED_FEATURE).getOrThrow(MythicOreFeatures.PLATINUM).value());
        entries.add(MythicOreFeatures.PROMETHEUM, registries.lookupOrThrow(Registries.PLACED_FEATURE).getOrThrow(MythicOreFeatures.PROMETHEUM).value());
        entries.add(MythicOreFeatures.QUADRILLUM, registries.lookupOrThrow(Registries.PLACED_FEATURE).getOrThrow(MythicOreFeatures.QUADRILLUM).value());
        entries.add(MythicOreFeatures.DEEPSLATE_RUNITE, registries.lookupOrThrow(Registries.PLACED_FEATURE).getOrThrow(MythicOreFeatures.DEEPSLATE_RUNITE).value());
        entries.add(MythicOreFeatures.RUNITE, registries.lookupOrThrow(Registries.PLACED_FEATURE).getOrThrow(MythicOreFeatures.RUNITE).value());
        entries.add(MythicOreFeatures.SILVER, registries.lookupOrThrow(Registries.PLACED_FEATURE).getOrThrow(MythicOreFeatures.SILVER).value());
        entries.add(MythicOreFeatures.STARRITE, registries.lookupOrThrow(Registries.PLACED_FEATURE).getOrThrow(MythicOreFeatures.STARRITE).value());
        entries.add(MythicOreFeatures.STORMYX, registries.lookupOrThrow(Registries.PLACED_FEATURE).getOrThrow(MythicOreFeatures.STORMYX).value());
        entries.add(MythicOreFeatures.TIN, registries.lookupOrThrow(Registries.PLACED_FEATURE).getOrThrow(MythicOreFeatures.TIN).value());
        entries.add(MythicOreFeatures.UNOBTAINIUM, registries.lookupOrThrow(Registries.PLACED_FEATURE).getOrThrow(MythicOreFeatures.UNOBTAINIUM).value());

        entries.add(MythicOreFeatures.ORE_ADAMANTITE, registries.lookupOrThrow(Registries.CONFIGURED_FEATURE).getOrThrow(MythicOreFeatures.ORE_ADAMANTITE).value());
        entries.add(MythicOreFeatures.ORE_AQUARIUM, registries.lookupOrThrow(Registries.CONFIGURED_FEATURE).getOrThrow(MythicOreFeatures.ORE_AQUARIUM).value());
        entries.add(MythicOreFeatures.ORE_BANGLUM, registries.lookupOrThrow(Registries.CONFIGURED_FEATURE).getOrThrow(MythicOreFeatures.ORE_BANGLUM).value());
        entries.add(MythicOreFeatures.ORE_NETHER_BANGLUM, registries.lookupOrThrow(Registries.CONFIGURED_FEATURE).getOrThrow(MythicOreFeatures.ORE_NETHER_BANGLUM).value());
        entries.add(MythicOreFeatures.ORE_CARMOT, registries.lookupOrThrow(Registries.CONFIGURED_FEATURE).getOrThrow(MythicOreFeatures.ORE_CARMOT).value());
        entries.add(MythicOreFeatures.ORE_CALCITE_KYBER, registries.lookupOrThrow(Registries.CONFIGURED_FEATURE).getOrThrow(MythicOreFeatures.ORE_CALCITE_KYBER).value());
        entries.add(MythicOreFeatures.ORE_END_STARRITE, registries.lookupOrThrow(Registries.CONFIGURED_FEATURE).getOrThrow(MythicOreFeatures.ORE_END_STARRITE).value());
        entries.add(MythicOreFeatures.ORE_KYBER, registries.lookupOrThrow(Registries.CONFIGURED_FEATURE).getOrThrow(MythicOreFeatures.ORE_KYBER).value());
        entries.add(MythicOreFeatures.ORE_MANGANESE, registries.lookupOrThrow(Registries.CONFIGURED_FEATURE).getOrThrow(MythicOreFeatures.ORE_MANGANESE).value());
        entries.add(MythicOreFeatures.ORE_MIDAS_GOLD, registries.lookupOrThrow(Registries.CONFIGURED_FEATURE).getOrThrow(MythicOreFeatures.ORE_MIDAS_GOLD).value());
        entries.add(MythicOreFeatures.ORE_MORKITE, registries.lookupOrThrow(Registries.CONFIGURED_FEATURE).getOrThrow(MythicOreFeatures.ORE_MORKITE).value());
        entries.add(MythicOreFeatures.ORE_MYTHRIL, registries.lookupOrThrow(Registries.CONFIGURED_FEATURE).getOrThrow(MythicOreFeatures.ORE_MYTHRIL).value());
        entries.add(MythicOreFeatures.ORE_ORICHALCUM, registries.lookupOrThrow(Registries.CONFIGURED_FEATURE).getOrThrow(MythicOreFeatures.ORE_ORICHALCUM).value());
        entries.add(MythicOreFeatures.ORE_OSMIUM, registries.lookupOrThrow(Registries.CONFIGURED_FEATURE).getOrThrow(MythicOreFeatures.ORE_OSMIUM).value());
        entries.add(MythicOreFeatures.ORE_PALLADIUM, registries.lookupOrThrow(Registries.CONFIGURED_FEATURE).getOrThrow(MythicOreFeatures.ORE_PALLADIUM).value());
        entries.add(MythicOreFeatures.ORE_PLATINUM, registries.lookupOrThrow(Registries.CONFIGURED_FEATURE).getOrThrow(MythicOreFeatures.ORE_PLATINUM).value());
        entries.add(MythicOreFeatures.ORE_PROMETHEUM, registries.lookupOrThrow(Registries.CONFIGURED_FEATURE).getOrThrow(MythicOreFeatures.ORE_PROMETHEUM).value());
        entries.add(MythicOreFeatures.ORE_QUADRILLUM, registries.lookupOrThrow(Registries.CONFIGURED_FEATURE).getOrThrow(MythicOreFeatures.ORE_QUADRILLUM).value());
        entries.add(MythicOreFeatures.ORE_DEEPSLATE_RUNITE, registries.lookupOrThrow(Registries.CONFIGURED_FEATURE).getOrThrow(MythicOreFeatures.ORE_DEEPSLATE_RUNITE).value());
        entries.add(MythicOreFeatures.ORE_RUNITE, registries.lookupOrThrow(Registries.CONFIGURED_FEATURE).getOrThrow(MythicOreFeatures.ORE_RUNITE).value());
        entries.add(MythicOreFeatures.ORE_SILVER, registries.lookupOrThrow(Registries.CONFIGURED_FEATURE).getOrThrow(MythicOreFeatures.ORE_SILVER).value());
        entries.add(MythicOreFeatures.ORE_STARRITE, registries.lookupOrThrow(Registries.CONFIGURED_FEATURE).getOrThrow(MythicOreFeatures.ORE_STARRITE).value());
        entries.add(MythicOreFeatures.ORE_STORMYX, registries.lookupOrThrow(Registries.CONFIGURED_FEATURE).getOrThrow(MythicOreFeatures.ORE_STORMYX).value());
        entries.add(MythicOreFeatures.ORE_TIN, registries.lookupOrThrow(Registries.CONFIGURED_FEATURE).getOrThrow(MythicOreFeatures.ORE_TIN).value());
        entries.add(MythicOreFeatures.ORE_UNOBTAINIUM, registries.lookupOrThrow(Registries.CONFIGURED_FEATURE).getOrThrow(MythicOreFeatures.ORE_UNOBTAINIUM).value());
    }

    @Override
    public String getName() {
        return "Mythic Metals Ore Generation";
    }
}
