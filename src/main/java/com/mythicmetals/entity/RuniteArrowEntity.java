package com.mythicmetals.entity;

import com.mythicmetals.item.tools.MythicTools;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.particles.ColorParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.syncher.*;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.projectile.arrow.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

// [VanillaCopy]
public class RuniteArrowEntity extends AbstractArrow {
    private static final EntityDataAccessor<Integer> COLOR = SynchedEntityData.defineId(RuniteArrowEntity.class, EntityDataSerializers.INT);
    public static final ItemStack RUNITE_ARROW_STACK = new ItemStack(MythicTools.RUNITE_ARROW);

    public RuniteArrowEntity(EntityType<RuniteArrowEntity> type, Level world) {
        super(type, world);
        this.initColor();
    }

    public RuniteArrowEntity(LivingEntity shooter, Level world, @Nullable ItemStack shotFrom) {
        super(MythicEntities.RUNITE_ARROW_ENTITY_TYPE, shooter, world, RUNITE_ARROW_STACK, shotFrom);
        this.initColor();
    }

    public RuniteArrowEntity(Level world, double x, double y, double z, ItemStack stack, @Nullable ItemStack shotFrom) {
        super(MythicEntities.RUNITE_ARROW_ENTITY_TYPE, x, y, z, world, stack, shotFrom);
    }

    public RuniteArrowEntity(Level world, LivingEntity owner, ItemStack stack, @Nullable ItemStack shotFrom) {
        super(MythicEntities.RUNITE_ARROW_ENTITY_TYPE, owner, world, stack, shotFrom);
        this.initColor();
    }

    private PotionContents getPotionContents() {
        return this.getPickupItemStackOrigin().getOrDefault(DataComponents.POTION_CONTENTS, PotionContents.EMPTY);
    }

    protected void initColor() {
        PotionContents potionContentsComponent = this.getPotionContents();
        this.entityData.set(COLOR, potionContentsComponent.equals(PotionContents.EMPTY) ? -1 : potionContentsComponent.getColor());
    }

    @Override
    protected ItemStack getDefaultPickupItem() {
        return RUNITE_ARROW_STACK;
    }

    @Override
    protected void doPostHurtEffects(LivingEntity target) {
        super.doPostHurtEffects(target);
        Entity entity = this.getEffectSource();
        PotionContents potionContentsComponent = this.getPotionContents();
        if (potionContentsComponent.potion().isPresent()) {
            for (var statusEffectInstance : potionContentsComponent.potion().get().value().getEffects()) {
                target.addEffect(
                    new MobEffectInstance(
                        statusEffectInstance.getEffect(),
                        Math.max(statusEffectInstance.mapDuration(i -> i / 8), 1),
                        statusEffectInstance.getAmplifier(),
                        statusEffectInstance.isAmbient(),
                        statusEffectInstance.isVisible()
                    ),
                    entity
                );
            }
        }

        for (MobEffectInstance statusEffectInstance : potionContentsComponent.customEffects()) {
            target.addEffect(statusEffectInstance, entity);
        }
    }

    public int getColor() {
        return this.entityData.get(COLOR);
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(COLOR, -1);
    }

    @Override
    public void tick() {
        super.tick();
        if (this.level().isClientSide()) {
            if (this.verticalCollisionBelow) {
                if (this.inGroundTime % 5 == 0) {
                    this.spawnParticles(1);
                }
            } else {
                this.spawnParticles(2);
            }
        } else if (this.verticalCollisionBelow && this.inGroundTime != 0 && !this.getPotionContents().equals(PotionContents.EMPTY) && this.inGroundTime >= 600) {
            this.level().broadcastEntityEvent(this, (byte) 0);
            this.setPickupItemStack(RUNITE_ARROW_STACK);
        }
    }

    private void spawnParticles(int amount) {
        int i = this.getColor();
        if (i != -1 && amount > 0) {
            for (int j = 0; j < amount; ++j) {
                this.level()
                    .addParticle(
                        ColorParticleOption.create(ParticleTypes.ENTITY_EFFECT, i), this.getRandomX(0.5), this.getRandomY(), this.getRandomZ(0.5), 0.0, 0.0, 0.0
                    );
            }
        }
    }
}
