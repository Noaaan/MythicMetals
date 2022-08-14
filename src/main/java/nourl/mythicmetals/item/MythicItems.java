package nourl.mythicmetals.item;

import io.wispforest.owo.itemgroup.OwoItemSettings;
import io.wispforest.owo.registration.reflect.ItemRegistryContainer;
import net.minecraft.item.Item;
import net.minecraft.item.MusicDiscItem;
import net.minecraft.sound.SoundEvent;
import net.minecraft.text.MutableText;
import net.minecraft.util.Formatting;
import net.minecraft.util.Rarity;
import nourl.mythicmetals.MythicMetals;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("unused")
public class MythicItems implements ItemRegistryContainer {

    //Normal Ingots
    public static final class Ingots implements ItemRegistryContainer {
        public static Map<String, Item> INGOTS = new HashMap<>();

        public static final Item ADAMANTITE_INGOT = new Item(new OwoItemSettings().group(MythicMetals.TABBED_GROUP));
        public static final Item AQUARIUM_INGOT = new Item(new OwoItemSettings().group(MythicMetals.TABBED_GROUP));
        public static final Item BANGLUM_INGOT = new Item(new OwoItemSettings().group(MythicMetals.TABBED_GROUP));
        public static final Item BRONZE_INGOT = new Item(new OwoItemSettings().group(MythicMetals.TABBED_GROUP));
        public static final Item CARMOT_INGOT = new Item(new OwoItemSettings().group(MythicMetals.TABBED_GROUP));
        public static final Item CELESTIUM_INGOT = new Item(new OwoItemSettings().group(MythicMetals.TABBED_GROUP).rarity(Rarity.RARE));
        public static final Item DURASTEEL_INGOT = new Item(new OwoItemSettings().group(MythicMetals.TABBED_GROUP));
        public static final Item HALLOWED_INGOT = new Item(new OwoItemSettings().group(MythicMetals.TABBED_GROUP).rarity(Rarity.UNCOMMON));
        public static final Item KYBER_INGOT = new Item(new OwoItemSettings().group(MythicMetals.TABBED_GROUP));
        public static final Item MANGANESE_INGOT = new Item(new OwoItemSettings().group(MythicMetals.TABBED_GROUP));
        public static final Item METALLURGIUM_INGOT = new Item(new OwoItemSettings().group(MythicMetals.TABBED_GROUP).fireproof().rarity(Rarity.RARE));
        public static final Item MORKITE = new Item(new OwoItemSettings().group(MythicMetals.TABBED_GROUP));
        public static final Item MIDAS_GOLD_INGOT = new Item(new OwoItemSettings().group(MythicMetals.TABBED_GROUP));
        public static final Item MYTHRIL_INGOT = new Item(new OwoItemSettings().group(MythicMetals.TABBED_GROUP));
        public static final Item ORICHALCUM_INGOT = new Item(new OwoItemSettings().group(MythicMetals.TABBED_GROUP));
        public static final Item OSMIUM_INGOT = new Item(new OwoItemSettings().group(MythicMetals.TABBED_GROUP));
        public static final Item PALLADIUM_INGOT = new Item(new OwoItemSettings().group(MythicMetals.TABBED_GROUP).fireproof());
        public static final Item PLATINUM_INGOT = new Item(new OwoItemSettings().group(MythicMetals.TABBED_GROUP));
        public static final Item PROMETHEUM_INGOT = new Item(new OwoItemSettings().group(MythicMetals.TABBED_GROUP));
        public static final Item QUADRILLUM_INGOT = new Item(new OwoItemSettings().group(MythicMetals.TABBED_GROUP));
        public static final Item RUNITE_INGOT = new Item(new OwoItemSettings().group(MythicMetals.TABBED_GROUP));
        public static final Item SILVER_INGOT = new Item(new OwoItemSettings().group(MythicMetals.TABBED_GROUP));
        public static final Item STARRITE = new Item(new OwoItemSettings().group(MythicMetals.TABBED_GROUP));
        public static final Item STAR_PLATINUM = new Item(new OwoItemSettings().group(MythicMetals.TABBED_GROUP));
        public static final Item STEEL_INGOT = new Item(new OwoItemSettings().group(MythicMetals.TABBED_GROUP));
        public static final Item STORMYX_INGOT = new Item(new OwoItemSettings().group(MythicMetals.TABBED_GROUP));
        public static final Item TIN_INGOT = new Item(new OwoItemSettings().group(MythicMetals.TABBED_GROUP));
        public static final Item UNOBTAINIUM = new Item(new OwoItemSettings().group(MythicMetals.TABBED_GROUP).fireproof().rarity(Rarity.UNCOMMON));

