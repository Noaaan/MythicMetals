package com.mythicmetals.command;


import com.mythicmetals.api.v2.BlockSet;
import com.mythicmetals.armor.ArmorSet;
import com.mythicmetals.config.OreConfig;
import com.mythicmetals.item.tools.MythicTools;
import com.mythicmetals.item.tools.ToolSet;
import com.mythicmetals.misc.StringUtilsAtHome;
import net.minecraft.util.Util;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.locale.Language;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.equipment.ArmorType;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

/**
 * Helper class that contains all the page layouts for the Mythic Metals Wiki
 */
public class WikiExporter {
    private WikiExporter() {}

    static final String ADMONITION_HEADER = """
        !!! info inline end ""
            <center class=tooltip>
        """;

    static final String ADMONIITION_TOP_IMAGE = """
            <h3>**%s**</h3>
            ![WRITE ALT TEXT HERE](%s)<br>
        """;

    static final String ADMONITION_TOOL_IMAGE = """
            <h3>**%s**</h3>
            ![WRITE ALT TEXT HERE]%s<br>
        """;

    static final String RECIPE_SCALING = "{ .sized-image style=\"--image-width: 40%;\" }";
    static final String ICON_SCALE = "{ .sized-image style=\"--image-width: 8%;\" }";

    public static String computeArmorSet(ArmorSet armorSet) {
        return createArmorTemplate(
            armorSet.getTitlecaseName(),
            armorSet.getName(),
            computeArmorAdmonition(armorSet),
            computeArmorRecipes(armorSet)
        );
    }

    public static String computeToolset(String name, ToolSet toolSet) {
        return createToolTemplate(
            name,
            computeToolAdmonition(toolSet),
            computeToolRecipes(toolSet)
        );
    }

    public static String createOreTemplate(String oreName, BlockSet blockSet, OreConfig oreConfig) {
        return createOreTemplate(
            oreName,
            computeOreAdmonition(blockSet, oreConfig)
        );
    }

    static String createOreTemplate(String name, String admonition) {
        return """
            
            ---
            title: %s
            project: mythicmetals
            summary: A summary of %s, their history, and where to find them.
            ---
            
            %s
            
            ## Generation
            
            ## Usages
            
            ## Trivia
            
            ## History
            
            """.formatted(name, name, admonition);
    }

    static String createToolTemplate(String name, String admonition, String recipes) {
        return """
            
            ---
            title: %s Tools
            project: mythicmetals
            summary: A summary of %s Tools, their abilities, their history, and how to craft them.
            ---
            
            %s
            ## Obtaining
            
            ### Crafting
            
            Tools can be crafted from [TODO - LINK TO MATERIAL.]
            
            %s
            ## Usages
            
            TODO - Remove if irrelevant, for example if it does not craft into anything
            
            ## Trivia
            
            ## History
            
            """.formatted(name, name, admonition, recipes);
    }

    static String createArmorTemplate(String name, String lowercaseName, String admonition, String recipes) {
        return """
            ---
            title: %s Armor
            project: mythicmetals
            summary: The armor does protect you (TODO).
            armoricon: %s
            ---
            
            %s
            
            ## Obtaining
            
            ### Crafting
            
            This armor can be crafted from [TODO - LINK TO MATERIAL.]
            
            %s
            ## Usages
            
            TODO - Remove if irrelevant, for example if it does not craft into anything
            
            ## Trivia
            
            ## History
            
            """.formatted(name, lowercaseName + ".png", admonition, recipes);
    }

    static String computeToolAdmonition(ToolSet toolSet) {
        var output = new StringBuilder();
        var translationStorage = Language.getInstance();
        // tool stats are really annoying to get
        Deque<Integer> damageDeque = new ArrayDeque<>(Arrays.stream(MythicTools.DEFAULT_DAMAGE).boxed().toList());
        var atkSpd = new ArrayDeque<>(toolSet.getAttackSpeed());
        output.append(ADMONITION_HEADER);
        toolSet.get().forEach(tool -> {
            String id = BuiltInRegistries.ITEM.getKey(tool).getPath();
            output.append(ADMONITION_TOOL_IMAGE.formatted(
                translationStorage.getOrDefault(tool.getDescriptionId()),
                "(../../assets/mythicmetals/%s.png)".formatted(id) + RECIPE_SCALING
            ));
            output.append("""
                    +%s Attack Damage, %s Attack Speed<br>
                    %s Durability<br>
                """.formatted(
                toolSet.getMaterial().attackDamageBonus() + damageDeque.pop() + 1,
                BigDecimal.valueOf(atkSpd.pop()).setScale(1, RoundingMode.HALF_UP).toPlainString(),
                tool.getDefaultInstance().getMaxDamage()
            ));
        });

        return output.toString();
    }

    static String computeToolRecipes(ToolSet toolSet) {
        StringBuilder output = new StringBuilder();
        for (Item tool : toolSet.get()) {
            String id = BuiltInRegistries.ITEM.getKey(tool).getPath();
            String name = StringUtilsAtHome.toTitleCase(id.replace('_', ' '));
            output.append(("""
                    ![Image of the recipe for %s](../../assets/mythicmetals/recipes/tools/%s.png)%s
                    """
                ).formatted(name, id, RECIPE_SCALING));
        }
        return output.toString();
    }

