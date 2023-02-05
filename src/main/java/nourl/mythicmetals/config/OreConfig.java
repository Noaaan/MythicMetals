package nourl.mythicmetals.config;


import io.wispforest.owo.config.annotation.RangeConstraint;
import io.wispforest.owo.config.annotation.RestartRequired;

/**
 * A config constructor that lets you quickly create all the settings used
 * for a ore in the {@link nourl.mythicmetals.world.MythicOreFeatures} class.
 */
public class OreConfig {
    @RestartRequired
    public boolean enabled;
    @RangeConstraint(min = 1, max = 64)
    @RestartRequired
    public int veinSize;
    @RangeConstraint(min = 1, max = 64)
    @RestartRequired
    public int perChunk;
    @RestartRequired
    public int bottom;
    @RestartRequired
    public int top;
    @RestartRequired
    public float discardChance;
    @RestartRequired
    public boolean offset;
    @RestartRequired
    public boolean trapezoid;

    /**
     *  The constructor of the OreConfig. Contains all the elements needed to configure a single ore feature.
     * @param enabled           Whether the ore is enabled or not
     * @param veinSize          The vein size of the ore feature
     * @param perChunk          How many times the ore should attempt to generate per chunk
     * @param bottom            The lowest value in the fixed/offset range of where the ore can generate
     * @param top               The highest value in the fixed/offset range of where the ore can generate
     * @param discardChance     The chance the ore is discarded when exposed to air
     */
    public OreConfig(boolean enabled, int veinSize, int perChunk, int bottom, int top, float discardChance, boolean offset) {
        this.enabled = enabled;
        this.veinSize = veinSize;
        this.perChunk = perChunk;
        this.bottom = bottom;
        this.top = top;
        this.discardChance = discardChance;
        this.offset = offset;
    }

    /**
     *  Extra constructor that contains an extra boolean, which is whether the feature contains an offset.
     * @param enabled           Whether the ore is enabled or not
     * @param veinSize          The vein size of the ore feature
     * @param perChunk          How many times the ore should attempt to generate per chunk
     * @param bottom            The lowest value in the fixed/offset range of where the ore can generate
     * @param top               The highest value in the fixed/offset range of where the ore can generate
     * @param discardChance     The chance the ore is discarded when exposed to air
     * @param offset            If true the feature is offset from the bottom, else it is not offset
     */
    public OreConfig(boolean enabled, int veinSize, int perChunk, int bottom, int top, float discardChance, boolean offset, boolean trapezoid) {
        this.enabled = enabled;
        this.veinSize = veinSize;
        this.perChunk = perChunk;
        this.bottom = bottom;
        this.top = top;
        this.discardChance = discardChance;
        this.offset = offset;
        this.trapezoid = trapezoid;
    }

}
