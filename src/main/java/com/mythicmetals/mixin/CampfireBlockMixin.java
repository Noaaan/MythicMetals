package com.mythicmetals.mixin;

import net.minecraft.block.BlockState;
import net.minecraft.block.CampfireBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import com.mythicmetals.armor.MythicArmor;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = CampfireBlock.class, priority = 1100)
public class CampfireBlockMixin {

    @Inject(method = "onEntityCollision", at = @At("HEAD"), cancellable = true)
    private void stompMarshmellows(BlockState state, World world, BlockPos pos, Entity entity, CallbackInfo ci) {
        if (!entity.isLiving()) return;
        for (ItemStack armorItems : ((LivingEntity) entity).getArmorItems()) {
            if (armorItems.getItem().equals(MythicArmor.PALLADIUM.getBoots())) {
                ci.cancel();
                return;
            }
        }
    }
}
