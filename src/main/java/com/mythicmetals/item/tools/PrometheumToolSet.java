package com.mythicmetals.item.tools;

import com.mythicmetals.MythicAttributeModifier;
import com.mythicmetals.component.MythicDataComponents;
import com.mythicmetals.component.PrometheumComponent;

import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.HoeItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.ShovelItem;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.ToolMaterial;
import java.util.List;

public class PrometheumToolSet extends ToolSet {

    public PrometheumToolSet(ToolMaterial material, int[] damage, float[] speed) {
        super("prometheum", material, damage, speed);
    }

    @Override
    protected PickaxeItem makePickaxe(ToolMaterial material, int damage, float speed, Item.Properties settings, List<MythicAttributeModifier> extraModifiers) {
        return new PrometheumPick(material, damage, speed, settings
            .component(MythicDataComponents.PROMETHEUM, PrometheumComponent.DEFAULT)
        );
    }

    @Override
    protected SwordItem makeSword(ToolMaterial material, int damage, float speed, Item.Properties settings, List<MythicAttributeModifier> extraModifiers) {
        return new PrometheumSword(material, damage, speed, settings
            .component(MythicDataComponents.PROMETHEUM, PrometheumComponent.DEFAULT)
        );
    }

    @Override
    protected AxeItem makeAxe(ToolMaterial material, int damage, float speed, Item.Properties settings, List<MythicAttributeModifier> extraModifiers) {
        return new PrometheumAxe(material, damage, speed, settings
            .component(MythicDataComponents.PROMETHEUM, PrometheumComponent.DEFAULT)
        );
    }

    @Override
    protected HoeItem makeHoe(ToolMaterial material, int damage, float speed, Item.Properties settings, List<MythicAttributeModifier> extraModifiers) {
        return new PrometheumHoe(material, damage, speed, settings
            .component(MythicDataComponents.PROMETHEUM, PrometheumComponent.DEFAULT)
        );
    }

    @Override
    protected ShovelItem makeShovel(ToolMaterial material, int damage, float speed, Item.Properties settings, List<MythicAttributeModifier> extraModifiers) {
        return new PrometheumShovel(material, damage, speed, settings
            .component(MythicDataComponents.PROMETHEUM, PrometheumComponent.DEFAULT)
        );
    }

    public static class PrometheumAxe extends AxeItem implements AutoRepairable {
        public PrometheumAxe(ToolMaterial material, int damage, float speed, Item.Properties settings) {
            super(material, damage, speed, settings);
        }

    }

    public static class PrometheumHoe extends HoeItem implements AutoRepairable {
        public PrometheumHoe(ToolMaterial material, int damage, float speed, Item.Properties settings) {
            super(material, damage, speed, settings);
        }

    }

    public static class PrometheumPick extends PickaxeItem implements AutoRepairable {
        public PrometheumPick(ToolMaterial material, int damage, float speed, Item.Properties settings) {
            super(material, damage, speed, settings);
        }

    }

    public static class PrometheumShovel extends ShovelItem implements AutoRepairable {
        public PrometheumShovel(ToolMaterial material, int damage, float speed, Item.Properties settings) {
            super(material, damage, speed, settings);
        }

    }

    public static class PrometheumSword extends SwordItem implements AutoRepairable {
        public PrometheumSword(ToolMaterial material, int damage, float speed, Item.Properties settings) {
            super(material, damage, speed, settings);
        }

    }

}