        @Override
        public void postProcessField(String namespace, Item value, String path, Field field) {
            INGOTS.put(path.replace("_ingot", ""), value);
        }
    }

    // Raw Ores
    public static final class RawOres implements ItemRegistryContainer {
        public static Map<String, Item> RAW_ORES = new HashMap<>();

        public static final Item RAW_ADAMANTITE = new Item(new OwoItemSettings().group(MythicMetals.TABBED_GROUP));
        public static final Item RAW_AQUARIUM = new Item(new OwoItemSettings().group(MythicMetals.TABBED_GROUP));
        public static final Item RAW_BANGLUM = new Item(new OwoItemSettings().group(MythicMetals.TABBED_GROUP));
        public static final Item RAW_CARMOT = new Item(new OwoItemSettings().group(MythicMetals.TABBED_GROUP));
        public static final Item RAW_KYBER = new Item(new OwoItemSettings().group(MythicMetals.TABBED_GROUP));
        public static final Item RAW_MANGANESE = new Item(new OwoItemSettings().group(MythicMetals.TABBED_GROUP));
        public static final Item RAW_MIDAS_GOLD = new Item(new OwoItemSettings().group(MythicMetals.TABBED_GROUP));
        public static final Item RAW_MYTHRIL = new Item(new OwoItemSettings().group(MythicMetals.TABBED_GROUP));
        public static final Item RAW_ORICHALCUM = new Item(new OwoItemSettings().group(MythicMetals.TABBED_GROUP));
        public static final Item RAW_OSMIUM = new Item(new OwoItemSettings().group(MythicMetals.TABBED_GROUP));
        public static final Item RAW_PALLADIUM = new Item(new OwoItemSettings().group(MythicMetals.TABBED_GROUP).fireproof());
        public static final Item RAW_PLATINUM = new Item(new OwoItemSettings().group(MythicMetals.TABBED_GROUP));
        public static final Item RAW_PROMETHEUM = new Item(new OwoItemSettings().group(MythicMetals.TABBED_GROUP));
        public static final Item RAW_QUADRILLUM = new Item(new OwoItemSettings().group(MythicMetals.TABBED_GROUP));
        public static final Item RAW_RUNITE = new Item(new OwoItemSettings().group(MythicMetals.TABBED_GROUP));
        public static final Item RAW_SILVER = new Item(new OwoItemSettings().group(MythicMetals.TABBED_GROUP));
        public static final Item RAW_STORMYX = new Item(new OwoItemSettings().group(MythicMetals.TABBED_GROUP));
        public static final Item RAW_TIN = new Item(new OwoItemSettings().group(MythicMetals.TABBED_GROUP));

        @Override
        public void postProcessField(String namespace, Item value, String path, Field field) {
            RAW_ORES.put(path.replace("raw_", ""), value);
        }
    }

