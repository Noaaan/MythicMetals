package nourl.mythicmetals.mixin;

import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPointer;
import nourl.mythicmetals.blocks.BanglumNukeHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

// [ConjuringCopy] https://github.com/wisp-forest/conjuring/blob/1.18/src/main/java/com/glisco/conjuring/mixin/DispenserBehaviorMixin.java
@Mixin(targets = {"net.minecraft.block.dispenser.DispenserBehavior$10"})
public class DispenserBehaviorMixin {

    @Inject(method = "dispenseSilently(Lnet/minecraft/util/math/BlockPointer;Lnet/minecraft/item/ItemStack;)Lnet/minecraft/item/ItemStack;", at = @At("HEAD"), cancellable = true)
    private void pleaseDoNotCommitWarCrimes(BlockPointer pointer, ItemStack stack, CallbackInfoReturnable<ItemStack> cir) {
        if (!BanglumNukeHandler.tryLightBigTntWithDispenser(pointer)) return;

        stack.damage(1, pointer.getWorld().getRandom(), null);

        cir.setReturnValue(stack.getDamage() > stack.getMaxDamage() ? ItemStack.EMPTY : stack);

    }

}