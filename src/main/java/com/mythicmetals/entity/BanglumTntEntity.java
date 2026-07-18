package com.mythicmetals.entity;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.syncher.*;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import org.jetbrains.annotations.Nullable;

public class BanglumTntEntity extends Entity implements TraceableEntity {
    private static final EntityDataAccessor<Integer> FUSE = SynchedEntityData.defineId(BanglumTntEntity.class, EntityDataSerializers.INT);
    private static final int DEFAULT_FUSE = 100;

    @Nullable
    protected LivingEntity causingEntity;

    public BanglumTntEntity(EntityType<? extends BanglumTntEntity> entityType, Level world) {
        super(entityType, world);
        this.blocksBuilding = true;
    }

    public BanglumTntEntity(Level world, double x, double y, double z, @Nullable LivingEntity igniter) {
        this(MythicEntities.BANGLUM_TNT_ENTITY_TYPE, world);
        this.setPos(x, y, z);
        double d = world.random.nextDouble() * (float) (Math.PI * 2);
        this.setDeltaMovement(-Math.sin(d) * 0.01, 0.2F, -Math.cos(d) * 0.01);
        this.setFuse(DEFAULT_FUSE);
        this.xo = x;
        this.yo = y;
        this.zo = z;
        this.causingEntity = igniter;
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        builder.define(FUSE, DEFAULT_FUSE);
    }

    @Override
    public void tick() {
        this.handlePortal();
        this.applyGravity();
        this.move(MoverType.SELF, this.getDeltaMovement());
        this.applyEffectsFromBlocks();
        this.setDeltaMovement(this.getDeltaMovement().scale(0.98));
        if (this.onGround()) {
            this.setDeltaMovement(this.getDeltaMovement().multiply(0.7, -0.5, 0.7));
        }

        int i = this.getFuse() - 1;
        this.setFuse(i);
        if (i <= 0) {
            this.discard();
            if (!this.level().isClientSide()) {
                this.explode();
            }
        } else {
            this.updateInWaterStateAndDoFluidPushing();
            // TODO - Better particles
            if (this.level().isClientSide()) {
                this.level().addParticle(ParticleTypes.LARGE_SMOKE, this.getX(), this.getY() + getSmokeParticleHeight(), this.getZ(), 0.0, 0.0, 0.0);
            }
        }
    }

    @Override
    public boolean hurtServer(ServerLevel world, DamageSource source, float amount) {
        return false;
    }

    public double getSmokeParticleHeight() {
        return 0.5;
    }

    @Override
    protected double getDefaultGravity() {
        return 0.04;
    }

    protected void readAdditionalSaveData(ValueInput valueInput) {
        this.setFuse(valueInput.getIntOr("fuse", DEFAULT_FUSE));
    }

    protected void addAdditionalSaveData(ValueOutput nbt) {
        nbt.putInt("fuse", this.getFuse());
    }

    protected void explode() {
        this.level().explode(this, this.getX(), this.getY(), this.getZ(), 6.0F, Level.ExplosionInteraction.TNT);
    }

    public int getFuse() {
        return this.entityData.get(FUSE);
    }

    public void setFuse(int fuse) {
        this.entityData.set(FUSE, fuse);
    }

    @Nullable
    public LivingEntity getCausingEntity() {
        return this.causingEntity;
    }

    @Override
    public @Nullable Entity getOwner() {
        return causingEntity;
    }
}
