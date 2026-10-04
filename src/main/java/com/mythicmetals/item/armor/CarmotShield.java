package com.mythicmetals.item.armor;

import com.mythicmetals.entity.MythicEntityAttributes;
import io.wispforest.endec.Endec;
import io.wispforest.endec.StructEndec;
import io.wispforest.endec.impl.StructEndecBuilder;
import net.minecraft.world.entity.LivingEntity;

import static com.mythicmetals.data.attachments.MythicDataAttachments.CARMOT_SHIELD_ATTACHMENT;
import static com.mythicmetals.data.attachments.MythicDataAttachments.CARMOT_SHIELD_COOLDOWN_ATTACHMENT;

public record CarmotShield(
    double shieldHealth,
    boolean isHurt
) {
    public static final int SHIELD_BREAK_COOLDOWN = 320;
    public static final int DAMAGE_COOLDOWN = 160;
    public static final CarmotShield NONE = new CarmotShield(0, false);
    public static final StructEndec<CarmotShield> ENDEC = StructEndecBuilder.of(
        Endec.DOUBLE.fieldOf("shield_health", CarmotShield::shieldHealth),
        Endec.BOOLEAN.fieldOf("is_hurt", CarmotShield::isHurt),
        CarmotShield::new
    );

    public static double getMaxHealth(LivingEntity entity) {
        return entity.getAttributeValue(MythicEntityAttributes.CARMOT_SHIELD);
    }

    public float computeNewDamage(float damage) {
        return Math.max((float) (damage - this.shieldHealth), 0);
    }

    public CarmotShield createHurtShield(float damage) {
        return new CarmotShield(Math.max(this.shieldHealth - damage, 0), true);
    }

    public CarmotShield tick(double maxShield) {
        return new CarmotShield(Math.min(shieldHealth + 0.1, maxShield), shieldHealth != maxShield && isHurt);
    }

    public static int tickCooldown(int cooldown) {
        return Math.max(0, cooldown - 1);
    }

    public int getCooldown() {
        return shieldHealth == 0.0 ? SHIELD_BREAK_COOLDOWN : DAMAGE_COOLDOWN;
    }

    public static float handleCarmotShield(LivingEntity entity, float incomingDamage) {
        var carmotShield = entity.getAttachedOrCreate(CARMOT_SHIELD_ATTACHMENT);
        float newDamage = carmotShield.computeNewDamage(incomingDamage);
        var newShield = carmotShield.createHurtShield(incomingDamage);
        entity.setAttached(CARMOT_SHIELD_ATTACHMENT, newShield);
        entity.setAttached(CARMOT_SHIELD_COOLDOWN_ATTACHMENT, newShield.getCooldown());
        return newDamage;
    }

    public boolean isBroken() {
        return this.shieldHealth == 0;
    }
}
