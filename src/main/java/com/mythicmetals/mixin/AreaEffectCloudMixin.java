package com.mythicmetals.mixin;

import com.mythicmetals.misc.WasSpawnedFromCreeper;
import net.minecraft.world.entity.AreaEffectCloud;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(AreaEffectCloud.class)
public abstract class AreaEffectCloudMixin implements WasSpawnedFromCreeper {

    @Unique
    private boolean mythicmetals$ownerWasCreeper = false;

    @Override
    public void mythicmetals$setSpawnedFromCreeper(boolean isCreeper) {
        mythicmetals$ownerWasCreeper = isCreeper;
    }

    @Override
    public boolean mythicmetals$isSpawnedFromCreeper() {
        return mythicmetals$ownerWasCreeper;
    }
}
