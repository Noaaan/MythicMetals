package com.mythicmetals.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.mythicmetals.ability.Abilities;
import io.github.apace100.apoli.power.type.DamageOverTimePowerType;
import io.github.apace100.apoli.power.type.PowerType;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import java.util.Optional;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;

@Pseudo
@Mixin(value = DamageOverTimePowerType.class, remap = false)
public abstract class DamageOverTimePowerMixin extends PowerType {

    @Shadow @Final private Optional<ResourceKey<Enchantment>> protectionEnchantmentKey;

    @ModifyReturnValue(method = "getProtection", at = @At("RETURN"))
    private int fakeWaterProtection(int original) {
        int change = original;

        LivingEntity entity = getHolder();
        if (protectionEnchantmentKey.isEmpty() || !protectionEnchantmentKey.get().location().equals(ResourceLocation.fromNamespaceAndPath("origins", "water_protection"))) {
            return original;
        }

        for (var itemStack : entity.getAllSlots()) {
            if (Abilities.WATER_PROTECTION.getItems().contains(itemStack.getItem())) {
                change += Abilities.WATER_PROTECTION.getLevel() + 1;
            }
            if (Abilities.BETTER_WATER_PROTECTION.getItems().contains(itemStack.getItem())) {
                change += Abilities.BETTER_WATER_PROTECTION.getLevel() + 1;
            }
        }
        return change;
    }
}
