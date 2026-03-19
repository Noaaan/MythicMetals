package com.mythicmetals.mixin;

import com.mythicmetals.misc.duck.WasSpawnedFromCreeper;
import net.minecraft.world.entity.AreaEffectCloud;
import net.minecraft.world.entity.monster.Creeper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(Creeper.class)
public class CreeperMixin {

    @ModifyVariable(method = "spawnLingeringCloud", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/AreaEffectCloud;setRadius(F)V"))
    private AreaEffectCloud mythicmetals$assignDataToCloud(AreaEffectCloud cloud) {
        ((WasSpawnedFromCreeper) cloud).mythicmetals$setSpawnedFromCreeper(true);
        return cloud;
    }
}
