package nourl.mythicmetals.data;

import static net.minecraft.registry.RegistryKeys.*;
import static nourl.mythicmetals.misc.RegistryHelper.id;

import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.world.biome.Biome;

public class MythicTags {

    public static final TagKey<Item> AUTO_REPAIR = TagKey.of(RegistryKeys.ITEM, id("abilities/auto_repair"));
    public static final TagKey<Item> BONUS_FORTUNE = TagKey.of(RegistryKeys.ITEM, id("abilities/bonus_fortune"));
    public static final TagKey<Item> BONUS_LOOTING = TagKey.of(RegistryKeys.ITEM, id("abilities/bonus_looting"));
    public static final TagKey<Item> MIDAS_TOUCH = TagKey.of(RegistryKeys.ITEM, id("abilities/midas_touch"));
    public static final TagKey<Item> CARMOT_STAFF_BLOCKS = TagKey.of(RegistryKeys.ITEM, id("carmot_staff_blocks"));
    public static final TagKey<Item> TIDESINGER_CORAL = TagKey.of(RegistryKeys.ITEM, id("tidesinger_coral"));

    public static final TagKey<Block> ANVILS = TagKey.of(RegistryKeys.BLOCK, id("anvils"));
    public static final TagKey<Block> BOOST_IN_LAVA = TagKey.of(RegistryKeys.BLOCK, id("boosts_in_lava"));
    public static final TagKey<Block> CARMOT_NUKE_IGNORED = TagKey.of(RegistryKeys.BLOCK, id("carmot_nuke_ignored"));
    public static final TagKey<Block> INCORRECT_FOR_UNOBTAINIUM_ALLOY_TOOLS = TagKey.of(RegistryKeys.BLOCK, id("incorrect_for_unobtainium_alloy_tools"));
    public static final TagKey<Block> NUKE_CORES = TagKey.of(RegistryKeys.BLOCK, id("nuke_cores"));
    public static final TagKey<Block> MYTHIC_ORES = TagKey.of(RegistryKeys.BLOCK, id("ores"));
    public static final TagKey<Block> SPONGABLES = TagKey.of(RegistryKeys.BLOCK, id("spongables"));

    public static final TagKey<Biome> AQUARIUM_BIOMES = TagKey.of(BIOME, id("aquarium_biomes"));
    public static final TagKey<Block> MINEABLE_MYTHRIL_DRILL = TagKey.of(RegistryKeys.BLOCK, id("mineable/mythril_drill"));
    public static final TagKey<Biome> MYTHIC_ORE_BIOMES = TagKey.of(BIOME, id("mythic_ore_biomes"));
    public static final TagKey<Biome> OSMIUM_BIOMES = TagKey.of(BIOME, id("osmium_biomes"));
    public static final TagKey<Biome> PROMETHEUM_BIOMES = TagKey.of(BIOME, id("prometheum_biomes"));

    public static final TagKey<Enchantment> SILK_TOUCH_LIKE = TagKey.of(ENCHANTMENT, id("silk_touch_like"));
    public static final TagKey<Enchantment> INCREASES_MINING_SPEED = TagKey.of(ENCHANTMENT, id("increases_mining_speed"));
    public static final TagKey<EntityType<?>> GRANTS_FIRE_RES_WHILE_RIDING = TagKey.of(ENTITY_TYPE, id("grants_fire_resistance_while_riding"));
    public static final TagKey<Item> MYTHRIL_DRILL_UPGRADES = TagKey.of(RegistryKeys.ITEM, id("mythril_drill_upgrades"));
}


