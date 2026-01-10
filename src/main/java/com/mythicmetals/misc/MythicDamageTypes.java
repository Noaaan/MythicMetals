package com.mythicmetals.misc;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.damagesource.DamageType;

/**
 * All of these damage types are present in the built-in datapack
 */
public class MythicDamageTypes {
    public static final ResourceKey<DamageType> ASCENSION = ResourceKey.create(Registries.DAMAGE_TYPE, RegistryHelper.id("ascension"));
    public static final ResourceKey<DamageType> BANGLUM_NUKE = ResourceKey.create(Registries.DAMAGE_TYPE, RegistryHelper.id("banglum_nuke"));
    public static final ResourceKey<DamageType> CARMOT_BELL = ResourceKey.create(Registries.DAMAGE_TYPE, RegistryHelper.id("carmot_bell"));
    public static final ResourceKey<DamageType> STAR_PLATINUM_ARROW = ResourceKey.create(Registries.DAMAGE_TYPE, RegistryHelper.id("star_platinum_arrow"));
}
