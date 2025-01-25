package nourl.mythicmetals.item.tools;

import net.minecraft.item.*;
import nourl.mythicmetals.component.MythicDataComponents;
import nourl.mythicmetals.component.PrometheumComponent;

public class PrometheumToolSet extends ToolSet {

    public PrometheumToolSet(ToolMaterial material, int[] damage, float[] speed) {
        super(material, damage, speed);
    }

    @Override
    protected PickaxeItem makePickaxe(ToolMaterial material, int damage, float speed, Item.Settings settings) {
        return new PrometheumPick(material, settings
            .attributeModifiers(createAttributeModifiers(material, damage, speed))
            .component(MythicDataComponents.PROMETHEUM, PrometheumComponent.DEFAULT)
        );
    }

    @Override
    protected SwordItem makeSword(ToolMaterial material, int damage, float speed, Item.Settings settings) {
        return new PrometheumSword(material, settings
            .attributeModifiers(createAttributeModifiers(material, damage, speed))
            .component(MythicDataComponents.PROMETHEUM, PrometheumComponent.DEFAULT)
        );
    }

    @Override
    protected AxeItem makeAxe(ToolMaterial material, int damage, float speed, Item.Settings settings) {
        return new PrometheumAxe(material, settings
            .attributeModifiers(createAttributeModifiers(material, damage, speed))
            .component(MythicDataComponents.PROMETHEUM, PrometheumComponent.DEFAULT)
        );
    }

    @Override
    protected HoeItem makeHoe(ToolMaterial material, int damage, float speed, Item.Settings settings) {
        return new PrometheumHoe(material, settings
            .attributeModifiers(createAttributeModifiers(material, damage, speed))
            .component(MythicDataComponents.PROMETHEUM, PrometheumComponent.DEFAULT)
        );
    }

    @Override
    protected ShovelItem makeShovel(ToolMaterial material, int damage, float speed, Item.Settings settings) {
        return new PrometheumShovel(material, settings
            .attributeModifiers(createAttributeModifiers(material, damage, speed))
            .component(MythicDataComponents.PROMETHEUM, PrometheumComponent.DEFAULT)
        );
    }

    public static class PrometheumAxe extends AxeItem implements AutoRepairable {
        public PrometheumAxe(ToolMaterial material, Item.Settings settings) {
            super(material, settings);
        }

    }

    public static class PrometheumHoe extends HoeItem implements AutoRepairable {
        public PrometheumHoe(ToolMaterial material, Item.Settings settings) {
            super(material, settings);
        }

    }

    public static class PrometheumPick extends PickaxeItem implements AutoRepairable {
        public PrometheumPick(ToolMaterial material, Item.Settings settings) {
            super(material, settings);
        }

    }

    public static class PrometheumShovel extends ShovelItem implements AutoRepairable {
        public PrometheumShovel(ToolMaterial material, Item.Settings settings) {
            super(material, settings);
        }

    }

    public static class PrometheumSword extends SwordItem implements AutoRepairable {
        public PrometheumSword(ToolMaterial material, Item.Settings settings) {
            super(material, settings);
        }

    }

}
