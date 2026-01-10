package com.mythicmetals.mixin;

import com.mythicmetals.component.MythicDataComponents;
import com.mythicmetals.component.PrometheumComponent;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.item.equipment.Equippable;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static com.mythicmetals.component.PrometheumComponent.createOvergrownModifier;
import static com.mythicmetals.component.PrometheumComponent.createOvergrownToughnessModifier;

@Mixin(Item.class)
public abstract class ItemMixin {

    @Inject(method = "postProcessComponents", at = @At("HEAD"))
    private void mythicmetals$dynamicAttributeHandler(ItemStack stack, CallbackInfo ci) {
        if (!stack.has(DataComponents.ATTRIBUTE_MODIFIERS)) return;
        if (!stack.has(MythicDataComponents.PROMETHEUM)) return;
        var prometheumComponent = stack.get(MythicDataComponents.PROMETHEUM);
        assert prometheumComponent != null;

        // Handle Overgrown modifiers
        // Equippables get armor and toughness. Anything else gets extra damage
        if (prometheumComponent.isOvergrown()) {
            if (stack.has(DataComponents.EQUIPPABLE)) {
                var attributeComponent = stack.get(DataComponents.ATTRIBUTE_MODIFIERS);
                var equippableComponent = stack.get(DataComponents.EQUIPPABLE);
                assert attributeComponent != null;
                assert equippableComponent != null;
                var changedComponent = attributeComponent
                    .withModifierAdded(Attributes.ARMOR, createOvergrownModifier(stack, 1, equippableComponent.slot()), EquipmentSlotGroup.bySlot(equippableComponent.slot()))
                    .withModifierAdded(Attributes.ARMOR_TOUGHNESS, createOvergrownToughnessModifier(stack, 0), EquipmentSlotGroup.bySlot(equippableComponent.slot()));
                stack.set(DataComponents.ATTRIBUTE_MODIFIERS, changedComponent);
            } else if (stack.has(DataComponents.TOOL)) {
                var attributeComponent = stack.get(DataComponents.ATTRIBUTE_MODIFIERS);
                assert attributeComponent != null;
                var modifier = createOvergrownModifier(stack, 0);
                var changedComponent = attributeComponent.withModifierAdded(Attributes.ATTACK_DAMAGE, modifier, EquipmentSlotGroup.MAINHAND);
                stack.set(DataComponents.ATTRIBUTE_MODIFIERS, changedComponent);
            }
        }
    }

    @Inject(method = "inventoryTick", at = @At("TAIL"))
    private void mythicmetals$inventoryTick(ItemStack stack, Level world, Entity entity, int slot, boolean selected, CallbackInfo ci) {
        if (world.isClientSide()) return;

        if (stack.has(MythicDataComponents.PROMETHEUM)) {
            PrometheumComponent.tickAutoRepair(stack, world);
        }
    }
}
