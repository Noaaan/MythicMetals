package com.mythicmetals.component;

import com.mythicmetals.misc.UsefulSingletonForColorUtil;
import io.wispforest.endec.Endec;
import io.wispforest.endec.impl.StructEndecBuilder;
import net.minecraft.item.Item;
import net.minecraft.item.tooltip.TooltipAppender;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.MathHelper;
import java.util.function.Consumer;

public record DrillComponent(int fuel) implements TooltipAppender {
    public static final Endec<DrillComponent> ENDEC = StructEndecBuilder.of(
        Endec.INT.fieldOf("fuel", DrillComponent::fuel),
        DrillComponent::new
    );
    public static final DrillComponent DEFAULT = new DrillComponent(0);

    /**
     * One fuel lets you break one block with all the Drill perks
     */
    public static final int MAX_FUEL = 4000;
    /**
     * Each piece of Morkite will fuel the drill by this constant worth of units
     */
    public static final int FUEL_CONSTANT = 20;


    public DrillComponent reduce(int fuel) {
        return new DrillComponent(MathHelper.clamp(fuel - 1, 0, MAX_FUEL));
    }

    public DrillComponent increase(int fuel) {
        return new DrillComponent(MathHelper.clamp(fuel + 1, 0, MAX_FUEL));
    }

    public boolean hasFuel() {
        return this.fuel > 0;
    }

    @Override
    public void appendTooltip(Item.TooltipContext context, Consumer<Text> tooltip, TooltipType type) {

        // Activation Status
        if (this.hasFuel()) {
            tooltip.accept(Text.translatable("tooltip.mythril_drill.activated").formatted(Formatting.AQUA));
        }
        if (this.fuel == 0) {
            tooltip.accept(Text.translatable("tooltip.mythril_drill.refuel").setStyle(Style.EMPTY.withColor(Formatting.GRAY)));
        }
        // Fuel Gauge
        tooltip.accept(Text.translatable("tooltip.mythril_drill.fuel", this.fuel, MAX_FUEL)
            .fillStyle(Style.EMPTY.withColor(UsefulSingletonForColorUtil.getSlightlyDarkerOwoBlueToRedGradient(this.fuel, MAX_FUEL))));

    }
}
