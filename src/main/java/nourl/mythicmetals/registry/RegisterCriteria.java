package nourl.mythicmetals.registry;

import net.minecraft.advancement.criterion.Criteria;
import nourl.mythicmetals.misc.BlastMiningCriteria;
import nourl.mythicmetals.misc.CombustionFromCreeperCriteria;

public class RegisterCriteria {
    public static final BlastMiningCriteria BLAST_MINING = new BlastMiningCriteria();
    public static final CombustionFromCreeperCriteria RECIEVED_COMBUSTION_FROM_CREEPER = new CombustionFromCreeperCriteria();

    public static void init() {
        Criteria.register(BLAST_MINING);
        Criteria.register(RECIEVED_COMBUSTION_FROM_CREEPER);
    }
}
