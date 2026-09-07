package com.mythicmetals.effects;

import net.minecraft.world.effect.*;

public class CombustingStatusEffect extends MobEffect {
    public CombustingStatusEffect(MobEffectCategory statusEffectCategory, int color) {
        super(statusEffectCategory, color);
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
        return duration % 20 == 0;
    }
}
