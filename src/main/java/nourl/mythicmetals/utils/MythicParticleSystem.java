package nourl.mythicmetals.utils;

import io.wispforest.owo.particles.ClientParticles;
import io.wispforest.owo.particles.systems.ParticleSystem;
import io.wispforest.owo.particles.systems.ParticleSystemController;
import net.minecraft.particle.ParticleTypes;

public class MythicParticleSystem {
    public static final ParticleSystemController CONTROLLER = new ParticleSystemController(RegistryHelper.id("particles"));

    public static final ParticleSystem<Void> OVERENGINEERED_SINGLE_EXPLOSION_PARTICLE = CONTROLLER.register(Void.class, ((world, pos, data) -> {

        ClientParticles.setParticleCount(2);
        ClientParticles.randomizeVelocity(0.5D);
        ClientParticles.spawn(ParticleTypes.EXPLOSION, world, pos, 0.5D);

    }));

    public static void init() {}
}
