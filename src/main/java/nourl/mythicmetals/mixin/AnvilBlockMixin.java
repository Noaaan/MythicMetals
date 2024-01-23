package nourl.mythicmetals.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.block.AnvilBlock;
import net.minecraft.block.BlockState;
import nourl.mythicmetals.data.MythicTags;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(AnvilBlock.class)
public class AnvilBlockMixin {

    @ModifyReturnValue(method = "getLandingState", at = @At("TAIL"))
    private static BlockState mythicmetals$cancelAnvilDamage(BlockState original, BlockState initial) {
        if (initial.isIn(MythicTags.ANVILS)) {
            return initial;
        }
        return original;
    }
}
