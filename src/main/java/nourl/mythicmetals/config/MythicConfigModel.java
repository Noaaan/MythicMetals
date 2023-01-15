package nourl.mythicmetals.config;

import io.wispforest.owo.config.annotation.*;
import io.wispforest.owo.ui.core.Positioning;
import nourl.mythicmetals.MythicMetals;

import java.util.ArrayList;

@SuppressWarnings("unused")
@Modmenu(modId = MythicMetals.MOD_ID)
@Config(name = MythicMetals.MOD_ID, wrapperName = "MythicMetalsConfig")
public class MythicConfigModel {
    @RestartRequired
    public boolean enableDusts = false;
    @RestartRequired
    public boolean enableNuggets = true;
    @RestartRequired
    public boolean enableAnvils = true;

    @Nest
    public OreConfig adamantite = new OreConfig(true, 5, 1, -54, -20, .125f, false, true);
    @Nest
    public OreConfig aquarium = new OreConfig(true, 9, 4, 31, 63, .0f, false);
    @Nest
    public OreConfig banglum = new OreConfig(true, 7, 3, 48, 69, .125f, false);
    @Nest
    public OreConfig netherBanglum = new OreConfig(true, 10, 4, 69, 110, .125f, false);
    @Nest
    public OreConfig carmot = new OreConfig(true, 4, 1, -24, 10, .125f, false);
    @Nest
    public OreConfig calciteKyber = new OreConfig(true, 16, 40, -56, 40, .4f, false, false);
    @Nest
    public OreConfig manganese = new OreConfig(true, 9, 2, 28, 48, .25f, false);
    @Nest
    public OreConfig kyber = new OreConfig(true, 3, 1, 12, 52, .5f, false, false);
    @Nest
    public OreConfig morkite = new OreConfig(true, 11, 3, 27, 32, .25f, true);
    @Nest
    public OreConfig midasGold = new OreConfig(true, 7, 4, 12, 125, .125f, false, false);
    @Nest
    public OreConfig mythril = new OreConfig(true, 5, 1, -24, 4, .125f, false, true);
    @Nest
    public OreConfig orichalcum = new OreConfig(true, 5, 1, 8, 8, .0f, true);
    @Nest
    public OreConfig osmium = new OreConfig(true, 6, 5, 50, 120, .25f, false);
    @Nest
    public OreConfig overworldNetherOres = new OreConfig(true, 4, 2, 40, 70, .125f, false);
    @Nest
    public OreConfig palladium = new OreConfig(true, 5, 2, 14, 36, .0f, false);
    @Nest
    public OreConfig platinum = new OreConfig(true, 6, 2, 8, 32, .125f, false, true);
    @Nest
    public OreConfig prometheum = new OreConfig(true, 6, 4, 30, 48, .0f, true);
    @Nest
    public OreConfig quadrillum = new OreConfig(true, 7, 2, 12, 40, .25f, false);
    @Nest
    public OreConfig runite = new OreConfig(true, 3, 1, 39, 53, .5f, false, true);
    @Nest
    public OreConfig deepslateRunite = new OreConfig(true, 4, 2, -55, -37, .5f, false, true);
    @Nest
    public OreConfig silver = new OreConfig(true, 8, 4, 25, 42, .25f, false);
    @Nest
    public OreConfig starrite = new OreConfig(true, 4, 1, 70, 260, .125f, false, false);
    @Nest
    public OreConfig endStarrite = new OreConfig(true, 8, 2, 30, 90, .0f, false);
    @Nest
    public OreConfig stormyx = new OreConfig(true, 9, 2, 4, 60, .0f, false);
    @Nest
    public OreConfig unobtainium = new OreConfig(true, 3, 1, -54, 5, .0f, false, true);
    @Nest
    public OreConfig tin = new OreConfig(true, 8, 4, 64, 92, .25f, false);
    @ExcludeFromScreen
    public int configVersion = MythicMetals.CONFIG_VERSION;
    @RestartRequired
    public boolean disableFunny = false;
    public int banglumNukeCoreRadius = 32;

    public ArrayList<String> blacklist = new ArrayList<>();

    @Hook
    public ShieldPosition shieldPosition = ShieldPosition.TOP_LEFT;

    public enum ShieldPosition {
        TOP_LEFT(2, 2),
        TOP_RIGHT(97, 2),
        BOTTOM_LEFT(2, 97),
        BOTTOM_RIGHT(97, 97);

        private final int x;
        private final int y;

        ShieldPosition(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public Positioning asRelativePos() {
            return Positioning.relative(x, y);
        }
    }
}
