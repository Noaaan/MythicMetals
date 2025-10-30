package com.mythicmetals.mixin;

import com.mythicmetals.block.Lavaloggable;
import com.mythicmetals.block.PalladiumRailBlock;
import net.minecraft.block.BlockState;
import net.minecraft.entity.vehicle.*;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(DefaultMinecartController.class)
public abstract class AbstractMinecartEntityMixin extends MinecartController {

    protected AbstractMinecartEntityMixin(AbstractMinecartEntity minecart) {
        super(minecart);
    }

    @ModifyVariable(method = "moveOnRail", at = @At(value = "STORE", ordinal = 0))
    private boolean mythicmetals$boostInLava(boolean original, ServerWorld value) {
        BlockPos blockPos = this.minecart.getRailOrMinecartPos();
        BlockState state = this.getWorld().getBlockState(blockPos);
        if (state.getBlock() instanceof Lavaloggable && PalladiumRailBlock.isLavaLogged(state)) {
            return true;
        }
        return original;
    }
}
