package com.mythicmetals.item.tools;

import com.mythicmetals.AttributeModifier;
import net.minecraft.component.type.AttributeModifierSlot;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.item.*;
import java.util.List;
import java.util.function.Consumer;

public class BanglumToolSet extends ToolSet {
    public static final List<AttributeModifier> LEGENDARY_BANGLUM_MODIFIERS = List.of(new AttributeModifier(
        EntityAttributes.ATTACK_KNOCKBACK, 6.0, EntityAttributeModifier.Operation.ADD_VALUE, AttributeModifierSlot.MAINHAND
    ));

    @Override
    protected SwordItem makeSword(ToolMaterial material, int damage, float speed, Item.Settings settings, List<AttributeModifier> extraModifiers) {
        return super.makeSword(material, damage, speed, settings, LEGENDARY_BANGLUM_MODIFIERS);
    }

    @Override
    protected AxeItem makeAxe(ToolMaterial material, int damage, float speed, Item.Settings settings, List<AttributeModifier> extraModifiers) {
        return super.makeAxe(material, damage, speed, settings, LEGENDARY_BANGLUM_MODIFIERS);
    }

    @Override
    protected HoeItem makeHoe(ToolMaterial material, int damage, float speed, Item.Settings settings, List<AttributeModifier> extraModifiers) {
        return super.makeHoe(material, damage, speed, settings, LEGENDARY_BANGLUM_MODIFIERS);
    }

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
