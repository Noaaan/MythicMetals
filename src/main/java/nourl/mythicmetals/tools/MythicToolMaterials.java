package nourl.mythicmetals.tools;

import com.google.common.base.Suppliers;
import net.minecraft.item.Items;
import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;
import nourl.mythicmetals.blocks.MythicBlocks;
import nourl.mythicmetals.item.MythicItems;

import java.util.function.Supplier;

public enum MythicToolMaterials implements ToolMaterial {
    ADAMANTITE(4, 1024, 7.0F, 5F, 16, () -> Ingredient.ofItems(MythicItems.Ingots.ADAMANTITE_INGOT)),
    AEGIS_RED(4, 2170, 8.0F, 6.0F, 25, () -> Ingredient.ofItems(MythicItems.Ingots.PALLADIUM_INGOT)),
    AEGIS_WHITE(4, 2070, 10.0F, 5.0F, 25, () -> Ingredient.ofItems(MythicItems.Ingots.HALLOWED_INGOT)),
    AQUARIUM(2, 455, 6.5F, 2.0F, 12, () -> Ingredient.ofItems(MythicItems.Ingots.AQUARIUM_INGOT)),
    BANGLUM(2, 260, 11.0F, 2.0F, 1, () -> Ingredient.ofItems(MythicItems.Ingots.BANGLUM_INGOT)),
    BRONZE(2, 354, 5.5F, 2.5F, 14, () -> Ingredient.ofItems(MythicItems.Ingots.BRONZE_INGOT)),
    CARMOT(3, 1130, 11.5F, 3.0F, 42, () -> Ingredient.ofItems(MythicItems.Ingots.CARMOT_INGOT)),
    CELESTIUM(5, 2270, 25.0F, 6.0F, 26, () -> Ingredient.ofItems(MythicItems.Ingots.CELESTIUM_INGOT)),
    COPPER(1, 125, 4.5F, 1.0F, 8, () -> Ingredient.ofItems(Items.COPPER_INGOT)),
    DURASTEEL(3, 800, 7.1F, 3.5F, 12, () -> Ingredient.ofItems(MythicItems.Ingots.DURASTEEL_INGOT)),
    GILDED_MIDAS_GOLD(3, 999, 13.0F, 4.0F, 30, () -> Ingredient.ofItems(MythicBlocks.MIDAS_GOLD.getStorageBlock().asItem())),
    HALLOWED(4, 1984, 12.0F, 5.0F, 20, () -> Ingredient.ofItems(MythicItems.Ingots.HALLOWED_INGOT)),
    KYBER(3, 889, 7.0F, 2.5F, 20, () -> Ingredient.ofItems(MythicItems.Ingots.KYBER_INGOT)),
    LEGENDARY_BANGLUM(3, 1040, 12.0F, 4.0F, 2, () -> Ingredient.ofItems(MythicItems.BANGLUM_CHUNK)),
    METALLURGIUM(5, 3000, 15.0F, 8.0F, 30, () -> Ingredient.ofItems(MythicItems.Ingots.METALLURGIUM_INGOT)),
    MIDAS_GOLD(3, 300, 13.0F, 3.0F, 30, () -> Ingredient.ofItems(MythicItems.Ingots.MIDAS_GOLD_INGOT)),
    MYTHRIL(4, 1564, 14.3F, 3.0F, 22, () -> Ingredient.ofItems(MythicItems.Ingots.MYTHRIL_INGOT)),
    MYTHRIL_DRILL(4, 1764, 27.4F, 3.0F, 20, () -> Ingredient.ofItems(MythicItems.Ingots.MYTHRIL_INGOT)),
    ORICHALCUM(4, 2048, 6.0F, 4.0F, 16, () -> Ingredient.ofItems(MythicItems.Ingots.ORICHALCUM_INGOT)),
    OSMIUM(2, 584, 7.0F, 2.0F, 13, () -> Ingredient.ofItems(MythicItems.Ingots.OSMIUM_INGOT)),
    PALLADIUM(4, 1234, 8.0F, 3.5F, 16, () -> Ingredient.ofItems(MythicItems.Ingots.PALLADIUM_INGOT)),
    PROMETHEUM(3, 1472, 6.0F, 4.0F, 15, () -> Ingredient.ofItems(MythicItems.Ingots.PROMETHEUM_INGOT)),
    QUADRILLUM(2, 321, 5.0F, 2.5F, 8, () -> Ingredient.ofItems(MythicItems.Ingots.QUADRILLUM_INGOT)),
    RUNITE(3, 1337, 8.9F, 3.3F, 17, () -> Ingredient.ofItems(MythicItems.Ingots.RUNITE_INGOT)),
    STAR_PLATINUM(4, 1300, 9.0F, 4.0F, 18, () -> Ingredient.ofItems(MythicItems.Ingots.STAR_PLATINUM)),
    STEEL(2, 600, 6.5F, 2.5F, 12, () -> Ingredient.ofItems(MythicItems.Ingots.STEEL_INGOT)),
    STORMYX(3, 1305, 8.5F, 3.5F, 20, () -> Ingredient.ofItems(MythicItems.Ingots.STORMYX_INGOT));

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
    public int getMiningLevel() {
        return this.miningLevel;
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
