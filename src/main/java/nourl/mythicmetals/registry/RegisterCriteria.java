package nourl.mythicmetals.registry;

import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.util.Identifier;
import nourl.mythicmetals.misc.RegistryHelper;
import nourl.mythicmetals.misc.SimpleCriteria;

public class RegisterCriteria {
    public static final Identifier ENCHANTED_MIDAS_IN_STAFF_ID = RegistryHelper.id("use_enchanted_midas_in_carmot_staff");
    public static final Identifier USED_BLAST_MINING_ID = RegistryHelper.id("used_blast_mining");
    public static final Identifier RECIEVED_COMBUSTION_FROM_CREEPER_ID = RegistryHelper.id("recieved_combustion_from_creeper");
    public static final SimpleCriteria BLAST_MINING = new SimpleCriteria(USED_BLAST_MINING_ID);
    public static final SimpleCriteria ENCHANTED_MIDAS_IN_STAFF = new SimpleCriteria(ENCHANTED_MIDAS_IN_STAFF_ID);
    public static final SimpleCriteria RECIEVED_COMBUSTION_FROM_CREEPER = new SimpleCriteria(RECIEVED_COMBUSTION_FROM_CREEPER_ID);

    public static void init() {
        Criteria.register(BLAST_MINING);
        Criteria.register(RECIEVED_COMBUSTION_FROM_CREEPER);
        Criteria.register(ENCHANTED_MIDAS_IN_STAFF);
    }
}
