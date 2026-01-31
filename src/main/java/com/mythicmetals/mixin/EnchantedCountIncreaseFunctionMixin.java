package com.mythicmetals.mixin;

import com.mythicmetals.data.MythicTags;
import net.minecraft.core.Holder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.functions.EnchantedCountIncreaseFunction;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(EnchantedCountIncreaseFunction.class)
public class EnchantedCountIncreaseFunctionMixin {

    @Shadow
    @Final
    private Holder<Enchantment> enchantment;

    @ModifyVariable(method = "run", at = @At(
        value = "STORE",
        ordinal = 0
    ))
    private int mythicmetals$increaseLooting(int original, ItemStack stack, LootContext context) {
        Entity entity = context.getOptionalParameter(LootContextParams.ATTACKING_ENTITY);
        if (entity instanceof LivingEntity livingEntity) {
            var mainHandStack = livingEntity.getMainHandItem();
            if (!mainHandStack.is(MythicTags.BONUS_LOOTING)) return original;
            if (this.enchantment.is((enchantmentRegistryKey) -> enchantmentRegistryKey.equals(Enchantments.LOOTING))) {
                return original + 1;
            }
        }
        return original;
    }
}
