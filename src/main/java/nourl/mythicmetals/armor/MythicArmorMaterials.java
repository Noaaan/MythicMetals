package nourl.mythicmetals.armor;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.recipe.Ingredient;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Lazy;
import nourl.mythicmetals.registry.RegisterIngots;

import java.util.function.Supplier;

@SuppressWarnings("ALL")
public enum MythicArmorMaterials implements ArmorMaterial {
	ADAMANTITE("adamantite", 35, new int[]{4, 7, 9, 4}, 16, SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, 2.0F, 0.1F, () -> {
        return Ingredient.ofItems(RegisterIngots.ADAMANTITE_INGOT);
    }),
	AETHERIUM("aetherium", 36, new int[]{3, 6, 9, 3}, 13, SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, 1.0F, 0.1F, () -> {
        return Ingredient.ofItems(RegisterIngots.AETHERIUM_INGOT);
    }),
	AQUARIUM("aquarium", 20, new int[]{2, 5, 7, 2}, 16, SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, 0.0F, 0.1F, () -> {
        return Ingredient.ofItems(RegisterIngots.AQUARIUM_INGOT);
    }),
	ARGONIUM("argonium", 13, new int[]{2, 5, 6, 2}, 44, SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, 1.0F, 0.1F, () -> {
        return Ingredient.ofItems(RegisterIngots.ARGONIUM_INGOT);
    }),
	BANGLUM("banglum", 16, new int[]{2, 5, 6, 3}, 1, SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, 0.0F, 0.1F, () -> {
        return Ingredient.ofItems(RegisterIngots.BANGLUM_INGOT);
    }),
	BRASS("brass", 5, new int[]{2, 4, 5, 2}, 18, SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, 0.0F, 0.1F, () -> {
        return Ingredient.ofItems(RegisterIngots.BRASS_INGOT);
    }),
	BRONZE("bronze", 19, new int[]{2, 6, 6, 2}, 14, SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, 0.0F, 0.1F, () -> {
        return Ingredient.ofItems(RegisterIngots.BRONZE_INGOT);
    }),
	CARMOT("carmot", 24, new int[]{3, 6, 8, 3}, 40, SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, 0.0F, 0.1F, () -> {
        return Ingredient.ofItems(RegisterIngots.CARMOT_INGOT);
    }),
	CELESTIUM("celestium", 40, new int[]{4, 8, 9, 4}, 24, SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, 4.0F, 0.1F, () -> {
        return Ingredient.ofItems(RegisterIngots.CELESTIUM_INGOT);
    }),
	COPPER("copper", 5, new int[]{1, 4, 5, 2}, 8, SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, 0.0F, 0.1F, () -> {
        return Ingredient.ofItems(RegisterIngots.COPPER_INGOT);
    }),
	DISCORDIUM("discordium", 27, new int[]{3, 6, 7, 3}, 19, SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, 3.0F, 0.1F, () -> {
        return Ingredient.ofItems(RegisterIngots.DISCORDIUM_INGOT);
    }),
	DURASTEEL("durasteel", 38, new int[]{3, 7, 8, 3}, 12, SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, 2.0F, 0.1F, () -> {
        return Ingredient.ofItems(RegisterIngots.DURASTEEL_INGOT);
    }),
	ELECTRUM("electrum", 10, new int[]{2, 3, 5, 2}, 25, SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, 0.0F, 0.1F, () -> {
        return Ingredient.ofItems(RegisterIngots.ELECTRUM_INGOT);
    }),
	ETHERITE("etherite", 39, new int[]{4, 7, 9, 4}, 17, SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, 3.0F, 0.1F, () -> {
        return Ingredient.ofItems(RegisterIngots.ETHERITE_INGOT);
    }),
	HALLOWED("hallowed", 40, new int[]{4, 8, 9, 4}, 20, SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, 3.0F, 0.1F, () -> {
		return Ingredient.ofItems(RegisterIngots.HALLOWED_INGOT);
	}),
	KYBER("kyber", 15, new int[]{2, 5, 7, 2}, 20, SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, 0.0F, 0.1F, () -> {
        return Ingredient.ofItems(RegisterIngots.KYBER_INGOT);
    }),
	METALLURGIUM("metallurgium", 69, new int[]{5, 8, 12, 5}, 30, SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, 5.0F, 0.1F, () -> {
        return Ingredient.ofItems(RegisterIngots.METALLURGIUM_INGOT);
    }),
	MIDAS_GOLD("midas_gold", 10, new int[]{2, 5, 5, 2}, 22, SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, 0.0F, 0.1F, () -> {
        return Ingredient.ofItems(RegisterIngots.MIDAS_GOLD_INGOT);
    }),
	MYTHRIL("mythril", 35, new int[]{4, 8, 8, 4}, 22, SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, 2.0F, 0.1F, () -> {
        return Ingredient.ofItems(RegisterIngots.MYTHRIL_INGOT);
    }),
	ORICHALCUM("orichalcum", 42, new int[]{4, 8, 9, 4}, 16, SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, 2.0F, 0.1F, () -> {
        return Ingredient.ofItems(RegisterIngots.ORICHALCUM_INGOT);
    }),
	OSMIUM("osmium", 24, new int[]{2, 6, 7, 2}, 13, SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, 1.0F, 0.1F, () -> {
        return Ingredient.ofItems(RegisterIngots.OSMIUM_INGOT);
    }),
	PROMETHEUM("prometheum", 39, new int[]{3, 7, 8, 3}, 17, SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, 0.0F, 0.1F, () -> {
        return Ingredient.ofItems(RegisterIngots.PROMETHEUM_INGOT);
    }),
	PLATINUM("platinum", 9, new int[]{3, 4, 4, 3}, 20, SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, 2.0F, 0.1F, () -> {
        return Ingredient.ofItems(RegisterIngots.PLATINUM_INGOT);
    }),
	QUADRILLUM("quadrillum", 20, new int[]{3, 6, 7, 2}, 15, SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, 0.5F, 0.1F, () -> {
        return Ingredient.ofItems(RegisterIngots.QUADRILLUM_INGOT);
    }),
	QUICKSILVER("quicksilver", 37, new int[]{4, 8, 8, 4}, 15, SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, 2.0F, 0.1F, () -> {
        return Ingredient.ofItems(RegisterIngots.QUICKSILVER_INGOT);
    }),
	RUNITE("runite", 33, new int[]{3, 7, 7, 3}, 13, SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, 2.0F, 0.1F, () -> {
        return Ingredient.ofItems(RegisterIngots.RUNITE_INGOT);
    }),
	SLOWSILVER("slowsilver", 20, new int[]{2, 4, 4, 2}, 14, SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, 3.0F, 0.2F, () -> {
        return Ingredient.ofItems(RegisterIngots.SLOWSILVER_INGOT);
    }),
	STARRITE("starrite", 28, new int[]{3, 7, 8, 3}, 18, SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, 2.5F, 0.1F, () -> {
        return Ingredient.ofItems(RegisterIngots.STARRITE_INGOT);
    }),
	SILVER("silver", 8, new int[]{2, 4, 6, 2}, 6, SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, 0.0F, 0.1F, () -> {
        return Ingredient.ofItems(RegisterIngots.SILVER_INGOT);
    }),
	STEEL("steel", 22, new int[]{2, 6, 7, 2}, 11, SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, 1.0F, 0.1F, () -> {
        return Ingredient.ofItems(RegisterIngots.STEEL_INGOT);
    }),
	STORMYX("stormyx", 32, new int[]{3, 6, 8, 3}, 17, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2.0F, 0.1F, () -> {
        return Ingredient.ofItems(RegisterIngots.STORMYX_INGOT);
    }),
	TANTALITE("tantalite", 21, new int[]{2, 6, 7, 2}, 15, SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, 0.0F, 0.1F, () -> {
        return Ingredient.ofItems(RegisterIngots.TANTALITE_INGOT);
    }),
	TIN("tin", 3, new int[]{1, 3, 4, 2}, 8, SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, 0.0F, 0.1F, () -> {
        return Ingredient.ofItems(RegisterIngots.TIN_INGOT);
    }),
	TRUESILVER("truesilver", 28, new int[]{3, 7, 8, 3}, 16, SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, 1.5F, 0.1F, () -> {
        return Ingredient.ofItems(RegisterIngots.TRUESILVER_INGOT);
    }),
	UR("ur", 20, new int[]{2, 6, 7, 2}, 13, SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, 0.0F, 0.1F, () -> {
        return Ingredient.ofItems(RegisterIngots.UR_INGOT);
    });

