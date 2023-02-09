package nourl.mythicmetals.armor;

import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.Item;

import java.util.function.Consumer;

public class MetallurgiumArmorSet extends ArmorSet {

    public MetallurgiumArmorSet(ArmorMaterial material, Consumer<Item.Settings> settingsProcessor) {
        super(material, settingsProcessor);
    }

    @Override
    protected ArmorItem makeItem(ArmorMaterial material, ArmorItem.Type slot, Item.Settings settings) {
        if (slot != ArmorItem.Type.HELMET) return super.makeItem(material, slot, settings);
        return new MetallurgiumArmor(slot, settings);
    }
}
