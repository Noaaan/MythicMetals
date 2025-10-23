package com.mythicmetals.item.tools;

import com.mythicmetals.component.GoldFoldedComponent;
import com.mythicmetals.component.MythicDataComponents;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.AttributeModifierSlot;
import net.minecraft.component.type.AttributeModifiersComponent;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.item.*;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;
import net.minecraft.util.math.MathHelper;
import org.jetbrains.annotations.Nullable;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class MidasGoldSword extends SwordItem {
    public MidasGoldSword(ToolMaterial material, Settings settings) {
        super(material, 3.0f, -2.4f, settings);
    }

    @Override
    public void postProcessComponents(ItemStack stack) {
        // TODO - Surely there is a better way to do dynamic attributes, right? Right??
        //  This is a lot of effort for the correct green tooltip... Thanks Mojang
        if (!stack.contains(DataComponentTypes.ATTRIBUTE_MODIFIERS)) return;
        var currentAttributes = stack.get(DataComponentTypes.ATTRIBUTE_MODIFIERS);
        assert currentAttributes != null;
        int goldCount = stack.getOrDefault(MythicDataComponents.GOLD_FOLDED, GoldFoldedComponent.of(0)).goldFolded();
        var originalDamage = new AtomicReference<>(0.0);
        stack.getDefaultComponents().get(DataComponentTypes.ATTRIBUTE_MODIFIERS).modifiers().forEach(entry -> {
            if (entry.modifier().id().equals(BASE_ATTACK_DAMAGE_MODIFIER_ID)) {
                originalDamage.set(entry.modifier().value());
            }
        });
        double goldDmgBonus = computeBonusDamage(goldCount);

        var speed = new AtomicReference<>(0.0);
        // Copy attack speed over. We want to re-build, not add anything
        currentAttributes.modifiers().forEach(entry -> {
            if (entry.attribute().equals(EntityAttributes.ATTACK_SPEED)) {
                speed.set(entry.modifier().value());
            }
        });

        if (goldDmgBonus > 0) {
            var changedComponent = AttributeModifiersComponent.builder()
                .add(
                    EntityAttributes.ATTACK_DAMAGE,
                    new EntityAttributeModifier(BASE_ATTACK_DAMAGE_MODIFIER_ID,
                        originalDamage.get() + goldDmgBonus,
                        EntityAttributeModifier.Operation.ADD_VALUE
                    ),
                    AttributeModifierSlot.MAINHAND
                )
                .add(
                    EntityAttributes.ATTACK_SPEED,
                    new EntityAttributeModifier(BASE_ATTACK_SPEED_MODIFIER_ID, speed.get(), EntityAttributeModifier.Operation.ADD_VALUE),
                    AttributeModifierSlot.MAINHAND
                )
                .build();
            stack.set(DataComponentTypes.ATTRIBUTE_MODIFIERS, changedComponent);
        }
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> lines, TooltipType type) {
        if (stack.contains(MythicDataComponents.GOLD_FOLDED)) {
            stack.get(MythicDataComponents.GOLD_FOLDED).appendTooltip(context, lines::add, type);
        }
    }

    public int computeBonusDamage(int goldCount) {
        int bonus = MathHelper.clamp(MathHelper.floor((float) goldCount / 64), 0, 6);
        if (goldCount >= 1280) {
            bonus += 1;
        }
        return bonus;
    }

    /**
     * Calculates a level from intervals of 64.
     * Used for appending specific text to a Midas Gold Sword tooltip
     *
     * @param goldCount The amount of gold that is currently applied on this stack
     * @return amount of gold divided by 64, or 0 if less than 64 gold
     */
    public static int calculateSwordLevel(int goldCount) {
        if (goldCount < 64) return 0;
        return (goldCount / 64);
    }

    public enum Type {
        REGULAR,
        GILDED,
        ROYAL;

        @Nullable
        public static MidasGoldSword.Type getSwordType(ItemStack stack) {
            return getSwordType(stack.getItem());
        }

        @Nullable
        public static MidasGoldSword.Type getSwordType(Item item) {

            if (item.equals(MythicTools.MIDAS_GOLD_SWORD)) {
                return REGULAR;
            }
            if (item.equals(MythicTools.GILDED_MIDAS_GOLD_SWORD)) {
                return GILDED;
            }
            if (item.equals(MythicTools.ROYAL_MIDAS_GOLD_SWORD)) {
                return ROYAL;
            }
            return null;
        }

        public static boolean isOf(ItemStack stack, Type type) {
            var comparedType = getSwordType(stack);
            if (comparedType != null) {
                return comparedType.equals(type);
            }
            return false;
        }
    }
}
