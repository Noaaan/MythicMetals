package com.mythicmetals.mixin;

import net.minecraft.item.RangedWeaponItem;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(RangedWeaponItem.class)
public abstract class RangedWeaponItemMixin {

    // FIXME
    // Increases the velocity of Runite Arrows when shot from Ranged Weapons
    // Also decreases divergence, leading to better accuracy
//    @ModifyArgs(method = "method_61659", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/RangedWeaponItem;shoot(Lnet/minecraft/entity/LivingEntity;Lnet/minecraft/entity/projectile/ProjectileEntity;IFFFLnet/minecraft/entity/LivingEntity;)V"))
//    private void mythicmetals$modifyArrowsForRunite(Args args) {
//        boolean shouldModify = false;
//        for (var arrow : projectiles) {
//            if (arrow.isOf(MythicTools.RUNITE_ARROW) || arrow.isOf(MythicTools.TIPPED_RUNITE_ARROW)) {
//                shouldModify = true;
//                break;
//            }
//        }
//        if (shouldModify) {
//            args.set(3, speed * 1.3f);
//            args.set(4, divergence * 0.9f);
//        }
//    }
}
