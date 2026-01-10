package com.mythicmetals.armor;

import com.mythicmetals.MythicMetals;
import com.mythicmetals.entity.MythicEntityAttributes;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Player;
import org.ladysnake.cca.api.v3.component.Component;
import org.ladysnake.cca.api.v3.component.sync.AutoSyncedComponent;

public class CarmotShield implements Component, AutoSyncedComponent {
    private final Player player;
    public float shieldHealth;
    public int renderTime;
    public int cooldown;

    public static final int MAX_COOLDOWN = 160;

    public CarmotShield(Player player) {
        this.player = player;
        shieldHealth = 0;
        renderTime = 0;
        cooldown = 0;
    }

    public boolean shouldRenderShield() {
        return renderTime > 0;
    }

    public void damageShield(float damage) {
        shieldHealth = Mth.clamp(shieldHealth - damage, 0f, getMaxHealth());

        // Put the shield on cooldown when you take damage
        if (shieldHealth > 0) {
            renderTime = 20;
            cooldown = 50;
        }

        // Handle if the shield should break
        if (shieldHealth == 0) {
            // Set the shield to render the break animation once
            if (cooldown == 0) {
                renderTime = 30;
            }
            cooldown = MAX_COOLDOWN;
        }
    }

    public void tickShield() {
        if (player.level() == null) return;

        // Prevent overshields
        if (shieldHealth > getMaxHealth()) {
            shieldHealth = getMaxHealth();
        }

        // Regenerate shield if not on cooldown
        if (shieldHealth < getMaxHealth()) {
            if (cooldown == 0) {
                shieldHealth = Mth.clamp(shieldHealth += 0.1f, 0f, this.getMaxHealth());
                renderTime = 40;
            } else {
                cooldown--;
            }
        }

        if (shouldRenderShield()) {
            renderTime--;
            MythicMetals.CARMOT_SHIELD.sync(player);
        }

        // No shield, stop rendering
        if (getMaxHealth() == 0) {
            renderTime = 0;
            shieldHealth = 0;
        }
    }

    // FIXME - This is likely causing some weird rendering
    public float getMaxHealth() {
        int result = 0;
        if (this.player.getAttributes().hasAttribute(MythicEntityAttributes.CARMOT_SHIELD)) {
            return (float) this.player.getAttributes().getValue(MythicEntityAttributes.CARMOT_SHIELD);
        }
        return result;
    }

    @Override
    public void readFromNbt(CompoundTag tag, HolderLookup.Provider registryLookup) {
        shieldHealth = tag.getFloat("health");
        renderTime = tag.getInt("rendertime");
        cooldown = tag.getInt("cooldown");
    }

    @Override
    public void writeToNbt(CompoundTag tag, HolderLookup.Provider registryLookup) {
        tag.putFloat("health", shieldHealth);
        tag.putInt("rendertime", renderTime);
        tag.putInt("cooldown", cooldown);

    }
}
