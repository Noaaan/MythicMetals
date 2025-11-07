package com.mythicmetals.item.tools;

import com.mythicmetals.AttributeModifier;
import com.mythicmetals.effects.MythicStatusEffects;
import com.mythicmetals.misc.IsAttackCritical;
import com.mythicmetals.misc.RegistryHelper;
import io.wispforest.owo.ops.WorldOps;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.item.*;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import java.util.List;
import java.util.function.Consumer;

public class PalladiumToolSet extends ToolSet {
    // TODO - Move to config
    public static final int MAX_HEAT = 6;

    public PalladiumToolSet(ToolMaterial material, int[] damage, float[] speed, Consumer<Item.Settings> settingsProcessor) {
        super("palladium", material, damage, speed, settingsProcessor);
    }

    @Override
    protected SwordItem makeSword(ToolMaterial material, int damage, float speed, Item.Settings settings, List<AttributeModifier> extraMods) {
        return new PalladiumSword(material, damage, speed, settings);
    }

    @Override
    protected AxeItem makeAxe(ToolMaterial material, int damage, float speed, Item.Settings settings, List<AttributeModifier> extraModifiers) {
        return new PalladiumAxe(material, damage, speed, settings);
    }

    @Override
    protected PickaxeItem makePickaxe(ToolMaterial material, int damage, float speed, Item.Settings settings, List<AttributeModifier> extraModifiers) {
        return new PalladiumPick(material, damage, speed, settings);
    }

    @Override
    protected ShovelItem makeShovel(ToolMaterial material, int damage, float speed, Item.Settings settings, List<AttributeModifier> extraModifiers) {
        return new PalladiumShovel(material, damage, speed, settings);
    }

    @Override
    protected HoeItem makeHoe(ToolMaterial material, int damage, float speed, Item.Settings settings, List<AttributeModifier> extraModifiers) {
        return new PalladiumHoe(material, damage, speed, settings);
    }

    public static class PalladiumAxe extends AxeItem {
        public PalladiumAxe(ToolMaterial material, int damage, float speed, Settings settings) {
            super(material, damage, speed, settings);
        }

        @Override
        public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
            applyHeatToTarget(target, attacker);
            return super.postHit(stack, target, attacker);
        }
    }

    public static class PalladiumHoe extends HoeItem {
        public PalladiumHoe(ToolMaterial material, int damage, float speed, Settings settings) {
            super(material, damage, speed, settings);
        }

        @Override
        public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
            applyHeatToTarget(target, attacker);
            return super.postHit(stack, target, attacker);
        }
    }

    public static class PalladiumPick extends PickaxeItem {
        public PalladiumPick(ToolMaterial material, int damage, float speed, Settings settings) {
            super(material, damage, speed, settings);
        }

        @Override
        public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
            applyHeatToTarget(target, attacker);
            return super.postHit(stack, target, attacker);
        }
    }

    public static class PalladiumShovel extends ShovelItem {
        public PalladiumShovel(ToolMaterial material, int damage, float speed, Settings settings) {
            super(material, damage, speed, settings);
        }

        @Override
        public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
            applyHeatToTarget(target, attacker);
            return super.postHit(stack, target, attacker);
        }
    }

    public static class PalladiumSword extends SwordItem {
        public PalladiumSword(ToolMaterial material, int damage, float speed, Settings settings) {
            super(material, damage, speed, settings);
        }

        @Override
        public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
            applyHeatToTarget(target, attacker);
            return super.postHit(stack, target, attacker);
        }
    }

    public static void applyHeatToTarget(LivingEntity target, LivingEntity attacker) {
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

            if (amplifier >= MAX_HEAT) {
                WorldOps.playSound(target.getWorld(), target.getPos(), SoundEvents.ENTITY_GENERIC_BURN, SoundCategory.PLAYERS);
            }
            target.addStatusEffect(new StatusEffectInstance(effect, 100 + (20 * amplifier * amplifier), Math.min(amplifier, MAX_HEAT)), attacker);
        }
    }
}
