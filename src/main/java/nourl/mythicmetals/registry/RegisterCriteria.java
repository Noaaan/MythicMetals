package nourl.mythicmetals.registry;

import net.minecraft.advancement.criterion.Criteria;
import nourl.mythicmetals.misc.RecievedCombustionFromCreeper;

public class RegisterCriteria {
    public static final RecievedCombustionFromCreeper RECIEVED_COMBUSTION_FROM_CREEPER = new RecievedCombustionFromCreeper();

    public static void init() {
        Criteria.register(RECIEVED_COMBUSTION_FROM_CREEPER);
    }
}
