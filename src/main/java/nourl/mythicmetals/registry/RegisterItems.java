package nourl.mythicmetals.registry;

import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import nourl.mythicmetals.MythicMetalsMain;

public class RegisterItems {
    public static final Item Vermiculite = new Item(new Item.Settings().group(MythicMetalsMain.MYTHICMETALS));
    public static void register() {
        Registry.register(Registry.ITEM, new Identifier("mythicmetals", "vermiculite"), Vermiculite);
    }
}
