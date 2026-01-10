package com.mythicmetals.registry;

import com.mythicmetals.misc.RandomChanceWithLuckCondition;
import com.mythicmetals.misc.RegistryHelper;
import net.minecraft.world.level.storage.loot.predicates.LootItemConditionType;

public class RegisterLootConditions {

    private RegisterLootConditions() {
    }

    public static final LootItemConditionType RANDOM_CHANCE_WITH_LUCK = RegistryHelper.lootConditionType("random_chance_with_luck", RandomChanceWithLuckCondition.CODEC);

    public static void init() {

    }
}
