package nourl.mythicmetals.armor;

import io.wispforest.owo.itemgroup.OwoItemSettings;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;

public class PrometheumArmorSet extends ArmorSet {
    public PrometheumArmorSet(ArmorMaterial material) {
        super(material);
    }

    @Override
    protected ArmorItem makeItem(ArmorMaterial material, ArmorItem.Type slot, OwoItemSettings settings) {
        return new PrometheumArmorItem(material, slot, settings);
    }
}
