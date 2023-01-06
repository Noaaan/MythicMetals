package nourl.mythicmetals.mixin;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ArrowItem;
import net.minecraft.item.BowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import nourl.mythicmetals.item.tools.MythicTools;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(BowItem.class)
public abstract class BowItemMixin {

    // Increases the velocity of Runite Arrows from 3.0 to 4.0, roughly 33%
    // Also decreases divergence, leading to better accuracy
    @Inject(method = "onStoppedUsing", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/World;spawnEntity(Lnet/minecraft/entity/Entity;)Z"), locals = LocalCapture.CAPTURE_FAILHARD)
    private void mythicmetals$runiteArrows(ItemStack stack, World world, LivingEntity user, int remainingUseTicks, CallbackInfo ci, PlayerEntity playerEntity, boolean bl, ItemStack itemStack, int i, float f, boolean bl2, ArrowItem arrowItem, PersistentProjectileEntity persistentProjectileEntity) {
        if (arrowItem.equals(MythicTools.RUNITE_ARROW) || arrowItem.equals(MythicTools.TIPPED_RUNITE_ARROW)) {
            persistentProjectileEntity.setVelocity(playerEntity, playerEntity.getPitch(), playerEntity.getYaw(), 0.0F, f * 4.0F, 0.75F);
        }
    }
}
