package nourl.mythicmetals.mixin;

import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.vehicle.AbstractMinecartEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import nourl.mythicmetals.MythicMetals;
import nourl.mythicmetals.blocks.Lavaloggable;
import nourl.mythicmetals.blocks.PalladiumRailBlock;
import nourl.mythicmetals.data.MythicTags;
import nourl.mythicmetals.entity.BanglumTntMinecartEntity;
import nourl.mythicmetals.entity.PalladiumMinecartEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(AbstractMinecartEntity.class)
public class AbstractMinecartEntityMixin {

    @Unique
    boolean mythicmetals$isInLava = false;

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
        if (state instanceof Lavaloggable && PalladiumRailBlock.isLavaLogged(state)) {
            mythicmetals$isInLava = true;
            return true;
        }
        return original;
    }
}
