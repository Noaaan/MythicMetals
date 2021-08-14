package nourl.mythicmetals.registry;

import net.fabricmc.fabric.api.tag.TagRegistry;
import net.minecraft.item.Item;
import net.minecraft.tag.Tag;

public class RegisterTags {
    public static final Tag<Item> CARMOT_ARMOR = TagRegistry.item(RegistryHelper.id("carmot_armor"));
    public static final Tag<Item> COPPER_ARMOR = TagRegistry.item(RegistryHelper.id("copper_armor"));
}
