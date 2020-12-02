package nourl.mythicmetals.registry;

import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import nourl.mythicmetals.MythicMetalsMain;

public class RegisterNuggets {
    //Normal Ingots
    public static final Item Adamantite_Nugget = new Item(new Item.Settings().group(MythicMetalsMain.MYTHICMETALS));
    public static final Item Aetherium_Nugget = new Item(new Item.Settings().group(MythicMetalsMain.MYTHICMETALS));
    public static final Item Aquarium_Nugget = new Item(new Item.Settings().group(MythicMetalsMain.MYTHICMETALS));
    public static final Item Argonium_Nugget = new Item(new Item.Settings().group(MythicMetalsMain.MYTHICMETALS));
    public static final Item Banglum_Nugget = new Item(new Item.Settings().group(MythicMetalsMain.MYTHICMETALS));
    public static final Item Brass_Nugget = new Item(new Item.Settings().group(MythicMetalsMain.MYTHICMETALS));
    public static final Item Bronze_Nugget = new Item(new Item.Settings().group(MythicMetalsMain.MYTHICMETALS));
    public static final Item Carmot_Nugget = new Item(new Item.Settings().group(MythicMetalsMain.MYTHICMETALS));
    public static final Item Celestium_Nugget = new Item(new Item.Settings().group(MythicMetalsMain.MYTHICMETALS));
    public static final Item Copper_Nugget = new Item(new Item.Settings().group(MythicMetalsMain.MYTHICMETALS));
    public static final Item Discordium_Nugget = new Item(new Item.Settings().group(MythicMetalsMain.MYTHICMETALS));
    public static final Item Durasteel_Nugget = new Item(new Item.Settings().group(MythicMetalsMain.MYTHICMETALS));
    public static final Item Electrum_Nugget = new Item(new Item.Settings().group(MythicMetalsMain.MYTHICMETALS));
    public static final Item Etherite_Nugget = new Item(new Item.Settings().group(MythicMetalsMain.MYTHICMETALS));
    public static final Item Ferrite_Nugget = new Item(new Item.Settings().group(MythicMetalsMain.MYTHICMETALS));
    public static final Item Kyber_Nugget = new Item(new Item.Settings().group(MythicMetalsMain.MYTHICMETALS));
    public static final Item Lutetium_Nugget = new Item(new Item.Settings().group(MythicMetalsMain.MYTHICMETALS));
    public static final Item Manganese_Nugget = new Item(new Item.Settings().group(MythicMetalsMain.MYTHICMETALS));
    public static final Item Metallurgium_Nugget = new Item(new Item.Settings().group(MythicMetalsMain.MYTHICMETALS).fireproof());
    public static final Item Midas_Gold_Nugget = new Item(new Item.Settings().group(MythicMetalsMain.MYTHICMETALS));
    public static final Item Mythril_Nugget = new Item(new Item.Settings().group(MythicMetalsMain.MYTHICMETALS));
    public static final Item Orichalcum_Nugget = new Item(new Item.Settings().group(MythicMetalsMain.MYTHICMETALS));
    public static final Item Osmium_Nugget = new Item(new Item.Settings().group(MythicMetalsMain.MYTHICMETALS));
    public static final Item Platinum_Nugget = new Item(new Item.Settings().group(MythicMetalsMain.MYTHICMETALS));
    public static final Item Prometheum_Nugget = new Item(new Item.Settings().group(MythicMetalsMain.MYTHICMETALS));
    public static final Item Quadrillum_Nugget = new Item(new Item.Settings().group(MythicMetalsMain.MYTHICMETALS));
    public static final Item Quicksilver_Nugget = new Item(new Item.Settings().group(MythicMetalsMain.MYTHICMETALS));
    public static final Item Runite_Nugget = new Item(new Item.Settings().group(MythicMetalsMain.MYTHICMETALS));
    public static final Item Silver_Nugget = new Item(new Item.Settings().group(MythicMetalsMain.MYTHICMETALS));
    public static final Item Slowsilver_Nugget = new Item(new Item.Settings().group(MythicMetalsMain.MYTHICMETALS));
    public static final Item Starrite_Nugget = new Item(new Item.Settings().group(MythicMetalsMain.MYTHICMETALS));
    public static final Item Steel_Nugget = new Item(new Item.Settings().group(MythicMetalsMain.MYTHICMETALS));
    public static final Item Stormyx_Nugget = new Item(new Item.Settings().group(MythicMetalsMain.MYTHICMETALS));
    public static final Item Tantalite_Nugget = new Item(new Item.Settings().group(MythicMetalsMain.MYTHICMETALS));
    public static final Item Tin_Nugget = new Item(new Item.Settings().group(MythicMetalsMain.MYTHICMETALS));
    public static final Item Truesilver_Nugget = new Item(new Item.Settings().group(MythicMetalsMain.MYTHICMETALS));
    public static final Item Unobtainium_Nugget = new Item(new Item.Settings().group(MythicMetalsMain.MYTHICMETALS).fireproof());
    public static final Item Ur_Nugget = new Item(new Item.Settings().group(MythicMetalsMain.MYTHICMETALS));
    public static final Item Zinc_Nugget = new Item(new Item.Settings().group(MythicMetalsMain.MYTHICMETALS));

