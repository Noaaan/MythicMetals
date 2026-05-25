package com.mythicmetals.armor;

// FIXME - Migrate to data attachment
public record CarmotShield(
    double shieldHealth,
    boolean isHurt
) {
    public static final int MAX_COOLDOWN = 160;
    public static final CarmotShield NONE = new CarmotShield(0, false);
}
