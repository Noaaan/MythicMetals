package nourl.mythicmetals.misc;

import io.wispforest.owo.ops.LootOps;
import net.minecraft.loot.LootTables;
import nourl.mythicmetals.item.MythicItems;

public class MythicLootOps {

    public static void init() {
        LootOps.injectItem(MythicItems.Mats.UNOBTAINIUM, 0.01F, LootTables.ANCIENT_CITY_CHEST);
        LootOps.injectItem(MythicItems.Templates.UNOBTAINIUM_SMITHING_TEMPLATE, 0.25F, LootTables.ANCIENT_CITY_CHEST);
        LootOps.injectItem(MythicItems.Templates.MYTHRIL_DRILL_SMITHING_TEMPLATE, 0.5F, LootTables.ABANDONED_MINESHAFT_CHEST);
    }
}
