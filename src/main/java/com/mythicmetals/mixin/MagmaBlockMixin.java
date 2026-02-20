package com.mythicmetals.mixin;

import com.mythicmetals.armor.MythicArmor;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.MagmaBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = MagmaBlock.class, priority = 1100)
public class MagmaBlockMixin {

    @Inject(method = "stepOn", at = @At("HEAD"), cancellable = true)
    private void cancelBurnWithPalladiumBoots(Level world, BlockPos pos, BlockState state, Entity entity, CallbackInfo ci) {
        if (!entity.showVehicleHealth()) return;
        if (entity instanceof LivingEntity living && living.getItemBySlot(EquipmentSlot.FEET).getItem().equals(MythicArmor.PALLADIUM.getBoots())) {
            ci.cancel();
        }
    }
}
