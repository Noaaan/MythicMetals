package com.mythicmetals.data.loot;

import com.mythicmetals.misc.RegistryHelper;
import net.minecraft.world.level.storage.loot.predicates.LootItemConditionType;

public class MythicLootConditions {

    private MythicLootConditions() {
    }

    public static final LootItemConditionType RANDOM_CHANCE_WITH_LUCK = RegistryHelper.lootConditionType("random_chance_with_luck", RandomChanceWithLuckCondition.CODEC);

    public static void init() {

    }
}
