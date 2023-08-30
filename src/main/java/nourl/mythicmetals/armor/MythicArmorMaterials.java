package nourl.mythicmetals.armor;

import com.google.common.base.Suppliers;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.sound.SoundEvent;
import nourl.mythicmetals.item.MythicItems;
import nourl.mythicmetals.registry.RegisterSounds;

import java.util.function.Supplier;

@SuppressWarnings("CodeBlock2Expr")
public enum MythicArmorMaterials implements ArmorMaterial {
    ADAMANTITE("adamantite", 30, new int[]{3, 6, 8, 3}, 16, RegisterSounds.EQUIP_ADAMANTITE, 2.0F, 0.0F, () -> {
        return Ingredient.ofItems(MythicItems.ADAMANTITE.getIngot());
    }),
    AQUARIUM("aquarium", 20, new int[]{1, 4, 5, 2}, 12, RegisterSounds.EQUIP_AQUARIUM, 0.0F, 0.0F, () -> {
        return Ingredient.ofItems(MythicItems.AQUARIUM.getIngot());
    }),
    BANGLUM("banglum", 14, new int[]{2, 5, 6, 3}, 1, RegisterSounds.EQUIP_BANGLUM, 0.0F, 0.0F, () -> {
        return Ingredient.ofItems(MythicItems.BANGLUM.getIngot());
    }),
    BRONZE("bronze", 15, new int[]{3, 5, 6, 2}, 14, RegisterSounds.EQUIP_BRONZE, 0.0F, 0.0F, () -> {
        return Ingredient.ofItems(MythicItems.BRONZE.getIngot());
    }),
    CARMOT("carmot", 26, new int[]{3, 6, 8, 3}, 40, RegisterSounds.EQUIP_CARMOT, 0.0F, 0.0F, () -> {
        return Ingredient.ofItems(MythicItems.CARMOT.getIngot());
    }),
    CELESTIUM("celestium", 38, new int[]{4, 8, 10, 4}, 30, RegisterSounds.EQUIP_CELESTIUM, 3.0F, 0.0F, () -> {
        return Ingredient.ofItems(MythicItems.CELESTIUM.getIngot());
    }),
    COPPER("copper", 9, new int[]{1, 3, 4, 2}, 8, RegisterSounds.EQUIP_COPPER, 0.0F, 0.0F, () -> {
        return Ingredient.ofItems(Items.COPPER_INGOT);
    }),
    DURASTEEL("durasteel", 25, new int[]{3, 6, 8, 3}, 12, RegisterSounds.EQUIP_DURASTEEL, 1.0F, 0.0F, () -> {
        return Ingredient.ofItems(MythicItems.DURASTEEL.getIngot());
    }),
    HALLOWED("hallowed", 41, new int[]{4, 7, 9, 4}, 20, RegisterSounds.EQUIP_HALLOWED, 4.0F, 0.0F, () -> {
        return Ingredient.ofItems(MythicItems.HALLOWED.getIngot());
    }),
    KYBER("kyber", 21, new int[]{3, 6, 8, 3}, 20, RegisterSounds.EQUIP_KYBER, 0.0F, 0.0F, () -> {
        return Ingredient.ofItems(MythicItems.KYBER.getIngot());
    }),
    LEGENDARY_BANGLUM("legendary_banglum", 28, new int[]{3, 6, 8, 3}, 2, RegisterSounds.EQUIP_LEGENDARY_BANGLUM, 2.0F, 0.0F, () -> {
        return Ingredient.ofItems(MythicItems.BANGLUM.getIngot());
    }),
    METALLURGIUM("metallurgium", 69, new int[]{5, 9, 12, 5}, 30, RegisterSounds.EQUIP_METALLURGIUM, 5.0F, 0.225F, () -> {
        return Ingredient.ofItems(MythicItems.METALLURGIUM.getIngot());
    }),
    MIDAS_GOLD("midas_gold", 14, new int[]{1, 3, 5, 2}, 24, RegisterSounds.EQUIP_MIDAS_GOLD, 0.0F, 0.0F, () -> {
        return Ingredient.ofItems(MythicItems.MIDAS_GOLD.getIngot());
    }),
    MYTHRIL("mythril", 31, new int[]{3, 6, 8, 3}, 22, RegisterSounds.EQUIP_MYTHRIL, 2.0F, 0.0F, () -> {
        return Ingredient.ofItems(MythicItems.MYTHRIL.getIngot());
    }),
    ORICHALCUM("orichalcum", 40, new int[]{4, 7, 9, 4}, 16, RegisterSounds.EQUIP_ORICHALCUM, 3.0F, 0.1F, () -> {
        return Ingredient.ofItems(MythicItems.ORICHALCUM.getIngot());
    }),
    OSMIUM("osmium", 25, new int[]{1, 4, 5, 2}, 16, RegisterSounds.EQUIP_OSMIUM, 2.0F, 0.25F, () -> {
        return Ingredient.ofItems(MythicItems.OSMIUM.getIngot());
    }),
    PALLADIUM("palladium", 28, new int[]{3, 6, 8, 3}, 16, RegisterSounds.EQUIP_PALLADIUM, 2.0F, 0.0F, () -> {
        return Ingredient.ofItems(MythicItems.PALLADIUM.getIngot());
    }),
    PROMETHEUM("prometheum", 18, new int[]{3, 6, 8, 3}, 17, RegisterSounds.EQUIP_PROMETHEUM, 1.0F, 0.0F, () -> {
        return Ingredient.ofItems(MythicItems.PROMETHEUM.getIngot());
    }),
    RUNITE("runite", 27, new int[]{3, 6, 8, 3}, 13, RegisterSounds.EQUIP_RUNITE, 2.0F, 0.0F, () -> {
        return Ingredient.ofItems(MythicItems.RUNITE.getIngot());
    }),
    SILVER("silver", 10, new int[]{1, 3, 4, 2}, 20, RegisterSounds.EQUIP_SILVER, 0.0F, 0.0F, () -> {
        return Ingredient.ofItems(MythicItems.SILVER.getIngot());
    }),
    STAR_PLATINUM("star_platinum", 34, new int[]{3, 7, 7, 3}, 18, RegisterSounds.EQUIP_STAR_PLATINUM, 2.0F, 0.1F, () -> {
        return Ingredient.ofItems(MythicItems.STAR_PLATINUM.getIngot());
    }),
    STEEL("steel", 20,  new int[]{2, 5, 6, 2}, 10, RegisterSounds.EQUIP_STEEL, 0.5F, 0.0F, () -> {
        return Ingredient.ofItems(MythicItems.STEEL.getIngot());
    }),
    STORMYX("stormyx", 30, new int[]{3, 6, 8, 3}, 20, RegisterSounds.EQUIP_STORMYX, 2.0F, 0.0F, () -> {
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

    public int getDurability(ArmorItem.Type type) {
        return BASE_DURABILITY[type.getEquipmentSlot().getEntitySlotId()] * this.durabilityMultiplier;
    }

    @Override
    public int getProtection(ArmorItem.Type type) {
        return this.protectionAmounts[type.getEquipmentSlot().getEntitySlotId()];
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
