package nourl.mythicmetals.registry;

import net.fabricmc.fabric.api.resource.conditions.v1.ResourceConditions;
import net.minecraft.util.Identifier;
import nourl.mythicmetals.MythicMetals;
import nourl.mythicmetals.config.MythicConfig;
import nourl.mythicmetals.utils.RegistryHelper;

/**
 * Simple resource condition that check if the corresponding {@link MythicConfig} booleans are enabled.
 * This is used to check if anvils or nuggets are disabled, so that errors regarding them can be surpressed.
 */
public class RegisterResourceConditions {

    private static final Identifier ANVILS_LOADED = RegistryHelper.id("anvils_enabled");
    private static final Identifier NUGGETS_LOADED = RegistryHelper.id("nuggets_enabled");

    public static void init() {
        ResourceConditions.register(ANVILS_LOADED, object -> MythicMetals.CONFIG.enableAnvils());
        ResourceConditions.register(NUGGETS_LOADED, object -> MythicMetals.CONFIG.enableNuggets());
    }
}
