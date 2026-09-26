package com.mythicmetals.item.component;

import io.wispforest.endec.Endec;
import io.wispforest.endec.StructEndec;
import io.wispforest.endec.impl.StructEndecBuilder;
import net.minecraft.ChatFormatting;
import net.minecraft.core.component.DataComponentGetter;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipProvider;

import java.util.function.Consumer;

public record MidasGoldComponent(int folds, boolean showTooltip) implements TooltipProvider {

    public static final StructEndec<MidasGoldComponent> ENDEC = StructEndecBuilder.of(
        Endec.INT.fieldOf("folds", MidasGoldComponent::folds),
        Endec.BOOLEAN.fieldOf("show_tooltip", MidasGoldComponent::showTooltip),
        MidasGoldComponent::new
    );

    public static MidasGoldComponent of(int folds) {
        return new MidasGoldComponent(folds, true);
    }

    @Override
    public void addToTooltip(Item.TooltipContext tooltipContext, Consumer<Component> tooltip, TooltipFlag tooltipFlag, DataComponentGetter dataComponentGetter) {
        if (!showTooltip) return;

        int level = calculateSwordLevel(this.folds);

        if (level > 20) {
            level = 20 + level / 6;
        }

        if (this.folds < 704 && this.isRoyal()) {
            level = 11;
        }

        // Spout fun facts and lore while leveling up the sword
        tooltip.accept(Component.translatable("tooltip.midas_gold.level." + level).withStyle(ChatFormatting.GOLD));
        if (this.folds == 0) {
            return;
        }

        // Remove the cap from tooltip when maxed
        if (this.folds >= 1280) {
            if (this.folds == 10000) {
                // e.g. **⭐10000 FOLDS - MAXED⭐**
                tooltip.accept(Component.translatable("tooltip.midas_gold.maxed", this.folds).withStyle(ChatFormatting.GOLD, ChatFormatting.BOLD));
            } else {
                // e.g. Folds: 2500
                tooltip.accept(Component.translatable("tooltip.midas_gold.fold_counter", this.folds).withStyle(ChatFormatting.GOLD));
            }
        } else {
            // Handle the cap format
            if (this.isRoyal()) {
                // e.g. 63/1280
                tooltip.accept(Component.literal(this.folds + " / " + 1280).withStyle(ChatFormatting.GOLD));
            } else if (folds() >= 321) {
                // e.g. 63/640
                tooltip.accept(Component.literal(this.folds + " / " + 640).withStyle(ChatFormatting.GOLD));
            } else {
                // e.g. 63/128
                tooltip.accept(Component.literal(this.folds + " / " + (64 + level * 64)).withStyle(ChatFormatting.GOLD));
            }
        }
    }

    public boolean isRoyal() {
        return folds > 640;
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
}
