package com.mythicmetals.mixin;

import com.mythicmetals.item.component.MythicDataComponents;
import com.mythicmetals.item.component.PrometheumComponent;
import net.minecraft.core.BlockPos;
import net.minecraft.core.component.DataComponents;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static com.mythicmetals.item.component.PrometheumComponent.createOvergrownModifier;
import static com.mythicmetals.item.component.PrometheumComponent.createOvergrownToughnessModifier;

@Mixin(Item.class)
public abstract class ItemMixin {

    @Inject(method = "inventoryTick", at = @At("TAIL"))
    private void mythicmetals$inventoryTick(ItemStack itemStack, ServerLevel level, Entity owner, EquipmentSlot slot, CallbackInfo ci) {
        if (level.isClientSide()) return;

        if (itemStack.has(MythicDataComponents.PROMETHEUM)) {
            PrometheumComponent.tickAutoRepair(itemStack, level);

            // TODO - I don't like this code. Look into if this can be optimized
            if (!itemStack.has(DataComponents.ATTRIBUTE_MODIFIERS)) return;
            var prometheumComponent = itemStack.get(MythicDataComponents.PROMETHEUM);
            assert prometheumComponent != null;
            var attributeComponent = itemStack.get(DataComponents.ATTRIBUTE_MODIFIERS);
            assert attributeComponent != null;

            // Handle Overgrown modifiers
            // Equippables get armor and toughness. Anything else gets extra damage
            if (prometheumComponent.isOvergrown()) {
                if (itemStack.has(DataComponents.EQUIPPABLE)) {
                    var equippableComponent = itemStack.get(DataComponents.EQUIPPABLE);
                    assert equippableComponent != null;
                    var changedComponent = attributeComponent
                        .withModifierAdded(Attributes.ARMOR, createOvergrownModifier(prometheumComponent, itemStack, 1, equippableComponent.slot()), EquipmentSlotGroup.bySlot(equippableComponent.slot()))
                        .withModifierAdded(Attributes.ARMOR_TOUGHNESS, createOvergrownToughnessModifier(prometheumComponent, itemStack, 0), EquipmentSlotGroup.bySlot(equippableComponent.slot()));
                    itemStack.set(DataComponents.ATTRIBUTE_MODIFIERS, changedComponent);
                } else if (itemStack.has(DataComponents.TOOL)) {
                    var modifier = createOvergrownModifier(prometheumComponent, itemStack, 0);
                    var changedComponent = attributeComponent.withModifierAdded(Attributes.ATTACK_DAMAGE, modifier, EquipmentSlotGroup.MAINHAND);
                    itemStack.set(DataComponents.ATTRIBUTE_MODIFIERS, changedComponent);
                }
            }
        }
    }

    @Inject(method = "mineBlock", at = @At("HEAD"), cancellable = true)
    private void mythicmetals$cancelDamageWhenDoingProperPhysicalLabor(ItemStack itemStack, Level level, BlockState state, BlockPos pos, LivingEntity owner, CallbackInfoReturnable<Boolean> cir) {
        if (itemStack.has(MythicDataComponents.SNOW_SHOVEL) && state.is(BlockTags.SNOW)) {
            cir.setReturnValue(true);
        }
    }
}
