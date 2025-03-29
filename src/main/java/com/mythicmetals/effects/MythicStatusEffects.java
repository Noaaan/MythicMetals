package com.mythicmetals.effects;

import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import com.mythicmetals.misc.RegistryHelper;
import com.mythicmetals.entity.MythicEntityAttributes;

public class MythicStatusEffects {

    public static final StatusEffect WORMHOLE_SPECIAL = new WormholeSpecial(StatusEffectCategory.HARMFUL, 133337);
    public static final StatusEffect HEAT = new StatusEffect(StatusEffectCategory.HARMFUL, 16747008);
    public static final StatusEffect COMBUSTION = new CombustingStatusEffect(StatusEffectCategory.HARMFUL, 16747008)
        .addAttributeModifier(MythicEntityAttributes.FIRE_VULNERABILITY, RegistryHelper.id("fire_vulnerability"), 1.0, EntityAttributeModifier.Operation.ADD_VALUE)
        .addAttributeModifier(EntityAttributes.GENERIC_BURNING_TIME, RegistryHelper.id("burn_time_reduction"), 0.5f, EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE);

    public static void init() {
        Registry.register(Registries.STATUS_EFFECT, RegistryHelper.id("wormhole_special"), WORMHOLE_SPECIAL);
        Registry.register(Registries.STATUS_EFFECT, RegistryHelper.id("heat"), HEAT);
        Registry.register(Registries.STATUS_EFFECT, RegistryHelper.id("combustion"), COMBUSTION);

    }

}
