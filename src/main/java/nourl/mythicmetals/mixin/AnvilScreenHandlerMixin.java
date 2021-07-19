package nourl.mythicmetals.mixin;

import net.minecraft.block.BlockState;
import net.minecraft.screen.AnvilScreenHandler;
import nourl.mythicmetals.blocks.MythicBlocks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(AnvilScreenHandler.class)
public class AnvilScreenHandlerMixin {

    @Inject(method = "canUse", at = @At("HEAD"), cancellable = true)
    public void canUse(BlockState state, CallbackInfoReturnable<Boolean> cir) {
        if (MythicBlocks.ANVILS.contains(state.getBlock())) {
            cir.setReturnValue(true);
            cir.cancel();
        }
    }

}
