package nourl.mythicmetals.mixin;

import net.minecraft.entity.AreaEffectCloudEntity;
import net.minecraft.entity.mob.CreeperEntity;
import nourl.mythicmetals.misc.WasSpawnedFromCreeper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(CreeperEntity.class)
public class CreeperEntityMixin {

    @ModifyVariable(method = "spawnEffectsCloud", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/World;spawnEntity(Lnet/minecraft/entity/Entity;)Z"))
    private AreaEffectCloudEntity mythicmetals$assignDataToCloud(AreaEffectCloudEntity cloud) {
        ((WasSpawnedFromCreeper)cloud).mythicmetals$setSpawnedFromCreeper(true);
        return cloud;
    }
}
