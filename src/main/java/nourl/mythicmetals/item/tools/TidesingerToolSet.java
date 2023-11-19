package nourl.mythicmetals.item.tools;

import net.minecraft.item.Item;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;

public class TidesingerToolSet extends ToolSet {
    public TidesingerToolSet(ToolMaterial material, int[] damage, float[] speed) {
        super(material, damage, speed);
    }

    @Override
    protected SwordItem makeSword(ToolMaterial material, int damage, float speed, Item.Settings settings) {
        return new TidesingerSword(material, damage, speed, settings);
    }
}
