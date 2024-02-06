package nourl.mythicmetals.registry;

import net.minecraft.loot.condition.LootConditionType;
import nourl.mythicmetals.misc.RandomChanceWithLuckCondition;
import nourl.mythicmetals.misc.RegistryHelper;

public class RegisterLootConditions {

    private RegisterLootConditions() {}
    public static final LootConditionType RANDOM_CHANCE_WITH_LUCK = RegistryHelper.lootConditionType("random_chance_with_luck", RandomChanceWithLuckCondition.CODEC);

    public static void init() {

    }
}
