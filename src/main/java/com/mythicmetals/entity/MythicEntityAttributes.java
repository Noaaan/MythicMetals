package com.mythicmetals.entity;

import com.mythicmetals.misc.RegistryHelper;
import net.minecraft.ChatFormatting;
import net.minecraft.core.Holder;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.RangedAttribute;

public class MythicEntityAttributes {
    public static final Holder<Attribute> CARMOT_SHIELD = RegistryHelper.entityAttribute("carmot_shield", new RangedAttribute("attribute.name.mythicmetals.carmot_shield", 0, 0, 2048).setSyncable(true));
    public static final Holder<Attribute> UNDEAD_BONUS_DAMAGE = RegistryHelper.entityAttribute("undead_bonus_damage", new RangedAttribute("attribute.name.mythicmetals.undead_bonus_damage", 0, -1024, 1024) {
        @Override
        public ChatFormatting getStyle(boolean addition) {
            return ChatFormatting.YELLOW;
        }
    }.setSyncable(true));
    public static final Holder<Attribute> ELYTRA_ROCKET_SPEED = RegistryHelper.entityAttribute("elytra_rocket_speed", new RangedAttribute("attribute.name.mythicmetals.elytra_rocket_speed", 1, 0, 1024).setSyncable(true));
    public static final Holder<Attribute> FIRE_VULNERABILITY = RegistryHelper.entityAttribute("fire_vulnerability", new RangedAttribute("attribute.name.mythicmetals.fire_vulnerability", 0, 0, 2048).setSyncable(true));

    public static void init() {
    }
}
