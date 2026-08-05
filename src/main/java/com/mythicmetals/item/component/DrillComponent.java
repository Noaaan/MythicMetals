package com.mythicmetals.item.component;

import com.mythicmetals.misc.UsefulSingletonForColorUtil;
import io.wispforest.endec.Endec;
import io.wispforest.endec.impl.StructEndecBuilder;
import net.minecraft.ChatFormatting;
import net.minecraft.core.component.DataComponentGetter;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.util.Mth;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipProvider;
import java.util.function.Consumer;

public record DrillComponent(int fuel) implements TooltipProvider {
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
        return new DrillComponent(Mth.clamp(fuel - 1, 0, MAX_FUEL));
    }

    public DrillComponent increase(int fuel) {
        return new DrillComponent(Mth.clamp(fuel + 1, 0, MAX_FUEL));
    }

    public boolean hasFuel() {
        return this.fuel > 0;
    }

    @Override
    public void addToTooltip(Item.TooltipContext tooltipContext, Consumer<Component> tooltip, TooltipFlag tooltipFlag, DataComponentGetter dataComponentGetter) {

        // Activation Status
        if (this.fuel == 0) {
            tooltip.accept(Component.translatable("tooltip.mythril_drill.refuel").setStyle(Style.EMPTY.withColor(ChatFormatting.GRAY)));
        }
        // Fuel Gauge
        tooltip.accept(Component.translatable("tooltip.mythril_drill.fuel", this.fuel, MAX_FUEL)
            .withStyle(Style.EMPTY.withColor(UsefulSingletonForColorUtil.getSlightlyDarkerOwoBlueToRedGradient(this.fuel, MAX_FUEL))));

    }
}
