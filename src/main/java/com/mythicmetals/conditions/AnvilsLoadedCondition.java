package com.mythicmetals.conditions;

import com.mojang.serialization.MapCodec;
import net.fabricmc.fabric.api.resource.conditions.v1.ResourceCondition;
import net.fabricmc.fabric.api.resource.conditions.v1.ResourceConditionType;
import net.minecraft.registry.RegistryWrapper;
import com.mythicmetals.MythicMetals;
import org.jetbrains.annotations.Nullable;

public class AnvilsLoadedCondition implements ResourceCondition {
    public static final MapCodec<AnvilsLoadedCondition> CODEC = MapCodec.unit(AnvilsLoadedCondition::new);

    @Override
    public ResourceConditionType<?> getType() {
        return MythicResourceConditions.NUGGETS;
    }

    @Override
    public boolean test(@Nullable RegistryWrapper.WrapperLookup registryLookup) {
        return MythicMetals.CONFIG.enableAnvils();
    }
}
