package nourl.mythicmetals.config;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;
import nourl.mythicmetals.MythicMetals;

import java.util.ArrayList;

@Config(name = MythicMetals.MOD_ID)
public class MythicConfig implements ConfigData {
    @ConfigEntry.Gui.RequiresRestart
    public boolean enableDusts = false;
    @ConfigEntry.Gui.RequiresRestart
    public boolean enableNuggets = true;
    @ConfigEntry.Gui.RequiresRestart
    public boolean enableAnvils = true;

    @ConfigEntry.Gui.CollapsibleObject(startExpanded = true)
    public OreConfig adamantite = new OreConfig(true, 5, 1, -54, -20, .125f, false, true);
    @ConfigEntry.Gui.CollapsibleObject(startExpanded = true)
    public OreConfig aquarium = new OreConfig(true, 9, 4, 41, 63, .0f, false);
    @ConfigEntry.Gui.CollapsibleObject(startExpanded = true)
    public OreConfig banglum = new OreConfig(true, 6, 3, 42, 69, .125f, false);
    @ConfigEntry.Gui.CollapsibleObject(startExpanded = true)
    public OreConfig carmot = new OreConfig(true,4, 2, -12 ,8, .125f, false);
    @ConfigEntry.Gui.CollapsibleObject(startExpanded = true)
     public VariantConfig kyber = new VariantConfig(true, 3, 15, 1, 40, 12, -62, 52, 40,.5f, false, false);
    @ConfigEntry.Gui.CollapsibleObject(startExpanded = true)
    public OreConfig manganese = new OreConfig(true, 9, 2, 28, 48,.25f, false);
    @ConfigEntry.Gui.CollapsibleObject(startExpanded = true)
    public OreConfig midas_gold = new OreConfig(true,7, 5, 9, 127,.25f, false, false);
    @ConfigEntry.Gui.CollapsibleObject(startExpanded = true)
    public OreConfig mythril = new OreConfig(true, 5, 1, -24, 4, .125f, false, true);
    @ConfigEntry.Gui.CollapsibleObject(startExpanded = true)
    public OreConfig orichalcum = new OreConfig(true, 5, 1, 8, 8, .0f, true);
    @ConfigEntry.Gui.CollapsibleObject(startExpanded = true)
    public OreConfig osmium = new OreConfig(true, 6, 5, 50, 120, .25f, false);
    @ConfigEntry.Gui.CollapsibleObject(startExpanded = true)
    public OreConfig overworld_nether_ores = new OreConfig(true, 4, 2, 40, 70, .125f, false);
    @ConfigEntry.Gui.CollapsibleObject(startExpanded = true)
    public OreConfig palladium = new OreConfig(true, 5, 2, 14, 35, .0f, false);
    @ConfigEntry.Gui.CollapsibleObject(startExpanded = true)
    public OreConfig platinum = new OreConfig(true, 6, 2, 8, 32, .0f, false, true);
    @ConfigEntry.Gui.CollapsibleObject(startExpanded = true)
    public OreConfig prometheum = new OreConfig(true, 6, 4, 30, 48, .0f, true);
    @ConfigEntry.Gui.CollapsibleObject(startExpanded = true)
    public OreConfig quadrillum = new OreConfig(true,7, 2, 12, 40, .25f, false);
    @ConfigEntry.Gui.CollapsibleObject(startExpanded = true)
    public OreConfig runite = new OreConfig(true, 4, 1, 38, 52, .25f, false, true);
    @ConfigEntry.Gui.CollapsibleObject(startExpanded = true)
    public OreConfig silver = new OreConfig(true,8, 4, 25, 42, .25f, false);
    @ConfigEntry.Gui.CollapsibleObject(startExpanded = true)
    public VariantConfig starrite = new VariantConfig(true, 4, 7, 1, 2, 50, 30, 300, 90, .25f, false, true);
    @ConfigEntry.Gui.CollapsibleObject(startExpanded = true)
    public OreConfig stormyx = new OreConfig(true,8, 2, 4, 60, .0f, false);
    @ConfigEntry.Gui.CollapsibleObject(startExpanded = true)
    public OreConfig unobtainium = new OreConfig(true,3, 1, -59, 5, .25f, false, true);
    @ConfigEntry.Gui.CollapsibleObject(startExpanded = true)
    public OreConfig tin = new OreConfig(true,8, 4, 64, 92, .25f, false);
    @ConfigEntry.Gui.CollapsibleObject(startExpanded = true)
    public OreConfig vermiculite = new OreConfig(true,8, 4, 64, 112, .25f, false);
    @ConfigEntry.Gui.Excluded
    public int configVersion = MythicMetals.CONFIG_VERSION;

    public ArrayList<String> blacklist = new ArrayList<>();
}
