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
        MaterialWorths.addMaterials(new HashMap<>() {{
            put("copper", new HashMap<>() {{
                put("#c:copper_blocks", new MaterialWorth(81, true));
                put("#c:copper_ingots", new MaterialWorth(9, true));
                put("#c:copper_ores", new MaterialWorth(9, false));
                put("#c:copper_nuggets", new MaterialWorth(1, true));
            }});
            put("tin", new HashMap<>() {{
                put("#c:tin_blocks", new MaterialWorth(81, true));
                put("#c:tin_ingots", new MaterialWorth(9, true));
                put("#c:tin_ores", new MaterialWorth(9, false));
                put("#c:tin_nuggets", new MaterialWorth(1, true));
            }});
            put("brass", new HashMap<>() {{
                put("#c:brass_blocks", new MaterialWorth(81, true));
                put("#c:brass_ingots", new MaterialWorth(9, true));
                put("#c:brass_nuggets", new MaterialWorth(1, true));
            }});
            put("zinc", new HashMap<>() {{
                put("#c:zinc_blocks", new MaterialWorth(81, true));
                put("#c:zinc_ingots", new MaterialWorth(9, true));
                put("#c:zinc_ores", new MaterialWorth(9, false));
                put("#c:zinc_nuggets", new MaterialWorth(1, true));
            }});
            put("silver", new HashMap<>() {{
                put("#c:silver_blocks", new MaterialWorth(81, true));
                put("#c:silver_ingots", new MaterialWorth(9, true));
                put("#c:silver_ores", new MaterialWorth(9, false));
                put("#c:silver_nuggets", new MaterialWorth(1, true));
            }});
            put("electrum", new HashMap<>() {{
                put("#c:electrum_blocks", new MaterialWorth(81, true));
                put("#c:electrum_ingots", new MaterialWorth(9, true));
                put("#c:electrum_nuggets", new MaterialWorth(1, true));
            }});
            put("gold", new HashMap<>() {{
                put("#c:gold_blocks", new MaterialWorth(81, true));
                put("#c:gold_ingots", new MaterialWorth(9, true));
                put("#c:gold_ores", new MaterialWorth(9, false));
                put("#c:gold_nuggets", new MaterialWorth(9, true));
            }});
            put("adamantite", new HashMap<>() {{
                put("#c:adamantite_blocks", new MaterialWorth(81, true));
                put("#c:adamantite_ingots", new MaterialWorth(9, true));
                put("#c:adamantite_ores", new MaterialWorth(9, false));
                put("#c:adamantite_nuggets", new MaterialWorth(1, true));
            }});
            put("aetherium", new HashMap<>() {{
                put("#mythicmetals:aetherium_blocks", new MaterialWorth(81, true));
                put("#mythicmetals:aetherium_ingots", new MaterialWorth(9, true));
                put("#mythicmetals:aetherium_ores", new MaterialWorth(9, false));
                put("#mythicmetals:aetherium_nuggets", new MaterialWorth(1, true));
            }});
            put("aquarium", new HashMap<>() {{
                put("#mythicmetals:aquarium_blocks", new MaterialWorth(81, true));
                put("#mythicmetals:aquarium_ingots", new MaterialWorth(9, true));
                put("#mythicmetals:aquarium_ores", new MaterialWorth(9, false));
                put("#mythicmetals:aquarium_nuggets", new MaterialWorth(1, true));
            }});
            put("argonium", new HashMap<>() {{
                put("#mythicmetals:argonium_blocks", new MaterialWorth(81, true));
                put("#mythicmetals:argonium_ingots", new MaterialWorth(9, true));
                put("#mythicmetals:argonium_nuggets", new MaterialWorth(1, true));
            }});
            put("banglum", new HashMap<>() {{
                put("#mythicmetals:banglum_blocks", new MaterialWorth(81, true));
                put("#mythicmetals:banglum_ingots", new MaterialWorth(9, true));
                put("#mythicmetals:banglum_ores", new MaterialWorth(9, false));
                put("#mythicmetals:banglum_nuggets", new MaterialWorth(1, true));
            }});
            put("carmot", new HashMap<>() {{
                put("#mythicmetals:carmot_blocks", new MaterialWorth(81, true));
                put("#mythicmetals:carmot_ingots", new MaterialWorth(9, true));
                put("#mythicmetals:carmot_ores", new MaterialWorth(9, false));
                put("#mythicmetals:carmot_nuggets", new MaterialWorth(1, true));
            }});
            put("celestium", new HashMap<>() {{
                put("#mythicmetals:celestium_blocks", new MaterialWorth(81, true));
                put("#mythicmetals:celestium_ingots", new MaterialWorth(9, true));
                put("#mythicmetals:celestium_nuggets", new MaterialWorth(1, true));
            }});
            put("discordium", new HashMap<>() {{
                put("#mythicmetals:discordium_blocks", new MaterialWorth(81, true));
                put("#mythicmetals:discordium_ingots", new MaterialWorth(9, true));
                put("#mythicmetals:discordium_nuggets", new MaterialWorth(1, true));
            }});
            put("durasteel", new HashMap<>() {{
                put("#mythicmetals:durasteel_blocks", new MaterialWorth(81, true));
                put("#mythicmetals:durasteel_ingots", new MaterialWorth(9, true));
                put("#mythicmetals:durasteel_nuggets", new MaterialWorth(1, true));
            }});
            put("etherite", new HashMap<>() {{
                put("#mythicmetals:etherite_blocks", new MaterialWorth(81, true));
                put("#mythicmetals:etherite_ingots", new MaterialWorth(9, true));
                put("#mythicmetals:etherite_nuggets", new MaterialWorth(1, true));
            }});
            put("hallowed", new HashMap<>() {{
                put("#mythicmetals:hallowed_blocks", new MaterialWorth(81, true));
                put("#mythicmetals:hallowed_ingots", new MaterialWorth(9, true));
                put("#mythicmetals:hallowed_nuggets", new MaterialWorth(1, true));
            }});
            put("kyber", new HashMap<>() {{
                put("#mythicmetals:kyber_blocks", new MaterialWorth(81, true));
                put("#mythicmetals:kyber_ingots", new MaterialWorth(9, true));
                put("#mythicmetals:kyber_ores", new MaterialWorth(9, false));
                put("#mythicmetals:kyber_nuggets", new MaterialWorth(1, true));
            }});
            put("manganese", new HashMap<>() {{
                put("#c:manganese_blocks", new MaterialWorth(81, true));
                put("#c:manganese_ingots", new MaterialWorth(9, true));
                put("#c:manganese_ores", new MaterialWorth(9, false));
                put("#c:manganese_nuggets", new MaterialWorth(1, true));
            }});
            put("metallurgium", new HashMap<>() {{
                put("#mythicmetals:metallurgium_blocks", new MaterialWorth(81, true));
                put("#mythicmetals:metallurgium_ingots", new MaterialWorth(9, true));
                put("#mythicmetals:metallurgium_nuggets", new MaterialWorth(1, true));
            }});
            put("midas_gold", new HashMap<>() {{
                put("#mythicmetals:midas_gold_blocks", new MaterialWorth(81, true));
                put("#mythicmetals:midas_gold_ingots", new MaterialWorth(9, true));
                put("#mythicmetals:midas_gold_ores", new MaterialWorth(9, false));
                put("#mythicmetals:midas_gold_nuggets", new MaterialWorth(1, true));
            }});
            put("mythril", new HashMap<>() {{
                put("#c:mythril_blocks", new MaterialWorth(81, true));
                put("#c:mythril_ingots", new MaterialWorth(9, true));
                put("#c:mythril_ores", new MaterialWorth(9, false));
                put("#c:mythril_nuggets", new MaterialWorth(1, true));
            }});
            put("orichalcum", new HashMap<>() {{
                put("#c:orichalcum_blocks", new MaterialWorth(81, true));
                put("#c:orichalcum_ingots", new MaterialWorth(9, true));
                put("#c:orichalcum_ores", new MaterialWorth(9, false));
                put("#c:orichalcum_nuggets", new MaterialWorth(1, true));
            }});
            put("osmium", new HashMap<>() {{
                put("#c:osmium_blocks", new MaterialWorth(81, true));
                put("#c:osmium_ingots", new MaterialWorth(9, true));
                put("#c:osmium_ores", new MaterialWorth(9, false));
                put("#c:osmium_nuggets", new MaterialWorth(1, true));
            }});
            put("platinum", new HashMap<>() {{
                put("#c:platinum_blocks", new MaterialWorth(81, true));
                put("#c:platinum_ingots", new MaterialWorth(9, true));
                put("#c:platinum_ores", new MaterialWorth(9, false));
                put("#c:platinum_nuggets", new MaterialWorth(1, true));
            }});
            put("prometheum", new HashMap<>() {{
                put("#mythicmetals:prometheum_blocks", new MaterialWorth(81, true));
                put("#mythicmetals:prometheum_ingots", new MaterialWorth(9, true));
                put("#mythicmetals:prometheum_ores", new MaterialWorth(9, false));
                put("#mythicmetals:prometheum_nuggets", new MaterialWorth(1, true));
            }});
            put("quadrillum", new HashMap<>() {{
                put("#mythicmetals:quadrillum_blocks", new MaterialWorth(81, true));
                put("#mythicmetals:quadrillum_ingots", new MaterialWorth(9, true));
                put("#mythicmetals:quadrillum_ores", new MaterialWorth(9, false));
                put("#mythicmetals:quadrillum_nuggets", new MaterialWorth(1, true));
            }});
            put("quicksilver", new HashMap<>() {{
                put("#c:quicksilver_blocks", new MaterialWorth(81, true));
                put("#c:quicksilver_ingots", new MaterialWorth(9, true));
                put("#c:quicksilver_nuggets", new MaterialWorth(1, true));
            }});
            put("runite", new HashMap<>() {{
                put("#mythicmetals:runite_blocks", new MaterialWorth(81, true));
                put("#mythicmetals:runite_ingots", new MaterialWorth(9, true));
                put("#mythicmetals:runite_ores", new MaterialWorth(9, false));
                put("#mythicmetals:runite_nuggets", new MaterialWorth(1, true));
            }});
            put("starrite", new HashMap<>() {{
                put("#mythicmetals:starrite_blocks", new MaterialWorth(81, true));
                put("#mythicmetals:starrite_ingots", new MaterialWorth(9, true));
                put("#mythicmetals:starrite_ores", new MaterialWorth(9, false));
                put("#mythicmetals:starrite_nuggets", new MaterialWorth(1, true));
            }});
            put("steel", new HashMap<>() {{
                put("#c:steel_blocks", new MaterialWorth(81, true));
                put("#c:steel_ingots", new MaterialWorth(9, true));
                put("#c:steel_nuggets", new MaterialWorth(1, true));
            }});
            put("stormyx", new HashMap<>() {{
                put("#mythicmetals:stormyx_blocks", new MaterialWorth(81, true));
                put("#mythicmetals:stormyx_ingots", new MaterialWorth(9, true));
                put("#mythicmetals:stormyx_ores", new MaterialWorth(9, false));
                put("#mythicmetals:stormyx_nuggets", new MaterialWorth(1, true));
            }});
            put("tantalite", new HashMap<>() {{
                put("#c:tantalite_blocks", new MaterialWorth(81, true));
                put("#c:tantalite_ingots", new MaterialWorth(9, true));
                put("#c:tantalite_ores", new MaterialWorth(9, false));
                put("#c:tantalite_nuggets", new MaterialWorth(1, true));
            }});
            put("truesilver", new HashMap<>() {{
                put("#mythicmetals:truesilver_blocks", new MaterialWorth(81, true));
                put("#mythicmetals:truesilver_ingots", new MaterialWorth(9, true));
                put("#mythicmetals:truesilver_ores", new MaterialWorth(9, false));
                put("#mythicmetals:truesilver_nuggets", new MaterialWorth(1, true));
            }});
            put("unobtainium", new HashMap<>() {{
                put("#mythicmetals:unobtainium_blocks", new MaterialWorth(81, true));
                put("#mythicmetals:unobtainium", new MaterialWorth(9, true));
                put("#mythicmetals:unobtainium_ores", new MaterialWorth(9, false));

            }});
            put("ur", new HashMap<>() {{
                put("#mythicmetals:ur_blocks", new MaterialWorth(81, true));
                put("#mythicmetals:ur_ingots", new MaterialWorth(9, true));
                put("#mythicmetals:ur_ores", new MaterialWorth(9, false));
                put("#mythicmetals:ur_nuggets", new MaterialWorth(1, true));
            }});
            put("vermiculite", new HashMap<>() {{
                put("#c:vermiculite_blocks", new MaterialWorth(81, false));
                put("#c:vermiculite", new MaterialWorth(9, false));
            }});
        }}, false);
    }

    public static void createAlloyRecipes() {
        // Recipes are shaped like this:
        // Item name, item amount, heat required, smeltery tier required
        // Final boolean is whether or not to overwrite existing recipes
        ForgeRecipes.addRecipes(new HashMap<>() {{
            // Argonium
            put(new HashMap<>() {{
                put("gold", 9);
                put("midas_gold", 9);
            }}, new RecipeOutput("mythicmetals:argonium_ingot", 1, 9, 1));
            // Bronze
            put(new HashMap<>() {{
                put("copper", 18);
                put("tin", 9);
            }}, new RecipeOutput("#c:bronze_ingots", 2, 10, 1));
            // Brass
            put(new HashMap<>() {{
                put("copper", 18);
                put("zinc", 9);
            }}, new RecipeOutput("#c:brass_ingots", 2, 10, 1));
            // Celestium
            put(new HashMap<>() {{
                put("starrite", 9);
                put("kyber", 18);
            }}, new RecipeOutput("mythicmetals:celestium_ingot", 2, 10, 2));
            // Discordium
            put(new HashMap<>() {{
                put("prometheum", 9);
                put("banglum", 9);
            }}, new RecipeOutput("mythicmetals:discordium_ingot", 2, 10, 1));
            // Durasteel
            put(new HashMap<>() {{
                put("quadrillum", 18);
                put("manganese", 9);
            }}, new RecipeOutput("mythicmetals:durasteel_ingot", 3, 15, 1));
            // Electrum
            put(new HashMap<>() {{
                put("silver", 9);
                put("gold", 9);
            }}, new RecipeOutput("#c:electrum_ingots", 1, 8, 1));
            // Etherite
            put(new HashMap<>() {{
                put("aetherium", 9);
                put("vermiculite", 36);
            }}, new RecipeOutput("mythicmetals:etherite_ingot", 1, 10, 2));
            // Hallowed
            put(new HashMap<>() {{
                put("adamantite", 9);
                put("mythril", 9);
                put("orichalcum", 9);
            }}, new RecipeOutput("mythicmetals:hallowed_ingot", 1, 15, 2));
            // Metallurgium
            put(new HashMap<>() {{
                put("adamantite", 9);
                put("mythril", 9);
                put("orichalcum", 9);
                put("unobtainium", 9);
            }}, new RecipeOutput("mythicmetals:metallurgium_ingot", 1, 20, 2));
            // Metallurgium Alternative
            put(new HashMap<>() {{
                put("hallowed", 9);
                put("unobtainium", 9);
            }}, new RecipeOutput("mythicmetals:metallurgium_ingot", 1, 15, 2));
            // Steel
            put(new HashMap<>() {{
                put("iron", 9);
                put("manganese", 9);
            }}, new RecipeOutput("mythicmetals:steel_ingot", 2, 5, 1));
            // Quicksilver
            put(new HashMap<>() {{
                put("silver", 9);
                put("mythril", 9);
            }}, new RecipeOutput("mythicmetals:quicksilver_ingot", 2, 10, 2));
        }}, true);
    }

    private static void createOreRecipes() {
        // These recipes are to smelt ores more efficiently than with a regular furnace or blast furnace.
        // Adamantite
        ForgeRecipes.addRecipes(new HashMap<>() {{
            put(new HashMap<>() {{
                put("mythicmetals:adamantite_ore", 2);
            }}, new RecipeOutput("mythicmetals:adamantite_ingot", 3, 16, 2));
        }}, false);
        // Aetherium
        ForgeRecipes.addRecipes(new HashMap<>() {{
            put(new HashMap<>() {{
                put("mythicmetals:aetherium_ore", 2);
            }}, new RecipeOutput("mythicmetals:aetherium_ingot", 3, 16, 2));
        }}, false);
        // Aquarium
        ForgeRecipes.addRecipes(new HashMap<>() {{
            put(new HashMap<>() {{
                put("mythicmetals:aquarium_ore", 2);
            }}, new RecipeOutput("mythicmetals:aquarium_ingot", 3, 8, 1));
        }}, false);
        // Banglum
        ForgeRecipes.addRecipes(new HashMap<>() {{
            put(new HashMap<>() {{
                put("mythicmetals:banglum_ore", 2);
            }}, new RecipeOutput("mythicmetals:banglum_ingot", 3, 8, 1));
        }}, false);
        // Carmot
        ForgeRecipes.addRecipes(new HashMap<>() {{
            put(new HashMap<>() {{
                put("mythicmetals:carmot_ore", 2);
            }}, new RecipeOutput("mythicmetals:carmot_ingot", 3, 8, 1));
        }}, false);
        // Copper
        ForgeRecipes.addRecipes(new HashMap<>() {{
            put(new HashMap<>() {{
                put("#c:copper_ores", 2);
            }}, new RecipeOutput("#c:copper_ingots", 3, 8, 1));
        }}, false);
        // Kyber
        ForgeRecipes.addRecipes(new HashMap<>() {{
            put(new HashMap<>() {{
                put("mythicmetals:kyber_ore", 2);
            }}, new RecipeOutput("mythicmetals:kyber_ingot", 3, 8, 1));
        }}, false);
        // Manganese
        ForgeRecipes.addRecipes(new HashMap<>() {{
            put(new HashMap<>() {{
                put("mythicmetals:manganese_ore", 2);
            }}, new RecipeOutput("mythicmetals:manganese_ingot", 3, 8, 1));
        }}, false);
        // Midas_Gold
        ForgeRecipes.addRecipes(new HashMap<>() {{
            put(new HashMap<>() {{
                put("mythicmetals:midas_gold_ore", 2);
            }}, new RecipeOutput("mythicmetals:midas_gold_ingot", 3, 8, 1));
        }}, false);
        // Mythril
        ForgeRecipes.addRecipes(new HashMap<>() {{
            put(new HashMap<>() {{
                put("mythicmetals:mythril_ore", 2);
            }}, new RecipeOutput("#c:mythril_ingots", 3, 16, 2));
        }}, false);
        // Orichalcum
        ForgeRecipes.addRecipes(new HashMap<>() {{
            put(new HashMap<>() {{
                put("mythicmetals:orichalcum_ore", 2);
            }}, new RecipeOutput("#c:orichalcum_ingots", 3, 16, 2));
        }}, false);
        // Osmium
        ForgeRecipes.addRecipes(new HashMap<>() {{
            put(new HashMap<>() {{
                put("#c:osmium_ores", 2);
            }}, new RecipeOutput("#c:osmium_ingots", 3, 10, 1));
        }}, false);
        // Platinum
        ForgeRecipes.addRecipes(new HashMap<>() {{
            put(new HashMap<>() {{
                put("#c:platinum_ores", 2);
            }}, new RecipeOutput("#c:platinum_ingots", 3, 8, 1));
        }}, false);
        // Prometheum
        ForgeRecipes.addRecipes(new HashMap<>() {{
            put(new HashMap<>() {{
                put("mythicmetals:prometheum_ore", 2);
            }}, new RecipeOutput("mythicmetals:prometheum_ingot", 3, 10, 1));
        }}, false);
        // Quadrillum
        ForgeRecipes.addRecipes(new HashMap<>() {{
            put(new HashMap<>() {{
                put("mythicmetals:quadrillum_ore", 2);
            }}, new RecipeOutput("mythicmetals:quadrillum_ingot", 4, 12, 1));
        }}, false);
        // Runite
        ForgeRecipes.addRecipes(new HashMap<>() {{
            put(new HashMap<>() {{
                put("mythicmetals:runite_ore", 2);
            }}, new RecipeOutput("mythicmetals:runite_ingot", 3, 8, 1));
        }}, false);
        // Silver
        ForgeRecipes.addRecipes(new HashMap<>() {{
            put(new HashMap<>() {{
                put("#c:silver_ores", 2);
            }}, new RecipeOutput("#c:silver_ingots", 3, 8, 1));
        }}, false);
        // Starrite
        ForgeRecipes.addRecipes(new HashMap<>() {{
            put(new HashMap<>() {{
                put("mythicmetals:starrite_ore", 2);
            }}, new RecipeOutput("mythicmetals:starrite_ingot", 3, 8, 1));
        }}, false);
        // Stormyx
        ForgeRecipes.addRecipes(new HashMap<>() {{
            put(new HashMap<>() {{
                put("mythicmetals:stormyx_ore", 2);
            }}, new RecipeOutput("mythicmetals:stormyx_ingot", 3, 8, 2));
        }}, false);
        // Tantalite
        ForgeRecipes.addRecipes(new HashMap<>() {{
            put(new HashMap<>() {{
                put("#c:tantalite_ores", 2);
            }}, new RecipeOutput("#c:tantalite_ingots", 3, 8, 1));
        }}, false);
        // Tin
        ForgeRecipes.addRecipes(new HashMap<>() {{
            put(new HashMap<>() {{
                put("#c:tin_ores", 2);
            }}, new RecipeOutput("#c:tin_ingots", 3, 8, 1));
        }}, false);
        // Truesilver
        ForgeRecipes.addRecipes(new HashMap<>() {{
            put(new HashMap<>() {{
                put("mythicmetals:truesilver_ore", 2);
            }}, new RecipeOutput("mythicmetals:truesilver_ingot", 3, 8, 2));
        }}, false);
        // Unobtainium
        ForgeRecipes.addRecipes(new HashMap<>() {{
            put(new HashMap<>() {{
                put("mythicmetals:unobtainium_ore", 2);
            }}, new RecipeOutput("mythicmetals:unobtainium_ingot", 3, 16, 2));
        }}, false);
        // Ur
        ForgeRecipes.addRecipes(new HashMap<>() {{
            put(new HashMap<>() {{
                put("mythicmetals:ur_ore", 2);
            }}, new RecipeOutput("mythicmetals:ur_ingot", 3, 8, 2));
        }}, false);
        // Zinc
        ForgeRecipes.addRecipes(new HashMap<>() {{
            put(new HashMap<>() {{
                put("#c:zinc_ores", 2);
            }}, new RecipeOutput("#c:zinc_ingots", 3, 8, 1));
        }}, false);
    }
}

