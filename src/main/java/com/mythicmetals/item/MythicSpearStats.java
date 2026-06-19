package com.mythicmetals.item;

public class MythicSpearStats {

    public static final SpearStats ADAMANTITE = new SpearStats(1f, 2.5f, 4.5f, 1.25f, 0.525f, 4f, 6.5f, 9.00f, 10);
    public static final SpearStats AQUARIUM = new SpearStats(1.08f, 2.5f, 4.5f, 0.75f, 0.7f, 4f, 8.5f, 12.50f, 12);
    public static final SpearStats BANGLUM = new SpearStats(1.3f, 2.5f, 4.5f, 0.69f, 0.7f, 4.5f, 9f, 13.37f, 13);
    public static final SpearStats BRONZE = new SpearStats(0.9f, 2.5f, 4.5f, 0.9f, 0.6f, 4f, 8f, 12.00f, 12);
    public static final SpearStats CARMOT = new SpearStats(1.06f, 2.5f, 4.5f, 0.9f, 0.5f, 4f, 7f, 11.50f, 11);
    public static final SpearStats CELESTIUM = new SpearStats(1.3f, 2.5f, 4.5f, 1.3f, 0.45f, 3f, 6f, 9.75f, 9);
    public static final SpearStats DURASTEEL = new SpearStats(0.1f, 2.5f, 4.5f, 1.1f, 0.55f, 2.75f, 6f, 11.25f, 11);
    public static final SpearStats HALLOWED = new SpearStats(1.05f, 2.5f, 4.5f, 1.2f, 0.475f, 2.5f, 5f, 8.50f, 8);
    public static final SpearStats KYBER = new SpearStats(1.5f, 2.25f, 4.25f, 0.8f, 0.69f, 3.5f, 7f, 11.50f, 11);
    public static final SpearStats LEGENDARY_BANGLUM = new SpearStats(0.1f, 3f, 4.75f, 1.05f, 0.67f, 3f, 6f, 12.50f, 13);
    public static final SpearStats METALLURGIUM = new SpearStats(1f, 2.5f, 4.5f, 1.5f, 0.35f, 2f, 5f, 7.00f, 7);
    public static final SpearStats MYTHRIL = new SpearStats(1.05f, 2.5f, 4.5f, 1.025f, 0.5f, 4.5f, 8f, 11.00f, 10);
    public static final SpearStats ORICHALCUM = new SpearStats(0.1f, 2.5f, 4.5f, 1.15f, 0.45f, 2.5f, 5f, 8.50f, 8);
    public static final SpearStats OSMIUM = new SpearStats(0.1f, 2.5f, 4.5f, 1.1f, 0.3f, 3f, 8f, 10.00f, 9);
    public static final SpearStats PALLADIUM = new SpearStats(0.1f, 2.5f, 4.5f, 1.15f, 0.4f, 3.5f, 7f, 10.00f, 10);
    public static final SpearStats PROMETHEUM = new SpearStats(1.17f, 2.5f, 4.5f, 1.12f, 0.6f, 4f, 6.5f, 11.00f, 11);
    public static final SpearStats QUADRILLUM = new SpearStats(1.05f, 2.5f, 4.5f, 0.73f, 0.65f, 3.5f, 7.5f, 12.00f, 12);
    public static final SpearStats RUNITE = new SpearStats(0.94f, 2.5f, 4.5f, 1.073f, 0.53f, 3.5f, 7f, 10.73f, 11);
    public static final SpearStats STAR_PLATINUM = new SpearStats(1.5f, 2.5f, 4.5f, 1f, 0.5f, 4f, 7.0f, 10.50f, 10);
    public static final SpearStats STEEL = new SpearStats(1.25f, 2.5f, 4.5f, 1f, 0.57f, 4f, 7f, 11.00f, 11);
    public static final SpearStats STORMYX = new SpearStats(0.1f, 2.5f, 4.5f, 1.08f, 0.5f, 3.5f, 7f, 10.00f, 10);
    public static final SpearStats TIDESINGER = new SpearStats(1.16f, 2.5f, 4.5f, 1.15f, 0.45f, 4.5f, 7.5f, 10.00f, 10);

    public record SpearStats(
        float swingDuration,
        float minimumRange,
        float maximumRange,
        float damageMultiplier,
        float activationDelay,
        float dismountTime,
        float knockbackTime,
        float damageTime,
        int dismountRequirement
    ) {

    }
}
