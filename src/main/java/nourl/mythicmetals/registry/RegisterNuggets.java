package nourl.mythicmetals.registry;

import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import nourl.mythicmetals.MythicMetals;

public class RegisterNuggets {
    public static final Item ADAMANTITE_NUGGET = new Item(new Item.Settings().group(MythicMetals.MYTHICMETALS));
    public static final Item AETHERIUM_NUGGET = new Item(new Item.Settings().group(MythicMetals.MYTHICMETALS));
    public static final Item AQUARIUM_NUGGET = new Item(new Item.Settings().group(MythicMetals.MYTHICMETALS));
    public static final Item ARGONIUM_NUGGET = new Item(new Item.Settings().group(MythicMetals.MYTHICMETALS));
    public static final Item BANGLUM_NUGGET = new Item(new Item.Settings().group(MythicMetals.MYTHICMETALS));
    public static final Item BRONZE_NUGGET = new Item(new Item.Settings().group(MythicMetals.MYTHICMETALS));
    public static final Item CARMOT_NUGGET = new Item(new Item.Settings().group(MythicMetals.MYTHICMETALS));
    public static final Item CELESTIUM_NUGGET = new Item(new Item.Settings().group(MythicMetals.MYTHICMETALS));
    public static final Item COPPER_NUGGET = new Item(new Item.Settings().group(MythicMetals.MYTHICMETALS));
    public static final Item DISCORDIUM_NUGGET = new Item(new Item.Settings().group(MythicMetals.MYTHICMETALS));
    public static final Item DURASTEEL_NUGGET = new Item(new Item.Settings().group(MythicMetals.MYTHICMETALS));
    public static final Item ELECTRUM_NUGGET = new Item(new Item.Settings().group(MythicMetals.MYTHICMETALS));
    public static final Item ETHERITE_NUGGET = new Item(new Item.Settings().group(MythicMetals.MYTHICMETALS));
    public static final Item HALLOWED_NUGGET = new Item(new Item.Settings().group(MythicMetals.MYTHICMETALS));
    public static final Item KYBER_NUGGET = new Item(new Item.Settings().group(MythicMetals.MYTHICMETALS));
    public static final Item MANGANESE_NUGGET = new Item(new Item.Settings().group(MythicMetals.MYTHICMETALS));
    public static final Item METALLURGIUM_NUGGET = new Item(new Item.Settings().group(MythicMetals.MYTHICMETALS).fireproof());
    public static final Item MIDAS_GOLD_NUGGET = new Item(new Item.Settings().group(MythicMetals.MYTHICMETALS));
    public static final Item MYTHRIL_NUGGET = new Item(new Item.Settings().group(MythicMetals.MYTHICMETALS));
    public static final Item ORICHALCUM_NUGGET = new Item(new Item.Settings().group(MythicMetals.MYTHICMETALS));
    public static final Item OSMIUM_NUGGET = new Item(new Item.Settings().group(MythicMetals.MYTHICMETALS));
    public static final Item PLATINUM_NUGGET = new Item(new Item.Settings().group(MythicMetals.MYTHICMETALS));
    public static final Item PROMETHEUM_NUGGET = new Item(new Item.Settings().group(MythicMetals.MYTHICMETALS));
    public static final Item QUADRILLUM_NUGGET = new Item(new Item.Settings().group(MythicMetals.MYTHICMETALS));
    public static final Item QUICKSILVER_NUGGET = new Item(new Item.Settings().group(MythicMetals.MYTHICMETALS));
    public static final Item RUNITE_NUGGET = new Item(new Item.Settings().group(MythicMetals.MYTHICMETALS));
    public static final Item SILVER_NUGGET = new Item(new Item.Settings().group(MythicMetals.MYTHICMETALS));
    public static final Item STARRITE_NUGGET = new Item(new Item.Settings().group(MythicMetals.MYTHICMETALS));
    public static final Item STEEL_NUGGET = new Item(new Item.Settings().group(MythicMetals.MYTHICMETALS));
    public static final Item STORMYX_NUGGET = new Item(new Item.Settings().group(MythicMetals.MYTHICMETALS));
    public static final Item TIN_NUGGET = new Item(new Item.Settings().group(MythicMetals.MYTHICMETALS));
    public static final Item TRUESILVER_NUGGET = new Item(new Item.Settings().group(MythicMetals.MYTHICMETALS));

