package com.mythicmetals.misc;

import com.mythicmetals.MythicMetals;
import com.mythicmetals.item.MythicItems;
import com.mythicmetals.item.MythicMaterials;
import io.wispforest.owo.ops.LootOps;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;

public class MythicLootOps {
    private MythicLootOps() {
    }

    public static final Identifier BETTER_PIGLIN_BARTERING = RegistryHelper.id("gameplay/better_piglin_bartering");
    public static final Identifier CUSTOM_PIGLIN_BARTERING = Identifier.fromNamespaceAndPath("custom_piglin_bartering", "mythicmetals/midas_gold_ingot");

    public static void init() {
        if (MythicMetals.CONFIG.unobtainium()) {
            LootOps.injectItem(MythicMaterials.UNOBTAINIUM.baseMaterial(), 0.01F, BuiltInLootTables.ANCIENT_CITY.identifier());
            LootOps.injectItem(MythicMaterials.UNOBTAINIUM.baseMaterial(), 0.00042F, BETTER_PIGLIN_BARTERING);
            if (FabricLoader.getInstance().isModLoaded("custom_piglin_bartering")) {
                LootOps.injectItem(MythicMaterials.UNOBTAINIUM.baseMaterial(), 0.00042F, CUSTOM_PIGLIN_BARTERING);
            }
        }
        LootOps.injectItem(MythicItems.Templates.UNOBTAINIUM_SMITHING_TEMPLATE, MythicMetals.CONFIG.unobtainiumTemplateChance(), BuiltInLootTables.ANCIENT_CITY.identifier());
        LootOps.injectItem(MythicItems.Templates.MYTHRIL_DRILL_SMITHING_TEMPLATE, MythicMetals.CONFIG.mythrilDrillTemplateChance(), BuiltInLootTables.ABANDONED_MINESHAFT.identifier());
    }
}
