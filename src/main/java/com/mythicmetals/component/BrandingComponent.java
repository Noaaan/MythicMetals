package com.mythicmetals.component;

import com.mythicmetals.effects.MythicStatusEffects;
import com.mythicmetals.misc.*;
import io.wispforest.endec.StructEndec;
import io.wispforest.endec.impl.StructEndecBuilder;
import io.wispforest.owo.ops.WorldOps;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipProvider;
import java.util.function.Consumer;

public record BrandingComponent(int maxHeat) implements TooltipProvider {

    public static final StructEndec<BrandingComponent> ENDEC = StructEndecBuilder.of(
        StructEndec.INT.fieldOf("max_heat", BrandingComponent::maxHeat),
        BrandingComponent::new
    );

    public void applyHeatToTarget(LivingEntity target, LivingEntity attacker) {
        var effect = RegistryHelper.getEntry(MythicStatusEffects.HEAT);
        if (!target.hasEffect(effect)) {
            target.addEffect(new MobEffectInstance(effect, 100), attacker);
        } else {
            var activeEffect = target.getEffect(effect);
            int amplifier = activeEffect == null ? 0 : activeEffect.getAmplifier();
            if (((IsAttackCritical) attacker).mythicmetals$isCritical()) {
                amplifier += 1;
            } else if (target.getRandom().nextInt(3) == 0) {
                amplifier += 1;
            }

            if (amplifier >= maxHeat) {
                WorldOps.playSound(target.level(), target.position(), SoundEvents.GENERIC_BURN, SoundSource.PLAYERS);
            }
            target.addEffect(new MobEffectInstance(effect, 100 + (20 * amplifier * amplifier), Math.min(amplifier, maxHeat)), attacker);
        }
    }

    @Override
    public void addToTooltip(Item.TooltipContext context, Consumer<Component> tooltip, TooltipFlag type) {
        MutableComponent text = Component.literal("");
        text.append(Component.translatable("tooltip.mythicmetals.branding"));
        text.append(" ").append(Component.translatable("enchantment.level." + maxHeat));
        tooltip.accept(text.setStyle(UsefulSingletonForColorUtil.MetalColors.PALLADIUM_STYLE));
    }
}
