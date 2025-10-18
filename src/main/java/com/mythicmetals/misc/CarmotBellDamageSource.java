package com.mythicmetals.misc;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageType;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.text.Text;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class CarmotBellDamageSource extends DamageSource {
    public CarmotBellDamageSource(RegistryEntry<DamageType> type, @Nullable Entity source, @Nullable Entity attacker) {
        super(type, source, attacker);
    }

    @Override
    public Text getDeathMessage(LivingEntity killed) {
        if (this.getAttacker() != null) {
            return Text.translatable("death.attack.carmot_bell.player", killed.getDisplayName(), this.getAttacker().getDisplayName());
        }
        return Text.translatable("death.attack.carmot_bell", killed.getDisplayName());
    }

    public static CarmotBellDamageSource of(World world, @Nullable LivingEntity attacker) {
        return new CarmotBellDamageSource(world.getRegistryManager().getOrThrow(RegistryKeys.DAMAGE_TYPE).getEntry(MythicDamageTypes.CARMOT_BELL.getValue()).orElseThrow(), null, attacker);
    }
}
