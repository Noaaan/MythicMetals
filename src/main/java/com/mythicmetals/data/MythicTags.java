package com.mythicmetals.data;

import com.mythicmetals.misc.RegistryHelper;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;

import static com.mythicmetals.misc.RegistryHelper.id;
import static net.minecraft.core.registries.Registries.*;

public class MythicTags {
    private MythicTags() {
    }

    // Biome Tags
    public static final TagKey<Biome> ADAMANTITE_BIOMES = TagKey.create(BIOME, RegistryHelper.id("adamantite_ore_biomes"));
    public static final TagKey<Biome> AQUARIUM_BIOMES = TagKey.create(BIOME, RegistryHelper.id("aquarium_ore_biomes"));
    public static final TagKey<Biome> BANGLUM_BIOMES = TagKey.create(BIOME, RegistryHelper.id("banglum_ore_biomes"));
    public static final TagKey<Biome> NETHER_BANGLUM_BIOMES = TagKey.create(BIOME, RegistryHelper.id("nether_banglum_ore_biomes"));
    public static final TagKey<Biome> CARMOT_BIOMES = TagKey.create(BIOME, RegistryHelper.id("carmot_ore_biomes"));
    public static final TagKey<Biome> CALCITE_KYBER_BIOMES = TagKey.create(BIOME, RegistryHelper.id("calcite_kyber_ore_biomes"));
    public static final TagKey<Biome> MANGANESE_BIOMES = TagKey.create(BIOME, RegistryHelper.id("manganese_ore_biomes"));
    public static final TagKey<Biome> KYBER_BIOMES = TagKey.create(BIOME, RegistryHelper.id("kyber_ore_biomes"));
    public static final TagKey<Biome> MORKITE_BIOMES = TagKey.create(BIOME, RegistryHelper.id("morkite_ore_biomes"));
    public static final TagKey<Biome> MIDAS_GOLD_BIOMES = TagKey.create(BIOME, RegistryHelper.id("midas_gold_ore_biomes"));
    public static final TagKey<Biome> MYTHRIL_BIOMES = TagKey.create(BIOME, RegistryHelper.id("mythril_ore_biomes"));
    public static final TagKey<Biome> ORICHALCUM_BIOMES = TagKey.create(BIOME, RegistryHelper.id("orichalcum_ore_biomes"));
    public static final TagKey<Biome> OSMIUM_BIOMES = TagKey.create(BIOME, RegistryHelper.id("osmium_ore_biomes"));
    public static final TagKey<Biome> PALLADIUM_BIOMES = TagKey.create(BIOME, RegistryHelper.id("palladium_ore_biomes"));
    public static final TagKey<Biome> PLATINUM_BIOMES = TagKey.create(BIOME, RegistryHelper.id("platinum_ore_biomes"));
    public static final TagKey<Biome> PROMETHEUM_BIOMES = TagKey.create(BIOME, RegistryHelper.id("prometheum_ore_biomes"));
    public static final TagKey<Biome> QUADRILLUM_BIOMES = TagKey.create(BIOME, RegistryHelper.id("quadrillum_ore_biomes"));
    public static final TagKey<Biome> RUNITE_BIOMES = TagKey.create(BIOME, RegistryHelper.id("runite_ore_biomes"));
    public static final TagKey<Biome> DEEPSLATE_RUNITE_BIOMES = TagKey.create(BIOME, RegistryHelper.id("deepslate_runite_ore_biomes"));
    public static final TagKey<Biome> SILVER_BIOMES = TagKey.create(BIOME, RegistryHelper.id("silver_ore_biomes"));
    public static final TagKey<Biome> STARRITE_BIOMES = TagKey.create(BIOME, RegistryHelper.id("starrite_ore_biomes"));
    public static final TagKey<Biome> END_STARRITE_BIOMES = TagKey.create(BIOME, RegistryHelper.id("end_starrite_ore_biomes"));
    public static final TagKey<Biome> STORMYX_BIOMES = TagKey.create(BIOME, RegistryHelper.id("stormyx_ore_biomes"));
    public static final TagKey<Biome> UNOBTAINIUM_BIOMES = TagKey.create(BIOME, RegistryHelper.id("unobtainium_ore_biomes"));
    public static final TagKey<Biome> TIN_BIOMES = TagKey.create(BIOME, RegistryHelper.id("tin_ore_biomes"));
    public static final TagKey<Biome> MYTHIC_ORE_BIOMES = TagKey.create(BIOME, id("mythic_ore_biomes"));

