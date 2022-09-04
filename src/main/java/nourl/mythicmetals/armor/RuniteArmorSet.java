package nourl.mythicmetals.armor;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.Item;

public class RuniteArmorSet extends ArmorSet {

    public RuniteArmorSet(ArmorMaterial material) {
        super(material);
    }

    @Override
    protected ArmorItem makeItem(ArmorMaterial material, EquipmentSlot slot, Item.Settings settings) {
        if (slot != EquipmentSlot.HEAD) return super.makeItem(material, slot, settings);
        return new RuniteArmor(slot, settings);
    }
}
