package nourl.mythicmetals.config;

import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import nourl.mythicmetals.tools.MythicTools;
import nourl.mythicmetals.armor.MythicArmor;
import wraith.enchant_giver.EnchantsList;

// Hardcodes enchantments onto armour into the Enchant Giver config
public class EnchantConfig {
    public static void appendEnchants() {
        EnchantsList.addEnchant(Registry.ITEM.getId(MythicArmor.AQUARIUM.getHelmet()), new Identifier("minecraft:aqua_affinity"), 1, true);
        EnchantsList.addEnchant(Registry.ITEM.getId(MythicArmor.AQUARIUM.getLeggings()), new Identifier("minecraft:respiration"), 2, true);
        EnchantsList.addEnchant(Registry.ITEM.getId(MythicArmor.AQUARIUM.getBoots()), new Identifier("minecraft:depth_strider"), 2, true);

        EnchantsList.addEnchant(Registry.ITEM.getId(MythicArmor.BANGLUM.getHelmet()), new Identifier("minecraft:projectile_protection"), 3, true);

        EnchantsList.addEnchant(Registry.ITEM.getId(MythicArmor.PROMETHEUM.getHelmet()), new Identifier("minecraft:mending"), 1, true);
        EnchantsList.addEnchant(Registry.ITEM.getId(MythicArmor.PROMETHEUM.getChestplate()), new Identifier("minecraft:mending"), 1, true);
        EnchantsList.addEnchant(Registry.ITEM.getId(MythicArmor.PROMETHEUM.getLeggings()), new Identifier("minecraft:mending"), 1, true);
        EnchantsList.addEnchant(Registry.ITEM.getId(MythicArmor.PROMETHEUM.getBoots()), new Identifier("minecraft:mending"), 1, true);
        EnchantsList.addEnchant(Registry.ITEM.getId(MythicTools.PROMETHEUM.getSword()), new Identifier("minecraft:mending"), 1, true);
        EnchantsList.addEnchant(Registry.ITEM.getId(MythicTools.PROMETHEUM.getAxe()), new Identifier("minecraft:mending"), 1, true);
        EnchantsList.addEnchant(Registry.ITEM.getId(MythicTools.PROMETHEUM.getPickaxe()), new Identifier("minecraft:mending"), 1, true);
        EnchantsList.addEnchant(Registry.ITEM.getId(MythicTools.PROMETHEUM.getShovel()), new Identifier("minecraft:mending"), 1, true);
        EnchantsList.addEnchant(Registry.ITEM.getId(MythicTools.PROMETHEUM.getHoe()), new Identifier("minecraft:mending"), 1, true);

        EnchantsList.addEnchant(Registry.ITEM.getId(MythicTools.WHITE_AEGIS_SWORD), new Identifier("minecraft:smite"), 6, true);
        EnchantsList.addEnchant(Registry.ITEM.getId(MythicTools.RED_AEGIS_SWORD), new Identifier("minecraft:fire_aspect"), 3, true);
    }
    public static void appendWaterProt() {
        EnchantsList.addEnchant(Registry.ITEM.getId(MythicArmor.AQUARIUM.getChestplate()), new Identifier("origins:water_protection"), 2, true);
    }
}
