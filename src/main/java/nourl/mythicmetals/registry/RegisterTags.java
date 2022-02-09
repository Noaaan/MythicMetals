package nourl.mythicmetals.registry;

import net.fabricmc.fabric.api.tag.TagFactory;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.tag.Tag;
import nourl.mythicmetals.utils.RegistryHelper;

public class RegisterTags {
    public static final Tag<Item> CARMOT_ARMOR = TagFactory.ITEM.create(RegistryHelper.id("carmot_armor"));
    public static final Tag<Item> COPPER_ARMOR = TagFactory.ITEM.create(RegistryHelper.id("copper_armor"));
    public static final Tag<Item> PALLADIUM_ARMOR = TagFactory.ITEM.create(RegistryHelper.id("palladium_armor"));
    public static final Tag<Item> PROMETHEUM_ARMOR = TagFactory.ITEM.create(RegistryHelper.id("prometheum_armor"));
    public static final Tag<Item> PROMETHEUM_TOOLS = TagFactory.ITEM.create(RegistryHelper.id("prometheum_tools"));

    public static final Tag<Block> MYTHIC_ORES = TagFactory.BLOCK.create(RegistryHelper.id("ores"));

}
