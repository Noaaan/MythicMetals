package com.mythicmetals.ability;

import com.mythicmetals.armor.MythicArmor;
import com.mythicmetals.block.MythicBlocks;
import com.mythicmetals.item.MythicItems;
import com.mythicmetals.item.tools.MythicTools;
import com.mythicmetals.item.tools.MythrilDrill;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.item.Items;
import net.minecraft.text.Style;
import net.minecraft.util.Formatting;

import static com.mythicmetals.misc.UsefulSingletonForColorUtil.MetalColors;

/**
 * Truly hardcode abilities onto items. These act as enchantments, but they stack with them.
 * FIXME - Move/make these into components, attributes, or move them to more appropriate places
 *
 * @author Noaaan
 */
public class Abilities {
    public static final Ability BETTER_WATER_PROTECTION = new Ability("water_protection", 4);
    public static final Ability BLAST_PADDING = new Ability("blast_padding", 1, false);
    public static final Ability BLAST_PROTECTION = new Ability("blast_protection", 6);
    public static final Ability FIRE_PROTECTION = new Ability("fire_protection", 1, false);
    public static final Ability HOT = new Ability("hot", 0, false);
    public static final Ability UPGRADE_TOOLTIP = new Ability("upgrade_tooltip", 0, false);
    public static final Ability MATERIAL_TOOLTIP = new Ability("material_tooltip", 0, false);
    public static final Ability PROJECTILE_PROTECTION = new Ability("projectile_protection", 5);
    public static final Ability WATER_PROTECTION = new Ability("water_protection", 2);

    public static void init() {
        BLAST_PADDING.addArmorSet(MythicArmor.BANGLUM, MetalColors.GOLD_STYLE);
        BLAST_PROTECTION.addItem(MythicArmor.LEGENDARY_BANGLUM.getChestplate(), MetalColors.GOLD_STYLE);
        PROJECTILE_PROTECTION.addItem(MythicArmor.LEGENDARY_BANGLUM.getLeggings(), MetalColors.GOLD_STYLE);
        FIRE_PROTECTION.addArmorSet(MythicArmor.PALLADIUM, MetalColors.PALLADIUM_STYLE);
        HOT.addToolSet(MythicTools.PALLADIUM, MetalColors.PALLADIUM_STYLE);
        MythrilDrill.drillUpgrades.forEach((item, s) -> {
            if (item != Items.AIR) {
                UPGRADE_TOOLTIP.addItem(item, Style.EMPTY.withColor(MetalColors.MYTHRIL.rgb()));
            }
        });
        // Material Tooltips
        MATERIAL_TOOLTIP.addItem(MythicItems.Mats.AQUARIUM_PEARL, MetalColors.AQUA_STYLE);
        MATERIAL_TOOLTIP.addItem(MythicItems.Mats.BANGLUM_CHUNK, MetalColors.GOLD_STYLE);
        MATERIAL_TOOLTIP.addItem(MythicItems.Mats.CARMOT_STONE, MetalColors.CARMOT_STYLE);
        MATERIAL_TOOLTIP.addItem(MythicBlocks.ENCHANTED_MIDAS_GOLD_BLOCK.asItem(), MetalColors.GOLD_STYLE);
        MATERIAL_TOOLTIP.addItem(MythicItems.Mats.STORMYX_SHELL, Style.EMPTY.withColor(Formatting.LIGHT_PURPLE));
        // Mod compat specific abilities
        if (FabricLoader.getInstance().isModLoaded("origins")) {
            WATER_PROTECTION.addItem(MythicArmor.AQUARIUM.getChestplate(), MetalColors.AQUA_STYLE);
            WATER_PROTECTION.addItem(MythicArmor.AQUARIUM.getLeggings(), MetalColors.AQUA_STYLE);
            BETTER_WATER_PROTECTION.addItem(MythicArmor.TIDESINGER.getChestplate(), MetalColors.TIDESINGER_BLUE);
            BETTER_WATER_PROTECTION.addItem(MythicArmor.TIDESINGER.getLeggings(), MetalColors.TIDESINGER_BLUE);
        }
    }

}
