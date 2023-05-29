package nourl.mythicmetals.registry;

import net.minecraft.loot.condition.LootConditionType;
import nourl.mythicmetals.misc.RandomChanceWithLuckCondition;
import nourl.mythicmetals.misc.RegistryHelper;

public class RegisterLootConditions {

    public RegisterLootConditions(){}
    @SuppressWarnings("unused")
    public static final LootConditionType RANDOM_CHANCE_WITH_LUCK = RegistryHelper.lootConditionType("random_chance_with_luck", new RandomChanceWithLuckCondition.Serializer());

    public static void init() {

    }
}
