package com.mythicmetals.item.component;

import com.mythicmetals.MythicMetals;
import com.mythicmetals.item.tools.MythrilDrill;
import io.wispforest.endec.Endec;
import io.wispforest.endec.impl.StructEndecBuilder;
import io.wispforest.owo.serialization.endec.MinecraftEndecs;
import net.minecraft.ChatFormatting;
import net.minecraft.core.component.DataComponentGetter;
import net.minecraft.util.Util;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.world.item.*;
import net.minecraft.world.item.component.TooltipProvider;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public record UpgradeComponent(List<Item> items, int size) implements TooltipProvider {

    public UpgradeComponent(int size) {
        this(Util.make(new ArrayList<>(), items1 -> {
            for (int i = 0; i < size; i++) {
                items1.add(Items.AIR);
            }
        }), size);
    }

    public static final Endec<UpgradeComponent> ENDEC = StructEndecBuilder.of(
        MinecraftEndecs.ofRegistry(BuiltInRegistries.ITEM).listOf().fieldOf("items", UpgradeComponent::items),
        Endec.INT.fieldOf("size", UpgradeComponent::size),
        UpgradeComponent::new
    );

    public static UpgradeComponent empty(int size) {
        return new UpgradeComponent(size);
    }

    public static UpgradeComponent addItem(UpgradeComponent oldComponent, Item item) {
        var newList = new ArrayList<>(oldComponent.items);
        newList.remove(Items.AIR);
        newList.addFirst(item);
        return new UpgradeComponent(newList, oldComponent.size);
    }

    /**
     * Check if any upgrade is installed in a specified requiredSlot
     */
    public boolean hasUpgrade(Item upgradeItem) {
        return this.items.contains(upgradeItem);
    }

    public boolean isEmpty() {
        for (Item item : this.items) {
            if (item != Items.AIR) {
                return false;
            }
        }
        return true;
    }

    public boolean hasFreeSlots() {
        return this.items.contains(Items.AIR);
    }

    @Override
    public void addToTooltip(Item.TooltipContext tooltipContext, Consumer<Component> tooltip, TooltipFlag tooltipFlag, DataComponentGetter dataComponentGetter) {
        if (this.size > 0 && this.isEmpty()) {
            tooltip.accept(Component.translatable("tooltip.upgrade_component.tooltip").setStyle(Style.EMPTY.withColor(ChatFormatting.GRAY)));
        }

        for (int i = this.size - 1; i != -1; i--) {
            var item = this.items().get(i);
            tooltip.accept(Component.translatable("tooltip.mythril_drill.upgrade_slot", i + 1, Component.translatable("tooltip.mythril_drill.upgrade." + MythrilDrill.drillUpgrades.get(item))));
        }

        if (this.size > this.items.size()) {
            MythicMetals.LOGGER.warn("Upgrade Component is larger than the initial item list");
        }
    }
}
