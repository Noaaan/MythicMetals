package com.mythicmetals.misc.wiki;

import com.mythicmetals.api.v2.*;
import com.mythicmetals.config.OreConfig;
import com.mythicmetals.item.MythicItemAttributes;
import com.mythicmetals.misc.StringUtilsAtHome;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.locale.Language;
import net.minecraft.util.Util;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.equipment.ArmorType;
import java.util.HashMap;

/**
 * Helper class that contains all the page layouts for the Mythic Metals Wiki
 */
public class WikiExporter {
    private WikiExporter() {
    }

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
        var stats = WikiHelper.TOOL_STAT_MAP.get(toolSet.getName());

        output.append(ADMONITION_HEADER);
        singleToolAdmonition(output, toolSet.getSword(), stats.getAttackDamage(MythicItemAttributes.ToolType.SWORD), stats.attackSpeeds().sword);
        singleToolAdmonition(output, toolSet.getPickaxe(), stats.getAttackDamage(MythicItemAttributes.ToolType.PICKAXE), stats.attackSpeeds().pickaxe);
        singleToolAdmonition(output, toolSet.getAxe(), stats.getAttackDamage(MythicItemAttributes.ToolType.AXE), stats.attackSpeeds().axe);
        singleToolAdmonition(output, toolSet.getShovel(), stats.getAttackDamage(MythicItemAttributes.ToolType.SHOVEL), stats.attackSpeeds().shovel);
        singleToolAdmonition(output, toolSet.getHoe(), stats.getAttackDamage(MythicItemAttributes.ToolType.HOE), stats.attackSpeeds().hoe);

        return output.toString();
    }

    static void singleToolAdmonition(StringBuilder output, Item tool, double damage, double attackSpeed) {
        String id = BuiltInRegistries.ITEM.getKey(tool).getPath();
        // image
        output.append(ADMONITION_TOOL_IMAGE.formatted(
            Language.getInstance().getOrDefault(tool.getDescriptionId()),
            "(../../assets/mythicmetals/%s.png)".formatted(id) + RECIPE_SCALING
        ));
        // stat block
        output.append("""
                +%s Attack Damage, %s Attack Speed<br>
                %s Durability<br>
            """.formatted(
            damage + 1,
            attackSpeed,
            tool.getDefaultInstance().getMaxDamage()
        ));
    }

    static String computeToolRecipes(ToolSet tools) {
        var stringBuilder = new StringBuilder();
        computeToolRecipe(stringBuilder, tools.getSword());
        computeToolRecipe(stringBuilder, tools.getAxe());
        computeToolRecipe(stringBuilder, tools.getPickaxe());
        computeToolRecipe(stringBuilder, tools.getShovel());
        computeToolRecipe(stringBuilder, tools.getHoe());
        return stringBuilder.toString();
    }

    static void computeToolRecipe(StringBuilder sb, Item tool) {
        String id = BuiltInRegistries.ITEM.getKey(tool).getPath();
        String name = StringUtilsAtHome.toTitleCase(id.replace('_', ' '));
        sb.append("""
            ![Image of the recipe for %s](../../assets/mythicmetals/recipes/tools/%s.png)%s
            """
            .formatted(name, id, RECIPE_SCALING));
    }

    static String computeOreAdmonition(BlockSet blockSet, OreConfig oreConfig) {
        var translationStorage = Language.getInstance();
        var output = new StringBuilder();

        output.append(ADMONITION_HEADER);
        output.append(ADMONIITION_TOP_IMAGE.formatted(
            translationStorage.getOrDefault(blockSet.ore().block().getDescriptionId()),
            "../../assets/mythicmetals/%s.png".formatted(blockSet.name() + "_ore")
        ));

        blockSet.oreVariants().forEach((variantName, blockRecord) -> {
            String variantOreName = translationStorage.getOrDefault(blockRecord.block().getDescriptionId());
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
        var armorMaterial = armorSet.getArmorMaterial();
        output.append(ADMONITION_HEADER);
        final String armorTitleName = armorSet.getTitlecaseName();
        var armorModelImage = "../../assets/armor-models/256/%s".formatted(armorSet.getName() + "_256.png");
        output.append(ADMONIITION_TOP_IMAGE.formatted(armorTitleName + " Armor", armorModelImage));

        var map = Util.make(new HashMap<Item, Integer>(), itemMap -> {
            itemMap.put(armorSet.getHelmet(), armorMaterial.defense().get(ArmorType.HELMET));
            itemMap.put(armorSet.getChestplate(), armorMaterial.defense().get(ArmorType.CHESTPLATE));
            itemMap.put(armorSet.getLeggings(), armorMaterial.defense().get(ArmorType.LEGGINGS));
            itemMap.put(armorSet.getBoots(), armorMaterial.defense().get(ArmorType.BOOTS));
        });

        for (var armor : map.entrySet()) {
            var itemId = BuiltInRegistries.ITEM.getKey(armor.getKey());
            String name = translationStorage.getOrDefault(Util.makeDescriptionId("item", itemId));
            String id = itemId.getPath();

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
            if (armorMaterial.toughness() > 0) {
                output.append(", +%s Toughness".formatted(armorMaterial.toughness()));
            }
            output.append("<br>\n");
            var kbRes = armorMaterial.knockbackResistance();
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
        for (Item armor : armorSet.getItems()) {
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
