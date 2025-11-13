package com.mythicmetals.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.mythicmetals.component.MythicDataComponents;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.util.ActionResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(ItemStack.class)
public class ItemStackMixin {

    @ModifyReturnValue(method = "useOnBlock", at = @At("RETURN"))
    private ActionResult mythicmetals$handleCustomComponents(ActionResult original, ItemUsageContext context) {
        var stack = context.getStack();
        if (stack.contains(MythicDataComponents.BLAST_MINING)) {
            var component = stack.get(MythicDataComponents.BLAST_MINING);
            assert component != null;
            return component.trigger(context);
        }
        return original;
    }

}
