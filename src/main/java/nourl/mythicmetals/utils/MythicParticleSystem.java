package nourl.mythicmetals.utils;

import io.wispforest.owo.particles.ClientParticles;
import io.wispforest.owo.particles.systems.ParticleSystem;
import io.wispforest.owo.particles.systems.ParticleSystemController;
import io.wispforest.owo.util.VectorRandomUtils;
import net.minecraft.particle.DustParticleEffect;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3f;

import java.util.Random;

public class MythicParticleSystem {
    public static final ParticleSystemController CONTROLLER = new ParticleSystemController(RegistryHelper.id("particles"));

    public static final ParticleSystem<Vec3d> EXPLOSION_TRAIL = CONTROLLER.register(Vec3d.class, (world, pos, pos2) -> {
        ClientParticles.setParticleCount(4);
        ClientParticles.persist();
        ClientParticles.spawnLine(ParticleTypes.EXPLOSION, world, pos, pos2, 2.0F);
        ClientParticles.reset();
    });

    public static final ParticleSystem<Void> CARMOT_PARTICLES = CONTROLLER.register(Void.class, (world, pos, data) -> {
        ClientParticles.setParticleCount(2);
        ClientParticles.randomizeVelocity(1.5D);
        ClientParticles.persist();
        Vec3f carmotColour = new Vec3f(Vec3d.unpackRgb(0xE63E73));
        ParticleEffect p = new DustParticleEffect(carmotColour, 1.0F);
        ClientParticles.spawn(p, world, new Vec3d(pos.x, pos.y + 1.0D, pos.z), 1.75D);
        ClientParticles.reset();
    });

    public static final ParticleSystem<Void> CARMOT_TRAIL = CONTROLLER.register(Void.class, (world, pos, data) -> {
        ClientParticles.setParticleCount(1);
        ClientParticles.setVelocity(new Vec3d(0, 0.1F, 0));
        ClientParticles.persist();
        ClientParticles.spawn(ParticleTypes.END_ROD, world, new Vec3d(pos.x, pos.y, pos.z), 1.0D);
        ClientParticles.reset();
    });

    public static final ParticleSystem<Void> COPPER_SPARK = CONTROLLER.register(Void.class, (world, pos, data) -> {
        ClientParticles.setParticleCount(1);
        ClientParticles.randomizeVelocity(1.25D);
        ClientParticles.persist();
        ClientParticles.spawn(ParticleTypes.ELECTRIC_SPARK, world, pos, 1.0D);
        ClientParticles.reset();

    });

    public static final ParticleSystem<Void> OVERENGINEERED_PALLADIUM_PARTICLE = CONTROLLER.register(Void.class, (world, pos, data) -> {
        ClientParticles.setParticleCount(1);
        Random r = new Random();
        var velocity = VectorRandomUtils.getRandomOffset(world,
                Vec3d.ZERO.add(r.nextDouble(-1, 1), 1.75D, r.nextDouble(-1, 1)), 1.25D);
        ClientParticles.setVelocity(velocity);

        ClientParticles.spawn(ParticleTypes.LAVA, world, pos, 0.0D);
    });

    public static final ParticleSystem<Vec3d> ICE_BARRAGE = CONTROLLER.register(Vec3d.class, (world, pos, data) -> {
        Vec3d step = data.subtract(pos).multiply(1 / 20f);

        for (int i = 0; i < 20; i++) {
            ClientParticles.spawn(ParticleTypes.FALLING_WATER, world, pos, 0.2D);

            pos = pos.add(step);
        }
    });

    public static void init() {
    }
}
