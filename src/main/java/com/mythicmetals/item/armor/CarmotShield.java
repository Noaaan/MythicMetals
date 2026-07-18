package com.mythicmetals.item.armor;

import com.mythicmetals.entity.MythicEntityAttributes;
import io.wispforest.endec.StructEndec;
import io.wispforest.endec.impl.StructEndecBuilder;
import net.minecraft.world.entity.player.Player;

public record CarmotShield(
    double shieldHealth,
    boolean isHurt
) {
    public static final int MAX_COOLDOWN = 160;
    public static final CarmotShield NONE = new CarmotShield(0, false);
    public static final StructEndec<CarmotShield> ENDEC = StructEndecBuilder.of(
        StructEndec.DOUBLE.fieldOf("shield_health", CarmotShield::shieldHealth),
        StructEndec.BOOLEAN.fieldOf("is_hurt", CarmotShield::isHurt),
        CarmotShield::new
    );

    public static double getMaxHealth(Player player) {
        return player.getAttributeValue(MythicEntityAttributes.CARMOT_SHIELD);
    }
}
