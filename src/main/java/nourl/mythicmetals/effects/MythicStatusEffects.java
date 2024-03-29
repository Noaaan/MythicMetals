package nourl.mythicmetals.effects;

import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import nourl.mythicmetals.misc.RegistryHelper;
import nourl.mythicmetals.registry.RegisterEntityAttributes;

public class MythicStatusEffects {

    public static final StatusEffect WORMHOLE_SPECIAL = new WormholeSpecial(StatusEffectCategory.HARMFUL, 133337);
    public static final StatusEffect HEAT = new StatusEffect(StatusEffectCategory.HARMFUL, 16747008);
    public static final StatusEffect COMBUSTION = new CombustingStatusEffect(StatusEffectCategory.HARMFUL, 16747008)
            .addAttributeModifier(RegisterEntityAttributes.FIRE_VULNERABILITY, "b69d29e0-3482-45d4-9a02-5c9bc5952a45", 0.0, EntityAttributeModifier.Operation.ADDITION);

    public static void init() {
        Registry.register(Registries.STATUS_EFFECT, RegistryHelper.id("wormhole_special"), WORMHOLE_SPECIAL);
        Registry.register(Registries.STATUS_EFFECT, RegistryHelper.id("heat"), HEAT);
        Registry.register(Registries.STATUS_EFFECT, RegistryHelper.id("combustion"), COMBUSTION);

    }

}
