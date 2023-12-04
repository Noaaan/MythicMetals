package nourl.mythicmetals.mixin;

import io.github.apace100.apoli.power.*;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.LivingEntity;
import nourl.mythicmetals.abilities.Abilities;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Pseudo
@Mixin(value = DamageOverTimePower.class, remap = false)
public class DamageOverTimePowerMixin extends Power {
    @Shadow @Final private Enchantment protectingEnchantment;

    public DamageOverTimePowerMixin(PowerType<?> type, LivingEntity entity) {
        super(type, entity);
    }

    @Inject(method = "getProtection", at = @At("TAIL"), cancellable = true)
    private void fakeWaterProtection(CallbackInfoReturnable<Integer> cir) {
        var amount = cir.getReturnValue();
        int change = 0;
        for (var itemStack : protectingEnchantment.getEquipment(entity).values()) {
            if (Abilities.WATER_PROTECTION.getItems().contains(itemStack.getItem())) {
                change += Abilities.WATER_PROTECTION.getLevel() + 1;
            }
            if (Abilities.BETTER_WATER_PROTECTION.getItems().contains(itemStack.getItem())) {
                change += Abilities.BETTER_WATER_PROTECTION.getLevel() + 1;
            }
        }
        if (change != 0)
            cir.setReturnValue(amount + change);
    }
}
