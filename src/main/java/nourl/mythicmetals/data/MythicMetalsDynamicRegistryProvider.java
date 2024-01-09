package nourl.mythicmetals.data;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import java.util.concurrent.CompletableFuture;

@SuppressWarnings("UnstableApiUsage")
public class MythicMetalsDynamicRegistryProvider extends FabricDynamicRegistryProvider {
    public MythicMetalsDynamicRegistryProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }
    @Override
    protected void configure(RegistryWrapper.WrapperLookup registries, Entries entries) {
        entries.add(MythicOreKeys.ADAMANTITE, registries.getWrapperOrThrow(RegistryKeys.PLACED_FEATURE).getOrThrow(MythicOreKeys.ADAMANTITE).value());
        entries.add(MythicOreKeys.AQUARIUM, registries.getWrapperOrThrow(RegistryKeys.PLACED_FEATURE).getOrThrow(MythicOreKeys.AQUARIUM).value());
        entries.add(MythicOreKeys.BANGLUM, registries.getWrapperOrThrow(RegistryKeys.PLACED_FEATURE).getOrThrow(MythicOreKeys.BANGLUM).value());
        entries.add(MythicOreKeys.NETHER_BANGLUM, registries.getWrapperOrThrow(RegistryKeys.PLACED_FEATURE).getOrThrow(MythicOreKeys.NETHER_BANGLUM).value());
        entries.add(MythicOreKeys.CARMOT, registries.getWrapperOrThrow(RegistryKeys.PLACED_FEATURE).getOrThrow(MythicOreKeys.CARMOT).value());
        entries.add(MythicOreKeys.CALCITE_KYBER, registries.getWrapperOrThrow(RegistryKeys.PLACED_FEATURE).getOrThrow(MythicOreKeys.CALCITE_KYBER).value());
        entries.add(MythicOreKeys.END_STARRITE, registries.getWrapperOrThrow(RegistryKeys.PLACED_FEATURE).getOrThrow(MythicOreKeys.END_STARRITE).value());
        entries.add(MythicOreKeys.KYBER, registries.getWrapperOrThrow(RegistryKeys.PLACED_FEATURE).getOrThrow(MythicOreKeys.KYBER).value());
        entries.add(MythicOreKeys.MANGANESE, registries.getWrapperOrThrow(RegistryKeys.PLACED_FEATURE).getOrThrow(MythicOreKeys.MANGANESE).value());
        entries.add(MythicOreKeys.MIDAS_GOLD, registries.getWrapperOrThrow(RegistryKeys.PLACED_FEATURE).getOrThrow(MythicOreKeys.MIDAS_GOLD).value());
        entries.add(MythicOreKeys.MORKITE, registries.getWrapperOrThrow(RegistryKeys.PLACED_FEATURE).getOrThrow(MythicOreKeys.MORKITE).value());
        entries.add(MythicOreKeys.MYTHRIL, registries.getWrapperOrThrow(RegistryKeys.PLACED_FEATURE).getOrThrow(MythicOreKeys.MYTHRIL).value());
        entries.add(MythicOreKeys.ORICHALCUM, registries.getWrapperOrThrow(RegistryKeys.PLACED_FEATURE).getOrThrow(MythicOreKeys.ORICHALCUM).value());
        entries.add(MythicOreKeys.OSMIUM, registries.getWrapperOrThrow(RegistryKeys.PLACED_FEATURE).getOrThrow(MythicOreKeys.OSMIUM).value());
        entries.add(MythicOreKeys.PALLADIUM, registries.getWrapperOrThrow(RegistryKeys.PLACED_FEATURE).getOrThrow(MythicOreKeys.PALLADIUM).value());
        entries.add(MythicOreKeys.PLATINUM, registries.getWrapperOrThrow(RegistryKeys.PLACED_FEATURE).getOrThrow(MythicOreKeys.PLATINUM).value());
        entries.add(MythicOreKeys.PROMETHEUM, registries.getWrapperOrThrow(RegistryKeys.PLACED_FEATURE).getOrThrow(MythicOreKeys.PROMETHEUM).value());
        entries.add(MythicOreKeys.QUADRILLUM, registries.getWrapperOrThrow(RegistryKeys.PLACED_FEATURE).getOrThrow(MythicOreKeys.QUADRILLUM).value());
        entries.add(MythicOreKeys.DEEPSLATE_RUNITE, registries.getWrapperOrThrow(RegistryKeys.PLACED_FEATURE).getOrThrow(MythicOreKeys.DEEPSLATE_RUNITE).value());
        entries.add(MythicOreKeys.RUNITE, registries.getWrapperOrThrow(RegistryKeys.PLACED_FEATURE).getOrThrow(MythicOreKeys.RUNITE).value());
        entries.add(MythicOreKeys.SILVER, registries.getWrapperOrThrow(RegistryKeys.PLACED_FEATURE).getOrThrow(MythicOreKeys.SILVER).value());
        entries.add(MythicOreKeys.STARRITE, registries.getWrapperOrThrow(RegistryKeys.PLACED_FEATURE).getOrThrow(MythicOreKeys.STARRITE).value());
        entries.add(MythicOreKeys.STORMYX, registries.getWrapperOrThrow(RegistryKeys.PLACED_FEATURE).getOrThrow(MythicOreKeys.STORMYX).value());
        entries.add(MythicOreKeys.TIN, registries.getWrapperOrThrow(RegistryKeys.PLACED_FEATURE).getOrThrow(MythicOreKeys.TIN).value());
        entries.add(MythicOreKeys.UNOBTAINIUM, registries.getWrapperOrThrow(RegistryKeys.PLACED_FEATURE).getOrThrow(MythicOreKeys.UNOBTAINIUM).value());

        entries.add(MythicOreKeys.ORE_ADAMANTITE, registries.getWrapperOrThrow(RegistryKeys.CONFIGURED_FEATURE).getOrThrow(MythicOreKeys.ORE_ADAMANTITE).value());
        entries.add(MythicOreKeys.ORE_AQUARIUM, registries.getWrapperOrThrow(RegistryKeys.CONFIGURED_FEATURE).getOrThrow(MythicOreKeys.ORE_AQUARIUM).value());
        entries.add(MythicOreKeys.ORE_BANGLUM, registries.getWrapperOrThrow(RegistryKeys.CONFIGURED_FEATURE).getOrThrow(MythicOreKeys.ORE_BANGLUM).value());
        entries.add(MythicOreKeys.ORE_NETHER_BANGLUM, registries.getWrapperOrThrow(RegistryKeys.CONFIGURED_FEATURE).getOrThrow(MythicOreKeys.ORE_NETHER_BANGLUM).value());
        entries.add(MythicOreKeys.ORE_CARMOT, registries.getWrapperOrThrow(RegistryKeys.CONFIGURED_FEATURE).getOrThrow(MythicOreKeys.ORE_CARMOT).value());
        entries.add(MythicOreKeys.ORE_CALCITE_KYBER, registries.getWrapperOrThrow(RegistryKeys.CONFIGURED_FEATURE).getOrThrow(MythicOreKeys.ORE_CALCITE_KYBER).value());
        entries.add(MythicOreKeys.ORE_END_STARRITE, registries.getWrapperOrThrow(RegistryKeys.CONFIGURED_FEATURE).getOrThrow(MythicOreKeys.ORE_END_STARRITE).value());
        entries.add(MythicOreKeys.ORE_KYBER, registries.getWrapperOrThrow(RegistryKeys.CONFIGURED_FEATURE).getOrThrow(MythicOreKeys.ORE_KYBER).value());
        entries.add(MythicOreKeys.ORE_MANGANESE, registries.getWrapperOrThrow(RegistryKeys.CONFIGURED_FEATURE).getOrThrow(MythicOreKeys.ORE_MANGANESE).value());
        entries.add(MythicOreKeys.ORE_MIDAS_GOLD, registries.getWrapperOrThrow(RegistryKeys.CONFIGURED_FEATURE).getOrThrow(MythicOreKeys.ORE_MIDAS_GOLD).value());
        entries.add(MythicOreKeys.ORE_MORKITE, registries.getWrapperOrThrow(RegistryKeys.CONFIGURED_FEATURE).getOrThrow(MythicOreKeys.ORE_MORKITE).value());
        entries.add(MythicOreKeys.ORE_MYTHRIL, registries.getWrapperOrThrow(RegistryKeys.CONFIGURED_FEATURE).getOrThrow(MythicOreKeys.ORE_MYTHRIL).value());
        entries.add(MythicOreKeys.ORE_ORICHALCUM, registries.getWrapperOrThrow(RegistryKeys.CONFIGURED_FEATURE).getOrThrow(MythicOreKeys.ORE_ORICHALCUM).value());
        entries.add(MythicOreKeys.ORE_OSMIUM, registries.getWrapperOrThrow(RegistryKeys.CONFIGURED_FEATURE).getOrThrow(MythicOreKeys.ORE_OSMIUM).value());
        entries.add(MythicOreKeys.ORE_PALLADIUM, registries.getWrapperOrThrow(RegistryKeys.CONFIGURED_FEATURE).getOrThrow(MythicOreKeys.ORE_PALLADIUM).value());
        entries.add(MythicOreKeys.ORE_PLATINUM, registries.getWrapperOrThrow(RegistryKeys.CONFIGURED_FEATURE).getOrThrow(MythicOreKeys.ORE_PLATINUM).value());
        entries.add(MythicOreKeys.ORE_PROMETHEUM, registries.getWrapperOrThrow(RegistryKeys.CONFIGURED_FEATURE).getOrThrow(MythicOreKeys.ORE_PROMETHEUM).value());
        entries.add(MythicOreKeys.ORE_QUADRILLUM, registries.getWrapperOrThrow(RegistryKeys.CONFIGURED_FEATURE).getOrThrow(MythicOreKeys.ORE_QUADRILLUM).value());
        entries.add(MythicOreKeys.ORE_DEEPSLATE_RUNITE, registries.getWrapperOrThrow(RegistryKeys.CONFIGURED_FEATURE).getOrThrow(MythicOreKeys.ORE_DEEPSLATE_RUNITE).value());
        entries.add(MythicOreKeys.ORE_RUNITE, registries.getWrapperOrThrow(RegistryKeys.CONFIGURED_FEATURE).getOrThrow(MythicOreKeys.ORE_RUNITE).value());
        entries.add(MythicOreKeys.ORE_SILVER, registries.getWrapperOrThrow(RegistryKeys.CONFIGURED_FEATURE).getOrThrow(MythicOreKeys.ORE_SILVER).value());
        entries.add(MythicOreKeys.ORE_STARRITE, registries.getWrapperOrThrow(RegistryKeys.CONFIGURED_FEATURE).getOrThrow(MythicOreKeys.ORE_STARRITE).value());
        entries.add(MythicOreKeys.ORE_STORMYX, registries.getWrapperOrThrow(RegistryKeys.CONFIGURED_FEATURE).getOrThrow(MythicOreKeys.ORE_STORMYX).value());
        entries.add(MythicOreKeys.ORE_TIN, registries.getWrapperOrThrow(RegistryKeys.CONFIGURED_FEATURE).getOrThrow(MythicOreKeys.ORE_TIN).value());
        entries.add(MythicOreKeys.ORE_UNOBTAINIUM, registries.getWrapperOrThrow(RegistryKeys.CONFIGURED_FEATURE).getOrThrow(MythicOreKeys.ORE_UNOBTAINIUM).value());
    }

    @Override
    public String getName() {
        return "Mythic Metals Ore Generation";
    }
}
