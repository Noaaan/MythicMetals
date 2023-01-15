package nourl.mythicmetals.armor;

import com.google.common.base.Suppliers;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import nourl.mythicmetals.item.MythicItems;
import nourl.mythicmetals.registry.RegisterSounds;

import java.util.function.Supplier;

@SuppressWarnings("CodeBlock2Expr")
public enum MythicArmorMaterials implements ArmorMaterial {
    ADAMANTITE("adamantite", 30, new int[]{3, 6, 8, 3}, 16, SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, 2.0F, 0.0F, () -> {
        return Ingredient.ofItems(MythicItems.ADAMANTITE.getIngot());
    }),
    AQUARIUM("aquarium", 20, new int[]{1, 4, 5, 2}, 12, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 0.0F, 0.0F, () -> {
        return Ingredient.ofItems(MythicItems.AQUARIUM.getIngot());
    }),
    BANGLUM("banglum", 14, new int[]{2, 5, 6, 3}, 1, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 0.0F, 0.0F, () -> {
        return Ingredient.ofItems(MythicItems.BANGLUM.getIngot());
    }),
    BRONZE("bronze", 15, new int[]{3, 5, 6, 2}, 14, SoundEvents.ITEM_ARMOR_EQUIP_GOLD, 0.0F, 0.0F, () -> {
        return Ingredient.ofItems(MythicItems.BRONZE.getIngot());
    }),
    CARMOT("carmot", 26, new int[]{3, 6, 8, 3}, 40, SoundEvents.ITEM_ARMOR_EQUIP_GOLD, 0.0F, 0.0F, () -> {
        return Ingredient.ofItems(MythicItems.CARMOT.getIngot());
    }),
    CELESTIUM("celestium", 38, new int[]{4, 8, 10, 4}, 30, SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, 3.0F, 0.0F, () -> {
        return Ingredient.ofItems(MythicItems.CELESTIUM.getIngot());
    }),
    COPPER("copper", 9, new int[]{1, 3, 4, 2}, 8, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 0.0F, 0.0F, () -> {
        return Ingredient.ofItems(Items.COPPER_INGOT);
    }),
    DURASTEEL("durasteel", 25, new int[]{3, 6, 8, 3}, 12, SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, 1.0F, 0.0F, () -> {
        return Ingredient.ofItems(MythicItems.DURASTEEL.getIngot());
    }),
    HALLOWED("hallowed", 41, new int[]{4, 7, 9, 4}, 20, SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, 4.0F, 0.0F, () -> {
        return Ingredient.ofItems(MythicItems.HALLOWED.getIngot());
    }),
    KYBER("kyber", 21, new int[]{3, 6, 8, 3}, 20, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 0.0F, 0.0F, () -> {
        return Ingredient.ofItems(MythicItems.KYBER.getIngot());
    }),
    LEGENDARY_BANGLUM("legendary_banglum", 28, new int[]{3, 6, 8, 3}, 2, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 2.0F, 0.0F, () -> {
        return Ingredient.ofItems(MythicItems.BANGLUM.getIngot());
    }),
    METALLURGIUM("metallurgium", 69, new int[]{5, 9, 12, 5}, 30, SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, 5.0F, 0.225F, () -> {
        return Ingredient.ofItems(MythicItems.METALLURGIUM.getIngot());
    }),
    MIDAS_GOLD("midas_gold", 14, new int[]{1, 3, 5, 2}, 24, SoundEvents.ITEM_ARMOR_EQUIP_CHAIN, 0.0F, 0.0F, () -> {
        return Ingredient.ofItems(MythicItems.MIDAS_GOLD.getIngot());
    }),
    MYTHRIL("mythril", 30, new int[]{3, 6, 8, 3}, 22, SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, 2.0F, 0.0F, () -> {
        return Ingredient.ofItems(MythicItems.MYTHRIL.getIngot());
    }),
    ORICHALCUM("orichalcum", 42, new int[]{4, 7, 9, 4}, 16, SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, 3.0F, 0.1F, () -> {
        return Ingredient.ofItems(MythicItems.ORICHALCUM.getIngot());
    }),
    OSMIUM("osmium", 25, new int[]{1, 4, 5, 2}, 16, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 2.0F, 0.1F, () -> {
        return Ingredient.ofItems(MythicItems.OSMIUM.getIngot());
    }),
    PALLADIUM("palladium", 28, new int[]{3, 6, 8, 3}, 16, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 2.0F, 0.0F, () -> {
        return Ingredient.ofItems(MythicItems.PALLADIUM.getIngot());
    }),
    PROMETHEUM("prometheum", 18, new int[]{3, 6, 8, 3}, 17, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 1.0F, 0.0F, () -> {
        return Ingredient.ofItems(MythicItems.PROMETHEUM.getIngot());
    }),
    RUNITE("runite", 27, new int[]{3, 6, 8, 3}, 13, RegisterSounds.EQUIP_RUNITE, 2.0F, 0.0F, () -> {
        return Ingredient.ofItems(MythicItems.RUNITE.getIngot());
    }),
    SILVER("silver", 10, new int[]{1, 3, 4, 2}, 20, SoundEvents.ITEM_ARMOR_EQUIP_GOLD, 0.0F, 0.0F, () -> {
        return Ingredient.ofItems(MythicItems.SILVER.getIngot());
    }),
    STAR_PLATINUM("star_platinum", 34, new int[]{3, 7, 7, 3}, 18, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2.0F, 0.1F, () -> {
        return Ingredient.ofItems(MythicItems.STAR_PLATINUM.getIngot());
    }),
    STEEL("steel", 20,  new int[]{2, 5, 6, 2}, 10, SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, 0.5F, 0.0F, () -> {
        return Ingredient.ofItems(MythicItems.STEEL.getIngot());
    }),
    STORMYX("stormyx", 30, new int[]{3, 6, 8, 3}, 20, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2.0F, 0.0F, () -> {
        return Ingredient.ofItems(MythicItems.STORMYX.getIngot());
    });


    private static final int[] BASE_DURABILITY = new int[]{13, 15, 16, 12};
    private final String name;
    private final int durabilityMultiplier;
    private final int[] protectionAmounts;
    private final int enchantability;
    private final SoundEvent equipSound;
    private final float toughness;
    private final float knockbackResistance;
    private final Supplier<Ingredient> repairIngredientSupplier;

    MythicArmorMaterials(String name, int durabilityMultiplier, int[] protectionAmounts, int enchantability, SoundEvent equipSound, float toughness, float knockbackResistance, Supplier<Ingredient> repairIngredientSupplier) {
        this.name = name;
        this.durabilityMultiplier = durabilityMultiplier;
        this.protectionAmounts = protectionAmounts;
        this.enchantability = enchantability;
        this.equipSound = equipSound;
        this.toughness = toughness;
        this.knockbackResistance = knockbackResistance;
        this.repairIngredientSupplier = Suppliers.memoize(repairIngredientSupplier::get);
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
        return this.repairIngredientSupplier.get();
    }

    public String getName() {
        return this.name;
    }

    public float getToughness() {
        return this.toughness;
    }

    public float getKnockbackResistance() {
        return this.knockbackResistance;
    }

    public int getDuraMultiplier() {
        return durabilityMultiplier;
    }

    public int[] getProtectionAmounts() {
        return protectionAmounts;
    }
}
