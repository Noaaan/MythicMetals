package nourl.mythicmetals.mixin;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.vehicle.AbstractMinecartEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import nourl.mythicmetals.MythicMetals;
import nourl.mythicmetals.entity.BanglumTntMinecartEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(AbstractMinecartEntity.class)
public class AbstractMinecartEntityMixin {

    @Inject(method = "create", at = @At("HEAD"), cancellable = true)
    private static void mythicmetals$createBanglumTntMinecart(ServerWorld world, double x, double y, double z, AbstractMinecartEntity.Type type, ItemStack stack, PlayerEntity player, CallbackInfoReturnable<AbstractMinecartEntity> cir) {
        if (type.equals(MythicMetals.BANGLUM_TNT)) {
            cir.setReturnValue(new BanglumTntMinecartEntity(world, x, y, z));
        }
    }
}
