package nourl.mythicmetals.config;

import me.sargunvohra.mcmods.autoconfig1u.ConfigData;
import me.sargunvohra.mcmods.autoconfig1u.annotation.Config;
import me.sargunvohra.mcmods.autoconfig1u.annotation.ConfigEntry;
import me.sargunvohra.mcmods.autoconfig1u.serializer.PartitioningSerializer;

@Config(name = "mythic_metals")
public class MythicConfig extends PartitioningSerializer.GlobalData {

    @ConfigEntry.Category("general")
    @ConfigEntry.Gui.TransitiveObject
    public MythicGeneralConfig mythgeneral = new MythicGeneralConfig();

    @ConfigEntry.Category("ores")
    @ConfigEntry.Gui.TransitiveObject
    public MythicOreConfig mythores = new MythicOreConfig();


    @Config(name = "general")
    public static class MythicGeneralConfig implements ConfigData {
        @ConfigEntry.Gui.RequiresRestart
        public boolean enableDusts = false;
    }

    @Config(name = "ores")
    public static class MythicOreConfig implements ConfigData {
        //Adamantite Ore Settings
        @ConfigEntry.Gui.RequiresRestart
        public boolean oreAdamantiteGeneration = true;
        @ConfigEntry.BoundedDiscrete(min = 1, max = 64)
        @ConfigEntry.Gui.RequiresRestart
        public int oreAdamantiteVeinSize = 4;
        @ConfigEntry.BoundedDiscrete(min = 1, max = 64)
        @ConfigEntry.Gui.RequiresRestart
        public int oreAdamantitePerChunk = 1;
        @ConfigEntry.Gui.RequiresRestart
        public int oreAdamantiteMinHeight = 4;
        @ConfigEntry.Gui.RequiresRestart
        public int oreAdamantiteMaxHeight = 24;

        //HashMap<ConfiguredFeature<?,?>, Boolean, Integer> adamantiteConfig = new HashMap<>();

        //Aetherium Ore Settings
        @ConfigEntry.Gui.RequiresRestart
        public boolean oreAetheriumGeneration = true;
        @ConfigEntry.BoundedDiscrete(min = 1, max = 64)
        @ConfigEntry.Gui.RequiresRestart
        public int oreAetheriumVeinSize = 4;
        @ConfigEntry.BoundedDiscrete(min = 1, max = 64)
        @ConfigEntry.Gui.RequiresRestart
        public int oreAetheriumPerChunk = 3;
        @ConfigEntry.Gui.RequiresRestart
        public int oreAetheriumMinHeight = 70;
        @ConfigEntry.Gui.RequiresRestart
        public int oreAetheriumMaxHeight = 180;


        //Aquarium Ore Settings
        @ConfigEntry.Gui.RequiresRestart
        public boolean oreAquariumGeneration = true;
        @ConfigEntry.BoundedDiscrete(min = 1, max = 64)
        @ConfigEntry.Gui.RequiresRestart
        public int oreAquariumVeinSize = 10;
        @ConfigEntry.BoundedDiscrete(min = 1, max = 64)
        @ConfigEntry.Gui.RequiresRestart
        public int oreAquariumPerChunk = 4;
        @ConfigEntry.Gui.RequiresRestart
        public int oreAquariumMinHeight = 6;
        @ConfigEntry.Gui.RequiresRestart
        public int oreAquariumMaxHeight = 63;


        //Banglum Ore Settings
        @ConfigEntry.Gui.RequiresRestart
        public boolean oreBanglumGeneration = true;
        @ConfigEntry.BoundedDiscrete(min = 1, max = 64)
        @ConfigEntry.Gui.RequiresRestart
        public int oreBanglumVeinSize = 7;
        @ConfigEntry.BoundedDiscrete(min = 1, max = 64)
        @ConfigEntry.Gui.RequiresRestart
        public int oreBanglumPerChunk = 3;
        @ConfigEntry.Gui.RequiresRestart
        public int oreBanglumMinHeight = 6;
        @ConfigEntry.Gui.RequiresRestart
        public int oreBanglumMaxHeight = 32;


        //Carmot Ore Settings
        @ConfigEntry.Gui.RequiresRestart
        public boolean oreCarmotGeneration = true;
        @ConfigEntry.BoundedDiscrete(min = 1, max = 64)
        @ConfigEntry.Gui.RequiresRestart
        public int oreCarmotVeinSize = 3;
        @ConfigEntry.BoundedDiscrete(min = 1, max = 64)
        @ConfigEntry.Gui.RequiresRestart
        public int oreCarmotPerChunk = 4;
        @ConfigEntry.Gui.RequiresRestart
        public int oreCarmotMinHeight = 8;
        @ConfigEntry.Gui.RequiresRestart
        public int oreCarmotMaxHeight = 48;


