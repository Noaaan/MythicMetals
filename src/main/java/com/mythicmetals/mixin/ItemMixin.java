package com.mythicmetals.mixin;

import com.mythicmetals.component.MythicDataComponents;
import com.mythicmetals.component.PrometheumComponent;
import com.mythicmetals.data.MythicTags;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.AttributeModifierSlot;
import net.minecraft.entity.Entity;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.item.*;
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
        if (!stack.isIn(MythicTags.AUTO_REPAIR)) return;
        if (!stack.contains(DataComponentTypes.ATTRIBUTE_MODIFIERS)) return;
        var prometheumComponent = stack.getOrDefault(MythicDataComponents.PROMETHEUM, PrometheumComponent.DEFAULT);

        // Handle Overgrown modifiers
        // Armor gets armor and toughness. Anything else gets extra damage
        if (prometheumComponent.isOvergrown()) {
            if (stack.getItem() instanceof ArmorItem item) {
                var attributeComponent = item.getAttributeModifiers();
                var changedComponent = attributeComponent
                    .with(EntityAttributes.GENERIC_ARMOR, createOvergrownModifier(stack, 1, item.getSlotType()), AttributeModifierSlot.forEquipmentSlot(item.getSlotType()))
                    .with(EntityAttributes.GENERIC_ARMOR_TOUGHNESS, createOvergrownToughnessModifier(stack, 0), AttributeModifierSlot.forEquipmentSlot(item.getSlotType()));
                stack.set(DataComponentTypes.ATTRIBUTE_MODIFIERS, changedComponent);
            }
            else if (stack.contains(DataComponentTypes.ATTRIBUTE_MODIFIERS)) {
                var attributeComponent = stack.get(DataComponentTypes.ATTRIBUTE_MODIFIERS);
                var modifier = createOvergrownModifier(stack, 0);
                var changedComponent = attributeComponent.with(EntityAttributes.GENERIC_ATTACK_DAMAGE, modifier, AttributeModifierSlot.MAINHAND);
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
