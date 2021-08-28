# 0.10.0 - Caves and Cliffs  
This update contains the biggest changes in the history of Mythic Metals.  
A lot has been removed from the mod, which includes:  
* All of Argonium
* All of Brass
* All of Electrum
* All of Tantalite
* All of Ur
* All of Zinc
* Copper Ingots/Blocks, which have been replaced by vanilla variants
* Etherite Tools
* Starrite gear, replaced for Star Platinum
* Tin equipment
* Truesilver, which has been replaced in favor of Palladium
* Quadrillum armour

Even though this update removes ores I have written a datafixer which should replace all the missing ores with stones, making this version compatible with older worlds. This is experimental, so 
**please make a backup before installing this update!**  
A lot of new ore variants have been added:  
* Added deepslate variants of Adamantite, Mythril and Orichalcum.
* Added a tuff and smooth basalt variant of Orichalcum.
* Added a calcite variant of Kyber. Normal Kyber spawns rarely in the world, but it spawns in large veins in calcite (mainly from amethyst geodes).
* Added a blackstone variant of Stormyx.
* Nether ores now have their own custom feature for spawning in the overworld (portal remnants and mod compat).
* Added a calcite variant of Starrite. Starrite now spawns rarely anywhere in the world. 
* Added an End Stone Ore of Starrite. Starrite spawns much more abundantly here.
* Added a deepslate variant of Prometheum, since it can now be found in Lush Caves. 
* Tweaked Unobtainium spawn rates (it is now more common to find per-world, but will be VERY hard to find in caves).  
* Ores heights have been tweaked, even respecting the max world height/bottom.

The world-gen is mainly balanced around the Caves and Cliffs Datapack, fully utilizing the new height range from 320 to -64. For the optimal experience I recommend using it or any other mod that changes the world height. 

A lot of the artwork has been changed in the mod. Shoutouts to the artists who have worked on Mythic Metals:
* Matpac
* RGBeet
* RuisZ
* Amoraire
* DH
* Tector
* Temreno
* Shiftless

To end off these patch notes will be balancing changes. The changes are major, and will be in the form of a list.  

Star Platinum is an alloy from Starrite, Platinum, and Osmium. It provides protection similar to Diamond, and is required for Celestium. 
Celestium is now an end-game metal, 



# 0.9.10
### Note!
Unembedded Cloth Config from the mod, you now have to download it yourself.  

Added dust support for Indrev and Mechanical Tech.  
Unobtainium can now spawn exposed in caves and similar, making it easier to find.  
SLightly buffed hoes and high-tier axes.  
Major interal refactors of the code.  
Added new hallowed and metallurgium armor models, as well as changing aetherium, adamantite, mythril and orichalcum ones.  
Various re-colours and texture changes.  
Added missing tags for gold and hallowed dusts.  

# 0.9.9
### Note!  
Added two new libraries: Enchant Giver and Alloy Forgery. 
You will need to download Alloy Forgery in order to use Mythic Metals! Link: https://www.curseforge.com/minecraft/mc-mods/alloy-forgery

EnchantGiver adds innate enchantments to aquarium and prometheum. They can be overridden with higher enchantments, but they will not disappear even if a grindstone is used.  
Alloy Forgery is a new mod that lets you build a multiblock alloy smelter. This smeltery replaces the old alloy system. Making alloys require lava and different tiers of smelters (currently only 2 tiers are in place, with nothing more planned).  
A tutorial will be available on the upcoming Mythic Metals Wiki! Once it's ready a link will be available.  

### What's new?
Added new anvils! These can be made from a selection of the metals, and will not break!  
Added new chains! These can be crafted from a selection of metals and nuggets.  
Added a new material: Hallowed. Made from the hallowed ores (adamantite, mythril and orichalcum).  
Unembedded PigLib. You will now have to install it manually, to prevent version issues.  
Added BYG, Terrestria and Traverse support! You will now find biome specific ores such as Starrite in their biomes.  
The ore generator has been changed internally so that it actually generates ores in their defined ranges. This means that worldgen has changed, and upgrading might cause funky chunk borders.  
Squashed a couple of bugs related to Harvest Scythes and Charm.  
Removed Lutetium, as it only served as a worse silver alternative.    

Adjusted most of the textures, as well as adding some new models. Thank you to:
* DH(LordDeatHunter), for a lot of the re-colours, item models and animated textures.  
* Temreno, for the new armour and tool models.  
* Tector, for the new ore and ingot textures.  
* Pois1x, for the new anvil model + texture.  
* Henchman, for new low-tier textures (copper, tin, bronze, and ur).  

### Balancing changes:  
* Quicksilver now has a higher mining level, higher dmg and speed, but less durability  
* Carmot has recieved a major buff  
* Quadrillum can now be smelted with a normal furnace  
* Slightly buffed hallowed  
* Made Kyber slightly easier to find  
* Increased Ur spawn rate  
* Buffed Banglum, Discordium, and Durasteel
* Nerfed Prometheum and Kyber  
* Buffed Metallurgium tools & weapons
* Major Stormyx buff, made it slightly easier to find  
* Slowsilver armor has been nerfed, as well as small tool nerf  
* Made tin and vermiculite spawn higher up   
* Celestium and Starrite have swapped mining speeds  

