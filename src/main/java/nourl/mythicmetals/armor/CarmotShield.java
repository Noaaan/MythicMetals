package nourl.mythicmetals.armor;

import dev.onyxstudios.cca.api.v3.component.Component;
import dev.onyxstudios.cca.api.v3.component.sync.AutoSyncedComponent;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.MathHelper;
import nourl.mythicmetals.MythicMetals;
import nourl.mythicmetals.registry.RegisterTags;

public class CarmotShield implements Component, AutoSyncedComponent {
    private final PlayerEntity player;

    public float health;
    public int renderTime;
    public int cooldown;

    public static final int HEALTH_PER_PIECE = 5;
    public static final int MAX_COOLDOWN = 160;

    public CarmotShield(PlayerEntity player) {
        this.player = player;
        health = 0;
        renderTime = 0;
        cooldown = 0;
    }
    
    public boolean isShieldActive() {
        return renderTime > 0 || cooldown > MAX_COOLDOWN - 15;
    }
    
    public void damageShield(float damage) {
        health = MathHelper.clamp(health - damage, 0f, getMaxHealth());

        if (health == 0) {
            cooldown = MAX_COOLDOWN;
        }
    }
    
    public void tickShield() {
        if (MinecraftClient.getInstance().world == null) return;
        MythicMetals.CARMOT_SHIELD.sync(player);

        if (health > getMaxHealth()) {
            health = getMaxHealth();
        }

        if (health < getMaxHealth()) {
            if (cooldown == 0) {
                health = MathHelper.clamp(health += 0.1f, 0f, this.getMaxHealth());
                renderTime = 40;
            } else {
                cooldown--;
            }
        }

        if (isShieldActive()) {
            renderTime--;
        }

        if (getMaxHealth() == 0) {
            renderTime = 0;
            health = 0;
        }
    }
    
    public float getMaxHealth() {
        // For loop using the players armor items
        int result = 0;
        for (ItemStack armorItems : player.getArmorItems()) {
            if (armorItems.getItem().getRegistryEntry().isIn(RegisterTags.CARMOT_ARMOR)) {
                result += HEALTH_PER_PIECE;
            }
        }
        return result;
    }

    @Override
    public void readFromNbt(NbtCompound tag) {
        health = tag.getFloat("health");
        renderTime = tag.getInt("rendertime");
        cooldown = tag.getInt("cooldown");
    }

    @Override
    public void writeToNbt(NbtCompound tag) {
        tag.putFloat("health", health);
        tag.putInt("rendertime", renderTime);
        tag.putInt("cooldown", cooldown);

    }
}
