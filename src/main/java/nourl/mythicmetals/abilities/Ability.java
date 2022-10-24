package nourl.mythicmetals.abilities;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.item.v1.ItemTooltipCallback;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.item.Item;
import net.minecraft.text.LiteralText;
import net.minecraft.text.MutableText;
import net.minecraft.text.Style;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;
import nourl.mythicmetals.armor.ArmorSet;
import nourl.mythicmetals.tools.MidasGoldSword;
import nourl.mythicmetals.tools.MythicTools;
import nourl.mythicmetals.tools.ToolSet;

import java.util.HashSet;
import java.util.Set;

/**
 * An ability is a container of items which have specific behaviour.
 * These are put into a set, and queried for later when needed.
 * Most of these are implemented in {@link nourl.mythicmetals.mixin.EnchantmentHelperMixin EnchantmentHelperMixin}
 * <br>
 * Other than that, this class also handles tooltips for the items.
 */
public class Ability {

    private final String tooltip;
    private final int level;
    private final Set<Item> items;
    private boolean showLevel = true;

    public Ability(String translationKey, int level) {
        this.tooltip = translationKey;
        this.level = level;
        this.items = new HashSet<>();
    }

    public Ability(String tranlsationKey, int level, boolean showLevel) {
        this.tooltip = tranlsationKey;
        this.level = level;
        this.items = new HashSet<>();
        this.showLevel = showLevel;
    }

    public int getLevel() {
        return level;
    }

    public Set<Item> getItems() {
        return items;
    }

    public void addItem(Item item, Style style) {
        items.add(item);
        if (FabricLoader.getInstance().getEnvironmentType().equals(EnvType.CLIENT))
            addTooltip(item, style);

    }

    public void addArmorSet(ArmorSet armorSet, Style style) {
        items.add(armorSet.getHelmet());
        items.add(armorSet.getChestplate());
        items.add(armorSet.getLeggings());
        items.add(armorSet.getBoots());
        if (FabricLoader.getInstance().getEnvironmentType().equals(EnvType.CLIENT)) {
            addTooltip(armorSet.getHelmet(), style);
            addTooltip(armorSet.getChestplate(), style);
            addTooltip(armorSet.getLeggings(), style);
            addTooltip(armorSet.getBoots(), style);
        }

    }

    public void addToolSet(ToolSet toolSet, Style style) {
        items.add(toolSet.getSword());
        items.add(toolSet.getAxe());
        items.add(toolSet.getHoe());
        items.add(toolSet.getPickaxe());
        items.add(toolSet.getShovel());
        if (FabricLoader.getInstance().getEnvironmentType().equals(EnvType.CLIENT)) {
            addTooltip(toolSet.getSword(), style);
            addTooltip(toolSet.getAxe(), style);
            addTooltip(toolSet.getHoe(), style);
            addTooltip(toolSet.getPickaxe(), style);
            addTooltip(toolSet.getShovel(), style);
        }
    }

    @Environment(EnvType.CLIENT)
    public void addTooltip(Item item, Style style) {
        ItemTooltipCallback.EVENT.register((stack, context, lines) -> {
            MutableText text = new LiteralText("");
            if (stack.getItem().equals(item)) {
                text.append(new TranslatableText("abilities.mythicmetals." + tooltip));
                text.setStyle(style);
                if (showLevel) {
                    text.append(" ").append(new TranslatableText("enchantment.level." + level));
                }
                if (lines.size() > 2) {
                    var enchantCount = stack.getEnchantments().size();
                    lines.add(enchantCount + 1, text);
                } else lines.add(text);
            }
        });
    }

    @Environment(EnvType.CLIENT)
    public static void initMidasGoldTooltip() {
        ItemTooltipCallback.EVENT.register((stack, context, lines) -> {
            if (stack.getItem().equals(MythicTools.MIDAS_GOLD_SWORD) || stack.getItem().equals(MythicTools.GILDED_MIDAS_GOLD_SWORD)) {

                int lineIndex = 1;

                if (lines.size() > 2) {
                    var enchantCount = stack.getEnchantments().size();
                   lineIndex = enchantCount + 1;
                }

                int goldCount = stack.get(MidasGoldSword.GOLD_FOLDED);

                if (goldCount < 64) {
                    lines.add(lineIndex, new TranslatableText("tooltip.midas_gold.level.0").formatted(Formatting.GOLD));
                    lines.add(lineIndex + 1, new LiteralText(goldCount + " / 64").formatted(Formatting.GOLD));
                }
                if (goldCount >= 64 && goldCount < 128) {
                    lines.add(lineIndex, new TranslatableText("tooltip.midas_gold.level.1").formatted(Formatting.GOLD));
                    lines.add(lineIndex + 1, new LiteralText(goldCount + " / 128").formatted(Formatting.GOLD));
                }
                if (goldCount >= 128 && goldCount < 192) {
                    lines.add(lineIndex, new TranslatableText("tooltip.midas_gold.level.2").formatted(Formatting.GOLD));
                    lines.add(lineIndex + 1, new LiteralText(goldCount + " / 192").formatted(Formatting.GOLD));
                }
                if (goldCount >= 192 && goldCount < 256) {
                    lines.add(lineIndex, new TranslatableText("tooltip.midas_gold.level.3").formatted(Formatting.GOLD));
                    lines.add(lineIndex + 1, new LiteralText(goldCount + " / 256").formatted(Formatting.GOLD));
                }
                if (goldCount >= 256 && goldCount < 320) {
                    lines.add(lineIndex, new TranslatableText("tooltip.midas_gold.level.4").formatted(Formatting.GOLD));
                    lines.add(lineIndex + 1, new LiteralText(goldCount + " / 320").formatted(Formatting.GOLD));
                }
                if (goldCount >= 320 && goldCount < 640) {
                    lines.add(lineIndex, new TranslatableText("tooltip.midas_gold.level.5").formatted(Formatting.GOLD));
                    lines.add(lineIndex + 1, new LiteralText(goldCount + " / 320").formatted(Formatting.GOLD));
                }
                if (goldCount >= 640) {
                    lines.add(lineIndex, new TranslatableText("tooltip.midas_gold.level.99").formatted(Formatting.GOLD));
                    lines.add(lineIndex + 1, new LiteralText(goldCount + " / 640").formatted(Formatting.GOLD));
                }
            }
        });
    }
}
