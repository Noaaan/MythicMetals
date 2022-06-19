package nourl.mythicmetals.registry;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.tag.Tag;
import net.minecraft.tag.TagKey;
import net.minecraft.util.registry.Registry;
import nourl.mythicmetals.utils.RegistryHelper;

public class RegisterTags {
    public static final TagKey<Item> CARMOT_ARMOR = TagKey.of(Registry.ITEM_KEY, RegistryHelper.id("carmot_armor"));
    public static final TagKey<Item> COPPER_ARMOR = TagKey.of(Registry.ITEM_KEY, RegistryHelper.id("copper_armor"));
    public static final TagKey<Item> PALLADIUM_ARMOR = TagKey.of(Registry.ITEM_KEY, RegistryHelper.id("palladium_armor"));
    public static final TagKey<Item> PROMETHEUM_ARMOR = TagKey.of(Registry.ITEM_KEY, RegistryHelper.id("prometheum_armor"));
    public static final TagKey<Item> PROMETHEUM_TOOLS = TagKey.of(Registry.ITEM_KEY, RegistryHelper.id("prometheum_tools"));

    public static final TagKey<Item> CARMOT_STAFF_BLOCKS = TagKey.of(Registry.ITEM_KEY, RegistryHelper.id("carmot_staff_blocks"));
    public static final TagKey<Block> MYTHIC_ORES = TagKey.of(Registry.BLOCK_KEY, RegistryHelper.id("ores"));


}
