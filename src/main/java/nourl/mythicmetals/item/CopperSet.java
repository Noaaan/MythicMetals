package nourl.mythicmetals.item;

import io.wispforest.owo.itemgroup.OwoItemSettings;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import nourl.mythicmetals.MythicMetals;
import nourl.mythicmetals.misc.RegistryHelper;

import java.util.function.Consumer;

public class CopperSet {
    private Item nuggetItem = null;
    private Item dustItem = null;

    private static Item.Settings createSettings(Consumer<Item.Settings> settingsProcessor) {
        final var settings = new OwoItemSettings().group(MythicMetals.TABBED_GROUP).tab(0);
        settingsProcessor.accept(settings);
        return settings;
    }

    public CopperSet() {
        this(settings -> {});
    }

    public CopperSet(Consumer<Item.Settings> settingsConsumer) {
        if (MythicMetals.CONFIG.enableNuggets()) {
            this.nuggetItem = makeItem(createSettings(settingsConsumer));
        }
        if (MythicMetals.CONFIG.enableDusts()) {
            this.dustItem = makeItem(createSettings(settingsConsumer));
        }
    }

    public void register(String name) {
        if (nuggetItem != null) {
            Registry.register(Registries.ITEM, RegistryHelper.id(name + "_nugget"), nuggetItem);
        }
        if (dustItem != null) {
            Registry.register(Registries.ITEM, RegistryHelper.id(name + "_dust"), dustItem);
        }
    }

    protected Item makeItem(Item.Settings settings) {
        return new Item(settings);
    }

    public Item getNugget() {
        return nuggetItem;
    }

    public Item getDust() {
        return dustItem;
    }
}
