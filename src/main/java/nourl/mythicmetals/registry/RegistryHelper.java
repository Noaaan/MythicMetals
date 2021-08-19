package nourl.mythicmetals.registry;

import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import nourl.mythicmetals.MythicMetals;

// Contains helper methods for registering items and blocks
public class RegistryHelper {

    public static Identifier id(String path) {
        return new Identifier(MythicMetals.MOD_ID, path);
    }

    public static void registerItem(String path, Item item) {
        Registry.register(Registry.ITEM, id(path), item);
    }

    public static void registerBlock(String path, Block block) {
        Registry.register(Registry.BLOCK, new Identifier(MythicMetals.MOD_ID, path), block);
    }

    public static void registerBlockItem(String path, Block block, ItemGroup group) {
        Registry.register(Registry.ITEM, new Identifier(MythicMetals.MOD_ID, path), new BlockItem(block, new Item.Settings().group(group)));
    }

    public static void registerFireproofBlockItem(String path, Block block, ItemGroup group) {
        Registry.register(Registry.ITEM, new Identifier(MythicMetals.MOD_ID, path), new BlockItem(block, new Item.Settings().group(group).fireproof()));
    }

    public static void registerChainBlock(String path, Block block) {
        Registry.register(Registry.BLOCK, new Identifier(MythicMetals.CHAIN_ID, path), block);
    }

    public static void registerChainItem(String path, Block block) {
        Registry.register(Registry.ITEM, new Identifier(MythicMetals.CHAIN_ID, path), new BlockItem(block, new Item.Settings().group(MythicMetals.MYTHICMETALS_DECOR)));
    }
}
