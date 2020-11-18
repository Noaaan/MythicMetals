package nourl.mythicmetals.config;

import nourl.mythicmetals.config.confighandler.IConfig;

public class MythicConfig {
    //Adamantite Ore Settings
    @IConfig(config = "mythicconfig", category = "adamantiteOre", key = "adamantiteGeneration", comment = "Generate Adamantite Ore")
    public static boolean adamantiteGeneration = true;

    @IConfig(config = "mythicconfig", category = "adamantiteOre", key = "adamantiteVeinSize", comment = "Set Max Adamantite Vein Size (Default: 5)")
    public static int adamantiteVeinSize = 5;

    @IConfig(config = "mythicconfig", category = "adamantiteOre", key = "adamantitePerChunk", comment = "Set Adamantite Spawn Chance (Default: 1)")
    public static int adamantitePerChunk = 1;

    @IConfig(config = "mythicconfig", category = "adamantiteOre", key = "adamantiteMinHeight", comment = "Set Adamantite Min Spawn Height (Default: 4)")
    public static int adamantiteMinHeight = 4;

    @IConfig(config = "mythicconfig", category = "adamantiteOre", key = "adamantiteMaxHeight", comment = "Set Adamantite Max Spawn Height (Default: 24)")
    public static int adamantiteMaxHeight = 24;


    //Aetherium Ore Settings
    @IConfig(config = "mythicconfig", category = "aetheriumOre", key = "aetheriumGeneration", comment = "Generate Aetherium Ore")
    public static boolean aetheriumGeneration = true;

    @IConfig(config = "mythicconfig", category = "aetheriumOre", key = "aetheriumVeinSize", comment = "Set Max Aetherium Vein Size (Default: 2)")
    public static int aetheriumVeinSize = 2;

    @IConfig(config = "mythicconfig", category = "aetheriumOre", key = "aetheriumPerChunk", comment = "Set Aetherium Spawn Chance (Default: 2)")
    public static int aetheriumPerChunk = 2;

    @IConfig(config = "mythicconfig", category = "aetheriumOre", key = "aetheriumMinHeight", comment = "Set Aetherium Min Spawn Height (Default: 70)")
    public static int aetheriumMinHeight = 70;

    @IConfig(config = "mythicconfig", category = "aetheriumOre", key = "aetheriumMaxHeight", comment = "Set Aetherium Max Spawn Height (Default: 180)")
    public static int aetheriumMaxHeight = 180;


    //Aquarium Ore Settings
    @IConfig(config = "mythicconfig", category = "aquariumOre", key = "aquariumGeneration", comment = "Generate Aquarium Ore (Default: False)")
    public static boolean aquariumGeneration = false;

    @IConfig(config = "mythicconfig", category = "aquariumOre", key = "aquariumVeinSize", comment = "Set Max Aquarium Vein Size (Default: 16)")
    public static int aquariumVeinSize = 16;

    @IConfig(config = "mythicconfig", category = "aquariumOre", key = "aquariumPerChunk", comment = "Set Aquarium Spawn Chance (Default: 3)")
    public static int aquariumPerChunk = 3;

    @IConfig(config = "mythicconfig", category = "aquariumOre", key = "aquariumMinHeight", comment = "Set Aquarium Min Spawn Height (Default: 6)")
    public static int aquariumMinHeight = 6;

    @IConfig(config = "mythicconfig", category = "aquariumOre", key = "aquariumMaxHeight", comment = "Set Aquarium Max Spawn Height (Default: 63)")
    public static int aquariumMaxHeight = 63;


    //Banglum Ore Settings
    @IConfig(config = "mythicconfig", category = "banglumOre", key = "banglumGeneration", comment = "Generate Banglum Ore")
    public static boolean banglumGeneration = true;

    @IConfig(config = "mythicconfig", category = "banglumOre", key = "banglumVeinSize", comment = "Set Max Banglum Vein Size (Default: 6)")
    public static int banglumVeinSize = 6;

    @IConfig(config = "mythicconfig", category = "banglumOre", key = "banglumPerChunk", comment = "Set Banglum Spawn Chance (Default: 2)")
    public static int banglumPerChunk = 2;

