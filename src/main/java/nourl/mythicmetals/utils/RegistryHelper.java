package nourl.mythicmetals.utils;

import io.wispforest.owo.itemgroup.OwoItemSettings;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.ConfiguredFeatures;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.minecraft.world.gen.feature.PlacedFeatures;
import nourl.mythicmetals.MythicMetals;

/*
 * This is a helper class containing methods for registering various blocks and items.
 * @author  Noaaan
 */
public class RegistryHelper {

    public static Identifier id(String path) {
        return new Identifier(MythicMetals.MOD_ID, path);
    }

    public static void item(String path, Item item) {
        Registry.register(Registry.ITEM, id(path), item);
    }

    public static void addonItem(String path, Item item) {
        Registry.register(Registry.ITEM, new Identifier(MythicMetals.ADDON_ID, path), item);
    }

    public static void block(String path, Block block) {
        Registry.register(Registry.BLOCK, id(path), block);
        Registry.register(Registry.ITEM, id(path), new BlockItem(block, new OwoItemSettings().group(MythicMetals.TABBED_GROUP).tab(1)));
    }

    public static void block(String path, Block block, boolean fireproof) {
        if (fireproof) {
            Registry.register(Registry.BLOCK, id(path), block);
            Registry.register(Registry.ITEM, id(path), new BlockItem(block, new OwoItemSettings().group(MythicMetals.TABBED_GROUP).tab(1).fireproof()));
        } else {
            block(path, block);
        }
    }
    public static void block(String path, Block block, ItemGroup group) {
        Registry.register(Registry.BLOCK, id(path), block);
        Registry.register(Registry.ITEM, id(path), new BlockItem(block, new Item.Settings().group(group)));
    }

    public static void block(String path, Block block, ItemGroup group, boolean fireproof) {
        if (fireproof) {
            Registry.register(Registry.BLOCK, id(path), block);
            Registry.register(Registry.ITEM, id(path), new BlockItem(block, new Item.Settings().group(group).fireproof()));
        } else {
            block(path, block, group);
        }
    }

    public static void chain(String path, Block block) {
        Registry.register(Registry.BLOCK, new Identifier(MythicMetals.ADDON_ID, path), block);
        Registry.register(Registry.ITEM, new Identifier(MythicMetals.ADDON_ID, path), new BlockItem(block, new Item.Settings().group(MythicMetals.MYTHICMETALS_DECOR)));
    }

    public static void chain(String path, Block block, boolean fireproof) {
        if (fireproof) {
            Registry.register(Registry.BLOCK, new Identifier(MythicMetals.ADDON_ID, path), block);
            Registry.register(Registry.ITEM, new Identifier(MythicMetals.ADDON_ID, path), new BlockItem(block, new Item.Settings().group(MythicMetals.MYTHICMETALS_DECOR).fireproof()));
        }
        else chain(path, block);
    }

    public static void placedFeature(String name, PlacedFeature feature) {
        PlacedFeatures.register(name, feature);
    }

    public static void configuredFeature(String name, ConfiguredFeature<?, ?> feature) {
        ConfiguredFeatures.register(name, feature);
    }

}
