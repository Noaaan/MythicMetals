package nourl.mythicmetals.registry;

import net.fabricmc.fabric.api.tag.TagFactory;
import net.minecraft.item.Item;
import net.minecraft.tag.Tag;
import nourl.mythicmetals.utils.RegistryHelper;

public class RegisterTags {
    public static final Tag<Item> CARMOT_ARMOR = TagFactory.ITEM.create(RegistryHelper.id("carmot_armor"));
    public static final Tag<Item> COPPER_ARMOR = TagFactory.ITEM.create(RegistryHelper.id("copper_armor"));
    public static final Tag<Item> PALLADIUM_ARMOR = TagFactory.ITEM.create(RegistryHelper.id("palladium_armor"));

}
