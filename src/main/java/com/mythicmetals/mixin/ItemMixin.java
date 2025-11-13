package com.mythicmetals.mixin;

import com.mythicmetals.component.MythicDataComponents;
import com.mythicmetals.component.PrometheumComponent;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.AttributeModifierSlot;
import net.minecraft.entity.Entity;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
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
        if (!stack.contains(DataComponentTypes.ATTRIBUTE_MODIFIERS)) return;
        if (!stack.contains(MythicDataComponents.PROMETHEUM)) return;
        var prometheumComponent = stack.get(MythicDataComponents.PROMETHEUM);
        assert prometheumComponent != null;

        // Handle Overgrown modifiers
        // Equippables get armor and toughness. Anything else gets extra damage
        if (prometheumComponent.isOvergrown()) {
            if (stack.contains(DataComponentTypes.EQUIPPABLE)) {
                var attributeComponent = stack.get(DataComponentTypes.ATTRIBUTE_MODIFIERS);
                var equippableComponent = stack.get(DataComponentTypes.EQUIPPABLE);
                assert attributeComponent != null;
                assert equippableComponent != null;
                var changedComponent = attributeComponent
                    .with(EntityAttributes.ARMOR, createOvergrownModifier(stack, 1, equippableComponent.slot()), AttributeModifierSlot.forEquipmentSlot(equippableComponent.slot()))
                    .with(EntityAttributes.ARMOR_TOUGHNESS, createOvergrownToughnessModifier(stack, 0), AttributeModifierSlot.forEquipmentSlot(equippableComponent.slot()));
                stack.set(DataComponentTypes.ATTRIBUTE_MODIFIERS, changedComponent);
            }
            else if (stack.contains(DataComponentTypes.TOOL)) {
                var attributeComponent = stack.get(DataComponentTypes.ATTRIBUTE_MODIFIERS);
                assert attributeComponent != null;
                var modifier = createOvergrownModifier(stack, 0);
                var changedComponent = attributeComponent.with(EntityAttributes.ATTACK_DAMAGE, modifier, AttributeModifierSlot.MAINHAND);
                stack.set(DataComponentTypes.ATTRIBUTE_MODIFIERS, changedComponent);
            }
        }
    }

    @Inject(method = "inventoryTick", at = @At("TAIL"))
    private void mythicmetals$inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected, CallbackInfo ci) {
        if (world.isClient()) return;

        if (stack.contains(MythicDataComponents.PROMETHEUM)) {
            PrometheumComponent.tickAutoRepair(stack, world);
        }
    }
}
