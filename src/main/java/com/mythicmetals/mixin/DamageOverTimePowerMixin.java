package com.mythicmetals.mixin;

import org.spongepowered.asm.mixin.*;

@Pseudo
@Mixin(targets = "io.github.apace100.apoli.power.type.DamageOverTimePowerType", remap = false)
public abstract class DamageOverTimePowerMixin /*extends PowerType */ {

//    @Shadow @Final private Optional<ResourceKey<Enchantment>> protectionEnchantmentKey;
    // TODO - Reimplement if Origins is ported
//    @ModifyReturnValue(method = "getProtection", at = @At("RETURN"))
//    private int fakeWaterProtection(int original) {
//        int change = original;
//
//        LivingEntity entity = getHolder();
//        if (protectionEnchantmentKey.isEmpty() || !protectionEnchantmentKey.get().identifier().equals(Identifier.fromNamespaceAndPath("origins", "water_protection"))) {
//            return original;
//        }
//
//        for (var itemStack : entity.getAllSlots()) {
//            if (Abilities.WATER_PROTECTION.getItems().contains(itemStack.getItem())) {
//                change += Abilities.WATER_PROTECTION.getLevel() + 1;
//            }
//            if (Abilities.BETTER_WATER_PROTECTION.getItems().contains(itemStack.getItem())) {
//                change += Abilities.BETTER_WATER_PROTECTION.getLevel() + 1;
//            }
//        }
//        return change;
//    }
}
