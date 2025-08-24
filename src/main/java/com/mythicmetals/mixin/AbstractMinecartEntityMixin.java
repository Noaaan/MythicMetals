package com.mythicmetals.mixin;

import com.mythicmetals.MythicMetals;
import com.mythicmetals.block.Lavaloggable;
import com.mythicmetals.block.PalladiumRailBlock;
import com.mythicmetals.entity.BanglumTntMinecartEntity;
import com.mythicmetals.entity.PalladiumMinecartEntity;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.vehicle.AbstractMinecartEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(AbstractMinecartEntity.class)
public class AbstractMinecartEntityMixin {

    @Inject(method = "create", at = @At("HEAD"), cancellable = true)
    private static void mythicmetals$createCustomMinecart(ServerWorld world, double x, double y, double z, AbstractMinecartEntity.Type type, ItemStack stack, PlayerEntity player, CallbackInfoReturnable<AbstractMinecartEntity> cir) {
        if (type.equals(MythicMetals.BANGLUM_TNT)) {
            cir.setReturnValue(new BanglumTntMinecartEntity(world, x, y, z));
        }

        if (type.equals(MythicMetals.PALLADIUM_MINECART)) {
            cir.setReturnValue(new PalladiumMinecartEntity(world, x, y, z));
        }
    }

    @ModifyVariable(method = "moveOnRail", at = @At(value = "STORE", ordinal = 0))
    private boolean mythicmetals$boostInLava(boolean original, BlockPos pos, BlockState state) {
        if (state.getBlock() instanceof Lavaloggable && PalladiumRailBlock.isLavaLogged(state)) {
            return true;
        }
        return original;
    }
}
