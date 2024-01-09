package nourl.mythicmetals.abilities;

import net.minecraft.item.Item;
import net.minecraft.item.Items;
import nourl.mythicmetals.blocks.MythicBlocks;
import nourl.mythicmetals.item.MythicItems;
import java.util.HashMap;
import java.util.Map;

public class DrillUpgrades {
    /**
     * Map used to store the different types of drill upgrades
     * Used for handling tooltips
     */
    public static final Map<Item, String> MAP = new HashMap<>();

    public static void init() {
        MAP.put(MythicItems.Mats.AQUARIUM_PEARL, "aquarium");
        MAP.put(MythicItems.Mats.CARMOT_STONE, "carmot");
        MAP.put(MythicBlocks.ENCHANTED_MIDAS_GOLD_BLOCK.asItem(), "midas_gold");
        MAP.put(MythicItems.Mats.PROMETHEUM_BOUQUET, "prometheum");
        MAP.put(MythicItems.Mats.STORMYX_SHELL, "stormyx");
        MAP.put(Items.AIR, "empty");
    }
}
