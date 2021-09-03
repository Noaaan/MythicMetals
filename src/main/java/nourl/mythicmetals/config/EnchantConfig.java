package nourl.mythicmetals.config;

import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import nourl.mythicmetals.registry.RegisterArmor;
import nourl.mythicmetals.registry.RegisterTools;
import wraith.enchant_giver.EnchantsList;

// Hardcodes enchantments onto armour into the Enchant Giver config
public class EnchantConfig {
    public static void appendEnchants() {
        EnchantsList.addEnchant(Registry.ITEM.getId(RegisterArmor.AQUARIUM.getHelmet()), new Identifier("minecraft:aqua_affinity"), 1, true);
        EnchantsList.addEnchant(Registry.ITEM.getId(RegisterArmor.AQUARIUM.getLeggings()), new Identifier("minecraft:respiration"), 2, true);
        EnchantsList.addEnchant(Registry.ITEM.getId(RegisterArmor.AQUARIUM.getBoots()), new Identifier("minecraft:depth_strider"), 2, true);

        EnchantsList.addEnchant(Registry.ITEM.getId(RegisterArmor.BANGLUM.getHelmet()), new Identifier("minecraft:projectile_protection"), 3, true);

        EnchantsList.addEnchant(Registry.ITEM.getId(RegisterArmor.PROMETHEUM.getHelmet()), new Identifier("minecraft:mending"), 1, true);
        EnchantsList.addEnchant(Registry.ITEM.getId(RegisterArmor.PROMETHEUM.getChestplate()), new Identifier("minecraft:mending"), 1, true);
        EnchantsList.addEnchant(Registry.ITEM.getId(RegisterArmor.PROMETHEUM.getLeggings()), new Identifier("minecraft:mending"), 1, true);
        EnchantsList.addEnchant(Registry.ITEM.getId(RegisterArmor.PROMETHEUM.getBoots()), new Identifier("minecraft:mending"), 1, true);

        EnchantsList.addEnchant(Registry.ITEM.getId(RegisterTools.DISCORDIUM.getSword()), new Identifier("minecraft:mending"), 1, true);
        EnchantsList.addEnchant(Registry.ITEM.getId(RegisterTools.DISCORDIUM.getAxe()), new Identifier("minecraft:mending"), 1, true);
        EnchantsList.addEnchant(Registry.ITEM.getId(RegisterTools.DISCORDIUM.getPickaxe()), new Identifier("minecraft:mending"), 1, true);
        EnchantsList.addEnchant(Registry.ITEM.getId(RegisterTools.DISCORDIUM.getShovel()), new Identifier("minecraft:mending"), 1, true);
        EnchantsList.addEnchant(Registry.ITEM.getId(RegisterTools.DISCORDIUM.getHoe()), new Identifier("minecraft:mending"), 1, true);
        EnchantsList.addEnchant(Registry.ITEM.getId(RegisterArmor.DISCORDIUM.getHelmet()), new Identifier("minecraft:mending"), 1, true);
        EnchantsList.addEnchant(Registry.ITEM.getId(RegisterArmor.DISCORDIUM.getChestplate()), new Identifier("minecraft:mending"), 1, true);
        EnchantsList.addEnchant(Registry.ITEM.getId(RegisterArmor.DISCORDIUM.getLeggings()), new Identifier("minecraft:mending"), 1, true);
        EnchantsList.addEnchant(Registry.ITEM.getId(RegisterArmor.DISCORDIUM.getBoots()), new Identifier("minecraft:mending"), 1, true);

        EnchantsList.addEnchant(Registry.ITEM.getId(RegisterTools.WHITE_AEGIS_SWORD), new Identifier("minecraft:smite"), 6, true);
        EnchantsList.addEnchant(Registry.ITEM.getId(RegisterTools.RED_AEGIS_SWORD), new Identifier("minecraft:fire_aspect"), 3, true);
    }
    public static void appendWaterProt() {
        EnchantsList.addEnchant(Registry.ITEM.getId(RegisterArmor.AQUARIUM.getChestplate()), new Identifier("origins:water_protection"), 2, true);
    }
}
