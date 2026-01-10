package com.mythicmetals.conditions;

import com.mojang.serialization.MapCodec;
import com.mythicmetals.MythicMetals;
import net.fabricmc.fabric.api.resource.conditions.v1.ResourceCondition;
import net.fabricmc.fabric.api.resource.conditions.v1.ResourceConditionType;
import net.minecraft.resources.RegistryOps;

public class NuggetsLoadedCondition implements ResourceCondition {
    public static final MapCodec<NuggetsLoadedCondition> CODEC = MapCodec.unit(NuggetsLoadedCondition::new);

    @Override
    public ResourceConditionType<?> getType() {
        return MythicResourceConditions.NUGGETS;
    }

    @Override
    public boolean test(RegistryOps.RegistryInfoLookup registryInfo) {
        return MythicMetals.CONFIG.enableNuggets();
    }
}
