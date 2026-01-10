package com.mythicmetals.data;

import static com.mythicmetals.misc.RegistryHelper.id;
import static net.minecraft.core.registries.Registries.ENCHANTMENT;
import static net.minecraft.core.registries.Registries.ENTITY_TYPE;

import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.block.Block;

public class MythicTags {

    public static final TagKey<Item> AUTO_REPAIR = TagKey.create(Registries.ITEM, id("abilities/auto_repair"));
    public static final TagKey<Item> BONUS_FORTUNE = TagKey.create(Registries.ITEM, id("abilities/bonus_fortune"));
    public static final TagKey<Item> BONUS_LOOTING = TagKey.create(Registries.ITEM, id("abilities/bonus_looting"));
    public static final TagKey<Item> MIDAS_TOUCH = TagKey.create(Registries.ITEM, id("abilities/midas_touch"));
    public static final TagKey<Item> TIDESINGER_CORAL = TagKey.create(Registries.ITEM, id("tidesinger_coral"));
    public static final TagKey<Item> MYTHRIL_DRILL_UPGRADES = TagKey.create(Registries.ITEM, id("mythril_drill_upgrades"));

    public static final TagKey<Block> ANVILS = TagKey.create(Registries.BLOCK, id("anvils"));
    public static final TagKey<Block> BOOST_IN_LAVA = TagKey.create(Registries.BLOCK, id("boosts_in_lava"));
    public static final TagKey<Block> CARMOT_NUKE_IGNORED = TagKey.create(Registries.BLOCK, id("carmot_nuke_ignored"));
    public static final TagKey<Block> INCORRECT_FOR_UNOBTAINIUM_ALLOY_TOOLS = TagKey.create(Registries.BLOCK, id("incorrect_for_unobtainium_alloy_tools"));
    public static final TagKey<Block> NUKE_CORES = TagKey.create(Registries.BLOCK, id("nuke_cores"));
    public static final TagKey<Block> MYTHIC_ORES = TagKey.create(Registries.BLOCK, id("ores"));
    public static final TagKey<Block> SPONGABLES = TagKey.create(Registries.BLOCK, id("spongables"));
    public static final TagKey<Block> MINEABLE_MYTHRIL_DRILL = TagKey.create(Registries.BLOCK, id("mineable/mythril_drill"));

    public static final TagKey<Enchantment> SILK_TOUCH_LIKE = TagKey.create(ENCHANTMENT, id("silk_touch_like"));
    public static final TagKey<Enchantment> INCREASES_MINING_SPEED = TagKey.create(ENCHANTMENT, id("increases_mining_speed"));
    public static final TagKey<EntityType<?>> GRANTS_FIRE_RES_WHILE_RIDING = TagKey.create(ENTITY_TYPE, id("grants_fire_resistance_while_riding"));
}