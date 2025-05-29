package com.mythicmetals.item.tools;

import net.minecraft.item.*;
import java.util.function.Consumer;

public class BanglumToolSet extends ToolSet {

    @Override
    protected PickaxeItem makePickaxe(ToolMaterial material, int damage, float speed, Item.Settings settings) {
        return new BanglumPick(material, settings.attributeModifiers(createAttributeModifiers(material, damage, speed)));
    }

    @Override
    protected ShovelItem makeShovel(ToolMaterial material, int damage, float speed, Item.Settings settings) {
        return new BanglumShovel(material, settings.attributeModifiers(createAttributeModifiers(material, damage, speed)));
    }

    public BanglumToolSet(ToolMaterial material, int[] damage, float[] speed, Consumer<Item.Settings> settingsProcessor) {
        super(material, damage, speed, settingsProcessor);
    }

}
