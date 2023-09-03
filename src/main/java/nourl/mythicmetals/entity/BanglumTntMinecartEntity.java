package nourl.mythicmetals.entity;

import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityStatuses;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.vehicle.TntMinecartEntity;
import net.minecraft.item.Item;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.tag.DamageTypeTags;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.world.World;
import nourl.mythicmetals.MythicMetals;
import nourl.mythicmetals.blocks.MythicBlocks;
import nourl.mythicmetals.item.tools.MythicTools;
import org.jetbrains.annotations.Nullable;

public class BanglumTntMinecartEntity extends TntMinecartEntity {
    private static final double MAX_POWER = 8.0;
    private int fuseTicks = -1;

    public BanglumTntMinecartEntity(EntityType<? extends TntMinecartEntity> entityType, World world) {
        super(entityType, world);
    }

    public BanglumTntMinecartEntity(World world, double x, double y, double z) {
        this(MythicEntities.BANGLUM_TNT_MINECART_ENTITY_TYPE, world);
        this.setPosition(x, y, z);
        this.prevX = x;
        this.prevY = y;
        this.prevZ = z;
    }

    @Override
    public BlockState getDefaultContainedBlock() {
        return MythicBlocks.BANGLUM_TNT_BLOCK.getDefaultState();
    }

    @Override
    protected Item getItem() {
        return MythicTools.BANGLUM_TNT_MINECART;
    }

    // [VanillaCopy], but increases the power cap to 8
    @Override
    protected void explode(@Nullable DamageSource damageSource, double power) {
        if (!this.getWorld().isClient) {
            double d = Math.sqrt(power);
            if (d > MAX_POWER) {
                d = MAX_POWER;
            }

            this.getWorld().createExplosion(this, damageSource, null, this.getX(), this.getY(), this.getZ(), (float) (4.0 + this.random.nextDouble() * 1.5 * d), false, World.ExplosionSourceType.TNT);
            this.discard();
        }
    }

    @Override
    public void tick() {
        super.tick();
        if (this.fuseTicks > 0) {
            --this.fuseTicks;
            this.getWorld().addParticle(ParticleTypes.SMOKE, this.getX(), this.getY() + 0.5, this.getZ(), 0.0, 0.0, 0.0);
        } else if (this.fuseTicks == 0) {
            this.explode(this.getVelocity().horizontalLengthSquared());
        }

        if (this.horizontalCollision) {
            double d = this.getVelocity().horizontalLengthSquared();
            if (d >= 0.01F) {
                this.explode(d);
            }
        }
    }

    @Override
    public void onActivatorRail(int x, int y, int z, boolean powered) {
        if (powered && this.fuseTicks < 0) {
            this.prime();
        }
    }

    @Override
    public void dropItems(DamageSource damageSource) {
        double d = this.getVelocity().horizontalLengthSquared();
        if (!damageSource.isIn(DamageTypeTags.IS_FIRE) && !damageSource.isIn(DamageTypeTags.IS_EXPLOSION) && !(d >= 0.01F)) {
            super.dropItems(damageSource);
        } else {
            if (this.fuseTicks < 0) {
                this.prime();
                this.fuseTicks = this.random.nextInt(20) + this.random.nextInt(20);
            }
        }
    }

    @Override
    public int getFuseTicks() {
        return this.fuseTicks;
    }

    @Override
    public boolean isPrimed() {
        return this.fuseTicks > -1;
    }

    @Override
    public void prime() {
        this.fuseTicks = 120;
        if (!this.getWorld().isClient) {
            this.getWorld().sendEntityStatus(this, EntityStatuses.SET_SHEEP_EAT_GRASS_TIMER_OR_PRIME_TNT_MINECART);
            if (!this.isSilent()) {
                this.getWorld().playSound(null, this.getX(), this.getY(), this.getZ(), SoundEvents.ENTITY_TNT_PRIMED, SoundCategory.BLOCKS, 1.0F, 0.8F);
            }
        }
    }

    @Override
    public Type getMinecartType() {
        return MythicMetals.BANGLUM_TNT;
    }
}
