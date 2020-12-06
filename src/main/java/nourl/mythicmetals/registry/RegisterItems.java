package nourl.mythicmetals.registry;

import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import nourl.mythicmetals.MythicMetals;

public class RegisterItems {
    public static final Item Vermiculite = new Item(new Item.Settings().group(MythicMetals.MYTHICMETALS));
    public static void register() {
        Registry.register(Registry.ITEM, new Identifier(MythicMetals.MOD_ID, "vermiculite"), Vermiculite);
    }
}
