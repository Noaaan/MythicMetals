package nourl.mythicmetals.abilities;

import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.item.Items;
import net.minecraft.text.Style;
import net.minecraft.util.Formatting;
import nourl.mythicmetals.armor.MythicArmor;
import nourl.mythicmetals.blocks.MythicBlocks;
import nourl.mythicmetals.item.MythicItems;
import nourl.mythicmetals.item.tools.MythicTools;
import static nourl.mythicmetals.misc.UsefulSingletonForColorUtil.MetalColors;

/**
 * Truly hardcode abilities onto items. These act as enchantments, but they stack with them.
 *
 * @author Noaaan
 */
public class Abilities {

    public static final Ability AQUA_AFFINITY = new Ability("aqua_affinity", 1, false);
    public static final Ability BETTER_RESPIRATION = new Ability("respiration", 3);
    public static final Ability BETTER_WATER_PROTECTION = new Ability("water_protection", 4);
    public static final Ability BLAST_MINING = new Ability("blast_mining", 0, false);
    public static final Ability BLAST_PADDING = new Ability("blast_padding", 1, false);
    public static final Ability BLAST_PROTECTION = new Ability("blast_protection", 6);
    public static final Ability BONUS_FORTUNE = new Ability("bonus_fortune", 1, false);
    public static final Ability BONUS_LOOTING = new Ability("bonus_looting", 1, false);
    // Tooltip only, applies to items in #mythicmetals:carmot_armor
    public static final Ability CARMOT_SHIELD = new Ability("carmot_shield", 0, false);
    public static final Ability DEPTH_STRIDER = new Ability("depth_strider", 3);
    public static final Ability FEATHER_FALLING = new Ability("feather_falling", 3);
    public static final Ability FIRE_ASPECT = new Ability("fire_aspect", 4);
    public static final Ability FIRE_PROTECTION = new Ability("fire_protection", 1, false);
    public static final Ability HOT = new Ability("hot", 0, false);
    public static final Ability KNOCKBACK = new Ability("knockback", 3);
    public static final Ability UPGRADE_TOOLTIP = new Ability("upgrade_tooltip", 0, false);
    public static final Ability MATERIAL_TOOLTIP = new Ability("material_tooltip", 0, false);
    // Tooltip only, applies to items in #mythicmetals:prometheum_tools and armor items in #mythicmetals:prometheum_armor
    public static final Ability MENDING = new Ability("mending", 1, false);
    // Tooltip only, applies to Royal Midas Gold Swords
    public static final Ability MIDAS_TOUCH = new Ability("midas_touch", 0, false);
    public static final Ability PROJECTILE_PROTECTION = new Ability("projectile_protection", 5);
    public static final Ability RESPIRATION = new Ability("respiration", 2);
    public static final Ability SMITE = new Ability("smite", 3);
    public static final Ability SPIKED_HELM = new Ability("spiked_helm", 3, false);
    public static final Ability WATER_PROTECTION = new Ability("water_protection", 3);

