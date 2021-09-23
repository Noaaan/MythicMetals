package nourl.mythicmetals.utils;

import com.glisco.owo.itemgroup.OwoItemSettings;
import net.minecraft.block.AnvilBlock;
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
import nourl.mythicmetals.blocks.MythicBlocks;

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
        Registry.register(Registry.ITEM, id(path), new BlockItem(block, new OwoItemSettings().group(MythicMetals.MAIN).tab(1)));
    }

    public static void block(String path, Block block, boolean fireproof) {
        if (fireproof) {
            Registry.register(Registry.BLOCK, id(path), block);
            Registry.register(Registry.ITEM, id(path), new BlockItem(block, new OwoItemSettings().group(MythicMetals.MAIN).tab(1)));
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

    public static void anvil(String path, AnvilBlock block) {
        Registry.register(Registry.BLOCK, new Identifier(MythicMetals.MOD_ID, path), block);
        Registry.register(Registry.ITEM, new Identifier(MythicMetals.MOD_ID, path), new BlockItem(block, new OwoItemSettings().group(MythicMetals.MAIN).tab(1)));
        MythicBlocks.ANVILS.add(block);
    }

    public static void anvil(String path, AnvilBlock block, boolean fireproof) {
        if (fireproof) {
            Registry.register(Registry.BLOCK, new Identifier(MythicMetals.MOD_ID, path), block);
            Registry.register(Registry.ITEM, new Identifier(MythicMetals.MOD_ID, path), new BlockItem(block, new OwoItemSettings().group(MythicMetals.MAIN).tab(1).fireproof()));
            MythicBlocks.ANVILS.add(block);
        }
        else anvil(path, block);
    }

    public static RegistryKey<ConfiguredFeature<?, ?>> registerKey(String string) {
        return RegistryKey.of(Registry.CONFIGURED_FEATURE_KEY, id(string));
    }

    public static void registerFeature(Identifier identifier, ConfiguredFeature<?, ?> feature) {
        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, identifier, feature);
    }
}
