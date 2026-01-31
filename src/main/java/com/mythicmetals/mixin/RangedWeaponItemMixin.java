package com.mythicmetals.mixin;

import com.mythicmetals.entity.MythicEntities;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.ProjectileWeaponItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

@Mixin(ProjectileWeaponItem.class)
public abstract class RangedWeaponItemMixin {

    // Increases the velocity of Runite Arrows when shot from Ranged Weapons
    // Should increase the damage of the arrows noticable
    // Also decreases divergence, which combined should lead to better accuracy
    // FIXME
//    @ModifyArgs(method = "method_61659", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/RangedWeaponItem;shoot(Lnet/minecraft/entity/LivingEntity;Lnet/minecraft/entity/projectile/ProjectileEntity;IFFFLnet/minecraft/entity/LivingEntity;)V"))
//    private void mythicmetals$modifyArrowsForRunite(Args args) {
//        boolean shouldModify = false;
//        Projectile projectile = args.get(1);
//        if (projectile.getType().equals(MythicEntities.RUNITE_ARROW_ENTITY_TYPE)) {
//            shouldModify = true;
//        }
//        if (shouldModify) {
//            float speed = args.get(3);
//            float divergence = args.get(4);
//
//            args.set(3, speed * 1.3f);
//            args.set(4, divergence * 0.85f);
//        }
//    }
}