    @IConfig(config = "mythicconfig", category = "banglumOre", key = "banglumMinHeight", comment = "Set Banglum Min Spawn Height (Default: 6)")
    public static int banglumMinHeight = 6;

    @IConfig(config = "mythicconfig", category = "banglumOre", key = "banglumMaxHeight", comment = "Set Banglum Max Spawn Height (Default: 32)")
    public static int banglumMaxHeight = 32;


    //Carmot Ore Settings
    @IConfig(config = "mythicconfig", category = "carmotOre", key = "carmotGeneration", comment = "Generate Carmot Ore")
    public static boolean carmotGeneration = true;

    @IConfig(config = "mythicconfig", category = "carmotOre", key = "carmotVeinSize", comment = "Set Max Carmot Vein Size (Default: 2)")
    public static int carmotVeinSize = 2;

    @IConfig(config = "mythicconfig", category = "carmotOre", key = "carmotPerChunk", comment = "Set Carmot Spawn Chance (Default: 4)")
    public static int carmotPerChunk = 4;

    @IConfig(config = "mythicconfig", category = "carmotOre", key = "carmotMinHeight", comment = "Set Carmot Min Spawn Height (Default: 8)")
    public static int carmotMinHeight = 8;

    @IConfig(config = "mythicconfig", category = "carmotOre", key = "carmotMaxHeight", comment = "Set Carmot Max Spawn Height (Default: 48)")
    public static int carmotMaxHeight = 48;


    //Copper Ore Settings
    @IConfig(config = "mythicconfig", category = "copperOre", key = "copperGeneration", comment = "Generate Copper Ore")
    public static boolean copperGeneration = true;

    @IConfig(config = "mythicconfig", category = "copperOre", key = "copperVeinSize", comment = "Set Max Copper Vein Size (Default: 12)")
    public static int copperVeinSize = 12;

    @IConfig(config = "mythicconfig", category = "copperOre", key = "copperPerChunk", comment = "Set Copper Spawn Chance (Default: 6)")
    public static int copperPerChunk = 6;

    @IConfig(config = "mythicconfig", category = "copperOre", key = "copperMinHeight", comment = "Set Copper Min Spawn Height (Default: 32)")
    public static int copperMinHeight = 32;

    @IConfig(config = "mythicconfig", category = "copperOre", key = "copperMaxHeight", comment = "Set Copper Max Spawn Height (Default: 128)")
    public static int copperMaxHeight = 128;


    //Kyber Ore Settings
    @IConfig(config = "mythicconfig", category = "kyberOre", key = "kyberGeneration", comment = "Generate Kyber Ore")
    public static boolean kyberGeneration = true;

    @IConfig(config = "mythicconfig", category = "kyberOre", key = "kyberVeinSize", comment = "Set Max Kyber Vein Size (Default: 14)")
    public static int kyberVeinSize = 14;

    @IConfig(config = "mythicconfig", category = "kyberOre", key = "kyberPerChunk", comment = "Set Kyber Spawn Chance (Default: 2)")
    public static int kyberPerChunk = 2;

    @IConfig(config = "mythicconfig", category = "kyberOre", key = "kyberMinHeight", comment = "Set Kyber Min Spawn Height (Default: 6)")
    public static int kyberMinHeight = 6;

    @IConfig(config = "mythicconfig", category = "kyberOre", key = "kyberMaxHeight", comment = "Set Kyber Max Spawn Height (Default: 18)")
    public static int kyberMaxHeight = 18;


    //Lutetium Ore Settings
    @IConfig(config = "mythicconfig", category = "lutetiumOre", key = "lutetiumGeneration", comment = "Generate Lutetium Ore")
    public static boolean lutetiumGeneration = true;

    @IConfig(config = "mythicconfig", category = "lutetiumOre", key = "lutetiumVeinSize", comment = "Set Max Lutetium Vein Size (Default: 5)")
    public static int lutetiumVeinSize = 5;

    @IConfig(config = "mythicconfig", category = "lutetiumOre", key = "lutetiumPerChunk", comment = "Set Lutetium Spawn Chance (Default: 3)")
    public static int lutetiumPerChunk = 3;

