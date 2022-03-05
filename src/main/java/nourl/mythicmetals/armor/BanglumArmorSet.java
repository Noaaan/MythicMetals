package nourl.mythicmetals.armor;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.Item;

import java.util.function.Consumer;

public class BanglumArmorSet extends ArmorSet {

    public BanglumArmorSet(ArmorMaterial material, Consumer<Item.Settings> settingsProcessor) {
        super(material);
    }

    @Override
    protected ArmorItem makeItem(ArmorMaterial material, EquipmentSlot slot, Item.Settings settings) {
        if (slot != EquipmentSlot.HEAD) return super.makeItem(material, slot, settings);
        return new BanglumArmor(slot, settings);
    }
}
