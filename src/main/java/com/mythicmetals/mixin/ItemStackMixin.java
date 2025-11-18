package com.mythicmetals.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.mythicmetals.component.BrandingComponent;
import com.mythicmetals.component.MythicDataComponents;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.util.ActionResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ItemStack.class)
public class ItemStackMixin {

    @ModifyReturnValue(method = "useOnBlock", at = @At("RETURN"))
    private ActionResult mythicmetals$handleUseableComponents(ActionResult original, ItemUsageContext context) {
        var stack = context.getStack();
        if (stack.contains(MythicDataComponents.BLAST_MINING)) {
            var component = stack.get(MythicDataComponents.BLAST_MINING);
            assert component != null;
            return component.trigger(context);
        }
        return original;
    }

    @Inject(method = "postHit", at = @At("HEAD"), cancellable = false)
    private void mythicmetals$handleCustomOnHitComponents(LivingEntity target, LivingEntity user, CallbackInfoReturnable<Boolean> cir) {
        if (user.getWeaponStack().contains(MythicDataComponents.BRANDING)) {
            var component = user.getWeaponStack().get(MythicDataComponents.BRANDING);
            assert component != null;
            component.applyHeatToTarget(target, user);
        }
    }

}
