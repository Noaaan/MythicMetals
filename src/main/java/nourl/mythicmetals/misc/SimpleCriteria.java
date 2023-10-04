package nourl.mythicmetals.misc;

import com.google.gson.JsonObject;
import net.minecraft.advancement.criterion.AbstractCriterion;
import net.minecraft.advancement.criterion.AbstractCriterionConditions;
import net.minecraft.predicate.entity.AdvancementEntityPredicateDeserializer;
import net.minecraft.predicate.entity.LootContextPredicate;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;

public class SimpleCriteria extends AbstractCriterion<SimpleCriteria.Conditions> {
    private final Identifier id;

    public SimpleCriteria(Identifier id) {
        this.id = id;
    }

    @Override
    public Identifier getId() {
        return id;
    }

    public void trigger(ServerPlayerEntity entity) {
        this.trigger(entity, conditions -> true);
    }

    @Override
    protected Conditions conditionsFromJson(JsonObject obj, LootContextPredicate playerPredicate, AdvancementEntityPredicateDeserializer predicateDeserializer) {
        return new Conditions();
    }

    public class Conditions extends AbstractCriterionConditions {
        public Conditions() {
            super(id, LootContextPredicate.EMPTY);
        }
    }
}
