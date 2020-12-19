package nourl.mythicmetals.registry;

import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import nourl.mythicmetals.MythicMetals;

public class RegisterIngots {
	//Normal Ingots
	public static final Item Adamantite_Ingot = new Item(new Item.Settings().group(MythicMetals.MYTHICMETALS));
	public static final Item Aetherium_Ingot = new Item(new Item.Settings().group(MythicMetals.MYTHICMETALS));
	public static final Item Aquarium_Ingot = new Item(new Item.Settings().group(MythicMetals.MYTHICMETALS));
	public static final Item Argonium_Ingot = new Item(new Item.Settings().group(MythicMetals.MYTHICMETALS));
	public static final Item Banglum_Ingot = new Item(new Item.Settings().group(MythicMetals.MYTHICMETALS));
	public static final Item Brass_Ingot = new Item(new Item.Settings().group(MythicMetals.MYTHICMETALS));
	public static final Item Bronze_Ingot = new Item(new Item.Settings().group(MythicMetals.MYTHICMETALS));
	public static final Item Carmot_Ingot = new Item(new Item.Settings().group(MythicMetals.MYTHICMETALS));
	public static final Item Celestium_Ingot = new Item(new Item.Settings().group(MythicMetals.MYTHICMETALS));
	public static final Item Copper_Ingot = new Item(new Item.Settings().group(MythicMetals.MYTHICMETALS));
	public static final Item Discordium_Ingot = new Item(new Item.Settings().group(MythicMetals.MYTHICMETALS));
	public static final Item Durasteel_Ingot = new Item(new Item.Settings().group(MythicMetals.MYTHICMETALS));
	public static final Item Electrum_Ingot = new Item(new Item.Settings().group(MythicMetals.MYTHICMETALS));
	public static final Item Etherite_Ingot = new Item(new Item.Settings().group(MythicMetals.MYTHICMETALS));
	public static final Item Kyber_Ingot = new Item(new Item.Settings().group(MythicMetals.MYTHICMETALS));
	public static final Item Lutetium_Ingot = new Item(new Item.Settings().group(MythicMetals.MYTHICMETALS));
	public static final Item Manganese_Ingot = new Item(new Item.Settings().group(MythicMetals.MYTHICMETALS));
	public static final Item Metallurgium_Ingot = new Item(new Item.Settings().group(MythicMetals.MYTHICMETALS).fireproof());
	public static final Item Midas_Gold_Ingot = new Item(new Item.Settings().group(MythicMetals.MYTHICMETALS));
	public static final Item Mythril_Ingot = new Item(new Item.Settings().group(MythicMetals.MYTHICMETALS));
	public static final Item Orichalcum_Ingot = new Item(new Item.Settings().group(MythicMetals.MYTHICMETALS));
	public static final Item Osmium_Ingot = new Item(new Item.Settings().group(MythicMetals.MYTHICMETALS));
	public static final Item Platinum_Ingot = new Item(new Item.Settings().group(MythicMetals.MYTHICMETALS));
	public static final Item Prometheum_Ingot = new Item(new Item.Settings().group(MythicMetals.MYTHICMETALS));
	public static final Item Quadrillum_Ingot = new Item(new Item.Settings().group(MythicMetals.MYTHICMETALS));
	public static final Item Quicksilver_Ingot = new Item(new Item.Settings().group(MythicMetals.MYTHICMETALS));
	public static final Item Runite_Ingot = new Item(new Item.Settings().group(MythicMetals.MYTHICMETALS));
	public static final Item Silver_Ingot = new Item(new Item.Settings().group(MythicMetals.MYTHICMETALS));
	public static final Item Slowsilver_Ingot = new Item(new Item.Settings().group(MythicMetals.MYTHICMETALS));
	public static final Item Starrite_Ingot = new Item(new Item.Settings().group(MythicMetals.MYTHICMETALS));
	public static final Item Steel_Ingot = new Item(new Item.Settings().group(MythicMetals.MYTHICMETALS));
	public static final Item Stormyx_Ingot = new Item(new Item.Settings().group(MythicMetals.MYTHICMETALS));
	public static final Item Tantalite_Ingot = new Item(new Item.Settings().group(MythicMetals.MYTHICMETALS));
	public static final Item Tin_Ingot = new Item(new Item.Settings().group(MythicMetals.MYTHICMETALS));
	public static final Item Truesilver_Ingot = new Item(new Item.Settings().group(MythicMetals.MYTHICMETALS));
	public static final Item Unobtainium_Ingot = new Item(new Item.Settings().group(MythicMetals.MYTHICMETALS).fireproof());
	public static final Item Ur_Ingot = new Item(new Item.Settings().group(MythicMetals.MYTHICMETALS));
	public static final Item Zinc_Ingot = new Item(new Item.Settings().group(MythicMetals.MYTHICMETALS));
	