    public static void register() {
        Registry.register(Registry.ITEM, new Identifier(MythicMetals.MOD_ID, "adamantite_nugget"), ADAMANTITE_NUGGET);
        Registry.register(Registry.ITEM, new Identifier(MythicMetals.MOD_ID, "aetherium_nugget"), AETHERIUM_NUGGET);
        Registry.register(Registry.ITEM, new Identifier(MythicMetals.MOD_ID, "aquarium_nugget"), AQUARIUM_NUGGET);
        Registry.register(Registry.ITEM, new Identifier(MythicMetals.MOD_ID, "argonium_nugget"), ARGONIUM_NUGGET);
        Registry.register(Registry.ITEM, new Identifier(MythicMetals.MOD_ID, "banglum_nugget"), BANGLUM_NUGGET);
        Registry.register(Registry.ITEM, new Identifier(MythicMetals.MOD_ID, "bronze_nugget"), BRONZE_NUGGET);
        Registry.register(Registry.ITEM, new Identifier(MythicMetals.MOD_ID, "carmot_nugget"), CARMOT_NUGGET);
        Registry.register(Registry.ITEM, new Identifier(MythicMetals.MOD_ID, "celestium_nugget"), CELESTIUM_NUGGET);
        Registry.register(Registry.ITEM, new Identifier(MythicMetals.MOD_ID, "copper_nugget"), COPPER_NUGGET);
        Registry.register(Registry.ITEM, new Identifier(MythicMetals.MOD_ID, "discordium_nugget"), DISCORDIUM_NUGGET);
        Registry.register(Registry.ITEM, new Identifier(MythicMetals.MOD_ID, "durasteel_nugget"), DURASTEEL_NUGGET);
        Registry.register(Registry.ITEM, new Identifier(MythicMetals.MOD_ID, "electrum_nugget"), ELECTRUM_NUGGET);
        Registry.register(Registry.ITEM, new Identifier(MythicMetals.MOD_ID, "etherite_nugget"), ETHERITE_NUGGET);
        Registry.register(Registry.ITEM, new Identifier(MythicMetals.MOD_ID, "hallowed_nugget"), HALLOWED_NUGGET);
        Registry.register(Registry.ITEM, new Identifier(MythicMetals.MOD_ID, "kyber_nugget"), KYBER_NUGGET);
        Registry.register(Registry.ITEM, new Identifier(MythicMetals.MOD_ID, "manganese_nugget"), MANGANESE_NUGGET);
        Registry.register(Registry.ITEM, new Identifier(MythicMetals.MOD_ID, "metallurgium_nugget"), METALLURGIUM_NUGGET);
        Registry.register(Registry.ITEM, new Identifier(MythicMetals.MOD_ID, "midas_gold_nugget"), MIDAS_GOLD_NUGGET);
        Registry.register(Registry.ITEM, new Identifier(MythicMetals.MOD_ID, "mythril_nugget"), MYTHRIL_NUGGET);
        Registry.register(Registry.ITEM, new Identifier(MythicMetals.MOD_ID, "orichalcum_nugget"), ORICHALCUM_NUGGET);
        Registry.register(Registry.ITEM, new Identifier(MythicMetals.MOD_ID, "osmium_nugget"), OSMIUM_NUGGET);
        Registry.register(Registry.ITEM, new Identifier(MythicMetals.MOD_ID, "platinum_nugget"), PLATINUM_NUGGET);
        Registry.register(Registry.ITEM, new Identifier(MythicMetals.MOD_ID, "prometheum_nugget"), PROMETHEUM_NUGGET);
        Registry.register(Registry.ITEM, new Identifier(MythicMetals.MOD_ID, "quadrillum_nugget"), QUADRILLUM_NUGGET);
        Registry.register(Registry.ITEM, new Identifier(MythicMetals.MOD_ID, "quicksilver_nugget"), QUICKSILVER_NUGGET);
        Registry.register(Registry.ITEM, new Identifier(MythicMetals.MOD_ID, "runite_nugget"), RUNITE_NUGGET);
        Registry.register(Registry.ITEM, new Identifier(MythicMetals.MOD_ID, "silver_nugget"), SILVER_NUGGET);
        Registry.register(Registry.ITEM, new Identifier(MythicMetals.MOD_ID, "starrite_nugget"), STARRITE_NUGGET);
        Registry.register(Registry.ITEM, new Identifier(MythicMetals.MOD_ID, "steel_nugget"), STEEL_NUGGET);
        Registry.register(Registry.ITEM, new Identifier(MythicMetals.MOD_ID, "stormyx_nugget"), STORMYX_NUGGET);
        Registry.register(Registry.ITEM, new Identifier(MythicMetals.MOD_ID, "tin_nugget"), TIN_NUGGET);
        Registry.register(Registry.ITEM, new Identifier(MythicMetals.MOD_ID, "truesilver_nugget"), TRUESILVER_NUGGET);
    }
}
