package nourl.mythicmetals.registry;

import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import nourl.mythicmetals.MythicMetals;

public class RegisterIngots {
    //Normal Ingots
    public static final Item ADAMANTITE_INGOT = new Item(new Item.Settings().group(MythicMetals.MYTHICMETALS));
    public static final Item AETHERIUM_INGOT = new Item(new Item.Settings().group(MythicMetals.MYTHICMETALS));
    public static final Item AQUARIUM_INGOT = new Item(new Item.Settings().group(MythicMetals.MYTHICMETALS));
    public static final Item ARGONIUM_INGOT = new Item(new Item.Settings().group(MythicMetals.MYTHICMETALS));
    public static final Item BANGLUM_INGOT = new Item(new Item.Settings().group(MythicMetals.MYTHICMETALS));
    public static final Item BRASS_INGOT = new Item(new Item.Settings().group(MythicMetals.MYTHICMETALS));
    public static final Item BRONZE_INGOT = new Item(new Item.Settings().group(MythicMetals.MYTHICMETALS));
    public static final Item CARMOT_INGOT = new Item(new Item.Settings().group(MythicMetals.MYTHICMETALS));
    public static final Item CELESTIUM_INGOT = new Item(new Item.Settings().group(MythicMetals.MYTHICMETALS));
    public static final Item COPPER_INGOT = new Item(new Item.Settings().group(MythicMetals.MYTHICMETALS));
    public static final Item DISCORDIUM_INGOT = new Item(new Item.Settings().group(MythicMetals.MYTHICMETALS));
    public static final Item DURASTEEL_INGOT = new Item(new Item.Settings().group(MythicMetals.MYTHICMETALS));
    public static final Item ELECTRUM_INGOT = new Item(new Item.Settings().group(MythicMetals.MYTHICMETALS));
    public static final Item ETHERITE_INGOT = new Item(new Item.Settings().group(MythicMetals.MYTHICMETALS));
    public static final Item HALLOWED_INGOT = new Item(new Item.Settings().group(MythicMetals.MYTHICMETALS));
    public static final Item KYBER_INGOT = new Item(new Item.Settings().group(MythicMetals.MYTHICMETALS));
    public static final Item MANGANESE_INGOT = new Item(new Item.Settings().group(MythicMetals.MYTHICMETALS));
    public static final Item METALLURGIUM_INGOT = new Item(new Item.Settings().group(MythicMetals.MYTHICMETALS).fireproof());
    public static final Item MIDAS_GOLD_INGOT = new Item(new Item.Settings().group(MythicMetals.MYTHICMETALS));
    public static final Item MYTHRIL_INGOT = new Item(new Item.Settings().group(MythicMetals.MYTHICMETALS));
    public static final Item ORICHALCUM_INGOT = new Item(new Item.Settings().group(MythicMetals.MYTHICMETALS));
    public static final Item OSMIUM_INGOT = new Item(new Item.Settings().group(MythicMetals.MYTHICMETALS));
    public static final Item PLATINUM_INGOT = new Item(new Item.Settings().group(MythicMetals.MYTHICMETALS));
    public static final Item PROMETHEUM_INGOT = new Item(new Item.Settings().group(MythicMetals.MYTHICMETALS));
    public static final Item QUADRILLUM_INGOT = new Item(new Item.Settings().group(MythicMetals.MYTHICMETALS));
    public static final Item QUICKSILVER_INGOT = new Item(new Item.Settings().group(MythicMetals.MYTHICMETALS));
    public static final Item RUNITE_INGOT = new Item(new Item.Settings().group(MythicMetals.MYTHICMETALS));
    public static final Item SILVER_INGOT = new Item(new Item.Settings().group(MythicMetals.MYTHICMETALS));
    public static final Item SLOWSILVER_INGOT = new Item(new Item.Settings().group(MythicMetals.MYTHICMETALS));
    public static final Item STARRITE_INGOT = new Item(new Item.Settings().group(MythicMetals.MYTHICMETALS));
    public static final Item STEEL_INGOT = new Item(new Item.Settings().group(MythicMetals.MYTHICMETALS));
    public static final Item STORMYX_INGOT = new Item(new Item.Settings().group(MythicMetals.MYTHICMETALS));
    public static final Item TANTALITE_INGOT = new Item(new Item.Settings().group(MythicMetals.MYTHICMETALS));
    public static final Item TIN_INGOT = new Item(new Item.Settings().group(MythicMetals.MYTHICMETALS));
    public static final Item TRUESILVER_INGOT = new Item(new Item.Settings().group(MythicMetals.MYTHICMETALS));
    public static final Item UNOBTAINIUM_INGOT = new Item(new Item.Settings().group(MythicMetals.MYTHICMETALS).fireproof());
    public static final Item UR_INGOT = new Item(new Item.Settings().group(MythicMetals.MYTHICMETALS));
    public static final Item ZINC_INGOT = new Item(new Item.Settings().group(MythicMetals.MYTHICMETALS));


