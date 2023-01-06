package nourl.mythicmetals.entity;

import dev.onyxstudios.cca.api.v3.component.Component;
import dev.onyxstudios.cca.api.v3.component.sync.AutoSyncedComponent;
import net.minecraft.entity.LivingEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.MathHelper;

public class CombustionCooldown implements Component, AutoSyncedComponent {
    private int cooldown;

    public CombustionCooldown(LivingEntity entity) {
        cooldown = 0;
    }

    @Override
    public void readFromNbt(NbtCompound tag) {
        cooldown = tag.getInt("cooldown");
    }

    @Override
    public void writeToNbt(NbtCompound tag) {
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