## What's next?  
Next update is mostly cleaning up before the final major release of Mythic Metals. I want to focus making addons for the mod over adding a lot more content, since I believe Mythic Metals serves the scope of adding more ores.  
Main focus will be getting even better textures for the rest of the items, ores and blocks. The first addon I wish to make is a decorations addon which would mean the chains will be moved to another mod eventually.  
There will be a few more features added, but after then the mod will mainly be maintained. I will be adding support for other mods though, but in the form extra mods.  
Thank you all for showing interest in this mod!  


# 0.9.8  
Added PigLib Integration. If installed Piglins will let you trade with Midas Gold, and ignore you wearing Midas Gold and Argonium armor.  
Added TechReborn Integration, letting you use their machines to possibly automate alloy creation!  
Added Mechanix Integration, letting you double ores through their methods.  
Added Modern Industrialization integration. You can now use the dust mixer for higher alloy yields.  
Updated tag conventions: moved a lot of tags out of the common directory, and added dust tags for mod compat.  
Added hallowed dust, allowing for better automation of metallurgium.  
Changed Durasteel recipe to require Quadrillum instead of Ferrite.  
Speaking of Ferrite, it has been removed. It had a brick-like color and conflicted in purpose with other ores.    
Added Hallowed Dust for mod compatability.  
Balancing changes:  
* Minor nerf to Oricalcums mining speed  
* Nerf to Starrite  
* Nerfed Kyber tools  
* Slight nerf to Durasteel armor  
* Reduced the max vein size of adamantite, mythril, and orichalcum ores

# 0.9.7  
Added nuggets from all ingots, thanks to LordDeatHunter!
Reverted Aetherium change, it now spawns in the overworld  
Expanded on biome specific ores.  
Adjusted Copper ingot texture.  
Reduced blasting time of regular ores (thanks m3talflame!)  
* Prometheum now spawns all jungle biome variants  
* Aetherium and Starrite now spawn in all mountain variants  

Minor tweaks to configs/tools:  
* Tweaked Tantalite vein chance and ores per vein
* Reduced default vein chance of Quadrillum and Midas Gold  
* Slightly reduced ores per vein of Aquarium  
* Increased Banglum vein size  
* Slightly increased Carmot vein size  
* Increased Truesilver vein size  
* Increased speed of Quicksilver tools  
* Increased speed of Slowsilver tools  
* Buffed Carmot  
* Changed spawnrate of silver and lutetium  

# 0.9.6  
Updated storage block mining levels  
Made Metallurgium and Unobtainium fireproof  
Added russian translations (Thanks Oscienet!)  
Added a configuration file, making all of the ores configurable  
* You can find the configuration file in the config/mythic_metals folder in your .minecraft folder or your main directory

Fixed an issue where Carmot would not spawn  
Made Starrite only spawn in mountains  
Made Aquarium only spawn in ocean type biomes
Made Aetherium only spawn in The End (experimental)
Added ModMenu support for configuration
Minor balancing tweaks
* Buffed copper, tin and silver armor  
* Buffed copper, tin, silver, brass and electrum tool durability  
* Slight nerf to Ur armor  
* Rebalanced Bronze armor  

Minor change to the Stormyx armor textures 
Updated Metallurgium Armor Textures  


# 0.9.5
Updated textures for ores  
Fixed a few recipe issues with silver and electrum

# 0.9.4
Added recipes for converting storage blocks back into ingots  
Updated all recipes to use tags instead of items. This makes the mod cross-compatible with other mods using the same tags, similar to Forges ore dictionary.  
Updated the vermiculite loot table in order to suppress an error  
Updated patch notes and credits  


# 0.9.3
Bumped version so it now supports 1.16.3 and 1.16.4  
Changed Kyber Ore to spawn a bit higher up  

# 0.9.2
Metallurgium Armor has been given its intended recipes (thanks to Ackman3121)  
Vermiculite has a new loot table, dropping similar to lapis/redstone ore  

# 0.9.1
Attempted to hotfix Metallurgium armor recipes  

# 0.9
Added common tags for ores, tools, and ingots  
Added storage blocks for all ingots  
Removed True Alloy  
Metallurgium now requires to be upgraded onto Netherite armor with a smithing table  
Updated textures for some ingots and alloys  
Fixed unobtainium being way too rare  
Changed vermiculite into a drop instead of an ingot based catalyst

# 0.8
Added yellow hue to Slowsilver Ingot  
Fixed Copper and Bronze Ingot colors  
Fixed Ur Ingot color  
Fixed armor item models  
Made Custom Runite Textures  
Made Orichalcum consistent  
Made Prometheum darker  
Made Silver shinier  
Made Steel darker  
Made Tin brighter  
Recolored Aetherium Textures  
Recolored Banglum textures  
Recolored Celestium textures  
Recolored Metallurgium Armor  
Recolored Quicksilver  
Recolored Stormyx  
Removed Damascus Steel  
Reworked Midas Gold Textures  
Updated Mod Icon  
