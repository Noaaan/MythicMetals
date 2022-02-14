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

    public static final Ability AQUA_AFFINITY = new Ability("Natural Aqua Affinity", 1, false);
    public static final Ability WATER_PROTECTION = new Ability("Natural Water Protection", 3);
    public static final Ability BLAST_PROTECTION = new Ability("Natural Blast Resistance", 6);
    public static final Ability DEPTH_STRIDER = new Ability("Natural Depth Strider", 3);
    public static final Ability FEATHER_FALLING = new Ability("Natural Feather Falling", 4);
    public static final Ability FIRE_ASPECT = new Ability("Natural Fire Aspect", 4);
    public static final Ability PROJECTILE_PROTECTION = new Ability("Natural Projectile Protection", 5);
    public static final Ability RESPIRATION = new Ability("Natural Respiration", 3);
    public static final Ability SPIKED_HELM = new Ability("Spiked Helmet", 3, false);
    public static final Ability SMITE = new Ability("Holy", 4);
    public static final Ability MENDING = new Ability("Regrowth", 1, false);
    public static final Ability BLAST_PADDING = new Ability("Blast Padding", 1, true);
    public static final Ability MATERIAL_TOOLTIP = new Ability("Rare Crafting Material", 0, false);

    public static void init() {
        SPIKED_HELM.addItem(MythicArmor.LEGENDARY_BANGLUM.getHelmet(), Style.EMPTY.withColor(Formatting.GOLD));
        BLAST_PROTECTION.addItem(MythicArmor.LEGENDARY_BANGLUM.getChestplate(), Style.EMPTY.withColor(Formatting.GOLD));
        BLAST_PADDING.addArmorSet(MythicArmor.BANGLUM, Style.EMPTY.withColor(Formatting.GOLD));
        PROJECTILE_PROTECTION.addItem(MythicArmor.LEGENDARY_BANGLUM.getLeggings(), Style.EMPTY.withColor(Formatting.GOLD));
        FEATHER_FALLING.addItem(MythicArmor.LEGENDARY_BANGLUM.getBoots(), Style.EMPTY.withColor(Formatting.GOLD));
        AQUA_AFFINITY.addItem(MythicArmor.AQUARIUM.getHelmet(), Style.EMPTY.withColor(Formatting.AQUA));
        AQUA_AFFINITY.addToolSet(MythicTools.AQUARIUM, Style.EMPTY.withColor(Formatting.AQUA));
        WATER_PROTECTION.addItem(MythicArmor.AQUARIUM.getChestplate(), Style.EMPTY.withColor(Formatting.AQUA));
        RESPIRATION.addItem(MythicArmor.AQUARIUM.getLeggings(), Style.EMPTY.withColor(Formatting.AQUA));
        DEPTH_STRIDER.addItem(MythicArmor.AQUARIUM.getBoots(), Style.EMPTY.withColor(Formatting.AQUA));
        MENDING.addArmorSet(MythicArmor.PROMETHEUM, Style.EMPTY.withColor(0x3A6A56));
        MENDING.addToolSet(MythicTools.PROMETHEUM, Style.EMPTY.withColor(0x3A6A56));
        MATERIAL_TOOLTIP.addItem(MythicItems.AQUARIUM_PEARL, Style.EMPTY.withItalic(true).withColor(Formatting.AQUA));
        MATERIAL_TOOLTIP.addItem(MythicItems.BANGLUM_CHUNK, Style.EMPTY.withItalic(true).withColor(Formatting.GOLD));

        SMITE.addItem(MythicTools.WHITE_AEGIS_SWORD, Style.EMPTY.withColor(Formatting.YELLOW));
        FIRE_ASPECT.addItem(MythicTools.RED_AEGIS_SWORD, Style.EMPTY.withColor(0xDA1F00));
    }

}
