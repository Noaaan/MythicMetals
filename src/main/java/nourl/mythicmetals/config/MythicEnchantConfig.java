package nourl.mythicmetals.config;

import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import nourl.mythicmetals.MythicMetals;
import nourl.mythicmetals.registry.RegisterArmor;

import java.util.HashMap;

// Most cursed code I have ever seen
public class MythicEnchantConfig {
    public static void createConfig() {
        HashMap<Identifier, HashMap<Identifier, Integer>> cursedconfig = new HashMap<Identifier, HashMap <Identifier, Integer>>() {{
            put(Registry.ITEM.getId(RegisterArmor.AQUARIUM_BOOTS), new HashMap<Identifier, Integer>() {{
                put(new Identifier("minecraft:depth_strider"), 2);
            }});
            put(Registry.ITEM.getId(RegisterArmor.AQUARIUM_LEGGINGS), new HashMap<Identifier, Integer>() {{
                put(new Identifier("minecraft:respiration"), 2);
            }});
            put(Registry.ITEM.getId(RegisterArmor.AQUARIUM_HELMET), new HashMap<Identifier, Integer>() {{
                put(new Identifier("minecraft:aqua_affinity"), 1);
            }});
            put(Registry.ITEM.getId(RegisterArmor.PROMETHEUM_BOOTS), new HashMap<Identifier, Integer>() {{
                put(new Identifier("minecraft:mending"), 1);
            }});
            put(Registry.ITEM.getId(RegisterArmor.PROMETHEUM_LEGGINGS), new HashMap<Identifier, Integer>() {{
                put(new Identifier("minecraft:mending"), 1);
            }});
            put(Registry.ITEM.getId(RegisterArmor.PROMETHEUM_CHESTPLATE), new HashMap<Identifier, Integer>() {{
                put(new Identifier("minecraft:mending"), 1);
            }});
            put(Registry.ITEM.getId(RegisterArmor.PROMETHEUM_HELMET), new HashMap<Identifier, Integer>() {{
                put(new Identifier("minecraft:mending"), 1);
            }});
        }};
        wraith.enchant_giver.EnchantsList.createConfig(MythicMetals.MOD_ID, cursedconfig, false);
    }
}
