package com.mythicmetals.data.loot;

import com.mythicmetals.misc.RegistryHelper;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;

public class MythicLootConditions {

    private MythicLootConditions() {
    }

    public static void init() {
        Registry.register(BuiltInRegistries.LOOT_CONDITION_TYPE, RegistryHelper.id("random_chance_with_luck"), RandomChanceWithLuckCondition.MAP_CODEC);
    }
}