    static String computeOreAdmonition(BlockSet blockSet, OreConfig oreConfig) {
        var translationStorage = Language.getInstance();
        var output = new StringBuilder();

        output.append(ADMONITION_HEADER);
        output.append(ADMONIITION_TOP_IMAGE.formatted(
            translationStorage.getOrDefault(blockSet.ore().getDescriptionId()),
            "../../assets/mythicmetals/%s.png".formatted(blockSet.name() + "_ore")
        ));

        blockSet.oreVariants().forEach((variantName, block) -> {
            String variantOreName = translationStorage.getOrDefault(block.getB().getDescriptionId());
            output.append(ADMONIITION_TOP_IMAGE.formatted(
                variantOreName,
                "../../assets/mythicmetals/" + variantName + "_" + blockSet.name() + "_ore.png"
            ));
        });

        var oreStatsTemplate = """
                ---
                **Mining Level**: X (Y for variant)<br>
                **Max Vein Size**: %s<br>
                **Attempts Per Chunk**: %s<br>
                **Spawn Range**: %s to %s<br>
                **Discard Chance**: %s<br>
            """.formatted(
            oreConfig.veinSize,
            oreConfig.perChunk,
            oreConfig.bottom + (oreConfig.offset ? "(Offset)" : ""),
            oreConfig.top + (oreConfig.trapezoid ? " (Triangle Range)" : ""),
            oreConfig.discardChance == 0 ? "Never discarded" : oreConfig.discardChance * 100 + "%"
        );

        output.append(oreStatsTemplate);
        return output.toString();
    }

    static String computeArmorAdmonition(ArmorSet armorSet) {
        var translationStorage = Language.getInstance();
        var output = new StringBuilder();
        output.append(ADMONITION_HEADER);
        final String armorTitleName = armorSet.getTitlecaseName();
        var armorModelImage = "../../assets/armor-models/256/%s".formatted(armorSet.getMaterialId() + "_256.png");
        output.append(ADMONIITION_TOP_IMAGE.formatted(armorTitleName + " Armor", armorModelImage));

        var map = Util.make(new HashMap<Item, Integer>(), itemMap -> {
            itemMap.put(armorSet.getHelmet(), armorSet.getMaterial().defense().get(ArmorType.HELMET));
            itemMap.put(armorSet.getChestplate(), armorSet.getMaterial().defense().get(ArmorType.CHESTPLATE));
            itemMap.put(armorSet.getLeggings(), armorSet.getMaterial().defense().get(ArmorType.LEGGINGS));
            itemMap.put(armorSet.getBoots(), armorSet.getMaterial().defense().get(ArmorType.BOOTS));
        });

        for (var armor : map.entrySet()) {
            var itemId = BuiltInRegistries.ITEM.getKey(armor.getKey());
            String name = translationStorage.getOrDefault(Util.makeDescriptionId("item", itemId));
            String id = itemId.getPath();

            var material = armorSet.getMaterial();
            int protection = armor.getValue();

            output.append("\n");
            output.append("\t<h4>**").append(name).append("**</h4>").append("\n");
            output.append("\t![Image of %s](../../assets/mythicmetals/%s.png)".formatted(name, id)).append(RECIPE_SCALING).append("<br>");
            for (int i = 1; i < protection; i = i + 2) {
                output.append("\t![armor](../../assets/icon/full_armor_icon.png)").append(ICON_SCALE).append("\n");
            }
            if ((protection & 1) == 1) {
                output.append("\t![armor](../../assets/icon/half_armor_icon.png)").append(ICON_SCALE).append("\n");
            }
            output.append("\t<br>\n");
            // +5 Armor, +2 Toughness
            output.append("\t+%s Armor".formatted(protection));
            if (material.toughness() > 0) {
                output.append(", +%s Toughness".formatted(material.toughness()));
            }
            output.append("<br>\n");
            var kbRes = material.knockbackResistance();
            if (kbRes > 0) {
                output.append("\t+%s Knockback Resistance".formatted(kbRes)).append("<br>\n");
            }
            // 350 Durability
            output.append("\t%s Durability".formatted(armor.getKey().getDefaultInstance().getMaxDamage())).append("<br>\n");
        }
        return output.toString();
    }

    static String computeArmorRecipes(ArmorSet armorSet) {
        StringBuilder output = new StringBuilder();
        for (Item armor : armorSet.getArmorItems()) {
            String id = BuiltInRegistries.ITEM.getKey(armor).getPath();
            String name = StringUtilsAtHome.toTitleCase(id.replace('_', ' '));
            output.append(("""
                    ![Image of the recipe for %s](../../assets/mythicmetals/recipes/armor/%s.png)%s
                    """
                ).formatted(name, id, RECIPE_SCALING));
        }
        return output.toString();
    }
}
