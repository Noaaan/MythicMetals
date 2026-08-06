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
    private void pleaseDoNotCommitWarCrimes(BlockSource source, ItemStack dispensed, CallbackInfoReturnable<ItemStack> cir) {
        if (!BanglumNukeHandler.tryLightBigTntWithDispenser(source)) return;

        dispensed.hurtAndBreak(1, source.level(), null, null);

        cir.setReturnValue(dispensed.getDamageValue() > dispensed.getMaxDamage() ? ItemStack.EMPTY : dispensed);

    }

}