package nourl.mythicmetals.config;

import me.sargunvohra.mcmods.autoconfig1u.ConfigData;
import me.sargunvohra.mcmods.autoconfig1u.annotation.Config;
import me.sargunvohra.mcmods.autoconfig1u.annotation.ConfigEntry;
import me.sargunvohra.mcmods.autoconfig1u.serializer.PartitioningSerializer;

@Config(name = "mythic_metals")
public class MythicConfig extends PartitioningSerializer.GlobalData {

    @ConfigEntry.Category("ores")
    @ConfigEntry.Gui.TransitiveObject
    public MythicOreConfig mythores = new MythicOreConfig();

    @Config(name = "ores")
    public static class MythicOreConfig implements ConfigData {
        //Adamantite Ore Settings
        @ConfigEntry.Gui.RequiresRestart
        public boolean adamantiteGeneration = true;
        @ConfigEntry.Gui.RequiresRestart
        public int adamantiteVeinSize = 5;
        @ConfigEntry.Gui.RequiresRestart
        public int adamantitePerChunk = 1;
        @ConfigEntry.Gui.RequiresRestart
        public int adamantiteMinHeight = 4;
        @ConfigEntry.Gui.RequiresRestart
        public int adamantiteMaxHeight = 24;

        //Aetherium Ore Settings
        @ConfigEntry.Gui.RequiresRestart
        public boolean aetheriumGeneration = true;
        @ConfigEntry.Gui.RequiresRestart
        public int aetheriumVeinSize = 2;
        @ConfigEntry.Gui.RequiresRestart
        public int aetheriumPerChunk = 2;
        @ConfigEntry.Gui.RequiresRestart
        public int aetheriumMinHeight = 70;
        @ConfigEntry.Gui.RequiresRestart
        public int aetheriumMaxHeight = 180;


        //Aquarium Ore Settings
        @ConfigEntry.Gui.RequiresRestart
        public boolean aquariumGeneration = false;
        @ConfigEntry.Gui.RequiresRestart
        public int aquariumVeinSize = 16;
        @ConfigEntry.Gui.RequiresRestart
        public int aquariumPerChunk = 3;
        @ConfigEntry.Gui.RequiresRestart
        public int aquariumMinHeight = 6;
        @ConfigEntry.Gui.RequiresRestart
        public int aquariumMaxHeight = 63;


        //Banglum Ore Settings
        @ConfigEntry.Gui.RequiresRestart
        public boolean banglumGeneration = true;
        @ConfigEntry.Gui.RequiresRestart
        public int banglumVeinSize = 6;
        @ConfigEntry.Gui.RequiresRestart
        public int banglumPerChunk = 2;
        @ConfigEntry.Gui.RequiresRestart
        public int banglumMinHeight = 6;
        @ConfigEntry.Gui.RequiresRestart
        public int banglumMaxHeight = 32;


        //Carmot Ore Settings
        @ConfigEntry.Gui.RequiresRestart
        public boolean carmotGeneration = true;
        @ConfigEntry.Gui.RequiresRestart
        public int carmotVeinSize = 2;
        @ConfigEntry.Gui.RequiresRestart
        public int carmotPerChunk = 4;
        @ConfigEntry.Gui.RequiresRestart
        public int carmotMinHeight = 8;
        @ConfigEntry.Gui.RequiresRestart
        public int carmotMaxHeight = 48;


        //Copper Ore Settings
        @ConfigEntry.Gui.RequiresRestart
        public boolean copperGeneration = true;
        @ConfigEntry.Gui.RequiresRestart
        public int copperVeinSize = 12;
        @ConfigEntry.Gui.RequiresRestart
        public int copperPerChunk = 6;
        @ConfigEntry.Gui.RequiresRestart
        public int copperMinHeight = 32;
        @ConfigEntry.Gui.RequiresRestart
        public int copperMaxHeight = 128;


        //Kyber Ore Settings
        @ConfigEntry.Gui.RequiresRestart
        public boolean kyberGeneration = true;
        @ConfigEntry.Gui.RequiresRestart
        public int kyberVeinSize = 14;
        @ConfigEntry.Gui.RequiresRestart
        public int kyberPerChunk = 2;
        @ConfigEntry.Gui.RequiresRestart
        public int kyberMinHeight = 6;
        @ConfigEntry.Gui.RequiresRestart
        public int kyberMaxHeight = 18;


        //Lutetium Ore Settings
        @ConfigEntry.Gui.RequiresRestart
        public boolean lutetiumGeneration = true;
        @ConfigEntry.Gui.RequiresRestart
        public int lutetiumVeinSize = 5;
        @ConfigEntry.Gui.RequiresRestart
        public int lutetiumPerChunk = 3;
        @ConfigEntry.Gui.RequiresRestart
        public int lutetiumMinHeight = 6;
        @ConfigEntry.Gui.RequiresRestart
        public int lutetiumMaxHeight = 64;


