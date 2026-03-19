package com.mythicmetals.misc.duck;

/**
 * Interface injection for potion effect clouds to determine whether it was spawned from a creeper.
 * Injected onto {@link net.minecraft.world.entity.AreaEffectCloud}
 * @see com.mythicmetals.mixin.AreaEffectCloudMixin
 */
public interface WasSpawnedFromCreeper {

    void mythicmetals$setSpawnedFromCreeper(boolean isCreeper);

    boolean mythicmetals$isSpawnedFromCreeper();
}
