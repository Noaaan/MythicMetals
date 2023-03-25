package nourl.mythicmetals.misc;

import net.minecraft.entity.damage.DamageType;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;

/**
 * All of these damage types are present in the built-in datapack
 */
public class MythicDamageTypes {
    public static final RegistryKey<DamageType> ASCENSION = RegistryKey.of(RegistryKeys.DAMAGE_TYPE, RegistryHelper.id("ascension"));
    public static final RegistryKey<DamageType> BANGLUM_NUKE = RegistryKey.of(RegistryKeys.DAMAGE_TYPE, RegistryHelper.id("banglum_nuke"));
    public static final RegistryKey<DamageType> STAR_PLATINUM_ARROW = RegistryKey.of(RegistryKeys.DAMAGE_TYPE, RegistryHelper.id("star_platinum_arrow"));
}
