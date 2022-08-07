package nourl.mythicmetals.blocks;

import net.minecraft.block.Block;
import net.minecraft.enchantment.ProtectionEnchantment;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.TntEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.EntityDamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import nourl.mythicmetals.MythicMetals;
import nourl.mythicmetals.mixin.DamageSourceAccessor;
import nourl.mythicmetals.registry.RegisterEntities;
import nourl.mythicmetals.utils.EpicExplosion;
import org.jetbrains.annotations.Nullable;

public class BanglumNukeEntity extends BanglumTntEntity {
    private static final int DEFAULT_FUSE = 200;

    private Block coreBlock = MythicBlocks.BANGLUM_NUKE_CORE;

    public BanglumNukeEntity(EntityType<? extends BanglumNukeEntity> entityType, World world) {
        super(entityType, world);
    }

    public BanglumNukeEntity(World world, double x, double y, double z, @Nullable LivingEntity igniter, Block coreBlock) {
        this(RegisterEntities.BANGLUM_NUKE_ENTITY_TYPE, world);
        this.setPosition(x, y, z);
        double d = world.random.nextDouble() * (float) (Math.PI * 2);
        this.setVelocity(-Math.sin(d) * 0.01, 0.2F, -Math.cos(d) * 0.01);
        this.setFuse(DEFAULT_FUSE);
        this.prevX = x;
        this.prevY = y;
        this.prevZ = z;
        this.causingEntity = igniter;
        this.coreBlock = coreBlock;
    }

    @Override
    public double getSmokeParticleHeight() {
        return 2.5;
    }

    @Override
    protected void explode() {
        int radius = MythicMetals.CONFIG.banglumNukeCoreRadius;

        EpicExplosion.explode((ServerWorld) world, (int) getX(), (int) getY(), (int) getZ(), radius);

        int soundRadius = (int) (radius * 1.25);

        for (PlayerEntity player : world.getPlayers()) {
            if (player.squaredDistanceTo(this) > soundRadius * soundRadius) continue;

            player.playSound(SoundEvents.ENTITY_GENERIC_EXPLODE, SoundCategory.BLOCKS, 5.0F, (1.0F + (this.world.random.nextFloat() - this.world.random.nextFloat()) * 0.2F) * 0.7F);
        }

        DamageSource damageSource;

        if (causingEntity != null) {
            damageSource = new EntityDamageSource("banglum_nuke.player", causingEntity);
        } else {
            damageSource = DamageSourceAccessor.createDamageSource("banglum_nuke");
        }

        damageSource.setExplosive();

        for (var entity : world.getOtherEntities(this, Box.of(getPos(), radius * 2, radius * 2, radius * 2))) {
            if (entity.isImmuneToExplosion()) continue;

            double w = 1 - entity.distanceTo(this) / (double) radius;
            if (w >= 0) {
                double x = entity.getX() - this.getX();
                double y = (entity instanceof TntEntity ? entity.getY() : entity.getEyeY()) - this.getY();
                double z = entity.getZ() - this.getZ();
                double dist = Math.sqrt(x * x + y * y + z * z);
                if (dist != 0.0) {
                    x /= dist;
                    y /= dist;
                    z /= dist;
                    entity.damage(damageSource, MathHelper.floor((w * w + w) * 7.0 * radius + 1.0));
                    double kb = w * 5;
                    if (entity instanceof LivingEntity living) {
                        kb = ProtectionEnchantment.transformExplosionKnockback(living, kb);
                    }

                    entity.addVelocity(x * kb, y * kb, z * kb);
                }
            }
        }
    }
}
