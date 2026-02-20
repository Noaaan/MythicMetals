package com.mythicmetals.mixin;

import com.mythicmetals.block.Lavaloggable;
import com.mythicmetals.block.PalladiumRailBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.vehicle.minecart.*;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(OldMinecartBehavior.class)
public abstract class OldMinecartBehaviorMixin extends MinecartBehavior {

    protected OldMinecartBehaviorMixin(AbstractMinecart minecart) {
        super(minecart);
    }

    @ModifyVariable(method = "moveAlongTrack", at = @At(value = "STORE", ordinal = 0))
    private boolean mythicmetals$boostInLava(boolean original, ServerLevel value) {
        BlockPos blockPos = this.minecart.getCurrentBlockPosOrRailBelow();
        BlockState state = this.level().getBlockState(blockPos);
        if (state.getBlock() instanceof Lavaloggable && PalladiumRailBlock.isLavaLogged(state)) {
            return true;
        }
        return original;
    }
}
