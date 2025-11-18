package com.mythicmetals.component;

import com.mythicmetals.effects.MythicStatusEffects;
import com.mythicmetals.misc.*;
import io.wispforest.endec.StructEndec;
import io.wispforest.endec.impl.StructEndecBuilder;
import io.wispforest.owo.ops.WorldOps;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.item.Item;
import net.minecraft.item.tooltip.TooltipAppender;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.*;
import java.util.function.Consumer;

public record BrandingComponent(int maxHeat) implements TooltipAppender {

    public static final StructEndec<BrandingComponent> ENDEC = StructEndecBuilder.of(
        StructEndec.INT.fieldOf("maxHeat", BrandingComponent::maxHeat),
        BrandingComponent::new
    );

    public void applyHeatToTarget(LivingEntity target, LivingEntity attacker) {
        var effect = RegistryHelper.getEntry(MythicStatusEffects.HEAT);
        if (!target.hasStatusEffect(effect)) {
            target.addStatusEffect(new StatusEffectInstance(effect, 100), attacker);
        } else {
            var activeEffect = target.getStatusEffect(effect);
            int amplifier = activeEffect == null ? 0 : activeEffect.getAmplifier();
            if (((IsAttackCritical) attacker).mythicmetals$isCritical()) {
                amplifier += 1;
            } else if (target.getRandom().nextInt(3) == 0) {
                amplifier += 1;
            }

            if (amplifier >= maxHeat) {
                WorldOps.playSound(target.getWorld(), target.getPos(), SoundEvents.ENTITY_GENERIC_BURN, SoundCategory.PLAYERS);
            }
            target.addStatusEffect(new StatusEffectInstance(effect, 100 + (20 * amplifier * amplifier), Math.min(amplifier, maxHeat)), attacker);
        }
    }

    @Override
    public void appendTooltip(Item.TooltipContext context, Consumer<Text> tooltip, TooltipType type) {
        MutableText text = Text.literal("");
        text.append(Text.translatable("tooltip.mythicmetals.branding"));
        text.append(" ").append(Text.translatable("enchantment.level." + maxHeat));
        tooltip.accept(text.setStyle(UsefulSingletonForColorUtil.MetalColors.PALLADIUM_STYLE));
    }
}
