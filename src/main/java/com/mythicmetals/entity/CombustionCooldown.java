package com.mythicmetals.entity;

import net.minecraft.entity.LivingEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.math.MathHelper;
import org.ladysnake.cca.api.v3.component.Component;
import org.ladysnake.cca.api.v3.component.sync.AutoSyncedComponent;

/**
 * Component used to prevent entities from constantly receiving the {@link com.mythicmetals.effects.CombustingStatusEffect}
 */
public class CombustionCooldown implements Component, AutoSyncedComponent {
    private int cooldown;

    public CombustionCooldown(LivingEntity entity) {
        cooldown = 0;
    }

    @Override
    public void readFromNbt(NbtCompound tag, RegistryWrapper.WrapperLookup registryLookup) {
        cooldown = tag.getInt("cooldown");
    }

    @Override
    public void writeToNbt(NbtCompound tag, RegistryWrapper.WrapperLookup registryLookup) {
        tag.putInt("cooldown", cooldown);
    }

    public void setCooldown(int ticks) {
        cooldown = ticks;
    }

    public boolean isCombustible() {
        return !(cooldown > 0);
    }

    public void tickCooldown() {
        if (cooldown > 0) {
            cooldown = MathHelper.clamp(cooldown - 1, 0, Integer.MAX_VALUE);
        }
    }
}
