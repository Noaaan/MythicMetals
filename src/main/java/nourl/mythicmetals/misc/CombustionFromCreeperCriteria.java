package nourl.mythicmetals.misc;

import com.google.gson.JsonObject;
import net.minecraft.advancement.criterion.AbstractCriterion;
import net.minecraft.advancement.criterion.AbstractCriterionConditions;
import net.minecraft.predicate.entity.AdvancementEntityPredicateDeserializer;
import net.minecraft.predicate.entity.LootContextPredicate;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;

public class CombustionFromCreeperCriteria extends AbstractCriterion<CombustionFromCreeperCriteria.Conditions> {
    public static final Identifier ID = RegistryHelper.id("recieved_combustion_from_creeper");

    @Override
    public Identifier getId() {
        return ID;
    }

    public void trigger(ServerPlayerEntity entity) {
        this.trigger(entity, conditions -> true);
    }

    @Override
    protected Conditions conditionsFromJson(JsonObject obj, LootContextPredicate playerPredicate, AdvancementEntityPredicateDeserializer predicateDeserializer) {
        return new Conditions();
    }

    public static class Conditions extends AbstractCriterionConditions {
        public Conditions() {
            super(ID, LootContextPredicate.EMPTY);
        }
    }
}