	   private static final int[] BASE_DURABILITY = new int[]{13, 15, 16, 11};
	   private final String name;
	   private final int durabilityMultiplier;
	   private final int[] protectionAmounts;
	   private final int enchantability;
	   private final SoundEvent equipSound;
	   private final float toughness;
	   private final float knockbackResistance;
	   private final Lazy<Ingredient> repairIngredientSupplier;

	   private MythicArmorMaterials(String name, int durabilityMultiplier, int[] protectionAmounts, int enchantability, SoundEvent equipSound, float toughness, float knockbackResistance, Supplier<Ingredient> repairIngredientSupplier) {
	      this.name = name;
	      this.durabilityMultiplier = durabilityMultiplier;
	      this.protectionAmounts = protectionAmounts;
	      this.enchantability = enchantability;
	      this.equipSound = equipSound;
	      this.toughness = toughness;
	      this.knockbackResistance = knockbackResistance;
	      this.repairIngredientSupplier = new Lazy<>(repairIngredientSupplier);
	   }

	   public int getDurability(EquipmentSlot slot) {
	      return BASE_DURABILITY[slot.getEntitySlotId()] * this.durabilityMultiplier;
	   }

	   public int getProtectionAmount(EquipmentSlot slot) {
	      return this.protectionAmounts[slot.getEntitySlotId()];
	   }

	   public int getEnchantability() {
	      return this.enchantability;
	   }

	   public SoundEvent getEquipSound() {
	      return this.equipSound;
	   }

	   public Ingredient getRepairIngredient() {
	        return (Ingredient)this.repairIngredientSupplier.get();
	    }

	   @Environment(EnvType.CLIENT)
	   public String getName() {
	      return this.name;
	   }

	   public float getToughness() {
	      return this.toughness;
	   }

	   public float getKnockbackResistance() {
	      return this.knockbackResistance;
	   }


	}
