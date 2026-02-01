package com.mythicmetals.component;


import com.mythicmetals.data.MythicTags;
import com.mythicmetals.misc.RegistryHelper;
import io.wispforest.endec.StructEndec;
import io.wispforest.endec.impl.StructEndecBuilder;
import net.minecraft.resources.Identifier;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentEffectComponents;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;

/**
 * A data carrier which holds and tracks the Prometheum Auto Repair ability.
 * Only applies to items within the {@link MythicTags#AUTO_REPAIR} tag.
 * <br>
 * If the Item is an {@link net.minecraft.world.item.ArmorItem} it gains bonus armor, and/or armor toughness.
 * Otherwise, if the Item has {@link net.minecraft.core.component.DataComponents#ATTRIBUTE_MODIFIERS} it will gain bonus damage.
 * <br>
 * Append this component on your {@link net.minecraft.world.item.Item.Properties} to use it.
 *
 * @see com.mythicmetals.mixin.ItemMixin
 */
public record PrometheumComponent(int durabilityRepaired) {
    public static final int OVERGROWN_THRESHOLD = 1200;
    public static final StructEndec<PrometheumComponent> ENDEC = StructEndecBuilder.of(
        StructEndec.INT.fieldOf("durability_repaired", PrometheumComponent::durabilityRepaired),
        PrometheumComponent::new
    );
    public static final Identifier ARMOR_BONUS_ID = RegistryHelper.id("prometheum_armor_bonus");
    public static final Identifier TOUGHNESS_BONUS_ID = RegistryHelper.id("prometheum_toughness_bonus");
    public static final Identifier DAMAGE_BONUS_ID = RegistryHelper.id("prometheum_damage_bonus");
    public static final PrometheumComponent DEFAULT = new PrometheumComponent(0);

    /**
     * Applies auto repair onto the item in question
     *
     * @param stack ItemStack to repair
     * @param world World where the ItemStack exists
     */
    public static void tickAutoRepair(ItemStack stack, Level world) {
        if (!stack.isDamaged()) return; // Don't handle auto repair if item is fully repaired
        if (world.isClientSide()) return; // Desyncs if done on client
        if (!stack.has(MythicDataComponents.PROMETHEUM)) return;
        var random = world.getRandom();

        var component = stack.get(MythicDataComponents.PROMETHEUM);
        assert component != null;

        var dmg = stack.getDamageValue();
        var rng = random.nextInt(200);

        if (rng != 177) return; // Roll for repair, ignore if roll fails. Number is arbitrary

        // Overgrown Items repair faster
        int damageToRepair = isOvergrown(stack) ? 2 : 1;

        // Extra repair speed if bound
        if (EnchantmentHelper.has(stack, EnchantmentEffectComponents.PREVENT_ARMOR_CHANGE)) {
            damageToRepair += 1;
        }

        int newDamage = Mth.clamp(dmg - damageToRepair, 0, Integer.MAX_VALUE);
        stack.setDamageValue(newDamage);
        stack.set(MythicDataComponents.PROMETHEUM, component.increase(damageToRepair));
    }

    public PrometheumComponent increase(int durabilityRepaired) {
        return new PrometheumComponent(this.durabilityRepaired + durabilityRepaired);
    }

    public boolean isOvergrown() {
        return this.durabilityRepaired > OVERGROWN_THRESHOLD;
    }

    public static boolean isOvergrown(ItemStack stack) {
        return stack.getOrDefault(MythicDataComponents.PROMETHEUM, DEFAULT).durabilityRepaired > OVERGROWN_THRESHOLD;
    }

    public static AttributeModifier createOvergrownModifier(ItemStack stack, int base) {
        return createOvergrownModifier(stack, base, EquipmentSlot.MAINHAND);
    }

    public static AttributeModifier createOvergrownModifier(ItemStack stack, int base, EquipmentSlot slot) {
        var id = switch (slot.getType()) {
            case HAND -> DAMAGE_BONUS_ID;
            case HUMANOID_ARMOR, ANIMAL_ARMOR -> ARMOR_BONUS_ID;
        };
        var component = stack.getOrDefault(MythicDataComponents.PROMETHEUM, PrometheumComponent.DEFAULT);
        int bonus = base;
        bonus += component.durabilityRepaired() > (OVERGROWN_THRESHOLD * 2) ? 2 : 1;
        return new AttributeModifier(
            id,
            bonus,
            AttributeModifier.Operation.ADD_VALUE
        );
    }

    public static AttributeModifier createOvergrownToughnessModifier(ItemStack stack, int base) {
        var component = stack.getOrDefault(MythicDataComponents.PROMETHEUM, PrometheumComponent.DEFAULT);
        int bonus = base;
        bonus += component.durabilityRepaired() > (OVERGROWN_THRESHOLD * 2) ? 2 : 1;
        bonus += EnchantmentHelper.has(stack, EnchantmentEffectComponents.PREVENT_ARMOR_CHANGE) ? 1 : 0;
        return new AttributeModifier(
            TOUGHNESS_BONUS_ID,
            bonus,
            AttributeModifier.Operation.ADD_VALUE);
    }
}
