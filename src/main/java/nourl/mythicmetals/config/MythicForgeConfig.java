package nourl.mythicmetals.config;

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
        MaterialWorths.addMaterials(new HashMap<String, HashMap<String, Integer>>(){{
            put("copper", new HashMap<String, Integer>(){{
                put("#c:copper_blocks", 81);
                put("#c:copper_ingots", 9);
                put("#c:copper_dusts", 9);
                put("#c:copper_nuggets", 1);
            }});
            put("tin", new HashMap<String, Integer>(){{
                put("#c:tin_blocks", 81);
                put("#c:tin_ingots", 9);
                put("#c:tin_dusts", 9);
                put("#c:tin_nuggets", 1);
            }});
            put("brass", new HashMap<String, Integer>(){{
                put("#c:brass_blocks", 81);
                put("#c:brass_ingots", 9);
                put("#c:brass_dusts", 9);
                put("#c:brass_nuggets", 1);
            }});
            put("zinc", new HashMap<String, Integer>(){{
                put("#c:zinc_blocks", 81);
                put("#c:zinc_ingots", 9);
                put("#c:zinc_dusts", 9);
                put("#c:zinc_nuggets", 1);
            }});
            put("silver", new HashMap<String, Integer>(){{
                put("#c:silver_blocks", 81);
                put("#c:silver_ingots", 9);
                put("#c:silver_dusts", 9);
                put("#c:silver_nuggets", 1);
            }});
            put("electrum", new HashMap<String, Integer>(){{
                put("#c:electrum_blocks", 81);
                put("#c:electrum_ingots", 9);
                put("#c:electrum_dusts", 9);
                put("#c:electrum_nuggets", 1);
            }});
        }}, false);
    }

    public static void createAlloyRecipes() {
        ForgeRecipes.addRecipes(new HashMap<HashMap<String, Integer>, RecipeOutput>(){{
            put(new HashMap<String, Integer>(){{
                put("copper", 27);
                put("tin", 9);
            }}, new RecipeOutput("#c:bronze_ingots", 2, 10, 1));
            put(new HashMap<String, Integer>(){{
                put("copper", 27);
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
                put("#c:copper_ores", 3);
            }}, new RecipeOutput("#c:copper_ingots", 2, 8, 1));
        }}, false);
    }

}
