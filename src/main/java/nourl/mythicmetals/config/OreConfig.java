package nourl.mythicmetals.config;

import me.shedaniel.autoconfig.annotation.ConfigEntry;

public class OreConfig {

    public boolean enabled;
    @ConfigEntry.BoundedDiscrete(min = 1, max = 64)
    public int veinSize;
    @ConfigEntry.BoundedDiscrete(min = 1, max = 64)
    public int perChunk;
    public int minHeight;
    public int maxHeight;
    public float discardChance;

    public OreConfig(boolean enabled, int veinSize, int perChunk, int minHeight, int maxHeight, float discardChance) {
        this.enabled = enabled;
        this.veinSize = veinSize;
        this.perChunk = perChunk;
        this.minHeight = minHeight;
        this.maxHeight = maxHeight;
        this.discardChance = discardChance;
    }
}