    @IConfig(config = "mythicconfig", category = "lutetiumOre", key = "lutetiumMinHeight", comment = "Set Lutetium Min Spawn Height (Default: 6)")
    public static int lutetiumMinHeight = 6;

    @IConfig(config = "mythicconfig", category = "lutetiumOre", key = "lutetiumMaxHeight", comment = "Set Lutetium Max Spawn Height (Default: 64)")
    public static int lutetiumMaxHeight = 64;


    //Manganese Ore Settings
    @IConfig(config = "mythicconfig", category = "manganeseOre", key = "manganeseGeneration", comment = "Generate Manganese Ore")
    public static boolean manganeseGeneration = true;

    @IConfig(config = "mythicconfig", category = "manganeseOre", key = "manganeseVeinSize", comment = "Set Max Manganese Vein Size (Default: 6)")
    public static int manganeseVeinSize = 6;

    @IConfig(config = "mythicconfig", category = "manganeseOre", key = "manganesePerChunk", comment = "Set Manganese Spawn Chance (Default: 4)")
    public static int manganesePerChunk = 4;

    @IConfig(config = "mythicconfig", category = "manganeseOre", key = "manganeseMinHeight", comment = "Set Manganese Min Spawn Height (Default: 6)")
    public static int manganeseMinHeight = 6;

    @IConfig(config = "mythicconfig", category = "manganeseOre", key = "manganeseMaxHeight", comment = "Set Manganese Max Spawn Height (Default: 48)")
    public static int manganeseMaxHeight = 48;


    //Midas Gold Ore Settings
    @IConfig(config = "mythicconfig", category = "midasgoldOre", key = "midasgoldGeneration", comment = "Generate Midas Gold Ore")
    public static boolean midasgoldGeneration = true;

    @IConfig(config = "mythicconfig", category = "midasgoldOre", key = "midasgoldVeinSize", comment = "Set Max Midas Gold Vein Size (Default: 6)")
    public static int midasgoldVeinSize = 6;

    @IConfig(config = "mythicconfig", category = "midasgoldOre", key = "midasgoldPerChunk", comment = "Set Midas Gold Spawn Chance (Default: 10)")
    public static int midasgoldPerChunk = 10;

    @IConfig(config = "mythicconfig", category = "midasgoldOre", key = "midasgoldMinHeight", comment = "Set Midas Gold Min Spawn Height (Default: 10)")
    public static int midasgoldMinHeight = 10;

    @IConfig(config = "mythicconfig", category = "midasgoldOre", key = "midasgoldMaxHeight", comment = "Set Midas Gold Max Spawn Height (Default: 128)")
    public static int midasgoldMaxHeight = 128;


    //Mythril Ore Settings
    @IConfig(config = "mythicconfig", category = "mythrilOre", key = "mythrilGeneration", comment = "Generate Mythril Ore")
    public static boolean mythrilGeneration = true;

    @IConfig(config = "mythicconfig", category = "mythrilOre", key = "mythrilVeinSize", comment = "Set Max Mythril Vein Size (Default: 5)")
    public static int mythrilVeinSize = 5;

    @IConfig(config = "mythicconfig", category = "mythrilOre", key = "mythrilPerChunk", comment = "Set Mythril Spawn Chance (Default: 1)")
    public static int mythrilPerChunk = 1;

    @IConfig(config = "mythicconfig", category = "mythrilOre", key = "mythrilMinHeight", comment = "Set Mythril Min Spawn Height (Default: 4)")
    public static int mythrilMinHeight = 4;

    @IConfig(config = "mythicconfig", category = "mythrilOre", key = "mythrilMaxHeight", comment = "Set Mythril Max Spawn Height (Default: 24)")
    public static int mythrilMaxHeight = 24;


    //Orichalcum Ore Settings
    @IConfig(config = "mythicconfig", category = "orichalcumOre", key = "orichalcumGeneration", comment = "Generate Orichalcum Ore")
    public static boolean orichalcumGeneration = true;

