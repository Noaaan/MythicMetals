package com.mythicmetals.armor;

import net.minecraft.entity.Entity;
import net.minecraft.item.*;
import net.minecraft.world.World;
import com.mythicmetals.component.PrometheumComponent;
import com.mythicmetals.misc.RegistryHelper;

public class PrometheumArmorItem extends ArmorItem {
    public PrometheumArmorItem(ArmorMaterial material, Type type, Settings settings) {
        super(RegistryHelper.getEntry(material), type, settings);
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        PrometheumComponent.tickAutoRepair(stack, world);
        super.inventoryTick(stack, world, entity, slot, selected);
    }
}
