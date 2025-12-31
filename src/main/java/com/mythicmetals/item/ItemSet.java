package com.mythicmetals.item;

import com.mythicmetals.MythicMetals;
import com.mythicmetals.misc.RegistryHelper;
import io.wispforest.owo.util.TagInjector;
import net.minecraft.item.Item;
import net.minecraft.registry.*;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;
import java.util.function.Consumer;

import static com.mythicmetals.misc.RegistryHelper.itemKey;

public class ItemSet {
    private final Item ingotItem;
    private Item rawOreItem = null;
    private Item nuggetItem = null;
    private boolean requiresBlasting = false;
    // Used for smelting recipes during datagen
    private final float xp;
    private final String name;

    private static Item.Settings createSettings(RegistryKey<Item> registryKey, Consumer<Item.Settings> settingsProcessor) {
        final var settings = new Item.Settings()
            .registryKey(registryKey)
            .group(MythicMetals.TABBED_GROUP)
            .tab(0);
        settingsProcessor.accept(settings);
        return settings;
    }

    public ItemSet(String name, float xp) {
        this(name, false, false, xp, settings -> {
        });
    }

    public ItemSet(String name, float xp, boolean requiresBlasting) {
        this(name, false, requiresBlasting, xp, settings -> {
        });
    }

    public ItemSet(String name, boolean isAlloy) {
        this(name, isAlloy, true, 0.1f, settings -> {
        });
    }

    public ItemSet(String name, boolean isAlloy, float xp) {
        this(name, isAlloy, false, xp, settings -> {
        });
    }

    public ItemSet(String name, boolean isAlloy, float xp, boolean requiresBlasting) {
        this(name, isAlloy, requiresBlasting, xp, settings -> {
        });
    }

    public ItemSet(String name, boolean isAlloy, boolean requiresBlasting, Consumer<Item.Settings> settingsConsumer) {
        this(name, isAlloy, requiresBlasting, 0.1f, settingsConsumer);
    }

    public ItemSet(String name, boolean isAlloy, boolean requiresBlasting, float xp, Consumer<Item.Settings> settingsConsumer) {
        this.ingotItem = makeItem(createSettings(itemKey(name + "_ingot"), settingsConsumer));
        this.name = name;
        if (!isAlloy) {
            this.rawOreItem = makeItem(createSettings(itemKey("raw_" + name), settingsConsumer));
        }
        if (MythicMetals.CONFIG.enableNuggets()) {
            this.nuggetItem = makeItem(createSettings(itemKey(name + "_nugget"), settingsConsumer));
        }
        this.xp = xp;
        this.requiresBlasting = requiresBlasting;
    }

    public void register(String name) {
        Registry.register(Registries.ITEM, RegistryHelper.id(name + "_ingot"), ingotItem);
        if (rawOreItem != null) {
            Registry.register(Registries.ITEM, RegistryHelper.id("raw_" + name), rawOreItem);
        }
        if (nuggetItem != null) {
            Registry.register(Registries.ITEM, RegistryHelper.id(name + "_nugget"), nuggetItem);
            // Conditionally add nuggets to nuggets tag
            TagInjector.inject(Registries.ITEM, Identifier.of("c", "nuggets"), nuggetItem);
        }
    }

    protected Item makeItem(Item.Settings settings) {
        return new Item(settings);
    }

    @Nullable
    public Item getRawOre() {
        return rawOreItem;
    }

    public Item getIngot() {
        return ingotItem;
    }

    @Nullable
    public Item getNugget() {
        return nuggetItem;
    }

    public String getName() {
        return name;
    }

    public boolean requiresBlasting() {
        return requiresBlasting;
    }

    public float getXp() {
        return this.xp > 0 ? this.xp : 0.0f;
    }
}
