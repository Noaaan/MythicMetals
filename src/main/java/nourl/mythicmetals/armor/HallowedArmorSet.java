package nourl.mythicmetals.armor;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.Item;

import java.util.function.Consumer;

public class HallowedArmorSet extends ArmorSet {

    public HallowedArmorSet(ArmorMaterial material, Consumer<Item.Settings> settingsProcessor) {
        super(material);
    }

    @Override
    protected ArmorItem makeItem(ArmorMaterial material, EquipmentSlot slot, Item.Settings settings) {
        if (slot != EquipmentSlot.HEAD && slot != EquipmentSlot.CHEST) return super.makeItem(material, slot, settings);
        return new HallowedArmor(slot, settings);
    }
}
