package com.mythicmetals.registry;

import com.mythicmetals.MythicMetals;
import com.mythicmetals.misc.SimpleCriteria;
import io.wispforest.owo.registration.reflect.AutoRegistryContainer;
import io.wispforest.owo.registration.reflect.SimpleFieldProcessingSubject;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.advancements.CriterionTrigger;
import java.lang.reflect.Field;

public class RegisterCriteria implements SimpleFieldProcessingSubject<CriterionTrigger<?>> {
    public static final SimpleCriteria USED_BLAST_MINING = new SimpleCriteria();
    public static final SimpleCriteria RECEIVED_COMBUSTION_FROM_CREEPER = new SimpleCriteria();

    @Override
    public void processField(CriterionTrigger<?> value, String name, Field field) {
        CriteriaTriggers.register(MythicMetals.MOD_ID + ":" + name, value);
    }

    @Override
    public Class<CriterionTrigger<?>> getTargetFieldType() {
        return AutoRegistryContainer.conform(CriterionTrigger.class);
    }
}
