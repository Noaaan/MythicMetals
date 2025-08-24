package com.mythicmetals.registry;

import com.mythicmetals.misc.RandomChanceWithLuckCondition;
import com.mythicmetals.misc.RegistryHelper;
import net.minecraft.loot.condition.LootConditionType;

public class RegisterLootConditions {

    private RegisterLootConditions() {
    }

    public static final LootConditionType RANDOM_CHANCE_WITH_LUCK = RegistryHelper.lootConditionType("random_chance_with_luck", RandomChanceWithLuckCondition.CODEC);

    public static void init() {

    }
}
