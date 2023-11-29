package nourl.mythicmetals.armor;

import io.wispforest.owo.itemgroup.OwoItemSettings;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;

import java.util.function.Consumer;

public class TidesingerArmorSet extends ArmorSet {

    public TidesingerArmorSet(ArmorMaterial material, Consumer<OwoItemSettings> settingsProcessor) {
        super(material, settingsProcessor);
    }

    @Override
    protected ArmorItem makeItem(ArmorMaterial material, ArmorItem.Type slot, OwoItemSettings settings) {
        return new TidesingerArmor(slot, settings);
    }
}
