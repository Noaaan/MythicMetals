package com.mythicmetals.config;

import com.mythicmetals.MythicAttributeModifier;

import java.util.List;

public record MythicArmorConfig(
    String name,
    int helmetArmor,
    int chestArmor,
    int legsArmor,
    int bootsArmor,
    float knockbackResistance,
    List<MythicAttributeModifier> extraModifiers
) {

}
