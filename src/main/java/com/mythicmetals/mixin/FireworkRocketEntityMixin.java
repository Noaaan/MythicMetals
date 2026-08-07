package com.mythicmetals.mixin;


import com.mythicmetals.entity.MythicEntityAttributes;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.FireworkRocketEntity;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(FireworkRocketEntity.class)
public abstract class FireworkRocketEntityMixin extends Projectile {

    @Shadow
    private @Nullable LivingEntity attachedToEntity;

    /**
     * @author BasiqueEvangelist
     */
    public FireworkRocketEntityMixin(EntityType<? extends FireworkRocketEntity> entityType, Level world) {
        super(entityType, world);
    }

    @ModifyVariable(method = "tick", at = @At(value = "STORE", ordinal = 1), name = "movement")
    private Vec3 mythicmetals$crabVec3D(Vec3 movement) {
        if (this.attachedToEntity == null) return movement;
        var speedModifier = this.attachedToEntity.getAttributeValue(MythicEntityAttributes.ELYTRA_ROCKET_SPEED);

        if (speedModifier == 0) return movement.scale(0);
        return movement.scale(1 / speedModifier);
    }

    @Inject(method = "tick", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/projectile/FireworkRocketEntity;setPos(DDD)V"))
    private void mythicmetals$increaseRocketSpeed(CallbackInfo ci) {
        if (this.attachedToEntity == null) return;
        var speedModifier = this.attachedToEntity.getAttributeValue(MythicEntityAttributes.ELYTRA_ROCKET_SPEED);
        if (speedModifier > 1 && this.attachedToEntity.isFallFlying()) {
            Vec3 lookAngle = this.attachedToEntity.getLookAngle();
            Vec3 movement = this.attachedToEntity.getDeltaMovement();
            this.attachedToEntity
                .setDeltaMovement(
                    movement.add(
                        lookAngle.x * 0.1 + (lookAngle.x * 1.5 - movement.x) * 0.5,
                        lookAngle.y * 0.1 + (lookAngle.y * 1.5 - movement.y) * 0.5,
                        lookAngle.z * 0.1 + (lookAngle.z * 1.5 - movement.z) * 0.5
                    )
                );
        }
    }
}
