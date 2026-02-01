package com.mythicmetals.effects;

import com.mythicmetals.data.attachments.MythicDataAttachments;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;

public class CombustingStatusEffect extends MobEffect {
    public CombustingStatusEffect(MobEffectCategory statusEffectCategory, int color) {
        super(statusEffectCategory, color);
    }

    @Override
    public void onMobRemoved(ServerLevel world, LivingEntity entity, int amplifier, Entity.RemovalReason reason) {
        super.onMobRemoved(world, entity, amplifier, reason);
        if (reason == Entity.RemovalReason.KILLED) {
            entity.setAttached(MythicDataAttachments.COMBUSTION_COOLDOWN_ATTACHMENT, 400);
        }
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
        return duration % 20 == 0;
    }
}
