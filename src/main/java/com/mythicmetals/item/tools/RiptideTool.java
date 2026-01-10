package com.mythicmetals.item.tools;


import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

/**
 * Contains relevant methods which allow for the tool to use the Riptide ability of {@link TridentItem}
 */
public interface RiptideTool {
    float TRIDENT_POWER = 3.0f;
    int COOLDOWN = 40;
    int MAX_USE_TIME = 72000;

    /**
     * Handles the activation of the Riptide ability. Requires you to be in water.
     * Override and call this in {@link Item#use}
     * [VanillaCopy]
     *
     * @see TridentItem#use(Level, Player, InteractionHand)
     */
    default InteractionResult activateRiptide(Player user, InteractionHand hand) {
        ItemStack itemStack = user.getItemInHand(hand);
        if (itemStack.getDamageValue() >= itemStack.getMaxDamage() - 1) {
            return InteractionResult.FAIL;
        } else if (!user.isInWaterOrRain()) {
            return InteractionResult.FAIL;
        } else if (user.getCooldowns().isOnCooldown(itemStack)) {
            return InteractionResult.FAIL;
        } else {
            user.startUsingItem(hand);
            return InteractionResult.CONSUME;
        }
    }

    /**
     * Performs a Riptide. Requires you to be in water.
     * Override and call this in {@link Item#releaseUsing}
     *
     * @see TridentItem#releaseUsing(ItemStack, Level, LivingEntity, int)
     * [VanillaCopy] with only the riptide parts in mind, not the projectile logic
     */
    default boolean performRiptide(ItemStack stack, Level world, LivingEntity entity, int remainingUseTicks) {
        if (entity instanceof Player user) {
            int i = MAX_USE_TIME - remainingUseTicks;
            if (i >= 10) {
                float f = EnchantmentHelper.getTridentSpinAttackStrength(stack, user) + TRIDENT_POWER;
                if (!(f > 0.0F) || user.isInWaterOrRain()) {
                    // TridentItem#isAboutToBreak inline
                    if (!(stack.getDamageValue() >= stack.getMaxDamage() - 1)) {
                        user.awardStat(Stats.ITEM_USED.get(stack.getItem()));
                        if (f > 0.0F) {
                            float g = user.getYRot();
                            float h = user.getXRot();
                            float j = -Mth.sin(g * (float) (Math.PI / 180.0)) * Mth.cos(h * (float) (Math.PI / 180.0));
                            float k = -Mth.sin(h * (float) (Math.PI / 180.0));
                            float l = Mth.cos(g * (float) (Math.PI / 180.0)) * Mth.cos(h * (float) (Math.PI / 180.0));
                            float m = Mth.sqrt(j * j + k * k + l * l);
                            j *= f / m;
                            k *= f / m;
                            l *= f / m;
                            user.push(j, k, l);
                            user.startAutoSpinAttack(20, 8.0F, stack);
                            if (user.onGround()) {
                                user.move(MoverType.SELF, new Vec3(0.0, 1.1999999F, 0.0));
                            }

                            user.getCooldowns().addCooldown(stack, COOLDOWN);
                            world.playSound(null, user, SoundEvents.TRIDENT_RIPTIDE_3.value(), SoundSource.PLAYERS, 1.0F, 1.0F);
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }
}
