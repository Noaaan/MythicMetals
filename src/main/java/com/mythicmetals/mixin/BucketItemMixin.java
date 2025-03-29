package com.mythicmetals.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.BucketItem;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import com.mythicmetals.block.Lavaloggable;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BucketItem.class)
public abstract class BucketItemMixin {

    @Shadow @Final private Fluid fluid;

    @Shadow protected abstract void playEmptyingSound(@Nullable PlayerEntity player, WorldAccess world, BlockPos pos);

    @ModifyVariable(method = "use", at = @At(value = "INVOKE", target = "Lnet/minecraft/block/BlockState;getBlock()Lnet/minecraft/block/Block;"), ordinal = 1)
    private BlockPos mythicmetals$targetBlockOnLava(BlockPos original, World world, PlayerEntity user, Hand hand, @Local BlockState blockState, @Local BlockHitResult blockHitResult) {
        if (blockState.getBlock() instanceof Lavaloggable && this.fluid.equals(Fluids.LAVA)) {
            return blockHitResult.getBlockPos();
        }
        return original;
    }

    @Inject(method = "placeFluid", at = @At(value = "INVOKE", target = "Lnet/minecraft/block/BlockState;isLiquid()Z"), cancellable = true)
    private void mythicmetals$fillLavalog(PlayerEntity player, World world, BlockPos pos, BlockHitResult hitResult, CallbackInfoReturnable<Boolean> cir) {
        var state = world.getBlockState(pos);
        if (this.fluid.equals(Fluids.LAVA) && state.getBlock() instanceof Lavaloggable lavaloggable) {
            // TODO - Vanilla behavior here is to eat the fluid if you log the same block twice
            // Try and explore whether you can prevent placing lava in the same block twice
            // Lava is mildly more inconvenient to source, after all
            lavaloggable.tryFillWithFluid(world, pos, state, Fluids.LAVA.getStill(false));
            this.playEmptyingSound(player, world, pos);
            cir.setReturnValue(true);
        }
    }
}
