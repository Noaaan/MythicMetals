package com.mythicmetals.item;

import com.mythicmetals.misc.RegistryHelper;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.potion.Potion;
import net.minecraft.registry.entry.RegistryEntry;

public class MythicPotions {

    public static void init() {}

    static {
        RegistryEntry<Potion> STRONG_LUCK = RegistryHelper.potion("strong_luck", new StatusEffectInstance(StatusEffects.LUCK, 6000, 1));
    }
}
