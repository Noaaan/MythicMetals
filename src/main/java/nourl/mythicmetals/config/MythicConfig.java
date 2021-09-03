package nourl.mythicmetals.config;


import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;
import nourl.mythicmetals.MythicMetals;

@Config(name = MythicMetals.MOD_ID)
public class MythicConfig implements ConfigData {
    @ConfigEntry.Gui.RequiresRestart

    public boolean enableDusts = false;
    public boolean enableNuggets = true;

    //Adamantite Ore Settings
    @ConfigEntry.Gui.CollapsibleObject
    public OreConfig adamantite = new OreConfig(true, 5, 1, -56, 8, .25f);
    @ConfigEntry.Gui.CollapsibleObject
    public OreConfig aetherium = new OreConfig(true, 4, 3, 80, 60, .125f);
    @ConfigEntry.Gui.CollapsibleObject
    public OreConfig aquarium = new OreConfig(true, 9, 4, 39, 63, .0f);
    @ConfigEntry.Gui.CollapsibleObject
    public OreConfig banglum = new OreConfig(true, 6, 4, 38, 52, .25f);
    @ConfigEntry.Gui.CollapsibleObject
    public OreConfig carmot = new OreConfig(true,4, 3, 16 ,32, .0f);
    @ConfigEntry.Gui.CollapsibleObject
    public OreConfig calcite_kyber = new OreConfig(true, 15, 40, 4, 56, .5f);
    @ConfigEntry.Gui.CollapsibleObject
    public OreConfig end_starrite = new OreConfig(true, 7, 2, 40, 20, 0.0f);
    @ConfigEntry.Gui.CollapsibleObject
    public OreConfig kyber = new OreConfig(true, 3, 1, 12, 40,.5f);
    @ConfigEntry.Gui.CollapsibleObject
    public OreConfig manganese = new OreConfig(true, 9, 2, 28, 48,.25f);
    @ConfigEntry.Gui.CollapsibleObject
    public OreConfig midas_gold = new OreConfig(true,6, 7, 10, 128,.125f);
    @ConfigEntry.Gui.CollapsibleObject
    public OreConfig mythril = new OreConfig(true, 4, 1, -36, 16, .25f);
    @ConfigEntry.Gui.CollapsibleObject
    public OreConfig orichalcum = new OreConfig(true, 4, 1, 8, 24, .25f);
    @ConfigEntry.Gui.CollapsibleObject
    public OreConfig osmium = new OreConfig(true, 6, 5, 20, 40, .25f);
    @ConfigEntry.Gui.CollapsibleObject
    public OreConfig overworld_nether_ores = new OreConfig(true, 4, 1, 40, 70, .125f);
    @ConfigEntry.Gui.CollapsibleObject
    public OreConfig palladium = new OreConfig(true, 4, 1, 16, 40, .0f);
    @ConfigEntry.Gui.CollapsibleObject
    public OreConfig platinum = new OreConfig(true, 6, 2, 8, 32, .0f);
    @ConfigEntry.Gui.CollapsibleObject
    public OreConfig prometheum = new OreConfig(true, 6, 4, 30, 48, .25f);
    @ConfigEntry.Gui.CollapsibleObject
    public OreConfig quadrillum = new OreConfig(true,7, 2, 16, 44, .25f);
    @ConfigEntry.Gui.CollapsibleObject
    public OreConfig runite = new OreConfig(true, 3, 2, 9, 39, .125f);
    @ConfigEntry.Gui.CollapsibleObject
    public OreConfig silver = new OreConfig(true,8, 4, 6, 28, .25f);
    @ConfigEntry.Gui.CollapsibleObject
    public OreConfig starrite = new OreConfig(true, 3, 1, 50, 100, .25f);
    @ConfigEntry.Gui.CollapsibleObject
    public OreConfig stormyx = new OreConfig(true,8, 2, 4, 116, .125f);
    @ConfigEntry.Gui.CollapsibleObject
    public OreConfig unobtainium = new OreConfig(true,3, 1, -60, 12, .5f);
    @ConfigEntry.Gui.CollapsibleObject
    public OreConfig tin = new OreConfig(true,8, 4, 44, 72, .25f);
    @ConfigEntry.Gui.CollapsibleObject
    public OreConfig vermiculite = new OreConfig(true,8, 4, 64, 108, .25f);
    public int configVersion = 0;
}
