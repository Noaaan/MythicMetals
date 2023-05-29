package nourl.mythicmetals.misc;

import io.wispforest.owo.ops.LootOps;
import net.minecraft.loot.LootTables;
import nourl.mythicmetals.MythicMetals;
import nourl.mythicmetals.item.MythicItems;

public class MythicLootOps {

    public static void init() {
        LootOps.injectItem(MythicItems.Mats.UNOBTAINIUM, 0.01F, LootTables.ANCIENT_CITY_CHEST);
        LootOps.injectItem(MythicItems.Templates.UNOBTAINIUM_SMITHING_TEMPLATE, MythicMetals.CONFIG.unobtainiumTemplateChance(), LootTables.ANCIENT_CITY_CHEST);
        LootOps.injectItem(MythicItems.Templates.MYTHRIL_DRILL_SMITHING_TEMPLATE, MythicMetals.CONFIG.mythrilDrillTemplateChance(), LootTables.ABANDONED_MINESHAFT_CHEST);
        LootOps.injectItem(MythicItems.Templates.CARMOT_STAFF_SMITHING_TEMPLATE, MythicMetals.CONFIG.carmotStaffTemplateChance(), LootTables.VILLAGE_TEMPLE_CHEST);
    }
}
