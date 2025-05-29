package com.mythicmetals.item.tools;

import com.google.common.base.Suppliers;
import net.minecraft.block.Block;
import net.minecraft.item.Items;
import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.TagKey;
import com.mythicmetals.block.MythicBlocks;
import com.mythicmetals.data.MythicTags;
import com.mythicmetals.item.MythicItems;
import java.util.function.Supplier;

public enum MythicToolMaterials implements ToolMaterial {
    ADAMANTITE(4, 1024, 7.0F, 5F, 16, () -> Ingredient.ofItems(MythicItems.ADAMANTITE.getIngot())),
    AEGIS_RED(4, 2170, 8.0F, 6.0F, 25, () -> Ingredient.ofItems(MythicItems.PALLADIUM.getIngot())),
    AEGIS_WHITE(4, 2070, 10.0F, 5.0F, 25, () -> Ingredient.ofItems(MythicItems.HALLOWED.getIngot())),
    AQUARIUM(2, 455, 6.5F, 2.0F, 12, () -> Ingredient.ofItems(MythicItems.AQUARIUM.getIngot())),
    BANGLUM(2, 260, 11.0F, 2.0F, 1, () -> Ingredient.ofItems(MythicItems.BANGLUM.getIngot())),
    BRONZE(2, 354, 5.5F, 2.5F, 14, () -> Ingredient.ofItems(MythicItems.BRONZE.getIngot())),
    CARMOT(3, 1130, 11.5F, 3.0F, 42, () -> Ingredient.ofItems(MythicItems.CARMOT.getIngot())),
    CARMOT_STAFF(3, 1230, 11.5F, 2.0F, 40, () -> Ingredient.ofItems(MythicItems.CARMOT.getIngot())),
    CELESTIUM(5, 2470, 25.0F, 6.0F, 26, () -> Ingredient.ofItems(MythicItems.CELESTIUM.getIngot())),
    COPPER(1, 187, 5.0F, 1.5F, 8, () -> Ingredient.ofItems(Items.COPPER_INGOT)),
    DURASTEEL(3, 820, 7.1F, 3.5F, 12, () -> Ingredient.ofItems(MythicItems.DURASTEEL.getIngot())),
    GILDED_MIDAS_GOLD(3, 999, 13.0F, 4.0F, 30, () -> Ingredient.ofItems(MythicBlocks.MIDAS_GOLD.getStorageBlock().asItem())),
    HALLOWED(4, 1984, 12.0F, 5.0F, 20, () -> Ingredient.ofItems(MythicItems.HALLOWED.getIngot())),
    KYBER(3, 889, 7.0F, 2.5F, 20, () -> Ingredient.ofItems(MythicItems.KYBER.getIngot())),
    LEGENDARY_BANGLUM(3, 1040, 12.0F, 4.0F, 2, () -> Ingredient.ofItems(MythicItems.BANGLUM.getIngot())),
    METALLURGIUM(5, 3000, 15.0F, 8.0F, 30, () -> Ingredient.ofItems(MythicItems.METALLURGIUM.getIngot())),
    MIDAS_GOLD(3, 300, 13.0F, 3.0F, 30, () -> Ingredient.ofItems(MythicItems.MIDAS_GOLD.getIngot())),
    MYTHRIL(4, 1564, 14.3F, 3.0F, 22, () -> Ingredient.ofItems(MythicItems.MYTHRIL.getIngot())),
    MYTHRIL_DRILL(4, 1764, 27.4F, 3.0F, 20, () -> Ingredient.ofItems(MythicItems.MYTHRIL.getIngot())),
    ORICHALCUM(4, 2048, 6.0F, 4.0F, 16, () -> Ingredient.ofItems(MythicItems.ORICHALCUM.getIngot())),
    OSMIUM(3, 664, 7.0F, 3.0F, 13, () -> Ingredient.ofItems(MythicItems.OSMIUM.getIngot())),
    PALLADIUM(4, 1234, 8.0F, 3.5F, 16, () -> Ingredient.ofItems(MythicItems.PALLADIUM.getIngot())),
    PROMETHEUM(3, 1472, 6.0F, 4.0F, 15, () -> Ingredient.ofItems(MythicItems.PROMETHEUM.getIngot())),
    QUADRILLUM(2, 321, 6.0F, 2.7F, 8, () -> Ingredient.ofItems(MythicItems.QUADRILLUM.getIngot())),
    RUNITE(3, 1337, 8.9F, 3.3F, 17, () -> Ingredient.ofItems(MythicItems.RUNITE.getIngot())),
    ROYAL_MIDAS_GOLD(3, 2147, 21.0F, 5.0F, 35, () -> Ingredient.ofItems(MythicBlocks.MIDAS_GOLD.getStorageBlock().asItem())),
    STAR_PLATINUM(4, 1300, 9.0F, 4.0F, 18, () -> Ingredient.ofItems(MythicItems.STAR_PLATINUM.getIngot())),
    STEEL(3, 700, 6.5F, 3.0F, 11, () -> Ingredient.ofItems(MythicItems.STEEL.getIngot())),
    STORMYX(3, 1305, 8.5F, 3.5F, 20, () -> Ingredient.ofItems(MythicItems.STORMYX.getIngot())),
    TIDESINGER(3, 1233, 9.0F, 4.0F, 18, () -> Ingredient.ofItems(MythicItems.AQUARIUM.getIngot()));

    private final int miningLevel;
    private final int itemDurability;
    private final float miningSpeedMultiplier;
    private final float attackDamage;
    private final int enchantability;
    private final Supplier<Ingredient> repairIngredient;

    MythicToolMaterials(int miningLevel, int itemDurability, float miningSpeedMultiplier, float attackDamage, int enchantability, Supplier<Ingredient> repairIngredient) {
        this.miningLevel = miningLevel;
        this.itemDurability = itemDurability;
        this.miningSpeedMultiplier = miningSpeedMultiplier;
        this.attackDamage = attackDamage;
        this.enchantability = enchantability;
        this.repairIngredient = Suppliers.memoize(repairIngredient::get);
    }

    @Override
    public int getDurability() {
        return this.itemDurability;
    }

    @Override
    public float getMiningSpeedMultiplier() {
        return this.miningSpeedMultiplier;
    }

    @Override
    public float getAttackDamage() {
        return this.attackDamage;
    }

    @Override
    public TagKey<Block> getInverseTag() {
        return switch (this.miningLevel) {
            case 1 -> BlockTags.INCORRECT_FOR_STONE_TOOL;
            case 2 -> BlockTags.INCORRECT_FOR_IRON_TOOL;
            case 3 -> BlockTags.INCORRECT_FOR_DIAMOND_TOOL;
            case 4 -> BlockTags.INCORRECT_FOR_NETHERITE_TOOL;
            case 5 -> MythicTags.INCORRECT_FOR_UNOBTAINIUM_ALLOY_TOOLS;
            default -> throw new IllegalStateException("Unexpected value: " + this.miningLevel);
        };
    }

    @Override
    public int getEnchantability() {
        return this.enchantability;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return this.repairIngredient.get();
    }
}
