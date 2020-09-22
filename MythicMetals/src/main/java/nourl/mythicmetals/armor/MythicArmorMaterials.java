package nourl.mythicmetals.armor;

import java.util.function.Supplier;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.recipe.Ingredient;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Lazy;
import nourl.mythicmetals.registry.RegisterIngots;

public enum MythicArmorMaterials implements ArmorMaterial {
	ADAMANTITE("adamantite", 35, new int[]{4, 7, 9, 4}, 16, SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, 2.0F, 0.1F, () -> {
        return Ingredient.ofItems(RegisterIngots.Adamantite_Ingot);
    }),
	AETHERIUM("aetherium", 36, new int[]{3, 6, 9, 3}, 13, SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, 1.0F, 0.1F, () -> {
        return Ingredient.ofItems(RegisterIngots.Aetherium_Ingot);
    }),
	AQUARIUM("aquarium", 20, new int[]{2, 5, 7, 2}, 16, SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, 0.0F, 0.1F, () -> {
        return Ingredient.ofItems(RegisterIngots.Aquarium_Ingot);
    }),
	ARGONIUM("argonium", 13, new int[]{2, 5, 6, 2}, 44, SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, 1.0F, 0.1F, () -> {
        return Ingredient.ofItems(RegisterIngots.Argonium_Ingot);
    }),
	BANGLUM("banglum", 16, new int[]{2, 5, 6, 3}, 1, SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, 0.0F, 0.1F, () -> {
        return Ingredient.ofItems(RegisterIngots.Banglum_Ingot);
    }),
	BRASS("brass", 5, new int[]{2, 4, 5, 2}, 18, SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, 0.0F, 0.1F, () -> {
        return Ingredient.ofItems(RegisterIngots.Brass_Ingot);
    }),
	BRONZE("bronze", 19, new int[]{2, 6, 7, 2}, 14, SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, 0.0F, 0.1F, () -> {
        return Ingredient.ofItems(RegisterIngots.Bronze_Ingot);
    }),
	CARMOT("carmot", 8, new int[]{2, 4, 5, 2}, 40, SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, 0.0F, 0.1F, () -> {
        return Ingredient.ofItems(RegisterIngots.Carmot_Ingot);
    }),
	CELESTIUM("celestium", 40, new int[]{4, 8, 9, 4}, 15, SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, 4.0F, 0.1F, () -> {
        return Ingredient.ofItems(RegisterIngots.Celestium_Ingot);
    }),
	COPPER("copper", 4, new int[]{2, 2, 2, 2}, 8, SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, 0.0F, 0.1F, () -> {
        return Ingredient.ofItems(RegisterIngots.Copper_Ingot);
    }),
	DAMASCUS_STEEL("damascus_steel", 17, new int[]{2, 6, 6, 2}, 15, SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, 1.0F, 0.1F, () -> {
        return Ingredient.ofItems(RegisterIngots.Damascus_Steel_Ingot);
    }),
	DISCORDIUM("discordium", 27, new int[]{3, 6, 7, 3}, 19, SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, 3.0F, 0.1F, () -> {
        return Ingredient.ofItems(RegisterIngots.Discordium_Ingot);
    }),
	DURASTEEL("durasteel", 38, new int[]{3, 8, 9, 3}, 10, SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, 2.0F, 0.1F, () -> {
        return Ingredient.ofItems(RegisterIngots.Durasteel_Ingot);
    }),
	ELECTRUM("electrum", 9, new int[]{2, 3, 4, 2}, 25, SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, 0.0F, 0.1F, () -> {
        return Ingredient.ofItems(RegisterIngots.Electrum_Ingot);
    }),
	ETHERITE("etherite", 39, new int[]{4, 7, 9, 4}, 17, SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, 3.0F, 0.1F, () -> {
        return Ingredient.ofItems(RegisterIngots.Etherite_Ingot);
    }),
	FERRITE("ferrite", 19, new int[]{2, 6, 6, 2}, 13, SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, 0.0F, 0.1F, () -> {
        return Ingredient.ofItems(RegisterIngots.Ferrite_Ingot);
    }),
	KYBER("kyber", 15, new int[]{2, 5, 7, 2}, 20, SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, 0.0F, 0.1F, () -> {
        return Ingredient.ofItems(RegisterIngots.Kyber_Ingot);
    }),
	METALLURGIUM("metallurgium", 69, new int[]{5, 8, 12, 5}, 15, SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, 5.0F, 0.1F, () -> {
        return Ingredient.ofItems(RegisterIngots.Metallurgium_Ingot);
    }),
	MIDAS_GOLD("midas_gold", 10, new int[]{2, 5, 5, 2}, 15, SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, 0.0F, 0.1F, () -> {
        return Ingredient.ofItems(RegisterIngots.Midas_Gold_Ingot);
    }),
	MYTHRIL("mythril", 35, new int[]{4, 8, 8, 4}, 22, SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, 2.0F, 0.1F, () -> {
        return Ingredient.ofItems(RegisterIngots.Mythril_Ingot);
    }),
	ORICHALCUM("orichalcum", 42, new int[]{4, 8, 8, 4}, 16, SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, 2.0F, 0.1F, () -> {
        return Ingredient.ofItems(RegisterIngots.Orichalcum_Ingot);
    }),
	OSMIUM("osmium", 24, new int[]{2, 6, 7, 2}, 16, SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, 1.0F, 0.1F, () -> {
        return Ingredient.ofItems(RegisterIngots.Osmium_Ingot);
    }),
	PROMETHEUM("prometheum", 39, new int[]{3, 7, 8, 3}, 17, SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, 0.0F, 0.1F, () -> {
        return Ingredient.ofItems(RegisterIngots.Prometheum_Ingot);
    }),
	PLATINUM("platinum", 9, new int[]{3, 4, 4, 3}, 20, SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, 2.0F, 0.1F, () -> {
        return Ingredient.ofItems(RegisterIngots.Platinum_Ingot);
    }),
	QUADRILLUM("quadrillum", 20, new int[]{3, 6, 7, 2}, 15, SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, 0.5F, 0.1F, () -> {
        return Ingredient.ofItems(RegisterIngots.Quadrillum_Ingot);
    }),
	QUICKSILVER("quicksilver", 37, new int[]{4, 8, 8, 4}, 15, SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, 2.0F, 0.1F, () -> {
        return Ingredient.ofItems(RegisterIngots.Quicksilver_Ingot);
    }),
	RUNITE("runite", 33, new int[]{3, 7, 7, 3}, 13, SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, 2.0F, 0.1F, () -> {
        return Ingredient.ofItems(RegisterIngots.Runite_Ingot);
    }),
	SLOWSILVER("slowsilver", 30, new int[]{3, 4, 4, 3}, 14, SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, 5.0F, 0.1F, () -> {
        return Ingredient.ofItems(RegisterIngots.Slowsilver_Ingot);
    }),
	STARRITE("starrite", 28, new int[]{3, 7, 8, 3}, 18, SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, 2.5F, 0.1F, () -> {
        return Ingredient.ofItems(RegisterIngots.Starrite_Ingot);
    }),
	SILVER("silver", 5, new int[]{1, 2, 2, 1}, 6, SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, 0.0F, 0.1F, () -> {
        return Ingredient.ofItems(RegisterIngots.Silver_Ingot);
    }),
	STEEL("steel", 22, new int[]{2, 6, 7, 2}, 11, SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, 1.0F, 0.1F, () -> {
        return Ingredient.ofItems(RegisterIngots.Steel_Ingot);
    }),
	STORMYX("stormyx", 24, new int[]{2, 6, 8, 2}, 17, SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, 1.0F, 0.1F, () -> {
        return Ingredient.ofItems(RegisterIngots.Stormyx_Ingot);
    }),
	TANTALITE("tantalite", 21, new int[]{2, 6, 7, 2}, 15, SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, 0.0F, 0.1F, () -> {
        return Ingredient.ofItems(RegisterIngots.Tantalite_Ingot);
    }),
	TIN("tin", 2, new int[]{1, 1, 2, 1}, 8, SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, 1.5F, 0.1F, () -> {
        return Ingredient.ofItems(RegisterIngots.Tin_Ingot);
    }),
	TRUESILVER("truesilver", 28, new int[]{3, 7, 8, 3}, 16, SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, 0.0F, 0.1F, () -> {
        return Ingredient.ofItems(RegisterIngots.Truesilver_Ingot);
    }),
	UR("ur", 20, new int[]{3, 6, 8, 3}, 3, SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, 0.0F, 0.1F, () -> {
        return Ingredient.ofItems(RegisterIngots.Ur_Ingot);
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
