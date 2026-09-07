package com.mythicmetals.effects;

import com.mythicmetals.entity.MythicEntityAttributes;
import com.mythicmetals.misc.RegistryHelper;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

public class MythicStatusEffects {

    public static final MobEffect WORMHOLE_SPECIAL = new WormholeSpecial(MobEffectCategory.HARMFUL, 133337);
    public static final MobEffect HEAT = new MobEffect(MobEffectCategory.HARMFUL, 16747008);
    public static final MobEffect COMBUSTION = new CombustingStatusEffect(MobEffectCategory.HARMFUL, 16747008)
        .addAttributeModifier(MythicEntityAttributes.FIRE_VULNERABILITY, RegistryHelper.id("fire_vulnerability"), 1.0, AttributeModifier.Operation.ADD_VALUE)
        .addAttributeModifier(Attributes.BURNING_TIME, RegistryHelper.id("burn_time_reduction"), 0.5f, AttributeModifier.Operation.ADD_MULTIPLIED_BASE);
    public static final MobEffect COMBUSTION_COOLDOWN = new MobEffect(MobEffectCategory.NEUTRAL, 0);

    public static final Holder<MobEffect> HEAT_HOLDER = Registry.registerForHolder(BuiltInRegistries.MOB_EFFECT, RegistryHelper.id("heat"), HEAT);
    public static final Holder<MobEffect> COMBUSTION_HOLDER = Registry.registerForHolder(BuiltInRegistries.MOB_EFFECT, RegistryHelper.id("combustion"), COMBUSTION);
    public static final Holder<MobEffect> COMBUSTION_COOLDOWN_HOLDER = Registry.registerForHolder(BuiltInRegistries.MOB_EFFECT, RegistryHelper.id("combustion_cooldown"), COMBUSTION_COOLDOWN);
    public static final Holder<MobEffect> WORMHOLE_SPECIAL_HOLDER = Registry.registerForHolder(BuiltInRegistries.MOB_EFFECT, RegistryHelper.id("wormhole_special"), WORMHOLE_SPECIAL);

    public static void init() {
    }
}
