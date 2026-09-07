package com.mythicmetals.item.tools;

import com.mythicmetals.item.component.GoldFoldedComponent;
import com.mythicmetals.misc.RegistryHelper;
import net.minecraft.resources.Identifier;
import net.minecraft.util.Mth;
import net.minecraft.world.item.*;
import org.jetbrains.annotations.Nullable;

import static com.mythicmetals.item.component.MythicDataComponents.GOLD_FOLDED;

public class MidasGoldSword extends Item {

    public static final Identifier MIDAS_BONUS_DAMAGE_ID = RegistryHelper.id("midas_gold_sword_bonus_damage");

    public MidasGoldSword(ToolMaterial material, Item.Properties settings) {
        super(material.applySwordProperties(settings, 3.0f, -2.4f));
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
        if (goldCount > 640) {
            var stack = new ItemStack(MythicTools.ROYAL_MIDAS_GOLD_SWORD);
            stack.set(GOLD_FOLDED, GoldFoldedComponent.of(goldCount, true));
            return stack;
        } else if (goldCount > 319) {
            var stack = new ItemStack(MythicTools.GILDED_MIDAS_GOLD_SWORD);
            stack.set(GOLD_FOLDED, GoldFoldedComponent.of(goldCount));
            return stack;
        } else {
            var stack = MythicTools.MIDAS_GOLD_SWORD.getDefaultInstance();
            stack.set(GOLD_FOLDED, GoldFoldedComponent.of(goldCount));
            return stack;
        }
    }
}