	//Alloys
	public static final Item Argonium_Alloy = new Item(new Item.Settings().group(MythicMetals.MYTHICMETALS));
	public static final Item Brass_Alloy = new Item(new Item.Settings().group(MythicMetals.MYTHICMETALS));
	public static final Item Bronze_Alloy = new Item(new Item.Settings().group(MythicMetals.MYTHICMETALS));
	public static final Item Celestium_Alloy = new Item(new Item.Settings().group(MythicMetals.MYTHICMETALS));
	public static final Item Discordium_Alloy = new Item(new Item.Settings().group(MythicMetals.MYTHICMETALS));
	public static final Item Durasteel_Alloy = new Item(new Item.Settings().group(MythicMetals.MYTHICMETALS));
	public static final Item Electrum_Alloy = new Item(new Item.Settings().group(MythicMetals.MYTHICMETALS));
	public static final Item Etherite_Alloy = new Item(new Item.Settings().group(MythicMetals.MYTHICMETALS));
	public static final Item Ferrite_Alloy = new Item(new Item.Settings().group(MythicMetals.MYTHICMETALS));
	public static final Item Slowsilver_Alloy = new Item(new Item.Settings().group(MythicMetals.MYTHICMETALS));
	public static final Item Steel_Alloy = new Item(new Item.Settings().group(MythicMetals.MYTHICMETALS));
	public static final Item Quicksilver_Alloy = new Item(new Item.Settings().group(MythicMetals.MYTHICMETALS));
	
	//The Metallurgium Alloy tree
	public static final Item Adamanthril_Alloy = new Item(new Item.Settings().group(MythicMetals.MYTHICMETALS));
	public static final Item Hallowed_Alloy = new Item(new Item.Settings().group(MythicMetals.MYTHICMETALS));
	public static final Item Metallurgium_Alloy = new Item(new Item.Settings().group(MythicMetals.MYTHICMETALS).fireproof());
	
