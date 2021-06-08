package nourl.mythicmetals.config;

import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import nourl.mythicmetals.registry.RegisterArmor;
import wraith.enchant_giver.EnchantsList;

// Hardcodes enchantments onto armour into the Enchant Giver config
public class MythicEnchantConfig {
    public static void appendEnchants() {
        EnchantsList.addEnchant(Registry.ITEM.getId(RegisterArmor.AQUARIUM_HELMET), new Identifier("minecraft:aqua_affinity"), 1, true);
        EnchantsList.addEnchant(Registry.ITEM.getId(RegisterArmor.AQUARIUM_LEGGINGS), new Identifier("minecraft:respiration"), 2, true);
        EnchantsList.addEnchant(Registry.ITEM.getId(RegisterArmor.AQUARIUM_BOOTS), new Identifier("minecraft:depth_strider"), 2, true);
        EnchantsList.addEnchant(Registry.ITEM.getId(RegisterArmor.PROMETHEUM_HELMET), new Identifier("minecraft:mending"), 1, true);
        EnchantsList.addEnchant(Registry.ITEM.getId(RegisterArmor.PROMETHEUM_CHESTPLATE), new Identifier("minecraft:mending"), 1, true);
        EnchantsList.addEnchant(Registry.ITEM.getId(RegisterArmor.PROMETHEUM_LEGGINGS), new Identifier("minecraft:mending"), 1, true);
        EnchantsList.addEnchant(Registry.ITEM.getId(RegisterArmor.PROMETHEUM_BOOTS), new Identifier("minecraft:mending"), 1, true);
    }
}
