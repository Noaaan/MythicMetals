package com.mythicmetals.entity;

import com.mythicmetals.item.tools.MythicTools;
import com.mythicmetals.data.damage.MythicDamageTypes;
import net.minecraft.core.registries.Registries;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.arrow.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import org.jetbrains.annotations.Nullable;

public class StarPlatinumArrowEntity extends AbstractArrow {

    public StarPlatinumArrowEntity(LivingEntity owner, Level world, ItemStack stack, @Nullable ItemStack weapon) {
        super(MythicEntities.STAR_PLATINUM_ARROW_ENTITY_TYPE, owner, world, stack, weapon);
    }

    public StarPlatinumArrowEntity(Level world, double x, double y, double z, ItemStack stack, @Nullable ItemStack shotFrom) {
        super(MythicEntities.STAR_PLATINUM_ARROW_ENTITY_TYPE, x, y, z, world, stack, shotFrom);
    }

    public StarPlatinumArrowEntity(EntityType<StarPlatinumArrowEntity> type, Level world) {
        super(type, world);
    }

    @Override
    protected ItemStack getPickupItem() {
        return new ItemStack(MythicTools.STAR_PLATINUM_ARROW);
    }

    @Override
    protected ItemStack getDefaultPickupItem() {
        return new ItemStack(MythicTools.STAR_PLATINUM_ARROW);
    }

    @Override
    protected void onHitEntity(EntityHitResult entityHitResult) {
        super.onHitEntity(entityHitResult);
    }

    @Override
    protected void doPostHurtEffects(LivingEntity target) {
        super.doPostHurtEffects(target);
        var source = new DamageSource(
            this.level().registryAccess().lookupOrThrow(Registries.DAMAGE_TYPE).get(MythicDamageTypes.STAR_PLATINUM_ARROW.identifier()).orElseThrow(),
            this,
            getOwner());
        if (target.getType().is(EntityTypeTags.UNDEAD)) {
            target.addEffect(new MobEffectInstance(MobEffects.INSTANT_HEALTH, 1, 3));
        } else {
            target.hurtServer(((ServerLevel) level()), source, 24);
        }
    }
}
