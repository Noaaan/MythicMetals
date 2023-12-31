package nourl.mythicmetals.armor;

import net.minecraft.entity.Entity;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import nourl.mythicmetals.item.tools.PrometheumToolSet;

public class PrometheumArmorItem extends ArmorItem {
    public PrometheumArmorItem(ArmorMaterial material, Type type, Settings settings) {
        super(material, type, settings);
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        if (!world.isClient()) PrometheumToolSet.tickAutoRepair(stack, world.getRandom());
        super.inventoryTick(stack, world, entity, slot, selected);
    }
}
