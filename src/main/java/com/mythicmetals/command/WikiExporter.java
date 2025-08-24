package com.mythicmetals.command;

import com.mythicmetals.block.BlockSet;
import com.mythicmetals.config.OreConfig;
import com.mythicmetals.item.tools.MythicTools;
import com.mythicmetals.item.tools.ToolSet;
import com.mythicmetals.misc.StringUtilsAtHome;
import net.minecraft.client.resource.language.TranslationStorage;
import net.minecraft.item.ToolItem;
import net.minecraft.registry.Registries;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

/**
 * Helper class that contains all the page layouts for the Mythic Metals Wiki
 */
public class WikiExporter {
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

    public static String computeToolset(ToolSet toolSet) {
        return createToolTemplate(
            toolSet.getTitlecaseName(),
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

    static String computeToolAdmonition(ToolSet toolSet) {
        var output = new StringBuilder();
        var translationStorage = TranslationStorage.getInstance();
        // tool stats are really annoying to get
        Deque<Integer> damageDeque = new ArrayDeque<>(Arrays.stream(MythicTools.DEFAULT_DAMAGE).boxed().toList());
        Stack<Float> atkSpd = new Stack<>();
        atkSpd.addAll(toolSet.getAttackSpeed());
        output.append(ADMONITION_HEADER);
        toolSet.get().forEach(tool -> {
            String id = Registries.ITEM.getId(tool).getPath();
            output.append(ADMONITION_TOOL_IMAGE.formatted(
                translationStorage.get(tool.getTranslationKey()),
                "(../../assets/mythicmetals/%s.png)".formatted(id) + RECIPE_SCALING
            ));
            output.append("""
                    +%s Attack Damage, %s Attack Speed<br>
                    %s Durability<br>
                """.formatted(
                tool.getMaterial().getAttackDamage() + damageDeque.pop() + 1,
                BigDecimal.valueOf(atkSpd.pop()).setScale(1, RoundingMode.HALF_UP).toPlainString(),
                tool.getDefaultStack().getMaxDamage()
            ));
        });

        return output.toString();
    }

    static String computeToolRecipes(ToolSet toolSet) {
        StringBuilder output = new StringBuilder();
        for (ToolItem tool : toolSet.get()) {
            String id = Registries.ITEM.getId(tool).getPath();
            String name = StringUtilsAtHome.toTitleCase(id.replace('_', ' '));
            output.append(("""
                    ![Image of the recipe for %s](../../assets/mythicmetals/recipes/tools/%s.png)%s
                    """
                ).formatted(name, id, RECIPE_SCALING));
        }
        return output.toString();
    }

    static String computeOreAdmonition(BlockSet blockSet, OreConfig oreConfig) {
        var translationStorage = TranslationStorage.getInstance();
        var output = new StringBuilder();

        output.append(ADMONITION_HEADER);
        output.append(ADMONIITION_TOP_IMAGE.formatted(
            translationStorage.get(blockSet.getOre().getTranslationKey()),
            "../../assets/mythicmetals/%s.png".formatted(blockSet.getName() + "_ore")
        ));

        blockSet.getOreVariantsMap().forEach((variantName, block) -> {
            String variantOreName = translationStorage.get(block.getTranslationKey());
            output.append(ADMONIITION_TOP_IMAGE.formatted(
                variantOreName,
                "../../assets/mythicmetals/" + variantName + "_" + blockSet.getName() + "_ore.png"
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
}
