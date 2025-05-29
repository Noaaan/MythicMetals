package com.mythicmetals.misc;

/**
 * Interface used as a hook to check whether an attack was a critical hit.
 * This is interface-injected onto {@link net.minecraft.entity.player.PlayerEntity}
 */
public interface IsAttackCritical {

    void mythicmetals$setCritical(boolean isCritical);

    boolean mythicmetals$isCritical();
}
