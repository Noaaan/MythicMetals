package nourl.mythicmetals.tools;

import net.minecraft.item.*;

public class PrometheumToolSet extends ToolSet {

    @Override
    protected PickaxeItem makePickaxe(ToolMaterial material, int damage, float speed, Item.Settings settings) {
        return new PrometheumPick(material, damage, speed, settings);
    }

    @Override
    protected SwordItem makeSword(ToolMaterial material, int damage, float speed, Item.Settings settings) {
        return new PrometheumSword(material, damage, speed, settings);
    }

    @Override
    protected AxeItem makeAxe(ToolMaterial material, int damage, float speed, Item.Settings settings) {
        return new PrometheumAxe(material, damage, speed, settings);
    }

    @Override
    protected HoeItem makeHoe(ToolMaterial material, int damage, float speed, Item.Settings settings) {
        return new PrometheumHoe(material, damage, speed, settings);
    }

    @Override
    protected ShovelItem makeShovel(ToolMaterial material, int damage, float speed, Item.Settings settings) {
        return new PrometheumShovel(material, damage, speed, settings);
    }

    public PrometheumToolSet(ToolMaterial material, int[] damage, float[] speed) {
        super(material, damage, speed);
    }

}