    public static void register() {
        //Register Ingots
        Registry.register(Registry.ITEM, new Identifier(MythicMetals.MOD_ID, "adamantite_ingot"), ADAMANTITE_INGOT);
        Registry.register(Registry.ITEM, new Identifier(MythicMetals.MOD_ID, "aetherium_ingot"), AETHERIUM_INGOT);
        Registry.register(Registry.ITEM, new Identifier(MythicMetals.MOD_ID, "aquarium_ingot"), AQUARIUM_INGOT);
        Registry.register(Registry.ITEM, new Identifier(MythicMetals.MOD_ID, "argonium_ingot"), ARGONIUM_INGOT);
        Registry.register(Registry.ITEM, new Identifier(MythicMetals.MOD_ID, "banglum_ingot"), BANGLUM_INGOT);
        Registry.register(Registry.ITEM, new Identifier(MythicMetals.MOD_ID, "brass_ingot"), BRASS_INGOT);
        Registry.register(Registry.ITEM, new Identifier(MythicMetals.MOD_ID, "bronze_ingot"), BRONZE_INGOT);
        Registry.register(Registry.ITEM, new Identifier(MythicMetals.MOD_ID, "carmot_ingot"), CARMOT_INGOT);
        Registry.register(Registry.ITEM, new Identifier(MythicMetals.MOD_ID, "celestium_ingot"), CELESTIUM_INGOT);
        Registry.register(Registry.ITEM, new Identifier(MythicMetals.MOD_ID, "copper_ingot"), COPPER_INGOT);
        Registry.register(Registry.ITEM, new Identifier(MythicMetals.MOD_ID, "discordium_ingot"), DISCORDIUM_INGOT);
        Registry.register(Registry.ITEM, new Identifier(MythicMetals.MOD_ID, "durasteel_ingot"), DURASTEEL_INGOT);
        Registry.register(Registry.ITEM, new Identifier(MythicMetals.MOD_ID, "electrum_ingot"), ELECTRUM_INGOT);
        Registry.register(Registry.ITEM, new Identifier(MythicMetals.MOD_ID, "etherite_ingot"), ETHERITE_INGOT);
        Registry.register(Registry.ITEM, new Identifier(MythicMetals.MOD_ID, "hallowed_ingot"), HALLOWED_INGOT);
        Registry.register(Registry.ITEM, new Identifier(MythicMetals.MOD_ID, "kyber_ingot"), KYBER_INGOT);
        Registry.register(Registry.ITEM, new Identifier(MythicMetals.MOD_ID, "manganese_ingot"), MANGANESE_INGOT);
        Registry.register(Registry.ITEM, new Identifier(MythicMetals.MOD_ID, "metallurgium_ingot"), METALLURGIUM_INGOT);
        Registry.register(Registry.ITEM, new Identifier(MythicMetals.MOD_ID, "midas_gold_ingot"), MIDAS_GOLD_INGOT);
        Registry.register(Registry.ITEM, new Identifier(MythicMetals.MOD_ID, "mythril_ingot"), MYTHRIL_INGOT);
        Registry.register(Registry.ITEM, new Identifier(MythicMetals.MOD_ID, "orichalcum_ingot"), ORICHALCUM_INGOT);
        Registry.register(Registry.ITEM, new Identifier(MythicMetals.MOD_ID, "osmium_ingot"), OSMIUM_INGOT);
        Registry.register(Registry.ITEM, new Identifier(MythicMetals.MOD_ID, "platinum_ingot"), PLATINUM_INGOT);
        Registry.register(Registry.ITEM, new Identifier(MythicMetals.MOD_ID, "prometheum_ingot"), PROMETHEUM_INGOT);
        Registry.register(Registry.ITEM, new Identifier(MythicMetals.MOD_ID, "quadrillum_ingot"), QUADRILLUM_INGOT);
        Registry.register(Registry.ITEM, new Identifier(MythicMetals.MOD_ID, "quicksilver_ingot"), QUICKSILVER_INGOT);
        Registry.register(Registry.ITEM, new Identifier(MythicMetals.MOD_ID, "runite_ingot"), RUNITE_INGOT);
        Registry.register(Registry.ITEM, new Identifier(MythicMetals.MOD_ID, "silver_ingot"), SILVER_INGOT);
        Registry.register(Registry.ITEM, new Identifier(MythicMetals.MOD_ID, "slowsilver_ingot"), SLOWSILVER_INGOT);
        Registry.register(Registry.ITEM, new Identifier(MythicMetals.MOD_ID, "starrite_ingot"), STARRITE_INGOT);
        Registry.register(Registry.ITEM, new Identifier(MythicMetals.MOD_ID, "steel_ingot"), STEEL_INGOT);
        Registry.register(Registry.ITEM, new Identifier(MythicMetals.MOD_ID, "stormyx_ingot"), STORMYX_INGOT);
        Registry.register(Registry.ITEM, new Identifier(MythicMetals.MOD_ID, "tantalite_ingot"), TANTALITE_INGOT);
        Registry.register(Registry.ITEM, new Identifier(MythicMetals.MOD_ID, "tin_ingot"), TIN_INGOT);
        Registry.register(Registry.ITEM, new Identifier(MythicMetals.MOD_ID, "truesilver_ingot"), TRUESILVER_INGOT);
        Registry.register(Registry.ITEM, new Identifier(MythicMetals.MOD_ID, "unobtainium_ingot"), UNOBTAINIUM_INGOT);
        Registry.register(Registry.ITEM, new Identifier(MythicMetals.MOD_ID, "ur_ingot"), UR_INGOT);
        Registry.register(Registry.ITEM, new Identifier(MythicMetals.MOD_ID, "zinc_ingot"), ZINC_INGOT);

    }
}
