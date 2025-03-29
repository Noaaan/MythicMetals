package com.mythicmetals.misc;

import io.wispforest.owo.ops.LootOps;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.loot.LootTables;
import net.minecraft.util.Identifier;
import com.mythicmetals.MythicMetals;
import com.mythicmetals.item.MythicItems;

public class MythicLootOps {
    public static final Identifier BETTER_PIGLIN_BARTERING = RegistryHelper.id("gameplay/better_piglin_bartering");
    public static final Identifier CUSTOM_PIGLIN_BARTERING = Identifier.of("custom_piglin_bartering", "mythicmetals/midas_gold_ingot");

    public static void init() {
        if (MythicMetals.CONFIG.unobtainium()) {
            LootOps.injectItem(MythicItems.Mats.UNOBTAINIUM, 0.01F, LootTables.ANCIENT_CITY_CHEST.getValue());
            LootOps.injectItem(MythicItems.Mats.UNOBTAINIUM, 0.00042F, BETTER_PIGLIN_BARTERING);
            if (FabricLoader.getInstance().isModLoaded("custom_piglin_bartering")) {
                LootOps.injectItem(MythicItems.Mats.UNOBTAINIUM, 0.00042F, CUSTOM_PIGLIN_BARTERING);
            }
        }
        LootOps.injectItem(MythicItems.Templates.UNOBTAINIUM_SMITHING_TEMPLATE, MythicMetals.CONFIG.unobtainiumTemplateChance(), LootTables.ANCIENT_CITY_CHEST.getValue());
        LootOps.injectItem(MythicItems.Templates.MYTHRIL_DRILL_SMITHING_TEMPLATE, MythicMetals.CONFIG.mythrilDrillTemplateChance(), LootTables.ABANDONED_MINESHAFT_CHEST.getValue());
        LootOps.injectItem(MythicItems.Templates.CARMOT_STAFF_SMITHING_TEMPLATE, MythicMetals.CONFIG.carmotStaffTemplateChance(), LootTables.VILLAGE_TEMPLE_CHEST.getValue());
    }
}
