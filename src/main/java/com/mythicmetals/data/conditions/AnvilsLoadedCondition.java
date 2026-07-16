package com.mythicmetals.data.conditions;

import com.mojang.serialization.MapCodec;
import com.mythicmetals.MythicMetals;
import net.fabricmc.fabric.api.resource.conditions.v1.ResourceCondition;
import net.fabricmc.fabric.api.resource.conditions.v1.ResourceConditionType;
import net.minecraft.resources.RegistryOps;

public class AnvilsLoadedCondition implements ResourceCondition {
    public static final MapCodec<AnvilsLoadedCondition> CODEC = MapCodec.unit(AnvilsLoadedCondition::new);

    @Override
    public ResourceConditionType<?> getType() {
        return MythicResourceConditions.NUGGETS;
    }

    @Override
    public boolean test(RegistryOps.RegistryInfoLookup registryInfo) {
        return MythicMetals.CONFIG.enableAnvils();
    }
}
