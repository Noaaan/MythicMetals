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

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

@SuppressWarnings("ALL")
public enum ArmorMaterials implements ArmorMaterial {
    ADAMANTITE("adamantite", 30, new int[]{3, 6, 8, 3}, 16, SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, 2.0F, 0.0F, () -> {
        return Ingredient.ofItems(MythicItems.ADAMANTITE_INGOT);
    }),
    AQUARIUM("aquarium", 16, new int[]{2, 5, 6, 2}, 12, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 0.0F, 0.0F, () -> {
        return Ingredient.ofItems(MythicItems.AQUARIUM_INGOT);
    }),
    BANGLUM("banglum", 14, new int[]{2, 5, 6, 3}, 1, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 0.0F, 0.0F, () -> {
        return Ingredient.ofItems(MythicItems.BANGLUM_INGOT);
    }),
    BRONZE("bronze", 17, new int[]{3, 5, 6, 2}, 14, SoundEvents.ITEM_ARMOR_EQUIP_GOLD, 0.0F, 0.0F, () -> {
        return Ingredient.ofItems(MythicItems.BRONZE_INGOT);
    }),
    CARMOT("carmot", 21, new int[]{3, 6, 8, 3}, 40, SoundEvents.ITEM_ARMOR_EQUIP_GOLD, 0.0F, 0.0F, () -> {
        return Ingredient.ofItems(MythicItems.CARMOT_INGOT);
    }),
    CELESTIUM("celestium", 38, new int[]{4, 8, 10, 4}, 24, SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, 3.0F, 0.0F, () -> {
        return Ingredient.ofItems(MythicItems.CELESTIUM_INGOT);
    }),
    COPPER("copper", 8, new int[]{1, 3, 4, 2}, 8, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 0.0F, 0.0F, () -> {
        return Ingredient.ofItems(Items.COPPER_INGOT);
    }),
    DURASTEEL("durasteel", 25, new int[]{3, 6, 8, 3}, 12, SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, 1.0F, 0.0F, () -> {
        return Ingredient.ofItems(MythicItems.DURASTEEL_INGOT);
    }),
    HALLOWED("hallowed", 37, new int[]{4, 7, 9, 4}, 20, SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, 3.0F, 0.0F, () -> {
        return Ingredient.ofItems(MythicItems.HALLOWED_INGOT);
    }),
    KYBER("kyber", 19, new int[]{2, 6, 8, 2}, 20, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 0.0F, 0.0F, () -> {
        return Ingredient.ofItems(MythicItems.KYBER_INGOT);
    }),
    METALLURGIUM("metallurgium", 69, new int[]{5, 9, 12, 5}, 30, SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, 5.0F, 0.2F, () -> {
        return Ingredient.ofItems(MythicItems.METALLURGIUM_INGOT);
    }),
    MIDAS_GOLD("midas_gold", 12, new int[]{1, 3, 5, 2}, 24, SoundEvents.ITEM_ARMOR_EQUIP_CHAIN, 0.0F, 0.0F, () -> {
        return Ingredient.ofItems(MythicItems.MIDAS_GOLD_INGOT);
    }),
    MYTHRIL("mythril", 28, new int[]{3, 6, 8, 3}, 22, SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, 2.0F, 0.0F, () -> {
        return Ingredient.ofItems(MythicItems.MYTHRIL_INGOT);
    }),
    ORICHALCUM("orichalcum", 42, new int[]{4, 7, 9, 4}, 16, SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, 2.0F, 0.1F, () -> {
        return Ingredient.ofItems(MythicItems.ORICHALCUM_INGOT);
    }),
    OSMIUM("osmium", 16, new int[]{2, 5, 7, 2}, 13, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 0.0F, 0.05F, () -> {
        return Ingredient.ofItems(MythicItems.OSMIUM_INGOT);
    }),
    PALLADIUM("palladium", 28, new int[]{3, 6, 8, 3}, 16, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 2.0F, 0.0F, () -> {
        return Ingredient.ofItems(MythicItems.PALLADIUM_INGOT);
    }),
    PROMETHEUM("prometheum", 18, new int[]{3, 6, 8, 3}, 17, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 1.0F, 0.0F, () -> {
        return Ingredient.ofItems(MythicItems.PROMETHEUM_INGOT);
    }),
    RUNITE("runite", 27, new int[]{3, 6, 7, 3}, 13, RegisterSounds.EQUIP_RUNITE, 2.0F, 0.0F, () -> {
        return Ingredient.ofItems(MythicItems.RUNITE_INGOT);
    }),
    SILVER("silver", 8, new int[]{2, 3, 5, 2}, 20, SoundEvents.ITEM_ARMOR_EQUIP_GOLD, 0.0F, 0.0F, () -> {
        return Ingredient.ofItems(MythicItems.SILVER_INGOT);
    }),
    STAR_PLATINUM("star_platinum", 34, new int[]{3, 6, 8, 3}, 18, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2.5F, 0.1F, () -> {
        return Ingredient.ofItems(MythicItems.STAR_PLATINUM);
    }),
    STEEL("steel", 19, new int[]{2, 5, 7, 2}, 11, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 1.0F, 0.0F, () -> {
        return Ingredient.ofItems(MythicItems.STEEL_INGOT);
    }),
    STORMYX("stormyx", 30, new int[]{3, 6, 8, 3}, 20, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2.0F, 0.0F, () -> {
        return Ingredient.ofItems(MythicItems.STORMYX_INGOT);
    });


    private static final int[] BASE_DURABILITY = new int[]{12, 15, 16, 11};
    private final String name;
    private final int durabilityMultiplier;
    private final int[] protectionAmounts;
    private final int enchantability;
    private final SoundEvent equipSound;
    private final float toughness;
    private final float knockbackResistance;
    private final Supplier<Ingredient> repairIngredientSupplier;

    private ArmorMaterials(String name, int durabilityMultiplier, int[] protectionAmounts, int enchantability, SoundEvent equipSound, float toughness, float knockbackResistance, Supplier<Ingredient> repairIngredientSupplier) {
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
        return (Ingredient) this.repairIngredientSupplier.get();
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

    public static final List KNOCKBACKABLE_ARMOR_MATERIALS = Arrays.asList(new ArmorMaterial[]{
            ORICHALCUM, OSMIUM, METALLURGIUM
    });

}
