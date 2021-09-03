package nourl.mythicmetals.tools;

import com.google.common.base.Suppliers;
import net.minecraft.item.Items;
import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;
import nourl.mythicmetals.registry.RegisterItems;

import java.util.function.Supplier;

public enum ToolMaterials implements ToolMaterial {
    ADAMANTITE(4, 1024, 7.0F, 5F, 16, () -> {
        return Ingredient.ofItems(RegisterItems.ADAMANTITE_INGOT);
    }),
    AETHERIUM(4, 1307, 8.0F, 4.0F, 18, () -> {
        return Ingredient.ofItems(RegisterItems.AETHERIUM_INGOT);
    }),
    AQUARIUM(2, 300, 6.5F, 2.0F, 16, () -> {
        return Ingredient.ofItems(RegisterItems.AQUARIUM_INGOT);
    }),
    BANGLUM(2, 260, 7.0F, 2.0F, 1, () -> {
        return Ingredient.ofItems(RegisterItems.BANGLUM_INGOT);
    }),
    BRONZE(2, 524, 6.0F, 2.0F, 14, () -> {
        return Ingredient.ofItems(RegisterItems.BRONZE_INGOT);
    }),
    CARMOT(3, 730, 11.0F, 2.0F, 40, () -> {
        return Ingredient.ofItems(RegisterItems.CARMOT_INGOT);
    }),
    CELESTIUM(4, 2270, 9.9F, 6.0F, 24, () -> {
        return Ingredient.ofItems(RegisterItems.CELESTIUM_INGOT);
    }),
    COPPER(1, 125, 5.0F, 1.0F, 8, () -> {
        return Ingredient.ofItems(Items.COPPER_INGOT);
    }),
    DISCORDIUM(3, 1666, 8.0F, 3.0F, 15, () -> {
        return Ingredient.ofItems(RegisterItems.DISCORDIUM_INGOT);
    }),
    DURASTEEL(3, 800, 6.0F, 3.0F, 12, () -> {
        return Ingredient.ofItems(RegisterItems.DURASTEEL_INGOT);
    }),
    ETHERITE(3, 2017, 8.0F, 5.0F, 19, () -> {
        return Ingredient.ofItems(RegisterItems.ETHERITE_INGOT);
    }),
    HALLOWED(4, 1848, 11.0F, 5.0F, 20, () -> {
        return Ingredient.ofItems(RegisterItems.HALLOWED_INGOT);
    }),
    KYBER(2, 889, 7.0F, 2.5F, 20, () -> {
        return Ingredient.ofItems(RegisterItems.KYBER_INGOT);
    }),
    METALLURGIUM(5, 3000, 15.0F, 7.0F, 30, () -> {
        return Ingredient.ofItems(RegisterItems.METALLURGIUM_INGOT);
    }),
    MYTHRIL(4, 1024, 11.0F, 3.0F, 22, () -> {
        return Ingredient.ofItems(RegisterItems.MYTHRIL_INGOT);
    }),
    ORICHALCUM(4, 2048, 6.0F, 3.0F, 16, () -> {
        return Ingredient.ofItems(RegisterItems.ORICHALCUM_INGOT);
    }),
    OSMIUM(2, 484, 7.0F, 2.0F, 13, () -> {
        return Ingredient.ofItems(RegisterItems.OSMIUM_INGOT);
    }),
    PALLADIUM(4, 1234, 8.0F, 3.5F, 16, () -> {
        return Ingredient.ofItems(RegisterItems.PALLADIUM_INGOT);
    }),
    PROMETHEUM(2, 672, 6.0F, 2.5F, 17, () -> {
        return Ingredient.ofItems(RegisterItems.PROMETHEUM_INGOT);
    }),
    QUADRILLUM(2, 324, 5.0F, 2.0F, 10, () -> {
        return Ingredient.ofItems(RegisterItems.QUADRILLUM_INGOT);
    }),
    QUICKSILVER(4, 952, 16.0F, 3.5F, 25, () -> {
        return Ingredient.ofItems(RegisterItems.QUICKSILVER_INGOT);
    }),
    RUNITE(3, 1337, 6.9F, 3.3F, 13, () -> {
        return Ingredient.ofItems(RegisterItems.RUNITE_INGOT);
    }),
    STAR_PLATINUM(3, 1300, 8.0F, 3.0F, 18, () -> {
        return Ingredient.ofItems(RegisterItems.STAR_PLATINUM);
    }),
    SILVER(1, 79, 10.0F, 1.0F, 20, () -> {
        return Ingredient.ofItems(RegisterItems.SILVER_INGOT);
    }),
    STEEL(3, 600, 7.0F, 2.5F, 11, () -> {
        return Ingredient.ofItems(RegisterItems.STEEL_INGOT);
    }),
    STORMYX(3, 1305, 8.0F, 3.5F, 20, () -> {
        return Ingredient.ofItems(RegisterItems.STORMYX_INGOT);
    });


    private final int miningLevel;
    private final int itemDurability;
    private final float miningSpeedMultiplier;
    private final float attackDamage;
    private final int enchantability;
    private final Supplier<Ingredient> repairIngredient;

    ToolMaterials(int miningLevel, int itemDurability, float miningSpeedMultiplier, float attackDamage, int enchantability, Supplier<Ingredient> repairIngredient) {
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