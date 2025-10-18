package com.mythicmetals.mixin;

import com.mythicmetals.entity.BanglumTntEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.world.explosion.Explosion;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Explosion.class)
public interface ExplosionMixin {

    @Inject(method = "getCausingEntity(Lnet/minecraft/entity/Entity;)Lnet/minecraft/entity/LivingEntity;", at = @At("HEAD"), cancellable = true)
    private static void superBang(@Nullable Entity entity, CallbackInfoReturnable<LivingEntity> cir) {
        if (entity instanceof BanglumTntEntity banglumTntEntity) {
            cir.setReturnValue(banglumTntEntity.getCausingEntity());
        }
    }
}
