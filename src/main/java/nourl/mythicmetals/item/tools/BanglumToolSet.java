package nourl.mythicmetals.item.tools;

import net.minecraft.item.Item;
import net.minecraft.item.PickaxeItem;
import net.minecraft.item.ShovelItem;
import net.minecraft.item.ToolMaterial;

import java.util.function.Consumer;

public class BanglumToolSet extends ToolSet {

    @Override
    protected PickaxeItem makePickaxe(ToolMaterial material, int damage, float speed, Item.Settings settings) {
        return new BanglumPick(material, damage, speed, settings);
    }

    @Override
    protected ShovelItem makeShovel(ToolMaterial material, int damage, float speed, Item.Settings settings) {
        return new BanglumShovel(material, damage, speed, settings);
    }

    public BanglumToolSet(ToolMaterial material, int[] damage, float[] speed, Consumer<Item.Settings> settingsProcessor) {
        super(material, damage, speed, settingsProcessor);
    }

}
