package nourl.mythicmetals.config;


import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;
import me.shedaniel.autoconfig.serializer.PartitioningSerializer;

@Config(name = "mythic_metals")
public class MythicConfig extends PartitioningSerializer.GlobalData {

    @ConfigEntry.Category("general")
    @ConfigEntry.Gui.TransitiveObject
    @ConfigEntry.Gui.RequiresRestart
    public MythicGeneralConfig mythgeneral = new MythicGeneralConfig();

    @ConfigEntry.Category("ores")
    @ConfigEntry.Gui.TransitiveObject
    @ConfigEntry.Gui.RequiresRestart
    public MythicOreConfig mythores = new MythicOreConfig();


    @Config(name = "general")
    public static class MythicGeneralConfig implements ConfigData {
        public boolean enableDusts = false;
        public boolean enableNuggets = true;
    }

    @Config(name = "ores")
    public static class MythicOreConfig implements ConfigData {
        //Adamantite Ore Settings
        public boolean oreAdamantiteGeneration = true;
        @ConfigEntry.BoundedDiscrete(min = 1, max = 64)
        public int oreAdamantiteVeinSize = 4;
        @ConfigEntry.BoundedDiscrete(min = 1, max = 64)
        public int oreAdamantitePerChunk = 1;
        public int oreAdamantiteMinHeight = 4;
        public int oreAdamantiteMaxHeight = 24;

        //Aetherium Ore Settings
        public boolean oreAetheriumGeneration = true;
        @ConfigEntry.BoundedDiscrete(min = 1, max = 64)
        public int oreAetheriumVeinSize = 4;
        @ConfigEntry.BoundedDiscrete(min = 1, max = 64)
        public int oreAetheriumPerChunk = 3;
        public int oreAetheriumMinHeight = 70;
        public int oreAetheriumMaxHeight = 180;

        //Aquarium Ore Settings
        public boolean oreAquariumGeneration = true;
        @ConfigEntry.BoundedDiscrete(min = 1, max = 64)
        public int oreAquariumVeinSize = 10;
        @ConfigEntry.BoundedDiscrete(min = 1, max = 64)
        public int oreAquariumPerChunk = 4;
        public int oreAquariumMinHeight = 6;
        public int oreAquariumMaxHeight = 63;

        //Banglum Ore Settings
        public boolean oreBanglumGeneration = true;
        @ConfigEntry.BoundedDiscrete(min = 1, max = 64)
        public int oreBanglumVeinSize = 7;
        @ConfigEntry.BoundedDiscrete(min = 1, max = 64)
        public int oreBanglumPerChunk = 3;
        public int oreBanglumMinHeight = 6;
        public int oreBanglumMaxHeight = 32;

        //Carmot Ore Settings
        public boolean oreCarmotGeneration = true;
        @ConfigEntry.BoundedDiscrete(min = 1, max = 64)
        public int oreCarmotVeinSize = 3;
        @ConfigEntry.BoundedDiscrete(min = 1, max = 64)
        public int oreCarmotPerChunk = 4;
        public int oreCarmotMinHeight = 8;
        public int oreCarmotMaxHeight = 48;

        //Kyber Ore Settings
        public boolean oreKyberGeneration = true;
        @ConfigEntry.BoundedDiscrete(min = 1, max = 64)
        public int oreKyberVeinSize = 3;
        @ConfigEntry.BoundedDiscrete(min = 1, max = 64)
        public int oreKyberPerChunk = 1;
        public int oreKyberMinHeight = 5;
        public int oreKyberMaxHeight = 70;

        //Manganese Ore Settings
        public boolean oreManganeseGeneration = true;
        @ConfigEntry.BoundedDiscrete(min = 1, max = 64)
        public int oreManganeseVeinSize = 6;
        @ConfigEntry.BoundedDiscrete(min = 1, max = 64)
        public int oreManganesePerChunk = 4;
        public int oreManganeseMinHeight = 6;
        public int oreManganeseMaxHeight = 48;

        //Midas Gold Ore Settings
        public boolean oreMidasGoldGeneration = true;
        @ConfigEntry.BoundedDiscrete(min = 1, max = 64)
        public int oreMidasgoldVeinSize = 6;
        @ConfigEntry.BoundedDiscrete(min = 1, max = 64)
        public int oreMidasgoldPerChunk = 7;
        public int oreMidasgoldMinHeight = 10;
        public int oreMidasgoldMaxHeight = 128;

        //Mythril Ore Settings
        public boolean oreMythrilGeneration = true;
        @ConfigEntry.BoundedDiscrete(min = 1, max = 64)
        public int oreMythrilVeinSize = 4;
        @ConfigEntry.BoundedDiscrete(min = 1, max = 64)
        public int oreMythrilPerChunk = 1;
        public int oreMythrilMinHeight = 4;
        public int oreMythrilMaxHeight = 24;

        //Orichalcum Ore Settings
        public boolean oreOrichalcumGeneration = true;
        @ConfigEntry.BoundedDiscrete(min = 1, max = 64)
        public int oreOrichalcumVeinSize = 4;
        @ConfigEntry.BoundedDiscrete(min = 1, max = 64)
        public int oreOrichalcumPerChunk = 1;
        public int oreOrichalcumMinHeight = 4;
        public int oreOrichalcumMaxHeight = 24;

