package com.mythicmetals.mixin;

import com.mojang.serialization.Dynamic;
import net.minecraft.datafixer.fix.ItemStackComponentizationFix;
import net.minecraft.util.Util;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import java.util.ArrayList;
import java.util.Set;

@Mixin(ItemStackComponentizationFix.class)
public abstract class ItemStackComponentizationFixin {

    @Unique
    private static final Set<String> MM_MIDAS = Set.of(
        "mythicmetals:midas_gold_sword",
        "mythicmetals:gilded_midas_gold_sword"
    );

    @Unique
    private static final Set<String> MM_PROMETHEUM = Set.of(
        "mythicmetals:prometheum_sword",
        "mythicmetals:prometheum_pickaxe",
        "mythicmetals:prometheum_axe",
        "mythicmetals:prometheum_shovel",
        "mythicmetals:prometheum_hoe",
        "mythicmetals:prometheum_helmet",
        "mythicmetals:prometheum_chestplate",
        "mythicmetals:prometheum_leggings",
        "mythicmetals:prometheum_boots"
    );

    private static final Set<String> MM_TIDESINGER = Set.of(
        "mythicmetals:tidesinger_helmet",
        "mythicmetals:tidesinger_chestplate",
        "mythicmetals:tidesinger_leggings",
        "mythicmetals:tidesinger_boots"
    );

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Inject(method = "fixStack", at = @At("TAIL"))
    private static void mythicmetals$migrateToMythicDataComponents(ItemStackComponentizationFix.StackData data, Dynamic dynamic, CallbackInfo ci) {
        if (data.itemMatches(MM_MIDAS)) {
            data.setComponent("mythicmetals:gold_folded", dynamic.emptyMap()
                .setFieldIfPresent("gold_folded", data.getAndRemove("GoldFolded").result())
                .set("is_royal", dynamic.createBoolean(false))
                .set("show_tooltip", dynamic.createBoolean(true))
            );
            data.getAndRemove("IsRoyal");
            data.getAndRemove("IsGilded");
        }

        if (data.itemEquals("mythicmetals:royal_midas_gold_sword")) {
            data.setComponent("mythicmetals:gold_folded", dynamic.emptyMap()
                .setFieldIfPresent("gold_folded", data.getAndRemove("GoldFolded").result())
                .set("is_royal", dynamic.createBoolean(true))
                .set("show_tooltip", dynamic.createBoolean(true))
            );
            data.getAndRemove("IsRoyal");
            data.getAndRemove("IsGilded");
        }

        if (data.itemMatches(MM_PROMETHEUM)) {
            data.setComponent("mythicmetals:prometheum", dynamic.emptyMap()
                .setFieldIfPresent("durability_repaired", data.getAndRemove("DurabilityRepaired").result())
            );
        }

        if (data.itemEquals("mythicmetals:carmot_staff")) {
            data.moveToComponent("Locked", "mythicmetals:locked");
            data.moveToComponent("Encore", "mythicmetals:encore");
            data.moveToComponent("IsUsed", "mythicmetals:is_used");

            data.setComponent("mythicmetals:carmot_staff_block", dynamic.emptyMap()
                .setFieldIfPresent("block", data.getAndRemove("StoredBlock").result())
                .set("show_tooltip", dynamic.createBoolean(true))
            );
        }

        if (data.itemEquals("mythicmetals:mythril_drill")) {

            var list = Util.make(new ArrayList<Dynamic<?>>(), objects -> {
                objects.add(data.getAndRemove("UpgradeSlot1").result().orElse(dynamic.emptyMap()));
                objects.add(data.getAndRemove("UpgradeSlot2").result().orElse(dynamic.emptyMap()));
            });

            data.setComponent("mythicmetals:drill", dynamic.emptyMap()
                .setFieldIfPresent("fuel", data.getAndRemove("Fuel").result())
            );

            data.setComponent("mythicmetals:upgrades", dynamic.emptyMap()
                .set("size", dynamic.createInt(2))
                .set("items", dynamic.createList(list.stream()))
            );

            data.getAndRemove("IsActive");
        }

        if (data.itemMatches(MM_TIDESINGER)) {
            data.setComponent("mythicmetals:tidesinger", dynamic.emptyMap()
                .setFieldIfPresent("pattern", data.getAndRemove("mm_coral_type").result())
            );
        }
    }
}
