package com.mythicmetals.misc;

import com.mythicmetals.MythicMetals;
import com.mythicmetals.item.MythicItems;
import io.wispforest.owo.ops.LootOps;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;

public class MythicLootOps {
    public static final ResourceLocation BETTER_PIGLIN_BARTERING = RegistryHelper.id("gameplay/better_piglin_bartering");
    public static final ResourceLocation CUSTOM_PIGLIN_BARTERING = ResourceLocation.fromNamespaceAndPath("custom_piglin_bartering", "mythicmetals/midas_gold_ingot");

    public static void init() {
        if (MythicMetals.CONFIG.unobtainium()) {
            LootOps.injectItem(MythicItems.Mats.UNOBTAINIUM, 0.01F, BuiltInLootTables.ANCIENT_CITY.location());
            LootOps.injectItem(MythicItems.Mats.UNOBTAINIUM, 0.00042F, BETTER_PIGLIN_BARTERING);
            if (FabricLoader.getInstance().isModLoaded("custom_piglin_bartering")) {
                LootOps.injectItem(MythicItems.Mats.UNOBTAINIUM, 0.00042F, CUSTOM_PIGLIN_BARTERING);
            }
        }
        LootOps.injectItem(MythicItems.Templates.UNOBTAINIUM_SMITHING_TEMPLATE, MythicMetals.CONFIG.unobtainiumTemplateChance(), BuiltInLootTables.ANCIENT_CITY.location());
        LootOps.injectItem(MythicItems.Templates.MYTHRIL_DRILL_SMITHING_TEMPLATE, MythicMetals.CONFIG.mythrilDrillTemplateChance(), BuiltInLootTables.ABANDONED_MINESHAFT.location());
    }
}
