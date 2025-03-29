package com.mythicmetals.mixin;

import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.RangedWeaponItem;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Hand;
import com.mythicmetals.item.tools.MythicTools;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;
import java.util.List;

@Mixin(RangedWeaponItem.class)
public abstract class RangedWeaponItemMixin {

    // Increases the velocity of Runite Arrows when shot from Ranged Weapons
    // Also decreases divergence, leading to better accuracy
    @ModifyArgs(method = "shootAll", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/RangedWeaponItem;shoot(Lnet/minecraft/entity/LivingEntity;Lnet/minecraft/entity/projectile/ProjectileEntity;IFFFLnet/minecraft/entity/LivingEntity;)V"))
    private void mythicmetals$modifyArrowsForRunite(Args args, ServerWorld world, LivingEntity shooter, Hand hand, ItemStack stack, List<ItemStack> projectiles, float speed, float divergence, boolean critical, @Nullable LivingEntity target) {
        boolean shouldModify = false;
        for (var arrow : projectiles) {
            if (arrow.isOf(MythicTools.RUNITE_ARROW) || arrow.isOf(MythicTools.TIPPED_RUNITE_ARROW)) {
                shouldModify = true;
                break;
            }
        }
        if (shouldModify) {
            args.set(3, speed * 1.3f);
            args.set(4, divergence * 0.9f);
        }
    }
}
