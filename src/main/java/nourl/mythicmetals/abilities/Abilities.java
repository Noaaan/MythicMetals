package nourl.mythicmetals.abilities;

import net.minecraft.text.Style;
import net.minecraft.util.Formatting;
import nourl.mythicmetals.armor.MythicArmor;
import nourl.mythicmetals.item.MythicItems;
import nourl.mythicmetals.tools.MythicTools;

/**
 * Truly hardcode abilities onto items. These act as enchantments, but they stack with them.
 * @author Noaaan
 */
public class Abilities {

    public static final Ability AQUA_AFFINITY = new Ability("aqua_affinity", 1, false);
    public static final Ability BLAST_MINING = new Ability("blast_mining", 0, false);
    public static final Ability KNOCKBACK = new Ability("knockback", 3);
    public static final Ability BLAST_PADDING = new Ability("blast_padding", 1, false);
    public static final Ability BLAST_PROTECTION = new Ability("blast_protection", 6);
    public static final Ability DEPTH_STRIDER = new Ability("depth_strider", 3);
    public static final Ability FEATHER_FALLING = new Ability("feather_falling", 4);
    public static final Ability FIRE_ASPECT = new Ability("fire_aspect", 4);
    public static final Ability MATERIAL_TOOLTIP = new Ability("material_tooltip", 0, false);
    public static final Ability MENDING = new Ability("mending", 1, false);
    public static final Ability PROJECTILE_PROTECTION = new Ability("projectile_protection", 5);
    public static final Ability RESPIRATION = new Ability("respiration", 3);
    public static final Ability SMITE = new Ability("smite", 4);
    public static final Ability SPIKED_HELM = new Ability("spiked_helm", 3, false);
    public static final Ability WATER_PROTECTION = new Ability("water_protection", 3);

    public static void init() {
        AQUA_AFFINITY.addItem(MythicArmor.AQUARIUM.getHelmet(), Style.EMPTY.withColor(Formatting.AQUA));
        AQUA_AFFINITY.addToolSet(MythicTools.AQUARIUM, Style.EMPTY.withColor(Formatting.AQUA));
        BLAST_MINING.addItem(MythicTools.LEGENDARY_BANGLUM.getPickaxe(), Style.EMPTY.withColor(Formatting.GOLD));
        BLAST_MINING.addItem(MythicTools.LEGENDARY_BANGLUM.getShovel(), Style.EMPTY.withColor(Formatting.GOLD));
        BLAST_PADDING.addArmorSet(MythicArmor.BANGLUM, Style.EMPTY.withColor(Formatting.GOLD));
        BLAST_PROTECTION.addItem(MythicArmor.LEGENDARY_BANGLUM.getChestplate(), Style.EMPTY.withColor(Formatting.GOLD));
        DEPTH_STRIDER.addItem(MythicArmor.AQUARIUM.getBoots(), Style.EMPTY.withColor(Formatting.AQUA));
        FEATHER_FALLING.addItem(MythicArmor.LEGENDARY_BANGLUM.getBoots(), Style.EMPTY.withColor(Formatting.GOLD));
        KNOCKBACK.addItem(MythicTools.LEGENDARY_BANGLUM.getSword(), Style.EMPTY.withColor(Formatting.GOLD));
        KNOCKBACK.addItem(MythicTools.LEGENDARY_BANGLUM.getAxe(), Style.EMPTY.withColor(Formatting.GOLD));
        KNOCKBACK.addItem(MythicTools.LEGENDARY_BANGLUM.getHoe(), Style.EMPTY.withColor(Formatting.GOLD));
        MATERIAL_TOOLTIP.addItem(MythicItems.AQUARIUM_PEARL, Style.EMPTY.withItalic(true).withColor(Formatting.AQUA));
        MATERIAL_TOOLTIP.addItem(MythicItems.BANGLUM_CHUNK, Style.EMPTY.withItalic(true).withColor(Formatting.GOLD));
        MENDING.addArmorSet(MythicArmor.PROMETHEUM, Style.EMPTY.withColor(0x3A6A56));
        MENDING.addToolSet(MythicTools.PROMETHEUM, Style.EMPTY.withColor(0x3A6A56));
        PROJECTILE_PROTECTION.addItem(MythicArmor.LEGENDARY_BANGLUM.getLeggings(), Style.EMPTY.withColor(Formatting.GOLD));
        RESPIRATION.addItem(MythicArmor.AQUARIUM.getLeggings(), Style.EMPTY.withColor(Formatting.AQUA));
        SPIKED_HELM.addItem(MythicArmor.LEGENDARY_BANGLUM.getHelmet(), Style.EMPTY.withColor(Formatting.GOLD));
        WATER_PROTECTION.addItem(MythicArmor.AQUARIUM.getChestplate(), Style.EMPTY.withColor(Formatting.AQUA));

        FIRE_ASPECT.addItem(MythicTools.RED_AEGIS_SWORD, Style.EMPTY.withColor(0xDA1F00));
        SMITE.addItem(MythicTools.WHITE_AEGIS_SWORD, Style.EMPTY.withColor(Formatting.YELLOW));
    }

}