        //Manganese Ore Settings
        @ConfigEntry.Gui.RequiresRestart
        public boolean manganeseGeneration = true;
        @ConfigEntry.Gui.RequiresRestart
        public int manganeseVeinSize = 6;
        @ConfigEntry.Gui.RequiresRestart
        public int manganesePerChunk = 4;
        @ConfigEntry.Gui.RequiresRestart
        public int manganeseMinHeight = 6;
        @ConfigEntry.Gui.RequiresRestart
        public int manganeseMaxHeight = 48;


        //Midas Gold Ore Settings
        @ConfigEntry.Gui.RequiresRestart
        public boolean midasgoldGeneration = true;
        @ConfigEntry.Gui.RequiresRestart
        public int midasgoldVeinSize = 6;
        @ConfigEntry.Gui.RequiresRestart
        public int midasgoldPerChunk = 10;
        @ConfigEntry.Gui.RequiresRestart
        public int midasgoldMinHeight = 10;
        @ConfigEntry.Gui.RequiresRestart
        public int midasgoldMaxHeight = 128;


        //Mythril Ore Settings
        @ConfigEntry.Gui.RequiresRestart
        public boolean mythrilGeneration = true;
        @ConfigEntry.Gui.RequiresRestart
        public int mythrilVeinSize = 5;
        @ConfigEntry.Gui.RequiresRestart
        public int mythrilPerChunk = 1;
        @ConfigEntry.Gui.RequiresRestart
        public int mythrilMinHeight = 4;
        @ConfigEntry.Gui.RequiresRestart
        public int mythrilMaxHeight = 24;


        //Orichalcum Ore Settings
        @ConfigEntry.Gui.RequiresRestart
        public boolean orichalcumGeneration = true;
        @ConfigEntry.Gui.RequiresRestart
        public int orichalcumVeinSize = 5;
        @ConfigEntry.Gui.RequiresRestart
        public int orichalcumPerChunk = 1;
        @ConfigEntry.Gui.RequiresRestart
        public int orichalcumMinHeight = 4;
        @ConfigEntry.Gui.RequiresRestart
        public int orichalcumMaxHeight = 24;


        //Osmium Ore Settings
        @ConfigEntry.Gui.RequiresRestart
        public boolean osmiumGeneration = true;
        @ConfigEntry.Gui.RequiresRestart
        public int osmiumVeinSize = 9;
        @ConfigEntry.Gui.RequiresRestart
        public int osmiumPerChunk = 6;
        @ConfigEntry.Gui.RequiresRestart
        public int osmiumMinHeight = 6;
        @ConfigEntry.Gui.RequiresRestart
        public int osmiumMaxHeight = 48;


        //Platinum Ore Settings
        @ConfigEntry.Gui.RequiresRestart
        public boolean platinumGeneration = true;
        @ConfigEntry.Gui.RequiresRestart
        public int platinumVeinSize = 6;
        @ConfigEntry.Gui.RequiresRestart
        public int platinumPerChunk = 2;
        @ConfigEntry.Gui.RequiresRestart
        public int platinumMinHeight = 6;
        @ConfigEntry.Gui.RequiresRestart
        public int platinumMaxHeight = 24;


        //Prometheum Ore Settings
        @ConfigEntry.Gui.RequiresRestart
        public boolean prometheumGeneration = true;
        @ConfigEntry.Gui.RequiresRestart
        public int prometheumVeinSize = 5;
        @ConfigEntry.Gui.RequiresRestart
        public int prometheumPerChunk = 2;
        @ConfigEntry.Gui.RequiresRestart
        public int prometheumMinHeight = 6;
        @ConfigEntry.Gui.RequiresRestart
        public int prometheumMaxHeight = 24;


        //Quadrillum Ore Settings
        @ConfigEntry.Gui.RequiresRestart
        public boolean quadrillumGeneration = true;
        @ConfigEntry.Gui.RequiresRestart
        public int quadrillumVeinSize = 8;
        @ConfigEntry.Gui.RequiresRestart
        public int quadrillumPerChunk = 8;
        @ConfigEntry.Gui.RequiresRestart
        public int quadrillumMinHeight = 6;
        @ConfigEntry.Gui.RequiresRestart
        public int quadrillumMaxHeight = 56;


        //Runite Ore Settings
        @ConfigEntry.Gui.RequiresRestart
        public boolean runiteGeneration = true;
        @ConfigEntry.Gui.RequiresRestart
        public int runiteVeinSize = 3;
        @ConfigEntry.Gui.RequiresRestart
        public int runitePerChunk = 5;
        @ConfigEntry.Gui.RequiresRestart
        public int runiteMinHeight = 6;
        @ConfigEntry.Gui.RequiresRestart
        public int runiteMaxHeight = 32;


