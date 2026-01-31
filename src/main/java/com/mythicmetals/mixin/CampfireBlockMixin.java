package com.mythicmetals.mixin;

import com.mythicmetals.armor.MythicArmor;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
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
    private void stompMarshmellows(BlockState state, Level world, BlockPos pos, Entity entity, CallbackInfo ci) {
        if (!entity.showVehicleHealth()) return;
        for (ItemStack armorItems : ((LivingEntity) entity).getArmorSlots()) {
            if (armorItems.getItem().equals(MythicArmor.PALLADIUM.getBoots())) {
                ci.cancel();
                return;
            }
        }
    }
}
