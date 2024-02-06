package nourl.mythicmetals.misc;

import com.google.gson.JsonObject;
import net.minecraft.advancement.criterion.AbstractCriterion;
import net.minecraft.advancement.criterion.AbstractCriterionConditions;
import net.minecraft.predicate.entity.AdvancementEntityPredicateDeserializer;
import net.minecraft.predicate.entity.LootContextPredicate;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;
import java.util.Optional;

public class SimpleCriteria extends AbstractCriterion<SimpleCriteria.Conditions> {

    public SimpleCriteria() {
    }

    public void trigger(ServerPlayerEntity entity) {
        this.trigger(entity, conditions -> true);
    }

    @Override
    protected Conditions conditionsFromJson(JsonObject obj, Optional<LootContextPredicate> predicate, AdvancementEntityPredicateDeserializer predicateDeserializer) {
        return new Conditions();
    }

    public static class Conditions extends AbstractCriterionConditions {
        public Conditions() {
            super(Optional.empty());
        }
    }
}
