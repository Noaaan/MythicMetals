package com.mythicmetals.mixin;

import com.mythicmetals.item.MythicMaterials;
import net.minecraft.world.entity.*;
import net.minecraft.world.level.block.PowderSnowBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PowderSnowBlock.class)
public class PowderSnowBlockMixin {

    @Inject(method = "canEntityWalkOnPowderSnow", at = @At("RETURN"), cancellable = true)
    private static void mythicmetals$palladiumCanWalkOnPowderSnow(Entity entity, CallbackInfoReturnable<Boolean> cir) {
        if (MythicMaterials.PALLADIUM.armorSet() == null) return;
        if (entity instanceof LivingEntity living && MythicMaterials.PALLADIUM.armorSet().isInArmorSet(living.getItemBySlot(EquipmentSlot.FEET))) {
            cir.setReturnValue(true);
        }
    }
}
