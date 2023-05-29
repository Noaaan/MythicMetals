package nourl.mythicmetals.armor;

import io.wispforest.owo.itemgroup.OwoItemSettings;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;

import java.util.function.Consumer;

public class CarmotArmorSet extends ArmorSet {

    public CarmotArmorSet(ArmorMaterial material, Consumer<OwoItemSettings> settingsProcessor) {
        super(material, settingsProcessor);
    }

    @Override
    protected ArmorItem makeItem(ArmorMaterial material, ArmorItem.Type slot, OwoItemSettings settings) {
        if (slot == ArmorItem.Type.BOOTS || slot == ArmorItem.Type.LEGGINGS) return super.makeItem(material, slot, settings);
        return new CarmotArmor(slot, settings);
    }
}
