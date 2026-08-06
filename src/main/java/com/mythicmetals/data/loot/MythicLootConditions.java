package com.mythicmetals.data.loot;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;

public class MythicLootConditions {

    private MythicLootConditions() {
    }

    public static final LootItemCondition RANDOM_CHANCE_WITH_LUCK = (LootItemCondition) Registry.register(BuiltInRegistries.LOOT_CONDITION_TYPE, "random_chance_with_luck", RandomChanceWithLuckCondition.MAP_CODEC);

    public static void init() {

    }
}