    public static void register() {
        //Register Ingots
        Registry.register(Registry.ITEM, new Identifier("mythicmetals", "adamantite_nugget"), Adamantite_Nugget);
        Registry.register(Registry.ITEM, new Identifier("mythicmetals", "aetherium_nugget"), Aetherium_Nugget);
        Registry.register(Registry.ITEM, new Identifier("mythicmetals", "aquarium_nugget"), Aquarium_Nugget);
        Registry.register(Registry.ITEM, new Identifier("mythicmetals", "argonium_nugget"), Argonium_Nugget);
        Registry.register(Registry.ITEM, new Identifier("mythicmetals", "banglum_nugget"), Banglum_Nugget);
        Registry.register(Registry.ITEM, new Identifier("mythicmetals", "brass_nugget"), Brass_Nugget);
        Registry.register(Registry.ITEM, new Identifier("mythicmetals", "bronze_nugget"), Bronze_Nugget);
        Registry.register(Registry.ITEM, new Identifier("mythicmetals", "carmot_nugget"), Carmot_Nugget);
        Registry.register(Registry.ITEM, new Identifier("mythicmetals", "celestium_nugget"), Celestium_Nugget);
        Registry.register(Registry.ITEM, new Identifier("mythicmetals", "copper_nugget"), Copper_Nugget);
        Registry.register(Registry.ITEM, new Identifier("mythicmetals", "discordium_nugget"), Discordium_Nugget);
        Registry.register(Registry.ITEM, new Identifier("mythicmetals", "durasteel_nugget"), Durasteel_Nugget);
        Registry.register(Registry.ITEM, new Identifier("mythicmetals", "electrum_nugget"), Electrum_Nugget);
        Registry.register(Registry.ITEM, new Identifier("mythicmetals", "etherite_nugget"), Etherite_Nugget);
        Registry.register(Registry.ITEM, new Identifier("mythicmetals", "ferrite_nugget"), Ferrite_Nugget);
        Registry.register(Registry.ITEM, new Identifier("mythicmetals", "kyber_nugget"), Kyber_Nugget);
        Registry.register(Registry.ITEM, new Identifier("mythicmetals", "lutetium_nugget"), Lutetium_Nugget);
        Registry.register(Registry.ITEM, new Identifier("mythicmetals", "manganese_nugget"), Manganese_Nugget);
        Registry.register(Registry.ITEM, new Identifier("mythicmetals", "metallurgium_nugget"), Metallurgium_Nugget);
        Registry.register(Registry.ITEM, new Identifier("mythicmetals", "midas_gold_nugget"), Midas_Gold_Nugget);
        Registry.register(Registry.ITEM, new Identifier("mythicmetals", "mythril_nugget"), Mythril_Nugget);
        Registry.register(Registry.ITEM, new Identifier("mythicmetals", "orichalcum_nugget"), Orichalcum_Nugget);
        Registry.register(Registry.ITEM, new Identifier("mythicmetals", "osmium_nugget"), Osmium_Nugget);
        Registry.register(Registry.ITEM, new Identifier("mythicmetals", "platinum_nugget"), Platinum_Nugget);
        Registry.register(Registry.ITEM, new Identifier("mythicmetals", "prometheum_nugget"), Prometheum_Nugget);
        Registry.register(Registry.ITEM, new Identifier("mythicmetals", "quadrillum_nugget"), Quadrillum_Nugget);
        Registry.register(Registry.ITEM, new Identifier("mythicmetals", "quicksilver_nugget"), Quicksilver_Nugget);
        Registry.register(Registry.ITEM, new Identifier("mythicmetals", "runite_nugget"), Runite_Nugget);
        Registry.register(Registry.ITEM, new Identifier("mythicmetals", "silver_nugget"), Silver_Nugget);
        Registry.register(Registry.ITEM, new Identifier("mythicmetals", "slowsilver_nugget"), Slowsilver_Nugget);
        Registry.register(Registry.ITEM, new Identifier("mythicmetals", "starrite_nugget"), Starrite_Nugget);
        Registry.register(Registry.ITEM, new Identifier("mythicmetals", "steel_nugget"), Steel_Nugget);
        Registry.register(Registry.ITEM, new Identifier("mythicmetals", "stormyx_nugget"), Stormyx_Nugget);
        Registry.register(Registry.ITEM, new Identifier("mythicmetals", "tantalite_nugget"), Tantalite_Nugget);
        Registry.register(Registry.ITEM, new Identifier("mythicmetals", "tin_nugget"), Tin_Nugget);
        Registry.register(Registry.ITEM, new Identifier("mythicmetals", "truesilver_nugget"), Truesilver_Nugget);
        Registry.register(Registry.ITEM, new Identifier("mythicmetals", "unobtainium_nugget"), Unobtainium_Nugget);
        Registry.register(Registry.ITEM, new Identifier("mythicmetals", "ur_nugget"), Ur_Nugget);
        Registry.register(Registry.ITEM, new Identifier("mythicmetals", "zinc_nugget"), Zinc_Nugget);
    }
}
