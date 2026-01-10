package com.mythicmetals.mixin;

import com.mythicmetals.misc.WasSpawnedFromCreeper;
import net.minecraft.world.entity.AreaEffectCloud;
import net.minecraft.world.entity.monster.Creeper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(Creeper.class)
public class CreeperEntityMixin {

    @ModifyVariable(method = "spawnEffectsCloud", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/World;spawnEntity(Lnet/minecraft/entity/Entity;)Z"))
    private AreaEffectCloud mythicmetals$assignDataToCloud(AreaEffectCloud cloud) {
        ((WasSpawnedFromCreeper) cloud).mythicmetals$setSpawnedFromCreeper(true);
        return cloud;
    }
}
