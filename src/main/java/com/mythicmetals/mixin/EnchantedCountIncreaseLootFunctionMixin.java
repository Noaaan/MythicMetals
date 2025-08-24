package com.mythicmetals.mixin;

import com.mythicmetals.data.MythicTags;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.context.LootContext;
import net.minecraft.loot.context.LootContextParameters;
import net.minecraft.loot.function.EnchantedCountIncreaseLootFunction;
import net.minecraft.registry.entry.RegistryEntry;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(EnchantedCountIncreaseLootFunction.class)
public class EnchantedCountIncreaseLootFunctionMixin {

    @Shadow
    @Final
    private RegistryEntry<Enchantment> enchantment;

    @ModifyVariable(method = "process", at = @At(
        value = "STORE",
        ordinal = 0
    ))
    private int mythicmetals$increaseLooting(int original, ItemStack stack, LootContext context) {
        Entity entity = context.get(LootContextParameters.ATTACKING_ENTITY);
        if (entity instanceof LivingEntity livingEntity) {
            var mainHandStack = livingEntity.getMainHandStack();
            if (!mainHandStack.isIn(MythicTags.BONUS_LOOTING)) return original;
            if (this.enchantment.matches((enchantmentRegistryKey) -> enchantmentRegistryKey.equals(Enchantments.LOOTING))) {
                return original + 1;
            }
        }
        return original;
    }
}
