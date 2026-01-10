package com.mythicmetals.item;

import com.mythicmetals.MythicMetals;
import com.mythicmetals.misc.RegistryHelper;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.Item;

public class CopperSet {
    private Item nuggetItem = null;
    private Item dustItem = null;

    private static Item.Properties createSettings(String name) {
        return new Item.Properties().setId(RegistryHelper.itemKey(name)).group(MythicMetals.TABBED_GROUP).tab(0);
    }

    public CopperSet() {
        if (MythicMetals.CONFIG.enableNuggets()) {
            this.nuggetItem = makeItem(createSettings("copper_nugget").modelId(RegistryHelper.id("copper_nugget")));
        }
    }

    public void register(String name) {
        if (nuggetItem != null) {
            Registry.register(BuiltInRegistries.ITEM, RegistryHelper.id(name + "_nugget"), nuggetItem);
        }
        if (dustItem != null) {
            Registry.register(BuiltInRegistries.ITEM, RegistryHelper.id(name + "_dust"), dustItem);
        }
    }

    protected Item makeItem(Item.Properties settings) {
        return new Item(settings);
    }

    public Item getNugget() {
        return nuggetItem;
    }

    public Item getDust() {
        return dustItem;
    }
}