        //Copper Ore Settings
        @ConfigEntry.Gui.RequiresRestart
        public boolean oreCopperGeneration = true;
        @ConfigEntry.BoundedDiscrete(min = 1, max = 64)
        @ConfigEntry.Gui.RequiresRestart
        public int oreCopperVeinSize = 12;
        @ConfigEntry.BoundedDiscrete(min = 1, max = 64)
        @ConfigEntry.Gui.RequiresRestart
        public int oreCopperPerChunk = 6;
        @ConfigEntry.Gui.RequiresRestart
        public int oreCopperMinHeight = 32;
        @ConfigEntry.Gui.RequiresRestart
        public int oreCopperMaxHeight = 128;


        //Kyber Ore Settings
        @ConfigEntry.Gui.RequiresRestart
        public boolean oreKyberGeneration = true;
        @ConfigEntry.BoundedDiscrete(min = 1, max = 64)
        @ConfigEntry.Gui.RequiresRestart
        public int oreKyberVeinSize = 10;
        @ConfigEntry.BoundedDiscrete(min = 1, max = 64)
        @ConfigEntry.Gui.RequiresRestart
        public int oreKyberPerChunk = 1;
        @ConfigEntry.Gui.RequiresRestart
        public int oreKyberMinHeight = 4;
        @ConfigEntry.Gui.RequiresRestart
        public int oreKyberMaxHeight = 28;


        //Manganese Ore Settings
        @ConfigEntry.Gui.RequiresRestart
        public boolean oreManganeseGeneration = true;
        @ConfigEntry.BoundedDiscrete(min = 1, max = 64)
        @ConfigEntry.Gui.RequiresRestart
        public int oreManganeseVeinSize = 6;
        @ConfigEntry.BoundedDiscrete(min = 1, max = 64)
        @ConfigEntry.Gui.RequiresRestart
        public int oreManganesePerChunk = 4;
        @ConfigEntry.Gui.RequiresRestart
        public int oreManganeseMinHeight = 6;
        @ConfigEntry.Gui.RequiresRestart
        public int oreManganeseMaxHeight = 48;


        //Midas Gold Ore Settings
        @ConfigEntry.Gui.RequiresRestart
        public boolean oreMidasGoldGeneration = true;
        @ConfigEntry.BoundedDiscrete(min = 1, max = 64)
        @ConfigEntry.Gui.RequiresRestart
        public int oreMidasgoldVeinSize = 6;
        @ConfigEntry.BoundedDiscrete(min = 1, max = 64)
        @ConfigEntry.Gui.RequiresRestart
        public int oreMidasgoldPerChunk = 7;
        @ConfigEntry.Gui.RequiresRestart
        public int oreMidasgoldMinHeight = 10;
        @ConfigEntry.Gui.RequiresRestart
        public int oreMidasgoldMaxHeight = 128;


        //Mythril Ore Settings
        @ConfigEntry.Gui.RequiresRestart
        public boolean oreMythrilGeneration = true;
        @ConfigEntry.BoundedDiscrete(min = 1, max = 64)
        @ConfigEntry.Gui.RequiresRestart
        public int oreMythrilVeinSize = 4;
        @ConfigEntry.BoundedDiscrete(min = 1, max = 64)
        @ConfigEntry.Gui.RequiresRestart
        public int oreMythrilPerChunk = 1;
        @ConfigEntry.Gui.RequiresRestart
        public int oreMythrilMinHeight = 4;
        @ConfigEntry.Gui.RequiresRestart
        public int oreMythrilMaxHeight = 24;


        //Orichalcum Ore Settings
        @ConfigEntry.Gui.RequiresRestart
        public boolean oreOrichalcumGeneration = true;
        @ConfigEntry.BoundedDiscrete(min = 1, max = 64)
        @ConfigEntry.Gui.RequiresRestart
        public int oreOrichalcumVeinSize = 4;
        @ConfigEntry.BoundedDiscrete(min = 1, max = 64)
        @ConfigEntry.Gui.RequiresRestart
        public int oreOrichalcumPerChunk = 1;
        @ConfigEntry.Gui.RequiresRestart
        public int oreOrichalcumMinHeight = 4;
        @ConfigEntry.Gui.RequiresRestart
        public int oreOrichalcumMaxHeight = 24;


        //Osmium Ore Settings
        @ConfigEntry.Gui.RequiresRestart
        public boolean oreOsmiumGeneration = true;
        @ConfigEntry.BoundedDiscrete(min = 1, max = 64)
        @ConfigEntry.Gui.RequiresRestart
        public int oreOsmiumVeinSize = 9;
        @ConfigEntry.BoundedDiscrete(min = 1, max = 64)
        @ConfigEntry.Gui.RequiresRestart
        public int oreOsmiumPerChunk = 6;
        @ConfigEntry.Gui.RequiresRestart
        public int oreOsmiumMinHeight = 6;
        @ConfigEntry.Gui.RequiresRestart
        public int oreOsmiumMaxHeight = 48;


