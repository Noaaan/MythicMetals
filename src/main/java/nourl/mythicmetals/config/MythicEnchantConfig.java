package nourl.mythicmetals.config;

import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import nourl.mythicmetals.MythicMetals;
import nourl.mythicmetals.registry.RegisterArmor;

import java.util.HashMap;

// Most cursed shit I have ever seen
public class MythicEnchantConfig {
    public static void createConfig() {
        HashMap<Identifier, HashMap<Identifier, Integer>> cursedconfig = new HashMap<Identifier, HashMap <Identifier, Integer>>() {{
    put(Registry.ITEM.getId(RegisterArmor.AQUARIUM_BOOTS), new HashMap<Identifier, Integer>() {{
        put(new Identifier("minecraft:depth_strider"), 3);
            }});
        }};
        wraith.enchant_giver.EnchantsList.createConfig(MythicMetals.MOD_ID, cursedconfig, false);
    }
}
