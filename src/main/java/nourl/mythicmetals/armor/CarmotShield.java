package nourl.mythicmetals.armor;

import dev.onyxstudios.cca.api.v3.component.Component;
import dev.onyxstudios.cca.api.v3.component.sync.AutoSyncedComponent;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.MathHelper;
import nourl.mythicmetals.MythicMetals;
import nourl.mythicmetals.data.MythicTags;

public class CarmotShield implements Component, AutoSyncedComponent {
    private final PlayerEntity player;
    public float shieldHealth;
    public int renderTime;
    public int cooldown;

    public static final int SHIELD_HEALTH_PER_PIECE = 5;
    public static final int MAX_COOLDOWN = 160;

    public CarmotShield(PlayerEntity player) {
        this.player = player;
        shieldHealth = 0;
        renderTime = 0;
        cooldown = 0;
    }

    public boolean shouldRenderShield() {
        return renderTime > 0;
    }

    public void damageShield(float damage) {
        shieldHealth = MathHelper.clamp(shieldHealth - damage, 0f, getMaxHealth());

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
        if (player.world == null) return;
        System.out.println(shieldHealth);
        MythicMetals.CARMOT_SHIELD.sync(player);

        // Prevent overshields
        if (shieldHealth > getMaxHealth()) {
            shieldHealth = getMaxHealth();
        }

        // Regenerate shield if not on cooldown
        if (shieldHealth < getMaxHealth()) {
            if (cooldown == 0) {
                shieldHealth = MathHelper.clamp(shieldHealth += 0.1f, 0f, this.getMaxHealth());
                renderTime = 40;
            } else {
                cooldown--;
            }
        }

        if (shouldRenderShield()) {
            renderTime--;
        }

        // If you dont have the shield anymore, stop rendering
        if (getMaxHealth() == 0) {
            renderTime = 0;
            shieldHealth = 0;
        }
    }

    public float getMaxHealth() {
        // For loop using the players armor items
        int result = 0;
        for (ItemStack armorItems : player.getArmorItems()) {
            if (armorItems.getItem().getRegistryEntry().isIn(MythicTags.CARMOT_ARMOR)) {
                result += SHIELD_HEALTH_PER_PIECE;
            }
        }
        return result;
    }

    @Override
    public void readFromNbt(NbtCompound tag) {
        shieldHealth = tag.getFloat("health");
        renderTime = tag.getInt("rendertime");
        cooldown = tag.getInt("cooldown");
    }

    @Override
    public void writeToNbt(NbtCompound tag) {
        tag.putFloat("health", shieldHealth);
        tag.putInt("rendertime", renderTime);
        tag.putInt("cooldown", cooldown);

    }
}
