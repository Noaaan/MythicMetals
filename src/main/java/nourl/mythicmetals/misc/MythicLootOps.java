package nourl.mythicmetals.misc;

import io.wispforest.owo.ops.LootOps;
import net.minecraft.loot.LootTables;
import net.minecraft.util.Identifier;
import nourl.mythicmetals.MythicMetals;
import nourl.mythicmetals.item.MythicItems;

public class MythicLootOps {
    public static final Identifier BETTER_PIGLIN_BARTERING = RegistryHelper.id("gameplay/better_piglin_bartering");
    public static void init() {
        if (MythicMetals.CONFIG.unobtainium()) {
            LootOps.injectItem(MythicItems.Mats.UNOBTAINIUM, 0.01F, LootTables.ANCIENT_CITY_CHEST);
            LootOps.injectItem(MythicItems.Mats.UNOBTAINIUM, 0.00042F, BETTER_PIGLIN_BARTERING);
        }
        LootOps.injectItem(MythicItems.Templates.UNOBTAINIUM_SMITHING_TEMPLATE, MythicMetals.CONFIG.unobtainiumTemplateChance(), LootTables.ANCIENT_CITY_CHEST);
        LootOps.injectItem(MythicItems.Templates.MYTHRIL_DRILL_SMITHING_TEMPLATE, MythicMetals.CONFIG.mythrilDrillTemplateChance(), LootTables.ABANDONED_MINESHAFT_CHEST);
        LootOps.injectItem(MythicItems.Templates.CARMOT_STAFF_SMITHING_TEMPLATE, MythicMetals.CONFIG.carmotStaffTemplateChance(), LootTables.VILLAGE_TEMPLE_CHEST);
    }
}
