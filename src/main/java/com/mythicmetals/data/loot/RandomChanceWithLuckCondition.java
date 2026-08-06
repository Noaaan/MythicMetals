package com.mythicmetals.data.loot;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;

public record RandomChanceWithLuckCondition(float chance) implements LootItemCondition {
    public static final MapCodec<RandomChanceWithLuckCondition> MAP_CODEC = RecordCodecBuilder.mapCodec(
        instance -> instance
            .group(Codec.FLOAT.fieldOf("chance")
                .forGetter(RandomChanceWithLuckCondition::chance))
            .apply(instance, RandomChanceWithLuckCondition::new)
    );

    public boolean test(LootContext lootContext) {
        if (lootContext.getOptionalParameter(LootContextParams.THIS_ENTITY) instanceof LivingEntity entity && entity.getAttributes().hasAttribute(Attributes.LUCK)) {
            double luckModifier = chance * (entity.getAttributeValue(Attributes.LUCK) / 10);
            return lootContext.getRandom().nextFloat() < this.chance + luckModifier;
        }
        return lootContext.getRandom().nextFloat() < this.chance;
    }

    public static LootItemCondition.Builder builder(float chance) {
        return () -> new RandomChanceWithLuckCondition(chance);
    }

    @Override
    public MapCodec<? extends LootItemCondition> codec() {
        return MAP_CODEC;
    }
}
