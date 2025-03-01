package nourl.mythicmetals.mixin;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.*;
import net.minecraft.item.BucketItem;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import nourl.mythicmetals.blocks.Lavaloggable;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BucketItem.class)
public abstract class BucketItemMixin {

    @Shadow @Final private Fluid fluid;

    @Shadow protected abstract void playEmptyingSound(@Nullable PlayerEntity player, WorldAccess world, BlockPos pos);

    @Inject(method = "placeFluid", at = @At(value = "INVOKE", target = "Lnet/minecraft/block/BlockState;isLiquid()Z", shift = At.Shift.BEFORE), cancellable = true)
    private void mythicmetals$fillLavalog(PlayerEntity player, World world, BlockPos pos, BlockHitResult hitResult, CallbackInfoReturnable<Boolean> cir) {
        var railPos = hitResult.getBlockPos();
        var state = world.getBlockState(railPos);
        if (this.fluid.equals(Fluids.LAVA) && state.getBlock() instanceof Lavaloggable lavaloggable) {
            lavaloggable.tryFillWithFluid(world, railPos, state, Fluids.LAVA.getStill(false));
            this.playEmptyingSound(player, world, railPos);
            cir.setReturnValue(true);
        }
    }
}
