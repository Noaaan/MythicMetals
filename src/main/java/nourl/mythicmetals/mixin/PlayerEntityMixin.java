package nourl.mythicmetals.mixin;

import net.minecraft.block.BlockState;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import nourl.mythicmetals.registry.RegisterTags;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin {

    @Shadow public abstract PlayerInventory getInventory();

    @Inject(method = "getBlockBreakingSpeed", at = @At("RETURN"), cancellable = true)
    private void slowBreak(BlockState blockState, CallbackInfoReturnable<Float> cir) {
        var hand = getInventory().getMainHandStack();
        if (blockState.isIn(RegisterTags.MYTHIC_ORES) && !hand.isSuitableFor(blockState)) {
            var speed = cir.getReturnValue();
            if (hand.hasEnchantments() && hand.getEnchantments().iterator().next().equals(Enchantments.EFFICIENCY)) {
                speed *= 0.01f;
            }
            else
                speed *= 0.3f;

            cir.setReturnValue(speed);
        }


    }
}
