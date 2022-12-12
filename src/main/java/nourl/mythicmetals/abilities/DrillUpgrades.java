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
        MAP.put(MythicItems.AQUARIUM_PEARL, "Aquarium");
        MAP.put(MythicItems.STORMYX_SHELL, "Stormyx");
        MAP.put(MythicBlocks.MIDAS_GOLD.getStorageBlock().asItem(), "Midas Gold");
        MAP.put(MythicBlocks.PROMETHEUM.getStorageBlock().asItem(), "Prometheum");
        MAP.put(Items.AIR,  "Empty");
    }
}