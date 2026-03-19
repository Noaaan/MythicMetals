package com.mythicmetals.misc.duck;

/**
 * Interface used as a hook to check whether an attack was a critical hit.
 * This is interface-injected onto {@link net.minecraft.world.entity.player.Player}
 * @see com.mythicmetals.mixin.PlayerEntityMixin
 */
public interface IsAttackCritical {

    void mythicmetals$setCritical(boolean isCritical);

    boolean mythicmetals$isCritical();
}
