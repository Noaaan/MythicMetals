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
    private static void mythicmetals$migrateToMythicDataComponents(ItemStackComponentizationFix.ItemStackData itemStack, Dynamic dynamic, CallbackInfo ci) {
        if (itemStack.is(MM_MIDAS)) {
            itemStack.setComponent("mythicmetals:gold_folded", dynamic.emptyMap()
                .setFieldIfPresent("gold_folded", itemStack.removeTag("GoldFolded").result())
                .set("is_royal", dynamic.createBoolean(false))
                .set("show_tooltip", dynamic.createBoolean(true))
            );
            itemStack.removeTag("IsRoyal");
            itemStack.removeTag("IsGilded");
        }

        if (itemStack.is("mythicmetals:royal_midas_gold_sword")) {
            itemStack.setComponent("mythicmetals:gold_folded", dynamic.emptyMap()
                .setFieldIfPresent("gold_folded", itemStack.removeTag("GoldFolded").result())
                .set("is_royal", dynamic.createBoolean(true))
                .set("show_tooltip", dynamic.createBoolean(true))
            );
            itemStack.removeTag("IsRoyal");
            itemStack.removeTag("IsGilded");
        }

        if (itemStack.is(MM_PROMETHEUM)) {
            itemStack.setComponent("mythicmetals:prometheum", dynamic.emptyMap()
                .setFieldIfPresent("durability_repaired", itemStack.removeTag("DurabilityRepaired").result())
            );
        }

        if (itemStack.is("mythicmetals:mythril_drill")) {

            var list = Util.make(new ArrayList<Dynamic<?>>(), objects -> {
                objects.add(itemStack.removeTag("UpgradeSlot1").result().orElse(dynamic.emptyMap()));
                objects.add(itemStack.removeTag("UpgradeSlot2").result().orElse(dynamic.emptyMap()));
            });

            itemStack.setComponent("mythicmetals:drill", dynamic.emptyMap()
                .setFieldIfPresent("fuel", itemStack.removeTag("Fuel").result())
            );

            itemStack.setComponent("mythicmetals:upgrades", dynamic.emptyMap()
                .set("size", dynamic.createInt(2))
                .set("items", dynamic.createList(list.stream()))
            );

            itemStack.removeTag("IsActive");
        }

        if (itemStack.is(MM_TIDESINGER)) {
            itemStack.setComponent("mythicmetals:tidesinger", dynamic.emptyMap()
                .setFieldIfPresent("pattern", itemStack.removeTag("mm_coral_type").result())
            );
        }
    }
}
