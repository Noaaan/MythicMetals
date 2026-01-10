package com.mythicmetals.misc;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.advancements.critereon.*;
import net.minecraft.server.level.ServerPlayer;
import java.util.Optional;

public class SimpleCriteria extends SimpleCriterionTrigger<SimpleCriteria.Conditions> {

    public SimpleCriteria() {
    }

    public void trigger(ServerPlayer entity) {
        this.trigger(entity, conditions -> true);
    }

    @Override
    public Codec<Conditions> codec() {
        return Conditions.CODEC;
    }

    public record Conditions(Optional<ContextAwarePredicate> player) implements SimpleCriterionTrigger.SimpleInstance {

        public static final Codec<Conditions> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            EntityPredicate.ADVANCEMENT_CODEC.optionalFieldOf("player").forGetter(Conditions::player)
        ).apply(instance, Conditions::new));
    }
}