    // Nuggets
    public static final class Nuggets implements ItemRegistryContainer {
        public static final Item ADAMANTITE_NUGGET = new Item(new OwoItemSettings().group(MythicMetals.TABBED_GROUP).tab(0));
        public static final Item AQUARIUM_NUGGET = new Item(new OwoItemSettings().group(MythicMetals.TABBED_GROUP).tab(0));
        public static final Item BANGLUM_NUGGET = new Item(new OwoItemSettings().group(MythicMetals.TABBED_GROUP).tab(0));
        public static final Item BRONZE_NUGGET = new Item(new OwoItemSettings().group(MythicMetals.TABBED_GROUP).tab(0));
        public static final Item CARMOT_NUGGET = new Item(new OwoItemSettings().group(MythicMetals.TABBED_GROUP).tab(0));
        public static final Item CELESTIUM_NUGGET = new Item(new OwoItemSettings().group(MythicMetals.TABBED_GROUP).tab(0).rarity(Rarity.RARE));
        public static final Item COPPER_NUGGET = new Item(new OwoItemSettings().group(MythicMetals.TABBED_GROUP).tab(0));
        public static final Item DURASTEEL_NUGGET = new Item(new OwoItemSettings().group(MythicMetals.TABBED_GROUP).tab(0));
        public static final Item HALLOWED_NUGGET = new Item(new OwoItemSettings().group(MythicMetals.TABBED_GROUP).tab(0).rarity(Rarity.UNCOMMON));
        public static final Item KYBER_NUGGET = new Item(new OwoItemSettings().group(MythicMetals.TABBED_GROUP).tab(0));
        public static final Item MANGANESE_NUGGET = new Item(new OwoItemSettings().group(MythicMetals.TABBED_GROUP).tab(0));
        public static final Item METALLURGIUM_NUGGET = new Item(new OwoItemSettings().group(MythicMetals.TABBED_GROUP).tab(0).fireproof().rarity(Rarity.RARE));
        public static final Item MIDAS_GOLD_NUGGET = new Item(new OwoItemSettings().group(MythicMetals.TABBED_GROUP).tab(0));
        public static final Item MYTHRIL_NUGGET = new Item(new OwoItemSettings().group(MythicMetals.TABBED_GROUP).tab(0));
        public static final Item ORICHALCUM_NUGGET = new Item(new OwoItemSettings().group(MythicMetals.TABBED_GROUP).tab(0));
        public static final Item OSMIUM_NUGGET = new Item(new OwoItemSettings().group(MythicMetals.TABBED_GROUP).tab(0));
        public static final Item PALLADIUM_NUGGET = new Item(new OwoItemSettings().group(MythicMetals.TABBED_GROUP).tab(0).fireproof());
        public static final Item PLATINUM_NUGGET = new Item(new OwoItemSettings().group(MythicMetals.TABBED_GROUP).tab(0));
        public static final Item PROMETHEUM_NUGGET = new Item(new OwoItemSettings().group(MythicMetals.TABBED_GROUP).tab(0));
        public static final Item QUADRILLUM_NUGGET = new Item(new OwoItemSettings().group(MythicMetals.TABBED_GROUP).tab(0));
        public static final Item RUNITE_NUGGET = new Item(new OwoItemSettings().group(MythicMetals.TABBED_GROUP).tab(0));
        public static final Item SILVER_NUGGET = new Item(new OwoItemSettings().group(MythicMetals.TABBED_GROUP).tab(0));
        public static final Item STAR_PLATINUM_NUGGET = new Item(new OwoItemSettings().group(MythicMetals.TABBED_GROUP).tab(0));
        public static final Item STEEL_NUGGET = new Item(new OwoItemSettings().group(MythicMetals.TABBED_GROUP).tab(0));
        public static final Item STORMYX_NUGGET = new Item(new OwoItemSettings().group(MythicMetals.TABBED_GROUP).tab(0));
        public static final Item TIN_NUGGET = new Item(new OwoItemSettings().group(MythicMetals.TABBED_GROUP).tab(0));

        @Override
        public boolean shouldProcessField(Item value, String identifier, Field field) {
            return MythicMetals.CONFIG.enableNuggets;
        }
    }

