package nourl.mythicmetals.registry;

import net.minecraft.util.Rarity;
import nourl.mythicmetals.armor.ArmorMaterials;
import nourl.mythicmetals.armor.ArmorSet;

public class RegisterArmor {

    // Create Armor Sets
    public static final ArmorSet ADAMANTITE = new ArmorSet(ArmorMaterials.ADAMANTITE);
    public static final ArmorSet AETHERIUM = new ArmorSet(ArmorMaterials.AETHERIUM);
    public static final ArmorSet AQUARIUM = new ArmorSet(ArmorMaterials.AQUARIUM);
    public static final ArmorSet BANGLUM = new ArmorSet(ArmorMaterials.BANGLUM);
    public static final ArmorSet BRONZE = new ArmorSet(ArmorMaterials.BRONZE);
    public static final ArmorSet CARMOT = new ArmorSet(ArmorMaterials.CARMOT);
    public static final ArmorSet CELESTIUM = new ArmorSet(ArmorMaterials.CELESTIUM, settings -> settings.rarity(Rarity.RARE));
    public static final ArmorSet COPPER = new ArmorSet(ArmorMaterials.COPPER);
    public static final ArmorSet DISCORDIUM = new ArmorSet(ArmorMaterials.DISCORDIUM);
    public static final ArmorSet DURASTEEL = new ArmorSet(ArmorMaterials.DURASTEEL);
    public static final ArmorSet ETHERITE = new ArmorSet(ArmorMaterials.ETHERITE);
    public static final ArmorSet HALLOWED = new ArmorSet(ArmorMaterials.HALLOWED);
    public static final ArmorSet KYBER = new ArmorSet(ArmorMaterials.KYBER);
    public static final ArmorSet METALLURGIUM = new ArmorSet(ArmorMaterials.METALLURGIUM, settings -> settings.fireproof().rarity(Rarity.RARE));
    public static final ArmorSet MIDAS_GOLD = new ArmorSet(ArmorMaterials.MIDAS_GOLD);
    public static final ArmorSet MYTHRIL = new ArmorSet(ArmorMaterials.MYTHRIL);
    public static final ArmorSet ORICHALCUM = new ArmorSet(ArmorMaterials.ORICHALCUM);
    public static final ArmorSet OSMIUM = new ArmorSet(ArmorMaterials.OSMIUM);
    public static final ArmorSet PALLADIUM = new ArmorSet(ArmorMaterials.PALLADIUM, settings -> settings.fireproof().rarity(Rarity.RARE));
    public static final ArmorSet PROMETHEUM = new ArmorSet(ArmorMaterials.PROMETHEUM);
    public static final ArmorSet QUICKSILVER = new ArmorSet(ArmorMaterials.QUICKSILVER);
    public static final ArmorSet RUNITE = new ArmorSet(ArmorMaterials.RUNITE);
    public static final ArmorSet SILVER = new ArmorSet(ArmorMaterials.SILVER);
    public static final ArmorSet STAR_PLATINUM = new ArmorSet(ArmorMaterials.STAR_PLATINUM);
    public static final ArmorSet STEEL = new ArmorSet(ArmorMaterials.STEEL);
    public static final ArmorSet STORMYX = new ArmorSet(ArmorMaterials.STORMYX);
    
    public static void register() {
        // Register Armor
        ADAMANTITE.register("adamantite");
        AETHERIUM.register("aetherium");
        AQUARIUM.register("aquarium");
        BANGLUM.register("banglum");
        BRONZE.register("bronze");
        CARMOT.register("carmot");
        CELESTIUM.register("celestium");
        COPPER.register("copper");
        DISCORDIUM.register("discordium");
        DURASTEEL.register("durasteel");
        ETHERITE.register("etherite");
        HALLOWED.register("hallowed");
        KYBER.register("kyber");
        METALLURGIUM.register("metallurgium");
        MIDAS_GOLD.register("midas_gold");
        MYTHRIL.register("mythril");
        ORICHALCUM.register("orichalcum");
        OSMIUM.register("osmium");
        PALLADIUM.register("palladium");
        PROMETHEUM.register("prometheum");
        QUICKSILVER.register("quicksilver");
        RUNITE.register("runite");
        SILVER.register("silver");
        STAR_PLATINUM.register("star_platinum");
        STEEL.register("steel");
        STORMYX.register("stormyx");
    }

}