    @IConfig(config = "mythicconfig", category = "orichalcumOre", key = "orichalcumVeinSize", comment = "Set Max Orichalcum Vein Size (Default: 5)")
    public static int orichalcumVeinSize = 5;

    @IConfig(config = "mythicconfig", category = "orichalcumOre", key = "orichalcumPerChunk", comment = "Set Orichalcum Spawn Chance (Default: 1)")
    public static int orichalcumPerChunk = 1;

    @IConfig(config = "mythicconfig", category = "orichalcumOre", key = "orichalcumMinHeight", comment = "Set Orichalcum Min Spawn Height (Default: 4)")
    public static int orichalcumMinHeight = 4;

    @IConfig(config = "mythicconfig", category = "orichalcumOre", key = "orichalcumMaxHeight", comment = "Set Orichalcum Max Spawn Height (Default: 24)")
    public static int orichalcumMaxHeight = 24;


    //Osmium Ore Settings
    @IConfig(config = "mythicconfig", category = "osmiumOre", key = "osmiumGeneration", comment = "Generate Osmium Ore")
    public static boolean osmiumGeneration = true;

    @IConfig(config = "mythicconfig", category = "osmiumOre", key = "osmiumVeinSize", comment = "Set Max Osmium Vein Size (Default: 9)")
    public static int osmiumVeinSize = 9;

    @IConfig(config = "mythicconfig", category = "osmiumOre", key = "osmiumPerChunk", comment = "Set Osmium Spawn Chance (Default: 6)")
    public static int osmiumPerChunk = 6;

    @IConfig(config = "mythicconfig", category = "osmiumOre", key = "osmiumMinHeight", comment = "Set Osmium Min Spawn Height (Default: 6)")
    public static int osmiumMinHeight = 6;

    @IConfig(config = "mythicconfig", category = "osmiumOre", key = "osmiumMaxHeight", comment = "Set Osmium Max Spawn Height (Default: 48)")
    public static int osmiumMaxHeight = 48;


    //Platinum Ore Settings
    @IConfig(config = "mythicconfig", category = "platinumOre", key = "platinumGeneration", comment = "Generate Platinum Ore")
    public static boolean platinumGeneration = true;

    @IConfig(config = "mythicconfig", category = "platinumOre", key = "platinumVeinSize", comment = "Set Max Platinum Vein Size (Default: 6)")
    public static int platinumVeinSize = 6;

    @IConfig(config = "mythicconfig", category = "platinumOre", key = "platinumPerChunk", comment = "Set Platinum Spawn Chance (Default: 2)")
    public static int platinumPerChunk = 2;

    @IConfig(config = "mythicconfig", category = "platinumOre", key = "platinumMinHeight", comment = "Set Platinum Min Spawn Height (Default: 6)")
    public static int platinumMinHeight = 6;

    @IConfig(config = "mythicconfig", category = "platinumOre", key = "platinumMaxHeight", comment = "Set Platinum Max Spawn Height (Default: 24)")
    public static int platinumMaxHeight = 24;


    //Prometheum Ore Settings
    @IConfig(config = "mythicconfig", category = "prometheumOre", key = "prometheumGeneration", comment = "Generate Prometheum Ore")
    public static boolean prometheumGeneration = true;

    @IConfig(config = "mythicconfig", category = "prometheumOre", key = "prometheumVeinSize", comment = "Set Max Prometheum Vein Size (Default: 5)")
    public static int prometheumVeinSize = 5;

    @IConfig(config = "mythicconfig", category = "prometheumOre", key = "prometheumPerChunk", comment = "Set Prometheum Spawn Chance (Default: 2)")
    public static int prometheumPerChunk = 2;

    @IConfig(config = "mythicconfig", category = "prometheumOre", key = "prometheumMinHeight", comment = "Set Prometheum Min Spawn Height (Default: 6)")
    public static int prometheumMinHeight = 6;

    @IConfig(config = "mythicconfig", category = "prometheumOre", key = "prometheumMaxHeight", comment = "Set Prometheum Max Spawn Height (Default: 24)")
    public static int prometheumMaxHeight = 24;


    //Quadrillum Ore Settings
    @IConfig(config = "mythicconfig", category = "quadrillumOre", key = "quadrillumGeneration", comment = "Generate Quadrillum Ore")
    public static boolean quadrillumGeneration = true;