    public static final class Dusts implements ItemRegistryContainer {
        public static final Item ADAMANTITE_DUST = new Item(new OwoItemSettings().group(MythicMetals.TABBED_GROUP));
        public static final Item AQUARIUM_DUST = new Item(new OwoItemSettings().group(MythicMetals.TABBED_GROUP));
        public static final Item BANGLUM_DUST = new Item(new OwoItemSettings().group(MythicMetals.TABBED_GROUP));
        public static final Item BRONZE_DUST = new Item(new OwoItemSettings().group(MythicMetals.TABBED_GROUP));
        public static final Item CARMOT_DUST = new Item(new OwoItemSettings().group(MythicMetals.TABBED_GROUP));
        public static final Item CELESTIUM_DUST = new Item(new OwoItemSettings().group(MythicMetals.TABBED_GROUP).rarity(Rarity.RARE));
        public static final Item COPPER_DUST = new Item(new OwoItemSettings().group(MythicMetals.TABBED_GROUP));
        public static final Item DURASTEEL_DUST = new Item(new OwoItemSettings().group(MythicMetals.TABBED_GROUP));
        public static final Item GOLD_DUST = new Item(new OwoItemSettings().group(MythicMetals.TABBED_GROUP));
        public static final Item HALLOWED_DUST = new Item(new OwoItemSettings().group(MythicMetals.TABBED_GROUP).rarity(Rarity.UNCOMMON));
        public static final Item KYBER_DUST = new Item(new OwoItemSettings().group(MythicMetals.TABBED_GROUP));
        public static final Item MANGANESE_DUST = new Item(new OwoItemSettings().group(MythicMetals.TABBED_GROUP));
        public static final Item METALLURGIUM_DUST = new Item(new OwoItemSettings().group(MythicMetals.TABBED_GROUP).fireproof().rarity(Rarity.RARE));
        public static final Item MIDAS_GOLD_DUST = new Item(new OwoItemSettings().group(MythicMetals.TABBED_GROUP));
        public static final Item MYTHRIL_DUST = new Item(new OwoItemSettings().group(MythicMetals.TABBED_GROUP));
        public static final Item ORICHALCUM_DUST = new Item(new OwoItemSettings().group(MythicMetals.TABBED_GROUP));
        public static final Item OSMIUM_DUST = new Item(new OwoItemSettings().group(MythicMetals.TABBED_GROUP));
        public static final Item PALLADIUM_DUST = new Item(new OwoItemSettings().group(MythicMetals.TABBED_GROUP).fireproof());
        public static final Item PLATINUM_DUST = new Item(new OwoItemSettings().group(MythicMetals.TABBED_GROUP));
        public static final Item PROMETHEUM_DUST = new Item(new OwoItemSettings().group(MythicMetals.TABBED_GROUP));
        public static final Item QUADRILLUM_DUST = new Item(new OwoItemSettings().group(MythicMetals.TABBED_GROUP));
        public static final Item RUNITE_DUST = new Item(new OwoItemSettings().group(MythicMetals.TABBED_GROUP));
        public static final Item SILVER_DUST = new Item(new OwoItemSettings().group(MythicMetals.TABBED_GROUP));
        public static final Item STAR_PLATINUM_DUST = new Item(new OwoItemSettings().group(MythicMetals.TABBED_GROUP));
        public static final Item STEEL_DUST = new Item(new OwoItemSettings().group(MythicMetals.TABBED_GROUP));
        public static final Item STORMYX_DUST = new Item(new OwoItemSettings().group(MythicMetals.TABBED_GROUP));
        public static final Item TIN_DUST = new Item(new OwoItemSettings().group(MythicMetals.TABBED_GROUP));

        @Override
        public boolean shouldProcessField(Item value, String identifier, Field field) {
            return MythicMetals.CONFIG.enableDusts;
        }
    }

    // Crafting Mats
    public static final Item AQUARIUM_PEARL = new Item(new OwoItemSettings().group(MythicMetals.TABBED_GROUP));
    public static final Item BANGLUM_CHUNK = new Item(new OwoItemSettings().group(MythicMetals.TABBED_GROUP));

    public static class CustomMusicDiscItem extends MusicDiscItem {
        public CustomMusicDiscItem(int comparatorOutput, SoundEvent sound, Settings settings) {
            super(comparatorOutput, sound, settings);
        }

        @Override
        public MutableText getDescription() {
            return super.getDescription().formatted(Formatting.ITALIC);
        }
    }

}
