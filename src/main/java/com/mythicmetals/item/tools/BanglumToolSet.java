package com.mythicmetals.item.tools;

import com.mythicmetals.MythicArmorAttributeModifier;
import com.mythicmetals.component.BlastMiningComponent;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.*;
import java.util.List;
import java.util.function.Consumer;

import static com.mythicmetals.component.MythicDataComponents.BLAST_MINING;

public class BanglumToolSet extends ToolSet {
    public static final List<MythicArmorAttributeModifier> LEGENDARY_BANGLUM_MODIFIERS = List.of(new MythicArmorAttributeModifier(
        Attributes.ATTACK_KNOCKBACK, 6.0, net.minecraft.world.entity.ai.attributes.AttributeModifier.Operation.ADD_VALUE, EquipmentSlotGroup.MAINHAND
    ));

    @Override
    protected Item makeSword(ToolMaterial material, int damage, float speed, Item.Properties settings, List<MythicArmorAttributeModifier> extraModifiers) {
        return super.makeSword(material, damage, speed, settings, LEGENDARY_BANGLUM_MODIFIERS);
    }

    @Override
    protected AxeItem makeAxe(ToolMaterial material, int damage, float speed, Item.Properties settings, List<MythicArmorAttributeModifier> extraModifiers) {
        return super.makeAxe(material, damage, speed, settings, LEGENDARY_BANGLUM_MODIFIERS);
    }

    @Override
    protected HoeItem makeHoe(ToolMaterial material, int damage, float speed, Item.Properties settings, List<MythicArmorAttributeModifier> extraModifiers) {
        return super.makeHoe(material, damage, speed, settings, LEGENDARY_BANGLUM_MODIFIERS);
    }

    @Override
    protected Item makePickaxe(ToolMaterial material, int damage, float speed, Item.Properties settings, List<MythicArmorAttributeModifier> extraModifiers) {
        return new PickaxeMock(material, damage, speed, settings.component(BLAST_MINING, new BlastMiningComponent(5)), extraModifiers) {
//            @Override
//            public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> lines, TooltipFlag type) {
//                super.appendHoverText(stack, context, lines, type);
//                if (stack.has(BLAST_MINING)) {
//                    //noinspection DataFlowIssue
//                    stack.get(BLAST_MINING).addToTooltip(context, lines::add, type);
//                }
//            }
        };
    }

    @Override
    protected ShovelItem makeShovel(ToolMaterial material, int damage, float speed, Item.Properties settings, List<MythicArmorAttributeModifier> extraModifiers) {
        return new ShovelMock(material, damage, speed, settings.component(BLAST_MINING, new BlastMiningComponent(5)), extraModifiers) {
//            @Override
//            public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> lines, TooltipFlag type) {
//                super.appendHoverText(stack, context, lines, type);
//                if (stack.has(BLAST_MINING)) {
//                    //noinspection DataFlowIssue
//                    stack.get(BLAST_MINING).addToTooltip(context, lines::add, type);
//                }
//            }
        };
    }

    public BanglumToolSet(ToolMaterial material, int[] damage, float[] speed, Consumer<Item.Properties> settingsProcessor) {
        super("legendary_banglum", material, damage, speed, settingsProcessor);
    }

}
