package nourl.mythicmetals.item;

import io.wispforest.owo.itemgroup.OwoItemSettings;
import net.minecraft.item.Item;
import net.minecraft.util.registry.Registry;
import nourl.mythicmetals.MythicMetals;
import nourl.mythicmetals.misc.RegistryHelper;

import java.util.function.Consumer;

public class ItemSet {
    private final Item ingotItem;
    private Item rawOreItem = null;
    private Item nuggetItem = null;
    private Item dustItem = null;

    private static Item.Settings createSettings(Consumer<Item.Settings> settingsProcessor) {
        final var settings = new OwoItemSettings().group(MythicMetals.TABBED_GROUP).tab(0);
        settingsProcessor.accept(settings);
        return settings;
    }

    public ItemSet() {
        this(false, settings -> {});
    }

    public ItemSet(boolean isAlloy) {
        this(isAlloy, settings -> {});
    }

    public ItemSet(boolean isAlloy, Consumer<Item.Settings> settingsConsumer) {
        this.ingotItem = makeItem(createSettings(settingsConsumer));
        if (!isAlloy) {
            this.rawOreItem = makeItem(createSettings(settingsConsumer));
        }
        if (MythicMetals.CONFIG.enableNuggets()) {
            this.nuggetItem = makeItem(createSettings(settingsConsumer));
        }
        if (MythicMetals.CONFIG.enableDusts()) {
            this.dustItem = makeItem(createSettings(settingsConsumer));
        }
    }

    public void register(String name) {
        Registry.register(Registry.ITEM, RegistryHelper.id(name + "_ingot"), ingotItem);
        if (rawOreItem != null) {
            Registry.register(Registry.ITEM, RegistryHelper.id("raw_" + name), rawOreItem);
        }
        if (nuggetItem != null) {
            Registry.register(Registry.ITEM, RegistryHelper.id(name + "_nugget"), nuggetItem);
        }
        if (dustItem != null) {
            Registry.register(Registry.ITEM, RegistryHelper.id(name + "_dust"), dustItem);
        }
    }

    public void register(String name, boolean imStarPlatinum) {
        if (imStarPlatinum) {
            Registry.register(Registry.ITEM, RegistryHelper.id(name), ingotItem);
            if (nuggetItem != null) {
                Registry.register(Registry.ITEM, RegistryHelper.id(name + "_nugget"), nuggetItem);
            }
            if (dustItem != null) {
                Registry.register(Registry.ITEM, RegistryHelper.id(name + "_dust"), dustItem);
            }
        } else {
            register(name);
        }

    }

    protected Item makeItem(Item.Settings settings) {
        return new Item(settings);
    }

    public Item getRawOre() {
        return rawOreItem;
    }

    public Item getIngot() {
        return ingotItem;
    }

    public Item getNugget() {
        return nuggetItem;
    }

    public Item getDust() {
        return dustItem;
    }
}
