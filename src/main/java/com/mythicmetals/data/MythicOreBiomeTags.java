package com.mythicmetals.data;

import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.Biome;

import static com.mythicmetals.misc.RegistryHelper.id;

public class MythicOreBiomeTags {
    public static final BiomeTag ADAMANTITE_BIOMES = BiomeTag.create("adamantite_ore_biomes", BiomesForTag.OVERWORLD);
    public static final BiomeTag AQUARIUM_BIOMES = BiomeTag.create("aquarium_ore_biomes", BiomesForTag.AQUATIC);
    public static final BiomeTag BANGLUM_BIOMES = BiomeTag.create("banglum_ore_biomes", BiomesForTag.OVERWORLD);
    public static final BiomeTag NETHER_BANGLUM_BIOMES = BiomeTag.create("nether_banglum_ore_biomes", BiomesForTag.NETHER);
    public static final BiomeTag CARMOT_BIOMES = BiomeTag.create("carmot_ore_biomes", BiomesForTag.OVERWORLD);
    public static final BiomeTag CALCITE_KYBER_BIOMES = BiomeTag.create("calcite_kyber_ore_biomes", BiomesForTag.OVERWORLD);
    public static final BiomeTag MANGANESE_BIOMES = BiomeTag.create("manganese_ore_biomes", BiomesForTag.OVERWORLD);
    public static final BiomeTag KYBER_BIOMES = BiomeTag.create("kyber_ore_biomes", BiomesForTag.OVERWORLD);
    public static final BiomeTag MORKITE_BIOMES = BiomeTag.create("morkite_ore_biomes", BiomesForTag.OVERWORLD);
    public static final BiomeTag MIDAS_GOLD_BIOMES = BiomeTag.create("midas_gold_ore_biomes", BiomesForTag.NETHER);
    public static final BiomeTag MYTHRIL_BIOMES = BiomeTag.create("mythril_ore_biomes", BiomesForTag.OVERWORLD);
    public static final BiomeTag ORICHALCUM_BIOMES = BiomeTag.create("orichalcum_ore_biomes", BiomesForTag.OVERWORLD);
    public static final BiomeTag OSMIUM_BIOMES = BiomeTag.create("osmium_ore_biomes", BiomesForTag.MOUNTAIN);
    public static final BiomeTag PALLADIUM_BIOMES = BiomeTag.create("palladium_ore_biomes", BiomesForTag.NETHER);
    public static final BiomeTag PLATINUM_BIOMES = BiomeTag.create("platinum_ore_biomes", BiomesForTag.OVERWORLD);
    public static final BiomeTag PROMETHEUM_BIOMES = BiomeTag.create("prometheum_ore_biomes", BiomesForTag.LUSH);
    public static final BiomeTag QUADRILLUM_BIOMES = BiomeTag.create("quadrillum_ore_biomes", BiomesForTag.OVERWORLD);
    public static final BiomeTag RUNITE_BIOMES = BiomeTag.create("runite_ore_biomes", BiomesForTag.OVERWORLD);
    public static final BiomeTag DEEPSLATE_RUNITE_BIOMES = BiomeTag.create("deepslate_runite_ore_biomes", BiomesForTag.OVERWORLD);
    public static final BiomeTag SILVER_BIOMES = BiomeTag.create("silver_ore_biomes", BiomesForTag.OVERWORLD);
    public static final BiomeTag STARRITE_BIOMES = BiomeTag.create("starrite_ore_biomes", BiomesForTag.OVERWORLD);
    public static final BiomeTag END_STARRITE_BIOMES = BiomeTag.create("end_starrite_ore_biomes", BiomesForTag.END);
    public static final BiomeTag STORMYX_BIOMES = BiomeTag.create("stormyx_ore_biomes", BiomesForTag.NETHER);
    public static final BiomeTag UNOBTAINIUM_BIOMES = BiomeTag.create("unobtainium_ore_biomes", BiomesForTag.OVERWORLD);
    public static final BiomeTag TIN_BIOMES = BiomeTag.create("tin_ore_biomes", BiomesForTag.OVERWORLD);

    public enum BiomesForTag {
        OVERWORLD,
        NETHER,
        MOUNTAIN,
        AQUATIC,
        LUSH,
        END
    }

    public record BiomeTag(TagKey<Biome> tag, BiomesForTag dimension) {
        public static BiomeTag create(String name, BiomesForTag dimension) {
            return new BiomeTag(TagKey.create(Registries.BIOME, id(name)), dimension);
        }
    }
}
