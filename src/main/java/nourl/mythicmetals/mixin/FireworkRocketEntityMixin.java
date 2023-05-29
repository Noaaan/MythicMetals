package nourl.mythicmetals.mixin;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.FireworkRocketEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import nourl.mythicmetals.registry.RegisterEntityAttributes;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(FireworkRocketEntity.class)
public abstract class FireworkRocketEntityMixin extends ProjectileEntity {

    @Shadow private @Nullable LivingEntity shooter;

    /**
     * @author BasiqueEvangelist
     */
    public FireworkRocketEntityMixin(EntityType<? extends FireworkRocketEntity> entityType, World world) {
        super(entityType, world);
    }

    @ModifyVariable(method = "tick", at = @At("STORE"), ordinal = 1)
    private Vec3d mythicmetals$crabVec3D(Vec3d vec) {
        if (this.shooter == null) return vec;
        var speedModifier = this.shooter.getAttributeValue(RegisterEntityAttributes.ELYTRA_ROCKET_SPEED);

        if (speedModifier == 0) return vec.multiply(0);
        return vec.multiply(1 / speedModifier);
    }

    @Redirect(method = "tick", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/math/Vec3d;add(DDD)Lnet/minecraft/util/math/Vec3d;", ordinal = 0))
    private Vec3d mythicmetals$increaseRocketSpeed(Vec3d velocity, double x, double y, double z) {
        if (this.shooter == null) return velocity;
        var speedModifier = this.shooter.getAttributeValue(RegisterEntityAttributes.ELYTRA_ROCKET_SPEED);

        return velocity.multiply(speedModifier).add(x, y, z);
    }
}
