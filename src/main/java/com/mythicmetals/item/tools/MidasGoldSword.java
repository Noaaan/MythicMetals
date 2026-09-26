package com.mythicmetals.item.tools;

import com.mythicmetals.item.component.MidasGoldComponent;
import net.minecraft.core.component.DataComponents;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.*;
import net.minecraft.world.item.component.ItemAttributeModifiers;

import static com.mythicmetals.item.component.MythicDataComponents.MIDAS_GOLD;

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
        int goldCount = stack.getOrDefault(MIDAS_GOLD, MidasGoldComponent.of(0)).folds();
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

    public static ItemStack createSwordFromGold(int goldCount) {
        ItemStack stack;
        if (goldCount > 1280) {
            stack = new ItemStack(MythicTools.TRUE_ROYAL_MIDAS_GOLD_SWORD);
        } else if (goldCount > 1023) {
            stack = new ItemStack(MythicTools.ROYAL_MIDAS_GOLD_GREATSWORD);
        } else if (goldCount > 895) {
            stack = new ItemStack(MythicTools.ROYAL_MIDAS_GOLD_LONGSWORD);
        } else if (goldCount > 767) {
            stack = new ItemStack(MythicTools.ROYAL_MIDAS_GOLD_BROADSWORD);
        } else if (goldCount > 640) {
            stack = new ItemStack(MythicTools.ROYAL_MIDAS_GOLD_SWORD);
        } else if (goldCount == 640) {
          stack = new ItemStack(MythicTools.MAXED_GILDED_MIDAS_GOLD_SWORD);
        } else if (goldCount > 319) {
            stack = new ItemStack(MythicTools.GILDED_MIDAS_GOLD_SWORD);
        } else if (goldCount > 255) {
            stack = new ItemStack(MythicTools.SOCKETED_MIDAS_GOLD_SWORD);
        } else if (goldCount > 127) {
            stack = new ItemStack(MythicTools.MIDAS_GOLD_SWORD);
        } else if (goldCount > 63) {
            stack = new ItemStack(MythicTools.MIDAS_GOLD_SHORTSWORD);
        } else {
            stack = MythicTools.MIDAS_GOLD_DAGGER.getDefaultInstance();
        }
        stack.set(MIDAS_GOLD, MidasGoldComponent.of(goldCount));
        return stack;
    }
}