    // Item Tags
    public static final TagKey<Item> SWORDS = TagKey.create(ITEM, id("sword"));
    public static final TagKey<Item> AXES = TagKey.create(ITEM, id("axes"));
    public static final TagKey<Item> PICKAXES = TagKey.create(ITEM, id("pickaxes"));
    public static final TagKey<Item> SHOVELS = TagKey.create(ITEM, id("shovels"));
    public static final TagKey<Item> HOES = TagKey.create(ITEM, id("hoes"));
    public static final TagKey<Item> SPEARS = TagKey.create(ITEM, id("spears"));
    public static final TagKey<Item> ARMOR = TagKey.create(ITEM, id("armor"));
    public static final TagKey<Item> TOOLS = TagKey.create(ITEM, id("tools"));
    public static final TagKey<Item> NUGGETS = TagKey.create(ITEM, id("nuggets"));
    public static final TagKey<Item> EQUIPMENT = TagKey.create(ITEM, id("equipment"));
    public static final TagKey<Item> MATERIALS = TagKey.create(ITEM, id("material"));
    public static final TagKey<Item> RAW_MATERIALS = TagKey.create(ITEM, id("raw_materials"));
    public static final TagKey<Item> STORAGE_BLOCKS = TagKey.create(ITEM, id("storage_blocks"));
    public static final TagKey<Item> RARE_MATERIALS = TagKey.create(ITEM, id("rare_materials"));
    public static final TagKey<Item> FIRE_RESISTANT_ARMOR = TagKey.create(ITEM, id("fire_resistant_armor"));
    public static final TagKey<Item> AUTO_REPAIR = TagKey.create(ITEM, id("abilities/auto_repair"));
    public static final TagKey<Item> BONUS_FORTUNE = TagKey.create(ITEM, id("abilities/bonus_fortune"));
    public static final TagKey<Item> BONUS_LOOTING = TagKey.create(ITEM, id("abilities/bonus_looting"));
    public static final TagKey<Item> CARMOT_ARMOR = TagKey.create(ITEM, id("armor/carmot"));
    public static final TagKey<Item> MIDAS_TOUCH = TagKey.create(ITEM, id("abilities/midas_touch"));
    public static final TagKey<Item> TIDESINGER_CORAL = TagKey.create(ITEM, id("tidesinger_coral"));
    public static final TagKey<Item> MYTHRIL_DRILL_UPGRADES = TagKey.create(ITEM, id("mythril_drill_upgrades"));

    public static final TagKey<Block> ANVILS = TagKey.create(BLOCK, id("anvils"));
    public static final TagKey<Block> BOOST_IN_LAVA = TagKey.create(BLOCK, id("boosts_in_lava"));
    public static final TagKey<Block> CARMOT_NUKE_IGNORED = TagKey.create(BLOCK, id("carmot_nuke_ignored"));
    public static final TagKey<Block> INCORRECT_FOR_UNOBTAINIUM_ALLOY_TOOLS = TagKey.create(BLOCK, id("incorrect_for_unobtainium_alloy_tools"));
    public static final TagKey<Block> NUKE_CORES = TagKey.create(BLOCK, id("nuke_cores"));
    public static final TagKey<Block> MYTHIC_ORES = TagKey.create(BLOCK, id("ores"));
    public static final TagKey<Block> SPONGABLES = TagKey.create(BLOCK, id("spongables"));
    public static final TagKey<Block> MINEABLE_MYTHRIL_DRILL = TagKey.create(BLOCK, id("mineable/mythril_drill"));

    public static final TagKey<Enchantment> SILK_TOUCH_LIKE = TagKey.create(ENCHANTMENT, id("silk_touch_like"));
    public static final TagKey<Enchantment> INCREASES_MINING_SPEED = TagKey.create(ENCHANTMENT, id("increases_mining_speed"));
    public static final TagKey<EntityType<?>> GRANTS_FIRE_RES_WHILE_RIDING = TagKey.create(ENTITY_TYPE, id("grants_fire_resistance_while_riding"));
}