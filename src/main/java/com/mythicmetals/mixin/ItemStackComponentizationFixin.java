package com.mythicmetals.mixin;

import com.mojang.serialization.Dynamic;
import net.minecraft.util.Util;
import net.minecraft.util.datafix.fixes.ItemStackComponentizationFix;
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
    @Inject(method = "fixItemStack", at = @At("TAIL"))
    private static void mythicmetals$migrateToMythicDataComponents(ItemStackComponentizationFix.ItemStackData data, Dynamic dynamic, CallbackInfo ci) {
        if (data.is(MM_MIDAS)) {
            data.setComponent("mythicmetals:gold_folded", dynamic.emptyMap()
                .setFieldIfPresent("gold_folded", data.removeTag("GoldFolded").result())
                .set("is_royal", dynamic.createBoolean(false))
                .set("show_tooltip", dynamic.createBoolean(true))
            );
            data.removeTag("IsRoyal");
            data.removeTag("IsGilded");
        }

        if (data.is("mythicmetals:royal_midas_gold_sword")) {
            data.setComponent("mythicmetals:gold_folded", dynamic.emptyMap()
                .setFieldIfPresent("gold_folded", data.removeTag("GoldFolded").result())
                .set("is_royal", dynamic.createBoolean(true))
                .set("show_tooltip", dynamic.createBoolean(true))
            );
            data.removeTag("IsRoyal");
            data.removeTag("IsGilded");
        }

        if (data.is(MM_PROMETHEUM)) {
            data.setComponent("mythicmetals:prometheum", dynamic.emptyMap()
                .setFieldIfPresent("durability_repaired", data.removeTag("DurabilityRepaired").result())
            );
        }

        if (data.is("mythicmetals:mythril_drill")) {

            var list = Util.make(new ArrayList<Dynamic<?>>(), objects -> {
                objects.add(data.removeTag("UpgradeSlot1").result().orElse(dynamic.emptyMap()));
                objects.add(data.removeTag("UpgradeSlot2").result().orElse(dynamic.emptyMap()));
            });

            data.setComponent("mythicmetals:drill", dynamic.emptyMap()
                .setFieldIfPresent("fuel", data.removeTag("Fuel").result())
            );

            data.setComponent("mythicmetals:upgrades", dynamic.emptyMap()
                .set("size", dynamic.createInt(2))
                .set("items", dynamic.createList(list.stream()))
            );

            data.removeTag("IsActive");
        }

        if (data.is(MM_TIDESINGER)) {
            data.setComponent("mythicmetals:tidesinger", dynamic.emptyMap()
                .setFieldIfPresent("pattern", data.removeTag("mm_coral_type").result())
            );
        }
    }
}
