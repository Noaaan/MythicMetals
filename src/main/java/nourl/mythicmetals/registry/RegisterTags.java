package nourl.mythicmetals.registry;

import net.fabricmc.fabric.api.tag.TagRegistry;
import net.minecraft.item.Item;
import net.minecraft.tag.Tag;
import net.minecraft.util.Identifier;
import nourl.mythicmetals.MythicMetals;

public class RegisterTags {
    public static final Tag<Item> CARMOT_ARMOR = TagRegistry.item(new Identifier(MythicMetals.MOD_ID, "carmot_armor"));
    public static final Tag<Item> COPPER_ARMOR = TagRegistry.item(new Identifier(MythicMetals.MOD_ID, "copper_armor"));
}