    @IConfig(config = "mythicconfig", category = "quadrillumOre", key = "quadrillumVeinSize", comment = "Set Max Quadrillum Vein Size (Default: 8)")
    public static int quadrillumVeinSize = 8;

    @IConfig(config = "mythicconfig", category = "quadrillumOre", key = "quadrillumPerChunk", comment = "Set Quadrillum Spawn Chance (Default: 8)")
    public static int quadrillumPerChunk = 8;

    @IConfig(config = "mythicconfig", category = "quadrillumOre", key = "quadrillumMinHeight", comment = "Set Quadrillum Min Spawn Height (Default: 6)")
    public static int quadrillumMinHeight = 6;

    @IConfig(config = "mythicconfig", category = "quadrillumOre", key = "quadrillumMaxHeight", comment = "Set Quadrillum Max Spawn Height (Default: 56)")
    public static int quadrillumMaxHeight = 56;


    //Runite Ore Settings
    @IConfig(config = "mythicconfig", category = "runiteOre", key = "runiteGeneration", comment = "Generate Runite Ore")
    public static boolean runiteGeneration = true;

    @IConfig(config = "mythicconfig", category = "runiteOre", key = "runiteVeinSize", comment = "Set Max Runite Vein Size (Default: 3)")
    public static int runiteVeinSize = 3;

    @IConfig(config = "mythicconfig", category = "runiteOre", key = "runitePerChunk", comment = "Set Runite Spawn Chance (Default: 5)")
    public static int runitePerChunk = 5;

    @IConfig(config = "mythicconfig", category = "runiteOre", key = "runiteMinHeight", comment = "Set Runite Min Spawn Height (Default: 6)")
    public static int runiteMinHeight = 6;

    @IConfig(config = "mythicconfig", category = "runiteOre", key = "runiteMaxHeight", comment = "Set Runite Max Spawn Height (Default: 32)")
    public static int runiteMaxHeight = 32;


    //Silver Ore Settings
    @IConfig(config = "mythicconfig", category = "silverOre", key = "silverGeneration", comment = "Generate Silver Ore")
    public static boolean silverGeneration = true;

    @IConfig(config = "mythicconfig", category = "silverOre", key = "silverVeinSize", comment = "Set Max Silver Vein Size (Default: 6)")
    public static int silverVeinSize = 6;

    @IConfig(config = "mythicconfig", category = "silverOre", key = "silverPerChunk", comment = "Set Silver Spawn Chance (Default: 5)")
    public static int silverPerChunk = 5;

    @IConfig(config = "mythicconfig", category = "silverOre", key = "silverMinHeight", comment = "Set Silver Min Spawn Height (Default: 6)")
    public static int silverMinHeight = 6;

    @IConfig(config = "mythicconfig", category = "silverOre", key = "silverMaxHeight", comment = "Set Silver Max Spawn Height (Default: 48)")
    public static int silverMaxHeight = 48;


    //Starrite Ore Settings
    @IConfig(config = "mythicconfig", category = "starriteOre", key = "starriteGeneration", comment = "Generate Starrite Ore")
    public static boolean starriteGeneration = true;

    @IConfig(config = "mythicconfig", category = "starriteOre", key = "starriteVeinSize", comment = "Set Max Starrite Vein Size (Default: 5)")
    public static int starriteVeinSize = 5;

    @IConfig(config = "mythicconfig", category = "starriteOre", key = "starritePerChunk", comment = "Set Starrite Spawn Chance (Default: 2)")
    public static int starritePerChunk = 2;

    @IConfig(config = "mythicconfig", category = "starriteOre", key = "starriteMinHeight", comment = "Set Starrite Min Spawn Height (Default: 150)")
    public static int starriteMinHeight = 150;

    @IConfig(config = "mythicconfig", category = "starriteOre", key = "starriteMaxHeight", comment = "Set Starrite Max Spawn Height (Default: 200)")
    public static int starriteMaxHeight = 200;


