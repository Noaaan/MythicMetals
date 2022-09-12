package nourl.mythicmetals.mixin;

import net.minecraft.block.AbstractBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.BlockView;
import net.minecraft.world.RaycastContext;
import nourl.mythicmetals.tools.HammerBase;
import nourl.mythicmetals.utils.BlockBreaker;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(AbstractBlock.AbstractBlockState.class)
public class AbstractBlockStateMixin {

    @Inject(at = @At("RETURN"), method = "calcBlockBreakingDelta", cancellable = true)
    private void mythicmetals$slowBreaking(PlayerEntity player, BlockView blockView, BlockPos originalBlockPos, CallbackInfoReturnable<Float> cir) {
        if (player.getMainHandStack().getItem() instanceof HammerBase hammer) {
            var oldDelta = cir.getReturnValue();
            // Raycast to get a block hit result, which contains the side of the targeted block
            BlockHitResult blockHitResult = blockView.raycast(
                    new RaycastContext(player.getPos(),
                            Vec3d.of(originalBlockPos),
                            RaycastContext.ShapeType.OUTLINE,
                            RaycastContext.FluidHandling.NONE,
                            player));

            var hammerDelta = BlockBreaker.calculateHardestDelta(blockHitResult, player, hammer);

            cir.setReturnValue(Math.min(oldDelta, hammerDelta));
        }
    }
}

