package nourl.mythicmetals.item.tools;

import io.wispforest.owo.ops.WorldOps;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.item.*;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import nourl.mythicmetals.effects.MythicStatusEffects;
import java.util.function.Consumer;

public class PalladiumToolSet extends ToolSet {
    public static final int MAX_HEAT = 6;
    public PalladiumToolSet(ToolMaterial material, int[] damage, float[] speed, Consumer<Item.Settings> settingsProcessor) {
        super(material, damage, speed, settingsProcessor);
    }

    @Override
    protected SwordItem makeSword(ToolMaterial material, int damage, float speed, Item.Settings settings) {
        return new PalladiumSword(material, damage, speed, settings);
    }

    @Override
    protected AxeItem makeAxe(ToolMaterial material, int damage, float speed, Item.Settings settings) {
        return new PalladiumAxe(material, damage, speed, settings);
    }

    @Override
    protected PickaxeItem makePickaxe(ToolMaterial material, int damage, float speed, Item.Settings settings) {
        return new PalladiumPick(material, damage, speed, settings);
    }

    @Override
    protected ShovelItem makeShovel(ToolMaterial material, int damage, float speed, Item.Settings settings) {
        return new PalladiumShovel(material, damage, speed, settings);
    }

    @Override
    protected HoeItem makeHoe(ToolMaterial material, int damage, float speed, Item.Settings settings) {
        return new PalladiumHoe(material, damage, speed, settings);
    }

    public static class PalladiumAxe extends AxeItem {
        public PalladiumAxe(ToolMaterial material, int attackDamage, float speed, Settings settings) {
            super(material, attackDamage, speed, settings);
        }

        @Override
        public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
            applyHeatToTarget(target, attacker);
            return super.postHit(stack, target, attacker);
        }
    }

    public static class PalladiumHoe extends HoeItem {
        public PalladiumHoe(ToolMaterial material, int attackDamage, float speed, Settings settings) {
            super(material, attackDamage, speed, settings);
        }

        @Override
        public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
            applyHeatToTarget(target, attacker);
            return super.postHit(stack, target, attacker);
        }
    }

    public static class PalladiumPick extends PickaxeItem {
        public PalladiumPick(ToolMaterial material, int attackDamage, float speed, Settings settings) {
            super(material, attackDamage, speed, settings);
        }

        @Override
        public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
            applyHeatToTarget(target, attacker);
            return super.postHit(stack, target, attacker);
        }
    }

    public static class PalladiumShovel extends ShovelItem {
        public PalladiumShovel(ToolMaterial material, int attackDamage, float speed, Settings settings) {
            super(material, attackDamage, speed, settings);
        }

        @Override
        public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
            applyHeatToTarget(target, attacker);
            return super.postHit(stack, target, attacker);
        }
    }

    public static class PalladiumSword extends SwordItem {
        public PalladiumSword(ToolMaterial material, int attackDamage, float speed, Settings settings) {
            super(material, attackDamage, speed, settings);
        }

        @Override
        public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
            applyHeatToTarget(target, attacker);
            return super.postHit(stack, target, attacker);
        }
    }

    public static void applyHeatToTarget(LivingEntity target, LivingEntity attacker) {
        if (!target.hasStatusEffect(MythicStatusEffects.HEAT)) {
            target.addStatusEffect(new StatusEffectInstance(MythicStatusEffects.HEAT, 100), attacker);
        } else {
            var effect = target.getStatusEffect(MythicStatusEffects.HEAT);
            int amplifier = effect == null ? 0 : target.getRandom().nextInt(3) == 0 ? effect.getAmplifier() + 1 : effect.getAmplifier();
            if (amplifier >= MAX_HEAT) {
                WorldOps.playSound(target.getWorld(), target.getPos(), SoundEvents.ENTITY_GENERIC_BURN, SoundCategory.PLAYERS);
            }
            target.setStatusEffect(new StatusEffectInstance(MythicStatusEffects.HEAT, 100 + (20 * amplifier * amplifier), Math.min(amplifier, MAX_HEAT)), attacker);
        }
    }
}