    //Stormyx Ore Settings
    @IConfig(config = "mythicconfig", category = "stormyxOre", key = "stormyxGeneration", comment = "Generate Stormyx Ore")
    public static boolean stormyxGeneration = true;

    @IConfig(config = "mythicconfig", category = "stormyxOre", key = "stormyxVeinSize", comment = "Set Max Stormyx Vein Size (Default: 8)")
    public static int stormyxVeinSize = 8;

    @IConfig(config = "mythicconfig", category = "stormyxOre", key = "stormyxPerChunk", comment = "Set Stormyx Spawn Chance (Default: 2)")
    public static int stormyxPerChunk = 2;

    @IConfig(config = "mythicconfig", category = "stormyxOre", key = "stormyxMinHeight", comment = "Set Stormyx Min Spawn Height (Default: 6)")
    public static int stormyxMinHeight = 6;

    @IConfig(config = "mythicconfig", category = "stormyxOre", key = "stormyxMaxHeight", comment = "Set Stormyx Max Spawn Height (Default: 54)")
    public static int stormyxMaxHeight = 54;


    //Tantalite Ore Settings
    @IConfig(config = "mythicconfig", category = "tantaliteOre", key = "tantaliteGeneration", comment = "Generate Tantalite Ore")
    public static boolean tantaliteGeneration = true;

    @IConfig(config = "mythicconfig", category = "tantaliteOre", key = "tantaliteVeinSize", comment = "Set Max Tantalite Vein Size (Default: 12)")
    public static int tantaliteVeinSize = 12;

    @IConfig(config = "mythicconfig", category = "tantaliteOre", key = "tantalitePerChunk", comment = "Set Tantalite Spawn Chance (Default: 3)")
    public static int tantalitePerChunk = 3;

    @IConfig(config = "mythicconfig", category = "tantaliteOre", key = "tantaliteMinHeight", comment = "Set Tantalite Min Spawn Height (Default: 6)")
    public static int tantaliteMinHeight = 6;

    @IConfig(config = "mythicconfig", category = "tantaliteOre", key = "tantaliteMaxHeight", comment = "Set Tantalite Max Spawn Height (Default: 48)")
    public static int tantaliteMaxHeight = 48;


    //Tin Ore Settings
    @IConfig(config = "mythicconfig", category = "tinOre", key = "tinGeneration", comment = "Generate Tin Ore")
    public static boolean tinGeneration = true;

    @IConfig(config = "mythicconfig", category = "tinOre", key = "tinVeinSize", comment = "Set Max Tin Vein Size (Default: 8)")
    public static int tinVeinSize = 8;

    @IConfig(config = "mythicconfig", category = "tinOre", key = "tinPerChunk", comment = "Set Tin Spawn Chance (Default: 4)")
    public static int tinPerChunk = 4;

    @IConfig(config = "mythicconfig", category = "tinOre", key = "tinMinHeight", comment = "Set Tin Min Spawn Height (Default: 6)")
    public static int tinMinHeight = 6;

    @IConfig(config = "mythicconfig", category = "tinOre", key = "tinMaxHeight", comment = "Set Tin Max Spawn Height (Default: 128)")
    public static int tinMaxHeight = 128;


    //Truesilver Ore Settings
    @IConfig(config = "mythicconfig", category = "truesilverOre", key = "truesilverGeneration", comment = "Generate Truesilver Ore")
    public static boolean truesilverGeneration = true;

    @IConfig(config = "mythicconfig", category = "truesilverOre", key = "truesilverVeinSize", comment = "Set Max Truesilver Vein Size (Default: 2)")
    public static int truesilverVeinSize = 2;

    @IConfig(config = "mythicconfig", category = "truesilverOre", key = "truesilverPerChunk", comment = "Set Truesilver Spawn Chance (Default: 6)")
    public static int truesilverPerChunk = 6;

    @IConfig(config = "mythicconfig", category = "truesilverOre", key = "truesilverMinHeight", comment = "Set Truesilver Min Spawn Height (Default: 10)")
    public static int truesilverMinHeight = 10;

    @IConfig(config = "mythicconfig", category = "truesilverOre", key = "truesilverMaxHeight", comment = "Set Truesilver Max Spawn Height (Default: 100)")
    public static int truesilverMaxHeight = 100;


