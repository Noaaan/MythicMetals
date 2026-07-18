package com.mythicmetals.ability;

/**
 * Truly hardcode abilities onto items. These act as enchantments, but they stack with them.
 * FIXME - Move/make these into components, attributes, or move them to more appropriate places
 *
 * @author Noaaan
 */
public class Abilities {
    public static final Ability BETTER_WATER_PROTECTION = new Ability("water_protection", 4);
    public static final Ability FIRE_PROTECTION = new Ability("fire_protection", 1, false);
    public static final Ability WATER_PROTECTION = new Ability("water_protection", 2);

    public static void init() {
//        FIRE_PROTECTION.addArmorSet(MythicArmor.PALLADIUM, MetalColors.PALLADIUM_STYLE);
//        // Material Tooltips
//        // Mod compat specific abilities
//        if (FabricLoader.getInstance().isModLoaded("origins")) {
//            WATER_PROTECTION.addItem(MythicArmor.AQUARIUM.getChestplate(), MetalColors.AQUA_STYLE);
//            WATER_PROTECTION.addItem(MythicArmor.AQUARIUM.getLeggings(), MetalColors.AQUA_STYLE);
//            BETTER_WATER_PROTECTION.addItem(MythicArmor.TIDESINGER.getChestplate(), MetalColors.TIDESINGER_BLUE);
//            BETTER_WATER_PROTECTION.addItem(MythicArmor.TIDESINGER.getLeggings(), MetalColors.TIDESINGER_BLUE);
//        }
    }

}
