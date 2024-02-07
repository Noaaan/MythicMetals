package nourl.mythicmetals.misc;

import com.mojang.serialization.Codec;
import net.minecraft.advancement.criterion.AbstractCriterion;
import net.minecraft.predicate.entity.LootContextPredicate;
import net.minecraft.predicate.entity.LootContextPredicateValidator;
import net.minecraft.server.network.ServerPlayerEntity;
import java.util.Optional;

public class SimpleCriteria extends AbstractCriterion<SimpleCriteria.Conditions> {

    // FIXME - Broken at the moment
    public SimpleCriteria() {
    }

    public void trigger(ServerPlayerEntity entity) {
        this.trigger(entity, conditions -> true);
    }

    @Override
    public Codec<Conditions> getConditionsCodec() {
        return null;
    }

    public static class Conditions implements AbstractCriterion.Conditions {
        public Conditions() {
        }

        @Override
        public void validate(LootContextPredicateValidator validator) {
            AbstractCriterion.Conditions.super.validate(validator);
        }

        @Override
        public Optional<LootContextPredicate> player() {
            return Optional.empty();
        }
    }
}
