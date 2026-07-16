package com.mythicmetals.item.tools;

import com.mythicmetals.item.component.GoldFoldedComponent;
import net.minecraft.core.component.DataComponentMap;
import net.minecraft.core.component.DataComponentPatch;
import net.minecraft.util.Mth;
import net.minecraft.world.item.*;
import org.jetbrains.annotations.Nullable;

import static com.mythicmetals.item.component.MythicDataComponents.GOLD_FOLDED;

public class MidasGoldSword extends Item {
    public MidasGoldSword(ToolMaterial material, Item.Properties settings) {
        super(material.applySwordProperties(settings, 3.0f, -2.4f));
    }

    @Override
    public void deriveStackComponents(DataComponentMap source, DataComponentPatch.Builder target) {
        super.deriveStackComponents(source, target);

        // FIXME - Dynamic Attributes
    }

//    @Override
//    public void verifyComponentsAfterLoad(ItemStack stack) {
//        // TODO - Surely there is a better way to do dynamic attributes, right? Right??
//        //  This is a lot of effort for the correct green tooltip... Thanks Mojang
//        if (!stack.has(DataComponents.ATTRIBUTE_MODIFIERS)) return;
//        var currentAttributes = stack.get(DataComponents.ATTRIBUTE_MODIFIERS);
//        assert currentAttributes != null;
//        int goldCount = stack.getOrDefault(GOLD_FOLDED, GoldFoldedComponent.of(0)).goldFolded();
//        var originalDamage = new AtomicReference<>(0.0);
//        stack.getPrototype().get(DataComponents.ATTRIBUTE_MODIFIERS).modifiers().forEach(entry -> {
//            if (entry.modifier().id().equals(BASE_ATTACK_DAMAGE_ID)) {
//                originalDamage.set(entry.modifier().amount());
//            }
//        });
//        double goldDmgBonus = computeBonusDamage(goldCount);
//
//        var speed = new AtomicReference<>(0.0);
//        // Copy attack speed over. We want to re-build, not add anything
//        currentAttributes.modifiers().forEach(entry -> {
//            if (entry.attribute().equals(Attributes.ATTACK_SPEED)) {
//                speed.set(entry.modifier().amount());
//            }
//        });
//
//        if (goldDmgBonus > 0) {
//            var changedComponent = ItemAttributeModifiers.builder()
//                .add(
//                    Attributes.ATTACK_DAMAGE,
//                    new AttributeModifier(BASE_ATTACK_DAMAGE_ID,
//                        originalDamage.get() + goldDmgBonus,
//                        AttributeModifier.Operation.ADD_VALUE
//                    ),
//                    EquipmentSlotGroup.MAINHAND
//                )
//                .add(
//                    Attributes.ATTACK_SPEED,
//                    new AttributeModifier(BASE_ATTACK_SPEED_ID, speed.get(), AttributeModifier.Operation.ADD_VALUE),
//                    EquipmentSlotGroup.MAINHAND
//                )
//                .build();
//            stack.set(DataComponents.ATTRIBUTE_MODIFIERS, changedComponent);
//        }
//    }

    // FIXME - Tooltips
//    @Override
//    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> lines, TooltipFlag type) {
//        if (stack.has(GOLD_FOLDED)) {
//            stack.get(GOLD_FOLDED).addToTooltip(context, lines::add, type);
//        }
//    }

    public int computeBonusDamage(int goldCount) {
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
