package com.mythicmetals.item;

import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

import static com.mythicmetals.misc.RegistryHelper.blockKey;
import static com.mythicmetals.misc.RegistryHelper.itemKey;

public class MythicResourceKeys {
    public static final ResourceKey<Block> AQUARIUM_GLASS = blockKey("aquarium_glass");
    public static final ResourceKey<Block> AQUARIUM_RESONATOR = blockKey("aquarium_resonator");
    public static final ResourceKey<Block> BANGLUM_NUKE_CORE = blockKey("banglum_nuke_core");
    public static final ResourceKey<Block> BANGLUM_TNT = blockKey("banglum_tnt");
    public static final ResourceKey<Item> BANGLUM_TNT_MINECART = itemKey("banglum_tnt_minecart");
    public static final ResourceKey<Block> CARMOT_BELL = blockKey("carmot_bell");
    public static final ResourceKey<Block> CARMOT_NUKE_CORE = blockKey("carmot_nuke_core");
    public static final ResourceKey<Block> ENCHANTED_MIDAS_GOLD_BLOCK = blockKey("enchanted_midas_gold_block");
    public static final ResourceKey<Item> PALLADIUM_RAIL_ITEM = itemKey("palladium_rail");
    public static final ResourceKey<Item> PALLADIUM_MINECART = itemKey("palladium_minecart");
    public static final ResourceKey<Block> QUADRILLUM_NUKE_CORE = blockKey("quadrillum_nuke_core");
    public static final ResourceKey<Block> SPONGE_NUKE_CORE = blockKey("sponge_nuke_core");

    // rare material drops
    public static final ResourceKey<Item> AQUARIUM_PEARL = itemKey("aquarium_pearl");
    public static final ResourceKey<Item> BANGLUM_CHUNK = itemKey("banglum_chunk");
    public static final ResourceKey<Item> CARMOT_STONE = itemKey("carmot_stone");
    public static final ResourceKey<Item> DURASTEEL_ENGINE = itemKey("durasteel_engine");
    public static final ResourceKey<Item> PROMETHEUM_ROSE = itemKey("prometheum_rose");
    public static final ResourceKey<Item> STORMYX_SHELL = itemKey("stormyx_shell");

    // smithing templates
    public static final ResourceKey<Item> AEGIS_SMITHING_TEMPLATE = itemKey("aegis_smithing_template");
    public static final ResourceKey<Item> CARMOT_SMITHING_TEMPLATE = itemKey("carmot_smithing_template");
    public static final ResourceKey<Item> LEGENDARY_BANGLUM_SMITHING_TEMPLATE = itemKey("legendary_banglum_smithing_template");
    public static final ResourceKey<Item> MIDAS_FOLDING_TEMPLATE = itemKey("midas_folding_template");
    public static final ResourceKey<Item> MYTHRIL_DRILL_SMITHING_TEMPLATE = itemKey("mythril_drill_smithing_template");
    public static final ResourceKey<Item> OSMIUM_CHAINMAIL_SMITHING_TEMPLATE = itemKey("osmium_chainmail_smithing_template");
    public static final ResourceKey<Item> ROYAL_MIDAS_SMITHING_TEMPLATE = itemKey("royal_midas_smithing_template");
    public static final ResourceKey<Item> TIDESINGER_SMITHING_TEMPLATE = itemKey("tidesinger_smithing_template");
    public static final ResourceKey<Item> UNOBTAINIUM_SMITHING_TEMPLATE = itemKey("unobtainium_smithing_template");
    public static final ResourceKey<Item> EMERALD_CRYSTAL = itemKey("emerald_crystal");
    public static final ResourceKey<Item> CELESTIUM_ELYTRA = itemKey("celestium_elytra");
}
