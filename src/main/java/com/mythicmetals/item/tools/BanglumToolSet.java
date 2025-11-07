package com.mythicmetals.item.tools;

import com.mythicmetals.AttributeModifier;
import net.minecraft.item.*;
import java.util.List;
import java.util.function.Consumer;

public class BanglumToolSet extends ToolSet {

    @Override
    protected PickaxeItem makePickaxe(ToolMaterial material, int damage, float speed, Item.Settings settings, List<AttributeModifier> extraModifiers) {
        return new BanglumPick(material, damage, speed, settings);
    }

    @Override
    protected ShovelItem makeShovel(ToolMaterial material, int damage, float speed, Item.Settings settings, List<AttributeModifier> extraModifiers) {
        return new BanglumShovel(material, damage, speed, settings);
    }

    public BanglumToolSet(ToolMaterial material, int[] damage, float[] speed, Consumer<Item.Settings> settingsProcessor) {
        super("legendary_banglum", material, damage, speed, settingsProcessor);
    }

}