    public static void init() {
        DrillUpgrades.init();
        UniqueStaffBlocks.init();

        AQUA_AFFINITY.addItem(MythicArmor.AQUARIUM.getHelmet(), MetalColors.AQUA_STYLE);
        AQUA_AFFINITY.addToolSet(MythicTools.AQUARIUM, MetalColors.AQUA_STYLE);
        AQUA_AFFINITY.addItem(MythicArmor.IMPROVED_AQUARIUM.getHelmet(), MetalColors.AQUA_STYLE);
        AQUA_AFFINITY.addToolSet(MythicTools.IMPROVED_AQUARIUM, MetalColors.AQUA_STYLE);
        BETTER_RESPIRATION.addItem(MythicArmor.IMPROVED_AQUARIUM.getChestplate(), MetalColors.AQUA_STYLE);
        BETTER_RESPIRATION.addItem(MythicArmor.IMPROVED_AQUARIUM.getLeggings(), MetalColors.AQUA_STYLE);
        BLAST_MINING.addItem(MythicTools.LEGENDARY_BANGLUM.getPickaxe(), MetalColors.GOLD_STYLE);
        BLAST_MINING.addItem(MythicTools.LEGENDARY_BANGLUM.getShovel(), MetalColors.GOLD_STYLE);
        BLAST_PADDING.addArmorSet(MythicArmor.BANGLUM, MetalColors.GOLD_STYLE);
        BLAST_PROTECTION.addItem(MythicArmor.LEGENDARY_BANGLUM.getChestplate(), MetalColors.GOLD_STYLE);
        BONUS_FORTUNE.addItem(MythicTools.CARMOT.getPickaxe(), MetalColors.CARMOT_STYLE);
        BONUS_FORTUNE.addItem(MythicTools.CARMOT.getAxe(), MetalColors.CARMOT_STYLE);
        BONUS_FORTUNE.addItem(MythicTools.CARMOT.getHoe(), MetalColors.CARMOT_STYLE);
        BONUS_FORTUNE.addItem(MythicTools.CARMOT.getShovel(), MetalColors.CARMOT_STYLE);
        BONUS_LOOTING.addItem(MythicTools.CARMOT.getSword(), MetalColors.CARMOT_STYLE);
        CARMOT_SHIELD.addArmorSet(MythicArmor.CARMOT, MetalColors.CARMOT_STYLE);
        DEPTH_STRIDER.addItem(MythicArmor.AQUARIUM.getBoots(), MetalColors.AQUA_STYLE);
        FEATHER_FALLING.addItem(MythicArmor.LEGENDARY_BANGLUM.getBoots(), MetalColors.GOLD_STYLE);
        FIRE_PROTECTION.addArmorSet(MythicArmor.PALLADIUM, MetalColors.PALLADIUM_STYLE);
        HOT.addToolSet(MythicTools.PALLADIUM, MetalColors.PALLADIUM_STYLE);
        KNOCKBACK.addItem(MythicTools.LEGENDARY_BANGLUM.getSword(), MetalColors.GOLD_STYLE);
        KNOCKBACK.addItem(MythicTools.LEGENDARY_BANGLUM.getAxe(), MetalColors.GOLD_STYLE);
        KNOCKBACK.addItem(MythicTools.LEGENDARY_BANGLUM.getHoe(), MetalColors.GOLD_STYLE);
        DrillUpgrades.MAP.forEach((item, s) -> {
            if (item != Items.AIR)
                UPGRADE_TOOLTIP.addItem(item, Style.EMPTY.withColor(MetalColors.MYTHRIL.rgb()));
        });
        MATERIAL_TOOLTIP.addItem(MythicItems.Mats.AQUARIUM_PEARL, MetalColors.AQUA_STYLE);
        MATERIAL_TOOLTIP.addItem(MythicItems.Mats.BANGLUM_CHUNK, MetalColors.GOLD_STYLE);
        MATERIAL_TOOLTIP.addItem(MythicItems.Mats.CARMOT_STONE, MetalColors.CARMOT_STYLE);
        MATERIAL_TOOLTIP.addItem(MythicBlocks.ENCHANTED_MIDAS_GOLD_BLOCK.asItem(), MetalColors.GOLD_STYLE);
        MATERIAL_TOOLTIP.addItem(MythicItems.Mats.STORMYX_SHELL, Style.EMPTY.withColor(Formatting.LIGHT_PURPLE));
        MENDING.addArmorSet(MythicArmor.PROMETHEUM, Style.EMPTY.withColor(MetalColors.PROMETHEUM.rgb()));
        MENDING.addToolSet(MythicTools.PROMETHEUM, Style.EMPTY.withColor(MetalColors.PROMETHEUM.rgb()));
        MIDAS_TOUCH.addItem(MythicTools.ROYAL_MIDAS_GOLD_SWORD, MetalColors.GOLD_STYLE);
        PROJECTILE_PROTECTION.addItem(MythicArmor.LEGENDARY_BANGLUM.getLeggings(), MetalColors.GOLD_STYLE);
        RESPIRATION.addItem(MythicArmor.AQUARIUM.getChestplate(), MetalColors.AQUA_STYLE);
        RESPIRATION.addItem(MythicArmor.AQUARIUM.getLeggings(), MetalColors.AQUA_STYLE);
        SPIKED_HELM.addItem(MythicArmor.LEGENDARY_BANGLUM.getHelmet(), MetalColors.GOLD_STYLE);
        FIRE_ASPECT.addItem(MythicTools.RED_AEGIS_SWORD, Style.EMPTY.withColor(MetalColors.RED_AEGIS.rgb()));
        SMITE.addItem(MythicTools.WHITE_AEGIS_SWORD, Style.EMPTY.withColor(Formatting.YELLOW));
        if (FabricLoader.getInstance().isModLoaded("origins")) {
            WATER_PROTECTION.addItem(MythicArmor.AQUARIUM.getChestplate(), MetalColors.AQUA_STYLE);
            BETTER_WATER_PROTECTION.addItem(MythicArmor.IMPROVED_AQUARIUM.getChestplate(), MetalColors.AQUA_STYLE);
        }
    }

}