        //Platinum Ore Settings
        @ConfigEntry.Gui.RequiresRestart
        public boolean orePlatinumGeneration = true;
        @ConfigEntry.BoundedDiscrete(min = 1, max = 64)
        @ConfigEntry.Gui.RequiresRestart
        public int orePlatinumVeinSize = 6;
        @ConfigEntry.BoundedDiscrete(min = 1, max = 64)
        @ConfigEntry.Gui.RequiresRestart
        public int orePlatinumPerChunk = 2;
        @ConfigEntry.Gui.RequiresRestart
        public int orePlatinumMinHeight = 6;
        @ConfigEntry.Gui.RequiresRestart
        public int orePlatinumMaxHeight = 24;


        //Prometheum Ore Settings
        @ConfigEntry.Gui.RequiresRestart
        public boolean orePrometheumGeneration = true;
        @ConfigEntry.BoundedDiscrete(min = 1, max = 64)
        @ConfigEntry.Gui.RequiresRestart
        public int orePrometheumVeinSize = 5;
        @ConfigEntry.BoundedDiscrete(min = 1, max = 64)
        @ConfigEntry.Gui.RequiresRestart
        public int orePrometheumPerChunk = 5;
        @ConfigEntry.Gui.RequiresRestart
        public int orePrometheumMinHeight = 6;
        @ConfigEntry.Gui.RequiresRestart
        public int orePrometheumMaxHeight = 48;


        //Quadrillum Ore Settings
        @ConfigEntry.Gui.RequiresRestart
        public boolean oreQuadrillumGeneration = true;
        @ConfigEntry.BoundedDiscrete(min = 1, max = 64)
        @ConfigEntry.Gui.RequiresRestart
        public int oreQuadrillumVeinSize = 8;
        @ConfigEntry.BoundedDiscrete(min = 1, max = 64)
        @ConfigEntry.Gui.RequiresRestart
        public int oreQuadrillumPerChunk = 4;
        @ConfigEntry.Gui.RequiresRestart
        public int oreQuadrillumMinHeight = 6;
        @ConfigEntry.Gui.RequiresRestart
        public int oreQuadrillumMaxHeight = 56;


        //Runite Ore Settings
        @ConfigEntry.Gui.RequiresRestart
        public boolean oreRuniteGeneration = true;
        @ConfigEntry.BoundedDiscrete(min = 1, max = 64)
        @ConfigEntry.Gui.RequiresRestart
        public int oreRuniteVeinSize = 3;
        @ConfigEntry.BoundedDiscrete(min = 1, max = 64)
        @ConfigEntry.Gui.RequiresRestart
        public int oreRunitePerChunk = 5;
        @ConfigEntry.Gui.RequiresRestart
        public int oreRuniteMinHeight = 6;
        @ConfigEntry.Gui.RequiresRestart
        public int oreRuniteMaxHeight = 32;


        //Silver Ore Settings
        @ConfigEntry.Gui.RequiresRestart
        public boolean oreSilverGeneration = true;
        @ConfigEntry.BoundedDiscrete(min = 1, max = 64)
        @ConfigEntry.Gui.RequiresRestart
        public int oreSilverVeinSize = 8;
        @ConfigEntry.BoundedDiscrete(min = 1, max = 64)
        @ConfigEntry.Gui.RequiresRestart
        public int oreSilverPerChunk = 4;
        @ConfigEntry.Gui.RequiresRestart
        public int oreSilverMinHeight = 6;
        @ConfigEntry.Gui.RequiresRestart
        public int oreSilverMaxHeight = 48;


        //Starrite Ore Settings
        @ConfigEntry.Gui.RequiresRestart
        public boolean oreStarriteGeneration = true;
        @ConfigEntry.BoundedDiscrete(min = 1, max = 64)
        @ConfigEntry.Gui.RequiresRestart
        public int oreStarriteVeinSize = 5;
        @ConfigEntry.BoundedDiscrete(min = 1, max = 64)
        @ConfigEntry.Gui.RequiresRestart
        public int oreStarritePerChunk = 2;
        @ConfigEntry.Gui.RequiresRestart
        public int oreStarriteMinHeight = 80;
        @ConfigEntry.Gui.RequiresRestart
        public int oreStarriteMaxHeight = 200;


