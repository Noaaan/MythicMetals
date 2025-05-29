package com.mythicmetals.component;

import io.wispforest.endec.StructEndec;
import io.wispforest.endec.impl.StructEndecBuilder;
import net.minecraft.item.Item;
import net.minecraft.item.tooltip.TooltipAppender;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import com.mythicmetals.item.tools.MidasGoldSword;
import com.mythicmetals.misc.UsefulSingletonForColorUtil;
import java.util.function.Consumer;

public record GoldFoldedComponent(int goldFolded, boolean isRoyal, boolean showTooltip) implements TooltipAppender {

    public static final StructEndec<GoldFoldedComponent> ENDEC = StructEndecBuilder.of(
        StructEndec.INT.fieldOf("gold_folded", GoldFoldedComponent::goldFolded),
        StructEndec.BOOLEAN.fieldOf("is_royal", GoldFoldedComponent::isRoyal),
        StructEndec.BOOLEAN.fieldOf("show_tooltip", GoldFoldedComponent::showTooltip),
        GoldFoldedComponent::new
    );

    public static GoldFoldedComponent of(int folds, boolean isRoyal) {
        return new GoldFoldedComponent(folds, isRoyal, true);
    }

    public static GoldFoldedComponent of(int folds) {
        return new GoldFoldedComponent(folds, false, true);
    }

    @Override
    public void appendTooltip(Item.TooltipContext context, Consumer<Text> tooltip, TooltipType type) {
        if (!showTooltip) return;

        int level = MidasGoldSword.calculateSwordLevel(this.goldFolded);

        if (level > 20) {
            level = 20 + level / 6;
        }

        if (this.goldFolded < 704 && this.isRoyal()) {
            level = 11;
        }

        // Spout fun facts and lore while leveling up the sword
        tooltip.accept(Text.translatable("tooltip.midas_gold.level." + level).formatted(Formatting.GOLD));
        if (this.goldFolded == 0) {
            return;
        }

        // Remove the cap from tooltip when maxed
        if (this.goldFolded >= 1280) {
            if (this.goldFolded == 10000) {
                // e.g. **⭐10000 FOLDS - MAXED⭐**
                tooltip.accept(Text.translatable("tooltip.midas_gold.maxed", this.goldFolded).formatted(Formatting.GOLD, Formatting.BOLD));
            } else {
                // e.g. Folds: 2500
                tooltip.accept(Text.translatable("tooltip.midas_gold.fold_counter", this.goldFolded).formatted(Formatting.GOLD));
            }
            if (this.isRoyal) {
                tooltip.accept(Text.translatable("tooltip.midas_gold.is_royal").setStyle(UsefulSingletonForColorUtil.MetalColors.GOLD_STYLE));
            }
            return;
        }

        // Handle the cap format
        if (this.isRoyal()) {
            // e.g. 63/1280
            tooltip.accept(Text.literal(this.goldFolded + " / " + 1280).formatted(Formatting.GOLD));
        } else if (goldFolded() >= 321) {
            // e.g. 63/640
            tooltip.accept(Text.literal(this.goldFolded + " / " + 640).formatted(Formatting.GOLD));
        } else {
            // e.g. 63/128
            tooltip.accept(Text.literal(this.goldFolded + " / " + (64 + level * 64)).formatted(Formatting.GOLD));
        }

        // If this isn't done here, the ability text won't show up due to return guard
        if (this.isRoyal) {
            tooltip.accept(Text.translatable("tooltip.midas_gold.is_royal").setStyle(UsefulSingletonForColorUtil.MetalColors.GOLD_STYLE));
        }
    }
}
