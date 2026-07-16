package com.mythicmetals.data;

import com.mythicmetals.MythicMetals;
import com.mythicmetals.misc.SimpleCriteria;
import net.minecraft.advancements.CriteriaTriggers;

public class MythicCriteriaTriggers {
    private MythicCriteriaTriggers() {
    }

    public static final SimpleCriteria USED_BLAST_MINING = new SimpleCriteria();
    public static final SimpleCriteria RECEIVED_COMBUSTION_FROM_CREEPER = new SimpleCriteria();

    public static void init() {
        CriteriaTriggers.register(MythicMetals.MOD_ID + ":" + "used_blast_mining", USED_BLAST_MINING);
        CriteriaTriggers.register(MythicMetals.MOD_ID + ":" + "received_combustion_from_creeper", RECEIVED_COMBUSTION_FROM_CREEPER);
    }
}
