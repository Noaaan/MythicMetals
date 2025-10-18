package com.mythicmetals.item.tools;

import com.google.common.base.Suppliers;
import com.mythicmetals.block.MythicBlocks;
import com.mythicmetals.data.MythicMetalsData;
import com.mythicmetals.data.MythicTags;
import com.mythicmetals.item.MythicItems;
import com.mythicmetals.misc.RegistryHelper;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalItemTags;
import net.minecraft.block.Block;
import net.minecraft.item.Items;
import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import java.util.function.Supplier;

public class MythicToolMaterials {
    public static final ToolMaterial ADAMANTITE  = new ToolMaterial(getInverseTag(4), 1024, 7.0f, 5f, 16, TagKey.of(RegistryKeys.ITEM, RegistryHelper.id("ingots/adamantite")));
    public static final ToolMaterial AEGIS_RED = new ToolMaterial(getInverseTag(4), 2170, 8.0F, 6.0F, 25, TagKey.of(RegistryKeys.ITEM, RegistryHelper.id("ingots/palladium")));
    public static final ToolMaterial AEGIS_WHITE = new ToolMaterial(getInverseTag(4), 2070, 10.0F, 5.0F, 25, TagKey.of(RegistryKeys.ITEM, RegistryHelper.id("ingots/hallowed")));
    public static final ToolMaterial AQUARIUM = new ToolMaterial(getInverseTag(2), 455, 6.5F, 2.0F, 12, TagKey.of(RegistryKeys.ITEM, RegistryHelper.id("ingots/aquarium")));
    public static final ToolMaterial BANGLUM = new ToolMaterial(getInverseTag(2), 260, 11.0F, 2.0F, 1, TagKey.of(RegistryKeys.ITEM, RegistryHelper.id("ingots/banglum")));
    public static final ToolMaterial BRONZE = new ToolMaterial(getInverseTag(2), 354, 5.5F, 2.5F, 14, TagKey.of(RegistryKeys.ITEM, Identifier.of("c", "ingots/bronze")));
    public static final ToolMaterial CARMOT = new ToolMaterial(getInverseTag(3), 1130, 11.5F, 3.0F, 42, TagKey.of(RegistryKeys.ITEM, RegistryHelper.id("ingots/carmot")));
    public static final ToolMaterial CELESTIUM = new ToolMaterial(getInverseTag(5), 2470, 25.0F, 6.0F, 26, TagKey.of(RegistryKeys.ITEM, RegistryHelper.id("ingots/celestium")));
    public static final ToolMaterial COPPER = new ToolMaterial(getInverseTag(1), 187, 5.0F, 1.5F, 8, ConventionalItemTags.COPPER_INGOTS);
    public static final ToolMaterial DURASTEEL = new ToolMaterial(getInverseTag(3), 820, 7.1F, 3.5F, 12, TagKey.of(RegistryKeys.ITEM, RegistryHelper.id("ingots/durasteel")));
    public static final ToolMaterial GILDED_MIDAS_GOLD = new ToolMaterial(getInverseTag(3), 999, 13.0F, 4.0F, 30, TagKey.of(RegistryKeys.ITEM, RegistryHelper.id("storage_blocks/midas_gold")));
    public static final ToolMaterial HALLOWED = new ToolMaterial(getInverseTag(4), 1984, 12.0F, 5.0F, 20, TagKey.of(RegistryKeys.ITEM, RegistryHelper.id("ingots/hallowed")));
    public static final ToolMaterial KYBER = new ToolMaterial(getInverseTag(3), 889, 7.0F, 2.5F, 20, TagKey.of(RegistryKeys.ITEM, RegistryHelper.id("ingots/kyber")));
    public static final ToolMaterial LEGENDARY_BANGLUM = new ToolMaterial(getInverseTag(3), 1040, 12.0F, 4.0F, 2, TagKey.of(RegistryKeys.ITEM, RegistryHelper.id("ingots/banglum")));
    public static final ToolMaterial METALLURGIUM = new ToolMaterial(getInverseTag(5), 3000, 15.0F, 8.0F, 30, TagKey.of(RegistryKeys.ITEM, RegistryHelper.id("ingots/metallurgium")));
    public static final ToolMaterial MIDAS_GOLD = new ToolMaterial(getInverseTag(3), 300, 13.0F, 3.0F, 30, TagKey.of(RegistryKeys.ITEM, RegistryHelper.id("ingots/midas_gold")));
    public static final ToolMaterial MYTHRIL = new ToolMaterial(getInverseTag(4), 1564, 14.3F, 3.0F, 22, TagKey.of(RegistryKeys.ITEM, RegistryHelper.id("ingots/mythril")));
    public static final ToolMaterial MYTHRIL_DRILL = new ToolMaterial(getInverseTag(4), 1764, 27.4F, 3.0F, 20, TagKey.of(RegistryKeys.ITEM, RegistryHelper.id("ingots/mythril")));
    public static final ToolMaterial ORICHALCUM = new ToolMaterial(getInverseTag(4), 2048, 6.0F, 4.0F, 16, TagKey.of(RegistryKeys.ITEM, RegistryHelper.id("ingots/orichalcum")));
    public static final ToolMaterial OSMIUM = new ToolMaterial(getInverseTag(3), 664, 7.0F, 3.0F, 13, TagKey.of(RegistryKeys.ITEM, Identifier.of("c", "ingots/osmium")));
    public static final ToolMaterial PALLADIUM = new ToolMaterial(getInverseTag(4), 1234, 8.0F, 3.5F, 16, TagKey.of(RegistryKeys.ITEM, RegistryHelper.id("ingots/palladium")));
    public static final ToolMaterial PROMETHEUM = new ToolMaterial(getInverseTag(3), 1472, 6.0F, 4.0F, 15, TagKey.of(RegistryKeys.ITEM, RegistryHelper.id("ingots/prometheum")));
    public static final ToolMaterial QUADRILLUM = new ToolMaterial(getInverseTag(2), 321, 6.0F, 2.7F, 8, TagKey.of(RegistryKeys.ITEM, RegistryHelper.id("ingots/quadrillum")));
    public static final ToolMaterial RUNITE = new ToolMaterial(getInverseTag(3), 1337, 8.9F, 3.3F, 17, TagKey.of(RegistryKeys.ITEM, RegistryHelper.id("ingots/runite")));
    public static final ToolMaterial ROYAL_MIDAS_GOLD = new ToolMaterial(getInverseTag(3), 2147, 21.0F, 5.0F, 35, TagKey.of(RegistryKeys.ITEM, RegistryHelper.id("storage_blocks/midas_gold")));
    public static final ToolMaterial STAR_PLATINUM = new ToolMaterial(getInverseTag(4), 1300, 9.0F, 4.0F, 18, TagKey.of(RegistryKeys.ITEM, RegistryHelper.id("ingots/star_platinum")));
    public static final ToolMaterial STEEL = new ToolMaterial(getInverseTag(3), 700, 6.5F, 3.0F, 11, TagKey.of(RegistryKeys.ITEM, Identifier.of("c", "ingots/steel")));
    public static final ToolMaterial STORMYX = new ToolMaterial(getInverseTag(3), 1305, 8.5F, 3.5F, 20, TagKey.of(RegistryKeys.ITEM, RegistryHelper.id("ingots/stormyx")));
    public static final ToolMaterial TIDESINGER = new ToolMaterial(getInverseTag(3), 1233, 9.0F, 4.0F, 18, TagKey.of(RegistryKeys.ITEM, RegistryHelper.id("ingots/aquarium")));

    public static TagKey<Block> getInverseTag(int level) {
        return switch (level) {
            case 1 -> BlockTags.INCORRECT_FOR_STONE_TOOL;
            case 2 -> BlockTags.INCORRECT_FOR_IRON_TOOL;
            case 3 -> BlockTags.INCORRECT_FOR_DIAMOND_TOOL;
            case 4 -> BlockTags.INCORRECT_FOR_NETHERITE_TOOL;
            case 5 -> MythicTags.INCORRECT_FOR_UNOBTAINIUM_ALLOY_TOOLS;
            default -> throw new IllegalStateException("Unexpected value: " + level);
        };
    }
}
