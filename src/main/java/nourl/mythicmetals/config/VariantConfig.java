package nourl.mythicmetals.config;

import me.shedaniel.autoconfig.annotation.ConfigEntry;

public class VariantConfig {

    @ConfigEntry.Gui.RequiresRestart
    public boolean enabled;
    @ConfigEntry.BoundedDiscrete(min = 1, max = 64)
    @ConfigEntry.Gui.RequiresRestart
    public int veinSize;
    @ConfigEntry.BoundedDiscrete(min = 1, max = 64)
    @ConfigEntry.Gui.RequiresRestart
    public int perChunk;
    @ConfigEntry.Gui.RequiresRestart
    public int bottom;
    @ConfigEntry.Gui.RequiresRestart
    public int top;
    @ConfigEntry.Gui.RequiresRestart
    public float discardChance;

    @ConfigEntry.BoundedDiscrete(min = 1, max = 64)
    @ConfigEntry.Gui.RequiresRestart
    public int veinSizeVariant;
    @ConfigEntry.BoundedDiscrete(min = 1, max = 64)
    @ConfigEntry.Gui.RequiresRestart
    public int perChunkVariant;
    @ConfigEntry.Gui.RequiresRestart
    public int bottomVariant;
    @ConfigEntry.Gui.RequiresRestart
    public int topVariant;

    public VariantConfig(boolean enabled, int veinSize, int veinSizeVariant, int perChunk, int perChunkVariant, int bottom, int bottomVariant, int top, int topVariant, float discardChance) {
        this.enabled = enabled;
        this.veinSize = veinSize;
        this.perChunk = perChunk;
        this.bottom = bottom;
        this.top = top;
        this.discardChance = discardChance;
        this.veinSizeVariant = veinSizeVariant;
        this.perChunkVariant = perChunkVariant;
        this.bottomVariant = bottomVariant;
        this.topVariant = topVariant;
    }

    public OreConfig getVariant() {
        return new OreConfig(enabled, veinSizeVariant, perChunkVariant, bottomVariant, topVariant, discardChance);
    }
}