        //Stormyx Ore Settings
        @ConfigEntry.Gui.RequiresRestart
        public boolean oreStormyxGeneration = true;
        @ConfigEntry.BoundedDiscrete(min = 1, max = 64)
        @ConfigEntry.Gui.RequiresRestart
        public int oreStormyxVeinSize = 8;
        @ConfigEntry.BoundedDiscrete(min = 1, max = 64)
        @ConfigEntry.Gui.RequiresRestart
        public int oreStormyxPerChunk = 3;
        @ConfigEntry.Gui.RequiresRestart
        public int oreStormyxMinHeight = 6;
        @ConfigEntry.Gui.RequiresRestart
        public int oreStormyxMaxHeight = 54;


        //Tantalite Ore Settings
        @ConfigEntry.Gui.RequiresRestart
        public boolean oreTantaliteGeneration = true;
        @ConfigEntry.BoundedDiscrete(min = 1, max = 64)
        @ConfigEntry.Gui.RequiresRestart
        public int oreTantaliteVeinSize = 8;
        @ConfigEntry.BoundedDiscrete(min = 1, max = 64)
        @ConfigEntry.Gui.RequiresRestart
        public int oreTantalitePerChunk = 4;
        @ConfigEntry.Gui.RequiresRestart
        public int oreTantaliteMinHeight = 6;
        @ConfigEntry.Gui.RequiresRestart
        public int oreTantaliteMaxHeight = 48;


        //Tin Ore Settings
        @ConfigEntry.Gui.RequiresRestart
        public boolean oreTinGeneration = true;
        @ConfigEntry.BoundedDiscrete(min = 1, max = 64)
        @ConfigEntry.Gui.RequiresRestart
        public int oreTinVeinSize = 8;
        @ConfigEntry.BoundedDiscrete(min = 1, max = 64)
        @ConfigEntry.Gui.RequiresRestart
        public int oreTinPerChunk = 4;
        @ConfigEntry.Gui.RequiresRestart
        public int oreTinMinHeight = 18;
        @ConfigEntry.Gui.RequiresRestart
        public int oreTinMaxHeight = 128;


        //Truesilver Ore Settings
        @ConfigEntry.Gui.RequiresRestart
        public boolean oreTruesilverGeneration = true;
        @ConfigEntry.BoundedDiscrete(min = 1, max = 64)
        @ConfigEntry.Gui.RequiresRestart
        public int oreTruesilverVeinSize = 4;
        @ConfigEntry.BoundedDiscrete(min = 1, max = 64)
        @ConfigEntry.Gui.RequiresRestart
        public int oreTruesilverPerChunk = 6;
        @ConfigEntry.Gui.RequiresRestart
        public int oreTruesilverMinHeight = 8;
        @ConfigEntry.Gui.RequiresRestart
        public int oreTruesilverMaxHeight = 100;


        //Unobtainium Ore Settings
        @ConfigEntry.Gui.RequiresRestart
        public boolean oreUnobtainiumGeneration = true;
        @ConfigEntry.BoundedDiscrete(min = 1, max = 64)
        @ConfigEntry.Gui.RequiresRestart
        public int oreUnobtainiumVeinSize = 2;
        @ConfigEntry.Gui.RequiresRestart
        public int oreUnobtainiumMinHeight = 4;
        @ConfigEntry.BoundedDiscrete(min = 1, max = 64)
        @ConfigEntry.Gui.RequiresRestart
        public int oreUnobtainiumMaxHeight = 10;
        @ConfigEntry.Gui.RequiresRestart
        public float oreUnobtainiumDiscardChance = 0.8F;


        //Vermiculite Ore Settings
        @ConfigEntry.Gui.RequiresRestart
        public boolean oreVermiculiteGeneration = true;
        @ConfigEntry.BoundedDiscrete(min = 1, max = 64)
        @ConfigEntry.Gui.RequiresRestart
        public int oreVermiculiteVeinSize = 8;
        @ConfigEntry.BoundedDiscrete(min = 1, max = 64)
        @ConfigEntry.Gui.RequiresRestart
        public int oreVermiculitePerChunk = 8;
        @ConfigEntry.Gui.RequiresRestart
        public int oreVermiculiteMinHeight = 20;
        @ConfigEntry.Gui.RequiresRestart
        public int oreVermiculiteMaxHeight = 128;


        //Zinc Ore Settings
        @ConfigEntry.Gui.RequiresRestart
        public boolean oreZincGeneration = true;
        @ConfigEntry.BoundedDiscrete(min = 1, max = 64)
        @ConfigEntry.Gui.RequiresRestart
        public int oreZincVeinSize = 6;
        @ConfigEntry.BoundedDiscrete(min = 1, max = 64)
        @ConfigEntry.Gui.RequiresRestart
        public int oreZincPerChunk = 6;
        @ConfigEntry.Gui.RequiresRestart
        public int oreZincMinHeight = 6;
        @ConfigEntry.Gui.RequiresRestart
        public int oreZincMaxHeight = 128;
    }
}