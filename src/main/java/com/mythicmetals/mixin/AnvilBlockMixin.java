package com.mythicmetals.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.mythicmetals.data.MythicTags;
import net.minecraft.world.level.block.AnvilBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(AnvilBlock.class)
public class AnvilBlockMixin {

    @ModifyReturnValue(method = "getLandingState", at = @At("TAIL"))
    private static BlockState mythicmetals$cancelAnvilDamage(BlockState original, BlockState initial) {
        if (initial.is(MythicTags.ANVILS)) {
            return initial;
        }
        return original;
    }
}
