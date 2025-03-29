package com.mythicmetals.item.tools;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MovementType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

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
     * @see TridentItem#use(World, PlayerEntity, Hand)
     */
    default TypedActionResult<ItemStack> activateRiptide(PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);
        if (itemStack.getDamage() >= itemStack.getMaxDamage() - 1) {
            return TypedActionResult.fail(itemStack);
        } else if (!user.isTouchingWaterOrRain()) {
            return TypedActionResult.fail(itemStack);
        } else if (user.getItemCooldownManager().isCoolingDown(itemStack.getItem())) {
            return TypedActionResult.fail(itemStack);
        } else {
            user.setCurrentHand(hand);
            return TypedActionResult.consume(itemStack);
        }
    }

    /**
     * Performs a Riptide. Requires you to be in water.
     * Override and call this in {@link Item#onStoppedUsing}
     *
     * @see TridentItem#onStoppedUsing(ItemStack, World, LivingEntity, int)
     * [VanillaCopy] with only the riptide parts in mind, not the projectile logic
     */
    default void performRiptide(ItemStack stack, World world, LivingEntity entity, int remainingUseTicks) {
        if (entity instanceof PlayerEntity user) {
            int i = MAX_USE_TIME - remainingUseTicks;
            if (i >= 10) {
                float f = EnchantmentHelper.getTridentSpinAttackStrength(stack, user) + TRIDENT_POWER;
                if (!(f > 0.0F) || user.isTouchingWaterOrRain()) {
                    // TridentItem#isAboutToBreak inline
                    if (!(stack.getDamage() >= stack.getMaxDamage() - 1)) {
                        user.incrementStat(Stats.USED.getOrCreateStat(stack.getItem()));
                        if (f > 0.0F) {
                            float g = user.getYaw();
                            float h = user.getPitch();
                            float j = -MathHelper.sin(g * (float) (Math.PI / 180.0)) * MathHelper.cos(h * (float) (Math.PI / 180.0));
                            float k = -MathHelper.sin(h * (float) (Math.PI / 180.0));
                            float l = MathHelper.cos(g * (float) (Math.PI / 180.0)) * MathHelper.cos(h * (float) (Math.PI / 180.0));
                            float m = MathHelper.sqrt(j * j + k * k + l * l);
                            j *= f / m;
                            k *= f / m;
                            l *= f / m;
                            user.addVelocity(j, k, l);
                            user.useRiptide(20, 8.0F, stack);
                            if (user.isOnGround()) {
                                user.move(MovementType.SELF, new Vec3d(0.0, 1.1999999F, 0.0));
                            }

                            user.getItemCooldownManager().set(stack.getItem(), COOLDOWN);
                            world.playSoundFromEntity(null, user, SoundEvents.ITEM_TRIDENT_RIPTIDE_3.value(), SoundCategory.PLAYERS, 1.0F, 1.0F);
                        }
                    }
                }
            }
        }
    }
}
