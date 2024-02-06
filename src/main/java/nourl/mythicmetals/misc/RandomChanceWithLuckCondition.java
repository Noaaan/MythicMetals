package nourl.mythicmetals.misc;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.loot.condition.LootCondition;
import net.minecraft.loot.condition.LootConditionType;
import net.minecraft.loot.context.LootContext;
import net.minecraft.loot.context.LootContextParameters;
import nourl.mythicmetals.registry.RegisterLootConditions;

public record RandomChanceWithLuckCondition(float chance) implements LootCondition {
    public static final Codec<RandomChanceWithLuckCondition> CODEC = RecordCodecBuilder.create(
            instance -> instance
                    .group(Codec.FLOAT.fieldOf("chance")
                            .forGetter(RandomChanceWithLuckCondition::chance))
                    .apply(instance, RandomChanceWithLuckCondition::new)
    );

    @Override
    public LootConditionType getType() {
        return RegisterLootConditions.RANDOM_CHANCE_WITH_LUCK;
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

}
