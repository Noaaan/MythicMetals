package com.mythicmetals.mixin;

import com.mythicmetals.block.BanglumNukeHandler;
import net.minecraft.core.dispenser.BlockSource;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

// [ConjuringCopy] https://github.com/wisp-forest/conjuring/blob/1.18/src/main/java/com/glisco/conjuring/mixin/DispenserBehaviorMixin.java
@Mixin(targets = {"net.minecraft.core.dispenser.DispenseItemBehavior$14"})
public class DispenserBehaviorMixin {

    // FIXME
//    @Inject(method = "execute", at = @At("HEAD"), cancellable = true)
    private void pleaseDoNotCommitWarCrimes(BlockSource pointer, ItemStack stack, CallbackInfoReturnable<ItemStack> cir) {
        if (!BanglumNukeHandler.tryLightBigTntWithDispenser(pointer)) return;

        stack.hurtAndBreak(1, pointer.level(), null, null);

        cir.setReturnValue(stack.getDamageValue() > stack.getMaxDamage() ? ItemStack.EMPTY : stack);

    }

}