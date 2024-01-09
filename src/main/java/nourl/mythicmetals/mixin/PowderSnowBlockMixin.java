package nourl.mythicmetals.mixin;

import net.minecraft.block.PowderSnowBlock;
import net.minecraft.entity.*;
import nourl.mythicmetals.armor.MythicArmor;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PowderSnowBlock.class)
public class PowderSnowBlockMixin {

    @Inject(method = "canWalkOnPowderSnow", at = @At("RETURN"), cancellable = true)
    private static void mythicmetals$palladiumCanWalkOnPowderSnow(Entity entity, CallbackInfoReturnable<Boolean> cir) {
        if (entity instanceof LivingEntity && ((LivingEntity)entity).getEquippedStack(EquipmentSlot.FEET).isOf(MythicArmor.PALLADIUM.getBoots())) {
            cir.setReturnValue(true);
        }
    }
}
