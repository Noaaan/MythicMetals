package nourl.mythicmetals.tools;

import java.util.function.Supplier;

import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;
import net.minecraft.util.Lazy;
import nourl.mythicmetals.registry.RegisterIngots;

public enum MythicMetalsToolMaterials implements ToolMaterial {
	ADAMANTITE(4, 1573, 8.0F, 5F, 16, () -> {
        return Ingredient.ofItems(RegisterIngots.Adamantite_Ingot);
    }),
	AETHERIUM(4, 1601, 8.0F, 3.0F, 13, () -> {
        return Ingredient.ofItems(RegisterIngots.Aetherium_Ingot);
    }),
	AQUARIUM(2, 300, 6.5F, 2.0F, 16, () -> {
        return Ingredient.ofItems(RegisterIngots.Aquarium_Ingot);
    }),
	ARGONIUM(3, 210, 14.0F, 1.5F, 44, () -> {
        return Ingredient.ofItems(RegisterIngots.Argonium_Ingot);
    }),
	BANGLUM(2, 251, 12.0F, 2.5F, 1, () -> {
        return Ingredient.ofItems(RegisterIngots.Banglum_Ingot);
    }),
	BRASS(1, 100, 10.0F, 1.0F, 18, () -> {
        return Ingredient.ofItems(RegisterIngots.Brass_Ingot);
    }),
	BRONZE(2, 400, 7.0F, 2.0F, 14, () -> {
        return Ingredient.ofItems(RegisterIngots.Bronze_Ingot);
    }),
	CARMOT(2, 220, 11.0F, 2.0F, 40, () -> {
        return Ingredient.ofItems(RegisterIngots.Carmot_Ingot);
    }),
	CELESTIUM(4, 1977, 9.0F, 4.0F, 24, () -> {
        return Ingredient.ofItems(RegisterIngots.Celestium_Ingot);
    }),
	COPPER(1, 125, 5.0F, 1.0F, 8, () -> {
        return Ingredient.ofItems(RegisterIngots.Copper_Ingot);
    }),
	DISCORDIUM(3, 666, 5.0F, 3.0F, 15, () -> {
        return Ingredient.ofItems(RegisterIngots.Discordium_Ingot);
    }),
	DURASTEEL(3, 1300, 7.0F, 2.0F, 10, () -> {
        return Ingredient.ofItems(RegisterIngots.Durasteel_Ingot);
    }),
	ELECTRUM(1, 200, 16.0F, 1.0F, 25, () -> {
        return Ingredient.ofItems(RegisterIngots.Electrum_Ingot);
    }),
	ETHERITE(4, 1969, 8.5F, 3.5F, 17, () -> {
        return Ingredient.ofItems(RegisterIngots.Etherite_Ingot);
    }),
	FERRITE(2, 261, 6.5F, 2.0F, 13, () -> {
        return Ingredient.ofItems(RegisterIngots.Ferrite_Ingot);
    }),
	KYBER(2, 800, 7.0F, 2.5F, 20, () -> {
        return Ingredient.ofItems(RegisterIngots.Kyber_Ingot);
    }),
	METALLURGIUM(5, 3000, 15.0F, 6.0F, 30, () -> {
        return Ingredient.ofItems(RegisterIngots.Metallurgium_Ingot);
    }),
	MIDAS_GOLD(1, 178, 12.0F, 1.0F, 22, () -> {
        return Ingredient.ofItems(RegisterIngots.Midas_Gold_Ingot);
    }),
	MYTHRIL(4, 1573, 11.0F, 4.0F, 22, () -> {
        return Ingredient.ofItems(RegisterIngots.Mythril_Ingot);
    }),
	ORICHALCUM(4, 2048, 8.0F, 4.0F, 16, () -> {
        return Ingredient.ofItems(RegisterIngots.Orichalcum_Ingot);
    }),
	OSMIUM(3, 1111, 8.0F, 3.0F, 13, () -> {
        return Ingredient.ofItems(RegisterIngots.Osmium_Ingot);
    }),
	PROMETHEUM(3, 2001, 8.5F, 3.5F, 17, () -> {
        return Ingredient.ofItems(RegisterIngots.Prometheum_Ingot);
    }),
	PLATINUM(2, 300, 7.0F, 3.0F, 20, () -> {
        return Ingredient.ofItems(RegisterIngots.Platinum_Ingot);
    }),
	QUADRILLUM(2, 531, 7.0F, 2.0F, 10, () -> {
        return Ingredient.ofItems(RegisterIngots.Quadrillum_Ingot);
    }),
	QUICKSILVER(3, 1652, 13.0F, 2.5F, 25, () -> {
        return Ingredient.ofItems(RegisterIngots.Quicksilver_Ingot);
    }),
	RUNITE(3, 1337, 6.9F, 3.3F, 13, () -> {
        return Ingredient.ofItems(RegisterIngots.Runite_Ingot);
    }),
	SLOWSILVER(2, 800, 6.0F, 2.0F, 14, () -> {
        return Ingredient.ofItems(RegisterIngots.Slowsilver_Ingot);
    }),
	STARRITE(3, 1000, 9.9F, 3.5F, 18, () -> {
        return Ingredient.ofItems(RegisterIngots.Starrite_Ingot);
    }),
	SILVER(1, 127, 4.0F, 1.0F, 20, () -> {
        return Ingredient.ofItems(RegisterIngots.Silver_Ingot);
    }),
	STEEL(3, 600, 7.0F, 2.5F, 11, () -> {
        return Ingredient.ofItems(RegisterIngots.Steel_Ingot);
    }),
	STORMYX(2, 905, 7.0F, 2.5F, 17, () -> {
        return Ingredient.ofItems(RegisterIngots.Stormyx_Ingot);
    }),
	TANTALUM(2, 689, 7.0F, 2.0F, 10, () -> {
        return Ingredient.ofItems(RegisterIngots.Tantalite_Ingot);
    }),
	TIN(1, 95, 5.0F, 1.0F, 8, () -> {
        return Ingredient.ofItems(RegisterIngots.Tin_Ingot);
    }),
	TRUESILVER(3, 1234, 8.0F, 3.5F, 16, () -> {
        return Ingredient.ofItems(RegisterIngots.Truesilver_Ingot);
    }),
	UR(3, 600, 8.1F, 3.0F, 3, () -> {
        return Ingredient.ofItems(RegisterIngots.Ur_Ingot);
    });
	
	 private final int miningLevel;
	 private final int itemDurability;
	 private final float miningSpeedMultiplier;
	 private final float attackDamage;
	 private final int enchantability;
	 private final Lazy<Ingredient> repairIngredient;
	
	MythicMetalsToolMaterials(int miningLevel, int itemDurability, float miningSpeedMultiplier, float attackDamage, int enchantability, Supplier<Ingredient> repairIngredient) {
    this.miningLevel = miningLevel;
    this.itemDurability = itemDurability;
    this.miningSpeedMultiplier = miningSpeedMultiplier;
    this.attackDamage = attackDamage;
    this.enchantability = enchantability;
    this.repairIngredient = new Lazy<>(repairIngredient);
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