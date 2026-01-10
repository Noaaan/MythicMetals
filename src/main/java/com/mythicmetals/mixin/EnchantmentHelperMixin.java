package com.mythicmetals.mixin;

import com.mythicmetals.ability.Abilities;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(EnchantmentHelper.class)
public class EnchantmentHelperMixin {

    @Inject(method = "getProtectionAmount", at = @At("TAIL"), cancellable = true)
    private static void mythicmetals$damageReduction(ServerLevel world, LivingEntity user, DamageSource source, CallbackInfoReturnable<Float> cir) {
        // Make sure that there is any gear to check
        if (!user.getArmorSlots().iterator().hasNext()) return;

        var amount = cir.getReturnValue();
        int change = 0;

        for (var gear : user.getArmorSlots()) {
            if (Abilities.BLAST_PROTECTION.getItems().contains(gear.getItem()) && source.is(DamageTypeTags.IS_EXPLOSION)) {
                change += Abilities.BLAST_PROTECTION.getLevel() * 2;
            }

            if (Abilities.BLAST_PADDING.getItems().contains(gear.getItem()) && source.is(DamageTypeTags.IS_EXPLOSION)) {
                change += Abilities.BLAST_PADDING.getLevel() * 2;
            }

            if (Abilities.PROJECTILE_PROTECTION.getItems().contains(gear.getItem()) && source.is(DamageTypeTags.IS_PROJECTILE)) {
                change += Abilities.PROJECTILE_PROTECTION.getLevel() * 2;
            }

            if (Abilities.FIRE_PROTECTION.getItems().contains(gear.getItem()) && source.is(DamageTypeTags.IS_FIRE)) {
                change += Abilities.FIRE_PROTECTION.getLevel() * 2;
            }
        }

        if (change != 0)
            cir.setReturnValue(amount + change);
    }
}
