package com.mythicmetals.mixin;

import com.mythicmetals.block.BanglumNukeHandler;
import net.minecraft.core.dispenser.BlockSource;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(targets = {"net.minecraft.core.dispenser.DispenseItemBehavior$6"})
public class DispenserBehaviorMixin {

    @Inject(method = "execute", at = @At("HEAD"), cancellable = true)
    private void pleaseDoNotCommitWarCrimes(BlockSource pointer, ItemStack stack, CallbackInfoReturnable<ItemStack> cir) {
        if (!BanglumNukeHandler.tryLightBigTntWithDispenser(pointer)) return;

        stack.hurtAndBreak(1, pointer.level(), null, null);

        cir.setReturnValue(stack.getDamageValue() > stack.getMaxDamage() ? ItemStack.EMPTY : stack);

    }

}