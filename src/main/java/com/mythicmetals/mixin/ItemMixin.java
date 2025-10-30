package com.mythicmetals.mixin;

import com.mythicmetals.component.MythicDataComponents;
import com.mythicmetals.component.PrometheumComponent;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Item.class)
public abstract class ItemMixin {

    @Inject(method = "postProcessComponents", at = @At("HEAD"))
    private void mythicmetals$dynamicAttributeHandler(ItemStack stack, CallbackInfo ci) {
        // FIXME
//        if (!stack.isIn(MythicTags.AUTO_REPAIR)) return;
//        if (!stack.contains(DataComponentTypes.ATTRIBUTE_MODIFIERS)) return;
//        var prometheumComponent = stack.getOrDefault(MythicDataComponents.PROMETHEUM, PrometheumComponent.DEFAULT);
//
//        // Handle Overgrown modifiers
//        // Armor gets armor and toughness. Anything else gets extra damage
//        if (prometheumComponent.isOvergrown()) {
//            if (stack.isIn(ConventionalItemTags.ARMORS) && stack.contains(DataComponentTypes.ATTRIBUTE_MODIFIERS) && stack.contains(DataComponentTypes.EQUIPPABLE)) {
//                var attributeComponent = stack.get(DataComponentTypes.ATTRIBUTE_MODIFIERS);
//                var equippableComponent = stack.get(DataComponentTypes.EQUIPPABLE);
//                assert attributeComponent != null;
//                assert equippableComponent != null;
//                var changedComponent = attributeComponent
//                    .with(EntityAttributes.ARMOR, createOvergrownModifier(stack, 1, equippableComponent.slot()), AttributeModifierSlot.forEquipmentSlot(equippableComponent.slot()))
//                    .with(EntityAttributes.ARMOR_TOUGHNESS, createOvergrownToughnessModifier(stack, 0), AttributeModifierSlot.forEquipmentSlot(equippableComponent.slot()));
//                stack.set(DataComponentTypes.ATTRIBUTE_MODIFIERS, changedComponent);
//            }
//            else if (stack.contains(DataComponentTypes.ATTRIBUTE_MODIFIERS)) {
//                var attributeComponent = stack.get(DataComponentTypes.ATTRIBUTE_MODIFIERS);
//                assert attributeComponent != null;
//                var modifier = createOvergrownModifier(stack, 0);
//                var changedComponent = attributeComponent.with(EntityAttributes.ATTACK_DAMAGE, modifier, AttributeModifierSlot.MAINHAND);
//                stack.set(DataComponentTypes.ATTRIBUTE_MODIFIERS, changedComponent);
//            }
//        }
    }

    @Inject(method = "inventoryTick", at = @At("TAIL"))
    private void mythicmetals$inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected, CallbackInfo ci) {
        if (world.isClient()) return;

        if (stack.contains(MythicDataComponents.PROMETHEUM)) {
            PrometheumComponent.tickAutoRepair(stack, world);
        }
    }
}
