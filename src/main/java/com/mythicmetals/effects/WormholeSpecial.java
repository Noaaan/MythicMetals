package com.mythicmetals.effects;

import io.wispforest.owo.ops.WorldOps;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.*;
import net.minecraft.util.Mth;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.Fox;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.Vec3;

/**
 * Joke status effect meant to be integrated with Spectrums Titration barrel
 * Teleports you around
 */
public final class WormholeSpecial extends MobEffect {

    public WormholeSpecial(MobEffectCategory statusEffectCategory, int color) {
        super(statusEffectCategory, color);
    }

    @Override
    public void onEffectStarted(LivingEntity user, int amplifier) {
        var world = user.level();
        if (!user.level().isClientSide) {
            for (int i = 0; i < 20; i++) {
                double x = user.getX() + (user.getRandom().nextDouble() - 0.5) * 24.0;
                double y = Mth.clamp(
                    user.getY() + (double) (user.getRandom().nextInt(24) - 8),
                    world.getMinY(),
                    world.getMinY() + ((ServerLevel) world).getLogicalHeight() - 1
                );
                double z = user.getZ() + (user.getRandom().nextDouble() - 0.5) * 24.0;
                if (user.isPassenger()) {
                    user.stopRiding();
                }

                Vec3 vec3d = user.position();
                if (user.randomTeleport(x, y, z, true)) {
                    world.gameEvent(GameEvent.TELEPORT, vec3d, GameEvent.Context.of(user));
                    SoundEvent soundEvent = user instanceof Fox ? SoundEvents.FOX_TELEPORT : SoundEvents.CHORUS_FRUIT_TELEPORT;
                    WorldOps.playSound(world, user.position(), soundEvent, SoundSource.PLAYERS, 1.0F, 1.0F);
                    break;
                }
            }
        }
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
        int i = 60 >> amplifier;
        if (i > 0) {
            return duration % i == 0;
        }
        return true;
    }
}