    //Unobtainium Ore Settings
    @IConfig(config = "mythicconfig", category = "unobtainiumOre", key = "unobtainiumGeneration", comment = "Generate Unobtainium Ore")
    public static boolean unobtainiumGeneration = true;

    @IConfig(config = "mythicconfig", category = "unobtainiumOre", key = "unobtainiumVeinSize", comment = "Set Max Unobtainium Vein Size (Default: 2)")
    public static int unobtainiumVeinSize = 2;

    @IConfig(config = "mythicconfig", category = "unobtainiumOre", key = "unobtainiumMinHeight", comment = "Set Unobtainium Min Spawn Height (Default: 7)")
    public static int unobtainiumAverageHeight = 7;

    @IConfig(config = "mythicconfig", category = "unobtainiumOre", key = "unobtainiumMaxHeight", comment = "Set Unobtainium Max Spawn Height (Default: 24)")
    public static int unobtainiumSpread = 24;


    //Ur Ore Settings
    @IConfig(config = "mythicconfig", category = "urOre", key = "urGeneration", comment = "Generate Ur Ore")
    public static boolean urGeneration = true;

    @IConfig(config = "mythicconfig", category = "urOre", key = "urVeinSize", comment = "Set Max Ur Vein Size (Default: 2)")
    public static int urVeinSize = 2;

    @IConfig(config = "mythicconfig", category = "urOre", key = "urPerChunk", comment = "Set Ur Spawn Chance (Default: 1)")
    public static int urPerChunk = 1;

    @IConfig(config = "mythicconfig", category = "urOre", key = "urMinHeight", comment = "Set Ur Min Spawn Height (Default: 10)")
    public static int urMinHeight = 10;

    @IConfig(config = "mythicconfig", category = "urOre", key = "urMaxHeight", comment = "Set Ur Max Spawn Height (Default: 70)")
    public static int urMaxHeight = 70;


    //Vermiculite Ore Settings
    @IConfig(config = "mythicconfig", category = "vermiculiteOre", key = "vermiculiteGeneration", comment = "Generate Vermiculite Ore")
    public static boolean vermiculiteGeneration = true;

    @IConfig(config = "mythicconfig", category = "vermiculiteOre", key = "vermiculiteVeinSize", comment = "Set Max Vermiculite Vein Size (Default: 8)")
    public static int vermiculiteVeinSize = 8;

    @IConfig(config = "mythicconfig", category = "vermiculiteOre", key = "vermiculitePerChunk", comment = "Set Vermiculite Spawn Chance (Default: 8)")
    public static int vermiculitePerChunk = 8;

    @IConfig(config = "mythicconfig", category = "vermiculiteOre", key = "vermiculiteMinHeight", comment = "Set Vermiculite Min Spawn Height (Default: 6)")
    public static int vermiculiteMinHeight = 6;

    @IConfig(config = "mythicconfig", category = "vermiculiteOre", key = "vermiculiteMaxHeight", comment = "Set Vermiculite Max Spawn Height (Default: 128)")
    public static int vermiculiteMaxHeight = 128;


    //Zinc Ore Settings
    @IConfig(config = "mythicconfig", category = "zincOre", key = "zincGeneration", comment = "Generate Zinc Ore")
    public static boolean zincGeneration = true;

    @IConfig(config = "mythicconfig", category = "zincOre", key = "zincVeinSize", comment = "Set Max Zinc Vein Size (Default: 6)")
    public static int zincVeinSize = 6;

    @IConfig(config = "mythicconfig", category = "zincOre", key = "zincPerChunk", comment = "Set Zinc Spawn Chance (Default: 6)")
    public static int zincPerChunk = 6;

    @IConfig(config = "mythicconfig", category = "zincOre", key = "zincMinHeight", comment = "Set Zinc Min Spawn Height (Default: 6)")
    public static int zincMinHeight = 6;

    @IConfig(config = "mythicconfig", category = "zincOre", key = "zincMaxHeight", comment = "Set Zinc Max Spawn Height (Default: 128)")
    public static int zincMaxHeight = 128;
}