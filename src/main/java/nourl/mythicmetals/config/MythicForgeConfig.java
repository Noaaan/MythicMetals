package nourl.mythicmetals.config;

import wraith.alloy_forgery.MaterialWorth;
import wraith.alloy_forgery.RecipeOutput;
import wraith.alloy_forgery.api.ForgeRecipes;
import wraith.alloy_forgery.api.MaterialWorths;
import wraith.alloy_forgery.utils.Config;

import java.util.HashMap;

public class MythicForgeConfig {

    public static void createAlloys() {
        Config.init();
        createMaterialWorths();
        createAlloyRecipes();
        createOreRecipes();
    }

    public static void createMaterialWorths() {
        // Creates new materials. They are shaped so:
        // First line is the material name, also defines a new hashmap for its contents
        // Following lines are what is considered to be the material, their worth, and whether they can be returned upon providing too much of the material.
        MaterialWorths.addMaterials(new HashMap<String, HashMap<String, MaterialWorth>>(){{
            put("copper", new HashMap<String, MaterialWorth>(){{
                put("#c:copper_blocks", new MaterialWorth(81, true));
                put("#c:copper_ingots", new MaterialWorth(9, true));
                put("#c:copper_dusts", new MaterialWorth(9, false));
                put("#c:copper_nuggets", new MaterialWorth(1, true));
            }});
            put("tin", new HashMap<String, MaterialWorth>(){{
                put("#c:tin_blocks", new MaterialWorth(81, true));
                put("#c:tin_ingots", new MaterialWorth(9, true));
                put("#c:tin_dusts", new MaterialWorth(9, false));
                put("#c:tin_nuggets", new MaterialWorth(1, true));
            }});
            put("brass", new HashMap<String, MaterialWorth>(){{
                put("#c:brass_blocks", new MaterialWorth( 81, true));
                put("#c:brass_ingots", new MaterialWorth( 9, true));
                put("#c:brass_dusts", new MaterialWorth( 9, false));
                put("#c:brass_nuggets", new MaterialWorth( 1, true));
            }});
            put("zinc", new HashMap<String, MaterialWorth>(){{
                put("#c:zinc_blocks", new MaterialWorth(81, true));
                put("#c:zinc_ingots", new MaterialWorth(9, true));
                put("#c:zinc_dusts", new MaterialWorth(9, false));
                put("#c:zinc_nuggets", new MaterialWorth(1, true));
            }});
            put("silver", new HashMap<String, MaterialWorth>(){{
                put("#c:silver_blocks", new MaterialWorth(81, true));
                put("#c:silver_ingots", new MaterialWorth(9, true));
                put("#c:silver_dusts",  new MaterialWorth(9, false));
                put("#c:silver_nuggets", new MaterialWorth(1, true));
            }});
            put("electrum", new HashMap<String, MaterialWorth>(){{
                put("#c:electrum_blocks", new MaterialWorth(81, true));
                put("#c:electrum_ingots", new MaterialWorth(9, true));
                put("#c:electrum_dusts", new MaterialWorth(9, false));
                put("#c:electrum_nuggets", new MaterialWorth(1, true));
            }});
        }}, false);
    }

    public static void createAlloyRecipes() {
        // Output is shaped like this:
        // Item name, item amount, heat required, smeltery tier required
        ForgeRecipes.addRecipes(new HashMap<HashMap<String, Integer>, RecipeOutput>(){{
            put(new HashMap<String, Integer>(){{
                put("copper", 18);
                put("tin", 9);
            }}, new RecipeOutput("#c:bronze_ingots", 2, 10, 1));

            put(new HashMap<String, Integer>(){{
                put("copper", 18);
                put("zinc", 9);
            }}, new RecipeOutput("#c:brass_ingots", 2, 10, 1));

            put(new HashMap<String, Integer>(){{
                put("silver", 9);
                put("gold", 9);
            }}, new RecipeOutput("#c:electrum_ingots", 1, 8, 1));
        }}, false);
    }

    private static void createOreRecipes() {
        ForgeRecipes.addRecipes(new HashMap<HashMap<String, Integer>, RecipeOutput>(){{
            put(new HashMap<String, Integer>(){{
                put("#c:copper_ores", 2);
            }}, new RecipeOutput("#c:copper_ingots", 3, 8, 1));
        }}, false);
    }

}
