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
        MaterialWorths.addMaterials(new HashMap<String, HashMap<String, MaterialWorth>>() {{
            put("copper", new HashMap<String, MaterialWorth>() {{
                put("#c:copper_blocks", new MaterialWorth(81, true));
                put("#c:copper_ingots", new MaterialWorth(9, true));
                put("#c:copper_dusts", new MaterialWorth(9, false));
                put("#c:copper_nuggets", new MaterialWorth(1, true));
            }});
            put("tin", new HashMap<String, MaterialWorth>() {{
                put("#c:tin_blocks", new MaterialWorth(81, true));
                put("#c:tin_ingots", new MaterialWorth(9, true));
                put("#c:tin_dusts", new MaterialWorth(9, false));
                put("#c:tin_nuggets", new MaterialWorth(1, true));
            }});
            put("brass", new HashMap<String, MaterialWorth>() {{
                put("#c:brass_blocks", new MaterialWorth(81, true));
                put("#c:brass_ingots", new MaterialWorth(9, true));
                put("#c:brass_dusts", new MaterialWorth(9, false));
                put("#c:brass_nuggets", new MaterialWorth(1, true));
            }});
            put("zinc", new HashMap<String, MaterialWorth>() {{
                put("#c:zinc_blocks", new MaterialWorth(81, true));
                put("#c:zinc_ingots", new MaterialWorth(9, true));
                put("#c:zinc_dusts", new MaterialWorth(9, false));
                put("#c:zinc_nuggets", new MaterialWorth(1, true));
            }});
            put("silver", new HashMap<String, MaterialWorth>() {{
                put("#c:silver_blocks", new MaterialWorth(81, true));
                put("#c:silver_ingots", new MaterialWorth(9, true));
                put("#c:silver_dusts", new MaterialWorth(9, false));
                put("#c:silver_nuggets", new MaterialWorth(1, true));
            }});
            put("electrum", new HashMap<String, MaterialWorth>() {{
                put("#c:electrum_blocks", new MaterialWorth(81, true));
                put("#c:electrum_ingots", new MaterialWorth(9, true));
                put("#c:electrum_dusts", new MaterialWorth(9, false));
                put("#c:electrum_nuggets", new MaterialWorth(1, true));
            }});
            put("gold", new HashMap<String, MaterialWorth>() {{
                put("#c:gold_dusts", new MaterialWorth(9, false));
            }});
            put("adamantite", new HashMap<String, MaterialWorth>() {{
                put("#c:adamantite_blocks", new MaterialWorth(81, true));
                put("#c:adamantite_dust", new MaterialWorth(9, false));
                put("#c:adamantite_ingots", new MaterialWorth(9, true));
                put("#c:adamantite_nuggets", new MaterialWorth(1, true));
            }});
            put("aetherium", new HashMap<String, MaterialWorth>() {{
                put("mythicmetals:aetherium_blocks", new MaterialWorth(81, true));
                put("mythicmetals:aetherium_dust", new MaterialWorth(9, false));
                put("mythicmetals:aetherium_ingots", new MaterialWorth(9, true));
                put("mythicmetals:aetherium_nuggets", new MaterialWorth(1, true));
            }});
            put("aquarium", new HashMap<String, MaterialWorth>() {{
                put("mythicmetals:aquarium_blocks", new MaterialWorth(81, true));
                put("mythicmetals:aquarium_dust", new MaterialWorth(9, false));
                put("mythicmetals:aquarium_ingots", new MaterialWorth(9, true));
                put("mythicmetals:aquarium_nuggets", new MaterialWorth(1, true));
            }});
            put("argonium", new HashMap<String, MaterialWorth>() {{
                put("mythicmetals:argonium_blocks", new MaterialWorth(81, true));
                put("mythicmetals:argonium_dust", new MaterialWorth(9, false));
                put("mythicmetals:argonium_ingots", new MaterialWorth(9, true));
                put("mythicmetals:argonium_nuggets", new MaterialWorth(1, true));
            }});
            put("banglum", new HashMap<String, MaterialWorth>() {{
                put("mythicmetals:banglum_blocks", new MaterialWorth(81, true));
                put("mythicmetals:banglum_dust", new MaterialWorth(9, false));
                put("mythicmetals:banglum_ingots", new MaterialWorth(9, true));
                put("mythicmetals:banglum_nuggets", new MaterialWorth(1, true));
            }});
            put("carmot", new HashMap<String, MaterialWorth>() {{
                put("mythicmetals:carmot_blocks", new MaterialWorth(81, true));
                put("mythicmetals:carmot_dust", new MaterialWorth(9, false));
                put("mythicmetals:carmot_ingots", new MaterialWorth(9, true));
                put("mythicmetals:carmot_nuggets", new MaterialWorth(1, true));
            }});
            put("celestium", new HashMap<String, MaterialWorth>() {{
                put("mythicmetals:celestium_blocks", new MaterialWorth(81, true));
                put("mythicmetals:celestium_dust", new MaterialWorth(9, false));
                put("mythicmetals:celestium_ingots", new MaterialWorth(9, true));
                put("mythicmetals:celestium_nuggets", new MaterialWorth(1, true));
            }});
            put("discordium", new HashMap<String, MaterialWorth>() {{
                put("mythicmetals:discordium_blocks", new MaterialWorth(81, true));
                put("mythicmetals:discordium_dust", new MaterialWorth(9, false));
                put("mythicmetals:discordium_ingots", new MaterialWorth(9, true));
                put("mythicmetals:discordium_nuggets", new MaterialWorth(1, true));
            }});
            put("durasteel", new HashMap<String, MaterialWorth>() {{
                put("mythicmetals:durasteel_blocks", new MaterialWorth(81, true));
                put("mythicmetals:durasteel_dust", new MaterialWorth(9, false));
                put("mythicmetals:durasteel_ingots", new MaterialWorth(9, true));
                put("mythicmetals:durasteel_nuggets", new MaterialWorth(1, true));
            }});
            put("etherite", new HashMap<String, MaterialWorth>() {{
                put("mythicmetals:etherite_blocks", new MaterialWorth(81, true));
                put("mythicmetals:etherite_dust", new MaterialWorth(9, false));
                put("mythicmetals:etherite_ingots", new MaterialWorth(9, true));
                put("mythicmetals:etherite_nuggets", new MaterialWorth(1, true));
            }});
            put("gravel", new HashMap<String, MaterialWorth>() {{
                put("minecraft:gravel", new MaterialWorth(18, false));
            }});
            put("hallowed", new HashMap<String, MaterialWorth>() {{
                put("mythicmetals:hallowed_blocks", new MaterialWorth(81, true));
                put("mythicmetals:hallowed_dust", new MaterialWorth(9, false));
                put("mythicmetals:hallowed_ingots", new MaterialWorth(9, true));
                put("mythicmetals:hallowed_nuggets", new MaterialWorth(1, true));
            }});
            put("kyber", new HashMap<String, MaterialWorth>() {{
                put("mythicmetals:kyber_blocks", new MaterialWorth(81, true));
                put("mythicmetals:kyber_dust", new MaterialWorth(9, false));
                put("mythicmetals:kyber_ingots", new MaterialWorth(9, true));
                put("mythicmetals:kyber_nuggets", new MaterialWorth(1, true));
            }});
            put("manganese", new HashMap<String, MaterialWorth>() {{
                put("#c:manganese_blocks", new MaterialWorth(81, true));
                put("#c:manganese_dust", new MaterialWorth(9, false));
                put("#c:manganese_ingots", new MaterialWorth(9, true));
                put("#c:manganese_nuggets", new MaterialWorth(1, true));
            }});
            put("metallurgium", new HashMap<String, MaterialWorth>() {{
                put("mythicmetals:metallurgium_blocks", new MaterialWorth(81, true));
                put("mythicmetals:metallurgium_dust", new MaterialWorth(9, false));
                put("mythicmetals:metallurgium_ingots", new MaterialWorth(9, true));
                put("mythicmetals:metallurgium_nuggets", new MaterialWorth(1, true));
            }});
            put("midas_gold", new HashMap<String, MaterialWorth>() {{
                put("mythicmetals:midas_gold_blocks", new MaterialWorth(81, true));
                put("mythicmetals:midas_gold_dust", new MaterialWorth(9, false));
                put("mythicmetals:midas_gold_ingots", new MaterialWorth(9, true));
                put("mythicmetals:midas_gold_nuggets", new MaterialWorth(1, true));
            }});
            put("mythril", new HashMap<String, MaterialWorth>() {{
                put("#c:mythril_blocks", new MaterialWorth(81, true));
                put("#c:mythril_dust", new MaterialWorth(9, false));
                put("#c:mythril_ingots", new MaterialWorth(9, true));
                put("#c:mythril_nuggets", new MaterialWorth(1, true));
            }});
            put("orichalcum", new HashMap<String, MaterialWorth>() {{
                put("#c:orichalcum_blocks", new MaterialWorth(81, true));
                put("#c:orichalcum_dust", new MaterialWorth(9, false));
                put("#c:orichalcum_ingots", new MaterialWorth(9, true));
                put("#c:orichalcum_nuggets", new MaterialWorth(1, true));
            }});
            put("osmium", new HashMap<String, MaterialWorth>() {{
                put("#c:osmium_blocks", new MaterialWorth(81, true));
                put("#c:osmium_dust", new MaterialWorth(9, false));
                put("#c:osmium_ingots", new MaterialWorth(9, true));
                put("#c:osmium_nuggets", new MaterialWorth(1, true));
            }});
            put("platinum", new HashMap<String, MaterialWorth>() {{
                put("#c:platinum_blocks", new MaterialWorth(81, true));
                put("#c:platinum_dust", new MaterialWorth(9, false));
                put("#c:platinum_ingots", new MaterialWorth(9, true));
                put("#c:platinum_nuggets", new MaterialWorth(1, true));
            }});
            put("prometheum", new HashMap<String, MaterialWorth>() {{
                put("mythicmetals:prometheum_blocks", new MaterialWorth(81, true));
                put("mythicmetals:prometheum_dust", new MaterialWorth(9, false));
                put("mythicmetals:prometheum_ingots", new MaterialWorth(9, true));
                put("mythicmetals:prometheum_nuggets", new MaterialWorth(1, true));
            }});
            put("quadrillum", new HashMap<String, MaterialWorth>() {{
                put("mythicmetals:quadrillum_blocks", new MaterialWorth(81, true));
                put("mythicmetals:quadrillum_dust", new MaterialWorth(9, false));
                put("mythicmetals:quadrillum_ingots", new MaterialWorth(9, true));
                put("mythicmetals:quadrillum_nuggets", new MaterialWorth(1, true));
            }});
            put("quicksilver", new HashMap<String, MaterialWorth>() {{
                put("#c:quicksilver_blocks", new MaterialWorth(81, true));
                put("#c:quicksilver_dust", new MaterialWorth(9, false));
                put("#c:quicksilver_ingots", new MaterialWorth(9, true));
                put("#c:quicksilver_nuggets", new MaterialWorth(1, true));
            }});
            put("runite", new HashMap<String, MaterialWorth>() {{
                put("mythicmetals:runite_blocks", new MaterialWorth(81, true));
                put("mythicmetals:runite_dust", new MaterialWorth(9, false));
                put("mythicmetals:runite_ingots", new MaterialWorth(9, true));
                put("mythicmetals:runite_nuggets", new MaterialWorth(1, true));
            }});
            put("slowsilver", new HashMap<String, MaterialWorth>() {{
                put("mythicmetals:slowsilver_blocks", new MaterialWorth(81, true));
                put("mythicmetals:slowsilver_dust", new MaterialWorth(9, false));
                put("mythicmetals:slowsilver_ingots", new MaterialWorth(9, true));
                put("mythicmetals:slowsilver_nuggets", new MaterialWorth(1, true));
            }});
            put("starrite", new HashMap<String, MaterialWorth>() {{
                put("mythicmetals:starrite_blocks", new MaterialWorth(81, true));
                put("mythicmetals:starrite_dust", new MaterialWorth(9, false));
                put("mythicmetals:starrite_ingots", new MaterialWorth(9, true));
                put("mythicmetals:starrite_nuggets", new MaterialWorth(1, true));
            }});
            put("steel", new HashMap<String, MaterialWorth>() {{
                put("#c:steel_blocks", new MaterialWorth(81, true));
                put("#c:steel_dust", new MaterialWorth(9, false));
                put("#c:steel_ingots", new MaterialWorth(9, true));
                put("#c:steel_nuggets", new MaterialWorth(1, true));
            }});
            put("stormyx", new HashMap<String, MaterialWorth>() {{
                put("mythicmetals:stormyx_blocks", new MaterialWorth(81, true));
                put("mythicmetals:stormyx_dust", new MaterialWorth(9, false));
                put("mythicmetals:stormyx_ingots", new MaterialWorth(9, true));
                put("mythicmetals:stormyx_nuggets", new MaterialWorth(1, true));
            }});
            put("tantalite", new HashMap<String, MaterialWorth>() {{
                put("#c:tantalite_blocks", new MaterialWorth(81, true));
                put("#c:tantalite_dust", new MaterialWorth(9, false));
                put("#c:tantalite_ingots", new MaterialWorth(9, true));
                put("#c:tantalite_nuggets", new MaterialWorth(1, true));
            }});
            put("truesilver", new HashMap<String, MaterialWorth>() {{
                put("mythicmetals:truesilver_blocks", new MaterialWorth(81, true));
                put("mythicmetals:truesilver_dust", new MaterialWorth(9, false));
                put("mythicmetals:truesilver_ingots", new MaterialWorth(9, true));
                put("mythicmetals:truesilver_nuggets", new MaterialWorth(1, true));
            }});
            put("unobtainium", new HashMap<String, MaterialWorth>() {{
                put("mythicmetals:unobtainium_blocks", new MaterialWorth(81, true));
                put("mythicmetals:unobtainium_dust", new MaterialWorth(9, false));
                put("mythicmetals:unobtainium_ingots", new MaterialWorth(9, true));
                put("mythicmetals:unobtainium_nuggets", new MaterialWorth(1, true));
            }});
            put("ur", new HashMap<String, MaterialWorth>() {{
                put("mythicmetals:ur_blocks", new MaterialWorth(81, true));
                put("mythicmetals:ur_dust", new MaterialWorth(9, false));
                put("mythicmetals:ur_ingots", new MaterialWorth(9, true));
                put("mythicmetals:ur_nuggets", new MaterialWorth(1, true));
            }});
            put("vermiculite", new HashMap<String, MaterialWorth>() {{
                put("mythicmetals:vermiculite_block", new MaterialWorth(81, false));
                put("mythicmetals:vermiculite", new MaterialWorth(9, false));
            }});
        }}, false);
    }

    public static void createAlloyRecipes() {
        // Recipes are shaped like this:
        // Item name, item amount, heat required, smeltery tier required
        // Final boolean is whether or not to overwrite existing recipes
        ForgeRecipes.addRecipes(new HashMap<HashMap<String, Integer>, RecipeOutput>() {{
            // Argonium
            put(new HashMap<String, Integer>() {{
                put("gold", 9);
                put("midas_gold", 9);
            }}, new RecipeOutput("#c:bronze_ingots", 2, 10, 1));
            // Bronze
            put(new HashMap<String, Integer>() {{
                put("copper", 18);
                put("tin", 9);
            }}, new RecipeOutput("#c:bronze_ingots", 2, 10, 1));
            // Brass
            put(new HashMap<String, Integer>() {{
                put("copper", 18);
                put("zinc", 9);
            }}, new RecipeOutput("#c:brass_ingots", 2, 10, 1));
            // Celestium
            put(new HashMap<String, Integer>() {{
                put("starrite", 9);
                put("kyber", 18);
            }}, new RecipeOutput("mythicmetals:celestium_ingot", 2, 10, 2));
            // Discordium
            put(new HashMap<String, Integer>() {{
                put("prometheum", 9);
                put("banglum", 9);
            }}, new RecipeOutput("mythicmetals:discordium_ingot", 2, 10, 1));
            // Durasteel
            put(new HashMap<String, Integer>() {{
                put("quadrillum", 18);
                put("manganese", 9);
            }}, new RecipeOutput("mythicmetals:durasteel_ingot", 3, 15, 1));
            // Electrum
            put(new HashMap<String, Integer>() {{
                put("silver", 9);
                put("gold", 9);
            }}, new RecipeOutput("#c:electrum_ingots", 1, 8, 1));
            // Etherite
            put(new HashMap<String, Integer>() {{
                put("aetherium", 9);
                put("vermiculite", 36);
            }}, new RecipeOutput("mythicmetals:etherite_ingot", 1, 10, 2));
            // Hallowed
            put(new HashMap<String, Integer>() {{
                put("adamantite", 9);
                put("mythril", 9);
                put("orichalcum", 9);
            }}, new RecipeOutput("mythicmetals:hallowed_ingot", 1, 15, 2));
            // Metallurgium
            put(new HashMap<String, Integer>() {{
                put("adamantite", 9);
                put("mythril", 9);
                put("orichalcum", 9);
                put("unobtainium", 9);
            }}, new RecipeOutput("mythicmetals:metallurgium_ingot", 1, 20, 2));
            // Metallurgium Alternative
            put(new HashMap<String, Integer>() {{
                put("hallowed", 9);
                put("unobtainium", 9);
            }}, new RecipeOutput("mythicmetals:metallurgium_ingot", 1, 15, 2));
            // Slowsilver
            put(new HashMap<String, Integer>() {{
                put("silver", 9);
                put("gravel", 72);
            }}, new RecipeOutput("mythicmetals:slowsilver_ingot", 1, 5, 1));
            // Steel
            put(new HashMap<String, Integer>() {{
                put("silver", 9);
                put("gravel", 72);
            }}, new RecipeOutput("mythicmetals:slowsilver_ingot", 1, 5, 1));
            // Quicksilver
            put(new HashMap<String, Integer>() {{
                put("silver", 9);
                put("mythril", 9);
            }}, new RecipeOutput("mythicmetals:quicksilver_ingot", 2, 10, 2));
        }}, false);
    }

    private static void createOreRecipes() {
        // These recipes are to smelt ores more efficiently than with a regular furnace or blast furnace
        // Adamantite
        ForgeRecipes.addRecipes(new HashMap<HashMap<String, Integer>, RecipeOutput>() {{
            put(new HashMap<String, Integer>() {{
                put("#c:adamantite_ores", 2);
            }}, new RecipeOutput("#c:adamantite_ingots", 3, 16, 2));
        }}, false);
        // Aetherium
        ForgeRecipes.addRecipes(new HashMap<HashMap<String, Integer>, RecipeOutput>() {{
            put(new HashMap<String, Integer>() {{
                put("mythicmetals:aetherium_ore", 2);
            }}, new RecipeOutput("mythicmetals:aetherium_ingot", 3, 16, 2));
        }}, false);
        // Aquarium
        ForgeRecipes.addRecipes(new HashMap<HashMap<String, Integer>, RecipeOutput>() {{
            put(new HashMap<String, Integer>() {{
                put("mythicmetals:aquarium_ore", 2);
            }}, new RecipeOutput("mythicmetals:aquarium_ingot", 3, 8, 1));
        }}, false);
        // Banglum
        ForgeRecipes.addRecipes(new HashMap<HashMap<String, Integer>, RecipeOutput>() {{
            put(new HashMap<String, Integer>() {{
                put("mythicmetals:banglum_ore", 2);
            }}, new RecipeOutput("mythicmetals:banglum_ingot", 3, 8, 1));
        }}, false);
        // Carmot
        ForgeRecipes.addRecipes(new HashMap<HashMap<String, Integer>, RecipeOutput>() {{
            put(new HashMap<String, Integer>() {{
                put("mythicmetals:carmot_ore", 2);
            }}, new RecipeOutput("mythicmetals:carmot_ingot", 3, 8, 1));
        }}, false);
        // Copper
        ForgeRecipes.addRecipes(new HashMap<HashMap<String, Integer>, RecipeOutput>() {{
            put(new HashMap<String, Integer>() {{
                put("#c:copper_ores", 2);
            }}, new RecipeOutput("#c:copper_ingots", 3, 8, 1));
        }}, false);
        // Kyber
        ForgeRecipes.addRecipes(new HashMap<HashMap<String, Integer>, RecipeOutput>() {{
            put(new HashMap<String, Integer>() {{
                put("mythicmetals:kyber_ore", 2);
            }}, new RecipeOutput("mythicmetals:kyber_ingot", 3, 8, 1));
        }}, false);
        // Manganese
        ForgeRecipes.addRecipes(new HashMap<HashMap<String, Integer>, RecipeOutput>() {{
            put(new HashMap<String, Integer>() {{
                put("mythicmetals:manganese_ore", 2);
            }}, new RecipeOutput("mythicmetals:manganese_ingot", 3, 8, 1));
        }}, false);
        // Midas_Gold
        ForgeRecipes.addRecipes(new HashMap<HashMap<String, Integer>, RecipeOutput>() {{
            put(new HashMap<String, Integer>() {{
                put("mythicmetals:midas_gold_ore", 2);
            }}, new RecipeOutput("mythicmetals:midas_gold_ingot", 3, 8, 1));
        }}, false);
        // Mythril
        ForgeRecipes.addRecipes(new HashMap<HashMap<String, Integer>, RecipeOutput>() {{
            put(new HashMap<String, Integer>() {{
                put("#c:mythril_ores", 2);
            }}, new RecipeOutput("#c:mythril_ingots", 3, 16, 2));
        }}, false);
        // Orichalcum
        ForgeRecipes.addRecipes(new HashMap<HashMap<String, Integer>, RecipeOutput>() {{
            put(new HashMap<String, Integer>() {{
                put("#c:orichalcum_ores", 2);
            }}, new RecipeOutput("#c:orichalcum_ingots", 3, 16, 2));
        }}, false);
        // Osmium
        ForgeRecipes.addRecipes(new HashMap<HashMap<String, Integer>, RecipeOutput>() {{
            put(new HashMap<String, Integer>() {{
                put("#c:osmium_ores", 2);
            }}, new RecipeOutput("#c:osmium_ingots", 3, 10, 1));
        }}, false);
        // Platinum
        ForgeRecipes.addRecipes(new HashMap<HashMap<String, Integer>, RecipeOutput>() {{
            put(new HashMap<String, Integer>() {{
                put("#c:platinum_ores", 2);
            }}, new RecipeOutput("#c:platinum_ingots", 3, 8, 1));
        }}, false);
        // Prometheum
        ForgeRecipes.addRecipes(new HashMap<HashMap<String, Integer>, RecipeOutput>() {{
            put(new HashMap<String, Integer>() {{
                put("mythicmetals:prometheum_ore", 2);
            }}, new RecipeOutput("mythicmetals:prometheum_ingot", 3, 10, 1));
        }}, false);
        // Quadrillum
        ForgeRecipes.addRecipes(new HashMap<HashMap<String, Integer>, RecipeOutput>() {{
            put(new HashMap<String, Integer>() {{
                put("mythicmetals:quadrillum_ore", 2);
            }}, new RecipeOutput("mythicmetals:quadrillum_ingot", 4, 12, 1));
        }}, false);
        // Runite
        ForgeRecipes.addRecipes(new HashMap<HashMap<String, Integer>, RecipeOutput>() {{
            put(new HashMap<String, Integer>() {{
                put("mythicmetals:runite_ore", 2);
            }}, new RecipeOutput("mythicmetals:runite_ingot", 3, 8, 1));
        }}, false);
        // Silver
        ForgeRecipes.addRecipes(new HashMap<HashMap<String, Integer>, RecipeOutput>() {{
            put(new HashMap<String, Integer>() {{
                put("c#:silver_ores", 2);
            }}, new RecipeOutput("#c:silver_ingots", 3, 8, 1));
        }}, false);
        // Starrite
        ForgeRecipes.addRecipes(new HashMap<HashMap<String, Integer>, RecipeOutput>() {{
            put(new HashMap<String, Integer>() {{
                put("mythicmetals:starrite_ore", 2);
            }}, new RecipeOutput("mythicmetals:starrite_ingot", 3, 8, 1));
        }}, false);
        // Stormyx
        ForgeRecipes.addRecipes(new HashMap<HashMap<String, Integer>, RecipeOutput>() {{
            put(new HashMap<String, Integer>() {{
                put("mythicmetals:stormyx_ore", 2);
            }}, new RecipeOutput("mythicmetals:stormyx_ingot", 3, 8, 2));
        }}, false);
        // Tantalite
        ForgeRecipes.addRecipes(new HashMap<HashMap<String, Integer>, RecipeOutput>() {{
                put(new HashMap<String, Integer>() {{
                    put("c#:tantalite_ores", 2);
                }}, new RecipeOutput("#c:tantalite_ingots", 3, 8, 1));
        }}, false);
        // Tin
        ForgeRecipes.addRecipes(new HashMap<HashMap<String, Integer>, RecipeOutput>() {{
            put(new HashMap<String, Integer>() {{
                put("c#:tin_ores", 2);
            }}, new RecipeOutput("#c:tin_ingots", 3, 8, 1));
        }}, false);
        // Truesilver
        ForgeRecipes.addRecipes(new HashMap<HashMap<String, Integer>, RecipeOutput>() {{
            put(new HashMap<String, Integer>() {{
                put("mythicmetals:stormyx_ore", 2);
            }}, new RecipeOutput("mythicmetals:truesilver_ingot", 3, 8, 2));
        }}, false);
        // Unobtainium
        ForgeRecipes.addRecipes(new HashMap<HashMap<String, Integer>, RecipeOutput>() {{
            put(new HashMap<String, Integer>() {{
                put("mythicmetals:stormyx_ore", 2);
            }}, new RecipeOutput("mythicmetals:unobtainium_ingot", 3, 16, 2));
        }}, false);
        // Ur
        ForgeRecipes.addRecipes(new HashMap<HashMap<String, Integer>, RecipeOutput>() {{
            put(new HashMap<String, Integer>() {{
                put("mythicmetals:ur_ore", 2);
            }}, new RecipeOutput("mythicmetals:ur_ingot", 3, 8, 2));
        }}, false);
        // Zinc
        ForgeRecipes.addRecipes(new HashMap<HashMap<String, Integer>, RecipeOutput>() {{
            put(new HashMap<String, Integer>() {{
                put("#c:zinc_ores", 2);
            }}, new RecipeOutput("#c:zinc_ingots", 3, 8, 1));
        }}, false);
    }
}

