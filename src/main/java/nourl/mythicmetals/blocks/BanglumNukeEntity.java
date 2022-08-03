package nourl.mythicmetals.blocks;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;
import nourl.mythicmetals.registry.RegisterEntities;
import nourl.mythicmetals.utils.EpicExplosion;
import org.jetbrains.annotations.Nullable;

public class BanglumNukeEntity extends BanglumTntEntity {
    private static final int DEFAULT_FUSE = 200;
    private static final int POWER = 64;

    public BanglumNukeEntity(EntityType<? extends BanglumNukeEntity> entityType, World world) {
        super(entityType, world);
    }

    public BanglumNukeEntity(World world, double x, double y, double z, @Nullable LivingEntity igniter) {
        this(RegisterEntities.BANGLUM_NUKE_ENTITY_TYPE, world);
        this.setPosition(x, y, z);
        double d = world.random.nextDouble() * (float) (Math.PI * 2);
        this.setVelocity(-Math.sin(d) * 0.01, 0.2F, -Math.cos(d) * 0.01);
        this.setFuse(DEFAULT_FUSE);
        this.prevX = x;
        this.prevY = y;
        this.prevZ = z;
        this.causingEntity = igniter;
    }

    @Override
    public double getSmokeParticleHeight() {
        return 2.5;
    }

    @Override
    protected void explode() {
        EpicExplosion.explode((ServerWorld) world, (int) getX(), (int) getY(), (int) getZ(), POWER);
    }
}
