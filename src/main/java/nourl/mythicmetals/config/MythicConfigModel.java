package nourl.mythicmetals.config;

import io.wispforest.owo.config.annotation.*;
import io.wispforest.owo.ui.core.Positioning;
import nourl.mythicmetals.MythicMetals;

@SuppressWarnings("unused")
@Modmenu(modId = MythicMetals.MOD_ID)
@Config(name = MythicMetals.MOD_ID + "-config", wrapperName = "MythicMetalsConfig")
public class MythicConfigModel {
    @RestartRequired
    public boolean adamantite = true;
    @RestartRequired
    public boolean aquarium = true;
    @RestartRequired
    public boolean banglum = true;
    @RestartRequired
    public boolean netherBanglum = true;
    @RestartRequired
    public boolean carmot = true;
    @RestartRequired
    public boolean calciteKyber = true;
    @RestartRequired
    public boolean manganese = true;
    @RestartRequired
    public boolean kyber = true;
    @RestartRequired
    public boolean morkite = true;
    @RestartRequired
    public boolean midasGold = true;
    @RestartRequired
    public boolean mythril = true;
    @RestartRequired
    public boolean orichalcum = true;
    @RestartRequired
    public boolean osmium = true;
    @RestartRequired
    public boolean palladium = true;
    @RestartRequired
    public boolean platinum = true;
    @RestartRequired
    public boolean prometheum = true;
    @RestartRequired
    public boolean quadrillum = true;
    @RestartRequired
    public boolean runite = true;
    @RestartRequired
    public boolean deepslateRunite = true;
    @RestartRequired
    public boolean silver = true;
    @RestartRequired
    public boolean starrite = true;
    @RestartRequired
    public boolean endStarrite = true;
    @RestartRequired
    public boolean stormyx = true;
    @RestartRequired
    public boolean unobtainium = true;
    @RestartRequired
    public boolean tin = true;
    @RestartRequired
    public boolean enableDusts = false;
    @RestartRequired
    public boolean enableNuggets = true;
    @RestartRequired
    public boolean enableAnvils = true;
    @ExcludeFromScreen
    public int configVersion = MythicMetals.CONFIG_VERSION;
    public float unobtainiumTemplateChance = 0.25f;
    public float mythrilDrillTemplateChance = 0.5f;
    public float carmotStaffTemplateChance = 0.9f;
    @RestartRequired
    public boolean disableFunny = false;
    public int banglumNukeCoreRadius = 32;
    @Hook
    public ShieldPosition shieldPosition = ShieldPosition.TOP_LEFT;

    public enum ShieldPosition {
        TOP_LEFT(2, 2),
        TOP_RIGHT(97, 2),
        BOTTOM_LEFT(2, 97),
        BOTTOM_RIGHT(97, 97),
        DISABLED(0, 0);

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
