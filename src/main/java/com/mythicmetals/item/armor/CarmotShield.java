package com.mythicmetals.item.armor;

import com.mythicmetals.entity.MythicEntityAttributes;
import io.wispforest.endec.Endec;
import io.wispforest.endec.StructEndec;
import io.wispforest.endec.impl.StructEndecBuilder;
import net.minecraft.world.entity.LivingEntity;

public record CarmotShield(
    double shieldHealth,
    boolean isHurt
) {
    public static final CarmotShield NONE = new CarmotShield(0, false);
    public static final StructEndec<CarmotShield> ENDEC = StructEndecBuilder.of(
        Endec.DOUBLE.fieldOf("shield_health", CarmotShield::shieldHealth),
        Endec.BOOLEAN.fieldOf("is_hurt", CarmotShield::isHurt),
        CarmotShield::new
    );

    public static double getMaxHealth(LivingEntity entity) {
        return entity.getAttributeValue(MythicEntityAttributes.CARMOT_SHIELD);
    }

    public CarmotShield tick(double maxShield) {
        return new CarmotShield(Math.min(shieldHealth + 0.1, maxShield), isHurt);
    }
}
