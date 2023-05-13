package nourl.mythicmetals.misc;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.loot.condition.LootCondition;
import net.minecraft.loot.condition.LootConditionType;
import net.minecraft.loot.condition.LootConditionTypes;
import net.minecraft.loot.context.LootContext;
import net.minecraft.loot.context.LootContextParameters;
import net.minecraft.util.JsonHelper;
import net.minecraft.util.JsonSerializer;

public class RandomChanceWithLuckCondition implements LootCondition {
    final float chance;

    RandomChanceWithLuckCondition(float chance) {
        this.chance = chance;
    }

    @Override
    public LootConditionType getType() {
        return LootConditionTypes.RANDOM_CHANCE;
    }

    public boolean test(LootContext lootContext) {
        if (lootContext.get(LootContextParameters.THIS_ENTITY) instanceof LivingEntity entity && entity.getAttributes().hasAttribute(EntityAttributes.GENERIC_LUCK)) {
            double luckModifier = chance * (entity.getAttributeValue(EntityAttributes.GENERIC_LUCK) / 10);
            return lootContext.getRandom().nextFloat() < this.chance + luckModifier;
        }
        return lootContext.getRandom().nextFloat() < this.chance;
    }

    public static LootCondition.Builder builder(float chance) {
        return () -> new RandomChanceWithLuckCondition(chance);
    }

    public static class Serializer implements JsonSerializer<RandomChanceWithLuckCondition> {
        public void toJson(JsonObject jsonObject, RandomChanceWithLuckCondition randomChanceLootCondition, JsonSerializationContext jsonSerializationContext) {
            jsonObject.addProperty("chance", randomChanceLootCondition.chance);
        }

        public RandomChanceWithLuckCondition fromJson(JsonObject jsonObject, JsonDeserializationContext jsonDeserializationContext) {
            return new RandomChanceWithLuckCondition(JsonHelper.getFloat(jsonObject, "chance"));
        }
    }
}
