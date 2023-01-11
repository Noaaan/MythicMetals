package nourl.mythicmetals.effects;

import io.wispforest.owo.ops.WorldOps;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.passive.FoxEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.event.GameEvent;

/**
 * Joke status effect meant to be integrated with Spectrums Titration barrel
 * Teleports you around
 */
public final class WormholeSpecial extends StatusEffect {

    public WormholeSpecial(StatusEffectCategory statusEffectCategory, int color) {
        super(statusEffectCategory, color);
    }

    @Override
    public void applyUpdateEffect(LivingEntity user, int amplifier) {
        // [VanillaCopy] Chorus Fruit Teleportation, but with the possibility of going up and down
        var world = user.world;
        if (!user.world.isClient) {
            for (int i = 0; i < 20; i++) {
                double x = user.getX() + (user.getRandom().nextDouble() - 0.5) * 24.0;
                double y = MathHelper.clamp(
                        user.getY() + (double) (user.getRandom().nextInt(24) - 8),
                        world.getBottomY(),
                        world.getBottomY() + ((ServerWorld) world).getLogicalHeight() - 1
                );
                double z = user.getZ() + (user.getRandom().nextDouble() - 0.5) * 24.0;
                if (user.hasVehicle()) {
                    user.stopRiding();
                }

                Vec3d vec3d = user.getPos();
                if (user.teleport(x, y, z, true)) {
                    world.emitGameEvent(GameEvent.TELEPORT, vec3d, GameEvent.Emitter.of(user));
                    SoundEvent soundEvent = user instanceof FoxEntity ? SoundEvents.ENTITY_FOX_TELEPORT : SoundEvents.ITEM_CHORUS_FRUIT_TELEPORT;
                    WorldOps.playSound(world, user.getPos(), soundEvent, SoundCategory.PLAYERS, 1.0F, 1.0F);
                    break;
                }
            }
        }
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        int i = 60 >> amplifier;
        if (i > 0) {
            return duration % i == 0;
        }
        return true;
    }
}
