package nourl.mythicmetals.utils;

import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import nourl.mythicmetals.MythicMetals;

// Contains helper methods for registering items and blocks.
// Note that it is not used for creating new items or blocks, only registering.
// Written by Noaaan
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
    }

    public static void chainItem(String path, Block block) {
        Registry.register(Registry.ITEM, new Identifier(MythicMetals.ADDON_ID, path), new BlockItem(block, new Item.Settings().group(MythicMetals.MYTHICMETALS_DECOR)));
    }

    public static RegistryKey<ConfiguredFeature<?, ?>> registerKey(String string) {
        return RegistryKey.of(Registry.CONFIGURED_FEATURE_KEY, id(string));
    }

    public static void registerFeature(Identifier identifier, ConfiguredFeature<?, ?> feature) {
        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, identifier, feature);
    }
}
