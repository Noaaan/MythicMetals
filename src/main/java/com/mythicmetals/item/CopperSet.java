package com.mythicmetals.item;

import com.mythicmetals.MythicMetals;
import com.mythicmetals.misc.RegistryHelper;
import net.minecraft.item.Item;
import net.minecraft.registry.*;

public class CopperSet {
    private Item nuggetItem = null;
    private Item dustItem = null;

    private static Item.Settings createSettings(String name) {
        return new Item.Settings().registryKey(RegistryHelper.itemKey(name)).group(MythicMetals.TABBED_GROUP).tab(0);
    }

    public CopperSet() {
        if (MythicMetals.CONFIG.enableNuggets()) {
            this.nuggetItem = makeItem(createSettings("copper_nugget").modelId(RegistryHelper.id("copper_nugget")));
        }
        if (MythicMetals.CONFIG.enableDusts()) {
            this.dustItem = makeItem(createSettings("copper_dust").modelId(RegistryHelper.id("copper_dust")));
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
