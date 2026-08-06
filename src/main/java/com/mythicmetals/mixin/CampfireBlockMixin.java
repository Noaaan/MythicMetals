package com.mythicmetals.mixin;

import com.mythicmetals.item.MythicMaterials;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.CampfireBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = CampfireBlock.class, priority = 1100)
public class CampfireBlockMixin {

    @Inject(method = "entityInside", at = @At("HEAD"), cancellable = true)
    private void stompMarshmellows(BlockState state, Level level, BlockPos pos, Entity entity, InsideBlockEffectApplier effectApplier, boolean isPrecise, CallbackInfo ci) {
        if (!entity.showVehicleHealth()) return;
        if (entity instanceof LivingEntity livingEntity) {
            var slot = SlotAccess.forEquipmentSlot(livingEntity, EquipmentSlot.FEET);
            if (slot.get().getItem().equals(MythicMaterials.PALLADIUM.armorSet().getBoots())) {
                ci.cancel();
            }
        }
    }
}
