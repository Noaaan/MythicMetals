package com.mythicmetals.item.tools;

import com.mythicmetals.item.component.GoldFoldedComponent;
import net.minecraft.core.component.DataComponents;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.*;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import org.jetbrains.annotations.Nullable;

import static com.mythicmetals.item.component.MythicDataComponents.GOLD_FOLDED;

public class MidasGoldSword extends Item {

    public MidasGoldSword(ToolMaterial material, Item.Properties settings) {
        super(material.applySwordProperties(settings, 3.0f, -2.4f));
    }

    public static void recalculateSwordDamage(ItemStack stack) {
        // TODO - This is a lot of effort for the correct green tooltip... Thanks Mojang
        // Originally added for 1.20.4, this code is terrible.
        if (!stack.has(DataComponents.ATTRIBUTE_MODIFIERS)) return;
        var currentAttributes = stack.get(DataComponents.ATTRIBUTE_MODIFIERS);
        assert currentAttributes != null;
        int goldCount = stack.getOrDefault(GOLD_FOLDED, GoldFoldedComponent.of(0)).goldFolded();
        double goldDmgBonus = computeBonusDamage(goldCount);
        if (goldDmgBonus <= 0) return;

        // rebuild the map with new attack damage + bonuses, since directly editing it is not allowed
        double originalDamage = 0.0;
        var builder = ItemAttributeModifiers.builder();
        for (ItemAttributeModifiers.Entry entry : currentAttributes.modifiers()) {
            if (entry.modifier().id().equals(Item.BASE_ATTACK_DAMAGE_ID)) {
                originalDamage = entry.modifier().amount();
            } else {
                builder.add(entry.attribute(), entry.modifier(), entry.slot(), entry.display());
            }
        }
        var changedComponent = builder
            .add(
                Attributes.ATTACK_DAMAGE,
                new AttributeModifier(BASE_ATTACK_DAMAGE_ID,
                    originalDamage + goldDmgBonus,
                    AttributeModifier.Operation.ADD_VALUE
                ),
                EquipmentSlotGroup.MAINHAND
            )
            .build();
        stack.set(DataComponents.ATTRIBUTE_MODIFIERS, changedComponent);
    }

    public static int computeBonusDamage(int goldCount) {
        int bonus = Mth.clamp(Mth.floor((float) goldCount / 64), 0, 6);
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

        public static boolean isOfMidas(ItemStack stack, Type type) {
            var comparedType = getSwordType(stack);
            if (comparedType != null) {
                return comparedType.equals(type);
            }
            return false;
        }
    }

    public static ItemStack createSwordFromGold(int goldCount) {
        ItemStack stack;
        if (goldCount > 640) {
            stack = new ItemStack(MythicTools.ROYAL_MIDAS_GOLD_SWORD);
            stack.set(GOLD_FOLDED, GoldFoldedComponent.of(goldCount, true));
        } else if (goldCount > 319) {
            stack = new ItemStack(MythicTools.GILDED_MIDAS_GOLD_SWORD);
            stack.set(GOLD_FOLDED, GoldFoldedComponent.of(goldCount));
        } else {
            stack = MythicTools.MIDAS_GOLD_SWORD.getDefaultInstance();
            stack.set(GOLD_FOLDED, GoldFoldedComponent.of(goldCount));
        }
        recalculateSwordDamage(stack);
        return stack;
    }
}
