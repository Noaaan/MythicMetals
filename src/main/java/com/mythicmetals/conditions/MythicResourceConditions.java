package com.mythicmetals.conditions;

import com.mythicmetals.MythicMetals;
import com.mythicmetals.misc.RegistryHelper;
import net.fabricmc.fabric.api.resource.conditions.v1.ResourceConditionType;
import net.fabricmc.fabric.api.resource.conditions.v1.ResourceConditions;
import net.minecraft.util.Identifier;

/**
 * Simple resource conditions that check if the corresponding {@link MythicMetals#CONFIG} booleans are enabled.
 * <br>
 * This is used to check if anvils or nuggets are disabled, so that errors regarding them can be surpressed.
 */
public class MythicResourceConditions {

    private static final Identifier ANVILS_LOADED = RegistryHelper.id("anvils_enabled");
    private static final Identifier NUGGETS_LOADED = RegistryHelper.id("nuggets_enabled");
    private static final Identifier DUST_LOADED = RegistryHelper.id("dust_enabled");
    public static final ResourceConditionType<AnvilsLoadedCondition> ANVILS = ResourceConditionType.create(ANVILS_LOADED, AnvilsLoadedCondition.CODEC);
    public static final ResourceConditionType<DustLoadedCondition> DUSTS = ResourceConditionType.create(DUST_LOADED, DustLoadedCondition.CODEC);
    public static final ResourceConditionType<NuggetsLoadedCondition> NUGGETS = ResourceConditionType.create(NUGGETS_LOADED, NuggetsLoadedCondition.CODEC);

    public static void init() {
        ResourceConditions.register(ANVILS);
        ResourceConditions.register(DUSTS);
        ResourceConditions.register(NUGGETS);
    }
}
