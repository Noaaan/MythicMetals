package com.mythicmetals.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.mythicmetals.data.MythicTags;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(EnchantmentHelper.class)
public class EnchantmentHelperMixin {

    @ModifyReturnValue(method = "getDamageProtection", at = @At("TAIL"))
    private static float mythicmetals$damageReduction(float original, ServerLevel world, LivingEntity user, DamageSource source) {
        // Make sure that there is any gear to check
        int change = 0;

        for (var slot : EquipmentSlot.VALUES) {
            if (!slot.isArmor()) continue;
            var gearStack = user.getItemBySlot(slot);
            if (gearStack.isEmpty()) {
                continue;
            }

            if (source.is(DamageTypeTags.IS_FIRE) && gearStack.is(MythicTags.FIRE_RESISTANT_ARMOR)) {
                change += 2;
            }
        }

        if (change != 0) {
            return original + change;
        }
        return original;
    }
}
