package com.mythicmetals.data.damage;

import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

public class CarmotBellDamageSource extends DamageSource {
    public CarmotBellDamageSource(Holder<DamageType> type, @Nullable Entity source, @Nullable Entity attacker) {
        super(type, source, attacker);
    }

    @Override
    public Component getLocalizedDeathMessage(LivingEntity killed) {
        if (this.getEntity() != null) {
            return Component.translatable("death.attack.carmot_bell.player", killed.getDisplayName(), this.getEntity().getDisplayName());
        }
        return Component.translatable("death.attack.carmot_bell", killed.getDisplayName());
    }

    public static CarmotBellDamageSource of(Level world, @Nullable LivingEntity attacker) {
        return new CarmotBellDamageSource(world.registryAccess().lookupOrThrow(Registries.DAMAGE_TYPE).get(MythicDamageTypes.CARMOT_BELL.identifier()).orElseThrow(), null, attacker);
    }
}