        //Osmium Ore Settings
        public boolean oreOsmiumGeneration = true;
        @ConfigEntry.BoundedDiscrete(min = 1, max = 64)
        public int oreOsmiumVeinSize = 9;
        @ConfigEntry.BoundedDiscrete(min = 1, max = 64)
        public int oreOsmiumPerChunk = 6;
        public int oreOsmiumMinHeight = 6;
        public int oreOsmiumMaxHeight = 48;

        //Platinum Ore Settings
        public boolean orePlatinumGeneration = true;
        @ConfigEntry.BoundedDiscrete(min = 1, max = 64)
        public int orePlatinumVeinSize = 6;
        @ConfigEntry.BoundedDiscrete(min = 1, max = 64)
        public int orePlatinumPerChunk = 2;
        public int orePlatinumMinHeight = 6;
        public int orePlatinumMaxHeight = 24;

        //Prometheum Ore Settings
        public boolean orePrometheumGeneration = true;
        @ConfigEntry.BoundedDiscrete(min = 1, max = 64)
        public int orePrometheumVeinSize = 5;
        @ConfigEntry.BoundedDiscrete(min = 1, max = 64)
        public int orePrometheumPerChunk = 5;
        public int orePrometheumMinHeight = 6;
        public int orePrometheumMaxHeight = 48;

        //Quadrillum Ore Settings
        public boolean oreQuadrillumGeneration = true;
        @ConfigEntry.BoundedDiscrete(min = 1, max = 64)
        public int oreQuadrillumVeinSize = 8;
        @ConfigEntry.BoundedDiscrete(min = 1, max = 64)
        public int oreQuadrillumPerChunk = 4;
        public int oreQuadrillumMinHeight = 6;
        public int oreQuadrillumMaxHeight = 56;

        //Runite Ore Settings
        public boolean oreRuniteGeneration = true;
        @ConfigEntry.BoundedDiscrete(min = 1, max = 64)
        public int oreRuniteVeinSize = 3;
        @ConfigEntry.BoundedDiscrete(min = 1, max = 64)
        public int oreRunitePerChunk = 5;
        public int oreRuniteMinHeight = 6;
        public int oreRuniteMaxHeight = 32;

        //Silver Ore Settings
        public boolean oreSilverGeneration = true;
        @ConfigEntry.BoundedDiscrete(min = 1, max = 64)
        public int oreSilverVeinSize = 8;
        @ConfigEntry.BoundedDiscrete(min = 1, max = 64)
        public int oreSilverPerChunk = 4;
        public int oreSilverMinHeight = 6;
        public int oreSilverMaxHeight = 48;

        //Starrite Ore Settings
        public boolean oreStarriteGeneration = true;
        @ConfigEntry.BoundedDiscrete(min = 1, max = 64)
        public int oreStarriteVeinSize = 5;
        @ConfigEntry.BoundedDiscrete(min = 1, max = 64)
        public int oreStarritePerChunk = 2;
        public int oreStarriteMinHeight = 80;
        public int oreStarriteMaxHeight = 200;

        //Stormyx Ore Settings
        public boolean oreStormyxGeneration = true;
        @ConfigEntry.BoundedDiscrete(min = 1, max = 64)
        public int oreStormyxVeinSize = 8;
        @ConfigEntry.BoundedDiscrete(min = 1, max = 64)
        public int oreStormyxPerChunk = 3;
        public int oreStormyxMinHeight = 6;
        public int oreStormyxMaxHeight = 54;

        //Tin Ore Settings
        public boolean oreTinGeneration = true;
        @ConfigEntry.BoundedDiscrete(min = 1, max = 64)
        public int oreTinVeinSize = 8;
        @ConfigEntry.BoundedDiscrete(min = 1, max = 64)
        public int oreTinPerChunk = 4;
        public int oreTinMinHeight = 18;
        public int oreTinMaxHeight = 128;

        //Truesilver Ore Settings
        public boolean oreTruesilverGeneration = true;
        @ConfigEntry.BoundedDiscrete(min = 1, max = 64)
        public int oreTruesilverVeinSize = 4;
        @ConfigEntry.BoundedDiscrete(min = 1, max = 64)
        public int oreTruesilverPerChunk = 6;
        public int oreTruesilverMinHeight = 8;
        public int oreTruesilverMaxHeight = 100;

        //Unobtainium Ore Settings
        public boolean oreUnobtainiumGeneration = true;
        @ConfigEntry.BoundedDiscrete(min = 1, max = 64)
        public int oreUnobtainiumVeinSize = 3;
        public int oreUnobtainiumMinHeight = 4;
        @ConfigEntry.BoundedDiscrete(min = 1, max = 64)
        public int oreUnobtainiumMaxHeight = 12;
        public float oreUnobtainiumDiscardChance = 0.75F;

        //Vermiculite Ore Settings
        public boolean oreVermiculiteGeneration = true;
        @ConfigEntry.BoundedDiscrete(min = 1, max = 64)
        public int oreVermiculiteVeinSize = 8;
        @ConfigEntry.BoundedDiscrete(min = 1, max = 64)
        public int oreVermiculitePerChunk = 8;
        public int oreVermiculiteMinHeight = 20;
        public int oreVermiculiteMaxHeight = 128;
    }
}