	public static void register() {
		//Register Ingots
		Registry.register(Registry.ITEM, new Identifier(MythicMetals.MOD_ID, "adamantite_ingot"), Adamantite_Ingot);
		Registry.register(Registry.ITEM, new Identifier(MythicMetals.MOD_ID, "aetherium_ingot"), Aetherium_Ingot);
		Registry.register(Registry.ITEM, new Identifier(MythicMetals.MOD_ID, "aquarium_ingot"), Aquarium_Ingot);
		Registry.register(Registry.ITEM, new Identifier(MythicMetals.MOD_ID, "argonium_ingot"), Argonium_Ingot);
		Registry.register(Registry.ITEM, new Identifier(MythicMetals.MOD_ID, "banglum_ingot"), Banglum_Ingot);
		Registry.register(Registry.ITEM, new Identifier(MythicMetals.MOD_ID, "brass_ingot"), Brass_Ingot);
		Registry.register(Registry.ITEM, new Identifier(MythicMetals.MOD_ID, "bronze_ingot"), Bronze_Ingot);
		Registry.register(Registry.ITEM, new Identifier(MythicMetals.MOD_ID, "carmot_ingot"), Carmot_Ingot);
		Registry.register(Registry.ITEM, new Identifier(MythicMetals.MOD_ID, "celestium_ingot"), Celestium_Ingot);
		Registry.register(Registry.ITEM, new Identifier(MythicMetals.MOD_ID, "copper_ingot"), Copper_Ingot);
		Registry.register(Registry.ITEM, new Identifier(MythicMetals.MOD_ID, "discordium_ingot"), Discordium_Ingot);
		Registry.register(Registry.ITEM, new Identifier(MythicMetals.MOD_ID, "durasteel_ingot"), Durasteel_Ingot);
		Registry.register(Registry.ITEM, new Identifier(MythicMetals.MOD_ID, "electrum_ingot"), Electrum_Ingot);
		Registry.register(Registry.ITEM, new Identifier(MythicMetals.MOD_ID, "etherite_ingot"), Etherite_Ingot);
		Registry.register(Registry.ITEM, new Identifier(MythicMetals.MOD_ID, "kyber_ingot"), Kyber_Ingot);
		Registry.register(Registry.ITEM, new Identifier(MythicMetals.MOD_ID, "lutetium_ingot"), Lutetium_Ingot);
		Registry.register(Registry.ITEM, new Identifier(MythicMetals.MOD_ID, "manganese_ingot"), Manganese_Ingot);
		Registry.register(Registry.ITEM, new Identifier(MythicMetals.MOD_ID, "metallurgium_ingot"), Metallurgium_Ingot);
		Registry.register(Registry.ITEM, new Identifier(MythicMetals.MOD_ID, "midas_gold_ingot"), Midas_Gold_Ingot);
		Registry.register(Registry.ITEM, new Identifier(MythicMetals.MOD_ID, "mythril_ingot"), Mythril_Ingot);
		Registry.register(Registry.ITEM, new Identifier(MythicMetals.MOD_ID, "orichalcum_ingot"), Orichalcum_Ingot);
		Registry.register(Registry.ITEM, new Identifier(MythicMetals.MOD_ID, "osmium_ingot"), Osmium_Ingot);
		Registry.register(Registry.ITEM, new Identifier(MythicMetals.MOD_ID, "platinum_ingot"), Platinum_Ingot);
		Registry.register(Registry.ITEM, new Identifier(MythicMetals.MOD_ID, "prometheum_ingot"), Prometheum_Ingot);
		Registry.register(Registry.ITEM, new Identifier(MythicMetals.MOD_ID, "quadrillum_ingot"), Quadrillum_Ingot);
		Registry.register(Registry.ITEM, new Identifier(MythicMetals.MOD_ID, "quicksilver_ingot"), Quicksilver_Ingot);
		Registry.register(Registry.ITEM, new Identifier(MythicMetals.MOD_ID, "runite_ingot"), Runite_Ingot);
		Registry.register(Registry.ITEM, new Identifier(MythicMetals.MOD_ID, "silver_ingot"), Silver_Ingot);
		Registry.register(Registry.ITEM, new Identifier(MythicMetals.MOD_ID, "slowsilver_ingot"), Slowsilver_Ingot);
		Registry.register(Registry.ITEM, new Identifier(MythicMetals.MOD_ID, "starrite_ingot"), Starrite_Ingot);
		Registry.register(Registry.ITEM, new Identifier(MythicMetals.MOD_ID, "steel_ingot"), Steel_Ingot);
		Registry.register(Registry.ITEM, new Identifier(MythicMetals.MOD_ID, "stormyx_ingot"), Stormyx_Ingot);
		Registry.register(Registry.ITEM, new Identifier(MythicMetals.MOD_ID, "tantalite_ingot"), Tantalite_Ingot);
		Registry.register(Registry.ITEM, new Identifier(MythicMetals.MOD_ID, "tin_ingot"), Tin_Ingot);
		Registry.register(Registry.ITEM, new Identifier(MythicMetals.MOD_ID, "truesilver_ingot"), Truesilver_Ingot);
		Registry.register(Registry.ITEM, new Identifier(MythicMetals.MOD_ID, "unobtainium_ingot"), Unobtainium_Ingot);
		Registry.register(Registry.ITEM, new Identifier(MythicMetals.MOD_ID, "ur_ingot"),Ur_Ingot);
		Registry.register(Registry.ITEM, new Identifier(MythicMetals.MOD_ID, "zinc_ingot"), Zinc_Ingot);
		//Register Alloys
		Registry.register(Registry.ITEM, new Identifier(MythicMetals.MOD_ID, "argonium_alloy"), Argonium_Alloy);
		Registry.register(Registry.ITEM, new Identifier(MythicMetals.MOD_ID, "brass_alloy"), Brass_Alloy);
		Registry.register(Registry.ITEM, new Identifier(MythicMetals.MOD_ID, "bronze_alloy"), Bronze_Alloy);
		Registry.register(Registry.ITEM, new Identifier(MythicMetals.MOD_ID, "celestium_alloy"), Celestium_Alloy);
		Registry.register(Registry.ITEM, new Identifier(MythicMetals.MOD_ID, "discordium_alloy"), Discordium_Alloy);
		Registry.register(Registry.ITEM, new Identifier(MythicMetals.MOD_ID, "durasteel_alloy"), Durasteel_Alloy);
		Registry.register(Registry.ITEM, new Identifier(MythicMetals.MOD_ID, "electrum_alloy"), Electrum_Alloy);
		Registry.register(Registry.ITEM, new Identifier(MythicMetals.MOD_ID, "etherite_alloy"), Etherite_Alloy);
		Registry.register(Registry.ITEM, new Identifier(MythicMetals.MOD_ID, "ferrite_alloy"), Ferrite_Alloy);
		Registry.register(Registry.ITEM, new Identifier(MythicMetals.MOD_ID, "slowsilver_alloy"), Slowsilver_Alloy);
		Registry.register(Registry.ITEM, new Identifier(MythicMetals.MOD_ID, "steel_alloy"), Steel_Alloy);
		Registry.register(Registry.ITEM, new Identifier(MythicMetals.MOD_ID, "quicksilver_alloy"), Quicksilver_Alloy);
		Registry.register(Registry.ITEM, new Identifier(MythicMetals.MOD_ID, "adamanthril_alloy"), Adamanthril_Alloy);
		Registry.register(Registry.ITEM, new Identifier(MythicMetals.MOD_ID, "hallowed_alloy"), Hallowed_Alloy);
		Registry.register(Registry.ITEM, new Identifier(MythicMetals.MOD_ID, "metallurgium_alloy"), Metallurgium_Alloy);
		
	}
}
