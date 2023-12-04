package nourl.mythicmetals.misc;

import io.wispforest.owo.particles.ClientParticles;
import io.wispforest.owo.particles.systems.ParticleSystem;
import io.wispforest.owo.particles.systems.ParticleSystemController;
import io.wispforest.owo.util.VectorRandomUtils;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import nourl.mythicmetals.compat.ExplosiveEnhancementCompat;
import java.util.Random;

public class MythicParticleSystem {
    public static final ParticleSystemController CONTROLLER = new ParticleSystemController(RegistryHelper.id("particles"));

    public static final ParticleSystem<Vec3d> EXPLOSION_TRAIL = CONTROLLER.register(Vec3d.class, (world, pos, pos2) -> {
        ClientParticles.reset();
        ClientParticles.setParticleCount(4);

        ClientParticles.spawnLine(ParticleTypes.EXPLOSION, world, pos, pos2, 2.0F);
    });

    public static final ParticleSystem<Void> CARMOT_TRAIL = CONTROLLER.register(Void.class, (world, pos, data) -> {
        ClientParticles.setParticleCount(1);
        ClientParticles.setVelocity(new Vec3d(0, 0.1F, 0));
        ClientParticles.spawn(ParticleTypes.END_ROD, world, new Vec3d(pos.x, pos.y, pos.z), 1.0D);
    });

    public static final ParticleSystem<Void> COPPER_SPARK = CONTROLLER.register(Void.class, (world, pos, data) -> {
        ClientParticles.setParticleCount(1);
        ClientParticles.randomizeVelocity(1.25D);
        ClientParticles.spawn(ParticleTypes.ELECTRIC_SPARK, world, pos, 1.0D);

    });

    public static final ParticleSystem<Void> SMOKING_PALLADIUM_PARTICLE = CONTROLLER.register(Void.class, (world, pos, data) -> {
        ClientParticles.setParticleCount(1);
        Random r = new Random();
        var velocity = VectorRandomUtils.getRandomOffset(world,
                Vec3d.ZERO.add(r.nextDouble(-1, 1), 0.75D, r.nextDouble(-1, 1)), 1.25D);
        ClientParticles.setVelocity(velocity);

        ClientParticles.spawn(ParticleTypes.SMOKE, world, pos, 0.0D);
    });

    public static final ParticleSystem<Void> OVERENGINEERED_PALLADIUM_PARTICLE = CONTROLLER.register(Void.class, (world, pos, data) -> {
        ClientParticles.setParticleCount(1);
        Random r = new Random();
        var velocity = VectorRandomUtils.getRandomOffset(world,
                Vec3d.ZERO.add(r.nextDouble(-1, 1), 0.75D, r.nextDouble(-1, 1)), 1.25D);
        ClientParticles.setVelocity(velocity);

        ClientParticles.spawn(ParticleTypes.LAVA, world, pos, 0.0D);
    });

    public static final ParticleSystem<Void> HEALING_HEARTS = CONTROLLER.register(Void.class, (world, pos, data) -> {
        ClientParticles.reset();
        ClientParticles.setParticleCount(5);

        ClientParticles.spawnWithOffsetFromBlock(ParticleTypes.HEART, world, BlockPos.ofFloored(pos), new Vec3d(0, 1.25, 0), 2.0F);

    });

    public static final ParticleSystem<Void> HEALING_DAMAGE = CONTROLLER.register(Void.class, (world, pos, data) -> {
        ClientParticles.reset();
        ClientParticles.setParticleCount(5);

        ClientParticles.spawnWithOffsetFromBlock(ParticleTypes.ENCHANTED_HIT, world, BlockPos.ofFloored(pos), new Vec3d(0, 1.25, 0), 2.0F);

    });

    public static final ParticleSystem<Void> COMBUSTION_EXPLOSION = CONTROLLER.register(Void.class, (world, pos, data) -> {
        ClientParticles.reset();
        ClientParticles.setParticleCount(15);
        ClientParticles.randomizeVelocity(0.5f);
        ClientParticles.persist();
        ClientParticles.spawn(ParticleTypes.ASH, world, pos.add(0, 1, 0), 3.0f);
        ClientParticles.spawn(ParticleTypes.LAVA, world, pos.add(0, 1, 0), 3.0f);
        ClientParticles.spawn(ParticleTypes.SMOKE, world, pos.add(0, 1, 0), 3.0f);
        ClientParticles.reset();
    });

    public static final ParticleSystem<Void> COLORED_NOTE = CONTROLLER.register(Void.class, (world, pos, data) -> {
        Random r = new Random();
       ClientParticles.reset();
       ClientParticles.setParticleCount(1);
       ClientParticles.persist();
       ClientParticles.setVelocity(new Vec3d(r.nextInt(32) / 16.0f, 0, 0));
       ClientParticles.spawn(ParticleTypes.NOTE, world, pos, 0);
       ClientParticles.reset();
    });

    public static final ParticleSystem<Void> NOTE_EXPLOSION = CONTROLLER.register(Void.class, (world, pos, data) -> {
        final int COUNT = 8;
        Random r = new Random();
        ClientParticles.reset();
        ClientParticles.setParticleCount(1);
        ClientParticles.persist();
        for (int i = 0; i < COUNT; i++) {
            ClientParticles.setVelocity(new Vec3d(r.nextInt(32) / 16.0f, 0, 0));
            double xOffset = r.nextDouble(-1.0, 1.0);
            double yOffset = r.nextDouble(-0.5, 2.0);
            double zOffset = r.nextDouble(-1.0, 1.0);
            ClientParticles.spawn(ParticleTypes.NOTE, world, pos.add(xOffset, yOffset, zOffset), 0.25f);
        }

        ClientParticles.reset();
    });

    public static final ParticleSystem<Float> EXPLOSIVE_EXPLOSION = CONTROLLER.register(Float.class, (world, pos, power) -> {
        if(FabricLoader.getInstance().isModLoaded("explosiveenhancement")) {
            ExplosiveEnhancementCompat.spawnParticles(world, pos.x, pos.y, pos.z, power);
        }
    });

    public static void init() {
    }
}
