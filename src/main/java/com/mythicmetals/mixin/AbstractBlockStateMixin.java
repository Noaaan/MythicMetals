package com.mythicmetals.mixin;


import com.mythicmetals.item.tools.HammerBase;
import com.mythicmetals.misc.BlockBreaker;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BlockBehaviour.BlockStateBase.class)
public class AbstractBlockStateMixin {

    @Inject(at = @At("RETURN"), method = "calcBlockBreakingDelta", cancellable = true)
    private void mythicmetals$slowBreaking(Player player, BlockGetter blockView, BlockPos originalBlockPos, CallbackInfoReturnable<Float> cir) {
        if (player.getMainHandItem().getItem() instanceof HammerBase hammer) {
            var oldDelta = cir.getReturnValue();
            // Raycast to get a block hit result, which contains the side of the targeted block
            BlockHitResult blockHitResult = blockView.clip(
                new ClipContext(player.position(),
                    Vec3.atLowerCornerOf(originalBlockPos),
                    ClipContext.Block.OUTLINE,
                    ClipContext.Fluid.NONE,
                    player));

            var hammerDelta = BlockBreaker.calculateHardestDelta(blockHitResult, player, hammer);

            cir.setReturnValue(Math.min(oldDelta, hammerDelta));
        }
    }
}

