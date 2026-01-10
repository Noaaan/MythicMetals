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

@Mixin(FireworkRocketEntity.class)
public abstract class FireworkRocketEntityMixin extends Projectile {

    @Shadow
    private @Nullable LivingEntity shooter;

    /**
     * @author BasiqueEvangelist
     */
    public FireworkRocketEntityMixin(EntityType<? extends FireworkRocketEntity> entityType, Level world) {
        super(entityType, world);
    }

    @ModifyVariable(method = "tick", at = @At("STORE"), ordinal = 1)
    private Vec3 mythicmetals$crabVec3D(Vec3 vec) {
        if (this.shooter == null) return vec;
        var speedModifier = this.shooter.getAttributeValue(MythicEntityAttributes.ELYTRA_ROCKET_SPEED);

        if (speedModifier == 0) return vec.scale(0);
        return vec.scale(1 / speedModifier);
    }

    @Redirect(method = "tick", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/math/Vec3d;add(DDD)Lnet/minecraft/util/math/Vec3d;", ordinal = 0))
    private Vec3 mythicmetals$increaseRocketSpeed(Vec3 velocity, double x, double y, double z) {
        if (this.shooter == null) return velocity;
        var speedModifier = this.shooter.getAttributeValue(MythicEntityAttributes.ELYTRA_ROCKET_SPEED);

        return velocity.scale(speedModifier).add(x, y, z);
    }
}