        //Silver Ore Settings
        @ConfigEntry.Gui.RequiresRestart
        public boolean silverGeneration = true;
        @ConfigEntry.Gui.RequiresRestart
        public int silverVeinSize = 6;
        @ConfigEntry.Gui.RequiresRestart
        public int silverPerChunk = 5;
        @ConfigEntry.Gui.RequiresRestart
        public int silverMinHeight = 6;
        @ConfigEntry.Gui.RequiresRestart
        public int silverMaxHeight = 48;


        //Starrite Ore Settings
        @ConfigEntry.Gui.RequiresRestart
        public boolean starriteGeneration = true;
        @ConfigEntry.Gui.RequiresRestart
        public int starriteVeinSize = 5;
        @ConfigEntry.Gui.RequiresRestart
        public int starritePerChunk = 2;
        @ConfigEntry.Gui.RequiresRestart
        public int starriteMinHeight = 150;
        @ConfigEntry.Gui.RequiresRestart
        public int starriteMaxHeight = 200;


        //Stormyx Ore Settings
        @ConfigEntry.Gui.RequiresRestart
        public boolean stormyxGeneration = true;
        @ConfigEntry.Gui.RequiresRestart
        public int stormyxVeinSize = 8;
        @ConfigEntry.Gui.RequiresRestart
        public int stormyxPerChunk = 2;
        @ConfigEntry.Gui.RequiresRestart
        public int stormyxMinHeight = 6;
        @ConfigEntry.Gui.RequiresRestart
        public int stormyxMaxHeight = 54;


        //Tantalite Ore Settings
        @ConfigEntry.Gui.RequiresRestart
        public boolean tantaliteGeneration = true;
        @ConfigEntry.Gui.RequiresRestart
        public int tantaliteVeinSize = 12;
        @ConfigEntry.Gui.RequiresRestart
        public int tantalitePerChunk = 3;
        @ConfigEntry.Gui.RequiresRestart
        public int tantaliteMinHeight = 6;
        @ConfigEntry.Gui.RequiresRestart
        public int tantaliteMaxHeight = 48;


        //Tin Ore Settings
        @ConfigEntry.Gui.RequiresRestart
        public boolean tinGeneration = true;
        @ConfigEntry.Gui.RequiresRestart
        public int tinVeinSize = 8;
        @ConfigEntry.Gui.RequiresRestart
        public int tinPerChunk = 4;
        @ConfigEntry.Gui.RequiresRestart
        public int tinMinHeight = 6;
        @ConfigEntry.Gui.RequiresRestart
        public int tinMaxHeight = 128;


        //Truesilver Ore Settings
        @ConfigEntry.Gui.RequiresRestart
        public boolean truesilverGeneration = true;
        @ConfigEntry.Gui.RequiresRestart
        public int truesilverVeinSize = 2;
        @ConfigEntry.Gui.RequiresRestart
        public int truesilverPerChunk = 6;
        @ConfigEntry.Gui.RequiresRestart
        public int truesilverMinHeight = 10;
        @ConfigEntry.Gui.RequiresRestart
        public int truesilverMaxHeight = 100;


        //Unobtainium Ore Settings
        @ConfigEntry.Gui.RequiresRestart
        public boolean unobtainiumGeneration = true;
        @ConfigEntry.Gui.RequiresRestart
        public int unobtainiumVeinSize = 2;
        @ConfigEntry.Gui.RequiresRestart
        public int unobtainiumAverageHeight = 7;
        @ConfigEntry.Gui.RequiresRestart
        public int unobtainiumSpread = 24;


        //Ur Ore Settings
        @ConfigEntry.Gui.RequiresRestart
        public boolean urGeneration = true;
        @ConfigEntry.Gui.RequiresRestart
        public int urVeinSize = 2;
        @ConfigEntry.Gui.RequiresRestart
        public int urPerChunk = 1;
        @ConfigEntry.Gui.RequiresRestart
        public int urMinHeight = 10;
        @ConfigEntry.Gui.RequiresRestart
        public int urMaxHeight = 70;


        //Vermiculite Ore Settings
        @ConfigEntry.Gui.RequiresRestart
        public boolean vermiculiteGeneration = true;
        @ConfigEntry.Gui.RequiresRestart
        public int vermiculiteVeinSize = 8;
        @ConfigEntry.Gui.RequiresRestart
        public int vermiculitePerChunk = 8;
        @ConfigEntry.Gui.RequiresRestart
        public int vermiculiteMinHeight = 6;
        @ConfigEntry.Gui.RequiresRestart
        public int vermiculiteMaxHeight = 128;


        //Zinc Ore Settings
        @ConfigEntry.Gui.RequiresRestart
        public boolean zincGeneration = true;
        @ConfigEntry.Gui.RequiresRestart
        public int zincVeinSize = 6;
        @ConfigEntry.Gui.RequiresRestart
        public int zincPerChunk = 6;
        @ConfigEntry.Gui.RequiresRestart
        public int zincMinHeight = 6;
        @ConfigEntry.Gui.RequiresRestart
        public int zincMaxHeight = 128;
    }
}