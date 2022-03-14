package nourl.mythicmetals.utils;

import com.google.gson.JsonObject;
import net.fabricmc.fabric.api.resource.conditions.v1.ConditionJsonProvider;
import net.fabricmc.fabric.api.resource.conditions.v1.ResourceConditions;
import net.minecraft.util.Identifier;
import nourl.mythicmetals.MythicMetals;
import nourl.mythicmetals.config.MythicConfig;

public class MythicResourceConditions {

    private static final Identifier ANVILS_LOADED = RegistryHelper.id("anvils_enabled");

    /**
     * A simple resource condition that check if the {@link MythicConfig#enableAnvils} boolean is enabled.
     * This is used to check if anvils are disabled, so that the loot tables can be surpressed.
     * @return  An empty resource condition
     */
    public static ConditionJsonProvider anvils() {
        return new ConditionJsonProvider() {
            @Override
            public Identifier getConditionId() {
                return ANVILS_LOADED;
            }

            @Override
            public void writeParameters(JsonObject object) {

            }

        };
    }

    public static void init() {
        ResourceConditions.register(ANVILS_LOADED, object -> MythicMetals.CONFIG.enableAnvils);
    }
}
