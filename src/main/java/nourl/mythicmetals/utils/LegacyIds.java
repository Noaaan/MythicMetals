package nourl.mythicmetals.utils;

import net.minecraft.util.Identifier;

import java.util.HashMap;

public class LegacyIds {
    private static final HashMap<Identifier, Identifier> LEGACY_IDS = new HashMap<>();

    // All the identifiers of removed stuff
    private static final Identifier OLD_COPPER_ORE = RegistryHelper.id("copper_ore");
    private static final Identifier OLD_COPPER_INGOT = RegistryHelper.id( "copper_ingot");
    private static final Identifier OLD_TANTALITE_ORE = RegistryHelper.id( "tantalite_ore");
    private static final Identifier OLD_TRUESILVER_ORE = RegistryHelper.id( "truesilver_ore");
    private static final Identifier OLD_TRUESILVER_INGOT = RegistryHelper.id( "truesilver_ingot");
    private static final Identifier OLD_TRUESILVER_BLOCK = RegistryHelper.id( "truesilver_block");
    private static final Identifier OLD_AETHERIUM_ORE = RegistryHelper.id( "aetherium_ore");
    private static final Identifier OLD_AETHERIUM_INGOT = RegistryHelper.id( "aetherium_ingot");
    private static final Identifier OLD_AETHERIUM_BLOCK = RegistryHelper.id( "aetherium_block");
    private static final Identifier OLD_RAW_AETHERIUM = RegistryHelper.id( "raw_aetherium");
    private static final Identifier OLD_UR = RegistryHelper.id( "ur_ore");
    private static final Identifier OLD_ZINC = RegistryHelper.id( "zinc_ore");
    private static final Identifier OLD_RAW_STARRITE = RegistryHelper.id( "raw_starrite");
    private static final Identifier OLD_STARRITE_INGOT = RegistryHelper.id( "starrite_ingot");
    private static final Identifier OLD_UNOBTAINIUM_DUST = RegistryHelper.id("unobtainium_dust");

    private static final Identifier VANILLA_COPPER_ORE = new Identifier("copper_ore");
    private static final Identifier VANILLA_COPPER_INGOT = new Identifier("copper_ingot");
    private static final Identifier VANILLA_NETHERRACK = new Identifier("netherrack");
    private static final Identifier VANILLA_STONE = new Identifier("stone");
    private static final Identifier NEW_UNOBTAINIUM = RegistryHelper.id("unobtainium");
    private static final Identifier NEW_PALLADIUM_ORE = RegistryHelper.id("palladium_ore");
    private static final Identifier NEW_PALLADIUM_INGOT = RegistryHelper.id("palladium_ingot");
    private static final Identifier NEW_PALLADIUM_BLOCK = RegistryHelper.id("palladium_block");
    private static final Identifier NEW_MYTHRIL_ORE = RegistryHelper.id("mythril_ore");
    private static final Identifier NEW_MYTHRIL_INGOT = RegistryHelper.id("mythril_ingot");
    private static final Identifier NEW_MYTHRIL_BLOCK = RegistryHelper.id("mythril_block");
    private static final Identifier NEW_MYTHRIL_RAW = RegistryHelper.id("raw_mythril");
    private static final Identifier NEW_STARRITE = RegistryHelper.id("starrite");



    private static void createMap() {
        LEGACY_IDS.put(OLD_COPPER_ORE, VANILLA_COPPER_ORE);
        LEGACY_IDS.put(OLD_COPPER_INGOT, VANILLA_COPPER_INGOT);
        LEGACY_IDS.put(OLD_TANTALITE_ORE, VANILLA_STONE);
        LEGACY_IDS.put(OLD_TRUESILVER_ORE, NEW_PALLADIUM_ORE);
        LEGACY_IDS.put(OLD_TRUESILVER_INGOT, NEW_PALLADIUM_INGOT);
        LEGACY_IDS.put(OLD_TRUESILVER_BLOCK, NEW_PALLADIUM_BLOCK);
        LEGACY_IDS.put(OLD_AETHERIUM_ORE, NEW_MYTHRIL_ORE);
        LEGACY_IDS.put(OLD_AETHERIUM_INGOT, NEW_MYTHRIL_INGOT);
        LEGACY_IDS.put(OLD_AETHERIUM_BLOCK, NEW_MYTHRIL_BLOCK);
        LEGACY_IDS.put(OLD_RAW_AETHERIUM, NEW_MYTHRIL_RAW);
        LEGACY_IDS.put(OLD_UR, VANILLA_NETHERRACK);
        LEGACY_IDS.put(OLD_ZINC, VANILLA_STONE);
        LEGACY_IDS.put(OLD_RAW_STARRITE, NEW_STARRITE);
        LEGACY_IDS.put(OLD_STARRITE_INGOT, NEW_STARRITE);
        LEGACY_IDS.put(OLD_UNOBTAINIUM_DUST, NEW_UNOBTAINIUM);

    }

    public static HashMap<Identifier, Identifier> getLegacyIds() {
        createMap();
        return LEGACY_IDS;
    }
}
