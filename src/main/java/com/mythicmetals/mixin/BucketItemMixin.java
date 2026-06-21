package com.mythicmetals.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import com.mythicmetals.block.Lavaloggable;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.BlockHitResult;
import org.jspecify.annotations.Nullable;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BucketItem.class)
public abstract class BucketItemMixin {

    @Shadow @Final private Fluid content;

    @Shadow
    protected abstract void playEmptySound(@Nullable LivingEntity livingEntity, LevelAccessor levelAccessor, BlockPos blockPos);

    @ModifyVariable(method = "use", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/block/state/BlockState;getBlock()Lnet/minecraft/world/level/block/Block;"), ordinal = 1)
    private BlockPos mythicmetals$targetBlockOnLava(BlockPos original, Level world, Player user, InteractionHand hand, @Local BlockState blockState, @Local BlockHitResult blockHitResult) {
        if (blockState.getBlock() instanceof Lavaloggable && this.content.equals(Fluids.LAVA)) {
            return blockHitResult.getBlockPos();
        }
        return original;
    }

    @Inject(method = "emptyContents", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/Level;isClientSide()Z"), cancellable = true)
    private void mythicmetals$fillLavalog(LivingEntity livingEntity, Level level, BlockPos pos, BlockHitResult blockHitResult, CallbackInfoReturnable<Boolean> cir) {
        var state = level.getBlockState(pos);
        if (this.content.equals(Fluids.LAVA) && state.getBlock() instanceof Lavaloggable lavaloggable) {
            lavaloggable.placeLiquid(level, pos, state, Fluids.LAVA.getSource(false));
            this.playEmptySound(livingEntity, level, pos);
            cir.setReturnValue(true);
        }
    }
}
