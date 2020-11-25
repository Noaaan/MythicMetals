package nourl.mythicmetals.registry;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import nourl.mythicmetals.MythicMetalsMain;
import nourl.mythicmetals.ores.MythicMetalsOres;
import nourl.mythicmetals.ores.OreGenerator;

public class RegisterOres {
	
	public static void register() {
		Registry.register(Registry.ITEM, new Identifier(MythicMetalsMain.MOD_ID, "adamantite_ore"), new BlockItem(MythicMetalsOres.ADAMANTITE_ORE, new Item.Settings().group(MythicMetalsMain.MYTHICMETALS)));
		Registry.register(Registry.ITEM, new Identifier(MythicMetalsMain.MOD_ID, "aetherium_ore"), new BlockItem(MythicMetalsOres.AETHERIUM_ORE, new Item.Settings().group(MythicMetalsMain.MYTHICMETALS)));
		Registry.register(Registry.ITEM, new Identifier(MythicMetalsMain.MOD_ID, "aquarium_ore"), new BlockItem(MythicMetalsOres.AQUARIUM_ORE, new Item.Settings().group(MythicMetalsMain.MYTHICMETALS)));
		Registry.register(Registry.ITEM, new Identifier(MythicMetalsMain.MOD_ID, "banglum_ore"), new BlockItem(MythicMetalsOres.BANGLUM_ORE, new Item.Settings().group(MythicMetalsMain.MYTHICMETALS)));
		Registry.register(Registry.ITEM, new Identifier(MythicMetalsMain.MOD_ID, "carmot_ore"), new BlockItem(MythicMetalsOres.CARMOT_ORE, new Item.Settings().group(MythicMetalsMain.MYTHICMETALS)));
		Registry.register(Registry.ITEM, new Identifier(MythicMetalsMain.MOD_ID, "copper_ore"), new BlockItem(MythicMetalsOres.COPPER_ORE, new Item.Settings().group(MythicMetalsMain.MYTHICMETALS)));
		Registry.register(Registry.ITEM, new Identifier(MythicMetalsMain.MOD_ID, "kyber_ore"), new BlockItem(MythicMetalsOres.KYBER_ORE, new Item.Settings().group(MythicMetalsMain.MYTHICMETALS)));
		Registry.register(Registry.ITEM, new Identifier(MythicMetalsMain.MOD_ID, "lutetium_ore"), new BlockItem(MythicMetalsOres.LUTETIUM_ORE, new Item.Settings().group(MythicMetalsMain.MYTHICMETALS)));
		Registry.register(Registry.ITEM, new Identifier(MythicMetalsMain.MOD_ID, "manganese_ore"), new BlockItem(MythicMetalsOres.MANGANESE_ORE, new Item.Settings().group(MythicMetalsMain.MYTHICMETALS)));
		Registry.register(Registry.ITEM, new Identifier(MythicMetalsMain.MOD_ID, "midas_gold_ore"), new BlockItem(MythicMetalsOres.MIDAS_GOLD_ORE, new Item.Settings().group(MythicMetalsMain.MYTHICMETALS)));
		Registry.register(Registry.ITEM, new Identifier(MythicMetalsMain.MOD_ID, "mythril_ore"), new BlockItem(MythicMetalsOres.MYTHRIL_ORE, new Item.Settings().group(MythicMetalsMain.MYTHICMETALS)));
		Registry.register(Registry.ITEM, new Identifier(MythicMetalsMain.MOD_ID, "orichalcum_ore"), new BlockItem(MythicMetalsOres.ORICHALCUM_ORE, new Item.Settings().group(MythicMetalsMain.MYTHICMETALS)));
		Registry.register(Registry.ITEM, new Identifier(MythicMetalsMain.MOD_ID, "osmium_ore"), new BlockItem(MythicMetalsOres.OSMIUM_ORE, new Item.Settings().group(MythicMetalsMain.MYTHICMETALS)));
		Registry.register(Registry.ITEM, new Identifier(MythicMetalsMain.MOD_ID, "platinum_ore"), new BlockItem(MythicMetalsOres.PLATINUM_ORE, new Item.Settings().group(MythicMetalsMain.MYTHICMETALS)));
		Registry.register(Registry.ITEM, new Identifier(MythicMetalsMain.MOD_ID, "prometheum_ore"), new BlockItem(MythicMetalsOres.PROMETHEUM_ORE, new Item.Settings().group(MythicMetalsMain.MYTHICMETALS)));
		Registry.register(Registry.ITEM, new Identifier(MythicMetalsMain.MOD_ID, "quadrillum_ore"), new BlockItem(MythicMetalsOres.QUADRILLUM_ORE, new Item.Settings().group(MythicMetalsMain.MYTHICMETALS)));
		Registry.register(Registry.ITEM, new Identifier(MythicMetalsMain.MOD_ID, "runite_ore"), new BlockItem(MythicMetalsOres.RUNITE_ORE, new Item.Settings().group(MythicMetalsMain.MYTHICMETALS)));
		Registry.register(Registry.ITEM, new Identifier(MythicMetalsMain.MOD_ID, "silver_ore"), new BlockItem(MythicMetalsOres.SILVER_ORE, new Item.Settings().group(MythicMetalsMain.MYTHICMETALS)));
		Registry.register(Registry.ITEM, new Identifier(MythicMetalsMain.MOD_ID, "starrite_ore"), new BlockItem(MythicMetalsOres.STARRITE_ORE, new Item.Settings().group(MythicMetalsMain.MYTHICMETALS)));
		Registry.register(Registry.ITEM, new Identifier(MythicMetalsMain.MOD_ID, "stormyx_ore"), new BlockItem(MythicMetalsOres.STORMYX_ORE, new Item.Settings().group(MythicMetalsMain.MYTHICMETALS)));
		Registry.register(Registry.ITEM, new Identifier(MythicMetalsMain.MOD_ID, "tantalite_ore"), new BlockItem(MythicMetalsOres.TANTALITE_ORE, new Item.Settings().group(MythicMetalsMain.MYTHICMETALS)));
		Registry.register(Registry.ITEM, new Identifier(MythicMetalsMain.MOD_ID, "tin_ore"), new BlockItem(MythicMetalsOres.TIN_ORE, new Item.Settings().group(MythicMetalsMain.MYTHICMETALS)));
		Registry.register(Registry.ITEM, new Identifier(MythicMetalsMain.MOD_ID, "truesilver_ore"), new BlockItem(MythicMetalsOres.TRUESILVER_ORE, new Item.Settings().group(MythicMetalsMain.MYTHICMETALS)));
		Registry.register(Registry.ITEM, new Identifier(MythicMetalsMain.MOD_ID, "unobtainium_ore"), new BlockItem(MythicMetalsOres.UNOBTAINIUM_ORE, new Item.Settings().group(MythicMetalsMain.MYTHICMETALS).fireproof()));
		Registry.register(Registry.ITEM, new Identifier(MythicMetalsMain.MOD_ID, "ur_ore"), new BlockItem(MythicMetalsOres.UR_ORE, new Item.Settings().group(MythicMetalsMain.MYTHICMETALS)));
		Registry.register(Registry.ITEM, new Identifier(MythicMetalsMain.MOD_ID, "vermiculite_ore"), new BlockItem(MythicMetalsOres.VERMICULITE_ORE, new Item.Settings().group(MythicMetalsMain.MYTHICMETALS)));
		Registry.register(Registry.ITEM, new Identifier(MythicMetalsMain.MOD_ID, "zinc_ore"), new BlockItem(MythicMetalsOres.ZINC_ORE, new Item.Settings().group(MythicMetalsMain.MYTHICMETALS)));
				
		Registry.register(Registry.BLOCK, new Identifier(MythicMetalsMain.MOD_ID, "adamantite_ore"), MythicMetalsOres.ADAMANTITE_ORE);
		Registry.register(Registry.BLOCK, new Identifier(MythicMetalsMain.MOD_ID, "aetherium_ore"), MythicMetalsOres.AETHERIUM_ORE);
		Registry.register(Registry.BLOCK, new Identifier(MythicMetalsMain.MOD_ID, "aquarium_ore"), MythicMetalsOres.AQUARIUM_ORE);
		Registry.register(Registry.BLOCK, new Identifier(MythicMetalsMain.MOD_ID, "banglum_ore"), MythicMetalsOres.BANGLUM_ORE);
		Registry.register(Registry.BLOCK, new Identifier(MythicMetalsMain.MOD_ID, "carmot_ore"), MythicMetalsOres.CARMOT_ORE);
		Registry.register(Registry.BLOCK, new Identifier(MythicMetalsMain.MOD_ID, "copper_ore"), MythicMetalsOres.COPPER_ORE);
		Registry.register(Registry.BLOCK, new Identifier(MythicMetalsMain.MOD_ID, "kyber_ore"), MythicMetalsOres.KYBER_ORE);
		Registry.register(Registry.BLOCK, new Identifier(MythicMetalsMain.MOD_ID, "lutetium_ore"), MythicMetalsOres.LUTETIUM_ORE);
		Registry.register(Registry.BLOCK, new Identifier(MythicMetalsMain.MOD_ID, "manganese_ore"), MythicMetalsOres.MANGANESE_ORE);
		Registry.register(Registry.BLOCK, new Identifier(MythicMetalsMain.MOD_ID, "midas_gold_ore"), MythicMetalsOres.MIDAS_GOLD_ORE);
		Registry.register(Registry.BLOCK, new Identifier(MythicMetalsMain.MOD_ID, "mythril_ore"), MythicMetalsOres.MYTHRIL_ORE);
		Registry.register(Registry.BLOCK, new Identifier(MythicMetalsMain.MOD_ID, "orichalcum_ore"), MythicMetalsOres.ORICHALCUM_ORE);
		Registry.register(Registry.BLOCK, new Identifier(MythicMetalsMain.MOD_ID, "osmium_ore"), MythicMetalsOres.OSMIUM_ORE);
		Registry.register(Registry.BLOCK, new Identifier(MythicMetalsMain.MOD_ID, "platinum_ore"), MythicMetalsOres.PLATINUM_ORE);
		Registry.register(Registry.BLOCK, new Identifier(MythicMetalsMain.MOD_ID, "prometheum_ore"), MythicMetalsOres.PROMETHEUM_ORE);
		Registry.register(Registry.BLOCK, new Identifier(MythicMetalsMain.MOD_ID, "quadrillum_ore"), MythicMetalsOres.QUADRILLUM_ORE);
		Registry.register(Registry.BLOCK, new Identifier(MythicMetalsMain.MOD_ID, "runite_ore"), MythicMetalsOres.RUNITE_ORE);
		Registry.register(Registry.BLOCK, new Identifier(MythicMetalsMain.MOD_ID, "silver_ore"), MythicMetalsOres.SILVER_ORE);
		Registry.register(Registry.BLOCK, new Identifier(MythicMetalsMain.MOD_ID, "starrite_ore"), MythicMetalsOres.STARRITE_ORE);
		Registry.register(Registry.BLOCK, new Identifier(MythicMetalsMain.MOD_ID, "stormyx_ore"), MythicMetalsOres.STORMYX_ORE);
		Registry.register(Registry.BLOCK, new Identifier(MythicMetalsMain.MOD_ID, "tantalite_ore"), MythicMetalsOres.TANTALITE_ORE);
		Registry.register(Registry.BLOCK, new Identifier(MythicMetalsMain.MOD_ID, "tin_ore"), MythicMetalsOres.TIN_ORE);
		Registry.register(Registry.BLOCK, new Identifier(MythicMetalsMain.MOD_ID, "truesilver_ore"), MythicMetalsOres.TRUESILVER_ORE);
		Registry.register(Registry.BLOCK, new Identifier(MythicMetalsMain.MOD_ID, "unobtainium_ore"), MythicMetalsOres.UNOBTAINIUM_ORE);
		Registry.register(Registry.BLOCK, new Identifier(MythicMetalsMain.MOD_ID, "ur_ore"), MythicMetalsOres.UR_ORE);
		Registry.register(Registry.BLOCK, new Identifier(MythicMetalsMain.MOD_ID, "vermiculite_ore"), MythicMetalsOres.VERMICULITE_ORE);
		Registry.register(Registry.BLOCK, new Identifier(MythicMetalsMain.MOD_ID, "zinc_ore"), MythicMetalsOres.ZINC_ORE);
        
		Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, new Identifier(MythicMetalsMain.MOD_ID, "adamantite_ore"), OreGenerator.ORE_ADAMANTITE);
		Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, new Identifier(MythicMetalsMain.MOD_ID, "aetherium_ore"), OreGenerator.ORE_AETHERIUM);
		Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, new Identifier(MythicMetalsMain.MOD_ID, "aquarium_ore"), OreGenerator.ORE_AQUARIUM);
		Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, new Identifier(MythicMetalsMain.MOD_ID, "banglum_ore"), OreGenerator.ORE_BANGLUM);
		Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, new Identifier(MythicMetalsMain.MOD_ID, "carmot_ore"), OreGenerator.ORE_CARMOT);
		Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, new Identifier(MythicMetalsMain.MOD_ID, "copper_ore"), OreGenerator.ORE_COPPER);
		Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, new Identifier(MythicMetalsMain.MOD_ID, "kyber_ore"), OreGenerator.ORE_KYBER);
		Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, new Identifier(MythicMetalsMain.MOD_ID, "lutetium_ore"), OreGenerator.ORE_LUTETIUM);
		Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, new Identifier(MythicMetalsMain.MOD_ID, "manganese_ore"), OreGenerator.ORE_MANGANESE);
		Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, new Identifier(MythicMetalsMain.MOD_ID, "midas_gold_ore"), OreGenerator.ORE_MIDAS_GOLD);
		Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, new Identifier(MythicMetalsMain.MOD_ID, "mythril_ore"), OreGenerator.ORE_MYTHRIL);
		Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, new Identifier(MythicMetalsMain.MOD_ID, "orichalcum_ore"), OreGenerator.ORE_ORICHALCUM);
		Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, new Identifier(MythicMetalsMain.MOD_ID, "osmium_ore"), OreGenerator.ORE_OSMIUM);
		Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, new Identifier(MythicMetalsMain.MOD_ID, "platinum_ore"), OreGenerator.ORE_PLATINUM);
		Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, new Identifier(MythicMetalsMain.MOD_ID, "prometheum_ore"), OreGenerator.ORE_PROMETHEUM);
		Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, new Identifier(MythicMetalsMain.MOD_ID, "quadrillum_ore"), OreGenerator.ORE_QUADRILLUM);
		Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, new Identifier(MythicMetalsMain.MOD_ID, "runite_ore"), OreGenerator.ORE_RUNITE);
		Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, new Identifier(MythicMetalsMain.MOD_ID, "silver_ore"), OreGenerator.ORE_SILVER);
		Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, new Identifier(MythicMetalsMain.MOD_ID, "starrite_ore"), OreGenerator.ORE_STARRITE);
		Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, new Identifier(MythicMetalsMain.MOD_ID, "stormyx_ore"), OreGenerator.ORE_STORMYX);
		Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, new Identifier(MythicMetalsMain.MOD_ID, "tantalite_ore"), OreGenerator.ORE_TANTALITE);
		Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, new Identifier(MythicMetalsMain.MOD_ID, "tin_ore"), OreGenerator.ORE_TIN);
		Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, new Identifier(MythicMetalsMain.MOD_ID, "truesilver_ore"), OreGenerator.ORE_TRUESILVER);
		Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, new Identifier(MythicMetalsMain.MOD_ID, "unobtainium_ore"), OreGenerator.ORE_UNOBTAINIUM);
		Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, new Identifier(MythicMetalsMain.MOD_ID, "ur_ore"), OreGenerator.ORE_UR);
		Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, new Identifier(MythicMetalsMain.MOD_ID, "vermiculite_ore"), OreGenerator.ORE_VERMICULITE);
		Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, new Identifier(MythicMetalsMain.MOD_ID, "zinc_ore"), OreGenerator.ORE_ZINC);


	}
}
