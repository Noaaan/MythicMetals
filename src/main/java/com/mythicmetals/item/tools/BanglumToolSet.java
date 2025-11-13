package com.mythicmetals.item.tools;

import com.mythicmetals.AttributeModifier;
import com.mythicmetals.component.BlastMiningComponent;
import com.mythicmetals.component.MythicDataComponents;
import net.minecraft.component.type.AttributeModifierSlot;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.item.*;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;
import java.util.List;
import java.util.function.Consumer;

import static com.mythicmetals.component.MythicDataComponents.BLAST_MINING;

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
        return new PickaxeMock(material, damage, speed, settings.component(BLAST_MINING, new BlastMiningComponent(5)), extraModifiers) {
            @Override
            public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> lines, TooltipType type) {
                super.appendTooltip(stack, context, lines, type);
                if (stack.contains(BLAST_MINING)) {
                    //noinspection DataFlowIssue
                    stack.get(BLAST_MINING).appendTooltip(context, lines::add, type);
                }
            }
        };
    }

    @Override
    protected ShovelItem makeShovel(ToolMaterial material, int damage, float speed, Item.Settings settings, List<AttributeModifier> extraModifiers) {
        return new ShovelMock(material, damage, speed, settings.component(BLAST_MINING, new BlastMiningComponent(5)), extraModifiers) {
            @Override
            public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> lines, TooltipType type) {
                super.appendTooltip(stack, context, lines, type);
                if (stack.contains(BLAST_MINING)) {
                    //noinspection DataFlowIssue
                    stack.get(BLAST_MINING).appendTooltip(context, lines::add, type);
                }
            }
        };
    }

    public BanglumToolSet(ToolMaterial material, int[] damage, float[] speed, Consumer<Item.Settings> settingsProcessor) {
        super("legendary_banglum", material, damage, speed, settingsProcessor);
    }

}
