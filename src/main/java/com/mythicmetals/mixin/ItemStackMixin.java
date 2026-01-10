package com.mythicmetals.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.mythicmetals.component.MythicDataComponents;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ItemStack.class)
public class ItemStackMixin {

    @ModifyReturnValue(method = "useOnBlock", at = @At("RETURN"))
    private InteractionResult mythicmetals$handleUseableComponents(InteractionResult original, UseOnContext context) {
        var stack = context.getItemInHand();
        if (stack.has(MythicDataComponents.BLAST_MINING)) {
            var component = stack.get(MythicDataComponents.BLAST_MINING);
            assert component != null;
            return component.trigger(context);
        }
        return original;
    }

    @Inject(method = "postHit", at = @At("HEAD"), cancellable = false)
    private void mythicmetals$handleCustomOnHitComponents(LivingEntity target, LivingEntity user, CallbackInfoReturnable<Boolean> cir) {
        if (user.getWeaponItem().has(MythicDataComponents.BRANDING)) {
            var component = user.getWeaponItem().get(MythicDataComponents.BRANDING);
            assert component != null;
            component.applyHeatToTarget(target, user);
        }
    }

}
